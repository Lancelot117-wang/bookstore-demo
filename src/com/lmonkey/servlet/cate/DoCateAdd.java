package com.lmonkey.servlet.cate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CATEGORYDao;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class DoCateAdd
 */
@WebServlet("/manage/admin_docateadd")
public class DoCateAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int pid = Integer.parseInt(request.getParameter("parentId"));
		String name = request.getParameter("className");
		
		LMONKEY_CATEGORY u = new LMONKEY_CATEGORY(0, name, pid);
		
		int count = LMONKEY_CATEGORYDao.insert(u);
		
		if(count > 0) {
			response.sendRedirect("admin_cateselect");
		} else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('·ÖÀàÌí¼ÓÊ§°Ü')");
			out.write("location.href='manage/admin_cateadd.jsp'");
			out.write("</script>");
		}
	}

}
