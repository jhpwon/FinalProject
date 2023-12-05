package com.multi.model;
import lombok.Data;
@Data
public class MemberVO {
	
	private String name;
	private String userid;
	private String userpwd;
	private String hp1;
	private String hp2;
	private String hp3;
	
	private String post;
	private String addr1;
	private String addr2;
	
	private java.sql.Date indate;
	private int mileage;
	private int mstate;
	private String mstateStr;
	
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}//----------------------
	public String getAllAddr() {
		
		return "["+post+"] "+addr1+" "+addr2;
	}
		
}//////////////////////////////////
