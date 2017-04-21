$('.myCustomClass').click(function (e) {
    $(function() {
        $('#toggle-event').slideToggle(function() {
            $('#console-event').html('Состояние: ' + $(this).prop('checked'));
            formData.enabled = $(this).prop('checked');
        })
    });

});
