package com.sunday.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.chart.MoviePerYearVO;
import com.sunday.cmn.ConnectionMaker;
import com.sunday.cmn.DTO;
import com.sunday.cmn.JDBCReturnReso;
import com.sunday.cmn.StringUtil;
import com.sunday.cmn.WorkDiv;
import com.sunday.movie.domain.MovieVO;
import com.sunday.movie.domain.SearchVO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.domain.MovieSearchVO;
import com.sunday.moviesearch.domain.SearchBarVO;

public class MovieDao implements WorkDiv {
	
	private final Logger LOG = Logger.getLogger(MovieDao.class);
	private ConnectionMaker connectionMaker;
	
	public MovieDao(){
		connectionMaker = new ConnectionMaker();
	}

	public List<MoviePerYearVO> do_moviePerYear(DTO dto) {
		MoviePerYearVO vo = (MoviePerYearVO) dto;
		List<MoviePerYearVO> list = new ArrayList<>(); 
//		MovieDetailVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT prod_year-substr(prod_year,4) as year, count(*) as movie_per_year \n");
		sb.append(" FROM movie                                                      \n");
		sb.append(" WHERE prod_year-substr(prod_year,4) >= ?                        \n");
		sb.append(" GROUP BY prod_year-substr(prod_year,4)                          \n");
		sb.append(" ORDER BY year                                                   \n");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, 1970);
			
			LOG.debug("3========================");
			LOG.debug("query: \n"+sb.toString());
			LOG.debug("3========================");
			
			LOG.debug("4========================");
			LOG.debug("param: "+vo.toString());
			LOG.debug("4========================");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){				
				MoviePerYearVO outVO = new MoviePerYearVO();
				outVO.setYear(rs.getString("year"));
				outVO.setMoviePerYear(rs.getInt("movie_per_year"));
				
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
	
	public List<MovieSearchVO> do_search(DTO dto) {
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
	    sb.append("           ,CASE                                     \n");
	    sb.append("            		WHEN b.posters LIKE '%|%' THEN substr(b.posters, 1, instr(b.posters, '|')-1) \n");
	    sb.append("            		ELSE b.posters                      \n");
	    sb.append("            END as posters                           \n");
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
		//sb.append(" 			AND rate IS NOT NULL 					\n");
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
	
	@Override
	public int do_insert(DTO dto) {
		int flag = 0;
		MovieVO vo = (MovieVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//query
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO movie ( 					\n");
		sb.append("     docid,          					\n");
		sb.append("     title,          					\n");
		sb.append("     title_eng,      					\n");
		sb.append("     genre,          					\n");
		sb.append("     director_nm,    					\n");
		sb.append("     director_id,    					\n");
		sb.append("     nation,         					\n");
		sb.append("     actor_nm,       					\n");
		sb.append("     actor_id,       					\n");
		sb.append("     keywords,       					\n");
		sb.append("     posters,        					\n");
		sb.append("     release_date,   					\n");
		sb.append("     prod_year,      					\n");
		sb.append("     rate,           					\n");
		sb.append("     plot,           					\n");
		sb.append("     reg_id,         					\n");
		sb.append("     reg_dt,         					\n");
		sb.append("     mod_id,         					\n");
		sb.append("     mod_dt          					\n");
		sb.append(" ) VALUES (          					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     ?,              					\n");
		sb.append("     TO_CHAR(SYSDATE, 'YYYY/MM/DD'),     \n");
		sb.append("     ?,              					\n");
		sb.append("     TO_CHAR(SYSDATE, 'YYYY/MM/DD')      \n");
		sb.append(" )                   					  ");
		
		try{
			//커넥션생성
			conn = connectionMaker.getConnection();
			
			//query
			pstmt = conn.prepareStatement(sb.toString());
			
			//query보기
			LOG.debug("3========================");
			LOG.debug("query: \n"+sb.toString());
			LOG.debug("3========================");
			
			//param설정
			pstmt.setString(1, vo.getDOCID());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getTitleEng());
			pstmt.setString(4, vo.getGenre());
			pstmt.setString(5, vo.getDirectorNm());
			pstmt.setInt(6, vo.getDirectorId());
			pstmt.setString(7, vo.getNation());
			pstmt.setString(8, vo.getActorNm());
			pstmt.setInt(9, vo.getActorId());
			pstmt.setString(10, vo.getKeywords());
			pstmt.setString(11, vo.getPosters());
			pstmt.setString(12, vo.getReleaseDate());
			pstmt.setString(13, vo.getProdYear());
			pstmt.setDouble(14, vo.getRate());
			pstmt.setString(15, vo.getPlot());
			pstmt.setString(16, vo.getRegId());
			pstmt.setString(17, vo.getModId());
			
			//param보기
			LOG.debug("4========================");
			LOG.debug("param: "+vo.toString());
			LOG.debug("4========================");
			
			//query실행
			flag = pstmt.executeUpdate();
			
			LOG.debug("5========================");
			LOG.debug("flag:"+flag);
			LOG.debug("5========================");
			
		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException: "+e.toString());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		MovieVO vo = (MovieVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE movie         \n");
		sb.append(" SET title = ?,       \n");
		sb.append("     title_eng = ?,   \n");
		sb.append("     genre = ?,       \n");
		sb.append("     director_nm = ?, \n");
		sb.append("     director_id = ?, \n");
		sb.append("     nation = ?,      \n");
		sb.append("     actor_nm = ?,    \n");
		sb.append("     actor_id = ?,    \n");
		sb.append("     keywords = ?,    \n");
		sb.append("     posters = ?,     \n");
		sb.append("     release_date = ?,\n");
		sb.append("     prod_year = ?,   \n");
		sb.append("     rate = ?,        \n");
		sb.append("     plot = ?,        \n");
		sb.append("     mod_id = ?,      \n");
		sb.append("     mod_dt = SYSDATE \n");
		sb.append(" WHERE docid = ?        ");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			LOG.debug("3========================");
			LOG.debug("query: \n"+sb.toString());
			LOG.debug("3========================");
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getTitleEng());
			pstmt.setString(3, vo.getGenre());
			pstmt.setString(4, vo.getDirectorNm());
			pstmt.setInt(5, vo.getDirectorId());
			pstmt.setString(6, vo.getNation());
			pstmt.setString(7, vo.getActorNm());
			pstmt.setInt(8, vo.getActorId());
			pstmt.setString(9, vo.getKeywords());
			pstmt.setString(10, vo.getPosters());
			pstmt.setString(11, vo.getReleaseDate());
			pstmt.setString(12, vo.getProdYear());
			pstmt.setDouble(13, vo.getRate());
			pstmt.setString(14, vo.getPlot());
			pstmt.setString(15, vo.getModId());
			pstmt.setString(16, vo.getDOCID());
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("4========================");
			LOG.debug("flag:"+flag);
			LOG.debug("4========================");
			
		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException: "+e.toString());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		MovieVO vo = (MovieVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM movie \n");
		sb.append(" WHERE docid = ?     ");
		
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
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("5========================");
			LOG.debug("flag: "+flag);
			LOG.debug("5========================");

		}catch(SQLException e){
			LOG.debug("========================");
			LOG.debug("SQLException: "+e.toString());
			LOG.debug("========================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag;
	}

	@Override
	public MovieDetailVO do_selectOne(DTO dto) {
		MovieVO vo = (MovieVO) dto;
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
		sb.append("        now_showing,                             \n");
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
	public List<MovieVO> do_retrieve(DTO dto) {
		List<MovieVO> list = new ArrayList<>();
		SearchVO vo = (SearchVO) dto;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/*검색조건 설정 
		 *상영중	= 10
		 *장르	= 20
		 *국가	= 30
		 *연도	= 40
		 *최신순	= 50
		*/

		StringBuilder sbWhere = new StringBuilder();
		
		switch(vo.getSearchDiv()){
			case "10":
				sbWhere.append("WHERE now_showing IS NOT NULL	\n");
				sbWhere.append("AND now_showing = 'Y'           \n");
				sbWhere.append("ORDER BY release_date DESC		");
			break;
			
			case "20":
				sbWhere.append("WHERE genre IS NOT NULL		\n");
				sbWhere.append("AND genre LIKE '%'||?||'%'	\n");
				//sbWhere.append("AND rate IS NOT NULL		\n");
				//sbWhere.append("ORDER BY rate DESC		\n");
			break;
				
			case "30":
				sbWhere.append("WHERE nation IS NOT NULL	\n");
				sbWhere.append("AND nation LIKE '%'||?||'%'	\n");
				sbWhere.append("AND rate IS NOT NULL		\n");
				//sbWhere.append("ORDER BY rate DESC		\n");
			break;
			
			case "40":
				sbWhere.append("WHERE prod_year IS NOT NULL	\n");
				sbWhere.append("AND prod_year BETWEEN to_number(?) AND to_number(?)+10 \n");
				sbWhere.append("AND rate IS NOT NULL		\n");
				//sbWhere.append("ORDER BY rate DESC		\n");
			break;
			
			case "50":
				sbWhere.append("WHERE mod_dt IS NOT NULL \n");
				sbWhere.append("ORDER BY mod_dt DESC");
			break;
			
			case "60":
				sbWhere.append("WHERE nation IS NOT NULL	\n");
				sbWhere.append("AND NOT nation LIKE '%'||?||'%'  \n");
				sbWhere.append("AND rate IS NOT NULL		\n");
				//sbWhere.append("ORDER BY rate DESC		\n");
			break;
		}
		
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
	    sb.append("           ,CASE                                     \n");
	    sb.append("            WHEN b.posters LIKE '%|%' THEN substr(b.posters, 1, instr(b.posters, '|')-1) \n");
	    sb.append("            ELSE b.posters                           \n");
	    sb.append("            END as posters                           \n");
	    sb.append("       	  ,b.release_date                       	\n");
	    sb.append("       	  ,b.prod_year                          	\n");
	    sb.append("       	  ,b.rate                               	\n");
	    sb.append("       	  ,b.plot                               	\n");
	    sb.append("           ,b.now_showing                            \n");
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
		sb.append(sbWhere.toString());
		//-------------------------------------------------------------------
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
		sb.append(sbWhere.toString());
		//-------------------------------------------------------------------
		sb.append(" )T2                                 				  ");
		
		    
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			//param설정
			switch(vo.getSearchDiv()){
				case "10": case "50":
					pstmt.setInt(1, vo.getPageSize());
					pstmt.setInt(2, vo.getPageNum());
					pstmt.setInt(3, vo.getPageSize());
					pstmt.setInt(4, vo.getPageSize());
					pstmt.setInt(5, vo.getPageNum());
				break;
				
				case "20": case "30": case "60":
					pstmt.setString(1, vo.getSearchWord());
					pstmt.setInt(2, vo.getPageSize());
					pstmt.setInt(3, vo.getPageNum());
					pstmt.setInt(4, vo.getPageSize());
					pstmt.setInt(5, vo.getPageSize());
					pstmt.setInt(6, vo.getPageNum());
					pstmt.setString(7, vo.getSearchWord());
				break;
				
				case "40":
					pstmt.setString(1, vo.getSearchWord());
					pstmt.setString(2, vo.getSearchWord());
					pstmt.setInt(3, vo.getPageSize());
					pstmt.setInt(4, vo.getPageNum());
					pstmt.setInt(5, vo.getPageSize());
					pstmt.setInt(6, vo.getPageSize());
					pstmt.setInt(7, vo.getPageNum());
					pstmt.setString(8, vo.getSearchWord());
					pstmt.setString(9, vo.getSearchWord());
				break;
			}
			
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
				MovieVO outVO = new MovieVO();
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
				outVO.setNowShowing(rs.getString("now_showing"));
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
