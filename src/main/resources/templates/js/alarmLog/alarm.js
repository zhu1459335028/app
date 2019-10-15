var Alarm = {
    alarm: new Object(),
    report: new Object(),
    alarmChart: null,
    reportChart: null,
    layerIndex: -1
};

Highcharts.setOptions({
    lang: {
        resetZoom: "恢复",
        printChart: "打印图表",
        downloadPNG: "导出PNG文件",
        downloadJPEG: "导出JPEG文件",
        downloadPDF: "导出PDF文件",
        downloadSVG: "导出SVG文件"
    }
});
/**
 * 平台标题
 * @param data
 */
var homePageQueryTitle = function () {
    console.log("111")
    $.ajax({
        type: 'get',
        url: "/homePage/homePageQueryTitle",
        dataType: "json",
        //contentType: "application/json",
        // jsonpCallback: "admin_cross",
        success: function (json) {
            if(json.code=="200"){
                console.log("111")
                console.log(json)
                $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }

        }
    });
};

/**
 * 加载实时告警数据
 * @param startTime
 * @param endTime
 * @param isSearch
 */
Alarm.loadAlarmInfo = function (startTime, endTime, isSearch) {
    var $container = $("#alarm-container"), result = null, html = "";
    if(!isSearch){
        $container.empty();
    }
    $.ajax({
        type: "GET",
        url: baseURL + "/report/reportHistory",
        data: {startTime: startTime, endTime: endTime},
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                result = json.result;
                if(isSearch){
                    $container.find('section').hide();
                    $container.find('.alarm-item').hide();
                    for(var item in result){
                        var section = $container.find('section[data-date="'+item+'"]');
                        if(section.length){
                            $(section).show();
                            var $row = $(section).children('.row');
                            $.each(result[item], function (i, t) {
                                var alarm = $row.find('#'+t.reportid);
                                if(alarm.length){
                                    $(alarm).parent().show();
                                }else{
                                    html = '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                        '<div class="thumbnail" id="'+t.reportid+'">' +
                                        '<img  class="viewer img-responsive" src="'+t.majorpic+'" alt="报警缩略图">' +
                                        '<div class="caption caption-center">' +
                                        '<div>'+
                                        '<p>' +
                                        '<label class="alarm-from">报警来源：</label>' +
                                        '<span class="alarm-info" title="'+t.cameraname+'">'+t.cameraname+'</span>' +
                                        '</p>' +
                                        '<p>' +
                                        '<label class="alarm-from">报警信息：</label>' +
                                        '<span class="alarm-info" title="'+t.itemname+'">'+t.itemname+'</span>' +
                                        '</p>' +
                                        '<p class="alarm-time">'+t.createtime+'</p>' +
                                        '</div>' +
                                        '</div>' +
                                        '</div>' +
                                        '</div>';
                                    $row.append(html);
                                }
                            });
                        }else{
                            html = '<section class="content alarm-row" data-date="'+item+'">' +
                                '<h4>'+item+'</h4>' +
                                '<div class="row">';
                            $.each(result[item], function (i, t) {
                                html = html + '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                    '<div class="thumbnail" id="'+t.reportid+'">' +
                                    '<img  class="viewer img-responsive" src="'+t.majorpic+'" alt="报警缩略图">' +
                                    '<div class="caption caption-center">' +
                                    '<div>'+
                                    '<p>' +
                                    '<label class="alarm-from">报警来源：</label>' +
                                    '<span class="alarm-info" title="'+t.cameraname+'">'+t.cameraname+'</span>' +
                                    '</p>' +
                                    '<p>' +
                                    '<label class="alarm-from">报警信息：</label>' +
                                    '<span class="alarm-info" title="'+t.itemname+'">'+t.itemname+'</span>' +
                                    '</p>' +
                                    '<p class="alarm-time">'+t.createtime+'</p>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>';
                            });
                            html = html + '</div></section>';
                            $container.append(html);
                        }
                    }
                }else{
                    for(var item in result){
                        html = html + '<section class="content alarm-row" data-date="'+item+'">' +
                            '<h4>'+item+'</h4>' +
                            '<div class="row">';
                        $.each(result[item], function (i, t) {
                            html = html + '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                '<div class="thumbnail" id="'+t.reportid+'">' +
                                '<img  class="viewer img-responsive" src="'+t.majorpic+'" alt="报警缩略图">' +
                                '<div class="caption caption-center">' +
                                '<div>'+
                                '<p>' +
                                '<label class="alarm-from">报警来源：</label>' +
                                '<span class="alarm-info" title="'+t.cameraname+'">'+t.cameraname+'</span>' +
                                '</p>' +
                                '<p>' +
                                '<label class="alarm-from">报警信息：</label>' +
                                '<span class="alarm-info" title="'+t.itemname+'">'+t.itemname+'</span>' +
                                '</p>' +
                                '<p class="alarm-time">'+t.createtime+'</p>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                        });
                        html = html + '</div></section>';
                    }
                    $container.append(html);
                }
                $('.alarm-item').each(function (i, item) {
                    var $element = $(item);
                    $element.hover(
                        function () {
                            $element.addClass('animated pulse');
                        },
                        function () {
                            //动画完成之前移除class
                            window.setTimeout(function () {
                                $element.removeClass('animated pulse');
                            }, 2000);
                        });
                });

            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};

/**
 * 加载运营报告数据
 * @param num
 * @returns {string}
 */
var severityStr = function (num) {
    var str = "", max = 5;
    for(var x = 0;x<max;x++){
        if(x<num){
            str = str + '<i class="iconfont icon-pingfenshoucang- star-severity"></i>';
        }else{
            str = str + '<i class="iconfont icon-pingfenshoucang-"></i>';
        }
    }
    return str;
};
Alarm.loadOperateReport = function (startTime, endTime, isSearch) {
    var $container = $("#report-container"), result = null, html = "";
    if(!isSearch){
        $container.empty();
    }
    $.ajax({
        type: "GET",
        url: baseURL + "/report/patrolReportHistory",
        data: {startTime: startTime, endTime: endTime},
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                result = json.result;
                if(isSearch){
                    $container.find('section').hide();
                    $container.find('.alarm-item').hide();
                    for(var item in result){
                        var section = $container.find('section[data-date="'+item+'"]');
                        if(section.length){
                            $(section).show();
                            var $row = $(section).children('.row');
                            $.each(result[item], function (i, t) {
                                var alarm = $row.find('#'+t.reportid);
                                if(alarm.length){
                                    $(alarm).parent().show();
                                }else{
                                    html = '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                        '<div class="thumbnail" id="'+t.id+'">' +
                                        '<img  class="viewer img-responsive" src="'+t.majorPic+'" alt="报告缩略图">' +
                                        '<div class="caption caption-center">' +
                                        '<div>'+
                                        '<p>' +
                                        '<label class="alarm-from">视频通道：</label>' +
                                        '<span class="alarm-info" title="'+t.cameraName+'">'+t.cameraName+'</span>' +
                                        '</p>' +
                                        '<p>' +
                                        '<label class="alarm-from">检查项：</label>' +
                                        '<span class="alarm-info" title="'+t.itemName+'">'+t.itemName+'</span>' +
                                        '</p>' +
                                        '<p>' +
                                        '<label class="alarm-from">严重程度：</label>' +
                                        '<span class="alarm-info" data-value="'+t.severity+'">'+severityStr(t.severity)+'</span>' +
                                        '</p>' +
                                        '</div>' +
                                        '</div>' +
                                        '</div>'+
                                        '</div>';
                                    $row.append(html);
                                }
                            });
                        }else{
                            html = '<section class="content alarm-row" data-date="'+item+'">' +
                                '<h4>'+item+'</h4>' +
                                '<div class="row">';
                            $.each(result[item], function (i, t) {
                                html = html + '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                    '<div class="thumbnail" id="'+t.id+'">' +
                                    '<img  class="viewer img-responsive" src="'+t.majorPic+'" alt="报告缩略图">' +
                                    '<div class="caption caption-center">' +
                                    '<div>'+
                                    '<p>' +
                                    '<label class="alarm-from">视频通道：</label>' +
                                    '<span class="alarm-info" title="'+t.cameraName+'">'+t.cameraName+'</span>' +
                                    '</p>' +
                                    '<p>' +
                                    '<label class="alarm-from">检查项：</label>' +
                                    '<span class="alarm-info" title="'+t.itemName+'">'+t.itemName+'</span>' +
                                    '</p>' +
                                    '<p>' +
                                    '<label class="alarm-from">严重程度：</label>' +
                                    '<span class="alarm-info" data-value="'+t.severity+'">'+severityStr(t.severity)+'</span>' +
                                    '</p>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>'+
                                    '</div>';
                            });
                            html = html + '</div></section>';
                            $container.append(html);
                        }
                    }
                }else{
                    for(var item in result){
                        html = html + '<section class="content alarm-row" data-date="'+item+'">' +
                            '<h4>'+item+'</h4>' +
                            '<div class="row">';
                        $.each(result[item], function (i, t) {
                            html = html + '<div class="col-md-2 col-sm-4 col-xs-12 alarm-item">' +
                                '<div class="thumbnail" id="'+t.id+'">' +
                                '<img  class="viewer img-responsive" src="'+t.majorPic+'" alt="报告缩略图">' +
                                '<div class="caption caption-center">' +
                                '<div>'+
                                '<p>' +
                                '<label class="alarm-from">视频通道：</label>' +
                                '<span class="alarm-info" title="'+t.cameraName+'">'+t.cameraName+'</span>' +
                                '</p>' +
                                '<p>' +
                                '<label class="alarm-from">检查项：</label>' +
                                '<span class="alarm-info" title="'+t.itemName+'">'+t.itemName+'</span>' +
                                '</p>' +
                                '<p>' +
                                '<label class="alarm-from">严重程度：</label>' +
                                '<span class="alarm-info" data-value="'+t.severity+'">'+severityStr(t.severity)+'</span>' +
                                '</p>' +
                                '</div>' +
                                '</div>' +
                                '</div>'+
                                '</div>';
                        });
                        html = html + '</div></section>';
                    }
                    $container.append(html);
                }
                $('.alarm-item').each(function (i, item) {
                    var $element = $(item);
                    $element.hover(
                        function () {
                            $element.addClass('animated pulse');
                        },
                        function () {
                            //动画完成之前移除class
                            window.setTimeout(function () {
                                $element.removeClass('animated pulse');
                            }, 2000);
                        });
                });

            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};

/**
 * 查看实时告警详细报告
 */
$("#alarm-container").on("click", '.alarm-item', function (ev) {
    var _this = $(ev.currentTarget);
    Alarm.alarm.url = _this.find("img").attr("src");
    Alarm.alarm.from = _this.find("span:eq(0)").text();
    Alarm.alarm.info = _this.find("span:eq(1)").text();
    Alarm.alarm.time = _this.find(".alarm-time").text();
    $('#alarm-modal').modal('show');
});
$('#alarm-modal').on('show.bs.modal', function (e) {
    var $this = $(this);
    $this.find('img').attr('src', Alarm.alarm.url);
    $this.find(".from").text(Alarm.alarm.from);
    $this.find(".info").text(Alarm.alarm.info);
    $this.find(".alarm-time").text(Alarm.alarm.time);
    $this.click(function () {
        $('#alarm-modal').modal('hide');
    });
}).on('hidden.bs.modal',function () {
    $(this).find('img').attr('src', "");
    $(this).blur();
});

/**
 * 查看运营报告详细报告
 */
$("#report-container").on("click", '.alarm-item', function (ev) {
    var _this = $(ev.currentTarget);
    Alarm.report.url = _this.find("img").attr("src");
    Alarm.report.camera = _this.find("span:eq(0)").text();
    Alarm.report.info = _this.find("span:eq(1)").text();
    Alarm.report.severity = _this.find("span:eq(2)").html();
    $('#report-modal').modal('show');
});

$('#report-modal').on('show.bs.modal', function (e) {
    var $this = $(this);
    $this.find('img').attr('src', Alarm.report.url);
    $this.find(".from").text(Alarm.report.camera);
    $this.find(".info").text(Alarm.report.info);
    $this.find(".alarm-time").html(Alarm.report.severity);
    $this.click(function () {
        $('#report-modal').modal('hide');
    });
}).on('hidden.bs.modal',function () {
    $(this).find('img').attr('src', "");
    $(this).blur();
});

/**
 * 初始化报告选择器
 * @param $tagert
 */
Alarm.initTimePicker = function ($tagert) {
    $tagert.daterangepicker({
        autoUpdateInput: true,
        ranges: {
            '今天': [moment().format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            '昨天': [moment().subtract(1, 'days').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'days').format("YYYY-MM-DD 23:59")],
            '最近一周': [moment().subtract(6, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            '最近30天': [moment().subtract(29, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            '本月': [moment().startOf('month').format("YYYY-MM-DD 00:00"), moment().endOf('month').format("YYYY-MM-DD 23:59")],
            '上月': [moment().subtract(1, 'month').startOf('month').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'month').endOf('month').format("YYYY-MM-DD 23:59")]
        },
        locale: {
            customRangeLabel: '自定义',
            format: 'YYYY-MM-DD HH:mm',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        timePicker: true,
        timePicker24Hour: true,
        startDate: moment().subtract(29, 'days'),
        endDate: moment()
    });
};

/**
 * 过滤实时告警
 */
$(".alarmPicker").on('apply.daterangepicker', function(ev, picker) {
    var startTime = picker.startDate.format('YYYY-MM-DD HH:mm');
    var endTime = picker.endDate.format('YYYY-MM-DD HH:mm');
    Alarm.loadAlarmInfo(startTime, endTime, true);
}).on('cancel.daterangepicker', function(ev, picker) {
    // $('#datepicker').val('');
});

/**
 * 过滤运营报告
 */
$(".reportPicker").on('apply.daterangepicker', function(ev, picker) {
    var startTime = picker.startDate.format('YYYY-MM-DD HH:mm');
    var endTime = picker.endDate.format('YYYY-MM-DD HH:mm');
    Alarm.loadOperateReport(startTime, endTime, true);
}).on('cancel.daterangepicker', function(ev, picker) {
    // $('#datepicker').val('');
});

/**
 * 初始化统计数据选择器
 * @param $target
 */
Alarm.initLayerTimePicker = function ($target) {
    $target.daterangepicker({
        ranges   : {
            '最近30天': [moment().subtract(29, 'days'), moment()],
            '本月'  : [moment().startOf('month'), moment().endOf('month')],
            '上月'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        locale: {
            customRangeLabel: '自定义',
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
    });
};

/**
 * 打开弹出层
 * @param id
 * @param callback
 */
Alarm.openLayer = function (id, callback) {
    var _this = this;
    var maxWidth = window.screen.width * 0.8;
    var maxHeight = window.screen.height * 0.8;
    this.layerIndex = layer.open({
        type: 1,
        title: false,
        area: [maxWidth+'px', maxHeight+'px'], //宽高
        content: $('#'+id).html(),
        success: function (index, layero) {
            $(".chart-container").height(maxHeight-135);
            if(callback){
                callback();
            }
        },
        cancel: function () {
            if(_this.alarmChart){
                _this.alarmChart.destroy();
                _this.alarmChart = null;
            }
            if(_this.reportChart){
                _this.reportChart.destroy();
                _this.reportChart = null;
            }
        },
        end: function () {
            if(_this.alarmChart){
                _this.alarmChart.destroy();
                _this.alarmChart = null;
            }
            if(_this.reportChart){
                _this.reportChart.destroy();
                _this.reportChart = null;
            }
        }
    });
};

/**
 * 实时告警数据统计
 */
Alarm.alarmStatistics = function (event) {
    $(event.target).blur();
    var _this = this;
    var callback = function () {
        _this.initLayerTimePicker($(".alarmDataFilter"));
        _this.initAlarmChart();

        $(".alarmDataFilter").on('apply.daterangepicker', function(ev, picker) {
            var startTime = picker.startDate.format('YYYY-MM-DD');
            var endTime = picker.endDate.format('YYYY-MM-DD');
            _this.updateAlarmChart(startTime, endTime);
        }).on('cancel.daterangepicker', function(ev, picker) {
        });
    };
    this.openLayer("alarmLayer", callback);
};

/**
 * 运营报告数据统计
 */
Alarm.reportStatistics = function (event) {
    $(event.target).blur();
    var _this = this;
    var callback = function () {
        _this.initLayerTimePicker($(".reportDataFilter"));
        _this.initReportChart();

        $(".reportDataFilter").on('apply.daterangepicker', function(ev, picker) {
            var startTime = picker.startDate.format('YYYY-MM-DD');
            var endTime = picker.endDate.format('YYYY-MM-DD');
            _this.updateReportChart(startTime, endTime);
        }).on('cancel.daterangepicker', function(ev, picker) {
        });
    };
    this.openLayer("reportLayer", callback);
};

/**
 * 获取实时告警统计数据
 * @param start
 * @param end
 * @returns {*}
 */
Alarm.getAlarmChartData = function (start, end) {
    var data = null;
    $.ajax({
        type: "GET",
        url: baseURL + "/dataCount/getRealTimeReportDataCount",
        data: {startTime: start, endTime: end},
        async: false,
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                data = json.result;
            }else{
                Feng.info(json.message);
            }
        },
        error: function (err) {
            Feng.error(err);
        }
    });
    return data;
};

/**
 * 获取运营报告统计数据
 * @param start
 * @param end
 * @returns {*}
 */
Alarm.getReportChartData = function (start, end) {
    var data = null;
    $.ajax({
        type: "GET",
        url: baseURL + "/dataCount/getPatrolReportDataCount",
        data: {startTime: start, endTime: end},
        async: false,
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                data = json.result;
            }else{
                Feng.info(json.message);
            }
        },
        error: function (err) {
            Feng.error(err);
        }
    });
    if(data){
        var tops = data.tops.filter(function (item) {
            return item.y>0;
        });
        data.downs.forEach(function (item) {
            var data = item.data.filter(function (t) {
                return t[1]>0;
            });
            item.data = data;
        });
        var downs = data.downs.filter(function (item) {
            return item.data.length>0;
        });
        if(tops.length || downs.length){
            data = {
                tops: tops,
                downs: downs
            }
        }
    }
    return data;
};

/**
 * 初始化实时告警统计图表
 */
Alarm.initAlarmChart = function () {
    var time = $(".alarmDataFilter").val();
    var start = $.trim(time.split("~")[0]);
    var end = $.trim(time.split("~")[1]);
    var data = this.getAlarmChartData(start, end);
    if(!data){
        return;
    }
    this.alarmChart = Highcharts.chart('alarmChart', {
        chart: {
            type: 'line'
        },
        title: {
            text: '实时告警数据统计'
        },
        credits: {
            enabled: false // 禁用版权信息
        },
        xAxis: {
            categories: data.x
        },
        yAxis: {
            title: {
                text: '告警 (次)'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    // 开启数据标签
                    enabled: true
                }
            }
        },
        series: data.y
    });
};

/**
 * 初始化运营报告统计图表
 */
Alarm.initReportChart = function () {
    var time = $(".reportDataFilter").val();
    var start = $.trim(time.split("~")[0]);
    var end = $.trim(time.split("~")[1]);
    var data = this.getReportChartData(start, end);
    if(!data){
        return;
    }
    this.reportChart = Highcharts.chart('reportPie', {
        chart: {
            type: 'pie'
        },
        title: {
            text: '运营报告数据统计'
        },
        credits: {
            enabled: false // 禁用版权信息
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{point.name}: {point.y}'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:12px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
        },
        series: [{
            name: '上一层',
            colorByPoint: true,
            data: data.tops
        }],
        drilldown: {
            series: data.downs
        }
    });
};

/**
 * 更新实时告警统计图表
 * @param start
 * @param end
 */
Alarm.updateAlarmChart = function (start, end) {
    var _this = this;
    var data = _this.getAlarmChartData(start, end);
    if(!data){
        return;
    }
    while(_this.alarmChart.series.length > 0) {
        _this.alarmChart.series[0].remove(false);
    }
    _this.alarmChart.update({
        xAxis: {
            categories: data.x
        }
    }, false);
    $.each(data.y, function (i, item) {
        _this.alarmChart.addSeries(item, false);
    });
    _this.alarmChart.redraw();
};

/**
 * 更新运用报告统计图表
 * @param start
 * @param end
 */
Alarm.updateReportChart = function (start, end) {
    var _this = this;
    var data = this.getReportChartData(start, end);
    if(!data){
        return;
    }
    _this.reportChart.update({
        series: [{
            name: '上一层',
            colorByPoint: true,
            data: data.tops
        }],
        drilldown: {
            series: data.downs
        }
    }, true);
};

$(function () {
    Alarm.initTimePicker($('.datepicker'));
    Alarm.loadAlarmInfo(null, null, false);
    Alarm.loadOperateReport(null, null, false);
    homePageQueryTitle();
});