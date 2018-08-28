package com.mysp.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.mysp.service.AdminService;
import com.mysp.vo.AdminVO;

//preHandle()을 이용하여 현재 사용자가 로그인한 상태인지를 체크하고, 컨트롤러를 호출할 것인지를 결정하는 인터셉터
public class AdminAuthInterceptor extends HandlerInterceptorAdapter{
	@Inject
	private AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession();
	    
	    if (session.getAttribute("adminLogin") == null) {

	      saveDest(request);
	      
//----------------자동 로그인에 대한 코드 추가-------------
	      //현재 사용자의 세션에 login이 존재하지 않지만, 쿠키 중에서 loginCookie가 존재할 때 처리가 진행
	      Cookie loginCookie =WebUtils.getCookie(request, "loginCookie");
	      if(loginCookie != null) {
	    	  AdminVO adminVO = adminService.AcheckLoginBefore(loginCookie.getValue());
	    	  if(adminVO != null) {
	    		  session.setAttribute("adminLogin", adminVO);
	    		  return true;
	    	  }
	      }
//---------------------------------------------end	      
	      
	      response.sendRedirect("/mysp/admin/adminLogin");
	      return false;
	    }
		return true;
	}
	
	//AuthInterceptor에서는 사용자가 원래 가려고 했던 URI를 저장했다가 로그인 성공 후 원래의 URI로 이동시키는 작업
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI(); 
		String query = request.getQueryString(); 
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" +query;
		}
		if(request.getMethod().equals("GET")) {
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}
