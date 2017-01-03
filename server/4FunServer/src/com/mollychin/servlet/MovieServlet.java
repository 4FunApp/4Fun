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

import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;

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
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String givenDate = request.getParameter("date");
			String currentDate = SystemUtil.getDate();
			String date = givenDate == null ? currentDate : givenDate;
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectByDate(date)), sqlList));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String selectByDate(String date) throws SQLException, Exception {
		ResultSet rs = JDBCUtil
				.selectData("select movieName from movieinfo where date='" + date + "'order by movieId;");
		while (rs.next()) {
			String sql = selectActorSql(rs.getString("movieName"));
			sqlList.add(sql);
		}
		return "select * from movieinfo where date='" + date + "'order by movieId;";
	}

	private String selectActorSql(String movieName) {
		return "select * from movieactorinfo where movieName='" + movieName + "';";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
