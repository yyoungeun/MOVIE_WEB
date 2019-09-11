/**
 * @Class Name : MemberTest.java
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

import org.apache.log4j.Logger;

/**
 * @author sist
 *
 */
public class MemberTest {
	private final Logger LOG = Logger.getLogger(MemberTest.class);
	
	private MemberVO vo01;
	private MemberVO vo02;
	private MemberVO vo03;
	
	private MemberDao dao;

	/**
	 * <PRE>
	 * 1. MethodName : MemberTest
	 * 2. ClassName  : MemberTest
	 * 3. Comment   : 
	 * 4. 작성자    : 송영은
	 * 5. 작성일    : 2019. 7. 15. 오후 4:32:33
	 * </PRE>
	 */
	public MemberTest() {
		vo01 = new MemberVO("ccc", "cc123", "dog", "ccc@gmail.com", "1", "", "", "", "");
		vo02 = new MemberVO("fax","1920","Lex","fax@nate.com","1","","","","");
		vo03 = new MemberVO("xsw","9403","Bruce","xsw@nate.com","1","","","","");
		
		dao = new MemberDao();
	}

	public void do_insert(){
		int flag = dao.do_insert(vo01);
	}
	
	public void do_delete(){
		vo01.setUser_id("ccc");
		int flag = dao.do_delete(vo01);
	}
	
	public void do_update(){
		vo02.setUser_id("fax");
		vo02.setPasswd("dd222");
		vo02.setName("young");
		vo02.setReg_id("");
		vo02.setMod_id("song");
		int flag = dao.do_update(vo02);
	}
	
	public void do_selectOne(){
		vo01.setUser_id("ccc");
		MemberVO outVO = (MemberVO) dao.do_selectOne(vo01);
		LOG.debug("==============================");
		LOG.debug("outVO: " + outVO);
		LOG.debug("==============================");
	}
	/**
	 * @Method Name  : main
	 * @작성일   : 2019. 7. 15.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param args
	 */
	public static void 
	main(String[] args) {
		MemberTest membertest = new MemberTest();
		//membertest.do_insert();
		//membertest.do_delete();
		//membertest.do_update();
		membertest.do_selectOne();

	}

}
