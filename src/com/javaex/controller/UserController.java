package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("joinForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
		}
		else if("join".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo uVo = new UserVo(id, password, name, gender);
			UserDao uDao = new UserDao();
			int confirm = uDao.userInsert(uVo);
			
			if(confirm > 0) {
				WebUtil.forward(request, response, "WEB-INF/views/user/joinOk.jsp");
			} else {
				WebUtil.redirect(request, response, "./user?action=joinForm");
			}
		}
		else if("loginForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
		}
		else if("login".equals(action)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDao uDao = new UserDao();
			UserVo uVo = new UserVo();
			uVo.setId(id);
			uVo.setPassword(password);
			
			UserVo authUser = uDao.getUser(uVo);
			
			if(authUser == null) {
				WebUtil.redirect(request, response, "./user?action=loginForm");
			} else { //왠지는 모르겠지만 authUser != null하면은 안통함
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authUser);
				
				WebUtil.redirect(request, response, "./main?");
			}
		}
		else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.setAttribute("authUser", null);
			session.invalidate();
			
			WebUtil.redirect(request, response, "./main?");
		}
		else if("modifyForm".equals(action)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		}
		else if("modify".equals(action)) {
			
		}
		else {
			System.out.println("unknown action");
			WebUtil.redirect(request, response, "./main?");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
