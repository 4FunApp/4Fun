package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.DailyPoem;
import com.mollychin.utils.JDBCUtil;

@Deprecated
public class PoemDailySpider {
	public static void main(String[] args) {
		PoemDailySpider poemSpider = new PoemDailySpider();
		String rootHtml = "http://www.zgshige.com/";
		String preHtml = "http://www.zgshige.com/myjx/index_";
		String suffHtml = ".shtml";

		// 这个网站不存在http://www.zgshige.com/myjx/index_1.shtml这个链接
		// for循环给出2--10页的诗歌
		for (int j = 2; j <= 10; j++) {
			try {
				String url = preHtml + j + suffHtml;
				Document document = Jsoup.connect(url).get();
				Elements select = document.select("a.h4.bold");// 不能写 a h4 bold
				for (int i = 0; i < select.size(); i++) {
					String attr = select.get(i).attr("href");
					poemSpider.parseWeb(rootHtml + attr);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void parseWeb(String url) {
		try {
			DailyPoem poemChinaDaily = new DailyPoem();
			Document document = Jsoup.connect(url).get();
			Elements poemTitle1 = document
					.select("div.text-center.b-b.b-2x.b-lt");
			Elements poemAuthor1 = document.select("div.col-xs-12 > span");
			Elements pubTime1 = document.select("span.p-l-sm.p-r-sm");
			Elements poemContent1 = document.select("div.m-lg.font14");
			// 因为作者作品 文章内容 出版时间访问次数都是匹配的 循环只需一次即可
			for (int i = 0; i < poemTitle1.size(); i++) {
				// 诗歌标题 诗歌作者诗歌发表时间 诗歌内容
				poemChinaDaily.setPoemTitle(poemTitle1.get(i).text());
				poemChinaDaily.setPoemAuthor(poemAuthor1.get(i).text());
				poemChinaDaily.setPubTime(pubTime1.get(i).text());
				poemChinaDaily.setPoemContent(poemContent1.get(i).text());
				poemChinaDaily.setUrl(url);
				// 数据库操作
				Connection conn = JDBCUtil.getConnection();
				JDBCUtil.insertData(conn, getSql(poemChinaDaily));
				JDBCUtil.closeConnection(conn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSql(DailyPoem poemChinaDaily) {
		return "insert into poemfromchinapoem(poemTitle,poemAuthor,pubTime,poemContent,url) values('"
				+ poemChinaDaily.getPoemTitle().replace("'", "\\\'")
				+ "','"
				+ poemChinaDaily.getPoemAuthor().replace("'", "\\\'")
				+ "','"
				+ poemChinaDaily.getPubTime().replace("'", "\\\'")
				+ "','"
				+ poemChinaDaily.getPoemContent().replace("'", "\\\'")
				+ "','"
				+ poemChinaDaily.getUrl().replace("'", "\\\'") + "');";
	}
}
