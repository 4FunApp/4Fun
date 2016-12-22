package com.mollychin.spider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mollychin.bean.PicZhihu;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;
import com.mysql.jdbc.Connection;

public class PicZhihuSpider {
	public static void main(String[] args) {
		try {
			String url = "http://news-at.zhihu.com/api/4/start-image/1080*1776";
			PicZhihuSpider picZhihu = new PicZhihuSpider();
			PicZhihu zhihu = SystemUtil.jsonParser(url, PicZhihu.class);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
			String time = format.format(date);
			zhihu.setTime(time);

			// 数据库操作
			Connection conn = (Connection) JDBCUtil.getConnection();
			JDBCUtil.insertData(conn, picZhihu.insertSql(zhihu));
			JDBCUtil.closeConnection(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String insertSql(PicZhihu zhihu) {
		return "insert into picturezhihu (text, time, img) values('"
				+ zhihu.getText().replace("'", "\\\'") + "','"
				+ zhihu.getTime().replace("'", "\\\'") + "','"
				+ zhihu.getImg().replace("'", "\\\'") + "');";
	}
}
