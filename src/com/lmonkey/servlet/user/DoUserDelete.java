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
 * Servlet implementation class DoUserDelete
 */
@WebServlet("/manage/admin_douserdelete")
public class DoUserDelete extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		int count = LMONKEY_USERDao.deleteById(id);
		
		if(count > 0) {
			response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
		} else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('”√ªßÃÌº” ß∞‹')");
			out.write("location.href='manage/admin_douserselect?cp="+request.getParameter("cpage")+"'");
			out.write("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String ids[] = request.getParameterValues("id[]");
		
		for(int i=0; i<ids.length; i++) {
			LMONKEY_USERDao.deleteById(ids[i]);
		}
		
		response.sendRedirect("/MonkeyShop/manage/admin_douserselect");
	}

}
