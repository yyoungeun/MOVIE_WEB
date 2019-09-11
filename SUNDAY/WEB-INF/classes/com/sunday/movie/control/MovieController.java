package com.sunday.movie.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sunday.cmn.StringUtil;
import com.sunday.movie.dao.MovieService;
import com.sunday.movie.domain.MovieVO;
import com.sunday.movie.domain.SearchVO;
import com.sunday.moviedetail.dao.MovieDetailVO;
import com.sunday.moviesearch.domain.MovieSearchVO;
import com.sunday.moviesearch.domain.SearchBarVO;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("/movie.do")
public class MovieController extends HttpServlet {
	
	private final Logger LOG = Logger.getLogger(MovieController.class);
	private MovieService movieService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
    	movieService = new MovieService();
    }

    protected void do_change_language(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_language_change");
    	response.setContentType("text/html;charset=utf-8");
    	String lang = StringUtil.nvl(request.getParameter("language"), "ko");
    	LOG.debug("03.1.1.lang:"+lang);
    	request.setAttribute("lang", lang);
    	HttpSession session = request.getSession();
    	session.setAttribute("lang", lang);
    	//RequestDispatcher dispatcher = request.getRequestDispatcher("/movie/movie_ranking.jsp");
    	//dispatcher.forward(request, response);
    }
    
    protected void do_search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_search()");
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
    	List<MovieSearchVO> list = movieService.do_search(inVO);
    	
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
    	List<MovieVO> list = movieService.do_retrieve(inVO);
    	
    	LOG.debug("---------------------------");
    	for(MovieVO vo : list){
    		LOG.debug(vo);
    	}
    	LOG.debug("---------------------------");
    	
    	int totalCnt=0; //총글수
    	if(list.size()>0 && list!=null){
    		MovieVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	request.setAttribute("totalCnt", totalCnt);
    	request.setAttribute("list", list);
    	request.setAttribute("paramVO", inVO);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/movie/movie_ranking.jsp");//임시로 적은 것!!!
    	dispatcher.forward(request, response);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doGet()");
		LOG.debug("01.1 movieService: "+ movieService);
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("01 doPost()");
		LOG.debug("01.1 movieService: "+ movieService);
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
		
		case "do_search":
			do_search(request, response);
		break;
		
		case "do_change_language":
			do_change_language(request, response);
		break;

		}
	}

}
