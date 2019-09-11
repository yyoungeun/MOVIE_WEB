<%--
  /**
  * @Class Name : useOutObject.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2019.07.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2019.07.01
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%@page import="com.sunday.moviedetail.dao.MovieDetailVO"%>
<%@page import="com.sunday.moviedetail.dao.SearchVO"%>
<%@page import="com.sunday.movie.domain.MovieVO"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/header.jsp" %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
	String id = (String)session.getAttribute("id");
	String docid = (String)request.getAttribute("docid");
	
	MovieDetailVO outVO = (MovieDetailVO)request.getAttribute("outVO");

	//화면목록
	List<MovieDetailVO> list = (List<MovieDetailVO>)request.getAttribute("list");
	//List<MovieVO> list1 = (List<MovieVO>)request.getAttribute("list1");
	
	//paging
    int maxNum			= 0; 							//총 글 수(server에서 받아와야 하는 값)
    int currPageNo		= 1;							//현재페이지(pageNum과 같음)
    int rowPerPage		= 10;							//row수(pageSize와 같음)
    int bottomCount		= 10;							//bottomCount(고정)
    String url			= "/SUNDAY/movie_detail.do";	//호출URL
    String scriptName	= "do_search_page";				//Javascript함수명
	
    //out.print(list);
	//Param
    String pageNum    = "1";
    String searchDiv  = "";
    String searchWord = "";
    String pageSize   = "10";
    
	SearchVO paramVO = (SearchVO)request.getAttribute("paramVO");

	if(null != paramVO){
	     pageNum    = paramVO.getPageNum()+"";
	     searchDiv  = paramVO.getSearchDiv();
	     searchWord = paramVO.getSearchWord();
	     pageSize   = paramVO.getPAGE_SIZE()+"";		
	     LOG.debug("===========================");
	     LOG.debug("paramVO="+paramVO);
	     LOG.debug("===========================");
	     rowPerPage=Integer.parseInt(pageSize);
		 currPageNo=Integer.parseInt(pageNum);
		 maxNum = (Integer) request.getAttribute("totalCnt");

	}
%>    
<!DOCTYPE html>
<html>
<head>
<style>
	td{
		padding-right:20px;
	}
	#write {
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
	font-size:15px;
	font-weight:bold;
	padding:6px 24px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
}
#write:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
	background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
	background-color:#f6f6f6;
}
#write:active {
	position:relative;
	top:1px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="movieDetail" id="movieDetail" method="get" >
		<input type="hidden" name="work_div"  id="work_div" />
		<input type="hidden" name="page_num"   />
		<input type="hidden" name="user_id" id="user_id" value="<%=StringUtil.nvl(id, "") %>"/>
		<input type="hidden" name="docid" id="docid"/>
		<input type="hidden" name="search_word" id="search_word"/>
		<input type="hidden" name="user_rate" id="user_rate"/>
		
		<!-- 영화상세정보  -->

					
		<!--// 검색영역 -->
		<div class="card mb-3 py-0 border-left-info" style="margin: auto; margin-left:50px; width:900px; height:auto;">
			    	<div class="card-body">
			 <table class="listTable"> 
							<tr>
								<td rowspan="9" style="width:160px; align:center; vertical-align: top;" id="poster">
								<img src=<%=StringUtil.nvl(outVO.getPosters(), "/SUNDAY/movie/noimage.jpg") %> width="230" height="300" style="vertical-align:top; align:center;"/>
								</td>
							</tr>
							<tr><td id="movieNum" style="display:none;"><%=outVO.getDocid() %></td></tr>
							<tr>
								<td><h2><%=StringUtil.nvl(outVO.getTitle(), " ") %></h2><br/></td>
							</tr>
							<tr>
								<td>장르: <%=StringUtil.nvl(outVO.getGenre()," ") %></td>
							</tr>
							<tr>
								<td>국가: <%=StringUtil.nvl(outVO.getNation()," ") %></td>
							</tr>
							<tr>
								<td>감독: <%=StringUtil.nvl(outVO.getDirectorNm()," ") %></td>
							</tr>
							<tr>
								<td>제작년도: <%=StringUtil.nvl(outVO.getProdYear(), " ") %></td>
							</tr>
							<tr>
								<td>개봉일: <%=StringUtil.nvl(outVO.getReleaseDate(), " ") %></td>
							</tr>
							<tr>
								<td>줄거리: <br/><br/><p style="font-size: 11pt; line-height:2.0em"><%=StringUtil.nvl(outVO.getPlot(), " ") %></p></td>
							</tr>
				
						</table>
						</div>
			</div>

		<table class="table table-striped" id="listTable" style="margin-left:50px; width:900px;">
			<thead>
				<tr>
					<!-- <th>docid</th> -->
					<th width="10%">ID</th>
					<th width="80%">리뷰</th>
					<th width="10%">평점</th>
						
				</tr>
			</thead>
			<tbody>
				<%
					if(null != list && list.size()>0){
						for(MovieDetailVO vo :list){
				%>
						<tr>
							<td><%= vo.getUser_id() %></td>
							<td><%= vo.getContents() %></td>
							<td>
								<select class="stars"> 
						           			<option value=""></option> 
						           			<option value="1" <%if(vo.getUser_rate()==1)out.print("selected"); %>>1</option> 
						           			<option value="2" <%if(vo.getUser_rate()==2)out.print("selected"); %>>2</option> 
						           			<option value="3" <%if(vo.getUser_rate()==3)out.print("selected"); %>>3</option> 
						           			<option value="4" <%if(vo.getUser_rate()==4)out.print("selected"); %>>4</option> 
						           			<option value="5" <%if(vo.getUser_rate()==5)out.print("selected"); %>>5</option>
					           	</select>
				           	</td>
						</tr>
				<%	
					    }//--for 
					}else{  
				%>
						<tr>
							<td colspan="99">등록된 리뷰가 없습니다.</td>
						</tr>
				
				<%	}//--else %>
			</tbody>
		</table>
		
		<div style="float: left; margin-left: 500px;">
		<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
	
		<br/>
		<label style="margin-left: 50px; font-size: 8pt; margin-bottom: 0px;">*한 영화에 한 개의 리뷰만을 쓸 수 있습니다.</label><br/>
	<div class="row" style="margin-left:50px; margin-top: 10px;">
					<input type="text" name="contents" id="contents" size="85"  maxlength="20" placeholder="리뷰를 남겨 주세요." />
					&nbsp;&nbsp;<!-- <label style="font-size: 8pt; margin-bottom: 0px; margin-right: 10px;">평점</label> -->
					<select class="rateStars"> 
						           			<option value=""></option> 
						           			<option value="1">1</option> 
						           			<option value="2">2</option> 
						           			<option value="3">3</option> 
						           			<option value="4">4</option> 
						           			<option value="5">5</option>
					 </select>
					<input type="button" name="write" id="write" value="등록" style="margin-left: 10px;">
					
				</div>
	</form>
	<br/>
	<br/>
	<br/>
    <br/>
    <br/>
    <br/>
    <br/>
    
	<script>
	
	//페이징
	function do_search_page(url, page_num){
		//alert(url+","+page_num);
		var frm = document.movieDetail;
		frm.work_div.value = 'do_retrieve';
		frm.page_num.value = page_num;
		frm.action = url;
		frm.submit();
	}
	
	//별점 설정
	$('.stars').barrating({ theme: 'fontawesome-stars' , readonly: true });
	$('.rateStars').barrating({ theme: 'fontawesome-stars' , readonly: false });
	
	//등록
	$('#write').on("click", function(){
		
		var id = $("#user_id").val();
		//alert(userRate);
		if(null==id || id.trim().length==0){
			alert('로그인 후에 리뷰를 남길 수 있습니다.');
			return;
		} 
		
		var contents = $("#contents").val();
		//alert(contents);
		  if(null==contents || contents.trim().length==0){
			$("#contents").focus();
			alert('리뷰를 입력하세요.');
			return;
		} 
		
 		var userRate = $(".rateStars").val();
		//alert(userRate);
 		if(null==userRate || userRate==""){
			alert('평점을 입력하세요.');
			return;
		} 
		
		var docid = $("#movieNum").text();
		//alert(docid);
		
		var frm = document.movieDetail;		
        frm.docid.value=docid;
        frm.search_word.value=docid;
        frm.contents.value=contents;
        frm.user_rate.value=userRate;
        frm.page_num.value=1;
        frm.work_div.value="do_insert";
        
        var param = $("#movieDetail").serialize(); //form의 값을 모두 다 가져오는 것
        //alert(param);   
        
      	//ajax
	 	   $.ajax({
				     type:"POST",
				     url:"/SUNDAY/movie_detail.do",
				     dataType:"html",
				     data:param,
				success: function(data){
						//console.log('data:'+data);
						var jData = JSON.parse(data);
				     	if(null != jData && jData.msgId=="1"){
				      		alert(jData.msgContents);
				      		//window.location.href="/SUNDAY/movie_detail.do?work_div=do_retrieve&docid="+docid;
				      		location.reload();
				     	}else{
				     		alert(jData.msgContents);
				     	}
				},
				complete:function(data){
				     
				},
				error:function(xhr,status,error){
				     alert("error:"+error);
				}
			});   
		//--ajax 
	});

	</script>
</body>
</html>
<%@ include file="/WEB-INF/footer.jsp" %>