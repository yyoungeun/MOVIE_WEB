package com.sunday.movieregister;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.DTO;

public class MovieRegisterService {
	
	// Logger 사용
	private final Logger LOG = Logger.getLogger(MovieRegisterService.class);
	// dao 객체 참조변수 선언
	private MovieRegisterDao dao;
	
	// 기본 생성자
	public MovieRegisterService() { 
		
		LOG.debug("0========================");
		dao = new MovieRegisterDao();
		LOG.debug("=dao: " + dao);
		LOG.debug("0========================");
		
	}//--MovieRegisterService
	
	/** 입력 */
	public int do_insert(DTO dto) {
		
		return dao.do_insert(dto);
	}
	
	/** 수정 */
	public int do_update(DTO dto) {
		
		return dao.do_update(dto);
	}
	
	/** 삭제 */
	public int do_delete(DTO dto) {
		
		return dao.do_delete(dto);
		
	}
	
	/** 단건 조회 */
	public DTO do_selectOne(DTO dto) {

		MovieRegisterVO outVO = (MovieRegisterVO)dao.do_selectOne(dto);
		
		LOG.debug("1. do_selectOne========================");
		LOG.debug("outVO: " + outVO);
		LOG.debug("1. do_selectOne========================");
		
		return outVO;
	}
	
	/** 전체 조회 */
	public List<?> do_retrieve(DTO dto) {
		
		return dao.do_retrieve(dto);
	}


}//--class
