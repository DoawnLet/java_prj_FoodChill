<%-- 
    Document   : customerProfile
    Created on : Mar 11, 2025, 9:59:34 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleMyProfile.css" />
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>

            <main class="profile-main">
                <h2 class="welcome-heading">Welcome! user</h2>
                <section class="account-section">
                    <h3 class="section-heading">My Account</h3>
                    <nav class="profile-nav">
                        <ul class="nav-list">
                            <a href="MainServlet?action=my-profile"> <li class="nav-item active">My Profile</li></a>
                            <a href="MainServlet?action=my-order"><li class="nav-item">My Order</li></a>
                        </ul>
                    </nav>
                    <form class="profile-form">
                        <h4 class="form-heading">Edit Your Profile</h4>
                        <div class="form-group">
                            <label class="form-label">Full Name</label>
                            <input type="text" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-input" required>
                        </div>

                        <h5 class="password-heading">Password Changes</h5>
                        <div class="form-group">
                            <label class="form-label">Current Password</label>
                            <input type="password" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">New Password</label>
                            <input type="password" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Confirm Password</label>
                            <input type="password" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Street</label>
                            <input type="text" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">District</label>
                            <input type="text" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">City</label>
                            <input type="text" class="form-input" required>
                        </div>

                        <div class="button-group">
                            <button type="button" class="btn btn-cancel">Cancel</button>
                            <button type="submit" class="btn btn-update">Update</button>
                        </div>
                    </form>
                </section>
            </main>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
