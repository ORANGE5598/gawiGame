package GamePackage;


import java.io.File;

import javax.swing.JOptionPane;

public class GawibawiboMain {
	private static String userInput; // 필드 선언
	private static int gameResult;
	
	private static String email;
	private static String password;
	private static boolean flag = false;
	
	private static PlayerDAO dao; // DAO 에서 대부분의 작업을 수행할 예정임.
	protected static PlayerInfo dto; // 사용자의 모든 데이터는 DTO를 사용하여 수행.
	private static GameLogic logic; // 실제 가위바위보 로직 수행
	
	////////////////// 이하 객체들은 임시로 생성한 것
	private static Register register; // 임시
	private static ChangePW changepw; // 임시
	private static EtcTest etc;
	
	

	
	public static void main(String[] args) {
		startMenu();

	}
	
	public static void startMenu() { // 첫 시작시 호출되는 메서드 startMenu()
		
		userInput = JOptionPane.showInputDialog("1. 로그인하기 2. 사용자 계정 생성 3. 기타 메뉴 보기 4. 종료"); // 사용자에게 입력 받기
		if(userInput.equals("1")) { // 로그인일 경우
			UserLogin login = new UserLogin();
			login.loginFrame();
		} else if(userInput.equals("2")) { // 계정 생성일 경우
			register = new Register();
			register.inputEmail();
		} else if(userInput.equals("3")) { // 
			etc = new EtcTest();
			etc.startMessage();
		} else if(userInput.equals("4")) {
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
		
		System.out.println(dto.getEmail());
		System.out.println(dto.getPassword());
		System.out.println(dto.getuserId());
		
		if(userInput.equals("1") ) {
			logic = new GameLogic();
			gameResult = logic.getResult(); // 게임 결과값 받아옴
			dto.plusCnt(gameResult); // 값 plus.
			
		} else if(userInput.equals("2")) {
			// 전적을 받아와야함.. 기존 파일에서 전적을 읽을 것이기 때문에 
			// 파일에서 아이디를 구분할 수 있도록 userid, 승무패, 토탈게임수, 승률 등의 정보가  
			// dto 안에 있으므로 활용해야함.
			dao.readInfo();
			
		} else if(userInput.equals("3")) {
			// 마지막 로그인 날짜
			
		} else if(userInput.equals("4")) {	// 암호 변경 
			changepw = new ChangePW(dto.getEmail(), dto.getPassword(), dto.getuserId());
			changepw.inputPW();
			
		} else if(userInput.equals("5")) {
			// 로그아웃
			startMenu();
			
		}
	}
	
	public static void showStats() {
		String filePath = "C:\\userData";
		String dat = ".dat";
		String id = dto.getuserId();
		File inputFile = new File(filePath + "\\" + id + dat); // 유저 id와 일치하는 dat 파일 
		File outputFile = new File(filePath + "\\" + id + ".backup");
		
		String userId = dto.getuserId(); // 유저 id 값
		String win = Integer.toString(dto.getWin()); // 승
		String draw = Integer.toString(dto.getDraw()); // 무
		String lose = Integer.toString(dto.getLose()); // 패
		String total = Integer.toString(dto.getTotal()); // 총판수
		String winrate = dto.getWinrate();
		
	}

}