let perPage = 5;
let idPage = 1;
let start = 0;
let end = perPage;

let BlogArr = window.sessionStorage.getItem('BlogArray');
let blogPageArr = JSON.parse(BlogArr);
console.log(blogPageArr)
let productArr = [];
productArr = blogPageArr;

const countTotalPage = document.querySelector('.total-page');
const countTotalProduct = document.querySelector('.total-item');
let totalPages = Math.ceil(productArr.length / perPage);

function initRender(productAr, totalPage) {
    renderProduct(productAr);
    renderListPage(totalPage);
}

initRender(productArr, totalPages);

function getCurrentPage(indexPage) {
    start = (indexPage - 1) * perPage;
    end = indexPage * perPage;
    totalPages = Math.ceil(productArr.length / perPage);
    countTotalPage.innerHTML = `Total pages: ${totalPages}`;
    countTotalProduct.innerHTML = `Total Product:  ${productArr.length}`
}

getCurrentPage(1);

function renderProduct(product) {
    html = '';
    const content = product.map((item, index) => {
        if (index >= start && index < end) {
            html += '<div class="store-item">';
            html += '<img class="store-img" src="'+ item.img +'">';
            html += '<div class="store-body">';
            html += '<a href="GetBlogDetail?blogId='+ item.id +'" class="store-name">'+ item.title +'</a>';
            html += '<div>';
            for (var i = 0; i < item.rating; i++) {
                html += '<span class="fa fa-star checked"></span>'; 
            }
            for (var i = 0; i < (5 - item.rating); i++) {
                html += '<span class="fa fa-star"></span>'; 
            }
            html += '</div>';
            html += '<p class="store-more">'+ item.type +'</p>';
            html += '<p class="store-more">'+ item.postday +'</p>';
            html += '<p class="store-more">'+ item.note +'</p>';
            html += '<a href="getUserProfile?checkedUserId='+ item.userid +'" class="author">'+ item.username +'</a>'
            html += '</div>';
            html += '</div>';
            return html;
        }
    });
    document.getElementById('blog-container').innerHTML = html;
}
//Ham nay, chi cho phep show ra 3 cai
function renderListPage(totalPages) {
    let html = '';
    if (totalPages == 1) {
        html += `<li class="current-page active btn-show"><a>${1}</a></li>`;
        for (let i = 2; i <= totalPages; i++) {
            html += `<li class="btn-hide"><a>${i}</a></li>`;
        }
    }
    if (totalPages == 2) {
        html += `<li class="current-page active btn-show"><a>${1}</a></li>`;
        html += `<li class="btn-show"><a>${2}</a></li>`;
        for (let i = 3; i <= totalPages; i++) {
            html += `<li class="btn-hide"><a>${i}</a></li>`;
        }
    }
    if (totalPages >= 3) {
        html += `<li class="current-page active btn-show"><a>${1}</a></li>`;
        html += `<li class="btn-show"><a>${2}</a></li>`;
        html += `<li class="btn-show"><a>${3}</a></li>`;
        for (let i = 4; i <= totalPages; i++) {
            html += `<li class="btn-hide"><a>${i}</a></li>`;
        }
    }
    if (totalPages === 0) {
        html = ''
    }
    document.getElementById('number-page').innerHTML = html;
}

const availPages = document.getElementsByClassName('btn-show');
console.log(availPages)

//Ham nay, chinh khi bam doi trang se nhay ra
function changePage() {
    const idPages = document.querySelectorAll('.number-page li');
    const a = document.querySelectorAll('.number-page li a');
    for (let i = 0; i < idPages.length; i++) {

        idPages[i].onclick = function () {
            let value = i + 1;
            const current = document.getElementsByClassName('active');
            current[0].className = current[0].className.replace('active', '');
            this.classList.add('active');
            if (value > 1 && value < idPages.length) {
                $('.btn-prev').removeClass('btn-active');
                $('.btn-next').removeClass('btn-active');
            }
            if (value == 1) {
                $('.btn-prev').addClass('btn-active');
                $('.btn-next').removeClass('btn-active');
            }
            if (value == idPages.length) {
                $('.btn-next').addClass('btn-active');
                $('.btn-prev').removeClass('btn-active');
            }
            for (let index = 0; index < availPages.length; index++) {
                availPages[index].classList.add('btn-hide');
                availPages[index].classList.remove('btn-show');
            }

            if (value > 1 && value < idPages.length) {
                idPages[i].classList.add('btn-show');
                idPages[i + 1].classList.add('btn-show');
                idPages[i - 1].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
                idPages[i + 1].classList.remove('btn-hide');
                idPages[i - 1].classList.remove('btn-hide');
            }
            if (value == 1 && totalPages == 1) {
                idPages[i].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
            }
            if (value == 1 && totalPages == 2) {
                idPages[i].classList.add('btn-show');
                idPages[i + 1].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
                idPages[i + 1].classList.remove('btn-hide');
            }
            if (value == 1 && totalPages >= 3) {
                idPages[i].classList.add('btn-show');
                idPages[i + 1].classList.add('btn-show');
                idPages[i + 2].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
                idPages[i + 1].classList.remove('btn-hide');
                idPages[i + 2].classList.remove('btn-hide');
            }
            if (value == idPages.length && totalPages == 1) {
                idPages[i].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
            }
            if (value == idPages.length && totalPages == 2) {
                idPages[i].classList.add('btn-show');
                idPages[i - 1].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
                idPages[i - 1].classList.remove('btn-hide');
            }
            if (value == idPages.length && totalPages >= 3) {
                idPages[i].classList.add('btn-show');
                idPages[i - 1].classList.add('btn-show');
                idPages[i - 2].classList.add('btn-show');
                idPages[i].classList.remove('btn-hide');
                idPages[i - 1].classList.remove('btn-hide');
                idPages[i - 2].classList.remove('btn-hide');
            }
            idPage = value;
            getCurrentPage(idPage);
            renderProduct(productArr);
        };
    }
}

changePage();

// Chinh hai ham sua list se thay doi khi bam icon chuyen
$('.btn-next').on('click', () => {
    idPage++;
    if (idPage > totalPages) {
        idPage = totalPages;
    }
    if (idPage == totalPages) {
        $('.btn-next').addClass('btn-active');
    } else {
        $('.btn-next').removeClass('btn-active');
    }
    console.log(idPage);
    const btnPrev = document.querySelector('.btn-prev');
    btnPrev.classList.remove('btn-active');
    $('.number-page li').removeClass('active');
    $(`.number-page li:eq(${idPage - 1})`).addClass('active');

    for (let index = 0; index < availPages.length; index++) {
        availPages[index].classList.add('btn-hide');
        availPages[index].classList.remove('btn-show');
    }
    if (idPage == totalPages) {
        $(`.number-page li:eq(${idPage - 1})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage - 2})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage - 3})`).addClass('btn-show');
    } else {
        $(`.number-page li:eq(${idPage - 1})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage - 2})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage})`).addClass('btn-show');
    }

    getCurrentPage(idPage);
    renderProduct(productArr);
});

$('.btn-prev').on('click', () => {
    idPage--;
    if (idPage <= 0) {
        idPage = 1;
    }
    if (idPage == 1) {
        $('.btn-prev').addClass('btn-active');
    } else {
        $('.btn-prev').removeClass('btn-active');
    }
    const btnNext = document.querySelector('.btn-next');
    btnNext.classList.remove('btn-active');
    $('.number-page li').removeClass('active');
    $(`.number-page li:eq(${idPage - 1})`).addClass('active');

    for (let index = 0; index < availPages.length; index++) {
        availPages[index].classList.add('btn-hide');
        availPages[index].classList.remove('btn-show');
    }

    if (idPage == 1) {
        $(`.number-page li:eq(${idPage - 1})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage + 1})`).addClass('btn-show');
    } else {
        $(`.number-page li:eq(${idPage - 1})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage - 2})`).addClass('btn-show');
        $(`.number-page li:eq(${idPage})`).addClass('btn-show');
    }
    getCurrentPage(idPage);
    renderProduct(productArr);
});


