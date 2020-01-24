package net.jin.dao;

public class PageMaker {
	private int totalcount;//전체 게시물 갯수
	private int pagenum;//현재 페이지 번호
	private int contentnum;//한 페이지에 몇개 표시할지
	private int startPage;//현재 페이지 블록의 시작 페이지
	private int endPage;//현재 페이지 블록의 마지막 페이지
	private boolean prev;//이전 페이지로 가는 화살표
	private boolean next;//다음 페이지로 가는 화살표
	private int currentblock;//현재 페이지 블록
	private int lastblock;//마지막 페이지 블록
	
	
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getContentnum() {
		return contentnum;
	}
	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
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
	public int getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(int currentblock) {
		this.currentblock = currentblock;
	}
	public int getLastblock() {
		return lastblock;
	}
	public void setLastblock(int lastblock) {
		this.lastblock = lastblock;
	}
	
	
}
