package com.sunday.movieregister;

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

@WebServlet(description = "영화 등록/수정/삭제/조회", urlPatterns = { "/movie_register/movie_register.do" })
public class MovieRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Logger 사용
	private final Logger LOG = Logger.getLogger(MovieRegisterService.class);
	
	private MovieRegisterService service;
       
	// 기본 생성자
    public MovieRegisterController() {
    	// 서비스는 처음 한번만 호출
    	LOG.debug("0========================");
        service = new MovieRegisterService();
        LOG.debug("=service: " + service);
        LOG.debug("0========================");
        
    }//--MovieRegisterController()
    
    public void doServiceHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	
    	// 기능: do_retrieve, do_insert, do_update, do_selectone, do_delete
    	
    	// work_div
    	LOG.debug("02 doServiceHandler()");
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	// work_div: read
    	String wordDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	LOG.debug("02.1 work_div: " + wordDiv);
    	
    	switch (wordDiv) {
    	
			// 조회화면으로 이동
			case "do_move_to_list":
				do_move_to_list(request, response);
			break;
			
			// 등록화면으로 이동
			case "do_save_move":
				do_save_move(request, response);
			break;
		
			case "do_insert":
				do_insert(request, response);
			break;
			
			case "do_update":
				do_update(request, response);
			break;
			
			case "do_delete":
				do_delete(request, response);
			break;
			
			case "do_selectone":
				do_selectone(request, response);
			break;
			
			case "do_retrieve":
				do_retrieve(request, response);
			break;
	
			default:
				break;
				
    	}//--switch
    	
    }//--doServiceHandler


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doServiceHandler(request, response);
		
	}//--doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doServiceHandler(request, response);
		
	}//--doPost
	
	
	/** 등록 */
	public void do_insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	LOG.debug("03.1 do_insert: ");
    	MovieRegisterVO inVO = new MovieRegisterVO();
    	
    	// 입력받은 값을 얻어옴
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");
    	String title = StringUtil.nvl(request.getParameter("title"), "");
    	String titleEng = StringUtil.nvl(request.getParameter("title_eng"), "");
    	String genre = StringUtil.nvl(request.getParameter("genre"), "");
    	String directorNm = StringUtil.nvl(request.getParameter("director_nm"), "");
    	
    	int directorId = Integer.parseInt(request.getParameter("director_id"));
    	String nation = StringUtil.nvl(request.getParameter("nation"), "");
    	String actorNm = StringUtil.nvl(request.getParameter("actor_nm"), "");
    	int actorId = Integer.parseInt(request.getParameter("actor_id"));
    	String keywords = StringUtil.nvl(request.getParameter("keywords"), "");
    	
    	String posters = StringUtil.nvl(request.getParameter("posters"), "");
    	String releaseDate = StringUtil.nvl(request.getParameter("release_date"), "");
    	String prodYear = StringUtil.nvl(request.getParameter("prod_year"), "");
    	double rate = Double.parseDouble(request.getParameter("rate"));
    	String plot = StringUtil.nvl(request.getParameter("plot"), "");
    	
    	String regId = StringUtil.nvl(request.getParameter("reg_id"), "");
    	String regDt = StringUtil.nvl(request.getParameter("reg_dt"), "");
    	String modId = StringUtil.nvl(request.getParameter("mod_id"), "");
    	String modDt = StringUtil.nvl(request.getParameter("mod_dt"), "");
    	String nowShowing = StringUtil.nvl(request.getParameter("now_showing"), "");
    	
    	int recM = Integer.parseInt(request.getParameter("rec_m"));
    	int recW = Integer.parseInt(request.getParameter("rec_w"));
    	int recT = Integer.parseInt(request.getParameter("rec_t"));
    	int recP = Integer.parseInt(request.getParameter("rec_p"));
    	
    	// MovieRegisterVO 객체에 set
    	inVO.setDOCID(docid);
    	inVO.setTitle(title);
    	inVO.setTitleEng(titleEng);
    	inVO.setGenre(genre);
    	inVO.setDirectorNm(directorNm);
    	
    	inVO.setDirectorId(directorId);
    	inVO.setNation(nation);
    	inVO.setActorNm(actorNm);
    	inVO.setActorId(actorId);
    	inVO.setKeywords(keywords);
    	
    	inVO.setPosters(posters);
    	inVO.setReleaseDate(releaseDate);
    	inVO.setProdYear(prodYear);
    	inVO.setRate(rate);
    	inVO.setPlot(plot);
    	
    	inVO.setRegId(regId);
    	inVO.setRegDt(regDt);
    	inVO.setModId(modId);
    	inVO.setModDt(modDt);
    	inVO.setNowShowing(nowShowing);
    	
    	inVO.setRecM(recM);
    	inVO.setRecW(recW);
    	inVO.setRecT(recT);
    	inVO.setRecP(recP);
 
    	// setting한 inVO의 값들을 출력해봄
    	LOG.debug("03.2 param: " + inVO);
    	
    	// inVO 값을 입력하고 성공여부를 반환받음: controller -> service -> dao -> service -> controller
    	int flag = service.do_insert(inVO);
    	LOG.debug("03.3 flag: " + flag);
    	
    	Gson gson = new Gson();
    	// 응답할 문서 타입과 인코딩 방식을 지정
    	response.setContentType("text/html;charset=utf-8");
    	// 응답 문서에 출력하기 위한 PrintWriter 객체를 가지고 옴
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString = "";
    	
    	if(flag > 0) {
    		msg = inVO.getDOCID()+"이(가)\n등록되었습니다.";
    	}
    	else {
    		msg = "등록실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString: " + gsonString);
    	out.print(gsonString);
		
	}//--do_insert
	
	/** 수정 */
	public void do_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	LOG.debug("03.1 do_update: ");
    	MovieRegisterVO inVO = new MovieRegisterVO();
    	
    	// 수정받은 값을 얻어옴
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");
    	String title = StringUtil.nvl(request.getParameter("title"), "");
    	String titleEng = StringUtil.nvl(request.getParameter("title_eng"), "");
    	String genre = StringUtil.nvl(request.getParameter("genre"), "");
    	String directorNm = StringUtil.nvl(request.getParameter("director_nm"), "");
    	
    	int directorId = Integer.parseInt(request.getParameter("director_id"));
    	String nation = StringUtil.nvl(request.getParameter("nation"), "");
    	String actorNm = StringUtil.nvl(request.getParameter("actor_nm"), "");
    	int actorId = Integer.parseInt(request.getParameter("actor_id"));
    	String keywords = StringUtil.nvl(request.getParameter("keywords"), "");
    	
    	String posters = StringUtil.nvl(request.getParameter("posters"), "");
    	String releaseDate = StringUtil.nvl(request.getParameter("release_date"), "");
    	String prodYear = StringUtil.nvl(request.getParameter("prod_year"), "");
    	double rate = Double.parseDouble(request.getParameter("rate"));
    	String plot = StringUtil.nvl(request.getParameter("plot"), "");
    
    	String modId = StringUtil.nvl(request.getParameter("mod_id"), "");
    	String modDt = StringUtil.nvl(request.getParameter("mod_dt"), "");
    	String nowShowing = StringUtil.nvl(request.getParameter("now_showing"), "");
    	
    	int recM = Integer.parseInt(request.getParameter("rec_m"));
    	int recW = Integer.parseInt(request.getParameter("rec_w"));
    	int recT = Integer.parseInt(request.getParameter("rec_t"));
    	int recP = Integer.parseInt(request.getParameter("rec_p"));
    	
    	// MovieRegisterVO 객체에 set
    	inVO.setDOCID(docid);
    	inVO.setTitle(title);
    	inVO.setTitleEng(titleEng);
    	inVO.setGenre(genre);
    	inVO.setDirectorNm(directorNm);
    	
    	inVO.setDirectorId(directorId);
    	inVO.setNation(nation);
    	inVO.setActorNm(actorNm);
    	inVO.setActorId(actorId);
    	inVO.setKeywords(keywords);
    	
    	inVO.setPosters(posters);
    	inVO.setReleaseDate(releaseDate);
    	inVO.setProdYear(prodYear);
    	inVO.setRate(rate);
    	inVO.setPlot(plot);
    	
    	inVO.setModId(modId);
    	inVO.setModDt(modDt);
    	inVO.setNowShowing(nowShowing);
    	
    	inVO.setRecM(recM);
    	inVO.setRecW(recW);
    	inVO.setRecT(recT);
    	inVO.setRecP(recP);
    	
    	// setting한 inVO의 값들을 출력해봄
    	LOG.debug("03.2 param: " + inVO);
    	
    	// inVO 값을 수정하고 성공여부를 반환받음: controller -> service -> dao -> service -> controller
    	int flag = this.service.do_update(inVO);
    	LOG.debug("03.3 flag: " + flag);
    	
    	Gson gson = new Gson();
    	// 응답할 문서 타입과 인코딩 방식을 지정
    	response.setContentType("text/html;charset=utf-8");
    	// 응답 문서에 출력하기 위한 PrintWriter 객체를 가지고 옴
    	PrintWriter out = response.getWriter();
    	
    	String msg = "";
    	String gsonString = "";
    	if(flag > 0) {
    		msg = inVO.getDOCID()+"이(가)\n수정되었습니다.";
    	}
    	else {
    		msg = "수정실패";
    	}
    	
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.4 gsonString: " + gsonString);
    	out.print(gsonString);
    	
	}//--do_update
	
	/** 삭제 */
	public void do_delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	LOG.debug("03.1 do_delete");
   
    	MovieRegisterVO inVO = new MovieRegisterVO();
    	
    	// 삭제하려는 영화의 등록번호를 가져와서
    	String docid = StringUtil.nvl(request.getParameter("docid"),"");
    	// MovieRegisterVO의 객체에 그 docid를 set함
    	inVO.setDOCID(docid);
    	
    	// inVO에 있는  docid를 가진 영화를 삭제하고 성공여부를 반환받음: controller -> service -> dao -> service -> controller
    	int flag =  this.service.do_delete(inVO);
    	LOG.debug("03.2 flag:"+flag);
    	
    	Gson gson = new Gson();
    	// 응답할 문서 타입과 인코딩 방식을 지정
    	response.setContentType("text/html;charset=utf-8");
    	// 응답 문서에 출력하기 위한 PrintWriter 객체를 가지고 옴
    	PrintWriter out = response.getWriter();
    	String msg = "";
    	String gsonString = "";
    	
    	// msgId = flag
    	// msgContents = '삭제되었습니다.';
    	
    	if(flag>0){
    		
    		msg = "삭제되었습니다.";
    	}
    	else {
    		
    		msg = "삭제 실패()";
    	}
    	gsonString = gson.toJson(new MessageVO(String.valueOf(flag), msg));
    	LOG.debug("03.3 gsonString:"+gsonString);
    	out.print(gsonString);
		
	}//--do_delete
	
	/** 단건 조회 */
	public void do_selectone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	LOG.debug("03.1 do_selectone: ");
    	MovieRegisterVO inVO = new MovieRegisterVO();
    	
    	String docid = StringUtil.nvl(request.getParameter("docid"), "");
    	
    	inVO.setDOCID(docid);
    	LOG.debug("03.2 : inVO: " + inVO);
    	
    	MovieRegisterVO outVO = (MovieRegisterVO) this.service.do_selectOne(inVO);
    	LOG.debug("03.3 : outVO: " + outVO);
    	
    	request.setAttribute("vo", outVO);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/movie_register/movie_register_mng.jsp");
    	dispatcher.forward(request, response);
		
	}//--do_selectone
	
	/** 전체 조회 */
	public void do_retrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("03.1 do_retrieve: ");
    	SearchVO inVO = new SearchVO();
    	
    	String pageNum = StringUtil.nvl(request.getParameter("page_num"), "1");
    	
    	inVO.setPageNum(Integer.parseInt(pageNum));
 
    	LOG.debug("03.2 inVO: " + inVO);
   
    	// inVO에 설정한 검색 페이지와 페이지 사이즈를 전달하여 영화를 전체 조회하고 리스트로 반환받음
    	// 	: controller -> service -> dao -> service -> controller
    	List<MovieRegisterVO> list = (List<MovieRegisterVO>)this.service.do_retrieve(inVO);
    	
    	// 검색된 영화를 하나씩 출력함
    	LOG.debug("--------------------");
    	for(MovieRegisterVO vo : list) {
    		LOG.debug(vo);
    	}
    	LOG.debug("--------------------");
    	
    	// 총글수
    	int totalCnt = 0;
    	
    	// 조회된 영화 수 출력
    	if(null != list && list.size() > 0) {
    		
    		MovieRegisterVO totalVO = list.get(0);
    		totalCnt = totalVO.getTotal();
    	}
    	
    	request.setAttribute("totalCnt", totalCnt); // 조회된 전체 영화 수 
    	request.setAttribute("list", list); // 페이징한 영화(10개씩 나눈 것 중 1페이지)
    	request.setAttribute("paramVO", inVO); // 페이징 정보
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/movie_register/movie_register_list.jsp");
    	dispatcher.forward(request, response);
		
	}//--do_retrieve
	
	/** 조회 이동 */
	public void do_move_to_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("03. do_move_to_list");
		RequestDispatcher dispatcher =request.getRequestDispatcher("/movie_register/movie_register.do?work_div=do_retrieve");
		dispatcher.forward(request, response); 
		
	}//--do_move_to_list
	
	/** 등록 이동 */ 
	public void do_save_move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =request.getRequestDispatcher("/movie_register/movie_register_mng.jsp");
		dispatcher.forward(request, response); 
		
	}//--do_save_move

}//--class
