/**
 * Created by malko on 16.05.2017.
 */
$('#order-form').submit(function (e) {
    e.preventDefault();
    var formData = {
        'username': $('input[id=username]').val(),
        'subject': $('input[id=subject]').val(),
        'description': $('input[id=description]').val(),
        'cost': $('input[id=cost]').val()
    };
    $('#course_wrapper').append('<div class="form-group">' + '<span style="color: red" ' +
        'class="form-group">' +
        'formData.subject' + '</span>' +
        '</div>');
/*    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "",
        data: JSON.stringify(formData),
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
    })*/
});
