package com.lmonkey.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RegisterFilter
 */
@WebFilter("/register")
public class RegisterFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RegisterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String username = req.getParameter("username");
		
		PrintWriter out = resp.getWriter();
		
		if(username.equals("")) {
			out.write("<script>");
			out.write("alert('用户名不能为空')");
			out.write("location.href='reg.jsp'");
			out.write("</script>");
			out.close();
			return;
		}
		
		HttpSession session = req.getSession();
		
		String verifyCode = req.getParameter("verifyCode");
		String sysCode = (String)session.getAttribute("code"); 
		
		if(!verifyCode.equals(sysCode)) {
			out.write("<script>");
			out.write("alert('验证码输入错误')");
			out.write("location.href='reg.jsp'");
			out.write("</script>");
			out.close();
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
