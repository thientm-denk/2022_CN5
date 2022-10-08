let perPageOrder = 5;
let idPageOrder = 1;
let startOrder = 0;
let endOrder = perPageOrder;

let OrderArr = window.sessionStorage.getItem('orderArray');
let OrderPfArr = JSON.parse(OrderArr);
console.log(OrderPfArr)
let productArrOrder = [];
productArrOrder = OrderPfArr;

const countTotalPageOrder = document.querySelector('.total-page-order');
const countTotalProductOrder = document.querySelector('.total-item-order');
let totalPagesOrder = Math.ceil(productArrOrder.length / perPageOrder);

function initRender(productAr, totalPage) {
    renderProduct(productAr);
    renderListPage(totalPage);
}

initRender(productArrOrder, totalPagesOrder);

function getCurrentPage(indexPage) {
    startOrder = (indexPage - 1) * perPageOrder;
    endOrder = indexPage * perPageOrder;
    totalPagesOrder = Math.ceil(productArrOrder.length / perPageOrder);
    countTotalPageOrder.innerHTML = `Total pages: ${totalPagesOrder}`;
    countTotalProductOrder.innerHTML = `Total Product:  ${productArrOrder.length}`
}

getCurrentPage(1);

function renderProduct(product) {
    html = '';
    const content = product.map((item, index) => {
        if (index >= startOrder && index < endOrder) {
            html += '<div class="order-item">';
            html += '<img class="order-img" src="' + item.img + '">';
            html += '<div class="order-body">';
            html += '<a href="GetOrderDetail?orderId=' + item.id + '" class="order-name">Detail</a>';
            html += '<p class="order-type">Order Date: ' + item.orderdate + '</p>';
            html += '<p class="order-type">Ship Date: ' + item.shipdate + '</p>';
            if (item.status == 1) {
                html += '<p class="order-type">Status: Processing</p>';
            } else if (item.status == 2) {
                html += '<p class="order-type">Status: Delivering</p>'; 
            } else {
                html += '<p class="order-type">Status: Completed</p>';
            }
            if (item.checkstore == 'STORE') {
                html += '<a class="order-more">Order by <a class="order-author" href="' + item.userid + '">' + item.username + '</a></a>';
            } 
            html += '</div>';
            html += '</div>';
        }
    });
    document.getElementById('order-container').innerHTML = html;
}
//Ham nay, chi cho phep show ra 3 cai
function renderListPage(totalPages) {
    let html = '';
    if (totalPages == 1) {
        html += `<li class="current-page active-order btn-show-order"><a>${1}</a></li>`;
        for (let i = 2; i <= totalPages; i++) {
            html += `<li class="btn-hide-order"><a>${i}</a></li>`;
        }
    }
    if (totalPages == 2) {
        html += `<li class="current-page active-order btn-show-order"><a>${1}</a></li>`;
        html += `<li class="btn-show-order"><a>${2}</a></li>`;
        for (let i = 3; i <= totalPages; i++) {
            html += `<li class="btn-hide-order"><a>${i}</a></li>`;
        }
    }
    if (totalPages >= 3) {
        html += `<li class="current-page active-order btn-show-order"><a>${1}</a></li>`;
        html += `<li class="btn-show-order"><a>${2}</a></li>`;
        html += `<li class="btn-show-order"><a>${3}</a></li>`;
        for (let i = 4; i <= totalPages; i++) {
            html += `<li class="btn-hide-order"><a>${i}</a></li>`;
        }
    }
    if (totalPages === 0) {
        html = ''
    }
    document.getElementById('number-page-order').innerHTML = html;
}

const availPagesOrder = document.getElementsByClassName('btn-show-order');
console.log(availPagesOrder)

//Ham nay, chinh khi bam doi trang se nhay ra
function changePage() {
    const idPages = document.querySelectorAll('.number-page-order li');
    const a = document.querySelectorAll('.number-page-order li a');
    for (let i = 0; i < idPages.length; i++) {

        idPages[i].onclick = function () {
            let value = i + 1;
            const current = document.getElementsByClassName('active-order');
            current[0].className = current[0].className.replace('active-order', '');
            this.classList.add('active-order');
            if (value > 1 && value < idPages.length) {
                $('.btn-prev-order').removeClass('btn-active-order');
                $('.btn-next-order').removeClass('btn-active-order');
            }
            if (value == 1) {
                $('.btn-prev-order').addClass('btn-active-order');
                $('.btn-next-order').removeClass('btn-active-order');
            }
            if (value == idPages.length) {
                $('.btn-next-order').addClass('btn-active-order');
                $('.btn-prev-order').removeClass('btn-active-order');
            }
            for (let index = 0; index < availPagesOrder.length; index++) {
                availPagesOrder[index].classList.add('btn-hide-order');
                availPagesOrder[index].classList.remove('btn-show-order');
            }

            if (value > 1 && value < idPages.length) {
                idPages[i].classList.add('btn-show-order');
                idPages[i + 1].classList.add('btn-show-order');
                idPages[i - 1].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
                idPages[i + 1].classList.remove('btn-hide-order');
                idPages[i - 1].classList.remove('btn-hide-order');
            }
            if (value == 1 && totalPagesOrder == 1) {
                idPages[i].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
            }
            if (value == 1 && totalPagesOrder == 2) {
                idPages[i].classList.add('btn-show-order');
                idPages[i + 1].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
                idPages[i + 1].classList.remove('btn-hide-order');
            }
            if (value == 1 && totalPagesOrder >= 3) {
                idPages[i].classList.add('btn-show-order');
                idPages[i + 1].classList.add('btn-show-order');
                idPages[i + 2].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
                idPages[i + 1].classList.remove('btn-hide-order');
                idPages[i + 2].classList.remove('btn-hide-order');
            }
            if (value == idPages.length && totalPagesOrder == 1) {
                idPages[i].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
            }
            if (value == idPages.length && totalPagesOrder == 2) {
                idPages[i].classList.add('btn-show-order');
                idPages[i - 1].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
                idPages[i - 1].classList.remove('btn-hide-order');
            }
            if (value == idPages.length && totalPagesOrder >= 3) {
                idPages[i].classList.add('btn-show-order');
                idPages[i - 1].classList.add('btn-show-order');
                idPages[i - 2].classList.add('btn-show-order');
                idPages[i].classList.remove('btn-hide-order');
                idPages[i - 1].classList.remove('btn-hide-order');
                idPages[i - 2].classList.remove('btn-hide-order');
            }
            idPageOrder = value;
            getCurrentPage(idPageOrder);
            renderProduct(productArrOrder);
        };
    }
}

changePage();

// Chinh hai ham sua list se thay doi khi bam icon chuyen
$('.btn-next-order').on('click', () => {
    idPageOrder++;
    if (idPageOrder > totalPagesOrder) {
        idPageOrder = totalPagesOrder;
    }
    if (idPageOrder == totalPagesOrder) {
        $('.btn-next-order').addClass('btn-active-order');
    } else {
        $('.btn-next-order').removeClass('btn-active-order');
    }
    console.log(idPageOrder);
    const btnPrev = document.querySelector('.btn-prev-order');
    btnPrev.classList.remove('btn-active-order');
    $('.number-page-order li').removeClass('active-order');
    $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('active-order');

    for (let index = 0; index < availPagesOrder.length; index++) {
        availPagesOrder[index].classList.add('btn-hide-order');
        availPagesOrder[index].classList.remove('btn-show-order');
    }
    if (idPageOrder == totalPagesOrder) {
        $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder - 2})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder - 3})`).addClass('btn-show-order');
    } else {
        $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder - 2})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder})`).addClass('btn-show-order');
    }

    getCurrentPage(idPageOrder);
    renderProduct(productArrOrder);
});

$('.btn-prev-order').on('click', () => {
    idPageOrder--;
    if (idPageOrder <= 0) {
        idPageOrder = 1;
    }
    if (idPageOrder == 1) {
        $('.btn-prev-order').addClass('btn-active-order');
    } else {
        $('.btn-prev-order').removeClass('btn-active-order');
    }
    const btnNext = document.querySelector('.btn-next-order');
    btnNext.classList.remove('btn-active-order');
    $('.number-page-order li').removeClass('active-order');
    $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('active-order');

    for (let index = 0; index < availPagesOrder.length; index++) {
        availPagesOrder[index].classList.add('btn-hide-order');
        availPagesOrder[index].classList.remove('btn-show-order');
    }

    if (idPageOrder == 1) {
        $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder + 1})`).addClass('btn-show-order');
    } else {
        $(`.number-page-order li:eq(${idPageOrder - 1})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder - 2})`).addClass('btn-show-order');
        $(`.number-page-order li:eq(${idPageOrder})`).addClass('btn-show-order');
    }
    getCurrentPage(idPageOrder);
    renderProduct(productArrOrder);
});






