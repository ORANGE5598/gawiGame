package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class changePW {
	private String originalPW; // 기존 비밀번호
	private String changePW; // 바꿀 비밀번호
	private String dat = ".dat"; // 유저 파일 확장자명



	///// 임시!! 
	private String userId = "Kimkymack1";

	String filePath = "D:\\workspace\\GawiGame\\userData";
	String readLine;
	String changeLine;
	ArrayList<String> list = new ArrayList<>();
	String[] str = new String[list.size()];
	int cnt = -1;

	boolean result = false;

	

	public changePW(String showInputDialog) {
		try {
			
			this.originalPW = showInputDialog;
			System.out.println(originalPW);
			

		}catch (Exception e) {

		}
		this.changePW = JOptionPane.showInputDialog("바꿀 비밀번호를 입력하세요.");
		System.out.println(this.changePW);
		cmpPassword();

	}
	
	public void cmpPassword() {
		String input = JOptionPane.showInputDialog("기존 비밀번호를 입력하세요.");
		
		
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
			
			List<String> list = new ArrayList<String>();
			String str;
			
			while((str = br.readLine()) != null) { // 파일 라인을 모두 읽어서 arraylist로 저장
				list.add(str);
				if(list.contains("password : " + input)) {
					System.out.println(list.contains("password : " + input));
					changeLogic();
				}

			}
			System.out.println(list);

		} catch (Exception e) {

		} finally {
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void changeLogic() {
		// File 객체 생성.
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
				
				changeLine = readLine.replace(originalPW, changePW);
				bw.write(changeLine, 0, changeLine.length());
				bw.newLine();
			}

			result = true;
			

		} catch (Exception e) {
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
				outputFile.renameTo(new File(filePath + "\\" + userId + dat));
			}
		}


	}
}