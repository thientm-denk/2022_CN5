window.onscroll = function () { myFunction() };

var navbar = document.getElementById("nav-contain");
var sticky = navbar.offsetTop;

function myFunction() {
  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky-part")
  } else {
    navbar.classList.remove("sticky-part");
  }
}

