<%@page import="com.sunday.moviesearch.domain.MovieSearchVO"%>
<%@page import="com.sunday.moviesearch.domain.SearchBarVO"%>
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
		padding:10px;
	}
</style>

<%
	Logger LOG = Logger.getLogger(this.getClass());
	MovieSearchVO movieVO = (MovieSearchVO) request.getAttribute("vo");
	
	//화면목록
	List<MovieSearchVO> list = (List<MovieSearchVO>) request.getAttribute("list");
	
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
    String url			= "/SUNDAY/movie_search.do";	//호출URL
    String scriptName	= "do_search_page";				//Javascript함수명

	SearchBarVO paramVO = (SearchBarVO) request.getAttribute("paramVO");
	
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
	
	MovieDetailVO detailVO = (MovieDetailVO) request.getAttribute("vo"); 
	
	//out.print(paramVO);
%>
 
  <!--====================================================================================
    									여기서 header 끝
 	======================================================================================-->
  
<html>
<body>
	<hr/>
	<div>
		<form name="selectMovie" action="/SUNDAY/movie_detail.do" method="get">
			<input type="hidden" name="work_div"/>
			<input type="hidden" name="search_word"/>	
		</form>
	
		<form name="movieFrm" action="/SUNDAY/movie_search.do" method="get">
			<input type="hidden" name="work_div" />
			<input type="hidden" name="page_size" />
			<input type="hidden" name="page_num" />
			<%-- <input type="hidden" name="search_div"  value="${paramVO.getSearchDiv()}"/> --%>
			<input type="hidden" name="search_word" value="${paramVO.getSearchWord()}"/>
		</form>	
	
	<!--====================================================================================
    										Result
 	======================================================================================-->
	
	<br/>

	<%
		if(null != list && list.size()>0){
			for(MovieSearchVO vo :list){
	%>			
				<div class="card mb-3 py-0 border-left-info" style="margin: auto; margin-left:50px; width:900px; height:250px;">
			    	<div class="card-body" style="cursor:pointer;">
			        	 <table class="listTable"> 
							<tr>
								<td rowspan="8" style="width:160px; align:center;" id="poster">
								<img src=<%=StringUtil.nvl(vo.getPosters(), "/SUNDAY/movie/noimage.jpg") %> width="140" height="190" style="align:center;"/>
								<label style="display:none;" class="docid"><%=StringUtil.nvl(vo.getDOCID(), " ") %></label>
								</td>
								<td><h6><%=StringUtil.nvl(vo.getNum(), " ") %>위</h6></td>
							</tr>
							<tr>
								<td>제목: <%=StringUtil.nvl(vo.getTitle(), " ") %></td>
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
    										//Result
 	======================================================================================-->

	<!--====================================================================================
    										Paging
 	======================================================================================-->
	
	<div style="padding:20px;">
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
			frm.search_word.value = docid;
			frm.work_div.value = "do_retrieve";
			frm.submit();
		});
		
		//별점 설정
		$('.stars').barrating({ theme: 'fontawesome-stars' , readonly: true });
	
	</script>

</body>
</html>
<%@ include file="/WEB-INF/footer.jsp" %>