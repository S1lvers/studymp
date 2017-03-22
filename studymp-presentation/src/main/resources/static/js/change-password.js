
$('#change_password').click(function (e) {
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
});
