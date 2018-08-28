package com.mysp.dto;

public class AdminDTO {
	private String adminid;
	private String adminpwd;
	private boolean useCookie;
	
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
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "AdminDTO [adminid="+adminid+", adminpwd="+adminpwd+", useCookie="+useCookie+"]";
	}

}
