<%--
/**
* @Class Name : useOutObject.jsp
* @Description : Sample Register 화면
* @Modification Information
*
* 수정일 수정자 수정내용
* ------- -------- ---------------------------
* 2019.07.01 최초 생성
*
* author 실행환경 개발팀
* since 2019.07.01
*
* Copyright (C) 2009 by KandJang All right reserved.
*/
--%>


<%@page import="com.sunday.movie.domain.SearchVO"%>
<%@page import="com.sunday.member.dao.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
	
	String pageNum = "1";
	String searchDiv = "";
	String searchWord = "";
	String pageSize= "10";
	
	SearchVO paramVO = (SearchVO)request.getAttribute("paramVO");
	out.print(paramVO);
	if(null != paramVO){
		pageNum = paramVO.getNum() + "";
		searchDiv = paramVO.getSearchDiv();
		searchWord = paramVO.getSearchWord();
		pageSize = paramVO.getPageSize()+ "";
		LOG.debug("====================================");
   		LOG.debug("paramVO=" + paramVO);
   	 	LOG.debug("====================================");
		
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
<link rel="stylesheet" href="/WEB_EX01/JS/jquery-ui.css">
<!-- <link  rel="shortcut icon" href="/WEB_EX01/img/favicon.ico"> -->
<title>Insert title here</title>
<script src="/WEB_EX01/JS/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/JS/jquery-ui.js"></script>

</head>
<body>
	<h3>Member관리창</h3>
	<hr/>
	<div>
		 <input type="button" value="조회" onclick="javascript:do_retrieve();" />
		 <input type="button" value="등록" onclick="javascript:do_save_move();" />
		 <input type="button" value="로그인" onclick="javascript:do_login();" />
	</div>
	
	<form name="searchFrm" action="/SUNDAY/login.do" method="get">
		<input type="hidden" name="work_div" />
		<input type="hidden" name="page_num" />
		<!-- 검색 영역 -->
		<table>
			<tr>
				<td>
					<div>구분
						<Select name="search_div" id="search_div">
							<option value="">전체</option>
							<option value="10"><%if(searchDiv.equals("10"))out.print("selected"); %>ID</option>
							<option value="20"><%if(searchDiv.equals("20"))out.print("selected"); %>이메일</option>
							<option value="30"><%if(searchDiv.equals("30"))out.print("selected"); %>등급</option>
						</Select>
						<input type="text" name="search_word" id="search_word" value="<%=searchWord %>" />
						<input type="text" name="search_word" id="search_word" />
						<select name="page_size" id="page_size">
							<option value="">전체</option>
							<option value="10" <%if(pageSize.equals("10"))out.print("selected"); %> >10</option>
							<option value="50" <%if(pageSize.equals("50"))out.print("selected"); %>>50</option>
							<option value="100" <%if(pageSize.equals("100"))out.print("selected"); %> >100</option>
							<option value="200"  <%if(pageSize.equals("200"))out.print("selected"); %>>200</option>
						</select>
					</div>
				</td>
			</tr>
		</table>
		<!-- 검색영역 -->
		
		<!-- data list -->
		<table id="listTable">
			<thead>
				<tr>
					<th>NO</th>
					<th>아이디</th>
					<th>이름</th>
					<th>이메일</th>
					<th>등급</th>
					<th>등록일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(null != list && list.size()>0){
					for(MemberVO vo :list){
				%>
					<tr>
						<td><%= vo.getNum() %></td>
						<td><%= vo.getUser_id() %></td>
						<td><%= vo.getName() %></td>
						<td><%= vo.getEmail() %></td>
						<td><%= vo.getLvl() %></td>
						<td><%= vo.getReg_dt()%></td>
						<td><%= vo.getMod_dt()%></td>
					</tr>
				<%
						}//--for
					}else{ 
				%>
						<tr>
							<td colspan="99">No data found</td>
						</tr>
				
				<% }//--else %>
				
			</tbody>
		</table>
		
		<!-- data list -->
	</form>
	<h3>결과</h3>
	<script>
	
	  function do_login(){
		//alert('do_login');
		if(confirm("로그인 하시겠습니까?")==false) return;
		var frm = document.searchFrm;
		frm.work_div.value="do_save_move";
		frm.action='/SUNDAY/login.do';
		frm.submit();
	}  
	//등록화면
		 function do_save_move(){
			//alert('do_save_move');
			if(confirm("등록하시겠습니까?")== false) return;
			var frm= document.searchFrm;
			frm.work_div.value="do_save_move";
			frm.action='/SUNDAY/login.do';
			frm.submit();
	} 
	//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.searchFrm;
			frm.work_div.value ='do_retrieve';
			frm.page_num.value ='1';
			frm.action ='/SUNDAY/login.do';
			
			//alert('frm.work_div.value: '+ frm.work_div.value);
			frm.submit();
		}
	//수정화면으로 이동: servlet -> join.jsp
		$("#listTable>tbody").on("click","tr",function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var userId = cTds.eq(1).text();
			console.log("userId: "+ userId);
			
			var frm = document.searchFrm;
			frm.work_div.value = "do_selectone";
			frm.action = '/SUNDAY/login.do?user_id='+userId;
			frm.submit();
		});
		
		$(document).ready(function(){
			
			
		});
	</script>
</body>
</html>