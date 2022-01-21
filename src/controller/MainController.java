package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ctrl + shift + o => �ڵ�����Ʈ
//ctrl + space => ���� ����Ʈ  
//�ֳ����̼��� �ڽ��� �ؿ� ������ �ż���, Ŭ����, ��������� �������ִ� �༮
//@WebServlet("/abc")
public class MainController extends HttpServlet {
	
	//doGet�� HttpServelt�� ��û�� ������ �� �װ� Get��û�̸� ����Ǵ� �ż��忡��
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("Hello Get");
//	}
	//post�� ���� get�� ���� �Ѵ� ����
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


// url �ּ� => �ڹ�Ŭ���� 1:1 
// /abc => MainController�� ����ǵ��� 