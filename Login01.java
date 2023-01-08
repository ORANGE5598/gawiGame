package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import project.Register;

public class Login01 implements ActionListener {

	int j = 0;
	GawibawiboMain game;
	File folder, file;
	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton reBtn = new JButton("Reset");
	JTextField userID = new JTextField();
	JPasswordField userPW = new JPasswordField();
	JLabel userIDlabel = new JLabel("Email : ");
	JLabel userPWlabel = new JLabel("password : ");
	JLabel msglabel = new JLabel("");

	public Login01() {
		userIDlabel.setBounds(50, 100, 75, 25);
		userPWlabel.setBounds(50, 150, 75, 25);

		msglabel.setBounds(125, 250, 250, 35);
		msglabel.setFont(new Font(null, Font.ITALIC, 25));

		userID.setBounds(125, 100, 200, 25);
		userPW.setBounds(125, 150, 200, 25);

		loginBtn.setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		loginBtn.addActionListener(this);

		reBtn.setBounds(225, 200, 100, 25);
		reBtn.setFocusable(false);
		reBtn.addActionListener(this);

		frame.add(userIDlabel);
		frame.add(userPWlabel);
		frame.add(msglabel);
		frame.add(userID);
		frame.add(userPW);
		frame.add(loginBtn);
		frame.add(reBtn);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);

	}// Login01End

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = userID.getText().trim();
		String pw = userPW.getText().trim();
		String login = "";
		String fName, fileName;

		// 리셋버튼눌렀을때 다 지워짐
		if (e.getSource() == reBtn) {
			userID.setText("");
			userPW.setText("");
		}

		if (id.length() == 0 || pw.length() == 0) {
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 해주세요.", "로그인폼", JOptionPane.DEFAULT_OPTION);
			return;
		}

		// 텍스트 파일에 저장된 모든 글자를 가져온다
		// 아이디와 비번이 맞는게 있다면 로그인 시켜주면 된다.
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str;
			String line = "";

			// 임시파일명 txtmember
			ArrayList<String> txtmember = new ArrayList<>();

			while ((str = br.readLine()) != null) {
				txtmember.add(str);
			}
			br.close();

			// ID,PW저장
			HashMap<String, String> memberlist = new HashMap<>();

			for (int i = 0; i < txtmember.size(); i++) {

				// "|"구분자 기준으로 잘라 텍스트를 id.txt 에 넣어주기.
				// tempresult [0]= ID tempresult [1]= PW

				String[] tempresult = txtmember.get(i).toString().split("\\|");
				memberlist.put(tempresult[0], tempresult[1]);

			}
			// txt 파일에서 가져온 아이디 비번과 입력한 아이디 비번이 맞는지 확인

			for (String key : memberlist.keySet()) {
				if (!id.equals(key.trim()) && pw.equals(memberlist.get(key))) {
					// 로그인성공!
					login = "성공";
				}
			}
		} catch (Exception errmsg) {
			errmsg.printStackTrace();
		}
		while(j<3)
		if (!login.equals("성공")) {
			JOptionPane.showMessageDialog(null, "비밀번호" + j + "회 잘못입력하셨습니다." , "로그인폼", JOptionPane.DEFAULT_OPTION);
			j++;
			return;
		} else {
			JOptionPane.showMessageDialog(null, "안녕하세요" + id + "님 좋은하루되세요", "로그인폼", JOptionPane.DEFAULT_OPTION);
			break;
		}
		if(j==3) {
			JOptionPane.showMessageDialog(frame, "비밀번호를" + j + "회 잘못입력하여 프로그램 종료합니다.");
			System.exit(0);
		}
	}

	 
		 private void startgame() {	
			 JOptionPane.showMessageDialog(null, "안녕하세요" + userID + "님 좋은하루되세요", "로그인폼", JOptionPane.DEFAULT_OPTION);
				game = new GawibawiboMain();	// 게임 메뉴가 있는 클래스 호출
				game.startMenu();
			
	}
		 public static void main(String[] args) {
			 new GawibawiboMain();
		}
}// class End
