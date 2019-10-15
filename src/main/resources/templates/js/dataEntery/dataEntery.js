var DataEntery = {
    siteInfo: {

    },
    envTable: {
        id: "env_record_table",
        title: "环境安全数据",
        dataUrl: baseURL + "/gas/getEnvironmentalList",
        toolTipId: "add-environment-record",
        columns: [
            // {title: '盾构区间', data: 'tbm_range'},
            {title: 'PM2.5', data: 'pm25'},
            {title: 'PM10', data: 'pm10'},
            {title: '噪声', data: 'noise'},
            {title: '温度', data: 'temperature'},
            {title: '湿度', data: 'humidity'},
            {title: '风速', data: 'windSpeed'},
            {title: '风向', data: 'windDirection'},
            {title: '风力', data: 'windPower'},
            {title: '检测人', data: 'rummager'},
            {title: '录入时间', data: 'dt'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            name: 'environmentForm'
        }
    },
    ditchTable: {
        id: "ditch_record_table",
        title: "基坑安全数据",
        dataUrl: baseURL + "/gas/getFoundationList",
        toolTipId: "add-ditch-record",
        columns: [
            {title: '盾构区间', data: 'tbm_range'},
            {title: '氧气', data: 'o2'},
            {title: '一氧化碳', data: 'co'},
            {title: '可燃气', data: 'fireO2'},
            {title: '硫化氢', data: 'h2S'},
            {title: '检测人', data: 'rummager'},
            {title: '录入时间', data: 'dt'},
            {title: '操作', data: null}
        ],
        isEdit: {
            status: false,
            data: null
        },
        form: {
            name: 'ditchForm'
        }
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
 * 初始化数据表
 * @param obj
 */
DataEntery.initTable = function (obj) {
    console.log(obj.dataUrl)
    var _this = this;
    obj.table = $("#"+obj.id).DataTable({
        dom: 'lrtip',
        processing: true,
        serverSide: true,
        ajax: {
            type: "GET",
            async:false,
            url: obj.dataUrl,
            cache: false,
            data: function (d) {

                for(var key in d){

                    if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){

                        delete d[key];

                    }
                }
                $.extend(d, {currentPage: d.start/d.length+1, pageSize: d.length});

            },
            dataType: "json",
            dataFilter: function (json) {

                json = JSON.parse(json);
                console.log(json)
                var returnData = {};
                returnData.draw = json.result.draw;
                returnData.recordsTotal = json.result.total;//返回数据全部记录
                returnData.recordsFiltered = json.result.total;//后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = json.result.list;//返回的数据列表
                console.log(returnData)
                return JSON.stringify(returnData);
            }
        },
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
    $("#"+obj.id).on('click', 'tbody td i.icon-bianji', function (event) {
        var data = obj.table.row($(this).parents('tr')).data();
        _this.openLayer(obj.toolTipId, obj.title+"编辑", function () {
            _this.initDatePicker();
            obj.initForm(data);
        }, obj);
        obj.isEdit = {
            status: true,
            data: data
        };
        event.stopImmediatePropagation();
    });
    $("#"+obj.id).on('click', 'tbody td i.icon-iconset0212', function () {
        var _this = this;
        Feng.confirm("是否删除？", function () {
            obj.delete(obj.table, _this);
        });
    });
};


/**
 * 打开弹出层
 * @param id
 * @param callback
 */
DataEntery.openLayer = function (id, title, callback, obj) {
    var maxWidth = window.screen.width * 0.5;
    var maxHeight = window.screen.height * 0.5;
    this.layerIndex = layer.open({
        type: 1,
        title: title,
        area: [maxWidth+'px', 'auto'], //宽高
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
DataEntery.initDatePicker = function () {
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
 * 初始化环境安全表单
 * @param rowData
 */
DataEntery.envTable.initForm = function (rowData) {
    console.log(1564)
    var form = document.environmentForm;
    for(var o in rowData){
        if(form[o]){
            form[o].value = rowData[o];
        }
    }
};

/**
 * 初始化基坑安全表单
 * @param rowData
 */
DataEntery.ditchTable.initForm = function (rowData) {
    console.log(rowData)
    var form = document.ditchForm;
    for(var o in rowData){
        if(form[o]){
            form[o].value = rowData[o];
        }
    }
};

/**
 * 验证表单
 */
DataEntery.validate = function (form) {
    var $form = $(document[form.name]);
    if(!$form.data('bootstrapValidator')){
        $form.bootstrapValidator({
            //excluded:[":hidden",":disabled",":not(visible)"] ,//bootstrapValidator的默认配置
            // excluded: [':disabled', ':hidden', ':not(:visible)'],//关键配置，表示只对于禁用域不进行验证，其他的表单元素都要验证
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
 * 环境安全录入
 */
DataEntery.addEnvRecord = function (event) {
    $(event.target).blur();

    this.openLayer(this.envTable.toolTipId, this.envTable.title+"录入", this.initDatePicker);

};

/**
 * 基坑安全录入
 */
DataEntery.addDitchRecord = function (event) {
    $(event.target).blur();
    this.openLayer(this.ditchTable.toolTipId, this.ditchTable.title+"录入", this.initDatePicker);
};

/**
 * 保存环境安全记录
 */
DataEntery.recordEnvSave = function () {
    var _this = this, url = "", text = "", data = new Object();
    var formData = $(document[_this.envTable.form.name]).serializeArray();

    formData.map(function (t) {
        data[t.name] = t.value;
    });
    if(_this.envTable.isEdit.status){
        url = baseURL + '/gas/updateEnvironmental';
        data.id = _this.envTable.isEdit.data.id;
        data.outletId = _this.envTable.isEdit.data.outletid;
        text = "修改成功";
    }else{
        url = baseURL + '/gas/saveEnvironmental';
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
            _this.envTable.table.ajax.reload(null, false);
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
 * 保存基坑安全记录
 */
DataEntery.recordDitchSave = function () {
    var _this = this, url = "", text = "", data = new Object();
    var formData = $(document[_this.ditchTable.form.name]).serializeArray();
    formData.map(function (t) {
        data[t.name] = t.value;
    });
    if(_this.ditchTable.isEdit.status){
        url = baseURL + '/gas/updateFoundationVo';
        data.id = _this.ditchTable.isEdit.data.id;
        data.outletId = _this.ditchTable.isEdit.data.outletid;
        text = "修改成功";
    }else{
        url = baseURL + '/gas/saveFoundationVo';
        text = "录入成功";
    }
    $.ajax({
        async:false,
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json"
    }).then(function (json) {
        console.log(json)
        if(json.code===200){

            _this.ditchTable.table.ajax.reload(null, false);
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
 * 环境安全数据删除
 * @param _this
 */
DataEntery.envTable.delete = function (table, _this) {
    var rowId = table.row($(_this).parents('tr')).data().id;
    $.ajax({
        async:false,
        type: "GET",
        url: baseURL + '/gas/delete',
        data: {id: rowId},
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            table.row($(_this).parents('tr')).remove().draw(false);
            Feng.success("删除成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error(err.message);
    });
};

/**
 * 基坑安全数据删除
 * @param _this
 */
DataEntery.ditchTable.delete = function (table, _this) {
    var rowId = table.row($(_this).parents('tr')).data().id;
    $.ajax({
        async:false,
        type: "GET",
        url: baseURL + '/gas/delete',
        data: {id: rowId},
        dataType: "json"
    }).then(function (json) {
        if(json.code===200){
            table.row($(_this).parents('tr')).remove().draw(false);
            Feng.success("删除成功");
        }else{
            Feng.error(json.message);
        }
    }, function (err) {
        Feng.error(err.message);
    });
};

/**
 * 初始化工地日期信息
 */
DataEntery.siteInfo.initDate = function () {
    var _this = this;
    $.ajax({
        async:false,
        type: 'GET',
        url: '/homePage/homePageQueryDate',
        dataType: 'json'
    }).then(function (json) {
        console.log(json)
        if(json.code==200){
            $("#site-start-date").datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                language: 'zh-CN',
                minView: 'month'
            }).on('changeDate', function(ev){
                _this.setDate(ev.date.format('yyyy-MM-dd'));
            }).val(json.result);
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('请求日期异常');
    });
};

/**
 * 设置工地日期信息
 * @param date
 */
DataEntery.siteInfo.setDate = function (date) {
    $.ajax({
        async:false,
        type: 'GET',
        url: '/homePage/homePageSaveData',
        data: {startTime: date},
        dataType: 'json'
    }).then(function (json) {
        console.log(json)
        if(json.code==200){
            Feng.success('设置成功');
        }else{
            Feng.error(json.message);
        }
    }, function () {
        Feng.error('设置日期请求异常');
    });
};
var maxWidth = window.screen.width * 0.5;
var maxHeight = window.screen.height * 0.5;
function addbutton(){
    layer.open({
        type: 1,
        title: "添加IO控制器",
        area: [maxWidth+'px', '550px'], //宽高
        maxHeight: maxHeight,
        content: $("#buttom-write").html()
})
}

var buttomadd={
    iodeviceid:"",//设备id
    ioname:"",//io名称
    devicename:"",//设备名称
    username:"",//用户名
    password:"",//密码
    type:"",//厂家子类型
    factory:"",//厂家类型
    road:"",//几路
    devicetype:""//设备类型
}
//添加IO控制器
function buttomsave(){
    console.log($("#ioname").val().length)
    if ($("#iodeviceid").val().length>0){
        buttomadd.iodeviceid=$("#iodeviceid").val()
    } else {
        Feng.error("设备id不能为空")
        return;
    }
    if ($("#ioname").val().length>0){
        buttomadd.ioname=$("#ioname").val()
    } else {
        Feng.error("IO名称不能为空")
        return;
    }
    if ($("#devicename").val().length>0){
        buttomadd.devicename=$("#devicename").val()
    } else {
        Feng.error("设备名称不能为空")
        return;
    }
    if ($("#road").val().length>0){
        buttomadd.road=$("#road").val()
    } else {
        Feng.error("设备id不能为空")
        return;
    }
   if ($("#inlineRadio1").prop("checked")!=true){
       buttomadd.devicetype="1"
       console.log(153)
   }else {
       console.log(222)
       buttomadd.devicetype="2"
   }
    buttomadd.username=$("#username").val()
    buttomadd.password=$("#inputPassword3").val()
    buttomadd.type=$("#type").val()
    buttomadd.factory=$("#factory").val()
    console.log(buttomadd)

    $.ajax({
        async: false,
        type: 'POST',
        url: "/gas/saveGasIoInfo",
        data:JSON.stringify(buttomadd),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function (json) {
            console.log(json)
           if (json.code==200){
               Feng.confirm(json.result)
               ButtonRendering()
               layer.closeAll()
           }else {
               Feng.error(json.message)
               return;
           }
        }
    })
}

var buttomadd1={
    iodeviceid:"",//设备id
    ioname:"",//io名称
    devicename:"",//设备名称
    username:"",//用户名
    password:"",//密码
    type:"",//厂家子类型
    factory:"",//厂家类型
    road:"",//几路
    devicetype:""//设备类型
}

//修改IO控制器
function buttomsave1(id){
    if ($("#iodeviceid").val().length>0){
        buttomadd1.iodeviceid=$("#iodeviceid").val()
    } else {
        Feng.error("设备id不能为空")
        return;
    }
    if ($("#ioname").val().length>0){
        buttomadd1.ioname=$("#ioname").val()
    } else {
        Feng.error("IO名称不能为空")
        return;
    }
    if ($("#devicename").val().length>0){
        buttomadd1.devicename=$("#devicename").val()
    } else {
        Feng.error("设备名称不能为空")
        return;
    }
    if ($("#road").val().length>0){
        buttomadd1.road=$("#road").val()
    } else {
        Feng.error("设备id不能为空")
        return;
    }
    if ($("#inlineRadio1").prop("checked")!=true){
        buttomadd1.devicetype="1"
        console.log(153)
    }else {
        console.log(222)
        buttomadd1.devicetype="2"
    }
    buttomadd1.username=$("#username").val()
    buttomadd1.password=$("#inputPassword3").val()
    buttomadd1.type=$("#type").val()
    buttomadd1.factory=$("#factory").val()
    buttomadd1.id=id
    console.log(buttomadd1)

    $.ajax({
        async: false,
        type: 'POST',
        url: "/gas/saveGasIoInfo",
        data:JSON.stringify(buttomadd1),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function (json) {
            console.log(json)
            if (json.code==200){
                layer.closeAll()
                Feng.success(json.result)
                ButtonRendering()

            }else {
                Feng.error(json.message)
                return;
            }
        },
        error:function (json) {
            console.log(json)
        }

    })
}

function ButtonRendering(){
$.ajax({
    async: false,
    type: 'GET',
    url: "/gas/selectAllIoInfo",
    dataType: "json",
    success:function (json) {
        console.log(json)
        $("#IOcontrol1").empty()

        $.each(json.result,function (index,item) {
            var devicetype=""//设备类型
            if (item.devicetype==1){
                devicetype="撤离"
            } else {
                devicetype="喷淋"
            }
            var online=""
            if (item.online==1){
                online="在线"
            } else {
                online="离线"
            }
            $("#IOcontrol1").append("<tr>" +
                "<td>"+devicetype+"</td>" +
                "<td>"+item.iodeviceid+"</td>" +
                "<td>"+item.ioname+"</td>" +
                "<td>"+item.devicename+"</td>" +
                "<td>"+item.factory+"</td>" +
                "<td>"+item.type+"</td>" +
                "<td>"+online+"</td>" +
                "<td>"+item.road+"</td>" +
                "<td>" +
                "<i class='iconfont icon-bianji' onclick='Buttoncompile("+item.id+")'></i>" +
                "<i class='iconfont icon-iconset0212'  onclick='ButtonDelete("+item.id+")'></i>" +
                "</td>" +
                "</tr>")

        })
    }
})
}
//编辑
function Buttoncompile(id){
    $.ajax({
        type: 'GET',
        url: "/gas/selectOneIoInfo/"+id,
        dataType: "json",
        success:function (json) {
            console.log(json)
            if (json.code==200){
                layer.open({
                    type: 1,
                    title: "修改IO控制器",
                    area: [maxWidth+'px', '550px'], //宽高
                    maxHeight: maxHeight,
                    content: $("#buttom-write").html()
                })
                $("#iodeviceid").val(json.result.iodeviceid)
                $("#ioname").val(json.result.ioname)
                $("#devicename").val(json.result.devicename)
                $("#username").val(json.result.username)
                $("#inputPassword3").val(json.result.password)
                $("#type").val(json.result.type)
                $("#factory").val(json.result.factory)
                $("#road").val(json.result.road)
                if (json.result.devicetype==1){
                    $("#inlineRadio1").prop("checked",false)
                    $("#inlineRadio2").prop("checked",true)
                } else {
                    $("#inlineRadio1").prop("checked",true)
                    $("#inlineRadio2").prop("checked",false)
                }
                $("#Add-IO-control").before("<button class='btn btn-sm btn-primary' id='amend-IO-control' onclick='buttomsave1("+json.result.id+")'>保存</button>")
                $("#Add-IO-control").hide()
            } else {
                Feng.error(json.message)
            }
        }
    })
}
//删除
function ButtonDelete(id){
    Feng.confirm("是否删除",function () {
        $.ajax({
            type: 'GET',
            url: "/gas/deleteOneIoInfo/"+id,
            dataType: "json",
            success:function (json) {
                console.log(json)
                if (json.code==200){
                    Feng.success(json.result)
                    ButtonRendering()
                } else {
                    Feng.error(json.message)
                }
            }
        })
    })

}


$(function () {
    homePageQueryTitle();
    DataEntery.initTable(DataEntery.envTable);
    DataEntery.initTable(DataEntery.ditchTable);
    DataEntery.siteInfo.initDate();
    ButtonRendering()
});
