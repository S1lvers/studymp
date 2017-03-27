/**
 * Created by qwerty on 26.03.2017.
 */
$( document ).ready(function() {
    var hash = window.location.href.split('hash=')[1];
    var formData = {
        'password': $('input[id=password]').val(),
        'hash': hash
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/auth/confirmAccount",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    });
});
