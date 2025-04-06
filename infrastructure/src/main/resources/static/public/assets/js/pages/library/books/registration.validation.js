$('#book-form').validate({
    rules: {
        title: {
            required: true,
            minlength: 2,
            maxlength: 100
        },
        author: {
            required: true,
            minlength: 2,
            maxlength: 100
        },

    },
    messages: {
        title: {
            required: 'Insira um título para o livro',
            minlength: 'O título do livro deve ter no mínimio 2 caracteres',
            maxlength: 'O título do livro deve ter no máximo 100 caracteres'
        },
        author: {
            required: 'Insira o nome do autor',
            minlength: 'O nome do autor deve ter no mínimio 2 caracteres',
            maxlength: 'O nome do autor deve ter no máximo 100 caracteres'
        }
    },
    errorElement: 'span',
    errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
    },
    highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
    },
    unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
    }
})