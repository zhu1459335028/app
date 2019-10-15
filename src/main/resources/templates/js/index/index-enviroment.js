var EnviromentModule = {
    $container: $("#enviroment"),
    enviromentData: null,
    update: function (data) {
        console.log(data);

        this.enviromentData = data;
        var html = '';
        for(var evi in data){
            var state = '';
            console.log(data[evi])
            if(data[evi].state==0){
                state = '<i class="iconfont icon-xiangshangjiantou"></i>';
            }else if(data[evi].state==1){
                state = '<i class="iconfont icon-xiangxiajiantou"></i>';
            }
            var site = data[evi].site || '--';
            var hangzhou = data[evi].hangzhou || '--';
            switch (evi) {
                case 'PM25':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-app_icons--"></i><span class="fenchen">PM2.5</span></span>' +
                                        '<span style="width: 35%;" class="color-fencen">'+site+'um/m³(工地)</span>' +
                                        '<span style="width: 35%;" class="dusthangzhou">'+hangzhou+'um/m³(杭州)</span>' +
                                        state +
                                    '</div>';
                    break;
                case 'temperature':
                    html = html + '<div class="between-flex white-font fontx2">' +
                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-wendu"></i>温度</span>' +
                        '<span style="width: 35%;">'+site+'°C(工地)</span>' +
                        '<span style="width: 35%;">'+hangzhou+'(杭州)</span>' +
                        state +
                        '</div>';
                    break;
                case 'humidity':
                    html = html + '<div class="between-flex white-font fontx2">' +
                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-shidu"></i>湿度</span>' +
                        '<span style="width: 35%;">'+site+'%(工地)</span>' +
                        '<span style="width: 35%;">'+hangzhou+'(杭州)</span>' +
                        state +
                        '</div>';
                    break;
            }
        }
        this.$container.html(html);
        if(this.tipIsOpen){
            this.onOpen();
        }
        $.ajax({
            type: 'get',
            url: "/homePage/homePageQueryTitle",
            dataType: "json",
            success: function (json){
                console.log(json)
                if(json.result.outletid==5){
                    console.log(125354)
                    $(".fenchen").text("粉尘")
                }
            }
        })
        if (data.reportStatus==1){
            console.log(111)
            $(".color-fencen").css("color",data.color)
        }else {
            console.log(222)
            $(".color-fencen").css("color","#efefef")
        }
    },
    initTips: function () {
        return new jBox('Tooltip', {
            attach: this.$container,
            trigger: 'click',
            content: '<div id="enviroment-content" style="width: 4rem;height: 2.5rem;display: flex;flex-flow: column;justify-content: space-around;"></div>',
            target: this.$container,
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
            onOpen: this.onOpen.bind(this),
            onClose: this.onClose.bind(this)
        });
    },
    onOpen: function () {
        this.tipIsOpen = true;
        var data = this.enviromentData, html = '';
        for(var evi in data){
            var state = '';
            if(data[evi].state==0){
                state = '<i class="iconfont icon-xiangshangjiantou"></i>';
            }else if(data[evi].state==1){
                state = '<i class="iconfont icon-xiangxiajiantou"></i>';
            }
            var site = data[evi].site || '--';
            var hangzhou = data[evi].hangzhou || '--';
            switch (evi) {
                case 'PM25':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-app_icons--"></i><span class="fenchen">PM2.5</span></span>' +
                                        '<span style="width: 30%;">'+site+'um/m³(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'um/m³(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'PM10':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-app_icons--"></i>PM10</span>' +
                                        '<span style="width: 30%;">'+site+'um/m³(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'um/m³(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'noise':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-zaoyin"></i>噪音</span>' +
                                        '<span style="width: 30%;">'+site+'dB(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'dB(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'temperature':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-wendu"></i>温度</span>' +
                                        '<span style="width: 30%;">'+site+'°C(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'°C(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'humidity':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-shidu"></i>湿度</span>' +
                                        '<span style="width: 30%;">'+site+'%(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'windspeed':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-fengsu"></i>风速</span>' +
                                        '<span style="width: 30%;">'+site+'(工地)</span>' +
                                        '<span style="width: 40%;">'+hangzhou+'(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'wind':
                    html = html + '<div class="between-flex white-font fontx2">' +
                                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-qiangfeng"></i>风向</span>' +
                                        '<span style="width: 30%;">'+site+'(工地)</span>' +
                                        '<span style="width: 45%;">'+hangzhou+'(杭州)</span>' +
                                        state +
                                '</div>';
                    break;
                case 'pressure':
                    html = html + '<div class="between-flex white-font fontx2">' +
                        '<span style="width: 20%;" class="theme-font"><i class="iconfont icon-zaoyin"></i>大气压</span>' +
                        '<span style="width: 30%;">'+site+'KPA(工地)</span>' +
                        '<span style="width: 40%;">'+hangzhou+'KPA(杭州)</span>' +
                        state +
                        '</div>';
                    break;
            }
        }
        $("#enviroment-content").html(html);
        $.ajax({
            type: 'get',
            url: "/homePage/homePageQueryTitle",
            dataType: "json",
            success: function (json){
                console.log(json)
                if(json.result.outletid==5){
                    $(".fenchen").text("粉尘")

                }
            }
        })
    },
    onClose: function () {
        this.tipIsOpen = false;
    }
};
EnviromentModule.initTips();

