var MeasureConfig = {
    settlement: {
        type: [],
        form: {
            layer: 'measure-settlement-point',
            name: 'measure-settlement-point'
        }
    },
    inclinometer: {
        type: [],
        form: {
            layer: 'measure-inclinometer-point',
            name: 'measure-inclinometer-point'
        }
    },
    axialForce: {
        type: [],
        form: {
            layer: 'measure-axialForce-point',
            name: 'measure-axialForce-point'
        }
    },
    waterLevel: {
        type: [],
        form: {
            layer: 'measure-waterLevel-point',
            name: 'measure-waterLevel-point'
        }
    },
    isDrawing: {
        status: false,
        data: null
    }
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
                console.log("111")
                console.log(json)
                if(json.result.outletid==2){
                    $("#Dungouhuans").show()
                }else {
                    $("#Dungouhuans").hide()
                }
                $(".header-title h1").text(json.result.title);
                $("title").text(json.result.title);
            }

        }
    });
};

/**
 * 打开弹出层
 * @param id
 * @param callback
 */
MeasureConfig.openLayer = function (content, title, onOpen, onClose) {
    var _this = this;
    var maxHeight = window.screen.height * 0.5;
    this.layerIndex = layer.open({
        type: 1,
        title: title,
        area: ['800px', 'auto'],
        maxHeight: maxHeight,
        content: content,
        success: onOpen,
        cancel: onClose,
        end: function () {
            _this.isDrawing = {
                status: false,
                data: null
            }
        }
    });
};


/**
 * 点位类型适配
 * @param type
 * @returns {string}
 */
MeasureConfig.typeAdapter = function (type) {
    var _type = '';
    switch (type.toString()){
        case '1': _type = 'settlement';
            break;
        case '2': _type = 'inclinometer';
            break;
        case '3': _type = 'settlement';
            break;
        case '4': _type = 'axialForce';
            break;
        case '5': _type = 'waterLevel';
            break;
        default: _type = 'settlement';
            break;
    }
    return _type;
};

/**
 * 点位匹配
 * @param point
 */

MeasureConfig.pointAdapter = function (point) {
    var marker = '', status = '';
    const drawerWidth = this.drawer.width(), drawerHeight = this.drawer.height(), radius = 10;
    const offsetXP = point.offsetX/drawerWidth, offsetYP = point.offsetY/drawerHeight;
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
};

/**
 * 查询工程图
 */
MeasureConfig.queryImage = function () {
    var _this = this, $drawer = $("#measure-drawing");
    $.ajax({
        url: '/settlement/querySettleScreen',
        type: "POST",
        dataType: 'json'
    }).then(function (json) {
        if(json.code==200){

            if(json.result.length){
                var data = json.result[0];
                console.log(data)
                $drawer.find('p').remove();
                $drawer.find('#image-container').unbind().remove();
                $('<div id="image-container" data-id="'+data.img.id+'"></div>').appendTo($drawer);
                var emap = new Image();
                emap.onload = function () {
                    _this.drawer = $("#image-container").width(this.naturalWidth).height(this.naturalHeight).css({
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
                    _this.initPoint(data.points);
                };
                emap.onerror = function () {
                    Feng.error("工程图加载失败");
                };
                emap.src = data.img.url;
            }else{
                $('<p class="empty-drawing-tips">未导入图纸</p>').appendTo($drawer);
            }
        }else{
            Feng.error(json.message);
        }
    },function () {
        Feng.error('请求图片资源异常');
    });
};

/**
 * 加载初始点位
 * @param points
 */
MeasureConfig.initPoint = function (points) {
    var _this = this;
    var drawerWidth = this.drawer.width(), drawerHeight = this.drawer.height();
    points.forEach(function (t) {
        var point = $.extend(true, {}, t, {id: 'point-'+t.id, offsetX: t.x*drawerWidth, offsetY: t.y*drawerHeight});
        var pointStr = _this.pointAdapter(point);
        $(pointStr).appendTo(_this.drawer).click(_this.editPoint.bind(_this)).contextmenu({
            target: '#context-menu',
            onItem: function (context, e) {
                _this.deletePoint(context, e)
            }
        });
    });
};

/**
 * 导入工程图
 * @param event
 */
MeasureConfig.importImage = function (event) {
    var _this = this, target = event.currentTarget;
    var imageName = target.value;
    var fileTArr = imageName.split(".");
    var filetype = fileTArr[fileTArr.length-1];
    if(filetype && (filetype == "png" || filetype == "jpg" || filetype == "dwg")){
        var formData = new FormData();
        formData.append("image", target.files[0]);
        $.ajax({
            url: '/settlement/uploadSettleImage',
            type: "POST",
            data: formData,
            contentType: false,
            processData: false
        }).then(function (json) {
            if(json.code==200){

                var imgurl = json.result;
                $.ajax({
                    url: '/settlement/saveSettleImageInfo',
                    type: "POST",
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({'imgurl': imgurl}),
                    dataType: 'json'
                }).then(function (json) {
                    console.log(json)
                    if(json.code==200){
                        _this.queryImage();
                    }else{
                        Feng.error(json.message);
                    }
                });
            }else{

                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    } else {
        Feng.info("请选择图片文件");
    }
    event.stopImmediatePropagation();
};

/**
 * 添加点位
 * @param event
 */
MeasureConfig.drawPoint = function (event) {
    var offsetX = event.offsetX || event.layerX, offsetY = event.offsetY || event.layerY, _this = this;
    var marker = this.pointAdapter({
        id: 'temporary-point',
        locationno: '',
        code: this.isDrawing.data.code,
        type: this.isDrawing.data.id,
        alarm: 0,
        offsetX: offsetX,
        offsetY: offsetY
    });
    $(marker).appendTo(this.drawer).click(this.editPoint.bind(this)).contextmenu({
        target: '#context-menu',
        onItem:  function (context, e) {
            _this.deletePoint(context, e)
        }
    });
    this.isDrawing.data.target = $('i#temporary-point');
    this.drawer.off('mouseup');
    var code = this.isDrawing.data.code;
    this.getConfigInfo(this.isDrawing.data.id).then(function (json) {
        if(json.code==200){
            var formStr = _this.renderForm(json.result);
            formStr = '<form class="form-horizontal" name="measure-point" role="form">' +
                '<div class="form-group">' +
                    '<div class="row">' +
                        '<label class="col-sm-2 control-label">点位编号</label>' +
                        '<div class="col-sm-10">' +
                            '<div class="input-group point-code-group" style="margin: 0 8px;">' +
                                '<span class="input-group-addon prefix-code">'+code+'</span>' +
                                '<input class="form-control" type="text">' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                formStr +
                '</form>' +
                '<div class="text-center vertical-spacing">' +
                '<button class="btn btn-sm btn-primary" style="margin: 0 8px;" onclick="MeasureConfig.savePoint()">保存</button>' +
                '<button class="btn btn-sm btn-danger" style="margin: 0 8px;" onclick="MeasureConfig.cancelDraw()">取消</button>' +
                '</div>';
            _this.openLayer(formStr, '点位配置', null, _this.cancelDraw.bind(_this));
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('请求配置信息异常');
    });
};


/**
 * 添加点位
 * @param event
 */
MeasureConfig.addPoint = function (event) {
    var $this = $(event.target);
    if(!this.drawer){
        Feng.info('请导入图纸');
        return;
    }
    this.isDrawing = {
        status: true,
        data: {
            id: $this.data('id'),
            type: $this.data('type'),
            code: $this.data('code'),
            color: $this.data('color') || 'lime'
        }
    };
    if(this.isDrawing.data.code){
        this.drawer.off('mouseup').on('mouseup', this.drawPoint.bind(this));
    }else{
        Feng.info('未知点位类型');
    }
};

/**
 * 取消点位添加
 */
MeasureConfig.cancelDraw = function () {
    if(this.isDrawing.data){
        this.isDrawing.data.target.remove();
    }
    location.reload();
    layer.close(this.layerIndex);

};

/**
 * 修改点位配置
 * @param event
 */
MeasureConfig.editPoint = function (event) {
    var $point = $(event.target), _this = this;
    _this.edit = $point;
    var type = $point.data('type'), code = $point.data('code'), locationno = $point.text();
    this.getConfigInfo(type, locationno).then(function (json) {
        if(json.code==200){
            var formStr = _this.renderForm(json.result);
            formStr = '<form class="form-horizontal" name="measure-point" role="form">' +
                '<div class="form-group">' +
                '<div class="row">' +
                '<label class="col-sm-2 control-label">点位编号</label>' +
                '<div class="col-sm-10">' +
                '<div class="input-group point-code-group" style="margin: 0 8px;">' +
                '<span class="input-group-addon prefix-code">'+code+'</span>' +
                '<input class="form-control" type="text" value="'+locationno.substring(3)+'">' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                formStr +
                '</form>' +
                '<div class="text-center vertical-spacing">' +
                '<button class="btn btn-sm btn-primary" style="margin: 0 8px;" onclick="MeasureConfig.savePoint()">保存</button>' +
                '<button class="btn btn-sm btn-danger" style="margin: 0 8px;" onclick="MeasureConfig.cancelDraw()">取消</button>' +
                '</div>';
            _this.openLayer(formStr, '点位配置', null);
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('请求配置信息异常');
    });
};

/**
 * 保存点位信息
 */
MeasureConfig.savePoint = function () {
    var form = document.forms['measure-point'], param = new Array(), _this = this;
    var $no = $(form).children().first();
    var noInput = $no.find('input')[0];
    if(!noInput.value){
        Feng.info('请输入点位编号');
        return;
    }
    var locationno = $(noInput).prev().text() + noInput.value;
    $.each($(form).children(':gt(0)'), function (i, item) {
        var $item = $(item), valueArr = new Array();
        $.each($item.find('input'), function (index, t) {
            var value = t.value;
            if(!value || value==undefined || value==null){
                value = ' ';
            }
            valueArr.push(value);
        });
        param.push({
            id: _this.edit?parseInt(_this.edit.attr('id').substring(6)):'',
            locationno: locationno,
            settleimageid: _this.drawer.data('id'),
            settleunitmonid: _this.edit?_this.edit.data('type'): _this.isDrawing.data.id,
            thrnameid: item.id,
            value: valueArr.join(','),
            x: _this.edit? _this.edit.data('offsetxp') : _this.isDrawing.data.target.data('offsetxp'),
            y: _this.edit? _this.edit.data('offsetyp') : _this.isDrawing.data.target.data('offsetyp')
        });
    });
    $.ajax({
        type: 'POST',
        url: '/settlement/saveOrUpdateLocationNoThresholdList',
        data: JSON.stringify(param),
        cache: false,
        contentType: "application/json;charset=utf-8",
        dataType: 'json'
    }).then(function (json) {
        if(json.code==200){
            if(_this.edit){
                _this.edit.text(locationno);
                _this.edit = null;
            }else{
                _this.isDrawing.data.target.attr('id', 'point-'+json.result.id).text(locationno);
            }
            layer.close(_this.layerIndex);
            Feng.success('保存成功');
            location.reload();
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('保存点位请求异常');
    });
};

/**
 * 删除点位
 */
MeasureConfig.deletePoint = function (context, event) {
    var oper = function () {
        $.ajax({
            type: 'POST',
            url: '/settlement/deletePoint',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({locationno: context.text()}),
            dataType: 'json'
        }).then(function (json) {
            if(json.code==200){
                context.unbind().remove();
                Feng.success('删除成功');
                location.reload();
            }else{
                Feng.error(json.message);
            }
        }, function () {
            Feng.error('删除点位请求异常');
        });
    };
    Feng.confirm('是否删除点位？', oper);
};

/**
 * 获取配置信息
 * @param id
 * @returns {*}
 */
MeasureConfig.getConfigInfo = function (id, locationno) {
    return $.ajax({
        type: 'GET',
        url: '/settlement/queryThresholdName',
        data: {id: id, locationno: locationno},
        dataType: 'json'
    });
};


MeasureConfig.renderForm = function (formData) {
    var formStr = '';
    formData.forEach(function (t) {
        switch (t.number.toString()){
            case '1':
                formStr = formStr + '<div id="'+t.id+'" data-thresholeid="'+t.thresholeid+'" class="form-group">' +
                    '<div class="row">' +
                    '<label class="col-sm-2 control-label">'+t.name+'</label>' +
                    '<div class="col-sm-10">' +
                    '<input class="form-control" type="text" value="'+t.value+'" style="margin: 0 8px;">' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                break;
            case '2':
                var values = t.value.split(',');
                formStr = formStr + '<div id="'+t.id+'" data-thresholeid="'+t.thresholeid+'" class="form-group">' +
                    '<div class="row">' +
                    '<label class="col-sm-2 control-label">'+t.name+'</label>' +
                    '<div class="col-sm-10">' +
                    '<input type="number" class="form-control inline-input" value="'+values[0]+'" />' +
                    '——' +
                    '<input type="number" class="form-control inline-input" value="'+values[1]+'" />' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                break;
        }
    });
    return formStr;
};

/**
 * 阈值设置
 * @param event
 */
MeasureConfig.setThreshold = function (event) {
    var _this = this, id = $(event.target).data('id');
    this.getConfigInfo(id).then(function (json) {
        if(json.code==200){
            var formStr = _this.renderForm(json.result);
            formStr = '<form class="form-horizontal" name="measure-point-config" role="form">' + formStr +
                '</form>' +
                '<div class="text-center vertical-spacing">' +
                '<button class="btn btn-sm btn-primary" style="margin: 0 8px;" onclick="MeasureConfig.saveThreshold('+id+')">保存</button>' +
                '<button class="btn btn-sm btn-danger" style="margin: 0 8px;" onclick="MeasureConfig.cancelDraw()">取消</button>' +
                '</div>';
            _this.openLayer(formStr, '阈值设置');
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('请求配置信息异常');
    });
};

/**
 * 保存阈值
 * @param id
 */
MeasureConfig.saveThreshold = function (id) {
    var _this = this;
    var form = document.forms['measure-point-config'], param = new Array();
    $.each($(form).children(), function (i, item) {
        var $item = $(item), valueArr = new Array();
        $.each($item.find('input'), function (index, t) {
            var value = t.value;
            if(!value || value==undefined || value==null){
                value = ' ';
            }
            valueArr.push(value);
        });
        param.push({
            id: $item.data('thresholeid'),
            settleunitmonid: id,
            thrnameid: item.id,
            value: valueArr.join(',')
        });
    });
    $.ajax({
        type: 'POST',
        url: '/settlement/saveOrUpdateThresholdList',
        data: JSON.stringify(param),
        cache: false,
        contentType: "application/json;charset=utf-8",
        dataType: 'json'
    }).then(function (json) {
        if(json.code==200){
            layer.close(_this.layerIndex);
            Feng.success('设置成功');
            location.reload();
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('阈值设置请求异常');
    });
};


/**
 * 初始化配置工具栏
 */
MeasureConfig.initToolbar = function () {
    var _this = this;
    $.ajax({
        type: 'GET',
        url: '/settlement/selectSettleunitmon',
        dataType: 'json',
        success: function (json) {
            if(json.code==200){
                var menus = '';
                json.result.forEach(function (t) {
                    var _type = _this.typeAdapter(t.type);
                    _this[_type].type.push(t.code);
                    menus = menus + '<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" data-id="'+t.id+'" data-type="'+t.type+'" data-code="'+t.code+'">'+t.name+'</a></li>';
                });
                $("#addPoint").next().html(menus).click(_this.addPoint.bind(_this));
                $("#setThreshold").next().html(menus).click(_this.setThreshold.bind(_this));
            }else{
                Feng.error(json.message);
            }
        },
        error: function () {
            Feng.error('请求点位类型异常');
        }
    });
};

$(function () {
    var maxHeight = parseInt($('section.content').css('min-height'));
    $("#measure-drawing").height(maxHeight-90);
    MeasureConfig.initToolbar();
    MeasureConfig.queryImage();
    homePageQueryTitle();
});





function tanchucengji(){  //录入环数信息的弹出窗
   
    var maxWidth = window.screen.width * 0.5;
    layer.open({
        type: 1,
        title: "盾构管片环号录入",
        area: [maxWidth+"px", 'auto'],
        maxHeight: 800,
        content: $('#Ring-number-information').html(),
    })
    var date = new Date();
    $(".datetime").daterangepicker({
        autoUpdateInput: true,
        ranges: {
            // '今天': [moment().format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '昨天': [moment().subtract(1, 'days').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'days').format("YYYY-MM-DD 23:59")],
            // '最近一周': [moment().subtract(6, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '最近30天': [moment().subtract(29, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '本月': [moment().startOf('month').format("YYYY-MM-DD 00:00"), moment().endOf('month').format("YYYY-MM-DD 23:59")],
            // '上月': [moment().subtract(1, 'month').startOf('month').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'month').endOf('month').format("YYYY-MM-DD 23:59")]
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
        opens: 'right'
    })
    $(".datetiment").daterangepicker({
        autoUpdateInput: true,
        ranges: {
            // '今天': [moment().format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '昨天': [moment().subtract(1, 'days').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'days').format("YYYY-MM-DD 23:59")],
            // '最近一周': [moment().subtract(6, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '最近30天': [moment().subtract(29, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '本月': [moment().startOf('month').format("YYYY-MM-DD 00:00"), moment().endOf('month').format("YYYY-MM-DD 23:59")],
            // '上月': [moment().subtract(1, 'month').startOf('month').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'month').endOf('month').format("YYYY-MM-DD 23:59")]
        },
        locale: {
            customRangeLabel: '自定义',
            format: 'HH:mm',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        timePicker: true,
        timePicker24Hour: true,
        opens: 'right'
    })
}
var SavedData={
    assembledate: "",//拼装日期
    assembletime: "",//拼装时间
    beonduty: "",//值班工程师
    construction: "",//施工单位
    driver: "",//盾构主司机
    factory: "",//管片生产厂家
    foreman: "",//班组长
    intervalname:"",//区间名称
    qualitymanager: "",//质量总管
    segmentassemble: "",//管片拼装手
    segmentno: null,//管片环数
    slipcastingmeasure: "",//注浆方量
    slipcastingtime: "",//注浆时间
    supervise: "",//监理单位
    superviseengineer: "",//监理工程师
    tunnellingdate: "",//掘进日期
    tunnellingtime: "",//掘进时间
}


function preserve() {//保存数据
    var ShowPages=$(".indexsm").val()
    $(".datetimes").val("")
    if ($(".form-control1").val().length>0){
        SavedData.segmentno=parseInt($(".form-control1").val())
    }  else {
        $(".form-control1").css("border","1px solid red")
        Feng.error("管片环数不能为空");
        return
    }
    if ($(".form-control2").val().length>0){
        SavedData.construction=$(".form-control2").val()
    }  else {
        $(".form-control2").css("border","1px solid red")
        Feng.error("施工单位不能为空");
        return
    }
    if ($(".form-control3").val().length>0){
        SavedData.supervise=$(".form-control3").val()
    }  else {
        $(".form-control2").css("border","1px solid red")
        Feng.error("监理单位不能为空");
        return
    }
    if ($(".form-control4").val().length>0){
        SavedData.intervalname=$(".form-control4").val()
    }  else {
        $(".form-control4").css("border","1px solid red")
        Feng.error("监理单位不能为空");
        return
    }
    if ($(".form-control7").val().length>0){
        SavedData.slipcastingmeasure=$(".form-control7").val()
    }  else {
        $(".form-control7").css("border","1px solid red")
        Feng.error("注浆方量不能为空");
        return
    }
    if ($(".form-control9").val().length>0){
        SavedData.driver=$(".form-control9").val()
    }  else {
        $(".form-control9").css("border","1px solid red")
        Feng.error("盾构主司机不能为空");
        return
    }
    if ($(".form-control10").val().length>0){
        SavedData.segmentassemble=$(".form-control10").val()
    }  else {
        $(".form-control10").css("border","1px solid red")
        Feng.error("管片拼装手不能为空");
        return
    }
    if ($(".form-control11").val().length>0){
        SavedData.qualitymanager=$(".form-control11").val()
    }  else {
        $(".form-control11").css("border","1px solid red")
        Feng.error("质量主管不能为空");
        return
    }
    if ($(".form-control12").val().length>0){
        SavedData.beonduty=$(".form-control12").val()
    }  else {
        $(".form-control12").css("border","1px solid red")
        Feng.error("值班工程师不能为空");
        return
    }
    if ($(".form-control13").val().length>0){
        SavedData.foreman=$(".form-control13").val()
    }  else {
        $(".form-control13").css("border","1px solid red")
        Feng.error("班组长不能为空");
        return
    }
    if ($(".form-control14").val().length>0){
        SavedData.superviseengineer=$(".form-control14").val()
    }  else {
        $(".form-control14").css("border","1px solid red")
        Feng.error("监理工不能为空");
        return
    }
    if ($(".form-control15").val().length>0){
        SavedData.factory=$(".form-control15").val()
    }  else {
        $(".form-control15").css("border","1px solid red")
        Feng.error("监理工不能为空");
        return
    }
    var $formControl5=$(".form-control5").val().split(" ")//获取掘进时间
    var $formControl6=$(".form-control6").val().split(" ")//获取注浆时间
    var $formControl8=$(".form-control8").val().split(" ")//获取拼装时间
    SavedData.slipcastingtime=$formControl6[0]+$formControl6[1]+$formControl6[2]
    SavedData.tunnellingdate=$formControl5[0]
    SavedData.tunnellingtime=$formControl5[1]+$formControl5[2]+$formControl5[4]
    SavedData.assembledate=$formControl8[0]
    SavedData.assembletime=$formControl8[1]+$formControl8[2]+$formControl8[4]
    console.log(SavedData)
    $.ajax({
        type:"post",
        url:"/shield/saveOrUpdateShieldadvance",
        data:JSON.stringify(SavedData),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(json){
            console.log(json)
            if (json.result=="添加成功"){
                Feng.success(json.result);
                layer.closeAll()
                pageRendering(1,ShowPages,0)
            } else {
                Feng.error(json.result.message);
                SavedData.segmentno=parseInt($(".form-control1").val())
            }
        }
    })

}
var ShowPages=$(".indexsm").val()//获取到所要的分的页数

$(".datetimes").datetimepicker({//时间插件
    autoclose: true,
    language: "zh-CN",
    startView: 2,
    minView: "month",
    format: "yyyy-mm-dd",
    forceParse: 0,
})
$(".datetimes").on('change',function () {//查询盾构掘进的日期
    // console.log(this.value)
    var ShowPages=$(".indexsm").val()
    pageRendering(1,ShowPages,this.value)
})

function pageRendering(currentPage, eachPage,data) {//熏染数据获取管片的环数。
$.ajax({
    type: 'get',
    url: "/shield/queryAllShieldadvance",
    data:{
        currentPage:currentPage,//第几页
        pageSize:eachPage,//每页个数
        tunnellingdate:data
    },
    dataType: "json",
    success:function(json){
        console.log(json)
         $("#import-shield tbody tr").remove()
       json.result.list.forEach(function (data, index) {

           $("#import-shield tbody").append("<tr>" +
               "<td>"+data.segmentno+"</td>" +
               "<td>"+data.tunnellingdate+"</td>" +
               "<td>"+data.tunnellingtime+"</td>" +
               "<td>"+data.slipcastingtime+"</td>" +
               "<td>"+data.slipcastingmeasure+"</td>" +
               "<td>"+data.assembledate+"</td>" +
               "<td>"+data.assembletime+"</td>" +
               "<td>"+data.driver+"</td>" +
               "<td>"+data.segmentassemble+"</td>" +
               "<td>"+data.qualitymanager+"</td>" +
               "<td>"+data.beonduty+"</td>" +
               "<td>"+data.foreman+"</td>" +
               "<td>"+data.superviseengineer+"</td>" +
               "<td><i class='iconfont icon-bianji' onclick='tbodyCompile("+data.segmentno+")'></i><i class='iconfont icon-iconset0212' onclick='tbodyDelete("+data.id+")'></i></td>" +
               "</tr>")
       })
        $(".dangqianyeshu").empty()
        $(".dangqianyeshu").append("第"+json.result.pageNum+"页（共"+json.result.lastPage+"页 "+json.result.total+"条数据）<div>" +
            "<ul class='pagination paginations' style='float: right;'>" +
            "</ul>" +
            "</div>")
        if (json.result.lastPage==1){
            $(".paginations").append('<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>' +
                '<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>' +
                ' <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">下一页</span></a></li>')
        } else {
            $(".paginations").bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage: json.result.pageNum,//当前页面
                numberOfPages: 7,//一页显示几个按钮（在ul里面生成7个li）
                totalPages:json.result.lastPage,//总页数，
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }

                },

                onPageClicked: function (event, originalEvent, type, page) {
                    // render(page);//根据点击页数渲染页面
                    console.log(page)

                    var ShowPages=$(".indexsm").val()
                    var dateTimes=$(".datetimes").val()
                    if (dateTimes.length>5){
                            pageRendering(page,ShowPages,dateTimes)
                    } else{
                        pageRendering(page,ShowPages,0)
                    }

                }
            })
        }

    }

})
}

function qingkong(){//清空盾构掘进日期
    var ShowPages=$(".indexsm").val()
    $(".datetimes").val("")
    pageRendering(1,ShowPages,0)
}
pageRendering(1,ShowPages,0)//渲染当前的数据
function ShowPagesd() {//获取显示多少分页
    var ShowPages=$(".indexsm").val()
    pageRendering(1,ShowPages,0)
}
//删除功能
function tbodyDelete(id) {
    Feng.confirm("是否删除？", function () {
    $.ajax({
        async:false,
        type:"get",
        url:"/shield/deleteShieldadvance",
        data:{ids:id},
        dataType: "json",
        success:function (json) {
            Feng.success(json.result);
            pageRendering(1,ShowPages,0)
        }
  })
    })
}
//编辑查看功能
var presentId=null //获取到查看的id
function tbodyCompile(segmentno) {
    var maxWidth = window.screen.width * 0.5;
    layer.open({
        type: 1,
        title: "盾构管片环号查看编辑",
        area: [maxWidth+"px", 'auto'],
        maxHeight: 800,
        content: $('#Ring-number2').html(),
    })
    var date = new Date();
    $(".datetime").daterangepicker({
        autoUpdateInput: true,
        ranges: {
            // '今天': [moment().format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '昨天': [moment().subtract(1, 'days').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'days').format("YYYY-MM-DD 23:59")],
            // '最近一周': [moment().subtract(6, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '最近30天': [moment().subtract(29, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '本月': [moment().startOf('month').format("YYYY-MM-DD 00:00"), moment().endOf('month').format("YYYY-MM-DD 23:59")],
            // '上月': [moment().subtract(1, 'month').startOf('month').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'month').endOf('month').format("YYYY-MM-DD 23:59")]
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
        opens: 'right'
    })
    $(".datetiment").daterangepicker({
        autoUpdateInput: true,
        ranges: {
            // '今天': [moment().format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '昨天': [moment().subtract(1, 'days').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'days').format("YYYY-MM-DD 23:59")],
            // '最近一周': [moment().subtract(6, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '最近30天': [moment().subtract(29, 'days').format("YYYY-MM-DD 00:00"), moment().format("YYYY-MM-DD 23:59")],
            // '本月': [moment().startOf('month').format("YYYY-MM-DD 00:00"), moment().endOf('month').format("YYYY-MM-DD 23:59")],
            // '上月': [moment().subtract(1, 'month').startOf('month').format("YYYY-MM-DD 00:00"), moment().subtract(1, 'month').endOf('month').format("YYYY-MM-DD 23:59")]
        },
        locale: {
            customRangeLabel: '自定义',
            format: 'HH:mm',
            separator: ' ~ ',
            applyLabel: "确定",
            cancelLabel: "取消",
            resetLabel: "重置",
            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
        },
        timePicker: true,
        timePicker24Hour: true,
        opens: 'right'
    })
    $.ajax({
        async:false,
        type:"get",
        url:"/shield/queryShieldadvance/"+segmentno,
        dataType: "json",
        success:function (json) {
          console.log(json)

           var  date=json.result[0]
            presentId= date.id
            var tunnelling=date.tunnellingtime.split("~")
            var tunnellingdata=date.tunnellingdate+" "+tunnelling[0]+" "+"~"+" "+date.tunnellingdate+" "+tunnelling[1]
            $(".form-control5").val(tunnellingdata)
            var assembletime=date.assembletime.split("~")
            var assembledata=date.assembledate+" "+assembletime[0]+" "+"~"+" "+date.assembledate+" "+assembletime[1]
            $(".form-control8").val(assembledata)
            var assembletime=date.assembletime.split("~")
            $(".form-control6").val(assembletime[0]+" "+"~"+" "+assembletime[0])

            $(".form-control1").val(date.segmentno)
            $(".form-control2").val(date.construction)
            $(".form-control3").val(date.supervise)
            $(".form-control4").val(date.intervalname)
            $(".form-control7").val(date.slipcastingmeasure)
            $(".form-control9").val(date.driver)
            $(".form-control10").val(date.segmentassemble)
            $(".form-control11").val(date.qualitymanager)
            $(".form-control12").val(date.beonduty)
            $(".form-control13").val(date.foreman)
            $(".form-control14").val(date.superviseengineer)
            $(".form-control15").val(date.factory)
        }
    })
}
var SavedDatas={//当编辑修改时
    id:null,
    assembledate: "",//拼装日期
    assembletime: "",//拼装时间
    beonduty: "",//值班工程师
    construction: "",//施工单位
    driver: "",//盾构主司机
    factory: "",//管片生产厂家
    foreman: "",//班组长
    intervalname:"",//区间名称
    qualitymanager: "",//质量总管
    segmentassemble: "",//管片拼装手
    segmentno: null,//管片环数
    slipcastingmeasure: "",//注浆方量
    slipcastingtime: "",//注浆时间
    supervise: "",//监理单位
    superviseengineer: "",//监理工程师
    tunnellingdate: "",//掘进日期
    tunnellingtime: "",//掘进时间
}
function redactSave() {
    var ShowPages=$(".indexsm").val()
    $(".datetimes").val("")

    SavedDatas.id=presentId
    if ($(".form-control1").val().length>0){
        SavedDatas.segmentno=parseInt($(".form-control1").val())
    }  else {
        $(".form-control1").css("border","1px solid red")
        Feng.error("管片环数不能为空");
        return
    }
    if ($(".form-control2").val().length>0){
        SavedDatas.construction=$(".form-control2").val()
    }  else {
        $(".form-control2").css("border","1px solid red")
        Feng.error("施工单位不能为空");
        return
    }
    if ($(".form-control3").val().length>0){
        SavedDatas.supervise=$(".form-control3").val()
    }  else {
        $(".form-control2").css("border","1px solid red")
        Feng.error("监理单位不能为空");
        return
    }
    if ($(".form-control4").val().length>0){
        SavedDatas.intervalname=$(".form-control4").val()
    }  else {
        $(".form-control4").css("border","1px solid red")
        Feng.error("监理单位不能为空");
        return
    }
    if ($(".form-control7").val().length>0){
        SavedDatas.slipcastingmeasure=$(".form-control7").val()
    }  else {
        $(".form-control7").css("border","1px solid red")
        Feng.error("注浆方量不能为空");
        return
    }
    if ($(".form-control9").val().length>0){
        SavedDatas.driver=$(".form-control9").val()
    }  else {
        $(".form-control9").css("border","1px solid red")
        Feng.error("盾构主司机不能为空");
        return
    }
    if ($(".form-control10").val().length>0){
        SavedDatas.segmentassemble=$(".form-control10").val()
    }  else {
        $(".form-control10").css("border","1px solid red")
        Feng.error("管片拼装手不能为空");
        return
    }
    if ($(".form-control11").val().length>0){
        SavedDatas.qualitymanager=$(".form-control11").val()
    }  else {
        $(".form-control11").css("border","1px solid red")
        Feng.error("质量主管不能为空");
        return
    }
    if ($(".form-control12").val().length>0){
        SavedDatas.beonduty=$(".form-control12").val()
    }  else {
        $(".form-control12").css("border","1px solid red")
        Feng.error("值班工程师不能为空");
        return
    }
    if ($(".form-control13").val().length>0){
        SavedDatas.foreman=$(".form-control13").val()
    }  else {
        $(".form-control13").css("border","1px solid red")
        Feng.error("班组长不能为空");
        return
    }
    if ($(".form-control14").val().length>0){
        SavedDatas.superviseengineer=$(".form-control14").val()
    }  else {
        $(".form-control14").css("border","1px solid red")
        Feng.error("监理工不能为空");
        return
    }
    if ($(".form-control15").val().length>0){
        SavedDatas.factory=$(".form-control15").val()
    }  else {
        $(".form-control15").css("border","1px solid red")
        Feng.error("监理工不能为空");
        return
    }
    var $formControl5=$(".form-control5").val().split(" ")//获取掘进时间
    var $formControl6=$(".form-control6").val().split(" ")//获取注浆时间
    var $formControl8=$(".form-control8").val().split(" ")//获取拼装时间

    SavedDatas.slipcastingtime=$formControl6[0]+$formControl6[1]+$formControl6[2]
    SavedDatas.tunnellingdate=$formControl5[0]
    SavedDatas.tunnellingtime=$formControl5[1]+$formControl5[2]+$formControl5[4]
    SavedDatas.assembledate=$formControl8[0]
    SavedDatas.assembletime=$formControl8[1]+$formControl8[2]+$formControl8[4]
    console.log(SavedDatas)
    $.ajax({
        type:"post",
        url:"/shield/saveOrUpdateShieldadvance",
        data:JSON.stringify(SavedDatas),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(json){
            console.log(json)
            if (json.result=="修改成功"){
                Feng.success(json.result);
                layer.closeAll()
                pageRendering(1,ShowPages,0)
            } else {
                Feng.error(json.result.message);
                SavedData.segmentno=parseInt($(".form-control1").val())
            }
        }
    })

}