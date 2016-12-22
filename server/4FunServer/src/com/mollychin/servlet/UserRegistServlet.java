package com.mollychin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mollychin.bean.UserBean;
import com.mollychin.dao.UserDao;

@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		UserBean userBean = null;
		userBean = userDao.findByName(userName);
		if (userName != null) {
			if (userName.equals(userBean.getUserName())) {
				String msg = "该用户名已被注册，您值得更优雅的用户名！";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("regist.jsp").forward(request, response);
			} else {
				userBean = new UserBean();
				userBean.setUserName(userName);
				userBean.setPassword(password);
				userDao.saveUser(userBean);
				String msg = "亲爱的" + userName + "，您已注册成功，欢迎登录！";
				request.setAttribute("msg", msg);
				// 下属于server端的跳转，可以获得request的属性
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} else {
			String msg = "请输入注册名哦";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
