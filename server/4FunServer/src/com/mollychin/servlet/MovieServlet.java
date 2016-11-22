package com.mollychin.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.mollychin.dao.JDBCUtil;

/**
 * Created by mollychin on 2016/11/18.
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> sqlList = new ArrayList<String>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String date = request.getParameter("date");
			// 在response之前要设置网页内容的编码格式
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectMovieSql(date)), sqlList));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String selectMovieSql(String date) throws SQLException, Exception {
		System.out.println("select movieName from movieinfo where date='" + date + "';");
		ResultSet rs = JDBCUtil.selectData("select movieName from movieinfo where date='" + date + "';");
		while (rs.next()) {
			String sql = selectActorSql(rs.getString("movieName"));
			sqlList.add(sql);
		}
		return "select * from movieinfo where date='" + date + "';";
	}

	private String selectActorSql(String movieName) {
		return "select * from actorinfo where movieName='" + movieName + "';";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
