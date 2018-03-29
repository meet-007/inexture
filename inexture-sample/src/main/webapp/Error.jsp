<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<h1>asdfasdf</h1>

<c:choose>
<c:when test="${pageContext.response.getStatus() == 500}">
<h1 class="text-danger">Error: <%=exception.getMessage() %></h1>
</c:when>
<c:otherwise>
Hi There, error code is  <c:out value="${pageContext.response.getStatus()}" ></c:out><br>
Please go to ${(empty sessionScope)? '<a href="/inexture-sample">home page</a>' : '<a href="/inexture-sample/UserHome.jsp">home page</a>'}
</c:otherwise>
</c:choose>
</div>
</body>
</html>