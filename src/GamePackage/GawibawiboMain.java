package GamePackage;

import javax.swing.JOptionPane;

public class GawibawiboMain {
	private static String userInput; // 필드 선언
	private static int gameResult;
	
	private static String email;
	private static String password;
	private static boolean flag = false;
	
	private static PlayerDAO dao; // DAO 에서 대부분의 작업을 수행할 예정임.
	private static PlayerInfo dto; // 사용자의 모든 데이터는 DTO를 사용하여 수행.
	private static GameLogic logic; // 실제 가위바위보 로직 수행
	
	
	////////////////// 이하 객체들은 임시로 생성한 것
	private static Register register; // 임시
	private static changePW changepw; // 임시
	
	

	
	public static void main(String[] args) {
		startMenu();

	}
	
	public static void startMenu() { // 첫 시작시 호출되는 메서드 startMenu()
		
		userInput = JOptionPane.showInputDialog("1. 로그인하기 2. 사용자 계정 생성 3. 기타 메뉴 보기"); // 사용자에게 입력 받기
		if(userInput.equals("1")) { // 로그인일 경우
			login();
		} else if(userInput.equals("2")) { // 계정 생성일 경우
			register = new Register();
			register.inputEmail();
		} else if(userInput.equals("3")) { // 
			afterLogin();
			
		} else { // 1 2 3 모두 아닐 경우 재입력 요구
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
		
	}
	
	public static void afterLogin() {
		dao = new PlayerDAO();
		userInput = JOptionPane.showInputDialog("1. 게임시작 2. 전적 보기 3. 마지막 로그인 날짜 확인 4. 암호 변경");
		
		if(userInput.equals("1") ) {
			playGame();
		} else if(userInput.equals("2")) {
			// 전적 보여주기
		} else if(userInput.equals("3")) {
			// 마지막 로그인 날짜
		} else if(userInput.equals("4")) {
			changepw = new changePW();
			changepw.inputPW();

			
		}
		
	}
	
	public static void playGame() {
		logic = new GameLogic();
		gameResult = logic.getResult(); // 게임 결과값 받아옴
		if(gameResult == 0) { // 0 승 1 패 2 무
			dto.getWin();
		} else if (gameResult == 1) {
			dto.getLose();
		} else if (gameResult == 2){
			dto.getDraw();
		}
		playAgain();
	}
	
	public static void playAgain() {
		int againAnswer = JOptionPane.showConfirmDialog(null, "다시 할래요?");
		if (againAnswer == 0) {
			playGame();
		} else {
			afterLogin();
		}
	}

}
	
	
