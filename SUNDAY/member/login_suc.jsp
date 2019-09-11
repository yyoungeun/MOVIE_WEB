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
pageEncoding="UTF-8" isELIgnored="false" %>
<%
	MemberVO user = (MemberVO)session.getAttribute("user");
	out.print(user);	
%>
<!-- http://localhost:8080/SUNDAY/login.do?work_div=do_login&user_id=aaa&passwd=bb77 -->
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
	<h3>성공!</h3>
	<hr/>
	<script>
		
		$(document).ready(function(){
			
		});
	</script>
</body>
</html>