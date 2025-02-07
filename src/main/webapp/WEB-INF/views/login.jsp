<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="sendOtp" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
        </div>
        <div>
            <button type="submit">Send OTP</button>
        </div>
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>
        <c:if test="${not empty message}">
            <p style="color:green;">${message}</p>
        </c:if>
    </form>
</body>
</html>
