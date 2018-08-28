package com.mysp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//관리자 로그인 처리 인터셉터
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//세션 객체를 가져온다.
		HttpSession session = request.getSession();

		if(session.getAttribute("adminLogin") != null) { //세션에 있는 값이 null이 아니면 
			session.removeAttribute("adminLogin"); //기존 세션 내용 깨끗이 제거
		}
		return true;
	}
	
	//postHandle() 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		//세션 객체를 가져온다.
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object adminVO = modelMap.get("adminVO");
		System.out.println(adminVO);
		
		if(adminVO != null) {
			session.setAttribute("adminLogin", adminVO);
			
			if(request.getParameter("useCookie") != null) {//사용자가 '자동 로그인'을 선택한 경우 쿠키를 생성하고 생성된 쿠키이름은 loginCookie로 지정합니다. 
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/mysp/");
				
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie); //만들어진 쿠키는 다시 HttpServletResponse에 담겨서 전송
			}
		}else {
			response.sendRedirect("/mysp/admin/adminLogin");
		}
	}
			
}
