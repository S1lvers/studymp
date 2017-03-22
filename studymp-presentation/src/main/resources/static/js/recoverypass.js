$('#recovery_password').submit(function (e) {
    e.preventDefault();
    var formData = {
        'email': $('input[id=email]').val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/auth/forgotPassword",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    });


    $('#error_wrapper').replaceWith('<div class="form-group"> ' +
        '<span class="form-group" id="error_area"> Данные отправлены на почту </span>' +
        '</div>');

        setTimeout(function() {
            document.location.href = "login.html";
        },1500);

});
