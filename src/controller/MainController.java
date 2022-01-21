package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ctrl + shift + o => 자동임포트
//ctrl + space => 수동 임포트  
//애노테이션은 자신의 밑에 나오는 매서드, 클래스, 멤버변수를 수사해주는 녀석
//@WebServlet("/abc")
public class MainController extends HttpServlet {
	
	//doGet은 HttpServelt에 요청이 들어왔을 때 그게 Get요청이면 실행되는 매서드에요
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("Hello Get");
//	}
	//post가 오건 get이 오건 둘다 동작
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//PrintWriter out = resp.getWriter();
		req.setAttribute("gmh", "Hello gmh");
		
		String path = "/main.jsp";
		
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy"); 
	}
}


// url 주소 => 자바클래스 1:1 
// /abc => MainController가 실행되도록 