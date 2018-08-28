package com.mysp.dao;

import java.util.List;

import com.mysp.vo.Criteria;
import com.mysp.vo.MyBoardVO;

public interface MyBoardDAO {
	
	public void write(MyBoardVO myboardVO) throws Exception;
	
	public MyBoardVO read(Integer bno) throws Exception;
	
	public void updateWrite(MyBoardVO myboardVO) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public void readCnt(int bno) throws Exception;
	
	public List<MyBoardVO> listPage(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri) throws Exception;
	
	//댓글갯수
	public void updateReplyCnt(Integer bNo, int amount) throws Exception;
}
