package com.wz.bs.filter;

import java.io.IOException;
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

import com.wz.bs.entity.Admin;

/**
 * Servlet Filter implementation class PermissionFilter
 */
@WebFilter(filterName = "/PermissionFilter", urlPatterns = "/permission/*")
public class PermissionFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession hs = request.getSession();
		// 在session中获得用户对象
		Admin admin = (Admin) hs.getAttribute("admin");
		// 检查用户是否存在，以判断用户是否登录
		if (admin != null) {
			chain.doFilter(request, response);
		} else {
			// 如果用户不存在,则跳转到首页
			response.sendRedirect(request.getContextPath() + "/index.jsp?info=3");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
