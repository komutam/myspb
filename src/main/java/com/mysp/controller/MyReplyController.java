package com.mysp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysp.service.MyReplyService;
import com.mysp.vo.Criteria;
import com.mysp.vo.PageMaker;
import com.mysp.vo.ReplyVO;

@Controller
@RequestMapping("/replies")
public class MyReplyController {
	@Inject
	private MyReplyService service;
	
	
/*1. 스프링 MVC의 경우 REST 방식의 처리에서 사용하는 특별한 애노테이션은 다음과 같습니다.
    @PathVariable - URI의 경로에서 원하는 데이터를 추출하는 용도로 사용
    @RequestBody-전송된 JSON데이터를 객체로 변환해 주는 애노테이션으로 @ModelAttribute와 유사한 역할을 하지만 JSON에서 사용한다는 점이 차이
2. 등록 작업은 '/replies/' URI로 처리되고 POST 방식으로 전송됩니다. 
3. 작성된 register()의 리턴타입은 ResponseEntity<String>로 설계되었습니다.
4. 만일 새로운 댓글을 등록하는데 실패하면 try~catch에 선언되었듯이 예외의 원인 메시지를 전송하고, 사용자에게는 BAD_REQUEST(400)를 결과로 전송합니다. 
     데이터의 전송방식은 JSON 포맷을 이용할 것이므로 이를 처리하는 @RequestBody애노테이션이 필요합니다.
5. 테스트할 시에는 프로젝트를 실행한 후 Advanced REST Client를 이용
	*/

	//댓글등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply) {
		ResponseEntity<String> entity = null;
		try {
			service.addReply(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

//----------------------------------------------------------------
	
	/*
	특정 게시물의 전체 댓글 목록의 처리 - REST GET 방식
	1. 특정 게시물의 전체 댓글의 목록은 GET 방식으로 처리돼야하고 반드시 특정 게시물의 번호(bNo)를 필요로 하기 때문에 다음과 같은 형태로 작성합니다.
	2. @RequestMapping()을 보면 URI내의 경로 {bNO}를 활용합니다. {bNO}는 메소드의 파라미터에서 @PathVariable("bNO")로 활용됩니다.
	     메소드의 처리가 성공하는 경우에는 HttpStatus.OK헤더를 전송하고, 데이터를 같이 전송합니다.
	3. 등록 작업과 마찬가지로 직접 예외를 처리하고 Advanced REST Client를 테스트했을 때 JSON 포맷의 데이터를 볼 수 있습니다.
	*/
	
	//특정 게시물의 전체댓글
	@RequestMapping(value="/all/{bNo}",  method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bNo") Integer bNo){
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>(service.listReply(bNo), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
//----------------------------------------------------------------------------
	
	/*
	수정 처리 - REST PUT/PATCH 방식
	REST 방식에서 update 작업은 PUT,PATCH 방식을 이용해서 처리합니다. 일반적으로 전체 데이터를 수정하는 경우에는 PUT을 이용하고,
	일부의 데이터를 수정하는 경우에는 PATCH를 이용합니다.
	예제로 사용하는 tbl_reply의 경우 변경될 만한 내용은 댓글의 내용을 의미하는 replyText밖에 없지만 PUT,PATCH 방식 모두 지원하도록 선언되었습니다.
	Advanced REST Client를 이용해서 테스트를 진행
	*/
	
	//수정처리
	@RequestMapping(value = "/{rNo}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("rNo") Integer rNo, @RequestBody ReplyVO reply){
		ResponseEntity<String> entity = null;
		try {
			reply.setrNo(rNo);
			service.modifyReply(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
//-----------------------------------------------------------------------------
	
	//삭제처리 REST DELETE 방식
	@RequestMapping(value = "/{rNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rNo") Integer rNo){
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//페이징 처리
	@RequestMapping(value="/{bNo}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bNo") Integer bNo, @PathVariable("page") Integer page){
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = service.listReplyPage(bNo, cri);
			map.put("list", list);
			
			int replyCount = service.count(bNo);
			pageMaker.setTotalCount(replyCount);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
}
