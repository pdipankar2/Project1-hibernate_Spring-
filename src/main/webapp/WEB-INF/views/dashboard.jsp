<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.jtc.entity.Student"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
</head>
<body>
	<h1>Welcome to your Dashboard</h1>
	<h2>Your Details</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th>City</th>
			<th>Course</th>
		</tr>
		<tr>
			<%
			Student student = (Student) session.getAttribute("student");
			if (student != null) {
			%>
			<td><%=student.getId()%></td>

			<td><%=student.getName()%></td>
			<td><%=student.getEmail()%></td>
			<td><%=student.getPhone()%></td>
			<td><%=student.getCity()%></td>
			<td><%=student.getCourse()%></td>
			<%
			} else {
			%>
			<td colspan="6">No student information available.</td>
			<%
			}
			%>
		</tr>
	</table>

	<form action="logout" method="post">
		<button type="submit">Logout</button>
	</form>
</body>
</html>

