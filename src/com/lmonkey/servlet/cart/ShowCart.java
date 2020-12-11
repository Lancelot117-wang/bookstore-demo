package com.lmonkey.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.LMONKEY_CATEGORYDao;

/**
 * Servlet implementation class ShowCart
 */
@WebServlet("/showcart")
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		ArrayList<LMONKEY_CATEGORY> plist = LMONKEY_CATEGORYDao.selectCate("parent");
		request.setAttribute("plist", plist);
		
		ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		HttpSession session = request.getSession();
		
		String isLogin = (String)session.getAttribute("isLogin");
		
		LMONKEY_USER user = (LMONKEY_USER)session.getAttribute("user");
		
		if(user!=null && isLogin.equals("1")) {
			String uid = (String)user.getUSER_ID();
			
			ArrayList<LMONKEY_CART> list = LMONKEY_CARTDao.selectByUid(uid);
			
			System.out.println(list.size());
			
			request.setAttribute("cartlist", list);
			
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
		}else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('ÇëÏÈµÇÂ¼');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			
			out.close();
			
			return;
		}
	}

}
