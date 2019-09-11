/**
 * @Class Name : memberDAO.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019. 7. 15.           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2019. 7. 15. 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.sunday.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.ConnectionMaker;
import com.sunday.cmn.DTO;
import com.sunday.cmn.JDBCReturnReso;
import com.sunday.cmn.WorkDiv;

import jdk.nashorn.internal.scripts.JD;

/**
 * @author sist
 *
 */
public class MemberDao implements WorkDiv {
	private final Logger LOG = Logger.getLogger(MemberDao.class);
	private ConnectionMaker connectionMaker;
	

	/**
	 * <PRE>
	 * 1. MethodName : memberDAO
	 * 2. ClassName  : memberDAO
	 * 3. Comment   : 
	 * 4. 작성자    : 송영은
	 * 5. 작성일    : 2019. 7. 15. 오후 4:09:12
	 * </PRE>
	 */
	public MemberDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_insert(com.hr.cmn.DTO)
	 */
	@Override
	public int do_insert(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" INSERT INTO member ( \n");
			sb.append("     user_id,         \n");
			sb.append("     passwd,          \n");
			sb.append("     name,            \n");
			sb.append("     email,           \n");
			sb.append("     lvl,             \n");
			sb.append("     reg_id,          \n");
			sb.append("     reg_dt,          \n");
			sb.append("     mod_id,          \n");
			sb.append("     mod_dt           \n");
			sb.append(" ) VALUES (           \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     SYSDATE,         \n");
			sb.append("     ?,               \n");
			sb.append("     SYSDATE          \n");
			sb.append(" )                    \n");
			
			LOG.debug("1.query: " + sb.toString());
			LOG.debug("param: " + vo.toString());
			
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getLvl());
			pstmt.setString(6, vo.getReg_id());
			pstmt.setString(7, vo.getMod_id());
			
			LOG.debug("param: " + vo.toString());
			
			flag = pstmt.executeUpdate();
			LOG.debug("flag: " + flag);
			
		}catch(SQLException e){
			LOG.debug("SQLException: " + e.toString());
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_update(com.hr.cmn.DTO)
	 */
	@Override
	public int do_update(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		 try{
			 StringBuilder sb = new StringBuilder();
			 sb.append(" UPDATE member         	\n");
			 sb.append(" SET 			      	\n");
			 sb.append("     passwd = ?,        \n");
			 sb.append("     name = ?,         	\n");
			 sb.append("     reg_id = ?,        \n");
			 sb.append("     reg_dt = SYSDATE,  \n");
			 sb.append("     mod_id = ?,        \n");
			 sb.append("     mod_dt = SYSDATE  	\n");
			 sb.append(" WHERE user_id = ?     	\n");
			 
			 conn = connectionMaker.getConnection();
			 LOG.debug("1.==============================");
			 LOG.debug("1.query " + sb.toString());
			 LOG.debug("1.==============================");
			 
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setString(1, vo.getPasswd());
			 pstmt.setString(2, vo.getName());
			 pstmt.setString(3, vo.getReg_id());
			 pstmt.setString(4, vo.getMod_id());
			 pstmt.setString(5, vo.getUser_id());
			 
			 LOG.debug("2.param " + vo);
			 
			 flag = pstmt.executeUpdate();
			 LOG.debug("3.flag= " + flag);
			 
		 }catch(SQLException s){
			 LOG.debug(s.toString());
		 }finally{
			 JDBCReturnReso.close(pstmt);
			 JDBCReturnReso.close(conn);
		 }
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_delete(com.hr.cmn.DTO)
	 */
	@Override
	public int do_delete(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		int flag =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM member \n");
		sb.append(" WHERE user_id = ?  \n");
		
		try{
			conn = connectionMaker.getConnection();
			conn.setAutoCommit(false);
			LOG.debug("1============================");
			LOG.debug("query: "+ sb.toString());
			LOG.debug("1============================");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			LOG.debug("2============================");
			LOG.debug("param, user_id="+ vo.getUser_id());
			LOG.debug("2============================");
			
			flag = pstmt.executeUpdate();
			
			if(flag > 0){
				LOG.debug("3============================");
				LOG.debug("transaction="+ vo.getUser_id());
				LOG.debug("3============================");
			}else{
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		LOG.debug("4.=======================");
		LOG.debug("flag: " + flag);
		LOG.debug("4========================");
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_selectOne(com.hr.cmn.DTO)
	 */
	@Override
	public MemberVO do_selectOne(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		
		MemberVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT                                   \n");
			sb.append("     user_id,                             \n");
			sb.append("     passwd,                              \n");
			sb.append("     name,                                \n");
			sb.append("     email,                               \n");
			sb.append("     DECODE(lvl,1,'사용자',9,'관리자') lvl,   \n");
			sb.append("     reg_id,                              \n");
			sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') reg_dt, \n");
			sb.append("     mod_id,                              \n");
			sb.append("     TO_CHAR(mod_dt,'YYYY-MM-DD') mod_dt  \n");
			sb.append(" FROM                                     \n");
			sb.append(" 	member                               \n");
			sb.append(" WHERE user_id= ?                         \n");
			
			conn = connectionMaker.getConnection();
			LOG.debug("1.==============================");
			LOG.debug("query : " + sb.toString());
			LOG.debug("1.==============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getUser_id());
			LOG.debug("2.==============================");
			LOG.debug("param, user_id= " + vo.getUser_id());
			LOG.debug("2.==============================");
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				outVO = new MemberVO();
				outVO.setUser_id(rs.getString("user_id"));
				outVO.setPasswd(rs.getString("passwd"));
				outVO.setName(rs.getString("name"));
				outVO.setEmail(rs.getString("email"));
				outVO.setLvl(rs.getString("lvl"));
				outVO.setReg_id(rs.getString("reg_id"));
				outVO.setReg_dt(rs.getString("reg_dt"));
				outVO.setMod_id(rs.getString("mod_id"));
				outVO.setMod_dt(rs.getString("mod_dt"));
			}
			
		}catch(SQLException e){
			LOG.debug("3.==============================");
			LOG.debug("SQLException: "+ e.getMessage());
			LOG.debug("3.==============================");
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return outVO;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_retrieve(com.hr.cmn.DTO)
	 */
	@Override
	public List<?> do_retrieve(DTO vo) {
		return null;
	}

}
