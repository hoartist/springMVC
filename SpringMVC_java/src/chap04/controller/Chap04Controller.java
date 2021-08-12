package chap04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Chap04Controller {

	@RequestMapping(value="/chap04/test1", method= RequestMethod.GET)
	public String test1_get() {
		return "/chap04/test1";
	}
	
	
	@RequestMapping(value="/chap04/test2",method=RequestMethod.POST)
	public String test2_post() {
		return "/chap04/test2";
	}

	@RequestMapping(value="/chap04/test3",method=RequestMethod.GET)
	public String test3_get() {
		return "/chap04/test3_get";
	}
	
	@RequestMapping(value="/chap04/test3",method=RequestMethod.POST)
	public String test3_post() {
		return "/chap04/test3_post";
	}

	@GetMapping("/chap04/test4")
	public String test4() {
		return "/chap04/test4";
	}
	
	@PostMapping("/chap04/test5")
	public String test5() {
		return "/chap04/test5";
	}
	
	@GetMapping("/chap04/test6")
	public String test6_get() {
		return "/chap04/test6_get";
	}
	
	@PostMapping("/chap04/test6")
	public String test6_post() {
		return "/chap04/test6_post";
	}
	
	@RequestMapping(value="/chap04/test7",method= {RequestMethod.GET, RequestMethod.POST})
	public String test7() {
		return "/chap04/test7";
	}
	
	
	/*
	-> 오류
	@GetMapping("/chap04/test8")
	public String test8_get() {
		return "/chap04/test8";
	}
	
	@GetMapping("/chap04/test8")
	public String test8_post() {
		return "/chap04/test8";
	}
	
	*/
	
	/* ↓ 나중에 파라미터를 주입받을 때 불편할 수 있음 */ 
	@GetMapping("/chap04/test8")
	public String test8_get() {
		return test8_post();
	}
	
	@PostMapping("/chap04/test8")
	public String test8_post() {
		return "/chap04/test8";
	}
}
