package com.mysp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysp.service.SignupService;
import com.mysp.vo.SignupVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private SignupService signupService;
	
	//회원가입 GET
	 @RequestMapping(value="/signup", method=RequestMethod.GET)
	    public void signupGET() {
	        
	    }
	 
	 //회원가입 POST
	 @RequestMapping(value="/signup", method=RequestMethod.POST)
	 public String signupPOST(SignupVO signVO, RedirectAttributes rttr) {
		 signupService.insertMember(signVO);
		 rttr.addFlashAttribute("msg", "success");
		 return "redirect:/";
	 }
	 
	 //아이디 중복체크 ajax
	 @ResponseBody//Ajax사용을 위해 @ResponseBody 어노테이션 사용 ResponseBody 어노테이션이 없으면 Ajax 데이터를 못받음
	 @RequestMapping(value="/checkId", method={ RequestMethod.GET, RequestMethod.POST})
	 public Map<Object, Object> checkId(String userid) throws Exception{//JSON으로 넘어온 userid를 매개변수로 받는다.
		 Map<Object, Object> map = new HashMap<Object, Object>();
		 /*
	         HashMap은 Map을 구현한다. Key와 value를 묶어 하나의 entry로 저장한다는 특징을 갖는다. 그리고 hashing을 사용하기 때문에 많은양의 데이터를 검색하는데 뛰어난 성능을 보인다.
	         Map 인터페이스의 한 종류로 ( "Key", value) 로 이뤄져 있다.
	         key 값을 중복이 불가능 하고 value는 중복이 가능. value에 null값도 사용 가능하다.
	             멀티쓰레드에서 동시에 HashMap을 건드려 Key - value값을 사용하면 문제가 될 수 있다. 멀티쓰레드에서는 HashTable을 쓴다
          */
		 int count = signupService.checkId(userid);
	     map.put("check", count);
	     
		 return map;
	 }
}
