package com.mollychin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mollychin.bean.UserBean;

public class UserDao {
	public final static String USER = "root";
	public final static String DATABASE_NAME = "4fun";
	public final static String PASSWORD = "0306";
	public final static String ROOT_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
	private static Connection CONNECTION = null;
	// private static PreparedStatement PS = null;

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
	public static void closeConnection() {
		if (CONNECTION != null)
			try {
				CONNECTION.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	public void saveUser(UserBean userBean) {
		try {
			Connection connection = UserDao.getConnection();
			String insertSql = "insert into userinfo values(?,?)";
			PreparedStatement ps = connection.prepareStatement(insertSql);
			ps.setString(1, userBean.getUserName());
			ps.setString(2, userBean.getPassword());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public UserBean findByName(String userName) {
		UserBean userBean = new UserBean();
		try {
			Connection connection = UserDao.getConnection();
			String selectSql = "select * from userinfo where userName='" + userName + ";' + ";
			PreparedStatement ps = connection.prepareStatement(selectSql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userBean.setUserName(rs.getString(1));
				userBean.setPassword(rs.getString(2));// 注意不是0和1
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			{
				closeConnection();
			}
		}
		return userBean;
	}

	public void deleteUser(String userName) {
		try {
			Connection connection = UserDao.getConnection();
			String deleteSql = "delete from user where name='" + userName + "'";
			PreparedStatement ps = connection.prepareStatement(deleteSql);
			if (ps.executeUpdate() > 0) {
				String msg = "删除用户" + userName + "成功哦";
			} else {
				String msg = "用户" + userName + "不存在，删除失败哦";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}
}
