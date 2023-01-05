package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class changePW {
	/* 암호 변경 로직 구성
	 * File list 추출해서 사용자가 입력한 id 비교해서 관련 데이터가 기록된 파일 찾기.
	 * 사용자에게 현재 password 입력받기
	 * 기존 내용을 읽어서 기존 password 와 입력한 password 비교
	 * 같다면, 패스워드 변경 창 출력, 다르다면 틀렸다고 출력
	 * 변경한 password 입력했다면 기존 데이터 기록 파일에서 수정?? or 
	 * 다시 startMenu로 이동. 
	 */
	public static void main(String[] args) {
				
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		String fileDir = "D:\\player";
		File dir = new File(fileDir);
		
		String[] filelists = dir.list(); // 해당 경로에 있는 파일 전체 리스트를 배열로 저장.
		String userId = "user1.txt"; // ***임시!! 유저 아이디 비교.
		String userInput; // 사용자가 입력한 패스워드. 이것으로 비교해야함.
		String password; // passwordtest1234
		int idCorrectCnt = 0; // 일치카운터
		ArrayList<String> txtList = new ArrayList<String>(); 
		// txt에서 긁어온 내용을 ArrayList로 저장함.
		String str;
		
		
		for(int i = 0; i < filelists.length; i++) { // 반복문으로 전체 파일 이름을 
			// 리스트 하나씩과 비교 후 일치한다면 카운트 증가.
			if(filelists[i].equals(userId)) {
				idCorrectCnt++;
				System.out.println(userId);
				System.out.println(filelists[i]);
				break;
			}
		} 
		
		if(idCorrectCnt > 0) {
			userInput = JOptionPane.showInputDialog("기존 비번입력");
			try { 
				fr = new FileReader(dir + "\\" + userId); // 회원 id와 일치하는 파일의 경로..
				br = new BufferedReader(fr); 
				str = br.readLine();
				while(txtList != null) {
					txtList.add(str);
					str = br.readLine();
				}
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	

}
