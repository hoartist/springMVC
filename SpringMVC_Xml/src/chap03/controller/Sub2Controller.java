package chap03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sub2") //공통부분
public class Sub2Controller {
	
	@RequestMapping("/test5")
	public String sub2Test5() {
		return "/chap03/sub2/test5";
	}
	
	@RequestMapping("/test6")
	public String sub2Test6() {
		return "/chap03/sub2/test6";
	}
	
}
