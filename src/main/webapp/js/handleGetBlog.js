$(document).ready(function () {
    // .GET instead of #getContent because ID must be unique
    $(".GET").click(function (e) {
        console.log("clicked")
        e.preventDefault();
        const ID = $(this).siblings('input[name="ID"]').val();
        $.ajax({
            type: "POST",
            url: "AdminGetSelectedBlogController",
            data: {ID: ID},
            dataType: "json",
            success: function (data) {

                if (data) {
                    $("#ID").val(data.BLOG.blogID)
                    $("#title").val(data.BLOG.blogTitle)
                    tinyMCE.activeEditor.setContent(data.BLOG.blogContent)
                    $("#description").val(data.BLOG.blogDescription)
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
    $(".clearData").click(function (e) {
        console.log("clicked")
        e.preventDefault()
        $("#ID").val("")
        $("#title").val("")
        tinyMCE.activeEditor.setContent("")
        $("#description").val("")
    })
});
