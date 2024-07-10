var AccessApi = [];
AccessApi.execute = function (type, url, obj, callback, progressHandlingCallback, reqCode) {
    if (obj == null) {
        obj = '{}';
    } else {
        obj = JSON.stringify(obj);
    }
    $.ajax({
        type: type,
        url: url,
        data: obj,
        contentType: "application/json; charset=utf-8",
        //headers: { 
        //    "Auth-Token": "DGC00i43fsbfsa16d0-76de5fsffsc565c-45df4rfs89dfst545-a4d5sd56cd83-b69s848fcdf3"
        //},
        dataType: "json",
        success: function (response) {
            if (callback) {
                callback(response);
            }
        },
        error: function (err) {
            if (err.status == 409) {
                callback(409);
            }

            else {
                callback(0);
            }
            //alert("Error loading data! Please try again.");
        }
    });
}


AccessApi.executeAsync = function (type, url, obj, callback, progressHandlingCallback, reqCode) {
    if (obj == null) {
        obj = '{}';
    } else {
        obj = JSON.stringify(obj);
    }
    $.ajax({
        type: type,
        url: url,
        async:true,
        data: obj,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (callback) {
                callback(response);
            }
        },
        error: function (err) {
            if (err.status == 409) {
                callback(409);
            }

            else {
                callback(0);
            }
            //alert("Error loading data! Please try again.");
        }
    });
}