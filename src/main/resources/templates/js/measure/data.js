var MeasureData = {
    table: {
        id: 'measure-data-table',
        dataUrl: '/settlement/getAll'
    },
    settlement: {
        //沉降/位移
        name: 'settlement',
        columns: [
            {title: '测点编号', data: 'location_no'},
            {title: '测点位置', data: 'location'},
            {title: '上次变量', data: 'last_val'},
            {title: '本次变量', data: 'current_val'},
            {title: '累计变量', data: 'accumulate_val'},
            {title: '变化速率', data: 'rate'},
            {title: '仪器编号', data: 'equipment_no'},
            {title: '仪器名称', data: 'equipment'},
            {title: '检测日期', data: 'dt'},
            {title: '备注', data: 'remark'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            id: 'measure-settlement',
            name: 'measure-settlement',
            validateFields: {
                location_no: {
                    message: '请输入测点编号',
                    validators: {
                        notEmpty: {
                            message: '测点编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点编号不能超过30位'
                        }
                    }
                },
                location: {
                    message: '请输入测点位置',
                    validators: {
                        notEmpty: {
                            message: '测点位置不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点位置不能超过30位'
                        }
                    }
                },
                equipment_no: {
                    message: '请输入仪器编号',
                    validators: {
                        notEmpty: {
                            message: '仪器编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器编号不能超过30位'
                        }
                    }
                },
                equipment: {
                    message: '请输入仪器名称',
                    validators: {
                        notEmpty: {
                            message: '仪器名称不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器名称不能超过30位'
                        }
                    }
                },
                last_val: {
                    message: '请输入上次变量',
                    validators: {
                        notEmpty: {
                            message: '上次变量不能为空'
                        },
                        numeric: {
                            message: '上次变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '上次变量长度不能超过10位'
                        }
                    }
                },
                current_val: {
                    message: '请输入本次变量',
                    validators: {
                        notEmpty: {
                            message: '本次变量不能为空'
                        },
                        numeric: {
                            message: '本次变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '本次变量长度不能超过10位'
                        }
                    }
                },
                accumulate_val: {
                    message: '请输入累计变量',
                    validators: {
                        notEmpty: {
                            message: '累计变量不能为空'
                        },
                        numeric: {
                            message: '累计变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '累计变量长度不能超过10位'
                        }
                    }
                },
                rate: {
                    message: '请输入变化速率',
                    validators: {
                        notEmpty: {
                            message: '变化速率不能为空'
                        },
                        numeric: {
                            message: '变化速率是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '变化速率长度不能超过10位'
                        }
                    }
                }
            }
        }
    },
    inclinometer: {
        //测斜
        name: 'inclinometer',
        columns: [
            {title: '测点编号', data: 'location_no'},
            {title: '测点位置', data: 'location'},
            {title: '深度', data: 'depth'},
            {title: '上次变量', data: 'last_val'},
            {title: '本次变量', data: 'current_val'},
            {title: '累计变量', data: 'accumulate_val'},
            {title: '变化速率', data: 'rate'},
            {title: '仪器编号', data: 'equipment_no'},
            {title: '仪器名称', data: 'equipment'},
            {title: '检测日期', data: 'dt'},
            {title: '备注', data: 'remark'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            id: 'measure-inclinometer',
            name: 'measure-inclinometer',
            validateFields: {
                location_no: {
                    message: '请输入测点编号',
                    validators: {
                        notEmpty: {
                            message: '测点编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点编号不能超过30位'
                        }
                    }
                },
                location: {
                    message: '请输入测点位置',
                    validators: {
                        notEmpty: {
                            message: '测点位置不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点位置不能超过30位'
                        }
                    }
                },
                equipment_no: {
                    message: '请输入仪器编号',
                    validators: {
                        notEmpty: {
                            message: '仪器编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器编号不能超过30位'
                        }
                    }
                },
                equipment: {
                    message: '请输入仪器名称',
                    validators: {
                        notEmpty: {
                            message: '仪器名称不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器名称不能超过30位'
                        }
                    }
                },
                depth: {
                    message: '请输入深度',
                    validators: {
                        notEmpty: {
                            message: '深度不能为空'
                        },
                        numeric: {
                            message: '深度是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '深度长度不能超过10位'
                        }
                    }
                },
                lastaccumulate_val: {
                    message: '请输入上次累计变量',
                    validators: {
                        notEmpty: {
                            message: '上次累计变量不能为空'
                        },
                        numeric: {
                            message: '上次累计变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '上次累计变量长度不能超过10位'
                        }
                    }
                },
                current_val: {
                    message: '请输入本次变量',
                    validators: {
                        notEmpty: {
                            message: '本次变量不能为空'
                        },
                        numeric: {
                            message: '本次变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '本次变量长度不能超过10位'
                        }
                    }
                },
                accumulate_val: {
                    message: '请输入累计变量',
                    validators: {
                        notEmpty: {
                            message: '累计变量不能为空'
                        },
                        numeric: {
                            message: '累计变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '累计变量长度不能超过10位'
                        }
                    }
                },
                rate: {
                    message: '请输入变化速率',
                    validators: {
                        notEmpty: {
                            message: '变化速率不能为空'
                        },
                        numeric: {
                            message: '变化速率是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '变化速率最大值不能超过10位'
                        }
                    }
                }
            }
        }
    },
    axialForce: {
        //轴力
        name: 'axialForce',
        columns: [
            {title: '测点编号', data: 'location_no'},
            {title: '测点位置', data: 'location'},
            {title: '上次变量', data: 'last_val'},
            {title: '本次变量', data: 'current_val'},
            {title: '累计变量', data: 'accumulate_val'},
            {title: '最大报警', data: 'maxalarm'},
            {title: '最小报警', data: 'minalarm'},
            {title: '变化速率', data: 'rate'},
            {title: '仪器编号', data: 'equipment_no'},
            {title: '仪器名称', data: 'equipment'},
            {title: '检测日期', data: 'dt'},
            {title: '备注', data: 'remark'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            id: 'measure-axialForce',
            name: 'measure-axialForce',
            validateFields: {
                location_no: {
                    message: '请输入测点编号',
                    validators: {
                        notEmpty: {
                            message: '测点编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点编号不能超过30位'
                        }
                    }
                },
                location: {
                    message: '请输入测点位置',
                    validators: {
                        notEmpty: {
                            message: '测点位置不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点位置不能超过30位'
                        }
                    }
                },
                equipment_no: {
                    message: '请输入仪器编号',
                    validators: {
                        notEmpty: {
                            message: '仪器编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器编号不能超过30位'
                        }
                    }
                },
                equipment: {
                    message: '请输入仪器名称',
                    validators: {
                        notEmpty: {
                            message: '仪器名称不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器名称不能超过30位'
                        }
                    }
                },
                last_val: {
                    message: '请输入上次轴力',
                    validators: {
                        notEmpty: {
                            message: '上次轴力不能为空'
                        },
                        numeric: {
                            message: '上次轴力是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '上次轴力长度不能超过10位'
                        }
                    }
                },
                current_val: {
                    message: '请输入本次轴力',
                    validators: {
                        notEmpty: {
                            message: '本次轴力不能为空'
                        },
                        numeric: {
                            message: '本次轴力是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '本次轴力长度不能超过10位'
                        }
                    }
                },
                rate: {
                    message: '请输入变化速率',
                    validators: {
                        notEmpty: {
                            message: '变化速率不能为空'
                        },
                        numeric: {
                            message: '变化速率是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '变化速率长度不能超过10位'
                        }
                    }
                }
            }
        }
    },
    waterLevel: {
        //水位
        name: 'waterLevel',
        columns: [
            {title: '测点编号', data: 'location_no'},
            {title: '测点位置', data: 'location'},
            {title: '上次变量', data: 'last_val'},
            {title: '本次变量', data: 'current_val'},
            {title: '累计变量', data: 'accumulate_val'},
            {title: '水位埋深', data: 'waterdepth'},
            {title: '变化速率', data: 'rate'},
            {title: '仪器编号', data: 'equipment_no'},
            {title: '仪器名称', data: 'equipment'},
            {title: '检测日期', data: 'dt'},
            {title: '备注', data: 'remark'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            id: 'measure-waterLevel',
            name: 'measure-waterLevel',
            validateFields: {
                location_no: {
                    message: '请输入测点编号',
                    validators: {
                        notEmpty: {
                            message: '测点编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点编号不能超过30位'
                        }
                    }
                },
                location: {
                    message: '请输入测点位置',
                    validators: {
                        notEmpty: {
                            message: '测点位置不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '测点位置不能超过30位'
                        }
                    }
                },
                equipment_no: {
                    message: '请输入仪器编号',
                    validators: {
                        notEmpty: {
                            message: '仪器编号不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器编号不能超过30位'
                        }
                    }
                },
                equipment: {
                    message: '请输入仪器名称',
                    validators: {
                        notEmpty: {
                            message: '仪器名称不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '仪器名称不能超过30位'
                        }
                    }
                },
                last_val: {
                    message: '请输入上次变量',
                    validators: {
                        notEmpty: {
                            message: '上次变量不能为空'
                        },
                        numeric: {
                            message: '上次变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '上次变量长度不能超过10位'
                        }
                    }
                },
                current_val: {
                    message: '请输入本次变量',
                    validators: {
                        notEmpty: {
                            message: '本次变量不能为空'
                        },
                        numeric: {
                            message: '本次变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '本次变量长度不能超过10位'
                        }
                    }
                },
                accumulate_val: {
                    message: '请输入累计变量',
                    validators: {
                        notEmpty: {
                            message: '累计变量不能为空'
                        },
                        numeric: {
                            message: '累计变量是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '累计变量长度不能超过10位'
                        }
                    }
                },
                waterdepth: {
                    message: '请输入水位埋深',
                    validators: {
                        notEmpty: {
                            message: '水位埋深不能为空'
                        },
                        numeric: {
                            message: '水位埋深是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '水位埋深长度不能超过10位'
                        }
                    }
                },
                rate: {
                    message: '请输入变化速率',
                    validators: {
                        notEmpty: {
                            message: '变化速率不能为空'
                        },
                        numeric: {
                            message: '变化速率是数字'
                        },
                        stringLength: {
                            max: 10,
                            message: '变化速率最大值不能超过10位'
                        }
                    }
                }
            }
        }
    }
};

/**
 * 初始化数据表
 * @param obj
 */
MeasureData.initTable = function (obj) {
    var _this = this;
    this.table.type = obj.name;
    if(this.table.instance){
        this.table.instance.destroy();
    }
    this.table.instance = $("#"+this.table.id).unbind().empty().DataTable({
        dom: 'lrtip',
        processing: true,
        serverSide: true,
        searching: true,
        deferRender: true,
        columnDefs: [
            {
                targets: "_all",
                className: "text-center"
            },
            {
                targets: -1,
                data: null,
                orderable: false,
                defaultContent: '<i class="iconfont icon-bianji"></i><i class="iconfont icon-iconset0212"></i>'
            }
        ],
        fnServerData: this.retrieveData.bind(this),
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
    $("#"+this.table.id).on('click', 'tbody td i.icon-bianji', function (event) {
        var data = _this.table.instance.row($(this).parents('tr')).data();
        _this.openLayer(obj.form.id, "测量测绘数据编辑", function () {
            _this.initDatePicker();
            _this.initForm(obj.form.name, data);
        }, obj);
        obj.isEdit = {
            status: true,
            data: data
        };
        event.stopImmediatePropagation();
    });
    $("#"+this.table.id).on('click', 'tbody td i.icon-iconset0212', function () {
        var target = this;
        Feng.confirm("是否删除？", function () {
            _this.delete(target);
        });
    });
};

MeasureData.retrieveData = function (sSource, aoData, fnCallback) {
    var data = new Object(), param = new Object();
    var type = $('#point-type').find('option:selected').val();
    aoData.forEach(function (t) {
        data[t.name] = t.value;
    });
    param.draw = data.draw;
    param.start = data.start;
    param.length = data.length;
    param.currentPage = data.start/data.length+1;
    param.pageSize = param.length;
    param.settleunitmonid = (type==-1)? '' : type;
    $.ajax({
        type: "GET",
        url: this.table.dataUrl,
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
 * 打开弹出层
 * @param id
 * @param callback
 */
MeasureData.openLayer = function (id, title, callback, obj) {
    var maxWidth = window.screen.width * 0.5;
    var maxHeight = window.screen.height * 0.5;
    this.layerIndex = layer.open({
        type: 1,
        title: title,
        area: [maxWidth+'px', 'auto'],
        maxHeight: maxHeight,
        content: $('#'+id).html(),
        success: function () {
            if(typeof callback =="function"){
                callback();
            }
        },
        end: function () {
            if(obj){
                obj.isEdit = {
                    status: false,
                    data: null
                };
            }
        }
    });
};

/**
 * 初始化日期选择器
 */
MeasureData.initDatePicker = function () {
    var date = new Date();
    $(".datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        minuteStep: 1
    }).val(date.format('yyyy-MM-dd hh:mm:ss'));
};

/**
 * 初始化表单
 * @param name
 * @param data
 */
MeasureData.initForm = function (name, data) {
    var form = document.forms[name];
    var value = '';
    for(var key in data){
        if(key=='location_no'){
            value = data[key].substring(3);
            $(form[key]).prev().text(data[key].substring(0, 3));
        }else {
            value = data[key];
        }
        $(form[key]).val(value);
    }
};

/**
 * 初始化测量点位类型
 */
MeasureData.initPointType = function () {
    var _this = this;
    $.ajax({
        type: 'GET',
        url: '/settlement/selectSettleunitmon',
        dataType: 'json',
        success: function (json) {
            if(json.code==200){
                var options = '';
                json.result.forEach(function (t) {
                    options = options + '<option value="'+t.id+'" data-type="'+t.type+'" data-code="'+t.code+'">'+t.name+'</option>';
                });
                options = '<option value="-1">全部</option>' + options;
                $('#point-type').html(options).change(function () {
                    _this.toggleTable($(this).find('option:selected').data('type'));
                });
            }else{
                Feng.error(json.message);
            }
        },
        error: function () {
            Feng.error('请求点位类型异常');
        }
    });
};

/**
 * 数据表切换
 * @param type
 */
MeasureData.toggleTable = function (type) {
    var _type = 'settlement';
    if(type){
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
    }
    this.initTable(this[_type]);
};

/**
 * 导入Excel
 * @param event
 */
MeasureData.importExcel = function (event) {
    var _this = this, target = event.currentTarget;
    var excelName = target.value;
    var fileTArr = excelName.split(".");
    var filetype = fileTArr[fileTArr.length-1];
    if(filetype && (filetype == "xls" || filetype == 'xlsx') ){
        var formData = new FormData();
        formData.append("file", target.files[0]);
        $.ajax({
            url: '/settlement/importexcel',
            type: "POST",
            data: formData,
            contentType: false,
            processData: false
        }).then(function (json) {
            if(json.code==200){
                var tableType = _this.table.type;
                Feng.success('导入成功');
                _this.initTable(_this[tableType]);
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    }else{
        Feng.error("请选择Excel文件");
    }
    event.stopImmediatePropagation();
};

/**
 * 手动录入
 */
MeasureData.manualInput = function (event) {
    $(event.target).blur();
    var _this = this, code = $('#point-type').find('option:selected').data('code');
    var obj = this[this.table.type];
    this.openLayer(obj.form.id, "录入测量测绘数据", function () {
        $('.prefix-code').text(code);
        _this.initDatePicker();
    });
};

/**
 * 表单验证
 * @param form
 */
MeasureData.validate = function (form) {
    var $form = $(document[form.name]);
    if(!$form.data('bootstrapValidator')){
        $form.bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
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
 * 保存测量测绘数据
 */
MeasureData.save = function () {
    var _this = this, url = "", text = "", data = new Object();
    var obj = this[this.table.type];
    var form = document.forms[obj.form.name];
    if(!this.validate(obj.form)){
        return;
    }
    var formData = $(form).serializeArray();
    formData.map(function (t) {
        if(t.name=='location_no'){
            data[t.name] = $(form[t.name]).prev().text()+t.value;
        }else{
            data[t.name] = t.value;
        }
    });
    if(obj.isEdit.status){
        url = '/settlement/update';
        data.id = obj.isEdit.data.id;
        data.outletid = obj.isEdit.data.outletid;
        text = "修改成功";
    }else{
        url = '/settlement/add';
        data.settleunitmonid = $('#point-type').find('option:selected').val();
        text = "录入成功";
    }
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            _this.table.instance.ajax.reload(null, false);
            layer.close(_this.layerIndex);
            Feng.success(text);
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error(err.message);
    });
};

/**
 * 测量数据删除
 * @param target
 */
MeasureData.delete = function (target) {
    var _this = this;
    var rowId = _this.table.instance.row($(target).parents('tr')).data().id;
    $.ajax({
        type: "GET",
        url: '/settlement/delete',
        data: {id: rowId},
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            _this.table.instance.row($(target).parents('tr')).remove().draw(false);
            Feng.success("删除成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error(err.message);
    });
};

$(function () {
    MeasureData.initPointType();
    MeasureData.initTable(MeasureData.settlement);
});

