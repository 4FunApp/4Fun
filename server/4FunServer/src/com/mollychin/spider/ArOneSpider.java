package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.ArOne;
import com.mollychin.utils.ConstantsUtil;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.JsoupUtil;

public class ArOneSpider {
	public static final int PAGE = 1;

	public void arOneSpider() {
		try {
			ArOneSpider arOneSpider = new ArOneSpider();
			for (int j = 1; j <= PAGE; j++) {
				String url = ConstantsUtil.AR_ONE_PRE_URL + j
						+ ConstantsUtil.AR_ONE_SUFF_URL;
				Document document = JsoupUtil.connect(url);
				Elements select = document.select("li.photo-big > h3 > a");
				for (int i = 0; i < select.size(); i++) {
					String attr = select.get(i).attr("href");
					arOneSpider.parseDetails(ConstantsUtil.AR_ONE_ROOT_URL
							+ attr);
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

	public void parseDetails(String html) throws IOException, SQLException,
			Exception {
		try {
			ArOne articleOne = new ArOne();
			Document document = JsoupUtil.connect(html);
			Elements authorWork = document.select("h1.ph");
			Elements article = document.select("div.articulo-contenido");
			Elements pubTime = document.select("p.xg1");
			// 因为作者作品 文章内容 出版时间访问次数都是匹配的 循环只需一次即可
			for (int i = 0; i < authorWork.size(); i++) {
				String text = authorWork.get(i).text();
				String[] textSplit = text.split(" 作者/");
				// 作者作品
				articleOne.setArticleTitle(textSplit[0]);
				articleOne.setArticleAuthor(textSplit[1]);
				// 出版时间和访问次数
				articleOne.setPubTime(pubTime.get(i).text()
						.replace("| 有 位朋友查看", "").replace("发布时间：", "")
						.substring(0, 10));
				// 文章链接
				articleOne.setArticleUrl(html);
				// 文章内容
				articleOne
						.setArticleContent(article
								.get(i)
								.html()
								.replace("&nbsp;", "")
								.replace(
										"<br style=\"box-sizing: border-box;\">",
										"\n").replace("\n\n", "\n"));
				// 数据库操作
				Connection conn = JDBCUtil.getConnection();
				JDBCUtil.insertData(conn, insertSql(articleOne));
				JDBCUtil.closeConnection(conn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("数据已经添加，不能重复添加哦");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String insertSql(ArOne articleOne) {
		return "insert into articlefromone(articleAuthor,articleTitle,articleContent,pubTime, articleUrl) values('"
				+ articleOne.getArticleAuthor().replace("'", "\\'")
				+ "','"
				+ articleOne.getArticleTitle().replace("'", "\\'")
				+ "','"
				+ articleOne.getArticleContent().replace("'", "\\'")
				+ "','"
				+ articleOne.getPubTime().replace("'", "\\'")
				+ "','"
				+ articleOne.getArticleUrl().replace("'", "\\'") + "');";
	}
}
