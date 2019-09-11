<%@page import="java.util.Random"%>
<%@page import="com.sunday.recommend.RecommendVO"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/header.jsp" %>
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
	
	<!-- bar-rating --> 
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"> 
	<link rel="stylesheet" href="/SUNDAY/css/fontawesome-stars.css"> 
	<script type="text/javascript" src="/SUNDAY/js/jquery.barrating.min.js"></script>
	

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">
 <%--  <%@ include file="/WEB-INF/header.jsp" %>	 --%>
  
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
            <section class="section">
				<div class="container">
	            	<div class="row">
							<tbody>
								<%
									if(null != list && list.size()>0){
										for(RecommendVO vo :list){
								%>			<div class="col-lg-4 col-sm-6 mb-4">
									          <article height="300px" width="200px" class="card shadow">
									          	<img height="350px" width="200px" class="rounded card-img-top" src="<%=StringUtil.nvl(vo.getPosters(), "/SUNDAY/movie/noimage.jpg") %>" alt="post-thumb"/>
									            <div height="200px" width="200px" class="card-body">
									              <h5 class="card-title">
									              	<a class="text-dark"><%=vo.getTitle() %></a>
									              </h5>
									           	  <p class="cars-text">
									           	  	<%=StringUtil.nvl(vo.getGenre(), "미입력") %>&nbsp;/&nbsp;
									           	  	<%=StringUtil.nvl(vo.getNation(), "미입력") %>&nbsp;/&nbsp;
									           	  	<%=StringUtil.nvl(vo.getReleaseDate(), "미입력") %>&nbsp;
												  <div>
													  <select class="stars"> 
										           	  	  <option value=""></option> 
										           		  <option value="1" <%if(vo.getRate()==1)out.print("selected"); %>>1</option> 
										           		  <option value="2" <%if(vo.getRate()==2)out.print("selected"); %>>2</option> 
										         		  <option value="3" <%if(vo.getRate()==3)out.print("selected"); %>>3</option> 
										           		  <option value="4" <%if(vo.getRate()==4)out.print("selected"); %>>4</option> 
										           		  <option value="5" <%if(vo.getRate()==5)out.print("selected"); %>>5</option>
									           		  </select>
												  </div>		           	  	
									           	  </p>
									           	  
									           	  <!-- 버튼 -->
												  <div>
													<a href="/SUNDAY/movie_detail.do?work_div=do_retrieve&search_word=<%=vo.getDOCID() %>" class="btn btn-xs btn-primary">상세보기</a>
												  </div>
												  <!--// 버튼 -->
												  
												</div>
									          </article>
										    </div>
								<%	
								    	}//--for 
									}else{   
								%>
										<tr>
											<td colspan="99">No data found</td>
										</tr>
							
								<%	}//--else %>
							</tbody>
						
					
					</div>
	
            <!-- paging -->
			<div style="padding:20px;">
				<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
			</div>
			<!--// paging -->
			
			</div>
			<!--// data list -->
			</section>
			
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
  
	<script>
	
		//별점 설정
		$('.stars').barrating({ theme: 'fontawesome-stars' , readonly: true });

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
	
		$(document).ready(function(){
		});
		
	</script>

  <!-- Bootstrap core JavaScript-->
<!--   <script src="/SUNDAY/vendor/jquery/jquery.min.js"></script>
  <script src="/SUNDAY/vendor/bootstrap/js/bootstrap.bundle.min.js"></script> -->

  <!-- Core plugin JavaScript-->
<!--   <script src="/SUNDAY/vendor/jquery-easing/jquery.easing.min.js"></script> -->

  <!-- Custom scripts for all pages-->
<!--   <script src="/SUNDAY/js/sb-admin-2.min.js"></script> -->

</body>

</html>
    