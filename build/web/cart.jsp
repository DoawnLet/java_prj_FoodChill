<%-- 
    Document   : cart
    Created on : Feb 27, 2025, 10:56:50 PM
    Author     : Asus
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Food</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="css/styleCarts.css"> 
        <script defer src="js/cart.js"></script>
        <script defer src="js/api.js"></script>

    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>


        <c:set var="cart" value="${sessionScope.cart}"></c:set>
        <c:set var="totalCost" value="${sessionScope.totalCost}"></c:set>
        <jsp:include page="navigation.jsp"></jsp:include>

            <div class="cart-container">
                <h2>Giỏ hàng của bạn</h2>    
                <div class="cart-items">
                <c:choose>
                    <c:when test="${cart == null || cart.isEmpty()}">
                        <h3>Hiện tại không có sản phẩm nào !</h3>
                    </c:when>
                    <c:otherwise> 
                        <c:forEach var="food_quantity" items="${cart}">
                            <div class="product">
                                <input hidden name="food-id" class="food-id" type="text" value="${food_quantity.key.getId()}">
                                <div class="image">
                                    <img src="${food_quantity.key.getImg()}" alt="Food_img" />
                                </div>
                                <div class="product-info">
                                    <div class="product-name">${food_quantity.key.getName()}</div>
                                    <div class="product-description">
                                        ${food_quantity.key.getDesc()}
                                    </div>
                                </div>
                                <div class="product-price">${food_quantity.key.getPrice()}</div>
                                <div class="quantity-controls">
                                    <div class="decrease btn">
                                        <i class="fa-solid fa-minus"></i>
                                    </div>
                                    <input name="quantity" type="text" value="${food_quantity.value}" class="quantity-input" />
                                    <div class="increase btn">
                                        <i class="fa-solid fa-plus"></i>
                                    </div>
                                </div>
                                <div class="product-total">
                                    <fmt:formatNumber value="${food_quantity.key.getPrice() * food_quantity.value}"
                                                      currencySymbol="₫" type="currency">
                                    </fmt:formatNumber>
                                </div>
                                <button class="remove-btn remove" type="button">X</button>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="cart-summary">
                <div class="coupon">
                    <label for="coupon">Mã giảm giá:</label>
                    <div class="coupon-input">
                        <form action="MainServlet" method="post">
                            <input type="text" name="discount" id="coupon" placeholder="Nhập mã giảm giá" />
                            <button class="apply-coupon" name="action" value="view-cart">Áp dụng mã</button>
                        </form>
                    </div>
                </div>

                <div class="total-section">
                    <div class="total">
                        <div id="cart-size" class="total-item">
                            Total (${cart != null ? cart.size() : 0} ${cart.size() > 1 ? 'items' : 'item'}):
                        </div>
                        <div id="total-cost" class="total-cost">
                            <fmt:formatNumber value="${totalCost}" currencySymbol="₫" type="currency"></fmt:formatNumber>
                            </div>
                        </div>
                    </div>

                    <!--To place order-->
                    <div class="checkout-section">
                        <form action="MainServlet?action=customer-place-order" method="post">
                            <div class="address-section">
                                <h5>Personal Information</h5>
                                <div class="inforCity">
                                    <label for="city">City:</label>
                                    <select required class="form-select" name="city" id="city">
                                        <option disabled selected value="">Select city</option>
                                    </select>
                                </div>

                                <div class="inforDisctrict">
                                    <label for="district">District: </label>
                                    <select required class="form-select" disabled name="district" id="district">
                                        <option value="">Select District</option>
                                    </select>
                                </div>

                                <div class="inforWard">   
                                    <label for="ward">Ward: </label>
                                    <select required class="form-select" disabled name="ward" id="ward">
                                        <option value="">select Ward:</option>
                                    </select>
                                </div>

                                <div class="inforStreet">
                                    <label for="street">Street: </label>
                                    <input required class="input-section" 
                                           name="street" 
                                           type="text" id="street"
                                           value=""
                                           placeholder="Enter your street" />
                                </div>


                                <!--Nếu được thì hãy random-->
                                <div class="shipper-select">
                                    <select required class="input-section" name="shipper" id="shipper">
                                        <option value="" disabled selected>Select Shipper</option>
                                    <c:forEach var="shipper" items="${requestScope.shipper}">
                                        <option value="${shipper.getId()}" >${shipper.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <button class="proceed-checkout" type="submit">
                            <span class="button-text">Place Order</span>
                            <i class="fas fa-arrow-right"></i>
                        </button>
                    </form>     

                    <c:if test="${not empty message}">
                        <div class="error-message">
                            <p>${message}</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
