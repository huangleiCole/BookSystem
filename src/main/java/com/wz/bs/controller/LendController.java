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
import com.wz.bs.entity.Lend;
import com.wz.bs.entity.LendStatus;
import com.wz.bs.entity.User;
import com.wz.bs.service.BookService;
import com.wz.bs.service.LendService;
import com.wz.bs.service.UserService;
import com.wz.bs.util.BeanFactory;

/**
 * Servlet implementation class LendController
 */
@WebServlet(name = "/LendController", urlPatterns = "/permission/lend/*")
public class LendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LendService lendService = (LendService) BeanFactory.getBean("lendService");
	BookService bookService = (BookService) BeanFactory.getBean("bookService");
	UserService userService = (UserService) BeanFactory.getBean("userService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LendController() {
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
			List<Lend> lends = lendService.selectAll();
			request.setAttribute("lends", lends);
			request.getRequestDispatcher("/WEB-INF/pages/lend/lendlist.jsp").forward(request, response);
		} else if ("/returnbook".equals(pathInfo)) {
			String sid = request.getParameter("lid");
			Integer id = Integer.parseInt(sid);
			Lend lend = lendService.selectOneById(id);
			lend.setReturndate(new Date());
			Book book = lend.getBook();
			book.setRestcount(lend.getBook().getRestcount() + lend.getCount());
			lendService.returnbook(id);
			bookService.update(book);
			response.sendRedirect(request.getContextPath() + "/permission/lend/all");
		} else if ("/readd".equals(pathInfo)) {
			String sid = request.getParameter("bid");
			Integer bid = Integer.parseInt(sid);
			Book book = bookService.selectOneById(bid);
			request.setAttribute("isbn", book.getIsbn());
			request.getRequestDispatcher("/WEB-INF/pages/lend/addlend.jsp").forward(request, response);
		} else if ("/ureadd".equals(pathInfo)) {
			String suid = request.getParameter("uid");
			Integer uid = Integer.parseInt(suid);
			User user = userService.selectOneById(uid);
			request.setAttribute("no", user.getNo());
			request.getRequestDispatcher("/WEB-INF/pages/lend/addlend.jsp").forward(request, response);
		} else if ("/realadd".equals(pathInfo)) {
			String no = request.getParameter("no");
			String isbn = request.getParameter("isbn");
			String scount = request.getParameter("count");
			Integer count = Integer.parseInt(scount);
			User user = userService.selectOneByNo(no);
			Book book = bookService.selectOneByIsbn(isbn);
			book.setRestcount(book.getRestcount() - count);
			Lend lend = new Lend(1, new Date(), null, count, LendStatus.已借出, user, book, admin);
			Integer t = lendService.add(lend);
			bookService.update(book);
			if (t > 0) {
				session.setAttribute("add1", t);
				response.sendRedirect(request.getContextPath() + "/permission/lend/all");
			}
		} else if ("/delete".equals(pathInfo)) {
			String sid = request.getParameter("lid");
			Integer id = Integer.parseInt(sid);
			Integer t = lendService.delete(id);
			if (t > 0) {
				session.setAttribute("delete1", t);
				response.sendRedirect(request.getContextPath() + "/permission/lend/all");
			}
		}else if("/show".equals(pathInfo)){
			String sid = request.getParameter("lid");
			Integer id = Integer.parseInt(sid);
			request.setAttribute("updatelend", lendService.selectOneById(id));
			request.getRequestDispatcher("/WEB-INF/pages/book/updatelend.jsp").forward(request, response);
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
