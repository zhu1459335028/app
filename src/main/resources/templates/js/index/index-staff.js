var StaffModule = {
    getTipsData: function () {
        var data = null;
        $.ajax({
            type: "GET",
            url: "/homePage/constructionOnwork",
            async: false,
            global: false,
            dataType: "json",
            contentType: "application/json",
            success: function (json) {
                data = json;
            }
        });
        return data;
    },
    initTips: function () {
        var _this = this;
        return new jBox('Tooltip', {
            attach: $("#staff-module"),
            trigger: 'click',
            content: '<div id="tipsContent" style="width: 4rem;height: auto;"></div>',
            target: $("#staff-module"),
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
                var tipsData = _this.getTipsData();
                var y = tipsData.result.y, tbodyHtml = '';
                $.each(tipsData.result.x, function (i, item) {
                    tbodyHtml = tbodyHtml + '<tr>' +
                        '<td>'+parseInt(i+1)+'</td>' +
                        '<td>'+item+'</td>' +
                        '<td>'+parseInt(parseInt(y[0].data[i])+parseInt(y[1].data[i]))+'</td>' +
                        '<td>'+y[1].data[i]+'</td>';
                    var precentD = '0.0';
                    if(parseInt(parseInt(y[0].data[i])+parseInt(y[1].data[i]))>0){
                        precentD = Number(parseInt(y[1].data[i])/(parseInt(parseInt(y[0].data[i])+parseInt(y[1].data[i])))*100).toFixed(1);
                    }
                    tbodyHtml = tbodyHtml +  '<td>'+precentD+'%</td>' +
                        '</tr>'
                });
                $("#tipsContent").empty().append('<table class="table table-bordered table-condensed">' +
                                                        '<thead>' +
                                                            '<tr>' +
                                                                '<th>序号</th>' +
                                                                '<th>岗位</th>' +
                                                                '<th>应到</th>' +
                                                                '<th>实到</th>'+
                                                                '<th>到岗率</th>' +
                                                            '</tr>' +
                                                        '</thead>' +
                                                        '<tbody>' +
                                                            tbodyHtml +
                                                        '</tbody>' +
                                                '</table>');
                $("#tipsContent table tbody").css('max-height', '2.1rem').niceScroll({
                    cursorcolor: '#1493E4',
                    cursoropacitymax: 0.8,
                    cursorborder: "1px solid #1493E4",
                    touchbehavior: false,
                    cursorwidth: "5px",
                    cursorborderradius: "5px",
                    autohidemode: true,
                    horizrailenabled: false
                });
            }
        });
    },
    update: function (data) {
        $("#percentProgress").circlePercent({
            fontSize: '0.14rem',
            percent: ((data.onLineCount/data.offLineCount)*100).toFixed(1)
        });
        $("#onLineCount").text(data.onLineCount);
        $("#offLineCount").text(data.offLineCount);
        $("#attendance").text(data.attendance);
        $("#onCount").text(data.onCount);
        $("#outCount").text(data.outCount);
        $("#staff-header").attr('src', data.imageUrl || '../image/worker/worker1.jpg');
        var managerEmploy = '';
        data.importantjob.forEach(function (t) {
            var state = 'icon-user-online';
            if(t.onLine==2){
                state = 'icon-user-offline';
            }
            managerEmploy = managerEmploy + '<div class="between-flex theme-font fontx2">' +
                                                '<span><i class="iconfont '+state+'"></i>'+t.jobName+'</span>' +
                                                '<span class="white-font">'+t.userName+'</span>' +
                                            '</div>';
        });
        $('#manager-employee').empty().append(managerEmploy);
    }
};
$(function () {
    StaffModule.initTips();
});