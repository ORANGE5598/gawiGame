package GamePackage2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventObject;

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

public class Login implements ActionListener {
	// GUI
	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton reBtn = new JButton("Reset");
	JTextField txtUser = new JTextField();
	JPasswordField txtPW = new JPasswordField();
	JLabel userIDlabel = new JLabel("Email : ");
	JLabel userPWlabel = new JLabel("password : ");

	int i = 0;
	private static String filePath = "C:\\userData";
	private static File file = new File(filePath);
	private boolean flag;
	
	PlayerInfo player;
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;

	//public void Login() {
	//}

	void showFrame() {
		userIDlabel.setBounds(50, 100, 75, 25);
		userPWlabel.setBounds(50, 150, 75, 25);

		txtUser.setBounds(125, 100, 200, 25);
		txtPW.setBounds(125, 150, 200, 25);

		loginBtn.setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);

		reBtn.setBounds(225, 200, 100, 25);
		reBtn.setFocusable(false);
		reBtn.addActionListener(this);

		frame.add(userIDlabel);
		frame.add(userPWlabel);
		frame.add(txtUser);
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
		
			String username = txtUser.getText();
			String password = String.valueOf(txtPW.getPassword());
			
			if (e.getSource() == reBtn) {
				//리셋버튼눌렀을때 다 지워짐
				txtUser.setText("");
				txtPW.setText("");
			}
			
			//로그인 버튼눌렀을때
			if (e.getSource() == loginBtn) {
				if(username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog
					(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", 
							"File Not Found!", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		
			flag = true;
			File[] fileList = file.listFiles();
			String id = player.getuserId() + ".dat";
			String pw = null;
			System.out.println(id + " 아이디");

			File Player = null;

			for(int i = 0; i < fileList.length; i++) {
				Player = fileList[i];
				System.out.println(Player.getName() + " 플레이어");
				if(Player.getName().equals(id)) {
					System.out.println(Player.getName());
					flag = true;
					break;

				}
			}

			try {
					fr = new FileReader(Player.getAbsolutePath());
					br = new BufferedReader(fr);
					String str = null;
					while((str = br.readLine()) !=null)  {
						str = str.trim();

						if (str.startsWith("password")) {
							pw = str.substring(str.indexOf(":") + 2, str.length());

							while(i<3) {
								if(pw.equals(player.getPassword())) {
									flag = true;
									JOptionPane.showMessageDialog(null,"반갑습니다" + id + "님 , 즐거운 하루되세요",
											"WELLCOM!!",JOptionPane.PLAIN_MESSAGE);
									fr.close();
									br.close();
									GawibawiboMain.afterLogin();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "로그인"+i+"회 오류!", 
											"Login_WARNING", JOptionPane.WARNING_MESSAGE);
									i++;

								}}
						}
						if(i==3) {
							JOptionPane.showMessageDialog(null, i+"회 오류로 시스템을 종료합니다.", 
									"관리자 연락요망", JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}



					}
			}catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), 
								"File Read Error!", JOptionPane.WARNING_MESSAGE);
					}
			return;
		}

		
	}


