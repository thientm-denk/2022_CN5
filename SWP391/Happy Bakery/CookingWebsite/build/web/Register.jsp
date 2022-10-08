<%-- 
    Document   : Register
    Created on : Sep 13, 2022, 1:59:17 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
            <p class="breadcrums"><a href="">Home</a>/<a href="">Register</a></p>
            <h1 class="mapping-heading">Recipe</h1>
        </div>
        <div class="register-container">
            <div class="contaner">
                <div class="register-form">
                    <div class="login-header">
                        <h3 class="fnt-30 mg-bot-20 fnt-wei-15">Tạo tài khoản</h3>
                        <p class="fnt-20">Hãy điền đầy đủ thông tin bên dưới để đăng ký tài khoản thành viên Happy Bakery
                        </p>
                    </div>
                    <form action="MainController" method="post" enctype="multipart/form-data">
                        <div class="login-body">
                            <div class="radio-group">
                                <label for="pass" class="fnt-wei-bold">Tài khoản được tạo dưới danh nghĩa<span class="cl-red">*</span>:</label>
                                <div class="radio-opt">
                                    <input type="radio" id="type" name="usertype" value="user" checked>
                                    <label class="mgn-right-15" for="type">Người dùng</label>
                                    <input type="radio" id="type" name="usertype" value="store">
                                    <label for="type">Doanh nghiệp</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fullname" class="fnt-wei-bold">Tên Của Bạn<span class="cl-red">*</span>:</label>
                                <input id="fullname" name="fullname" type="text" placeholder="Nhập họ tên của bạn" required>
                            </div>
                            <div class="form-group">
                                <label for="mail" class="fnt-wei-bold">Email<span class="cl-red">*</span>:</label>
                                <input id="mail" name="mail" type="text" placeholder="Nhập email" required>
                                <c:if test="${requestScope.emailError != null}">
                                    <span class="alert">Email sai định dạng</span>
                                </c:if>
                                <c:if test="${requestScope.emailDupli != null}">
                                    <span class="alert">Email đã tồn tại</span>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="pass" class="fnt-wei-bold">Mật khẩu<span class="cl-red">*</span>:</label>
                                <input id="pass" name="pass" type="password" placeholder="Nhập mật khẩu" required>
                            </div>
                            <div class="radio-group">
                                <label for="pass" class="fnt-wei-bold">Giới tính<span class="cl-red">*</span>:</label>
                                <div class="radio-opt">
                                    <input type="radio" id="gend" name="gender" value="male" checked>
                                    <label class="mgn-right-15" for="gend">Nam</label>
                                    <input type="radio" id="gend" name="gender" value="female">
                                    <label for="gend">Nữ</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="fnt-wei-bold">Số điện thoại:</label>
                                <input id="phone" name="phone" type="text" placeholder="Nhập số điện thoại">
                                <c:if test="${requestScope.phoneError != null}">
                                    <span class="alert">Sđt sai định dạng</span>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="date" class="fnt-wei-bold">Ngày sinh:</label>
                                <input id="date" name="date" type="date" placeholder="Nhập ngày sinh">
                            </div>
                            <div class="form-group">
                                <label for="address" class="fnt-wei-bold">Địa chỉ:</label>
                                <input id="address" name="address" type="text" placeholder="Nhập địa chỉ">
                            </div>
                            <div class="form-group">
                                <label for="img" class="fnt-wei-bold">Hình ảnh:</label>
                                <input id="img" name="img" type="file" accept="image/*" placeholder="Nhập link hình ảnh cá nhân">
                            </div>
                            <c:if test="${requestScope.registerFail != null}">
                                <p class="cl-red">Đăng ký thất bại</p>
                            </c:if>
                            <c:if test="${requestScope.registerSuccess != null}">
                                <p class="cl-red">Đăng ký thành công</p>
                            </c:if>
                            <input class="submit-btn bg-orange mgn-top-15 mgn-bot-20" type="submit" value="Register" name="action">
                            <p class="line-h"> Bạn đã có tài khoản thành viên? <a href="Login.jsp">Đăng nhập</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <c:import url="Footer.jsp"/>
    </body>
</html>
