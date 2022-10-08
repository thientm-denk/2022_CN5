<%-- 
    Document   : AuthLogined
    Created on : Sep 14, 2022, 3:53:41 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}"/>
        <div class="wrapper bg-grey">
            <div class="container">
                <div class="user-container">
                    <div class="user-spec has-hover">
                        <c:if test="${empty userRecipeDetail.getUserImg()}">
                            <c:set var="authorImg" value="img/userdefault.jpg"/>
                        </c:if>
                        <c:if test="${not empty userRecipeDetail.getUserImg()}">
                            <c:set var="authorImg" value="${userRecipeDetail.getUserImg()}"/>
                        </c:if>
                        <img class="user-img" src="${authorImg}" alt="">
                        <p class="user-fullname">${user.getUserName()}</p>
                        <div class="navbar-notify">
                            <ul class="notify-list">
                                <li class="notify-item">
                                    <c:url var="profileLink" value="getUserProfile">
                                        <c:param name="checkedUserId" value="${user.getUserId()}"/>
                                    </c:url>
                                    <a href="${profileLink}">Hồ sơ</a>
                                </li>
                                <li class="notify-item">
                                    <c:url var="logoutLink" value="MainController">
                                        <c:param name="action" value="logout"/>
                                    </c:url>
                                    <a href="${logoutLink}">Đăng xuất</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
