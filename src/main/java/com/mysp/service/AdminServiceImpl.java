package com.mysp.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mysp.dao.AdminDAO;
import com.mysp.dto.AdminDTO;
import com.mysp.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO adminDAO;
	
	//로그인 처리
	@Override
	public AdminVO adminLogin(AdminDTO adto) throws Exception{
		return adminDAO.adminLogin(adto);
	}
	
	//자동 로그인
	@Override
	public void AKeepLogin(String adminid, String sessionid, Date next) throws Exception{
		adminDAO.AkeepLogin(adminid, sessionid, next);
	}
	@Override
	public AdminVO AcheckLoginBefore(String value) {
		return adminDAO.AcheckAdminWithSessionKey(value);
	}
}
