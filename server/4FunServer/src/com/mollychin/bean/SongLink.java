package com.mollychin.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SongLink {
	@SerializedName("hash_code")
	private String hashCode;
	@SerializedName("channel_id")
	private String channelId;
	@SerializedName("channel_name")
	private String channelName;
	private List<SongMusicList> list;

	public String getHash_code() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<SongMusicList> getList() {
		return list;
	}

	public void setList(List<SongMusicList> list) {
		this.list = list;
	}

	public static class SongMusicList {
		private String id;
		private int type;
		private int method;
		private int flow_mark;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getMethod() {
			return method;
		}

		public void setMethod(int method) {
			this.method = method;
		}

		public int getFlow_mark() {
			return flow_mark;
		}

		public void setFlow_mark(int flow_mark) {
			this.flow_mark = flow_mark;
		}
	}
}
