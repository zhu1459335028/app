var ipaddress=null;
/**
 *
 *
 * 加载盾构图
 */
var queryDgImage = function () {

    $(".tbmRange img").remove()
    $.ajax({
        async:false,
        type: "GET",
        url: baseURL + "/homePage/QueryDgImage",
        dataType: "json",
        contentType: "application/json",
        success: function (json) {

            if(json.code==200){
                result = json.result;

                    $(".tbmRange").append("<img src="+json.result.imageurl+">")
                    // emap.src="http://site.secusoft.cc:8070"+result.imageurl
            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};




$.ajax({
    type: 'get',
    url: "/homePage/homePageQueryTitle",
    dataType: "json",
    success: function (json){

        if (json.result.outletid==3){
            $(".dunGouGongdi").attr('href','./index3-tbm.html');
            $("#videos").attr("src","")
        }else if (json.result.outletid==2) {
            $(".dunGouGongdi").attr('href','./index-tbm.html');
            $("#videos").attr("src","")
        }else if (json.result.outletid==5){
            // $("#videos").attr("src","../video/杭州天目山.mp4")
        }
    }
})
var Index = {
    maxVideo: 9,    //最大播放视频数
    onlineDevices: new Object(), //已登录成功的设备
    video: [],    //视频
    videoId: []  //视频登录id
};

var isFirst = true;
/**
 * 平台标题
 * @param data
 */
var homePageQueryTitle = function () {
    console.log("111")
    $.ajax({
        async: false,
        type: 'get',
        url: "/homePage/homePageQueryTitle",
        dataType: "json",
        //contentType: "application/json",
        // jsonpCallback: "admin_cross",
        success: function (json) {
            if(json.code=="200"){
                console.log("111")
                console.log(json)
                ipaddress=json.result.outletid
                 $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }

        }
    });
};
/**
 * 平台时间
 */
var showTime = function(){
    var date = new Date();
    var week = ' 星期'+'日一二三四五六'.charAt(date.getDay()) ;
    $(".header-time").html('<p class="theme-font fontx4" style="font-weight: bolder;">'+date.format('hh:mm:ss')+'</p>' +
                            '<p class="theme-font fontx2">'+date.format('yyyy-MM-dd')+' '+week+'</p>' +
                            '<p class="theme-font fontx2">'+showCal()+'</p>'
    );
};
setInterval(showTime,1000);


/**
 * 平台天气
 * @param data
 */
var getWeather = function (data) {
    $.ajax({
        url: "http://api.map.baidu.com/telematics/v3/weather?location=杭州&output=json&ak=H7W5CxI0BPzKtwGcBHmpGPAz50xP1Qjw",
        dataType: "jsonp",
        jsonpCallback: "admin_cross",
        success: function (data) {
            var res = data.results[0].weather_data[0];
            $(".header-weather").text(res.weather+" "+res.wind);
        }
    });
};

/**
 * 加载施工天数
 */
var getWorkDays = function () {
    $.ajax({
        type: 'get',
        url: "/homePage/constructiondays",
        dataType: "json",
        success: function (json) {
            if(json.code==200){

                $("#construction-days").text(json.result.daynumbers);
            }else{
                Feng.error(json.message);
            }
        }
    });
};

/**
 * 加载报警信息
 */
var loadAlarmInfo = function () {
    var $tbody = $(".alarm-content .table tbody"), html = "";
    $.ajax({
        type: "GET",
        url: "/homePage/reportInfo",
        dataType: "json",
        contentType: "application/json",
        success: function (json) {
            if(json.code==200){

                $("#alarm-total").text('今日报警总数: '+json.result.length);
                var index = json.result.length;
                $.each(json.result, function (i, item) {
                     html = html + '<tr><td>'+parseInt(index-i)+'</td>';
                     if(item.hasOwnProperty("itemtype")){
                         html = html + '<td>'+item.itemType+'</td>';
                     }else{
                         html = html + '<td>文明施工</td>';
                     }
                     html = html + '<td>'+item.itemname+'</td><td>'+item.actiontime+'</td></tr>';
                });
                $tbody.empty().append(html);
                $tbody.niceScroll({
                    cursorcolor: '#1493E4',
                    cursoropacitymax: 0.8,
                    cursorborder: "1px solid #1493E4",
                    touchbehavior: false,
                    cursorwidth: "5px",
                    cursorborderradius: "5px",
                    autohidemode: true,
                    horizrailenabled: false
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
 * 加载电子地图
 */
var loadEmap = function () {
    var result1 = null;
    var $Emap = $("#Emap");
    $Emap.find('img').remove();
    var emap = new Image();
    emap.onload = function (ev) {
        $Emap.width(this.naturalWidth).height(this.naturalHeight).append(this);
        this.style.width = "100%";
        this.style.height = "100%";
        loadEmapCamera(result1);
        loadEmapDoor(result1);
    };
    // emap.src="http://localhost/resource/getploadHomePageImage/2/20190719/2869b80b-2f77-4b4d-809a-560f92ad0ed7.png"
    // emap.id = 22;
    // emap.onerror = function (ev) {
    //     Feng.error("电子地图加载失败");
    // };
    $.ajax({
        type: "GET",
        url: baseURL + "/homePage/emap",
        dataType: "json",
        contentType: "application/json",
        success: function (json) {
            if(json.code==200){

                result1 = json.result;
                // emap.src = result.mapurl;
                emap.id = result1.mapid;
                /**
                 * 平面图切换
                 */
                // $(".eMap-container").append('<div class="select-box">' +
                //     '<select class="form-control">' +
                //     '<option value="'+result.mapurl+'">默认</option>'+
                //     '<option value="../image/emap/1.jpg">围护总平面图</option>' +
                //     '<option value="../image/emap/2.jpg">围护结构剖面图一</option>' +
                //     '<option value="../image/emap/3.jpg">围护结构剖面图二</option>' +
                //     '<option value="../image/emap/4.jpg">地质钻孔及管线迁改路由图</option>' +
                //     '<option value="../image/emap/5.jpg">基坑监测平面图</option>' +
                //     '<option value="../image/emap/6.jpg">基坑监测横断面图</option>' +
                //     '<option value="../image/emap/7.jpg">车站加固及降水井布置图</option>' +
                //     '<option value="../image/emap/8.jpg">车站围护纵剖面图</option>' +
                //     '<option value="../image/emap/9.jpg">车站地质剖面图</option>' +
                //     '<option value="../image/emap/10.jpg">钢支撑平面布置图</option>' +
                //     '</select>' +
                //     '</div>')
                //     .on('change', 'select', function () {
                //         emap.src = $(this).find('option:selected').val();
                //     });
            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
    $.ajax({
        type: "GET",
        url: baseURL + "/homePage/homePageQueryDgImage",
        dataType: "json",
        contentType: "application/json",
        success: function (json) {
            var str1="";
            if(json.code==200){
                result = json.result;


                for (var i=0;i<result.length;i++){
                    if(result[i].outletid==2){

                        /**
                         * 平面图切换
                         */

                        if(i==0){
                            str1=str1+'<option value='+result[i].imageurl+'>默认</option>'
                        }else{
                            str1=str1+'<option value='+result[i].imageurl+'>'+result[i].name+'</option>'
                        }



                    }else{
                        if(i==0){
                            str1=str1+'<option value='+result[i].imageurl+'>默认</option>'
                        }else{
                            str1=str1+'<option value='+result[i].imageurl+'>'+result[i].name+'</option>'
                        }
                        // result = json.result;
                        // emap.src = result.mapurl;
                        // emap.id = result.mapid;
                        // /**
                        //  * 平面图切换
                        //  */
                        // $(".eMap-container").append('<div class="select-box">' +
                        //     '<select class="form-control">' +
                        //     '<option value="../image/emap/522_2.png">默认</option>'+
                        //     '<option value="../image/emap/522_1.png">文桥区间隧道土建设计参数和施工进度图示</option>' +
                        //     '</select>' +
                        //     '</div>')
                        //     .on('change', 'select', function () {
                        //         emap.src = $(this).find('option:selected').val();
                        //     });
                        // $(".form-control").change();

                    }

                }
                $(".form-control").append(str1)
                $(".eMap-container")
                    .on('change', 'select', function () {

                        emap.src ="http://site.secusoft.cc:8070"+$(this).find('option:selected').val();
                        emap.id=4
                    });
                $(".form-control").change();



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
 * 加载电子地图上的设备
 * @param map
 */
var loadEmapCamera = function (map) {
    var $Emap = $("#Emap"), html = "", coordinate = [], x, y, direction = "", fontSize = 0, tips = "";
    $Emap.find('i.emap-camera').remove();
    $.ajax({
        type: "GET",
        url: baseURL + "/homePage/cameraMap/"+map.mapid,
        dataType: "json",
        contentType: "application/json",
        success: function (json) {
            if(json.code==200){
                $.each(json.result, function (i, item) {
                    coordinate = item.coordinate.trim().split(',');
                    x = parseInt(coordinate[0])/map.mapwidth*100;
                    y = parseInt(coordinate[1])/map.mapheight*100;
                    direction = "transform:rotate("+item.direction+"deg);-ms-transform:rotate("+item.direction+"deg);-webkit-transform:rotate("+item.direction+"deg);";
                    fontSize = parseInt(map.mapwidth/100) + 'px';
                    tips = item.ipaddress+':'+item.tcpport+' '+item.chanindex;
                    html = html + "<i class='iconfont icon-jianshiqicai01 emap-camera' title='"+tips+"' onmouseover='event.stopImmediatePropagation()' onclick='openVideo(event)' data-cameraInfo='"+JSON.stringify(item)+"' style='left: "+x+"%;top:"+y+"%;font-size:"+fontSize+";"+direction+"'></i>";
                });
                $Emap.append(html);
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
 * 加载电子地图上的门禁
 * @param map
 */
var loadEmapDoor = function (map) {
    var $Emap = $("#Emap"), html = "", coordinate = [], x, y, direction = "", fontSize = 0;
    $Emap.find('i.emap-door').remove();
    $.ajax({
        type: "GET",
        url: baseURL + "/homePage/accessDeviceMap/"+map.mapid,
        dataType: "json",
        contentType: "application/json",
        success: function (json) {
            if(json.code==200){
                $.each(json.result, function (i, item) {
                    coordinate = item.coordinate.trim().split(',');
                    x = parseInt(coordinate[0])/map.mapwidth*100;
                    y = parseInt(coordinate[1])/map.mapheight*100;
                    if(item.direction==1||item.direction==3){
                        direction = "icon-jinmen";
                    }else if(item.direction==2||item.direction==4){
                        direction = "icon-chumen";
                    }
                    fontSize = parseInt(map.mapwidth/100) + 'px';
                    html = html + '<i class="iconfont '+direction+' emap-door" data-doorInfo="'+JSON.stringify(item)+'" style="left:'+x+'%;top:'+y+'%;font-size: '+fontSize+';"></i>';
                });
                $Emap.append(html);
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
 * 定义电子地图拖拽事件和放缩事件
 */
$("#Emap")
    .draggable({ cursor: "move", start: function() {
        $(this).css('transform', "translate(0, 0)");
    }})
    .bind("mousewheel", function (ev) {
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
            $_this.width(nextwidth).height(nextheight);
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
            $_this.width(nextwidth).height(nextheight);
        }
        $_this.css({'left': '50%','top': '50%', 'transform': 'translate(-50%, -50%)'});
        $_this.find("i").css("font-size", nextwidth/100);
    });

/**
 * 加载轮播视频列表信息
 */
var loadLoopVideo = function () {
    var _this = Index;
    $.ajax({
        type: "GET",
        url: "/homePage/camera",
        dataType: "json",
        async: false,
        contentType: "application/json",
        success: function (json) {
            console.log(json)
                if (json.code == 200) {
                    _this.video = json.result;
                } else {
                    Feng.error(json.message);
                }

        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};

/**
 * 打开地图上设备视频
 */
var openVideo = function (event) {
    layer.closeAll();
    var loginId = -1, videoID = -1, player = null;
    var ocx = new OCX();
    var data = JSON.parse($(event.target).attr("data-cameraInfo"));
    Index.layerIndex = layer.open({
        type: 1,
        title: false,
        area: ['520px', '370px'], //宽高
        fixed:false,
        move: '.layui-layer-content',
        resize: false,
        // shade: false,
        content: '<div id="emapVideo"></div>',
        success: function (index, layero) {
            ocx.createPluginObject($('#emapVideo'), "emapOCX");
            if(!ocx.detectPluginObject()){
                return;
            }
            player = document.getElementById('emapOCX');
            player.SetSelectedWnd(0);
            player.SetWndColor("#07152F", "#1493E4", "#FF8000");
            loginId = player.LoginDevice(data.devicetype, data.ipaddress, data.tcpport, data.username, data.passwd);
            // if(loginId>=0){
            //     videoID = player.StartVideoPlay(loginId, data.chanindex, 1);
            //     if(videoID<0){
            //         alert("视频打开失败");
            //         layer.closeAll();
            //     }
            // }else{
            //     alert("设备登录失败");
            //     layer.closeAll();
            // }
        },
        cancel: function () {
            if(ocx.detectPluginObject()){
                player.StopVideoPlay(videoID);
                player.LogoutDevice(loginId);
            }
            $('#emapVideo').remove();
        },
        end: function () {
            if(ocx.detectPluginObject()){
                player.StopVideoPlay(videoID);
                player.LogoutDevice(loginId);
            }
            $('#emapVideo').remove();
        }
    });
};

/**
 * 打开ocx播放视频
 */
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    if($(e.target).attr("href")==="#actualVideo"){
        if(isFirst){
            isFirst = false;
            var _this = Index, videoID = -1;
            var player = document.getElementById('ocxObject');
            player.InitMultiScreen(1);
            player.ChangeWndCount(9);
            player.SetWndColor("#07152F", "#1493E4", "#FF8000");
            player.SetSelectedWnd(-1);
            window.setTimeout(function () {

                if (ipaddress==5){
                    var domainName=null
                    $.ajax({
                        async:false,
                        type: "get",
                        url:"/homePage/queryIpPort",
                        dataType: "json",
                        success:function (json) {
                            domainName=json.result.domainName
                            // item.ipaddress=json.result.domainName
                        }
                    })
                }
                console.log(domainName)
                $.each(_this.video, function (i, item) {
                    if (ipaddress==5){
                        item.ipaddress=domainName
                    }
                    if(i<_this.maxVideo){
                        var key = item.ipaddress+':'+item.tcpport;
                        if(!_this.onlineDevices[key]){
                            var loginId = player.LoginDevice(item.devicetype, item.ipaddress, item.tcpport, item.username, item.passwd);
                            if(loginId>=0){
                                _this.onlineDevices[key] = loginId;
                                videoID = player.StartVideoByWndIndex(loginId, i, item.chanindex, 1);
                                if(videoID>=0){
                                    _this.videoId.push(videoID);
                                }
                            }else{
                                return true;
                            }
                        }else{
                            videoID = player.StartVideoByWndIndex(_this.onlineDevices[key], i, item.chanindex, 1);
                            if(videoID>=0){
                                _this.videoId.push(videoID);
                            }
                        }
                    }else{
                        return false;
                    }
                });
            }, 100);

        }
    }else if($(e.target).attr("href")==="#softConstruct"){
        loadOperateReport();
    }
});

/**
 * 初始化tips
 * @param target
 * @param content
 * @param callback
 */
var initTips = function (target, content, onOpenCallBack, onCloseCallBack) {
    return new jBox('Tooltip', {
        attach: target,
        trigger: 'click',
        content: content,
        target: target,
        position: {
            x: 'left',
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
        onOpen: function () {
            if(typeof onOpenCallBack=="function"){
                onOpenCallBack();
            }
        },
        onClose: function (event, ui) {
            if(typeof onCloseCallBack=="function"){
                onCloseCallBack();
            }
        }
    });
};


/**
 * 加载文明施工报告（运营报告）
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
var loadOperateReport = function () {
    var date = new Date();
    var startTime = date.getFullYear() + '-' + parseInt(date.getMonth()+1) + '-' + date.getDate() + ' 00:00';
    var endTime = date.getFullYear() + '-' + parseInt(date.getMonth()+1) + '-' + date.getDate() + ' 23:59';
    var $container = $("#report-container").empty(), html = "";
    $.ajax({
        type: "GET",
        url: baseURL + "/report/patrolReportHistory",
        data: {startTime: startTime, endTime: endTime},
        dataType: "json",
        success: function (json) {
            if (json.code == 200) {
                if(JSON.stringify(json.result) == "{}"){
                    $container.append('<p class="noReport">暂无报告</p>');
                }else{
                    for(var x in json.result){
                        $.each(json.result[x], function (i, item) {
                            html = html + '<div class="col-sm-3">' +
                                '<div class="report">' +
                                '<img src="'+item.majorPic+'" alt="报告图">' +
                                '<div class="caption">' +
                                '<div>' +
                                '<p>' +
                                '<span class="alarm-from">视频通道:</span>' +
                                '<span class="alarm-info" title="'+item.cameraName+'">'+item.cameraName+'</span>' +
                                '</p>' +
                                '<p>' +
                                '<span class="alarm-from">报警内容:</span>' +
                                '<span class="alarm-info" title="'+item.itemName+'">'+item.itemName+'</span>' +
                                '</p>' +
                                '<p>' +
                                '<span class="alarm-from">严重程度:</span>' +
                                '<span class="alarm-info" data-value="'+item.severity+'">'+severityStr(item.severity)+'</span>' +
                                '</p>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                        });
                    }
                    $container.append(html);
                    $container.niceScroll({
                        cursorcolor: '#1493E4',
                        cursoropacitymax: 0.8,
                        cursorborder: "1px solid #1493E4",
                        touchbehavior: false,
                        cursorwidth: "5px",
                        cursorborderradius: "5px",
                        autohidemode: false,
                        horizrailenabled: false
                    });
                }
            }
        },
        error: function () {
            
        }
    });
};

/**
 * 一键撤离
 * @param event
 */

var evacuate = function(event){
    var oper = function(){
        $.ajax({
            type: "get",
            url:"/homePage/sendIoStatus",
            data: {evacuate:"yes",devicetype:"1"},
            dataType: "json",
            success:function (json) {
                console.log(json)
                if (json.result==1 || json.result==0 ){
                    $("#evacuate").hide()
                    $("#cancel").show()
                } else {
                    Feng.error("报警未响应");
                }
            },
            error:function (json) {

               console.log(json)
            }
        })
    };
    Feng.confirm("是否撤离？", oper);
    // event.stopImmediatePropagation();
    // event.preventDefault();
};

/**
 * 取消报警
 */
function cancel() {
    var oper = function(){
        $.ajax({
            type: "get",
            url:"/homePage/sendIoStatus",
            data: {evacuate:"no",devicetype:"1"},
            dataType: "json",
            success:function (json) {
                    $("#evacuate").show()
                    $("#cancel").hide()
            }

        })
    };
    Feng.confirm("是否取消报警？", oper);
}

/**
 * 打开起重吊装视频
 * @returns {boolean}
 */
var openCraneVideo = function () {
    loadLoopVideo();
    var _this = Index, videoID = -1;
    var player = document.getElementById('crane-video');
    player.SetSelectedWnd(0);
    player.SetWndColor("#07152F", "#1493E4", "#FF8000");
    var defaultVideo = _this.video[3];
    if (ipaddress==5) {
        $.ajax({
            async: false,
            type: "get",
            url: "/homePage/queryIpPort",
            dataType: "json",
            success: function (json) {
                console.log(json)
                defaultVideo.ipaddress = json.result.domainName
            }
        })
        var key = defaultVideo.ipaddress + ':' + defaultVideo.tcpport;
        console.log(key)
        if (!_this.onlineDevices[key]) {

            var loginId = player.LoginDevice(defaultVideo.devicetype, defaultVideo.ipaddress, defaultVideo.tcpport, defaultVideo.username, defaultVideo.passwd);
            if (loginId >= 0) {
                _this.onlineDevices[key] = loginId;
                videoID = player.StartVideoPlay(loginId, defaultVideo.chanindex, 1);
                if (videoID >= 0) {
                    _this.videoId.push(videoID);
                } else {
                             Feng.error("视频打开失败");
                }
            } else {
                      Feng.error("设备登录失败");
            }
        } else {
            videoID = player.StartVideoPlay(_this.onlineDevices[key], defaultVideo.chanindex, 1);
            if (videoID >= 0) {
                _this.videoId.push(videoID);
            } else {
                //        Feng.error("视频打开失败");
            }
        }
    }else {


        var key = defaultVideo.ipaddress+':'+defaultVideo.tcpport;
       if(!_this.onlineDevices[key]){
        var loginId = player.LoginDevice(defaultVideo.devicetype, defaultVideo.ipaddress, defaultVideo.tcpport, defaultVideo.username, defaultVideo.passwd);
        if(loginId>=0){
            _this.onlineDevices[key] = loginId;
            videoID = player.StartVideoPlay(loginId, defaultVideo.chanindex, 1);
            if(videoID>=0){
                _this.videoId.push(videoID);
            }else {
                //          Feng.error("视频打开失败");
            }
        }else{
            //       Feng.error("设备登录失败");
        }
    }else{
              videoID = player.StartVideoPlay(_this.onlineDevices[key], defaultVideo.chanindex, 1);
            if(videoID>=0){
            _this.videoId.push(videoID);
           }else {
            //        Feng.error("视频打开失败");
           }
       }
    }
};

/**
 * 周界轮播图
 */
var environmentPic = function (data) {
    var $this = $("#environment-marquee"), html = "";
    data.map(function (t) {
        var image = $this.find('div.sliderItem img#'+t.CameraNo);
        if(image.length) {
            $(image[0]).attr('src', t.imageUrl);
            $(image[0]).prev().text(t.cameraName);
        } else {
            html = html +  '<div class="sliderItem">' +
                                '<h3 style="width: 100%;text-align: center;position: absolute; left: 50%; top: 5px; transform: translate(-50%, 0); color: #1E9FFF; font-weight: bold;">'+ t.cameraName +'</h3>'+
                                '<img src="'+t.imageUrl+'" id="'+t.CameraNo+'">' +
                            '</div>';
        }
    });
    $.each($this.find('div.sliderItem'), function (i, item) {
        html = html + '<div class="sliderItem">' +
                            $(item).html() +
                        '</div>';
    });
    if($this.find('div.sliderItem').length>1){
        $this.data("initialized" ,false).sliderStop();
    }
    $this.empty().html(html).sliderInit({
        loop: true,
        navigation: 'hover',
        delay: 5000
    });
};

/**
 * WebSocket
 */
(function () {
    var token = getCookie("user.cookie"), isFirst = true;
    var target = 'ws://'+ws+'/webSocketServer?token='+token;
    if ('WebSocket' in window) {
        Index.ws = new WebSocket(target);

    } else if ('MozWebSocket' in window) {
        Index.ws = new MozWebSocket(target);

    } else {
        Feng.info('当前浏览器不支持WebSocket');
        return;
    }

    Index.ws.onopen = function (event) {
        console.log("open success");
    };

    Index.ws.onmessage = function (event) {
        var socketData = JSON.parse(event.data);
        if(socketData){
            switch (socketData.type.toString()){
                case '5':
                    // 实时报警
                    loadAlarmInfo();
                    break;
                case '6':
                    // 周界管理
                    environmentPic(socketData.data.data);
                    setTimeout(function () {
                        if(isFirst){
                            environmentPic(socketData.data.data);
                            isFirst = false;
                        }
                    }, 200);
                    break;
                case '7':
                    // 环境安全
                    // EnvGauge.updateRealData(socketData.data.data);
                    break;
                case '8':
                    //车辆管理
                    VehicleTip.update(socketData.data);
                    break;
                case '9':
                    // WaterLevelTips.initwaterLevelBar(socketData.data);
                    break;
                case '12':
                    DitchModule.update(socketData.data);
                    break;
                case '13':
                    StaffModule.update(socketData.data);
                    break;
                case '14':
                    EnviromentModule.update(socketData.data);
                    break;
                case '20':
                    chelipanduan(socketData.data.online,socketData.data.status)
                default :
                    break;
            }
        }

    };
    Index.ws.error = function (event){
        Feng.error("服务器连接失败");
    };
    Index.ws.onclose = function () {

    };
}());

window.onload = function () {
    // homePageQueryTitle();
    var ocx = new OCX();
    ocx.createPluginObject($('.video'));
    ocx.createPluginObject($('.crane-video'), 'crane-video');
    if(!ocx.detectPluginObject()){
        $(document.body).append('<div class="alert alert-warning" onclick="$(this).remove()" style="height: 1rem;line-height: 0.6rem;position: absolute;bottom: 0;right: 0.15rem;z-index: 100000;border: none;">' +
            '<a href="'+baseURL+'/resource/downLoadVideoPlayer">无法检测到视频播放插件，请下载插件后重启浏览器</a>' +
            '<i class="fa fa-close" style="position: absolute;top: 5px;right: 5px;cursor: default;"></i>'+
            '</div>');
        return;
    }
    if(ocx.isIE()){
        setTimeout(openCraneVideo, 2000);
    }
};

$(function () {
    queryDgImage();
    homePageQueryTitle();
    getWorkDays();
    loadAlarmInfo();
    loadEmap();

    $(".side-nav").hover(function () {
        $(this).children('i').removeClass('icon-htmal5icon44').addClass('icon-htmal5icon45');
        $(this).find('ul').show(500);
    }, function () {
        $(this).children('i').removeClass('icon-htmal5icon45').addClass('icon-htmal5icon44');
        $(this).find('ul').hide(500);
    })
});

window.onbeforeunload = function(){
    var player = document.getElementById('ocxObject');
    $.each(Index.videoId, function (i, item) {
        player.StopVideoPlay(item);
    });
    for(var loginId in Index.onlineDevices){
        player.LogoutDevice(Index.onlineDevices[loginId]);
    }
    $(player).remove();
    Index.ws.close();
    clearInterval(interval);
};

$("#staff-header").on("click",function(){
    layer.open({
        type: 1,
        title: "查看图片",
        area: ["604px", '604px'],
        maxHeight: 800,
        content: $('#image-magnification').html(),
    })
    $(".image-book").attr("src",$(this).attr("src")).width("100%").height("100%")
})
$("#car-pic").on("click",function(){

    layer.open({
        type: 1,
        title: "查看图片",
        area: ["1300px", '760px'],
        maxHeight: 800,
        content: $('#image-magnification').html(),
    })
    $(".image-book").attr("src",$(this).attr("src")).width("100%").height("100%")

})

$("#ditch-header").on("click",function(){
    layer.open({
        type: 1,
        title: "查看图片",
        area: ["604px", '604px'],
        maxHeight: 800,
        content: $('#image-magnification').html(),
    })
    $(".image-book").attr("src",$(this).attr("src")).width("100%").height("100%")
})


function chelipanduan(number1,number2){
    if (number1==1){
        $("#off-line-evacuate").hide()
        if (number2==-1){
            $("#evacuate").show()
            $("#cancel").hide()
        } else{
            $("#evacuate").hide()
            $("#cancel").show()
        }
    } else {
        $("#evacuate").hide()
        $("#cancel").hide()
        $("#off-line-evacuate").show()
    }
}

$.ajax({
    type: "get",
    url:"/homePage/queryIoStatus",
    dataType: "json",
    success:function (json) {
        // console.log(json)
        if (json.result.online==1){
            if (json.result.status==1){
                $("#evacuate").hide()
                $("#cancel").show()

            } else{
                $("#evacuate").show()
                $("#cancel").hide()

            }
        } else {
            $("#evacuate").hide()
            $("#cancel").hide()
            $("#off-line-evacuate").show()
        }
    }
})
function IOcontrols(){
    $.ajax({
        async: false,
        type: 'GET',
        url: "/gas/selectAllIoInfo",
        dataType: "json",
        success:function (json) {
            if (json.code==200){
                $(".showIO").empty()
                $.each(json.result,function (index,item) {
                    console.log(item)
                    if (item.devicetype==2){
                        if (item.online==1){
                            if (item.automanual==1){
                                if (item.status==-1){
                                    $(".showIO").append("<div class="+item.devicename+">" +
                                        "<button class='btn' style='background: rgb(46, 197, 94); color: #FFFFFF;' onclick='start("+item.id+","+item.status+")'>"+item.devicename+"</button>" +
                                        "<br>" +
                                        "<input type='checkbox' onclick='Automatic("+item.id+","+item.automanual+")'>  自动" +
                                        "</div>")
                                }else {
                                    $(".showIO").append("<div class="+item.devicename+">" +
                                        "<button class='btn' style='background: rgb(46, 197, 94); color: #FFFFFF;' onclick='start("+item.id+","+item.status+")'>"+item.devicename+" 启动中···</button>" +
                                        "<br>" +
                                        "<input type='checkbox' onclick='Automatic("+item.id+","+item.automanual+")'>  自动" +
                                        "</div>")
                                }

                            }else {
                                if (item.status==-1){
                                    $(".showIO").append("<div class="+item.devicename+">" +
                                        "<button class='btn' style='background: rgb(46, 197, 94); color: #FFFFFF;'>"+item.devicename+" </button>" +
                                        "<br>" +
                                        "<div onclick='motion()'></div>" +
                                        "<input type='checkbox' checked onclick='Automatic("+item.id+","+item.automanual+")'>  自动" +
                                        "</div>")
                                }else {
                                    $(".showIO").append("<div class="+item.devicename+" style='border-bottom:1px  '>" +
                                        "<button class='btn' style='background: rgb(46, 197, 94); color: #FFFFFF;'>"+item.devicename+" 启动中···</button>" +
                                        "<div onclick='motion()'></div>" +
                                        "<input type='checkbox' checked onclick='Automatic("+item.id+","+item.automanual+")'>   自动" +
                                        "</div>")
                                }

                            }

                        }else {
                            $(".showIO").append("<div>" +
                                "<button class='btn' style='background: #8a8a8a; color: #FFFFFF;' onclick='offline()'>"+item.devicename+"</button>" +
                                "</div>")
                        }
                        // $(".showIO").append("<div>" +
                        //     "<button class='btn' style='background: #cccccc; color: #FFFFFF;'>"+item.devicename+" 启动中···</button>" +
                        //     "<br>" +
                        //     "<input type='checkbox'>自动" +
                        //     "</div>")
                    }
                })
            } else {
                Feng.error(json.message)
            }
        }
    })
}
$("#IOcontrol").on("click",function () {
    layer.open({
        type: 1,
        title: "喷淋按钮",
        area: ["604px", '604px'],
        skin: '#ffffff',
        maxHeight: 800,
        content: $('#IOspray').html(),
    })
    IOcontrols()
})
function Automatic(id,automanual) {
    if (automanual==1){
        var automanuals=-1
    }  else {
        var automanuals=1
    }
    var robotization={
        id:id,
        automanual:automanuals
    }
    $.ajax({
        async: false,
        type: 'post',
        url: "/homePage/updateAutomanual",
        data:JSON.stringify(robotization),
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success:function (json) {
            if (json.code==200){
                Feng.success(json.result)
                IOcontrols()
            } else {
                Feng.error(json.message)
                IOcontrols()
            }
        }
    })
}
function start(id,status) {
    if (status==1){
        var status="no"
    } else {
        var status="yes"
    }
    console.log(status)
    $.ajax({
        type: "get",
        url:"/homePage/sendIoPlStatus",
        data: {evacuate:status,id:id},
        dataType: "json",
        success:function (json){
            console.log(json)
            IOcontrols()
        }
    })
}
function offline() {
    Feng.error("控制器不在线")
}
function motion() {
    Feng.error("自动控制中请勿点击")
}