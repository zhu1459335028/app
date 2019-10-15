var DitchModule = {
    jbox: new Array(),
    update: function (data) {
        this.jbox.forEach(function (t) {
            t.destroy();
        });
        /**
         * 测量测绘
         */
        $('#measure-module').find('.measure-state').remove();
        if(data.settlestatus==0){
            $('#measure-module').append('<span class="label label-success measure-state">正常</span>');
        }else if(data.settlestatus==1){
            $('#measure-module').append('<span class="label label-danger measure-state">报警</span>');
        }
        this.jbox.push(this.initTips($('#measure-module .measure-state'), '<div id="measure-content" style="width: 7rem;height: 4rem;"></div>', this.measureModule.onOpen.bind(this.measureModule), this.measureModule.onClose.bind(this.measureModule)));
        /**
         * 在坑人数
         * @type {string}
         */
        $("#ditch-header").attr('src', data.url || '../image/worker/worker1.jpg');
        var inDitch = data.number.toString();
        var inDitchHtml = "";
        for(var i = 0;i<inDitch.length;i++){
            inDitchHtml = inDitchHtml + '<span class="fontx5" style="background-color: rgba(20,147,228,0.2);padding: 0 8px;color: yellow;border-radius: 2px;">'+inDitch.charAt(i)+'</span>';
        }
        $("#inDitch").html(inDitchHtml);
        /**
         * 基坑气体和水位
         * @type {string}
         */
        var ditchHtml = "";
        data.found.forEach(function (t) {
            var gasState = '<span data-ditchId="'+t.foundid+'" class="label label-success ditch-gas">正常</span>';
            var waterState = '<span data-ditchId="'+t.foundid+'" class="label label-success ditch-water">正常</span>';
            if(t.noxiousgasstatus==1){
                gasState = '<span data-ditchId="'+t.foundid+'" class="label label-danger ditch-gas">报警</span>';
            }
            if(t.wterlevelstatus==1){
                waterState = '<span data-ditchId="'+t.foundid+'" class="label label-danger ditch-water">报警</span>';
            }
            ditchHtml = ditchHtml + '<div id="'+t.foundid+'">' +
                                        '<p class="white-font fontx2"><i class="iconfont icon-map-tunnel"></i>'+t.foundnamne+'</p>' +
                                        '<div style="height: 100%;margin-left: 5%;">' +
                                            '<div class="between-flex white-font fontx2" style="line-height: 1.8;">' +
                                                '<span class="decorationLable"><i class="iconfont icon-jianzhuanquan-"></i>有害气体</span>' +
                                                '<span>'+t.noxiousgas+'</span>' +
                                                gasState +
                                            '</div>' +
                                            '<div class="between-flex white-font fontx2" style="line-height: 1.8;">' +
                                              '<span class="decorationLable"><i class="iconfont icon-shuiweichuanganqi_o"></i>水位值</span>' +
                                              '<span>'+t.wterlevel+'</span>' +
                                               waterState +
                                            '</div>' +
                                                // '<div class="between-flex white-font fontx2" style="display:none;">' +
                                                // '<span class="decorationLable"><i class="iconfont icon-shuiweichuanganqi_o"></i>水位值</span>' +
                                                // '<span>'+t.wterlevel+'</span>' +
                                                // waterState +
                                                // '</div>' +
                                        '</div>' +
                                    '</div>';
        });
        $("#ditch-container").html(ditchHtml);
        for(var i=0;i<$('#ditch-container .ditch-water').length;i++){
            var ditchId = $('#ditch-container .ditch-water:eq("'+i+'")').data('ditchid');
            this.jbox.push(this.initTips($('#ditch-container .ditch-water:eq("'+i+'")'), '<div id="ditchWater-content-'+ditchId+'" style="width: 2rem;height: 2rem;"></div>', this.ditchWater.onOpen, this.ditchWater.onClose.bind(this.ditchWater)));
            this.jbox.push(this.initTips($('#ditch-container .ditch-gas:eq("'+i+'")'), '<div class="around-flex" id="ditchGas-content-'+ditchId+'" style="width: 4.5rem;height: 0.8rem;"></div>', this.ditchGas.onOpen, this.ditchGas.onClose.bind(this.ditchGas)));
        }
    },
    initTips: function (target, content, onOpen, onClose) {
        return new jBox('Tooltip', {
            attach: target,
            trigger: 'click',
            content: content,
            target: target,
            position: {
                x: 'right',
                y: 'center'
            },
            offset: {
                x: 0,
                y: 0
            },
            outside: 'x',
            pointer: 'center: 5',
            animation: 'pulse',
            closeOnClick: 'body',
            delayOpen: 1,
            onOpen: onOpen,
            onClose: onClose
        });
    },
    measureModule: {
        measureTipsChartId: "measure-tips-chart",
        interval: 7,
        chartOptions: {
            legend: {
                textStyle: {
                    color: '#1493E4',
                    fontSize: 12
                },
                show: true
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
        getMeasureData: function () {
            var endTime = new Date('2018-12-27').getTime();
            var startTime = endTime - this.interval*3*24*3600*1000;
            return $.ajax({
                type: "GET",
                url: "/homePage/settlement",
                data: {startTime: parseInt(startTime/1000), endTime: parseInt(endTime/1000)},
                dataType: "json"
            });
        },
        onOpen: function () {
            var _this = this;
            var $container = $("#measure-content").empty();
            var measureData = new Object(), html = "", series = [], x = [], ayy = new Object();
            $("<div id='measure-tips-chart' style='width: 100%;height: 85%;'></div>").appendTo($container);
            _this.getMeasureData().then(function (json) {
                if(json.code==200){
                    $.each(json.result, function (i, item) {
                        var time = item.dt.split(" ")[0];
                        if(x.indexOf(time)<0){
                            x.push(time);
                        }
                        if(measureData[item.location]){
                            ayy = measureData[item.location];
                            if(ayy.hasOwnProperty(item.location_no)){
                                ayy[item.location_no].push(item.accumulate_val);
                            }else{
                                ayy[item.location_no] = new Array();
                                ayy[item.location_no].push(item.accumulate_val);
                            }
                        }else{
                            if(i===0){
                                html = html + '<span style="color: #1493E4;cursor: pointer;font-size: 0.12rem;margin: 0 0.3rem;">'+item.location+'</span>';
                            }else{
                                html = html + '<span style="color: #efefef;cursor: pointer;font-size: 0.12rem;margin: 0 0.3rem;">'+item.location+'</span>';
                            }
                            measureData[item.location] = new Object();
                        }
                    });
                    var key = Object.keys(measureData)[0];
                    var def = measureData[key];
                    for(var type in def){
                        series.push({
                            name: type,
                            type:'line',
                            smooth: true,
                            data: def[type]
                        });
                    }
                    _this.toolTipChart = _this.initTipsLineChart({x: x, series: series});
                    $("<div id='measure-tips-tooltip' style='width: 100%;height: 15%;display: flex;flex-flow: wrap row;justify-content: space-around;align-items: center;'>" + html +
                        "</div>").appendTo($container);
                    $container.on('click', '#measure-tips-tooltip span', function () {
                        $("#measure-tips-tooltip span").css('color', "#efefef");
                        this.style.color = "#1493E4";
                        key = $(this).text();
                        def = measureData[key];
                        series = [];
                        for (var JGC in def) {
                            series.push({
                                name: JGC,
                                type: 'line',
                                smooth: true,
                                data: def[JGC]
                            });
                        }
                        _this.chartOptions.xAxis.data = x;
                        _this.chartOptions.series = series;
                        _this.toolTipChart.setOption(_this.chartOptions, true);
                    });
                }else{
                    Feng.error(json.message);
                }
            }, function (err) {
                Feng.error(err.message);
            });
        },
        onClose: function () {
            this.toolTipChart.dispose();
            this.toolTipChart = null;
        },
        initTipsLineChart: function (data) {
            this.chartOptions.xAxis.data = data.x;
            this.chartOptions.series = data.series;
            var target = document.getElementById(this.measureTipsChartId);
            var chart = echarts.init(target);
            chart.setOption(this.chartOptions);
            return chart;
        }
    },
    ditchStaff: {
        getStaffData: function () {
            var data = null;
            $.ajax({
                type: "GET",
                url: baseURL + "/homePage/foundationDitch/peopleCount",
                async: false,
                global: false,
                dataType: "json",
                contentType: "application/json",
                success: function (json) {
                    if(json.code==200){
                        data = json.result;
                    }else{
                        Feng.error(json.message);
                    }
                },
                error: function (err) {
                    Feng.error(err.message);
                }
            });
            return data;
        },
        onOpen: function () {
            var staff = this.getStaffData();
            var tbodyHtml = "";
            staff.forEach(function (t, index) {
                tbodyHtml = tbodyHtml + '<tr>' +
                    '<td>'+parseInt(index+1)+'</td>'+
                    '<td>'+t.jobName+'</td>'+
                    '<td>'+t.name+'</td>'+
                    '<td>'+t.phone+'</td>'+
                    '</tr>';
            });
            $("#ditch-staff").empty().append('<table class="table table-bordered table-condensed">' +
                '<thead>' +
                '<tr>' +
                '<th>序号</th>' +
                '<th>岗位</th>' +
                '<th>姓名</th>' +
                '<th>电话</th>'+
                '</tr>' +
                '</thead>' +
                '<tbody>' +
                tbodyHtml +
                '</tbody>' +
                '</table>');
            $("#ditch-staff table tbody").css('max-height', '2.1rem').niceScroll({
                cursorcolor: '#1493E4',
                cursoropacitymax: 0.8,
                cursorborder: "1px solid #1493E4",
                touchbehavior: false,
                cursorwidth: "5px",
                cursorborderradius: "5px",
                autohidemode: true,
                horizrailenabled: false
            });
        },
        onClose: function () {
            
        }
    },
    ditchWater: {
        barOptions: {
            grid: {
                left: '5%',
                top: '10%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: {
                axisLabel: {
                    interval: 0,
                    fontSize: 10,
                    textStyle: {
                        color: '#1493E4'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                }
            },
            yAxis: {
                show: false,
                inverse: true
            }
        },
        getWaterData: function (foundid) {
            return $.ajax({
                type: "GET",
                url: "/homePage/homePageWaterPopupWindow?foundid="+foundid,
                dataType: "json"
            });
        },
        onOpen: function () {
            var ditchId = this.source.data('ditchid');
            var gray = new Array(), _this = DitchModule.ditchWater;
            _this.getWaterData(ditchId).then(function (json) {
                if(json.code==200){
                    var $container = $("#ditchWater-content-"+ditchId).empty();
                    $("<div id='ditchWater-tips-bar-"+ditchId+"' style='width: 100%;height: 100%;'></div>").appendTo($container);
                    json.result.y.forEach(function (t, index) {
                        var color = '#1493E4';
                        if((json.result.hasOwnProperty('mins') && json.result.mins[index]==1) || (json.result.hasOwnProperty('maxs') && json.result.maxs[index]==1) || (json.result.hasOwnProperty('identicals') && json.result.identicals[index]==1)) {
                            color = '#BB6633';
                        }
                        gray.push({
                            value: json.result.depth-t,
                            itemStyle: {
                                color: color
                            }
                        });
                    });
                    var series = [
                        {
                            type: 'bar',
                            itemStyle: {
                                normal: {color: '#8E8E8E'}
                            },
                            barGap:'-100%',
                            barWidth: '35%',
                            barCategoryGap:'40%',
                            data: [json.result.depth, json.result.depth],
                            animation: false
                        },
                        {
                            type: 'bar',
                            barWidth: '35%',
                            data: gray,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    distance: 0,
                                    color: '#fff',
                                    formatter: function (data) {
                                        return (json.result.depth-data.value).toFixed(1);
                                    }
                                }
                            }
                        }
                    ];
                    _this.barOptions.xAxis.data = json.result.x;
                    _this.barOptions.series = series;
                    console.log(document.getElementById('ditchWater-tips-bar-'+ditchId));
                    _this.bar = echarts.init(document.getElementById('ditchWater-tips-bar-'+ditchId));
                    _this.bar.setOption(_this.barOptions);
                }else{
                    Feng.error(json.message);
                }
            }, function (err) {
                Feng.error(err.message);
            })
        },
        onClose: function () {
            this.bar.dispose();
            this.bar = null;
        }
    },
    ditchGas: {
        getGasData: function () {
            return $.ajax({
                type: "GET",
                url: "/homePage/homePageHarmfulGasPopupWindow",
                dataType: "json"
            });
        },
        onOpen: function () {
            var ditchId = this.source.data('ditchid'), _this = DitchModule.ditchGas;
            _this.getGasData().then(function (json) {
                if(json.code==200){
                    var html = "";
                    for(var gas in json.result){
                        var name = '';
                        switch (gas){
                            case 'yyht': name = '一氧化碳(PPM)';
                                break;
                            case 'yq': name = '氧气(%)';
                                break;
                            case 'krq': name = '可燃气(PPM)';
                                break;
                            case 'lhq': name = '硫化氢(mg/m³)';
                                break;
                        }
                        html = html + '<div class="text-center">' +
                                        '<span class="realRecord fontx4">'+json.result[gas]+'</span>' +
                                        '<span class="decorationLable fontx2">'+name+'</span>' +
                                    '</div>';
                    }
                    $("#ditchGas-content-"+ditchId).html(html);
                }else{
                    Feng.error(json.message);
                }
            }, function (error) {
                Feng.error(error);
            });
        },
        onClose: function () {
            
        }
    }
};

window.setTimeout(function () {
    DitchModule.initTips($('#inDitch'), '<div id="ditch-staff" style="width: 4rem;height: auto;"></div>', DitchModule.ditchStaff.onOpen.bind(DitchModule.ditchStaff), DitchModule.ditchStaff.onClose.bind(DitchModule.ditchStaff));
}, 1000);