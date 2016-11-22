package com.mollychin.bean;

import java.util.List;

public class MovieInfo {
	private String date;
	private String movieName;
	private double mark;
	private String pic;
	private String director;
	private String playWriter;
	private String country;
	private String briefIntro;
	private String moreInfo;
	private String pageUrl;
	private List<ActorInfo> actor;

	public List<ActorInfo> getActor() {
		return actor;
	}

	public void setActor(List<ActorInfo> actor) {
		this.actor = actor;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlayWriter() {
		return playWriter;
	}

	public void setPlayWriter(String playWriter) {
		this.playWriter = playWriter;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBriefIntro() {
		return briefIntro;
	}

	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public static class ActorInfo {
		private String movieName;
		private String actorName;
		private String actorRole;

		public String getMovieName() {
			return movieName;
		}

		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		public String getActorName() {
			return actorName;
		}

		public void setActorName(String actorName) {
			this.actorName = actorName;
		}

		public String getActorRole() {
			return actorRole;
		}

		public void setActorRole(String actorRole) {
			this.actorRole = actorRole;
		}
	}
}
