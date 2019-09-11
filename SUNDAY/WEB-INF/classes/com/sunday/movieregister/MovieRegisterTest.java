package com.sunday.movieregister;

import java.util.List;

import org.apache.log4j.Logger;

public class MovieRegisterTest {
	
	// Logger 형식의 변수를 생성하고, 그 안에 클래스 이름을 지정해 클래스에서 log4j를 사용할 수 있게 함
	private final Logger LOG = Logger.getLogger(MovieRegisterTest.class);
	
	// MovieDetailVO 클래스의 참조 변수 선언
	private MovieRegisterVO vo01;
	
	// MovieDetailDao 클래스의 참조 변수 선언
	private MovieRegisterDao dao;
	
	/** 기본 생성자 */
	public MovieRegisterTest() {
		
		// MovieDetailVO 객체를 생성하면서 값을 넣어줌
		vo01 = new MovieRegisterVO(1, 1, 1, 1, "2", "제목", "영제", "장르", "감독이름", 1, "국가", "배우이름", 1, 
								   "키워드", "포스터", "개봉일", "제작년도", 1, "줄거리", "admin", "admin", "유");
		
		// MovieDetailDao 객체 생성
		dao = new MovieRegisterDao();
		
	}//--MovieDetailTest()
	
	/**0. do_upsert() 메소드 호출 
	public void do_upsert_test() {
		
		dao.do_upsert(vo01);
		
	}//--do_upsert_test() */
	
	/**1. do_insert() 메소드 호출  */
	public void do_insert_test() {
		
		dao.do_insert(vo01);
		
	}//--do_insert_test()
	
	/**2. do_update() 메소드 호출  */
	public void do_update_test() {
		
		vo01.setTitle("수정");
		dao.do_update(vo01);
		
	}//--do_update_test()
	
	/**3. do_delete 메소드 호출 */
	public void do_delete_test() {
		
		//vo01.setDOCID("1"); // docid의 값을 1로 set -> 삭제할 데이터의 등록번호
	
		int flag = dao.do_delete(vo01); // 삭제할 데이터를 담은 객체를 인자로 전달하면서 삭제 함수 호출
		
	}//--do_delete()
	
	/**4. 전체 조회 메소드: 10개씩 잘라서 반환하고, 최근 등록일 순으로 정렬*/
	public void do_retrieve_test() {
		
		SearchVO searchVO = new SearchVO(); // SearchVO 객체 생성
		// 객체의 값을 지정
		searchVO.setPageNum(1); // 검색 페이지
		
		List<MovieRegisterVO> list = (List<MovieRegisterVO>) dao.do_retrieve(searchVO); // 값을 설정한 SearchVO의 객체를 인자로 전달하여 함수 호출
		LOG.debug("===================");
		
		for(MovieRegisterVO vo : list) {
			LOG.debug(vo);
		}
		
		LOG.debug("===================");	
		
	}//--do_retrieve_test()
	
	/**5. do_selectOne 메소드 호출 */
	public void do_selectOne_test() {
		
		vo01.setDOCID("2"); // docid의 값을 2로 set -> 조회할 데이터의 등록번호
		// 객체 vo01을 인자로 전달하면서  단건 조회 메소드 호출  -> BoardVO형 변수에 넣음
		MovieRegisterVO outVO = (MovieRegisterVO)dao.do_selectOne(vo01); 
		LOG.debug("=======================");
		LOG.debug("outVO=" + outVO); // 반환된 검색 객체를 출력
		LOG.debug("=======================");
		
	}//--do_selectOne_test()
	
	/** 메인 메소드 */
	public static void main(String[] args) {
		
		MovieRegisterTest movieDetailTest = new MovieRegisterTest(); 
		//movieDetailTest.do_upsert_test();
		//movieDetailTest.do_insert_test(); // 입력
		//movieDetailTest.do_update_test(); // 수정
		//movieDetailTest.do_delete_test(); // 삭제
		//movieDetailTest.do_retrieve_test(); // 전체 조회
		movieDetailTest.do_selectOne_test(); // 단건 조회
		
	}//--main
	
}//--class