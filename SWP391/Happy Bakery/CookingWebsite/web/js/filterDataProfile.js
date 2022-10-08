filterSelectionPfStore("information-table");
function filterSelectionPfStore(c) {
    var x, i;
    x = document.getElementsByClassName("filterDivPf");
    if (c == "information-table") c = "information-table";
    for (i = 0; i < x.length; i++) {
        w3RemoveClass(x[i], "show-filter-pf");
        if (x[i].className.indexOf(c) > -1) w3AddClass(x[i], "show-filter-pf");
    }
}

function w3AddClass(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
        if (arr1.indexOf(arr2[i]) == -1) { 
            element.className += " " + arr2[i]; 
        }
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
var btnContainer = document.getElementById("myBtnContainerPf");
var btns = btnContainer.getElementsByClassName("btn-pf");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active-filter-pf");
        current[0].className = current[0].className.replace("active-filter-pf", " ");
        this.className += "active-filter-pf";
    });
}