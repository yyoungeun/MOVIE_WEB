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

<%-- <%@page import="com.hr.code.CodeVO"%>
<%@page import="com.hr.code.CodeDao"%> --%>
<%@page import="com.sunday.cmn.DTO"%>
<%@page import="com.sunday.member.dao.MemberDao"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="com.sunday.member.dao.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	//List<CodeVO> list = (List<CodeVO>)request.getAttribute("lvlList");
	MemberVO vo = (MemberVO)request.getAttribute("vo");
	String level = "1";
	if(null != vo){
		level = vo.getLvl();
	}
	out.print(vo);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/SUNDAY/js/jquery-ui.css">
<link  rel="shortcut icon" href="/WEB_EX01/img/favicon.ico">
<title>Insert title here</title>
<script src="/SUNDAY/js/jquery-1.12.4.js"></script>
<script src="/SUNDAY/js/jquery-ui.js"></script> 
</head>
<body>
	<h3>로그인</h3>
	<hr/>
	
	<!-- 버튼 -->
	
	<!-- 회원관리 Form -->
	<form name="frmMng" id="frmMng" action="/SUNDAY/login.do" method="post">
	<input type="hidden" name="work_div" id="work_div" />
	아이디:  <input type="text"  name="user_id" id="user_id" maxlength="20" placeholder="영문,숫자_입력"/><br/>
	비밀번호: <input type="password" name="passwd" id="passwd" size="20" maxlength="20"/>
		<div><input type="button" value="로그인" id="login_btn"/></div>
	</form>
	
	<script>
	
	//로그인
	$("#login_btn").on('click',function(){
		console.log('login_btn');
		console.log($("#user_id").val());
		
		var userId = $("#user_id").val();
		if(null == userId || userId.trim().length ==0){
			$("#user_id").focus();
			alert('ID를 입력하세요.');
			return;
		}
		var passWord = $("#passwd").val();
		if(null == passWord || passWord.trim().length ==0){
			$("#passwd").focus();
			alert('password를 입력하세요.');
			return;
		}
		
		if(false == confirm(userId+ '으로 로그인 하시겠습니까?')) return;
		
		 $("#work_div").val("do_login");
			var param = $("#frmMng").serialize(); 
		 
		$.ajax({
			type:"post",
			url:"/SUNDAY/login.do",
			dataType: "html",
			data:param,
			success: function(data){
				//alert(data);
				var jData = JSON.parse(data);
					//alret(jData);
					//console.log(jData.msgId + "|" + jData.msgContents);
				
					if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/login.do?work_div=do_retrieve";
					}else{
						alert(jData.msgId+"|"+ jData.msgContents);
					}
				}, 
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error: "+ error);
				}
		});//ajax 

  });
	
	 //삭제function
	/* function doDel(){
		if(false == confirm('삭제하시겠습니까?')) return;
		var frm = document.frmMng;
		frm.work_div.value ="do_delete";
		frm.action = "/SUNDAY/mypage.do";
		frm.submit();
	}
	//삭제: buttonEvent
	$("#del_btn").on('click',function(){
		console.log('del_btn');
		console.log($("#user_id").val());
		
		var userId = $("#user_id").val();
		if(null == userId || userId.trim().length ==0){
			$("#user_id").focus();
			alert('사용자 ID를 입력하세요');
			return;
		}
		if(false == confirm(userId+ ('을(를) 삭제하시겠습니까?'))) return;
		
		$.ajax({
			type: "POST",
			url:"/SUNDAY/mypage.do",
			dataType: "html",
			data:{
				"work_div": "do_delete",
				"user_id": $("#user_id").val()
			},
			success: function(data){
				var jData = JSON.parse(data);
					console.log(jData.msgId + "|" + jData.msgContents);
					
					if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/mypage.do?work_div=do_retrieve";
						
					}else{
						alert(jData.msgId+ "|" + jData.msgContents);
					}
				},
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error: "+ error);
				}
		});//ajax
	});
	
	//수정
	$("#update_btn").on('click',function(){
		var userId = $("#user_id").val();
		if(null == userId || userId.trim().length ==0){
			$("#user_id").focus();
			alert('사용자ID를 입력하세요.');
			return;
		}
		
		$("#work_div").val("do_update");
		var param = $("#frmMng").serialize();
		
		if(false == confirm(userId + ('을(를) 수정하시겠습니까?'))) return;
		
		$.ajax({
			type: "POST",
			url:"/SUNDAY/mypage.do",
			dataType: "html",
			data:param,
			success: function(data){
				var jData = JSON.parse(data);
				if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/mypage.do?work_div=do_retrieve";
					}else{
						alert(jData.msgId+ "|" + jData.msgContents);
					}
				},
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error: "+ error);
				}
		});//ajax
		
	});
	
	//등록
	$("#save_btn").on('click',function(){
		//alert('save_btn');
		//validation 필수
		var userId = $("#user_id").val();
		if(null == userId || userId.trim().length == 0){
			$("#user_id").focus();
			alert('사용자 ID를 입력하세요.');
			return;
		}
		
		$("#work_div").val("do_insert");
		var param = $("#frmMng").serialize();
		
		if(false == confirm(userId+ ('을(를) 등록하시겠습니까?'))) return;
		
		$.ajax({
			type: "POST",
			url:"/SUNDAY/mypage.do",
			dataType: "html",
			data:param,
			success: function(data){
				var jData = JSON.parse(data);
				if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/mypage.do?work_div=do_retrieve";
						
					}else{
						alert(jData.msgId+ "|" + jData.msgContents);
					}
				},
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error: "+ error);
				}
		});//ajax
	});
		
		$(document).ready(function(){
			console.log('1 mode: '+ '${mode}');
			
			if(null != '${mode}' && 'insert'=='${mode}'){
				console.log('2 mode:'+'${mode}');
				$("#user_id").removeAttr("readonly");
				$("#email").removeAttr("readonly");
				//$("#mod_id").removeAttr("readonly");
			}
		});  */
		
		
	</script>
</body>
</html>