package com.mysp.dao;

import java.util.List;

import com.mysp.vo.CartVO;

public interface CartDAO {
	
	//장바구니 추가
	public void insertCart(CartVO vo) throws Exception;
	
	//장바구니 목록
	public List<CartVO> cartList(String userid) throws Exception;
	
	//장바구니 삭제
	public void deleteCart(int cartid) throws Exception;
	
	//장바구니 수정
	public void modifyCart(CartVO vo) throws Exception;
	
	//장바구니 금액 합계
	public int sumMoney(String userid) throws Exception;
	
	//장바구니 동일 상품인지 확인
	public int countCart(int proid, String userid) throws Exception;
	
	//동일 상품일 경우 카트수량 합산
	public void updateCart(CartVO vo) throws Exception;
	
}
