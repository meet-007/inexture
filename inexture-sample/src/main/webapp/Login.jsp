<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
window.history.forward();
	function noBack() { window.history.forward(); }
	</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
<jsp:include page="header-footer/header.jsp"></jsp:include>
<div class="container">
<c:if test="${requestScope.rspmsg ne null}">
<div id="success_alert" class="alert alert-success" role="alert"><c:out value="${requestScope.rspmsg}"></c:out>.</div>
</c:if>
</div>
<jsp:include page="commonlogin.jsp"></jsp:include>
	<script type="text/javascript" src="js/myjs.js"></script>
</body>
</html>