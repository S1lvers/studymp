$('.myCustomClass').click(function (e) {
    var dataUser = $(this).parent().parent();
    var formData = {
        'username': dataUser.attr('data-username'),
        'email': dataUser.attr('data-email'),
        'enabled': dataUser.attr('data-enabled'),
        'password': ""
    };

    $(function() {
        $('#'+formData.username+'-toggle').slideToggle(function() {
            formData.enabled = $(this).prop('checked');
            console.log(formData.enabled);
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

});
