package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

// Data Access Object 
public class PlayerDAO extends GawibawiboMain {
	// 필드 정의
	private PlayerInfo player; // DTO
	private Register register; // 회원가입
	private ChangePW changePW; // 비번변경
	private boolean flag;
	
	private static String filePath = "C:\\userData";
	private static File file = new File(filePath);
	private static String dat = ".dat";
	
	public String count;
	public String win;
	public String draw;
	public String lose;
	public String total;
	public String winrate;
	

	FileInputStream fis;
	FileOutputStream fos;
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
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
		File outputFile = new File(filePath + "\\" + id + ".backup");
		File[] fileList = file.listFiles(); // userdata 폴더 내 파일 리스트 배열
		System.out.println("아이디 : " + id);
		
		File Player = null;
		for(int i = 0; i < fileList.length; i++) {
			Player = fileList[i];
			System.out.println(Player.getName() + "이 리스트에 있음.");
			System.out.println(Player.getName() + "||" + id+dat);
			if(Player.getName().equals(id + dat)) {
				break;
			}
		}
		try {
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			br = new BufferedReader(new InputStreamReader(fis));
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			String str = null;
			while((str = br.readLine()) != null) {
				str = str.trim();
				if(str.contains("count : ")) {
					count = str.substring(str.indexOf(":") + 2, str.length());
				} else if(str.contains("win : ")) {
					win = str.substring(str.indexOf(":") + 2, str.length());
				} else if(str.contains("draw : ")) {
					draw = str.substring(str.indexOf(":") + 2, str.length());
				} else if(str.contains("lose : ")) {
					lose = str.substring(str.indexOf(":") + 2, str.length());
				} else if(str.contains("winRate : ")) {
					winrate = str.substring(str.indexOf(":") + 2, str.length());
				}
			}
			fr.close();
			br.close();
			
		} catch (Exception e) {
			
		}
		
		JOptionPane.showMessageDialog(null, "총 판수 : " + count + "\n승 : " + win + "\n무 : " + draw + "\n패 : " + lose + "\n승률 : " + winrate);
		afterLogin();
	}
	
	public boolean check(PlayerInfo player) { // 파일 목록에 해당 아이디가 있는지..
		flag = true;
		File[] fileList = file.listFiles();
		String id = player.getuserId() + ".dat";
		String pw = null;
		System.out.println(id + " 아이디");
		
		File Player = null;
		
		for(int i = 0; i < fileList.length; i++) {
			Player = fileList[i];
			System.out.println(Player.getName() + " 플레이어");
			if(Player.getName().equals(id)) {
				System.out.println(Player.getName());
				flag = true;
				break;
			}
		}
		
		try {
			fr = new FileReader(Player.getAbsolutePath());
			br = new BufferedReader(fr);
			String str = null;
			while((str = br.readLine()) != null) {
				if(str.startsWith("password")) {
					System.out.println(str.toString());
					pw = str.substring(str.indexOf(":") + 2, str.length());
					if(pw.equals(player.getPassword())) {
						flag = true;
						JOptionPane.showMessageDialog(null, "로그인되었습니다.");
						fr.close();
						br.close();
						break;
					} else {
						JOptionPane.showMessageDialog(null, "로그인 실패. 다시 입력하세요.");
						GawibawiboMain.login();
					}
				}
			}
			
		} catch (Exception e) {

		}
		
		if(!flag) {
			return flag;
		}
		
		return flag;
		
	}
	
	
	
}