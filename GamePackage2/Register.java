package GamePackage2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Register {
	
//	GawibawiboMain game;
	String mem_Email, id, mem_Password, fName, fileName;
	char[] chEmail;
	File folder, file;
	
	public void inputEmail() {	// 가입할 email 입력 메서드
		
		try {
			this.mem_Email = JOptionPane.showInputDialog("Email을 입력 해주세요.");
			if (mem_Email.equals("")) {
				JOptionPane.showMessageDialog(null, "아무것도 입력하지 않았습니다.");
				inputEmail();
			}else if(mem_Email.indexOf('@') == -1){
				JOptionPane.showMessageDialog(null, "잘못된 이메일 형식입니다. (@ 빠짐)");
				inputEmail();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "종료합니다.");
			System.exit(0);
		}	// try~catch의 끝
		
		id = mem_Email.substring(0, mem_Email.indexOf('@'));	// 입력한 email에서 @ 이전까지만 잘라냄
		if ((id.length() >= 6) && (id.length() <= 12)) {	// 길이가 6~12인지 검사
			checkUpper();
		}else {
			JOptionPane.showMessageDialog(null, "ID가 8~12 글자인지 확인 하세요.");
			inputEmail();
		}
	}	// inputEmail()의 끝
	
	private void checkUpper() {		// 	// email 첫문자열이 대문자인지 검사하는 메서드
		if (Character.isUpperCase(id.charAt(0)) == true) {
			checkIdNum();
		}else {
			JOptionPane.showMessageDialog(null, "ID 첫글자는 대문자로 시작해야 합니다.");
			inputEmail();
		}
	}	// checkUpper()

	private void checkIdNum() {		//email에 숫자가 포함되어있는지 검사하는 메서드
		
		chEmail = id.toCharArray();
		for (int i = 0; i < chEmail.length; i++) {
			if (Character.isDigit(chEmail[i])) {
				idDuple();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "email에 숫자가 포함되어야 합니다.");
		inputEmail();
	}	// checkIdNum()의 끝

	private void idDuple() {
		
		fileName = id + ".dat";
		fName = "userData";
//		folder = new File("C:\\Users\\wjddu\\eclipse-workspace\\myjava\\" + fName);
		folder = new File(fName);
		file = new File(folder,fileName);
		
		if (!folder.exists())
			System.out.println(folder.mkdir());
		
		if (!file.exists()) {
			PlayerInfoDTO dto = new PlayerInfoDTO();
			dto.setEmail(mem_Email);
			dto.setId(id);
			inputPassWord();	// 암호 입력 메서드 호출
		}else {
			JOptionPane.showMessageDialog(null, "사용할 수 없는 ID 입니다. (중복된 ID)");
			inputEmail();	
		}
	}	// idDuple()의 끝
	
	private void inputPassWord() {		// 암호를 입력하는 메서드
		this.mem_Password = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");
		if (mem_Password.equals("")) {
			JOptionPane.showMessageDialog(null, "아무것도 입력하지 않았습니다.");
			inputPassWord();
		}
		if ((mem_Password.length() >= 8) && (mem_Password.length() <= 20)) {	// 길이가 8~20인지 검사
			PlayerInfoDTO dto = new PlayerInfoDTO();
			dto.setPassword(mem_Password);
			PlayerDAO dao = new PlayerDAO();
			dao.makeFile();	// Id.dat 생성하는 메서드 호출
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호가 8~12 글자인지 확인 하세요.");
			inputPassWord();
		}
		
	}	// inputPassWord()의 끝

	
}