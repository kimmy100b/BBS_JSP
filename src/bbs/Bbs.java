package bbs;

public class Bbs {
	private int bbsID;
	private String bbsTitle;
	private String userID;
	private String bbsContent;
	private String regDate;
	private int bbsAvailable;

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getBbsContent() {
		return bbsContent;
	}

	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getBbsAvaliable() {
		return bbsAvaliable;
	}

	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvaliable = bbsAvaliable;
	}
}
