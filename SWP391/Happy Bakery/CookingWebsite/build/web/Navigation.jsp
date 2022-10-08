<%-- 
    Document   : Navigation
    Created on : Sep 13, 2022, 1:51:44 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
    </head>
    <body>
        <div id="nav-contain">
            <div class="container">
                <div class="nav-container">
                    <h1 class="heading">Happy Bakery</h1>

                    <ul class="navbar">
                        <li><a href="HomePage.jsp">HOME</a></li>
                        <li><a href="Recipe.jsp">RECIPE</a></li>
                        <li><a href="Blog.jsp">BLOG</a></li>
                        <li><a href="Store.jsp">STORE</a></li>
                        <li><a href="#about">CART</a></li>
                        <li><a href="#about">FAQS</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <script src="js/navbar.js"></script>
    </body>
</html>
