package com.mollychin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JDBCUtil {
	public final static String USER = "root";
	public final static String DATABASE_NAME = "4fun_v1.0";
	public final static String PASSWORD = "123456";
	public final static String ROOT_URL = "jdbc:mysql://localhost:3306/"
			+ DATABASE_NAME;

	// 连接
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(ROOT_URL, USER, PASSWORD);
		} catch (Exception e) {
			throw e;
		}
	}

	// 关闭
	public static void closeConnection(Connection conn) {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询
	public static ResultSet selectData(String sql) throws SQLException,
			Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			return prepareStatement.executeQuery();
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	// 插入数据
	public static void insertData(Connection conn, String sql)
			throws SQLException {
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate > 0) {
				System.out.println("添加数据成功哦");
			} else {
				System.out.println("添加数据失败哦");
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	// 插入用户注册信息
	public static String insertUserData(Connection conn, String sql)
			throws SQLException {
		String returnInfo = "注册成功";
		try {

			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			int executeUpdate = prepareStatement.executeUpdate();
			if (executeUpdate <= 0) {
				returnInfo = "注册失败";
			}
			return returnInfo;
		} catch (SQLException e) {
			throw e;
		}
	}

	public static String resultSet2Json(ResultSet rs) throws SQLException,
			JSONException {
		// json 对象
		JSONObject object = new JSONObject();
		boolean error = true;
		// json 数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		if (array.length() > 0) {
			error = !error;
		}
		object.put("error", error);
		object.put("result", array);

		return object.toString();
	}

	public static List<Object> resultSet2JSONArray(ResultSet rs)
			throws SQLException, JSONException {
		// json 数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}

		return array.toList();
	}

	public static String resultSet2Json(ResultSet rs, List<String> sqlList)
			throws Exception {
		// json 对象
		JSONObject object = new JSONObject();
		boolean error = true;
		// json 数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			int row = rs.getRow() - 1;
			ResultSet data = selectData(sqlList.get(row));
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				Object value = columnName.equals("actor") ? resultSet2JSONArray(data)
						: rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		if (array.length() > 0) {
			error = !error;
		}
		object.put("error", error);
		object.put("result", array);

		return object.toString();
	}
}
