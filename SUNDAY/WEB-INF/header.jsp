<%@page import="com.sunday.member.dao.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	MemberVO user = (MemberVO)session.getAttribute("user");
	String name = (String)session.getAttribute("name");
	String lvl = (String)session.getAttribute("lvl");
	String lang = (String)session.getAttribute("lang");
	request.setAttribute("lang", lang);
	//out.print(name);
	//out.print(lang);
	//String uri = (String)request.getAttribute("uri");
	//String path = javax.servlet.http.HttpUtils.getRequestURL(request);
%>
<fmt:setLocale value="${lang}" scope="application"/>

<fmt:bundle basename="resource.message">
<fmt:message key="TITLE" var="title"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
		
	<!--버튼 안돼서 추가  -->
	<script src="/SUNDAY/js/jquery-1.12.4.js"></script>
	<script src="/SUNDAY/js/jquery-ui.js"></script>
	
	<!-- Custom fonts for this template-->
	<link href="/SUNDAY/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
		
	<!-- Custom styles for this template-->
	<link href="/SUNDAY/css/sb-admin-2.min.css" rel="stylesheet">
	<!-- favicon -->
	<link rel="shortcut icon" href="/SUNDAY/image/movie.ico">
	
	<title>${title}</title>
	<!-- <script src="/SUNDAY/vendor/jquery/jquery.min.js"></script> -->
	<script src="/SUNDAY/js/jquery-1.12.4.js"></script>
	<script src="/SUNDAY/js/jquery-ui.js"></script>

	<!-- bar-rating --> 
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"> 
	<link rel="stylesheet" href="/SUNDAY/css/fontawesome-stars.css"> 
	<script type="text/javascript" src="/SUNDAY/js/jquery.barrating.min.js"></script>
  
	<!-- Bootstrap core JavaScript -->

	<script src="/SUNDAY/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript -->
	<script src="/SUNDAY/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	<!-- jquery validation --> 
	<script src="/SUNDAY/js/jquery.validate.js"></script>
	<script src="/SUNDAY/js/messages_ko.js"></script>
	<script src="/SUNDAY/js/additional-methods.js"></script>
	<!-- favicon -->
	<!-- <link rel="shortcut icon" href="/SUNDAY/image/movie.ico"> -->
	
	<!-- Custom scripts for all pages -->
	<script src="/SUNDAY/js/sb-admin-2.min.js"></script>

<style>
#eng, #kor {
	-moz-box-shadow:inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow:inset 0px 1px 0px 0px #ffffff;
	box-shadow:inset 0px 1px 0px 0px #ffffff;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ffffff), color-stop(1, #f6f6f6));
	background:-moz-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
	background:-webkit-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
	background:-o-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
	background:-ms-linear-gradient(top, #ffffff 5%, #f6f6f6 100%);
	background:linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#f6f6f6',GradientType=0);
	background-color:#ffffff;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	border-radius:6px;
	border:1px solid #dcdcdc;
	display:inline-block;
	cursor:pointer;
	color:#666666;
	font-family:Arial;
	font-size:8px;
	font-weight:bold;
	padding:1px 1px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
	width: 40px; height:30px;
}
#eng,#kor:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
	background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
	background-color:#f6f6f6;
}
#eng,#kor:active {
	position:relative;
	top:1px;
}

#join_form label.error{
		color: red;
		font-size: small;
		}
#updateForm label.error{
		color: red;
		font-size: small;
		}
</style>

</head>
<script>

</script>
<body id="page-top">


  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/SUNDAY/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SUNDAY</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	<fmt:message key="MOVIEINFO" />
      </div>

      <!-- 메뉴 -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="/SUNDAY/movie.do?work_div=do_retrieve&page_size=&page_num=1&search_div=10&search_word=">
          <i class="fas fa-fw fa-cog"></i>
          <span><fmt:message key="RANKING" /></span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="/SUNDAY/recommend/recommend.do?work_div=do_retrieve">
          <i class="fas fa-fw fa-wrench"></i>
          <span><fmt:message key="RECOMMENDATION" /></span>
        </a>
      </li>

      <%if(null!=lvl && lvl.equals("9")){ %>
      <li class="nav-item">
        <a class="nav-link collapsed" href="/SUNDAY/movie_register/movie_register.do?work_div=do_retrieve">
          <i class="fas fa-fw fa-cog"></i>
          <span><fmt:message key="REGISTER" /></span>
        </a>
      </li>
      <%} %>

      <li class="nav-item">
        <a class="nav-link collapsed" href="/SUNDAY/chart/chart.jsp">
          <i class="fas fa-fw fa-cog"></i>
          <span><fmt:message key="CHART" /></span>
        </a>
      </li>
      <!-- Divider -->
      <hr class="sidebar-divider">
	<%if(null != user){ %>	
	<!-- Heading -->
      <div class="sidebar-heading">
        	<fmt:message key="MYPAGE" />
      </div>
	    <li class="nav-item">
	        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMyPages" aria-expanded="true" aria-controls="collapseMyPages">
	          <i class="fas fa-fw fa-wrench"></i>
	          <span><fmt:message key="MYPAGE" /></span>
	        </a>
	        <div id="collapseMyPages" class="collapse" aria-labelledby="#collapseMyPages" data-parent="#accordionSidebar">
	          <div class="bg-white py-2 collapse-inner rounded">
	            <h6 class="collapse-header">My Page</h6>
	            <a class="collapse-item" data-toggle="modal" data-target="#updateModal"><fmt:message key="MYINFO" /></a>
	            <a class="collapse-item" href="/SUNDAY/user_review.do?work_div=do_retrieve&page_num=1&search_word=<%=user.getUser_id()%>"><fmt:message key="MYREVIEW" /></a>
	          </div>
	        </div>
	      </li>
	<%}else{ %>
      
		<!-- Heading -->
      <div class="sidebar-heading">
        	<fmt:message key="LOGIN" />
      </div>
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
          <span><fmt:message key="LOGIN" /></span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Login / Join</h6>
            <a class="collapse-item" data-toggle="modal" data-target="#loginModal"><fmt:message key="LOGIN" /></a>
            <a class="collapse-item" data-toggle="modal" data-target="#joinModal"><fmt:message key="JOIN" /></a>
            <a class="collapse-item" data-toggle="modal" data-target="#findModal"><fmt:message key="FIND" /></a>

          </div>
        </div>
      </li>
    <%} %>
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
        <!-- 상단 바 -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
          <!-- 검색창 -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" id="TopSearchForm" name="TopSearchForm">
            <div class="input-group">
            	<input type="hidden" name="work_div"/>
            	<input type="hidden" name="search_word"/>
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" id="search">
              <div class="input-group-append">
                <button id="searchBtn" class="btn btn-primary" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>
          <button class="lang" name="eng" id="eng" value="en">ENG</button><button class="lang" name="kor" id="kor" value="ko">한국어</button>
</fmt:bundle>        

        <!-- //상단 바 -->

 <div class="topbar-divider d-none d-sm-block" style="margin-right:0px;"></div> 
  <%if(null!=user && user.getName().trim().length()!=0){ %>      
           <!-- Nav Item - 로그인 후 보이는 부분 -->
           <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"></span>
         
                <c:out value="${name }"/>
                
                <span class="glyphicon glyphicon-log-out"></span>
                <!-- <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60"> -->
              </a>
              <!-- Dropdown - User 정보 -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
              </div>
            </li>

          </ul>
<%}%>
       </nav>
        
    <!-- Begin Page Content -->
	<div class="container-fluid">
    </div>
    <!-- End of Content Wrapper -->
  </div>
  
 
  <!-- End of Page Wrapper -->	