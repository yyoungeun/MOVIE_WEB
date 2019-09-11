package com.sunday.moviedetail.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.movie.domain.MovieVO;
import com.sunday.userREVIEW.ReviewDao;

//import com.hr.userREVIEW.ReviewDao;



public class MovieDetailTest {
   
   
   private final Logger LOG = Logger.getLogger(MovieDetailTest.class);
   
   
   private MovieDetailVO vo01;
   
   private MovieDetailDao dao;
   
   public MovieDetailTest() {
      vo01 = new MovieDetailVO("F43306","posters","title","title_eng","user_id","contents",3,"plot","directornm","genre", "nation","1999","1999/12/12");
      
      dao=new MovieDetailDao();
   }

   public void do_update(){
      //vo01.setDocid("F43306");
      //vo01.setUser_id("update");
      vo01.setContents("update_contents");
      vo01.setUser_rate(5);
      int flag= dao.do_update(vo01);
   }
   
   public void do_delect(){
      vo01.setDocid("F43280");
      int flag = dao.do_delete(vo01);
      LOG.debug("==================================");
      LOG.debug("flag="+flag);
      LOG.debug("==================================");
   }
   public void do_retrieve(){
     SearchVO searchVO= new SearchVO();
     	searchVO.setSearchWord("F43306");
     // searchVO.setPageNum(1);
     // vo01.setDocid("F43306");
      List<MovieDetailVO> list=dao.do_retrieve(searchVO);
      LOG.debug(list);
      
   }

   public void do_insert(){
      int flag =dao.do_insert(vo01);
      
   }
   
   public void do_selectOne(){
		vo01.setDocid("F43306");
		MovieDetailVO outVO=(MovieDetailVO) dao.do_selectOne(vo01);
		LOG.debug("======================");
		LOG.debug("outVO="+outVO);
		LOG.debug("======================");
		
	}
   public static void main(String[] args) {
      MovieDetailTest reviewTest = new MovieDetailTest(); 
            
      reviewTest.do_insert();
     // reviewTest.do_delect();
      //reviewTest.do_update();
      //reviewTest.do_retrieve();
      //reviewTest.do_selectOne();
   }

}