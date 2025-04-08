$('[data-toggle="popover"]').popover({
    content: function () {
        return $(this).siblings('.popover-content').html()
    },
    html: true,
    placement: 'top',
    trigger: 'focus'
})