function getDayWithDateTimeByLanguage(date, language) {
    if (date != '' && date != null && language > 0) {
        var d = new Date(date);
        var weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var weekdaysH = ["रविवार", "सोमवार", "मंगलवार", "बुधवार", "वृहस्पतिवार", "शुक्रवार", "शनिवार"];
        var monthNamesH = ["जनवरी", "फ़रवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त", "सितम्बर", "अक्टूबर", "नवम्बर", "दिसम्बर"];
         
        var weekdaysU = ["پیر", "منگل", "بدھ", "جمعرات", "جمعہ", "ہفتہ", "اتوار"];
        var monthNamesU = ["جنوری", "فروری", "مارچ", "اپریل", "مئی", "جون", "جولائی", "اگست", "ستمبر", "اکتوبر", "نومبر", "دسمبر"];

        var hours = d.getHours();
        var minutes = d.getMinutes();
        var ampm = hours >= 12 ? 'PM' : 'AM';
        var ampmH = hours >= 12 ? 'पू०' : 'अ०';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'
        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;
        var strTimeH = hours + ':' + minutes + ' ' + ampmH;


        var dayno = d.getDate();
        var monthIndex = d.getMonth();
        var year = d.getFullYear();

        var day = weekdays[d.getDay()];
        var dayH = weekdaysH[d.getDay()]; 
        var dayU = weekdaysU[d.getDay()];

        if (language == 1) {
            return day + ', ' + dayno + ' ' + monthNames[monthIndex] + ' ' + year + ' at ' + strTime;
        }
        else if (language == 2) {
            return dayH + ', ' + dayno + ' ' + monthNamesH[monthIndex] + ' ' + year + ' at ' + strTimeH;
        }
        else if (language == 3) {
            return dayU + ', ' + dayno + ' ' + monthNamesU[monthIndex] + ' ' + year + ' at ' + strTimeH;
        }
    }
    else
        return '';
}

function getDayWithDateTime(date) {
    if (date != '' && date != null) {
        var d = new Date(date);
        var weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var hours = d.getHours();
        var minutes = d.getMinutes();
        var ampm = hours >= 12 ? 'PM' : 'AM';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'
        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;


        var dayno = d.getDate();
        var monthIndex = d.getMonth();
        var year = d.getFullYear();

        var day = weekdays[d.getDay()];

        return day + ', ' + dayno + ' ' + monthNames[monthIndex] + ' ' + year + ' at ' + strTime;
    }
    else
        return '';
}


function getDayWithDateByLanguage(date, language) {
    if (date != '' && date != null && language > 0) {
        var d = new Date(date);
        var weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var weekdaysH = ["रविवार", "सोमवार", "मंगलवार", "बुधवार", "वृहस्पतिवार", "शुक्रवार", "शनिवार"];
        var monthNamesH = ["जनवरी", "फ़रवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त", "सितम्बर", "अक्टूबर", "नवम्बर", "दिसम्बर"];

        var weekdaysU = ["پیر", "منگل", "بدھ", "جمعرات", "جمعہ", "ہفتہ", "اتوار"];
        var monthNamesU = ["جنوری", "فروری", "مارچ", "اپریل", "مئی", "جون", "جولائی", "اگست", "ستمبر", "اکتوبر", "نومبر", "دسمبر"];

        var dayno = d.getDate();
        var monthIndex = d.getMonth();
        var year = d.getFullYear();

        var day = weekdays[d.getDay()];
        var dayH = weekdaysH[d.getDay()];
        var dayU = weekdaysU[d.getDay()];

        if (language == 1) {
            return day + ', ' + dayno + ' ' + monthNames[monthIndex] + ' ' + year;
        }
        else if (language == 2) {
            return dayH + ', ' + dayno + ' ' + monthNamesH[monthIndex] + ' ' + year;
        }
        else if (language == 3) { 
             return dayU + ', ' + dayno + ' ' + monthNamesU[monthIndex] + ' ' + year;
        }
    }
    else
        return '';
}

function getDayWithDate(date) {
    if (date != '' && date != null) {
        var d = new Date(date);
        var weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var dayno = d.getDate();
        var monthIndex = d.getMonth();
        var year = d.getFullYear();

        var day = weekdays[d.getDay()];

        return day + ', ' + dayno + ' ' + monthNames[monthIndex] + ' ' + year;
    }
    else
        return '';
}


function formatDate(date) {
    if (date != '' && date != null) {
        var monthNames = [
          "JAN", "FEB", "MAR",
          "APR", "MAY", "JUN", "JUL",
          "AUG", "SEP", "OCT",
          "NOV", "DEC"
        ];

        var day = date.getDate();
        var monthIndex = date.getMonth();
        var year = date.getFullYear();

        return day + ' ' + monthNames[monthIndex] + ' ' + year;
    }
    else
        return '';
}

function formatDateDDMMYYYY(date) {
    if (date != '' && date != null) {
        var d = new Date(date);
        var day = d.getDate();
        var monthIndex = d.getMonth();
        var year = d.getFullYear();

        return day + '/' + monthIndex + '/' + year;
    }
    else
        return '';
}

function formatAMPM(date) {
    if (date != '' && date != null) {
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var ampm = hours >= 12 ? 'PM' : 'AM';
        hours = hours % 12;
        hours = hours ? hours : 12; // the hour '0' should be '12'
        minutes = minutes < 10 ? '0' + minutes : minutes;
        var strTime = hours + ':' + minutes + ' ' + ampm;
        return strTime;
    }
    else
        return '';
}



function getDateMonth(date, languageId) {
    if (date != '' && date != null) {
        var d = new Date(date);
        var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

        var monthNamesH = ["जनवरी", "फ़रवरी", "मार्च", "अप्रैल", "मई", "जून", "जुलाई", "अगस्त", "सितम्बर", "अक्टूबर", "नवम्बर", "दिसम्बर"];

        var monthNamesU = ["جنوری", "فروری", "مارچ", "اپریل", "مئی", "جون", "جولائی", "اگست", "ستمبر", "اکتوبر", "نومبر", "دسمبر"];

        var dayno = d.getDate();
        var monthIndex = d.getMonth();
        if (languageId == 1) {
            return dayno + ' ' + monthNames[monthIndex];
        }
        else if (languageId == 2) {
            return dayno + ' ' + monthNamesH[monthIndex];
        }
        else if (languageId == 3) {
            return dayno + ' ' + monthNamesU[monthIndex];
        }
    }
    else
        return '';
}