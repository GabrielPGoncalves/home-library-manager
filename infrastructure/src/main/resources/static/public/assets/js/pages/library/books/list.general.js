$(document).ready(() => {
    let order = 'REGISTER_DATE'
    let sort = 'DESC'

    loadBooks(order, sort)

    let orderSelect = $('#order-select')

    orderSelect.select2({
        placeholder: 'Classificar por',
        theme: 'bootstrap4',
        minimumResultsForSearch: Infinity,
    })
    orderSelect.val(order).trigger('change')
    orderSelect.change(e => {
        order = $(e.target).val()
        loadBooks(order, sort)
    })

    let sortSelect = $('#sort-select')

    sortSelect.select2({
        placeholder: 'Ordenação',
        theme: 'bootstrap4',
        minimumResultsForSearch: Infinity
    })
    sortSelect.val(sort).trigger('change')
    sortSelect.change(e => {
        sort = $(e.target).val()
        loadBooks(order, sort)
    })
})

function generateBookElement(bookData){
    let bookContainer = $(`
        <div class="col-lg-6">
            <div class="card card-default book-card">
                <div class="card-body d-flex flex-sm-row flex-column">
                    <div class="mr-sm-2 mb-2">
                        <img src="${bookData.coverImage === null ? '' : 'data:image/jpeg;base64,' + bookData.coverImage}" alt="" class="book-cover">
                    </div>
                    <div class="w-100 mb-sm-0 mb-2">
                        <h5 class="card-title mb-1">${bookData.title}</h5>
                        <p class="card-text text-muted">${bookData.author}</p>
                    </div>
                    <div class="d-flex w-100 justify-content-sm-end align-items-center text-lg">
                        <button type="button" class="btn btn-link text-secondary" data-toggle="popover"><i class="fas fa-ellipsis-v"></i></button>
                        <div class="d-none popover-content">
                            <div class="popover-body d-flex flex-row justify-content-center">
                                <a href="${window.location.origin}/library/books/${bookData.id}/edit" class="mr-3"><i class="fas fa-pen"></i></a>
                                <a href="${window.location.origin}/library/books/${bookData.id}/delete" class="text-danger"><i class="fas fa-trash"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
    )

    $('#book-list').append(bookContainer)
}

function activateBookElementsPopovers(){
    $('[data-toggle="popover"]').popover({
        content: function () {
            return $(this).siblings('.popover-content').html()
        },
        html: true,
        placement: 'top',
        trigger: 'focus'
    })
}

function disposeBookElementsPopovers(){
    $('[data-toggle="popover"]').popover('dispose')
}

function loadBooks(order, sort){
    $.ajax({
        method: 'GET',
        url: `${window.location.origin}/api/library/books?page=1&size=10&order=${order}&sort=${sort}`,
        contentType: 'application/json',
        success: (data) => {
            let bookListSelector = $('#book-list')

            if(!bookListSelector.is(':empty')){
                disposeBookElementsPopovers()
                bookListSelector.empty()
            }

            data.content.forEach(generateBookElement)
            activateBookElementsPopovers()
        }
    })
}