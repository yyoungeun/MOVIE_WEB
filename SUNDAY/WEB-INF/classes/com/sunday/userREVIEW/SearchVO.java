package com.sunday.userREVIEW;

import com.sunday.cmn.DTO;

public class SearchVO extends DTO {
	private final int PAGE_SIZE = 10;		//페이지사이즈:10
	private int pageNum;    				//페이지넘버
	private String searchDiv;   			//검색구분:제목&영제, 장르, 제작년도
	private String searchWord;				//검색어

	
	public SearchVO(){}


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

	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}


	@Override
	public String toString() {
		return "SearchVO [PAGE_SIZE=" + PAGE_SIZE + ", pageNum=" + pageNum + ", searchDiv=" + searchDiv
				+ ", searchWord=" + searchWord  + "]";
	}


	public SearchVO(int pageNum, String searchDiv, String searchWord) {
		super();
		this.pageNum = pageNum;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + PAGE_SIZE;
		result = prime * result + pageNum;
		result = prime * result + ((searchDiv == null) ? 0 : searchDiv.hashCode());
		result = prime * result + ((searchWord == null) ? 0 : searchWord.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchVO other = (SearchVO) obj;
		if (PAGE_SIZE != other.PAGE_SIZE)
			return false;
		if (pageNum != other.pageNum)
			return false;
		if (searchDiv == null) {
			if (other.searchDiv != null)
				return false;
		} else if (!searchDiv.equals(other.searchDiv))
			return false;
		if (searchWord == null) {
			if (other.searchWord != null)
				return false;
		} else if (!searchWord.equals(other.searchWord))
			return false;
		return true;
	}

	

	
	
	
}
