package com.mysp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysp.vo.SampleVO;
@RestController
@RequestMapping("/sample")
public class SampleController {
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
	  SampleVO sample = new SampleVO();
	  sample.setFirstName("길동");
	  sample.setLastName("홍");
	  sample.setMno(123);
	  return sample;
	}
}
