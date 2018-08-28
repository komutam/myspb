package com.mysp.service;

import java.util.List;

import com.mysp.vo.CartVO;

public interface CartService {
	
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
	
	//장바구니 동일상품 확인
	public int countCart(int proid, String userid) throws Exception;
	
	//장바구니 상품수량 변경
	public void updateCart(CartVO vo) throws Exception;
	
}
