package com.mollychin.bean;

public class Poem {
	private String poemContent;
	private String poemAuthor;
	private String poemDate;
	private String poemVoice;
	private String poemTitle;

	public String getPoemTitle() {
		return poemTitle;
	}

	public void setPoemTitle(String poemTitle) {
		this.poemTitle = poemTitle;
	}

	public String getPoemContent() {
		return poemContent;
	}

	public void setPoemContent(String poemContent) {
		this.poemContent = poemContent;
	}

	public String getPoemAuthor() {
		return poemAuthor;
	}

	public void setPoemAuthor(String poemAuthor) {
		this.poemAuthor = poemAuthor;
	}

	public String getPoemDate() {
		return poemDate;
	}

	public void setPoemDate(String poemDate) {
		this.poemDate = poemDate;
	}

	public String getPoemVoice() {
		return poemVoice;
	}

	public void setPoemVoice(String poemVoice) {
		this.poemVoice = poemVoice;
	}

	@Override
	public String toString() {
		return "Poem [poemContent=" + poemContent + ", poemAuthor="
				+ poemAuthor + ", poemDate=" + poemDate + ", poemVoice="
				+ poemVoice + ", poemTitle=" + poemTitle + "]";
	}
}
