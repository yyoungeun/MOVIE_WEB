<%@page import="java.util.Random"%>
<%@page import="com.sunday.recommend.RecommendVO"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());

	// 검색된 영화
	List<RecommendVO> list = (List<RecommendVO>)request.getAttribute("list");
	//out.print(list);
	
	// Param
    String pageNum    = "1";
    String pageSize   = "10";
    String recM = "";
    String recW = "";
    String recT = "";
    String recP = "";
    
    // paging
    int maxNum = 0; // 총글수 -> 검색할 때마다 달라지므로 서버에서 받아와야 함
    int currPageNo = 1; // 현재 페이지 = pageNum
    int rowPerPage = 10; // 한 번에 보여질 글 수  = pageSize
    int bottomCount = 10; 
    String url ="/SUNDAY/recommend/recommend.do"; // 호출 url
    String scriptName ="doSearchPage"; // javascript 함수명
    
    
    RecommendVO paramVO = (RecommendVO)request.getAttribute("paramVO");
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
<title>Insert title here</title>
<script src="/SUNDAY/js/jquery-1.12.4.js"></script>
<script src="/SUNDAY/js/jquery-ui.js"></script>
</head>
<body>
	<h3>영화 추천</h3>
	<hr/>
	
	<form name="searchFrm" action="/SUNDAY/recommend/recommend.do" method="post" >
		<input type="hidden"  name="work_div"   />
		<input type="hidden"  name="page_num"   />
		<!-- 검색영역 -->
		<table>
			<tr>
				<td>
					<div>
					
						<h4>기분</h4>
						신남<input type="radio" name="rec_m" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(1))out.print("checked='checked'"); %> > 
						차분<input type="radio" name="rec_m" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(2))out.print("checked='checked'"); %> >
						긍정<input type="radio" name="rec_m" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(3))out.print("checked='checked'"); %> >
						우울<input type="radio" name="rec_m" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(4))out.print("checked='checked'"); %> >
						<hr/>
						
						<h4>날씨</h4>
						맑음<input type="radio" name="rec_w" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(1))out.print("checked='checked'"); %> > 
						흐림<input type="radio" name="rec_w" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(2))out.print("checked='checked'"); %> >
						비<input type="radio" name="rec_w" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(3))out.print("checked='checked'"); %> >
						눈<input type="radio" name="rec_w" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(4))out.print("checked='checked'"); %> >
						<hr/>
						
						<h4>시간</h4>
						아침<input type="radio" name="rec_t" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(1))out.print("checked='checked'"); %> > 
						오후<input type="radio" name="rec_t" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(2))out.print("checked='checked'"); %> >
						저녁<input type="radio" name="rec_t" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(3))out.print("checked='checked'"); %> >
						새벽<input type="radio" name="rec_t" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(4))out.print("checked='checked'"); %> >
						<hr/>
						
						<h4>사람</h4>
						혼자<input type="radio" name="rec_p" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(1))out.print("checked='checked'"); %> > 
						가족<input type="radio" name="rec_p" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(2))out.print("checked='checked'"); %> >
						연인<input type="radio" name="rec_p" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(3))out.print("checked='checked'"); %> >
						친구<input type="radio" name="rec_p" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(4))out.print("checked='checked'"); %> >
						<hr/>
											
					</div>
				</td>
			</tr>
		</table>
		<!--// 검색영역 -->
		
		<!-- 버튼 -->	
		<div>
			<input type="button" value="조회" onclick="javascript:do_retrieve();" />
			<input type="button"  value="선택 초기화" id="reset_btn" />
		</div>
		<br>
		<!--// 버튼 -->
		
		<!-- data list -->
		<table id="listTable">
			<thead>
				<tr>
					<th>한글제목</th>
					<th>영어제목</th>
					<th>장르</th>
					<th>감독</th>	
					<th>국가</th>
					<th>배우</th>
					<th>포스터</th>
					<th>개봉일</th>
					<th>평점</th>
					<th>줄거리</th>		
			
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && list.size()>0){
						for(RecommendVO vo :list){
				%>
						<tr>
							<td><%=vo.getTitle() %></td>
							<td><%=vo.getTitleEng() %></td>
							<td><%=vo.getGenre() %></td>
							<td><%=vo.getDirectorNm() %></td>
							<td><%=vo.getNation() %></td>
							<td><%=vo.getActorNm() %></td>
							<td><img src="<%=vo.getPosters() %>" /></td>
							<%-- <td><%=vo.getPosters() %></td> --%>
							<td><%=vo.getReleaseDate() %></td>
							<td><%=vo.getRate() %></td>
							<td><%=vo.getPlot() %></td>					
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
	
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.searchFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/SUNDAY/recommend/recommend.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
		
		$(document).ready(function(){
		});
		
        //선택 초기화
        $("#reset_btn").on('click',function(){
        
        	$("input:radio[name='rec_m']:radio[value='1']").prop("checked", false);
        	$("input:radio[name='rec_m']:radio[value='2']").prop("checked", false);
        	$("input:radio[name='rec_m']:radio[value='3']").prop("checked", false);
        	$("input:radio[name='rec_m']:radio[value='4']").prop("checked", false);
        	
        	$("input:radio[name='rec_w']:radio[value='1']").prop("checked", false);
        	$("input:radio[name='rec_w']:radio[value='2']").prop("checked", false);
        	$("input:radio[name='rec_w']:radio[value='3']").prop("checked", false);
        	$("input:radio[name='rec_w']:radio[value='4']").prop("checked", false);
        	
        	$("input:radio[name='rec_t']:radio[value='1']").prop("checked", false);
        	$("input:radio[name='rec_t']:radio[value='2']").prop("checked", false);
        	$("input:radio[name='rec_t']:radio[value='3']").prop("checked", false);
        	$("input:radio[name='rec_t']:radio[value='4']").prop("checked", false);
        	
        	$("input:radio[name='rec_p']:radio[value='1']").prop("checked", false);
        	$("input:radio[name='rec_p']:radio[value='2']").prop("checked", false);
        	$("input:radio[name='rec_p']:radio[value='3']").prop("checked", false);
        	$("input:radio[name='rec_p']:radio[value='4']").prop("checked", false);
        	
        }); 
		
	</script>
</body>
</html>