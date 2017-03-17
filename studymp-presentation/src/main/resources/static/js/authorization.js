$('#enter-to-studymp').click(function (e) {

    var formData = {
        'username': $('input[name=username]').val(),
        'password': $('input[name=password]').val()
    };

    var successForm = false;
    if (formData.password.length > 7){
        successForm = true;
        $('#error_area').remove();
    }
    else {
        $('#error_wrapper').append('<div class="container"> ' +
            '<textarea id="error_area"> Пароль дожен быть не менее 8 символов </textarea>' +
            '</div>');
    } 

    if(successForm) {
        $.ajax({
         type : "POST",
         contentType : "application/json",
         url : "secure",
         data : JSON.stringify(formData),
         dataType : 'json',
         timeout : 100000
         }).success(function (data) {
            if (!data.status){
                $('#error_wrapper').append('<div class="container"> ' +
                    '<textarea id="error_area">'+ data.body +' </textarea>' +
                    '</div>');
            }
         });
    }

});
