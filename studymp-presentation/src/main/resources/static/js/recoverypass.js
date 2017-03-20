$('#forgot_password').click(function (e) {
    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val(),
        'password2': $('input[id=password2]').val(),
        'captcha': $('input[id=captcha]').val()
    };
});
