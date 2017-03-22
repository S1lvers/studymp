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
        dataType: 'json',
        success: function(jqXHR, textStatus) {

            $('#error_wrapper').replaceWith('<div class="form-group">' +
                '<span class="form-group" id="error_area"> Данныве отправлены на почту! </span>' +
                '</div>');
            setTimeout(function() {
                document.location.href = "login.html";
            },1500);
        },
        error: function (jqXhr, textStatus, errorMessage) { // error callback
            $('#error_wrapper').replaceWith('<div class="form-group">' +
                '<span class="form-group" id="error_area"> Данные не отправлены! </span>' +
                '</div>');

        }
    }).done(function (data) {
        console.log(data);
    });
    

});
