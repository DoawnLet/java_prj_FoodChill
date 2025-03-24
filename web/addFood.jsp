 <%-- 
    Document   : addProduct
    Created on : Jul 6, 2024, 9:19:01 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product Page</title>
        <style>
            
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                height: 100vh;
                
            }
            
            header {
                background-color: #333;
                padding: 10px;
                color: #fff;
                text-align: center;
                margin-bottom: 20px;
            }

            nav {
                margin-bottom: 20px;
            }

            nav a {
                text-decoration: none;
                color: #333;
                padding: 10px;
                margin-right: 10px;
                border-radius: 5px;
                background-color: #eee;
            }
            
            form {
                max-width: 400px;
                margin: 0 auto;
            }

            label {
                display: block;
                margin-bottom: 8px;
            }

            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 16px;
                box-sizing: border-box;
            }

            button {
                background-color: #333;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .img{
                border: 1px solid grey;
            }
            
        </style>
    </head>

    <body>


        <header>
            <h1>Add Food Page</h1>
        </header>


        <nav>
            <a href="admin.jsp">Return</a>
            <a href="#">Hello Admin ${sessionScope.USER.getUsername()}</a>

        </nav>


            <form id="addProductForm" action="AdminFoodServlet" method="post" enctype="multipart/form-data">
            <label for="foodID">Food ID:</label><h5 style="color: red">${requestScope.EXISTED}</h5>
            <input type="text" id="foodID" name="foodID" value="${food.getId()}" required>

            <label for="cateID">Category ID:</label>
            <input type="text" id="cateID" name="cateID" value="${food.getCateId()}" required>

            <label for="name">Food Name:</label>
            <input type="text" id="name" name="name" value="${food.getName()}" required>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${food.getDesc()}" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="1.00" value="${food.getPrice()}" required>

            <div>
                <label for="image">Image:</label>
                <input class="img" type="file" id="image" name="image" placeholder="Enter photo" required>
            </div>

            <table style="width: 100%">
                <tr>
                    <td>
                        <input type="hidden" name="action" value=${requestScope.nextaction}>
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
            <h4 style="color: blue">${requestScope.SUCCESSFULL}</h4>
            
        </form>
    </body>

</html>
