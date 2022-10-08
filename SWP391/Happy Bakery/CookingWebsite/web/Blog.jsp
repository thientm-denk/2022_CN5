<%-- 
    Document   : Blog
    Created on : Sep 24, 2022, 12:32:25 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Page</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/paging.css">
        <link rel="stylesheet" href="css/blogPage.css">
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
        <c:import url="getBlogPageItems"/>

        <c:set var="blogList" value="${sessionScope.blogList}"/>

        <div class="mapping grad">
            <p class="breadcrums"><a href="">Home</a>/<a href="">Blog</a></p>
            <h1 class="mapping-heading">Blog</h1>
        </div>

        <div class="container bg-item-grey">
            <div class="store-container" id="blog-container">
                <!-- Blog Item -->
                
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

            var blogList = [
            <c:forEach items="${blogList}" var="blog">
                {id: '<c:out value="${blog.getBlogId()}"/>', img: '<c:out value="${blog.getBlogImg()}"/>', title: '<c:out value="${blog.getBlogTitle()}"/>', type: '<c:out value="${blog.getBlogCategory()}"/>',
                    postday: '<c:out value="${blog.getBlogAddedDay()}"/>', userid: '<c:out value="${blog.getUserId()}"/>', username: '<c:out value="${blog.getUserName()}"/>',
                    rating: '<c:out value="${blog.getBlogRatingPoint()}"/>', note: '<c:out value="${blog.getBlogNote()}"/>'},
            </c:forEach>
            ];

            window.sessionStorage.setItem('BlogArray', JSON.stringify(blogList));
        </script>
        <script src="js/pagingBlog.js"></script>

        <c:import url="Footer.jsp"/>
    </body>
</html>
