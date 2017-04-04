$('.myCustomClass').click(function (e) {

    var papanya = $(this).parent().parent();
    var btn = $(this);
    var formData = {
        'username': papanya.attr('data-username'),
        'email': papanya.attr('data-email'),
        'enabled': papanya.attr('data-enabled'),
        'password': ""
    };
    
   if (papanya.attr('data-enabled') == 'true'){
       var formData = {
           'username': papanya.attr('data-username'),
           'email': papanya.attr('data-email'),
           'enabled': false,
           'password': ""
       };
    }
    else {
       var formData = {
           'username': papanya.attr('data-username'),
           'email': papanya.attr('data-email'),
           'enabled': true,
           'password': ""
       };

   }

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "user/update",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).always(function(){
        if (papanya.attr('data-enabled') == 'true'){
            $('.btn').button('complete');
            
        }
        else{
            $('.btn').button('uncomplete');
        }
    });
    console.log(formData);

    
});
