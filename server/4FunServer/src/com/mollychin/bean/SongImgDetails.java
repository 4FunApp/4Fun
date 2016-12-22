package com.mollychin.bean;

import java.util.List;

public class SongImgDetails {
	private SongList data;

	public SongList getData() {
		return data;
	}

	public void setData(SongList data) {
		this.data = data;
	}

	public static class SongList {
		private List<ResultBean> songList;

		public List<ResultBean> getSongList() {
			return songList;
		}

		public void setSongList(List<ResultBean> songList) {
			this.songList = songList;
		}
	}

	public static class ResultBean {
		private String songPicRadio;

		public String getSongPicRadio() {
			return songPicRadio;
		}

		public void setSongPicRadio(String songPicRadio) {
			this.songPicRadio = songPicRadio;
		}
	}
}
