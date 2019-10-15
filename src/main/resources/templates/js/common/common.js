var baseURL = "";
var ws = location.host;
var setCookie = function (name, value, dates) {
    var exp = new Date();
    exp.setTime(exp.getTime() + dates*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
};
var getCookie = function (name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
};
var delCookie = function (name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=this.getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
};
var compare = function (prop) {
    return function (obj1, obj2) {
        var val1 = obj1[prop];
        var val2 = obj2[prop];
        if (val1 < val2) {
            return 1;
        } else if (val1 > val2) {
            return -1;
        } else {
            return 0;
        }
    }
};

(function (){
    var userToken = getCookie("user.cookie");
    $.ajaxSetup({
        cache: false,
        timeout: 1000,
        beforeSend: function(xhr, option) {
            if(userToken == null || typeof userToken === "undefined"){
                xhr.setRequestHeader('token', null);
            } else if(option.url.indexOf("http://116.62.221.103:8080/api/v1/sensor")<0){
                xhr.setRequestHeader('token', userToken);
            }
        },
        statusCode: {
            200: function(json) {
                if(json.code==400){
                    location.href="./login.html";
                }
            }
        }
    });
}());

$(".sign-out").click(function () {
    var oper = function () {
        delCookie('user.cookie');
        location.href = "./login.html";
    };
    if(location.href.indexOf("video.html")>=0){
        loginOut();
    }else{
        Feng.confirm("是否退出？", oper);
    }
});