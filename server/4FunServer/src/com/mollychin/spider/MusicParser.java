package com.mollychin.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.mollychin.bean.SongDetails;
import com.mollychin.bean.SongInfo;
import com.mollychin.dao.JDBCUtil;
import com.mysql.jdbc.Connection;

public class MusicParser {
	public static void main(String[] args) {
		try {
			MusicParser musicParser = new MusicParser();
			String firstUrl = "http://fm.baidu.com/dev/api/?tn=playlist&id=public_fengge_qingyinyue&hashcode=fe1bcde0a7d25f21a59a257d73268a01&_=1479281643794";
			String secondUrl = "http://play.baidu.com/data/music/songlink?songIds=";
			MusicParser parser = new MusicParser();
			List<String> musicIds = parser.getIdList(firstUrl);
			for (int i = 0; i < musicIds.size(); i++) {
				String realUrl = secondUrl + String.valueOf(musicIds.get(i));
				SongDetails songDetails = parser.jsonParser(realUrl, SongDetails.class);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
				String time = format.format(date);
				songDetails.setTime(time);
				// System.out.println(songDetails.getTime());
				// System.out.println(songDetails.getData().getSongList().get(0).getQueryId());
				// 数据库操作
				Connection conn = (Connection) JDBCUtil.getConnection();
				JDBCUtil.insertData(conn, musicParser.insertSql(songDetails));
				JDBCUtil.closeConnection(conn);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String insertSql(SongDetails songDetails) {
		return "insert into musicinfo (queryId,date,songName,artistId,artistName,songLink,lrcLink,albumId,albumName)  values('"
				+ songDetails.getData().getSongList().get(0).getQueryId() + "','" + songDetails.getTime() + "','"
				+ songDetails.getData().getSongList().get(0).getSongName() + "','"
				+ songDetails.getData().getSongList().get(0).getArtistId() + "','"
				+ songDetails.getData().getSongList().get(0).getArtistName() + "','"
				+ songDetails.getData().getSongList().get(0).getSongLink() + "','"
				+ songDetails.getData().getSongList().get(0).getLrcLink() + "','"
				+ songDetails.getData().getSongList().get(0).getAlbumId() + "','"
				+ songDetails.getData().getSongList().get(0).getAlbumName() + "');";
	}

	public List<String> getIdList(String url) throws MalformedURLException, IOException {
		List<String> idList = new ArrayList<String>();
		try {
			SongInfo bean = jsonParser(url, SongInfo.class);
			for (int i = 0; i < 8; i++) {
				String id = bean.getList().get(i).getId();
				idList.add(id);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return idList;
	}

	private <T> T jsonParser(String urlString, Class<T> aClass) throws MalformedURLException, IOException {
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			// json
			String json = bs.toString();
			// System.out.println(json);
			T fromJson = new Gson().fromJson(json, aClass);
			return fromJson;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
