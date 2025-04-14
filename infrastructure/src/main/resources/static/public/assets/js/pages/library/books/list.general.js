let page = 1
const size = 10
let order = 'REGISTER_DATE'
let sort = 'DESC'

$(document).ready(() => {

    loadBooks(page, size, order, sort)

    let orderSelect = $('#order-select')

    orderSelect.select2({
        placeholder: 'Classificar por',
        theme: 'bootstrap4',
        minimumResultsForSearch: Infinity,
    })
    orderSelect.val(order).trigger('change')
    orderSelect.change(e => {
        order = $(e.target).val()
        loadBooks(page, size, order, sort)
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
        loadBooks(page, size, order, sort)
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

function generatePaginationElement(actualPage, pageSize, totalPageCount){
    // const paginationElementMaxPageCount = 5;
    $('#pagination-container').append(`
        <ul id="pagination-element" class="pagination justify-content-center"></ul>
    `)

    for(let i = 0; i < totalPageCount; i++){
        $('#pagination-element').append(`
                    <li class="page-item ${i + 1 === actualPage ? 'active' : ''}">
                        <button type="button" class="btn-link page-link" data-page-target="${i + 1}">${i + 1}</button>
                    </li>
        `)
    }

    $('#pagination-element').prepend(`
            <li class="page-item ${actualPage === 1 ? 'disabled' : ''}">
                <button type="button" class="page-link" data-page-target="${actualPage - 1}" aria-label="Anterior">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Anterior</span>
                </button>
            </li>
    `)

    $('#pagination-element').append(`
            <li class="page-item ${actualPage === totalPageCount ? 'disabled' : ''}">
                <button type="button" class="page-link" data-page-target="${actualPage + 1}" aria-label="Próximo">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Próximo</span>
                </button>
            </li>
    `)

    $('.page-link').click(e => {
        if($(e.target).parent().hasClass('active')){
            return
        }

        page = $(e.target).attr('data-page-target')
        loadBooks(page, size, order, sort)
    })
}

function loadBooks(page, size, order, sort){
    $.ajax({
        method: 'GET',
        url: `${window.location.origin}/api/library/books?page=${page}&size=${size}&order=${order}&sort=${sort}`,
        contentType: 'application/json',
        success: (data) => {
            console.log(data)

            $('#pagination-container').empty()
            if(data.totalPageCount > 1){
                generatePaginationElement(data.number, data.size, data.totalPageCount)
            }

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