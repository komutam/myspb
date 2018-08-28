package com.mysp.service;

import java.util.List;

import com.mysp.vo.Criteria;
import com.mysp.vo.ReplyVO;

public interface MyReplyService {
	public void addReply(ReplyVO reply) throws Exception;
	 
	public List<ReplyVO> listReply(Integer bNo) throws Exception;
	 
	public void modifyReply(ReplyVO reply) throws Exception;
	 
	public void removeReply(Integer rNo) throws Exception;
	
	//페이징 처리
	public List<ReplyVO> listReplyPage(Integer bNo, Criteria cri) throws Exception;
	public int count(Integer bNo) throws Exception;
}
