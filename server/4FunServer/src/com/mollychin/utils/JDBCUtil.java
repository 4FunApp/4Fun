package com.mollychin.utils;

import static com.mollychin.utils.ConstantsUtil.PASSWORD;
import static com.mollychin.utils.ConstantsUtil.ROOT_URL;
import static com.mollychin.utils.ConstantsUtil.USER;

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

import com.google.gson.Gson;
import com.mollychin.bean.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class JDBCUtil {

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
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("数据已经添加");
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

	public static void addUser(User user) throws Exception {
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into users (userName,password,sex,email) values(?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getSex());
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User userVerify(String userName, String password)
			throws Exception {
		User user = new User();
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where userName=? and password=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setSex(rs.getInt("sex"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}

		return user;
	}

	/**
	 * 验证数据库里是否有相同的用户名
	 * 
	 * @param userName
	 * @return return true if has, or return false
	 * @throws Exception
	 */
	public static boolean sameNameVerify(String userName) throws Exception {
		String name = null;
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where userName=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("userName");
				if (name.equals(userName)) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}

		return false;
	}

	/**
	 * 验证数据库里是否有相同的邮箱
	 * 
	 * @param email
	 * @return return true if has, or return false
	 * @throws Exception
	 */

	public static boolean sameEmailVerify(String email) throws Exception {
		String emailVerify = null;
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from users where email=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				emailVerify = rs.getString("email");
				if (emailVerify.equals(email)) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}

		return false;
	}

	public static String bean2Json(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);

		return json;
	}

	public static Object json2Bean(String json, Class<?> classOfT) {
		Gson gson = new Gson();
		Object bean = gson.fromJson(json, classOfT);

		return bean;
	}
}
