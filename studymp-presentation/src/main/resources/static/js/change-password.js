$('#change-form').submit(function (e) {
    e.preventDefault();
    var hash = window.location.href.split('hash=')[1];
    var formData = {
        'password': $('input[id=password]').val(),
        'hash': hash
    };


    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/api/auth/changePassword",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    });
    $('#error_wrapper').replaceWith('<div class="form-group">' +
        '<span class="form-group" id="error_area"> Пароль изменен! </span>' +
        '</div>');

    setTimeout(function() {
        document.location.href = "login.html";
    },1500);
});
