package com.mysp.dao;

import java.util.Date;

import com.mysp.dto.AdminDTO;
import com.mysp.vo.AdminVO;

public interface AdminDAO {
	
	//로그인 처리
	public AdminVO adminLogin(AdminDTO adto) throws Exception;
	
	//자동 로그인
	public void AkeepLogin(String adminid, String sessionid, Date next);
	public AdminVO AcheckAdminWithSessionKey(String value);
	
}
