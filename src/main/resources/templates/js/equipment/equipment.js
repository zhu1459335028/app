var ApplyColours =function (data,page,devicetype,iscertificateofinspection,isputonrecord) { // data每页个数 page第几页 devicetype设备类型  iscertificateofinspection  有无合格证  isputonrecord  是否备案
        $.ajax({
            type: 'get',
            url: "/device/queryAllDevice",
            data:{
                currentPage:page,//第几页
                pageSize:data,//每页个数
                devicetype: devicetype,
                iscertificateofinspection: iscertificateofinspection,
                isputonrecord: isputonrecord
            },
            dataType: "json",
            success: function (json) {
                console.log(json)
                $(".private-sub_pages").text(json.result.pageNum) //当前页数
                $(".center-Number").text(json.result.pages)//总共页数

                $(".center-sum").text(json.result.total)//总共数据
               $(".center-block").empty()
                $(".paging").empty()

                $(json.result.list).each(function (){
                    var iscertificateofinspection=""
                    if (this.iscertificateofinspection==1){
                        iscertificateofinspection ="有"
                    } else {
                        iscertificateofinspection ="无"

                    }
                        var isputonrecord=""
                        if (this.isputonrecord==1){
                            isputonrecord ="是"
                        } else {
                            isputonrecord ="否"
                        }
                    $(".center-block").append(" <div class='device-box1' id="+this.id+">" +
                        "<input type='checkbox'>" +
                        "<div> " +
                        "<span>"+this.devicename+"</span> " +
                        "<span>"+this.devicetype+"</span> " +
                        "<span>"+this.devicemodel+"</span>" +
                        " <span>"+iscertificateofinspection+"</span> " +
                        "<span>"+this.examineid+"</span> " +
                        "<span>"+this.examinetime+"</span> " +
                        "<span>"+isputonrecord+"</span>" +
                        "<span>"+this.drivername+"</span>" +
                        "<span>"+this.telephone+"</span>" +
                        "<span>"+this.operate+"</span>" +
                        "<span style='color: #7491CC;'><b onclick='looKover("+this.id+")'>查看</b>&nbsp;&nbsp;&nbsp;&nbsp;<b onclick='ContentEncoding("+this.id+")'>编辑</b></span>" +
                        "</div>" +
                        "</div>")
                })

                $(".paging").append("<nav aria-label='Page navigation'>" +
                    "<ul class='pagination'>" +
                    "<li>" +
                    "<a href='#' onclick='Previous("+json.result.pageSize+","+json.result.pageNum+","+iscertificateofinspection+","+isputonrecord+")' aria-label='Previous'>" +
                    "<span aria-hidden='true'>上一页</span>" +
                    "</a>" +
                    "</li>" +
                    "<li class='next-center'>" +
                    "<a href='#' onclick='Previous3("+json.result.pageSize+","+json.result.pageNum+","+iscertificateofinspection+","+isputonrecord+","+json.result.pages+")' aria-label='Next'>" +
                    "<span aria-hidden='true'>" +
                    "下一页" +
                    "</span>" +
                    "</a>" +
                    "</li>" +
                    "</ul>" +
                    "</nav>")
                var p=0;

                for (var j=0;j<json.result.lastPage;j++){
                    p=parseInt(j)+1
                    $("<li><a href='#' onclick='Previous1("+json.result.pageSize+","+p+","+iscertificateofinspection+","+isputonrecord+")' class='box'>"+p+"</a></li>").insertBefore(".next-center")
                }
            }
        })
    }//这是渲染数据的
    function showpages(){
        var i= $(".Show-pages").val()
        ApplyColours(i,1,null,2,2 )
    }
    var i = $('.Show-pages').val()//获取它的要展示的页数

    ApplyColours(i,1,null,2,2 )//页面刷新就渲染
function search(){
    $(".Check-All").prop("checked",false)
    var devicetype=$(".vehicle-type").val()
    var  isputonrecord=parseInt($(".put-on-records").val())
    console.log(typeof $(".vehicle-type").val())
    var  iscertificateofinspection=parseInt($(".qualified").val())
    ApplyColours(i,1,devicetype,iscertificateofinspection,isputonrecord)


}
//重置按钮
function StopCircle() {
    // $(".Check-All").prop("checked",false)
    // $(".vehicle-type").val("2")
    // $(".put-on-records").val("2")
    // $(".qualified").val("2")
    // var i = $('.Show-pages').val()
    // ApplyColours(i,1,2,2,2)
    location.reload()
}

//上一页
function Previous(a,b,d,e){
    var devicetype=$(".vehicle-type").val()
    if (b<=1){
        b=2
        console.log(b-1)
        ApplyColours(a,b-1,devicetype,d,e)
    }else {
        console.log(b-1)
        ApplyColours(a,b-1,devicetype,d,e)
    }
}
//每一页
function Previous1(a,b,d,e) {
    var devicetype=$(".vehicle-type").val()
    ApplyColours(a,b,devicetype,d,e)
}
//下一页
function Previous3(a,b,d,e,f) {
    var devicetype=$(".vehicle-type").val()
    if (b==f){
        ApplyColours(a,f,devicetype,d,e)
    } else {
        ApplyColours(a,b+1,devicetype,d,e)
    }

}

//删除按钮
function deletename(){
    Feng.confirm("是否删除？", function () {
        var arr =""

        // console.log($(this).children("input").prop("checked"))
        $(".device-box1").each(function () {
            if ($(this).children("input").prop("checked")==1){
                arr += $(this).attr('id')+","
            }
        })
        arr= arr.substring(0,arr.length-1)
        console.log(arr)
        $.ajax({
            async:false,
            type: 'get',
            url: "/device/deleteDevice",
            data:{ids:arr},
            dataType: "json",
            success:function (json) {
                console.log(json)
            }
        })
        $(".Check-All").prop("checked",false)
        location.reload()
    });

}
//这是多选按钮设置
function CheckAll() {
    $(".device-box1").each(function (){
        $(this).children("input").attr("checked",$(".Check-All").prop("checked"))
    })
}
//这是获取设备类型接口
// vehicle-type
$.ajax({
    type: 'get',
    url: "/device/queryDeviceType",
    dataType: "json",
    success:function (json) {
        console.log(json)
        $(json.result).each(function () {
            $(".vehicle-type").append("<option value="+this.devicetype+">"+this.devicetype+"</option>")
        })
    }
})
//录入
    function entering(){
        $(".datetimepicker").datetimepicker({
            autoclose: true,
            language: "zh-CN",
            startView: 2,
            minView: "month",
            format: "yyyy-mm-dd",
            forceParse: 0
        })
    $(".queren").hide()
        $(".save_keep").show()
    $(".zhenzheo").show()
        $(".verification").hide()
        $.ajax({
            type: 'get',
            url: "/device/queryDeviceType",
            dataType: "json",
            success:function (json) {
                $(".devices_type").empty()
                console.log(json)
                $(json.result).each(function () {
                    $(".devices_type").append("<option value="+this.id+">"+this.devicetype+"</option>")
                })
            }
        })
        $(".hegeyes").prop("checked",false)
        $(".hegeno").prop("checked",true)
        $(".beianno").prop("checked",true)
        $(".beianyes").prop("checked",false)
        $(".facilityname").val("")
        $(".quipment-model").val("")
        $(".driver_name").val("")
        $(".driver_touch").val("")
        $(".Operation_number").val("")
        $(".Add_type").show()
        $(".devices_type").show()
        $(".Device_add_type").hide()


    }
    //取消
function quxiaoneirong() {
     // $(".zhenzheo").hide()
    // typeisadded=1
    location.reload()
}
var typeisadded=1
//添加管理
function TianJiaGuanLi() {
    typeisadded=2
    $(".Add_type").hide()
    $(".devices_type").hide()
    $(".Device_add_type").show()
    $(".add_content").val("")
}
//确定添加
function QueDingTianJia() {
    typeisadded=2
    var devicetype={devicetype:$(".add_content").val()}
    if ($(".add_content").val()==0){
        Feng.error("添加类型内容不能为空")
        return;
    }
// else
// if($(".add_content").val().length<=1){
//     Feng.error("添加类型内容长度小于1")
//     return;
// }
    else {
        $.ajax({
            type: 'POST',
            url: "/device/saveDeviceType",
            data:JSON.stringify(devicetype),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success:function (json) {
                $(".devices_type").empty()
                 $.ajax({
                    type: 'get',
                    url: "/device/queryDeviceType",
                    dataType: "json",
                    success:function (json) {
                        console.log(json)
                        $(json.result).each(function () {
                            $(".devices_type").append("<option value="+this.id+">"+this.devicetype+"</option>")
                        })
                    }
                })
            }
        })

        QuXiaoTianJia()
    }

}
//取消添加
function QuXiaoTianJia() {
    typeisadded=1
    $(".Add_type").show()
    $(".devices_type").show()
    $(".Device_add_type").hide()
}
//有合格证
$(".hegeyes").click(function () {
   if($(this).prop("checked")) {
      $(".hegeno").prop("checked",false)

       $(".serial_number").val("")
       $(".serial_data").val("")
       $(".verification").show()
   }else {
       $(".verification").hide()
       $(".hegeno").prop("checked",true)
   }

})
//无合格证
$(".hegeno").click(function () {
    if($(this).prop("checked")) {
        $(".hegeyes").prop("checked",false)
        $(".verification").hide()

    }else {
        $(".hegeyes").prop("checked",true)

        $(".serial_number").val("")
        $(".serial_data").val("")
        $(".verification").show()
    }
})
//有备案
$(".beianyes").click(function () {
    if($(this).prop("checked")) {
        $(".beianno").prop("checked",false)

    }else {
        $(".beianno").prop("checked",true)
    }
})
//无备案
$(".beianno").click(function () {
    if($(this).prop("checked")) {
        $(".beianyes").prop("checked",false)

    }else {
        $(".beianyes").prop("checked",true)
    }
})
var OnePersonOne={
    devicename:"",//设备名称
    devicetypeid:0,//设备类型
    devicemodel:"",//设备型号
    iscertificateofinspection:0,//有无合格证
    examineid:"",//检验编号
    examinetime:"",//检验日期
    isputonrecord:2,//是否备案
    drivername:"",//司机名称
    telephone:"",//联系方式
    operate:"",//操作证明
    qualifiedimage:"",//合格证
    operateimage:"",//操作证
    fileurl:""//上传文件
}
//数据保存
function ShuJiBaoCun(){
    if (typeisadded==2){
        Feng.error("添加类型没有确认")
        return;
    } else {
        
var $SheBeiName= $(".facilityname").val(),//设备名称
    $devices_type=$(".devices_type").val(),//设备类型
    $quipmentmodel=$(".quipment-model").val(),//设备型号
    $serialnumber=$(".serial_number").val(),//验证编号
    $serialdata=$(".serial_data").val(),//验证日期
    $drivername=$(".driver_name").val(),//司机姓名
    $driver_touch= $(".driver_touch").val(),//联系方式
    $Operationnumber=$(".Operation_number").val()//操作证号
    console.log($devices_type)
    OnePersonOne.drivername=$drivername
    OnePersonOne.telephone=$driver_touch
    OnePersonOne.operate=$Operationnumber
        
    if ($SheBeiName.length<=0){
        Feng.error("设备名称不能为空")
        return;
    } else if ($quipmentmodel.length<=0) {
        Feng.error("设备型号不能为空")
        return;
    }else {
        OnePersonOne.devicename=$SheBeiName
        OnePersonOne.devicetypeid=parseInt($devices_type)
        OnePersonOne.devicemodel=$quipmentmodel
        if ($(".hegeyes").prop("checked")){//判断是否有合格证
            OnePersonOne.iscertificateofinspection=1
            if($serialnumber.length<=0){
                Feng.error("检验编号不能为空")
                return;
            }else if ($serialdata.length<=0){
                Feng.error("检验日期不能为空")
                return;
            } else {
                OnePersonOne.examineid=$serialnumber
                OnePersonOne.examinetime=$serialdata
            }
            if($(".inspection_imgs")[0].files.length==1){
                console.log(123)
                var formData = new FormData();
                formData.append("image",$(".inspection_imgs")[0].files[0]);

                $.ajax({
                    async:false,
                    type: 'POST',
                    url: "/device/uploadDeviceImage",
                    data: formData,
                    contentType: "multipart/form-data",
                    processData: false, // 告诉jQuery不要去处理发送的数据
                    contentType: false,
                    success: function(result){
                        console.log(result.result)
                        OnePersonOne.qualifiedimage=result.result
                    }
                })
            }else {
                OnePersonOne.qualifiedimage=""
            }

        }else {
            OnePersonOne.iscertificateofinspection=0
            OnePersonOne.examineid=""
            OnePersonOne.examinetime=null
         }
        if ($(".beianyes").prop("checked")) {//判断是否备案
            OnePersonOne.isputonrecord=1
        }else {
            OnePersonOne.isputonrecord=0
        }
    }

        if($(".inspection_imgs11s")[0].files.length==1){
            console.log(154)
            var formDataword = new FormData();
            formDataword.append("file",$(".inspection_imgs11s")[0].files[0]);

            $.ajax({
                async:false,
                type: 'POST',
                url: "/device/uploadDeviceFile",
                data: formDataword,
                contentType: "multipart/form-data",
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false,
                success: function(result){
                    console.log(result.result)
                    OnePersonOne.fileurl=result.result.fileurl
                }
            })
        }else {
            OnePersonOne.fileurl=""
    }

    if ($(".inspection_imgs1")[0].files.length==1){
        console.log(123)
        var formData = new FormData();
        formData.append("image",$(".inspection_imgs1")[0].files[0]);

        $.ajax({
            async:false,
            type: 'POST',
            url: "/device/uploadDeviceImage",
            data: formData,
            contentType: "multipart/form-data",
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false,
            success: function(result){
                console.log(result.result)
                OnePersonOne.operateimage=result.result
            }
        })
    }else {
        OnePersonOne.operateimage=""
    }
    console.log(OnePersonOne)
    $.ajax({
        async:false,
        type: 'POST',
        url: "/device/saveOrUpdateDevice",
        data: JSON.stringify(OnePersonOne),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function (json) {
            console.log(json)
        }
    })
    }

    // $(".zhenzheo").hide()
    Feng.success("录入成功")
    location.reload()
}
var deviceid="";
function ContentEncoding(id) {
    deviceid=id;
    $(".queren").show()
    $(".save_keep").hide()

    $(".zhenzheo").show()
    $.ajax({
        type: 'get',
        url: "/device/queryDeviceType",
        dataType: "json",
        success:function (json) {
            $(".devices_type").empty()
            console.log(json)
            $(json.result).each(function () {
                $(".devices_type").append("<option value="+this.id+">"+this.devicetype+"</option>")
            })
        }
    })
    $(".datetimepicker").datetimepicker({
        autoclose: true,
        language: "zh-CN",
        startView: 2,
        timepicker: true,
        minView: "month",
        format: "yyyy-mm-dd",
        forceParse: 0
    })

    $.ajax({
        type: 'GET',
        url: "/device/queryDevice/"+id,
        dataType: "json",
        success:function (json) {
            console.log(json)
            $(".facilityname").val(json.result.devicename)//设备名称
            $(".quipment-model").val(json.result.devicemodel)//设备型号
            $(".devices_type").val(json.result.devicetypeid)//设备类型
            $(".driver_name").val(json.result.drivername)//
            $(".driver_touch").val(json.result.telephone)
            $(".Operation_number").val(json.result.operate)

            if (json.result.isputonrecord==1){
                $(".beianyes").prop("checked",true)
                $(".beianno").prop("checked",false)

            }else {
                $(".beianyes").prop("checked",false)
                $(".beianno").prop("checked",true)
            }
            if (json.result.iscertificateofinspection==1){
                $(".hegeyes").prop("checked",true)
                $(".hegeno").prop("checked",false)
                $(".verification").show()
                $(".serial_number").val(json.result.examineid)
                $(".serial_data").val(json.result.examinetime)
                if (json.result.qualifiedimage!=""){
                    $(".inspection_imgs").hide()
                    $(".inspection_img span").text("")
                    $(".inspection_img").append("<img onclick='imgsbox1()' class='qualifiedimages'  src="+json.result.qualifiedimage+">").css("background","#fff")
                    $(".shanchutu").show()
                } else {
                    $(".inspection_imgs").show()
                    $(".inspection_img span").text("添加检验图片")
                    $(".shanchutu").hide()
                    $(".inspection_img img").remove()
                }
            }else {
                $(".hegeyes").prop("checked",false)
                $(".hegeno").prop("checked",true)
                $(".verification").hide()
                $(".verification").val("")
                $(".serial_data").val("")
            }
            if(json.result.operateimage!=""){
                $(".inspection_imgs1").hide()
                $(".inspection_img1 span").text("")
                $(".inspection_img1").append("<img onclick='imgsbox2()' class='operateimages' src="+json.result.operateimage+">").css("background","#fff")
                $(".shanchutu1").show()
            }else {
                $(".inspection_imgs1").show()
                $(".inspection_img1 span").text("添加驾照图片")
                $(".shanchutu1").hide()
                $(".inspection_img1 img").remove()
            }
            if (json.result.fileurl.length!=0){
                $(".inspection_img1s").css("background","#ffffff")
                $(".inspection_img1s input").hide()
                $(".inspection_img1s span").text("")
                $(".shanchutu1s").show()
                $(".inspection_img1s").append("<b onclick='wordslooks()' class='wordslooks1' style='color: #000000;' data-src="+json.result.fileurl+">操作人教育信息</b>")

            }else {
                $(".inspection_img1s").css("background","#009DD6")
                $(".inspection_img1s input").show()
                $(".shanchutu1s").hide()
            }
            $(".Add_type").show()
            $(".devices_type").show()
            $(".Device_add_type").hide()
        }
    })

}
//修改内容
var OnePersonOne1={
    id:0,
    devicename:"",//设备名称
    devicetypeid:0,//设备类型
    devicemodel:"",//设备型号
    iscertificateofinspection:0,//有无合格证
    examineid:"",//检验编号
    examinetime:"",//检验日期
    isputonrecord:2,//是否备案
    drivername:"",//司机名称
    telephone:"",//联系方式
    operate:"",//操作证明
    qualifiedimage:"",//合格证
    operateimage:"",//操作证
    fileurl:""//驾驶人文件
}
//保存数据
function affirm() {
    OnePersonOne1.id=deviceid
    if (typeisadded==2){
        Feng.error("添加类型没有确认")
        return;
    } else {
        var $SheBeiName = $(".facilityname").val(),//设备名称
            $devices_type = $(".devices_type").val(),//设备类型
            $quipmentmodel = $(".quipment-model").val(),//设备型号
            $serialnumber = $(".serial_number").val(),//验证编号
            $serialdata = $(".serial_data").val(),//验证日期
            $drivername = $(".driver_name").val(),//司机姓名
            $driver_touch = $(".driver_touch").val(),//联系方式
            $Operationnumber = $(".Operation_number").val()//操作证号
        console.log($devices_type)
        OnePersonOne1.drivername = $drivername
        OnePersonOne1.telephone = $driver_touch
        OnePersonOne1.operate = $Operationnumber
        OnePersonOne1.operate = $Operationnumber
        if ($SheBeiName.length <= 0) {
            Feng.error("设备名称不能为空")
            return;
        } else if ($quipmentmodel.length <= 0) {
            Feng.error("设备型号不能为空")
            return;
        } else {
            OnePersonOne1.devicename = $SheBeiName
            OnePersonOne1.devicetypeid = parseInt($devices_type)
            OnePersonOne1.devicemodel = $quipmentmodel
            if ($(".hegeyes").prop("checked")) {//判断是否有合格证
                OnePersonOne1.iscertificateofinspection = 1
                if ($serialnumber.length <= 0) {
                    Feng.error("检验编号不能为空")
                    return;
                } else if ($serialdata.length <= 0) {
                    Feng.error("检验日期不能为空")
                    return;
                } else {
                    OnePersonOne1.examineid = $serialnumber
                    OnePersonOne1.examinetime = $serialdata
                }


                if ($(".inspection_imgs")[0].files.length==0){
                   if ($(".inspection_img img").length==0){
                       OnePersonOne1.qualifiedimage=""
                   }else {
                       OnePersonOne1.qualifiedimage=$(".inspection_img img")[0].src
                   }
                }else {
                    console.log(123)
                    var formData = new FormData();
                    formData.append("image",$(".inspection_imgs")[0].files[0]);

                    $.ajax({
                        async:false,
                        type: 'POST',
                        url: "/device/uploadDeviceImage",
                        data: formData,
                        contentType: "multipart/form-data",
                        processData: false, // 告诉jQuery不要去处理发送的数据
                        contentType: false,
                        success: function(result){
                            console.log(result.result)
                            OnePersonOne1.qualifiedimage=result.result
                        }
                    })
                }
            } else {
                OnePersonOne1.iscertificateofinspection = 0
                OnePersonOne1.examineid = ""
                OnePersonOne1.examinetime = null
            }

            if ($(".inspection_imgs11s")[0].files.length==0) {
                console.log($(".inspection_img1s b").data("src"))
                if ($(".inspection_img1s b").length==0){
                    OnePersonOne1.fileurl=""
                }else {
                    OnePersonOne1.fileurl=$(".inspection_img1s b").data("src")
                }
            }else {
                var formDataas = new FormData();
                formDataas.append("file",$(".inspection_imgs11s")[0].files[0]);
                $.ajax({
                    async:false,
                    type: 'POST',
                    url: "/device/uploadDeviceFile",
                    data: formDataas,
                    contentType: "multipart/form-data",
                    processData: false, // 告诉jQuery不要去处理发送的数据
                    contentType: false,
                    success: function(result){
                        console.log(result)
                        OnePersonOne1.fileurl=result.result.fileurl
                    }
                })
            }

            if ($(".beianyes").prop("checked")) {//判断是否备案
                OnePersonOne1.isputonrecord = 1
            } else {
                OnePersonOne1.isputonrecord = 0
            }
        }


        if ($(".inspection_imgs1")[0].files.length==0){
            if ($(".inspection_img1 img").length==0){
                OnePersonOne1.operateimage=""
            }else {
                OnePersonOne1.operateimage=$(".inspection_img1 img")[0].src
            }
        }else {
            console.log(123)
            var formData = new FormData();
            formData.append("image",$(".inspection_imgs1")[0].files[0]);

            $.ajax({
                async:false,
                type: 'POST',
                url: "/device/uploadDeviceImage",
                data: formData,
                contentType: "multipart/form-data",
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false,
                success: function(result){
                    console.log(result.result)
                    OnePersonOne1.operateimage=result.result
                }
            })
        }

        console.log(OnePersonOne1)
        $.ajax({
            async:false,
            type: 'POST',
            url: "/device/saveOrUpdateDevice",
            data: JSON.stringify(OnePersonOne1),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (json) {
                Feng.success("保存成功")
            }
        })

        setInterval(function () {
            location.reload()
        },100)
    }
}
function looKover (id) {
    layer.open({
        type: 1,
        title: "查看设备管理",
        area: ["800px", '580px'],
        maxHeight: 800,
        content: $('#photograph').html(),
    })
    $.ajax({
        type: 'GET',
        url: "/device/queryDevice/"+id,
        dataType: "json",
        success:function(json){
            console.log(json)
            if (json.result.iscertificateofinspection==1){
                $(".hegezheng").empty().append("有&nbsp;&nbsp;<img class='boximgs' onclick='boximgs1()' width='16px' height='16px' src="+json.result.qualifiedimage+">")
                if(json.result.qualifiedimage==""){
                    $(".hegezheng img").remove()
                }
                $(".jianyanbian").text(json.result.examineid)
                $(".yanzhengriqi").text(json.result.examinetime)
            }else {
                $(".hegezheng").text("无")
                $(".jianyanbian").text("")
                $(".yanzhengriqi").text("")
            }
            if (json.result.isputonrecord==1){
                $(".beianjilu").text("有")

            }else {
                $(".beianjilu").text("无")
            }
            if (json.result.fileurl.length!=0){
                $(".czrwd").empty().append("<div onclick='wordslook()' class='word_look' data-src="+json.result.fileurl+" style='color:blue;'>操作人教育文档</div>")
            } else {
                $(".czrwd").empty()
            }

            $(".shebeiming").text(json.result.devicename)
            $(".shebeilei").text(json.result.devicetype)
            $(".shebeixing").text(json.result.devicemodel)
            $(".sjxm").text(json.result.drivername)
            $(".sjlxfs").text(json.result.telephone)
            $(".sjlxfs").text(json.result.telephone)
            $(".czzh").empty().append(""+json.result.operate+"&nbsp;&nbsp;<img  onclick='boximgs()' class='boximgs1' width='16px' height='16px' src="+json.result.operateimage+">")
            if(json.result.qualifiedimage==""){
                $(".hegezheng img").remove()
            }
        }
    })
}
var userAgent = navigator.userAgent;
$(".inspection_imgs").change(function () {
    var docObj =$(this)[0];
    var fileList = docObj.files;
   console.log($(this)[0].files)
    $(".inspection_img img").remove()
    $(".inspection_img").append("<img class='qualifiedimages' onclick='imgsbox1()'/>")
    if(userAgent.indexOf('MSIE') == -1){//IE以外浏览器
        $(".inspection_img img").attr("src",window.URL.createObjectURL(docObj.files[0]));
        $(".inspection_imgs").hide()
        $(".inspection_img span").text("")
        $(".inspection_img").css("background","#fff")
        $(".shanchutu").show()
    }else {//IE浏览器
        if (docObj.value.indexOf(",") != -1) {
            var srcArr = docObj.value.split(",");
            $(".inspection_img img").attr("src",srcArr[0]);
            $(".inspection_imgs").hide()
        } else {
            $(".inspection_img img").attr("src",docObj.value)
            $(".inspection_imgs").hide()
        }
    }
})

$(".inspection_imgs1").change(function () {
    var docObj =$(this)[0];
    var fileList = docObj.files;
    console.log($(this)[0].files)
    $(".inspection_img1 img").remove()
    $(".inspection_img1").append("<img class='operateimages' onclick='imgsbox2()'/>")
    if(userAgent.indexOf('MSIE') == -1){//IE以外浏览器
        $(".inspection_img1 img").attr("src",window.URL.createObjectURL(docObj.files[0]));
        $(".inspection_imgs1").hide()
        $(".inspection_img1 span").text("")
        $(".inspection_img1").css("background","#fff")
        $(".shanchutu1").show()
    }else {//IE浏览器
        if (docObj.value.indexOf(",") != -1) {
            var srcArr = docObj.value.split(",");
            $(".inspection_img1 img").attr("src",srcArr[0]);
            $(".inspection_imgs1").hide()
        } else {
            $(".inspection_img1 img").attr("src",docObj.value)
            $(".inspection_imgs1").hide()
        }
    }
})




$(".inspection_imgs11s").change(function () {
    var docObj =$(this)[0];
    //得到所有的图片文件
    var fileList = docObj.files;
    var fileformat=".doc"
    if (fileList[0].name.indexOf(fileformat)!=-1) {


        $(".inspection_img1s").append("<b style='color: #000000; width: 80%; display: inline-block;'>" + fileList[0].name + "</b>")
        $(".inspection_imgs1s").hide()
        $(".inspection_img1s span").text("")
        $(".inspection_img1s input").hide()
        $(".inspection_img1s").css("background", "#fff")
        $(".shanchutu1s").show()
    }else {
        Feng.error("文件类型错误");
        $(this).val("")
    }
})


var deleteimgs={
    fileType:"",
    outletid:"",
    date:"",
    filename:""
}
$(".shanchutu").on("click",function () {
    Feng.confirm("是否删除图片？删除后将图片直接删除且保存。", function () {
        if($(".inspection_imgs")[0].files.length==1){
            $(".inspection_imgs").val("").show()
            $(".inspection_img img").remove()
            $(".shanchutu").hide()
            $(".inspection_img").css("background","#009DD6")
            $(".inspection_img span").text("添加图片")
        }else {
            var images=$(".inspection_img img")[0].src
            images=images.split("resource/")
            console.log(images[1].split("/"))
            var DeleteThePicture=images[1].split("/")
            deleteimgs.fileType=DeleteThePicture[0]
            deleteimgs.outletid=DeleteThePicture[1]
            deleteimgs.fileType=DeleteThePicture[2]
            deleteimgs.fileType=DeleteThePicture[3]
            $.ajax({
                type:"get",
                url:"/resource/deleteDeviceImage",
                data: deleteimgs,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success:function (json) {
                    $(".inspection_imgs").show()
                    $(".inspection_img span").text("添加图片")
                    $(".inspection_img").css("background","#009DD6")
                    $(".shanchutu").hide()
                    $(".inspection_img img").remove()
                }
            })
        }

    })
})
$(".shanchutu1").on("click",function () {
    Feng.confirm("是否删除图片？删除后将图片直接删除且保存。", function () {
        if($(".inspection_imgs1")[0].files.length==1){
            $(".inspection_imgs1").val("").show()
            $(".inspection_img1 img").remove()
            $(".shanchutu1").hide()
            $(".inspection_img1").css("background","#009DD6")
            $(".inspection_img1 span").text("添加图片")
        }else {
            var images=$(".inspection_img1 img")[0].src
            images=images.split("resource/")
            console.log(images[1].split("/"))
            var DeleteThePicture=images[1].split("/")
            deleteimgs.fileType=DeleteThePicture[0]
            deleteimgs.outletid=DeleteThePicture[1]
            deleteimgs.fileType=DeleteThePicture[2]
            deleteimgs.fileType=DeleteThePicture[3]
            $.ajax({
                type:"get",
                url:"/resource/deleteDeviceImage",
                data: deleteimgs,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success:function (json) {
                    console.log(json)
                    $(".inspection_imgs1").show()
                    $(".inspection_img1 span").text("添加图片")
                    $(".inspection_img1").css("background","#009DD6")
                    $(".shanchutu1").hide()
                    $(".inspection_img1 img").remove()
                }
            })
        }

    })
})
function imgbox(src) {

    layer.open({
        type: 1,
        title: "查看图片",
        area: ["1000px", '800px'],
        content: $('#img_looks').html(),
    })
    $(".img_look").attr("src",src).width("100%").height("100%")
}

function imgboxs(src) {

    layer.open({
        type: 1,
        title: "查看文件",
        area:["900px","800px"],
        content: $('#img_looks1').html(),
    })
    $(".img_lookword").attr("src",src).width("860px").height("700px")
}

$(".shanchutu1s").on("click",function () {
    Feng.confirm("是否删除文件？",function() {
        if($(".inspection_imgs11s")[0].files.length==1){
            $(".inspection_imgs11s").val("").show()
            $(".inspection_img1s b").remove()
            $(".shanchutu1s").hide()
            $(".inspection_img1s span").text("添加教育人文件")

            $(".inspection_img1s").css("background","#009dd6")
        }else {
           var words=$(".inspection_img1s b").data("src")
            words=words.split("resource/")
            console.log(words[1].split("/"))
            var DeleteThePicture=words[1].split("/")
            deleteimgs.fileType=DeleteThePicture[0]
            deleteimgs.outletid=DeleteThePicture[1]
            deleteimgs.fileType=DeleteThePicture[2]
            deleteimgs.fileType=DeleteThePicture[3]

            $.ajax({
                type:"get",
                url:"/resource/deleteDeviceFile",
                data: deleteimgs,
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success:function (json) {
                    console.log(json)
                    if (json.code==200){
                        $(".inspection_imgs1s").show()
                        $(".inspection_imgs11s").show()
                        $(".inspection_img1s span").text("添加教育人文件")
                        $(".inspection_img1s").css("background","#009DD6")
                        $(".shanchutu1s").hide()
                        $(".inspection_img1s b").remove()

                    } else {
                        Feng.error("删除失败");
                        return;
                    }

                }
            })


            $(".shanchutu1s").hide()
            $(".inspection_img1s").css("background","#009dd6")
        }
    })
})





function boximgs() {
 imgbox( $(".boximgs1").attr("src"))
}
function boximgs1() {
    imgbox( $(".boximgs").attr("src"))
}

function imgsbox1() {
    imgbox( $(".qualifiedimages").attr("src"))
}
function imgsbox2() {
    imgbox( $(".operateimages").attr("src"))
}

function wordslook(){
    var word=
    imgboxs($(".word_look").data("src"))
}

function wordslook(){
        imgboxs($(".word_look").data("src"))
}
function wordslooks(src){
    imgboxs($(".wordslooks1").data("src"))
}