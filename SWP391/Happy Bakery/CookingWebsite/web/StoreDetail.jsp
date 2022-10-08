<%-- 
    Document   : StoreDetail
    Created on : Oct 3, 2022, 10:01:22 AM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Detail</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/StoreProfile.css">
        <link rel="stylesheet" href="css/pfPaging.css">
        <link rel="stylesheet" href="css/recipePage.css">
        <link rel="stylesheet" href="css/recipePfPaging.css">
        <link rel="stylesheet" href="css/blogPfPaging.css">
        <link rel="stylesheet" href="css/blogPf.css">
        <link rel="stylesheet" href="css/orderPfPaging.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
        <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    </head>
    <body onload="submit()">

        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <c:import url="AuthRedirect.jsp"/>
            </c:when>
            <c:otherwise>
                <c:import url="AuthLogined.jsp"/>
            </c:otherwise>
        </c:choose>
        <c:import url="Navigation.jsp"/>

        <c:set var="checkedUser" value="${sessionScope.checkedUser}"/>
        <c:set var="blogPosted" value="${sessionScope.blogPosted}"/>
        <c:set var="recipePosted" value="${sessionScope.recipePosted}"/>
        <c:set var="followers" value="${sessionScope.followers}"/>
        <c:set var="pfTotalRating" value="${sessionScope.pfTotalRating}"/>
        <c:set var="pfBadgeList" value="${sessionScope.pfBadgeList}"/>
        <c:set var="pfBlogList" value="${sessionScope.pfBlogList}"/>
        <c:set var="pfRecipeList" value="${sessionScope.pfRecipeList}"/>
        <c:set var="pfOrderList" value="${sessionScope.pfOrderList}"/>
        <c:set var="user" value="${sessionScope.user}"/>    
        <c:if test="${checkedUser.getUserType() == 'STORE'}">
            <c:set var="specIngredient" value="${sessionScope.specIngredient}"/>
        </c:if>
        <c:set var="followStat" value="${sessionScope.followStat}"/>

        <div class="mapping grad">
            <p class="breadcrums"><a href="HomePage.jsp">Home</a>/<a href="Store.jsp">Store</a>/<a href="#">Profile</a></p>
            <h1 class="mapping-heading">Profile</h1>
        </div>

        <div class="container">
            <div class="store-profile-container">
                <div class="store-heading">
                    <c:if test="${checkedUser.getUserImg() == ''}">
                        <img class="store-img" src="img/userdefault.jpg" alt="">
                    </c:if>

                    <c:if test="${not empty checkedUser.getUserImg()}">
                        <img class="store-img" src="${checkedUser.getUserImg()}" alt="">
                    </c:if>
                    <h2 class="store-fullname">${checkedUser.getUserName()}</h2>

                    <c:url var="unfollow" value="followUser">
                        <c:param name="action" value="unfollow"/>
                        <c:param name="followed" value="${checkedUser.getUserId()}"/>
                        <c:param name="follower" value="${user.getUserId()}"/>
                    </c:url>

                    <c:url var="follow" value="followUser">
                        <c:param name="action" value="follow"/>
                        <c:param name="followed" value="${checkedUser.getUserId()}"/>
                        <c:param name="follower" value="${user.getUserId()}"/>
                    </c:url>

                    <c:if test="${checkedUser.getUserId() != user.getUserId()}">
                        <c:if test="${followStat == false}">
                            <a href="${follow}" class="btn-follow">Follow</a>
                        </c:if>
                        <c:if test="${followStat == true}">
                            <a href="${unfollow}" class="btn-follow">Followed <i class="fa fa-check"></i></a> 
                            </c:if>
                        </c:if>                    

                </div>

                <div class="store-info-filter">
                    <div class="info-table">
                        <div class="table-choice">
                            <div id="myBtnContainerPf" class="filter-selection">
                                <button class="btn-pf active-filter-pf"
                                        onclick="filterSelectionPfStore('information-table')">Information</button>
                                <c:if test="${checkedUser.getUserType() == 'STORE'}">
                                    <button class="btn-pf "
                                            onclick="filterSelectionPfStore('ingredient-table')">Ingredient</button>
                                </c:if>
                                <button class="btn-pf " onclick="filterSelectionPfStore('recipe-table')">Recipe</button>
                                <button class="btn-pf " onclick="filterSelectionPfStore('blog-table')">Blog</button>
                                <c:if test="${checkedUser.getUserId() == user.getUserId()}">
                                    <button id="click-order" class="btn-pf " onclick="filterSelectionPfStore('order-table')">Order</button>
                                </c:if>

                            </div>

                            <c:if test="${checkedUser.getUserType() == 'STORE'}">
                                <div class="filterDivPf ingredient-table">
                                    <div class="add-section">
                                        <a href="#" class="add-btn"><i class="fa fa-plus"></i> Add More Ingredient</a>
                                    </div>

                                    <div class="search-section">
                                        <input type="text" name="txtsearch" class="search-val" value=""
                                               placeholder="Input search value...">
                                        <select name="searchby">
                                            <option value="byName">Name</option>
                                            <option value="bycate">Category</option>
                                        </select>
                                        <input type="submit" class="search-btn" value="SEARCH" name="action">
                                    </div>

                                    <div class="table-body" id="ingredient-table-body">

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
                            </c:if>

                            <c:if test="${checkedUser.getUserId() == user.getUserId()}">
                                <div class="filterDivPf order-table">

                                    <c:if test="${checkedUser.getUserType() == 'STORE'}">
                                        <div class="search-section" style="padding-top: 40px;">
                                            <input type="text" name="txtsearch" class="search-val" value=""
                                                   placeholder="Input search value...">
                                            <select name="searchby">
                                                <option value="byUserName">User Name</option>
                                            </select>
                                            <input type="submit" class="search-btn" value="SEARCH" name="action">
                                        </div>
                                    </c:if>



                                    <div class="filter-order-status" >
                                        <c:url var="processing" value="filterOrder">
                                            <c:param value="1" name="orderStatus"/>
                                            <c:param name="userType" value="${checkedUser.getUserType()}"/>
                                            <c:param name="userId" value="${checkedUser.getUserId()}"/>
                                        </c:url>
                                        <c:url var="delivering" value="filterOrder">
                                            <c:param value="2" name="orderStatus"/>
                                            <c:param name="userType" value="${checkedUser.getUserType()}"/>
                                            <c:param name="userId" value="${checkedUser.getUserId()}"/>
                                        </c:url>
                                        <c:url var="completed" value="filterOrder">
                                            <c:param value="3" name="orderStatus"/>
                                            <c:param name="userType" value="${checkedUser.getUserType()}"/>
                                            <c:param name="userId" value="${checkedUser.getUserId()}"/>
                                        </c:url>
                                        <a href="${processing}" onclick="clickChange()" class="status">Processing</a>
                                        <a href="${delivering}" onclick="clickChange()" class="status">Delivering</a>
                                        <a href="${completed}" onclick="clickChange()" class="status">Completed</a>
                                    </div>

                                    <div class="table-order" id="order-container">
                                    </div>

                                    <div class="content__paging-order">
                                        <div class="page-order">
                                            <ul>
                                                <li class="btn-prev-order btn-active-order fas fa-angle-left"></li>
                                                <div class="number-page-order" id="number-page-order">
                                                    <!-- Page Number -->
                                                </div>
                                                <li class="btn-next-order fas fa-angle-right"></li>
                                            </ul>
                                        </div>
                                        <div class="total-page-order"></div>
                                        <div class="total-item-order"></div>
                                    </div>

                                </div>
                            </c:if>

                            <div class="filterDivPf recipe-table">

                                <div class="add-section">
                                    <a href="#" class="add-btn"><i class="fa fa-plus"></i> Add More Recipes</a>
                                </div>

                                <div class="search-section">
                                    <input type="text" name="txtsearch" class="search-val" value=""
                                           placeholder="Input search value...">
                                    <select name="searchby">
                                        <option value="byName">Name</option>
                                        <option value="bycate">Category</option>
                                        <option value="byCountry">Country</option>
                                    </select>
                                    <input type="submit" class="search-btn" value="SEARCH" name="action">
                                </div>

                                <div class="table-body-recipe" id="recipe-container">

                                </div>
                                <div class="content__paging-recipe">
                                    <div class="page-recipe">
                                        <ul>
                                            <li class="btn-prev-recipe btn-active-recipe fas fa-angle-left"></li>
                                            <div class="number-page-recipe" id="number-page-recipe">
                                                <!-- Page Number -->
                                            </div>
                                            <li class="btn-next-recipe fas fa-angle-right"></li>
                                        </ul>
                                    </div>
                                    <div class="total-page-recipe"></div>
                                    <div class="total-item-recipe"></div>
                                </div>

                            </div>

                            <div class="filterDivPf blog-table">

                                <div class="add-section">
                                    <a href="#" class="add-btn"><i class="fa fa-plus"></i> Add More Blogs</a>
                                </div>

                                <div class="search-section">
                                    <input type="text" name="txtsearch" class="search-val" value=""
                                           placeholder="Input search value...">
                                    <select name="searchby">
                                        <option value="byTitle">Title</option>
                                        <option value="bycate">Category</option>
                                    </select>
                                    <input type="submit" class="search-btn" value="SEARCH" name="action">
                                </div>

                                <div class="blog-container" id="blog-container">

                                </div>
                                <div class="content__paging-blog">
                                    <div class="page-blog">
                                        <ul>
                                            <li class="btn-prev-blog btn-active-blog fas fa-angle-left"></li>
                                            <div class="number-page-blog" id="number-page-blog">
                                                <!-- Page Number -->
                                            </div>
                                            <li class="btn-next-blog fas fa-angle-right"></li>
                                        </ul>
                                    </div>
                                    <div class="total-page-blog"></div>
                                    <div class="total-item-blog"></div>
                                </div>

                            </div>



                            <div class="filterDivPf information-table">
                                <div class="table-body-info">

                                    <div class="infor-badge">
                                        <div class="badge-heading">
                                            <h2>Badge Achieved</h2>
                                        </div>
                                        <div class="badge-body">
                                            <c:forEach var="badge" items="${pfBadgeList}">
                                                <p class="badge-title">${badge.getBadgeName()}</p>
                                            </c:forEach>
                                            <c:if test="${pfBadgeList.size() == 0}">
                                                <p class="badge-title">None</p>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="recipe-blog-posted">
                                        <div class="posted">
                                            <div class="post-heading">
                                                <h3>Recipe posted</h3>
                                            </div>
                                            <div class="post-body">
                                                <p>${recipePosted}</p>
                                            </div>
                                        </div>
                                        <div class="posted">
                                            <div class="post-heading">
                                                <h3>Blog posted</h3>
                                            </div>
                                            <div class="post-body">
                                                <p>${blogPosted}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="recipe-blog-posted">
                                        <div class="posted">
                                            <div class="post-heading">
                                                <h3>Followers</h3>
                                            </div>
                                            <div class="post-body">
                                                <p>${followers} <i class="fas fa-user"></i></p>
                                            </div>
                                        </div>
                                        <div class="posted">
                                            <div class="post-heading">
                                                <h3>Total Rating</h3>
                                            </div>
                                            <div class="post-body">
                                                <c:forEach begin="1" end="${pfTotalRating}">
                                                    <span class="fa fa-star checked"></span>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${5 - pfTotalRating}">
                                                    <span class="fa fa-star"></span>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="user-detail">
                                        <div class="detail-heading">
                                            <h2>Detail</h2>
                                        </div>
                                        <div class="detail-body">
                                            <div class="detail-item">
                                                <p class="detail-title">Gender:</p>
                                                <p class="detail-descript">${checkedUser.getUserGender()}</p>
                                            </div>
                                            <div class="detail-item">
                                                <p class="detail-title">Birth Day:</p>
                                                <p class="detail-descript">${checkedUser.getUserBDay()}</p>
                                            </div>
                                            <div class="detail-item">
                                                <p class="detail-title">Address:</p>
                                                <p class="detail-descript">${checkedUser.getUserAddress()}</p>
                                            </div>
                                            <div class="detail-item">
                                                <p class="detail-title">Phone:</p>
                                                <p class="detail-descript">${checkedUser.getUserPhone()}</p>
                                            </div>
                                            <div class="detail-item">
                                                <p class="detail-title">Email:</p>
                                                <p class="detail-descript">${checkedUser.getUserMail()}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="update-user">
                                        <a class="update-user-info" href="UpdateProfile">UPDATE</a>
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

            let recipeList = [
            <c:forEach items="${pfRecipeList}" var="recipe">
            { loginedid: '${user.getUserId()}',
                    id: '${recipe.getRecipeId()}',
                    img: '${recipe.getRecipeImg()}',
                    name: '${recipe.getRecipeName()}',
                    type: '${recipe.getCategoryName()}',
                    postday: '${recipe.getRecipeAddedDate()}',
                    userid: '${recipe.getUserId()}',
                    username: '${recipe.getUserName()}',
                    rating: '${recipe.getRecipeRating()}',
                    numberrating: '${recipe.getNumberOfRatings()}'},
            </c:forEach>
            ];
            <c:if test="${checkedUser.getUserType() == 'STORE'}">
            let ingredientList = [
                <c:forEach var="ingredient" items="${specIngredient}">
            {loginedid: '${user.getUserId()}',
                    pfid: '${checkedUser.getUserId()}',
                    id: '${ingredient.getIngredientId()}',
                    img: "${ingredient.getIngredientImg()}",
                    name: "${ingredient.getIngredientName()}",
                    category: "${ingredient.getIngredientCategory()}",
                    price: ${ingredient.getPrice()},
                    sale: ${ingredient.getSalePercent()}},
                </c:forEach>
            ];
            window.sessionStorage.setItem('IngredientArray', JSON.stringify(ingredientList));
            </c:if>

            let blogList = [
            <c:forEach items="${pfBlogList}" var="blog">
            { loginedid: '${user.getUserId()}',
                    id: ${blog.getBlogId()},
                    img: '${blog.getBlogImg()}',
                    title: '${blog.getBlogTitle()}',
                    type: '${blog.getBlogCategory()}',
                    postday: '${blog.getBlogAddedDay()}',
                    userid: '${blog.getUserId()}',
                    username: '${user.getUserName()}',
                    rating: '${blog.getBlogRatingPoint()}',
                    note: '${blog.getBlogNote()}' },
            </c:forEach>
            ];
            <c:if test="${checkedUser.getUserId() == user.getUserId()}">
            let orderList = [
                <c:forEach items="${pfOrderList}" var="order">
            { img: '${order.getOrderImg()}',
                    id: '${order.getOrderId()}',
                    orderdate: '${order.getOrderDate()}',
                    shipdate: '${order.getShipDate()}',
                    status: '${order.getOrderStatus()}',
                    userid: '${order.getUserId()}',
                    username: '${order.getUserName()}',
                    checkstore: '${checkedUser.getUserType()}' },
                </c:forEach>
            ];
            window.sessionStorage.setItem('orderArray', JSON.stringify(orderList));
            </c:if>

            window.sessionStorage.setItem('recipeArray', JSON.stringify(recipeList));
            window.sessionStorage.setItem('BlogArray', JSON.stringify(blogList));
        </script>   
        <script src="js/filterDataProfile.js"></script>
        <c:if test="${checkedUser.getUserType() == 'STORE'}">
            <script src="js/storePfPaging.js"></script>
        </c:if>
        <script src="js/recipePfPaging.js"></script>
        <script src="js/blogPfPaging.js"></script>
        <c:if test="${checkedUser.getUserId() == user.getUserId()}">
            <script src="js/orderPfPaging.js"></script>
        </c:if>
        
            <script src="js/loadPage.js"></script>
        <c:import url="Footer.jsp"/>
    </body>
</html>
