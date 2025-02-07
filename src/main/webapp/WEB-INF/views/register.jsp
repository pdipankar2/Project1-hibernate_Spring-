<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="component/Navbar.jsp" %>
        <%@include file="component/css.jsp" %>

<html>
<head>
    <title>Student Registration</title>
</head>
<body>
<h2 class="text-center">Student Registration Form</h2>
<div class="container">
<div class="row">
<div class="col-md-6 offset-md-5">

<form action="registerStudent" method="post">

     <label for="studentId">Student name:</label><br>
    <input type="text" id="name" name="name" required><br><br>

    <label for="email">Student Email:</label><br>
    <input type="email" id="studentName" name="email" required><br><br>

    <label for="city">City:</label><br>
    <input type="text" id="city" name="city" required><br><br>
    <label for="phone">Student phone:</label><br>
    <input type="number" id="phone" name="phone" required><br><br>
    
   
   <!--  <label for="studentEnrolmentDate">Enrolment Date:</label><br>
    <input type="date" id="studentEnrolmentDate" name="studentEnrolmentDate" required><br><br>
 -->
    <label for="course">Courses:</label><br>
    <input type="text" id="course" name="course" required><br><br>

    <input type="submit" value="Register">
</form>
</div>

</div>
</div>
<h1>register </h1>
<a href="register">register</a><br>
<h1>login </h1>
<a href="login">login</a><br>
</body>
</html>
