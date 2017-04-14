$('#testAjax').click(function (e) {
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : "/user/chat?destination=username1",
        dataType : 'json',
        timeout : 100000
    }).success(function (data) {
        data.console.log()
    });
});
