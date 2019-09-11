package com.hr.userREVIEW;

import com.sunday.cmn.DTO;

public class MovieDetailVO extends DTO {
	private String  docid     ; 
	private String  posters   ; 
	private String  title     ; 
	private String  title_eng ;  
	private String  user_id   ; 
	private String  contents  ; 
	private int  user_rate    ; 
	
	public MovieDetailVO(){}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((docid == null) ? 0 : docid.hashCode());
		result = prime * result + ((posters == null) ? 0 : posters.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((title_eng == null) ? 0 : title_eng.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result + user_rate;
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
		MovieDetailVO other = (MovieDetailVO) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (docid == null) {
			if (other.docid != null)
				return false;
		} else if (!docid.equals(other.docid))
			return false;
		if (posters == null) {
			if (other.posters != null)
				return false;
		} else if (!posters.equals(other.posters))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (title_eng == null) {
			if (other.title_eng != null)
				return false;
		} else if (!title_eng.equals(other.title_eng))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_rate != other.user_rate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovieDetailVO [docid=" + docid + ", posters=" + posters + ", title=" + title + ", title_eng="
				+ title_eng + ", user_id=" + user_id + ", contents=" + contents + ", user_rate=" + user_rate + "]";
	}

	public MovieDetailVO(String docid, String posters, String title, String title_eng, String user_id, String contents,
			int user_rate) {
		super();
		this.docid = docid;
		this.posters = posters;
		this.title = title;
		this.title_eng = title_eng;
		this.user_id = user_id;
		this.contents = contents;
		this.user_rate = user_rate;
	}
	
	
}
