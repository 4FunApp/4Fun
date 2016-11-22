package com.mollychin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.mollychin.dao.JDBCUtil;

@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int limit = 6;
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			String date = request.getParameter("date");
			String limitString = request.getParameter("limit");
			limit = limitString == null ? 6 : Integer.parseInt(limitString);
			// 在response之前要设置网页内容的编码格式
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectSql(date, limit))));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String selectSql(String date, int limit) {
		return "select * from musicinfo where date='" + date + "'" + " limit " + limit + ";";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}