<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="com.sunday.movieregister.SearchVO"%>
<%@page import="com.sunday.movieregister.MovieRegisterVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  <%@ include file="/WEB-INF/header.jsp" %> --%>
<style type="text/css">
	.target {
	/* 한 줄 자르기 */
	display: inline-block;
	width: 200px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	
	/* 여러 줄 자르기 추가 스타일 */
	white-space: normal;
	line-height: 1.24;
	height: 224px;
	text-align: left;
	word-wrap: break-word;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	}
</style>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	//화면목록
	List<MovieRegisterVO> list = (List<MovieRegisterVO>)request.getAttribute("list");

	//Param
    String pageNum    = "1";
    String pageSize   = "10";
    
    // paging
    int maxNum = 0; // 총글수 -> 검색할 때마다 달라지므로 서버에서 받아와야 함
    int currPageNo = 1; // 현재 페이지 = pageNum
    int rowPerPage = 10; // 한 번에 보여질 글 수  = pageSize
    int bottomCount = 10; 
    String url ="/SUNDAY/movie_register/movie_register.do"; // 호출 url
    String scriptName ="doSearchPage"; // javascript 함수명
    
    
	SearchVO paramVO = (SearchVO)request.getAttribute("paramVO");
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
  
  	<title>영화 등록</title>
  
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
       	<h1 class="h3 mb-0 text-gray-800">영화 등록</h1>
   		</div>
   		
   		<!-- class=row -->
		<div class="row">
		<!-- 버튼 -->
		<div class="col-xl-9 col-md-10 mb-4" >
			<input class="text-s btn btn-secondary" type="button" value="신규 영화 등록" onclick="javascript:do_save_move();" />
		</div>
		<!--// 버튼 -->
		</div>
        <!--// class=row -->
   		
		<form class="container-fluid" name="registerFrm" action="/SUNDAY/movie_register/movie_register.do" method="post" >
			
			<input type="hidden"  name="work_div"   />
			<input type="hidden"  name="page_num"   />
		
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
									<th>등록번호</th>   
								    <th>제목</th>    
								    <!-- <th>영제</th>  -->
								    <!--  <th>장르</th>   -->  
								    <th>감독이름</th>  
								    <!-- <th>감독코드</th>   -->
								    <th>국가</th> 
								    <th>배우이름</th>  
								    <!-- <th>배우코드</th> -->  
								    <!-- <th>키워드</th>  -->
								    <th>포스터</th>
								    <th>개봉일</th>   
								    <!-- <th>제작년도</th> -->
								    <!-- <th>평점</th>  -->
								    <th>줄거리</th> 
								    <th>등록자ID</th> 
								    <th>등록일</th>   
								    <th>수정자ID</th> 
								    <th>수정일</th>
								    <th>현재상영중</th>
									<th>기분</th>
									<th>날씨</th>
									<th>시간</th>
									<th>사람</th>				
								</tr>
							</thead>
							<tbody>

								<%
									if(null != list && list.size()>0){
										for(MovieRegisterVO vo :list){
								%>
										<tr>
											<td><%=vo.getDOCID() %></td>
											<td><%=vo.getTitle() %></td>
											<%-- <td><%=vo.getTitleEng() %></td> --%>
											<%-- <td><%=vo.getGenre() %></td> --%>
											<td><%=StringUtil.nvl(vo.getDirectorNm(), "") %></td>
											
											<%-- <td><%=vo.getDirectorId() %></td> --%>
											<td><%=StringUtil.nvl(vo.getNation(), "") %></td>
											<td><%=StringUtil.nvl(vo.getActorNm(), "") %></td>
											<%-- <td><%=vo.getActorId() %></td> --%>
											<%-- <td><%=vo.getKeywords() %></td> --%>
											
											<td width="150px" height="199px"><img width="150px" height="199px" src="<%=StringUtil.nvl(vo.getPosters(), "/SUNDAY/movie/noimage.jpg") %>" /></td>
											<td><%=StringUtil.nvl(vo.getReleaseDate(), "") %></td>
											<%-- <td><%=vo.getProdYear() %></td> --%>
											<%-- <td><%=vo.getRate() %></td> --%>
											<td width="200px" height="199px" class="target"><%=StringUtil.nvl(vo.getPlot(), "") %></td>
											
											<td><%=vo.getRegId() %></td>
											<td><%=vo.getRegDt() %></td>
											<td><%=StringUtil.nvl(vo.getModId(), "") %></td>
											<td><%=vo.getModDt() %></td>
											<td><%=vo.getNowShowing() %></td>
											
											<td><%=vo.getRecM() %></td>
											<td><%=vo.getRecW() %></td>
											<td><%=vo.getRecT() %></td>
											<td><%=vo.getRecP() %></td>
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
			
			<!--// data list -->
			
			<!-- paging -->
			<div class="container-fluid">
				<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
			</div>
			<!--// paging -->
				
		</form>
		
		</div>
	    <!-- End of Page Content -->
 	  </div>
      <!-- End of Main Content --> 
  </div>
  <!-- End of Content Wrapper -->
 </div>
 <!-- End of Page Wrapper -->
    
	<script>
		//page 이동
		function doSearchPage(url, page_num) {
			//alert(url+","+page_num);
			var frm = document.registerFrm;
			frm.work_div.value = "do_retrieve";
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
			
		}
	
	
		//등록화면으로 이동
		function do_save_move(){
			//alert('do_save_move');
			if(confirm("등록 하시겠습니까?")==false)return;
			
			var frm = document.registerFrm;
			frm.work_div.value = "do_save_move";
			frm.action  = '/SUNDAY/movie_register/movie_register.do';
			frm.submit();
		}
		
		
		//검색
		function do_retrieve(){
			//alert('do_retrieve');
			var frm = document.registerFrm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = '1';
			frm.action         = '/SUNDAY/movie_register/movie_register.do';
			
			//alert('frm.work_div.value:'+frm.work_div.value);
			frm.submit();
		}	
	
		//수정화면으로 이동: servlet -> movie_register_mng.jsp
		$("#dataTable>tbody").on("click","tr",function(){
			var cTrs = $(this);
			var cTds = cTrs.children();
			console.log(cTds);
			var docid = cTds.eq(0).text();
			console.log("docid:"+docid);
			
			var frm = document.registerFrm;
			frm.work_div.value ="do_selectone";
			frm.action = '/SUNDAY/movie_register/movie_register.do?docid='+docid;
			frm.submit();
			
		});
		
		$("#search_word").keypress(function(e) {
			//console.log("search_word: " + e);
			if(e.which == 13) {// enter
				do_retrieve();
			}
		});
		
		
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
<%@ include file="/WEB-INF/footer.jsp" %>