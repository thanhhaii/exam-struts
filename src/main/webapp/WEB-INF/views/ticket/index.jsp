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
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Title</th>
					<th scope="col">Description</th>
					<th scope="col">Created At</th>
					<th scope="col">Status</th>
					<th scope="col">Category</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ticket" items="${tickets }">
					<tr>
						<th scope="row">${ticket.id }</th>
						<td>${ticket.title }</td>
						<td>${ticket.description }</td>
						<td>${ticket.createdAt }</td>
						<td>${ticket.status.id == 1 ? "Done" : ticket.status.id == 2 ? "Pending" : "Cancel"  }</td>
						<td>${ticket.categorystruts.id == 1 ? "category 1" : ticket.status.id == 2 ? "category 2" : "category 3"}</td>
						<td>
							<s:url var="urlDelete"
						namespace="/ticket" action="delete">
						<s:param name="id">${ticket.id }</s:param>
					</s:url> <s:a href='%{urlDelete}' onClick="return confirm('Are you sure?')">Delete</s:a>
						</td>
					</tr>
				</c:forEach>
			</tbody>			
			<s:a class="btn btn-primary" namespace="/ticket" action="create">Add ticket</s:a>
		</table>
	</section>
</body>
</html>