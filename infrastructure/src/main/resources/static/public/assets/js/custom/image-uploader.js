$(document).ready(() => {
    const imageUpload = $('#imageUpload')
    const fileInput = $('#fileInput')
    const fileInputContent = $('#fileInputContent')
    const removeImage = $('#removeImage')
    const uploadIcon = $('#uploadIcon')

    const imageUrlPrefix = 'data:image/jpeg;base64,'

    if (fileInputContent.val() !== '') {
        imageUpload.css('backgroundImage', `url('${imageUrlPrefix}${fileInputContent.val()}')`)
        uploadIcon.addClass('hidden')
        removeImage.css('display', 'flex')
    }

// Clique na área ativa o input
    imageUpload.click(e => {
        fileInput.click()
    })

// Evita que clique no botão de remover dispare o input
    removeImage.click(e => {
        e.stopPropagation() // evita abrir o seletor de arquivo
        imageUpload.css('backgroundImage', '')
        uploadIcon.removeClass('hidden')
        removeImage.css('display', 'none')
        fileInputContent.val('')
        fileInput.val('') // limpa o input
    })

// Exibir imagem ao selecionar
    fileInput.change(e => {
        const file = e.target.files[0]
        if (file) {
            const reader = new FileReader()
            reader.onload = (e) => {
                fileInputContent.val(e.target.result)
                imageUpload.css('backgroundImage', `url('${e.target.result}')`)
                uploadIcon.addClass('hidden')
                removeImage.css('display', 'flex')
            }
            reader.readAsDataURL(file)
        }
    })
})