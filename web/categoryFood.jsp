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
                <!-- Products Grid -->
                <div class="row row-cols-1 row-cols-md-4 g-4 mb-5">
                    <!-- Product Card -->
                <c:forEach var="food" items="${sessionScope.listFood}">
                    <div class="col">
                        <div class="card h-100">
                            <img src="${food.img}" class="card-img-top" alt="${food.name}">
                            <div class="card-body">
                                <h5 class="card-title"><a href="MainServlet?action=category&foodId=${food.id}">${food.name}</a></h5>
                                <p class="text-danger">${food.price} VND</p>
                                <p class="card-text">${food.desc}</p>
                            </div>
                            <c:if test="${not empty sessionScope.USER}">
                                <div class="card-footer bg-white border-top-0">

                                    <div class="d-flex justify-content-center mb-2">
                                        <input 
                                            class="form-control quantity-input text-center quantity-value" 
                                            style="width: 100px;"
                                            data-food-id="${food.id}" 
                                            name="quantity" 
                                            type="number" 
                                            value="1"
                                            placeholder="Số lượng">
                                    </div>

                                    <button class="btn btn-outline-primary w-100 add-to-cart" type="button"
                                            id="add-with-quantity" value="${food.id}">
                                        <i class="fas fa-shopping-cart me-2"></i>Thêm vào giỏ
                                    </button>

                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--end main content-->

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>
        <!--end footer-->
    </body>
</html>

