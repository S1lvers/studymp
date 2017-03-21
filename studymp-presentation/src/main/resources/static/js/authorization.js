$('#enter-to-studymp').click(function (e) {

    var formData = {
        'username': $('input[name=username]').val(),
        'email': "tosha993@mail.ru",
        'password': $('input[name=password]').val()
    };
    
        $.ajax({
         type : "PUT",
         contentType : "application/json",
         url : "api/auth/register",
         data : JSON.stringify(formData),
         dataType : 'json',
         timeout : 100000
         }).success(function (data) {
            
         });
});
