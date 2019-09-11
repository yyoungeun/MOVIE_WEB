package com.sunday.movie.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.movie.domain.MovieVO;
import com.sunday.movie.domain.SearchVO;
import com.sunday.moviedetail.dao.MovieDetailVO;

public class MovieTest {

	private final Logger LOG = Logger.getLogger(MovieTest.class);
	private MovieDao dao;
	private MovieVO vo;
	
	public MovieTest(){
		vo = new MovieVO("111111", "제목", "영제", "장르", "감독이름", 123456, "국가", "배우이름", 987654, "키워드", "포스터주소", "개봉일", "제작년도", 3.0, "줄거리", "현재상영중", "등록자id", "등록일", "수정자id", "수정일");
		dao = new MovieDao();
	}
	
	public void do_insert(){
		int flag = dao.do_insert(vo);
		if(flag==1){
			LOG.debug("등록성공");
		}else{
			LOG.debug("등록실패");
		}
	}
	
	public void do_update(){
		vo.setTitle("제목u");
		vo.setTitleEng("영제u");
		vo.setGenre("장르u");
		vo.setDirectorNm("감독이름u");
		vo.setDirectorId(121212);
		vo.setNation("국가u");
		vo.setActorNm("배우이름u");
		vo.setActorId(999999);
		vo.setKeywords("키워드u");
		vo.setPosters("포스터주소u");
		vo.setReleaseDate("개봉일u");
		vo.setProdYear("제작년도u");
		vo.setRate(5.0);
		vo.setPlot("줄거리u");
		vo.setModId("수정자idu");
		int flag = dao.do_update(vo);
		if(flag==1){
			LOG.debug("수정성공");
		}else{
			LOG.debug("수정실패");
		}
	}
	
	public void do_delete(){
		int flag = dao.do_delete(vo);
		if(flag==1){
			LOG.debug("삭제성공");
		}else{
			LOG.debug("삭제실패");
		}
	}
	
	public void do_selectOne(){
		MovieDetailVO outVO = dao.do_selectOne(vo);
		LOG.debug("===========================");
		LOG.debug(outVO.toString());
		LOG.debug("===========================");
	}
	
	public void do_retrieve(){
		SearchVO searchVO = new SearchVO();
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("10");
		//searchVO.setSearchWord("");
		List<MovieVO> list = dao.do_retrieve(searchVO);
		LOG.debug("---------------list---------------");
		for(int i=0; i<list.size(); i++){
			LOG.debug(list.get(i)+"\n");
		}
		LOG.debug("----------------------------------");
	}
	
	public static void main(String[] args) {
		MovieTest test = new MovieTest();
		//test.do_insert();
		//test.do_update();
		//test.do_delete();
		//test.do_selectOne();
		test.do_retrieve();
		
	}

}
