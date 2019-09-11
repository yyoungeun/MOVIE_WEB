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
<%@page import="com.sunday.member.dao.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	MemberVO inVO = (MemberVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/WEB_EX01/JS/jquery-ui.css">
<link  rel="shortcut icon" href="/WEB_EX01/img/favicon.ico">
<title>Insert title here</title>
<script src="/WEB_EX01/JS/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/JS/jquery-ui.js"></script> 
</head>
<body>
	<h3>Member 관리</h3>
	<hr/>
	<form action="/WEB_EX01/sunday/login.do" method="get">
		<input type="hidden" name="work_div" value=do_selectone"/>
		<input type="submit" name="전송"/>
	</form>
	<h3>결과</h3>
	<script>
	<%
		if(null != inVO){
			out.print(inVO);
		}
	%>
		
		$(document).ready(function(){
			
			
		});
	</script>
</body>
</html>