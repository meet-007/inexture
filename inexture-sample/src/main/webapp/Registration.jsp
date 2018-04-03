<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header-footer/header.jsp"></jsp:include>
	<div class="section-header text-center"><h2 class="title">Register here</h2></div>
	<jsp:include page="commonReg.jsp"></jsp:include>
	<script>
	$(document).ready(function(){
		$("#nav").removeClass("nav-transparent");
	});
	</script>
</body>
</html>