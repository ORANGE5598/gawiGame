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

public class changePW2 {
	private PlayerInfo player;
	private String originalPW; // 기존 비밀번호
	private String changePW; // 바꿀 비밀번호
	private String dat = ".dat"; // 유저 파일 확장자명

	private String userId = player.getuserId();
	String filePath = "D:\\workspace\\GawiGame\\userData";
	String readLine;
	String changeLine;

	List<String> list = new ArrayList<>(); // 파일 내용을 긁어와서 담을 리스트.
	boolean result = false; // flag
	
	public void changePW2() {
		
	}
	
	
	public void inputPW(String password) {
		this.originalPW = password;
		this.changePW = JOptionPane.showInputDialog("바꿀 비밀번호를 입력하세요.");
		System.out.println(this.changePW);
		
	}
	
}
