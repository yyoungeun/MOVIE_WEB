package com.sunday.moviesearch.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.DTO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.domain.MovieSearchVO;


public class MovieSearchService {

	private final Logger LOG = Logger.getLogger(MovieSearchService.class);
	private MovieSearchDao movieSearchDao;
	
	public MovieSearchService(){
		movieSearchDao = new MovieSearchDao();		
	}
	
	public List<MovieSearchVO> do_retrieve(DTO dto) {
		return movieSearchDao.do_retrieve(dto);
	}
	
	public MovieDetailVO do_selectOne(DTO dto) {
		return movieSearchDao.do_selectOne(dto);
	}
}
