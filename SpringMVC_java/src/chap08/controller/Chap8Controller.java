package chap08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import chap08.beans.DataBean;

@Controller
public class Chap8Controller {
	
	@PostMapping("/chap08/test1")
	public String test1(@ModelAttribute DataBean bean) {
		
		System.out.println("data1 : "+bean.getData1());
		System.out.println("data2 : "+bean.getData2());
		
		return "chap08/test1";
		
	}
	
	@PostMapping("/chap08/test2")
	public String test2(@ModelAttribute("testData") DataBean bean) { //jsp에서 사용할 이름을 따로 지정할 경우 @ModelAttribute를 사용해야 함 (원래는 생략가능)
		return "chap08/test2";
	}
	
}
