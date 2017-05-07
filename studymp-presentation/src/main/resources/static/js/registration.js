$('#registration-form').submit(function(e) {
    e.preventDefault();
    var formData = {
        'username': $('input[id=username]').val(),
        'email': $('input[id=email]').val(),
        'password': $('input[id=password]').val()
    };
    
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
