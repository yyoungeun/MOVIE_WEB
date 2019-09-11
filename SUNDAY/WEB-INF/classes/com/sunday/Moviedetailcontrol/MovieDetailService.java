package com.sunday.Moviedetailcontrol;

import java.util.List;

import org.apache.log4j.Logger;


import com.sunday.cmn.DTO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviedetail.dao.MovieDetailDao;



public class MovieDetailService {
	private final Logger LOG = Logger.getLogger(MovieDetailService.class);
	
	private MovieDetailDao movieDetailDao;
	
	
	public MovieDetailService(){
		movieDetailDao = new MovieDetailDao();
	}
	   public List<MovieDetailVO> do_retrieve(DTO dto) {
		   //reviewDetailDao.do_selectOne(dto);
		   return movieDetailDao.do_retrieve(dto);
	   }
	   
	   public int  do_insert(DTO dto) {
			return movieDetailDao.do_insert(dto);
		}
	   
	   public int  do_update(DTO dto) {
			return movieDetailDao.do_update(dto);
		}
	   
	   public int  do_delete(DTO dto) {
			return movieDetailDao.do_delete(dto);
		}
	   
	public MovieDetailVO do_selectOne(DTO dto) {
		return movieDetailDao.do_selectOne(dto);
	}
}
