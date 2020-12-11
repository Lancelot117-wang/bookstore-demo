package com.lmonkey.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		LMONKEY_PRODUCT p = null;
		
		String pid = request.getParameter("id");
		String count = request.getParameter("count");
		String url = request.getParameter("url");
		
		HttpSession session = request.getSession();
		
		String isLogin = (String)session.getAttribute("isLogin");
		
		LMONKEY_USER user = (LMONKEY_USER)session.getAttribute("user");
		
		if(user!=null && isLogin.equals("1")) {
			String uid = user.getUSER_ID();
			
			LMONKEY_CART srcCart = LMONKEY_CARTDao.selectCartById(uid, pid);
			
			if(srcCart!=null) {
				int srcCount = srcCart.getCART_QUANTITY();
				
				int newCount = srcCount + Integer.parseInt(count);
				
				LMONKEY_CARTDao.updateCartQuantity(srcCart.getCART_ID(), newCount);
			}else {
				if(pid!=null) {				
					p = LMONKEY_PRODUCTDao.selectById(Integer.parseInt(pid));
				}
				
				LMONKEY_CART cart = new LMONKEY_CART(
						0, 
						p.getPRODUCT_FILENAME(), 
						p.getPRODUCT_NAME(), 
						p.getPRODUCT_PRICE(), 
						Integer.parseInt(count), 
						p.getPRODUCT_STOCK(), 
						p.getPRODUCT_ID(), 
						uid, 
						1);
				
				LMONKEY_CARTDao.insert(cart);
			}
						
		}else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('ÇëÏÈµÇÂ¼');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			
			out.close();
			
			return;
		}
		
		if(url.equals("z")) {
			response.sendRedirect("showcart");
		}else {
			response.sendRedirect("selectproductview?id="+pid);
		}
	}

}
