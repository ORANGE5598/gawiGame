package GamePackage2;

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

class UserLogin implements ActionListener {

	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton reBtn = new JButton("Reset");
	JTextField txtID = new JTextField();
	JPasswordField txtPW = new JPasswordField();
	JLabel userIDlabel = new JLabel("Email : ");
	JLabel userPWlabel = new JLabel("password : ");
	
	
	Date now = new Date(System.currentTimeMillis());
	SimpleDateFormat simple = new SimpleDateFormat("(a hh:mm)");

	int i = 0;
	private boolean flag;
	private static String filePath = "C:\\userData";
	private static File file = new File(filePath);

	PlayerInfo dto;
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	void loginFrame() {

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
			}else if (ID.length() == 0 && password.length() == 0) {
				JOptionPane.showMessageDialog(null, "Email & Password 를 입력하지 않으셨습니다.", "User Not Found.",
						JOptionPane.DEFAULT_OPTION);
				return;
			}

			File[] fileList = file.listFiles();
			String username = txtID.getText(); // 텍스트필드에 입력한값을 가져옴
			String userpass = txtPW.getText();
			dto = new PlayerInfo(System.currentTimeMillis(), username, password);
			
			String id = dto.getuserId()+".dat";
			String pw = null;
			File Player = null;

			for (int i = 0; i < fileList.length; i++) {
				Player = fileList[i];
				if (Player.getName().equals(id)) {
					flag = true;
					break;
				}
			}

			try (BufferedReader br = new BufferedReader(new FileReader(Player.getAbsolutePath()))) {
				String str = null;

					while ((str = br.readLine()) != null) {
						if (str.startsWith("password")) {
							userpass = str.substring(str.indexOf(":") + 2, str.length());

							while (i < 3) { 
							if (userpass.equals(dto.getPassword())) {
								flag = true;
								JOptionPane.showMessageDialog(null, "반갑습니다" + username + "님 즐거운하루되세요 로그인시간:"+ simple,
										"WELLCOM!!" + username, JOptionPane.PLAIN_MESSAGE);
								
								fr.close();
								br.close();
								GawibawiboMain.afterLogin();
								System.exit(0);
								break;
								
							} else {
								JOptionPane.showMessageDialog(null, "로그인" + i + "회 오류!", "Login_WARNING",
										JOptionPane.WARNING_MESSAGE);
								i++;
								return;
							}
						}
					}
					if (i == 3) {
						JOptionPane.showMessageDialog(null, i + "회 오류로 시스템을 종료합니다.", "관리자 연락요망",
								JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}

				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "File Read Error!", JOptionPane.WARNING_MESSAGE);
			}
			return;

		}
	}

}
