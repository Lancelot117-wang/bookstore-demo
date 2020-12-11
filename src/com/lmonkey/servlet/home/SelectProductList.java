package com.lmonkey.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.service.LMONKEY_CATEGORYDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class SelectProductList
 */
@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<LMONKEY_CATEGORY> plist = LMONKEY_CATEGORYDao.selectCate("parent");
		request.setAttribute("plist", plist);
		
		ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		
		int id=0;
		ArrayList<LMONKEY_PRODUCT> list = null;
		
		if(pid!=null) {
			id=Integer.parseInt(pid);
			list = LMONKEY_PRODUCTDao.selectByPid(id);
		}
		
		if(cid!=null) {
			id=Integer.parseInt(cid);
			list = LMONKEY_PRODUCTDao.selectByCid(id);
		}
		
		request.setAttribute("title",LMONKEY_CATEGORYDao.selectById(id).getCATE_NAME());
		request.setAttribute("list", list);
				
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}


}
