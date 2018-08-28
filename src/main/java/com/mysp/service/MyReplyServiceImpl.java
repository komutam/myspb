package com.mysp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysp.vo.Criteria;
import com.mysp.dao.MyBoardDAO;
import com.mysp.dao.MyReplyDAO;
import com.mysp.vo.ReplyVO;

@Service
public class MyReplyServiceImpl implements MyReplyService {
	@Inject
	private MyReplyDAO replyDAO;
	
	@Inject//댓글의 등록과 삭제 시 처리할 부분이 있기 때문에 BoardDAO를 같이 사용하도록 추가
	private MyBoardDAO boardDAO;
	
	@Transactional
	@Override
	public void addReply(ReplyVO reply) throws Exception {
		replyDAO.create(reply);
		boardDAO.updateReplyCnt(reply.getbNo(), 1);//새로운 댓글이 추가되면 tbl_board의 replyCnt 컬럼의 값을 1 증가 시키는 작업
	}

	@Override
	public List<ReplyVO> listReply(Integer bNo) throws Exception {
		return replyDAO.list(bNo);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws Exception {
		replyDAO.update(reply);
	}
	
	@Transactional
	@Override
	public void removeReply(Integer rNo) throws Exception {
		int bNo = replyDAO.getBNo(rNo); //추가
		replyDAO.delete(rNo);
		boardDAO.updateReplyCnt(bNo, -1); //댓글이 삭제될 때 replyCnt 컬럼의 값이 -1시키는 작업을 수행하고 @Transactional을 이용해서 처리
	}

	//페이징 처리
	@Override
	public List<ReplyVO> listReplyPage(Integer bNo, Criteria cri) throws Exception {
		return replyDAO.listPage(bNo, cri);
	}
	@Override
	public int count(Integer bNo) throws Exception {
		return replyDAO.count(bNo);
	}
	
}
