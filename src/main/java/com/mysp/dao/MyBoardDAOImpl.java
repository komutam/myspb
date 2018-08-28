package com.mysp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysp.vo.Criteria;
import com.mysp.vo.MyBoardVO;

@Repository
public class MyBoardDAOImpl implements MyBoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void write(MyBoardVO myboardVO) throws Exception{
		sqlSession.insert("MyBoardMapper.write", myboardVO);
	}
	
	@Override
	public MyBoardVO read(Integer bno) throws Exception{
		return sqlSession.selectOne("MyBoardMapper.read", bno);
	}
	
	@Override
	public void updateWrite(MyBoardVO myboardVO) throws Exception{
		sqlSession.update("MyBoardMapper.update", myboardVO);
	}
	
	@Override
	public void delete(Integer bno) throws Exception{
		sqlSession.selectOne("MyBoardMapper.delete", bno);
	}
	
	@Override
	public void readCnt(int bno) throws Exception{
		sqlSession.update("MyBoardMapper.readCnt", bno);
	}
	@Override
	public List<MyBoardVO> listPage(Criteria cri) throws Exception{
		return sqlSession.selectList("MyBoardMapper.listPage", cri);
	}
	@Override
	public int countPaging(Criteria cri) throws Exception{
		return sqlSession.selectOne("MyBoardMapper.countPaging", cri);
	}
	
	//댓글갯수
	@Override
	public void updateReplyCnt(Integer bNo, int amount) throws Exception {
	  Map<String, Object> paramMap = new HashMap<String, Object>();
	  paramMap.put("bno", bNo);
	  paramMap.put("amount", amount);
	  sqlSession.update("MyBoardMapper.updateReplyCnt", paramMap);
	}
}
