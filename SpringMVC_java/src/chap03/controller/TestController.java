package chap03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test1")
	public String test1() {
		return "/chap03/test1";
	}
	
	@RequestMapping("/test2")
	public String test2() {
		return "/chap03/test2";
	}
	
}
