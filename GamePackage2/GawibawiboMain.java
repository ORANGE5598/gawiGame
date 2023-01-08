package GamePackage2;

import javax.swing.JOptionPane;

public class GawibawiboMain {
	static private String userInput;
	
	public static void main(String[] args) {
		startMenu();

	}
	
	public static void startMenu() { // 첫 시작시 호출되는 메서드 startMenu()
		userInput = JOptionPane.showInputDialog("1. 로그인하기 2. 사용자 계정 생성 3. 기타 메뉴 보기"); // 사용자에게 입력 받기
		if(userInput.equals("1")) { // 로그인일 경우
			
		} else if(userInput.equals("2")) { // 계정 생성일 경우
			Register regi = new Register();
			regi.inputEmail();
		} else if(userInput.equals("3")) {
			Etc2 etc = new Etc2();
			etc.startMessage();
		} else { // 1 2 3 모두 아닐 경우 재입력 요구
			JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 다시 시도하세요.");
			startMenu();
		}
				
	}

}