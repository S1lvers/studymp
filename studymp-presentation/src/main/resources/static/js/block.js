$('#user-info').submit(function (e) {
    e.preventDefault();
    var hash = window.location.href.split('hash=')[1];
    var formData = {
        'username': $('input[id=username]').val(),

    };

});
