package com.mysp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysp.dto.AdminDTO;
import com.mysp.vo.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public AdminVO adminLogin(AdminDTO adto) throws Exception {
		return sqlSession.selectOne("memberMapper.adminLogin", adto);
	}

	@Override
	public void AkeepLogin(String adminid, String sessionid, Date next) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminid", adminid);
		paramMap.put("sessionid", sessionid);
		paramMap.put("next", next);
		
		sqlSession.update("memberMapper.AkeepLogin", paramMap);
	}

	@Override
	public AdminVO AcheckAdminWithSessionKey(String value) {
		return sqlSession.selectOne("memberMapper.AcheckAdminWithSessionKey", value);
	}

}
