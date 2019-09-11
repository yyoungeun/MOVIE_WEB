package com.sunday.recommend;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.DTO;

public class RecommendService {
	
	private final Logger LOG = Logger.getLogger(RecommendService.class);
	private RecommendDao dao;
	
	// 기본 생성자
	public RecommendService() {
		
		LOG.debug("0========================");
		dao = new RecommendDao();
		LOG.debug("=dao: " + dao);
		LOG.debug("0========================");
		
	}//--RecommendService()
	
	/** 추천 코드에 맞는 영화 조회 */
	public List<?> do_retrieve(DTO dto) {
		
		return dao.do_retrieve(dto); // Dao의  do_retrieve() 메소드 호출
		
	}//--do_retrieve()
	

}//--class
