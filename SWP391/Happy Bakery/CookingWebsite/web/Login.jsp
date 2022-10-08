<%-- 
    Document   : Login.jsp
    Created on : Sep 13, 2022, 1:56:59 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <c:import url="AuthRedirect.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="AuthLogined.jsp"/>
            </c:otherwise>
        </c:choose>
        <c:import url="Navigation.jsp"/>

        <div class="mapping grad">
            <p class="breadcrums"><a href="">Home</a>/<a href="">Login</a></p>
            <h1 class="mapping-heading">Recipe</h1>
        </div>
        <div class="login-container">
            <div class="contaner">
                <div class="login-form">
                    <div class="login-header">
                        <h3 class="fnt-30 mg-bot-20 fnt-wei-15">Xin chào</h3>
                        <p class="fnt-20">Đăng nhập vào tài khoản của bạn</p>
                    </div>
                    <form action="MainController" method="post">
                        <div class="login-body">
                            <div class="form-group">
                                <label for="email" class="fnt-wei-bold">Email Đăng Nhập:</label>
                                <input id="email" name="email" type="text" placeholder="Nhập email bạn đã đăng ký">
                            </div>
                            <div class="form-group">
                                <label for="pass" class="fnt-wei-bold">Mật Khẩu:</label>
                                <input id="pass" name="password" type="password" placeholder="Nhập mật khẩu">
                            </div>
                            <div class="save-login">
                                <input id="save-login-checkbox" type="checkbox" value="saveLogin" name="saveLogin"> 
                                <label for="save-login-checkbox">Ghi Nhớ Đăng Nhập</label>
                            </div>
                            <c:if test="${requestScope.errorLogin != null}">
                                <p class="line-h cl-red">Sai tên đăng nhập hoặc mật khẩu</p>
                            </c:if>
                            <input class="submit-btn bg-orange mgn-top-15 mgn-bot-20" type="submit" value="Login" name="action">
                            <p class="line-h">Nếu không thể đăng nhập (báo thành công nhưng không vào tài khoản) thì bạn hãy xem và làm theo <a href="#">FAQS</a> nhé</p>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <c:import url="Footer.jsp"/>
    </body>
</html>
