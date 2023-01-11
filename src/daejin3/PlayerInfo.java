package daejin2;

public class PlayerInfo { // DTO 정리한 클래스
	private String loginTime, logoutTime; // 필드 정의
	private String email, password, userId;
	private int win, lose, draw, count;
	private double winrate;

	public PlayerInfo() { // 디폴트 생성자 정의

	}

	public PlayerInfo(String loginTime, String email, String password) { // 생성자 정의
		super();
		this.loginTime = loginTime;
		this.email = email;
		this.password = password;
		this.userId = this.email.substring(0, this.email.indexOf('@'));

	}

	public String getuserId() {
		return userId;
	}

	public void setuserId(String id) {
		this.userId = id;
	}

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
		this.winrate = (double) (this.getWin()) / (double) (this.getCount()) * 100.00;
		this.winrate = Double.parseDouble(String.format("%.2f", this.winrate));
	}

	public String printStats() {
		return this.userId + " 님, 전적은 다음과 같습니다. \n게임수 : " + getCount() + " \n승 : " + getWin() + "\n무 : " + getDraw()
				+ "\n패 : " + getLose() + "\n승률 : " + getWinrate();
	}

}