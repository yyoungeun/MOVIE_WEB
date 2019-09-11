package com.sunday.userreviewcontrol;

import java.util.List;

import org.apache.log4j.Logger;


import com.sunday.cmn.DTO;
import com.sunday.userREVIEW.ReviewDao;
import com.sunday.userREVIEW.ReviewVO;




public class UserReviewDetailService {
	private final Logger LOG = Logger.getLogger(UserReviewDetailService.class);
	
	private ReviewDao reviewDao;
	
	
		public UserReviewDetailService(){
			reviewDao = new ReviewDao();
		}
	   
	   public int  do_insert(DTO dto) {
			return reviewDao.do_insert(dto);
		}
	   
	   public int  do_update(DTO dto) {
			return reviewDao.do_update(dto);
		}
	   
	   public int  do_delete(DTO dto) {
			return reviewDao.do_delete(dto);
		}
	   
	   public List<ReviewVO> do_retrieve(DTO dto) {
		    return reviewDao.do_retrieve(dto);
		    
	   }
/*	   
 * 
 * 
	public DTO do_selectOne(DTO dto) {
		
		//단건조회
		MovieVO outVO = (MovieVO) reviewDao.do_selectOne(dto);
		
		LOG.debug("1-do_selectOne------------------");
		LOG.debug("-outVO-"+outVO);
		LOG.debug("1-do_selectOne------------------");
		return outVO;
	}
*/
	   
}
