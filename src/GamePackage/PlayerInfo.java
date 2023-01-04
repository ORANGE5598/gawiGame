package GamePackage;

public class PlayerInfo {
	private long loginTime; // 필드 정의.
	private String email;
	private String password;
	private int win;
	private int lose;
	private int draw;
	private int gamecount;
	private double winrate;
	
	public PlayerInfo(long loginTime, String email, String password) { // 생성자 정의.
		super();
		this.loginTime = loginTime;
		this.email = email;
		this.password = password;
	}
	
	// @ 기준으로 id만 분류해내기.
	public String userId() {
		return this.email.substring(0, this.email.indexOf('@') + 1);
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

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getGamecount() {
		return gamecount;
	}

	public void setGamecount(int gamecount) {
		this.gamecount = gamecount;
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	
	
	
	
}
