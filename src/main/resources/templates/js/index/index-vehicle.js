var VehicleTip = {
    $container: $("#car-table"),
    update: function (data) {
        var _this = this;
        var tbody = '<tr>' +
                        '<td>总数</td>' +
                        '<td>'+data.data.entrySum+'</td>' +
                        '<td>'+data.data.outSum+'</td>' +
                        '<td>'+data.data.leakySum+'</td>' +
                        '<td>'+data.data.onLineSum+'</td>' +
                        '<td><i class="iconfont icon-wenhao" aria-hidden="true"></i></td>' +
                    '</tr>';
        data.data.data.forEach(function (t) {
            tbody = tbody + '<tr>' +
                                '<td>'+t.vehicletype+'</td>' +
                                '<td>'+t.entryNum+'</td>' +
                                '<td>'+t.outNum+'</td>' +
                                '<td>'+t.leakyNum+'</td>' +
                                '<td>'+t.onlineNum+'</td>' +
                                '<td><i class="iconfont icon-wenhao" aria-hidden="true" data-type="'+t.vehicletype+'"></i></td>' +
                            '</tr>';
        });
        this.$container.html('<table class="table table-condensed carinfo-table">' +
                '<thead>' +
                    '<tr>' +
                        '<th>统计</th>' +
                        '<th>进场</th>' +
                        '<th>出场</th>' +
                        '<th>漏拍</th>' +
                        '<th>场内</th>' +
                        '<th>详情</th>' +
                    '</tr>' +
                '</thead>' +
                '<tbody>' +
                    tbody +
                '</tbody>'+
            '</table>'
        );
        $.each(this.$container.find('i.icon-wenhao'), function (i, item) {
            _this.initTips($(item).closest('tr'));
        });

        $("#car-pic").attr('src', data.imageurl || '../image/worker/car1.jpg');
        $("#car-type").text(data.vehicletype);
        if(data.entryouttype==1){
            $("#car-turnover").text('出场');
        }else{
            $("#car-turnover").text('进场');
        }
        $("#car-timedate").text(data.datatime);
    },
    initTips: function (target) {
        return new jBox('Tooltip', {
            attach: target,
            trigger: 'click',
            content: '<div class="car-tips"></div>',
            target: target,
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
            onCreated: this.tipsCreated,
            onOpen: this.tipsOpen,
            onClose: this.tipsClose
        });
    },
    tipsCreated: function () {
        return false;
    },
    tipsOpen: function () {
        var $tipContent = $('.car-tips').empty();
        $.ajax({
            type: "GET",
            url: "/vehicle/queryVehicleScreenMessage",
            data: {"vehicletype": this.source.data('type')},
            async: false,
            global: false,
            dataType: "json",
            success: function (json) {
                if(json.code==200){
                    var content = "";
                    json.result.vehicleimagelist.forEach(function (t) {
                        var status = "";
                        if(t.entryouttype==0){
                            status = "进场";
                        }else if(t.entryouttype==1){
                            status = "出场";
                        }
                        content = content + '<div class="car">' +
                                                '<div class="car-abstract">' +
                                                    '<span>车牌号：'+t.plate_number+'</span>' +
                                                    '<span>车辆类型：'+t.vehicletype+'</span>' +
                                                    '<span>车辆状态：'+status+'</span>' +
                                                '</div>' +
                                                '<div class="car-image">' +
                                                    '<div>' +
                                                        '<img src="'+t.headimageurl+'" alt="'+t.vehicletype+'">' +
                                                        '<div class="caption">' +
                                                            '<div class="tip-car-info">' +
                                                                '图片来源：<span>'+t.headsourcename+'</span>' +
                                                            '</div>' +
                                                            '<div class="tip-car-info">' +
                                                                '抓拍时间：<span>'+t.headdatatime+'</span>' +
                                                            '</div>' +
                                                        '</div>' +
                                                    '</div>' +
                                                    '<div>' +
                                                        '<img src="'+t.bodyimageurl+'" alt="'+t.vehicletype+'">' +
                                                        '<div class="caption">' +
                                                            '<div class="tip-car-info">' +
                                                                '图片来源：<span>'+t.bodsourceyname+'</span>' +
                                                            '</div>' +
                                                            '<div class="tip-car-info">' +
                                                                '抓拍时间：<span>'+t.bodydatatime+'</span>' +
                                                            '</div>' +
                                                        '</div>' +
                                                    '</div>' +
                                                '</div>' +
                                            '</div>';
                    });
                    $('<div class="car-container" style="max-height: 300px;"></div>')
                        .html(content)
                        .appendTo($tipContent)
                        .niceScroll({
                            cursorcolor: '#1493E4',
                            cursoropacitymax: 0.8,
                            cursorborder: "1px solid #1493E4",
                            touchbehavior: false,
                            cursorwidth: "5px",
                            cursorborderradius: "5px",
                            autohidemode: true,
                            horizrailenabled: false
                        });
                }else{
                    Feng.error(json.message);
                }
            },
            error: function (error) {
                Feng.error(error);
            }
        });
    },
    tipsClose: function () {
        
    }
};

