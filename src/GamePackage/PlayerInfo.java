package GamePackage;

public class PlayerInfo {
	private long loginTime; // 필드 정의.
	private String email;
	private String password;
	private int win;
	private int lose;
	private int draw;
	private int total;
	private double winrate;
//	private String showstats;
	
	public PlayerInfo() { // 생성자 정의.
		
	}
	
	public PlayerInfo(long loginTime, String email, String password) { // 생성자 정의.
		super();
		this.loginTime = loginTime;
		this.email = email;
		this.password = password;
	}
	
	// @ 기준으로 id만 분류해내기.
	public String userId() {
		return this.email.substring(0, this.email.indexOf('@'));
	}
	
	
	// 이하 getter, setter.
	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
