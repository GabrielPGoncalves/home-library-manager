$(document).ready(() => {
    let Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        padding: '1em',
        didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    })

    let errorMessage = $('#error-message').val()

    if(errorMessage !== undefined){
        Toast.fire({
            icon: 'error',
            title: `<div class="pt-1"><h6>${errorMessage}</h6></div>`
        })
    }

    let successMessage = $('#success-message').val()

    if(successMessage !== undefined){
        Toast.fire({
            icon: 'success',
            title: `<div class="pt-1"><h6>${successMessage}</h6></div>`
        })
    }
})