
package com.wz.bs.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Book;
import com.wz.bs.entity.BookStatus;
import com.wz.bs.entity.Category;
import com.wz.bs.service.AdminService;
import com.wz.bs.service.BookService;
import com.wz.bs.service.CategoryService;
import com.wz.bs.util.BeanFactory;

/**
 * Servlet implementation class BookController
 */
@WebServlet(name = "/BookController", urlPatterns = "/permission/book/*")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService = (BookService) BeanFactory.getBean("bookService");
	AdminService adminService = (AdminService) BeanFactory.getBean("adminService");
	CategoryService categoryService = (CategoryService) BeanFactory.getBean("categoryService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
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
			List<Book> books = bookService.selectAll();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/pages/book/booklist.jsp").forward(request, response);
			session.removeAttribute("add1");
			session.removeAttribute("delete1");
			session.removeAttribute("update1");
			session.removeAttribute("down1");
		} else if ("/jsp/addbook".equals(pathInfo)) {
			request.setAttribute("categories", categoryService.selectAll());
			request.getRequestDispatcher("/WEB-INF/pages/book/addbook.jsp").forward(request, response);
		} else if ("/add".equals(pathInfo)) {
			String isbn = request.getParameter("isbn");
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String publishhouse = request.getParameter("publishhouse");
			String scount = request.getParameter("count");
			Integer count = Integer.parseInt(scount);
			String scategory = request.getParameter("category");
			Integer categoryid = Integer.parseInt(scategory);
			Category category = categoryService.selectOneById(categoryid);
			Book book = new Book(1, isbn, name, author, publishhouse, count, count, new Date(), BookStatus.正常, category,
					admin);
			Integer t = bookService.add(book);
			if (t > 0) {
				session.setAttribute("add1", t);
				response.sendRedirect(request.getContextPath() + "/permission/book/all");
			}
		} else if ("/delete".equals(pathInfo)) {
			String sid = request.getParameter("bid");
			Integer id = Integer.parseInt(sid);
			Integer t = bookService.delete(id);
			if (t > 0) {
				session.setAttribute("delete1", t);
				response.sendRedirect(request.getContextPath() + "/permission/book/all");
			}
		} else if ("/show".equals(pathInfo)) {
			String sid = request.getParameter("bid");
			Integer id = Integer.parseInt(sid);
			Book book = bookService.selectOneById(id);
			session.setAttribute("updatebook", book);
			request.setAttribute("categories", categoryService.selectAll());
			request.getRequestDispatcher("/WEB-INF/pages/book/updatebook.jsp").forward(request, response);
		} else if ("/update".equals(pathInfo)) {
			String sid = request.getParameter("bid");
			Integer id = Integer.parseInt(sid);
			String isbn = request.getParameter("isbn");
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String publishhouse = request.getParameter("publishhouse");
			String scount = request.getParameter("count");
			Integer count = Integer.parseInt(scount);
			Book old = (Book) session.getAttribute("updatebook");
			Integer oldcount = old.getCount();
			Integer mocount = 0;
			mocount = count - oldcount;
			String scategory = request.getParameter("category");
			Integer categoryid = Integer.parseInt(scategory);
			Category category = categoryService.selectOneById(categoryid);
			Book book = new Book(id, isbn, name, author, publishhouse, count, old.getRestcount() + mocount, null, null,
					category, admin);
			Integer t = bookService.update(book);
			if (t > 0) {
				session.setAttribute("update1", t);
				response.sendRedirect(request.getContextPath() + "/permission/book/all");
			}

		} else if ("/down".equals(pathInfo)) {
			String sid = request.getParameter("bid");
			Integer id = Integer.parseInt(sid);
			Integer t = bookService.down(id);
			if (t > 0) {
				session.setAttribute("down1", t);
				response.sendRedirect(request.getContextPath() + "/permission/book/all");
			}
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
