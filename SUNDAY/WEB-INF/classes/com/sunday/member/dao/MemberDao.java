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
package com.sunday.member.dao;

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
import com.sunday.cmn.MessageVO;
import com.sunday.cmn.WorkDiv;
import com.sunday.movie.domain.SearchVO;


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
	
	public MemberVO do_find(DTO dto){
		MemberVO vo =  (MemberVO) dto;
		
		//MemberVO outVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT user_id, \n");
			sb.append("        passwd   \n");
			sb.append(" FROM member     \n");
			sb.append(" WHERE email= ?  \n");
			
			conn = connectionMaker.getConnection();
			LOG.debug("=========================");
			LOG.debug("query: " + sb.toString());
			LOG.debug("=========================");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getEmail());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				vo.setUser_id(rs.getString("user_id"));
				vo.setPasswd(rs.getString("passwd"));
			}
		}catch(Exception ex){
			LOG.debug("do_find: "+ ex.toString());
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return vo;
	}
	
	public MessageVO passCheck(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		
		MessageVO outVO = new MessageVO();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT COUNT(*) cnt \n");
			sb.append(" FROM member      \n");
			sb.append(" WHERE user_id = ?   \n");
			sb.append(" AND passwd = ?      \n");

			conn = connectionMaker.getConnection();
			LOG.debug("1. conn : "+ conn);
			LOG.debug("1.==============================");
			LOG.debug("query : " + sb.toString());
			LOG.debug("1.==============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getPasswd());;
			LOG.debug("3.param: " + vo.getUser_id());
			LOG.debug("3.param: " + vo.getPasswd());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result == 0){
					outVO.setMsgId("20");
					outVO.setMsgContents("비밀번호를 확인하세요.");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("비번 OK");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
	}

	
	public MessageVO idcheck(DTO dto) {
		MemberVO vo = (MemberVO) dto;
		
		MessageVO outVO = new MessageVO();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT COUNT(*) cnt \n");
			sb.append(" FROM member      \n");
			sb.append(" WHERE user_id = ?   \n");

			conn = connectionMaker.getConnection();
			LOG.debug("1.==============================");
			LOG.debug("query : " + sb.toString());
			LOG.debug("1.==============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
				if(result == 0){
					outVO.setMsgId("10");
					outVO.setMsgContents("id를 확인하세요");
				}else{
					outVO.setMsgId("1");
					outVO.setMsgContents("id가 있습니다.");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		return outVO;
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
			sb.append("     reg_dt          \n");
			sb.append(" ) VALUES (           \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     ?,               \n");
			sb.append("     SYSDATE         \n");
			sb.append(" )                    \n");
			
			LOG.debug("1.query: " + sb.toString());
			LOG.debug("param: " + vo.toString());
			
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			
			
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
			 sb.append("     mod_dt = SYSDATE  	\n");
			 sb.append(" WHERE user_id = ?     	\n");
			 
			 conn = connectionMaker.getConnection();
			 LOG.debug("1.==============================");
			 LOG.debug("1.query " + sb.toString());
			 LOG.debug("1.==============================");
			 
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setString(1, vo.getPasswd());
			 pstmt.setString(2, vo.getName());
			 pstmt.setString(3, vo.getUser_id());
			 
			 LOG.debug("2.param " + vo);
			 
			 flag = pstmt.executeUpdate();
			 LOG.debug("3.flag= " + flag);
			 
		 }catch(SQLException s){
			 LOG.debug(s.toString());
		 }finally{
			 JDBCReturnReso.close(pstmt);
			 JDBCReturnReso.close(conn);
		 }
		return flag;
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
			sb.append("     lvl,  								 \n");
			sb.append("     reg_id,                              \n");
			sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') reg_dt, \n");
			sb.append("     mod_id,                              \n");
			sb.append("     TO_CHAR(mod_dt,'YYYY-MM-DD') mod_dt  \n");
			sb.append(" FROM                                     \n");
			sb.append(" 	MEMBER                               \n");
			sb.append(" WHERE user_id= ?                         \n");

			
			conn = connectionMaker.getConnection();
			LOG.debug("1.==============================");
			LOG.debug("query : " + sb.toString());
			LOG.debug("1.==============================");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getUser_id());
			
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
			
		}catch(Exception ex){
			LOG.debug("3.==============================");
			LOG.debug("do_selectOne: "+ ex);
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
	public List<MemberVO> do_retrieve(DTO dto) {
		SearchVO vo = (SearchVO) dto;
		List<MemberVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		if(null != vo.getSearchDiv()){
			if("10".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE user_id like ? || '%' \n");
			}else if("20".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE email like ?|| '%' \n");
			}else if("30".equals(vo.getSearchDiv())){
				sbWhere.append("WHERE lvl like ?|| '%' \n");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT T1.*, T2.*                                        \n");
		sb.append(" FROM(                                                    \n");
		sb.append(" SELECT B.rnum as num                                     \n");
		sb.append("       ,B.user_id                                         \n");
		sb.append(" 	  ,B.passwd                                          \n");
		sb.append("       ,B.name                                            \n");
		sb.append("       ,B.email                                           \n");
		sb.append("       ,B.lvl                                             \n");
		sb.append(" 	  ,B.mod_id                                          \n");
		sb.append(" 	  ,TO_CHAR(b.reg_dt, 'YYYY-MM-DD') reg_dt            \n");
		sb.append(" 	  ,TO_CHAR(b.mod_dt,'YYYY-MM-DD') mod_dt            \n");
		sb.append(" FROM(                                                    \n");
		sb.append("         SELECT ROWNUM AS rnum, A.*                       \n");
		sb.append("         FROM                                             \n");
		sb.append("             (                                            \n");
		sb.append("                 SELECT *                                 \n");
		sb.append("                 FROM member                              \n");
		if(null != vo.getSearchDiv()){ //검색구분
			if(null != vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는가
				sb.append(sbWhere.toString());
			}
		}
		sb.append(" 		ORDER BY reg_dt DESC                             \n");
		sb.append("             )A                                           \n");
		sb.append("         WHERE ROWNUM <= (? * (?-1) + ?))B                \n");
		sb.append("         WHERE B.rnum >= (? * (?-1) +1)                   \n");
		sb.append("     )T1 CROSS JOIN                                       \n");
		sb.append("     (                                                    \n");
		sb.append("         SELECT COUNT(*) total_cnt                        \n");
		sb.append("         FROM member                                      \n");
		if(null != vo.getSearchDiv()){ //검색구분
			if(null != vo.getSearchWord() && vo.getSearchWord().length()>0){//검색어가 있는가
				sb.append(sbWhere.toString());
			}
		}
		sb.append("     )T2                                                  \n");
		
		
		LOG.debug("2 sql \n" + sb.toString());
		try{
			conn = connectionMaker.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			if(null != vo.getSearchDiv() && !"".equals(vo.getSearchDiv())){
				
				pstmt.setString(1, vo.getSearchWord());
				pstmt.setInt(2, vo.getPageSize());
				pstmt.setInt(3, vo.getPageNum());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageSize());
				pstmt.setInt(6, vo.getPageNum());
				pstmt.setString(7,  vo.getSearchWord());
			}else{
				
				pstmt.setInt(1, vo.getPageSize());
				pstmt.setInt(2, vo.getPageNum());
				pstmt.setInt(3, vo.getPageSize());
				pstmt.setInt(4, vo.getPageSize());
				pstmt.setInt(5, vo.getPageNum());
			}
			LOG.debug("3.param\n" + vo);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MemberVO memberVO = new MemberVO();
				memberVO.setNum(rs.getInt("num"));
				memberVO.setUser_id(rs.getString("user_id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setLvl(rs.getString("lvl"));
				//memberVO.setReg_id(rs.getString("reg_id"));
				memberVO.setReg_dt(rs.getString("reg_dt"));
				memberVO.setMod_id(rs.getString("mod_id"));
				memberVO.setMod_dt(rs.getString("mod_dt"));
				memberVO.setTotal(rs.getInt("total_cnt"));
				list.add(memberVO);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			JDBCReturnReso.close(rs);
			JDBCReturnReso.close(pstmt);
			JDBCReturnReso.close(conn);
		}
		
		return list;
	}

}
