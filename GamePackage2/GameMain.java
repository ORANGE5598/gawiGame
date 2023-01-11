package GamePackage2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GameMain {
	PlayerInfo playerinfo = new PlayerInfo();
	GawibawiboMain gawibawibomain = new GawibawiboMain();
	// MatchHistory matchHistory = new MatchHistory();
	private char start;
	int com;
	String name, choice, choice2, readLine;
	boolean result = false;
	
	public GameMain() {
		choiceSomething();
	}

	void choice() { // 게임 시작 여부를 선택하는 메서드

		start = JOptionPane.showInputDialog("게임 다시 하실래용? (y/n)").charAt(0);

		if (start == 'y') {
			gawibawibomain.afterLogin(); // y가 나오면 continueGame()으로 넘어가 게임 메뉴로 넘어감
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

		if (choice.equals("가위") || choice.equals("1")) {
			switch (com) {
			case 1:
				JOptionPane.showMessageDialog(null, "무승부!!");
				playerinfo.getDraw();
				break;

			case 2:
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				playerinfo.getLose();
				break;

			case 3:
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				playerinfo.getWin();
				break;
			}

		} else if (choice.equals("바위") || choice.equals("2")) {
			switch (com) {
			case 1:
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				playerinfo.getWin();
				break;

			case 2:
				JOptionPane.showMessageDialog(null, "무승부!!");
				playerinfo.getDraw();
				break;

			case 3:
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				playerinfo.getLose();
				break;
			}
		} else if (choice.equals("보") || choice.equals("3")) {
			switch (com) {
			case 1:
				JOptionPane.showMessageDialog(null, "패배하였습니다.");
				playerinfo.getLose();
				break;

			case 2:
				JOptionPane.showMessageDialog(null, "승리하였습니다.");
				playerinfo.getWin();
				break;

			case 3:
				JOptionPane.showMessageDialog(null, "무승부!!");
				playerinfo.getDraw();
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null, "잘못된 값입니다. 재입력 바랍니다.");
			choiceSomething();
		}
		playerinfo.getTotal(); // 결과가 올바르게 나왔다면 MatchHistory에 있는 countPlay()로 결과를 보내 게임횟수를 1 추가한다.
		choice();

	}

	private void notPlay() { // 게임 시작 여부에서 n 누르면 호출되는 메서드. 메세지 출력 후 게임 종료
		JOptionPane.showMessageDialog(null, "다음에 다시 찾아주세요.");
		loadNum();
		System.exit(0);
	}

	private void wrongChoice() { // 게임 시작 여부에서 잘못된 값 입력시 호출되는 메서드. 메세지 출력 후 다시 게임 시작 여부 메서드로 되돌려줌
		JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 재입력 바랍니다.");
		choiceSomething();
	}

	// id.dat로부터 count, win 등 값 가져오는 메서드
	private void loadNum() {
		String id = "Wjdduddn1324";
		String idFile = id + ".dat";
		String path = "C:\\userData";
		String nthLine1, nthLine2, nthLine3, nthLine4, nthLine5, subLine1, subLine2, subLine3, subLine4;
		String changeLine1, changeLine2, changeLine3, changeLine4, changeLine5, originalPW, changePW;
		Path path2;
		path2 = Paths.get(path + "\\" + idFile);
		List<String> allLines;
		List<String> list = new ArrayList<>();

		try { // count 추출하는 try~catch
			allLines = Files.readAllLines(path2); // 파일 경로 지정
			nthLine1 = allLines.get(6); // 파일 내용 전체 중 6 라인(첫 번째 줄이 0부터 시작)에 있는 count 줄
			subLine1 = nthLine1.substring(8, nthLine1.length()); // 게임횟수부분만 substring으로 잘라내기
			int count = Integer.parseInt(subLine1); // 게임횟수를 int로 변경

			nthLine2 = allLines.get(7); // 파일 내용 전체 중 6 라인(첫 번째 줄이 0부터 시작)에 있는 count 줄
			subLine2 = nthLine2.substring(6, nthLine2.length()); // 게임횟수부분만 substring으로 잘라내기
			int win = Integer.parseInt(subLine2); // 게임횟수를 int로 변경

			nthLine3 = allLines.get(8); // 파일 내용 전체 중 6 라인(첫 번째 줄이 0부터 시작)에 있는 count 줄
			subLine3 = nthLine3.substring(7, nthLine3.length()); // 게임횟수부분만 substring으로 잘라내기
			int draw = Integer.parseInt(subLine3); // 게임횟수를 int로 변경

			nthLine4 = allLines.get(9); // 파일 내용 전체 중 6 라인(첫 번째 줄이 0부터 시작)에 있는 count 줄
			subLine4 = nthLine4.substring(7, nthLine4.length()); // 게임횟수부분만 substring으로 잘라내기
			int lose = Integer.parseInt(subLine4); // 게임횟수를 int로 변경

			nthLine5 = allLines.get(10); // 파일 내용 전체 중 6 라인(첫 번째 줄이 0부터 시작)에 있는 count 줄
			
			System.out.println(count + win + draw + lose);

			// 현재결과값 + 읽어온 id.dat의 결과값
			int resultCount = count + 1;
			int resultWin = win + 1;
			int resultDraw = draw + 1;
			int resultLose = lose + 1;
			double resultRate = (double)resultWin / (double)resultCount;
			// String으로 변경 후 id.dat에 넣을 문장으로 변경
			String resultCount2 = "count : " + resultCount;
			String resultWin2 = "win : " + resultWin;
			String resultDraw2 = "draw : " + resultDraw;
			String resultLose2 = "lose : " + resultLose;
			String resultRate2 = "winRate : " + resultRate;
			
			File inputFile = new File(path + "\\" + idFile);
			File outputFile = new File(path + "\\" + id + ".backup");

			FileInputStream fis = null;
			BufferedReader br = null;
			FileOutputStream fos = null;
			BufferedWriter bw = null;

			try {
				fis = new FileInputStream(inputFile);
				fos = new FileOutputStream(outputFile);
				br = new BufferedReader(new InputStreamReader(fis));
				bw = new BufferedWriter(new OutputStreamWriter(fos));

				while ((readLine = br.readLine()) != null) {
					list.add(readLine);
					changeLine1 = readLine.replace(nthLine1, resultCount2);
					changeLine2 = readLine.replace(nthLine2, resultWin2);
					changeLine3 = readLine.replace(nthLine3, resultDraw2);
					changeLine4 = readLine.replace(nthLine4, resultLose2);
					changeLine5 = readLine.replace(nthLine5, resultRate2);
					bw.write(changeLine1, 0, changeLine1.length());
					bw.write(changeLine2, 0, changeLine2.length());
					bw.write(changeLine3, 0, changeLine3.length());
					bw.write(changeLine4, 0, changeLine4.length());
					bw.write(changeLine5, 0, changeLine5.length());
					bw.newLine();
				}

				result = true;

				br.close();
				bw.close();

				if(result) {
					inputFile.delete();
					outputFile.renameTo(new File(path + "\\" + idFile));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 파일 내용 전체 읽어오기

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
