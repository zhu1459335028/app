var TaskLoger = {
    staffid: location.search.substring(1).split('=')[1],
    tableParams: {'uniqueId': "id", search: true, showColumns: false, showRefresh: false},
    table: null
};

var columns = [
    {title: '日期', data: 'dayTime'},
    {title: '出入时间', data: 'crTime'},
    {title: '进出状态', data: 'status'},
    {title: '当日在岗时长', data: 'daySumTime'},
    {title: '考勤异常', data: 'error'}
];

TaskLoger.loadStaffInfo = function () {
    $.ajax({
        type: "GET",
        url: baseURL + "/peopleManage/employeeInfo/"+TaskLoger.staffid,
        async: false,
        dataType: "json",
        success: function (json) {
            if(json.code==200){
                $(".staff-pic").attr('src', baseURL + json.result.headUrl);
                $(".staff-name").text(json.result.name);
                $(".staff-phone").text(json.result.phone);
                $(".staff-dept").text(json.result.departmentName);
                $(".staff-post").text(json.result.jobName);
            }else{
                Feng.error(json.message);
            }
        },
        error: function (err) {
            Feng.error(err.message);
        }
    });
};

TaskLoger.getAttendInfo = function (staffid, startTime, endTime) {
    return  $.ajax({
        type: "GET",
        url: "/peopleManage/onWork/"+staffid,
        data: {startTime: startTime, endTime: endTime},
        async: false,
        dataType: "json"
    });
};

/**
 * 初始化员工信息表
 */
TaskLoger.initTaskTable = function () {
    var _this = this;
    _this.table = $("#task_table").DataTable({
        dom: 'lrtip',
        columnDefs: [
            { className: "text-center", "targets": "_all" },
            {targets: [1,2], render: function ( data, type, full, meta ) {
                    if(data.indexOf(",")>=0){
                        var dataArr = data.split(",");
                        var html = "";
                        $.each(dataArr, function (i, item) {
                            html = html + "<p>"+item+"</p>";
                        });
                        return html;
                    }else{
                        return data;
                    }
            }
            },
            {targets: 4, render: function ( data, type, full, meta ) {
                    var html = "";
                    switch (data.toString()){
                        case '0': html = '<span>正常</span>';
                            break;
                        case '1': html = '<span style="color: red;">缺少离岗考勤</span>';
                            break;
                        case '2': html = '<span style="color: red;">缺少进岗考勤</span>';
                            break;
                        default: html = '<span>未知</span>';
                            break;
                    }
                    return html;
                }
            }
        ],
        columns:columns,
        paging: true,
        ordering: true,
        autoWidth: false,
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
    _this.getAttendInfo(_this.staffid).then(function (json) {
        if(json.code==200){
            if(json.result.length){
                $(".staff-duration").text(json.result[0].allSumTime);
            }
            _this.table.rows.add(json.result).draw();
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(err.message);
    });
};

TaskLoger.updateTaskTable = function (startTime, endTime) {
    var _this = this;
    _this.table.clear();
    _this.getAttendInfo(_this.staffid, startTime, endTime).then(function (json) {
        if(json.code==200){
            _this.table.rows.add(json.result).draw();
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(err.message);
    });


};

$(function () {
    var maxHeight = parseInt($("section.content").css('min-height'));
    $(".box").css('min-height', maxHeight-30+'px');
    $("#task_table").css('max-height', maxHeight-172+'px');
    TaskLoger.loadStaffInfo();
    TaskLoger.initTaskTable();

    $('#datepicker-filter').daterangepicker({
        autoUpdateInput: true,
        ranges: {
            '全部': [moment(), moment()],
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
        timePicker24Hour: true,
        opens: 'left'
    }).on('apply.daterangepicker', function(ev, picker) {
        var startTime, endTime;
        if(picker.chosenLabel=='全部'){
            $(this).val('全部');
            startTime = '';
            endTime = '';
        }else{
            startTime = picker.startDate.format('YYYY-MM-DD');
            endTime = picker.endDate.format('YYYY-MM-DD');
        }
        TaskLoger.updateTaskTable(startTime, endTime);
    }).val('');
});