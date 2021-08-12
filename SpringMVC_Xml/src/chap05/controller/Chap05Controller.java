package chap05.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class Chap05Controller {
	

	@GetMapping("/chap05/test1")
	public String test1(HttpServletRequest request) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
		
		return "/chap05/result";
	}
	
	
	@GetMapping("/chap05/test2")
	public String test2(WebRequest request) {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String[] data3 = request.getParameterValues("data3");
		
		System.out.println("data1 : " + data1);
		System.out.println("data2 : " + data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
		
		
		return "/chap05/result";
	}
	
	@GetMapping("chap05/test3/{data1}/{data2}/{data3}/{data4}")
	public String test3(@PathVariable int data1, @PathVariable int data2, 
						@PathVariable String data3, @PathVariable String data4) {
	
		int sum = data1 + data2;
		
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
		System.out.println("sum : " + sum);
		System.out.println("data3 : "+data3);
		System.out.println("data4 : "+data4);
		
		return "/chap05/result";
	}
	
	
	@GetMapping("chap05/test4")
	public String test4(@RequestParam int data1, @RequestParam int data2,
						@RequestParam int data3) {
		
		int add = data1 + data2 + data3;
		
		System.out.println("add : "+add);
		
		return "/chap05/result";
	}
	
	@GetMapping("chap05/test5")
	public String test5(@RequestParam(value="data1") int value1, @RequestParam(value="data2") int value2,
						@RequestParam(value="data3") int value3) {
		
		int add = value1 + value2 + value3;
		
		System.out.println("add : "+add);
		
		return "/chap05/result";
	}
	
	
//  ↓ 넘어오지 않은 데이터를 받는다면 오류가 발생한다.
//	@GetMapping("chap05/test6")
//	public String test6(@RequestParam String data1) {
//		
//		if(data1 != null) {
//			System.out.println("data1");
//		}
//		
//		return "/chap05/result";
//	}
	
	
	
//	@GetMapping("chap05/test6")
//	public String test6(@RequestParam(required = false) String data1) {
//		
//		System.out.println("data1 : "+data1);
//		
//		return "/chap05/result";
//	}
	
	@GetMapping("chap05/test6")
	public String test6(@RequestParam(defaultValue = "700") int data1 ) {
		System.out.println("data1 : "+data1);
		return "/chap05/result";
	}
	
}
