package com.sunday.movie.domain;

import com.sunday.cmn.DTO;

public class SearchVO extends DTO {
	private final int PAGE_SIZE = 10;    	//페이지사이즈:10
	private int pageNum;    				//페이지넘버
	private String searchDiv;   			//검색구분:제목&영제, 장르, 제작년도
	private String searchWord;  			//검색어
	
	public SearchVO(){}

	public SearchVO(int pageNum, String searchDiv, String searchWord) {
		super();
		this.pageNum = pageNum;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	public int getPageSize() {
		return PAGE_SIZE;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "SearchVO [pageSize=" + PAGE_SIZE + ", pageNum=" + pageNum + ", searchDiv=" + searchDiv + ", searchWord="
				+ searchWord + "]";
	}
	
	
}
