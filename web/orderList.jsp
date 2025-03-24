<%-- 
    Document   : placeOrder
    Created on : Mar 12, 2025, 4:51:44 PM
    Author     : Asus
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
        <link rel="stylesheet" href="css/stylePlaceOrder.css"> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script defer src="./js/api.js"></script>
    </head>
    <body>
        <!-- Navigation -->
        <jsp:include page="navigation.jsp"/>

        <!-- Container chính -->
        <div class="orders-container">
            <c:choose>
                <c:when test="${empty customerOrders}">
                    <div class="empty-msg">
                        <h2>You haven't had any order yet!</h2>
                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach var="order" items="${requestScope.customerOrders}">
                        <div class="order">
                            <div class="order-info">
                                <a href="MainServlet?action=customer-get-order-detail&orderId=${order.getOrderId()}" class="link-to-detail">

                                    <!-- Thông tin cơ bản của đơn hàng -->
                                    <div class="order-meta">
                                        <div class="order-id">
                                            <div class="label">Order Id:</div>
                                            <div class="value">${order.getOrderId()}</div>
                                        </div>
                                        <div class="order-status">
                                            <div class="label">Status:</div>
                                            <div class="value">${order.getStatus().getName()}</div>
                                        </div>
                                    </div>

                                    <!-- Danh sách food/item của đơn hàng -->
                                    <c:forEach var="detail" items="${order.getDetails()}">
                                        <div class="order-row">
                                            <div class="food-content">
                                                <img class="food-image" 
                                                     src="${detail.food.img}" 
                                                     alt="food Image">
                                                <div class="food-des">
                                                    <div class="food-name">${detail.food.name}</div>
                                                    <div class="food-quantity">X ${detail.quantity}</div>
                                                </div>
                                            </div>
                                            <div class="food-unit-price">
                                                <fmt:formatNumber value="${detail.unitPrice}"
                                                                  currencySymbol="₫"
                                                                  type="currency"/>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </a>
                            </div>
                            <!-- Tổng tiền đơn hàng -->
                            <div class="order-total-cost">
                                Total cost: 
                                <fmt:formatNumber value="${order.total}" currencySymbol="₫" type="currency"/>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
