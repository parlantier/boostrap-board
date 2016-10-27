package org.board.domain;

public class UserVO {
	// uid 유저아이디 , 유저패스워드, 유저이름, 포인트
	private String uid;
	private String upw;
	private String uname;
	private int upoint;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	
	@Override
	public String toString() {
		return "UserVO [uid=" + uid + ", upw=" + upw + ", uname=" + uname + ", upoint=" + upoint + "]";
	}
}
