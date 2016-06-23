<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Page</title>
<style>
	h2 {
		color: green;
		text-shadow: 5px 5px 5px lightgreen;
	}
	table, th, td {
		border: 1px solid black;
		width: 200px;
	}
</style>
</head>
<body>
<h2>${userInfo.message}</h2>
<table>
	<tr>
		<th>Name</th>
		<th>Age</th>
	</tr>
	<c:forEach var="user" items="${userInfo.users}">
		<tr>
			<td>${user.name}</td>
			<td>${user.age}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>