<#macro layout>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Inexture demo project</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CVarela+Round" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<!-- datepicker -->
	<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<!-- Owl Carousel -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.css" />
	<!-- Magnific Popup -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css" />
	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<!-- my css -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" />
</head>
	<body>
		<!-- Header -->
	<nav id="nav" class="navbar nav-transparent">
			<div class="container">
				<div class="navbar-header">
					<!-- Logo -->
					<div class="navbar-brand">
						<a href="index.html">
							<img class="logo" src="img/logo.png" alt="logo">
							<img class="logo-alt" src="img/logo-alt.png" alt="logo">
						</a>
					</div>
					<!-- /Logo -->
					<!-- Collapse nav button -->
					<div class="nav-collapse">
						<span></span>
					</div>
					<!-- /Collapse nav button -->
				</div>
				<!--  Main navigation  -->
				<ul class="main-nav nav navbar-nav navbar-right">
					<li class="active"><a href="/inexture-sample">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#portfolio">Portfolio</a></li>
					<li><a href="#service">Services</a></li>
					<li><a href="#pricing">Prices</a></li>
					<li><a href="#team">Team</a></li>
					<li class="has-dropdown"><a href="#blog">Blog</a>
						<ul class="dropdown">
							<li><a href="blog-single.html">blog post</a></li>
						</ul>
					</li>
					<li><a href="Login.jsp">Login</a></li>
				</ul>
				<!-- /Main navigation -->
			</div>
		</nav>
		<#nested>
		<!-- jQuery Plugins -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<!-- datepicker -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.magnific-popup.js"></script>
	<script type="/text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
	</body>
</html>
</#macro>