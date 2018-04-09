package com.wz.bs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Category;
import com.wz.bs.service.AdminService;
import com.wz.bs.service.CategoryService;
import com.wz.bs.util.BeanFactory;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet(name = "/CategoryController", urlPatterns = "/permission/category/*")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = (CategoryService) BeanFactory.getBean("categoryService");
	AdminService adminService = (AdminService) BeanFactory.getBean("adminService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if ("/all".equals(pathInfo)) {
			List<Category> categories = categoryService.selectAll();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/WEB-INF/pages/category/categorylist.jsp").forward(request, response);
			session.removeAttribute("add1");
			session.removeAttribute("update1");
			session.removeAttribute("delete1");
		} else if ("/add".equals(pathInfo)) {
			String categoryname = request.getParameter("categoryname");
			String location = request.getParameter("location");
			Category category = new Category(1, categoryname, location, admin);
			Integer t = categoryService.add(category);
			if (t > 0) {
				session.setAttribute("add1", t);
				response.sendRedirect(request.getContextPath() + "/permission/category/all");
			}
		} else if ("/show".equals(pathInfo)) {
			String cid = request.getParameter("cid");
			Integer id = Integer.parseInt(cid);
			Category category = categoryService.selectOneById(id);
			request.setAttribute("updatecategory", category);
			request.getRequestDispatcher("/WEB-INF/pages/category/updatecategory.jsp").forward(request, response);
		} else if ("/update".equals(pathInfo)) {
			String cid = request.getParameter("cid");
			Integer id = Integer.parseInt(cid);
			String categoryname = request.getParameter("categoryname");
			Category category = categoryService.selectOneById(id);
			category.setName(categoryname);
			Integer t = categoryService.update(category);
			if (t > 0) {
				session.setAttribute("update1", t);
				response.sendRedirect(request.getContextPath() + "/permission/category/all");
			}
		} else if ("/delete".equals(pathInfo)) {
			String cid = request.getParameter("cid");
			Integer id = Integer.parseInt(cid);
			Integer t = categoryService.delete(id);
			if (t > 0) {
				session.setAttribute("delete1", t);
				response.sendRedirect(request.getContextPath() + "/permission/category/all");
			}
		} else if ("/jsp/addcategory".equals(pathInfo)) {
			request.getRequestDispatcher("/WEB-INF/pages/category/addcategory.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
