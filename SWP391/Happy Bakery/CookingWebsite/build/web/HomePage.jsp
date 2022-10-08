<%-- 
    Document   : homepage
    Created on : Sep 9, 2022, 8:06:27 PM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/slider.css">
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
        <c:import url="getHomePageItems" />

        <c:import url="Navigation.jsp"/>
        <c:import url="Slider.jsp"/>

        <c:set var="listNewRecipe" value="${sessionScope.listNewRecipe}"/> 
        <c:set var="listNewStore" value="${sessionScope.listNewStore}"/>
        <c:set var="listNewBlog" value="${sessionScope.listNewBlog}"/>
        <c:set var="displayBigNewRecipe" value="${sessionScope.displayBigNewRecipe}"/>
        <c:set var="displayBigNewStore" value="${sessionScope.displayBigNewStore}"/>

        <div class="home-container">
            <div class="item-section">

                <div class="home-search bg-item-grey">
                    <div class="search-container">
                        <input id="pass" type="searchValue" placeholder="Nhập ký tự muốn tìm kiếm">
                        <select name="searchBy">
                            <option value="byAll">Tất cả</option>
                            <option value="byRecipe">Recipe</option>
                            <option value="byStore">Store</option>
                            <option value="byBlog">Blog</option>
                        </select>
                        <a href="#" class="search">Tìm kiếm</a>
                    </div>
                </div>

                <!-- Start Recipe Session -->
                <!-- Start item recipe -->
                <div class="item-about bg-item-grey">
                    <h2>RECIPE</h2>
                    <p>Recipe about all type of cakes</p>
                </div>
                <div class="item-table bg-item-grey">
                    <div class="item-heading bg-red ">
                        <div id="myBtnContainer">
                            <button class="btn active-filter" onclick="filterSelection('recipe-new')">Recipe mới</button>
                            <button class="btn " onclick="filterSelection('recipe-hot')">Recipe hot</button>
                        </div>
                    </div>
                    <div class="filterDiv recipe-new">
                        <div class="item-body">
                            <div class="item-big">
                                <img src="${displayBigNewRecipe.getRecipeImg()}" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">${displayBigNewRecipe.getRecipeName()}</a>
                                    <p class="item-type">${displayBigNewRecipe.getCategoryName()}</p>
                                    <div class="item-rating">
                                        <c:forEach begin="1" end="${displayBigNewRecipe.getRecipeRating()}">
                                            <span class="fa fa-star checked"></span>
                                        </c:forEach>
                                        <c:forEach begin="1" end="${5 - displayBigNewRecipe.getRecipeRating()}">
                                            <span class="fa fa-star"></span>
                                        </c:forEach>
                                    </div>
                                    <p class="item-intro">${displayBigNewRecipe.getRecipeDesciption()}</p>
                                </div>
                            </div>
                            <div class="item-small">

                                <c:forEach var="recipe" items="${listNewRecipe}">
                                    <div class="item-small-child">
                                        <img src="${recipe.getRecipeImg()}" alt="">
                                        <div class="small-child">
                                            <a href="" class="small-child-name">${recipe.getRecipeName()}</a>
                                            <p class="child-type">${recipe.getCategoryName()}</p>
                                            <c:forEach begin="1" end="${recipe.getRecipeRating()}">
                                                <span class="fa fa-star checked"></span>
                                            </c:forEach>
                                            <c:forEach begin="1" end="${5 - recipe.getRecipeRating()}">
                                                <span class="fa fa-star"></span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                    <div class="filterDiv recipe-hot">
                        <div class="item-body">
                            <div class="item-big">
                                <img src="img/croissant.jpg" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">Bánh sừng bò</a>
                                    <p class="item-type">Bánh ngọt</p>
                                    <div class="item-rating">
                                        <span class="fa fa-star checked"></span>
                                        <span class="fa fa-star checked"></span>
                                        <span class="fa fa-star checked"></span>
                                        <span class="fa fa-star"></span>
                                        <span class="fa fa-star"></span>
                                    </div>
                                    <p class="item-intro">This place is shrouded in mystery. Horrible legends and gloom
                                        filled
                                        ancient stories,
                                        full of unimaginable things,
                                        have been going around for centuries. The Endless Dungeon that was created long
                                        before
                                        humans...So say the old people...</p>
                                </div>
                            </div>
                            <div class="item-small">
                                <div class="item-small-child">
                                    <img src="img/Cheese Cake.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Cheese Cake</a>
                                        <p class="child-type">Bánh ngọt</p>
                                        <div class="item-rating">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/crepe.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Crepe</a>
                                        <p class="child-type">Bánh ngọt</p>
                                        <div class="item-rating">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/Sandwich.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Sandwich</a>
                                        <p class="child-type">Bánh mặn</p>
                                        <div class="item-rating">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/waffe.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Cheese Cake</a>
                                        <p class="child-type">Bánh ngọt</p>
                                        <div class="item-rating">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Recipe Session -->

                <!-- Start Store Session -->
                <!-- Start Store recipe -->
                <div class="item-about bg-item-white">
                    <h2>STORE</h2>
                    <p>Best stores</p>
                </div>
                <div class="item-table bg-item-white">
                    <div class="item-heading bg-red">
                        <div id="myBtnContainerStore">
                            <button class="btn active-filter-store" onclick="filterSelectionStore('store-new')">Store
                                mới</button>
                            <button class="btn " onclick="filterSelectionStore('store-sale')">Store sale</button>
                        </div>
                    </div>
                    <div class="filterDiv store-new">
                        <div class="item-body">
                            <div class="item-small-store">
                                <c:forEach var="store" items="${listNewStore}">
                                    <div class="item-small-child">
                                        <img src="${store.getUserImg()}" alt="">
                                        <div class="small-child">
                                            <a href="" class="small-child-name">${store.getUserName()}</a>
                                            <p class="child-type">Address: ${store.getUserAddress()}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="item-big-store">
                                <img src="${displayBigNewStore.getUserImg()}" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">${displayBigNewStore.getUserName()}</a>
                                    <p class="item-type">Phone: ${displayBigNewStore.getUserPhone()}</p>
                                    <p class="item-intro">Address: ${displayBigNewStore.getUserAddress()}</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="filterDiv store-sale">
                        <div class="item-body">
                            <div class="item-small-store">
                                <div class="item-small-child">
                                    <img src="img/Cheese Cake.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Cheese Cake</a>
                                        <p class="child-type">Bánh ngọt</p>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/crepe.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Crepe</a>
                                        <p class="child-type">Bánh ngọt</p>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/Sandwich.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Sandwich</a>
                                        <p class="child-type">Bánh mặn</p>
                                    </div>
                                </div>
                                <div class="item-small-child">
                                    <img src="img/waffe.jpg" alt="">
                                    <div class="small-child">
                                        <a href="" class="small-child-name">Cheese Cake</a>
                                        <p class="child-type">Bánh ngọt</p>
                                    </div>
                                </div>
                            </div>
                            <div class="item-big-store">
                                <img src="img/croissant.jpg" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">Bánh sừng bò</a>
                                    <p class="item-type">Bánh ngọt</p>
                                    <p class="item-intro">This place is shrouded in mystery. Horrible legends and gloom
                                        filled
                                        ancient stories,
                                        full of unimaginable things,
                                        have been going around for centuries. The Endless Dungeon that was created long
                                        before
                                        humans...So say the old people...</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Store Session -->

                <!-- Start Blog Session -->
                <!-- Start Blog recipe -->
                <div class="item-about bg-item-grey">
                    <h2>BLOG</h2>
                    <p>Useful and interesting</p>
                </div>
                <div class="item-table bg-item-grey pad-bot-90">
                    <div class="item-heading bg-red">
                        <div id="myBtnContainerBlog">
                            <button class="btn active-filter-blog" onclick="filterSelectionBlog('blog-new')">Blog mới</button>
                            <button class="btn " onclick="filterSelectionBlog('blog-hot')">Blog hot</button>
                        </div>
                    </div>

                    <div class="filterDiv blog-new">
                        <div class="blog-body">
                            <c:forEach var="blog" items="${listNewBlog}">
                                <div class="blog-item">
                                    <img src="${blog.getBlogImg()}" alt="">
                                    <div class="item-description">
                                        <a href="" class="item-name">${blog.getBlogTitle()}</a>

                                        <p class="item-intro mgn-top-15">${blog.getBlogNote()}</p>
                                        <a class="item-type">by <a class="blog-author" href="#">${blog.getUserName()}</a></a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="filterDiv blog-hot">
                        <div class="blog-body">
                            <div class="blog-item">
                                <img src="img/croissant.jpg" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">Bánh sừng bò 123</a>

                                    <p class="item-intro">This place is shrouded in mystery. Horrible legends and gloom
                                        filled
                                        ancient stories,
                                        full of unimaginable things,
                                        have been going around for centuries. The Endless Dungeon that was created long
                                        before
                                        humans...So say the old people...</p>
                                    <a class="item-type">by <a class="blog-author" href="#">Bánh ngọt</a></a>
                                </div>
                            </div>

                            <div class="blog-item">
                                <img src="img/croissant.jpg" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">Bánh sừng bò</a>

                                    <p class="item-intro">This place is shrouded in mystery. Horrible legends and gloom
                                        filled
                                        ancient stories,
                                        full of unimaginable things,
                                        have been going around for centuries. The Endless Dungeon that was created long
                                        before
                                        humans...So say the old people...</p>
                                    <a class="item-type">by <a class="blog-author" href="#">Bánh ngọt</a></a>
                                </div>
                            </div>

                            <div class="blog-item">
                                <img src="img/croissant.jpg" alt="">
                                <div class="item-description">
                                    <a href="" class="item-name">Bánh sừng bò</a>

                                    <p class="item-intro">This place is shrouded in mystery. Horrible legends and gloom
                                        filled
                                        ancient stories,
                                        full of unimaginable things,
                                        have been going around for centuries. The Endless Dungeon that was created long
                                        before
                                        humans...So say the old people...</p>
                                    <a class="item-type">by <a class="blog-author" href="#">Bánh ngọt</a></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Blog Session -->
            </div>
        </div>
        <script src="js/filter-data.js"></script>
        <c:import url="Footer.jsp"/>
    </body>
</html>
