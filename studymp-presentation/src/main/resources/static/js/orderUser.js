/**
 * Created by malko on 16.05.2017.
 */
$('#order-form').submit(function (e) {
    e.preventDefault();
    var formData = {
        'name': $('input[id=subject]').val(),
        'description': $('input[id=description]').val(),
        'cost': $('input[id=cost]').val(),
        'section' : "Математический Анализ"
    };
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/user/order/create",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    })
    /*$('#course_wrapper').append('<div class="form-group">' + '<span style="color: red" ' +
        'class="form-group">' +
        'formData.subject' + '</span>' +
        '</div>');*/

});
