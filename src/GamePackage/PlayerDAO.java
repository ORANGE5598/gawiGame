package GamePackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

// Data Access Object 
public class PlayerDAO {
	// 필드 정의
	private PlayerInfo player; // DTO
	private Register register; // 회원가입
	private changePW changePW; // 비번변경
	
	private static String filePath = "D:\\\\workspace\\\\GawiGame\\\\userData";
	private static File file = new File(filePath);
	
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;
	
	// 생성자 정의
	public PlayerDAO() {
		// 기본 생성자.
	}
	public PlayerDAO(PlayerInfo player) {
		this.player = player;
	}
	
	
}
