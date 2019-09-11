package com.sunday.member.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sunday.cmn.StringUtil;
import com.sunday.member.dao.MemberVO;
import com.sunday.movie.domain.SearchVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet(description = "회원관리", urlPatterns = { "/join.do" })
public class MemberControl extends HttpServlet {
	private final Logger LOG = Logger.getLogger(MemberService.class);
	
	private MemberService memberService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberControl() {
    	memberService = new MemberService();
    }
    
    protected void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_retrieve: ");
    	SearchVO inVO = new SearchVO();
    	String pageNum = StringUtil.nvl(request.getParameter("page_num"), "1");
    	String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
    	String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	//inVO.setPageSize(Integer.parseInt(pageSize));
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	List<MemberVO> list = memberService.do_retrieve(inVO);
    	LOG.debug("========================");
    	for(MemberVO vo: list){
    		LOG.debug(vo);
    	}
    	LOG.debug("========================");
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    	dispatcher.forward(request, response);
    	
    }
    
    protected void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_insert: ");
    	MemberVO inVO = new MemberVO();
    	
    	String userId = StringUtil.nvl(request.getParameter("join_id"), "");
    	String passwd = StringUtil.nvl(request.getParameter("join_passwd"), "");
    	String name = StringUtil.nvl(request.getParameter("join_name"), "");
    	String email = StringUtil.nvl(request.getParameter("join_email"), "");
    	
    	
    	inVO.setUser_id(userId);
    	inVO.setPasswd(passwd);
    	inVO.setName(name);
    	inVO.setEmail(email);
    	
    	LOG.debug("03.2 param: " + inVO);
    	
    	int flag = memberService.do_insert(inVO);
    	LOG.debug("03.3 flag: " + flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset= utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag >0){
    		msg = inVO.getUser_id()+ "을(를) 등록되었습니다.";
    	}else{
    		msg = "등록실패";
    	}
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
		
			case "do_insert":
				do_insert(request,response);
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
}
