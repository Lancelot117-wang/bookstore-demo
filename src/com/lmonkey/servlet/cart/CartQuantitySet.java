package com.lmonkey.servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.service.LMONKEY_CARTDao;

/**
 * Servlet implementation class CartQuantitySet
 */
@WebServlet("/cartquantityset")
public class CartQuantitySet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String count = request.getParameter("count");
		String id = request.getParameter("id");
		
		LMONKEY_CARTDao.updateCartQuantity(Integer.parseInt(id), Integer.parseInt(count));
	}

}
