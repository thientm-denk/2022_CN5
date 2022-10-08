function submit() {
    document.getElementById("processing").addEventListener("click", function (e) {
        sessionStorage.setItem("submitted", "true");
    }, false);
    document.getElementById("delivering").addEventListener("click", function (e) {
        sessionStorage.setItem("submitted", "true");
    }, false);
    document.getElementById("completed").addEventListener("click", function (e) {
        sessionStorage.setItem("submitted", "true");
    }, false);
    if (!sessionStorage.getItem("submitted")) {
        console.log("submitting");
    } else {
        document.getElementById("click-order").click();
        sessionStorage.removeItem("submitted");
    }
}   