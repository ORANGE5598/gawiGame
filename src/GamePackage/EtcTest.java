package GamePackage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class EtcTest {
	String path = "C:\\userData";
	String nthLine, subLine, maskId, input, winRate, userId;
	int winRate2;
	double rate;
	Path path2;
	File file = new File(path);
	String[] fList = file.list();
	ArrayList<String> al;
	List<String> allLines;
	File inputFile = new File(path + "\\" + winRate + ".dat");

	public void startMessage() {
		input = JOptionPane.showInputDialog("1.총플레이어수, 2.승률1위플레이어 3.승률꼴찌부터보기 4.승률1위부터보기");
		if (input.equals("1")) {
			allPlayer();

		} else if (input.equals("2")) {

			rateFirst();

		} else if (input.equals("3")) {

			playerArrays();

		} else if (input.equals("4")) {

			playerReverse();

		} else {
			JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 다시 시도하세요.");
			startMessage();
		}
		startMessage();
	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void allPlayer() {

		File f = new File(path);
		File[] files = f.listFiles();
		int count = 0;
		{
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					count++;
				}
			}
			JOptionPane.showMessageDialog(null, "참가자의 수 : " + count + "명");

		} // for문 마무리
	}// allplayer end
//----------------------------------------------------------------------------------------------------

	private void rateFirst() {	// 승률 1위 출력하는 메서드
		
		al = new ArrayList<String>();
		
		for (int i = 0; i < fList.length; i++) {	// 루프를 돌려 파일을 순차적으로 읽어내기
			path2 = Paths.get(path + "\\" + fList[i]);	// 폴더 경로 + \\ + 파일이름으로 파일경로 지정

			try {
				allLines = Files.readAllLines(path2);	// 파일 내용 전체 읽어오기
				nthLine = allLines.get(10);			// 파일 내용 전체 중 11 라인(첫 번째 줄이 0부터 시작)에 있는 winRate 줄
				subLine = nthLine.substring(10, nthLine.length());	// 승률부분만 substring으로 잘라내기
				rate = Double.parseDouble(subLine);		// 승률을 double로 변경
				maskId = fList[i].substring(0, fList[i].length()-7) + "***";	// id.dat의 뒤 7자리까지 잘라서 id뒷3자리 자른 후 *** 붙이기
				al.add(rate + ":" + maskId);
			} catch (IOException e) {
				System.out.println("예외 발생");
			}
		}	// for문의 끝

		Collections.sort(al, Collections.reverseOrder());	// ArrayList al을 내림차순으로 정렬
		JOptionPane.showMessageDialog(null, al.get(0).substring(al.get(0).indexOf(':')+1));	// 1등(0번 index)의 이름만 subString으로 잘라서 출력
	}	// rateFirst()의 끝

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void playerArrays() {	// 승률 오름차순으로 꼴등부터 출력하는 메서드
		
		al = new ArrayList<String>();
		for (int i = 0; i < fList.length; i++) {
			path2 = Paths.get(path + "\\" + fList[i]);

			try {
				allLines = Files.readAllLines(path2);
				nthLine = allLines.get(10);
				subLine = nthLine.substring(10, nthLine.length());
				rate = Double.parseDouble(subLine);		// 승률을 double로 변경
				
				maskId = fList[i].substring(0, fList[i].length()-7) + "***";
				al.add(rate + ", " + maskId + "\r\n");	// arrayList에 승률과 이름 입력
				
				
			} catch (IOException e) {
				System.out.println("예외 발생");
			}	// try~catch의 끝
		}	// for문의 끝
		
		Collections.sort(al);	// ArrayList al을 오름차순으로 정렬
		JOptionPane.showMessageDialog(null, al);	// 정렬한 내용을 출력
		
		startMessage();		// 출력 이후 다시 메뉴로 돌아감
		
	}	// playerArrays()의 끝

	private void playerReverse() {	// 승률내림차순으로 1등부터 출력하는 메서드
		
		al = new ArrayList<String>();
		for (int i = 0; i < fList.length; i++) {
			path2 = Paths.get(path + "\\" + fList[i]);

			try {
				allLines = Files.readAllLines(path2);
				nthLine = allLines.get(10);
				subLine = nthLine.substring(10, nthLine.length());
				rate = Double.parseDouble(subLine);		// 승률을 double로 변경
				
				maskId = fList[i].substring(0, fList[i].length()-7) + "***";
				al.add(rate + ", " + maskId + "\r\n");	// arrayList에 승률과 이름 입력
				
				
			} catch (IOException e) {
				System.out.println("예외 발생");
			}	// try~catch의 끝
		}	// for문의 끝
		
		Collections.sort(al, Collections.reverseOrder());	// ArrayList al을 내림차순으로 정렬
		JOptionPane.showMessageDialog(null, al);	// 정렬한 내용을 출력
		
		startMessage();		// 출력 이후 다시 메뉴로 돌아감

	}

}