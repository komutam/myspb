package com.mysp.service;

import java.util.Date;

import com.mysp.dto.LoginDTO;
import com.mysp.vo.SignupVO;

public interface SignupService { //이것도 memberService로 해서 한꺼번에 정리
	
	//회원가입
	public void insertMember(SignupVO signVO);
	
	//아이디 중복체크
	public int checkId(String userid) throws Exception;
	
	//로그인 처리
	public SignupVO login(LoginDTO dto) throws Exception;
	
	//자동 로그인
	//로그인 정보를 유지하는 keepLogin과 과거에 접속한 사용자인지를 확인하는 기능을 작성
	public void KeepLogin(String userid, String sessionid, Date next) throws Exception;
	public SignupVO checkLoginBefore(String value);
}
