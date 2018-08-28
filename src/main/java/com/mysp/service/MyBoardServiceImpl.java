package com.mysp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysp.dao.MyBoardDAO;
import com.mysp.vo.Criteria;
import com.mysp.vo.MyBoardVO;

@Service
public class MyBoardServiceImpl implements MyBoardService{
	@Autowired
	private MyBoardDAO myboardDao;
	
	@Override
	public void write(MyBoardVO myboard) throws Exception{
		myboardDao.write(myboard);
	}
	
	@Override
	public MyBoardVO read(Integer bno) throws Exception{
		/*myboardDao.readCnt(bno);*/
		return myboardDao.read(bno);
	}
	
	@Override
	public void updateWrite(MyBoardVO myboard) throws Exception{
		myboardDao.updateWrite(myboard);
	}
	
	@Override
	public void delete(Integer bno) throws Exception{
		myboardDao.delete(bno);
	}
	@Override
	public List<MyBoardVO> listCriteria(Criteria cri) throws Exception{
		return myboardDao.listPage(cri);
	}
	@Override
	public int listCount(Criteria cri) throws Exception{
		return myboardDao.countPaging(cri);
	}
	
	@Override
	public void readCnt(int bno, HttpSession session) throws Exception{
		long update_time = 0;
		if(session.getAttribute("update_time_"+bno) != null) {
			update_time = (long)session.getAttribute("update_time_"+bno);
		}
		long current_time = System.currentTimeMillis();
		if(current_time - update_time > 24*60*60*1000) {
			myboardDao.readCnt(bno);
			session.setAttribute("update_time_" + bno, current_time);
		}
	}
	
}
