package com.mysp.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.mysp.dto.AdminDTO;
import com.mysp.service.AdminService;
import com.mysp.vo.AdminVO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Inject
	private AdminService adminService;
	
	//admin login GET 
	@RequestMapping(value="/adminLogin", method=RequestMethod.GET)
	public void adminLogin(@ModelAttribute("adto") AdminDTO adto) {
		
	}
	
	//admin login POST
	@RequestMapping(value="/aloginPost", method=RequestMethod.POST)
	public void aloginPOST(AdminDTO adto, HttpSession session, Model model) throws Exception{
		try {
			AdminVO avo = adminService.adminLogin(adto);
			
			if(avo == null) {
				return;
			}
			model.addAttribute("adminVO", avo);

			if(adto.isUseCookie()) {
				int amount = 60 * 60 * 24 * 7;
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				adminService.AKeepLogin(avo.getAdminid(), session.getId(), sessionLimit);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//logout
	@RequestMapping(value="/adminLogout", method=RequestMethod.GET)
	public void adminLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Object aobj = session.getAttribute("adminLogin");
		
		if(aobj != null) {
			AdminVO avo = (AdminVO) aobj;
			
			session.removeAttribute("adminLogin");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			response.sendRedirect("/mysp/");
			
			if(loginCookie != null) {
				loginCookie.setPath("/mysp/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				adminService.AKeepLogin(avo.getAdminid(), session.getId(), new Date());
			}
		}
	}
	
}
