<%-- 
    Document   : MyOrder
    Created on : Mar 16, 2025, 2:51:23 PM
    Author     : Asus
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/styleMyOrder.css" />
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}" />

        <jsp:include page="navigation.jsp"></jsp:include>

            <main class="profile-main">
                <h2 class="welcome-heading">Welcome!</h2>
                <section class="account-section">
                    <h3 class="section-heading">My Account</h3>
                    <nav class="profile-nav">
                        <ul class="nav-list">
                            <a href="MainServlet?action=my-profile">
                                <li class="nav-item">My Profile</li>
                            </a>
                            <a href="MainServlet?action=my-order">
                                <li class="nav-item active">My Order</li>
                            </a>
                        </ul>
                    </nav>
                    <div class="history-order">
                        <h4 class="form-heading">Order History</h4>
                        <div class="container">

                            <!-- Order food -->
                        <c:if test="${not empty requestScope.order}">
                            <c:forEach var="order" items="${requestScope.order}">
                                <div class="order-food">
                                    <div class="order-header">
                                        <span class="order-id">${order.getOrderId()}</span>
                                        <span class="order-date">${order.getOrderDate()}</span>
                                    </div>
                                    <div class="product-container">
                                        <!--sử dụng vòng lặp order để lấy các food ra ngoài-->
                                        <c:forEach var="detail" items="${order.details}">
                                            <div class="product">
                                                <div class="product-info">
                                                    <p class="product-name">${detail.food.getName()}</p>
                                                    <div class="product-details">
                                                        <span class="product-price">
                                                            <fmt:formatNumber value="${detail.food.getPrice()}" currencySymbol="₫" type="currency"/>
                                                        </span>
                                                        <span class="product-quantity">Số lượng: ${detail.getUnitPrice()}</span>
                                                    </div>
                                                </div>
                                                <!--Repeat product-info blocks as needed--> 
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="order-summary">
                                        <div class="order-status">
                                            <h6>Delivery Status</h6>
                                            <div class="status-container">
                                                <span class="status-badge in-transit">${order.getStatus().getName()}</span>
                                                <!--<span class="delivery-time">Estimated delivery: 15:00</span>-->
                                            </div>
                                        </div>
                                        <div class="order-total">
                                            <span>Total:</span>
                                            <span class="total-price">
                                                <fmt:formatNumber value="${order.getTotal()}" currencySymbol="₫" type="currency"/>
                                            </span>
                                        </div>
                                        <div class="product-address">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <div class="value">${order.getAddress()}</div>
                                        </div>
                                    </div>

                                    <div class="order-actions">
                                        <button class="reorder-btn">Mua lại</button>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>

                        <!-- You can add more order items here -->
                        <!--order 2 mẫu-->
                        <div class="order-food" onclick="location.href = 'MainServlet?action=order-details&id=123'">
                            <div class="order-header">
                                <span class="order-id">Order #123</span>
                                <span class="order-date">March 16, 2025</span>
                            </div>
                            <div class="product-container">
                                <div class="product">
                                    <div class="product-info">
                                        <p class="product-name">Tên sản phẩm</p>
                                        <div class="product-details">
                                            <span class="product-price">120,000₫</span>
                                            <span class="product-quantity">x1</span>
                                        </div>
                                    </div>
                                    <!-- Repeat product-info blocks as needed -->
                                </div>
                            </div>
                            <div class="order-summary">
                                <div class="order-status">
                                    <h6>Delivery Status</h6>
                                    <div class="status-container">
                                        <span class="status-badge in-transit">In Transit</span>
                                        <span class="delivery-time">Estimated delivery: 15:00</span>
                                    </div>
                                </div>
                                <div class="order-total">
                                    <span>Total:</span>
                                    <span class="total-price">10,000,000₫</span>
                                </div>
                                <div class="product-address">
                                    <i class="fas fa-map-marker-alt"></i>
                                    123 Nguyen Hue Street, District 1, HCMC
                                </div>
                            </div>

                            <div class="order-actions">
                                <button class="reorder-btn">Mua lại</button>
                            </div>

                        </div>



                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="footer.jsp"></jsp:include>

    </body>
</html>
