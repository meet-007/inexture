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
hello user
welcome<c:out value="${sessionScope.user.firstname}"></c:out>
Update Profile:<a href="UpdateProfile">UpdateProfile</a><br/>
<a href="Logout">logout</a><br/>
</body>
</html>