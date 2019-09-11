<%@page import="com.sunday.movie.domain.SearchVO"%>
<%@page import="com.sunday.moviedetail.dao.MovieDetailVO"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.sunday.movie.domain.MovieVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/header.jsp" %>
<style type="text/css">
	.listTable{
		width  : 800px;
		margin-bottom: 15px;
	}
	
	#poster{
		padding:1px;
	}
</style>

<%
	Logger LOG = Logger.getLogger(this.getClass());
	MovieVO movieVO = (MovieVO) request.getAttribute("vo");
	
	//화면목록
	List<MovieVO> list = (List<MovieVO>) request.getAttribute("list");
	
	//param
	String pageSize = "10";
	String pageNum = "1";
	String searchDiv = "";
	String searchWord = "";
	
	//paging
    int maxNum			= 0; 							//총 글 수(server에서 받아와야 하는 값)
    int currPageNo		= 1;							//현재페이지(pageNum과 같음)
    int rowPerPage		= 10;							//row수(pageSize와 같음)
    int bottomCount		= 10;							//bottomCount(고정)
    String url			= "/SUNDAY/movie.do";			//호출URL
    String scriptName	= "do_search_page";				//Javascript함수명

	SearchVO paramVO = (SearchVO) request.getAttribute("paramVO");
	
	if(null!=paramVO){
		pageNum = paramVO.getPageNum()+"";
		pageSize = paramVO.getPageSize()+"";
		searchDiv = paramVO.getSearchDiv();
		searchWord = paramVO.getSearchWord();
		LOG.debug("---------------------------");
		LOG.debug("paramVO:"+paramVO);
		LOG.debug("---------------------------");
		rowPerPage=Integer.parseInt(pageSize);
		currPageNo=Integer.parseInt(pageNum);
		maxNum = (Integer) request.getAttribute("totalCnt");
	}	
	
	//out.print(paramVO);
%>
 <html>
 <body>
  <!--====================================================================================
    									header 끝
 	======================================================================================-->
  
	<hr/>
	<div>
		<form name="selectMovie" action="/SUNDAY/movie_detail.do" method="get">
			<input type="hidden" name="work_div"/>
			<input type="hidden" name="search_word"/>	
			<input type="hidden" name="page_num" />
		</form>
	
		<form name="movieFrm" action="/SUNDAY/movie.do" method="get">
			<input type="hidden" name="work_div" />
			<input type="hidden" name="page_size" />
			<input type="hidden" name="page_num" />
			<input type="hidden" name="search_div"  value="${paramVO.getSearchDiv()}"/>
			<input type="hidden" name="search_word" value="${paramVO.getSearchWord()}"/>
			</form>	
	<!--====================================================================================
    									여기서부터 subMenu
 	======================================================================================-->
 	
	<div class="container-fluid" style="margin-left:40px;">
		<div class="row">
			<div class="dropdown no-arrow mb-4" style="margin-right: 5px;">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="now_showing">
		                      현재상영중
				</button>
			</div>
		 	<div class="dropdown mb-4" style="margin-right: 5px;" id="genre">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true">
		                      장르
				</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="#">코메디</a>
						<a class="dropdown-item" href="#">액션</a>
						<a class="dropdown-item" href="#">드라마</a>
						<a class="dropdown-item" href="#">공포</a>
						<a class="dropdown-item" href="#">판타지</a>
						<a class="dropdown-item" href="#">SF</a>
						<a class="dropdown-item" href="#">멜로</a>
						<a class="dropdown-item" href="#">스릴러</a>
						<a class="dropdown-item" href="#">아동</a>
					</div>
			</div>
			<div class="dropdown mb-4" style="margin-right: 5px;" id="nation">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                      국가
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="#">국내영화</a>
					<a class="dropdown-item" href="#">해외영화</a>
				</div>
			</div>
			<div class="dropdown mb-4" style="margin-right: 5px;" id="prod_year">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                      연도
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" id="1970" href="#">1970년도</a>
					<a class="dropdown-item" id="1980" href="#">1980년도</a>
					<a class="dropdown-item" id="1990" href="#">1990년도</a>
					<a class="dropdown-item" id="2000" href="#">2000년도</a>
					<a class="dropdown-item" id="2010" href="#">2010년도</a>
				</div>
			</div>
			<div class="dropdown no-arrow mb-4" style="margin-right: 5px;">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="recent">
		                      최신등록순
				</button>
			</div> 
		</div>

 	</div>
   
	<!--====================================================================================
    										//subMenu
 	======================================================================================-->
	
	<!--====================================================================================
    										목록조회결과
 	======================================================================================-->
	
	<br/>
	<%
		if(null != list && list.size()>0){
			for(MovieVO vo :list){
	%>			
				<div class="card mb-3 py-0 border-left-info" style="margin: auto; margin-left:50px; width:900px; height:260px;">
			    	<div class="card-body" style="cursor:pointer;">
			        	 <table class="listTable"> 
							<tr>
								<td rowspan="8" style="width:170px; align:center;" id="poster">
								<img src=<%=StringUtil.nvl(vo.getPosters(), "/SUNDAY/movie/noimage.jpg") %> width="160" height="210" style="align:center; padding-right: 10px;"/>
								<label style="display:none;" class="docid"><%=StringUtil.nvl(vo.getDOCID(), " ") %></label>
								</td>
								<td style="font-size: 8pt; height:10px;"><%=StringUtil.nvl(vo.getNum(), " ") %>위<br/></td>
							</tr>
							<tr>
								<td style="padding-top: 10px;"><h5><b><%=StringUtil.nvl(vo.getTitle(), " ") %></b></h5></td>
							</tr>
							<tr>
								<td>장르: <%=StringUtil.nvl(vo.getGenre()," ") %></td>
							</tr>
							<tr>
								<td>국가: <%=StringUtil.nvl(vo.getNation()," ") %></td>
							</tr>
							<tr>
								<td>감독: <%=StringUtil.nvl(vo.getDirectorNm()," ") %></td>
							</tr>
							<tr>
								<td>제작년도: <%=StringUtil.nvl(vo.getProdYear(), " ") %></td>
							</tr>
							<tr>
								<td>개봉일: <%=StringUtil.nvl(vo.getReleaseDate(), " ") %></td>
							</tr>
							<tr>
								<td>
									<select class="stars"> 
					           			<option value=""></option> 
					           			<option value="1" <%if(vo.getRate()==1)out.print("selected"); %>>1</option> 
					           			<option value="2" <%if(vo.getRate()==2)out.print("selected"); %>>2</option> 
					           			<option value="3" <%if(vo.getRate()==3)out.print("selected"); %>>3</option> 
					           			<option value="4" <%if(vo.getRate()==4)out.print("selected"); %>>4</option> 
					           			<option value="5" <%if(vo.getRate()==5)out.print("selected"); %>>5</option>
				           			</select>
			           			</td>
							</tr>
						</table>
			        </div>
			    </div>
	                     
	                     
	<%	
			}//--for 
		}else{  
	%>
		<div class="card mb-3 py-0 border-left-info" style="margin: auto; margin-left:50px; width:900px; height:250px;">
			<div class="card-body">
				NO DATA FOUND
			</div>
		</div>
	<%	}//--else %>
	

	
	<!--====================================================================================
    									//목록조회 끝
 	======================================================================================-->

	<!--====================================================================================
    										Paging
 	======================================================================================-->
	
	<div style="float:left; margin-left: 400px;">
		<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
	</div>
	
	<!--====================================================================================
    										//Paging
 	======================================================================================-->
	
	
	<script>

		//목록조회
		function do_retrieve(){
			//alert('retrieve');
			var frm = document.movieFrm;
			frm.work_div.value='do_retrieve';
			frm.page_num.value='1';
			frm.action = '/SUNDAY/movie.do';
			
			frm.submit();
		}
		
		//페이징
		function do_search_page(url, page_num){
			//alert(url+","+page_num);
			var frm = document.movieFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
		}
		
		//제목 클릭 시 단건조회
		$(".listTable").on("click", function(){
			var frm = document.selectMovie;
			var docid = $(this).find(".docid").text();
			//alert(docid);
			frm.search_word.value = docid;
			frm.page_num.value='1';
			frm.work_div.value = "do_retrieve";
			frm.submit();
		});
		
		//별점 설정
		$('.stars').barrating({ theme: 'fontawesome-stars' , readonly: true });
		
	//=========================================================================================
	//============================================메뉴===========================================
	//=========================================================================================

			/*검색조건 설정 
			 *상영중	= 10
			 *장르	= 20
			 *국내영화	= 30
			 *연도	= 40
			 *최신순	= 50
			 *해외영화	= 60
			*/
		
		//현재상영중 클릭 -> 조회
		$("#now_showing").on("click", function(){
			var frm = document.movieFrm;
			frm.search_div.value='10';
			do_retrieve();
		});
		
		//최신등록순 클릭 -> 조회
		$("#recent").on("click", function(){
			var frm = document.movieFrm;
			frm.search_div.value='50';
			do_retrieve();
		});
		
		//장르 클릭 -> 조회
		$("#genre>.dropdown-menu>.dropdown-item").on("click", function(){
			var frm = document.movieFrm;
			var keyword = $(this).text();
			console.log(keyword);
			frm.search_div.value='20';
			frm.search_word.value=keyword;
			do_retrieve();
		});
		
		//국가 클릭 -> 조회
		$("#nation>.dropdown-menu>.dropdown-item").on("click", function(){
			var frm = document.movieFrm;
			var keyword = $(this).text();
			
			if(keyword=='국내영화'){
				frm.search_div.value='30';
			}else{
				frm.search_div.value='60';
			}
			
			frm.search_word.value='대한민국';
			do_retrieve();
		});
		
		//연도 클릭 -> 조회
		$("#prod_year>.dropdown-menu>.dropdown-item").on("click", function(){
			var frm = document.movieFrm;
			var keyword = $(this).attr("id");
			//alert(keyword);
			frm.search_div.value='40';
			frm.search_word.value=keyword;
			do_retrieve();
			
		});

	</script>
</body>
</html>

	<!--====================================================================================
    										footer 시작
 	======================================================================================-->

<%@ include file="/WEB-INF/footer.jsp" %>