package chap03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Sub1Controller {
	
	@RequestMapping(value="/sub1/test3")
	public String sub1Test3() {
		return "/chap03/sub1/test3";
	}
	
	@RequestMapping("/sub1/test4")
	public String sub1Test4() {
		return "/chap03/sub1/test4";
	}
	
}
