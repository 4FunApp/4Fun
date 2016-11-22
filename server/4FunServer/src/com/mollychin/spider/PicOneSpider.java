package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.PicOne;
import com.mollychin.dao.JDBCUtil;

public class PicOneSpider {
	public static void main(String[] args) {
		PicOneSpider picOneSpider = new PicOneSpider();
		String rootHtml = "http://www.wufafuwu.com/";
		String preHtml = "http://www.wufafuwu.com/a/ONE_tupian/list_11_";
		String suffHtml = ".html";
		for (int i = 1; i <= 10; i++) {
			String url = preHtml + i + suffHtml;
			try {
				Document document = Jsoup.connect(url).get();
				Elements select = document.select("li.photo-big h3 > a");
				for (int j = 0; j < select.size(); j++) {
					String attr = select.get(j).attr("href");
					picOneSpider.parse(rootHtml + attr);
					System.out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void parse(String html) {
		try {
			PicOne pictureOne = new PicOne();
			Document document = Jsoup.connect(html).get();
			// 作者作品
			Elements authorWork1 = document.select("div.one-imagen-leyenda");
			// 图片描述
			Elements picDescription1 = document.select("div.one-cita");
			// 图片日期
			Elements picDate1 = document.select("div.one-pubdate");
			// 图片链接
			Elements picUrl1 = document.select("div.one-imagen > img ");
			// 发布时间和访问量
			Elements pubTime = document.select("p.xg1");
			for (int i = 0; i < authorWork1.size(); i++) {
				pictureOne.setAuthorWork(authorWork1.get(i).text());
				pictureOne.setPicDescription(picDescription1.get(i).text());
				pictureOne.setPicDate(picDate1.get(i).text());
				pictureOne.setPicUrl(picUrl1.get(i).attr("src"));
				pictureOne.setPubTime(pubTime.get(i).text().replace("| 有 位朋友查看", ""));
				// System.out.println(pubTimeVisitNumText);
				// 数据库操作
				Connection conn = JDBCUtil.getConnection();
				JDBCUtil.insertData(conn, getSql(pictureOne));
				JDBCUtil.closeConnection(conn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSql(PicOne pictureOne) {
		return "insert into picturefromone(authorWork,picDescription,picDate,picUrl,pubTime) values('"
				+ pictureOne.getAuthorWork() + "','" + pictureOne.getPicDescription() + "','" + pictureOne.getPicDate()
				+ "','" + pictureOne.getPicUrl() + "','" + pictureOne.getPubTime() + "');";
	}
}
