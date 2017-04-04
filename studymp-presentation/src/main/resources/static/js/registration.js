$('#registration-form').submit(function(e) {
    e.preventDefault();
    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val()
    };
    $('#error_wrapper').replaceWith('<div class="form-group">' +
        '<div class="adjust"><div class="loader2">' + '</div>' + '</div>' + '</div>');
    setTimeout(function() {
        document.location.href = "login.html";
    },1500);
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "api/auth/register",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    }).always(function (){

    })
});
