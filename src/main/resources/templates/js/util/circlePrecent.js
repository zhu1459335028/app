"use strict";

(function ($) {
    $.fn.circlePercent = function (options) {
        var width = this.width();
        var height = this.height();
        var radius = width>height? height: width;
        var settings = $.extend({
            radius: radius/2-5,
            offsetX: width/2,
            offsetY: height/2,
            backgroundColor: '#efefef',
            fillColor: '#1493E4',
            percent: 30,
            fontSize: '14px'
        }, options);
        createPercent(this, settings);
    };
    var createPercent = function (_this, options) {
        _this.empty();
        var circumference = (Math.PI * (2 * options.radius));
        var percent = Math.floor(circumference - ((options.percent / 100) * circumference));
        $('<svg width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" style="transform: rotate(-90deg);">' +
            '<circle r="'+options.radius+'" cy="'+options.offsetY+'" cx="'+options.offsetX+'" stroke-width="4" stroke="'+options.backgroundColor+'" fill="none" />' +
            '<circle ' +
                'r="'+options.radius+'" ' +
                'cy="'+options.offsetY+'" ' +
                'cx="'+options.offsetX+'" ' +
                'stroke-width="5" ' +
                'stroke="'+options.fillColor+'" ' +
                'stroke-linejoin="round" ' +
                'stroke-linecap="round" ' +
                'stroke-dasharray="'+circumference+'"' +
                'stroke-dashoffset="'+percent+'"'+
                'fill="none" ' +
                'style="transition: stroke-dashoffset 1200ms cubic-bezier(.99,.01,.62,.94);"' +
            '/>' +
        '</svg>').appendTo(_this);
        $('<span style="position: absolute;left: 50%;top: 50%;transform: translate(-50%, -50%);font-size: '+options.fontSize+';color: #efefef;z-index: 1000;">'+options.percent+'%</span>').appendTo(_this);
    }
}(jQuery));