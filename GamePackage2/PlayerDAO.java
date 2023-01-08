package GamePackage2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;


// Data Access Object 
public class PlayerDAO {
	
	// 필드 정의
	private Register register; // 회원가입
	private ChangePW changePW; // 비번변경
	
	private static String filePath = "C:\\Users\\wjddu\\git\\gawiGame\\userData";
	private static File file = new File(filePath);
	
	String fileName, id, mem_Email, mem_Password;
	File folder;
	
	FileWriter fw;
	BufferedWriter bw;
	FileReader fr;
	BufferedReader br;
	
	// 생성자 정의
	public PlayerDAO() {
		// 기본 생성자.
	}

	
	public void makeFile() {	// 중복된 ID가 없을 때 파일 생성하는 메서드
		
		fileName = id + ".dat";
		file = new File(folder,fileName);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat firstDate = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss"); // yyyy-MM-dd apm HH:mm:dd 잘못 적어주신듯
		
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			PrintStream ps = new PrintStream(fos);
			PlayerInfoDTO player = new PlayerInfoDTO();
			
			ps.println("regDate : " + firstDate.format(cal.getTime()));
			ps.println("logInDate : ");
			ps.println("logOutDate : ");
			ps.println("email : " + player.getEmail());
			ps.println("id : " + id);
			ps.println("password : " + player.getPassword());
			ps.println("count : ");
			ps.println("win : ");
			ps.println("draw : ");
			ps.println("lose : ");
			ps.println("winRate : ");
			ps.close();
		} catch (FileNotFoundException e) {
			
		}
		
		finReg();	// 회원가입 완료 메서드 호출
	}	// makeFile()의 끝
	
	public void finReg() {	// 회원가입 성공 시 이동되어 가입축하 메세지 출력 후 게임 시작 전 메뉴 호출하는 메서드
		JOptionPane.showMessageDialog(null, "축하드립니다" + mem_Email + "님 회원가입 되었습니다.");
		GawibawiboMain game = new GawibawiboMain();	// 게임 메뉴가 있는 클래스 호출
	}
	
	
	
	
}