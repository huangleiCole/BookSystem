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
import com.wz.bs.entity.Status;
import com.wz.bs.entity.User;
import com.wz.bs.service.AdminService;
import com.wz.bs.service.UserService;
import com.wz.bs.util.BeanFactory;
import com.wz.bs.util.DateUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "/UserController", urlPatterns = "/permission/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = (UserService) BeanFactory.getBean("userService");
	AdminService adminService = (AdminService) BeanFactory.getBean("adminService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
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
			List<User> users = userService.selectAll();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/pages/user/userlist.jsp").forward(request, response);
			session.removeAttribute("update2");
			session.removeAttribute("add1");
			session.removeAttribute("delete1");
			session.removeAttribute("update1");
		} else if ("/select".equals(pathInfo)) {
			String no = request.getParameter("no");
			List<User> users = userService.selectByNo(no);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/pages/user/userlist.jsp").forward(request, response);
		} else if ("/selectbycondition".equals(pathInfo)) {
			String start = request.getParameter("startdate");
			start = (start == "") ? null : start;
			String end = request.getParameter("enddate");
			end = (end == "") ? null : end;
			System.out.println(end);
			String phone = request.getParameter("phone");
			phone = (phone == "") ? null : phone;
			String status = request.getParameter("status");
			status = (status == "") ? null : status;
			Date startdate = null;
			Date enddate = null;
			if (start != null || end != null) {
				startdate = DateUtil.parse(start, "yyyy-MM-dd");
				enddate = DateUtil.parse(end, "yyyy-MM-dd");
			}
			Status statu = Status.valueOf(status);
			status = (status == "") ? null : status;
			String realname = request.getParameter("realname");
			realname = (realname == "") ? null : realname;
			String adminname = request.getParameter("adminname");
			adminname = (adminname == "") ? null : adminname;
			Admin admin2 = null;
			if (adminname != null) {
				admin2 = adminService.selectOneByName(adminname);
			}

			List<User> users = userService.selectByCondition(realname, admin2, startdate, enddate, phone,
					statu);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/pages/user/userlist.jsp").forward(request, response);
		} else if ("/add".equals(pathInfo)) {
			String no = (String) session.getAttribute("no");
			String realname = request.getParameter("realname");
			String phone = request.getParameter("phone");
			User user = new User(1, no, new Date(), realname, phone, Status.正常, admin);
			Integer t = userService.add(user);
			if (t > 0) {
				session.setAttribute("add1", t);
				response.sendRedirect(request.getContextPath() + "/permission/user/all");
			}

		} else if ("/show".equals(pathInfo)) {
			String uid = request.getParameter("uid");
			Integer id = Integer.parseInt(uid);
			User user = userService.selectOneById(id);
			request.setAttribute("updateuser", user);
			request.getRequestDispatcher("/WEB-INF/pages/user/updateuser.jsp").forward(request, response);
		} else if ("/update".equals(pathInfo)) {
			String uid = request.getParameter("uid");
			Integer id = Integer.parseInt(uid);
			User user = userService.selectOneById(id);
			String realname = request.getParameter("realname");
			String phone = request.getParameter("phone");
			user.setRealname(realname);
			user.setPhone(phone);
			Integer t = userService.update(user);
			if (t > 0) {
				session.setAttribute("update1", t);
				response.sendRedirect(request.getContextPath() + "/permission/user/all");
			}
		} else if ("/disable".equals(pathInfo)) {
			String uid = request.getParameter("uid");
			Integer id = Integer.parseInt(uid);
			userService.disable(id);
			response.sendRedirect(request.getContextPath() + "/permission/user/all");
		} else if ("/able".equals(pathInfo)) {
			String uid = request.getParameter("uid");
			Integer id = Integer.parseInt(uid);
			userService.able(id);
			response.sendRedirect(request.getContextPath() + "/permission/user/all");
		} else if ("/delete".equals(pathInfo)) {
			String uid = request.getParameter("uid");
			Integer id = Integer.parseInt(uid);
			Integer t = userService.delete(id);
			if (t > 0) {
				session.setAttribute("delete1", t);
				response.sendRedirect(request.getContextPath() + "/permission/user/all");
			}
		} else if ("/jsp/adduser".equals(pathInfo)) {
			Date date = new Date();
			long time = date.getTime();
			String dd = String.valueOf(time);
			String ddd = dd.substring(3, 13);
			String no = "WZ" + ddd;
			session.setAttribute("no", no);
			request.getRequestDispatcher("/WEB-INF/pages/user/adduser.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
