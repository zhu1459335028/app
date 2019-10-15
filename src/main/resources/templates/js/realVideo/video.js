var ConfigObject = {
    ztreeObj: null,     //ztree对象
    node: null,  //当前配置通道
    curWindowIndex: 0,  //当前选中窗口
    onlineDevices: new Object(), //已登录成功的设备
    play: new Object()  //记录窗口和视频播放关系{ip,port,videoId}
};

var showTips = function (message) {
    ConfigObject.layerIndex = layer.open({
        type: 1,
        title: false,
        area: ['250px','85px'],
        fix: false,
        maxmin: false,
        resize: false,
        content: $('#tips').html(),
        success: function (layero, index) {
            $(".test").text(message);
            setTimeout(function (args) {
                layer.close(ConfigObject.layerIndex);
            }, 2000);
        }
    });
};

var loginOut = function () {
    ConfigObject.layerIndex = layer.open({
        type: 1,
        title: false,
        area: ['250px','120px'],
        closeBtn: 0,
        fix: false,
        maxmin: false,
        resize: false,
        content: $('#loginout').html(),
        success: function (layero, index) {
            $("#sign-out").click(function () {
                delCookie('user.cookie');
                location.href='./login.html';
            });
        }
    });
};
/**
 * 平台标题
 * @param data
 */
var outletid=null;
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
                outletid=json.result.outletid
                $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }

        }
    });
};

/**
 * 屏蔽设备点击事件
 * @param treeId
 * @param treeNode
 * @param clickFlag
 * @returns {boolean}
 */
var zTreeBeforeClick = function (treeId, treeNode, clickFlag) {
    if(treeNode.isParent){
        return false;
    }
};

/**
 * 单击打开设备通道视频
 * @param e
 * @param treeId
 * @param treeNode
 */
var onClickTree = function (e, treeId, treeNode) {
    if(!treeNode.isParent){
        /**
         * 屏蔽弹出屏播放
         */
        var ocx = document.getElementById('ocxObject');
        console.log(outletid)
        if (outletid==5){
            $.ajax({
                async:false,
                type: "get",
                url:"/homePage/queryIpPort",
                dataType: "json",
                success:function (json) {
                    console.log(json)

                    treeNode.ipaddress=json.result.domainName
                }
            })
        } 
        var key = treeNode.ipaddress + ':' + treeNode.tcpport;
        console.log(key)
        console.log(1545454)
        if(!ConfigObject.onlineDevices[key]){

            var loginId = ocx.LoginDevice(treeNode.devicetype, treeNode.ipaddress, treeNode.tcpport, treeNode.username, treeNode.passwd);

            if(loginId>=0){
                ConfigObject.onlineDevices[key] = loginId;
            }else{
                showTips("设备登录失败");
                return;
            }
        }
        var videoId = ocx.StartVideoPlay(ConfigObject.onlineDevices[key], treeNode.chanindex, 1);
        if(videoId>=0){
            ConfigObject.play[ConfigObject.curWindowIndex] = {
                ip: treeNode.ipaddress,
                port: treeNode.tcpport,
                camerano: treeNode.camerano,
                videoId: videoId
            };
        }else{
            showTips("视频打开失败");
            // Feng.error("视频打开失败");
        }
        e.stopImmediatePropagation();
    }
};

/**
 * 添加自定义控件
 */
var addHoverDom = function (treeId, treeNode) {
    if(treeNode.isParent) return;
    if (treeNode.editNameFlag || $("#congBtn_" + treeNode.tId).length > 0) return;
    var sObj = $("#" + treeNode.tId + "_ico");

    var addStr = "<img src='../../image/image/config.png' style='margin-top: -8px;width: 20px;' id='congBtn_" + treeNode.tId + "' title='配置' onfocus='this.blur()'>";
    sObj.before(addStr);
    var btn = $("#congBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function (event) {
        ConfigObject.node = treeNode;
        ConfigObject.layerIndex = layer.open({
            type: 1,
            title: '设备配置',
            area: ['500px','auto'],
            fix: false,
            maxmin: false,
            resize: false,
            content: $('#video_config').html(),
            success: function (layero, index) {
                $("input[type='radio']").change(function () {
                    if($(this).val()==1){
                        $("div[data-target='"+this.name+"']").show();
                    }else{
                        $("div[data-target='"+this.name+"']").hide();
                    }
                });
                $("input[type='radio'][name='playback'][value='"+(treeNode.playback || 0)+"']").prop('checked', true).trigger('change');
                $("input[type='radio'][name='enableSlicing'][value='"+(treeNode.enableSlicing || 0)+"']").prop('checked', true).trigger('change');
                if(treeNode.playback==1) {
                    $('input[name="second"]').val(treeNode.second);
                }
                if(treeNode.enableSlicing==1) {
                    $('input[name="slicingTime"]').val(treeNode.slicingTime);
                }
            },
            cancel: function(index, layero){

            },
            end: function () {
                $("input[type='radio']").blur();
            }
        });
        event.stopImmediatePropagation();
    });
};
/**
 * 移除自定义控件
 */
var removeHoverDom = function (treeId, treeNode) {
    $("#congBtn_"+treeNode.tId).unbind().remove();
};


var setting = {
    view : {
        showLine: false,
        dblClickExpand : true,
        selectedMulti : false,
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom
    },
    data : {
        simpleData : {
            enable : true,
            idKey: "camerano",
            pIdKey: "deviceno",
            rootPId: null
            }
        },
    edit : {
        drag : {
                isCopy : false,
                isMove : false
            }
        },
    callback : {
        beforeClick: zTreeBeforeClick,
        onClick : onClickTree
    }
};

/**
 * 加载设备通道
 */
var loadDevice = function () {
    var ztree = new $ZTree("device-tree", null);
    ztree.setSettings(setting);
    $.ajax({
        type: "GET",
        url: baseURL + "/device/deviceCamera",
        async: false,
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                $.each(json.result, function (i, item) {
                    if(item.type==1){
                        item.icon = '../image/image/device-on.png';
                    }else if(item.type==2){
                        if(item.playback==0){
                            item.icon = '../image/image/video-on.png';
                        } else if(item.playback==1){
                            item.icon = '../image/image/video-loop.png';
                        } else if(item.playback==2){
                            item.icon = '../image/image/video-on.png';
                        }
                        if(item.enableSlicing==1){
                            item.name = item.name + '（' + item.slicingTime + '分）';
                        }
                    }
                });
                ConfigObject.ztreeObj =  ztree.initLocal(json.result);
            }else{

                Feng.error(json.message);
            }
        },
        error: function (err) {

            Feng.error(err.message);
        }
    });
    var filter = function (node) {
        if(node.isParent){
            return false;
        }else {
            if(node.name.indexOf($('#searcher').val())>=0){
                return false;
            }else{
                return true;
            }
        }
    };
    $('#searcher').keyup(function (e) {
        var keyword = $(this).val();
        ztree.filter(ConfigObject.ztreeObj, keyword, filter);
    });
};

/**
 * 通道配置
 */
var modifyConfig = function () {
    var data = new Object();
    var checkedRadios = $("input[type='radio']:checked");
    $.each(checkedRadios, function (i ,item) {
        data[item.name] = item.value;
    });
    if(data.playback==1){
        data.second = $('input[name="second"]').val();
        if(!/^[1-9]\d*$/.test(data.second)){
            Feng.info("请输入正确轮播时长");
            return;
        }
    }
    if(data.enableSlicing==1){
        data.slicingTime = $('input[name="slicingTime"]').val();
        if(!/^[1-9]\d*$/.test(data.slicingTime)){
            Feng.info("请输入正确轮播时长");
            return;
        }
    }
    $.ajax({
        type: "POST",
        url: baseURL + "/device/setCamera/"+ConfigObject.node.camerano.split('_')[1],
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                layer.close(ConfigObject.layerIndex);
                Feng.success("配置成功");
                if(data.playback==1){
                    ConfigObject.node.icon = '../image/image/video-loop.png';
                    ConfigObject.node.second = data.second;
                }else if(data.playback==0){
                    ConfigObject.node.icon = '../image/image/video-on.png';
                    ConfigObject.node.second = 0;
                }
                if(data.enableSlicing==1) {
                    ConfigObject.node.name = ConfigObject.node.name.split('（')[0];
                    ConfigObject.node.name = ConfigObject.node.name + '（' + data.slicingTime + '分）';
                    ConfigObject.node.slicingTime = data.slicingTime;
                }else if(data.enableSlicing==0) {
                    ConfigObject.node.name = ConfigObject.node.name.split('（')[0];
                    ConfigObject.node.slicingTime = 0;
                }
                ConfigObject.node.playback = data.playback;
                ConfigObject.node.enableSlicing = data.enableSlicing;
                ConfigObject.ztreeObj.updateNode(ConfigObject.node);
            }else{
                Feng.error("配置失败");
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};


/**
 * 云台控制
 */
$(".ptz").on('mousedown mouseup mouseover mouseout click', 'img', function (event) {
    var oper = -1, speed = 3, _this = this;  //oper=0 启动 ; oper=1 停止; speed=3 默认速度为3
    var value = $(_this).data("value");
    var ocx = document.getElementById('ocxObject');
    switch (event.type){
        case 'mousedown':
            oper = 0;
            this.src = '../image/ptz/' + this.name +'-active.png';
            break;
        case 'mouseup':
            oper = 1;
            this.src = '../image/ptz/' + this.name +'.png';
            break;
        case 'mouseover':
            this.src = '../image/ptz/' + this.name +'-hover.png';
            break;
        case 'mouseout':
            this.src = '../image/ptz/' + this.name +'.png';
            break;
        case 'click':
            if(value==14){
                var state = $(_this).attr("data-isSpin");
                if(state=="false"){
                    ocx.PTZControlCurrentWnd(value, 0, speed);
                    $(_this).attr("data-isSpin", "true");
                }else{
                    ocx.PTZControlCurrentWnd(value, 1, speed);
                    $(_this).attr("data-isSpin", "false");
                }
            }
            break;
        default:
            this.src = '../image/ptz/' + this.name +'.png';
            break;
    }
    if(oper>=0 && value!=14){
        ocx.PTZControlCurrentWnd(value, oper, speed);
    }
    event.stopImmediatePropagation();
    event.preventDefault();
});


/**
 *
 */
$(".ava-screen").on("mousedown mouseup click", 'img', function (event) {
    switch (event.type){
        case 'mousedown':
            this.src = '../image/screen/'+ this.name +'-active.png';
            break;
        case 'mouseup':
            this.src = '../image/screen/'+ this.name +'.png';
            break;
        case 'click':
            var screen = $(this).data("value");
            var ocx = document.getElementById('ocxObject');
            ocx.ChangeWndCount(screen);
            break;
        default:
            this.src = '../image/screen/'+ this.name +'.png';
            break;
    }
    event.stopImmediatePropagation();
    event.preventDefault();
});

ConfigObject.localChanindex = function (wndIndex, playStatus, deviceInfo) {
    this.curWindowIndex = wndIndex;
    this.ztreeObj.cancelSelectedNode();
    if(playStatus==1){
        var node = this.ztreeObj.getNodeByParam("camerano", this.play[wndIndex].camerano);
        this.ztreeObj.selectNode(node);
    }
};

ConfigObject.stopVideo = function () {
    var ocx = document.getElementById('ocxObject');
    ocx.StopCurrentVideoPlay();
    this.ztreeObj.cancelSelectedNode();
    delete this.play[this.curWindowIndex];
};

window.onload = function () {
    var ocx = new OCX();
    ocx.createPluginObject($('.video'));
    if(!ocx.detectPluginObject()){
        $(document.body).append('<div class="alert alert-warning" onclick="$(this).remove()" style="height: 1rem;line-height: 0.6rem;position: absolute;bottom: 0;right: 0.15rem;z-index: 100000;border: none;">' +
            '<a href="'+baseURL+'/resource/downLoadVideoPlayer">无法检测到视频播放插件，请下载插件后重启浏览器</a>' +
            '<i class="fa fa-close" style="position: absolute;top: 5px;right: 5px;cursor: default;"></i>'+
            '</div>');
        return;
    }
    var ocxObject = document.getElementById('ocxObject');
    ocxObject.InitMultiScreen(1);
};

window.onbeforeunload = function(){
    var ocx = document.getElementById('ocxObject');
    for(var wnIndex in ConfigObject.play){
        ocx.LogoutDevice(ConfigObject.play[wnIndex].videoId);
    }
    for(var loginId in ConfigObject.onlineDevices){
        ocx.LogoutDevice(ConfigObject.onlineDevices[loginId]);
    }
    $(ocx).remove();
};

$(function () {
    var maxHeight = parseInt($('section.content').css('min-height'));
    $(".video").height(maxHeight-65);
    $('#device-tree').css({'max-height': maxHeight-300, 'overflow-y': 'auto'});

    loadDevice();
    homePageQueryTitle();
});