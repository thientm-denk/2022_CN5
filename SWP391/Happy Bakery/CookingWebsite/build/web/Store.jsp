<%-- 
    Document   : Store
    Created on : Sep 24, 2022, 10:32:01 AM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/storePage.css">
        <link rel="stylesheet" href="css/paging.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
        <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
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
        <c:import url="getStorePageItems"/>

        <c:set var="storeList" value="${sessionScope.storeList}"/>

        <div class="mapping grad">
            <p class="breadcrums"><a href="">Home</a>/<a href="">Store</a></p>
            <h1 class="mapping-heading">Store</h1>
        </div>

        <div class="container bg-item-grey">
            <div class="store-container" id="store-container">
                <!-- Store Item -->
                <div class="store-item">
                    <img class="store-img" src="img/banner.jpg">
                    <div class="store-body">
                        <a href="" class="store-name">Tiem banh 123</a>
                        <p class="store-more">Phone: 098765431</p>
                        <p class="store-more">Address: Quang Nam</p>
                    </div>
                </div>
            </div>

            <div class="content__paging">
                <div class="page">
                    <ul>
                        <li class="btn-prev btn-active fas fa-angle-left"></li>
                        <div class="number-page" id="number-page">
                            <!-- Page Number -->
                        </div>
                        <li class="btn-next fas fa-angle-right"></li>
                    </ul>
                </div>
                <div class="total-page"></div>
                <div class="total-item"></div>
            </div>
        </div>

        <script>

            var storeList = [
            <c:forEach items="${storeList}" var="store">
                    { id: <c:out value="${store.getUserId()}"/>, img: "${store.getUserImg()}", 
                        name: "${store.getUserName()}", address: "${store.getUserAddress()}", phone: "${store.getUserPhone()}"},
            </c:forEach>
            ];

            window.sessionStorage.setItem('storeArray', JSON.stringify(storeList));
        </script>
        <script src="js/pagingStore.js"></script>

        <c:import url="Footer.jsp"/>
    </body>
</html>
