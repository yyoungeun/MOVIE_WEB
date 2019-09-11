<%@page import="com.sunday.movieregister.MovieRegisterVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/header.jsp" %>
<%
	MovieRegisterVO vo = (MovieRegisterVO)request.getAttribute("vo");
    //out.print(vo);
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
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">
  <%-- <%@ include file="/WEB-INF/header.jsp" %>	 --%>
	
    <div class>
    
      <div class="card-body p-0">
        
        <!-- Begin Page Content -->
       	<div class="container-fluid">
       	
        <!-- Page Heading -->
   		<div class="d-sm-flex align-items-center justify-content-between mb-4">
       	<h1 class="h3 mb-0 text-gray-800">영화 등록</h1>
   		</div>
   		
	    <!-- 영화 정보 관리 Form -->
	    <form class="container-fluid" name="movRegFrmMng" id="movRegFrmMng" action="/SUNDAY/movie_register/movie_register.do" method="post" >
	       
	       	<div class="col mr-2">
		        <input type="hidden"  name="work_div" id="work_div" />
		              
		        <div class="form-group row">
		        	<div class="col-sm-12">     
				    	<label>등록번호</label>
				    	<input class="form-control form-control-user onlyAlphabetAndNumber" type="text" value="${vo.getDOCID() }"  name="docid" id="docid" size="20"  maxlength="20" placeholder="ex) A00001" />
			    	</div>
				</div>
				 
				<div class="form-group row"> 
			    	<div class="col-sm-6 mb-3 mb-sm-0">
				    	<label>제목</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getTitle()}"  name="title" id="title" size="100" maxlength="100"  />
			    	</div>
					<div class="col-sm-6">
						<label>영제</label>
			    		<input class="form-control form-control-user" type="text" value="${vo.getTitleEng() }" name="title_eng" id="title_eng" size="100" maxlength="100"  />
			    	</div>
				</div>
				
				<div class="form-group row">
					<div class="col-sm-12">
						<label>장르</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getGenre() }" name="genre" id="genre" size="20" maxlength="20"  />
				    </div>
			    </div>
				
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<label>감독이름</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getDirectorNm() }"  name="director_nm" id="director_nm" size="30" maxlength="30"  />
				    </div>
					<div class="col-sm-6">
						<label>감독코드</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getDirectorId() }"  name="director_id" id="director_id" size="30" maxlength="30" placeholder="숫자만 입력" />
				    </div>
				</div>
				
				<div class="form-group row">
					<div class="col-sm-12">
						<label>국가</label>
				    	<input class="form-control form-control-user"  type="text" value="${vo.getNation() }"  name="nation" id="nation" size="30" maxlength="30"  />
				    </div>
		    	</div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-6 mb-3 mb-sm-0">
				    	<label>배우이름</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getActorNm() }"  name="actor_nm" id="actor_nm" size="30" maxlength="30"  />
				    </div>
			    	<div class="col-sm-6">
				    	<label>배우코드</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getActorId() }"  name="actor_id" id="actor_id" size="30" maxlength="30" placeholder="숫자만 입력" />
				    </div>
		    	</div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-12">
				    	<label>키워드</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getKeywords() }"  name="keywords" id="keywords" size="30" maxlength="30"  />
			    	</div>
		    	</div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-12">
				    	<label>포스터</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getPosters() }"  name="posters" id="posters" size="100" maxlength="100" placeholder="이미지 경로 입력" />
				    </div>
		    	</div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-6 mb-3 mb-sm-0">
				    	<label>개봉일</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getReleaseDate() }"  name="release_date" id="release_date" size="10" maxlength="10" placeholder="ex) 2019/07/30" />
				    </div> 
				    <div class="col-sm-6">
				    	<label>제작년도</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getProdYear() }"  name="prod_year" id="prod_year" size="10" maxlength="10" placeholder="ex) 2019/07/30" />
				    </div>	
		    	</div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-12">
				    	<label>평점</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getRate() }"  name="rate" id="rate" size="5" maxlength="5" placeholder="ex) 3.5" />
				    </div>
		    	</div>
		    	
				<div class="form-group row">
		    		<div class="col-sm-12">	
				    	<label>줄거리</label>
				    	<textarea class="form-control form-control-user" rows="6" name="plot" id="plot">${vo.plot}</textarea>
			    	</div>
				</div> 
				
	 			<%--<label>등록자ID</label>
		    	<div class="col-sm-6"><input class="form-control form-control-user" type="text" value="${vo.getRegId() }" name="reg_id" id="reg_id" size="20" maxlength="20"  readonly="readonly"/></div><br/>
				
				<label>등록일</label>
		    	<div class="col-sm-6"><input class="form-control form-control-user" type="text" value="${vo.getRegDt() }" name="reg_dt" id="reg_dt" size="10" maxlength="10"  readonly="readonly"/></div><br/> --%>
				
 				<%-- <label>수정자ID</label>
		    	<div class="col-sm-6"><input type="text" value="${vo.getModId() }" name="mod_id" id="mod_id" size="20" maxlength="20"  readonly="readonly"/></div><br/>
				
				<label>수정일</label>
		    	<div class="col-sm-6"><input type="text" value="${vo.getModDt() }"  name="mod_dt" id="mod_dt" size="10" maxlength="10"  readonly="readonly"/></div><br/> --%>
		    	
				<div class="form-group row">
		    		<div class="col-sm-12">
				    	<label>현재상영여부</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getNowShowing() }" name="now_showing" id="now_showing" size="10" maxlength="2" />
			    	</div>
			    </div>
		    	
		    	<div class="form-group row">
		    		<div class="col-sm-3">
				    	<label>기분</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getRecM() }"  name="rec_m" id="rec_m" size="10" maxlength="1" placeholder="없으면 0"/>
				    </div>
			    	<div class="col-sm-3">
				    	<label>날씨</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getRecW() }"  name="rec_w" id="rec_w" size="10" maxlength="1" placeholder="없으면 0"/>
				    </div>
			    	<div class="col-sm-3">
				    	<label>시간</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getRecT() }"  name="rec_t" id="rec_t" size="10" maxlength="1" placeholder="없으면 0"/>
				    </div>
			    	<div class="col-sm-3">
				    	<label>사람</label>
				    	<input class="form-control form-control-user" type="text" value="${vo.getRecP() }"  name="rec_p" id="rec_p" size="10" maxlength="1" placeholder="없으면 0"/>
			    	</div>
	   			</div>
	   		</div>


	   </form>
	   <!--// 영화 정보 관리 Form -->
	   
	   	<br/>
   		<hr>
   		<!-- button area -->
	    <div class="col-xl-9 col-md-10 mb-4" style="float: right;">
	   		<input class="text-s btn btn-secondary" type="button"  value="목록으로" id="listTo_btn" />
	    	<input class="text-s btn btn-secondary" type="button"  value="초기화" id="reset_btn" />
	    	<input class="text-s btn btn-secondary" type="button"  value="등록" id="movie_save_btn" />
	    	<input class="text-s btn btn-secondary" type="button"  value="수정" id="movie_update_btn" />
	    	<input class="text-s btn btn-secondary" type="button"  value="삭제" id="movie_del_btn" />
	    </div>
	    <!--// button area -->
	   	<br/>
	    <br/>
		    <br/>
	   
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
		//삭제 function
	    function doDel(){
	    	if( false == confirm('삭제 하시겠습니까?'))return;
	    	var frm = document.movRegFrmMng;
	    	frm.work_div.value = "do_delete";
	    	frm.action = "/SUNDAY/movie_register/movie_register.do";
	    	frm.submit();
	    }
	
		//삭제:buttonEvent
		//             비동기통신             JSON
        //movie_register_mng.jsp -> servlet -> movie_register_mng.jsp -> movie_register_list.jsp
		$("#movie_del_btn").on('click',function(){
			console.log('movie_del_btn');
			console.log($("#docid").val());
			//validation
			var docid = $("#docid").val();
			if(null == docid || docid.trim().length == 0){
				$("#docid").focus();
				alert('등록번호를 입력 하세요.');
				return ;
			}
			
			if( false == confirm(docid+'을(를)\n삭제 하시겠습니까?'))return;
			
			//ajax
 			$.ajax({
					type:"POST",
					url:"/SUNDAY/movie_register/movie_register.do",
					dataType:"html",
					data:{
						"work_div":"do_delete",
						"docid":$("#docid").val()
				    }, 
				success: function(data){
					//{"msgId":"1","msgContents":"삭제되었습니다.","total":0,"num":0}
					var jData = JSON.parse(data);
					console.log(jData.msgId+"|"+jData.msgContents);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgContents);
						window.location.href="/SUNDAY/movie_register/movie_register.do?work_div=do_retrieve";
					}else{
						alert(jData.msgId+"|"+jData.msgContents); 
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
	
        //수정	
        $("#movie_update_btn").on('click',function(){
        	//alert('update_btn');
        	//validation:필수 check
        	var docid = $("#docid").val();
			if(null == docid || docid.trim().length == 0){
				$("#docid").focus();
				alert('등록번호를 입력 하세요.');
				return ;
			}  
			
			$("#work_div").val("do_update");//"do_update" set
			var param = $("#movRegFrmMng").serialize();
			//alert(param)
			
        	if( false==confirm(docid+'을(를)\n수정 하시겠습니까?'))return;
        	
        	//ajax
            $.ajax({
               type:"POST",
               url:"/SUNDAY/movie_register/movie_register.do",
               dataType:"html",
               data:param, 
            success: function(data){
              var jData = JSON.parse(data);
              if(null != jData && jData.msgId=="1"){
                alert(jData.msgContents);
                window.location.href="/SUNDAY/movie_register/movie_register.do?work_div=do_retrieve";
              }else{
                alert(jData.msgId+"|"+jData.msgContents);
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
        
        //등록
        $("#movie_save_btn").on('click',function(){
        	
        	//alert('save_btn');
        	
        	//validation:필수 check
        	var docid = $("#docid").val();
			if(null == docid || docid.trim().length == 0){
				$("#docid").focus();
				alert('등록번호를 입력 하세요.');
				return ;
			}        	
        	
			$("#work_div").val("do_insert");//"do_insert" set
			var param = $("#movRegFrmMng").serialize();
			//alert(param);
			
        	if( false==confirm(docid+'을(를)\n등록 하시겠습니까?'))return;
        	
        	//ajax
            $.ajax({
               type:"POST",
               url:"/SUNDAY/movie_register/movie_register.do",
               dataType:"html",
               data:param, 
            success: function(data){
              var jData = JSON.parse(data);
              if(null != jData && jData.msgId=="2"){
                alert(jData.msgContents);
                window.location.href="/SUNDAY/movie_register/movie_register.do?work_div=do_retrieve";
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
        
        // 초기화
        $("#reset_btn").on('click', function(){
        	
        	//alert('reset_btn');
        	$("#docid").removeAttr("readonly");
			$("#reg_id").removeAttr("readonly")
			
			$("#docid").val("");
			$("#title").val("");
			$("#title_eng").val("");
			$("#genre").val("");
			$("#director_nm").val("");
			$("#director_id").val("");
			$("#nation").val("");
			$("#actor_nm").val("");
			$("#actor_id").val("");
			$("#keywords").val("");
			$("#posters").val("");
			$("#release_date").val("");
			$("#prod_year").val("");
			$("#rate").val("");
			$("#plot").val("");
			$("#now_showing").val("");
			$("#rec_m").val("");
			$("#rec_w").val("");
			$("#rec_t").val("");
			$("#rec_p").val("");

        });
        
        // 목록으로 이동 함수
        function moveTolist() {
        	
        	if(false==confirm('목록으로 이동하시겠습니까?')) return;
        	
        	var frm = document.movRegFrmMng;
        	frm.work_div.value = "do_move_to_list";
        	frm.action = "/SUNDAY/movie_register/movie_register.do"
        	frm.submit();
        }
        
        // 목록 이동 버튼 
        $("#listTo_btn").on('click',function(){
        	moveTolist();
        });
        
        
		$(document).ready(function(){
			console.log('1mode:'+'${mode}')
			
			if(null !='${mode}' && 'insert'=='${mode}'){
				console.log('2 mode:'+'${mode}');
				$("#docid").removeAttr("readonly");
				$("#reg_id").removeAttr("readonly")
			}
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