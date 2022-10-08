let perPageRecipe = 9;
let idPageRecipe = 1;
let startRecipe = 0;
let endRecipe = perPageRecipe;

let RecipeArr = window.sessionStorage.getItem('recipeArray');
let RecipePfArr = JSON.parse(RecipeArr);
console.log(RecipePfArr)
let productArrRecipe = [];
productArrRecipe = RecipePfArr;

const countTotalPageRecipe = document.querySelector('.total-page-recipe');
const countTotalProductRecipe = document.querySelector('.total-item-recipe');
let totalPagesRecipe = Math.ceil(productArrRecipe.length / perPageRecipe);

function initRender(productAr, totalPage) {
    renderProduct(productAr);
    renderListPage(totalPage);
}

initRender(productArrRecipe, totalPagesRecipe);

function getCurrentPage(indexPage) {
    startRecipe = (indexPage - 1) * perPageRecipe;
    endRecipe = indexPage * perPageRecipe;
    totalPagesRecipe = Math.ceil(productArrRecipe.length / perPageRecipe);
    countTotalPageRecipe.innerHTML = `Total pages: ${totalPagesRecipe}`;
    countTotalProductRecipe.innerHTML = `Total Product:  ${productArrRecipe.length}`
}

getCurrentPage(1);

function renderProduct(product) {
    html = '';
    const content = product.map((item, index) => {
        if (index >= startRecipe && index < endRecipe) {
            html += '<div class="recipe-item">';
            html += '<img class="recipe-img" src="' + item.img + '">';
            html += '<div class="recipe-body">';
            html += '<a href="' + item.id + '" class="recipe-name">' + item.name + '</a>';
            html += '<p class="recipe-type">' + item.type + '</p>';
            html += '<p class="recipe-more">Post day: ' + item.postday + '</p>';
            html += '<a class="recipe-more">by <a class="recipe-author" href="' + item.userid + '">' + item.username + '</a></a>';
            html += '<div class="recipe-rating">';
            for (var i = 0; i < item.rating; i++) {
                html += '<span class="fa fa-star checked"></span>'; 
            }
            for (var i = 0; i < (5 - item.rating); i++) {
                html += '<span class="fa fa-star"></span>'; 
            }
            html += '<p class="pp-rating">(' + item.numberrating + '<i class="fas fa-user"></i> )</p>';
            html += '</div>';
            html += '</div>';
            if (item.loginedid === item.userid) {
                html += '<a href="" class="recipe-delete"><i class="fa fa-trash"></i></a>';
                html += '<a href="" class="recipe-update"><i class="fa fa-wrench"></i></a>';
            }
            html += '</div>';
            return html;
        }
    });
    document.getElementById('recipe-container').innerHTML = html;
}
//Ham nay, chi cho phep show ra 3 cai
function renderListPage(totalPages) {
    let html = '';
    if (totalPages == 1) {
        html += `<li class="current-page active-recipe btn-show-recipe"><a>${1}</a></li>`;
        for (let i = 2; i <= totalPages; i++) {
            html += `<li class="btn-hide-recipe"><a>${i}</a></li>`;
        }
    }
    if (totalPages == 2) {
        html += `<li class="current-page active-recipe btn-show-recipe"><a>${1}</a></li>`;
        html += `<li class="btn-show-recipe"><a>${2}</a></li>`;
        for (let i = 3; i <= totalPages; i++) {
            html += `<li class="btn-hide-recipe"><a>${i}</a></li>`;
        }
    }
    if (totalPages >= 3) {
        html += `<li class="current-page active-recipe btn-show-recipe"><a>${1}</a></li>`;
        html += `<li class="btn-show-recipe"><a>${2}</a></li>`;
        html += `<li class="btn-show-recipe"><a>${3}</a></li>`;
        for (let i = 4; i <= totalPages; i++) {
            html += `<li class="btn-hide-recipe"><a>${i}</a></li>`;
        }
    }
    if (totalPages === 0) {
        html = ''
    }
    document.getElementById('number-page-recipe').innerHTML = html;
}

const availPagesRecipe = document.getElementsByClassName('btn-show-recipe');
console.log(availPagesRecipe)

//Ham nay, chinh khi bam doi trang se nhay ra
function changePage() {
    const idPages = document.querySelectorAll('.number-page-recipe li');
    const a = document.querySelectorAll('.number-page-recipe li a');
    for (let i = 0; i < idPages.length; i++) {

        idPages[i].onclick = function () {
            let value = i + 1;
            const current = document.getElementsByClassName('active-recipe');
            current[0].className = current[0].className.replace('active-recipe', '');
            this.classList.add('active-recipe');
            if (value > 1 && value < idPages.length) {
                $('.btn-prev-recipe').removeClass('btn-active-recipe');
                $('.btn-next-recipe').removeClass('btn-active-recipe');
            }
            if (value == 1) {
                $('.btn-prev-recipe').addClass('btn-active-recipe');
                $('.btn-next-recipe').removeClass('btn-active-recipe');
            }
            if (value == idPages.length) {
                $('.btn-next-recipe').addClass('btn-active-recipe');
                $('.btn-prev-recipe').removeClass('btn-active-recipe');
            }
            for (let index = 0; index < availPagesRecipe.length; index++) {
                availPagesRecipe[index].classList.add('btn-hide-recipe');
                availPagesRecipe[index].classList.remove('btn-show-recipe');
            }

            if (value > 1 && value < idPages.length) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i + 1].classList.add('btn-show-recipe');
                idPages[i - 1].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
                idPages[i + 1].classList.remove('btn-hide-recipe');
                idPages[i - 1].classList.remove('btn-hide-recipe');
            }
            if (value == 1 && totalPagesRecipe == 1) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
            }
            if (value == 1 && totalPagesRecipe == 2) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i + 1].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
                idPages[i + 1].classList.remove('btn-hide-recipe');
            }
            if (value == 1 && totalPagesRecipe >= 3) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i + 1].classList.add('btn-show-recipe');
                idPages[i + 2].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
                idPages[i + 1].classList.remove('btn-hide-recipe');
                idPages[i + 2].classList.remove('btn-hide-recipe');
            }
            if (value == idPages.length && totalPagesRecipe == 1) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
            }
            if (value == idPages.length && totalPagesRecipe == 2) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i - 1].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
                idPages[i - 1].classList.remove('btn-hide-recipe');
            }
            if (value == idPages.length && totalPagesRecipe >= 3) {
                idPages[i].classList.add('btn-show-recipe');
                idPages[i - 1].classList.add('btn-show-recipe');
                idPages[i - 2].classList.add('btn-show-recipe');
                idPages[i].classList.remove('btn-hide-recipe');
                idPages[i - 1].classList.remove('btn-hide-recipe');
                idPages[i - 2].classList.remove('btn-hide-recipe');
            }
            idPageRecipe = value;
            getCurrentPage(idPageRecipe);
            renderProduct(productArrRecipe);
        };
    }
}

changePage();

// Chinh hai ham sua list se thay doi khi bam icon chuyen
$('.btn-next-recipe').on('click', () => {
    idPageRecipe++;
    if (idPageRecipe > totalPagesRecipe) {
        idPageRecipe = totalPagesRecipe;
    }
    if (idPageRecipe == totalPagesRecipe) {
        $('.btn-next-recipe').addClass('btn-active-recipe');
    } else {
        $('.btn-next-recipe').removeClass('btn-active-recipe');
    }
    console.log(idPageRecipe);
    const btnPrev = document.querySelector('.btn-prev-recipe');
    btnPrev.classList.remove('btn-active-recipe');
    $('.number-page-recipe li').removeClass('active-recipe');
    $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('active-recipe');

    for (let index = 0; index < availPagesRecipe.length; index++) {
        availPagesRecipe[index].classList.add('btn-hide-recipe');
        availPagesRecipe[index].classList.remove('btn-show-recipe');
    }
    if (idPageRecipe == totalPagesRecipe) {
        $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe - 2})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe - 3})`).addClass('btn-show-recipe');
    } else {
        $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe - 2})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe})`).addClass('btn-show-recipe');
    }

    getCurrentPage(idPageRecipe);
    renderProduct(productArrRecipe);
});

$('.btn-prev-recipe').on('click', () => {
    idPageRecipe--;
    if (idPageRecipe <= 0) {
        idPageRecipe = 1;
    }
    if (idPageRecipe == 1) {
        $('.btn-prev-recipe').addClass('btn-active-recipe');
    } else {
        $('.btn-prev-recipe').removeClass('btn-active-recipe');
    }
    const btnNext = document.querySelector('.btn-next-recipe');
    btnNext.classList.remove('btn-active-recipe');
    $('.number-page-recipe li').removeClass('active-recipe');
    $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('active-recipe');

    for (let index = 0; index < availPagesRecipe.length; index++) {
        availPagesRecipe[index].classList.add('btn-hide-recipe');
        availPagesRecipe[index].classList.remove('btn-show-recipe');
    }

    if (idPageRecipe == 1) {
        $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe + 1})`).addClass('btn-show-recipe');
    } else {
        $(`.number-page-recipe li:eq(${idPageRecipe - 1})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe - 2})`).addClass('btn-show-recipe');
        $(`.number-page-recipe li:eq(${idPageRecipe})`).addClass('btn-show-recipe');
    }
    getCurrentPage(idPageRecipe);
    renderProduct(productArrRecipe);
});


