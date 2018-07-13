<%@page import="sample.models.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Inexture Demo project</title>
<!-- Bootstrap -->
<link href="/inexture-sample/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome Icon -->
<link rel="stylesheet" href="/inexture-sample/css/font-awesome.min.css">
<!-- sidebar -->
<link href="/inexture-sample/css/sidebar.css" rel="stylesheet">
<!-- Datepicker css -->
<link href="/inexture-sample/css/bootstrap-datepicker.min.css" rel="stylesheet">
<!-- Datepicker css -->
<link href="/inexture-sample/css/mycss.css" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<li class="sidebar-brand"><div class="fix-brand"><a href="#">welcome <c:out
						value="${sessionScope.userObject.firstname}"></c:out>
			</a></div></li>
			<li><a href="${pageContext.request.contextPath}/user/UpdateProfile">Update profile</a></li>
			<li><a href="#">Home</a></li>
			<c:if test="${sessionScope.userObject.role.idrole eq 1}">
				<li><a href="${pageContext.request.contextPath}/user/admin/ShowAllUser">Manage Users</a></li>
			</c:if>
			<li><a href="#">About</a></li>
			<li><a href="#">Events</a></li>
			<li><a href="#">Team</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Works <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li class="dropdown-header">Dropdown heading</li>
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li><a href="#">Separated link</a></li>
					<li><a href="#">One more separated link</a></li>
				</ul></li>
			<li><a href="ChangePass.jsp">Change Password</a></li>
			<li><a href="${pageContext.request.contextPath}/user/logout">logout</a><br /></li>
			<li><a href="https://twitter.com/maridlcrmn">Follow me</a></li>
		</ul>
		</nav>
		<!-- /#sidebar-wrapper -->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="/inexture-sample/js/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="/inexture-sample/js/bootstrap.min.js"></script>
		<!-- datepicker -->
		<script src="/inexture-sample/js/bootstrap-datepicker.min.js"></script>
		<!-- sidebar js -->
		<script src="/inexture-sample/js/sidebar.js"></script>
</body>
</html>
