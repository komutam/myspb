package com.mysp.vo;

public class MyBoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String regedate;
	private int viewcnt;
	private int replyCnt;
	
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegedate() {
		return regedate;
	}
	public void setRegedate(String regedate) {
		this.regedate = regedate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	@Override
	public String toString() {
		return "myBoardVO[bno="+bno+", title="+title+", content="+content+", writer="+writer+", regedate="+regedate+", viewcnt="+viewcnt+", replycnt="+replyCnt+"]";
	}
	
}
