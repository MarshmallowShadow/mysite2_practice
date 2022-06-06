package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gbc")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("addList".equals(action)) {
			GuestBookDao gDao = new GuestBookDao();
			List<GuestVo> gList = gDao.getList();
			request.setAttribute("gList", gList);
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		}
		else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookDao gDao = new GuestBookDao();
			GuestVo gVo = new GuestVo(name, password, content);
			gDao.insert(gVo);
			
			WebUtil.redirect(request, response, "./gbc?action=addList");
		}
		else if("deleteForm".equals(action)) {
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
		}
		else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestVo gVo = new GuestVo();
			gVo.setNo(no);
			gVo.setPassword(password);
			
			GuestBookDao gDao = new GuestBookDao();
			int confirm = gDao.delete(gVo);
			
			if(confirm > 0) {
				WebUtil.redirect(request, response, "./gbc?action=addList");
			} else {
				WebUtil.redirect(request, response, "./gbc?action=deleteForm&no=" + no);
			}
		}
		else {
			System.out.println("unknown action");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
