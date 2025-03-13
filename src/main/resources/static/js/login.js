$(document).ready(function() {
    localStorage.removeItem('jwt');
});

$("#btn-sign-in").click(function () {
    let u_id = $("#user-id").val();
    let u_password = $("#user-password").val();
    let data = {
        'userId': u_id,
        'userPassword': u_password,
    }
    $.ajax({
        type: 'post',
        url: '/user/api/signin',
        data: JSON.stringify(data),
        contentType : 'application/json; charset=utf-8',
        success: function(response) {
            localStorage.setItem("jwt", response.accessToken);
            window.location.href = '/delivery';
        },
        error: function(xhr) {
            console.log(xhr);
            alert(xhr.responseText);
        }
    })
});