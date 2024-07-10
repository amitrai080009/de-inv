function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



function bindDropdownList(control, data, firstText) {
    var dropdownData = '';
    dropdownData += '<option value="0"> ' + firstText + ' </option>';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
    }
    $(control).append(dropdownData);
}


function bindDropdownFixedList(control, data, firstText) {
    var dropdownData = '';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
    }
    $(control).append(dropdownData);
    for (var i = 0; i < data.length; i++) {
        if (data[i].id == 0) {
            $(control).val(data[i].id);
        }
    }
}


var removeValue = function (list, value, separator) {
    separator = separator || ",";
    var values = list.split(separator);
    for (var i = 0 ; i < values.length ; i++) {
        if (values[i] == value) {
            values.splice(i, 1);
            return values.join(separator);
        }
    }
    return list;
}


function truncateString(Text, TextLength) {
    if (Text != null && Text != '') {
        Text = Text.replace(/<\/?[^>]+(>|$)/g, "");//Strip HTML.
        TextLength = TextLength - 3;    //To Append 3 dots.
        if (Text.length < TextLength)
            return Text;
        Text = Text.substring(0, TextLength);
        return Text.substring(0, Text.lastIndexOf(" ")) + '...';
    }
    else {
        return '';
    }
}

function ConfirmationSweetAlert(callback) {
    swal({
        title: "Are you sure?",
        text: "you want to delete",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    },
function () {
    if (callback) {
        callback('yes');
    }
});
}

function swalMessage(title, message, type) {
    swal(title, message, type);
}
function swalSaveMessage() {
    swalMessage("Success!", "Data saved successfully.", "success")
}
function swalUpdateMessage() {
    swalMessage("Success!", "Data updated successfully.", "success")
}

function swalDeleteMessage() {
    swalMessage("Success!", "Data deleted successfully.", "success")
}

function swalErrorMessage() {
    swalMessage("Error!", "Some Error occured.", "error")
}

function swalConflicInfotMessage() {
    swalMessage("Information!", "Data already exist.", "info")
}

function swalDeleteInfoMessage() {
    swalMessage("Information!", "You can't delete this record.", "info")
}

function swalPromptInforMessage(message) {
    swalMessage("Information!", message, "info")
}


function RowSelectionHighlited(control) {
    $(control).addClass('row-highlighted').siblings().removeClass("row-highlighted");
}


function getBase64Image(img) {
    // Create an empty canvas element
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;

    // Copy the image contents to the canvas
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0);

    // Get the data-URL formatted image
    // Firefox supports PNG and JPEG. You could check img.src to
    // guess the original format, but be aware the using "image/jpg"
    // will re-encode the image.
    var dataURL = canvas.toDataURL("image/png");

    return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
}

//var userImage = document.getElementById('userImg');
//var imgData = getBase64Image(userImage);



//---------------------------------------------------------------------------------------------------Avneesh Start-----------------------------------------------------

$(document).ready(function () {
    $(".numeric").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if (((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) || e.keyCode == 190) {
            e.preventDefault();
        }
    });

    $(".numeric").blur(function (e) {
        var v = $(this).val();
        if (v.match(/^\d+$/)) {
            $(this).val(v);
        } else {
            $(this).val('');
        }
    });

    $(".numericWith2Decimal").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if (((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))) {
            e.preventDefault();
        }
    });

    $(".numericWith5Decimal").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if (((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105))) {
            e.preventDefault();
        }
    });

    $(".numericWith2Decimal").blur(function (e) {
        var v = parseFloat($(this).val());
        if (v % 1 === 0) { //......For int remove .
            $(this).val((isNaN(v)) ? '' : v.toFixed(0))
        } else { //......For Float remove .
            $(this).val((isNaN(v)) ? '' : v.toFixed(2))
        }
    });

    $(".numericWith5Decimal").blur(function (e) {
        var v = parseFloat($(this).val());
        if (v % 1 === 0) { //......For int remove .
            $(this).val((isNaN(v)) ? '' : v.toFixed(0))
        } else { //......For Float remove .
            $(this).val((isNaN(v)) ? '' : v.toFixed(5))
        }

    });

    $(".timepicker").keydown(function (e) {
        var maxLength = 8;
        var text = $(this).val();
        var textLength = text.length;
        if (textLength > maxLength) {
            e.preventDefault();
        }

        //alert(e.keyCode);
        // Allow: backspace, delete, tab, escape, enter
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 65, 80, 77, 186]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if (((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) || e.keyCode == 190) {
            e.preventDefault();
        }
    });

    $(".timepicker").keyup(function (e) {
        this.value = this.value.toUpperCase();
    });

    $(".timepicker").blur(function () {
        var validTime = $(this).val().match(/^(0?[1-9]|1[012])(:[0-5]\d) [APap][mM]$/);
        if (!validTime) {
            $(this).val('');
        }
    });
});










function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

function validateUrl(url) {
    return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(url);
}

//////// validate From Time and To time (Start-time must be smaller then End-time)

function compareFromToTime(TxtFromTime, TxtToTime) {

    //start time
    var start_time = $.trim(TxtFromTime);
    //end time
    var end_time = $.trim(TxtToTime);
    if (!start_time || !end_time) {
        return false;
    }

    //convert both time into timestamp
    var stt = new Date("November 13, 2013 " + start_time); //.....We can take any date
    stt = stt.getTime();

    var endt = new Date("November 13, 2013 " + end_time);
    endt = endt.getTime();

    //by this you can see time stamp value in console via firebug
    //console.log("Time1: " + stt + " Time2: " + endt);

    if (stt >= endt) {
        //$("#start_time").after('<span class="error"><br>Start-time must be smaller then End-time.</span>');
        //$("#end_time").after('<span class="error"><br>End-time must be bigger then Start-time.</span>');
        return false;
    }
    return true;

}


function validateTimeRange(range1, range2) {
    if (!range1 || !range2) {
        return false;
    }

    //convert both time into timestamp
    var from11 = new Date("November 13, 2013 " + range1.from); //.....We can take any date
    from11 = from11.getTime();

    var to12 = new Date("November 13, 2013 " + range1.to);
    to12 = to12.getTime();

    var from21 = new Date("November 13, 2013 " + range2.from); //.....We can take any date
    from21 = from21.getTime();

    var to22 = new Date("November 13, 2013 " + range2.to);
    to22 = to22.getTime();

    //validate From value of second range.
    //if (from21 >= to12 || from21 < from11) {
    //    //form validate now validate to
    //    if ((to22 > from21) && (to22>to12 || to22<from11)) {
    //        return true;
    //    }
    //}

    if ((from11 <= to22) && (to12 >= from21)) {
        return false;
    }
    return true;
}


function validateTimeOverlapRange(range1, range2) {
    if (!range1 || !range2) {
        return false;
    }

    //convert both time into timestamp
    var from11 = new Date("November 13, 2013 " + range1.from); //.....We can take any date
    from11 = from11.getTime();

    var to12 = new Date("November 13, 2013 " + range1.to);
    to12 = to12.getTime();

    var from21 = new Date("November 13, 2013 " + range2.from); //.....We can take any date
    from21 = from21.getTime();

    var to22 = new Date("November 13, 2013 " + range2.to);
    to22 = to22.getTime();

    if (((from21 >= from11) && (from21 < to12)) && ((to22 > from11) && (to22 <= to12))) {
        return true;
    }
    return false;
}

function validateDuplicacyofTimeRange(range1, range2) {
    //convert both time into timestamp
    var from11 = new Date("November 13, 2013 " + range1.from); //.....We can take any date
    from11 = from11.getTime();

    var to12 = new Date("November 13, 2013 " + range1.to);
    to12 = to12.getTime();

    var from21 = new Date("November 13, 2013 " + range2.from); //.....We can take any date
    from21 = from21.getTime();

    var to22 = new Date("November 13, 2013 " + range2.to);
    to22 = to22.getTime();

    if (from11 == from21 && to12 == to22) {
        //form validate now validate to
        return false;
    }
    return true;
}

function getMonthName(index) {
    var Month = "Jan";
    switch (index) {
        case 1:
            Month = "Jan";
            break;
        case 2:
            Month = "Feb";
            break;
        case 3:
            Month = "Mar";
            break;
        case 4:
            Month = "Apr";
            break;
        case 5:
            Month = "May";
            break;
        case 6:
            Month = "Jun";
            break;
        case 7:
            Month = "Jul";
            break;
        case 8:
            Month = "Aug";
            break;
        case 9:
            Month = "Sep";
            break;
        case 10:
            Month = "Oct";
            break;
        case 11:
            Month = "Nov";
            break;
        case 12:
            Month = "Dec";
            break;
    }
    return Month;
}
//.################################## ControlBind Functions Start ###################

function bindDropdownList(control, data, firstText) {
    var dropdownData = '';
    dropdownData += '<option value="0"> ' + firstText + ' </option>';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
    }
    $(control).html('').append(dropdownData);
}

function bindMaterialDropdownList(control, data, firstText) {
    var dropdownData = '<option value="" disabled selected> ' + firstText + ' </option>';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
    }
    $(control).html('').append(dropdownData);
}

//.################################## ControlBind Functions end ###################

function counter(key, val, ObjJson) {
    return json.ObjJson.filter(function (elem) {
        return elem.key === val;
    }).length;
}

//###### Simlpy-Tag Add close functionality  Start ###############################

//This function is called to set id in varable when ever any tag is added.
function onTagAdded(controlId, Id) {
    var elem = $('#divS' + Id)
    if (elem.hasClass('simplyselectedItem')) {
        elem.removeClass('simplyselectedItem');
        removeId(controlId, Id);
    }
    else {
        addId(controlId, Id);
        elem.addClass('simplyselectedItem');
    }
}
//......This function is call when ever any tag close by close button on the tag.
function onTagClosed(controlId, Id) {
    var elem = $('#divS' + Id);
    $('#' + controlId + 'spanR_' + Id).parent().remove();
    var elem = $('#divS' + Id);
    // if (elem.hasClass('simplyselectedItem')) {
    removeId(controlId, Id);
    elem.removeClass('simplyselectedItem');
    // }
}

function removeId(controlId, Id) {
    if (controlId == 'txtPlaceTips') {
        TravelTipsIds = removeValue(TravelTipsIds, Id, ',');
    } else if (controlId == 'txtClosingDay') {
        closingdaysIds = removeValue(closingdaysIds, Id, ',');
    } else if (controlId == 'txtPreferredMonths') {
        preferMonthsIds = removeValue(preferMonthsIds, Id, ',');
    }
}

function addId(controlId, Id) {
    if (controlId == 'txtPlaceTips') {
        TravelTipsIds += Id + ',';
    } else if (controlId == 'txtClosingDay') {
        closingdaysIds += Id + ',';
    } else if (controlId == 'txtPreferredMonths') {
        preferMonthsIds += Id + ',';
    }
}

//###### Simlpy-Tag Add close functionality  End ###############################
function truncateString(Text, TextLength) {
    if (Text != null && Text != '') {
        Text = Text.replace(/<\/?[^>]+(>|$)/g, "");//Strip HTML.
        TextLength = TextLength - 3;    //To Append 3 dots.
        if (Text.length < TextLength)
            return Text;
        Text = Text.substring(0, TextLength);
        return Text.substring(0, Text.lastIndexOf(" ")) + '...';
    }
    else {
        return '';
    }
}

//-----------------------------------------------------------------------------------------Avneesh ---------------------------------------------------------------------------



//var reAllowedHTMLTags = /^(h1|h2|a|img|b|em|li|ol|i|pre|strong|ul|font|span|div|u|sub|sup|table|tbody|blockquote|tr|td)$/i

//function ParseHTML(theHTML) {
//    // Start of with a test to match all HTML tags and a group for the tag name which we pass in as an extra parameter
//    theHTML = theHTML.replace(/<[/]?([^> ]+)[^>]*>/g, function (match, HTMLTag) {
//        // if the HTML tag does not match our list of allowed tags return empty string which will be used as a
//        // a replacement for the pattern in our inital test.
//        if (!reAllowedHTMLTags.test(HTMLTag)) {
//            return "";
//        } else {
//            // The HTML tag is allowed so check attributes with the tag

//            // Certain attributes are allowed so we do another replace statement looking for attributes and using another
//            // function for the replacement value.
//            //match = match.replace(/ ([^=]+)="[^"]*"/g, function (match2, attributeName) {
//            //    // If the attribute matches our list of allowed attributes we return the whole match string
//            //    // so we replace our match with itself basically allowing the attribute.
//            //    if (reAllowedAttributes.test(attributeName)) {
//            //        return match2;
//            //    } else {
//            //        return "";  // not allowed so return blank string to wipe out the attribute value pair
//            //    }
//            //});

//        }
//        return match;

//    }); //end of the first replace

//    //return our cleaned HTML
//    return theHTML;
//} 

function StripHtml(html) {
    var regex = /(<([^>]+)>)/ig
    var dd = html.replace('<b>', '@b@').replace('</b>', '@/b@').replace('<u>', '@u@').replace('</u>', '@/u@').replace('<i>', '@i@').replace('</i>', '@/i@');
    var result = dd.replace(regex, "");
    var f_result = result.replace('@b@', '<b>').replace('@/b@', '</b>').replace('@u@', '<u>').replace('@/u@', '</u>').replace('@i@', '<i>').replace('@/i@', '</i>');
    return f_result;
}

function ShowLoader(idSelector) {
    $(idSelector).LoadingOverlay("show");
    $(idSelector).LoadingOverlay("show");
}

function HideLoader(idSelector) {
    $(idSelector).LoadingOverlay("hide", true);
}


function bindContainerDropdownList(control, data, firstText) {
    var dropdownData = '';
    dropdownData += '<option value="0"> ' + firstText + ' </option>';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].ratio + '</option>';
        }
    }
    $(control).html('').append(dropdownData);
}


function setPageHeaderText(text) {
    $('#page-header-container-text').text(text);
}

//Here val is use when need to bind value in text box.
function intializeRTE(controlId, val) {
    var editor = new Simditor({
        textarea: $('#' + controlId),
        toolbar: [
    //'title',
    'bold',
    'italic',
    'underline',
    //'strikethrough',
    //'fontScale',
    //'color',
    //'ol',
    //'ul',
    //'blockquote',
    //'code',
    //'table',
    'link',
    //'image',
    //'hr',
    //'indent',
    //'outdent',
    //'alignment'
        ],
        toolbarFloat: false,
        toolbarHidden: false,
        upload: false,
        cleanPaste: true
    });

    if (val)
        editor.setValue(val);
}

function intializeTimepicker() {
    $('.timepicker').pickatime({
        default: 'now', // Set default time
        fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
        twelvehour: true, // Use AM/PM or 24-hour format
        donetext: 'OK', // text for done-button
        cleartext: 'Clear', // text for clear-button
        canceltext: 'Cancel', // Text for cancel-button
        autoclose: false, // automatic close timepicker
        ampmclickable: true, // make AM PM clickable
        aftershow: function () { } //Function for after opening timepicker  
    });
}



var transliterationControl;

function bindTranslation(TextBoxNameArrays) {

    function onLoad() {
        var options = {
            sourceLanguage: 'en',
            destinationLanguage: ['ar', 'hi', 'kn', 'ml', 'ta', 'te'],
            transliterationEnabled: true,
            shortcutKey: 'ctrl+g'
        };
        // Create an instance on TransliterationControl with the required
        // options.
        transliterationControl =
          new google.elements.transliteration.TransliterationControl(options);

        // Enable transliteration in the textfields with the given ids.
        transliterationControl.makeTransliteratable(TextBoxNameArrays);
    }
    google.setOnLoadCallback(onLoad);
}

//......End Google translate.


function bindDropdownLanguageList(control, data, firstText) {
    var dropdownData = '';
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            dropdownData += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
        }
    }
    $(control).append(dropdownData);
    for (var i = 0; i < data.length; i++) {
        if (data[i].id == 1) {
            $(control).val(data[i].id);
        }
    }
}

function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function ConfirmationSweetAlertWithMsg(Msg, callback) {
    swal({
        title: "Are you sure?",
        text: Msg,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes!",
        closeOnConfirm: true
    },
 function () {
     if (callback) {
         callback('yes');
     }
 });
}

//Show more and less start here
function bindShowMoreOrLess(control) {
    //Show More And Less 
    $('#' + control + ' table tr').each(function (i, v) {
        if (i > 0) {
            var text = $(this).find('.text-overflow'),
                 btn = $(this).find('.btn-overflow'),
                 h = text[0].scrollHeight;
            console.log(h)
            if (h > 60) {
                btn.addClass('less');
                btn.css('display', 'block');
            }
            //End Show More And Less
        }
    })
}

function showMoreOrLessClick() {
    $('.btn-overflow').click(function (e) {
        var text = $(this).parent().find('.text-overflow'),
                    h = text[0].scrollHeight;
        console.log(h)
        e.stopPropagation();
        if ($(this).hasClass('less')) {
            $(this).removeClass('less');
            $(this).addClass('more');
            $(this).text('Show less');
            text.animate({ 'height': h });
        } else {
            $(this).addClass('less');
            $(this).removeClass('more');
            $(this).text('Show more');
            text.animate({ 'height': '60px' });
        }
    });
}
//Show more and less end here


//Show more and less start here
function bindShowMoreOrLessWithRTE(control) {
    //Show More And Less 
    $('#' + control + ' table tr').each(function (i, v) {
        if (i > 0) {
            var text = $(this).find('.text-overflowRTE'),
                 btn = $(this).find('.btn-overflowRTE'),
                 h = text[0].scrollHeight;
            console.log(h)
            if (h > 60) {
                btn.addClass('less');
                btn.css('display', 'block');
            }
            //End Show More And Less
        }
    })
}

function showMoreOrLessWithRTEClick() {
    $('.btn-overflowRTE').click(function (e) {
        var text = $(this).parent().find('.text-overflowRTE'),
                    h = text[0].scrollHeight;
        console.log(h)
        e.stopPropagation();
        if ($(this).hasClass('less')) {
            $(this).removeClass('less');
            $(this).addClass('more');
            $(this).text('Show less');
            text.animate({ 'height': h });
        } else {
            $(this).addClass('less');
            $(this).removeClass('more');
            $(this).text('Show more');
            text.animate({ 'height': '60px' });
        }
    });
}
//Show more and less end here