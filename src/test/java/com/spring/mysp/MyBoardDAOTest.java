package com.spring.mysp;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.mysp.dao.MyBoardDAO;
import com.mysp.vo.MyBoardVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class MyBoardDAOTest {
	private final static Logger logger = LoggerFactory.getLogger(MyBoardDAOTest.class);
	@Inject
	private MyBoardDAO myboardDAO;
	
 	/*@Test
	public void testCreate() throws Exception {
		MyBoardVO myboard = new MyBoardVO();
		myboard.setTitle("ㅁㄴㅇㅁㄴㅇㅁㄴ");
		myboard.setContent("13123123");
		myboard.setWriter("ㄴㅇ");
		myboardDAO.write(myboard);
	}*/
/* 	@Test
	public void testRead() throws Exception {
		logger.info(myboardDAO.read(3).toString());
	}*/

/* 	@Test
	public void testUpdate() throws Exception {
		MyBoardVO myboard = new MyBoardVO();
		myboard.setBno(3);
		myboard.setTitle("수정된 글입니다.");
		myboard.setContent("수정 테스트");
		myboardDAO.updateWrite(myboard);
	}*/

/*	@Test
	public void testDelete() throws Exception {
		myboardDAO.delete(2);
	}*/
	
	/*@Test
	public void testListCriteria() throws Exception {
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);

		List<BoardVO> list = boardDAO.listCriteria(cri);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}*/
}
