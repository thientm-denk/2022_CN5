<%-- 
    Document   : RecipeDetail
    Created on : Sep 28, 2022, 12:47:43 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="img/websitelogo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/detailRecipe.css">
        <link rel="stylesheet" href="css/modal.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="tinymce/js/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#mytextarea',
                plugins: 'advlist autolink lists link image charmap preview anchor pagebreak code visualchars wordcount',
                setup: function (editor) {
                    var max = 50;
                    editor.on('submit', function (event) {
                        var numChars = tinymce.activeEditor.plugins.wordcount.body.getCharacterCount();
                        if (numChars > max) {
                            alert("Over 50 letters !!");
                            event.preventDefault();
                            return false;
                        }
                    });
                }

            });
        </script>

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

        <c:set var="listStep" value="${sessionScope.listStep}"/>
        <c:set var="listCmt" value="${sessionScope.listCmt}"/>
        <c:set var="listIngredient" value="${sessionScope.listIngredient}"/>
        <c:set var="recipeRecipeDetail" value="${sessionScope.recipeRecipeDetail}"/>
        <c:set var="userRecipeDetail" value="${sessionScope.userRecipeDetail}"/>

        <c:if test="${recipeRecipeDetail != null}">
            <c:url value = "getRecipeDetail" var = "myURL">
                <c:param name = "recipeId" value = "${recipeRecipeDetail.getRecipeId()}"/>
            </c:url>
            <c:import url = "${myURL}"/>
        </c:if>

        <div class="mapping grad">
            <p class="breadcrums"><a href="HomePage.jsp">Home</a>/<a href="Recipe.jsp">Recipe</a>/<a href="#">${recipeRecipeDetail.getRecipeName()}</a></p>
            <h1 class="mapping-heading">Recipe</h1>
        </div>
        <div class="container">
            <div class="recipe-detail-container">
                <h3 class="recipe-heading">${recipeRecipeDetail.getRecipeName()}</h3>
                <div class="rating-container">
                    <div class="rating-rate">
                        <c:forEach begin="1" end="${recipeRecipeDetail.getRecipeRating()}">
                            <i class="fa fa-star checked"></i>
                        </c:forEach>
                        <c:forEach begin="1" end="${5 - recipeRecipeDetail.getRecipeRating()}">
                            <i class="fa fa-star"></i>
                        </c:forEach>
                    </div>
                    <p class="rating-number">Number of reviews: ${recipeRecipeDetail.getNumberOfRatings()}</p>
                </div>
                <div class="author-container">
                    <c:if test="${empty userRecipeDetail.getUserImg()}">
                        <c:set var="authorImg" value="img/userdefault.jpg"/>
                    </c:if>
                    <c:if test="${not empty userRecipeDetail.getUserImg()}">
                        <c:set var="authorImg" value="${userRecipeDetail.getUserImg()}"/>
                    </c:if>
                    <img class="author-img" src="${authorImg}" alt="">
                    <p class="author-fullname">Recipe by <a href="getUserProfile?checkedUserId=${userRecipeDetail.getUserId()}" class="author-redirect">${userRecipeDetail.getUserName()}</a></p>
                </div>

                <div class="body-detail" id="body-detail">
                    <div class="save-rate-container" id="save-rate-container">
                        <div class="save-container">
                            <i class="fa fa-bookmark" style="font-size: 30px;"></i>
                        </div>

                        <div class="rate-container js-buy">
                            <i class="fa fa-star-half-o" style="font-size: 30px;"></i>
                        </div>
                    </div>

                    <div class="body-info">
                        <img class="body-img" src="${recipeRecipeDetail.getRecipeImg()}" alt="">
                        <div class="cooking-time">
                            <div class="time-spec">
                                <span class="time-ammount-heading">PREP TIME</span>
                                <span class="time-ammount">${recipeRecipeDetail.getPrepTime()} MINS</span>
                            </div>
                            <div class="time-spec">
                                <span class="time-ammount-heading">COOK TIME</span>
                                <span class="time-ammount">${recipeRecipeDetail.getCookTime()} MINS</span>
                            </div>
                            <div class="time-spec">
                                <span class="time-ammount-heading">YIELDS</span>
                                <span class="time-ammount">${recipeRecipeDetail.getYields()}</span>
                            </div>
                        </div>

                        <div class="body-description">
                            <h3 class="recipe-description-heading">DESCRIPTION</h3>
                            <p class="recipe-description-detail">${recipeRecipeDetail.getRecipeDesciption()}</p>
                        </div>

                        <div class="ingredient-list">
                            <h3 class="ingredient-list-heading">INGREDIENT</h3>
                            <div class="ingredient-detail">
                                <c:forEach var="ingredient" items="${listIngredient}">
                                    <div class="ingredient-item">
                                        <p class="ingredient-name">${ingredient.getIngredientName()}</p>
                                        <a href="" class="checkout-ingredient">CHECK OUT</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="step-list">
                            <h3 class="step-list-heading">DIRECTIONS</h3>
                            <div class="step-detail">
                                <c:forEach var="step" items="${listStep}">
                                    <div class="step-item">
                                        <p class="step-number">STEP ${step.getStepPosition()}</p>
                                        <p class="step-description">${step.getStepDescription()}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="comment-section">
                            <h3 class="step-list-heading">REVIEWS</h3>
                            <p class="guide-description">Check out our <a href="" class="author-redirect">FAQS</a> about
                                reviews guidelines and rules.</p>
                            <button class="add-review js-buy">Add Your Reviews</button>
                            <div class="comment-detail">

                                <c:forEach var="cmt" items="${listCmt}">
                                    <div class="comment-item">
                                        <c:if test="${empty cmt.getUserImg()}">
                                            <c:set var="userImg" value="img/userdefault.jpg"/>
                                        </c:if>
                                        <c:if test="${not empty cmt.getUserImg()}">
                                            <c:set var="userImg" value="${cmt.getUserImg()}"/>
                                        </c:if>
                                        <div class="cmt-author-container">
                                            <img class="cmt-img" src="${userImg}" alt="">
                                            <a href="" class="author-cmt">${cmt.getUserName()}</a>
                                        </div>
                                        <div class="rating-rate-comment">
                                            <c:forEach begin="1" end="${cmt.getRatingPoint()}">
                                                <i class="fa fa-star checked"></i>
                                            </c:forEach>
                                            <c:forEach begin="1" end="${5 - cmt.getRatingPoint()}">
                                                <i class="fa fa-star"></i>
                                            </c:forEach>
                                        </div>
                                        <p class="comment-description">${cmt.getCmtDescription()}</p>
                                    </div> 
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="shadow js-shadow">

        </div>

        <!-- Modal here -->
        <div class="modal js-modal">
            <div class="modal-container js-container">

                <div class="modal-header">
                    <div class="modal-close js-close">
                        <i class="fa fa-times"></i>
                    </div>
                    <div class="modal-heading">
                        <i class="fa fa-commenting-o"></i>
                        Review this recipe
                    </div>
                </div>

                <div class="modal-body">
                    <form action="MainController" method="post">
                        <label for="" class="modal-label">
                            <i class="ti-shopping-cart"></i>
                            Rating:
                        </label>
                        <div class="rating">
                            <label>
                                <input type="radio" name="stars" value="1" />
                                <span class="icon">★</span>
                            </label>
                            <label>
                                <input type="radio" name="stars" value="2" />
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                            </label>
                            <label>
                                <input type="radio" name="stars" value="3" />
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>   
                            </label>
                            <label>
                                <input type="radio" name="stars" value="4" />
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                            </label>
                            <label>
                                <input type="radio" name="stars" value="5" />
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                                <span class="icon">★</span>
                            </label>
                        </div>

                        <label for="" class="modal-label">
                            <i class="ti-user"></i>
                            Your Review: 
                        </label>
                        <div class="enter-review">
                            <textarea name="text" id="mytextarea" class="review-input" cols="40" rows="5" placeholder="Enter your review..." maxlength="10"></textarea>
                        </div>
                        <input type="hidden" name="recipeId" value="${recipeRecipeDetail.getRecipeId()}">
                        <c:set var="user" value="${sessionScope.user}"/>
                        <input type="hidden" name="userId" value="${user.getUserId()}">

                        <button class="add-rv-btn" type="submit" value="Addreview" name="action">Add Your review</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <p class="modal-help">
                        Need <a href="#" class="help">help?</a>
                    </p>
                </div>
            </div>
        </div>

        <script src="js/modal.js"></script>

        <c:import url="Footer.jsp"/>
    </body>
</html>
