$('.myCustomClass').click(function (e) {

    var papanya = $(this).parent().parent();

    var formData = {
        'username': papanya.attr('data-username'),
        'email': papanya.attr('data-email'),
        'enabled': "",
        'password': ""
    }

    console.log(formData);
});
