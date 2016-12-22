package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mollychin.bean.PicOne;
import com.mollychin.utils.DownloadUtil;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.JsoupUtil;

public class PicWufaZhuceSpider {
	public final static String URL = "http://wufazhuce.com/";

	public static void main(String[] args) {
		try {
			PicWufaZhuceSpider picSpider = new PicWufaZhuceSpider();
			Document document = JsoupUtil.connect(URL);
			Elements select = document.select("div.fp-one-cita > a");
			for (int i = 0; i < select.size(); i++) {
				picSpider.parse(select.get(i).attr("href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parse(String html) throws IOException {
		try {
			Document document = JsoupUtil.connect(html);
			PicOne picOne = new PicOne();
			// 图片 url
			Element pic = document.select("img[alt]").first();
			String imageUrl = pic.attr("src");
			String download = DownloadUtil.downloadPicture(imageUrl,
					imageUrl.substring(28, imageUrl.length() - 1));
			picOne.setPicUrl(download);
			// 图片描述
			Element picDes = document.select("div.one-cita").first();
			picOne.setPicDescription(picDes.text());
			// VOL
			Element VOL = document.select("div.one-titulo").first();
			picOne.setVOL(VOL.text().trim());
			// 作者
			Element authorWork = document.select("div.one-imagen-leyenda")
					.first();
			picOne.setAuthorWork(authorWork.text().trim().replace("\"", ""));
			// 日期
			Elements date = document.select("div.one-pubdate > p");
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < date.size();) {
				s.append(date.get(i).text());
				if ((++i) != date.size()) {
					s.append(" ");
				}
			}
			String formatDate = s.toString().replace(" ", "-");
			picOne.setPicDate(formatDate);
			// 推送时间
			picOne.setPubTime(string2Date(formatDate));

			// 数据库操作
			Connection conn = JDBCUtil.getConnection();
			JDBCUtil.insertData(conn, getSql(picOne));
			JDBCUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSql(PicOne picOne) {
		return "insert into picturefromone(authorWork,picDescription,picDate,picUrl,pubTime, VOL) values('"
				+ picOne.getAuthorWork().replace("'", "\\\'")
				+ "','"
				+ picOne.getPicDescription().replace("'", "\\\'")
				+ "','"
				+ picOne.getPicDate().replace("'", "\\\'")
				+ "','"
				+ picOne.getPicUrl().replace("'", "\\\'")
				+ "','"
				+ picOne.getPubTime().replace("'", "\\\'").replace("发布时间：", "")
						.substring(0, 10)
				+ "','"
				+ picOne.getVOL().replace("'", "\\\'") + "');";
	}

	private String string2Date(String dateString) {
		try {
			// 格式
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",
					Locale.ENGLISH);
			// 将字符串转换成日期
			Date parse = dateFormat.parse(dateString);
			// 日期转换成中文
			SimpleDateFormat chineseFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = chineseFormat.format(parse);

			return format;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "";
	}
}
