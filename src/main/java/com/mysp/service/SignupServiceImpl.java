package com.mysp.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mysp.dao.SignupDAO;
import com.mysp.dto.LoginDTO;
import com.mysp.vo.SignupVO;

@Service
public class SignupServiceImpl implements SignupService {
	
	@Inject
	private SignupDAO signupDAO;
	
	@Override
	public void insertMember(SignupVO signVO) {
		signupDAO.insertMember(signVO);
	}

	//아이디 중복체크
	@Override
	public int checkId(String userid) throws Exception{
		return signupDAO.checkId(userid);
	}
	
	//로그인 처리
	@Override
	public SignupVO login(LoginDTO dto) throws Exception{
		return signupDAO.login(dto);
	}
	
	//자동 로그인
	@Override
	public void KeepLogin(String userid, String sessionid, Date next) throws Exception{
		signupDAO.keepLogin(userid, sessionid, next);
	}
	@Override
	public SignupVO checkLoginBefore(String value) {
		return signupDAO.checkUserWithSessionKey(value);
	}
}
