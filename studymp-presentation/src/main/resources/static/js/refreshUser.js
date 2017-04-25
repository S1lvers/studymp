$(window).ready(function (e) {
        var dataUser = $(this).parent().parent();
        var username = dataUser.attr('data-username');
        var enabled = dataUser.attr('data-enabled');

        if (enabled === true) {
            $('#'+username+'-toggle').bootstrapToggle('on')
        }
    });