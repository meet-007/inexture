<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header-footer/newheader.jsp"></jsp:include>
	<!-- Page Content -->
	<div id="page-content-wrapper">
		<button type="button" class="hamburger is-closed"
			data-toggle="offcanvas">
			<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
				class="hamb-bottom"></span>
		</button>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2"></div>
				<h1>Edit your details</h1>
				<jsp:include page="commonReg.jsp"></jsp:include>
			</div>
		</div>
	</div>

</body>
</html>