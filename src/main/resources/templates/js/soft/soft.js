var outletid=null;



$.ajax({
    async:false,
    type: 'get',
    url: "/homePage/homePageQueryTitle",
    dataType: "json",
    //contentType: "application/json",
    // jsonpCallback: "admin_cross",
    success: function (json) {
        console.log(json)
        if(json.code=="200"){
            if (json.result.outletid==5){
                outletid=1

            }
        }
    }
});

var Soft = {
    envType: {
        6: {id: 'PM10', text: 'PM10', name: 'PM10', unit: 'ug/m³'},
        7: {id: 'PM2.5', text: 'PM2.5', name: 'PM2.5', unit: 'ug/m³'},
        8: {id: 'noise', text: '噪声', name: 'noise', unit: 'dB'},
        9: {id: 'temperature', text: '温度', name: 'temperature', unit: '℃'},
        10: {id: 'humidity', text: '湿度', name: 'humidity', unit: '%RH'},
        11: {id: 'windSpeed', text: '风速', name: 'windspeed', unit: 'm/s'},
        13: {id: 'windPower', text: '风力', name: 'windpower', unit: '级'},
    },
    ditchType: {
        1: {id: 'combustible', text: '可燃气', name: 'combustible', unit: 'PPM'},
        2: {id: 'oxygen', text: '氧气', name: 'oxygen', unit: '%'},
        3: {id: 'carbon', text: '一氧化碳', name: 'carbon', unit: 'PPM'},
        4: {id: 'hydrogen', text: '硫化氢', name: 'hydrogen', unit: 'mg/m³'}
    },
    chartOption: {
        title: {
            textStyle: {
                color: '#1493E4',
                fontSize: 14
            },
            top: '3%',
            left: '1.5%'
        },
        legend: {
            show: false
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
    },
    interval: 7  //默认取7天的数据
};
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
                console.log(json)
                $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }
        }
    });
};


$.ajax({
    type: 'get',
    url: "/homePage/homePageQueryTitle",
    dataType: "json",
    //contentType: "application/json",
    // jsonpCallback: "admin_cross",
    success: function (json) {
        if(json.code=="200"){
            console.log(json)
            $(".header-title h1").text(json.result.title);
            $("title").text(json.result.title);
        }
    }
});
/**
 * 打开弹出层
 * @param id
 * @param callback
 */
Soft.openLayer = function (id, title, callback) {
    var maxWidth = window.screen.width * 0.5;
    var maxHeight = window.screen.height * 0.5;
    this.layerIndex = layer.open({
        type: 1,
        title: title,
        area: [maxWidth+'px', 'auto'], //宽高
        maxHeight: maxHeight,
        content: $('#'+id).html(),
        success: function (index, layero) {
            if(typeof callback =="function"){
                callback();
            }
        },
        cancel: function () {

        }
    });
};

/**
 * 获取环境数据
 */
Soft.getEnvData = function (type) {

    var endTime = new Date().getTime();
    var startTime = endTime - (this.interval-1)*24*3600*1000;
    var result = null;

    $.ajax({
        type: "GET",
        url: "/homePage/gas",
        async: false,
        global: false,
        data: {gas_type: type, startTime: parseInt(startTime / 1000), endTime: parseInt(endTime / 1000)},
        dataType: "json",
        success: function (json) {

            if(json.code==200){

                result = json.result;
            }else{
                Feng.error(json.message);
            }
        },
        error: function () {
            Feng.error('请求环境数据异常');
        }
    });
    return result;
};

/**
 * 格式化数据
 * @param result
 * @returns {{x: Array, series: Array}}
 */
Soft.dataFormatter = function (text, result) {
    var series = [], x = [], data = [];
    $.each(result.gas, function (i, item) {
        x.push(item.dtStr);
        data.push(item.val);
    });
    series.push({
        name: text,
        type: 'line',
        smooth: true,
        color: 'lime',
        label: {
            normal: {
                show: true,
                position: 'top'
            }
        },
        data: data
    });
    for(var _threshold in result.threshold){
        if(_threshold=="max_val" || _threshold=="min_val" || _threshold=="identical_val"){
            if(result.threshold[_threshold]){
                var _thresholdVal = JSON.parse(result.threshold[_threshold]);
                for(var key in _thresholdVal){
                    var val = [];
                    for(var timeindex=0;timeindex<this.interval;timeindex++){
                        val.push(key);
                    }
                    series.push({
                        type: 'line',
                        color: _thresholdVal[key],
                        lineStyle: {
                            width: 1,
                            type: 'dotted',
                            opacity: 0.8
                        },
                        symbol: 'none',
                        data: val
                    });
                }
            }
        }
    }
    return {x: x, series: series};
};

/**
 * 初始化环境安全图表
 */
Soft.initEnvPage = function () {

    for(var type in this.envType){

        var data = this.dataFormatter(this.envType[type].text,  this.getEnvData(type));
        this.envType[type].chartInstance = this.drawChart(this.envType[type].id, this.envType[type].text + '('+this.envType[type].unit + ')', data);
    }
};

/**
 * 初始化基坑安全图表
 */
Soft.initDitchPage = function () {
    for(var type in this.ditchType){
        var data = this.dataFormatter(this.ditchType[type].text, this.getEnvData(type));
        this.ditchType[type].chartInstance = this.drawChart(this.ditchType[type].id, this.ditchType[type].text + '('+this.ditchType[type].unit + ')', data);
    }
};

/**
 * 绘制图表
 * @param target
 * @param title
 * @param data
 * @returns {Highcharts.Chart}
 */
Soft.drawChart = function (id, title, data) {
    if (outletid!=null){
        if (title=="PM10(ug/m³)"){
            title="大气压(KPA)"
             data.series[0].name="大气压"
        }
    }
    var option = $.extend(true, {}, this.chartOption, {
        title:{
            text: title
        },
        xAxis: {
            data: data.x
        },
        series: data.series
    });
    var target = document.getElementById(id);
    var chart = echarts.init(target);
    chart.setOption(option);
    return chart;
};

/**
 * 更新图表
 * @param type
 */
Soft.updateChart = function (type) {

    var detectItem = null;
    if(this.envType.hasOwnProperty(type)){
        detectItem = this.envType[type];
    }else if(this.ditchType.hasOwnProperty(type)){
        detectItem = this.ditchType[type];
    }
    var data = this.dataFormatter(detectItem.text, this.getEnvData(type));
    var option = $.extend(true, {}, this.chartOption, {
        title:{
            text: detectItem.text
        },
        xAxis: {
            data: data.x
        },
        series: data.series
    });
    detectItem.chartInstance.setOption(option, true);
};

/**
 * 获取阈值
 * @param type
 */
Soft.getThreshold = function (type) {
    return $.ajax({
        type: "GET",
        url: baseURL + "/threshold/get",
        data: {type: type},
        dataType: "json"
    });
};

/**
 * 设置报警值
 */
Soft.setAlarmValue = function (event) {
    $(event.target).blur();

    var _this = this;
    _this.openLayer("set-alarm-value", "设置报警值", function () {
       $("#detect-select").change(function () {
           var type = $(this).find("option:selected").val();
           $("#threshold_container").empty();
            _this.getThreshold(type).then(function (json) {
                if(json.code===200){
                    if(json.result){
                        _this.initAlarmValue(json.result);
                        _this.threshold = json.result;
                    }
                }else{
                    Feng.error(json.message);
                }
            }, function (err) {
                Feng.error(err);
            });
       });
    });
    if (outletid==1){
        $("#dust").text("粉尘")
    }

};

/**
 * 初始化阈值
 * @param data
 */
Soft.initAlarmValue = function (thresholds) {
    for(var x in thresholds){
        var threshold = null, text = "";
        if(x==="max_val" || x==="min_val" || x==="identical_val"){
            if(thresholds[x]){
                threshold = JSON.parse(thresholds[x]);
                if(x==="max_val"){
                    text = "阈值上限";
                }else if(x==="min_val"){
                    text = "阈值下限";
                }else if(x==="identical_val"){
                    text = "阈值恒等";
                }
                for(var value in threshold){
                    var rnd = "";
                    for(var i=0;i<5;i++)
                        rnd+=Math.floor(Math.random()*10);
                    $("#threshold_container").append('<div id="form-item'+rnd+'" class="form-group" data-type="'+x+'">' +
                        '            <div class="row">' +
                        '                <div class="col-sm-8">' +
                        '                    <label class="control-label">'+text+'</label>' +
                        '                    <input type="number" class="form-control" style="display: inline-block;width: 80%;margin-left: 20px;" value="'+value+'">' +
                        '                </div>' +
                        '                <div class="col-sm-3">' +
                        '                    <div class="input-group">' +
                        '                        <input class="form-control color-picker" name="alarm-color" type="text" value="'+threshold[value]+'" readonly>' +
                        '                        <span class="input-group-addon" style="background-color: '+threshold[value]+'">&nbsp;&nbsp;</span>' +
                        '                    </div>' +
                        '                </div>' +
                        '                <div class="col-sm-1 text-center" style="height: 34px;;line-height: 34px;">' +
                        '                    <i class="fa fa-trash" onclick="$(this).parents(\'.form-group\').remove()" style="color: red;font-size: 16px;"></i>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>');
                    $("#form-item"+rnd).find("input.color-picker").bigColorpicker(function (el, color) {
                        el.value = color;
                        $(el).next().css('background-color', color);
                    },"L",3);
                    $(".layui-layer-content").css({
                        'height': 'auto',
                        'max-height': '500px'
                    });
                }
            }
        }
    }
};

/**
 * 添加报警项
 */
Soft.addAlarmValue = function (event) {
    var _this = event.currentTarget;
    var rnd = "", text = "";
    for(var i=0;i<5;i++)
        rnd+=Math.floor(Math.random()*10);
    switch ($(_this).attr("data-name")){
        case "max_val":
            text = "阈值上限";
            break;
        case "min_val":
            text = "阈值下限";
            break;
        case "identical_val":
            text = "阈值恒等";
            break;
        default: return;
    }
    $("#threshold_container").append('<div id="form-item'+rnd+'" class="form-group" data-type="'+$(_this).attr("data-name")+'">' +
        '            <div class="row">' +
        '                <div class="col-sm-8">' +
        '                    <label class="control-label">'+text+'</label>' +
        '                    <input type="number" class="form-control" style="display: inline-block;width: 80%;margin-left: 20px;" name="alarm-value">' +
        '                </div>' +
        '                <div class="col-sm-3">' +
        '                    <div class="input-group">' +
        '                        <input class="form-control color-picker" name="alarm-color" type="text" readonly placeholder="选择颜色">' +
        '                        <span class="input-group-addon">&nbsp;&nbsp;</span>' +
        '                    </div>' +
        '                </div>' +
        '                <div class="col-sm-1 text-center" style="height: 34px;;line-height: 34px;">' +
        '                    <i class="fa fa-trash" onclick="$(this).parents(\'.form-group\').remove()" style="color: red;font-size: 16px;"></i>' +
        '                </div>' +
        '            </div>' +
        '        </div>');
    $("#form-item"+rnd).find("input.color-picker").bigColorpicker(function (el, color) {
        el.value = color;
        $(el).next().css('background-color', color);
    },"L",3);
    $(".layui-layer-content").css({
        'height': 'auto',
        'max-height': '500px'
    });
};

/**
 * 保持阈值设置
 */
Soft.saveAlarmValue = function () {
    var max_val = new Object(), min_val = new Object(), identical_val = new Object(), _this = this;
    if(!_this.threshold){
        layer.close(_this.layerIndex);
        return;
    }
    $.each($("#threshold_container").children(), function (i, item) {
        var type = $(item).attr("data-type");
        var inputs = $(item).find("input");
        var thresholdVal = $(inputs[0]).val();
        var color = $(inputs[1]).val();
        if(thresholdVal.trim() && color){
            if(type==="max_val"){
                max_val[thresholdVal] = color;
            }else if(type==="min_val"){
                min_val[thresholdVal] = color;
            }else if(type==="identical_val"){
                identical_val[thresholdVal] = color;
            }
        }
    });
    _this.threshold.max_val = JSON.stringify(max_val);
    _this.threshold.min_val = JSON.stringify(min_val);
    _this.threshold.identical_val = JSON.stringify(identical_val);
    _this.threshold.type = _this.threshold.type || $("#detect-select").find("option:selected").val();
    $.ajax({
        type: "POST",
        url: baseURL + "/threshold/update",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(_this.threshold),
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            _this.updateChart(_this.threshold.type.split('_')[1]);
            _this.threshold = null;
            layer.close(_this.layerIndex);
            Feng.success("设置成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error("更新阈值设置异常");
    });
};

$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
    if($(e.target).attr("href")==="#environmentSoft") {
        for(var type in Soft.envType){
            Soft.envType[type].chartInstance.resize();
        }
    } else if($(e.target).attr("href")==="#ditchSoft") {
        for(var type in Soft.ditchType){
            Soft.ditchType[type].chartInstance.resize();
        }
    }
});

$(function () {
    Soft.initEnvPage();
    Soft.initDitchPage();
    homePageQueryTitle();
    if (outletid!=null){

       $("#noise").remove()
        var endTime = new Date().getTime();
        var startTime = endTime - 6*24*3600*1000;
        console.log(endTime)
        $.ajax({
            type: "GET",
            url: "/homePage/gas",
            async: false,
            global: false,
            data: {gas_type: "12", startTime: parseInt(startTime / 1000), endTime: parseInt(endTime / 1000)},
            dataType: "json",
            success:function(json){
                console.log(json)
                var date=json.result.gas
                console.log(date)
                $(".tables").show()
                $.each(date,function (index, value) {
                    if(value.val==null){
                        value.val=0
                    }
                    console.log(value.dtStr)
                    $("#biaodan").append("<tr>" +
                        "<td>"+value.dtStr+"</td>" +
                        "<td>"+value.val+"</td>" +
                        "</tr>")
                })

            }
        })

    }
});
