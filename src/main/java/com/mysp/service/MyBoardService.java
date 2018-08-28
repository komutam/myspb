package com.mysp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mysp.vo.Criteria;
import com.mysp.vo.MyBoardVO;

public interface MyBoardService {

	public void write(MyBoardVO myboard) throws Exception;
	
	public MyBoardVO read(Integer bno) throws Exception;
	
	public void updateWrite(MyBoardVO myboard) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<MyBoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCount(Criteria cri) throws Exception;
	
	public void readCnt(int bno, HttpSession session) throws Exception;
} 
