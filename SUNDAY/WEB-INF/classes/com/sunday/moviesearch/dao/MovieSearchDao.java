package com.sunday.moviesearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.ConnectionMaker;
import com.sunday.cmn.DTO;
import com.sunday.cmn.JDBCReturnReso;
import com.sunday.cmn.WorkDiv;
import com.sunday.movie.domain.MovieVO;
import com.sunday.movie.domain.SearchVO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.domain.MovieSearchVO;
import com.sunday.moviesearch.domain.SearchBarVO;

public class MovieSearchDao implements WorkDiv {
	
	private final Logger LOG = Logger.getLogger(MovieSearchDao.class);
	private ConnectionMaker connectionMaker;
	
	public MovieSearchDao(){
		connectionMaker = new ConnectionMaker();
	}

	@Override
	public int do_insert(DTO dto) {
		int flag=0;
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		return flag;
	}

	@Override
	public MovieDetailVO do_selectOne(DTO dto) {
		MovieSearchVO vo = (MovieSearchVO) dto;
		MovieDetailVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT docid,        							\n");
		sb.append(" 	   title,        							\n");
		sb.append(" 	   title_eng,    							\n");
		sb.append(" 	   genre,        							\n");
		sb.append(" 	   director_nm,  							\n");
		sb.append(" 	   director_id,  							\n");
		sb.append(" 	   nation,       							\n");
		sb.append(" 	   actor_nm,     							\n");
		sb.append(" 	   actor_id,    							\n");
		sb.append(" 	   keywords,    							\n");
		sb.append(" 	   posters,      							\n");
		sb.append(" 	   release_date, 							\n");
		sb.append(" 	   prod_year,    							\n");
		sb.append(" 	   rate,         							\n");
		sb.append(" 	   plot,         							\n");
		sb.append(" 	   reg_id,       							\n");
		sb.append(" 	   TO_CHAR(reg_dt, 'YYYY/MM/DD') AS reg_dt, \n");
		sb.append(" 	   mod_id,       							\n");
		sb.append(" 	   TO_CHAR(mod_dt, 'YYYY/MM/DD') AS mod_dt	\n");
		sb.append(" FROM movie          							\n");
		sb.append(" WHERE docid = ?      							  ");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vo.getDOCID());
			
			LOG.debug("3========================");
			LOG.debug("query: \n"+sb.toString());
			LOG.debug("3========================");
			
			LOG.debug("4========================");
			LOG.debug("param: "+vo.toString());
			LOG.debug("4========================");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				outVO = new MovieDetailVO();
				outVO.setDocid(rs.getString("docid"));
			}
			
		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException: "+e.toString());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return outVO;
		
	}

	@Override
	public List<MovieSearchVO> do_retrieve(DTO dto) {
		List<MovieSearchVO> list = new ArrayList<>();
		SearchBarVO vo = (SearchBarVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT T1.*, T2.*				    				\n");	  
		sb.append(" FROM(                               				\n");    	  
		sb.append(" 	SELECT b.rnum as num            				\n");
		sb.append("       	  ,b.docid                  				\n");
		sb.append(" 	  	  ,b.title                  				\n");
		sb.append(" 	  	  ,b.title_eng              				\n");
		sb.append(" 	  	  ,b.genre                  				\n");
		sb.append(" 	  	  ,b.director_nm             				\n");
	    sb.append("		  	  ,b.director_id            				\n");
	    sb.append("       	  ,b.nation                  				\n");
	    sb.append("       	  ,b.actor_nm                         		\n");
	    sb.append("       	  ,b.actor_id                           	\n");
	    sb.append("       	  ,b.keywords                           	\n");
	    sb.append("       	  ,b.posters                            	\n");
	    sb.append("       	  ,b.release_date                       	\n");
	    sb.append("       	  ,b.prod_year                          	\n");
	    sb.append("       	  ,b.rate                               	\n");
	    sb.append("       	  ,b.plot                               	\n");
	    sb.append("       	  ,b.reg_id                             	\n");
	    sb.append(" 	  	  ,TO_CHAR(b.reg_dt,'YYYY/MM/DD') reg_dt	\n");
	    sb.append("       	  ,b.mod_id                              	\n");
		sb.append(" 	  	  ,TO_CHAR(b.mod_dt,'YYYY/MM/DD') mod_dt	\n");
		sb.append(" 	FROM(                           				\n");   
		sb.append(" 		SELECT ROWNUM as rnum, a.*  				\n");    
		sb.append(" 		FROM(                      					\n");    
		sb.append(" 			SELECT *                				\n");    
		sb.append(" 			FROM movie              				\n");    
		sb.append("          	--SEARCH CONDITION      				\n");
		//-------------------------------------------------------------------
		sb.append("WHERE title like '% '||?||' %'                       \n");
		sb.append("OR title_eng like '% '||?||' %'                      \n");
		sb.append("OR director_nm like '%'||?||'%'                \n");
		//-------------------------------------------------------------------
//		sb.append(" 			AND rate IS NOT NULL 					\n");
		sb.append(" 			ORDER BY rate DESC     					\n");
		sb.append("          	,mod_dt DESC    				\n");    
		sb.append(" 		)a                          				\n");    
		sb.append(" 	WHERE ROWNUM <=(? *(? -1) + ?)  				\n");
		sb.append(" 	)b  	                        				\n");
		sb.append(" WHERE b.rnum >=(? * (? -1)+1)       				\n"); 	  
		sb.append(" )T1                                 				\n");	  
		sb.append(" CROSS JOIN(                         				\n");          	  
		sb.append(" SELECT COUNT(*) total_cnt           				\n");	  
		sb.append(" FROM movie                          				\n");	  
		sb.append(" --SEARCH CONDITION                  				\n");	 
		//-------------------------------------------------------------------
		sb.append("WHERE title like '% '||?||' %'                       \n");
		sb.append("OR title_eng like '% '||?||' %'                      \n");
		sb.append("OR director_nm like '%'||?||'%'                \n");
		//-------------------------------------------------------------------
		sb.append(" )T2                                 				  ");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param설정
			pstmt.setString(1, vo.getSearchWord());
			pstmt.setString(2, vo.getSearchWord());
			pstmt.setString(3, vo.getSearchWord());
			pstmt.setInt(4, vo.getPageSize());
			pstmt.setInt(5, vo.getPageNum());
			pstmt.setInt(6, vo.getPageSize());
			pstmt.setInt(7, vo.getPageSize());
			pstmt.setInt(8, vo.getPageNum());
			pstmt.setString(9, vo.getSearchWord());
			pstmt.setString(10, vo.getSearchWord());
			pstmt.setString(11, vo.getSearchWord());
			
			//query보기
			LOG.debug("3========================");
			LOG.debug("query: \n"+sb.toString());
			LOG.debug("3========================");
			
			//param보기
			LOG.debug("4========================");
			LOG.debug("param: "+vo.toString());
			LOG.debug("4========================");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MovieSearchVO outVO = new MovieSearchVO();
				outVO.setDOCID(rs.getString("docid"));
				outVO.setTitle(rs.getString("title"));
				outVO.setTitleEng(rs.getString("title_eng"));
				outVO.setGenre(rs.getString("genre"));
				outVO.setDirectorNm(rs.getString("director_nm"));
				outVO.setDirectorId(rs.getInt("director_id"));
				outVO.setNation(rs.getString("nation"));
				outVO.setActorNm(rs.getString("actor_nm"));
				outVO.setActorId(rs.getInt("actor_id"));
				outVO.setKeywords(rs.getString("keywords"));
				outVO.setPosters(rs.getString("posters"));
				outVO.setReleaseDate(rs.getString("release_date"));
				outVO.setProdYear(rs.getString("prod_year"));
				outVO.setRate(rs.getDouble("rate"));
				outVO.setPlot(rs.getString("plot"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setTotal(rs.getInt("total_cnt"));
				outVO.setNum(rs.getInt("num"));
				
				list.add(outVO);
			}
	
		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException: "+e.toString());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return list;
	}

}
