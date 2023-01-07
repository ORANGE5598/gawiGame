package GamePackage;

import javax.swing.JOptionPane;

public class GawibawiboMain {
	static private String userInput; // 필드 선언
	
	static private PlayerDAO dao; // DAO 에서 대부분의 작업을 수행할 예정임.
	static private PlayerInfo dto; // 사용자의 모든 데이터는 DTO를 사용하여 수행.
	static private GameLogic logic; // 실제 가위바위보 로직 수행
	
	
	////////////////// 이하 객체들은 임시로 생성한 것
	static private Register register; // 임시
	static private changePW changepw; // 임시
	
	

	
	public static void main(String[] args) {
		//afterLogin();
		startMenu();

	}
	
	public static void startMenu() { // 첫 시작시 호출되는 메서드 startMenu()
		userInput = JOptionPane.showInputDialog("1. 로그인하기 2. 사용자 계정 생성 3. 기타 메뉴 보기"); // 사용자에게 입력 받기
		if(userInput.equals("1")) { // 로그인일 경우
			// 로그인창 메서드 호출.. 로그인이 되면 afterLogin() 메서드 호출할것.
		} else if(userInput.equals("2")) { // 계정 생성일 경우
			register = new Register();
			register.inputEmail();
		} else if(userInput.equals("3")) { // 
			afterLogin();
			/* 기타메뉴 메서드 호출
			 * 1.총 플레이어수
			 * 2.승률 1위 플레이어 : id의 끝 3자리는 *** 로 표시 및 승률 표시
			 * 3.승률 꼴찌부터 보기 선택시 승률이 제일 아래인 사용자부터 전체 사용자를 출력해주고
			 * 역시 ID의 끝 3자리는 *** 로 표기합니다.
			 * 4.승률 1위 부터 보기는 반대로 보여주고, ID 끝 3자리는 *** 로 표기합니다.
			 */
			
		} else { // 1 2 3 모두 아닐 경우 재입력 요구
			JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 다시 시도하세요.");
			startMenu();
		}
				
	}
	
	public static void afterLogin() {
		
		userInput = JOptionPane.showInputDialog("1. 게임시작 2. 전적 보기 3. 마지막 로그인 날짜 확인 4. 암호 변경");
		if(userInput.equals("1") ) {
			logic = new GameLogic();
		} else if(userInput.equals("2")) {
			// 전적 보여주기
		} else if(userInput.equals("3")) {
			// 마지막 로그인 날짜
		} else if(userInput.equals("4")) {
			// 암호 변경 
			changepw = new changePW(JOptionPane.showInputDialog("기존 비밀번호를 입력하세요."));
		}
		
	}

}
