package com.mollychin.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mollychin.bean.SongDetails;
import com.mollychin.bean.SongImgDetails;
import com.mollychin.bean.SongLink;
import com.mollychin.utils.ConstantsUtil;
import com.mollychin.utils.DownloadUtil;
import com.mollychin.utils.JDBCUtil;
import com.mollychin.utils.SystemUtil;
import com.mysql.jdbc.Connection;

public class MusicSpider {
	// PARENT_URL的链接来源是“http://fm.baidu.com”轻音乐模块的F12netWork的第一个包中的api (通过双击)
	public static final String PARENT_URL = ConstantsUtil.MUSIC_PARENT_URL;
	// 提供歌曲的具体信息
	public static final String LINK_URL = ConstantsUtil.MUSIC_LINK_URL;
	// 提供歌曲的图
	public static final String INFO_URL = ConstantsUtil.MUSIC_INFO_URL;
	public static final String PITCTUR4MUSIC = "Picture4Music/";

	public void musicSpider() {
		try {
			MusicSpider musicParser = new MusicSpider();
			MusicSpider parser = new MusicSpider();
			// 获取歌曲 id 给第二个层页面
			List<String> musicIds = parser.getIdList(PARENT_URL);
			for (int i = 0; i < musicIds.size(); i++) {
				String id = String.valueOf(musicIds.get(i));
				String infoUrl = INFO_URL + id;
				String linkUrl = LINK_URL + id;
				// 第三方库Gson将json转化为bean 调用bean的set方法
				SongDetails songDetails = SystemUtil.jsonParser(linkUrl,
						SongDetails.class);
				// songList是一个List,看网页的json表示
				String songLink = songDetails.getData().getSongList().get(0)
						.getSongLink();
				String songName = songDetails.getData().getSongList().get(0)
						.getSongName();
				// 下载链接
				String localLink = DownloadUtil.downloadMusic(songLink,
						songName);
				songDetails.getData().getSongList().get(0)
						.setSongLink(localLink);
				// 歌曲图片
				SongImgDetails songImgInfo = SystemUtil.jsonParser(infoUrl,
						SongImgDetails.class);
				// 在图片的链接中得到歌曲的图片url 通过歌曲的setImgUrl方法赋值给歌曲的图片属性
				String imgUrl = songImgInfo.getData().getSongList().get(0)
						.getSongPicRadio();
				String imgName = imgUrl;
				String projectPicturePath = DownloadUtil.downloadPicture(
						imgUrl, imgName, PITCTUR4MUSIC);
				songDetails.getData().getSongList().get(0)
						.setImgUrl(projectPicturePath);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
				String time = format.format(date);
				songDetails.setDate(time);

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
		System.out
				.println("insert into musicinfo (queryId,date,songName,artistId,artistName,songLink,lrcLink,albumId,albumName,imgUrl)  values('"
						+ songDetails.getData().getSongList().get(0)
								.getQueryId().replace("'", "\\\'")
						+ "','"
						+ songDetails.getDate().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getSongName().replace("'", "\\\'")
								.replace("\\", "")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getArtistId().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getArtistName().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getSongLink().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getLrcLink().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getAlbumId().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getAlbumName().replace("'", "\\\'")
						+ "','"
						+ songDetails.getData().getSongList().get(0)
								.getImgUrl().replace("'", "\\\'") + "');");
		return "insert into musicinfo (queryId,date,songName,artistId,artistName,songLink,lrcLink,albumId,albumName,imgUrl)  values('"
				+ songDetails.getData().getSongList().get(0).getQueryId()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getDate().replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getSongName()
						.replace("'", "\\\'").replace("\\", "")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getArtistId()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getArtistName()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getSongLink()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getLrcLink()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getAlbumId()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getAlbumName()
						.replace("'", "\\\'")
				+ "','"
				+ songDetails.getData().getSongList().get(0).getImgUrl()
						.replace("'", "\\\'") + "');";
	}

	public List<String> getIdList(String url) throws MalformedURLException,
			IOException {
		List<String> idList = new ArrayList<String>();
		try {
			//
			SongLink bean = SystemUtil.jsonParser(url, SongLink.class);
			for (int i = 0; i < 5; i++) {
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

}
