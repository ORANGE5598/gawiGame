package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class getStats {

	String userId;
	String userEmail;
	String originalPW; // 기존 비밀번호
	String changePW; // 바꿀 비밀번호
	String dat = ".dat"; // 유저 파일 확장자명

	int win;
	int draw;
	int lose;
	
	String fileWin = "win"; 
	

	String filePath = "C:\\userData";
	String readLine;
	String changeLine;
	List<String> list = new ArrayList<>(); // 파일 내용을 긁어와서 담을 리스트.
	
	public getStats() {
		
	}
	
	public getStats(String email, String password, String getuserId, int win, int draw, int lose) {
		this.userEmail = email;
		this.originalPW = password;
		this.userId = getuserId;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	}
	
	public void stats() {
		File inputFile = new File(filePath + "\\" + userId + dat);
		File outputFile = new File(filePath + "\\" + userId + ".backup");

		FileInputStream fis = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		
		try {
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			br = new BufferedReader(new InputStreamReader(fis));
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			while((readLine = br.readLine()) != null) {
				list.add(readLine);
				changeLine = readLine.
				bw.write(changeLine, 0, changeLine.length());
				bw.newLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
