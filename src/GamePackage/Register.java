package GamePackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Register {
	
	GawibawiboMain game;
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
			inputPassWord();	// 암호 입력 메서드 호출
		}else {
			JOptionPane.showMessageDialog(null, "사용할 수 없는 ID 입니다. (중복된 ID)");
			inputEmail();	
		}
	}	// idDuple()의 끝
	
	private void makeFile() {	// 중복된 ID가 없을 때 파일 생성하는 메서드

		fileName = id + ".dat";
		file = new File(folder,fileName);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat firstDate = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss"); // yyyy-MM-dd apm HH:mm:dd 잘못 적어주신듯
		
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			PrintStream ps = new PrintStream(fos);
			ps.println("regDate : " + firstDate.format(cal.getTime()));
			ps.println("email : " + mem_Email);
			ps.println("ID : " + id);
			ps.println("password : " + mem_Password);
			ps.println("count : " + 0);
			ps.println("win : " + 0);
			ps.println("draw : " + 0);
			ps.println("lose : " + 0);
			ps.println("winrate : " + 0);
			ps.close();
		} catch (FileNotFoundException e) {
			
		}
		
		finReg();	// 회원가입 완료 메서드 호출
	}	// makeFile()의 끝
	
	private void inputPassWord() {		// 암호를 입력하는 메서드
		this.mem_Password = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");
		if (mem_Password.equals("")) {
			JOptionPane.showMessageDialog(null, "아무것도 입력하지 않았습니다.");
			inputPassWord();
		}
		if ((mem_Password.length() >= 8) && (mem_Password.length() <= 20)) {	// 길이가 8~20인지 검사
			makeFile();	// Id.dat 생성하는 메서드 호출
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호가 8~12 글자인지 확인 하세요.");
			inputPassWord();
		}
		
	}	// inputPassWord()의 끝
	
	private void finReg() {	// 회원가입 성공 시 이동되어 가입축하 메세지 출력 후 게임 시작 전 메뉴 호출하는 메서드
		JOptionPane.showMessageDialog(null, "축하드립니다" + mem_Email + "님 회원가입 되었습니다.");
		//game = new GawibawiboMain();	// 게임 메뉴가 있는 클래스 호출
		game.startMenu();
	}
	
	/////// 가입 시 사용된 이메일, 패스워드 get.
	public String getEmail() {
		return mem_Email;
	}
	
	public String getPassword() {
		return mem_Password;
	}
	
	public void setPassword(String passwd) {
		this.mem_Password = passwd;
	}
	
}