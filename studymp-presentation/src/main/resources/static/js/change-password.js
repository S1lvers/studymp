$('#change-form').submit(function (e) {
    e.preventDefault();
    var hash = window.location.href.split('hash=')[1];
    var formData = {
        'password': $('input[id=password]').val(),
        'hash': hash
    };


    $.ajax('/jquery',
        {
        type: "PUT",
        contentType: "application/json",
        url: "/api/auth/changePassword",
        data: JSON.stringify(formData),
        dataType: 'json',

    success: function(jqXHR, textStatus) {

                $('#error_wrapper').replaceWith('<div class="form-group">' +
                    '<span class="form-group" id="error_area"> Пароль изменен! </span>' +
                    '</div>');
                setTimeout(function() {
                    document.location.href = "login.html";
                },1500);
            },

        error: function (jqXhr, textStatus, errorMessage) { // error callback
            $('#error_wrapper').replaceWith('<div class="form-group">' +
                '<span class="form-group" id="error_area"><i>Пароль не изменен!</i></span>' +
                '</div>');

        }
    });
});
