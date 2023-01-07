package GamePackage;

import javax.swing.JOptionPane;

public class GameLogic {
	private String userInput; // 사용자 입력값
	private String comValue; // 컴퓨터 랜덤값
	private String [] hands = {"가위", "바위", "보"};
	private String [] wr = {"WIN", "LOSE", "DRAW"};
	private int gameResult; // 게임 승무패값 0=승 1=패 2=무
	private int wrongCnt;

	public GameLogic() {
		comVal(); // 컴퓨터가 낼 손
	}

	private void comVal() {
		comValue = hands[(int)(Math.random()*hands.length)];
		// 컴퓨터가 낼 손패를 정함. 이제 사용자에게 낼 값을 물어보아야함.
		userVal();
	}

	private void userVal() {
		userInput = JOptionPane.showInputDialog("가위 바위 보 중에 하나 입력");

		if(!userInput.equals(hands[0]) && !userInput.equals(hands[1]) && !userInput.equals(hands[2])) {
			userInput = "";
			wrongCnt++;
			JOptionPane.showMessageDialog(null, wrongCnt + "회 " + "잘못 입력하였습니다.");
			userVal();
			// 유저 승
		} else if (userInput.equals(hands[0]) && comValue.equals(hands[2]) || userInput.equals(hands[1]) && comValue.equals(hands[0]) || userInput.equals(hands[2]) && comValue.equals(hands[1])) {
			gameResult = 0;
			JOptionPane.showMessageDialog(null, wr[0] + "\n유저 : " + userInput + "\n컴퓨터 : " + comValue);
			
			//유저 패
		} else if (userInput.equals(hands[0]) && comValue.equals(hands[1]) || userInput.equals(hands[1]) && comValue.equals(hands[2]) || userInput.equals(hands[2]) && comValue.equals(hands[0])) {
			gameResult = 1;
			JOptionPane.showMessageDialog(null, wr[1] + "\n유저 : " + userInput + "\n컴퓨터 : " + comValue);

			// 무승부
		} else if (userInput.equals(comValue)) { 
			gameResult = 2;
			JOptionPane.showMessageDialog(null, wr[2] + "\n유저 : " + userInput + "\n컴퓨터 : " + comValue);

		}
	}
	
	public int getResult() {
		return gameResult;
	}
}
