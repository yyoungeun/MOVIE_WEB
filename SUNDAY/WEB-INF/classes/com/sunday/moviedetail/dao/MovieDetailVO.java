package com.sunday.moviedetail.dao;

import com.sunday.cmn.DTO;

public class MovieDetailVO extends DTO {
	private String  docid     ; 
	private String  posters   ; 
	private String  title     ; 
	private String  title_eng ;  
	private String  user_id   ; 
	private String  contents  ; 
	private int  user_rate    ;
	private String plot;
	private String directorNm;
	private String genre;
	private String nation;
	private String prodYear;
	private String releaseDate;
	
	public MovieDetailVO(){}

	public MovieDetailVO(String docid, String posters, String title, String title_eng, String user_id, String contents,
			int user_rate, String plot, String directorNm, String genre, String nation, String prodYear,
			String releaseDate) {
		super();
		this.docid = docid;
		this.posters = posters;
		this.title = title;
		this.title_eng = title_eng;
		this.user_id = user_id;
		this.contents = contents;
		this.user_rate = user_rate;
		this.plot = plot;
		this.directorNm = directorNm;
		this.genre = genre;
		this.nation = nation;
		this.prodYear = prodYear;
		this.releaseDate = releaseDate;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getPosters() {
		return posters;
	}

	public void setPosters(String posters) {
		this.posters = posters;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_eng() {
		return title_eng;
	}

	public void setTitle_eng(String title_eng) {
		this.title_eng = title_eng;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getUser_rate() {
		return user_rate;
	}

	public void setUser_rate(int user_rate) {
		this.user_rate = user_rate;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getDirectorNm() {
		return directorNm;
	}

	public void setDirectorNm(String directorNm) {
		this.directorNm = directorNm;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProdYear() {
		return prodYear;
	}

	public void setProdYear(String prodYear) {
		this.prodYear = prodYear;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "MovieDetailVO [docid=" + docid + ", posters=" + posters + ", title=" + title + ", title_eng="
				+ title_eng + ", user_id=" + user_id + ", contents=" + contents + ", user_rate=" + user_rate + ", plot="
				+ plot + ", directorNm=" + directorNm + ", genre=" + genre + ", nation=" + nation + ", prodYear="
				+ prodYear + ", releaseDate=" + releaseDate + ", toString()=" + super.toString() + "]";
	}

	
}
