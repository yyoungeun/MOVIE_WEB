/**
 * @Class Name : Hello.java
 * @Description : Hello
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.07.02           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2018.07.10 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.sunday.cmn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author sist
 *
 */
public class JDBCReturnReso {

	private static Logger LOG = Logger.getLogger(JDBCReturnReso.class);
	
	/**
	 * 
	 * @Method Name  : close
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : ResultSet Close
	 * @param rs
	 */
	public static void close(ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			LOG.debug("----------------------");
			LOG.debug("ResultSet="+e.getMessage());
			LOG.debug("----------------------");
		}
	}
	
	/**
	 * 
	 * @Method Name  : close
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :PreparedStatement 자원반납
	 * @param pstmt
	 */
	public static void close(PreparedStatement pstmt){
		try {
			if(pstmt!=null){
				pstmt.close();
			}
		} catch (SQLException e) {
			LOG.debug("----------------------");
			LOG.debug("PreparedStatement="+e.getMessage());
			LOG.debug("----------------------");
		}
	}
	
	/**
	 * 
	 * @Method Name  : close
	 * @작성일   : 2019. 6. 25.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 : Connection 자원반납
	 * @param conn
	 */
	public static void close(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			LOG.debug("----------------------");
			LOG.debug("Connection="+e.getMessage());
			LOG.debug("----------------------");
		}
	}
	
}
