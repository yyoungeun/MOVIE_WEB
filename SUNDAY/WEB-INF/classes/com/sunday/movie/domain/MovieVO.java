package com.sunday.movie.domain;

import com.sunday.cmn.DTO;

public class MovieVO extends DTO {
	String DOCID;			//DOCID 
	String title;			//제목    
	String titleEng;		//영제 
	String genre;			//장르    
	String directorNm;		//감독이름  
	int    directorId;		//감독코드  
	String nation;			//국가 
	String actorNm;			//배우이름  
	int    actorId;			//배우코드  
	String keywords;		//키워드 
	String posters;			//포스터 주소
	String releaseDate;		//개봉일   
	String prodYear;		//제작년도
	double rate;			//평점 
	String plot;			//줄거리   
	String nowShowing;		//현재상영 여부
	String regId;			//등록자ID 
	String regDt;			//등록일   
	String modId;			//수정자ID 
	String modDt;			//수정일   
	
	public MovieVO(){}
	


	public MovieVO(String dOCID, String title, String titleEng, String genre, String directorNm, int directorId,
			String nation, String actorNm, int actorId, String keywords, String posters, String releaseDate,
			String prodYear, double rate, String plot, String nowShowing, String regId, String regDt, String modId,
			String modDt) {
		super();
		DOCID = dOCID;
		this.title = title;
		this.titleEng = titleEng;
		this.genre = genre;
		this.directorNm = directorNm;
		this.directorId = directorId;
		this.nation = nation;
		this.actorNm = actorNm;
		this.actorId = actorId;
		this.keywords = keywords;
		this.posters = posters;
		this.releaseDate = releaseDate;
		this.prodYear = prodYear;
		this.rate = rate;
		this.plot = plot;
		this.nowShowing = nowShowing;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getDOCID() {
		return DOCID;
	}

	public void setDOCID(String dOCID) {
		DOCID = dOCID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleEng() {
		return titleEng;
	}

	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirectorNm() {
		return directorNm;
	}

	public void setDirectorNm(String directorNm) {
		this.directorNm = directorNm;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getActorNm() {
		return actorNm;
	}

	public void setActorNm(String actorNm) {
		this.actorNm = actorNm;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPosters() {
		return posters;
	}

	public void setPosters(String posters) {
		this.posters = posters;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getProdYear() {
		return prodYear;
	}

	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getNowShowing() {
		return nowShowing;
	}

	public void setNowShowing(String nowShowing) {
		this.nowShowing = nowShowing;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "MovieVO [DOCID=" + DOCID + ", title=" + title + ", titleEng=" + titleEng + ", genre=" + genre
				+ ", directorNm=" + directorNm + ", directorId=" + directorId + ", nation=" + nation + ", actorNm="
				+ actorNm + ", actorId=" + actorId + ", keywords=" + keywords + ", posters=" + posters
				+ ", releaseDate=" + releaseDate + ", prodYear=" + prodYear + ", rate=" + rate + ", plot=" + plot
				+ ", nowShowing=" + nowShowing + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId
				+ ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}



}