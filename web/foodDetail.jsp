<%-- 
    Document   : foodDetail
    Created on : Feb 28, 2025, 11:13:18 PM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            p{
                font-size: 20px;
            }
            body{
                height: 100vh;
            }
            h2{
                text-align: center;
            }
            a{
                text-decoration: none;
                border: 1px solid black;
                background-color: blue;
                color: #fff;
                padding: 5px;
                border-radius: 5px;
                margin-left: 50px;
            }
        </style>
    </head>
    <body>


        <h2>CHI TIẾT FOOD</h2>

        <div class="row" style="border: 1px gray solid;border-radius:5px; margin-bottom: 20px">
            <div class="col-4">
                <img src="${food.getImg()}" width="500" height="500" />
            </div>
            <div class="col-8 ps-5">
                <p style="font-weight: 600">${food.getName()}</p>
                <p>
                    <span style="text-decoration: underline; color: red; font-size: 25px">Price: ${food.getPrice()}</span> 
                </p>
                <div class="col-12 ">
                    <h2 style="text-align: left">Mô tả</h2>
                    <p>${food.getDesc()}</p>
                </div>
            </div>
        </div>
        <a href="MainServlet?btnAction=adminAction&action=mystore">Return</a>
    </body>
</html>
