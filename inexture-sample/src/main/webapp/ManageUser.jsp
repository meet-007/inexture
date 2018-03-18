<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<div class="col-sm-8 col-sm-offset-2">
					<h3>
						Users list
					</h3>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								<th>email</th>
								<th>mobile</th>
								<th>gender</th>
								<th>edit</th>
								<th>delete</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="index" value="1"></c:set>
							<c:forEach var="user" items="${requestScope.userslist}">
								<tr>
								
									<td><c:out value="${index}"></c:out></td>
									<td><c:out value="${user.firstname} ${user.lastname}"></c:out></td>
									<td><c:out value="${user.email}"></c:out></td>
									<td><c:out value="${user.mobile}"></c:out></td>
									<td>
									<c:out value="${(user.gender eq 0)?'male':''}"></c:out>
									<c:out value="${(user.gender eq 1)?'female':''}"></c:out></td>
									<td><a href="UpdateProfile?email=<c:out value="${user.email}"></c:out>&password=<c:out value="${user.password}"></c:out>">edit</a></td>
									<td><a href="UpdateProfile?user=<c:out value="${user}"></c:out>">delete</a></td>
								</tr>
								<c:set var="index" value="${index+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="js/paginathing.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$('.table tbody').paginathing({
				perPage : 10,
				insertAfter : '.table',
				pageNumbers : true
			});
		});
	</script>
</body>
</html>