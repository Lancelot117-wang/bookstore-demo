package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		String sex = request.getParameter("sex");
		String year = request.getParameter("birthday");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		LMONKEY_USER u = new LMONKEY_USER(username, name, pwd, sex, year, null, email, mobile, address, 1);
		
		int count = LMONKEY_USERDao.insert(u);
		
		System.out.println(count);
		System.out.println(u);
		
		if(count > 0) {
			response.sendRedirect("login.jsp");
		} else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('ÓÃ»§×¢²áÊ§°Ü');");
			out.write("location.href='reg.jsp';");
			out.write("</script>");
			
			out.close();
		}
	}

}
