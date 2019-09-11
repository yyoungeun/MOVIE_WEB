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
<!-- jquery validation --> 
<script src="/SUNDAY/js/jquery.validate.js"></script>
<script src="/SUNDAY/js/additional-methods.js"></script>
<script src="/SUNDAY/js/messages_ko.js"></script>
<!--// jquery validation --> 
</head>
<body>
	<h3>회원정보 수정</h3>
	<hr/>
	
	<!-- 회원관리 Form -->
	<form name="frmMng" id="frmMng" action="/SUNDAY/mypage.do" method="post">
	<input type="hidden" name="work_div" id="work_div"/>
		
	아이디:   	  <input type="text" readonly="readonly" value="${vo.getUser_id()}" name="user_id" id="user_id" maxlength="20"/><br/>
	비밀번호:	  <input type="password" value="${vo.getPasswd()}" name="passwd" id="passwd" size="20" maxlength="20"/><br/>
	비밀번호 확인: <input type="password" name="repasswd" id="repasswd"/><br/>
	이름: 	  <input type="text"  value ="${vo.getName()}" name="name" id="name" size="30" maxlength="30"/><br/>
	이메일: 	  <input type="text" readonly= "readonly" value="${vo.getEmail()}" name="email"  id="email" size="100" maxlength="150"/><br/>
	등록일:	  <input type="text" value="${vo.getReg_dt()}" name="reg_dt" id="reg_dt" size="10" maxlength="10" readonly="readonly"/><br/>
	수정일: 	  <input type="text" value="${vo.getMod_dt()}" name="mod_dt" id="mod_dt" size="10" maxlength="10" readonly="readonly"/><br/>
	<input type="button" value="수정하기" id="update_btn"/>
	<input type="button" value="탈퇴하기" id="del_btn"/>	
	</form>
	
	<script>
	//validation
	$(document).ready(function(){
		$("form").validate({
				//validation종료 후 submit직전에 할 작업.: 메시지
			/* submitHandler:function(){
				var f = confirm('회원가입을 하시겠습니까?');
				if(f){
					return true;
				}else{
					return false;
				}
			}, */
			//규칙
			rules:{
				
				passwd:{
					required:true,
					minlength:3,
				},
				repasswd:{
					required:true,
					minlength:3,
					equalTo: passwd
				},
				name:{
					required: true,
				}
			},
			//출력 메시지
			message:{
				
				passwd:{
					required:"필수 입력값 입니다.",
					minlength:"최소{0}글자 이상이어야 합니다.",
				},
				repasswd:{
					required:"필수 입력값 입니다.",
					minlength:"최소{0}글자 이상이어야 합니다.",
					equalTo: "비밀번호가 일치하지 않습니다."
				},
				name:{
					required:"필수 항목입니다."
				}
			}
		});
	});
	
	
	//삭제function
	/* function doDel(){
		//if(false == confirm('삭제하시겠습니까?')) return;
		var frm = document.frmMng;
		frm.work_div.value ="do_delete";
		frm.action = "/SUNDAY/mypage.do";
		frm.submit();
	} */
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
	
		$(document).ready(function(){
			console.log('1 mode: '+ '${mode}');
			
			if(null != '${mode}' && 'insert'=='${mode}'){
				console.log('2 mode:'+'${mode}');
				$("#user_id").removeAttr("readonly");
				$("#email").removeAttr("readonly");
			}
		});
	</script>
</body>
</html>