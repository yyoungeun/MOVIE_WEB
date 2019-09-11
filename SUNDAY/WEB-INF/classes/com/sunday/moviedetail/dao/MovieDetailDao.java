package com.sunday.moviedetail.dao;

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






public class MovieDetailDao implements WorkDiv {
	
	private final Logger LOG = Logger.getLogger(MovieDetailDao.class);
	private ConnectionMaker  connectionMaker;
	
	public MovieDetailDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public int do_insert(DTO dto) {
		MovieDetailVO vo =(MovieDetailVO) dto;
		int flag  = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			StringBuilder sb=new StringBuilder();
			sb.append("INSERT INTO user_review (\n");
			sb.append("    docid,               \n");
			sb.append("    user_id,             \n");
			sb.append("    contents,            \n");
			sb.append("    user_rate,           \n");
			sb.append("    reg_id,              \n");
			sb.append("    reg_dt,              \n");
			sb.append("    mod_id,              \n");
			sb.append("    mod_dt               \n");
			sb.append(") VALUES (               \n");
			sb.append("    	?,                 \n");
			sb.append("    	?,                 \n");
			sb.append("    	?,                 \n");
			sb.append("    	?,                 \n");
			sb.append("    	?,                 \n");
			sb.append("    	SYSDATE,            \n");
			sb.append("    	?,                 \n");
			sb.append("     SYSDATE             \n");
			sb.append(")                        \n");
			
			LOG.debug("1.============================");
			LOG.debug("1.query\n"+sb.toString());
			LOG.debug("1.============================");
			
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDocid());
			pstmt.setString(2, vo.getUser_id());
			pstmt.setString(3, vo.getContents());
			pstmt.setInt(4, vo.getUser_rate());
			pstmt.setString(5, vo.getUser_id());
			pstmt.setString(6, vo.getUser_id());
			
			LOG.debug("2.============================");
			LOG.debug("2.param:"+vo.toString());
			LOG.debug("2.============================");			
			
			flag = pstmt.executeUpdate();
			
			LOG.debug("3.============================");
			LOG.debug("3.flag:"+flag);
			LOG.debug("3.============================");	
		}catch(SQLException e){
			LOG.debug("======================");
			LOG.debug("SQLException:"+e.toString());
			LOG.debug("======================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		
		return flag;
	}
	//수정제외

	@Override

	public int do_update(DTO dto) {
		MovieDetailVO vo =(MovieDetailVO) dto;
		int flag =0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE user_review       \n");
			sb.append("SET                      \n");
			sb.append("     contents  = ?,       \n");
			sb.append("     user_rate = ?,       \n");
			sb.append("     reg_id    = ?,       \n");
			sb.append("     reg_dt    = SYSDATE, \n");
			sb.append("     mod_id    = ?,       \n");
			sb.append("     mod_dt    = SYSDATE \n");
			sb.append(" WHERE docid   = ?		\n");
			sb.append(" AND user_id   = ?         ");
			
			conn = connectionMaker.getConnection();
			LOG.debug("===================");
			LOG.debug("1.query\n="+sb.toString());
			LOG.debug("===================");
			
			pstmt = conn.prepareStatement(sb.toString());
			//pstmt.setString(1,vo.getUser_id());
			pstmt.setString(1,vo.getContents());
			pstmt.setInt(2,vo.getUser_rate());
			pstmt.setString(3,vo.getUser_id());
			pstmt.setString(4,vo.getUser_id());
			pstmt.setString(5,vo.getDocid());
			pstmt.setString(6, vo.getUser_id());
			
			LOG.debug("===================");
			LOG.debug("2.param="+vo);
			LOG.debug("===================");
			
			flag = pstmt.executeUpdate();
			LOG.debug("3.======================");
			LOG.debug("3.flag :"+flag);
			LOG.debug("3.======================");
			
			
		}catch(SQLException s){
			LOG.debug("================");
			LOG.debug("SQLException="+s.toString());
			LOG.debug("================");
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);		
		}
		
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		MovieDetailVO vo =(MovieDetailVO) dto;
		int flag =0;
		Connection conn =null;
		
		PreparedStatement pstmt =null;
		
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM user_review \n");
		sb.append(" WHERE docid= ?      \n");
		sb.append(" AND user_id= ?      \n");
		
		try {
			conn = connectionMaker.getConnection();
			//transaction개발자가 관리한다.
			conn.setAutoCommit(false);
			
			//LOG.debug("1======================");
			//LOG.debug("query:\n"+sb.toString());
			//LOG.debug("1======================");
			
			pstmt = conn.prepareStatement(sb.toString());
			//query param
			pstmt.setString(1, vo.getDocid());
			pstmt.setString(2, vo.getUser_id());
			
			LOG.debug("2======================");
			LOG.debug("param, seq="+vo.getUser_id());
			LOG.debug("2======================");	
			
			flag = pstmt.executeUpdate();
			//-transaction
			if(flag>0){
				LOG.debug("3======================");
				LOG.debug("param, seq="+vo.getUser_id());
				LOG.debug("3======================");	
				
				conn.commit();
			}else{
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);						
	    }
		
		LOG.debug("3=====================");
		LOG.debug("flag:"+flag);
		LOG.debug("3=====================");
		
		
		return flag;
	}

	@Override
	public MovieDetailVO do_selectOne(DTO dto) {
		MovieDetailVO vo = (MovieDetailVO) dto;
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
		sb.append("        CASE                                     \n");
	    sb.append("        WHEN posters LIKE '%|%' THEN substr(posters, 1, instr(posters, '|')-1) \n");
	    sb.append("        ELSE posters                           \n");
	    sb.append("        END as posters,                           \n");
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

			pstmt.setString(1, vo.getDocid());
			
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
				outVO.setTitle(rs.getString("title"));
				outVO.setGenre(rs.getString("genre"));
				outVO.setDirectorNm(rs.getString("director_nm"));
				outVO.setNation(rs.getString("nation"));
				outVO.setPosters(rs.getString("posters"));
				outVO.setReleaseDate(rs.getString("release_date"));
				outVO.setProdYear(rs.getString("prod_year"));
				outVO.setPlot(rs.getString("plot"));
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

	public List<MovieDetailVO> do_retrieve(DTO dto) {
		
		List<MovieDetailVO> list =new ArrayList<>();
		SearchVO vo = (SearchVO) dto;
		LOG.debug(vo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT T1.*, T2.*				    	 \n");	  
		sb.append(" FROM(                               	 \n");    	  
		sb.append("SELECT B.rnum as num,                     \n");
		sb.append("       B.docid,                           \n");
		sb.append("       B.posters,                         \n");
		sb.append("       B.title,                           \n");
		sb.append("       B.title_eng,                       \n");
		sb.append("       B.user_id,                         \n");
		sb.append("       B.contents,                        \n");
		sb.append("       B.user_rate                        \n");
		sb.append("FROM(                                     \n");
		sb.append("   SELECT ROWNUM AS rnum,A.*              \n");
		sb.append("   FROM(                                  \n");
		sb.append("SELECT                                   \n");
		sb.append("               a.docid,                  \n");  
		sb.append("               a.posters,                \n");  
		sb.append("               a.title,                  \n");  
		sb.append("               a.title_eng,              \n");  
		sb.append("               b.user_id,                \n");  
		sb.append("               b.contents,               \n");  
		sb.append("               b.user_rate,              \n");  
		sb.append("               b.mod_dt                  \n");
		sb.append("        FROM movie a JOIN user_review b  \n");  
		sb.append("        ON a.docid = b.docid             \n");  
		sb.append("		WHERE a.docid = ?         		    \n");
		sb.append("     ORDER BY b.mod_dt DESC              \n");
	    sb.append("   )A                                    \n");
		sb.append("    WHERE ROWNUM <=(?*(?-1)+?)  )B       \n");
		sb.append("WHERE B.rnum>= (?*(?-1)+1)               \n");
		sb.append(" )T1                                 	\n");	  
		sb.append(" CROSS JOIN(                         	\n");          	  
		sb.append(" SELECT COUNT(*) total_cnt           	\n");		 
		sb.append("        FROM movie a JOIN user_review b  \n");  
		sb.append("        ON a.docid = b.docid             \n");  
		sb.append("		WHERE a.docid = ?         		    \n");
		sb.append(" )T2                                 	  ");
		
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			
			
			pstmt.setString(1, vo.getSearchWord());
			pstmt.setInt(2, vo.getPAGE_SIZE());
			pstmt.setInt(3, vo.getPageNum());
			pstmt.setInt(4, vo.getPAGE_SIZE());
			pstmt.setInt(5, vo.getPAGE_SIZE());
			pstmt.setInt(6, vo.getPageNum());
			pstmt.setString(7, vo.getSearchWord());
			
			LOG.debug(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				MovieDetailVO outVO =new MovieDetailVO();
		
				outVO.setDocid(rs.getString("docid"));
				outVO.setPosters(rs.getString("posters"));
				outVO.setTitle(rs.getString("title"));
				outVO.setTitle_eng(rs.getString("title_eng"));
				outVO.setUser_id(rs.getString("user_id"));
				outVO.setContents(rs.getString("contents"));
				outVO.setUser_rate(rs.getInt("user_rate"));
									
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
