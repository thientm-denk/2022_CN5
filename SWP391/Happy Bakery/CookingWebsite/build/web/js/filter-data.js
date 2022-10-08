filterSelection("recipe-new")
function filterSelection(c) {
    var x, i;
    x = document.getElementsByClassName("filterDiv");
    if (c == "recipe-new") c = "recipe-new";
    for (i = 0; i < x.length; i++) {
        w3RemoveClass(x[i], "show-filter");
        if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show-filter");
    }
}

filterSelectionStore("store-new")
function filterSelectionStore(c) {
    var x, i;
    x = document.getElementsByClassName("filterDiv");
    if (c == "store-new") c = "store-new";
    for (i = 0; i < x.length; i++) {
        w3RemoveClass(x[i], "show-filter-store");
        if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show-filter-store");
    }
}

filterSelectionBlog("blog-new")
function filterSelectionBlog(c) {
    var x, i;
    x = document.getElementsByClassName("filterDiv");
    if (c == "blog-new") c = "blog-new";
    for (i = 0; i < x.length; i++) {
        w3RemoveClass(x[i], "show-filter-blog");
        if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show-filter-blog");
    }
}

function w3AddClass(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
        if (arr1.indexOf(arr2[i]) == -1) { element.className += " " + arr2[i]; }
    }
}

function w3RemoveClass(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
        while (arr1.indexOf(arr2[i]) > -1) {
            arr1.splice(arr1.indexOf(arr2[i]), 1);
        }
    }
    element.className = arr1.join(" ");
}

// Add active class to the current button (highlight it)
var btnContainer = document.getElementById("myBtnContainer");
var btns = btnContainer.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active-filter");
        current[0].className = current[0].className.replace(" active-filter", " ");
        this.className += "active-filter";
    });
}

// Add active class to the current button (highlight it)
var btnContainer = document.getElementById("myBtnContainerStore");
var btns = btnContainer.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active-filter-store");
        current[0].className = current[0].className.replace("active-filter-store", " ");
        this.className += "active-filter-store";
    });
}

// Add active class to the current button (highlight it)
var btnContainer = document.getElementById("myBtnContainerBlog");
var btns = btnContainer.getElementsByClassName("btn");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active-filter-blog");
        current[0].className = current[0].className.replace("active-filter-blog", " ");
        this.className += "active-filter-blog";
    });
}