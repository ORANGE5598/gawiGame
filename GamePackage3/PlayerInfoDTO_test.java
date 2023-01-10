package GamePackage2;

public class PlayerInfoDTO_test {
	private long regDate, loginTime, logOutTime; // 필드 정의.
	private String mem_Email;
	private String id;
	private String mem_Password;
	private int win;
	private int lose;
	private int draw;
	private int total;
	private double winrate;
//	private String showstats;
	
//	public PlayerInfoDTO(long loginTime, String email, String password) { // 생성자 정의.
//		super();
//		this.loginTime = loginTime;
//		this.email = email;
//		this.mem_Password = password;
//	}

	// 이하 getter, setter.
	public long regDate() {
		return regDate;
	}

	public void regDate(long regDate) {
		this.regDate = regDate;
	}
	
	public long getLogInTime() {
		return loginTime;
	}
	
	public void setLogInTime(long loginTime) {
		this.loginTime = loginTime;
	}
	
	public long getLogOutTime() {
		return loginTime;
	}
	
	public void setLogOutTime(long logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getEmail() {
		return mem_Email;
	}

	public void setEmail(String mem_Email) {
		this.mem_Email = mem_Email;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return mem_Password;
	}

	public void setPassword(String mem_Password) {
		this.mem_Password = mem_Password;
	}
	
	public void plusCnt(int result) { // 게임 로직에서 받아오는 result 값
		// 0 승 1 패 2 무
		if(result == 0) {
			setWin(1);
		} else if(result == 1) {
			setLose(1);
		} else if(result == 2) {
			setDraw(1);
		}
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win += win;
		setTotal();
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose += lose;
		setTotal();
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw += draw;
		setTotal();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = this.win + this.lose + this.draw;
	}

	public String getWinrate() {
		return String.format("%.3f", this.winrate);
	}

	public void setWinrate(double winrate) {
		this.winrate = this.getWin() / (double) this.getTotal() * 100.0;
	}
	
	public String printStats() {
		return "전적은 다음과 같습니다. \n게임수 : " + getTotal() + " \n승 : " + getWin() + "\n무 : " + getDraw() + "\n패 : " + getLose() + "\n승률 : " + getWinrate();
	}
	
	
	
}