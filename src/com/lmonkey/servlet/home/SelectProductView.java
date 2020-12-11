package com.lmonkey.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.service.LMONKEY_CATEGORYDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class SelectProductView
 */
@WebServlet("/selectproductview")
public class SelectProductView extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<LMONKEY_CATEGORY> plist = LMONKEY_CATEGORYDao.selectCate("parent");
		request.setAttribute("plist", plist);
		
		ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		
		ArrayList<Integer> ids = (ArrayList<Integer>)session.getAttribute("ids");
		
		if(ids == null) {
			ids = new ArrayList<Integer>();
		}
		
		if(ids.size() >= 5) {
			ids.remove(0);
		}
		
		if(id!=null&&(!ids.contains(Integer.parseInt(id)))) {
			ids.add(Integer.parseInt(id));
		}
		
		session.setAttribute("ids", ids);
		
		ids = (ArrayList<Integer>)session.getAttribute("ids");
		
		if(ids!=null) {
			ArrayList<LMONKEY_PRODUCT> lastlist = LMONKEY_PRODUCTDao.selectAllById(ids);
			
			request.setAttribute("lastlist", lastlist);
		}
		
		LMONKEY_PRODUCT p = null;
		
		if(id!=null) {
			p = LMONKEY_PRODUCTDao.selectById(Integer.parseInt(id));
			request.setAttribute("p", p);
		}
		
		if(p!=null) {
			int cid = p.getPRODUCT_CID();
			ArrayList<LMONKEY_PRODUCT> productlist = LMONKEY_PRODUCTDao.selectByCid(cid);
			request.setAttribute("productlist", productlist);
			
			LMONKEY_CATEGORY cate = LMONKEY_CATEGORYDao.selectById(cid);
			
			request.setAttribute("cate", cate);
		}
		
		request.getRequestDispatcher("productview.jsp").forward(request, response);
	}

}
