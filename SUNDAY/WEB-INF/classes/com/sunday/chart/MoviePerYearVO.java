package com.sunday.chart;

import com.sunday.cmn.DTO;

public class MoviePerYearVO extends DTO {

	private String year;
	private int moviePerYear;
	
	public MoviePerYearVO(){}

	public MoviePerYearVO(String year, int moviePerYear) {
		super();
		this.year = year;
		this.moviePerYear = moviePerYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMoviePerYear() {
		return moviePerYear;
	}

	public void setMoviePerYear(int moviePerYear) {
		this.moviePerYear = moviePerYear;
	}

	@Override
	public String toString() {
		return "MoviePerYearVO [year=" + year + ", moviePerYear=" + moviePerYear + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}