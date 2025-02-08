<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <%@include file="component/css.jsp" %>
</head>
<body>
    <%@include file="component/Navbar.jsp" %>
    <div class="container">
        <h1>Admin Panel</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>City</th>
                    <th>Course</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.phone}</td>
                        <td>${student.city}</td>
                        <td>${student.course}</td>
                        <td>
                            <a href="#" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editModal${student.id}">Edit</a>
                            <form action="deleteStudent" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${student.id}">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?')">Delete</button>
                            </form>
                        </td>
                    </tr>
                    
                    <!-- Edit Modal -->
                    <div class="modal fade" id="editModal${student.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editModalLabel">Edit Student</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="updateStudent" method="post">
                                        <input type="hidden" name="id" value="${student.id}">
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input type="text" class="form-control" id="name" name="name" value="${student.name}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="email" name="email" value="${student.email}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input type="text" class="form-control" id="phone" name="phone" value="${student.phone}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="city">City</label>
                                            <input type="text" class="form-control" id="city" name="city" value="${student.city}" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="course">Course</label>
                                            <input type="text" class="form-control" id="course" name="course" value="${student.course}" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html> --%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Panel</title>
<%@include file="component/css.jsp"%>
</head>
<body>
	<%@include file="admin/Navbar.jsp"%>
	<div class="container">
		<h1>Admin Panel</h1>

		<h2>Set Admin Access</h2>
		<form action="setAdminAccess" method="post">
			<div class="form-group">
				<label for="email">Student Email:</label> <input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="isAdmin">Admin Access:</label> <select
					class="form-control" id="isAdmin" name="isAdmin">
					<option value="true">Grant</option>
					<option value="false">Revoke</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Update Admin
				Access</button>
		</form>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<!-- Existing table for displaying students -->

		<table class="table">
			<thead>
				<tr>
					<th>SLNo</th>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>City</th>
					<th>Course</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
					<tr>
						<td>${student.id}</td>
						<td>${student.studentId}</td>
						<td>${student.name}</td>
						<td>${student.email}</td>
						<td>${student.phone}</td>
						<td>${student.city}</td>
						<td>${student.course}</td>
						<td><a href="#" class="btn btn-primary btn-sm"
							data-toggle="modal" data-target="#editModal${student.id}">Edit</a>
							<form action="deleteStudent" method="post"
								style="display: inline;">
								<input type="hidden" name="id" value="${student.id}">
								<button type="submit" class="btn btn-danger btn-sm"
									onclick="return confirm('Are you sure you want to delete this student?')">Delete</button>
							</form></td>
					</tr>

					<!-- Edit Modal -->
					<div class="modal fade" id="editModal${student.id}" tabindex="-1"
						role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="editModalLabel">Edit Student</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form action="updateStudent" method="post">
										<input type="hidden" name="id" value="${student.id}">

										<div class="form-group">
											<label for="name">StudentId</label> <input type="text"
												class="form-control" id="studentId" name="studentId"
												value="${student.studentId}" readonly>
										</div>
										<div class="form-group">
											<label for="name">Name</label> <input type="text"
												class="form-control" id="name" name="name"
												value="${student.name}" required>
										</div>
										<div class="form-group">
											<label for="email">Email</label> <input type="email"
												class="form-control" id="email" name="email"
												value="${student.email}" readonly>
										</div>
										<div class="form-group">
											<label for="phone">Phone</label> <input type="text"
												class="form-control" id="phone" name="phone"
												value="${student.phone}" required>
										</div>
										<div class="form-group">
											<label for="city">City</label> <input type="text"
												class="form-control" id="city" name="city"
												value="${student.city}" required>
										</div>
										<div class="form-group">
											<label for="course">Course</label> <input type="text"
												class="form-control" id="course" name="course"
												value="${student.course}" required>
										</div>
										<button type="submit" class="btn btn-primary">Save
											changes</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<form action="logout" method="post">
		<button type="submit">Logout</button>
	</form>

	<!-- Bootstrap JS and jQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
