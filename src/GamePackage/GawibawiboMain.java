package GamePackage;

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
			// 계정생성 메서드 호출
			Register regi = new Register();
			regi.inputEmail();
		} else if(userInput.equals("3")) {
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

}
