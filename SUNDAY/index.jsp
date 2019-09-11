<%@ include file="/WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<style>
body, html {
  height: 100%;
}
.bg {
  /* The image used */
  background-image: url("https://mdbootstrap.com/img/Photos/Horizontal/Nature/full page/img(11).jpg");

  /* Half height */
  height: 87%;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>

<head>

  	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1" shrink-to-fit=no"> 
  	<meta name="description" content="">
    <meta name="author" content="">

  
	<!-- Custom fonts for this template-->
    <link href="/SUNDAY/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	 <link href="/SUNDAY/css/sb-admin-2.min.css" rel="stylesheet">
	
</head>

<body>

    <div class="bg">
	    <div class="container d-flex h-100 align-items-center">
	      <div class="mx-auto text-center">
	        <h1 class="mx-auto my-0 text-uppercase">SUNDAY CINEMA</h1>
	        <h2 class="text-white-50 mx-auto mt-2 mb-5"></h2>
	        <a href="/SUNDAY/movie.do?work_div=do_retrieve&page_size=&page_num=1&search_div=10&search_word=" class="btn btn-primary js-scroll-trigger">Get Started</a>
	      </div>
	    </div>
    </div>
    <br/>

<%@ include file="/WEB-INF/footer.jsp" %>

</body>
</html>
