var CarMager_count = {
    numberCount: {
        id: "numberCount",
        option: {
            title : {
                text: '车辆类型统计',
                textStyle: {
                    color: '#1493E4',
                    fontSize: 16
                },
                x: 'center',
                top: '3%'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series : [
                {
                    name: '工地车辆',
                    type: 'pie',
                    radius : '60%',
                    center: ['50%', '50%'],
                    label: {
                        normal: {
                            formatter: '  {b|{b}：}{c}  {per|{d}%}  ',
                            backgroundColor: 'white',
                            borderColor: '#aaa',
                            borderWidth: 0,
                            borderRadius: 4,
                            rich: {
                                a: {
                                    color: '#999',
                                    lineHeight: 22,
                                    align: 'center'
                                },
                                hr: {
                                    borderColor: '#aaa',
                                    width: '100%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                b: {
                                    fontSize: 16,
                                    lineHeight: 33
                                },
                                per: {
                                    color: '#eee',
                                    backgroundColor: '#334455',
                                    padding: [2, 4],
                                    borderRadius: 2
                                }
                            }
                        }
                    },
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        }
    },
    accessTypeCount: {
        id: "accessTypeCount",
        option: {
            title: {
                text: '进出车辆类型统计',
                textStyle: {
                    color: '#1493E4',
                    fontSize: 16
                },
                x: 'center',
                top: '3%'
            },
            tooltip: {
                trigger: 'axis'
            },
            color: ['#D7C459','#4BCA37'],
            legend: {
                data: [{name: '进场'}, {name: '出场'}],
                bottom: '3%'
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                }
            },
            yAxis: {
                type: 'value',
                minInterval: 1,
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                }
            }
        }
    },
    companyNumCount: {
        id: "companyNumCount",
        option: {
            title: {
                text: '进出辆数统计',
                textStyle: {
                    color: '#1493E4',
                    fontSize: 16
                },
                x: 'center',
                top: '3%'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'value',
                show: false
            },
            yAxis: {
                type: 'category',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            },
            series: [
                {
                    type: 'bar',
                    barWidth: 20,
                    barCategoryGap: '20%',
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            color: '#1493E4'
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    }
                }
            ]
        }
    },
    accessNumCount: {
        id: "accessNumCount",
        option: {
            title: {
                text: '进出场统计',
                textStyle: {
                    color: '#1493E4',
                    fontSize: 16
                },
                x: 'center',
                top: '3%'
            },
            color: ['#D7C459','#4BCA37'],
            legend: {
                data: [{name: '进场'}, {name: '出场'}],
                bottom: '3%'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                }
            },
            yAxis: {
                type: 'value',
                minInterval: 1,
                axisTick: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: '#1493E4',
                        width: 0.5,
                        opacity: 0.5
                    }
                }
            }
        }
    },
    companyTimesCount: {
        id: "companyTimesCount",
        option: {
            title: {
                text: '进出趟数统计',
                textStyle: {
                    color: '#1493E4',
                    fontSize: 16
                },
                x: 'center',
                top: '3%'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                type: 'value',
                show: false
            },
            yAxis: {
                type: 'category',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            },
            series: [
                {
                    type: 'bar',
                    barWidth: 20,
                    barCategoryGap: '20%',
                    label: {
                        normal: {
                            show: true,
                            position: 'right',
                            color: '#1493E4'
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    }
                }
            ]
        }
    }
};

/**
 * 获取车辆类型数据
 */
CarMager_count.getNumberCount_data = function () {
    return $.ajax({
        url: '/vehicle/queryVehicleCount',
        type: 'GET',
        cache: false,
        dataType: 'json'
    });
};
/**
 * 初始化工地车辆类型数据统计
 */
CarMager_count.initNumberCount_Charts = function () {
    var _this = this;
    _this.getNumberCount_data().then(function (json) {
        if(json.code==200){
            _this.numberCount.option.series[0].data = json.result.VehicleTypeCount;
            _this.numberCount.chart = echarts.init(document.getElementById( _this.numberCount.id));
            _this.numberCount.chart.setOption(_this.numberCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};
/**
 * 更新工地车辆类型数据统计
 */
CarMager_count.updateNumberCount_Charts = function () {
    var _this = this;
    _this.getNumberCount_data().then(function (json) {

        if(json.code==200){
            _this.numberCount.option.series[0].data = json.result.VehicleTypeCount;
            _this.numberCount.chart.setOption(_this.numberCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

/**
 * 获取今日进出车辆统计数据
 * @param flag
 */
CarMager_count.getAccessTypeCount_data = function (flag) {
    return $.ajax({
        url: '/vehicle/queryToDayInOutCount',
        type: 'GET',
        cache: false,
        data: {flag: flag},
        dataType: 'json'
    });
};
/**
 * 初始化今日进出车辆统计
 * @param flag
 */
CarMager_count.initAccessTypeCount_Charts = function (flag) {
    var ele = document.getElementById(this.accessTypeCount.id), _this = this;
    _this.getAccessTypeCount_data(flag).then(function (json) {
        if(json.code==200){
            var series = new Array();
            var y = json.result.y[0];
            for(var param in y){
                var color = "";
                if(param=="entry"){
                    color = '#D7C459';
                }else{
                    color = '#4BCA37'
                }
                series.push({
                    name: y[param].name,
                    type:'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            color: color
                        }
                    },
                    lineStyle: {
                        width: 2,
                        color: color
                    },
                    data: y[param].data
                });
            }
            _this.accessTypeCount.option.xAxis.data = json.result.x;
            _this.accessTypeCount.option.series = series;
            _this.accessTypeCount.chart = echarts.init(ele);
            _this.accessTypeCount.chart.setOption(_this.accessTypeCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
    $(ele).prev().on('click', 'span:not(.active)', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var flag = $(this).data('value');
        _this.updateAccessTypeCount_Charts(flag);
    });
};
/**
 * 更新今日进出车辆统计
 * @param flag
 */
CarMager_count.updateAccessTypeCount_Charts = function (flag) {
    var _this = this;
    _this.getAccessTypeCount_data(flag).then(function (json) {
        if(json.code==200){
            var series = new Array();
            var y = json.result.y[0];
            for(var param in y){
                var color = "";
                if(param=="entry"){
                    color = '#D7C459';
                }else{
                    color = '#4BCA37'
                }
                series.push({
                    name: y[param].name,
                    type:'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            color: color
                        }
                    },
                    lineStyle: {
                        width: 2,
                        color: color
                    },
                    data: y[param].data
                });
            }
            _this.accessTypeCount.option.xAxis.data = json.result.x;
            _this.accessTypeCount.option.series = series;
            _this.accessTypeCount.chart.setOption(_this.accessTypeCount.option, true);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};



/**
 * 获取公司进出车辆统计数据
 * @param flag
 */
CarMager_count.getCompanyNumCount_data = function (flag) {
    return $.ajax({
        url: '/vehicle/queryVehicleCompanyToDayInOutCount',
        type: 'GET',
        cache: false,
        data: {flag: flag},
        dataType: 'json'
    });
};
/**
 * 初始化公司进出车辆统计
 * @param flag
 */
CarMager_count.initCompanyNumCount_charts = function (flag) {
    var ele = document.getElementById(this.companyNumCount.id), _this = this;
   _this.getCompanyNumCount_data(flag).then(function (json) {
        if(json.code==200){
            _this.companyNumCount.option.yAxis.data = json.result.y;
            _this.companyNumCount.option.series[0].data = json.result.x;
            _this.companyNumCount.chart = echarts.init(document.getElementById(_this.companyNumCount.id));
            _this.companyNumCount.chart.setOption(_this.companyNumCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
    $(ele).prev().on('click', 'span:not(.active)', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var flag = $(this).data('value');
        _this.updateCompanyNumCount_charts(flag);
    });
};
/**
 * 更新公司进出车辆统计
 * @param flag
 */
CarMager_count.updateCompanyNumCount_charts = function (flag) {
    var _this = this;
    _this.getCompanyNumCount_data(flag).then(function (json) {
        if(json.code==200){
            _this.companyNumCount.option.yAxis.data = json.result.y;
            _this.companyNumCount.option.series[0].data = json.result.x;
            _this.companyNumCount.chart.setOption(_this.companyNumCount.option, true);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};



/**
 * 获取工地车辆进出统计数据
 * @param starttime
 * @param endtime
 */
CarMager_count.getAccessNumCount_data = function (starttime, endtime) {
    return $.ajax({
        url: '/vehicle/queryVehicleInOutCount',
        type: 'GET',
        cache: false,
        data: {starttime: starttime, endtime: endtime},
        dataType: 'json'
    });
};
/**
 * 初始化工地车辆进出统计
 * @param starttime
 * @param endtime
 */
CarMager_count.initAccessNumCount_charts = function (starttime, endtime) {
    var ele = document.getElementById(this.accessNumCount.id), _this = this;
    _this.getAccessNumCount_data(starttime, endtime).then(function (json) {
        if(json.code==200){
            var series = new Array();
            var y = json.result.y[0];
            for(var param in y){
                var color = "";
                if(param=="entry"){
                    color = '#D7C459';
                }else{
                    color = '#4BCA37'
                }
                series.push({
                    name: y[param].name,
                    type:'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            color: color
                        }
                    },
                    lineStyle: {
                        width: 2,
                        color: color
                    },
                    data: y[param].data
                });
            }
            _this.accessNumCount.option.xAxis.data = json.result.x;
            _this.accessNumCount.option.series = series;
            _this.accessNumCount.chart = echarts.init(document.getElementById(_this.accessNumCount.id));
            _this.accessNumCount.chart.setOption(_this.accessNumCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
    $(ele).prev().find('input.datepicker').daterangepicker({
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
            format: 'YYYY-MM-DD',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        timePicker: false,
        timePicker24Hour: true,
        startDate: moment().subtract(6, 'days'),
        endDate: moment(),
        opens: 'left'
    }).on('apply.daterangepicker', function(ev, picker) {
        var startTime = picker.startDate.format('YYYY-MM-DD');
        var endTime = picker.endDate.format('YYYY-MM-DD');
        _this.updateAccessNumCount_charts(startTime, endTime);
    });
};
/**
 * 更新工地车辆进出统计
 * @param starttime
 * @param endtime
 */
CarMager_count.updateAccessNumCount_charts = function (starttime, endtime) {
    var _this = this;
    _this.getAccessNumCount_data(starttime, endtime).then(function (json) {
        if(json.code==200){
            var series = new Array();
            var y = json.result.y[0];
            for(var param in y){
                var color = "";
                if(param=="entry"){
                    color = '#D7C459';
                }else{
                    color = '#4BCA37'
                }
                series.push({
                    name: y[param].name,
                    type:'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                            color: color
                        }
                    },
                    lineStyle: {
                        width: 2,
                        color: color
                    },
                    data: y[param].data
                });
            }
            _this.accessNumCount.option.xAxis.data = json.result.x;
            _this.accessNumCount.option.series = series;
            _this.accessNumCount.chart.setOption(_this.accessNumCount.option, true);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};



/**
 * 获取公司车辆进出趟数统计数据
 * @param starttime
 * @param endtime
 */
CarMager_count.getCompanyTimesCount_data = function (starttime, endtime) {
    return $.ajax({
        url: '/vehicle/queryVehicleCompanyInOutCount',
        type: 'GET',
        cache: false,
        data: {starttime: starttime, endtime: endtime},
        dataType: 'json'
    });
};
/**
 * 初始化公司车辆进出趟数统计
 * @param starttime
 * @param endtime
 */
CarMager_count.initCompanyTimesCount_charts = function (starttime, endtime) {
    var ele = document.getElementById(this.companyTimesCount.id), _this = this;
    _this.getCompanyTimesCount_data(starttime, endtime).then(function (json) {
        if(json.code==200){
            _this.companyTimesCount.option.yAxis.data = json.result.y;
            _this.companyTimesCount.option.series[0].data = json.result.x;
            _this.companyTimesCount.chart = echarts.init(document.getElementById(_this.companyTimesCount.id));
            _this.companyTimesCount.chart.setOption(_this.companyTimesCount.option);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
    $(ele).prev().find('input.datepicker').daterangepicker({
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
            format: 'YYYY-MM-DD HH:mm:ss',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        timePicker: false,
        timePicker24Hour: true,
        startDate: moment().subtract(6, 'days'),
        endDate: moment(),
        opens: 'left'
    }).on('apply.daterangepicker', function(ev, picker) {
        var startTime = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
        var endTime = picker.endDate.format('YYYY-MM-DD HH:mm:ss');
        _this.updateCompanyTimesCount_charts(startTime, endTime);
    });
};
/**
 * 更新公司车辆进出趟数统计
 * @param starttime
 * @param endtime
 */
CarMager_count.updateCompanyTimesCount_charts = function (starttime, endtime) {
    var _this = this;
    _this.getCompanyTimesCount_data(starttime, endtime).then(function (json) {
        if(json.code==200){
            _this.companyTimesCount.option.yAxis.data = json.result.y;
            _this.companyTimesCount.option.series[0].data = json.result.x;
            _this.companyTimesCount.chart.setOption(_this.companyTimesCount.option, true);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

window.onresize = function () {
    CarMager_count.accessTypeCount.chart.resize();
    CarMager_count.numberCount.chart.resize();
    CarMager_count.companyNumCount.chart.resize();
    CarMager_count.accessNumCount.chart.resize();
    CarMager_count.companyTimesCount.chart.resize();
};

$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
    if($(e.target).attr("href")==="#vehicle-count") {
        CarMager_count.updateNumberCount_Charts();
        CarMager_count.numberCount.chart.resize();
        CarMager_count.accessTypeCount.chart.resize();
        CarMager_count.companyNumCount.chart.resize();
        CarMager_count.accessNumCount.chart.resize();
        CarMager_count.companyTimesCount.chart.resize();
    }
});

$(function () {
    var date = new Date();
    var forDate = new Date(date.getTime()-6*24*3600*1000);
    CarMager_count.initNumberCount_Charts();
    CarMager_count.initAccessTypeCount_Charts(0);
    CarMager_count.initCompanyNumCount_charts(0);
    CarMager_count.initAccessNumCount_charts(forDate.format('yyyy-MM-dd'), date.format('yyyy-MM-dd'));
    CarMager_count.initCompanyTimesCount_charts(forDate.format('yyyy-MM-dd hh:mm:ss'), date.format('yyyy-MM-dd hh:mm:ss'));
});