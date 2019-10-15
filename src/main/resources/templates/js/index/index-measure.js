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
                $(".header-title").text(json.result.title+"-实时监测");

            }

        }
    });
};


var MeasureModule = {
    $container: $("#image-container"),
    chartOptions: {
        title: {
            text: '',
            textStyle: {
                color: '#efefef',
                fontSize: 12
            },
            top: '5%',
            left: '20'
        },
        legend: {
            textStyle: {
                color: '#1493E4',
                fontSize: 12
            },
            show: true,
            top: '5%',
            right: 0
        },
        grid: null,
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
            },
            axisLabel: {
                fontSize: 10
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLabel: {
                fontSize: 10
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
    init: function () {
        this.$container.find('#image').unbind().remove();
        var _this = this;
        $.ajax({
            url: '/settlement/querySettleScreen',
            type: "POST",
            dataType: 'json'
        }).then(function (json) {
            if(json.code==200){
                if(json.result.length){
                    var data = json.result[0];
                    _this.$container.find('p').remove();
                    _this.$container.find('#image').unbind().remove();
                    $('<div id="image" data-id="'+data.img.id+'"></div>').appendTo(_this.$container);
                    var emap = new Image();
                    emap.onload = function () {
                        _this.drawer = $("#image").width(this.naturalWidth).height(this.naturalHeight).css({
                            'background': 'url("'+this.src+'") left top no-repeat',
                            'background-size': '100% 100%'
                        }).draggable({ cursor: "move", start: function() {
                            $(this).css('transform', "translate(0, 0)");
                        }}).bind("mousewheel", function (ev) {
                            var nextwidth, nextheight, $_this = $(this);
                            var prewidth = $_this.width();
                            var preheight = $_this.height();
                            var $container = $_this.parent();
                            var container_width = $container.width();
                            var container_height = $container.height();
                            var wheelDelta = ev.originalEvent.wheelDelta;
                            if(wheelDelta>0){
                                nextwidth = prewidth*1.2;
                                nextheight = preheight*1.2;
                            }else{
                                nextwidth = prewidth*0.8;
                                nextheight = preheight*0.8;
                                if(nextwidth<=container_width || nextheight<=container_height){
                                    var width_ratio = container_width/prewidth;
                                    var height_ratio = container_height/preheight;
                                    if(width_ratio>height_ratio){
                                        var ratio1 = prewidth/preheight;
                                        nextwidth = container_height*ratio1;
                                        nextheight = container_height;
                                    }else{
                                        var ratio2 = preheight/prewidth;
                                        nextwidth = container_width;
                                        nextheight = container_width*ratio2;
                                    }
                                }
                            }
                            $_this.width(nextwidth).height(nextheight).css({'left': '50%','top': '50%', 'transform': 'translate(-50%, -50%)'});
                            $.each($_this.find('i'), function (i, t) {
                                var _this = $(t);
                                var offsetX = nextwidth*_this.data('offsetxp'), offsetY = nextheight*_this.data('offsetyp');
                                _this.css({
                                    left: (offsetX-10)+'px',
                                    top: (offsetY-10)+'px'
                                });
                            });
                        });
                        _this.drawPoint(data.points);
                    };
                    emap.onerror = function () {
                        Feng.error("工程图加载失败");
                    };
                    emap.src = data.img.url;
                }else{
                    $('<p class="empty-drawing-tips">无工程图纸</p>').appendTo(this.$container);
                }
            }else{
                Feng.error(json.message);
            }
        },function () {
            Feng.error('请求图片资源异常');
        });
    },
    drawPoint: function (points) {
        var _this = this;
        var drawerWidth = this.drawer.width(), drawerHeight = this.drawer.height();
        points.forEach(function (t) {
            var point = $.extend(true, {}, t, {id: 'point-'+t.id, offsetX: t.x*drawerWidth, offsetY: t.y*drawerHeight});
            var pointStr = _this.pointAdapter(point);
            $(pointStr).appendTo(_this.drawer);
            _this.initTips($('#'+point.id), '<div id="measure-tips-content-'+point.locationno+'" style="width: 6rem;height: 4.4rem;"></div>', _this.onOpen, _this.onClose.bind(_this));
        });
    },
    pointAdapter: function (point) {
        var marker = '', status = '';
        var drawerWidth = this.drawer.width(), drawerHeight = this.drawer.height(), radius = 10;
        var offsetXP = point.offsetX/drawerWidth, offsetYP = point.offsetY/drawerHeight;
        switch (point.alarm.toString()){
            case '0': status = 'normal-state';
                break;
            case '1': status = 'warning';
                break;
            case '2': status = 'warningX2';
                break;
            case '3': status = 'warningX3';
                break;
            default: status = 'normal-state';
                break;
        }
        switch (point.code.toString()){
            case 'DBC': marker = '<i id="'+point.id+'" class="iconfont icon-dibiaochenjiang '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'ZCL': marker = '<i id="'+point.id+'" class="iconfont icon-zhichengzhouli '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'JGC': marker = '<i id="'+point.id+'" class="iconfont icon-jianzhuchenjiang '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'ZQC': marker = '<i id="'+point.id+'" class="iconfont icon-qiangdinggaibanlizhuchenjiang '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'ZQS': marker = '<i id="'+point.id+'" class="iconfont icon-qiangdingweiyi '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'GBC': marker = '<i id="'+point.id+'" class="iconfont icon-qiangdinggaibanlizhuchenjiang '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'LZC': marker = '<i id="'+point.id+'" class="iconfont icon-qiangdinggaibanlizhuchenjiang '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'DSW': marker = '<i id="'+point.id+'" class="iconfont icon-kengwaishuiwei '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'ZQT': marker = '<i id="'+point.id+'" class="iconfont icon-qiangtituticexie '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
            case 'TST': marker = '<i id="'+point.id+'" class="iconfont icon-qiangtituticexie '+status+'" data-type="'+point.type+'" data-code="'+point.code+'" data-offsetxp="'+offsetXP+'" data-offsetyp="'+offsetYP+'" style="position: absolute;left: '+(point.offsetX-radius)+'px;top: '+(point.offsetY-radius)+'px;">'+point.locationno+'</i>';
                break;
        }
        return marker;
    },
    getPointInfo: function (url, locationno) {
        return $.ajax({
            type: 'POST',
            url: url,
            data: {locationno: locationno},
            dataType: 'json'
        });
    },
    initTips: function (target, content, onOpen, onClose) {
        return new jBox('Tooltip', {
            attach: target,
            content: content,
            animation: 'pulse',
            closeOnMouseleave: true,
            delayOpen: 1,
            onOpen: onOpen,
            onClose: onClose
        });
    },
    onOpen: function () {
        var locationno = this.source.text();
        var $tipsContent = $("#measure-tips-content-"+locationno).empty();
        switch (this.source.data('type').toString()) {
            case '1': MeasureModule.renderSettlement(locationno, $tipsContent);
                break;
            case '2': MeasureModule.renderInclinometer(locationno, $tipsContent);
                break;
            case '3': MeasureModule.renderSettlement(locationno, $tipsContent);
                break;
            case '4': MeasureModule.renderAxialForce(locationno, $tipsContent);
                break;
            case '5': MeasureModule.renderWaterLevel(locationno, $tipsContent);
                break;
            default: MeasureModule.renderSettlement(locationno, $tipsContent);
                break;
        }
    },
    onClose: function () {
        $("#measure-tips-content").empty();
    },
    renderSettlement: function (locationno, $tipsContent) {
        var _this = this;
        this.getPointInfo('/settlement/querySettleScreenPopupWindow', locationno).then(function (json) {
            if(json.code==200){
                $tipsContent.height('5.5rem');
                var settlementData = json.result;
                $('<div style="display: flex;flex-flow: nowrap row;justify-content: space-around;align-items: center;margin: 15px 0;"></div>').html('<div style="color: #efefef;"><span>本次变量</span><span style="display: inline-block;padding: 3px 8px;background-color: rgba(20,147,228, 0.5);margin-left: 10px;font-size: 0.16rem;border-radius: 4px;">'+settlementData.thevar+'mm</span></div><div style="color: #efefef;"><span>累计变量</span><span style="display: inline-block;padding: 3px 8px;background-color: rgba(20,147,228, 0.5);margin-left: 10px;font-size: 0.16rem;border-radius: 4px;">'+settlementData.cumulativevar+'mm</span></div>').appendTo($tipsContent);
                $('<div id="chart1-'+locationno+'" style="width: 100%;height: 2.5rem;"></div>').appendTo($tipsContent);
                $('<div id="chart2-'+locationno+'" style="width: 100%;height: 2.5rem;"></div>').appendTo($tipsContent);
                var chartOption1 = $.extend(true, {}, _this.chartOptions, {title: {text: '本次变量曲线图'},xAxis:{data: settlementData.dates}, series: [{type: 'line', smooth: true, data: settlementData.currents, color: '#1493E4'}]});
                var chartOption2 = $.extend(true, {}, _this.chartOptions, {title: {text: '累计变量曲线图'},xAxis:{data: settlementData.dates}, series: [{type: 'line', smooth: true, data: settlementData.accumulates, color: '#1493E4'}]});
                var chart1 = echarts.init(document.getElementById('chart1-'+locationno));
                var chart2 = echarts.init(document.getElementById('chart2-'+locationno));
                chart1.setOption(chartOption1);
                chart2.setOption(chartOption2);
            }else{
                Feng.error(json.message);
            }
        }, function () {
            Feng.error('请求沉降数据异常');
        });
    },
    renderInclinometer: function (locationno, $tipsContent) {
        var _this = this;
        this.getPointInfo('/settlement/querySettleScreenPopupWindowSite', locationno).then(function (json) {
            if(json.code==200){
                $tipsContent.height('3rem');
                var inclinometerData = json.result;
                var chartOption = $.extend(true, {}, _this.chartOptions, {title: {text: ''}, xAxis:{name: '深度/米', data: inclinometerData.dept}, series: [{type: 'line', smooth: true, data: inclinometerData.accumulates, color: '#1493E4'}]});
                $('<div id="chart-'+locationno+'" style="width: 100%;height: 3rem;"></div>').appendTo($tipsContent);
                var chart = echarts.init(document.getElementById('chart-'+locationno));
                chart.setOption(chartOption);
            }else{
                Feng.error(json.message);
            }
        }, function () {
            Feng.error('请求测斜数据异常');
        });
    },
    renderAxialForce: function (locationno, $tipsContent) {
        var _this = this;
        _this.getPointInfo('/settlement/querySettleScreenPopupWindowAxialforce', locationno).then(function (json) {
            if(json.code==200){
                var axialForceData = json.result;
                var tbody = '<tbody>';
                axialForceData.header.forEach(function (t) {
                    tbody = tbody + '<tr><td>'+t.name+'</td><td>'+t.last+'</td><td>'+t.curr+'</td><td>'+t.currentvariation+'</td></tr>';
                });
                tbody = tbody + '</tbody>';
                $('<table class="table table-striped"><thead><tr><th>点位编号</th><th>上次轴力</th><th>本次轴力</th><th>本次变化量</th></tr></thead>'+tbody+'</table>').appendTo($tipsContent);
                axialForceData.curveline.series.forEach(function (t) {
                    $.extend(t, {type: 'line', smooth: true});
                });
                var chartOption = $.extend(true, {}, _this.chartOptions, {title: {text: '本次变量曲线图'},xAxis:{data: axialForceData.curveline.date}, series: axialForceData.curveline.series});
                $('<div id="chart-'+locationno+'" style="width: 100%;height: 3rem;"></div>').appendTo($tipsContent);
                var chart = echarts.init(document.getElementById('chart-'+locationno));
                chart.setOption(chartOption);
            }else{
                Feng.error(json.message);
            }
        }, function () {
            Feng.error('请求轴力数据异常');
        });
    },
    renderWaterLevel: function (locationno, $tipsContent) {
        var _this = this;
        this.getPointInfo('/settlement/querySettleScreenPopupWindowWater', locationno).then(function (json) {
            if(json.code==200){
                $tipsContent.height('3.4rem');
                var waterLevelData = json.result;
                $('<div style="display: flex;flex-flow: nowrap row;justify-content: space-around;align-items: center;margin: 15px 0;"></div>').html('<div style="color: #efefef;"><span>本次变量</span><span style="display: inline-block;padding: 3px 8px;background-color: rgba(20,147,228, 0.5);margin-left: 10px;font-size: 0.16rem;border-radius: 4px;">'+waterLevelData.currents+'mm</span></div><div style="color: #efefef;"><span>累计变量</span><span style="display: inline-block;padding: 3px 8px;background-color: rgba(20,147,228, 0.5);margin-left: 10px;font-size: 0.16rem;border-radius: 4px;">'+waterLevelData.accumulates+'mm</span></div>').appendTo($tipsContent);
                var chartOption = $.extend(true, {}, _this.chartOptions, {title: {text: '本次变量曲线图'},xAxis:{data: waterLevelData.x}, series: [{type: 'line', smooth: true, data: waterLevelData.y, color: '#1493E4'}]});
                $('<div id="chart-'+locationno+'" style="width: 100%;height: 3rem;"></div>').appendTo($tipsContent);
                var chart = echarts.init(document.getElementById('chart-'+locationno));
                chart.setOption(chartOption);
            }else{
                Feng.error(json.message);
            }
        }, function () {
            Feng.error('请求水位数据异常');
        });
    }
};
/**
 * 平台标题
 * @param data
 */
var homePageQueryTitles = function () {
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

$(function () {
    homePageQueryTitle();
    homePageQueryTitles();
    MeasureModule.init();
});