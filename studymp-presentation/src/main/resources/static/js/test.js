$('#testAjax').click(function (e) {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/user/chat?destination=username2",
        dataType : 'json',
        timeout : 100000
    }).done(function (data) {
        window.location(url)
    });
});
