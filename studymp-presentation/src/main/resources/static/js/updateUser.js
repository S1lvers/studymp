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
