/**
 * Created by malko on 20.03.2017.
 */
$('#reg-on-studymp').click(function (e) {
    var confirmPassword = $('input[id=password2]').val();
    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val(),
    };
    if (formData.username.length < 5) {
        $('#error_wrapper').replaceWith('<textarea id="error_wrapper"> Имя пользователя должно быть не менее 5 символов </textarea>');
        return;
    } else if (formData.password.length < 8) {
        $('#error_wrapper').replaceWith('<textarea id="error_wrapper"> Пароль должен быть не менее 8 символов </textarea>');
        return;
    } else if (formData.password !== confirmPassword) {
        $('#error_wrapper').replaceWith('<textarea id="error_wrapper"> Пароли не совпадают </textarea>');
        return;
    }
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "api/auth/register",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    });


});