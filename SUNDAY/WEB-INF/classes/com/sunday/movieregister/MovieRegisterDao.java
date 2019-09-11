package com.sunday.movieregister;

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

public class MovieRegisterDao implements WorkDiv {
	
	// Logger 형식의 변수를 생성하고, 그 안에 클래스 이름을 지정해 클래스에서 log4j를 사용할 수 있게 함
	private final Logger LOG = Logger.getLogger(MovieRegisterDao.class);
	
	// ConnectionMaker 클래스의 참조 변수 선언
	private ConnectionMaker connectionMaker;
	
	/**기본 생성자*/
	public MovieRegisterDao() {
		// ConnectionMaker 클래스의 객체 생성해서 참조 변수에 저장
		connectionMaker = new ConnectionMaker();
		
	}//--MovieDetailDao()
	
	/**0. MERGE를 활용한 입력 및 갱신 메소드 
	public int do_upsert(DTO dto) { // 입력 혹은 갱신할 데이터를 객체로 받음
		
		MovieRegisterVO vo = (MovieRegisterVO) dto; // DTO 타입의 매개변수를 MovieDetailVO 타입의 변수에 저장
		int flag = 0; // 쿼리 실행 성공 여부를 반환받을 변수
		Connection conn = null; // 커넥션 객체를 반환받을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수
		
		try {
			// 문자열을 담을 StringBuilder 객체 생성
			StringBuilder sb = new StringBuilder();
			// merge 쿼리문을 StringBuilder 객체에 담음
			sb.append("MERGE INTO movie ta                                                   \n"); 
			sb.append("USING (                                                               \n");
			sb.append("        SELECT                                                        \n");
			sb.append("            ? AS docid,                                               \n");
			sb.append("            ? AS title,                                               \n");
			sb.append("            ? AS title_eng,                                           \n");
			sb.append("            ? AS genre,                                               \n");
			sb.append("            ? AS director_nm,                                         \n");
			sb.append("            ? AS director_id,                                         \n");
			sb.append("            ? AS nation,                                              \n");
			sb.append("            ? AS actor_nm,                                            \n");
			sb.append("            ? AS actor_id,                                            \n");
			sb.append("            ? AS keywords,                                            \n");
			sb.append("            ? AS posters,                                             \n");
			sb.append("            ? AS release_date,                                        \n");
			sb.append("            ? AS prod_year,                                           \n");
			sb.append("            ? AS rate,                                               \n");
			sb.append("            ? AS plot,                                                 \n");
			sb.append("            ? AS reg_id,                                                 \n");
			sb.append("            ? AS mod_id                                                 \n");
			sb.append("        FROM dual                                                     \n");
			sb.append(")tb                                                                   \n");
			sb.append("ON (ta.docid = tb.docid)                                              \n");
			sb.append("WHEN MATCHED THEN                                                     \n");
			sb.append("UPDATE SET title = tb.title,                                          \n");
			sb.append("          title_eng = tb.title_eng,                                   \n");
			sb.append("          genre = tb.genre,                                           \n");
			sb.append("          director_nm = tb.director_nm,                               \n");
			sb.append("          director_id = tb.director_id,                               \n");
			sb.append("          nation = tb.nation,                                         \n");
			sb.append("          actor_nm = tb.actor_nm,                                     \n");
			sb.append("          actor_id = tb.actor_id,                                     \n");
			sb.append("          keywords = tb.keywords,                                     \n");
			sb.append("          posters = tb.posters,                                       \n");
			sb.append("          release_date = tb.release_date,                             \n");
			sb.append("          prod_year = tb.prod_year,                                   \n");
			sb.append("          rate = tb.rate,                                       	     \n");
			sb.append("          plot = tb.plot,                                         	 \n");
			sb.append("          reg_id = tb.reg_id,                                         \n");
			sb.append("          mod_id = tb.mod_id                                          \n");
			sb.append("WHEN NOT MATCHED THEN                                                 \n");
			sb.append("INSERT (ta.docid, ta.title, ta.title_eng, ta.genre, ta.director_nm, ta.director_id, ta.nation, ta.actor_nm, ta.actor_id, ta.keywords, ta.posters, ta.release_date, ta.prod_year, ta.rate, ta.plot, ta.reg_id, ta.mod_id)  \n");
			sb.append("VALUES (tb.docid, tb.title, tb.title_eng, tb.genre, tb.director_nm, tb.director_id, tb.nation, tb.actor_nm, tb.actor_id, tb.keywords, tb.posters, tb.release_date, tb.prod_year, tb.rate, tb.plot, tb.reg_id, tb.mod_id ) \n");
			
			// 커넥션 객체를 받아옴
			conn = new ConnectionMaker().getConnection(); // 커넥션 객체를 반환받음
			
			// merge 쿼리문을 출력
			LOG.debug("1.==========================");
			LOG.debug("1.query: \n" + sb.toString());
			LOG.debug("1.==========================");
			
			// Connection 인터페이스의 prepareStatement() 함수를 호출하여 
			// merge 쿼리문을 전달하며 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			// 객체 안 쿼리문의 물음표 값을 매개변수로 받은 객체의 변수의 값으로 지정
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
			
			// 매개변수로 받은 객체를 출력
			LOG.debug("2.==========================");
			LOG.debug("2.param: " +vo);
			LOG.debug("2.==========================");
			
			// 쿼리문을 실행하고 성공 여부를 반환받음
			flag = pstmt.executeUpdate();
			
			LOG.debug("3.==========================");
			LOG.debug("3.flag: " +flag); // 쿼리문 실행 성공 여부 출력
			LOG.debug("3.==========================");
			
		} catch (SQLException e) {
			
			LOG.debug("1.===============================");
			LOG.debug("SQLException" + e.toString());
			LOG.debug("1.===============================");
			
		} finally {
			// 자원 반납 클래스에 있는 메소드를 불러와 자원반납 시킴
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag; // 쿼리문 실행 성공 여부 반환
		
	}//--do_upsert */

	/**1. 입력 메소드 */
	public int do_insert(DTO dto) {
		
		MovieRegisterVO vo = (MovieRegisterVO) dto; // DTO 타입의 매개변수를 MovieRegisterVO 타입의 변수에 저장
		int flag = 0;
		Connection conn = null; // 커넥션 객체를 반환받을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수
		
		try {
			// StringBuilder 객체 생성 -> 문자열 담는 역할
			StringBuilder sb = new StringBuilder();
			// insert 쿼리문을 버퍼 객체에 담음 : movie 테이블
			sb.append("insert all                            \n");
			sb.append("into movie ( docid,                   \n");
			sb.append("             title,                   \n");
			sb.append("             title_eng,               \n");
			sb.append("             genre,                   \n");
			sb.append("             director_nm,             \n");
			sb.append("             director_id,             \n");
			sb.append("             nation,                  \n");
			sb.append("             actor_nm,                \n");
			sb.append("             actor_id,                \n");
			sb.append("             keywords,                \n");
			sb.append("             posters,                 \n");
			sb.append("             release_date,            \n");
			sb.append("             prod_year,               \n");
			sb.append("             rate,                    \n");
			sb.append("             plot,                    \n");
			sb.append("             reg_id,                  \n");
			sb.append("             mod_id,                  \n");
			sb.append("             now_showing       )      \n");
			sb.append("values ( docid,                       \n");
			sb.append("             title,                   \n");
			sb.append("             title_eng,               \n");
			sb.append("             genre,                   \n");
			sb.append("             director_nm,             \n");
			sb.append("             director_id,             \n");
			sb.append("             nation,                  \n");
			sb.append("             actor_nm,                \n");
			sb.append("             actor_id,                \n");
			sb.append("             keywords,                \n");
			sb.append("             posters,                 \n");
			sb.append("             release_date,            \n");
			sb.append("             prod_year,               \n");
			sb.append("             rate,                    \n");
			sb.append("             plot,                    \n");
			sb.append("             reg_id,                  \n");
			sb.append("             mod_id,                  \n");
			sb.append("             now_showing       )      \n");
			// insert 쿼리문을 버퍼 객체에 담음 : recommend 테이블
			sb.append("into recommend ( docid,               \n");
			sb.append("                 rec_m,               \n");
			sb.append("                 rec_w,               \n");
			sb.append("                 rec_t,               \n");
			sb.append("                 rec_p,               \n");
			sb.append("                 reg_id,              \n");
			sb.append("                 mod_id      )        \n");
			sb.append("values ( docid,                       \n");
			sb.append("         rec_m,                       \n");
			sb.append("         rec_w,                       \n");
			sb.append("         rec_t,                       \n");
			sb.append("         rec_p,                       \n");
			sb.append("         reg_id,                      \n");
			sb.append("         mod_id      )                \n");
			sb.append("select     ? as docid,                \n");
			sb.append("           ? as title,                \n");
			sb.append("           ? as title_eng,            \n");
			sb.append("           ? as genre,                \n");
			sb.append("           ? as director_nm,          \n");
			sb.append("           ? as director_id,          \n");
			sb.append("           ? as nation,               \n");
			sb.append("           ? as actor_nm,             \n");
			sb.append("           ? as actor_id,             \n");
			sb.append("           ? as keywords,             \n");
			sb.append("           ? as posters,              \n");
			sb.append("           ? as release_date,         \n");
			sb.append("           ? as prod_year,            \n");
			sb.append("           ? as rate,                 \n");
			sb.append("           ? as plot,                 \n");
			sb.append("           ? as reg_id,               \n");
			sb.append("           ? as mod_id,               \n");
			sb.append("           ? as now_showing,          \n");
			sb.append("           ? as rec_m,                \n");
			sb.append("           ? as rec_w,                \n");
			sb.append("           ? as rec_t,                \n");
			sb.append("           ? as rec_p                 \n");
			sb.append("from dual                             \n");
			
			LOG.debug("1=====================");
			LOG.debug("1. query:\n" + sb.toString()); // 입력 쿼리문 출력해 봄
			LOG.debug("1=====================");
			
			// connectionMaker 클래스의 getConnection() 메소드를 이용해서 커넥션 객체를 받아옴
			conn = connectionMaker.getConnection(); 
			// 쿼리문을 전달하면서 prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			
			// 매개변수로 받은 객체의 값의 가져와서 쿼리안의 물음표 값으로 지정
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
			pstmt.setString(18, vo.getNowShowing());
			
			pstmt.setInt(19, vo.getRecM());
			pstmt.setInt(20, vo.getRecW());
			pstmt.setInt(21, vo.getRecT());
			pstmt.setInt(22, vo.getRecP());
			
			// 매개변수로 받은 입력할 객체를 출력해 봄
			LOG.debug("2=====================");
			LOG.debug("2. param:" + vo.toString());
			LOG.debug("2=====================");
			
			flag = pstmt.executeUpdate(); // 쿼리문을 실행하고 성공 여부를 반환받음
			
			LOG.debug("3=====================");
			LOG.debug("3. flag:" + flag); // 쿼리문 실행 성공 여부 출력
			LOG.debug("3=====================");
			
		}catch(SQLException e) {
			LOG.debug("==================");
			LOG.debug("SQLException: " + e.toString());
			LOG.debug("==================");
		}finally {
			// JDBCReturnReso 클래스의 close() 메소드 호출하여 자원반납
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return flag; // 쿼리문 실행 성공 여부를 반환
		
	}//--do_insert
	
	/**2. 수정 메소드 */
	public int do_update(DTO dto) {
		
		MovieRegisterVO vo = (MovieRegisterVO) dto; // DTO 타입의 매개변수를 MovieRegisterVO 타입의 변수에 저장
		int flag = 0;
		int flag01 = 0;
		Connection conn = null; // 커넥션 객체를 반환받을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수(movie)
		PreparedStatement pstmt01 = null; // PreparedStatement 객체를 반환받을 변수(recommend)
		
		try {
			StringBuilder sb = new StringBuilder(); // StringBuilder 객체 생성 : movie 테이블 업데이트 쿼리문 
			StringBuilder sb01 = new StringBuilder(); // StringBuilder 객체 생성 : recommend 테이블 업데이트 쿼리문 
			// update 쿼리문을 버퍼 객체에 담음: movie
			sb.append("UPDATE movie                \n");
			sb.append("SET title = ?,              \n");
			sb.append("    title_eng = ?,          \n");
			sb.append("    genre = ?,              \n");
			sb.append("    director_nm = ?,        \n");
			sb.append("    director_id = ?,        \n");
			sb.append("    nation = ?,             \n");
			sb.append("    actor_nm = ?,           \n");
			sb.append("    actor_id = ?,           \n");
			sb.append("    keywords = ?,           \n");
			sb.append("    posters = ?,            \n");
			sb.append("    release_date = ?,       \n");
			sb.append("    prod_year = ?,          \n");
			sb.append("    rate = ?,               \n");
			sb.append("    plot = ?,               \n");
			sb.append("    mod_id = ?,             \n");
			sb.append("    now_showing = ?         \n");
			sb.append("WHERE docid = ?             \n");
			
			// update 쿼리문을 버퍼 객체에 담음: recommend
			sb01.append("UPDATE recommend                 \n");
			sb01.append("        SET rec_m = ?,           \n");
			sb01.append("            rec_w = ?,           \n");
			sb01.append("            rec_t = ?,           \n");
			sb01.append("            rec_p = ?,           \n");
			sb01.append("            mod_id = ?           \n");
			sb01.append("WHERE docid =   ?                \n");
			
			// 수정 쿼리문 출력해 봄
			LOG.debug("1=====================");
			LOG.debug("1. query(movie):\n" + sb.toString()); 
			LOG.debug("1. query(recommend):\n" + sb01.toString()); 
			LOG.debug("1=====================");
			
			// connectionMaker 클래스의 getConnection() 메소드를 이용해서 커넥션 객체를 받아옴
			conn = connectionMaker.getConnection(); 
			// 쿼리문을 전달하면서 prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			pstmt01 = conn.prepareStatement(sb01.toString());
			
			// 매개변수로 받은 객체의 값의 가져와서 쿼리안의 물음표 값으로 지정
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
			pstmt.setString(16, vo.getNowShowing());
			pstmt.setString(17, vo.getDOCID());
			
			pstmt01.setInt(1, vo.getRecM());
			pstmt01.setInt(2, vo.getRecW());
			pstmt01.setInt(3, vo.getRecT());
			pstmt01.setInt(4, vo.getRecP());
			pstmt01.setString(5, vo.getModId());
			pstmt01.setString(6, vo.getDOCID());
			
			// 매개변수로 받은 수정된 데이터를 가진 객체를 출력해 봄
			LOG.debug("2=====================");
			LOG.debug("2. param:" + vo.toString());
			LOG.debug("2=====================");
			
			flag = pstmt.executeUpdate(); // 쿼리문을 실행하고 성공 여부를 반환받음(movie)
			flag01 = pstmt01.executeUpdate(); // 쿼리문을 실행하고 성공 여부를 반환받음(recommend)
			
			// 쿼리문 실행 성공 여부 출력
			LOG.debug("3=====================");
			LOG.debug("3. flag:" + flag);
			LOG.debug("3. flag01:" + flag01);
			LOG.debug("3=====================");
			
		}catch(SQLException e) {
			LOG.debug("==================");
			LOG.debug("SQLException: " + e.toString());
			LOG.debug("==================");
		}finally {
			// JDBCReturnReso 클래스의 close() 메소드 호출하여 자원반납
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(pstmt01);
			JDBCReturnReso.close(conn);
		}
		
		return flag; // 쿼리문 실행 성공 여부를 반환
		
	}//--do_update
	
	/** 3. 삭제 메소드 */
	public int do_delete(DTO dto) {
		
		MovieRegisterVO vo = (MovieRegisterVO) dto; // DTO 타입의 매개변수를 MovieDetailVO 타입의 변수에 저장
		int flag = 0; // 쿼리 실행 성공 여부를 반환받을 변수
		int flag01 = 0;
		Connection conn = null; // 커넥션 객체를 반환받을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수(movie)
		PreparedStatement pstmt01 = null; // PreparedStatement 객체를 반환받을 변수(recommend)
		
		StringBuilder sb = new StringBuilder(); // StringBuilder 객체 생성 : movie 테이블 delete 쿼리문 
		StringBuilder sb01 = new StringBuilder(); // StringBuilder 객체 생성 : recommend 테이블 delete 쿼리문 
		// 삭제 쿼리문을 StringBuilder에 입력 L movie
		sb.append(" DELETE FROM movie \n");
		sb.append(" WHERE docid = ?     \n");
		
		// 삭제 쿼리문을 버퍼 객체에 담음: recommend
		sb01.append(" DELETE FROM recommend \n");
		sb01.append(" WHERE docid = ?     \n");
		
		try {
			// 커넥션 객체 받아옴
			conn = connectionMaker.getConnection();
			
			// 삭제 쿼리문 출력
			LOG.debug("1======================");
			LOG.debug("query(movie):\n"+sb.toString());
			LOG.debug("query(recommend):\n"+sb01.toString());
			LOG.debug("1======================");
			
			// 삭제 쿼리문을 담으면서 prepareStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt01 = conn.prepareStatement(sb01.toString());
			
			// 매개변수로 받은 삭제할 객체의 docid 값을 가져와서  쿼리문 안의 물음표 값으로 지정
			pstmt.setString(1, vo.getDOCID());
			pstmt01.setString(1, vo.getDOCID());
			LOG.debug("2======================");
			// 삭제할 객체의  등록번호를 출력
			LOG.debug("param: "+vo.getDOCID());
			LOG.debug("2======================");	
			
			flag = pstmt.executeUpdate(); // 쿼리문 실행결과를 반환(movie)
			flag01 = pstmt01.executeUpdate(); // 쿼리문을 실행하고 성공 여부를 반환받음(recommend)
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 자원반납 클래스에 있는 메소드를 불러와서 자원반납 시킴
			JDBCReturnReso.close(pstmt); 
			JDBCReturnReso.close(pstmt01);
			JDBCReturnReso.close(conn);						
	    }
		
		LOG.debug("3=====================");
		LOG.debug("flag:"+flag); // 삭제 쿼리 실행 성공여부를 출력
		LOG.debug("flag01:" + flag01);
		LOG.debug("3=====================");
		
		return flag; // 삭제 쿼리 실행 성공여부를 반환
		
	}//--do_delete

	/**4. 전체 조회 메소드: 10개씩 잘라서 반환하고, 최근 등록일 순으로 정렬*/
	public List<?> do_retrieve(DTO dto) {
		
		SearchVO vo = (SearchVO) dto;
		List<MovieRegisterVO> list=new ArrayList<>(); // MovieDetailVO형의  ArrayList 생성
		Connection conn = null; // 커넥션 객체를 담을 변수
		PreparedStatement pstmt = null; // 쿼리를 담을 PreparedStatement 객체를 담을 변수
		ResultSet rs = null; // 쿼리 실행 결과를 반환받을 변수(ResultSet 객체에 담아서 리턴)
		
		// 쿼리문
		// 테이블 전체 조회하는데 페이징으로 잘라서 반환 + 크로스 조인으로 전체 검색된 데이터 수도 같이 반환
		StringBuilder sb=new StringBuilder();
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
//	    sb.append("       	  ,b.posters                            	\n");
	    sb.append("           ,CASE                                     \n");
	    sb.append("            WHEN b.posters LIKE '%|%' THEN substr(b.posters, 1, instr(b.posters, '|')-1) \n");
	    sb.append("            ELSE b.posters                           \n");
	    sb.append("            END as posters                           \n");
	    sb.append("       	  ,b.release_date                       	\n");
	    sb.append("       	  ,b.prod_year                          	\n");
	    sb.append("       	  ,b.rate                               	\n");
	    sb.append("       	  ,b.plot                               	\n");
	    sb.append("       	  ,b.reg_id                             	\n");
	    sb.append(" 	  	  ,TO_CHAR(b.reg_dt,'YYYY/MM/DD') reg_dt	\n");
	    sb.append("       	  ,b.mod_id                              	\n");
		sb.append(" 	  	  ,TO_CHAR(b.mod_dt,'YYYY/MM/DD') mod_dt	\n");
		sb.append("       	  ,b.now_showing                          	\n");
		sb.append("       	  ,b.rec_m                               	\n");
		sb.append("       	  ,b.rec_w                               	\n");
		sb.append("       	  ,b.rec_t                               	\n");
		sb.append("       	  ,b.rec_p                               	\n");
		sb.append(" 	FROM(                           				\n");   
		sb.append(" 		SELECT ROWNUM as rnum, a.*  				\n");    
		sb.append(" 		FROM(                      					\n");    
		sb.append(" 			SELECT m.*, r.rec_m, r.rec_w, r.rec_t, r.rec_p \n");
		sb.append("             FROM movie m, recommend r               \n");
		sb.append("             WHERE r.docid = m.docid                 \n");
		sb.append("		        ORDER BY m.reg_dt       DESC            \n");
		sb.append(" 		)a                          				\n");    
		sb.append(" 	WHERE ROWNUM <=(10 *(? -1) + 10)  				\n");
		sb.append(" 	)b  	                        				\n");
		sb.append(" WHERE b.rnum >=(10 * (? -1)+1)       				\n"); 	  
		sb.append(" )T1                                 				\n");	  
		sb.append(" CROSS JOIN(                         				\n");          	  
		sb.append(" SELECT COUNT(*) total_cnt           				\n");	  
		sb.append(" FROM movie                          				\n");
		sb.append(" )T2                                 				  ");
		
		// 전체 쿼리문 출력
		LOG.debug("0. sql \n:"+sb.toString()); 
		
		try{
			//conn = getConnection(); // 커넥션 객체를 반환받음
			conn = new ConnectionMaker().getConnection();
			pstmt = conn.prepareStatement(sb.toString()); // prepareStatement 객체를 생성하면서  버퍼의 쿼리문을 저장
			
			// 쿼리문의 물음표(?) 부분을 대체할 값을 입력
			// searchVO에서 값을 가져와서 입력
			pstmt.setInt(1, vo.getPageNum());
			pstmt.setInt(2, vo.getPageNum());	
		
		// 매개 변수로 받은 searchVO 객체를 출력(페이지 사이즈, 페이지 넘버, 검색 구분자, 검색 키워드를 요소로 가짐)
		LOG.debug("3. param: "+vo);
		
		rs = pstmt.executeQuery(); // 쿼리 실행결과를 반환받음
		
		while(rs.next()){ // rs에 쿼리 실행 결과가 있어서, 다음 행이 존재하는 동안 반복
			
			// 쿼리 실행결과에서 각 변수의 값을 뽑아내서 outVO 객체에 그 값을  set함
			MovieRegisterVO outVO=new MovieRegisterVO();
			
			outVO.setNum(rs.getInt("num"));
			outVO.setTotal(rs.getInt("total_cnt"));
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
			outVO.setRecM(rs.getInt("rec_m"));
			outVO.setRecW(rs.getInt("rec_w"));
			outVO.setRecT(rs.getInt("rec_t"));
			outVO.setRecP(rs.getInt("rec_p"));
			
			list.add(outVO); // set된 값을 가진 outVO를 리스트에 추가
		}
			
		}catch(SQLException e){
			LOG.debug("===============================");
			LOG.debug("SQLException="+e.toString());
			LOG.debug("===============================");
		}finally{
			// 자원 반납 클래스에 있는 메소드를 불러와서 자원반납 시킴
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
	
		return list; // 쿼리문 수행을 통해 반환된 데이터를 각각 객체로 하여 담은 리스트를 반환
		
	}//--do_retrieve
	
	/** 5. 단건 조회 */
	public DTO do_selectOne(DTO dto) {
		
		MovieRegisterVO vo = (MovieRegisterVO) dto; // DTO 타입의 매개변수를 MovieRegisterVO 타입의 변수에 저장
		MovieRegisterVO outVO = null; // 검색할 객체를 담을 변수
		Connection conn = null; // 커넥션 객체를 담을 변수
		PreparedStatement pstmt = null; // PreparedStatement 객체를 반환받을 변수(쿼리문 가짐)
		ResultSet rs = null; // 쿼리의 실행 결과를 반환받을 변수(ResultSet 객체에 담아서 리턴)
		
		try {
			// StringBuilder 객체 생성 -> 문자열을 담는 역할
			StringBuilder sb = new StringBuilder();
			// StringBuilder에 docid에 따른 데이터 조회 쿼리문을 담음
			sb.append("SELECT m.*, r.rec_m, r.rec_w, r.rec_t, r.rec_p  \n");
			sb.append("FROM movie m, recommend r                       \n");
			sb.append("WHERE m.docid = ?                               \n");
			
			// 커넥션 객체를 받아옴
			conn = connectionMaker.getConnection();
			// 단건 조회 쿼리문 출력
			LOG.debug("1.==========================");
			LOG.debug("1.query: \n" + sb.toString());
			LOG.debug("1.==========================");
			
			// Connection 인터페이스의  prepareStatement() 함수를 호출
			// 단건 조회 쿼리문을 전달하며 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			
			// 객체 안 쿼리 문의 첫 번째 물음표 값을 전달받은 매개변수의 docid로 지정
			pstmt.setString(1, vo.getDOCID());
			// (매개변수로 받음) 조회할 객체의 docid를 출력
			LOG.debug("2.==========================");
			LOG.debug("2.param: " + vo.getDOCID());
			LOG.debug("2.==========================");
			
			rs = pstmt.executeQuery(); // SELECT 쿼리를 실행하여, 쿼리 실행 결과값을 담은 ResultSet 객체를 반환
			// ResultSet.next(): 처음에 첫번 째 행 이전에 커서가 위치하기 때문에, 첫 번째 행에 저장된 데이터를 읽으려면  next() 메소드를 사용하여 커서를 이동해야함
			//                 : 커서의 다음 행이 존재하는 경우 true를 리턴하고, 커서를 그 행으로 이동시킴
			//   			   : 커서는 순차적으로 이동하고, 마지막 행에 도달하면 false를 리턴
			
			// rs에 쿼리 실행 결과가 있어서 다음 행이 존재하는 경우
			if(rs.next()) {
				// MovieRegisterVO 객체를 생성
				outVO = new MovieRegisterVO();
				// 쿼리문으로 검색된 객체에서 추출한 데이터에서 값을 뽑아내어 OutVo의 변수 값으로 설정
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
				outVO.setRecM(rs.getInt("rec_m"));
				outVO.setRecW(rs.getInt("rec_w"));
				outVO.setRecT(rs.getInt("rec_t"));
				outVO.setRecP(rs.getInt("rec_p"));
			}
			
			
		} catch(SQLException e) {
			LOG.debug("====================");
			LOG.debug("SQLException=" + e.getMessage());
			LOG.debug("====================");
		}finally {
			// 자원 반납 클래스에 있는 메소드를 불러와서 자원반납 시킴
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
			
		}
		
		return outVO; // 검색된 객체를 반환(검색된 데이터의 값을 각 객체의 변수 값으로  설정한)
		
	}//--do_selectOne
	

}//--class
