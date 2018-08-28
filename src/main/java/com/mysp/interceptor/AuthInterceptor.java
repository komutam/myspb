package com.mysp.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.mysp.service.SignupService;
import com.mysp.vo.SignupVO;

/*LoginInterceptor가 로그인한 사용자에 대해서 postHandle()을 이용해서 HttpSession에 보관하는 처리를 담당한다면
지금부터 작성하는 AuthInterceptor는 특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의 사용자인지를 체크하는 역할을 처리하기 위해서 작성합니다.*/

/*AuthInterceptor는 preHandle()을 이용하여 현재 사용자가 로그인한 상태인지를 체크하고, 컨트롤러를 호출할 것인지를 결정합니다.
만일 사용자가 로그인하지 않은 상태라면 로그인하는 /user/login 으로 이동합니다.*/
public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	private SignupService service;
	
	@Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
	    HttpSession session = request.getSession();
	    
	    if (session.getAttribute("login") == null) {
	      logger.info("로그인 안함");
	      
	      /*response.sendRedirect("/mysp/user/login");
	      return false;*/
	      saveDest(request);
	      
//----------------자동 로그인에 대한 코드 추가-------------
	      //현재 사용자의 세션에 login이 존재하지 않지만, 쿠키 중에서 loginCookie가 존재할 때 처리가 진행
	      Cookie loginCookie =WebUtils.getCookie(request, "loginCookie");
	      if(loginCookie != null) {
	    	  SignupVO signupVO = service.checkLoginBefore(loginCookie.getValue());
	    	  logger.info("VO : " + signupVO );
	    	  if(signupVO != null) {
	    		  session.setAttribute("login", signupVO);
	    		  return true;
	    	  }
	      }
/* AuthInterceptor는 UserService 타입의 객체를 주입받습니다.
	현재 사용자가 HttpSession에 적당한 값이 없는 경우 loginCookie를 가지고 있는지를 체크합니다.
	만일 과거에 보관한 쿠키가 있다면 UserService 객체를 이용해서 사용자의 정보가 존재하는지를 확인합니다.
	만일 사용자의 정보가 존재한다면 HttpSession에 다시 사용자의 정보를 넣어주게 됩니다. */
//---------------------------------------------end	      
	      
	      response.sendRedirect("/mysp/user/login");
	      return false;
	    }
	    return true;
	  }
	
	//AuthInterceptor에서는 사용자가 원래 가려고 했던 URI를 저장했다가 로그인 성공 후 원래의 URI로 이동시키는 작업을 진행하도록 해야함
	private void saveDest(HttpServletRequest request) {//saveDest()메소드를 이용해서 원래 사용자가 원하는 페이지의 정보는 HttpSession에 dest라는 이름으로 저장
		String uri = request.getRequestURI(); //request.getRequestURI()은 프로젝트와 파일 경로까지 얻어온다.
		String query = request.getQueryString(); //request.getQueryString()은 get으로 전송된 파라메터 쿼리문자열을 컨트롤한다.
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" +query;
		}
		if(request.getMethod().equals("GET")) { //GET 방식인경우에는 URI 정보 뒤의 파라미터들을 함께 보관해야 합니다.
			logger.info("dest"+(uri+query));
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}


