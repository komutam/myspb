package com.mysp.vo;

import java.util.Date;

public class AdminVO {
	private String adminid;
	private String adminpwd;
	private String adminname;
	private String email;
	private Date regdate;
	private Date updatedate;
	
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	public String toString() {
		return "SignupVO [adminid:"+adminid+", adminpwd:"+adminpwd+", username:"+adminname+", email:"+email+", regdate:"+regdate+", updatedate:"+updatedate+"]";
	}
}
