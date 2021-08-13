package chap07.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Chap07Controller {
	
	@GetMapping("chap07/test1")
	public String test1() {
		return "chap07/test1";
	}
	
	@GetMapping("chap07/test2")
	public String test2(HttpServletRequest request) {
		
		//request 객체에 담긴 모든 데이터들은 modelAndView에 담기고 modelAndView는 viewResolver에게 전달된다.
		request.setAttribute("data1", 10);
		request.setAttribute("data2", 20);
		
		return "chap07/test2";
	}
	
	@GetMapping("chap07/test3")
	public String test3(Model model) {
		
		model.addAttribute("data1",300);
		model.addAttribute("data2", 400);
		
		
		return "chap07/test3";
	}
	
	@GetMapping("chap07/test4")
	public ModelAndView test4(ModelAndView mv) {
		
		mv.addObject("data1", 500);
		mv.addObject("data2", 600);
		mv.setViewName("chap07/test4");
		
		return mv;
	}
}
