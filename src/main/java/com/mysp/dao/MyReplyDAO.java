package com.mysp.dao;

import java.util.List;

import com.mysp.vo.Criteria;
import com.mysp.vo.ReplyVO;

public interface MyReplyDAO {
	
	public List<ReplyVO> list(Integer bNo) throws Exception;
	
	public void create(ReplyVO reply) throws Exception;
	
	public void update(ReplyVO reply) throws Exception;
	
	public void delete(Integer rNo) throws Exception;
	
	//페이징 처리
	public List<ReplyVO> listPage(Integer bNo, Criteria cri) throws Exception;
	public int count(Integer bNo) throws Exception;
	
	//ReplyDAO에는 댓글이 삭제될 때 해당 게시물의 번호를 알아내는 기능
	public int getBNo(Integer rNo) throws Exception;
	
}
