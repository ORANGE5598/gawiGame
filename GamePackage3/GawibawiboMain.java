package GamePackage2;

import javax.swing.JOptionPane;

public class GawibawiboMain {
	static private String userInput; // 필드 선언
	static private String email;
	static private String password;
	static private PlayerDAO dao; // DAO 에서 대부분의 작업을 수행할 예정임.
	static private PlayerInfo dto; // 사용자의 모든 데이터는 DTO를 사용하여 수행.
	static private Register register;
	static private ChangePW changepw;
	static private Etc etc;
	

	
	public static void main(String[] args) {
		startMenu();

	}
	
	public static void startMenu() { // 첫 시작시 호출되는 메서드 startMenu()
		
		userInput = JOptionPane.showInputDialog("1. 로그인하기 2. 사용자 계정 생성 3. 기타 메뉴 보기 4. 종료"); // 사용자에게 입력 받기
		if(userInput.equals("1")) { // 로그인일 경우
			LogIn logIn = new LogIn();
			logIn.loginFrame();
//			login();
		} else if(userInput.equals("2")) { // 계정 생성일 경우
			register = new Register();
			register.inputEmail();
		} else if(userInput.equals("3")) {	// 기타 메뉴 보기일 경우
			etc = new Etc();
			etc.startMessage();
		} else if(userInput.equals("4")) {	// 종료할 경우
			JOptionPane.showMessageDialog(null, "종료합니다.");
			System.exit(0);
		}
		
		else { // 1 2 3 모두 아닐 경우 재입력 요구
			JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 다시 시도하세요.");
			startMenu();
		}
				
	}
	
	public static void login() {
		email = JOptionPane.showInputDialog("이메일을 입력하세요.");
		password = JOptionPane.showInputDialog("패스워드를 입력하세요.");
		dto = new PlayerInfo(System.currentTimeMillis(), email, password);
		
		System.out.println(dto.getEmail());
		System.out.println(dto.getPassword());
		new PlayerDAO().check(dto);
		afterLogin();
		
	}
	
	public static void afterLogin() {
		
		dao = new PlayerDAO();
		userInput = JOptionPane.showInputDialog("1. 게임시작 2. 전적 보기 3. 마지막 로그인 날짜 확인 4. 암호 변경 5. 로그아웃");
		
		if(userInput.equals("1")) {
			GameMain gamemain = new GameMain();
			gamemain.choiceSomething();
			
		} else if(userInput.equals("2")) {
			PlayerInfo playerinfo = new PlayerInfo();
			String result = playerinfo.printStats();
			JOptionPane.showMessageDialog(null, result);
			
			// 전적 보여주기
		} else if(userInput.equals("3")) {
			// 마지막 로그인 날짜
			// 미완성
		} else if(userInput.equals("4")) {
			// 암호 변경 
			changepw = new ChangePW(dto.getEmail(), dto.getPassword(), dto.getuserId());
			changepw.inputPW();
		} else if(userInput.equals("5")) {
			// 로그아웃
			startMenu();
		}
		
	}

	

}
	