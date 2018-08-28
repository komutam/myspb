package com.mysp.service;

import java.util.Date;

import com.mysp.dto.AdminDTO;
import com.mysp.vo.AdminVO;

public interface AdminService {
	
	//로그인 처리
		public AdminVO adminLogin(AdminDTO adto) throws Exception;
		
		//자동 로그인
		//로그인 정보를 유지하는 keepLogin과 과거에 접속한 사용자인지를 확인하는 기능을 작성
		public void AKeepLogin(String adminid, String sessionid, Date next) throws Exception;
		public AdminVO AcheckLoginBefore(String value);
}
