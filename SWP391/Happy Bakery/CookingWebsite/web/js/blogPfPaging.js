let perPageBlog = 5;
let idPageBlog = 1;
let startBlog = 0;
let endBlog = perPageBlog;

let BlogArr = window.sessionStorage.getItem('BlogArray');
let BlogPfArr = JSON.parse(BlogArr);
console.log(BlogPfArr)
let productArrBlog = [];
productArrBlog = BlogPfArr;

const countTotalPageBlog = document.querySelector('.total-page-blog');
const countTotalProductBlog = document.querySelector('.total-item-blog');
let totalPagesBlog = Math.ceil(productArrBlog.length / perPageBlog);

function initRender(productAr, totalPage) {
    renderProduct(productAr);
    renderListPage(totalPage);
}

initRender(productArrBlog, totalPagesBlog);

function getCurrentPage(indexPage) {
    startBlog = (indexPage - 1) * perPageBlog;
    endBlog = indexPage * perPageBlog;
    totalPagesBlog = Math.ceil(productArrBlog.length / perPageBlog);
    countTotalPageBlog.innerHTML = `Total pages: ${totalPagesBlog}`;
    countTotalProductBlog.innerHTML = `Total Product:  ${productArrBlog.length}`
}

getCurrentPage(1);

function renderProduct(product) {
    html = '';
    const content = product.map((item, index) => {
        if (index >= startBlog && index < endBlog) {
            html += '<div class="blog-item-pf">';
            html += '<img class="blog-img" src="' + item.img + '">';
            html += '<div class="blog-pf-body">';
            html += '<a href="getBlogDetail?blogId=' + item.id + '" class="blog-name">' + item.title + '</a>';
            html += '<div>';
            for (var i = 0; i < item.rating; i++) {
                html += '<span class="fa fa-star checked"></span>';
            }
            for (var i = 0; i < (5 - item.rating); i++) {
                html += '<span class="fa fa-star"></span>';
            }
            html += '</div>';
            html += '<p class="blog-more">' + item.type + '</p>';
            html += '<p class="blog-more">' + item.postday + '</p>';
            html += '<p class="blog-more">' + item.note + '</p>';
            html += '<a href="getUserProfile?checkedUserId=' + item.userid + '" class="author">' + item.username + '</a>';
            html += '</div>';
            if (item.loginedid === item.userid) {
                html += '<a href="" class="blog-delete"><i class="fa fa-trash"></i></a>';
                html += '<a href="" class="blog-update"><i class="fa fa-wrench"></i></a>';
            }
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
        html += `<li class="current-page active-blog btn-show-blog"><a>${1}</a></li>`;
        for (let i = 2; i <= totalPages; i++) {
            html += `<li class="btn-hide-blog"><a>${i}</a></li>`;
        }
    }
    if (totalPages == 2) {
        html += `<li class="current-page active-blog btn-show-blog"><a>${1}</a></li>`;
        html += `<li class="btn-show-blog"><a>${2}</a></li>`;
        for (let i = 3; i <= totalPages; i++) {
            html += `<li class="btn-hide-blog"><a>${i}</a></li>`;
        }
    }
    if (totalPages >= 3) {
        html += `<li class="current-page active-blog btn-show-blog"><a>${1}</a></li>`;
        html += `<li class="btn-show-blog"><a>${2}</a></li>`;
        html += `<li class="btn-show-blog"><a>${3}</a></li>`;
        for (let i = 4; i <= totalPages; i++) {
            html += `<li class="btn-hide-blog"><a>${i}</a></li>`;
        }
    }
    if (totalPages === 0) {
        html = ''
    }
    document.getElementById('number-page-blog').innerHTML = html;
}

const availPagesBlog = document.getElementsByClassName('btn-show-blog');
console.log(availPagesBlog)

//Ham nay, chinh khi bam doi trang se nhay ra
function changePage() {
    const idPages = document.querySelectorAll('.number-page-blog li');
    const a = document.querySelectorAll('.number-page-blog li a');
    for (let i = 0; i < idPages.length; i++) {

        idPages[i].onclick = function () {
            let value = i + 1;
            const current = document.getElementsByClassName('active-blog');
            current[0].className = current[0].className.replace('active-blog', '');
            this.classList.add('active-blog');
            if (value > 1 && value < idPages.length) {
                $('.btn-prev-blog').removeClass('btn-active-blog');
                $('.btn-next-blog').removeClass('btn-active-blog');
            }
            if (value == 1) {
                $('.btn-prev-blog').addClass('btn-active-blog');
                $('.btn-next-blog').removeClass('btn-active-blog');
            }
            if (value == idPages.length) {
                $('.btn-next-blog').addClass('btn-active-blog');
                $('.btn-prev-blog').removeClass('btn-active-blog');
            }
            for (let index = 0; index < availPagesBlog.length; index++) {
                availPagesBlog[index].classList.add('btn-hide-blog');
                availPagesBlog[index].classList.remove('btn-show-blog');
            }

            if (value > 1 && value < idPages.length) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i + 1].classList.add('btn-show-blog');
                idPages[i - 1].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
                idPages[i + 1].classList.remove('btn-hide-blog');
                idPages[i - 1].classList.remove('btn-hide-blog');
            }
            if (value == 1 && totalPagesBlog == 1) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
            }
            if (value == 1 && totalPagesBlog == 2) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i + 1].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
                idPages[i + 1].classList.remove('btn-hide-blog');
            }
            if (value == 1 && totalPagesBlog >= 3) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i + 1].classList.add('btn-show-blog');
                idPages[i + 2].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
                idPages[i + 1].classList.remove('btn-hide-blog');
                idPages[i + 2].classList.remove('btn-hide-blog');
            }
            if (value == idPages.length && totalPagesBlog == 1) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
            }
            if (value == idPages.length && totalPagesBlog == 2) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i - 1].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
                idPages[i - 1].classList.remove('btn-hide-blog');
            }
            if (value == idPages.length && totalPagesBlog >= 3) {
                idPages[i].classList.add('btn-show-blog');
                idPages[i - 1].classList.add('btn-show-blog');
                idPages[i - 2].classList.add('btn-show-blog');
                idPages[i].classList.remove('btn-hide-blog');
                idPages[i - 1].classList.remove('btn-hide-blog');
                idPages[i - 2].classList.remove('btn-hide-blog');
            }
            idPageBlog = value;
            getCurrentPage(idPageBlog);
            renderProduct(productArrBlog);
        };
    }
}

changePage();

// Chinh hai ham sua list se thay doi khi bam icon chuyen
$('.btn-next-blog').on('click', () => {
    idPageBlog++;
    if (idPageBlog > totalPagesBlog) {
        idPageBlog = totalPagesBlog;
    }
    if (idPageBlog == totalPagesBlog) {
        $('.btn-next-blog').addClass('btn-active-blog');
    } else {
        $('.btn-next-blog').removeClass('btn-active-blog');
    }
    console.log(idPageBlog);
    const btnPrev = document.querySelector('.btn-prev-blog');
    btnPrev.classList.remove('btn-active-blog');
    $('.number-page-blog li').removeClass('active-blog');
    $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('active-blog');

    for (let index = 0; index < availPagesBlog.length; index++) {
        availPagesBlog[index].classList.add('btn-hide-blog');
        availPagesBlog[index].classList.remove('btn-show-blog');
    }
    if (idPageBlog == totalPagesBlog) {
        $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog - 2})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog - 3})`).addClass('btn-show-blog');
    } else {
        $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog - 2})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog})`).addClass('btn-show-blog');
    }

    getCurrentPage(idPageBlog);
    renderProduct(productArrBlog);
});

$('.btn-prev-blog').on('click', () => {
    idPageBlog--;
    if (idPageBlog <= 0) {
        idPageBlog = 1;
    }
    if (idPageBlog == 1) {
        $('.btn-prev-blog').addClass('btn-active-blog');
    } else {
        $('.btn-prev-blog').removeClass('btn-active-blog');
    }
    const btnNext = document.querySelector('.btn-next-blog');
    btnNext.classList.remove('btn-active-blog');
    $('.number-page-blog li').removeClass('active-blog');
    $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('active-blog');

    for (let index = 0; index < availPagesBlog.length; index++) {
        availPagesBlog[index].classList.add('btn-hide-blog');
        availPagesBlog[index].classList.remove('btn-show-blog');
    }

    if (idPageBlog == 1) {
        $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog + 1})`).addClass('btn-show-blog');
    } else {
        $(`.number-page-blog li:eq(${idPageBlog - 1})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog - 2})`).addClass('btn-show-blog');
        $(`.number-page-blog li:eq(${idPageBlog})`).addClass('btn-show-blog');
    }
    getCurrentPage(idPageBlog);
    renderProduct(productArrBlog);
});




