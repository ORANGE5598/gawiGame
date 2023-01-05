package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class changePW2 {
	/* 암호 변경 로직 구성
	 * File list 추출해서 사용자가 입력한 id 비교해서 관련 데이터가 기록된 파일 찾기.
	 * 사용자에게 현재 password 입력받기
	 * 기존 내용을 읽어서 기존 password 와 입력한 password 비교
	 * 같다면, 패스워드 변경 창 출력, 다르다면 틀렸다고 출력
	 * 변경한 password 입력했다면 기존 데이터 기록 파일에서 수정?? or 
	 * 다시 startMenu로 이동. 
	 */
	public static void main(String[] args) {
		// 임시** 유저 아이디 임의로 정의함. 실제로는 유저가 로그인했을때 사용된 변수를 가져올것.
		String userId = "user1";
		String filePath = "D:\\Players"; // 원본 파일 경로 정의.
		String userFile = userId; // 파일 이름은 유저의 ID.txt
		String readLi; // 라인 읽기
		String changeLi; // temp
		String userPw = "user1234"; // 임시.. 
		String userPwChange = "changed"; // 임시..
		
		// File 객체 생성함.
		File inputFile = new File(filePath + "\\" + userId + ".txt");
		File outputFile = new File(filePath + "\\" + userId + ".back");
		
		FileInputStream fis = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		
		boolean result = false;
		
		try {
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			br = new BufferedReader(new InputStreamReader(fis));
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			while((readLi = br.readLine()) != null) {
				changeLi = readLi.replace(userPw, userPwChange);
				bw.write(changeLi, 0, changeLi.length());
				bw.newLine();
			}
			result = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(result) {
				inputFile.delete();
				outputFile.renameTo(new File(filePath + "\\" +userId + ".txt"));
			}
		}
		
	}
	
	

}
