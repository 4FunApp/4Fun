package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.PicOne;
import com.mollychin.utils.DownloadUtil;
import com.mollychin.utils.JDBCUtil;

@Deprecated
public class PicOneSpider {
	final static String rootHtml = "http://www.wufafuwu.com/";
	final static String preHtml = "http://www.wufafuwu.com/a/ONE_tupian/list_11_";

	public static void main(String[] args) {
		PicOneSpider picOneSpider = new PicOneSpider();
		String suffHtml = ".html";
		for (int i = 1; i <= 2; i++) {
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
			Elements authorWork = document.select("div.one-imagen-leyenda");
			// 图片描述
			Elements picDescription = document.select("div.one-cita");
			// 图片日期
			Elements picDate = document.select("div.one-pubdate");
			// 图片链接
			Elements picUrl = document.select("div.one-imagen > img ");
			// 发布时间和访问量
			Elements pubTime = document.select("p.xg1");
			for (int i = 0; i < authorWork.size(); i++) {
				pictureOne.setAuthorWork(authorWork.get(i).text());
				pictureOne.setPicDescription(picDescription.get(i).text());
				pictureOne.setPicDate(picDate.get(i).text());
				pictureOne.setPicUrl(DownloadUtil.downloadPicture(rootHtml
						+ picUrl.get(i).attr("src"), picUrl.get(i).attr("src")
						.substring(25, picUrl.get(i).attr("src").length())));
				pictureOne.setPubTime(pubTime.get(i).text()
						.replace("| 有 位朋友查看", ""));
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
				+ pictureOne.getAuthorWork().replace("'", "\\\'")
				+ "','"
				+ pictureOne.getPicDescription().replace("'", "\\\'")
				+ "','"
				+ pictureOne.getPicDate().replace("'", "\\\'")
				+ "','"
				+ pictureOne.getPicUrl().replace("'", "\\\'")
				+ "','"
				+ pictureOne.getPubTime().replace("'", "\\\'")
						.replace("发布时间：", "").substring(0, 10) + "');";
	}
}
