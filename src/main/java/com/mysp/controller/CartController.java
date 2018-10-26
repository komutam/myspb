package com.mysp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysp.service.CartService;
import com.mysp.vo.CartVO;


@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Inject
	private CartService cartService;
	
	//장바구니 추가
	@RequestMapping(value="/insertCart", method=RequestMethod.POST)
	public String insertCart(@ModelAttribute CartVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		String userid = vo.getUserid();
		vo.setUserid(userid);
		//상품이 장바구니에 있는지 검사
		int count = cartService.countCart(vo.getProid(), userid);
		if(count == 0) { //상품이 존재하지 않을 경우 상품넣기
			cartService.insertCart(vo);
		}else { //존재할 경우 수량 업데이트
			cartService.updateCart(vo);
		}
		rttr.addAttribute("userid", userid);
		return "redirect:/cart/cartList";
	}
	
	//장바구니 리스트
	@RequestMapping(value="/cartList", method=RequestMethod.GET)
	public ModelAndView cartList(HttpSession session, ModelAndView mav, @ModelAttribute("userid") CartVO ca) throws Exception{
		String userid = ca.getUserid();
		System.out.println("테스테스 아이디 = "+userid);
		Map<String, Object> map = new HashMap<String, Object>();
		List<CartVO> list = cartService.cartList(userid); //장바구니 리스트 가져와서 넣기
		int sumMoney = cartService.sumMoney(userid); //전체금액 불러와 넣기
		
		int fee = sumMoney >=100000 ? 0 : 2500; //금액 합계에 따라 배송비 부과 10만 이상이면 무료 아니면 2500원
		
		map.put("list", list); //장바구니 정보를 map에 저장
		map.put("count", list.size()); //몇개인지 세기
		map.put("sumMoney", sumMoney); //장바구니 전체금액
		map.put("fee", fee); //배송비
		map.put("allSum", sumMoney+fee); //모든 상품금액 + 배송비 = 합한금액
		mav.setViewName("/cart/cartList"); //view이름 저장
		mav.addObject("map", map); //map변수 저장
		
		return mav;
	}
	
	//장바구니 삭제
	@RequestMapping(value="/deleteCart", method=RequestMethod.GET)
	public String deleteCart(@RequestParam int cartid) throws Exception{
		cartService.deleteCart(cartid);
		return "redirect:/cart/cartList";
	}
	
	//장바구니 modify
	@RequestMapping(value="/modifyCart", method=RequestMethod.POST)
	public String modifyCart(@RequestParam int[] amount, @RequestParam int[] proid, @RequestParam String userid) throws Exception{
		for(int i=0; i<proid.length; i++) { //갯수만큼 반복문실행
			CartVO vo = new CartVO();
			vo.setUserid(userid);
			vo.setAmount(amount[i]);
			vo.setProid(proid[i]);
			cartService.modifyCart(vo);
		}
		return "redirect:/cart/cartList";
	}

}
