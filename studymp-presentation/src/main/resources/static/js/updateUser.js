$('#user_update').click(function (e) {
    var formData = {
        
        'username': $('[id=username]').val(),
        'enabled': true,
        'email': $('input[id=email]').val(),
        'password': ""
    };
    
/*    if (enabled==true){
        enabled=false;
    }
    else {
        enabled=true;
    }*/
    
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/admin/user/update",
        data: JSON.stringify(formData),
        dataType: 'json',
        timeout: 100000
    })
});
