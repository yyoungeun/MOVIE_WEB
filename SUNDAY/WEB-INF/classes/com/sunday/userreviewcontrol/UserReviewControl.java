package com.sunday.userreviewcontrol;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import com.sunday.cmn.MessageVO;
import com.sunday.cmn.StringUtil;
import com.sunday.userREVIEW.ReviewVO;
import com.sunday.userREVIEW.SearchVO;




/**
 * Servlet implementation class MovieDetailControl
 * @param <BoardVO>
 */
@WebServlet(description = "마이페이지/내가쓴리뷰", urlPatterns = { "/user_review.do" })
	public class UserReviewControl<BoardVO> extends HttpServlet {
	private UserReviewDetailService userReviewDetailService;

	private final Logger LOG=Logger.getLogger(UserReviewControl.class);

   
	public UserReviewControl() {
    //변수 = new 클래스();
    	userReviewDetailService = new UserReviewDetailService();
    	
    }
    
 
     
  
    //do_retrieve
    protected void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_retrieve()");
    	response.setContentType("text/html;charset=utf-8");
    	SearchVO inVO = new SearchVO();
    	
    	String pageSize   = StringUtil.nvl(request.getParameter("page_size"), "10");
    	String pageNum    = StringUtil.nvl(request.getParameter("page_num"), "1");
    	String searchDiv  = StringUtil.nvl(request.getParameter("search_div"),"");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"), ""); 
    	
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	List<ReviewVO> list = userReviewDetailService.do_retrieve(inVO);
    	
    	LOG.debug("---------------------------");
    	for(ReviewVO vo : list){
    		LOG.debug(vo);
    	}
    	LOG.debug("---------------------------");
    	
    	int totalCnt=0; //총글수
    	if(list.size()>0 && list!=null){
    		ReviewVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/user/user_list.jsp");//임시로 적은 것!!!
    	dispatcher.forward(request, response);
    }  
    
   
    
    protected void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	LOG.debug("03.1--------- do_update");
    	ReviewVO inVO = new ReviewVO();
    	//ReviewVO inVO=new ReviewVO();
    	//param
    	
    	String docid    = StringUtil.nvl(request.getParameter("docid"),"");
    	String contents = StringUtil.nvl(request.getParameter("contents"),"");
    	String userId   = StringUtil.nvl(request.getParameter("search_word"), "");
    	String userRate = StringUtil.nvl(request.getParameter("user_rate"), "0");
    	
    	inVO.setDocid(docid);
    	inVO.setContents(contents);
    	inVO.setUser_id(userId);
    	inVO.setUser_rate(Integer.parseInt(userRate));
    	
    	LOG.debug("03.2 param:"+inVO);
    	//--param
    	int flag = userReviewDetailService.do_update(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString ="";
    	if(flag>0){
    		msg = "리뷰를 수정했습니다.";
    	}else{
    		msg = "수정실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    	
    }
    
    protected void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		LOG.debug("03.1 do_delete");
        	//Service call:삭제
    		ReviewVO inVO=new ReviewVO();
        	//param:seq
        	String docid = StringUtil.nvl(request.getParameter("docid"),"");
        	String userId = StringUtil.nvl(request.getParameter("search_word"), "");
        	
        	inVO.setDocid(docid);
        	inVO.setUser_id(userId);
        	
        	int flag =  this.userReviewDetailService.do_delete(inVO);
        	
        	LOG.debug("03.2 flag:"+flag);
        	
        	//JSON
        	Gson gson = new Gson();
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
        	String msg = "";
        	String gsonString = "";
        	//msg=flag
        	//msgContents="삭제되었습니다.";
        	if(flag>0){
        		msg="삭제되었습니다.";
//        		out.println("location.href='/WEB_EX01/member/member.do?work_div=do_retrieve';");
        	}else{
        		msg="삭제실패.";
        	}
        	gsonString = gson.toJson(new MessageVO(String.valueOf(flag) , msg));
        	LOG.debug("03.3 gsonString:"+gsonString);
        	out.print(gsonString);
    }
 
    protected void do_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SearchVO inVO =new SearchVO();
    	
    	String pageNum    = StringUtil.nvl(request.getParameter("page_num"),"1");
    	String searchDiv  = StringUtil.nvl(request.getParameter("search_div"),"");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"),"");
    	String pageSize   = StringUtil.nvl(request.getParameter("page_size"),"10");
    	
    	
    	//inVO.setPageNum(Integer.parseInt(pageNum));
    	//inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	//inVO.setPageNum(Integer.parseInt(pageSize));
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	List<ReviewVO> list= userReviewDetailService.do_retrieve(inVO);
    	
    	LOG.debug("------------------------");
    	for(ReviewVO vo: list){
    		LOG.debug(vo);
        }
    	LOG.debug("------------------------");
    	
    	int totalCnt = 0;
    	//총글수
    	if(null != list && list.size()>0){
    		ReviewVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
		
    	String docid = StringUtil.nvl(request.getParameter("docid"),"");
    
    	
  
    	
    	
    
    	request.setAttribute("docid", docid);
     	LOG.debug("03.2 docid:"+docid);
    	
    	RequestDispatcher dispatcher =request.getRequestDispatcher("/user/user_list2.jsp");
    	dispatcher.forward(request, response);
    	
    	
    	
    	//JSON
    	/*Gson gson=new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter  out = response.getWriter();
    	String msg = "";
    	String gsonString = "";
    	
    	if(flag>0){
//    		out.println("location.href='/WEB_EX01/member/member.do?work_div=do_retrieve';");
    		msg = "삭제되었습니다.";
    	}else{
    		msg = "삭제 실패.";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	//03.3 gsonString:{"msgId":"1","msgContents":"삭제되었습니다.","total":0,"num":0}
    	LOG.debug("03.3 gsonString:"+gsonString);
    	out.print(gsonString);*/
}
    protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//기능 : do_retrieve,do_insert,do_update,do_selectone,do_retrieve
    	//work_div
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	//work_div:read
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"),"");
    	LOG.debug("02.1 workDiv:"+workDiv);
    	
    	/* 
    	 *
    	 * do_update:수정
    	 * do_delete:삭제
    	 * do_retrieve:조회
    	 */
    	switch(workDiv){
    	    //등록화면으로 이동
		 	

    		case "do_update":
    			do_update(request,response);
    		break;  
    		
    		case "do_delete":
    			do_delete(request,response);
    		break;   
  		
    		case "do_retrieve":
    			do_retrieve(request,response);
    		break;      		
    		
    		case "do_move":
    			do_move(request,response);
    			break;
    	}
    	
   }   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 hrMemberService:"+userReviewDetailService);
		doServiceHandler(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 hrMemberService:"+userReviewDetailService);	
		doServiceHandler(request, response);
	}

}
