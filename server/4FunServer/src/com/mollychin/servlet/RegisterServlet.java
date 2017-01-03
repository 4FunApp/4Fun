package com.mollychin.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int STATE_CODE = ConstantsUtil.REGISTER_USER_REPEAT_ERROR_CODE;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 传入 json
		response.setContentType("text/json;charset=utf-8");
		// 读取请求内容
		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		// 转换
		User user = (User) JDBCUtil.json2Bean(sb.toString(), User.class);
		String userName = user.getUserName();
		String password = user.getPassword();
		int sex = user.getSex();
		String email = user.getEmail();
		User dataBaseUser = new User();
		UserLoginInfo info = new UserLoginInfo();
		Bean bean = new Bean();
		List<Bean> list = new ArrayList<UserLoginInfo.Bean>();
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setSex(sex);
		bean.setUserName(userName);
		list.add(bean);
		try {
			if (!userName.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
				boolean sameNameVerify = JDBCUtil.sameNameVerify(userName);
				boolean sameEmailVerify = JDBCUtil.sameEmailVerify(email);
				if (!sameNameVerify && !sameEmailVerify) {
					// 用户名不存在且邮箱都不存在，注册
					dataBaseUser.setEmail(email);
					dataBaseUser.setPassword(password);
					dataBaseUser.setSex((sex));
					dataBaseUser.setUserName(userName);
					STATE_CODE = userNameIsnotExist(dataBaseUser);
				} else {
					// 用户名存在，无法注册
					STATE_CODE = userNameIsExist();
				}
				info.setError(false);
			} else {
				info.setError(true);
			}
		} catch (Exception e) {
			STATE_CODE = ConstantsUtil.REGISTER_USER_REPEAT_ERROR_CODE;
			e.printStackTrace();
		}
		bean.setCode(STATE_CODE);
		info.setResult((list));
		response.getWriter().write(JDBCUtil.bean2Json(info));
	}

	private int userNameIsExist() {
		return STATE_CODE = ConstantsUtil.REGISTER_USER_REPEAT_ERROR_CODE;
	}

	private int userNameIsnotExist(User user) {
		try {
			JDBCUtil.addUser(user);
			STATE_CODE = ConstantsUtil.REGISTER_SUCCESS_CODE;
		} catch (Exception e) {
			STATE_CODE = ConstantsUtil.REGISTER_USER_REPEAT_ERROR_CODE;
			e.printStackTrace();
		}

		return STATE_CODE;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
