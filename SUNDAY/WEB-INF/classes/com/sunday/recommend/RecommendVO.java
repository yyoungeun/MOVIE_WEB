package com.sunday.recommend;

import com.sunday.cmn.DTO;

public class RecommendVO extends DTO {

	// 추천
	private int recM = 0; // 기분 추천코드
	private int recW = 0; // 날씨 추천코드
	private int recT = 0; // 시간 추천코드
	private int recP = 0; // 사람 추천코드
	
	// 영화
	private String DOCID;			//DOCID 
	private String title;			//제목    
	private String titleEng;		//영제 
	private String genre;			//장르    
	private String directorNm;		//감독이름  
	private int    directorId;		//감독코드  
	private String nation;			//국가 
	private String actorNm;			//배우이름  
	private int    actorId;			//배우코드  
	private String keywords;		//키워드 
	private String posters;			//포스터 주소
	private String releaseDate;		//개봉일   
	private String prodYear;		//제작년도
	private double rate;			//평점 
	private String plot;			//줄거리   
	private String regId;			//등록자ID 
	private String regDt;			//등록일   
	private String modId;			//수정자ID 
	private String modDt;			//수정일   
	private String nowShowing;      //현재상영여부
	
	// 페이징
	private int pageSize; // 페이지 사이즈: 10
	private int pageNum; // 페이지 넘버: 1
	private String searchWord; // 검색어
	
	// 기본 생성자
	public RecommendVO() {
		
	}

	// 매개변수 있는 생성자
	public RecommendVO(int recM, int recW, int recT, int recP, String dOCID, String title, String titleEng,
			String genre, String directorNm, int directorId, String nation, String actorNm, int actorId,
			String keywords, String posters, String releaseDate, String prodYear, double rate, String plot,
			String regId, String regDt, String modId, String modDt, String nowShowing, int pageSize, int pageNum,
			String searchWord) {
		super();
		this.recM = recM;
		this.recW = recW;
		this.recT = recT;
		this.recP = recP;
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
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
		this.nowShowing = nowShowing;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.searchWord = searchWord;
	}


	// getter/setter
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getRecM() {
		return recM;
	}

	public void setRecM(int recM) {
		this.recM = recM;
	}

	public int getRecW() {
		return recW;
	}

	public void setRecW(int recW) {
		this.recW = recW;
	}

	public int getRecT() {
		return recT;
	}

	public void setRecT(int recT) {
		this.recT = recT;
	}

	public int getRecP() {
		return recP;
	}

	public void setRecP(int recP) {
		this.recP = recP;
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
	
	public String getNowShowing() {
		return nowShowing;
	}

	public void setNowShowing(String nowShowing) {
		this.nowShowing = nowShowing;
	}

	// toString() 재정의
	@Override
	public String toString() {
		return "RecommendVO [recM=" + recM + ", recW=" + recW + ", recT=" + recT + ", recP=" + recP + ", DOCID=" + DOCID
				+ ", title=" + title + ", titleEng=" + titleEng + ", genre=" + genre + ", directorNm=" + directorNm
				+ ", directorId=" + directorId + ", nation=" + nation + ", actorNm=" + actorNm + ", actorId=" + actorId
				+ ", keywords=" + keywords + ", posters=" + posters + ", releaseDate=" + releaseDate + ", prodYear="
				+ prodYear + ", rate=" + rate + ", plot=" + plot + ", regId=" + regId + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", nowShowing=" + nowShowing + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", searchWord=" + searchWord + "]";
	}

	// hashCode() 재정의
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DOCID == null) ? 0 : DOCID.hashCode());
		result = prime * result + actorId;
		result = prime * result + ((actorNm == null) ? 0 : actorNm.hashCode());
		result = prime * result + directorId;
		result = prime * result + ((directorNm == null) ? 0 : directorNm.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result + ((modDt == null) ? 0 : modDt.hashCode());
		result = prime * result + ((modId == null) ? 0 : modId.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((nowShowing == null) ? 0 : nowShowing.hashCode());
		result = prime * result + pageNum;
		result = prime * result + pageSize;
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result + ((posters == null) ? 0 : posters.hashCode());
		result = prime * result + ((prodYear == null) ? 0 : prodYear.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + recM;
		result = prime * result + recP;
		result = prime * result + recT;
		result = prime * result + recW;
		result = prime * result + ((regDt == null) ? 0 : regDt.hashCode());
		result = prime * result + ((regId == null) ? 0 : regId.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((searchWord == null) ? 0 : searchWord.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((titleEng == null) ? 0 : titleEng.hashCode());
		return result;
	}

	// equals() 재정의
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecommendVO other = (RecommendVO) obj;
		if (DOCID == null) {
			if (other.DOCID != null)
				return false;
		} else if (!DOCID.equals(other.DOCID))
			return false;
		if (actorId != other.actorId)
			return false;
		if (actorNm == null) {
			if (other.actorNm != null)
				return false;
		} else if (!actorNm.equals(other.actorNm))
			return false;
		if (directorId != other.directorId)
			return false;
		if (directorNm == null) {
			if (other.directorNm != null)
				return false;
		} else if (!directorNm.equals(other.directorNm))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (modDt == null) {
			if (other.modDt != null)
				return false;
		} else if (!modDt.equals(other.modDt))
			return false;
		if (modId == null) {
			if (other.modId != null)
				return false;
		} else if (!modId.equals(other.modId))
			return false;
		if (nation == null) {
			if (other.nation != null)
				return false;
		} else if (!nation.equals(other.nation))
			return false;
		if (nowShowing == null) {
			if (other.nowShowing != null)
				return false;
		} else if (!nowShowing.equals(other.nowShowing))
			return false;
		if (pageNum != other.pageNum)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (plot == null) {
			if (other.plot != null)
				return false;
		} else if (!plot.equals(other.plot))
			return false;
		if (posters == null) {
			if (other.posters != null)
				return false;
		} else if (!posters.equals(other.posters))
			return false;
		if (prodYear == null) {
			if (other.prodYear != null)
				return false;
		} else if (!prodYear.equals(other.prodYear))
			return false;
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
			return false;
		if (recM != other.recM)
			return false;
		if (recP != other.recP)
			return false;
		if (recT != other.recT)
			return false;
		if (recW != other.recW)
			return false;
		if (regDt == null) {
			if (other.regDt != null)
				return false;
		} else if (!regDt.equals(other.regDt))
			return false;
		if (regId == null) {
			if (other.regId != null)
				return false;
		} else if (!regId.equals(other.regId))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (searchWord == null) {
			if (other.searchWord != null)
				return false;
		} else if (!searchWord.equals(other.searchWord))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (titleEng == null) {
			if (other.titleEng != null)
				return false;
		} else if (!titleEng.equals(other.titleEng))
			return false;
		return true;
	}

	
}