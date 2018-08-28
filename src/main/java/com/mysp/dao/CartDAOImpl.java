package com.mysp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysp.vo.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {
	
	@Inject
	SqlSession sqlSession;
	
	//장바구니 추가
	@Override
	public void insertCart(CartVO vo) throws Exception {
		sqlSession.insert("CartMapper.insertCart", vo);
	}
	
	//장바구니 목록
	@Override
	public List<CartVO> cartList(String userid) throws Exception {
		return sqlSession.selectList("CartMapper.cartList", userid);
	}
	
	//장바구니 삭제
	@Override
	public void deleteCart(int cartid) throws Exception {
		sqlSession.delete("CartMapper.deleteCart", cartid);
	}

	//장바구니 수정
	@Override
	public void modifyCart(CartVO vo) throws Exception {
		sqlSession.update("CartMapper.modifyCart", vo);
	}

	//장바구니 금액합계
	@Override
	public int sumMoney(String userid) throws Exception {
		//sqlSession.selectOne("CartMapper.sumMoney", userid);
		return sqlSession.selectOne("CartMapper.sumMoney", userid);
	}

	//동일상품 확인
	@Override
	public int countCart(int proid, String userid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", proid);
		map.put("userid", userid);
		return sqlSession.selectOne("CartMapper.countCart", map);
	}
	
	//수량변경
	@Override
	public void updateCart(CartVO vo) throws Exception {
		sqlSession.update("CartMapper.updateCart", vo);
	}
	
}
