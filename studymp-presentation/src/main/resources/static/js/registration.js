$(document).ready(function(){
    var formData = {
        username: 'username',
        password: 'password',
        email: 'tosha994@mail.ru'
    }
    $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'api/auth/register',
        dataType: 'JSON',
        data: JSON.stringify(formData)
    }).success(function (data) {
        console.log(data)
    });
});


