package daejin2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JOptionPane;

// Data Access Object
public class PlayerDAO extends GawibawiboMain {
	// 필드 정의
	private PlayerInfo player; // DTO
	boolean result = false;
	private static String filePath = "C:\\userData"; // 경로
	private static File file = new File(filePath);
	private static String dat = ".dat";
	public String count, win, draw, lose, total, winrate, logInDate;
	FileInputStream fis;
	FileOutputStream fos;
	FileWriter fw;
	BufferedWriter bw;
	BufferedReader br;

	// 생성자 정의
	public PlayerDAO() {

	}

	public PlayerDAO(PlayerInfo player) {
		this.player = player;
	}

	public void readInfo() {

		String id = dto.getuserId();
		File inputFile = new File(filePath + "\\" + id + dat); // 유저 id와 일치하는 dat 파일
		File[] fileList = file.listFiles(); // userdata 폴더 내 파일 리스트 배열

		File Player = null;
		for (int i = 0; i < fileList.length; i++) {
			Player = fileList[i];
			if (Player.getName().equals(id + dat)) {
				break;
			}
		}
		try {
			fis = new FileInputStream(inputFile);
			br = new BufferedReader(new InputStreamReader(fis));
			String str = null;
			while ((str = br.readLine()) != null) {
				str = str.trim();
				if (str.contains("count : ")) {
					count = str.substring(str.indexOf(":") + 2, str.length());
				} else if (str.contains("win : ")) {
					win = str.substring(str.indexOf(":") + 2, str.length());
				} else if (str.contains("draw : ")) {
					draw = str.substring(str.indexOf(":") + 2, str.length());
				} else if (str.contains("lose : ")) {
					lose = str.substring(str.indexOf(":") + 2, str.length());
				} else if (str.contains("winRate : ")) {
					winrate = str.substring(str.indexOf(":") + 2, str.length());
				}
			}
//			fr.close();
			br.close();

		} catch (Exception e) {

		}

		JOptionPane.showMessageDialog(null,
				"총 판수 : " + count + "\n승 : " + win + "\n무 : " + draw + "\n패 : " + lose + "\n승률 : " + winrate);
		afterLogin();
	}

	public void loadNum() { // 게임 그만하기를 선택 했을 때 id.dat 파일에 게임 기록을 저장(write)하는 메서드
		String id = dto.getuserId();
		String path = "C:\\userData";
		Path file = Paths.get(path + "\\" + id + ".dat");
		File inFile = new File(path + "\\" + id + ".dat");
		File outFile = new File(path + "\\" + id + ".txt");
		String rep = "";
		String nthLine1, nthLine2, nthLine3, nthLine4, nthLine5, nthLine6, nthLine7, subLine1, subLine2, subLine3,
				subLine4;

		try {
			BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
			BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));

			// String 타입인 파일의 내용을 한 줄씩 읽어서 list에 담는다.
			List<String> allLines;
			allLines = Files.readAllLines(file); // 파일 경로 지정
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
			double resultWinRate = (double) resultWin / (double) resultCount * 100.00;
			resultWinRate = Double.parseDouble(String.format("%.2f", resultWinRate));

			while ((rep = br2.readLine()) != null) { // replace를 여러번 중첩시켜 count,win,draw,lose 값을 원하는 값으로 수정하여 파일에 덮어쓰기
				rep = rep.replace(nthLine6, "logInDate : " + dto.getLoginTime())
						.replace(nthLine7, "logOutDate : " + dto.getLogoutTime())
						.replace(nthLine1, "count : " + resultCount).replace(nthLine2, "win : " + resultWin)
						.replace(nthLine3, "draw : " + resultDraw).replace(nthLine4, "lose : " + resultLose)
						.replace(nthLine5, "winRate : " + resultWinRate);
				bw2.write(rep + "\r\n");
				bw2.flush();
			}

			result = true;

			bw2.close();
			br2.close();

//			Path newFilePath = Files.move(oldfile, newfile, StandardCopyOption.REPLACE_EXISTING);
//			System.out.println(newFilePath);

			if (result) {
				inFile.delete(); // 기존의 id.dat 제거
				outFile.renameTo(inFile); // id.back을 id.dat로 변경

				// backup파일 지우기 위해 추가한 내용//
				byte[] buff = new byte[1024];
				FileInputStream fin = null;
				FileOutputStream fout = null;

				if (!(outFile.renameTo(inFile))) {
					buff = new byte[1024];
					fin = new FileInputStream(outFile);
					fout = new FileOutputStream(inFile);

					int read = 0;
					if (outFile.exists()) {
						while ((read = fin.read(buff, 0, buff.length)) != -1) {
							fout.write(buff, 0, read);
						}
					}
					fin.close();
					fout.close();
					outFile.delete();
				} else {
					afterLogin();
				}
				//
				afterLogin(); // 파일 저장이 끝났으므로 게임메뉴로 돌아감
			}

		} catch (FileNotFoundException e) {
			afterLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readLoginDate() { // 로그인 시간 출력하는 메서드
		JOptionPane.showMessageDialog(null, dto.getLoginTime());
		afterLogin();
	}

}