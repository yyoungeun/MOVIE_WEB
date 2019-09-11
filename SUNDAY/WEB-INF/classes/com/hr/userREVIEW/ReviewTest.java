package com.hr.userREVIEW;

import java.util.List;

import org.apache.log4j.Logger;





public class ReviewTest {
	
	
	private final Logger LOG = Logger.getLogger(ReviewTest.class);
	
	
	private ReviewVO vo01;
	
	private ReviewDao dao;
	
	public ReviewTest() {
		vo01 = new ReviewVO("F43306","zpk","",5,"zpk","","","");
		
		dao=new ReviewDao();
	}

	public void do_update(){
		//vo01.setDocid("F43306");
		//vo01.setUser_id("update");
		vo01.setContents("update_contents");
		vo01.setUser_rate(5);
		vo01.setReg_id("reg_id_update");
		vo01.setMod_id("mod_id_update");
		int flag= dao.do_update(vo01);
	}
	
	public void do_delect(){
		vo01.setDocid("F43306");
		int flag = dao.do_delete(vo01);
		LOG.debug("==================================");
		LOG.debug("flag="+flag);
		LOG.debug("==================================");
	}
	public void do_retrieve(){
		SearchVO searchVO= new SearchVO();
		searchVO.setPageNum(1);
		searchVO.setSearchWord("F43306");
		
		//List<MovieDetailVO> list=dao.do_retrieve(searchVO);
		//LOG.debug(list);
		
	}

	public void do_insert(){
		int flag =dao.do_insert(vo01);
	}
	public static void main(String[] args) {
		ReviewTest reviewTest = new ReviewTest(); 
				
		//reviewTest.do_insert();
		//reviewTest.do_delect();
		//reviewTest.do_update();
		reviewTest.do_retrieve();
	}

}
