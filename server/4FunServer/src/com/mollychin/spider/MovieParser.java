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
import com.mollychin.dao.JDBCUtil;

/**
 * Created by mollychin on 2016/11/16.
 */
public class MovieParser {
	public static void main(String[] args) {
		Connection conn = null;
		// 影名 日期 评分
		try {
			conn = JDBCUtil.getConnection();
			MovieInfo movieInfo = new MovieInfo();
			ActorInfo actorInfo = new ActorInfo();
			MovieParser movieParser = new MovieParser();
			String url = "http://movie.mtime.com/classic/";
			Document document = Jsoup.connect(url).get();
			Elements month = document.select("span.month");
			Elements day = document.select("span.day");
			Elements movie = document.select("h3.px14.fl > a");
			Elements point = document.select("p.point.ml9");
			for (int i = 0; i < month.size(); i++) {
				// 日期
				movieInfo.setDate((month.get(i).text() + " " + day.get(i).text()).replace("月", "-").replace(" ", ""));
				// 片名
				actorInfo.setMovieName(movie.get(i).text());
				movieInfo.setMovieName(movie.get(i).text());
				// 评分
				movieInfo.setMark(Double.parseDouble(point.get(i).text()));
				movieInfo.setPageUrl(movie.get(i).attr("href"));
				movieParser.parseDetails(conn, movieInfo, actorInfo, movie.get(i).attr("href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	// 图片 信息 剧情 三个主演 更多
	private void parseDetails(Connection conn, MovieInfo movieInfo, ActorInfo actorInfo, String href) {
		try {
			Document document = Jsoup.connect(href).get();
			List<String> peopleInfo = new ArrayList<String>();
			System.out.println(href);
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
				// System.out.println("----------------");
			}
			System.out.println(peopleInfo);
			movieInfo.setDirector(peopleInfo.get(0));
			movieInfo.setPlayWriter(peopleInfo.get(1));
			movieInfo.setCountry(peopleInfo.get(2));
			// 剧情以及更多剧情链接
			Elements story = document.select("p.mt6.lh18");
			Elements link = document.select("p.tr.mt9 > a");
			movieInfo.setBriefIntro(story.text());
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
		return "insert movieinfo(date,movieName,mark,pic,director,playWriter,country,briefIntro,moreInfo,pageUrl) values('"
				+ movieInfo.getDate().replace("'", "\\\'") + "','" + movieInfo.getMovieName().replace("'", "\\\'")
				+ "','" + movieInfo.getMark() + "','" + movieInfo.getPic().replace("'", "\\\'") + "','"
				+ movieInfo.getDirector().replace("'", "\\\'") + "','" + movieInfo.getPlayWriter().replace("'", "\\\'")
				+ "','" + movieInfo.getCountry().replace("'", "\\\'") + "','"
				+ movieInfo.getBriefIntro().replace("'", "\\\'") + "','" + movieInfo.getMoreInfo().replace("'", "\\\'")
				+ "','" + movieInfo.getPageUrl().replace("'", "\\\'") + "');";

	}

	private String getActorSql(ActorInfo actorInfo) {
		return "insert actorinfo(movieName,actorName,actorRole) values('"
				+ actorInfo.getMovieName().replace("'", "\\\'") + "','" + actorInfo.getActorName().replace("'", "\\\'")
				+ "','" + actorInfo.getActorRole().replace("'", "\\\'") + "');";
	}
}
