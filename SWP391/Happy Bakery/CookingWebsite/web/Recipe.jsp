<%-- 
    Document   : Recipe
    Created on : Sep 21, 2022, 4:40:05 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Recipe</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/recipePage.css">
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
        <c:import url="getRecipePageItems"/>
        
        <c:set var="recipeList" value="${sessionScope.recipeList}"/>
       
        <div class="mapping grad">
            <p class="breadcrums"><a href="">Home</a>/<a href="">Recipe</a></p>
            <h1 class="mapping-heading">Recipe</h1>
        </div>

        <div class="container bg-item-grey">
            <div class="recipe-container" id="recipe-container">
                <!-- Recipe Item -->
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

            var recipeList = [
            <c:forEach items="${recipeList}" var="recipe">
                    { id: '<c:out value="${recipe.getRecipeId()}"/>', img: '<c:out value="${recipe.getRecipeImg()}"/>', name: '<c:out value="${recipe.getRecipeName()}"/>', type: '<c:out value="${recipe.getCategoryName()}"/>', 
                        postday: '<c:out value="${recipe.getRecipeAddedDate()}"/>', userid: '<c:out value="${recipe.getUserId()}"/>', username: '<c:out value="${recipe.getUserName()}"/>', 
                        rating: '<c:out value="${recipe.getRecipeRating()}"/>', numberrating: '<c:out value="${recipe.getNumberOfRatings()}"/>'},
            </c:forEach>
            ];
            
            window.sessionStorage.setItem('recipeArray', JSON.stringify(recipeList));
        </script>
        <script src="js/pagingRecipe.js"></script>

        <c:import url="Footer.jsp"/>
    </body>
</html>
