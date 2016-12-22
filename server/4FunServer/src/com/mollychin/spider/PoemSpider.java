package com.mollychin.spider;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.Poem;
import com.mollychin.utils.DownloadUtil;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.JsoupUtil;
import com.mysql.jdbc.Connection;

public class PoemSpider {
	public static final String BASE_URL = "http://www.zgshige.com";

	public static void main(String[] args) {
		try {
			PoemSpider poemSpider = new PoemSpider();
			Document document = JsoupUtil.connect(BASE_URL + "/mrhs/");
			Elements div = document.select("div.yun_mrhs_box");
			Elements select = div.select("h4 > a[href]");
			// 或者写：Elements select = div.select("h4 > a[_target]")；
			// System.out.println(select);
			// 数据库操作
			Connection conn = (Connection) JDBCUtil.getConnection();
			for (int i = 0; i < select.size(); i++) {
				Poem poem = poemSpider.parseDetail(BASE_URL + select.get(i).attr("href"));
				JDBCUtil.insertData(conn, poemSpider.insertSql(poem));
			}
			JDBCUtil.closeConnection(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String insertSql(Poem poem) {
		return "insert into poemchina(poemContent,poemAuthor,poemDate,poemVoice,poemTitle) values('"
				+ poem.getPoemContent().replace("'", "\\\'") + "','" + poem.getPoemAuthor().replace("'", "\\\'") + "','"
				+ poem.getPoemDate().replace("'", "\\\'") + "','" + poem.getPoemVoice().replace("'", "\\\'") + "','"
				+ poem.getPoemTitle().replace("'", "\\\'") + "');";
	}

	private Poem parseDetail(String url) throws IOException {
		Poem poem = new Poem();
		Document document = JsoupUtil.connect(url);
		// 诗歌标题
		Elements title = document.select("title");
		poem.setPoemTitle(title.text().replace("-每日好诗-中国诗歌网", ""));
		// System.out.println(title.text().replace("-每日好诗-中国诗歌网", ""));
		// System.out.println(url);
		Elements info = document.select("div.col-xs-12 > span");
		// 诗歌作者
		poem.setPoemAuthor(info.get(0).text().replace("作者：", ""));
		// 推送时间 过滤中文
		poem.setPoemDate(info.get(1).text().replaceAll("[\u4e00-\u9fa5]", "-").substring(0, 10));
		// 诗歌内容
		Elements contentElements = document.select("p > span");
		String content = "";
		// 有些网页可能是第二个 span 才是诗歌内容
		if ((content = contentElements.get(0).html().toString().replace("<br style=\"box-sizing: border-box;\">", "\n"))
				.replace("&nbsp;", "").equals("")) {
			content = contentElements.get(1).html().toString().replace("<br style=\"box-sizing: border-box;\">", "\n")
					.replace("&nbsp;", "");
		}
		poem.setPoemContent(content);

		// 朗诵链接
		Elements linkElements = document.select("div[audio-url]");
		String link = "";
		if (linkElements != null && linkElements.size() > 0) {
			link = BASE_URL + linkElements.get(0).attr("audio-url");
			poem.setPoemVoice(DownloadUtil.downloadRecitation(link, link.substring(link.length() - 10, link.length())));
		} else {
			poem.setPoemVoice(link);
		}

		return poem;
	}
}
