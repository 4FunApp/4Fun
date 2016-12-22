package com.mollychin.bean;

public class PicOne {
	private String authorWork;
	private String picDescription;
	private String picDate;
	private String picUrl;
	private String pubTime;
	private String VOL;

	public String getVOL() {
		return VOL;
	}

	public void setVOL(String vOL) {
		VOL = vOL;
	}

	public String getAuthorWork() {
		return authorWork;
	}

	public void setAuthorWork(String authorWork) {
		this.authorWork = authorWork;
	}

	public String getPicDescription() {
		return picDescription;
	}

	public void setPicDescription(String picDescription) {
		this.picDescription = picDescription;
	}

	public String getPicDate() {
		return picDate;
	}

	public void setPicDate(String picDate) {
		this.picDate = picDate;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

	@Override
	public String toString() {
		return "PicOne [authorWork=" + authorWork + ", picDescription="
				+ picDescription + ", picDate=" + picDate + ", picUrl="
				+ picUrl + ", pubTime=" + pubTime + "]";
	}
}
