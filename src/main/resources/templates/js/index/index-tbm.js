var svgWidth = $('#layer-container').width();
var svgHeight = $('#layer-container').height();
var shieldno=null;
var TbmMoudle = {
    svg: {
        draw: SVG('layer-container').size('100%', '100%')
    },
    ringLength: 1.2,
    traPoints: [
        {x: 0, y: 0.27*svgHeight},
        {x: 0.18*svgWidth, y: 0.27*svgHeight},
        {x: 0.3*svgWidth, y: 0.35*svgHeight},
        {x: 0.645*svgWidth, y: 0.4*svgHeight},
        {x: 0.77*svgWidth, y: 0.35*svgHeight},
        {x: 0.889*svgWidth, y: 0.27*svgHeight},
        {x: svgWidth, y: 0.25*svgHeight}
    ],
    interval: 7,  //默认取7天的数据
    chartOptions: {
        title: {
            text: '推进统计(环数)',
            textStyle: {
                color: 'white',
                fontSize: 10,
                fontWeight: 'lighter'
            },
            x: 'left',
            top: 0
        },
        legend: {
            show: false
        },
        grid: {
            left: '3%',
            top: '15%',
            bottom: '3%',
            containLabel: true
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
    toggleTbm: function (tbmData) {
        console.log(tbmData)
        shieldno=tbmData.shieldno
        this.updateProcess(tbmData.mysql);
        this.updateCutterhead(tbmData.es);
        this.updateScrew(tbmData.es);
        this.updateTrail(tbmData.mysql);
    },
    /**
     * 更新进度统计
     * @param data
     */
    updateProcess: function (data) {
        $("#circle-progress").circlePercent({
            fontSize: '0.2rem',
            percent: (data.currentnumber/data.totalring).toFixed(1)*100
        });
        var $ring = $(".ring-data").empty(), $mileage = $(".mileage-data").empty();
        $('<div><div class="data">'+data.currentnumber.toFixed(1)+'</div><div class="title">当前环数</div></div><div><div class="data">'+data.totalring.toFixed(1)+'</div><div class="title">总环数</div></div>').appendTo($ring);
        $('<div><div class="data">'+data.currentmileage.toFixed(1)+'</div><div class="title">当前里程</div></div><div><div class="data">'+data.totalmileage.toFixed(1)+'</div><div class="title">总里程</div></div>').appendTo($mileage);
        if(this.processChart){
            this.chartOptions.yAxis.data = data.x;
            this.chartOptions.series[0].data = data.y;
            this.processChart.setOption(this.chartOptions, true);
        }else{
            this.initProcessChart(data)
        }
    },
    /**
     * 更新刀盘数据
     * @param data
     */
    updateCutterhead: function (data) {

        var $cutterheadStatus = $("#cutterhead-status").empty();
        var $groutingStatus = $("#grouting-status").empty();
        var $cutterheadConnect = $("#cutterhead-connect-status").empty();
        var $cutterheadParam = $("#cutterhead-param").empty();
        var $gascontent =$("#gas-content").empty()
        $('<div style="position: absolute; left: 9%;top: 20%;z-index: 10;">' +
            '<p class="data-label">左上土压</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_144+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; left: 10%;bottom: 15%;z-index: 10;">' +
            '<p class="data-label">左下土压</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_148+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; right: 6%;top: 20%;z-index: 10;">' +
            '<p class="data-label">右上土压</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_144+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; right: 6%;bottom: 15%;z-index: 10;">' +
            '<p class="data-label">右下土压</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_152+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; right: -8%;top: 44%;z-index: 10;">' +
            '<p class="data-horizontal-lable">右中土压<span class="data-value">'+data.db40_156+'</span>bar</p>' +
            '<p class="data-horizontal-lable">A组位移<span class="data-value">'+data.db40_512+'</span>mm</p>' +
            '<p class="data-horizontal-lable">A组压力<span class="data-value">'+data.db40_44+'</span>bar</p>' +
            '</div>' +
            '<div style="position: absolute; left: 34%;bottom: 2%;z-index: 10;">' +
            '<p class="data-horizontal-lable">B组位移<span class="data-value">'+data.db40_516+'</span>mm</p>' +
            '<p class="data-horizontal-lable">B组压力<span class="data-value">'+data.db40_52+'</span>bar</p>' +
            '</div>' +
            '<div style="position: absolute; left: -8%;top: 44%;z-index: 10;">' +
            '<p class="data-horizontal-lable">左中土压<span class="data-value">'+data.db40_140+'</span>bar</p>' +
            '<p class="data-horizontal-lable">C组位移<span class="data-value">'+data.db40_520+'</span>mm</p>' +
            '<p class="data-horizontal-lable">C组压力<span class="data-value">'+data.db40_60+'</span>bar</p>' +
            '</div>' +
            '<div style="position: absolute; left: 34%;top: 2%;z-index: 10;">' +
            '<p class="data-horizontal-lable">D组位移<span class="data-value">'+data.db40_524+'</span>mm</p>' +
            '<p class="data-horizontal-lable">D组压力<span class="data-value">'+data.db40_68+'</span>bar</p>' +
            '</div>' +
            '<div style="position: absolute;left: 50%;top: 50%;transform: translate(-50%, -50%);z-index: 101;padding: 8px;">' +
            '<p class="data-horizontal-lable levelstatus" >水平位移<span class="data-value" id="levelcoler">'+data.avgDaily_db47_52+'</span>cm</p>' +
            '<p class="data-horizontal-lable levelstatus">垂直位移<span class="data-value"  id="verticalcoler">'+data.avgDaily_db47_56+'</span>cm</p>' +
            '<p class="data-horizontal-lable">俯仰角<span class="data-value">'+data.db40_778+'</span></p>' +
            '<p class="data-horizontal-lable">滚动角<span class="data-value">'+data.db40_124+'</span></p>' +
            '<p class="data-horizontal-lable">贯入度<span class="data-value">'+data.db40_1084+'</span>mm/r</p>' +
            '<p class="data-horizontal-lable">转速<span class="data-value">'+data.db40_20+'</span>r/min</p>' +
            '<p class="data-horizontal-lable">扭矩<span class="data-value">'+data.db40_336+'</span>KN.m</p>' +
            '<p class="data-horizontal-lable">超挖量<span class="data-value">'+data.db40_798+'</span>mm</p>' +
            '<p class="data-horizontal-lable">刀盘角度<span class="data-value">'+data.db40_794+'</span></p>' +
            '<p class="data-horizontal-lable">刀盘磨损<span class="data-value">'+data.db40_802+'</span></p>' +
            '</div>').appendTo($cutterheadStatus);
        $('<div style="position: absolute; left: 6%;top: 23%;z-index: 10;">' +
            '<p class="data-label">左上注浆压力</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_168+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; left: 6%;bottom: 23%;z-index: 10;">' +
            '<p class="data-label">左下注浆压力</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_180+'</span>bar' +
            '</p>' +
            '</div> <div style="position: absolute; right: 5%;top: 23%;z-index: 10;">' +
            '<p class="data-label">右上注浆压力</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_172+'</span>bar' +
            '</p>' +
            '</div>' +
            '<div style="position: absolute; right: 5%;bottom: 23%;z-index: 10;">' +
            '<p class="data-label">右下注浆压力</p>' +
            '<p class="data-label">' +
            '<span class="data-value">'+data.db40_176+'</span>bar' +
            '</p>' +
            '</div>').appendTo($groutingStatus);
        $('<div style="border:1px solid rgba(20,152,235,1);background:rgba(9,41,74,1); border-radius: 0.01rem; padding: 0.05rem 0.08rem; color: #FFF; text-align: center;">' +
            '气体含量</div>' +
            '<div style="width: 100%; border: 1px solid rgba(20,152,235,1); background:rgba(9,41,74,1); margin-top: 0.03rem; border-radius: 0.01rem; padding:0.1rem 0.2rem;">'+
        '<p class="data-horizontal-lable">CH4含量<span class="data-value">'+data.db40_344+'</span>%</p>' +
        '<p class="data-horizontal-lable">O2含量&nbsp; <span class="data-value">'+data.db40_1240+'</span>%vol</p>' +
        '<p class="data-horizontal-lable">H2S含量 <span class="data-value">'+data.db40_296+'</span>ppm</p>' +
            '</div>').appendTo($gascontent)
        //请求数据 判定垂直 和水平是否超过临界值
        $.ajax({
            type: "GET",
            url: "/shield/getShield",
            data:{datetime: data.datetime},
            dataType: "json",
            success: function (json) {
                console.log(json)
                if (json.code==200){
                    if (json.result.levelstatus==1){
                        $("#levelcoler").css({"color":json.result.levelcoler ,"font-size":"0.2rem"})
                    }else {
                        $("#levelcoler").css({"color":"yellow" ,"font-size":"0.12rem"})
                    }
                    if (json.result.verticalstatus==1){
                        $("#verticalcoler").css({"color":json.result.verticalcoler ,"font-size":"0.2rem"})
                    }else {
                        $("#verticalcoler").css({"color":"yellow" ,"font-size":"0.12rem"})
                    }
                } else {
                    console.log("数据错误")
                }

            }
        })

        var status = '';

        if(data.status==0){
            status = '<span style="display: inline-block;text-align: center;width: 46%;color: lime;">运行中</span>';
        }else if(!data.status || data.status==1){
            status = '<span style="display: inline-block;text-align: center;width: 46%;color: gray;">未运行</span>';
        }else if(data.status==2){
            status = '<span style="display: inline-block;text-align: center;width: 46%;color: orangered;">运行失败</span>';
        }
        $('<span style="display: inline-block;color: white;width: 50%;border-right: 1px solid rgba(20,147,228, 0.5);text-align: center;">工作状态</span>' +
            status
        ).appendTo($cutterheadConnect);
        $('<p class="data-horizontal-lable">推进压力<span class="data-value">'+data.db40_32+'</span>bar</p>' +
            '<p class="data-horizontal-lable">推进速度<span class="data-value">'+data.db40_320+'</span>mm/min</p>' +
            '<p class="data-horizontal-lable">总推进力<span class="data-value">'+data.db40_552+'</span>KN</p>' +
            '<p class="data-horizontal-lable">抓举头角度<span class="data-value">'+data.db40_1018+'</span></p>' +
            '<p class="data-horizontal-lable">抓举头压力<span class="data-value">'+data.db40_1286+'</span>bar</p>' +
            '<p class="data-horizontal-lable">皮带机转速<span class="data-value">'+data.db40_1250+'</span>m/s</p>'
        ).appendTo($cutterheadParam);
    },
    /**
     * 更新螺旋机数据
     * @param data
     */
    updateScrew: function (data) {

        var $foamTbody = $("#foam-system tbody").empty();
        var screwImage = $('#screw-image').empty();
        var $screwParam = $("#screw-param").empty();
        $('<tr>' +
            '<td>添加剂流量</td>' +
            '<td>'+data.db40_192+'</td>' +
            '<td>'+data.db40_196+'</td>' +
            '<td>'+data.db40_200+'</td>' +
            '<td>'+data.db40_854+'</td>' +
            '<td>'+data.db40_858+'</td>' +
            '<td>'+data.db40_862+'</td>' +
            '</tr>' +
            '<tr>' +
            '<td>压力</td>' +
            '<td>'+data.db40_224+'</td>' +
            '<td>'+data.db40_228+'</td>' +
            '<td>'+data.db40_232+'</td>' +
            '<td>'+data.db40_236+'</td>' +
            '<td>'+data.db40_906+'</td>' +
            '<td>'+data.db40_910+'</td>' +
            '</tr>'
        ).appendTo($foamTbody);
        $('<div style="position: absolute;bottom: -12%;left: 12%;background-color: rgba(20,147,228, 0.5);border-radius: 4px;color: white;line-height: 1.5;padding: 0.05rem 0.1rem;">前端土压: '+data.db40_260+'bar</div>' +
            '<div style="position: absolute;top: -5%;right: 20%;background-color: rgba(20,147,228, 0.5);border-radius: 4px;color: white;line-height: 1.5;padding: 0.05rem 0.1rem;">后端土压: '+data.db40_1286+'bar</div>'
        ).appendTo(screwImage);
        $('<p>螺旋机油压: '+data.db40_992+'</p>' +
            '<p>螺旋机转速: '+data.db40_116+'</p>' +
            '<p>螺旋机扭矩: '+data.db40_340+'</p>' +
            '<p>上闸门开度: '+data.db40_544+'</p>' +
            '<p>下闸门开度: '+data.db40_548+'</p>'
        ).appendTo($screwParam);
    },
    /**
     * 更新盾构轨迹数据
     * @param tbmParam
     */
    updateTrail: function (tbmParam) {
        this.svg.draw.node.instance.off();
        this.svg.draw.clear();
        this.initSvg(tbmParam);
    },
    initProcessChart: function (data) {

        var series = new Array();
        series.push({
            name: '推进环数',
            type: 'line',
            smooth: true,
            color: 'lime',
            label: {
                normal: {
                    show: true,
                    position: 'bottom',
                    color: 'white'
                }
            },
            data: data.y
        });
        this.chartOptions.xAxis.data = data.x;
        this.chartOptions.series = series;
        var target = document.getElementById('process-chart');
        this.processChart = echarts.init(target);
        this.processChart.setOption(this.chartOptions);
    },
    getTraPoints: function () {
        var trsPointsStr = new Array(), prePoint = new Object(), traLength = 0;
        this.traPoints.forEach(function (t) {
            trsPointsStr.push(t.x+','+t.y);

            if(prePoint.hasOwnProperty('x')){
                traLength = traLength + Math.distance(prePoint, t);
            }
            prePoint = t;
        });
        /**
         * 轨迹曲线长度
         */

        this.svg.traLength = traLength;

        return trsPointsStr.join(' ');
    },
    getFullPoints: function (totalRing, currentRing, advancefx) {
        var prePoint = new Object(), completePointStr = new Array(), traPoints = new Array(), distance = 0;
        var currentLength = currentRing/totalRing*this.svg.traLength;
        if(advancefx==0){
            traPoints = this.traPoints;
        }else if(advancefx==1){
            traPoints = this.traPoints.creverse();
        }
        $.each(traPoints, function (i, t) {
            var d = 0;
            if(prePoint.hasOwnProperty('x')){
                d = Math.distance(prePoint, t);
                distance = distance + d;
            }
            if(distance>currentLength){
                var point = Math.getPoint(prePoint, t, (d-(distance-currentLength))/(distance-currentLength));
                completePointStr.push(point.x+','+point.y);
                return false;
            }
            completePointStr.push(t.x+','+t.y);
            prePoint = t;
        });
        return completePointStr.join(' ')
    },
    getTipRing: function (point) {
        var prePoint = new Object(), distance = 0;
        $.each(this.traPoints, function (i, t) {
            if(t.x>point.x){
                distance = distance + Math.distance(prePoint, point);
                return false;
            }
            if(prePoint.hasOwnProperty('x')){
                distance = distance + Math.distance(prePoint, t);
            }
            prePoint = t;
        });
        return distance;
    },
    getMarkerPoint: function (currentRing, totalRing, advancefx) {
        var prePoint = new Object(), targetPoint = new Object(), traPoints = new Array(), distance = 0;
        var currentLength = currentRing/totalRing*this.svg.traLength;
        if(advancefx==0){
            traPoints = this.traPoints;
        }else if(advancefx==1){
            traPoints = this.traPoints.creverse();
        }
        $.each(traPoints, function (i, t) {
            var d = 0;
            if(prePoint.hasOwnProperty('x')){
                d = Math.distance(prePoint, t);
                distance = distance + d;
            }
            if(distance>currentLength){
                var point = Math.getPoint(prePoint, t, (d-(distance-currentLength))/(distance-currentLength));
                targetPoint = {
                    x: point.x,
                    y: point.y
                };
                return false;
            }
            prePoint = t;
        });
        return targetPoint;
    },
    initSvg: function (tbmParam) {

        var totalRing = tbmParam.totalring,
            currentRing = tbmParam.currentnumber,
            totaldatenumber = tbmParam.totaldatenumber,
            eachringmileage = tbmParam.eachringmileage,
            targetList = tbmParam.targetlist,
            advancefx = tbmParam.advancefx,
            warns = tbmParam.dangerlist,
            totalring=tbmParam.totalring,
            _this = this, groupTip = null;
        console.log(totalring)
        /*
         *
         * 设置底图
         */
        tbmParam.shieldImageUrl.forEach(function (t) {
            if(t.type==0){
                $("#layer-container").css({
                    'background': 'url("'+t.imageUrl+'") no-repeat left top',
                    'background-size': '100% 100%'
                });
            }else if(t.type==1){
                $(".building").css({
                    'background': 'url("'+t.imageUrl+'") no-repeat left top',
                    'background-size': '100% 100%'
                });
            }
        });
        /**
         * 轨迹曲线
         */
        this.svg.draw.polyline(this.getTraPoints()).fill('none').stroke({width: 5,color:'#2B3648'})
        /**
         * 目标阶段
         */
        targetList.forEach(function (t) {
            var marker = _this.getMarkerPoint(t.targetring, totalRing, advancefx), lineColor = "", flag = "";

            if(t.targetring<=currentRing){
                lineColor = 'lime';
                flag = '../image/tbm/complete.png';
            }else{
                lineColor = '#bfbfbf';
                flag = '../image/tbm/uncomplete.png';
            }
            var targetGroup = _this.svg.draw.group();
            targetGroup.line(marker.x, svgHeight*0.1, marker.x, svgHeight).stroke({width: 1,color:lineColor}).attr('stroke-dasharray',"5,5");
            targetGroup.image(flag, 28, 28).move((marker.x-8), 0);
            targetGroup.text(function(add) {
                add.tspan(t.targetname+'('+t.enddate+')').newLine().fill(lineColor).font({size: 10});
                add.tspan('推进环数: '+t.targetring+' 环').newLine().fill(lineColor).font({size: 10});
                add.tspan('推进里程: '+t.targetmileage+' 米').newLine().fill(lineColor).font({size: 10});
            }).attr({
                'x': marker.x+10,
                'y': 10,
            });
        });
        /**
         * 危险土层
         */
        warns.forEach(function (t) {
            var hOffset;
            if(advancefx==0){
                hOffset = t.startspacing/t.drawlength*svgWidth;
            }else if(advancefx==1){
                hOffset = svgWidth - t.startspacing/t.drawlength*svgWidth;
            }
            var vOffset = t.dangerdepth/t.standarddepth*svgHeight;

            _this.svg.draw.circle(14).move(hOffset,vOffset).fill('red');
            _this.svg.draw.text(t.pointname).fill('white').attr({
                'x': hOffset+16,
                'y': vOffset-10
            });
        });

        if(currentRing && currentRing>0){
            var fullPolyline = this.svg.draw.polyline(this.getFullPoints(totalRing, currentRing, advancefx)).fill('none').stroke({width: 5,color:'#1493E4'})
                .on("mousemove",function () {
                    $(".current_number").show()
                    var e = event || window.event;
                   var eachRing=parseInt((e.screenX-$("#layer-container").offset().left)/($("#layer-container").width()/totalring))
                    if (eachRing<1200){
                        eachRing=eachRing
                    } else if (eachRing>=1200 &&eachRing<2600){
                        eachRing=eachRing-8//出现的误差
                    } else if (eachRing>=2600 &&eachRing<3400){
                        eachRing=eachRing-12//出现的误差
                    }else if (eachRing>=3400){
                        eachRing=eachRing
                    }
        $(".current_number").css({
            "left":e.screenX,
            "top":e.screenY-50
        })
        $(".current_number").empty()
        $.ajax({
            async:false,
            type:"get",
            url:"/shield/queryShieldadvance/"+eachRing,
            dataType: "json",
            success:function (json) {
                console.log(json)
                var  date=json.result[0]

                $(".current_number").empty().append("<p>管片环数为："+date.segmentno+"</p>" +
                    "<p>推进里程："+(date.segmentno*1.2).toFixed(2)+"</p>" +
                    "<p>掘进日期为："+date.tunnellingdate+"</p>" +
                    "<p>掘进时间为："+date.tunnellingtime+"</p>" +
                    "<p>注浆时间："+date.slipcastingtime+"</p>" +
                    "<p>注浆方量："+date.slipcastingmeasure+"</p>" +
                    "<p>拼装日期："+date.assembledate+"</p>" +
                    "<p>拼装时间："+date.assembletime+"</p>")
            }
        })
                   //获取到鼠标位置的环数
            })
                .on("mouseout",function () {
                    $(".current_number").hide()
                })
            // fullPolyline.off()
            //     .on('mousemove', function(e){
            //         if(groupTip){
            //             groupTip.remove();
            //         }
            //         var mouseX = e.offsetX ? e.offsetX : e.layerX;
            //         var mouseY = e.offsetY ? e.offsetY : e.layerY;
            //         var rings = _this.getTipRing({x: mouseX, y: mouseY})/_this.svg.traLength*totalRing;
            //         groupTip = _this.svg.draw.group();
            //         groupTip.line(mouseX, svgHeight*0.25, mouseX, svgHeight).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
            //         groupTip.text(function(add) {
            //             add.tspan('推进环数: '+rings.toFixed(1)+'环').newLine().fill('yellow');
            //             add.tspan('推进里程: '+(rings*_this.ringLength).toFixed(2)+'米').newLine().fill('yellow');
            //         }).attr({
            //             'x': mouseX+20,
            //             'y': svgHeight*0.25
            //         });
            //     })
            //     .on('mouseout', function(){
            //         groupTip.remove();
            //         groupTip = null;
            //     });
            /**
             * 历史推进标记点
             */
            // for(var circlePoint in totaldatenumber){
            //     var marker = this.getMarkerPoint(totaldatenumber[circlePoint], totalRing, advancefx);
            //     this.svg.draw.circle().attr({
            //         'fill': 'lime',
            //         'stroke': '#fff',
            //         'stroke-width': 2,
            //         'r': 6,
            //         'cx':marker.x,
            //         'cy': marker.y,
            //         'data-date': circlePoint,
            //         'data-ring': totaldatenumber[circlePoint]
            //     })
            //         .off()
            //         .on('mousemove', function(e){
            //             if(groupTip){
            //                 groupTip.remove();
            //             }
            //             this.node.instance.attr('r', 7).animate({duration: 1500, ease: '<>'});
            //             var mouseX = this.node.instance.attr('cx');
            //             var date = this.node.instance.attr('data-date');
            //             var rings = this.node.instance.attr('data-ring');
            //             groupTip = _this.svg.draw.group();
            //             groupTip.line(mouseX, svgHeight*0.25, mouseX, svgHeight).stroke({width: 2,color:'#1493E4'}).attr('stroke-dasharray',"5,5");
            //             groupTip.text(function(add) {
            //                 add.tspan(date).newLine().fill('#fff').font({
            //                     size: 16,
            //                     weight: 600
            //                 });
            //                 add.tspan('推进环数: '+rings.toFixed(1)+' 环').newLine().fill('#fff');
            //                 add.tspan('推进里程: '+(rings*eachringmileage).toFixed(1)+' 米').newLine().fill('#fff');
            //             }).attr({
            //                 'x': mouseX+20,
            //                 'y': svgHeight*0.25
            //             });
            //         })
            //         .on('mouseout', function(){
            //             this.node.instance.attr('r', 6).animate({duration: 1500, ease: '<>'});
            //             groupTip.remove();
            //             groupTip = null;
            //         });
            // }
            /**
             * 当前进度点
             */
            var points = fullPolyline.attr('points').split(' ');

            var position = points[points.length-1].split(',');
            console.log(position)
            var groupTips = null;
            this.svg.draw.circle(14).move(position[0],position[1]).attr({
                'fill': '#1493E4',
                'stroke': '#fff',
                'stroke-width': 2
            }).on('mousemove',function(e){
                if(groupTips){
                    groupTips.remove();
                }
                var mouseX = this.node.instance.attr('cx');

                groupTips = _this.svg.draw.group();
                groupTips.line(mouseX, svgHeight*0.25, mouseX, svgHeight).stroke({width: 2,color:'lime'}).attr('stroke-dasharray',"5,5")
                groupTips.text(function(add) {
                    add.tspan('推进环数:'+currentRing+' 环').newLine().fill('#fff');
                    add.tspan('推进里程:'+currentRing*1.2+' 米').newLine().fill('#fff');
                    // add.tspan('前方50米到达淤泥图层请注意要减速').newLine().fill("yellow").font({size: 20})
                }).attr({
                    'x': mouseX+20,
                    'y': svgHeight*0.3
                });
            }).on('mouseout',function (e) {
                groupTips.remove();
                groupTips=null

            }).animate({duration: 1500, ease: '<>'}).fill('#00FC55').radius(7.5).animate({duration: 1500, ease: '<>'}).fill('#1493E4').radius(7).loop();
        }
    },
    resizeSvg: function () {
        this.svg.draw.clear();
        svgWidth = $('#layer-container').width();
        svgHeight = $('#layer-container').height();
        this.traPoints = [
            {x: 0, y: 0.45*svgHeight},
            {x: 0.184*svgWidth, y: 0.45*svgHeight},
            {x: 0.313*svgWidth, y: 0.526*svgHeight},
            {x: 0.645*svgWidth, y: 0.546*svgHeight},
            {x: 0.785*svgWidth, y: 0.45*svgHeight},
            {x: 0.889*svgWidth, y: 0.445*svgHeight},
            {x: svgWidth, y: 0.404*svgHeight}
        ];
        var tbmNo = $("#tbm-select").find('option:selected').val();
        var currentTbmData = null;
        $.each(this.tbmData, function (i, item) {
            if(item.shieldno==tbmNo){
                currentTbmData = item;
                return false;
            }
        });
        this.initSvg(currentTbmData.mysql);
        this.processChart.resize();
    },
    getTbmVideo: function () {
        return $.ajax({
            type: 'GET',
            url: '/homePage/camera',
            dataType: "json",
            async: false
        });
    },
    showVideo: function (tbmNo) {
        var _this = this;
        _this.getTbmVideo(tbmNo).then(function (json) {
            if (json.code == 200) {
                var videoList = json.result;
                var loginId = -1, videoID = -1, player = null, options = "";
                var ocx = new OCX();
                _this.layerIndex = layer.open({
                    type: 1,
                    title: false,
                    area: ['500px', '384px'], //宽高
                    fixed: true,
                    // move: '.layui-layer-content',
                    offset: ['50%', '50%'],
                    resize: false,
                    shade: false,
                    content: '<div id="videoLayer" style="width: 500px;height: 384px;position: relative;"></div>',
                    success: function () {
                        var $container = $("#videoLayer");
                        videoList.forEach(function (t) {
                            options = options + "<option value='"+t.camerano+"' data-cameraInfo='"+JSON.stringify(t)+"'>"+t.cameraname+"</option>";
                        });
                        $('<select class="video-select"></select>').html(options).appendTo($container).change(function () {
                            $(this).blur();
                            var cameraInfo = $(this).find("option:selected").attr('data-cameraInfo');
                            cameraInfo = JSON.parse(cameraInfo);
                            player.StopVideoPlay(videoID);
                            player.LogoutDevice(loginId);
                            loginId = player.LoginDevice(cameraInfo.devicetype, cameraInfo.ipaddress, cameraInfo.tcpport, cameraInfo.username, cameraInfo.passwd);
                            if(loginId>=0){
                                videoID = player.StartVideoPlay(loginId, cameraInfo.chanindex, 1);
                                if(videoID<0){
                                    alert("视频打开失败");
                                }
                            }else{
                                alert("设备登录失败");
                            }
                        });
                        $('<div id="tbmVideo" class="video-player"></div>').appendTo($container);
                        ocx.createPluginObject($('#tbmVideo'), "tbmOCX");
                        if(ocx.isIE()){
                            player = document.getElementById('tbmOCX');
                            player.SetSelectedWnd(0);
                            player.SetWndColor("#07152F", "#1493E4", "#FF8000");
                            loginId = player.LoginDevice(videoList[0].devicetype, videoList[0].ipaddress, videoList[0].tcpport, videoList[0].username, videoList[0].passwd);
                            if(loginId>=0){
                                videoID = player.StartVideoPlay(loginId, videoList[0].chanindex, 1);
                                if(videoID<0){
                                    alert("视频打开失败");
                                    layer.closeAll();
                                }
                            }else{
                                alert("设备登录失败");
                                layer.closeAll();
                            }
                        }
                    },
                    cancel: function () {
                        if(ocx.detectPluginObject()){
                            player.StopVideoPlay(videoID);
                            player.LogoutDevice(loginId);
                        }
                        $("#videoLayer").empty();
                    },
                    end: function () {
                        if(ocx.detectPluginObject()){
                            player.StopVideoPlay(videoID);
                            player.LogoutDevice(loginId);
                        }
                        $("#videoLayer").empty();
                        $("#tbm-video").find('input[type="checkbox"]').prop('checked', false);
                    }
                });
            } else {
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });

    }
};



window.onresize = TbmMoudle.resizeSvg.bind(this.TbmMoudle);
/**
 * 平台标题
 * @param data
 */
var homePageQueryTitle = function () {

    $.ajax({
        type: 'get',
        url: "/homePage/homePageQueryTitle",
        dataType: "json",
        //contentType: "application/json",
        // jsonpCallback: "admin_cross",
        success: function (json) {
            if(json.code=="200"){
                console.log(json)
                if (json.result.outletid==5){
                    $("#layer-container").hide()
                }
                $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }

        }
    });
};



(function () {
        var token = getCookie("user.cookie");
        var target = 'ws://'+ws+'//webSocketServerTbm?token='+token;
        if ('WebSocket' in window) {
            TbmMoudle.ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
        TbmMoudle.ws = new MozWebSocket(target);
    } else {
        Feng.info('当前浏览器不支持WebSocket');
        return;
    }

    TbmMoudle.ws.onopen = function (event) {
        console.log("open success");
    };



    TbmMoudle.ws.onmessage = function (event) {
        var socketData = JSON.parse(event.data);
        var tbmData = new Object();
        var $tbmSelect = $("#tbm-select"), options = "";
        var tbmNo = $tbmSelect.find('option:selected').val() || socketData.data[0].shieldno;
        var defaultTbmData = socketData.data[0];
        socketData.data.forEach(function (t) {
            options = options + '<option value="'+t.shieldno+'">'+t.shieldname+'</option>';
            if(t.shieldno==tbmNo){
                defaultTbmData = t;
            }
            tbmData[t.shieldno] = t;
        });
        $tbmSelect.empty().append(options).val(tbmNo);
        TbmMoudle.toggleTbm(defaultTbmData);

        TbmMoudle.tbmData = tbmData;
    };
    TbmMoudle.ws.error = function (event){
        Feng.error("服务器连接失败");
    };
    TbmMoudle.ws.onclose = function () {

    };
}());

window.setTimeout(function () {
    var tbmNo = $("#tbm-select").find("option:selected").val();

    // var tbmData = TbmMoudle.tbmData[tbmNo];
    TbmMoudle.showVideo(tbmNo);
}, 1000);

(function () {
    $("#tbm-select").change(function () {
        var tbmNo = $(this).find("option:selected").val();
        var tbmData = TbmMoudle.tbmData[tbmNo];

        TbmMoudle.toggleTbm(tbmData);
    });
    $("#tbm-video").on('change', 'input[type="checkbox"]', function () {
        if($(this).prop('checked')){
            var tbmNo = $("#tbm-select").find("option:selected").val();
            // var tbmData = TbmMoudle.tbmData[tbmNo];
            TbmMoudle.showVideo(tbmNo);
        }else{
            layer.close(TbmMoudle.layerIndex);
        }
    });
}())

//盾构土层推进备注
var dblclickbao={
    stages:"",
    date:"",
    number:0.0,
    mileage:0.0,
    describes:"",

}
$(function () {
    homePageQueryTitle();

    //鼠标双击实现
    $("#layer-container").on("dblclick",function () {
        layer.open({
            type: 1,
            title: "盾构土层推进",
            area: ["5rem", '4rem'],
            maxHeight: 800,
            content: $('#duntui').html(),
            btn: ["保存", "取消"],
            yes: function (index, layero) {
                if ($(".form-control1").val().length == 0){
                    Feng.error("实推阶段不能为空")

                    $(".form-control1").css("border-color","red")
                    return;
                }else  if ($(".form-control8").val().length == 0){
                    Feng.error("推进日期不能为空")
                    $(".form-control8").css("border-color","red")
                    return;
                }else  if ($(".form-control5").val().length == 0){
                    Feng.error("推进环数不能为空")
                    $(".form-control5").css("border-color","red")
                    return;
                }else if ($(".form-control3").val().length == 0){
                    Feng.error("推进里数不能为空")
                    $(".form-control3").css("border-color","red")
                    return;
                }
                dblclickbao.stages =$(".form-control1").val()
                dblclickbao.date =$(".form-control8").val()
                dblclickbao.number =parseFloat($(".form-control5").val())
                dblclickbao.mileage =parseFloat($(".form-control3").val())
                dblclickbao.describes =$(".form-control4").val()
                console.log(dblclickbao)
                $.ajax({
                    url: '/shield/saveOrUpdateShieldpoint',
                    type: 'POST',
                    data: JSON.stringify(dblclickbao),
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success:function (json) {
                        console.log(json)
                        var e = event || window.event;
                        $(".current_number").css({
                                "left":e.screenX,
                                "top":e.screenY-50
                            })
                      if (json.code==200) {

                          Feng.success(json.result.msg)


                          setTimeout(function () {
                              $.ajax({
                                  async: false,
                                  url: '/shield/selectAllShieldpoint',
                                  type: 'get',
                                  dataType: "json",
                                  success:function (json) {
                                      console.log(json)
                                      if (json.code==200){
                                          var totalring=json.result.info.totalring
                                          var advancefx=json.result.info.advancefx

                                          $.each(json.result.shieldpoints,function (index,data) {

                                              var marker = TbmMoudle.getMarkerPoint(data.number,totalring,advancefx)
                                              TbmMoudle.svg.draw.circle(14).move(marker.x-5,marker.y-7).attr({
                                                  'fill': 'yellow',
                                                  'stroke': '#fff',
                                                  'stroke-width': 2
                                              }).on('mousemove',function(e){

                                                  var e = event || window.event;
                                                  $(".current_number").css({
                                                      "left":e.screenX,
                                                      "top":e.screenY-50
                                                  })

                                                  $.ajax({
                                                      async:false,
                                                      url: '/shield/selectShieldpoint/'+data.number,
                                                      type: 'get',
                                                      dataType: "json",
                                                      success:function (json) {

                                                          $(".current_number").show()
                                                          $(".current_number").empty().append("<p>实推阶段"+json.result.stages+"</p>" +
                                                              "<p>推进日期:"+json.result.date+"</p>" +
                                                              "<p>推进环数:"+json.result.number+"</p>" +
                                                              "<p>推进日期:"+json.result.mileage+"</p>" +
                                                              "<p>说明:"+json.result.describes+"</p>")
                                                      }
                                                  })
                                              }).on('mouseout',function (e) {
                                                  $(".current_number").hide()
                                              })

                                          })
                                      }
                                  }
                              })
                          }, 10)
                          layer.closeAll()
                      }else {
                          Feng.error(json.message)

                      }

                    }

                })
            }
        })
        $(".form-control8").datetimepicker({
            autoclose: true,
            language: "zh-CN",
            startView: 2,
            minView: "month",
            format: "yyyy-mm-dd",
            forceParse: 0,

        }).val(new Date().getFullYear()+"-"+parseInt(new Date().getMonth()+1)+"-"+new Date().getDate())


    })

    var danger={
        shieldno:"",
        pointname:"",
        remarks:"",
    }



    $("#weixian2").on("mousemove",function () {
        $(".weixian2-text1").text(danger.remarks)
    })
    $("#weixian2").on("mouseout",function () {
        $(".weixian2-text1").text("")
    })

    $("#weixian2").on("click",function () {


        layer.open({
            type: 1,
            title: "盾构土层推进",
            area: ["5rem", '4rem'],
            maxHeight: 800,
            content: $('#risk').html(),
            btn: ["保存", "取消"],
            yes: function (index, layero) {
                danger.id=9;
                danger.pointname=$(".Name-dangerous").val()
                danger.remarks=$("#remark").val()

                $.ajax({
                    async: false,
                    url: '/shield/saveOrUpdateDangerous',
                    type: 'POST',
                    data: JSON.stringify(danger),
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success:function (json) {
                        Feng.success(json.result)
                        $(".weixian2-text").text(danger.pointname)
                        $(".weixian2-text1").text(danger.remarks)
                        layer.closeAll()
                    }
                })
            }
        })
        $(".Name-dangerous").val(danger.pointname)
        $("#remark").text(danger.remarks)
    })


    setTimeout(function(){
        danger.shieldno=shieldno
        $.ajax({
            async: false,
            url: '/shield/selectDangerous/9',
            type: 'get',
            dataType: "json",
            success:function (json) {
                console.log(json)
                $(".weixian2-text").text(json.result.pointname)
                danger.pointname=json.result.pointname
                danger.remarks=json.result.remarks
            }
        })

        $.ajax({
            async: false,
            url: '/shield/selectAllShieldpoint',
            type: 'get',
            dataType: "json",
            success:function (json) {
                console.log(json)
                if (json.code==200){
                    var totalring=json.result.info.totalring
                    var advancefx=json.result.info.advancefx

                    $.each(json.result.shieldpoints,function (index,data) {

                        var marker = TbmMoudle.getMarkerPoint(data.number,totalring,advancefx)
                        TbmMoudle.svg.draw.circle(14).move(marker.x-5,marker.y-7).attr({
                            'fill': 'yellow',
                            'stroke': '#fff',
                            'stroke-width': 2
                        }).on('mousemove',function(e){

                            var e = event || window.event;
                            $(".current_number").css({
                                "left":e.screenX,
                                "top":e.screenY-50
                            })

                            $.ajax({
                                async:false,
                                url: '/shield/selectShieldpoint/'+data.number,
                                type: 'get',
                                dataType: "json",
                                success:function (json) {

                                    $(".current_number").show()
                                    $(".current_number").empty().append("<p>实推阶段"+json.result.stages+"</p>" +
                                        "<p>推进日期:"+json.result.date+"</p>" +
                                        "<p>推进环数:"+json.result.number+"</p>" +
                                        "<p>推进里程:"+json.result.mileage+"</p>" +
                                        "<p>说明:"+json.result.describes+"</p>")
                                }
                            })
                        }).on('mouseout',function (e) {
                            $(".current_number").hide()
                        })

                    })
                }
            }
        })
    }, 1000)
});



