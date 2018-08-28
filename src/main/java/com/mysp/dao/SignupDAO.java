package com.mysp.dao;

import java.util.Date;

import com.mysp.dto.LoginDTO;
import com.mysp.vo.SignupVO;

public interface SignupDAO {//원래 memberDAO로 만들어야 하는데 일단은 이걸로
	
	//회원가입
	public void insertMember(SignupVO signVO);

	//아이디 중복체크
	public int checkId(String userid) throws Exception;
	
	//로그인 처리
	public SignupVO login(LoginDTO dto) throws Exception;
	
	//자동 로그인
	//로그인한 사용자의 sessionkey와 sessionlimit을 업데이트하는 기능과 loginCookie에 기록된 값으로 사용자의 정보를 조회하는 기능을 추가
	public void keepLogin(String userid, String sessionid, Date next);
	public SignupVO checkUserWithSessionKey(String value);
}
