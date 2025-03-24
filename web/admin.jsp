<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="images/logo.png"/>

        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>

        <link href="css/styleAdmin.css" rel="stylesheet" type="text/css"/>

        <title>AdminHub</title>

        <style>
            /* General table styles */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px; /* Add some spacing */
            }

            th, td {
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
                color: #333;
                text-align: center;
            }

            /* Links in table */
            a {
                text-decoration: none;
                color: #007bff; /* Bootstrap blue color */
            }

            /* Buttons */
            button {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 8px 16px;
                cursor: pointer;
                border-radius: 4px;
            }

            button:hover {
                background-color: #0056b3; /* Darker shade of blue on hover */
            }

            /* Form input style */
            input[type="text"] {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 110px;
            }

            input[type="number"] {
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 110px;
            }

            .action {
                padding: 5px;
                border-radius: 10px; 
                background-color: #007bff;
                color: white;
            }
            .action:hover{
                background-color: #0056b3;
            }
            td{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:set var="foodList" value="${sessionScope.FOOD_LIST}"/>
        <c:set var="categoryList" value="${sessionScope.CATE_LIST}"/>
        <c:set var="orderList" value="${sessionScope.ORDER_LIST}"/>
        <c:set var="discountList" value="${sessionScope.DISCOUNT_LIST}"/>
        <c:set var="userList" value="${sessionScope.USER_LIST}"/>
        <c:set var="dashboard" value="dashboard"/>
        <c:set var="mystore" value="mystore"/>
        <c:set var="message" value="message"/>
        <c:set var="user" value="user"/>
        <c:set var="category" value="category"/>
        <c:set var="order" value="order"/>
        <c:set var="couponCode" value="couponCode"/>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">AdminHub</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="MainServlet?btnAction=adminAction&action=mystore">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Store</span>
                    </a>
                </li>
                <li>
                    <a href="MainServlet?btnAction=adminAction&action=user">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">User</span>
                    </a>
                </li>
                <li>
                    <a href="MainServlet?btnAction=adminAction&action=category">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Category</span>
                    </a>
                </li>
                <li>
                    <a href="MainServlet?btnAction=adminAction&action=order">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Orders</span>
                    </a>
                </li>
                <li>
                    <a href="MainServlet?btnAction=adminAction&action=couponCode">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Coupon Code</span>
                    </a>
                </li>
                <li>
                    <a href="LoginServlet?btnAction=Logout" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link">Menu</a>
                <form action="#">
                    <div class="form-input"></div>
                </form>

                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                    <span class="num">0</span>
                </a>
                <a href="#" class="profile">
                    <img src="image/admin.png">
                </a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->

            <c:if test="${sessionScope.ADMIN_ACTION eq mystore}">
                
                <div id="mystore" class="content-section">
                    
                    <!-- My Store content -->
                    <!-- You can fill this section with your store management content -->
                    <table border="1">
                        <thead>
                            <tr>
                                <th style="display: flex; justify-content: space-between; align-items: center;">
                                    <div>
                                        <form action="AdminFoodServlet" method="POST">
                                            <!--<a class="action" href="AdminFoodServlet?action=AddOrEdit">ADD A NEW FOOD</a>-->
                                            <!--<button type="submit" name="action" value="AddOrEdit">ADD A NEW FOOD</button>-->
                                            <input type="hidden" name="action" value="AddOrEdit">
                                            <button type="submit">ADD A NEW FOOD</button>
                                        </form>
                                    </div>
                                    <div>
                                        <form action="AdminFoodServlet?action=Search" method="POST">
                                            <input type="text" style="width: 200px" name="keyword" placeholder="Search food..." 
                                                   value='<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>'>
                                            <input type="submit" value="Search" style="padding: 5px">
                                        </form>
                                    </div>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>Food_ID</th>
                                                <th>Cate_ID</th>
                                                <th>Name</th>
                                                <th>Description</th>
                                                <th>Price</th>
                                                <th>Image</th>
                                                <th colspan="2">Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="food" items="${foodList}">
                                                <tr>
                                                    <td><a href="AdminFoodServlet?action=Detail&id=${food.getId()}">${food.getId()}</a></td>
                                                    <td>${food.getCateId()}</td>
                                                    <td><input type="hidden" name="name" value="${food.getName()}">${food.getName()}</td>
                                                    <td><input type="hidden" name="description" value="${food.getDesc()}">${food.getDesc()}</td>
                                                    <td><input type="hidden" name="price" value="${food.getPrice()}">${food.getPrice()}</td>
                                                    <td><img src="${food.getImg()}" width="100" height="100" /></td>
                                                    <td><a class="action" href="AdminFoodServlet?action=Edit&id=${food.getId()}">Edit</a></td>
                                                    <td><a class="action" href="AdminFoodServlet?action=Delete&id=${food.getId()}">Delete</a></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>

                                    </table>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${sessionScope.ADMIN_ACTION eq user}">
                <div id="mystore" class="content-section">
                    <table border="1">
                        <thead>
                            <tr>
                                <th style="align-items: center;">
                                    <div>
                                        <form action="AdminUserServlet?action=Search" method="POST">
                                            <input type="text" style="width: 200px" name="keyword" placeholder="Search food..." 
                                                   value='<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>'>
                                            <input type="submit" value="Search" style="padding: 5px">
                                        </form>
                                    </div>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <!--Product Management-->
                                <td>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>User_ID</th>
                                                <th>Username</th>
                                                <th>Email</th>
                                                <th>Name</th>
                                                <th>Phone</th>
                                                <th>Street</th>
                                                <th>Ward</th>
                                                <th>District</th>
                                                <th>City</th>
                                                <th colspan="1">Action</th>

                                            </tr>
                                        </thead>

                                        <tbody>

                                            <c:forEach var="user" items="${userList}">
                                            <form action="AdminUserServlet" method="POST" accept-charset="UTF-8">
                                                <tr>
                                                    <td><a href="AdminUserServlet?action=Detail&id=${user.getUserId()}">${user.getUserId()}</a></td>
                                                    <td><input type="hidden" name="username" value="${user.getUsername()}">${user.getUsername()}</td>
                                                    <td><input type="hidden" name="email" value="${user.getEmail()}">${user.getEmail()}</td>
                                                    <td><input type="hidden" name="name" value="${user.getName()}">${user.getName()}</td>
                                                    <td><input type="hidden" name="phone" value="${user.getPhone()}">${user.getPhone()}</td>
                                                    <td><input type="hidden" name="street" value="${user.getStreet()}">${user.getStreet()}</td>
                                                    <td><input type="hidden" name="ward" value="${user.getWard()}">${user.getWard()}</td>
                                                    <td><input type="hidden" name="district" value="${user.getDistrict()}">${user.getDistrict()}</td>
                                                    <td><input type="hidden" name="city" value="${user.getCity()}">${user.getCity()}</td>
                                                    <td><a class="action" href="AdminUserServlet?action=Delete&id=${user.getUserId()}">Delete</a></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                        </tbody>

                    </table>

                    </td>
                    </tr>
                    </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${sessionScope.ADMIN_ACTION eq category}">
                <div id="mystore" class="content-section">
                    <!-- My Store content -->
                    <!-- You can fill this section with your store management content -->
                    <table border="1">
                        <thead>
                            <tr>
                                <th style="display: flex; justify-content: space-between; align-items: center;">
                                    <div>
                                        <form action="AdminCategoryServlet" method="POST">
                                            <!--<a class="action" href="AdminFoodServlet?action=AddOrEdit">ADD A NEW FOOD</a>-->
                                            <!--<button type="submit" name="action" value="AddOrEdit">ADD A NEW FOOD</button>-->
                                            <input type="text" name="id" placeholder="Enter id...">
                                            <input type="text" name="name" placeholder="Enter name...">
                                            <button type="submit" name="action" value="Add">ADD A NEW Category</button>
                                        </form>
                                    </div>
                                    <div>
                                        <form action="AdminCategoryServlet?action=Search" method="POST">
                                            <input type="text" style="width: 200px" name="keyword" placeholder="Search category..." 
                                                   value='<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>'>
                                            <input type="submit" value="Search" style="padding: 5px">
                                        </form>
                                    </div>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>Category_ID</th>
                                                <th>Name</th>
                                                <th colspan="2">Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="cate" items="${categoryList}">
                                            <form action="AdminCategoryServlet" method="POST">
                                                <tr>
                                                    <td style="text-align: center"><input type="hidden" name="cateID" value="${cate.getCateId()}">${cate.getCateId()}</td>
                                                    <td><input type="text" style="width: 100%" name="name" value="${cate.getNameCate()}"></td>
                                                    <td style="text-align: center"><button type="submit" name="action" value="Update">Update</button></td>
                                                    <td style="text-align: center"><button type="submit" name="action" value="Delete">Delete</button></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                        </tbody>

                    </table>

                    </td>
                    </tr>
                    </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${sessionScope.ADMIN_ACTION eq order}">
                <div id="mystore" class="content-section">
                    <!-- My Store content -->
                    <!-- You can fill this section with your store management content -->
                    <table border="1">
                        <thead>
                            <tr>
                                <th style="align-items: center;">
                                    <div>
                                        <form action="AdminOrderServlet?action=Search" method="POST">
                                            <input type="text" style="width: 200px" name="keyword" placeholder="Search food..." 
                                                   value='<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>'>
                                            <input type="submit" value="Search" style="padding: 5px">
                                        </form>
                                    </div>
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <table border="1">
                                        <thead>
                                            <tr>
                                                <th>Order_ID</th>
                                                <th>Name</th>
                                                <th>Phone</th>
                                                <th>TotalPrice</th>
                                                <th>OrderDate</th>
                                                <th>Address</th>
                                                <th>Status</th>
                                                <th colspan="1">Action</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="order" items="${orderList}">
                                            <form action="AdminOrderServlet" method="POST">
                                                <tr>
                                                    <td><input name="id" type="hidden" value="${order.getOrderId()}">${order.getOrderId()}</td>
                                                    <td>${order.getName()}</td>
                                                    <td>${order.getPhone()}</td>
                                                    <td>${order.getTotal()}</td>
                                                    <td>${order.getOrderDate()}</td>
                                                    <td>${order.getAddress()}</td>
                                                    <td style="text-align: center"><input style="width: 50px; text-align: center; align-items: center; padding: 2px" name="status" value="${order.getStatus().id}"></td>
                                                    <td style="text-align: center"><button type="submit" name="action" value="Update">Update</button></td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                        </tbody>

                    </table>

                    </td>
                    </tr>
                    </tbody>
                    </table>
                </div>
            </c:if>

                <c:if test="${sessionScope.ADMIN_ACTION eq couponCode}">
                    <div id="mystore" class="content-section">
                        <!-- My Store content -->
                        <!-- You can fill this section with your store management content -->
                        <table border="1">
                            <thead>
                                <tr>
                                    <th style="display: flex; justify-content: space-between; align-items: center;">
                                        <div>
                                            <form action="AdminDiscountServlet" method="POST">
                                                <!--<a class="action" href="AdminFoodServlet?action=AddOrEdit">ADD A NEW FOOD</a>-->
                                                <!--<button type="submit" name="action" value="AddOrEdit">ADD A NEW FOOD</button>-->
                                                <input type="hidden" name="action" value="Add">
                                                <button type="submit">ADD A NEW DISCOUNT</button>
                                            </form>
                                        </div>
                                        <div>
                                            <form action="AdminDiscountServlet?action=Search" method="POST">
                                                <input type="text" style="width: 200px" name="keyword" placeholder="Search discount..." 
                                                       value='<%=request.getParameter("keyword") != null ? request.getParameter("keyword") : ""%>'>
                                                <input type="submit" value="Search" style="padding: 5px">
                                            </form>
                                        </div>
                                    </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <table border="1">
                                            <thead>
                                                <tr>
                                                    <th>Discount_ID</th>
                                                    <th>Description</th>
                                                    <th>Percentage</th>
                                                    <th>StartDate</th>
                                                    <th>EndDate</th>
                                                    <th colspan="2">Action</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach var="discount" items="${discountList}">
                                                <form action="AdminDiscountServlet" method="POST">
                                                    <tr>
                                                        <td><input name="id" type="hidden" value="${discount.getDiscount_ID()}">${discount.getDiscount_ID()}</td>
                                                        <td><input name="description" type="hidden" value="${discount.getDescription()}">${discount.getDescription()}</td>
                                                        <td style="width: 50px"><input style="height: 30px; text-align: center"  name="percentage" value="${discount.getPercentage()}"></td>
                                                        <td><input type="date" name="startDate" value="${discount.getStartDate()}"></td>
                                                        <td><input type="date" name="endDate" value="${discount.getEndDate()}"></td>
                                                        <td><button type="submit" name="action" value="Update">Update</button></td>
                                                        <td><button type="submit" name="action" value="Delete">Delete</button></td>
                                                    </tr>
                                                </form>
                                            </c:forEach>
                            </tbody>

                        </table>

                        </td>
                        </tr>
                        </tbody>
                        </table>
                    </div>
                </c:if>

        </section>
        <script src="js/admin.js"></script>

    </body>
</html>
