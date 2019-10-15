var CarMager_access = {
    table: {
        id: "access-table",
        instance: null,
        dataUrl: baseURL + "/vehicle/getVehcileRecord",
        columns: [
            {title: '车牌号', data: 'plate_number'},
            {title: '进场时间', data: 'entry_imagetime'},
            {title: '进场抓图', data: null, render: function (data, type, row, meta) {
                if(row.entry_imageurl){
                    return '<div><img style="height: 50px;" src="'+row.entry_imageurl+'"></div>';
                }else{
                    return '';
                }
            }},
            {title: '出场时间', data: 'out_imagetime'},
            {title: '出场抓图', data: null, render: function (data, type, row, meta) {
                if(row.out_imageurl){
                    return '<div><img style="height: 50px;" src="'+row.out_imageurl+'"></div>';
                }else{
                    return '';
                }
            }},
            {title: '车辆类型', data: 'vehicleType'},
            {title: '所属公司', data: 'company'},
            {title: '车辆状态', data: 'vehicleStatus'},
            {title: '操作', data: null, render: function(data, type, row, meta) {
                var html = '<a href="javascript:void(0)" style="margin-right: 10px;" onclick="CarMager_access.accessDetail(event)">详情</a>';
                if(row.entry_imagetime && row.out_imagetime){
                    if(row.entry_imageurl && row.out_imageurl){
                        html = html + '<a href="javascript:void(0)" class="disabled">补录</a>';
                    }else{
                        html = html + '<a href="javascript:void(0)" class="datetime">重录</a>';
                    }
                }else{
                    html = html + '<a href="javascript:void(0)" class="datetime">补录</a>';
                }
                return html;
            }}
        ]
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
CarMager_access.openLayer = function (title, content, callback) {
    var maxWidth = window.screen.width * 0.5;
    var maxHeight = window.screen.height * 0.5;
    CarMager_access.layerIndex = layer.open({
        type: 1,
        title: title,
        area: [maxWidth+'px', maxHeight+'px'], //宽高
        content: content,
        success: function (index, layero) {
            if(typeof callback =="function"){
                callback();
            }
        },
        end: function () {

        }
    });
};

/**
 * 初始化车辆类型、所属公司过滤项
 */
CarMager_access.initQuery = function () {
    $.ajax({
        url: baseURL + '/vehicle/queryCompanyList',
        type: 'GET',
        async: false,
        dataType: 'json'
    }).then(function (json) {
        if(json.code==200){
            var html = "";
            $.each(json.result, function (i ,item) {
                html = html + '<option value="'+item+'">'+item+'</option>';
            });
            html = '<option value="-1" selected>全部</option>' + html;
            $('.company-select').empty().append(html);
        }else{
            Feng.error(json.message);
        }
    }, function(error) {
        Feng.error(error);
    });
    $.ajax({
        url: baseURL + '/vehicle/queryVehicleTypeList',
        type: 'GET',
        async: false,
        dataType: 'json'
    }).then(function (json) {
        if(json.code==200){
            var html = "";
            CarMager_info.carType = {};
            $.each(json.result, function (i ,item) {
                html = html + '<option value="'+item+'">'+item+'</option>';
                CarMager_info.carType[item] = item;
            });
            html = '<option value="-1" selected>全部</option>' + html;
            $('.vehicleType-select').empty().append(html);
        }else{
            Feng.error(json.message);
        }
    }, function(error) {
        Feng.error(error);
    });

    $('.datepicker').daterangepicker({
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
        timePicker24Hour: true
    }).on('apply.daterangepicker', function(ev, picker) {
        var startTime = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
        var endTime = picker.endDate.format('YYYY-MM-DD HH:mm:ss');
        $(this).attr({
            'data-starttime': startTime,
            'data-endtime': endTime
        });
    }).val('');
};

/**
 * 初始化车辆进出记录表
 * @param obj
 */
CarMager_access.initTbale = function (obj) {
    var _this = this;
    obj.instance = $("#"+obj.id).DataTable({
        dom: 'lrtip',
        processing: true,
        serverSide: true,
        searching: true,
        deferRender: true,
        columnDefs: [
            {
                targets: "_all",
                className: "text-center ellipsis"
            },
            {
                targets: -1,
                data: null,
                orderable: false
            }
        ],
        fnServerData: _this.retrieveData,
        columns: obj.columns,
        paging: true,
        ordering: true,
        autoWidth: true,
        stateSave: true,
        language: {
            emptyTable: "没有数据~",
            infoEmpty: "",
            lengthMenu:     "显示 _MENU_ 条",
            search: "搜索",
            info: "第_PAGE_页(共_PAGES_页   _MAX_条数据）",
            zeroRecords: "没有匹配的数据",
            infoFiltered: '（从 _MAX_ 记录中过滤）',
            paginate: {
                previous:   "上一页",
                next:       "下一页"
            }
        }
    });
    $("#"+obj.id).on('draw.dt', function () {
        $(".datetime").datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true,
            language: 'zh-CN',
            minuteStep: 1
        }).on('show', function (ev) {
            var rowData = _this.table.instance.row($(this).closest('tr')).data();
            if(rowData.entry_imageurl){
                $(this).datetimepicker('setStartDate', rowData.entry_imagetime);
            }else{
                $(this).datetimepicker('setEndDate', rowData.out_imagetime);
            }
        }).on('changeDate', function(ev){
            var row = _this.table.instance.row($(ev.currentTarget).closest('tr')).data();
            _this.updateRecord(row, ev.date.format('yyyy-MM-dd hh:mm:ss'));
        });
    });
};

CarMager_access.retrieveData = function (sSource, aoData, fnCallback) {
    var data = new Object(), param = new Object();
    var type = $("#access-vehicleType").find("option:selected").val();
    var plate_number = $("#access-carNum").val();
    var company = $("#access-company").find("option:selected").val();
    var starttime = $("#access-time").attr('data-starttime');
    var endtime = $("#access-time").attr('data-endtime');
    if(type!=-1 && type!=undefined){
        param.type = type;
    }
    if(plate_number.trim()!="" && type!=undefined){
        param.plate_number = plate_number;
    }
    if(company!=-1 && type!=undefined){
        param.company = company;
    }
    if(starttime && endtime){
        param.starttime = starttime;
        param.endtime = endtime;
    }
    aoData.forEach(function (t) {
        data[t.name] = t.value;
    });
    param.draw = data.draw;
    param.start = data.start;
    param.length = data.length;
    param.currentPage = data.start/data.length+1;
    param.pageSize = param.length;
    $.ajax({
        type: "POST",
        url: CarMager_access.table.dataUrl,
        cache: false,
        data: param,
        dataType: "json"
    }).then(function (json) {
        if(json.code==200){
            var returnData = new Object();
            returnData.draw = json.result.draw;
            returnData.recordsTotal = json.result.total;
            returnData.recordsFiltered = json.result.total;
            returnData.data = json.result.list;
            fnCallback(returnData);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

/**
 * 查询
 * @param event
 */
CarMager_access.searchAccess = function (event) {
    $(event.currentTarget).blur();
    $('#access-table').dataTable().fnDraw();
};

/**
 * 查看进出详情
 * @param event
 */
CarMager_access.accessDetail = function (event) {
    var currentTarget = event.currentTarget;
    var data = this.table.instance.row($(currentTarget).closest('tr')).data();
    var callback = function () {
        var $detail = $("#access-detail"), html = "", text = "";
        $('<div class="row"></div>').html('<div class="col-sm-12"><p>车牌号码：'+data.plate_number+'</p><p>车辆类型：'+data.vehicleType+'</p><p>所属公司：'+data.company+'</p></div>').appendTo($detail);
        data.detailoRespVos.forEach(function (t) {
            if(t.url){
                if(t.type==0){
                    text = "进场";
                }else if(t.type==1){
                    text = "出场";
                }
                html = html + '<div class="col-sm-6">' +
                    '<div class="thumbnail">' +
                    '<img src="'+t.url+'" alt="图片">' +
                    '<div class="caption ellipsis">' +
                    '<p>图片来源：'+t.source+'</p>' +
                    '<p>进出状态：'+text+'</p>' +
                    '<p>图片类型：'+t.imagetype+'</p>' +
                    '<p>抓拍时间：'+t.imagetime+'</p>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            }
        });
        html = '<div class="row">' + html + '</div>';
        $(html).appendTo($detail);
    };
    this.openLayer('进出详情', '<div class="content" id="access-detail"></div>', callback);
};

/**
 * 补录/重录
 */
CarMager_access.updateRecord = function (row, date) {
    var _this = this, id = "";
    if(!row.entry_imageurl){
        id = row.entryId;
    }else if(!row.out_imageurl){
        id = row.outId;
    }
    $.ajax({
        url: '/vehicle/updateVehicle',
        type: 'POST',
        data: {id: parseInt(id), bltime: date},
        dataType: "json"
    }).then(function (json) {
        if(json.code==200){
            Feng.success("录入成功");
            _this.table.instance.ajax.reload(null, false);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

$(function () {
    CarMager_access.initQuery();
    CarMager_access.initTbale(CarMager_access.table);
    homePageQueryTitle();
});