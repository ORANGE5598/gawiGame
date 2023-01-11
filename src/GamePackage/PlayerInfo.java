package daejin;

public class PlayerInfo {
	private String loginTime, logoutTime; // 필드 정의.
	private String email, password, userId;
	private int win, lose, draw, count;
	private double winrate;
	
	public PlayerInfo() { // 생성자 정의.
		
	}
	
	public PlayerInfo(String loginTime, String email, String password) { // 생성자 정의.
		super();
		this.loginTime = loginTime;
		this.email = email;
		this.password = password;
		this.userId = this.email.substring(0, this.email.indexOf('@'));
		
		///// 기존에 있는 전적을 긁어오려면,,, 여기에 파일 인아웃스트림해서 읽어와야 하나??
	}
	
	// @ 기준으로 id만 분류해내기.
	public String getuserId() {
		return userId;
	}
	
	public void setuserId(String id) {
		this.userId = id;
	}
	
	
	// 이하 getter, setter.
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	public String getLogoutTime() {
		return logoutTime;
	}
	
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
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
		this.win = win;
//		setTotal();
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
//		setTotal();
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getWinrate() {
		return this.winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = (double)(this.getWin()) / (double)(this.getCount()) * 100.00;
		this.winrate = Double.parseDouble(String.format("%.2f", this.winrate));
	}
	
	public String printStats() {
		return this.userId + " 님, 전적은 다음과 같습니다. \n게임수 : " + getCount() + " \n승 : " + getWin() + "\n무 : " + getDraw() + "\n패 : " + getLose() + "\n승률 : " + getWinrate();
	}
	
	
	
}