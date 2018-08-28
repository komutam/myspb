package com.mysp.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.mysp.dto.LoginDTO;
import com.mysp.service.SignupService;
import com.mysp.vo.SignupVO;

@Controller
@RequestMapping("/user") //일단 원래 member컨트롤러에서 합해서 해도 되는데, 일단은 따로해서 로그인 컨트롤러 따로 만듬
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private SignupService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		
	}
	
	//로그인 화면에서 /user/login 결과처리는 /users/loginPost로 설정된다.
	@RequestMapping(value="/loginPost", method=RequestMethod.POST) //POST 방식으로 파라미터를 이용해서 Model에 SignupVO 객체를 추가
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception { //실제로 로그인 처리가 이뤄지는 loginPost()에서는 Model 객체에 사용자가 존재하는 경우에 signupVO라는 이름으로 저장하게 된다.
		
		logger.info("LoginPost");
		
		try {
			SignupVO vo = service.login(dto);
			if(vo == null) {
				return;
			}
			model.addAttribute("signupVO", vo);
			
			//사용자가 자동 로그인 선택시 작동하는 기능 추가
			if(dto.isUseCookie()) {//코드의 핵심은 loginCookie 값이 유지되는 시간 정보를 데이터베이스에 저장하는 것
				int amount = 60 * 60 * 24 * 7;
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				service.KeepLogin(vo.getUserid(), session.getId(), sessionLimit);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	//로그아웃
	//로그아웃 처리는 HttpSession인 경우 login과 같이 저장된 정보를 삭제하고 invalidate(를 주는 작업과 쿠키의 유효시간을 변경하는 작업으로 이루어집니다.
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			SignupVO vo = (SignupVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			response.sendRedirect("/mysp/");
			
			if(loginCookie != null) {
				loginCookie.setPath("/mysp/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.KeepLogin(vo.getUserid(), session.getId(), new Date());
			}
		}
	}
	
}

/*가장 중요한 결정은 컨트롤러에서 HttpSession 객체를 처리할 것인가?
인터셉터에서 HttpSession을 처리할 것인가?라고 할 수 있습니다.

스프링 MVC는 컨트롤러에서 필요한 모든 자원을 파라미터에서 수집해서 처리하기 때문에
HttpSevletRequest나 HttpSession과 같은 자원들 역시 파라미터로 처리해도 아무런 문제가 없습니다.

이번에서는 컨트롤러는 좀 더 순수하게 데이터를 만들어 내는데 집중하고 인터셉터를 이용해서
HttpSession을 처리하도록 하겠습니다.

스프링의 인터셉터는 웹 관련 API를 처리하는 용도로 사용되기 때문에
가능하면 컨트롤러 역시 기존 방식과 동일하게 제작하는 것이 좋습니다.*/