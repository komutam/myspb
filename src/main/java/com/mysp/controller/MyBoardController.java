package com.mysp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysp.service.MyBoardService;
import com.mysp.vo.Criteria;
import com.mysp.vo.MyBoardVO;
import com.mysp.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class MyBoardController {
	private static final Logger logger = LoggerFactory.getLogger(MyBoardController.class);
	
	@Autowired
	private MyBoardService service;
	
	@RequestMapping(value="/writePage", method=RequestMethod.GET)
	public void writeGET(MyBoardVO myboard, Model model) throws Exception{
		logger.info("write get...");
	}
	
	@RequestMapping(value="/writePage", method=RequestMethod.POST)
	public String writePOST(MyBoardVO myboard, RedirectAttributes rttr) throws Exception{
		service.write(myboard);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listPage";
	}
	
/*	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPageGET(Model model) throws Exception {
		logger.info("show all list............");
	}*/
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public ModelAndView read(@RequestParam("bno") int bno, Model model, @ModelAttribute("cri") Criteria cri, HttpSession session) throws Exception{
		service.readCnt(bno, session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/readPage");
		mav.addObject("dto", service.read(bno));
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		service.delete(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modify(@RequestParam("bno") int bno, Model model, @ModelAttribute("cri")Criteria cri) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPost(MyBoardVO myboard, RedirectAttributes rttr, Criteria cri) throws Exception{
		service.updateWrite(myboard);
		System.out.println(myboard.getTitle());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listPage";
	}
}
