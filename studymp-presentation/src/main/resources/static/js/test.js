$('#testAjax').click(function (e) {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/user/chat?destination=username2",
        dataType : 'json',
        timeout : 100000
    }).always(function (data) {
        data.console.log()
    });
});
