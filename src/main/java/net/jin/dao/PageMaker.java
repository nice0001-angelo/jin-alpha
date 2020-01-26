/*
 * This is a Jin-alpha Project
 * File name : PageMaker.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : index.html write board paging
 */
package net.jin.dao;

public class PageMaker {
	private int totalcount;//전체 게시물 갯수
	private int pagenum;//현재 페이지 번호
	private int contentnum=10;//한 페이지에 몇개 표시할지
	private int startPage=1;//현재 페이지 블록의 시작 페이지
	private int endPage=5;//현재 페이지 블록의 마지막 페이지
	private boolean prev=false;//이전 페이지로 가는 화살표
	private boolean next;//다음 페이지로 가는 화살표
	private int currentblock;//현재 페이지 블록
	private int lastblock;//마지막 페이지 블록
	
	public void prenext(int pagenum) {
		if(pagenum > 0 && pagenum < 6) {
			setPrev(false);
			setNext(true);
		}
		else if(getLastblock() == getCurrentblock()) {
			setPrev(true);
			setNext(false);
		}
		else {
			setPrev(true);
			setNext(true);
		}
	}
	
	public int calcpage(int totalcount, int contentnum) { // totalpage calculation method
		int totalpage = totalcount/contentnum;
		if(totalcount%contentnum > 0) {
			totalpage++;
		}
		return totalpage;
	}
	
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
	public void setStartPage(int currentblock) { //Setting currentBlock's Start page
		this.startPage = (currentblock*5)-4;
		//1/1 2 3 4 5 --> 1
		//2/6 7 8 9 10 --> 6
		//3/11 12 13 14 15 ->11
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int getlastblock, int getcurrentblock) {//Setting currentBlock's End page
		if (getlastblock == getcurrentblock) {
			this.endPage = calcpage(getTotalcount(), getContentnum());
		} else {
		this.endPage = getStartPage()+4;
		}
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
	public void setCurrentblock(int pagenum) {
		//현재 페이지 번호를 통해 구한다
		//1/5=0.2 -> 0.2 > 0 -> 0+1 = 1
		//10/5=0 -> 0 == 0 -> 2+0 = 2
		//11/5=0.2 -> 0.2 > 0 -> 2+1 = 3
		this.currentblock = pagenum/5;
		if(pagenum%5>0) {
			this.currentblock++;
		}
	}
	public int getLastblock() {
		return lastblock;
	}
	public void setLastblock(int totalcount) {
		//페이지당 10개 게시물, 블록당 5페이지 그러므로 10*5=50개 게시물
		//totalcount = 125, contnum=10 일때  this.lastblock = 125/(5*10) = 2.5 -> (int)2.5 = 2
		//totalcount%(5*this.contentnum)= 0.25 > 0 -> this.lastblock++ ->< 2+1 = 3 
		this.lastblock = totalcount/(5*this.contentnum);
		if(totalcount%(5*this.contentnum)>0) {
			this.lastblock++;
		}
	}
	
	
}
