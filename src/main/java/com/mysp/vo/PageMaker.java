package com.mysp.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum=10;
	
	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	private void calcData() {//Math.ceil : 강제로 소수점을 올림해주는 메소드
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage-displayPageNum)+1;
		int tempEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		if(endPage > tempEndPage) {//endPage는 실제 데이터의 개수와 관련이 있기 때문에 다시 한번 계산할 필요가 있음, 이전에 구한 endPage값과 계산된 결과를 비교해서 계산된 결과가 작은 경우에는 실제 endPage는 최종 계산된 결과가 돼야만 함
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	public String makeQuery(int page) { //게시글을 읽을 때 페이지 값과 현페이지의 나타날 글 갯수 이 두개의 값을 누름과 동시에 같이 넘기기 위한 메소드 이거 하고나면 listPage.jsp도 수정
		//글제목의 링크태그 url 벨류값에 value='/board/read${pageMaker.makeQuery(pageMaker.cri.page)}&bno=${boardVO.bno}' />">${boardVO.title } 넣으면 된다
			UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
					.queryParam("perPageNum", cri.getPerPageNum()).build();
			return uriComponents.toUriString();
		}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
