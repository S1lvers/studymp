/**
 * Created by qwerty on 26.03.2017.
 */

    var hash = window.location.href.split('hash=')[1];
    var formData = {
        'password': $('input[id=password]').val(),
        'hash': hash
    };
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/auth/confirmAccount",
        data: JSON.stringify(formData),
        success: function (data) {
            if (!data.status) {
                $('#error_wrapper').replaceWith('<div class="form-group">' + '<span style="color: red" class="form-group" id="error_area">' +
                    data.error + '</span>' +
                    '</div>');
            }
            else {
                setTimeout(function () {
                    $('#success_wrapper').replaceWith('<div class="form-group">' + '<h1>' +
                        '<span class="form-group" id="success_area">' + 'Вы зарегистрированы!' + '</span>' + '</h1>' +
                        '</div>');
                }, 1000);
                setTimeout(function () {
                    document.location.href = "login.html";
                }, 2000);
            }
        }
    }).done(function (data) {
        console.log(data);
    });

