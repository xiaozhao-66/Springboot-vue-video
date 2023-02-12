
// 时间日期转化
export function formatDate (date, format) {
    date = new Date(date);
    // date.setHours(date.getHours());
    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'h+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //[表情]cond
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };

    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

    for (var k in o)
        if (new RegExp('(' + k + ')').test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
    return format;
}

//时间戳转时间  年/月/日 时:分:秒
export function getHMS (timestamp) {
  var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '/';
  var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
  var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';

  var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
  var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
  var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
  return Y + M + D + h + m + s;
}
