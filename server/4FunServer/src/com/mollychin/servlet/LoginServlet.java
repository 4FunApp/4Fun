package com.mollychin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mollychin.bean.User;
import com.mollychin.bean.UserLoginInfo;
import com.mollychin.bean.UserLoginInfo.Bean;
import com.mollychin.utils.ConstantsUtil;
import com.mollychin.utils.JDBCUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int STATE_CODE = ConstantsUtil.LOGIN_USER_ERROR_CODE;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserLoginInfo info = new UserLoginInfo();
		Bean bean = new Bean();
		List<Bean> list = new ArrayList<UserLoginInfo.Bean>();
		list.add(bean);
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if (!userName.isEmpty() && !password.isEmpty()) {
				boolean sameNameVerify = JDBCUtil.sameNameVerify(userName);
				if (!sameNameVerify) {
					// 用户名不存在
					STATE_CODE = userNameIsnotExist();
				} else {
					// 用户名存在，校验
					STATE_CODE = userNameIsExist(bean, userName, password);
				}
				// error 设置为 false
				info.setError(false);
			} else {
				// 密码账号为空
				info.setError(true);
			}
		} catch (Exception e) {
			STATE_CODE = ConstantsUtil.LOGIN_USER_ERROR_CODE;
			e.printStackTrace();
		}
		// 放入状态码
		bean.setCode(STATE_CODE);

		info.setResult(list);
		response.getWriter().write(JDBCUtil.bean2Json(info));
	}

	private int userNameIsExist(Bean bean, String userName, String password) {
		try {
			User user = JDBCUtil.userVerify(userName, password);
			if (password.equals(user.getPassword())) {
				STATE_CODE = ConstantsUtil.LOGIN_SUCCESS_CODE;
				bean.setEmail(user.getEmail());
				bean.setPassword(user.getPassword());
				bean.setSex(user.getSex());
				bean.setUserName(user.getUserName());
				System.out.println(STATE_CODE);
			} else {
				STATE_CODE = ConstantsUtil.LOGIN_PASSWORD_ERROR_CODE;
			}
		} catch (Exception e) {
			STATE_CODE = ConstantsUtil.LOGIN_PASSWORD_ERROR_CODE;
			e.printStackTrace();
		}

		return STATE_CODE;
	}

	private int userNameIsnotExist() {
		return STATE_CODE = ConstantsUtil.LOGIN_USER_ERROR_CODE;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
