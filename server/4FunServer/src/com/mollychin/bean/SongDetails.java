package com.mollychin.bean;

import java.util.List;

public class SongDetails {
	private String errorCode;
	private SongData data;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public SongData getData() {
		return data;
	}

	public void setData(SongData data) {
		this.data = data;
	}

	public static class SongData {
		private String time;
		private String xcode;
		private List<SongList> songList;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getXcode() {
			return xcode;
		}

		public void setXcode(String xcode) {
			this.xcode = xcode;
		}

		public List<SongList> getSongList() {
			return songList;
		}

		public void setSongList(List<SongList> songList) {
			this.songList = songList;
		}

		public static class SongList {
			private String queryId;
			private String songName;
			private String artistId;
			private String artistName;
			private String lrcLink;
			private String time;
			private String songLink;
			private String albumId;
			private String albumName;

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

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
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
