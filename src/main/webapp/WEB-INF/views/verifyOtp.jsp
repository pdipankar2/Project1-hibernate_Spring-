<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify OTP</title>
</head>
<body>
    <h1>Verify OTP</h1>
    <form action="verifyOtp" method="post">
        <div>
            <label for="otp">OTP:</label>
            <input type="text" id="otp" name="otp" placeholder="Enter your OTP" required>
        </div>
        <input type="hidden" name="email" value="${email}"/>
        <div>
            <button type="submit">Verify OTP</button>
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
