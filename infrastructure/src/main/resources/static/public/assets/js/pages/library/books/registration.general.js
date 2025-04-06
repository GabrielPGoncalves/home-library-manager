$('#read').change(event => {
    let readingEndDateSelector = $('#readingEndDate');

    readingEndDateSelector.prop('disabled', !$('#read').is(':checked'))
    readingEndDateSelector.text('')
    readingEndDateSelector.datetimepicker('clear')
})