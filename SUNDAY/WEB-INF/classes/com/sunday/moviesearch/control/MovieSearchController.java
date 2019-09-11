package com.sunday.moviesearch.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunday.cmn.StringUtil;
import com.sunday.movie.domain.MovieVO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.dao.MovieSearchService;
import com.sunday.moviesearch.domain.MovieSearchVO;
import com.sunday.moviesearch.domain.SearchBarVO;

/**
 * Servlet implementation class MovieSearchController
 */
@WebServlet("/movie_search.do")
public class MovieSearchController extends HttpServlet {

	private final Logger LOG = Logger.getLogger(MovieSearchController.class);
	private MovieSearchService movieSearchService;
	       
    public MovieSearchController() {
    	movieSearchService = new MovieSearchService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 movieSearchService: "+ movieSearchService);
		doServiceHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 movieSearchService: "+ movieSearchService);
		doServiceHandler(request, response);
	}
	
	protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("02 doServiceHandler()");
		request.setCharacterEncoding("UTF-8");
		
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		LOG.debug("workDiv:"+workDiv);
		
		switch(workDiv){
			
			case "do_retrieve":
				do_retrieve(request, response);
			break;
			
			case "do_selectone":
				do_selectone(request, response);
			break;

		}
	}
	
	protected void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("03.1 do_retrieve()");
    	response.setContentType("text/html;charset=utf-8");
    	SearchBarVO inVO = new SearchBarVO();
    	
    	String pageSize   = StringUtil.nvl(request.getParameter("page_size"), "10");
    	String pageNum    = StringUtil.nvl(request.getParameter("page_num"), "1");
    	String searchDiv  = StringUtil.nvl(request.getParameter("search_div"),"");
    	String searchWord = StringUtil.nvl(request.getParameter("search_word"), ""); 
    	
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setSearchDiv(searchDiv);
    	inVO.setSearchWord(searchWord);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	List<MovieSearchVO> list = movieSearchService.do_retrieve(inVO);
    	
    	LOG.debug("---------------------------");
    	for(MovieSearchVO vo : list){
    		LOG.debug(vo);
    	}
    	LOG.debug("---------------------------");
    	
    	int totalCnt=0; //총글수
    	if(list.size()>0 && list!=null){
    		MovieSearchVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/movie_search/movie_search.jsp");
    	dispatcher.forward(request, response);
	}
	
	protected void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("03.1 do_selectone()");
    	MovieDetailVO inVO = new MovieDetailVO();
 
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");    	
    	inVO.setDocid(docid);
    	
    	LOG.debug("03.2 inVO:"+inVO);
    	
    	MovieDetailVO outVO = movieSearchService.do_selectOne(inVO);
    	
    	LOG.debug("03.3 outVO:"+outVO);
    	
    	request.setAttribute("vo", outVO);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/detail/detail_list.jsp");//임시로 적어 놓은 것
    	dispatcher.forward(request, response);    	
	}

}
