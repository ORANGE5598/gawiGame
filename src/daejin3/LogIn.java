package daejin2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* 회원가입 시 설정된 암호 맞는지 검사
 * 
 * 로그인시 [' '님 환영합니다] 출력
 * 로그인 오류 : 오류알림 , 3회 재입력 기회 , [ 관리자 연락바람 ] 메세지 출력 후 게임 종료
 */

class LogIn extends GawibawiboMain implements ActionListener {

	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton reBtn = new JButton("Reset");
	JTextField txtID = new JTextField();
	JPasswordField txtPW = new JPasswordField();
	JLabel userIDlabel = new JLabel("Email : ");
	JLabel userPWlabel = new JLabel("password : ");

	Date now = new Date(System.currentTimeMillis());
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
	String date = formatDate.format(now);

	int chance = 4; // 비밀번호 기회
	private boolean flag;
	private static String filePath = "C:\\userData";
	private static File file = new File(filePath);

	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	void loginFrame() {

		frame.setLocationRelativeTo(null);

		userIDlabel.setBounds(50, 100, 75, 25);
		userPWlabel.setBounds(50, 150, 75, 25);

		txtID.setBounds(125, 100, 200, 25);
		txtPW.setBounds(125, 150, 200, 25);

		loginBtn.setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);

		reBtn.setBounds(225, 200, 100, 25);
		reBtn.setFocusable(false);
		reBtn.addActionListener(this);

		frame.add(userIDlabel);
		frame.add(userPWlabel);
		frame.add(txtID);
		frame.add(txtPW);
		frame.add(loginBtn);
		frame.add(reBtn);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 리셋버튼눌렀을때 다 지워짐
		if (e.getSource() == reBtn) {
			txtID.setText("");// 사용자가 공백을 넣었을때 지워짐
			txtPW.setText("");
		}
		// 로그인 버튼눌렀을때
		if (e.getSource() == loginBtn) {

			String ID = txtID.getText();
			String password = String.valueOf(txtPW.getPassword());

			if (ID.length() == 0) {
				JOptionPane.showMessageDialog(null, "Emaile 입력되지 않았습니다.", "You have not entered your Email",
						JOptionPane.DEFAULT_OPTION);
			} else if (password.length() == 0) {
				JOptionPane.showMessageDialog(null, "Password 입력되지 않았습니다.", "You did not enter a password.",
						JOptionPane.DEFAULT_OPTION);
			} else if (ID.length() == 0 && password.length() == 0) {
				JOptionPane.showMessageDialog(null, "Email & Password 를 입력하지 않으셨습니다.", "User Not Found.",
						JOptionPane.DEFAULT_OPTION);
				return;
			}

			File[] fileList = file.listFiles();
			String username = txtID.getText(); // 텍스트필드에 입력한값을 가져옴
			String userpass = txtPW.getText();
			dto = new PlayerInfo(date, username, password);
			dto.setLoginTime(date);
			String id = dto.getuserId() + ".dat";
			File Player = null;
			boolean check = false;// 비밀번호 확인

			for (int i = 0; i < fileList.length; i++) {
				Player = fileList[i];
				if (Player.getName().equals(id)) {
					flag = true;
					break;
				}
			}

			try (BufferedReader br = new BufferedReader(new FileReader(Player.getAbsolutePath()))) {
				String str = null;

				Outer: while ((str = br.readLine()) != null) {
					if (str.startsWith("password")) {
						userpass = str.substring(str.indexOf(":") + 2, str.length());

						while (true) {
							if (userpass.equals(dto.getPassword())) {
								flag = true;
								JOptionPane.showMessageDialog(null, "반갑습니다" + dto.getuserId() + "님 즐거운하루되세요",
										"WELLCOM!!" + id, JOptionPane.PLAIN_MESSAGE);

								frame.setVisible(false);
								br.close();
								GawibawiboMain.afterLogin();
								check = true;
								break Outer;

							} else if (chance != 1) {
								JOptionPane.showMessageDialog(null, "로그인 오류! 기회 :" + (chance - 1), "Login_WARNING",
										JOptionPane.WARNING_MESSAGE);
								chance--;
								return;
							} else if (chance == 1) {
								JOptionPane.showMessageDialog(null, "로그인 3회 오류. 시스템을 종료합니다.", "관리자 연락요망",
										JOptionPane.ERROR_MESSAGE);
								System.exit(0);
							}
						}
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, ex.getMessage(), "File Read Error!", JOptionPane.WARNING_MESSAGE);
			}
			return;
		}

	}
}