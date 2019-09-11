
<%@page import="com.sunday.userREVIEW.SearchVO"%>
<%@page import="com.sunday.userREVIEW.ReviewVO"%>
<%@page import="com.sunday.movie.domain.MovieVO"%>
<%@page import="com.sunday.cmn.StringUtil"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/header.jsp" %>
<%
	Logger LOG = Logger.getLogger(this.getClass());
    //String id = (String)session.getAttribute("id");
	//화면목록
	List<ReviewVO> list = (List<ReviewVO>)request.getAttribute("list");
	String id =(String)session.getAttribute("id");
    LOG.debug("===========================");
    LOG.debug("id="+id);
    LOG.debug("===========================");
    //out.print(list);
	//Param
    String pageNum    = "1";
    String searchDiv  = "";
    String searchWord = "";
    String pageSize   = "10";
    
  	//paging
    int maxNum			= 0; 							//총 글 수(server에서 받아와야 하는 값)
    int currPageNo		= 1;							//현재페이지(pageNum과 같음)
    int rowPerPage		= 10;							//row수(pageSize와 같음)
    int bottomCount		= 10;							//bottomCount(고정)
    String url			= "/SUNDAY/user_review.do";			//호출URL
    String scriptName	= "do_search_page";	
    
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
  <style type="text/css">
    .updateBtn, .update, .delete {
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
	font-size:10px;
	font-weight:bold;
	padding:1px 1px;
	text-decoration:none;
	text-shadow:0px 1px 0px #ffffff;
	width: 40px; height:30px;
}
.updateBtn, .update, .delete:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f6f6f6), color-stop(1, #ffffff));
	background:-moz-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-webkit-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-o-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:-ms-linear-gradient(top, #f6f6f6 5%, #ffffff 100%);
	background:linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f6f6f6', endColorstr='#ffffff',GradientType=0);
	background-color:#f6f6f6;
}
.updateBtn, .update, .delete:active {
	position:relative;
	top:1px;
}
    
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
  	<form name="userReviewForm" method="get" >
		<input type="hidden"  name="work_div"  id="work_div" />
		<input type="hidden"  name="page_num"   />
		<input type="hidden" name="docid"/>
		<input type="hidden"  name="search_word" value="<%=id %>" />
		<input type="hidden" name="contents" id="contents"/>
		<input type="hidden" name="user_rate" id="user_rate"/> 
		
	<!-- 영화상세정보  -->
		
 	<div name="id" id="id" style="display:none;"><%=id %></div>
	<h3 style="margin-left:50px; vertical-align: top;">내가 쓴 리뷰/평점</h3>
		<%if(id==null){ %>
				<label style="margin-left:50px;">로그인이 필요한 서비스입니다.<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/></label>
		<%}else{ %>
		<!-- data list -->
 		<table class="table table-striped" id="listTable" style="width:1000px; margin-left:50px; vertical-align: top;">
			<thead>
				<tr>
					<th width="10%" style="text-align: center;">영화번호</th>
					<th width="30%" style="text-align: center;">제목</th>
					<th width="20%" style="text-align: center;">내용</th>
					<th width="10%" style="text-align: center;">평점</th>
					<th colspan="2" width="30%" style="text-align: center;">변경</th>					
				</tr>
			</thead>
			<tbody>
			
				<%
					if(null != list && list.size()>0){
						for(ReviewVO vo :list){
				%>
						<tr>
							<td class="docid" width="10%"><%= vo.getDocid() %></td>
						 	<td width="20%"><%= vo.getTitle() %></td>
							<td width="50%"><%= vo.getContents() %></td>
							<td width="10%">
							<select class="stars"> 
						          <option value=""></option> 
						          <option value="1" <%if(vo.getUser_rate()==1)out.print("selected"); %>>1</option> 
						          <option value="2" <%if(vo.getUser_rate()==2)out.print("selected"); %>>2</option> 
						          <option value="3" <%if(vo.getUser_rate()==3)out.print("selected"); %>>3</option> 
						          <option value="4" <%if(vo.getUser_rate()==4)out.print("selected"); %>>4</option> 
						          <option value="5" <%if(vo.getUser_rate()==5)out.print("selected"); %>>5</option>
					        </select>
					        </td>
							<td width="10%" style="text-align: center;">
								<div class="row">
									<ul class="navbar-nav ml-auto">
										<li class="nav-item dropdown no-arrow">
             		 						<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                								<span class="mr-2 d-none d-lg-inline text-gray-800 small"></span>
                								<input type="button" class="updateBtn" name="updateBtn" id="updateBtn" value="수정"/>
              								</a>
              					<!-- 수정 dropdown -->
              								<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">	
                								<a class="dropdown-item" href="#" data-toggle="modal">
                  									<input type="text" class="updateReview" size="60" value="<%= vo.getContents() %>" />
                  									<select class="updateStars"> 
												    	<option value=""></option> 
												        <option value="1" <%if(vo.getUser_rate()==1)out.print("selected"); %>>1</option> 
												        <option value="2" <%if(vo.getUser_rate()==2)out.print("selected"); %>>2</option> 
												        <option value="3" <%if(vo.getUser_rate()==3)out.print("selected"); %>>3</option> 
												        <option value="4" <%if(vo.getUser_rate()==4)out.print("selected"); %>>4</option> 
												        <option value="5" <%if(vo.getUser_rate()==5)out.print("selected"); %>>5</option>
				  									</select>
				  									<input type="button" class="update" name="update" id="update" value="수정"/> 
                								</a>
              								</div>
           	 							</li>
           							</ul>
							</div>
						</td>
						<td style="float:center; padding: 20px;">
							<input type="button" class="delete" name="delete" id="delete" value="삭제"/> 
						</td>
					</tr>
				<%	
					    }//--for 
					}else{  
				%>
						<tr>
							<td colspan="99">등록한 리뷰가 없습니다.</td>
						</tr>
				<%	}//--else %>
			
			</tbody>
		</table>
			<div style="float: left; margin-left: 550px;">
				<%=StringUtil.renderPaging(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
			</div>
		<%} %>
 	</form>
 	
    <br/>
    <br/>
    <br/>
    <br/>
	
	<script>
		
		//페이징
		function do_search_page(url, page_num){
			//alert(url+","+page_num);
			var frm = document.userReviewForm;
			frm.work_div.value = 'do_retrieve';
			frm.page_num.value = page_num;
			frm.action = url;
			frm.submit();
		}
		
		//별점 설정
		$('.stars').barrating({ theme: 'fontawesome-stars' , readonly: true });
		$('.updateStars').barrating({ theme: 'fontawesome-stars' , readonly: false });
	
		//수정
		$(".update").on("click", function(){
			//alert('update');
			var updateBtn = $(this);
			
			var a = updateBtn.parent();
			var newReview = a.children().val();
			//alert(newReview);
			
			var id = $("#id").text();
			
			var tr = updateBtn.parent().parent().parent().parent().parent().parent().parent();
			var td = tr.children();
			var docid = td.eq(0).text();
			//alert(docid);
			var newRate = $(a).find('.updateStars').val();
			//alert(newRate);
			
			if(false==confirm('수정하시겠습니까?')) return;
			
			//ajax
		 	   $.ajax({
					     type:"POST",
					     url:"/SUNDAY/user_review.do",
					     dataType:"html",
					     data:{"work_div":"do_update",
					     	   "docid":docid,
					     	   "search_word":id,
					     	   "user_rate":newRate,
					     	   "contents":newReview
					     	  },
					success: function(data){
							//console.log('data:'+data);
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					     		alert(jData.msgContents);
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
		});
		
		//삭제
		$(".delete").on("click", function(){
			/* var str = "";
			var tdArr = new Array(); */
			var deleteBtn = $(this);
			
			var tr = deleteBtn.parent().parent();
			var td = tr.children();
			var docid = td.eq(0).text();
			//alert(docid);
			
			var id = $("#id").text();
			
			if(false==confirm('삭제하시겠습니까?')) return;
			
	 		 //ajax
		 	   $.ajax({
					     type:"POST",
					     url:"/SUNDAY/user_review.do",
					     dataType:"html",
					     data:{"work_div":"do_delete",
					     	   "docid":docid,
					     	   "search_word":id
					     	  },
					success: function(data){
							//console.log('data:'+data);
							var jData = JSON.parse(data);
					     	if(null != jData && jData.msgId=="1"){
					     		alert(jData.msgContents);
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
		});
		
	</script>
</body>
</html>
<%@ include file="/WEB-INF/footer.jsp" %>