
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

@WebServlet("/PoemServlet")
public class PoemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			// 在response之前要设置网页内容的字符编码
			response.setContentType("text/html;charset=utf-8");
			System.out.println(selectSql(num));
			response.getWriter().write(JDBCUtil.resultSet2Json(JDBCUtil.selectData(selectSql(num))));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String selectSql(int num) {
		return "select * from poemfromchinapoem limit " + num + ";";
	}

}
