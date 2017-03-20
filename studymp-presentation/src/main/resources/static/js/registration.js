/**
 * Created by malko on 20.03.2017.
 */
$('#reg-on-studymp').click(function (e) {

    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val(),
        'password2': $('input[id=password2]').val(),
        'captcha': $('input[id=captcha]').val()
    };

    var successForm = false;
    {
    if (formData.password.length > 7) {
        /*condition = condition + 1;*/
        successForm = false;
        $('#error_area').replaceWith();
    }
    else {

        $('#error_wrapper').replaceWith('<div class="container">' +
            '<textarea id="error_area"> Пароль должен быть не менее 8 символов </textarea>' +
            '</div>');
    }
}
    {
        if (formData.username.length > 5) {
            /*condition = condition + 1;*/
            successForm = true;
            $('#error_area').replaceWith();
        }
        else {

            $('#error_wrapper').replaceWith('<div class="container">' +
                '<textarea id="error_area"> Логин должен быть не менее 8 символов </textarea>' +
                '</div>');
        }
    }


    if(successForm) {
        $.ajax({
            type : "PUT",
            contentType : "application/json",
            url : "/api/auth/register",
            data : JSON.stringify(formData),
            dataType : 'json'
        }).success(function (data) {
            data.console.log()
        });
    }

});