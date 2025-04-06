$('#read').change(event => {
    let readingEndDateSelector = $('#readingEndDate');

    readingEndDateSelector.prop('disabled', !$('#read').is(':checked'))
    readingEndDateSelector.text('')
    readingEndDateSelector.datetimepicker('clear')
})

$('#isbn').inputmask({
    mask: "9999999999[999]",
    showMaskOnHover: false,
    placeholder: ''
})