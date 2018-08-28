package com.mysp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysp.dto.LoginDTO;
import com.mysp.vo.SignupVO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insertMember(SignupVO signVO) {
		sqlSession.insert("memberMapper.insertMember", signVO);
	}
	
	//아이디 중복체크
	@Override
	public int checkId(String userid) throws Exception {
		return sqlSession.selectOne("memberMapper.checkId", userid);
	}
	
	//로그인 처리
	@Override
	public SignupVO login(LoginDTO dto) throws Exception{
		return sqlSession.selectOne("memberMapper.login", dto);
	}
	
	//자동 로그인
	@Override
	public void keepLogin(String userid, String sessionid, Date next) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("sessionid", sessionid);
		paramMap.put("next", next);
		
		sqlSession.update("memberMapper.keepLogin", paramMap);
	}
	@Override
	public SignupVO checkUserWithSessionKey(String value) {
		return sqlSession.selectOne("memberMapper.checkUserWithSessionKey", value);
	}
}
