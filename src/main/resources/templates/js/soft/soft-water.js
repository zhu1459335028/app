var SoftWater = {

};

SoftWater.getAlarmValue = function () {
    return $.ajax({
        type: "POST",
        url: "/threshold/queryThreshold",
        dataType: "json"
    });
};

SoftWater.initValueHtml = function (thresholds) {
    var html = "";
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
                    html = html + '<div class="form-group threshold-item" data-type="'+x+'">' +
                                        '<div class="row">' +
                                            '<div class="col-sm-8">' +
                                                '<label class="control-label">'+text+'</label>' +
                                                '<input type="number" class="form-control" value="'+value+'" style="display: inline-block;width: 80%;margin-left: 20px;" name="'+x+'">' +
                                            '</div>' +
                                            '<div class="col-sm-3">' +
                                                '<div class="input-group">' +
                                                    '<input class="form-control color-picker" name="'+x+'_color" type="text" value="'+threshold[value]+'" readonly="readonly" placeholder="选择颜色">' +
                                                    '<span class="input-group-addon" style="background-color:'+threshold[value]+'">&nbsp;&nbsp;</span>' +
                                                '</div>' +
                                            '</div>' +
                                            '<div class="col-sm-1 text-center" style="height: 34px;;line-height: 34px;">' +
                                                '<i class="fa fa-trash" onclick="$(this).parents(\'.form-group\').remove()" style="color: red;font-size: 16px;"></i>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>';
                }
            }
        }
    }
    return html;
};

SoftWater.initDatetimePicker = function ($target) {
    $target.find('input.datepicker').daterangepicker({
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
        timePicker: true,
        timePicker24Hour: true,
        opens: 'right'
    }).on('apply.daterangepicker', function(ev, picker) {
        var startTime = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
        var endTime = picker.endDate.format('YYYY-MM-DD HH:mm:ss');
    });
};

SoftWater.initAlarmValue = function (initData) {
    var _this = this, $waterLevel = $("#waterLevel-threshold");
    initData.forEach(function (t, index) {
        $waterLevel.append('<fieldset data-num="'+parseInt(index+1)+'" data-groupId="'+t.id+'">' +
                                '<legend>阈值分组' + parseInt(index+1) +
                                    '<a href="javascript:void (0)" class="pull-right" onclick="$(this).parents(\'fieldset\').remove()" style="font-size: 14px;">移除分组</a>' +
                                '</legend>' +
                                '<div class="fieldset-content">' +
                                    '<div class="threshold-time form-group">' +
                                        '<div class="row">' +
                                            '<div class="col-sm-12">' +
                                                '<label class="control-label">时间段</label>' +
                                                '<input type="text" class="form-control datepicker" value="'+t.starttimestr+' ~ '+t.endtimestr+'" readonly="readonly">' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="threshold-tool form-group">' +
                                            '<div class="text-center threshold-btn" data-name="max_val"' +
                                                'onclick="SoftWater.addThreshold(event)">' +
                                                '<i class="fa fa-plus"></i>阈值上限' +
                                            '</div>' +
                                            // '<div class="text-center threshold-btn" data-name="min_val"' +
                                            //     'onclick="SoftWater.addThreshold(event)">' +
                                            //     '<i class="fa fa-plus"></i>阈值下限' +
                                            // '</div>' +
                                            // '<div class="text-center threshold-btn" data-name="identical_val"' +
                                            //     'onclick="SoftWater.addThreshold(event)">' +
                                            //     '<i class="fa fa-plus"></i>阈值恒等' +
                                            // '</div>' +
                                    '</div>' +
                                    '<div class="threshold-content">' +
                                        _this.initValueHtml(t) +
                                    '</div>' +
                                '</div>' +
                            '</fieldset>');
    });
    $(".threshold-item").find("input.color-picker").bigColorpicker(function (el, color) {
        el.value = color;
        $(el).next().css('background-color', color);
    },"L",3);
    _this.initDatetimePicker($('#waterLevel-threshold'));
    $(".layui-layer-content").css({
        'height': 'auto',
        'max-height': '650px'
    });
};
//垂直警告
//  function VerticalCall(){
//     console.log(123)
// Soft.openLayer('set-water-alarmValue',"设置报警值",function(){
//     SoftWater.getAlarmValue().then(function(json){
//         console.log(json)
//     })
// })
//
// }
// //水平警告
// LevelCall=function(){
//
// }
SoftWater.setAlarmValue = function (event) {

    $(event.target).blur();
    var _this = this;
    // console.log(this)
    // console.log(event)

    Soft.openLayer('set-water-alarmValue', '设置报警值', function () {
        _this.getAlarmValue().then(function (json) {
            if(json.code==200){
                console.log(json)
                _this.initAlarmValue(json.result);
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    });
};

SoftWater.addGroup = function (event) {

    var target = event.currentTarget;
    var $prev = $(target).parent().prev();
    var num = $prev.find('fieldset:last').attr('data-num')? parseInt($prev.find('fieldset:last').attr('data-num')) + 1 : 1;
    $prev.append('<fieldset data-num="'+num+'">' +
                    '<legend>阈值分组' + num +
                        '<a href="javascript:void (0)" class="pull-right" onclick="$(this).parents(\'fieldset\').remove()" style="font-size: 14px;">移除分组</a>' +
                    '</legend>' +
                    '<div class="fieldset-content">' +
                        '<div class="threshold-time form-group">' +
                            '<div class="row">' +
                                '<div class="col-sm-12">' +
                                    '<label class="control-label">时间段</label>' +
                                    '<input type="text" class="form-control datepicker" readonly="readonly">' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="threshold-tool form-group">' +
                                '<div class="text-center threshold-btn" data-name="max_val"' +
                                        'onclick="SoftWater.addThreshold(event)">' +
                                        '<i class="fa fa-plus"></i>阈值上限' +
                                '</div>' +
                                // '<div class="text-center threshold-btn" data-name="min_val"' +
                                //         'onclick="SoftWater.addThreshold(event)">' +
                                //         '<i class="fa fa-plus"></i>阈值下限' +
                                // '</div>' +
                                // '<div class="text-center threshold-btn" data-name="identical_val"' +
                                //         'onclick="SoftWater.addThreshold(event)">' +
                                //         '<i class="fa fa-plus"></i>阈值恒等' +
                                // '</div>' +
                        '</div>' +
                        '<div class="threshold-content">' +
                        '</div>' +
                    '</div>' +
                '</fieldset>');
    this.initDatetimePicker($('fieldset[data-num="'+num+'"]'));
};

SoftWater.addThreshold = function (event) {
    var $target = $(event.currentTarget), text = "";
    var type = $target.data('name');
    var $thresholds = $target.parents('div.threshold-tool').next();
    var num = $thresholds.find('input[name="'+type+'"]').length;
    switch (type){
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
    if((type=='max_val' || type=='min_val') && (num>1)){
        Feng.info(text+'的个数不能超过两个');
        return;
    }
    if((type=='identical_val') && (num>0)){
        Feng.info(text+'的个数不能超过一个');
        return;
    }
    $('<div class="form-group threshold-item" data-type="'+type+'">' +
        '<div class="row">' +
        '<div class="col-sm-8">' +
        '<label class="control-label">'+text+'</label>' +
        '<input type="number" class="form-control" style="display: inline-block;width: 80%;margin-left: 20px;" name="'+type+'">' +
        '</div>' +
        '<div class="col-sm-3">' +
        '<div class="input-group">' +
        '<input class="form-control color-picker" name="'+type+'_color" type="text" readonly="readonly" placeholder="选择颜色">' +
        '<span class="input-group-addon" >&nbsp;&nbsp;</span>' +
        '</div>' +
        '</div>' +
        '<div class="col-sm-1 text-center" style="height: 34px;;line-height: 34px;">' +
        '<i class="fa fa-trash" onclick="$(this).parents(\'.form-group\').remove()" style="color: red;font-size: 16px;"></i>' +
        '</div>' +
        '</div>' +
        '</div>').appendTo($thresholds).find("input.color-picker").bigColorpicker(function (el, color) {
            el.value = color;
            $(el).next().css('background-color', color);
        },"L",3);
};

/**
 * 保持阈值设置
 */
SoftWater.saveThresholdValue = function () {
    var param = new Array(), _this = this, state = true;
    $.each($("#waterLevel-threshold").find('input[name="max_val"]'), function (i, item) {
        if($(item).val()>0){
            state = false;
            return true;
        }
    });
    if(!state){
        Feng.info('阈值不能设置为大于0的值');
        return;
    }
    $.each($("#waterLevel-threshold").children(), function (i, item) {
        var $item = $(item), groupThreshold = new Object(), max_val = new Object(), min_val = new Object(), identical_val = new Object();
        var timeStr = $item.find('input.datepicker').val();
        if(!timeStr){
            return false;
        }
        timeStr = timeStr.split(' ~ ');
        groupThreshold.starttimestr = timeStr[0];
        groupThreshold.endtimestr = timeStr[1];
        // var id = $item.attr('data-groupId');
        // if(id){
        //     groupThreshold.id = id;
        // }
        $.each($item.find('div[data-type="max_val"]'), function (x, t) {
            var inputs = $(t).find("input");
            if($(inputs[0]).val()){
                max_val[$(inputs[0]).val()] = $(inputs[1]).val();
            }
        });
        groupThreshold.max_val = JSON.stringify(max_val);
        $.each($item.find('div[data-type="min_val"]'), function (x, t) {
            var inputs = $(t).find("input");
            if($(inputs[0]).val()){
                min_val[$(inputs[0]).val()] = $(inputs[1]).val();
            }
        });
        groupThreshold.min_val = JSON.stringify(min_val);
        var identicalInputs = $item.find('div[data-type="identical_val"]').find('input');
        if($(identicalInputs[0]).val()){
            identical_val[$(identicalInputs[0]).val()] = $(identicalInputs[1]).val();
        }
        groupThreshold.identical_val = JSON.stringify(identical_val);
        if(groupThreshold.max_val=='{}' && groupThreshold.min_val=='{}' && groupThreshold.identical_val=='{}'){
            return false;
        }
        param.push(groupThreshold);
    });
    $.ajax({
        type: "POST",
        url: "/threshold/SaveOrUpdateThreshold",
        cache: false,
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(param),
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            layer.close(Soft.layerIndex);
            Feng.success("设置成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error("水位阈值设置异常");
    });
};

SoftWater.getDepth = function () {
    return $.ajax({
        type: "GET",
        url: "/watermonitor/queryWaterDepth",
        dataType: "json"
    });
};

SoftWater.setDepth = function (event) {
    $(event.target).blur();
    var _this = this;
    Soft.openLayer('set-water-depth', '监测点深度', function () {
        _this.getDepth().then(function (json) {
            if(json.code==200){
                var form = document.forms['water-alarmValue-form'];
                $(form).find('input[name="depth"]').val(json.result);
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    });
};

SoftWater.saveDepth = function () {
    var form = document.forms['water-alarmValue-form'];
    var depth = $(form).find('input[name="depth"]').val();
    if(depth>0){
        Feng.info("监测点深度不能为大于0的值");
        return;
    }
    $.ajax({
        type: "GET",
        url: "/watermonitor/saveORupdateWaterDepth",
        data: {depth: depth},
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            layer.close(Soft.layerIndex);
            Feng.success("设置成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error("监测点深度设置异常");
    });
};