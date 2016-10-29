/**
 * Created by TrungNN on 10/29/2016.
 */

/**
 * Fc: format date
 * @param date
 * @returns {string}
 */
function formatDate(date) {
    var year = date.getFullYear(),
        month = date.getMonth() + 1, // months are zero indexed
        day = date.getDate(),
        hour = date.getHours(),
        minute = date.getMinutes(),
        second = date.getSeconds(),
        hourFormatted = hour % 24 || 24, // hour returned in 24 hour format
        minuteFormatted = minute < 10 ? "0" + minute : minute;


    return day + "/" + month + "/" + year + " (" + hourFormatted + ":" + minuteFormatted + ")";
}
