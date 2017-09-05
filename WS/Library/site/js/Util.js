function getAllTags() {
        $.ajax({
            dataType: 'json',
            crossDomain: true,
            contentType: "application/json; charset=utf-8",
            url: localServer + "getAllTags",
            success: function (data) {
                updateInfoSelectTags(data);
            },
            error: function (data) {
                alert("Ocorreu um erro ao listar as Tags");
            }
        });
    }