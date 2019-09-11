/**
 * @Class Name : MemberService.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019. 7. 22.           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2019. 7. 22. 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 */
package com.sunday.member.control;

import java.util.List;

import org.apache.log4j.Logger;

import com.sunday.cmn.DTO;
import com.sunday.cmn.MessageVO;
import com.sunday.member.dao.MemberDao;
import com.sunday.member.dao.MemberVO;

/**
 * @author sist
 *
 */
public class MemberService {
	private final Logger LOG = Logger.getLogger(MemberService.class);
	
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public MemberVO do_find(DTO dto){
		return memberDao.do_find(dto);
	}
	
	public int loginCheck(DTO dto){
		MessageVO outVO = new MessageVO();
		
		//user_id check
		outVO = memberDao.idcheck(dto);
		if(!outVO.getMsgId().equals("1")){
			return 2; //아이디 틀릴경우
		}
		//passwd check
		outVO = memberDao.passCheck(dto);
		if(!outVO.getMsgId().equals("1")){
			return 3; //비밀번호 틀릴경우
		}
		return 1; //로그인 성공
	}
	
	public List<MemberVO> do_retrieve(DTO dto){
		return memberDao.do_retrieve(dto);
	}
	
	public MemberVO do_selectOne(DTO dto){
		return memberDao.do_selectOne(dto);
	}
	
	public int do_delete(DTO dto){
		return memberDao.do_delete(dto);
	}
	
	public int do_update(DTO dto){
		return memberDao.do_update(dto);
	}
	
	public int do_insert(DTO dto) {
		return memberDao.do_insert(dto);
	}
	
}
