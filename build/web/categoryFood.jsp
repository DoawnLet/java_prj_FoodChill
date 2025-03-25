<%-- 
    Document   : categoryFood.jsp
    Created on : Feb 19, 2025, 10:57:00 AM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>FoodChill</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script defer src="./js/category_food.js"></script>
        <script defer src="./js/scroll_page.js"></script>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="./css/styleProduct.css" />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>
        <!-- Navbar -->
        <jsp:include page="navigation.jsp"></jsp:include>
            <!--end nav-->


            <!-- Main Content -->
            <div class="container mt-4 flex-grow-1">
                <div id="food-list" 
                     class="row row-cols-1 row-cols-md-4 g-4 mb-5"
                     data-cate-id="${cateID}">
            </div>
            <div id="loading" class="text-center my-3" style="display: none;">Đang tải thêm món ăn...</div>
        </div>
        <!--end main content-->

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>
        <!--end footer-->
    </body>
</html>

