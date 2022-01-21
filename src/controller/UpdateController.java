package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

public class UpdateController implements Controller {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("POST")) {
			int custno = Integer.parseInt(req.getParameter("custno"));
			String custname = req.getParameter("custname");
			String phone = req.getParameter("phone");
			String address = req.getParameter("address");
			Date joindate = Date.valueOf( req.getParameter("joindate") );
			String grade = req.getParameter("grade");
			String city = req.getParameter("city");
			
			MemberVO vo = new MemberVO();
			vo.setCustno(custno);
			vo.setCustname(custname);
			vo.setPhone(phone);
			vo.setAddress(address);
			vo.setJoinDate(joindate);
			vo.setGrade(grade);
			vo.setCity(city);
			
			int n = MemberDAO.dao.updateMemeber(vo);
			return "redirect::/member"; //멤버 업데이트 이후 멤버 리스트로 다시 보여진다.
			
		}else if(method.equalsIgnoreCase("GET"))
		{
			int custno =  Integer.parseInt(req.getParameter("custno"));
			MemberVO vo = MemberDAO.dao.getMemeber(custno);
			
			req.setAttribute("member", vo);
			return "update";
		}
		
		return "redirect::/";
	}
}
