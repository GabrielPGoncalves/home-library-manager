$('[data-toggle="popover"]').popover({
    content: function () {
        return $(this).siblings('.popover-content').html()
    },
    html: true,
    placement: 'top',
    trigger: 'focus'
})

// $('[data-toggle="popover"]').click(e => {
//     let element = $(e.target)
//     element.popover({
//         content: `
//                 <div class="popover-body d-flex flex-row justify-content-center">
//                         <a href="#" class="mr-3"><i class="fas fa-pen"></i></a>
//                         <a href="#" class="text-danger"><i class="fas fa-trash"></i></a>
//                 </div>
//               `,
//         html: true,
//         placement: 'top',
//         trigger: 'focus'
//     })
//     element.popover('toggle')
// })

/*
template: `<div class="popover" role="tooltip">
                        <div class="arrow"></div>
                        <h3 class="popover-header">Mais Opções</h3>
                        <div class="popover-body">
                                <a th:href="@{/library/books/{id}/edit(id=${book.id})}" class="mr-3"><i class="fas fa-pen"></i></a>
                                <a th:href="@{/library/books/{id}/delete(id=${book.id})}" class="text-danger"><i class="fas fa-trash"></i></a>
                        </div>
                    </div>`

 */
