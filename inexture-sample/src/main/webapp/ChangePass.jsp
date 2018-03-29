<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.update-pass {
	margin: 50px auto;
}
</style>
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
				<div class="col-md-offset-3">
					<div class="col-md-6">
						<div class="well well-lg">
							<h4>Change your password</h4>
							<form class="form-horizontal" action="ChngPassServ" method="post">
								<div class="form-group has-feedback">
									<label for="inputEmail" class="col-sm-4 control-label">Old
										Password :</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" id="inputoldpass"
											placeholder="Old Password" name="oldpass">
											<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											<div class="help-block with-errors" required></div>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputPassword3" class="col-sm-4 control-label">New
										Password :</label>
									<div class="col-sm-8">
										<input type="password" class="form-control"
											id="inputnewpass" placeholder="New Password" name="newpass" required>
											<span class="glyphicon form-control-feedback" aria-hidden="true" ></span>
											<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label for="inputPassword3" class="col-sm-4 control-label">Repeat New
										Password :</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" data-match="#inputnewpass"
											 data-match-error="Whoops, these don't match"
											id="inputrptnewpass" placeholder="Repeat New Password" required>
											<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
											<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-10">
										<button type="submit" class="btn btn-primary">Update</button>
									</div>
								</div>
							</form>
							<c:out value="${requestScope.rspmsg}"></c:out>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js" type="text/javascript"></script>
</body>
</html>