package daejin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class GameMain extends GawibawiboMain {
	GawibawiboMain gawibawibomain = new GawibawiboMain();
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
		
		loadNum();	// 게임이 종료되었으므로 결과값들을 파일에 저장하는 메서드 호출
	}

	private void wrongChoice() { // 게임 시작 여부에서 잘못된 값 입력시 호출되는 메서드. 메세지 출력 후 다시 게임 시작 여부 메서드로 되돌려줌
		JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 재입력 바랍니다.");
		choiceSomething();
	}

	// id.dat로부터 count, win 등 값 가져오는 메서드
	
	public void loadNum() {
		String id = dto.getuserId();		// dto dao 이용해서 로그인 한 id값을 불러오도록 수정해야함. 필요하다면 메서드 전체를 dao 클래스로 보내서 해당 클래스에서 작동시키기
		String path = "C:\\userData";
		Path path2 = Paths.get(path + "\\" + id + ".dat");
		File inFile = new File(path + "\\" + id + ".dat");
		File outFile = new File(path + "\\" + id + ".backup");
		String rep = "";
		String nthLine1, nthLine2, nthLine3, nthLine4, nthLine5, nthLine6, nthLine7, subLine1, subLine2, subLine3, subLine4;
		
		//
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
			
			// String 타입인 파일의 내용을 한 줄씩 읽어서 list에 담는다.
			List<String> allLines;
			allLines = Files.readAllLines(path2); // 파일 경로 지정
			nthLine1 = allLines.get(6); // count line 가져오기
			subLine1 = nthLine1.substring(8, nthLine1.length()); // count 값만 substring으로 잘라내기
			nthLine2 = allLines.get(7); // win line 가져오기
			subLine2 = nthLine2.substring(6, nthLine2.length());
			nthLine3 = allLines.get(8); // draw line 가져오기
			subLine3 = nthLine3.substring(7, nthLine3.length());
			nthLine4 = allLines.get(9); // lose line 가져오기
			subLine4 = nthLine4.substring(7, nthLine4.length());
			nthLine5 = allLines.get(10); // lose line 가져오기
			nthLine6 = allLines.get(1); // logInDate line 가져오기
			nthLine7 = allLines.get(2); // logOutDate line 가져오기
			
			int resultCount = dto.getCount() + Integer.parseInt(subLine1);
			int resultWin = dto.getWin() + Integer.parseInt(subLine2);
			int resultDraw = dto.getDraw() + Integer.parseInt(subLine3);
			int resultLose = dto.getLose() + Integer.parseInt(subLine4);
			double resultWinRate = (double)resultWin / (double)resultCount * 100.00;
			resultWinRate = Double.parseDouble(String.format("%.2f", resultWinRate));
			
			while ((rep = br.readLine()) != null) {		// replace를 여러번 중첩시켜 count,win,draw,lose 값을 원하는 값으로 수정하여 파일에 덮어쓰기
				rep = rep.replace(nthLine6, "logInDate : " + dto.getLoginTime()).replace(nthLine7, "logOutDate : " + dto.getLogoutTime()).replace(nthLine1, "count : " + resultCount).replace(nthLine2, "win : " + resultWin).replace(nthLine3, "draw : " + resultDraw).replace(nthLine4, "lose : " + resultLose).replace(nthLine5, "winRate : " + resultWinRate);
				bw.write(rep + "\r\n");
				bw.flush();
			}
			
			result = true;
			
			bw.close();
			br.close();
			
			if(result) {
				inFile.delete();	// 기존의 id.dat 제거
				outFile.renameTo(new File(path + id + ".dat"));	// id.back을 id.dat로 변경
				
				if(!outFile.renameTo(inFile)) {		// renameTo가 작동 잘 안될때 if문으로 바꾸도록 다시 제어
					FileInputStream fis = new FileInputStream(inFile);
					FileOutputStream fos = new FileOutputStream(outFile);
					
					int read = 0;
					while((read=fis.read()) != -1) {
						fos.write(read);
					}
					fis.close();
					fos.close();
					inFile.delete();
				}
				afterLogin();	// 파일 저장이 끝났으므로 게임메뉴로 돌아감
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
