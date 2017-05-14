$('#registration-form').submit(function (e) {
    e.preventDefault();
    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val()
    };
    /*   $('#error_wrapper').replaceWith('<div class="form-group">' +
     '<div class="adjust"><div class="loader2">' + '</div>' + '</div>' + '</div>');
     setTimeout(function() {
     $('#error_wrapper').replaceWith('<div class="form-group">' +
     '<span class="form-group" id="error_area"> Сообщение отправлено на почту </span>' +
     '</div>');
     },1000);
     setTimeout(function() {
     document.location.href = "login.html";
     },1000);*/
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "api/auth/register",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (data) {
            if (!data.status) {
                $('#error_wrapper').replaceWith('<div class="form-group">' + '<span style="color: red" class="form-group" id="error_area">' +
                    data.error + '</span>' +
                    '</div>');
            }
            else {
                $('#error_wrapper').replaceWith('<div class="form-group">' +
                    '<div class="adjust"><div class="loader2">' + '</div>' + '</div>' + '</div>');
                setTimeout(function () {
                    $('#success_wrapper').replaceWith('<div class="form-group">' +
                        '<span class="form-group" id="success_area">' + 'Сообщение отправлено на почту' + '</span>' +
                        '</div>');
                }, 1000);
                setTimeout(function () {
                    document.location.href = "login.html";
                }, 2000);
            }
        }

    }).done(function (data) {
        console.log(data);
    })
});
