package com.sunday.recommend;

import java.util.List;

import org.apache.log4j.Logger;

public class RecommendTest {

	// Logger 형식의 변수를 생성하고, 그 안에 클래스 이름을 지정해 클래스에서 log4j를 사용할 수 있게 함
	private final Logger LOG = Logger.getLogger(RecommendTest.class);
	
	private RecommendVO recommendVO; // RecommendVO 클래스의 참조 변수 선언
	private RecommendDao recommendDao; // RecommendDao 클래스의 참조 변수 선언
	
	
	// 기본 생성자
	
	public RecommendTest() {
		
		// RecommendVO 객체를 생성하면서 값을 넣어줌
		recommendVO = new RecommendVO(0,0,0,0, "","","","","",0,"","",0,"","","","",0,"","","","","","",0,0,"");
		
		// RecommendDao 객체 생성
		recommendDao = new RecommendDao();
	}
	
	
	/** 추천 코드에 따른 영화 검색 메소드 테스트*/
	public void do_retrieve_test() {
		
		// 객체 recommendVO의 값을 지정
		recommendVO.setRecM(1); // 기분 추천 코드
		recommendVO.setRecW(1); // 날씨 추천 코드
		
		recommendVO.setRecT(1); // 시간 추천 코드
		recommendVO.setRecP(1); // 사람 추천 코드
		
		recommendVO.setPageNum(1); // 검색 페이지
		
		// 값을 설정한 객체 recommendVO를 인자로 전달하여 함수 호출
		List<RecommendVO> list = (List<RecommendVO>) recommendDao.do_retrieve(recommendVO); 
		LOG.debug("===================");
		
		// 리스트의 모든 객체를 출력
		for(RecommendVO vo : list) {
			LOG.debug("vo\t" + vo);
		}
		
		LOG.debug("===================");	
		
	}//-do_retrieve_test()
	
	public static void main(String[] args) {
		
		RecommendTest recommendTest = new RecommendTest(); // RecommendTest의 객체를 만들고
		recommendTest.do_retrieve_test(); // 테스트 함수 호출
		

	}//--main

}//--class
