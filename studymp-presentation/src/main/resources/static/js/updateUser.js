$('#user_update').click(function (e) {
    var formData = {
        'username': "username",
        'enabled': true,
        'email': "tosha993@mail.ru",
        'password': ""
    }
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/admin/user/update",
        data: JSON.stringify(formData),
        dataType: 'json',
        timeout: 100000
    })
});
