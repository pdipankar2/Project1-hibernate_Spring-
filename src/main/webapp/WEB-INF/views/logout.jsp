<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="component/Navbar.jsp"%>
<%@include file="component/css.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>Login</title>
</head>
<body>

<div class="container p-5">
		<div class="row ">
			<div class="col-md-4 offset-md-4">
				<div class="card point-card">
				<p class="fs-4 text-center">Student Login</p>
	  <div class="fs-4 text-center">
   
        <h1>Login</h1>
        <p>Use your email to login. If you're an admin, your account will be recognized automatically.</p>
        <form action="sendOtp" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Send OTP</button>
            </div>
            <c:if test="${not empty error}">
                <p style="color:red;">${error}</p>
            </c:if>
            <c:if test="${not empty message}">
                <p style="color:green;">${message}</p>
            </c:if>
        </form>
        
        <a href="home">Home page</a>
    </div>
    
     Don't have an account ? <a href="register.jsp" class="text-decoration-none">Click Register Here</a>
	
	<a href="register">Register</a>
				</div>
				</div>
			</div>
		</div>
	</div>
    
</body>
</html>