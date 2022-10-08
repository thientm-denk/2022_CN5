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
        <title>Blog Detail</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
        <link rel="stylesheet" href="css/detailBlog.css">
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

        <c:set var="listCmtBlog" value="${sessionScope.listCmtBlog}"/>
        <c:set var="blogBlogDetail" value="${sessionScope.blogBlogDetail}"/>
        <c:set var="userBlogDetail" value="${sessionScope.userBlogDetail}"/>

        <c:if test="${blogBlogDetail != null}">
            <c:url value = "GetBlogDetail" var = "myURL">
                <c:param name = "blogId" value = "${blogBlogDetail.getBlogId()}"/>
            </c:url>
            <c:import url = "${myURL}"/>
        </c:if>

        <div class="mapping grad">
            <p class="breadcrums"><a href="HomePage.jsp">Home</a>/<a href="Blog.jsp">Blog</a>/<a href="#">${blogBlogDetail.getBlogTitle()}</a></p>
            <h1 class="mapping-heading">Blog</h1>
        </div>
        <div class="container">
            <div class="blog-detail-container">
                <div class="blog-detail-heading">
                    <a href="#" class="blog-category">${blogBlogDetail.getBlogCategory()}</a>
                    <h3 class="blog-heading">${blogBlogDetail.getBlogTitle()}</h3>
                    <p class="blog-note">${blogBlogDetail.getBlogNote()}</p>
                    <div class="author-container">
                        <c:if test="${empty userBlogDetail.getUserImg()}">
                            <c:set var="authorImg" value="img/userdefault.jpg"/>
                        </c:if>
                        <c:if test="${not empty userBlogDetail.getUserImg()}">
                            <c:set var="authorImg" value="${userBlogDetail.getUserImg()}"/>
                        </c:if>
                        <img class="author-img" src="${authorImg}" alt="">
                        <p class="author-fullname">by <a href="getUserProfile?checkedUserId=${userBlogDetail.getUserId()}" class="author-redirect">${userBlogDetail.getUserName()}</a></p>
                    </div>
                </div>

                <div class="body-detail" id="body-detail">
                    <img class="body-img" src="${blogBlogDetail.getBlogImg()}" alt="">

                    <div class="body-description">${blogBlogDetail.getBlogDescription()}</div>


                    <div class="comment-section">
                        <h3 class="step-list-heading">REVIEWS</h3>
                        <p class="guide-description">Check out our <a href="" class="author-redirect">FAQS</a> about
                            reviews guidelines and rules.</p>
                        <div class="rating-container">
                            <p class="rating-heading">Blog rating:</p>
                            <div class="rating-rate">
                                <c:forEach begin="1" end="${blogBlogDetail.getBlogRatingPoint()}">
                                    <i class="fa fa-star checked"></i>
                                </c:forEach>
                                <c:forEach begin="1" end="${5 - blogBlogDetail.getBlogRatingPoint()}">
                                    <i class="fa fa-star"></i>
                                </c:forEach>
                            </div>
                            <p class="rating-number">Number of reviews: ${blogBlogDetail.getBlogNumberOfRating()}</p>
                        </div>
                        <button class="add-review js-buy">Add Your Reviews</button>
                        <div class="comment-detail">

                            <c:forEach var="cmt" items="${listCmtBlog}">
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
                    <input type="hidden" name="blogId" value="${blogBlogDetail.getBlogId()}">
                    <c:set var="user" value="${sessionScope.user}"/>
                    <input type="hidden" name="userId" value="${user.getUserId()}">

                    <input class="add-rv-btn" type="submit" value="Add" name="action">
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
