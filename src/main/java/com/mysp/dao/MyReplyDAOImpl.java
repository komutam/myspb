package com.mysp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysp.vo.Criteria;
import com.mysp.vo.ReplyVO;

@Repository
public class MyReplyDAOImpl implements MyReplyDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(Integer bNo) throws Exception {
		return sqlSession.selectList("replyMapper.list", bNo);
	}

	@Override
	public void create(ReplyVO reply) throws Exception {
		sqlSession.insert("replyMapper.create", reply);
	}

	@Override
	public void update(ReplyVO reply) throws Exception {
		sqlSession.update("replyMapper.update", reply);
	}

	@Override
	public void delete(Integer rNo) throws Exception {
		sqlSession.delete("replyMapper.delete", rNo);
	}

	
	//페이징 처리
	@Override
	public List<ReplyVO> listPage(Integer bNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("bNo", bNo);
		paramMap.put("cri", cri);

		return sqlSession.selectList("replyMapper.listPage", paramMap);
	}

	@Override
	public int count(Integer bNo) throws Exception {
		return sqlSession.selectOne("replyMapper.count", bNo);
	}
	
	//ReplyDAO에는 댓글이 삭제될 때 해당 게시물의 번호를 알아내는 기능
	@Override
	public int getBNo(Integer rNo) throws Exception {
	  return sqlSession.selectOne("replyMapper.getBNo",rNo);
	}
}
