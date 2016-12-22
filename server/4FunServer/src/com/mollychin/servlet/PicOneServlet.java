package com.mollychin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;

@WebServlet("/PicOneServlet")
public class PicOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String currentDate = SystemUtil.getDate();
		String givenDate = request.getParameter("date");
		String date = givenDate == null ? currentDate : givenDate;
		try {
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectByDate(date))));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String selectByDate(String date) {
		return "select * from picturefromone where pubTime='" + date + "'order by picId;";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String selectSql(String date) {
		return "select * from picturefromone where pubTime ='" + date + "';";
	}

}
