Date.prototype.format = function (format) {
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    };
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
};

Array.prototype.creverse = function () {
    var arrLength = this.length;
    var result = new Array(arrLength);
    if(arrLength){
        this.forEach(function (t, index) {
            result[arrLength-1-index] = t;
        });
    }
    return result;
};

Math.distance = function (point1, point2) {
    var dx = Math.abs(point1.x - point2.x);
    var dy = Math.abs(point1.y - point2.y);
    var distance = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    return distance;
};

Math.getPoint = function (point1, point2, λ) {
    var x = (point1.x + point2.x*λ)/(1+λ);
    var y = (point1.y + point2.y*λ)/(1+λ);
    return {x: x, y: y};
};