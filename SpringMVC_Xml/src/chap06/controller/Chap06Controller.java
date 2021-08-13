package chap06.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import chap06.beans.DataBean;
import chap06.beans.DataBean2;



@Controller
public class Chap06Controller {
	
	@GetMapping("/chap06/test1")
	public String test1(@RequestParam Map<String,String> map, @RequestParam List<String> data3) {
		String data1 = map.get("data1");
		String data2 = map.get("data2");
		
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
		
		for(String str : data3) {
			System.out.println("data3 : "+str);
		}
	
		return "chap06/result";
		
	}
	
	
/* ↓Map과 List 사용시 모든 value는 String 형태로 담기게 된다. (자동 형변환 X) */
//	@GetMapping("/chap06/test1")
//	public String test1(@RequestParam Map<String,Integer> map, @RequestParam List<Integer> data3) {
//		
//		int data1 = map.get("data1");
//		int data2 = map.get("data2");
//		
//		System.out.println("data1 : "+data1);
//		System.out.println("data2 : "+data2);
//		
//		for(int number : data3) {
//			System.out.println("data3 : " + number);
//		}
//	
//		return "chap06/result";
//		
//	}
	
	@GetMapping("/chap06/test2")
	public String test2(@ModelAttribute DataBean bean1, DataBean2 bean2) {
		
		
		System.out.println("data1 : "+bean1.getData1());
		System.out.println("data2 : "+bean1.getData2());
		
		for(int number : bean1.getData3()) {
			System.out.println("data3 : "+number);
		}
		
		System.out.println("====================================");
		
		
		System.out.println("bean2.data1 : "+bean2.getData1());
		System.out.println("bean2.data2 : "+bean2.getData2());
		return "chap06/result";
	
	}
}





