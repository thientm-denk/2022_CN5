const buyBtns = document.querySelectorAll('.js-buy')
const modal = document.querySelector('.js-modal')
const modalContainer = document.querySelector('.js-container')
const modalClose = document.querySelector('.js-close')
const shadow = document.querySelector('.js-shadow')

//Hàm hiển thị modal mua vé (thêm class open)
function showBuyTicket() {
    modal.classList.add('open')
    shadow.classList.add('open')
}

//Hàm ẩn modal mua vé (bỏ class open)
function hideBuyTicket() {
    modal.classList.remove('open')
    shadow.classList.remove('open')
}

//Lặp qua từng button để add listener vì có 3 button
for (const buyBtn of buyBtns ) {
    buyBtn.addEventListener('click', showBuyTicket)
}

modalClose.addEventListener('click', hideBuyTicket)

modal.addEventListener('click', hideBuyTicket)

// Câu lệnh bên dưới dùng để tránh việc khi ấn vào phần modal thì modal vẫn bị ẩn
modalContainer.addEventListener('click', function(event) {
    event.stopPropagation()
})