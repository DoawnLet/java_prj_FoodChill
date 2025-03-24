<%-- 
    Document   : register.jsp
    Created on : Feb 22, 2025, 11:39:54 AM
    Author     : Asus
--%>

<%@page import="errors.RegisterError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <link href="./css/styleRegister.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            RegisterError errors = (RegisterError) request.getAttribute("ERROR");
            String user = null;
            String pass = null;
            String confirm = null;
            String mail = null;
            String sdt = null;
            if (errors != null) {
                user = errors.getUsername();
                pass = errors.getPassword();
                confirm = errors.getConfirm_pass();
                mail = errors.getEmail();
                sdt = errors.getPhone();
            }
            String exist = (String) request.getAttribute("EXISTED");
        %>

        <div class="signup-box">
            <form action="MainServlet" method="POST">
                <h2>Sign up</h2>
                <div class="input-box">
                    <span class="icon"><ion-icon name="people"></ion-icon></span>
                    <input type="text" name="username" value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>" required />
<!--                    <input type="text" name="username" required />-->
                    <label>Username</label>
                    <%
                        if(user != null){
                    %>
                    <font style="color: red"><%=user%></font>
                    <%}%>
                    
                    <%
                        if(exist != null){
                    %>
                    <font style="color: red"><%=exist%></font>
                    <%}%>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" name="password" required />
                    <label>Password</label>
                    <%
                        if(pass != null){
                    %>
                    <font style="color: red"><%=pass%></font>
                    <%}%>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" name="confirm_password" required />
                    <label>Confirm password</label>
                    <%
                        if(confirm != null){
                    %>
                    <font style="color: red"><%=confirm%></font>
                    <%}%>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
                    <input type="text" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" required/>
<!--                    <input type="text" name="email" required />-->
                    <label>Email</label>
                    <%
                        if(mail != null){
                    %>
                    <font style="color: red"><%=mail%></font>
                    <%}%>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="call"></ion-icon></span>
                    <input type="text" name="phone" value="<%= request.getParameter("phone") != null ? request.getParameter("phone") : "" %>" required/>
<!--                    <input type="text" name="phone" required />-->
                    <label>Number phone</label>
                    <%
                        if(sdt != null){
                    %>
                    <font style="color: red"><%=sdt%></font>
                    <%}%>
                </div>
                <button type="submit" name="action" value="Register">Sign up</button>
                <div class="login-link">
                    <p>Already have an account? <a href="login.jsp">Login</a></p>
                </div>
            </form>
        </div>   
    </body>
</html>