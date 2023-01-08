package GamePackage2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Etc2 {
	String path = "C:\\Users\\wjddu\\git\\gawiGame\\userData";
	String input;
	String winRate;
	String userId;
	int winRate2;

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

	private void rateFirst() {
		Path path2;
		TreeMap<Double, String> scores = new TreeMap<Double, String>();
		File file = new File("C:\\Users\\wjddu\\git\\gawiGame\\userData");
		List<String> allLines = null;
		double rate;
		String[] fList = file.list();
		Entry<Double, String> entry = null;
			// 폴더의 파일/폴더 목록을 문자열 배열로 반환
//			String[] fList = file.list();
			// 출력
			for (int i = 0; i < fList.length; i++) {
				path2 = Paths.get(path + "\\" + fList[i]);
//				file = new File(path + "\\" + fList[i]);
				
				try {
					allLines = Files.readAllLines(path2);
					String nthLine;
					String subLine;
					nthLine = allLines.get(10);
					subLine = nthLine.substring(10, nthLine.length());
					rate = Integer.parseInt(subLine);
					scores.put(rate, fList[i]);
				} catch (IOException e) {
					System.out.println("예외 발생");
				}
			}
			entry = scores.lastEntry();
			String first = entry.getValue();
			System.out.println(first.substring(0, first.length()-4));
	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void playerArrays() {
		
		Path path2;
		TreeMap<Double, String> scores = new TreeMap<Double, String>();
		File file = new File("C:\\Users\\wjddu\\git\\gawiGame\\userData");
		List<String> allLines = null;
		double rate;
		String[] fList = file.list();
		Set<Map.Entry<Double, String>> entrySet = scores.entrySet();
			// 폴더의 파일/폴더 목록을 문자열 배열로 반환
//			String[] fList = file.list();
			// 출력
			for (int i = 0; i < fList.length; i++) {
				path2 = Paths.get("C:\\Users\\wjddu\\git\\gawiGame\\userData\\" + fList[i]);
//				file = new File(path + "\\" + fList[i]);
				
				try {
					allLines = Files.readAllLines(path2);
					String nthLine;
					String subLine;
					nthLine = allLines.get(10);
					subLine = nthLine.substring(10, nthLine.length());
					rate = Integer.parseInt(subLine);
					scores.put(rate, fList[i]);
				} catch (IOException e) {
					System.out.println("예외 발생");
				}
			}
			for (Entry<Double, String> entry2 : entrySet) {
				String list = entry2.getValue();
				String subList = list.substring(0, list.length()-4);
				System.out.println(subList);
			}
		startMessage();
	}

	private void playerReverse() {
		Path path2;
		TreeMap<Double, String> scores = new TreeMap<Double, String>();
		NavigableMap<Double, String> descMap = scores.descendingMap();
		Set<Entry<Double, String>> descSet = descMap.entrySet();
		File file = new File("C:\\Users\\wjddu\\git\\gawiGame\\userData");
		List<String> allLines = null;
		double rate;
		String[] fList = file.list();
			// 폴더의 파일/폴더 목록을 문자열 배열로 반환
//			String[] fList = file.list();
			// 출력
			for (int i = 0; i < fList.length; i++) {
				path2 = Paths.get("C:\\Users\\wjddu\\git\\gawiGame\\userData\\" + fList[i]);
//				file = new File(path + "\\" + fList[i]);
				
				try {
					allLines = Files.readAllLines(path2);
					String nthLine;
					String subLine;
					nthLine = allLines.get(10);
					subLine = nthLine.substring(10, nthLine.length());
					rate = Integer.parseInt(subLine);
					descMap.put(rate, fList[i]);
				} catch (IOException e) {
					System.out.println("예외 발생");
				}
			}
			for (Entry<Double, String> entry3 : descSet) {
				String list = entry3.getValue();
				String subList = list.substring(0, list.length()-4);
				System.out.println(subList);
			}
		startMessage();
	
	}
	
}
