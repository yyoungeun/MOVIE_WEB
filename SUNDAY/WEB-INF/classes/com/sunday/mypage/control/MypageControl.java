package com.sunday.mypage.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sunday.cmn.MessageVO;
/*
import com.hr.code.CodeDao;
import com.hr.code.CodeVO;*/
import com.sunday.cmn.StringUtil;
import com.sunday.member.control.MemberService;
import com.sunday.member.dao.MemberVO;
import com.sunday.movie.domain.SearchVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet(description = "회원관리", urlPatterns = { "/mypage.do" })
public class MypageControl extends HttpServlet {
	private final Logger LOG = Logger.getLogger(MemberService.class);
	
	private MemberService memberService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageControl() {
    	memberService = new MemberService();
    }
    
    protected void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_delete: ");
    	MemberVO inVO = new MemberVO();
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	inVO.setUser_id(userId);
    	int flag = memberService.do_delete(inVO);
    	LOG.debug("03.2 flag: " + flag);
    	
    	HttpSession httpSession = request.getSession();
    	if(null != httpSession){
    		LOG.debug("03.1 httpSession: " + httpSession);
    		httpSession.removeAttribute("user");
    		httpSession.removeAttribute("id");
    		httpSession.removeAttribute("passwd");
    		httpSession.removeAttribute("name");
    		httpSession.removeAttribute("email");
    		httpSession.removeAttribute("lvl");
    		httpSession.removeAttribute("reg_dt");
    		httpSession.removeAttribute("mod_dt");
    		
    		httpSession.invalidate();
    		LOG.debug("03.2 httpSession: " + httpSession);
    	}
    	
    	//JSON
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String msg= "";
    	String gsonString ="";
    	
    	if(flag >0){
    		msg = "삭제되었습니다.";
    	}else{
    		msg ="삭제 실패";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag),msg));
    	LOG.debug("03.3 gsonString: " + gsonString);
    	out.print(gsonString);
    }
    
    protected void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_update: ");
    	MemberVO inVO = new MemberVO();
    	
    	//param
    	String userId = StringUtil.nvl(request.getParameter("mod_id"), "");
    	String passwd = StringUtil.nvl(request.getParameter("mod_passwd"), "");
    	String name=StringUtil.nvl(request.getParameter("mod_name"), "");
    	String email = StringUtil.nvl(request.getParameter("email"), "");
  	
    	inVO.setUser_id(userId);
    	inVO.setPasswd(passwd);
    	inVO.setName(name);
    	inVO.setEmail(email);
    	
    	LOG.debug("03.2 param: " + inVO);
    	
    	int flag = this.memberService.do_update(inVO);
    	LOG.debug("03.3 flag:" + flag);
    	
    	//끊기
    	HttpSession httpSession = request.getSession();
    	if(null != httpSession){
    		LOG.debug("03.1 httpSession: " + httpSession);
    		httpSession.removeAttribute("user");
    		httpSession.removeAttribute("id");
    		httpSession.removeAttribute("passwd");
    		httpSession.removeAttribute("name");
    		httpSession.removeAttribute("email");
    		httpSession.removeAttribute("lvl");
    		httpSession.removeAttribute("reg_dt");
    		httpSession.removeAttribute("mod_dt");
    		
    		httpSession.invalidate();
    		LOG.debug("03.2 httpSession: " + httpSession);
    	}
    	
    	//다시 세션
    	MemberVO outVO = new MemberVO();
    	outVO = memberService.do_selectOne(inVO);
    	HttpSession session = request.getSession();
		session.setAttribute("user", outVO);
		session.setAttribute("id", outVO.getUser_id());
		session.setAttribute("passwd", outVO.getPasswd());
		session.setAttribute("name", outVO.getName());
		session.setAttribute("email", outVO.getEmail());
		session.setAttribute("lvl", outVO.getLvl());
		session.setAttribute("reg_dt", outVO.getReg_dt());
    	session.setAttribute("mod_dt", outVO.getMod_dt());
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset= utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg ="";
    	String gsonString ="";
    	if(flag >0){
    		msg = inVO.getUser_id()+"가 수정되었습니다.";
    		
    	}else{
    		msg = "수정실패";
    	}
    	
    	session = request.getSession(true);
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag),msg));
    	LOG.debug("03.4 gsonString: " + gsonString);
    	out.print(gsonString);
    }

    
    protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	LOG.debug("02.1 workDiv: " + workDiv);
    	
    	switch(workDiv){
			
			case "do_update":
				do_update(request,response);
			break;
			
			case "do_delete":
				do_delete(request,response);
			break;
			
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 memberService: " + memberService);
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		doServiceHandler(request, response);
	}
	
	
	/* protected void do_remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);
	
}
*/
}
