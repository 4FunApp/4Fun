package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.ArOne;
import com.mollychin.dao.JDBCUtil;

public class ArOneSpider {
	public static void main(String[] args) {
		try {
			ArOneSpider arOneSpider = new ArOneSpider();
			final int PAGE = 2;
			String rootHtml = "http://www.wufafuwu.com/";
			String preHtml = "http://www.wufafuwu.com/a/ONE_wenzhang/list_1_";
			String suffHtml = ".html";
			for (int j = 1; j <= PAGE; j++) {
				String url = preHtml + j + suffHtml;
				Document document = Jsoup.connect(url).get();
				Elements select = document.select("li.photo-big > h3 > a");
				for (int i = 0; i < select.size(); i++) {
					String attr = select.get(i).attr("href");
					arOneSpider.parseDetails(rootHtml + attr);
					System.out.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseDetails(String html) throws IOException, SQLException, Exception {
		try {
			ArOne articleOne = new ArOne();
			Document document = Jsoup.connect(html).get();
			Elements authorWork = document.select("h1.ph");
			Elements article = document.select("div.articulo-contenido");
			Elements pubTime = document.select("p.xg1");
			// 因为作者作品 文章内容 出版时间访问次数都是匹配的 循环只需一次即可
			for (int i = 0; i < authorWork.size(); i++) {
				// 作者作品
				articleOne.setAuthorWork(authorWork.get(i).text());
				// 文章内容
				articleOne.setArticle(article.get(i).text());
				// 出版时间和访问次数
				articleOne.setPubTime(pubTime.get(i).text().replace("| 有 位朋友查看", ""));
				// 文章链接
				articleOne.setPicUrl(html);
				// 数据库操作
				Connection conn = JDBCUtil.getConnection();
				JDBCUtil.insertData(conn, insertSql(articleOne));
				JDBCUtil.closeConnection(conn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据已经添加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String insertSql(ArOne articleOne) {
		return "insert into articlefromone(authorWork,article,pubTime,picUrl) values('" + articleOne.getAuthorWork()
				+ "','" + articleOne.getArticle() + "','" + articleOne.getPubTime() + "','" + articleOne.getPicUrl()
				+ "');";
	}
}
