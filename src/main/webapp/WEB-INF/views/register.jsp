<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="component/Navbar.jsp"%>
<%@include file="component/css.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
<title>Student Registration</title>
</head>
<body>
	<h2 class="text-center">Student Registration Form</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-5">

				<!-- Display error message if email already exists -->
				<c:if test="${not empty error}">
					<div style="color: red;">
						<p>${error}</p>
					</div>
				</c:if>

				<form action="registerStudent" method="post">


					<div class="form-group">
						<label for="name">Student Name</label> <input type="text"
							class="form-control" name="name" id="name"
							placeholder="Enter your name" required>
					</div>
					<br>


					<div class="form-group">
						<label for="email">Student Email</label> <input type="email"
							class="form-control" name="email" id="email"
							placeholder="Enter your email" required>
					</div>
					<br>

					<div class="form-group">
						<label for="email">Student Phone</label> <input type="number"
							class="form-control" name="phone" id="phone"
							placeholder="Enter your phone" required>
					</div>
					<br>

					<div class="form-group">
						<label for="cityDropdown">City</label> <select
							class="form-control" id="cityDropdown" name="city" required>
							<option value="">Select a city</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Delhi">Delhi</option>
							<option value="Bangalore">Bangalore</option>
							<option value="Chennai">Chennai</option>
							<option value="Kolkata">Kolkata</option>
							<option value="Kolkata">Noida</option>
						</select>
					</div><br>

					<!-- <br> <label for="city">City:</label><br> <input
						type="text" id="city" name="city" required><br> <br>
					 -->


					<!--  <label for="studentEnrolmentDate">Enrolment Date:</label><br>
    <input type="date" id="studentEnrolmentDate" name="studentEnrolmentDate" required><br><br>
 -->
					<!-- <label for="course">Courses:</label><br> <input type="text"
						id="course" name="course" required><br> <br> -->
			
			<div class="form-group">
						<label>Courses</label><br>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="course"
								id="course1" value="Web Development"> <label
								class="form-check-label" for="course1">Web Development</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" id="course2"
								name="course" value="Data Science"> <label
								class="form-check-label" for="course2">Data Science</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="course"
								id="course3" value="Graphic Design"> <label
								class="form-check-label" for="course3">Graphic Design</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="course"
								id="course4" value="Digital Marketing"> <label
								class="form-check-label" for="course4">Digital Marketing</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" id="course5"
								name="course" value="Mobile App Development"> <label
								class="form-check-label" for="course5">Mobile App
								Development</label>
						</div>
					</div><br>
			
					<div class="fs-4 text-center">
						<button type="submit" class=" btn btn-primary">Register</button>
					</div>	
						
					<!-- 	 <input
						type="submit" value="Register"> -->
				</form>
				
				<a href="login"> Login Page </a> &nbsp;&nbsp;&nbsp; <a
					href="home">Home Page </a>
				
			</div>

		</div>
	</div>
	
	<h1>login</h1>
	<a href="login">login</a>
	<br>
</body>
</html>
