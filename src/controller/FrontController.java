package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	private Map<String, Controller> uriMap = null;
	
	@Override
	public void init() throws ServletException {
		
		uriMap = new HashMap<String, Controller>();
		uriMap.put("/", new IndexController());
		uriMap.put("/member", new MemberController());
		uriMap.put("/memberMoneyList", new MoneyController());
		uriMap.put("/memberInsert", new RegisterController());
		uriMap.put("/member/update", new UpdateController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		// http://localhost:8080/testWeb/board
		
		String url = req.getRequestURI();

		String contextPath = req.getContextPath();

		String path = url.substring(contextPath.length());

		Controller c = uriMap.get(path);
		
		String view = "notfound";
		
		if(c != null) {
			view = c.execute(req, resp);	
		}
		
		if(view.startsWith("redirect::")) {
			// redirect::/admin
			String redirectUrl = view.substring("redirect::".length());
			// /admin
			resp.sendRedirect(redirectUrl);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
			rd.forward(req, resp);	
		}
		
		// REST API
		
	}
	
}
