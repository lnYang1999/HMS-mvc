Date.prototype.Format = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for ( var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 *	组织正则表达式，主要是定位name所在的位置，并截取name=value
 *	如www.baidu.com:8080?a=1&b=2&c=3
 *	若传入b，则获取b=2，并经过后续步骤拆解成2返回
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}

//判断字符串是否包含英文字母
function hasLetter(str) {
    for (var i in str) {
        var asc = str.charCodeAt(i);
        if ((asc >= 65 && asc <= 90 || asc >= 97 && asc <= 122)) {
            return true;
        }
    }
    return false;
}

//判断字符串是否包含中文
function isChina(str){
    var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
    // [\u4E00-\u9FA5]表示汉字，[\uFE30-\uFFA0]表示全角
    if(!patrn.exec(str)){
        return false;
    }
    else{
        return true;
    }
}

//判断字符串是否包含数字
function containsNumber(str) {
    var reg=/\d/;
    return reg.test(str);
}

//下载作业文件
function downloadHomework(homeworkId){
    window.open("/downloadHomework?homeworkId="+homeworkId);
}

//下载学生作业文件
function downloadStudentHomework(studentHomeworkId){
    window.open("/downloadStudentHomework?studentHomeworkId="+studentHomeworkId);
}