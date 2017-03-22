$('#forgot_password').click(function (e) {
    var formData = {
        'email': $('input[id=email]').val(),
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
});
