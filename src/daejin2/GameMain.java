package daejin2;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class GameMain extends GawibawiboMain {
	PlayerDAO dao;
	private char start;
	int com;
	int count, win, draw, lose;
	String name, choice, choice2, readLine;
	boolean result = false;
	
	public GameMain() {
		choiceSomething();
	}

	void choice() { // 게임 시작 여부를 선택하는 메서드

		start = JOptionPane.showInputDialog("게임 다시 하실래용? (y/n)").charAt(0);

		if (start == 'y') {
			choiceSomething(); // y가 나오면 continueGame()으로 넘어가 게임 메뉴로 넘어감
		} else if (start == 'n') {
			notPlay(); // n이 나오면 notPlay()로 넘어가 게임종료
		} else {
			wrongChoice(); // 그 외의 문자가 입력되면 wrongChoice()로 넘어가서 다시 choice()로 되돌아옴
		}
	}

	void choiceSomething() { // 가위,바위,보(1,2,3)를 입력하고나면 컴퓨터가 낼 1~3까지 정수인 난수 생성 후 playGame()으로 넘어감

		choice = JOptionPane.showInputDialog("가위(1), 바위(2), 보(3) 중 하나를 선택하여 입력하세요.");
		com = (int) Math.round((Math.random() * 2) + 1);
		playGame();
	}

	private void playGame() { // 내가 입력한 값과 컴퓨터가 낸 값을 비교하여 승패 비교 후 결과에 맞게 MatchHistory 클래스에 있는 메서드로 보냄
		count++;
		if (choice.equals("가위") || choice.equals("1")) {
			switch (com) {
			case 1:
				draw++;
				JOptionPane.showMessageDialog(null, "무승부!!");
				dto.setDraw(draw);
				break;

			case 2:
				lose++;
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				dto.setLose(lose);
				break;

			case 3:
				win++;
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				dto.setWin(win);
				break;
			}

		} else if (choice.equals("바위") || choice.equals("2")) {
			switch (com) {
			case 1:
				win++;
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				dto.setWin(win);
				break;

			case 2:
				draw++;
				JOptionPane.showMessageDialog(null, "무승부!!");
				dto.setDraw(draw);
				break;

			case 3:
				lose++;
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				dto.setLose(lose);
				break;
			}
		} else if (choice.equals("보") || choice.equals("3")) {
			switch (com) {
			case 1:
				lose++;
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				dto.setLose(lose);
				break;

			case 2:
				win++;
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				dto.setWin(win);
				break;

			case 3:
				draw++;
				JOptionPane.showMessageDialog(null, "무승부!!");
				dto.setDraw(draw);
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null, "잘못된 값입니다. 재입력 바랍니다.");
			choiceSomething();
		}
		dto.setCount(count); // 결과가 올바르게 나왔다면 MatchHistory에 있는 countPlay()로 결과를 보내 게임횟수를 1 추가한다.
		choice();

	}

	private void notPlay() { // 게임 시작 여부에서 n 누르면 호출되는 메서드. 메세지 출력 후 게임 종료
		JOptionPane.showMessageDialog(null, "다음에 다시 찾아주세요.");
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		Date now = new Date(System.currentTimeMillis());
		String date = formatDate.format(now);
		dto.setLogoutTime(date);
		
		dao = new PlayerDAO();
		dao.loadNum();	// 게임이 종료되었으므로 결과값들을 파일에 저장하는 메서드 호출
	}

	private void wrongChoice() { // 게임 시작 여부에서 잘못된 값 입력시 호출되는 메서드. 메세지 출력 후 다시 게임 시작 여부 메서드로 되돌려줌
		JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 재입력 바랍니다.");
		choiceSomething();
	}

}
