<%-- 
    Document   : Error
    Created on : Sep 14, 2022, 4:06:15 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <c:import url="AuthRedirect.jsp"/>
        <c:import url="Navigation.jsp"/>
        <div class="error-container">
            <img class="error-img" src="img/error.webp" alt="">
        </div>
        <c:import url="Footer.jsp"/>
    </body>
</html>
