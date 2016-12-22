package com.mollychin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;

@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String currentDate = SystemUtil.getDate();
			String givenDate = request.getParameter("date");
			String date = givenDate == null ? currentDate : givenDate;
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectByDate(date))));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String selectByDate(String date) {
		return "select * from musicinfo where date='" + date + "'" + "order by musicId;";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}