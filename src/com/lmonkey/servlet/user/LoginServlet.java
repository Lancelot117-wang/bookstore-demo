package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int count = LMONKEY_USERDao.selectByNM(username, password);
		
		if(count > 0) {
			HttpSession session = request.getSession();
			LMONKEY_USER user = LMONKEY_USERDao.selectUser(username, password);
			session.setAttribute("user", user);
			session.setAttribute("isLogin", "1");
			
			response.sendRedirect("indexselect");
		}else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('�û���¼ʧ��');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			
			out.close();
		}
	}

}