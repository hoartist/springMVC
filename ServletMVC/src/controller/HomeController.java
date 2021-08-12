package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Test2Service;

@WebServlet("*.mvc")
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* System.out.println("HmoeController"); */
		
		//��û�� �ּҸ� �����´�.
		String url = request.getRequestURI();
		System.out.println("url : "+url);
		
		//View�� ����� JSP ����
		String viewName = null;
	
		if(url.contains("main.mvc")) {
//			System.out.println("main ��û");
			
			viewName = "main.jsp";
			
		}else if(url.contains("test1.mvc")) {
			/* System.out.println("test1 ��û"); */
			viewName = "test1.jsp";
			
			//�Ķ���� ������ ����
			String str1 = request.getParameter("data1");
			String str2 = request.getParameter("data2");
			
			int number1 = Integer.parseInt(str1);
			int number2 = Integer.parseInt(str2);
			
			int result = number1 + number2;
			
			request.setAttribute("result", result);
			
		}else if(url.contains("test2.mvc")) {
			/* System.out.println("test2 ��û"); */
			
			//�� ��û
			int result = Test2Service.minus(request);
			
			viewName = "test2.jsp";
			
			request.setAttribute("result", result);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewName);
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
