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
 * Servlet implementation class OrderSelect
 */
@WebServlet("/orderselect")
public class OrderSelect extends HttpServlet {
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
		
		String cids = request.getParameter("ids");
		
		if(user!=null && isLogin.equals("1")) {
			String ids[] = cids.split(",");
			
			int total_price = 0;
			
			ArrayList<LMONKEY_CART> list = new ArrayList<LMONKEY_CART>();
			
			for(int i=0; i<ids.length; i++) {
				LMONKEY_CART c = LMONKEY_CARTDao.selectById(ids[i]);
				
				total_price += c.getCART_P_PRICE() * c.getCART_QUANTITY();
				
				list.add(c);
			}
			
			request.setAttribute("list", list);
			request.setAttribute("totalprice", total_price);
			
			request.getRequestDispatcher("order.jsp").forward(request, response);
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
