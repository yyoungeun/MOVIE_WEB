package com.sunday.chart;

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
import com.google.gson.JsonArray;
import com.sunday.cmn.StringUtil;
import com.sunday.movie.dao.MovieDao;

/**
 * Servlet implementation class ChartController
 */
@WebServlet(description = "chart", urlPatterns = { "/chart.do" })
public class ChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger LOG = Logger.getLogger(this.getClass());
	private MovieDao movieDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartController() {
        super();
        movieDao = new MovieDao();
    }
    
    protected void do_change_language(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03.1 do_language_change");
    	response.setContentType("text/html;charset=utf-8");
    	String lang = StringUtil.nvl(request.getParameter("language"), "ko");
    	LOG.debug("03.1.1.lang:"+lang);
    	request.setAttribute("lang", lang);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/chart/chart.jsp");
    	dispatcher.forward(request, response);
    }
    
    protected void do_line_chart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.debug("03 do_pie_chart");
    	
    	MoviePerYearVO inVO = new MoviePerYearVO();
    	List<MoviePerYearVO> list = movieDao.do_moviePerYear(inVO);
    	LOG.debug("01 list:"+list);
    	
    	Gson gson = new Gson();
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	String gsonString = "";
    	
    	JsonArray jArray = new JsonArray();
    	for(int i=0; i<list.size(); i++){
    		JsonArray sArray = new JsonArray();
    		sArray.add(list.get(i).getYear());
    		sArray.add(list.get(i).getMoviePerYear());
    		
    		jArray.add(sArray);
    	}
    	gsonString = jArray.toString();
    	LOG.debug("gsonString:"+gsonString);
    	out.print(gsonString);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("1.doGet");
		doServiceHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("1.doPost");
		doServiceHandler(request, response);
	}
	
	protected void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("2.doServiceHandler");
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		LOG.debug("2.1 workDiv:"+workDiv);
		
		switch(workDiv){
			case "do_line_chart":
				do_line_chart(request, response);
			break;
			
			case "do_change_language":
				do_change_language(request, response);
			break;
		}
	}

}
