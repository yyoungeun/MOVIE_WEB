<%--
  /**
  * @Class Name : useOutObject.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2019.07.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2019.07.01
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="com.sunday.movieregister.SearchVO"%>
<%@page import="com.sunday.movieregister.MovieRegisterVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	//화면목록
	List<MovieRegisterVO> list = (List<MovieRegisterVO>)request.getAttribute("list");

	//Param
    String pageNum    = "1";
    String pageSize   = "10";
    
    // paging
    int maxNum = 0; // 총글수 -> 검색할 때마다 달라지므로 서버에서 받아와야 함
    int currPageNo = 1; // 현재 페이지 = pageNum
    int rowPerPage = 10; // 한 번에 보여질 글 수  = pageSize
    int bottomCount = 10; 
    String url ="/SUNDAY/movie_register/movie_register.do"; // 호출 url
    String scriptName ="doSearchPage"; // javascript 함수명
    
    
	SearchVO paramVO = (SearchVO)request.getAttribute("paramVO");
	//out.print(paramVO);
	
	if(null != paramVO){
		
	     pageNum    = paramVO.getPageNum()+"";
	     pageSize   = paramVO.getPageSize()+"";		
	     
	     LOG.debug("===========================");
	     LOG.debug("paramVO="+paramVO);
	     LOG.debug("===========================");
	     
	 	 rowPerPage = Integer.parseInt(pageSize);
		 currPageNo = Integer.parseInt(pageNum);
		 
		 // 총글수
		 maxNum = (Integer)request.getAttribute("totalCnt");
	}
	

	
%>    
<!DOCTYPE html>
<html>
<head>
  <style type="text/css">
   body {
    font-family: Arial, Verdana, sans-serif;
    color: #111111;}
   table {
    width: 90%;}
   th, td {
    padding: 7px 10px 10px 10px;}
   th {
    text-transform: uppercase;
    letter-spacing: 0.1em;
    font-size: 90%;
    border-bottom: 2px solid #111111;
    border-top: 1px solid #999;
    text-align: left;}
   tr.even {
    background-color: #efefef;}
   tr:hover {
    background-color: #c3e6e5;}
   .money {
    text-align: right;}
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/SUNDAY/js/jquery-ui.css" >
<title>등록 영화 관리</title>
<script src="/SUNDAY/js/jquery-1.12.4.js"></script>
<script src="/SUNDAY/js/jquery-ui.js"></script>
</script>
</head>
<body>
	<h3>등록 영화 관리</h3>
	<hr/>
	<!-- 버튼 -->
	<div>
		<input type="button" value="조회" onclick="javascript:do_retrieve();" />
		<input type="button" value="등록" onclick="javascript:do_save_move();" />
	</div>
	<br/>
	<!--// 버튼 -->
	
	
	<form name="searchFrm" action="/SUNDAY/movie_register/movie_register.do" method="post" >
		<input type="hidden"  name="work_div"   />
		<input type="hidden"  name="page_num"   />
		
		<!-- data list -->
		<table id="listTable">
			<thead>
				<tr>
					<th>등록번호</th>   
				    <th>제목</th>    
				    <th>영제</th> 
				    <th>장르</th>    
				    <th>감독이름</th>  
				    <th>감독코드</th>  
				    <th>국가</th> 
				    <th>배우이름</th>  
				    <th>배우코드</th>  
				    <th>키워드</th> 
				    <th>포스터</th>
				    <th>개봉일</th>   
				    <th>제작년도</th>
				    <th>평점</th> 
				    <th>줄거리</th> 
				    <th>등록자ID</th> 
				    <th>등록일</th>   
				    <th>수정자ID</th> 
				    <th>수정일</th>
				    <th>현재상영중</th>
					<th>기분</th>
					<th>날씨</th>
					<th>시간</th>
					<th>사람</th>				
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && list.size()>0){
						for(MovieRegisterVO vo :list){
				%>
						<tr>
							<td><%=vo.getDOCID() %></td>
							<td><%=vo.getTitle() %></td>
							<td><%=vo.getTitleEng() %></td>
							<td><%=vo.getGenre() %></td>
							<td><%=vo.getDirectorNm() %></td>
							
							<td><%=vo.getDirectorId() %></td>
							<td><%=vo.getNation() %></td>
							<td><%=vo.getActorNm() %></td>
							<td><%=vo.getActorId() %></td>
							<td><%=vo.getKeywords() %></td>
							
							<td><img src="<%=vo.getPosters() %>" /></td>
							<td><%=vo.getReleaseDate() %></td>
							<td><%=vo.getProdYear() %></td>
							<td><%=vo.getRate() %></td>
							<td><%=vo.getPlot() %></td>
							
							<td><%=vo.getRegId() %></td>
							<td><%=vo.getRegDt() %></td>
							<td><%=vo.getModId() %></td>
							<td><%=vo.getModDt() %></td>
							<td><%=vo.getNowShowing() %></td>
							
							<td><%=vo.getRecM() %></td>
							<td><%=vo.getRecW() %></td>
							<td><%=vo.getRecT() %></td>
							<td><%=vo.getRecP() %></td>
						</tr>
				<%	
					    }//--for 
					}else{  
				%>
						<tr>
							<td colspan="99">No data found</td>
						</tr>
				
				<%	}//--else %>
			</tbody>
		</table>
		
		<!--// data list -->
		
		<!-- paging -->
		<div>
			<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// paging -->
		
		
	</form>
	

    
	<script>
		//page 이동
		function doSearchPage(url, page_num) {
			//alert(url+","+page_num);
			var frm = document.searchFrm;
			frm.work_div.value = "do_retrieve";
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
			
		}
	
	
		//등록화면으로 이동
		function do_save_move(){
			//alert('do_save_move');
			if(confirm("등록 하시겠습니까?")==false)return;
			
			var frm = document.searchFrm;
			frm.work_div.value = "do_save_move";
			frm.action  = '/SUNDAY/movie_register/movie_register.do';
			frm.submit();
		}
		
		
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.searchFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/SUNDAY/movie_register/movie_register.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
	
		//수정화면으로 이동: servlet -> movie_register_mng.jsp
		$("#listTable>tbody").on("click","tr",function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var docid = cTds.eq(0).text();
			console.log("docid:"+docid);
			
			var frm = document.searchFrm;
			frm.work_div.value ="do_selectone";
			frm.action = '/SUNDAY/movie_register/movie_register.do?docid='+docid;
			frm.submit();
			
		});
		
		$("#search_word").keypress(function(e) {
			//console.log("search_word: " + e);
			if(e.which == 13) {// enter
				do_retrieve();
			}
		});
		
		
		$(document).ready(function(){
		});
		
		
	</script>
</body>
</html>