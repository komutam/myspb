package com.mysp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인 처리를 담당하는 인터셉터
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
			//세션 객체를 가져온다.
			HttpSession session = request.getSession();

			if(session.getAttribute("login") != null) { //세션에 있는 값이 null이 아니면 
				logger.info("clear login");
				session.removeAttribute("login"); //기존 세션 내용 깨끗이 제거
			}
			return true;
		}
	
	//postHandle() 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		//세션 객체를 가져온다.
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object signupVO = modelMap.get("signupVO"); //사용자 정보를 담고있는 객체 가져와서 넣기
		System.out.println(signupVO);
		
		if(signupVO != null) { //사용자 정보가 있으면 로그인 성공
			logger.info("new log success");
			session.setAttribute("login", signupVO);

	//자동로그인 : postHandle()을 이용해서 HttpSession에 UserVO타입의 객체를 보관합니다.
	//이를 수정해서 중간에 쿠키를 생성하고 HttpServletResponse에 같이 담아서 전송하도록 코드를 수정
			if(request.getParameter("useCookie") != null) {//사용자가 '자동 로그인'을 선택한 경우 쿠키를 생성하고 생성된 쿠키이름은 loginCookie로 지정합니다. 
				logger.info("remember me...................");
				//생성된 loginCookie에는 값으로 현재 세션의 아이디값을 보관합니다. 세션 아이디는 세션 쿠키의 값을 의미합니다.
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/mysp/");
				
				/*세션 쿠키의 경우 브라우저를 종료하면 사라지지만, 작성하는 loginCookie의 경우
				오랜 시간 보관되기 때문에 setMaxAge()를 이용합니다.
				setMaxAge()는 초단위의 시간 동안 유효하므로
				60*60*24*7을 이용해서 일주일간 브라우저상에서 보관됩니다.*/
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie); //만들어진 쿠키는 다시 HttpServletResponse에 담겨서 전송
			}
			
			/*response.sendRedirect("/mysp/");*/
		//LoginInterceptor는 로그인 성공 후의 response.sendRedirect() 작업에 'dest' 정보를 사용하도록 수정
		//이렇게 하면 무조건 /mysp/ 경로가 아니라 원래 가려했던 페이지로 간다.
			Object dest = session.getAttribute("dest");
			response.sendRedirect(dest != null ? (String) dest : "/mysp/");
		}else {
			response.sendRedirect("/mysp/user/login");
		}
	}
	
}

/*
UserController에서 HtppSession과 관련된 아무런 작업도 처리된 적이 없기 때문에
HttpSession에 관한 모든 설정은 고스란히 인터셉터에서 처리됩니다.

LoginInterceptor는 /loginPost로 접근하도록 설정하는 것을 목적으로 작성됐습니다.
preHandle()에서는 기존 HttpSession에 남아있는 정보가 있는 경우에는 정보를 삭제합니다. 

postHandle()에서는 UserController에서 userVO라는 이름으로 객체를 담아둔 상태이므로
이상태를 체크해서 HttpSession에 저장합니다.

LoginInterceptor의 설정은 /loginPost의동작에서 이뤄져야 하므로, servlet-context.xml에서 설정
*/