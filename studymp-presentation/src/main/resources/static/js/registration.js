/**
 * Created by malko on 14.03.2017.
 */
function regClick() {

    var formData = {
        'username': $('#username').val(),
        'password': $('#password').val(),
        'email': $('#email').val()
    };
    $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'api/auth/register',
        dataType: 'JSON',
        data: JSON.stringify(formData)
    }).success(function (data) {
        console.log(data)
    });
}
