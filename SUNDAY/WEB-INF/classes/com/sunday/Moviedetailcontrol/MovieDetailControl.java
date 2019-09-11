package com.sunday.Moviedetailcontrol;

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
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviedetail.dao.SearchVO;




/**
 * Servlet implementation class MovieDetailControl
 * @param <BoardVO>
 */
@WebServlet(description = "영화상세정보 및 리뷰", urlPatterns = { "/movie_detail.do" })
	public class MovieDetailControl<BoardVO> extends HttpServlet {
	private MovieDetailService movieDetailService;

	//	클래스 변수 = new 클래스();
	//  클래스 변수;
	
	private final Logger LOG=Logger.getLogger(MovieDetailControl.class);

    public MovieDetailControl() {
    //변수 = new 클래스();
    	movieDetailService = new MovieDetailService();
    	
    }
    //do_insert
    protected void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_insert");
    	MovieDetailVO vo = new MovieDetailVO();
    	
    	String pageNum = StringUtil.nvl(request.getParameter("page_num"), "1");
    	String userId = StringUtil.nvl(request.getParameter("user_id"), "");
    	String contents = StringUtil.nvl(request.getParameter("contents"), "");
    	String userRate = StringUtil.nvl(request.getParameter("user_rate"), "0");
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");
    	
    	vo.setUser_id(userId);
    	vo.setContents(contents);
    	vo.setUser_rate(Integer.parseInt(userRate));
    	vo.setDocid(docid);
    	
    	LOG.debug("03.2 param:" + vo);
    	
    	int flag = movieDetailService.do_insert(vo);
    	LOG.debug("03.3 flag:"+flag);
    	
    	//response.setContentType("text/html;charset=utf-8");
    	//PrintWriter out = response.getWriter();
    	
    	
//    	RequestDispatcher dispatcher =request.getRequestDispatcher("/detail/detail_list.jsp");
//    	dispatcher.forward(request, response);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString = "";
    	if(flag>0) msg = "리뷰를 등록하였습니다.";
    	else msg = "이미 등록한 리뷰가 있습니다.";
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
    //do_retrieve
    protected void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_retrieve");
    	response.setContentType("text/html;charset=utf-8");
    	
    	SearchVO  inVO =new SearchVO();
    	MovieDetailVO tmpVO = new MovieDetailVO();
    	
    	String pageNum    = StringUtil.nvl(request.getParameter("page_num"),"1");
    	String searchDiv  = StringUtil.nvl(request.getParameter("search_div"),"");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"),"");
    	String pageSize   = StringUtil.nvl(request.getParameter("page_size"),"10");
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");
    	
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	
    	tmpVO.setDocid(searchWord);
    	LOG.debug("tmpVO:"+tmpVO);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	List<MovieDetailVO> list= movieDetailService.do_retrieve(inVO);
    	
    	LOG.debug("------------------------");
    	for(MovieDetailVO vo: list){
    		LOG.debug(vo);
        }
    	LOG.debug("------------------------");
    	
    	
    	MovieDetailVO outVO = movieDetailService.do_selectOne(tmpVO);
    	LOG.debug("03.3 outVO:"+outVO);
    	
    	
    	int totalCnt = 0;
    	//총글수
    	if(null != list && list.size()>0){
    		MovieDetailVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	request.setAttribute("outVO", outVO);
    	
    	RequestDispatcher dispatcher =request.getRequestDispatcher("/detail/detail_list.jsp");
    	dispatcher.forward(request, response);
    }     
    
/*    protected void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_update");
    	MovieDetailVO inVO=new MovieDetailVO();
    	//param
    	String docid =StringUtil.nvl(request.getParameter("docid"),"");
    	String posters   =StringUtil.nvl(request.getParameter("posters"),"");
    	String title =StringUtil.nvl(request.getParameter("title"),"");
    	String title_eng  =StringUtil.nvl(request.getParameter("title_eng"),"");
    	String user_id  =StringUtil.nvl(request.getParameter("user_id"),"");
    	String contents   =StringUtil.nvl(request.getParameter("contents"),"");
    	String user_rate =StringUtil.nvl(request.getParameter("user_rate"),"");
    	
    	inVO.setDocid(docid);
    	inVO.setPosters(posters);;
    	inVO.setTitle(title);
    	inVO.setTitle_eng(title_eng);
    	inVO.setUser_id(user_id);
    	inVO.setContents(contents);
    	inVO.setUser_rate(Integer.parseInt(user_rate));

    	LOG.debug("03.2 param:"+inVO);
    	//--param
    	int flag = movieDetailService.do_update(inVO);
    	LOG.debug("03.3 flag:"+flag);
    	
    	Gson gson=new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter  out = response.getWriter();
    	
    	String msg = "";
    	String gsonString = "";
    	if(flag>0){
    		msg =inVO.getUser_id()+"을(를)\n수정되었습니다.";
    	}else{
    		msg ="수정실패.";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag),msg));
    	LOG.debug("03.4 gsonString:"+gsonString);
    	out.print(gsonString);
    	
    }*/
    
    protected void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		LOG.debug("03.1 do_delete");
        	//Service call:삭제
    		MovieDetailVO inVO=new MovieDetailVO();
        	//param:seq
        	String docid = StringUtil.nvl(request.getParameter("docid"),"");
        	inVO.setDocid(docid);
        	
        	int flag =  this.movieDetailService.do_delete(inVO);
        	LOG.debug("03.2 flag:"+flag);
        	//JSON
        	Gson gson=new Gson();
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter  out = response.getWriter();
        	String msg = "";
        	String gsonString = "";
        	
        	if(flag>0){
//        		out.println("location.href='/WEB_EX01/member/member.do?work_div=do_retrieve';");
        		msg = "삭제되었습니다.";
        	}else{
        		msg = "삭제 실패.";
        	}
        	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
        	//03.3 gsonString:{"msgId":"1","msgContents":"삭제되었습니다.","total":0,"num":0}
        	LOG.debug("03.3 gsonString:"+gsonString);
        	out.print(gsonString);
    }
    
    public void do_selectOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	   	LOG.debug("03.1 do_selectone");
	   	MovieDetailVO inVO=new MovieDetailVO(); //
	   	
	   	String docid = StringUtil.nvl(request.getParameter("docid"),"");
	   	
	  
   // 	String seq = StringUtil.nvl(request.getParameter("seq"),"");
    	
   // 	inVO.setSeq(seq);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	MovieDetailVO outVO = (MovieDetailVO) this.movieDetailService.do_selectOne(inVO);
    	LOG.debug("03.3 outVO:"+outVO);
    	request.setAttribute("vo", outVO);
    	//code

    	//--code
		
    	RequestDispatcher dispatcher =request.getRequestDispatcher("/detail/detail_list.jsp");
    	dispatcher.forward(request, response);
		
	}
    
    protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//기능 : do_retrieve,do_insert,do_update,do_selectone,do_retrieve
    	//work_div
    	LOG.debug("02 doServiceHandler()");
    	request.setCharacterEncoding("UTF-8");
    	//work_div:read
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"),"");
    	LOG.debug("02.1 workDiv:"+workDiv);
    	
    	/* do_retrieve:목록
    	 * do_insert:등록
    	 * do_update:수정
    	 * do_selectone:단건조회
    	 * do_delete:
    	 */
    	switch(workDiv){
    	    //등록화면으로 이동
		 	
    		case "do_insert":
			do_insert(request,response);
    		break; 
    		
/*    		case "do_update":
    			do_update(request,response);
    		break; */ 
    		
/*    		case "do_delete":
    			do_delete(request,response);
    		break;  */ 
    		
/*    		case "do_selectOne":
    			do_selectOne(request,response);
    		break;*/
    		
   		
    		case "do_retrieve":
    			do_retrieve(request,response);
    		break;      		
    	}
    	
   }   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 hrMemberService:"+movieDetailService);
		doServiceHandler(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 hrMemberService:"+movieDetailService);	
		doServiceHandler(request, response);
	}

}
