var AttendManage = {
    id: "staff_table",
    table: null
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
 * 表格字段
 */
AttendManage.columns = [
    {title: '工号', data: 'cardid'},
    {title: '姓名', data: 'name'},
    {title: '职位', data: 'jobName'},
    {title: '手机号码', data: 'phone'},
    {title: '性别', data: 'sex'},
    {title: '是否在岗', data: 'onLine'},
    {title: '今日到岗时间', data: 'todayTime'},
    {title: '今日在岗时长', data: 'todaySumTime'},
    {title: '累计在岗时长', data: 'allSumTime'}
];

/**
 * 单击查看部门人员信息
 * @param e
 * @param treeId
 * @param treeNode
 */
var onClickTree = function (e, treeId, treeNode) {
    $(".dept-name").text(treeNode.name);
    initStaffTable(treeNode.id);
    e.stopImmediatePropagation();
};

var setting = {
    view : {
        showLine: false,
        dblClickExpand : true,
        selectedMulti : false,
        showIcon: false
    },
    data : {
        simpleData : {
            enable : true,
            idKey: "id",
            pIdKey: "parentid",
            rootPId: 0
        }
    },
    edit : {
        drag : {
            isCopy : false,
            isMove : false
        }
    },
    callback : {
        onClick : onClickTree
    }
};

/**
 * 加载设备通道
 */
var loadDept = function () {
    var ztreeObj = null;
    var ztree = new $ZTree("dept-tree", null);
    ztree.setSettings(setting);
    $.ajax({
        type: "GET",
        url: baseURL + "/peopleManage/department",
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                $.each(json.result, function (i, item) {
                    item.name = item.name + '（' + item.peopleNum + '人）';
                });
                ztreeObj = ztree.initLocal(json.result);
            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
    $('#searcher').keyup(function (e) {
        var keyword = $(this).val();
        ztree.filter(ztreeObj, keyword);
    });
};

/**
 * 初始化人员表
 */
var initStaffTable = function (deptId) {
    if(deptId){
        AttendManage.table.clear();
        $.ajax({
            type: "GET",
            url: baseURL + "/peopleManage/employeeList/"+deptId,
            async: false,
            dataType: "json",
            success: function (json) {
                if(json.code==200){
                    AttendManage.table.rows.add(json.result).draw();
                }else{
                    Feng.error(json.message);
                }
            },
            error: function (err) {
                Feng.error(err.message);
            }
        });
    }
};

$(function () {
    var maxHeight = parseInt($('section.content').css('min-height'));
    $(".box").css('min-height', maxHeight-30+'px');
    $('#dept-tree').css({'max-height': maxHeight-140, 'overflow-y': 'auto'});
    $("#staff_table").css('max-height', maxHeight-172+'px');
    loadDept();
    AttendManage.table = $("#"+AttendManage.id).DataTable({
        columnDefs: [
            { className: "text-center", "targets": "_all" }
            // {targets: 4, render:
            //         function ( data, type, full, meta ) {
            //             return html;
            //         }
            // }
        ],
        columns:AttendManage.columns,
        paging: true,
        ordering: true,
        autoWidth: false,
        stateSave: true,
        language: {
            emptyTable: "没有表格数据",
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
    $('#staff_table tbody').on('click', 'tr', function () {
        var data = AttendManage.table.row(this).data();
        location.href = './attendInfo.html?cardId='+data.id;
    } );
});
$(function () {
    homePageQueryTitle();
});