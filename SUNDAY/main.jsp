<%@page import="com.sunday.member.dao.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

 <%
	MemberVO user = (MemberVO)session.getAttribute("user");
 
 	/* if(session.getAttribute("id") == null){
 		response.sendRedirect("index.jsp");
 	} */
%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <!-- 버튼 안돼서 추가 -->
  <script src="/SUNDAY/js/jquery-1.12.4.js"></script>
  <title>SUNDAY</title>
  <!-- Custom fonts for this template-->
  <link href="/SUNDAY/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- jquery validation --> 
<script src="/SUNDAY/js/jquery.validate.js"></script>
<script src="/SUNDAY/js/messages_ko.js"></script>
<script src="/SUNDAY/js/additional-methods.js"></script>

  <!-- Custom styles for this template-->
  <link href="/SUNDAY/css/sb-admin-2.min.css" rel="stylesheet">
  <style type="text/css">
		#frmMng label.error{
		color: red;
		font-size: small;
		}
  </style>

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="main.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SUNDAY</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="index.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	영화정보
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>영화랭킹</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="buttons.html">Buttons</a>
            <a class="collapse-item" href="cards.html">Cards</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>추천 영화</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Utilities:</h6>
            <a class="collapse-item" href="utilities-color.html">Colors</a>
            <a class="collapse-item" href="utilities-border.html">Borders</a>
            <a class="collapse-item" href="utilities-animation.html">Animations</a>
            <a class="collapse-item" href="utilities-other.html">Other</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	마이페이지
      </div>

      <!-- Nav Item - Charts -->
     <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMyPages" aria-expanded="true" aria-controls="collapseMyPages">
          <i class="fas fa-fw fa-wrench"></i>
          <span>마이페이지</span>
        </a>
        <div id="collapseMyPages" class="collapse" aria-labelledby="#collapseMyPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">My Page</h6>
            <a class="collapse-item" data-toggle="modal" data-target="#updateModal">회원정보 수정</a>
            <a class="collapse-item" href="mypage.jsp">내가 쓴 리뷰</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="tables.html">
          <i class="fas fa-fw fa-table"></i>
          <span>게시판-참고</span></a>
      </li>

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

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>
            
            <!-- Nav Item - 로그인 후 보이는 부분 -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"></span><c:out value="${name }"/>
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
        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid"></div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Login Modal-->
 <!-- <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	 <form class="user">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputId" aria-describedby="emailHelp" placeholder="아이디">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="비밀번호">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div>
                    <a href="index.html" class="btn btn-primary btn-user btn-block">
                      Login
                    </a>
                    <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
 
 
 join Modal
  <div class="modal fade" id="joinModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	 <form class="user">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputId" aria-describedby="emailHelp" placeholder="아이디">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="비밀번호">
                    </div>
                     <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputRePassword" placeholder="비밀번호 확인">
                    </div>
                     <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputName" aria-describedby="emailHelp" placeholder="이름">
                    </div>
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="이메일">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div>
                    <a href="index.html" class="btn btn-primary btn-user btn-block">
                      		회원가입
                    </a>
                    <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div> -->
 
 <!-- 회원정보 수정(updateModal) -->
<!-- <form name="frmMng" id="frmMng" class="user" method="post"> -->
 <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">회원정보 수정</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        <form name="frmMng" id="frmMng" class="user">
        <input type="hidden" name="work_div" id="work_div"/>
        	<!--  <form class="user"> -->
                    <div class="form-group">
                      <input type="text" c:out value="${id }" readonly="readonly"name="mod_id" id="mod_id" maxlength="20" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                      <input type="password"  c:out value="${passwd }"  name="mod_passwd" id="mod_passwd" size="20" maxlength="20" class="form-control form-control-user" placeholder="비밀번호">
                    </div>
                     <div class="form-group">
                      <input type="password"  name="mod_repasswd" id="mod_repasswd" class="form-control form-control-user" placeholder="비밀번호 확인" >
                    </div>
                     <div class="form-group">
                      <input type="text" c:out value="${name }" name="mod_name" id="mod_name" size="30" maxlength="30" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="이름">
                    </div>
                    <div class="form-group">
                      <input type="email" c:out value="${email }" readonly= "readonly" value="${vo.getEmail()}" name="email"  id="email" size="100" maxlength="150" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                     	등록일 <input type="text" readonly= "readonly" c:out value="${reg_dt }" name="reg_dt" id="reg_dt" size="10" maxlength="10" class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                     	 수정일<input type="text" readonly= "readonly" c:out value="${mod_dt }" name="mod_dt" id="mod_dt" size="10" maxlength="10"  class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <hr>
                    <input type="submit" value="수정완료" class="btn btn-primary btn-user btn-block" />
                    <input type="button" value="계정삭제" id="del_btn" class="btn btn-primary btn-user btn-block" />
       			 </form>
       		 </div>
     	 </div>
    </div>
 </div>
<!-- </form> -->
 
 <script>
 //정보 수정
 function do_update(){
 		console.log("do_update");
 		
 		var frm = document.frmMng;
 		frm.work_div.value="do_update";
 		var param = $("#frmMng").serialize();
 		//$("#work_div").val("do_insert");
 /* $("#update_btn").on('click',function(){
		var userId = $("#user_id").val();
		/* if(null == userId || userId.trim().length ==0){
			$("#user_id").focus();
			alert('사용자ID를 입력하세요.');
			return;
		} */
		
		/* $("#work_div").val("do_update");
		var param = $("#frmMng").serialize();
		
		if(false == confirm('회원정보를  수정하시겠습니까?')) return; 
		 */
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
 }
//validate
	 $(document).ready(function(){
		 //alert("validation");
		$("#frmMng").validate({
			//validation 종료 후 submit 직전에 할 작업 ex)메세지 표시
			submitHandler:function(){
			var f = confirm('수정을 하시겠습니까?');
			if(f){
				return do_update();
			}else{
				return false;
			}
		},
			rules:{
				mod_passwd:{
					required: true,
					minlength: 4
				},
				mod_repasswd:{
					required: true,
					minlength:4,
					equalTo: mod_passwd
				},
				mod_name:{
					required: true
				}
				
			},
			message:{
				mod_passwd:{
					required: "필수 항목입니다.",
					minlength: '최소 {0}글자 이상이어야 합니다.'
				},
				mod_repasswd:{
 					required: "필수 항목입니다.",
 					minlength:"최소 {0}글자 이상이어야 합니다.",
 					equalTo: "같은 값을 다시 입력하세요."
				},
				mod_name:{
						required: "필수 항목입니다."
				}
			}
		});
	}); 
		
 //계정 삭제
 $("#del_btn").on('click',function(){
		console.log('del_btn');
		console.log($("#mod_id").val());
		
		var userId = $("#mod_id").val();
		if(null == userId || userId.trim().length ==0){
			$("#mod_id").focus();
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
				"user_id": $("#mod_id").val()
			},
			success: function(data){
				var jData = JSON.parse(data);
					console.log(jData.msgId + "|" + jData.msgContents);
					
					if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/mypage.do?work_div=do_remove";
						
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
 
 </script>
    <script>
			 	function doLogout(){
			 		var frm = document.topForm;
			 		frm.work_div.value='do_logout';
			 		frm.action="<c:url value='/login.json' />";
			 		frm.submit();
 				}
 	</script>
 
  <!-- Logout Modal-->
  <div id="wrapper">
  	<form action="" name="topForm" id="topForm" method="post">
  		<input type="hidden" name="work_div" />
  	</form>
  </div>>
 <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">안녕히 가세요~</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="javascript:doLogout();">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
 <!--  <script src="/SUNDAY/vendor/jquery/jquery.min.js"></script> -->
  <script src="/SUNDAY/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/SUNDAY/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/SUNDAY/js/sb-admin-2.min.js"></script>

</body>
</html>