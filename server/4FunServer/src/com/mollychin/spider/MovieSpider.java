package com.mollychin.spider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mollychin.bean.MovieInfo;
import com.mollychin.bean.MovieInfo.ActorInfo;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.JsoupUtil;

/**
 * Created by mollychin on 2016/11/16.
 */
public class MovieSpider {
	public static final String URL = "http://movie.mtime.com/classic/";

	public static void main(String[] args) {
		try {
			MovieSpider movieParser = new MovieSpider();
			Document document = JsoupUtil.connect(URL);
			Connection conn = JDBCUtil.getConnection();
			MovieInfo movieInfo = new MovieInfo();
			ActorInfo actorInfo = new ActorInfo();
			Elements month = document.select("span.month");
			Elements day = document.select("span.day");
			Elements movie = document.select("h3.px14.fl > a");
			Elements point = document.select("p.point.ml9");
			for (int i = 0; i < month.size(); i++) {
				// 日期
				movieInfo.setDate("2016-"
						+ (month.get(i).text() + " " + day.get(i).text())
								.replace("月", "-").replace(" ", ""));
				// 片名
				actorInfo.setMovieName(movie.get(i).text());
				movieInfo.setMovieName(movie.get(i).text());
				// 评分
				movieInfo.setMark(Double.parseDouble(point.get(i).text()));
				movieInfo.setPageUrl(movie.get(i).attr("href"));
				movieParser.parseDetails(conn, movieInfo, actorInfo,
						movie.get(i).attr("href"));
			}
			JDBCUtil.closeConnection(conn);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 图片 信息 剧情 三个主演 更多
	private void parseDetails(Connection conn, MovieInfo movieInfo,
			ActorInfo actorInfo, String href) {
		try {
			Document document = Jsoup.connect(href).get();
			List<String> peopleInfo = new ArrayList<String>();
			// 类型
			Elements movieTypes = document.select("div.otherbox.__r_c_ > a");
			String movieType = "";
			for (int i = 0; i < movieTypes.size() - 1; i++) {
				movieType = movieType + movieTypes.get(i).text();
				if (i != movieTypes.size() - 2) {
					movieType = movieType + "/";
				}
			}
			movieInfo.setMovieType(movieType);
			// 图片
			Elements select = document.select("div > a > img");
			movieInfo.setPic(select.attr("src"));
			// 信息
			// Elements job = document.select("dd.__r_c_ > strong");
			Elements name = document.select("dl.info_l > dd.__r_c_");
			for (int i = 0; i < 3; i++) {
				Elements info = name.get(i).select("a");
				String people = "";
				for (int j = 0; j < info.size(); j++) {
					people = people + info.get(j).text().replace(".", "") + " ";
					if (people.equals("") || people.equals(" ")) {
						continue;
					}
					// System.out.println(people);
				}
				peopleInfo.add(people.substring(0, people.length() - 1));
			}
			movieInfo.setDirector(peopleInfo.get(0));
			movieInfo.setPlayWriter(peopleInfo.get(1));
			movieInfo.setCountry(peopleInfo.get(2));
			// 剧情以及更多剧情链接
			Elements story = document.select("p.mt6.lh18");
			Elements link = document.select("p.tr.mt9 > a");
			movieInfo.setBriefIntro(story.text().trim());
			movieInfo.setMoreInfo(link.attr("href"));
			// 三个主演
			Elements actorsName = document.select("dl.main_actor > dd");
			Elements actorsRole = actorsName.select("p");
			String all = "";
			for (int i = 0, j = 0; i < actorsRole.size(); i++) {
				String s = actorsRole.get(i).text();
				all = all + " " + s;
				if (s.startsWith("饰")) {
					int sub = all.indexOf("饰");
					String actor = all.substring(1, sub - 1);
					String role = all.substring(sub + 2, all.length() - 1);
					actorInfo.setActorName(actor);
					actorInfo.setActorRole(role);
					all = "";
					j++;
					JDBCUtil.insertData(conn, getActorSql(actorInfo));
				}
				if (j > 2) {
					break;
				}
			}
			JDBCUtil.insertData(conn, getMovieSql(movieInfo));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getMovieSql(MovieInfo movieInfo) {
		return "insert movieinfo(date,movieName,mark,pic,director,playWriter,country,briefIntro,moreInfo,pageUrl,movieType) values('"
				+ movieInfo.getDate().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getMovieName().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getMark()
				+ "','"
				+ movieInfo.getPic().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getDirector().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getPlayWriter().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getCountry().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getBriefIntro().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getMoreInfo().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getPageUrl().replace("'", "\\\'")
				+ "','"
				+ movieInfo.getMovieType().replace("'", "\\\'") + "');";
	}

	private String getActorSql(ActorInfo actorInfo) {
		return "insert movieactorinfo(movieName,actorName,actorRole) values('"
				+ actorInfo.getMovieName().replace("'", "\\\'") + "','"
				+ actorInfo.getActorName().replace("'", "\\\'") + "','"
				+ actorInfo.getActorRole().replace("'", "\\\'") + "');";
	}
}
