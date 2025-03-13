$("#btn-sign-up").click(function () {
    let u_name = $("#user-name").val();
    let u_id = $("#user-id").val();
    let u_password = $("#user-password").val();
    let data = {
        'userName': u_name,
        'userId': u_id,
        'userPassword': u_password,
    }
    $.ajax({
        type: 'post',
        url: '/user/api/signup',
        data: JSON.stringify(data),
        contentType : 'application/json; charset=utf-8',
        success: function(response) {
            alert(response.responseText);
            window.location.href = '/';
        },
        error: function(xhr) {
            let errors = xhr.responseJSON;
            let errorMsg = '';
            for (let error in errors) {
                errorMsg += errors[error] + '\n';
            }
            alert(errorMsg);
        }
    })
});