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
	
	List<String> list = new ArrayList<>(); // 파일 내용을 긁어와서 담을 리스트.
	boolean result = false; // flag
	
	public void inputPW() {
		this.originalPW = JOptionPane.showInputDialog("기존 비밀번호를 입력하세요.");
		System.out.println(originalPW);

		this.changePW = JOptionPane.showInputDialog("바꿀 비밀번호를 입력하세요.");
		System.out.println(this.changePW);
		
		changeLogic();
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
				list.add(readLine);
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
				if(!list.contains("password : " + originalPW)) {
					JOptionPane.showMessageDialog(null, "입력한 기존 비밀번호가 불일치 합니다. 다시 입력하세요.");
					outputFile.delete();
					result = false;
					inputPW();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(result) {
				inputFile.delete();
				outputFile.renameTo(new File(filePath + "\\" + userId + dat));
				JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다. 다시 로그인해주세요.");
				GawibawiboMain.startMenu();
			}
		}
	}
}
