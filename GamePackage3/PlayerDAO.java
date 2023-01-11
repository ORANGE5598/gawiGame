package GamePackage2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

// Data Access Object 
public class PlayerDAO {
	// 필드 정의
	private PlayerInfo player; // DTO
	private Register register; // 회원가입
	private ChangePW changePW; // 비번변경
	private boolean flag;
	
	private static String filePath = "C:\\userData";
	private static File file = new File(filePath);
	
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
						GawibawiboMain.afterLogin();
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
