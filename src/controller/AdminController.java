package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WelcomeDAO;
import vo.WelcomeVO;

//컨트롤러 인터페이스를 구현한 컨트롤러
public class AdminController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		
		if(method.equalsIgnoreCase("POST"))
		{
			String msg = req.getParameter("msg");
			int id = Integer.parseInt(req.getParameter("id"));
			
			WelcomeDAO.dao.add(id, msg);
			
			return "redirect::/admin";
		}
		
		int maxId = WelcomeDAO.dao.getMaxID();
		List<WelcomeVO> list = WelcomeDAO.dao.getList();
		
		req.setAttribute("maxId", maxId);
		req.setAttribute("list", list);
		
		return "admin";
	}
}
