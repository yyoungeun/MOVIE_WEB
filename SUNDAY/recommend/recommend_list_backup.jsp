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

  	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  
  	<title>영화 추천</title>
  
	<!-- Custom fonts for this template-->
    <link href="/SUNDAY/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	<link href="/SUNDAY/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">
  <%@ include file="/WEB-INF/header.jsp" %>	
  
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Begin Page Content -->
       	<div class="container-fluid">
       	
        <!-- Page Heading -->
   		<div class="d-sm-flex align-items-center justify-content-between mb-4">
       	<h1 class="h3 mb-0 text-gray-800">영화 추천</h1>
   		</div>
   		
		<form class="container-fluid" name="recommendFrm" action="/SUNDAY/recommend/recommend.do" method="post" >
			
			<!-- class=row -->
			<div class="row">
			
			<input type="hidden"  name="work_div"   />
			<input type="hidden"  name="page_num"   />
			<!-- 검색영역 -->
	   		
   			<!-- 기분 -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-primary text-uppercase mb-1">기분</div>
                      <div>
                     	신남&nbsp;<input class="h5 mb-0 font-weight-bold text-gray-800" type="radio" name="rec_m" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(1))out.print("checked='checked'"); %> >&nbsp;&nbsp; 
						차분&nbsp;<input type="radio" name="rec_m" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(2))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						긍정&nbsp;<input type="radio" name="rec_m" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(3))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						우울&nbsp;<input type="radio" name="rec_m" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecM())).equals(4))out.print("checked='checked'"); %> >
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="far fa-grin fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
   		
   			<!-- 날씨 -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-success text-uppercase mb-1">날씨</div>
                      <div>
						맑음&nbsp;<input type="radio" name="rec_w" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(1))out.print("checked='checked'"); %> >&nbsp;&nbsp; 
						흐림&nbsp;<input type="radio" name="rec_w" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(2))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						비&nbsp;<input type="radio" name="rec_w" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(3))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						눈&nbsp;<input type="radio" name="rec_w" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecW())).equals(4))out.print("checked='checked'"); %> >
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-cloud fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- 시간 -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-info text-uppercase mb-1">시간</div>
                      <div>
						아침&nbsp;<input type="radio" name="rec_t" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(1))out.print("checked='checked'"); %> >&nbsp;&nbsp; 
						오후&nbsp;<input type="radio" name="rec_t" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(2))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						저녁&nbsp;<input type="radio" name="rec_t" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(3))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						새벽&nbsp;<input type="radio" name="rec_t" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecT())).equals(4))out.print("checked='checked'"); %> >
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="far fa-clock fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- 사람 -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-warning text-uppercase mb-1">사람</div>
                      <div>
						혼자&nbsp;<input type="radio" name="rec_p" value="1" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(1))out.print("checked='checked'"); %> >&nbsp;&nbsp; 
						가족&nbsp;<input type="radio" name="rec_p" value="2" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(2))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						연인&nbsp;<input type="radio" name="rec_p" value="3" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(3))out.print("checked='checked'"); %> >&nbsp;&nbsp;
						친구&nbsp;<input type="radio" name="rec_p" value="4" <%if(null != paramVO && ((Integer)(paramVO.getRecP())).equals(4))out.print("checked='checked'"); %> >
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-user-friends fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--// 검색영역 -->
            
           	<!-- 버튼 -->	
			<div class="col-xl-3 col-md-6 mb-4">
				<input class="text-s btn btn-secondary" type="button" value="조회" onclick="javascript:do_retrieve();" />
				<input class="text-s btn btn-secondary" type="button" value="선택 초기화"" onclick="javascript:do_reset();" />
			</div>
			<!--// 버튼 -->
            
            </div>
            <!--// class=row -->
            
            <!-- data list -->
			<div class="container-fluid">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
	              <h6 class="m-0 font-weight-bold text-primary">검색결과</h6>
	            </div>
	            <div class="card-body">
	            	<div class="table-responsive">
	            		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th width="10%">제목</th>
									<th width="9%">영제</th>
									<th width="6%">장르</th>
									<th width="8%">감독</th>	
									<th width="6%">국가</th>
									<th width="8%">배우</th>
									<th width="10%">포스터</th>
									<th width="6%">개봉일</th>
									<th width="3%">평점</th>
									<th width="30%">줄거리</th>
									<th width="4%">현재상영</th>		
						
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
											<td><%=vo.getNowShowing() %></td>				
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
					</div>
				</div>
				<!-- End of card-body -->
            </div> 
            <!-- End of Table -->  	
            
            <!-- paging -->
			<div style="padding:20px;">
				<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
			</div>
			<!--// paging -->
			
			</div>
			<!--// data list -->
			
    	</form> 
    	<!-- End of Form -->

	    </div>
	    <!-- // container-fluid -->
    
  	  </div>
      <!-- End of Main Content --> 
      
  <%@ include file="/WEB-INF/footer.jsp" %>
  </div>
  <!-- End of Content Wrapper -->
 </div>
 <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  
  <!-- Login Modal-->
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                    <a href="main.jsp" class="btn btn-primary btn-user btn-block">
                      Login
                    </a>
                    <hr>
                    <!-- <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a> -->
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
 
 
 <!-- join Modal-->
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
                    <!-- <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div> -->
                    <a href="/login.do" class="btn btn-primary btn-user btn-block">
                      		회원가입
                    </a>
                    <hr>
                    <!-- <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a> -->
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
  
  
  <!-- find Modal(아이디 찾기) -->
   <div class="modal fade" id="findModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">아이디/비밀번호 찾기</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	 <form class="user">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="이메일">
                    </div>
                    <a href="index.jsp" class="btn btn-primary btn-user btn-block">
                      Login
                    </a>
                    <hr>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
  
	<script>
	
		//page 이동
		function doSearchPage(url, page_num) {
			//alert(url+","+page_num);
			var frm = document.recommendFrm;
			frm.work_div.value = "do_retrieve";
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
			
		}
		
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.recommendFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/SUNDAY/recommend/recommend.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
		
		//선택 초기화
		function do_reset() {
			
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
	      	
		}
		
		// 영화 상세정보 화면으로 이동: servlet -> detail_mng.jsp
		$("#dataTable>tbody").on("click","tr",function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var docid = cTds.eq(0).text();
			console.log("docid:"+docid);
			
			var frm = document.recommendFrm;
			frm.work_div.value ="do_retrieve";
			frm.action = '/SUNDAY/movie_detail.do?docid='+docid; // 미완성
			frm.submit();
			
		});
		
		$(document).ready(function(){
		});
		
	</script>

  <!-- Bootstrap core JavaScript-->
  <script src="/SUNDAY/vendor/jquery/jquery.min.js"></script>
  <script src="/SUNDAY/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/SUNDAY/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/SUNDAY/js/sb-admin-2.min.js"></script>

</body>

</html>
    