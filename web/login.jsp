<%-- 
    Document   : loginPage
    Created on : Feb 22, 2025, 12:07:45 PM
    Author     : Asus
--%>

<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/styleLogin.css"/>   
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </head>
    <body>
        <div class="login-box">
            <form action="MainServlet" method="POST">
                <h2>Login</h2>
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
                    <input type="text" name="username" required>
                    <label>Enter your username</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" name="password" required>
                    <label>Enter your password</label>
                </div>
                <div class="remember-forgot">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                    <a href="#">Forgot Password?</a>
                </div>
                <button type="submit" name="action" value="Login">Login</button>
                <div class="register-link">
                    <p>Don't have an account? <a href="register.jsp">Register</a></p>
                </div>
            </form>
        </div>
    </body>

    <c:set var="err" value="${requestScope.ERROR}"/>
    <c:if test="${not empty err}">
        <div class="notification">
            <h2>${requestScope.ERROR}</h2>
            <p>Please try again.</p>
        </div>
    </c:if>
</html>
