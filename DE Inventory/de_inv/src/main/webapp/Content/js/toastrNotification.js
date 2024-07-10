$(document).ready(function () {
    toastr.options = {
        "preventDuplicates": true,
        "positionClass": "toast-bottom"
    };


    showSuccessMsg = function (Msg) {
        toastr.success(Msg);
    }

    showErrorMsg = function (Msg) {
        toastr.error(Msg);
    }

});
