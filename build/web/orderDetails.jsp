<%-- 
    Document   : orderDetails
    Created on : Mar 16, 2025, 10:08:47 AM
    Author     : Asus
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/styleOrderDetail.css"> 
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <c:set var="order" value="${requestScope.order}"></c:set>
        <c:set var="details" value="${requestScope.order.getDetails()}"></c:set>

            <div class="order-detail-container">
                <div class="order-tracking">
                    <h4 class="order-id">Order Id: ${order.getOrderId()}</h4>
                <h6 class="order-items">${details.size()} ${details.size() > 0 ? 'Foods' : 'Food'}</h6>
                <div class="order-progress">
                    <a class="track-detail" href="">Track Order</a>
                    <div class="progress-bar">
                        <div class="bar-load ${order.getStatus().getName().toLowerCase()}">
                            <i class="fa-solid fa-truck-fast package"></i>
                        </div>
                    </div>
                    <div class="order-status">${order.getStatus().getName()}</div>
                </div>
            </div>
            <div class="order-details">
                <div class="ordered-foods">
                    <div class="food-title title">
                        <h5>foods</h5>
                    </div>
                    <div class="food-list">
                        <c:forEach var="detail" items="${details}">
                            <div class="food-row">
                                <div class="food">
                                    <img class="food-image" src="${detail.getFood().getImg()}" alt="Food Image">
                                    <div class="food-info">
                                        <div class="food-name">${detail.getFood().getName()}</div>
                                        <div class="food-unit-quantity">
                                            ${detail.getQuantity()} X <fmt:formatNumber value="${detail.getUnitPrice()}" currencySymbol="₫" type="currency"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="food-total-price">
                                    <fmt:formatNumber value="${detail.getQuantity() * detail.getUnitPrice()}" currencySymbol="₫" type="currency"/>
                                </div>
                            </div> 
                        </c:forEach>
                        <div class="total">
                            <div class="sub-total">
                                <p class="label">Shipping Cost (+):</p>
                                <p class="value">...đ</p>
                            </div>
                            <div class="sub-total">
                                <p class="label">Discount (-):</p>
                                <p class="value">...đ</p>
                            </div>
                        </div>
                        <div class="payable">
                            <p class="label">Total:</p>
                            <p class="value">
                                <fmt:formatNumber value="${order.getTotal()}" currencySymbol="₫" type="currency"/>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="order-infos">
                    <div class="more-order-info">
                        <div class="title">
                            <h5>More Informations</h5>
                        </div>
                        <div class="date-row row">
                            <div class="label">Date:</div>
                            <div class="value">${order.getOrderDate()}</div>
                        </div>
                        <div class="address-row row">
                            <div class="label">Delivery Address:</div>
                            <div class="value">${order.getAddress()}</div>
                        </div>
                    </div>
                    <div class="customer">
                        <div class="customer-title title">
                            <h5>Customer details</h5>
                        </div>
                        <div class="customer-details">
                            <div class="customer-detail-row row">
                                <div class="label">Name:</div>
                                <div class="value">${order.getName()}</div>
                            </div>
                            <div class="customer-detail-row row">
                                <div class="label">Email:</div>
                                <div class="value">${order.getEmail()}</div>
                            </div>
                            <div class="customer-detail-row row">
                                <div class="label">Phone:</div>
                                <div class="value">${order.getPhone() != null ? order.getPhone() : 'None'}</div>
                            </div>
                        </div>
                    </div>
                    <div class="ship-company">
                        <div class="ship-title title">
                            <h5>Ship Via</h5>
                        </div>
                        <div class="ship">
                            <div class="ship-detail-row row">
                                <div class="label">Company:</div>
                                <div class="vale">${order.getShipper().getName()}</div>
                            </div>
                            <div class="ship-detail-row row">
                                <div class="label">Phone:</div>
                                <div class="vale">${order.getShipper().getPhoneNumber()}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>                      

    </body>
</html>
