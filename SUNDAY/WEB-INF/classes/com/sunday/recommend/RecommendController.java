package com.sunday.recommend;

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
import com.sunday.cmn.StringUtil;

@WebServlet(description = "영화 추천", urlPatterns = { "/recommend/recommend.do" })
public class RecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger LOG = Logger.getLogger(RecommendService.class);
       
	private RecommendService service;

	// 기본 생성자
    public RecommendController() {
        
        // 서비스는 처음 한 번만 호출
		LOG.debug("0========================");
		service = new RecommendService();
		LOG.debug("=service: " + service);
		LOG.debug("0========================");
    }

    public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    	// work_div
    	LOG.debug("02. doServiceHandler()");
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	// work_div: read
    	String wordDiv = StringUtil.nvl(request.getParameter("work_div"), ""); // work_div의 값을 받아옴
    	LOG.debug("02.1 work_div: " + wordDiv);
    	
    	do_retrieve(request, response); // request와 response 객체를 전달하면서 do_retrieve() 함수 호출
	
    }//--doServiceHandler

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doServiceHandler(request, response);
		
	}//--doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doServiceHandler(request, response);
		
	}//--doPost
	
	
	public void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("03.1 do_retrieve: ");
    	RecommendVO inVO = new RecommendVO();
    	
    	// 페이지 번호와 페이지 사이즈를 가져옴(없으면 각각 1, 10으로 함)
    	String pageNum = StringUtil.nvl(request.getParameter("page_num"), "1");
    	String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");
    	// 추천 코드를 가져옴
    	String recM = StringUtil.nvl(request.getParameter("rec_m"), "0");
    	String recW = StringUtil.nvl(request.getParameter("rec_w"), "0");
    	String recT = StringUtil.nvl(request.getParameter("rec_t"), "0");
    	String recP = StringUtil.nvl(request.getParameter("rec_p"), "0");
    	
    	// 가져온 값을 RecommendVO 객체의 변수의 값으로 설정함
    	inVO.setPageNum(Integer.parseInt(pageNum));
    	inVO.setPageSize(Integer.parseInt(pageSize));
    	inVO.setRecM(Integer.parseInt(recM));
    	inVO.setRecW(Integer.parseInt(recW));
    	inVO.setRecT(Integer.parseInt(recT));
    	inVO.setRecP(Integer.parseInt(recP));
    	
    	// RecommendVO 객체 출력
    	LOG.debug("03.2 inVO: " + inVO); 
   
    	// RecommendVO 객체를 전달하면서, service의 do_retrieve() 함수를 호출
    	// service -> dao -> service -> controller
    	// 전달된 객체의 추천코드에 해당하는 영화를 반환받아 리스트에 저장
    	List<RecommendVO> list = (List<RecommendVO>)this.service.do_retrieve(inVO);
    	
    	// 검색된 영화를 담고 있는 list의 내용을 모두 출력
    	LOG.debug("--------------------");
    	for(RecommendVO vo : list) {
    		LOG.debug(vo);
    	}
    	LOG.debug("--------------------");
    	
    	// 총글수
    	int totalCnt = 0;
    	
    	// 리스트에 값이 있으면
    	if(null != list && list.size() > 0) {
    		
    		RecommendVO totalVO = list.get(0); // 리스트의 첫 번째 요솟값을  RecommendVO 타입의 변수에 저장
    		totalCnt = totalVO.getTotal(); // 총 글 수를 반환받음
    	}
    	
    	// setAttribute(String name, Object value): 이름이 name인 속성의 값을 value로 지정
    	request.setAttribute("totalCnt", totalCnt); // 총 글 수
    	request.setAttribute("list", list); // 검색된 영화들
    	request.setAttribute("paramVO", inVO); // 검색 조건
    	
    	// request를 recommend_list.jsp에 전달
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/recommend/recommend_list.jsp");
    	dispatcher.forward(request, response);
		
	}//--do_retrieve
	

}//--class