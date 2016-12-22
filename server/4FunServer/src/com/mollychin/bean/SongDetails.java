package com.mollychin.bean;

import java.util.List;

public class SongDetails {
	private SongData data;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setData(SongData data) {
		this.data = data;
	}

	public SongData getData() {
		return data;
	}

	public static class SongData {
		private List<SongList> songList;

		public List<SongList> getSongList() {
			return songList;
		}

		public void setSongList(List<SongList> songList) {
			this.songList = songList;
		}

		public static class SongList {
			private String date;

			public String getDate() {
				return date;
			}

			public void setDate(String date) {
				this.date = date;
			}

			private String queryId;
			private String songName;
			private String artistId;
			private String artistName;
			private String lrcLink;
			private String songLink;
			private String albumId;
			private String albumName;
			private String imgUrl;

			public String getImgUrl() {
				return imgUrl;
			}

			public void setImgUrl(String imgUrl) {
				this.imgUrl = imgUrl;
			}

			public String getAlbumName() {
				return albumName;
			}

			public void setAlbumName(String albumName) {
				this.albumName = albumName;
			}

			public String getAlbumId() {
				return albumId;
			}

			public void setAlbumId(String albumId) {
				this.albumId = albumId;
			}

			public String getQueryId() {
				return queryId;
			}

			public void setQueryId(String queryId) {
				this.queryId = queryId;
			}

			public String getSongName() {
				return songName;
			}

			public void setSongName(String songName) {
				this.songName = songName;
			}

			public String getArtistId() {
				return artistId;
			}

			public void setArtistId(String artistId) {
				this.artistId = artistId;
			}

			public String getArtistName() {
				return artistName;
			}

			public void setArtistName(String artistName) {
				this.artistName = artistName;
			}

			public String getLrcLink() {
				return lrcLink;
			}

			public void setLrcLink(String lrcLink) {
				this.lrcLink = lrcLink;
			}

			public String getSongLink() {
				return songLink;
			}

			public void setSongLink(String songLink) {
				this.songLink = songLink;
			}

		}
	}
}
