var CarMager_info = {
    carType: new Object(),
    table: {
        id: "info-table",
        instance: null,
        dataUrl: baseURL + "/vehicle/getVehicleList",
        columns: [
            {title: '<input type="checkbox" id="checkAll">', data: null, width: 20, orderable: false},
            {title: '车牌号', data: 'plate_number'},
            {title: '车辆类型', data: 'type', render: function (data, type, row, meta) {
                if(Object.keys(CarMager_info.carType).length>2){
                    $("#car-type-config").attr('disabled', false);
                }else{
                    $("#car-type-config").attr('disabled', true);
                }
                return data;
            }},
            {title: '联系人', data: 'contacts'},
            {title: '联系人电话', data: 'phone'},
            {title: '所属公司', data: 'company'},
            {title: '车辆照片', data: null, render: function () {
                return '<a href="javascript:void(0)" onclick="CarMager_info.checkCarPic(event)">查看照片</a>';
            }},
            {title: '操作', data: null, render: function () {
                return '<a href="javascript:void(0)" onclick="CarMager_info.editCarInfo(event)">编辑</a>';
            }}
        ]
    },
    form: {
        name: 'car-info',
        validateFields: {
            type: {
                message: '请输入车辆类型',
                validators: {
                    notEmpty: {
                        message: '请输入车辆类型'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]+$/,
                        message: '车辆类型只能是汉字'
                    },
                    stringLength: {
                        max: 10,
                        message: '车辆类型长度不能超过10位'
                    }
                }
            },
            plate_number: {
                message: '请输入车牌号',
                validators: {
                    notEmpty: {
                        message: '请输入车牌号'
                    },
                    regexp: {
                        regexp: /^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$/,
                        message: '请输入正确车牌号'
                    }
                }
            },
            company: {
                message: '请输入公司名称',
                validators: {
                    notEmpty: {
                        message: '请输入公司名称'
                    },
                    regexp: {
                        regexp: /^[A-Z\u4e00-\u9fa5]+$/,
                        message: '公司名称只能是汉字和大写字母'
                    }
                }
            },
            contacts: {
                message: '请输入联系人',
                validators: {
                    notEmpty: {
                        message: '请输入联系人'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z\u4e00-\u9fa5]+$/,
                        message: '联系人名只能是汉字和字母'
                    },
                    stringLength: {
                        max: 10,
                        message: '联系人名长度不能超过10位'
                    }
                }
            },
            phone: {
                message: '请输入联系人电话',
                validators: {
                    notEmpty: {
                        message: '请输入联系人电话'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '请输入正确电话号码'
                    }
                }
            }
        }
    },
    isEdit: {
        status: false,
        id: null
    }
};

/**
 * 表格checkbox切换
 */
CarMager_info.toggleCheck = function () {
    var state = true;
    if($(this).is(":checked") == false) {
        $("#checkAll").prop("checked", false);
    }
    $("input[name='checklist']").each(function () {
        if(!this.checked){
            state = false;
        }
    });
    if(state){
        $('#checkAll').prop('checked', true);
    }
};

/**
 * 初始化车辆信息表
 * @param obj
 */
CarMager_info.initTbale = function (obj) {
    var _this = this;
    obj.instance = $("#"+obj.id).DataTable({
        dom: 'lrtip',
        processing: true,
        serverSide: true,
        deferRender: true,
        columnDefs: [
            {
                targets: 0,
                className: "text-center",
                data: null,
                render: function(data, type, row, meta) {
                    return '<input type="checkbox" name="checklist" onclick="CarMager_info.toggleCheck()" value="' + row.id + '" />'
                }
            },
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

    $('#checkAll').on('click', function () {
        var isChecked = this.checked;
        $("input[name='checklist']").each(function () {
            this.checked = isChecked;
        });
    });

    $("#info-table").find('thead th:eq(0)').removeClass('sorting_asc');
};

CarMager_info.retrieveData = function (sSource, aoData, fnCallback) {
    var data = new Object(), param = new Object();
    var type = $("#info-vehicleType").find("option:selected").val();
    var plate_number = $("#info-carNum").val();
    var company = $("#info-company").find("option:selected").val();
    if(type!=-1 && type!=undefined){
        param.type = type;
    }
    if(plate_number.trim()!="" && type!=undefined){
        param.plate_number = plate_number;
    }
    if(company!=-1 && type!=undefined){
        param.company = company;
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
        url: CarMager_info.table.dataUrl,
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
            if(json.result.total==0){
                $("#car-type-config").attr('disabled', true);
            }
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

/**
 * 打开弹出层
 * @param id
 * @param callback
 */
CarMager_info.openLayer = function (title, content, callback, size) {
    var _this = this;
    var maxWidth = size? size.width : window.screen.width * 0.5;
    var maxHeight = size? size.height : window.screen.height * 0.5;
    CarMager_info.layerIndex = layer.open({
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
            _this.isEdit = {
                status: false,
                id: null
            };
        }
    });
};

CarMager_info.searchCarInfo = function (event) {
    $(event.currentTarget).blur();
    $('#info-table').dataTable().fnDraw();
};

/**
 * 添加车辆信息
 */
CarMager_info.addCarInfo = function (event) {
    $(event.currentTarget).blur();
    var tipCallback = function () {
        var headerImgUpload = new ImageUpload({
            id: '#header-image',
            uploadUrl: '/vehicle/uploadVehicleImage',
            removeUrl: '/resource/deleteVehicleImage'
        });
        var bodyImgUpload = new ImageUpload({
            id: '#body-image',
            uploadUrl: '/vehicle/uploadVehicleImage',
            removeUrl: '/resource/deleteVehicleImage'
        });
        $.ajax({
            url: '/vehicle/queryVehicleTypeList',
            type: 'GET',
            dataType: 'json'
        }).then(function (json) {
            if(json.code==200){
                var html = "";
                $.each(json.result, function (i ,item) {
                    html = html + '<option value="'+item+'">'+item+'</option>';
                });
                $('select[name=type]').empty().append(html);
            }else{
                Feng.error(json.message);
            }
        }, function(error) {
            Feng.error(error);
        });
        $("#type-toggle").click(function () {
            var status = $(this).attr('data-status');
            if(status=='select'){
                $('select[name="type"]').addClass('hide');
                $('input[name="type"]').removeClass('hide');
                $(this).html('取消').attr('data-status', 'input');
            }else{
                $('select[name="type"]').removeClass('hide');
                $('input[name="type"]').addClass('hide');
                $(this).html('添加类型').attr('data-status', 'select');
            }
        });
    };
    this.openLayer('添加车辆信息', $("#addCarInfo").html(), tipCallback);
};

/**
 * 编辑车辆信息
 * @param event
 */
CarMager_info.editCarInfo = function (event) {
    var currentTarget = event.currentTarget;
    var data = this.table.instance.row($(currentTarget).closest('tr')).data();
    this.isEdit = {
        status: true,
        id: data.id
    };
    var tipCallback = function () {
        var headerImgUpload = new ImageUpload({
            id: '#header-image',
            uploadUrl: '/vehicle/uploadVehicleImage',
            removeUrl: '/resource/deleteVehicleImage'
        });
        var bodyImgUpload = new ImageUpload({
            id: '#body-image',
            uploadUrl: '/vehicle/uploadVehicleImage',
            removeUrl: '/resource/deleteVehicleImage'
        });
        $.ajax({
            url: '/vehicle/queryVehicleTypeList',
            type: 'GET',
            dataType: 'json'
        }).then(function (json) {
            if(json.code==200){
                var html = "";
                $.each(json.result, function (i ,item) {
                    html = html + '<option value="'+item+'">'+item+'</option>';
                });
                $('select[name=type]').empty().append(html).val(data.type);
            }else{
                Feng.error(json.message);
            }
        }, function(error) {
            Feng.error(error);
        });
        $("#type-toggle").click(function () {
            var status = $(this).attr('data-status');
            if(status=='select'){
                $('select[name="type"]').addClass('hide');
                $('input[name="type"]').removeClass('hide');
                $(this).html('取消').attr('data-status', 'input');
            }else{
                $('select[name="type"]').removeClass('hide');
                $('input[name="type"]').addClass('hide');
                $(this).html('添加类型').attr('data-status', 'select');
            }
        });
    };
    this.openLayer('修改车辆信息', $("#addCarInfo").html(), tipCallback);
    if(data.headimageurl && data.headimageurl!=""){
        $("#header-image").find('.cancel').css('display', 'block');
        $("#header-image").find('img').attr({
            'src': data.headimageurl || '../image/upload.png',
            'data-url': data.headimageurl
        });
    }
    if(data.bodyimageurl && data.bodyimageurl!=""){
        $("#body-image").find('.cancel').css('display', 'block');
        $("#body-image").find('img').attr({
            'src': data.bodyimageurl || '../image/upload.png',
            'data-url': data.bodyimageurl
        });
    }
    $('input[name="plate_number"]').val(data.plate_number).attr('readonly', true);
    $('input[name="company"]').val(data.company);
    $('input[name="contacts"]').val(data.contacts);
    $('input[name="phone"]').val(data.phone);
};

/**
 * 验证表单
 */
CarMager_info.validate = function (form) {
    var $form = $(document[form.name]);
    if(!$form.data('bootstrapValidator')){
        $form.bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],//关键配置，表示只对于禁用域不进行验证，其他的表单元素都要验证
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',//显示验证成功或者失败时的一个小图标
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields:  form.validateFields
        });
    }
    var bootstrapValidator = $form.data('bootstrapValidator');
    $form.bootstrapValidator('validate');
    return bootstrapValidator.isValid();
};

/**
 * 保存车辆信息
 */
CarMager_info.saveCarInfo = function () {
    var _this = this, data = new Object();
    if(!_this.validate(_this.form)){
        return;
    }
    var formData = $(document[_this.form.name]).serializeArray();
    formData.map(function (t) {
        if(t.value){
            data[t.name] = t.value;
        }
    });
    var headimageurl = $("#header-image").find('img').attr('data-url');
    var bodyimageurl = $("#body-image").find('img').attr('data-url');
    if(headimageurl){
        data.headimageurl = headimageurl;
    }
    if(bodyimageurl){
        data.bodyimageurl = bodyimageurl;
    }
    if(_this.isEdit.status){
        data.id = _this.isEdit.id;
    }
    $.ajax({
        url: '/vehicle/saveORupdateVehicle',
        type: 'POST',
        data: data,
        dataType: "json"
    }).then(function (json) {
        if(json.code==200){
            Feng.success("保存成功");
            layer.close(_this.layerIndex);
            _this.table.instance.ajax.reload(null, false);
            CarMager_access.initQuery();
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

/**
 * 删除车辆信息
 */
CarMager_info.delectCarInfo = function (event) {
    $(event.currentTarget).blur();
    var ids = new Array(), _this = this;
    $("input[name='checklist']:checked").each(function () {
        ids.push(this.value);
    });
    ids = ids.join(',');
    if(!ids){
        Feng.info('请选择车辆信息');
        return;
    }
    var callback = function () {
        $.ajax({
            url: '/vehicle/deleteVehicle',
            type: 'POST',
            data: {ids: ids},
            dataType: "json"
        }).then(function (json) {
            if(json.code==200){
                Feng.success("删除成功");
                _this.table.instance.ajax.reload(null, false);
                CarMager_access.initQuery();
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    };
    Feng.confirm('是否删除车辆信息？', callback);
};

/**
 * 查看照片
 * @param event
 */
CarMager_info.checkCarPic = function (event) {
    var currentTarget = event.currentTarget;
    var data = this.table.instance.row($(currentTarget).closest('tr')).data();
    var callback = function () {
        var $detail = $("#pic-detail"), html = "";
        $('<div class="row"></div>').html('<div class="col-sm-12"><p>车牌号码：'+data.plate_number+'</p><p>车辆类型：'+data.type+'</p></div>').appendTo($detail);
        if(data.headimageurl){
            html = html + '<div class="col-sm-6">' +
                '<div class="thumbnail">' +
                '<img src="'+ data.headimageurl +'" alt="图片">' +
                '<div class="caption ellipsis">' +
                '<p>车头前脸</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        if(data.bodyimageurl){
            html = html + '<div class="col-sm-6">' +
                '<div class="thumbnail">' +
                '<img src="'+ data.bodyimageurl +'" alt="图片">' +
                '<div class="caption ellipsis">' +
                '<p>全车照片</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        $('<div class="row"></div>').html(html).appendTo($detail);
    };
    this.openLayer('车辆照片', '<div class="content" id="pic-detail"></div>', callback);
};

/**
 * 车辆类型配置
 * @param event
 */
CarMager_info.typeConfig = function (event) {
    $(event.currentTarget).blur();
    var _this = this;
    var callback = function () {
        var option = "";
        for(var type in _this.carType){
            option = option + '<option value="'+type+'">'+type+'</option>';
        }
        $("select.typeConfig").empty().append(option);
        $.ajax({
            url: '/vehicle/queryScreenVehicleType',
            type: 'GET',
            dataType: "json"
        }).then(function (json) {
            if(json.code==200){
                json.result.forEach(function (t, index) {
                    $('select.typeConfig:eq('+index+')').val(t);
                });
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    };
    this.openLayer('类型配置', $("#carTypeConfig").html(), callback, {width: 580, height: 250});
};

/**
 * 保存类型配置
 */
CarMager_info.saveTypeConfig = function () {
    var form = document.forms['carTypeConfig'], _this = this;
    var data = $(form).serializeArray(), param = new Array();
    data.forEach(function (t) {
        if(param.indexOf(t.value)<0){
            param.push(t.value);
        }
    });
    if(param.length<2){
        Feng.info('车辆类型不能重复设置');
        return;
    }
    $.ajax({
        url: '/vehicle/saveScreenVehicleType',
        type: 'GET',
        data: {screenvheicle: param.join(',')},
        dataType: "json"
    }).then(function (json) {
        if(json.code==200){
            Feng.success("配置成功");
            layer.close(_this.layerIndex);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

$(function () {
    CarMager_info.initTbale(CarMager_info.table);
});