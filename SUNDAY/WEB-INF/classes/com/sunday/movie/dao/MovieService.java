package com.sunday.movie.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.chart.MoviePerYearVO;
import com.sunday.cmn.DTO;
import com.sunday.movie.domain.MovieVO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.domain.MovieSearchVO;

public class MovieService {

	private final Logger LOG = Logger.getLogger(MovieService.class);
	private MovieDao movieDao;
	
	public MovieService(){
		movieDao = new MovieDao();
	}
	
	public List<MoviePerYearVO> do_moviePerYear(DTO dto) {
		return movieDao.do_moviePerYear(dto);
	}
	
	public int do_insert(DTO dto){
		return movieDao.do_insert(dto);
	}
	
	public int do_delete(DTO dto){
		return movieDao.do_delete(dto);
	}
	
	public int do_update(DTO dto){
		return movieDao.do_update(dto);
	}
	
	public MovieDetailVO do_selectOne(DTO dto){
		return movieDao.do_selectOne(dto);
	}
	
	public List<MovieVO> do_retrieve(DTO dto){
		return movieDao.do_retrieve(dto);
	}
	
	public List<MovieSearchVO> do_search(DTO dto){
		return movieDao.do_search(dto);
	}
}
