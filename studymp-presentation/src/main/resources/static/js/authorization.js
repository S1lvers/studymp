
$('#enter-to-studymp').click(function (e) {

    var formData = {
        'username': $('input[id=username]').val(),
        'password': $('input[id=password]').val()
    };
    
    var successForm = false;

    if (formData.password.length > 7){
            /*condition = condition + 1;*/
        successForm = true;
        $('#error_area').remove();
    }
    else {
        $('#error_wrapper').append('<div class="container"> ' +
            '<textarea id="error_area"> Пароль дожен быть не менее 8 символов </textarea>' +
                '<textarea id="error_area"> Пароль должен быть не менее 8 символов </textarea>' +
                '</div>');
        }


  /*  if (validateEmail( $(formData.username).val() ) ) {
        alert('Ok!');
        condition = condition + 1;
    } else {
        $('#error_wrapper').replaceWith('<div class="container">' +
            '<textarea id="error_area"> Неправильно введена почта </textarea>' +
    }

    function validateEmail(username) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(username);
            }

     if (condition = 2){
        successForm = true;
    }
*/

        $.ajax({
         type : "PUT",
         contentType : "application/json",
         url : "api/auth/register",
         data : JSON.stringify(formData),
         dataType : 'json',
         timeout : 100000
         }).success(function (data) {
            data.console.log()
            
         });
});
