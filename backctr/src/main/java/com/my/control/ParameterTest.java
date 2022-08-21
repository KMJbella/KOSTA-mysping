package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.my.dto.Product;

@Controller
public class ParameterTest {
	@GetMapping("a")
	public void a() {
		System.out.println("a메서드 호출");
	}

	@GetMapping("b") //http://localhost:8888/backctr/b?no=1
	public void b(HttpServletRequest request) {
		System.out.println(request.getParameter("no"));
	}
	
	@GetMapping("c") 
	public void c(HttpServletResponse response) throws IOException {
		response.sendRedirect("http://www.google.com");
	}
	
	@GetMapping("d") 
	public void d(HttpSession session) {
		System.out.println("세션 새로 생성여부:" + session.isNew());
	}
	@GetMapping("e") //http://localhost:8888/backctr/e?prodNo=C0001&prodName=아메리카노&prodPrice=1000
	public void e(String prodNo, String prodName, int prodPrice) {
		System.out.println("prodNo=" + prodNo);
		System.out.println("prodName=" + prodName);
		System.out.println("prodPrice=" + prodPrice);
	}
	
	@GetMapping("f") //http://localhost:8888/backctr/f?prod_no=C0001&prodName=아메리카노&prodPrice=1000
					 //http://localhost:8888/backctr/f?prod_no=C0001&prodPrice=1000
					 //http://localhost:8888/backctr/f?prod_no=C0001
	public void f(@RequestParam(name="prod_no") String prodNo, 
				  @RequestParam(required = false)String prodName, //false 선택전달사항
				  @RequestParam(required = false, defaultValue = "0")int prodPrice) { //int는 전달되지 않았을 때에 기본값 0 으로 설정
		System.out.println("prodNo=" + prodNo);
		System.out.println("prodName=" + prodName);
		System.out.println("prodPrice=" + prodPrice);
	}
	
	@GetMapping("g")//http://localhost:8888/backctr/g?prodNo=C0001&prodName=아메리카노&prodPrice=1000
					//http://localhost:8888/backctr/g?prodNo=C0001
	public void g(Product p) {
		System.out.println("prodNo=" + p.getProdNo());
		System.out.println("prodName=" + p.getProdName());
		System.out.println("prodPrice=" + p.getProdPrice());
	}
	//http://localhost:8888/backctr/h?arr=one&arr=two&arr=three
	@GetMapping("h")
	public void h(String[] arr) {
		for(String str: arr) { //str에서 arr을 하나씩 꺼내서 사용
			System.out.println(str);
		}
	}
	//http://localhost:8888/backctr/i?prodNo=1&prodName=a&prodNo=2&prodName=b
	@PostMapping("i")
//	public void i(String[]prodNo, String[]prodName) {
//		for(String no:prodNo) {
//			System.out.println(no);
//			
//		}
//		for(String name:prodName) {
//			System.out.println(name);
//		}
//		for(int i=0; i<prodNo.length; i++) {
//			System.out.println(prodNo[i] + ":" + prodName[i]);
//		}
//	}
	public void i(@RequestBody Product[] list) {
		for(Product p:list) {
			System.out.println(p);
		}
	}
	
	
}






















