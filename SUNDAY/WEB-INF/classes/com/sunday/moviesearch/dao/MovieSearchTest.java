package com.sunday.moviesearch.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.movie.domain.MovieVO;
import com.sunday.movie.domain.SearchVO;
import com.sunday.moviesearch.domain.MovieSearchVO;

public class MovieSearchTest {

	private final Logger LOG = Logger.getLogger(MovieSearchTest.class);
	private MovieSearchDao dao;
	private MovieVO vo;
	
	public MovieSearchTest(){
		vo = new MovieVO("111111", "제목", "영제", "장르", "감독이름", 123456, "국가", "배우이름", 987654, "키워드", "포스터주소", "개봉일", "제작년도", 3.0, "줄거리", "현재상영중", "등록자id", "등록일", "수정자id", "수정일");
		dao = new MovieSearchDao();
	}

	public void do_retrieve(){
		SearchVO searchVO = new SearchVO();
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("20");
		searchVO.setSearchWord("김혁철");
		List<MovieSearchVO> list = dao.do_retrieve(searchVO);
		LOG.debug("---------------list---------------");
		for(int i=0; i<list.size(); i++){
			LOG.debug(list.get(i)+"\n");
		}
		LOG.debug("----------------------------------");
	}
	
	public static void main(String[] args) {
		MovieSearchTest test = new MovieSearchTest();
		test.do_retrieve();
	}

}
