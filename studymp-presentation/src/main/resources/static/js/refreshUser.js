$('.myCustomClass').click(function (e) {

    var papanya = $(this).parent().parent();
    var btn = $(this);
    var formData = {
        'username': papanya.attr('data-username'),
        'email': papanya.attr('data-email'),
        'enabled': papanya.attr('data-enabled'),
        'password': ""
    };

    $(function() {
        $('#toggle-event').slideToggle(function() {
            $('#console-event').html('Состояние: ' + $(this).prop('checked'));
            formData.enabled = $(this).prop('checked');
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "user/update",
                data: JSON.stringify(formData),
                dataType: 'json'
            }).done(function (data) {

                console.log(data);
            });
        })
    });
    /*
     $.ajax({
     type: "PUT",
     contentType: "application/json",
     url: "user/update",
     data: JSON.stringify(formData),
     dataType: 'json'
     }).done(function (data) {

     console.log(data);
     });
     */


});
