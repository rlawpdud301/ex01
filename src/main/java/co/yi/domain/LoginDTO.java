package co.yi.domain;

public class LoginDTO {
	private String userid;
	private String uesrname;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUesrname() {
		return uesrname;
	}
	public void setUesrname(String uesrname) {
		this.uesrname = uesrname;
	}
	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", uesrname=" + uesrname + "]";
	}
	
	
}
