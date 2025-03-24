<%-- 
    Document   : navigation
    Created on : Feb 18, 2025, 8:19:22 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            rel="stylesheet"
            />
        <link href="css/styleHeader.css" rel="stylesheet" type="text/css" />

    </head> 
    <body>
        <c:set var="listCate" value="${sessionScope.listCate}"/>
        <c:set var="user" value="${sessionScope.USER}"/>
        <nav class="navbar navbar-expand-lg bg-white shadow-sm sticky-top">
            <div class="container">
                <a class="navbar-brand" href="MainServlet">
                    <span style="color: red; font-weight: 700">FoodChill</span>
                </a
                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    >
                    <!--<span class="navbar-toggler-icon"></span>-->
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <!--Thanh điều hướng-->
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" href="MainServlet">Trang chủ</a>
                        </li>  
                        <!--List các loại món ăn-->
                        <c:forEach var="cate" items="${listCate}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainServlet?action=category-food&cateID=${cate.cateId}">${cate.nameCate}</a>
                            </li>
                        </c:forEach>
                    </ul>

                    <!--Chức năng search, giỏ hàng, đăng nhập-->    
                    <div class="d-flex align-items-center gap-3">
                        <!--search-->
                        <div class="search-box">
                            <form action="MainServlet" method="post" class="d-flex">
                                <input name="action" value="search" type="hidden">
                                <input name="keyword" class="form-control" type="search" placeholder="Tìm kiếm..."/>
                                <button class="btn btn-outline-secondary" type="submit">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                            </form>

                            <c:if test="${not empty requestScope.mess}">
                                <h5 style="color: red">${requestScope.mess}</h5>
                            </c:if>
                        </div>


                        <!--Giỏ hàng-->
                        <div class="cart-icon">
                            <a href="MainServlet?action=view-cart"> 
                                <i class="fa-solid fa-cart-shopping fs-5"></i>
                                <span id="cart-badge" class="items ${cart != null ? 'has-items' : ''}">
                                    ${cart.size() > 9 ? '9+' : cart.size()}</span>
                            </a>
                        </div>

                        <!--Trình đăng nhập-->
                        <c:if test="${empty sessionScope.USER}">
                            <a href="login.jsp" class="btn btn-outline-primary">Đăng nhập</a>
                        </c:if>
                        <c:if test="${not empty sessionScope.USER}">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <a href="">Welcome to ${user.username}!</a>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="MainServlet?action=GetUrl&url=customerProfile.jsp&userId=${user.getUserId()}">My Profile</a></li>
                                    <li><a class="dropdown-item" href="MainServlet?action=customer-history-order&userId=${user.getUserId()}">My Order</a></li>
                                    <li><a class="dropdown-item" href="MainServlet?action=Logout">Logout</a></li>
                                </ul>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>
    </body>
</html>
