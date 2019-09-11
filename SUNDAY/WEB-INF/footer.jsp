 <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
 	<!--====================================================================================
    										화면 하단
 	======================================================================================-->
 	
	<div style="background-color: transparent; width:100%; height:100px;">

	</div>
	
	<!--====================================================================================
    										//화면 하단
 	======================================================================================--> 
 	
 	<!--====================================================================================
    										modal설정
 	======================================================================================--> 
 
  <!-- Login Modal-->
  <form name="loginForm" id="loginForm" action="/SUNDAY/login.do"  method="post">
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
        	<input type="hidden" name="work_div" id="work_div" />
        	 <form class="user">
                    <div class="form-group">
                      <input type="text"  name="user_id" id="user_id" maxlength="20" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="아이디">
                    </div>
                    <div class="form-group">
                      <input type="password" name="passwd" id="passwd" size="20" maxlength="20" class="form-control form-control-user" placeholder="비밀번호">
                    </div>
                    <!-- <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div> -->
                    <input type="button" value="로그인" class="btn btn-primary btn-user btn-block" id="login_btn" />
                    <hr>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
 </form>
 
 <!-- join Modal-->
 <form name="join_form"  id="join_form" class="user" method="post"> 
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
			<input type="hidden" name="work_div" id="work_div"/>
        	 	 <form class="user">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" name="join_id" id="join_id" aria-describedby="emailHelp" placeholder="아이디" maxlength="20" />
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="join_passwd" id="join_passwd" placeholder="비밀번호" maxlength="20" />
                    </div>
                     <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="join_repasswd" id="join_repasswd" placeholder="비밀번호 확인" maxlength="20" />
                    </div>
                     <div class="form-group">
                      <input type="text" class="form-control form-control-user" name="join_name" id="join_name" aria-describedby="emailHelp" placeholder="이름" maxlength="30" />
                    </div>
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" name="join_email" id="join_email" aria-describedby="emailHelp" placeholder="이메일" maxlength="320" />
                    </div>
                    <input type="submit" class="btn btn-primary btn-user btn-block" value="회원가입" />
                    <hr>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
</form>
 
  <!-- find Modal(아이디/비밀번호 찾기) -->
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
        	 <form class="user" role="form" name="formMailFrm" id="formMailFrm" method="post" action="<c:url value='/login.do' />">
                   <input type="hidden"  name="work_div" id="work_div" />  
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" name="find_email" id="find_email" aria-describedby="emailHelp" placeholder="example@domain.com">
                    </div>
                    <input id="mailSend" name="mailSend" type="button" value="메일보내기" class="btn btn-primary btn-user btn-block" />
                    <hr>
                  </form>
       		 </div>
     	 </div>
    </div>
 </div>
 
<!-- 회원정보 수정(updateModal) -->
 <!-- <form name="updateForm" id="updateForm" action="/SUNDAY/mypage.do" method="post"> -->
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
        <form name="updateForm" id="updateForm" class="user">
        <input type="hidden" name="work_div" id="work_div"/>
        	<!--  <form class="user"> -->
                    <div class="form-group">
                      <input type="text" value="${id }" readonly="readonly" name="mod_id" id="mod_id" maxlength="20" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                      <input type="password" value="${passwd }"  name="mod_passwd" id="mod_passwd" size="20" maxlength="20" class="form-control form-control-user" placeholder="비밀번호">
                    </div>
                     <div class="form-group">
                      <input type="password"  name="mod_repasswd" id="mod_repasswd" class="form-control form-control-user" placeholder="비밀번호 확인" >
                    </div>
                     <div class="form-group">
                      <input type="text" value="${name }" name="mod_name" id="mod_name" size="30" maxlength="30" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="이름">
                    </div>
                    <div class="form-group">
                      <input type="email" value="${email }" readonly= "readonly" name="email"  id="email" size="100" maxlength="150" class="form-control form-control-user"  aria-describedby="emailHelp" >
                    </div>
                    <div class="form-group">
                     	등록일 <input type="text" readonly= "readonly" value="${reg_dt }" name="reg_dt" id="reg_dt" size="10" maxlength="10" class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                     	 수정일<input type="text" readonly= "readonly" value="${mod_dt }" name="mod_dt" id="mod_dt" size="10" maxlength="10"  class="form-control form-control-user"  aria-describedby="emailHelp">
                    </div>
                    <hr>
                    <input type="submit" value="수정완료"  class="btn btn-primary btn-user btn-block" />
                    <input type="button" value="계정삭제" id="del_btn" class="btn btn-primary btn-user btn-block" />
       		  	</form>
       		 </div>
     	 </div>
    </div>
 </div>
 <!-- </form> -->
 
   <!-- Logout Modal-->
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
          <button id="logoutBtn" name="logoutBtn" class="btn btn-secondary" type="button" data-dismiss="modal">Logout</button>
        </div>
      </div>
    </div>
  </div>
  
  <!--====================================================================================
    										//modal설정
 	======================================================================================--> 
 	
 	<!-- 다국어처리 -->
 	<form name="langForm" id="langForm">
 	<input type="hidden" name="work_div" id="work_div">
	<input type="hidden" name="language" id="language">
	</form>
	
   <script>
	//검색창에 검색하기
		$("#searchBtn").on("click", function(){
			var frm = document.TopSearchForm;
			var search_word = $("#search").val();
			console.log(search_word);
			frm.work_div.value = "do_search";
			frm.search_word.value = search_word;
			//var param = $("#frmMng").serialize(); //form의 값을 모두 다 가져오는 것
			//alert(param);			
			frm.action = '/SUNDAY/movie.do';
			frm.submit();
		
		});
	
	//로그인
	$("#login_btn").on('click',function(){
		
		var parent = $(this).parent();
		var userId = parent.find("#user_id").val();
		var password = parent.find("#passwd").val();
		//alert(password);
		
		if(null == userId || userId.trim().length ==0){
			$("#user_id").focus();
			alert('ID를 입력하세요.');
			return;
		}
		
		if(null == password || password.trim().length ==0){
			$("#passwd").focus();
			alert('password를 입력하세요.');
			return;
		}
		
		if(false == confirm(userId+ '으로 로그인 하시겠습니까?')) return;
		
		$.ajax({
			type:"post",
			url:"/SUNDAY/login.do",
			dataType: "html",
			data:{
				"work_div":"do_login",
				"user_id":userId,
				"passwd":password
			},
			success: function(data){
				var jData = JSON.parse(data);
					if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						window.location.reload();
					}else{
						alert(jData.msgContents);
					}
				}, 
				complete: function(data){
					
				},
				error: function(xhr, status, error){
					alert("error: "+ error);
				}
		});//ajax 
	});
	
	//회원가입
	function do_insert(){
 		console.log("do_insert");
 		
 		var frm = document.join_form;
 		frm.work_div.value="do_insert";
 		var param = $("#join_form").serialize();
 		
		$.ajax({
			type: "POST",
			url:"/SUNDAY/join.do",
			dataType: "html",
			data:param,
			success: function(data){
				
				var jData = JSON.parse(data);
				if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						//window.location.href="/SUNDAY/join.do?work_div=do_retrieve";
						window.location.reload();
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
	 		$("#join_form").validate({
	 			//validation 종료 후 submit 직전에 할 작업 ex)메세지 표시
	 			submitHandler:function(){
					var f = confirm('회원가입을 하시겠습니까?');
					if(f){
						return do_insert();
					}else{
						return false;
					}
				},
	 			rules:{
	 				join_id:{
	 					required: true,
	 					minlength:5
	 				},
	 				join_passwd:{
	 					required: true,
	 					minlength: 4
	 				},
	 				join_repasswd:{
	 					required: true,
	 					minlength:4,
	 					equalTo: join_passwd
	 				},
	 				join_name:{
	 					required: true
	 				},
	 				join_email:{
	 					email:true
	 				}
	 			},
	 			message:{
	 				join_id:{ 
		 					required: "필수 항목입니다.",
		 					minlength: "최소{0}글자 이상이어야 합니다."
	 				},
	 				join_passwd:{
							required: "필수 항목입니다.",
							minlength: '최소 {0}글자 이상이어야 합니다.'
					},
					join_repasswd:{
		 					required: "필수 항목입니다.",
		 					minlength:"최소 {0}글자 이상이어야 합니다.",
		 					equalTo: "같은 값을 다시 입력하세요."
	 				},
	 				join_name:{
	 						required: "필수 항목입니다."
	 				},
	 				join_email:{
	 						email: "유효하지 않은 E-Mail주소입니다."
	 				}
	 			}
	 		});
	 	}); 
	
	//정보 수정
 function do_update(){
 		console.log("do_update");
 		
 		var frm = document.updateForm;
 		frm.work_div.value="do_update";
 		var param = $("#updateForm").serialize();
		
		$.ajax({
			type: "POST",
			url:"/SUNDAY/mypage.do",
			dataType: "html",
			data:param,
			success: function(data){
				
				var jData = JSON.parse(data);
				if(null != jData && jData.msgId == "1"){
						alert(jData.msgContents);
						//window.location.href="/SUNDAY/mypage.do?work_div=do_retrieve";
						window.location.reload();
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
		$("#updateForm").validate({
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
	 						//window.location.href="/SUNDAY/mypage.do?work_div=do_remove";
	 						window.location.reload();
	 						
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
	
	
	//로그아웃버튼
	$("#logoutBtn").on("click", function(){
		doLogout();
	});
	
	//로그아웃
	function doLogout(){
		var frm = document.topForm;
		$.ajax({
 			type: "POST",
 			url:"/SUNDAY/login.json",
 			dataType: "html",
 			data:{
 				"work_div": "do_logout"
 			},
 			success: function(data){
 						window.location.reload();
 				},
 				complete: function(data){
 					
 				},
 				error: function(xhr, status, error){
 					alert("error: "+ error);
 				}
 		});//ajax
		
	}
	
	//메뉴 언어 바꾸기
	$(".lang").on("click", function(){	
		var lang = $(this).val();
 		//ajax
	 	   $.ajax({
				     type:"POST",
				     url:"/SUNDAY/movie.do",
				     dataType:"html",
				     data:{
				    	 "work_div":"do_change_language",
				    	 "language":lang
				     },
				success: function(data){
				     	location.reload();
				},
				complete:function(data){
				     
				},
				error:function(xhr,status,error){
				     alert("error:"+error);
				}
			});   
	});

	//메일전송
	$("#mailSend").on('click',function(){
 		console.log("mailSend");
 		
 		var frm = document.formMailFrm;
 		var email = $("#find_email").val();
 		if(false == confirm(email +" 에 전송하시겠습니까?")) return;
 		
		frm.work_div.value="do_find";
		frm.action="/SUNDAY/login.do";
		frm.submit();
		
		$("#find_email").val("");
		
 	});
  </script>
  
</body>
</html>
