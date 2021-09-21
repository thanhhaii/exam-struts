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
	<section class="container">
		<s:a class="btn btn-primary" namespace="/ticket" action="index">Back</s:a>
		<s:form method="post" namespace="/ticket" action="saveTicket">
			<s:textfield label="Title" name="ticket.title" class="form-control"></s:textfield>
			<s:textarea label="Description" name="ticket.description"
				class="form-control"></s:textarea>
			<s:select label="Status" name="ticket.status.id"
				list="#{'1': 'Done', '2': 'Pending', '3':'Cancel' }"
				class="form-select"></s:select>
			<s:select label="Category" name="ticket.categorystruts.id"
				list="#{'1': 'category 1', '2': 'category 2', '3':'category 3' }"
				class="form-select"></s:select>
			<s:submit value="Save"></s:submit>
		</s:form>
	</section>
</body>
</html>