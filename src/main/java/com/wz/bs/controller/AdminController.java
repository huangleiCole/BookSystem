package com.wz.bs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wz.bs.entity.Admin;
import com.wz.bs.entity.Flag;
import com.wz.bs.entity.Status;
import com.wz.bs.service.AdminService;
import com.wz.bs.util.BeanFactory;
import com.wz.bs.util.DateUtil;
import com.wz.bs.util.MD5Code;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(name = "/AdminController", urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MD5Code MD5;
	AdminService adminService = (AdminService) BeanFactory.getBean("adminService");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		MD5 = new MD5Code();
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
		Admin admin = new Admin();
		if ("/start".equals(pathInfo)) {
			admin = adminService.hasSuper();
			boolean b = false;
			if (admin != null) {
				b = true;
			}
			request.setAttribute("hasSuper", b);
			session.removeAttribute("add0");
			request.getRequestDispatcher("/WEB-INF/pages/admin/login.jsp").forward(request, response);
			
		} else if ("/login".equals(pathInfo)) {
			String name = request.getParameter("name").trim();
			String password = request.getParameter("password").trim();
			admin = adminService.selectOneByName(name);
			if (admin.getStatus() != Status.禁用 && MD5.getMD5ofStr(password).equals(admin.getPassword())) {
				session.setAttribute("admin", admin);
				if (admin.getFlag() == Flag.超级管理员) {
					request.getRequestDispatcher("/admin/all").forward(request, response);
				} else {
					request.getRequestDispatcher("/admin/isdefault").forward(request, response);
				}
				admin.setLastlogin(new Date());
				adminService.update(admin);

			} else {
				session.setAttribute("admin", "0");
				request.getRequestDispatcher("/WEB-INF/pages/admin/login.jsp").forward(request, response);
			}
		} else if ("/all".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				List<Admin> admins = adminService.selectAll();
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("/WEB-INF/pages/admin/adminlist.jsp").forward(request, response);
				session.removeAttribute("reset1");
				session.removeAttribute("update1");
				session.removeAttribute("delete1");
				session.removeAttribute("add1");
				session.removeAttribute("add0");
			}

		} else if ("/logout".equals(pathInfo)) {
			session.removeAttribute("admin");
			response.sendRedirect(request.getContextPath() + "/admin/start");
		} else if ("/reset".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				Integer t = adminService.reset(id);
				if (t > 0) {
					session.setAttribute("reset1", t);
					response.sendRedirect(request.getContextPath() + "/admin/all");
				}
			}
		} else if ("/updatepassword".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.超级管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String password = request.getParameter("newpassword");
				admin.setPassword(MD5.getMD5ofStr(password));
				Integer t = adminService.update(admin);
				if (t > 0) {
					session.setAttribute("update2", t);
					response.sendRedirect(request.getContextPath() + "/permission/user/all");
				}
			}
		} else if ("/update".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				String realname = request.getParameter("realname");
				String phone = request.getParameter("phone");
				admin = adminService.selectOneById(id);
				admin.setPhone(phone);
				admin.setRealname(realname);
				Integer t = adminService.update(admin);
				if (t > 0) {
					session.setAttribute("update1", t);
					response.sendRedirect(request.getContextPath() + "/admin/all");
				}
			}
		} else if ("/show".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				admin = adminService.selectOneById(id);
				request.setAttribute("updateadmin", admin);
				request.getRequestDispatcher("/WEB-INF/pages/admin/updateadmin.jsp").forward(request, response);
			}

		} else if ("/select".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String name = request.getParameter("name");
				List<Admin> admins = adminService.selectByMhName(name);
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("/WEB-INF/pages/admin/adminlist.jsp").forward(request, response);
			}

		} else if ("/selectbycondition".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
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
				List<Admin> admins = adminService.selectByCondition(startdate, enddate, phone, statu);
				request.setAttribute("admins", admins);
				request.getRequestDispatcher("/WEB-INF/pages/admin/adminlist.jsp").forward(request, response);
			}
		} else if ("/delete".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				Integer t = adminService.delete(id);
				if (t > 0) {
					session.setAttribute("delete1", t);
					response.sendRedirect(request.getContextPath() + "/admin/all");
				}

			}

		} else if ("/add".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String phone = request.getParameter("phone");
				String realname = request.getParameter("realname");
				admin = new Admin(1, name, MD5.getMD5ofStr(password), new Date(), null, realname, phone,
						Flag.普通管理员, Status.正常);
				Integer t = adminService.add(admin);
				if (t > 0) {
					session.setAttribute("add1", t);
					response.sendRedirect(request.getContextPath() + "/admin/all");
				}
			}

		} else if ("/checklogin".equals(pathInfo)) {
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			String name = request.getParameter("name");
			if (adminService.selectOneByName(name) != null) {
				out.print("1");// 用户名存在

			} else {
				out.print("0");
			}
			out.flush();
			out.close();
		} else if ("/addsuper".equals(pathInfo)) {
			String name = request.getParameter("newname");
			String password = request.getParameter("newpassword");
			String phone = request.getParameter("phone");
			String realname = request.getParameter("realname");
			admin = new Admin(1, name, MD5.getMD5ofStr(password), new Date(), new Date(), realname, phone, Flag.超级管理员,
					Status.正常);
			Integer t = adminService.add(admin);
			if (t > 0) {
				session.setAttribute("admin", admin);
				session.setAttribute("add0", t);
				response.sendRedirect(request.getContextPath() + "/admin/all");
			}

		} else if ("/able".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				adminService.able(id);
				response.sendRedirect(request.getContextPath() + "/admin/all");
			}

		} else if ("/disable".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				String sid = request.getParameter("aid");
				Integer id = Integer.parseInt(sid);
				adminService.disable(id);
				response.sendRedirect(request.getContextPath() + "/admin/all");
			}

		} else if ("/isdefault".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				Admin admin2 = adminService.isDefault(admin.getName());
				if (admin2 != null) {
					session.setAttribute("isDefault", true);
				} else {
					session.setAttribute("isDefault", false);
				}
				response.sendRedirect(request.getContextPath() + "/permission/user/all");
			}
		} else if ("/jsp/addadmin".equals(pathInfo)) {
			admin = (Admin) session.getAttribute("admin");
			if (admin == null || admin.getFlag() == Flag.普通管理员) {
				response.sendRedirect(request.getContextPath() + "/admin/start");
			} else {
				request.getRequestDispatcher("/WEB-INF/pages/admin/addadmin.jsp").forward(request, response);
			}

		} 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
