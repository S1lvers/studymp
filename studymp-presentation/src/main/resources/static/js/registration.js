/**
 * Created by malko on 14.03.2017.
 */
function regClick() {

    var formData = {
        username: 'username4',
        password: 'password',
        email: 'email4'
    }
    $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'api/auth/register',
        dataType: 'JSON',
        data: JSON.stringify(formData)
    }).success(function (data) {
        console.log(data)
    });
}
