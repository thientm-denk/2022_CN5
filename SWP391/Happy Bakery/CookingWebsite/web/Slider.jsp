<%-- 
    Document   : Slider
    Created on : Sep 17, 2022, 3:55:46 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/default.css">
        <link rel="stylesheet" href="css/mycss.css">
    </head>
    <body>
        <div class="slideshow-container">

            <div class="mySlides fade">
                <div class="numbertext">1 / 3</div>
                <img src="img/banner.jpg" style="width:100%">
                <div class="text">
                    <h2 class="slider-heading">Welcome To Happy Bakery</h2>
                    <p class="slider-description">Explore newest Recipes</p>
                    <a class="slider-redirect" href="#">EXPLORE NOW</a>
                </div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">2 / 3</div>
                <img src="img/banner.jpg" style="width:100%">
                <div class="text">
                    <h2 class="slider-heading">BLOG</h2>
                    <p class="slider-description">Useful And Interesting</p>
                    <a class="slider-redirect" href="#">EXPLORE NOW</a>
                </div>
            </div>

            <div class="mySlides fade">
                <div class="numbertext">3 / 3</div>
                <img src="img/banner.jpg" style="width:100%">
                <div class="text">
                    <h2 class="slider-heading">STORE</h2>
                    <p class="slider-description">All Sales Available</p>
                    <a class="slider-redirect" href="#">EXPLORE NOW</a>
                </div>
            </div>

            <a class="prev" onclick="plusSlides(-1)">❮</a>
            <a class="next" onclick="plusSlides(1)">❯</a>

            <div class="list-dot">
                <span class="dot" onclick="currentSlide(1)"></span>
                <span class="dot" onclick="currentSlide(2)"></span>
                <span class="dot" onclick="currentSlide(3)"></span>
            </div>
        </div>

        <script src="js/slider.js"></script>
    </body>
</html>
