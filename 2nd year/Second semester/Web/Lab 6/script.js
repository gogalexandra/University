let isUpdating = false

function delete_record(id) {
    if (confirm("Are you sure? You want to delete this record?")) {
        $.ajax({
            type: "GET",
            url: "http://localhost/lab/delete.php?delete_id=" + id,
            dataType: 'json',
            beforeSend: function () {
                toastr.info("Please wait..");
            },
            success: function (response) {
                console.log(response);
                if (response.status) { //if response status is true show success message
                    toastr.warning(response.message);
                    get_all_documents();
                } else {
                    //  alert(response.message);
                }
            }
        });
    } else {
        alert('ok');
    }

}

function edit_record(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost/lab/update.php?edit_id=" + id,
        dataType: 'json',
        beforeSend: function () {
            toastr.info("Please wait..");
        },
        success: function (response) {
            // alert('ok2');
            console.log(response);
            if (response.status) { //if response status is true show success message
                $("#title").val(response.data.title);
                $("#id").val(response.data.id);
                $("#author").val(response.data.author);
                $("#noOfPages").val(response.data.noOfPages);
                $("#type").val(response.data.type);
                $("#format").val(response.data.format);
                $("#userformModal").modal('show');
                isUpdating = true;
                //  setTimeout(function(){ alert(response.message);window.location.reload(); }, 3000);

            } else {
                //  alert(response.message);
            }
        }
    });
}

$(document).ready(function () {

    $("#user_form").submit(function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "http://localhost/lab/add.php?isUpdating=" + isUpdating,
            data: $("#user_form").serialize(),
            dataType: 'json',
            beforeSend: function () {
                toastr.info("Please wait..");
            },
            success: function (response) {
                // alert('ok2');
                console.log(response);
                isUpdating = false;
                if (response.status) {
                    toastr.success(response.message);
                    $("#user_form")[0].reset();
                    $("#userformModal").modal('hide');
                    get_all_documents();
                } else {
                    toastr.error(response.message);
                }
            }
        });
    });

});

function get_all_documents() {
    $.ajax({
        type: "GET",
        url: "http://localhost/lab/get.php?get_users=1",
        dataType: 'html',
        success: function (response) {
            console.log(response);
            $("#users_data").html(response);

        }
    });
}
