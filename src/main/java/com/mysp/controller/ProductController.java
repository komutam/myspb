package com.mysp.controller;

import java.io.File;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.mysp.service.ProductService;
import com.mysp.vo.ProductVO;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Inject
	private ProductService productService;
	
	//상품전체 목록
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public ModelAndView prolist(ModelAndView mavs) throws Exception{
		mavs.setViewName("/product/productList");
		mavs.addObject("list", productService.prolist());
		return mavs;
	}
	
	//상품상세
	@RequestMapping("/productInfo/{proid}")
	//페이지 맵핑은 하나의 URL이 하나의 고유한 리소스를 대표(Rest)할 수 있도록 처리해
	//리턴타입을 ModelAndView로 설정하여 view의 이름과 service에서 가져온 상품 객체를 리턴
	public ModelAndView proinfo(@PathVariable("proid") int proid, ModelAndView mavs) throws Exception{
		mavs.setViewName("product/productInfo");
		mavs.addObject("vo", productService.proinfo(proid));
		return mavs;
	}
	
	//상품등록 GET
	@RequestMapping(value="/productWrite", method=RequestMethod.GET)
	public void insertGET(ProductVO vo, Model model) throws Exception{
		
	}
	//상품등록 POST
	@RequestMapping(value="/productInsert", method=RequestMethod.POST)
	public String insertPOST(ProductVO vo) throws Exception{
		String filename="";
		//사진이 있을경우
		if(vo.getProductPhoto() != null) {
			filename=vo.getProductPhoto().getOriginalFilename();
			String path = "C:\\_stsSpring\\mysp\\src\\main\\webapp\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdirs(); //디렉토리 생성
				vo.getProductPhoto().transferTo(new File(path+filename)); //임시 디렉토리에 저장된 파일을 지정 디렉토리로 전송
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setProurl(filename);
			productService.insertProduct(vo);
		}
		return "redirect:/product/productList";
	}
	
	//상품수정 - 페이지
	@RequestMapping("/productEdit/{proid}")
	public ModelAndView productEdit(@PathVariable("proid") int proid, ModelAndView mav) throws Exception{
		mav.setViewName("/product/productEdit");
		mav.addObject("vo", productService.proinfo(proid));
		return mav;
	}
	//상품수정 - 처리
	@RequestMapping("/productUpdate")
	public String productUpdate(ProductVO vo) throws Exception{
		String filename = "";
		//첨부 사진파일 변경될 경우
		if(vo.getProductPhoto() != null) {
			filename = vo.getProductPhoto().getOriginalFilename();
			String path = "C:\\_stsSpring\\mysp\\src\\main\\webapp\\WEB-INF\\views\\images\\";
			try {
				new File(path).mkdirs();
				vo.getProductPhoto().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setProurl(filename);
		}else { //변경되지 않을 경우
			ProductVO vo2 = productService.proinfo(vo.getProid());
            vo.setProurl(vo2.getProurl());
		}
		productService.proupdate(vo);
		return "redirect:/product/productList";
	}
	
	//상품삭제
	@RequestMapping(value="/productDelete", method= {RequestMethod.POST, RequestMethod.GET})
	public String productDelete(@RequestParam("proid") int proid) throws Exception{
		//상품 이미지 정보
		String filename = productService.fileInfo(proid);
		String path = "C:\\_stsSpring\\mysp\\src\\main\\webapp\\WEB-INF\\views\\images\\";
		//상품 이미지 삭제
		if(filename != null) {
			File file = new File(path+filename);
			if(file.exists()) {
				file.delete();
			}
		}
		productService.prodelete(proid);
		return "redirect:/product/productList";
	}
}
