package com.mysp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mysp.dao.CartDAO;
import com.mysp.vo.CartVO;

@Service
public class CartServiceImpl implements CartService {
	
	@Inject
	private CartDAO cartDAO;
	
	@Override
	public void insertCart(CartVO vo) throws Exception {
		cartDAO.insertCart(vo);
	}

	@Override
	public List<CartVO> cartList(String userid) throws Exception {
		return cartDAO.cartList(userid);
	}

	@Override
	public void deleteCart(int cartid) throws Exception {
		cartDAO.deleteCart(cartid);
	}

	@Override
	public void modifyCart(CartVO vo) throws Exception {
		cartDAO.modifyCart(vo);
	}

	@Override
	public int sumMoney(String userid) throws Exception {
		return cartDAO.sumMoney(userid);
	}

	@Override
	public int countCart(int proid, String userid) throws Exception {
		return cartDAO.countCart(proid, userid);
	}

	@Override
	public void updateCart(CartVO vo) throws Exception {
		cartDAO.updateCart(vo);
	}

}
