<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<section class="container vh-100">
		<div class="w-50 m-auto">
			<div
				class="d-flex align-items-center justify-content-center flex-column">
				<h3>Register</h3>
				<p class="text-danger">${error }</p>
				<s:form method="post" namespace="/account" action="saveAccount">
					<div class="mb-3">
						<s:textfield type="text" class="form-control" name="account.username"
							placeholder="Username"></s:textfield>
					</div>
					<div class="mb-3">
						<s:password type="password" class="form-control"
							name="account.password" placeholder="Password"></s:password>
					</div>
					<div class="mb-3">
						<s:textfield type="text" class="form-control"
							name="account.fullName" placeholder="FullName"></s:textfield>
					</div>
					<button class="btn btn-success w-100" type="submit">Create Account</button>
				</s:form>
				<s:a namespace="/account" action="index">Sign In</s:a>
			</div>
		</div>
	</section>
</body>
</html>