$(document).ready(function () {
    // .GET instead of #getContent because ID must be unique
    $(".GET").click(function (e) {
        console.log("clicked")
        e.preventDefault();
        const ID = $(this).siblings('input[name="ID"]').val();
        $.ajax({
            type: "POST",
            url: "AdminGetSelectedUserController",
            data: {ID: ID},
            dataType: "json",
            success: function (data) {

                if (data) {
                    $("#name").val(data.USER.name)
                    $("#email").val(data.USER.email)
                    $("#phone").val(data.USER.phoneNumber)
                    $("#password").val(data.USER.password)
                }
            },
            error: function (xhr, textStatus, error) {
                console.log(xhr.responseText);
                console.log(xhr.statusText);
                console.log(textStatus);
                console.log(error);
            }
        });
    });
});
