var StaffMager = {
    table: {
        id: "staff_table",
        instance: null,
        dataUrl: "/peopleManage/employeeList/",
        columns: [
            {title: '工号', data: 'cardid'},
            {title: '姓名', data: 'name'},
            {title: '性别', data: 'sex'},
            {title: '手机号码', data: 'phone'},
            {title: '职位', data: 'jobName'},
            {title: '身份证号', data: 'cardid'},
            {title: '身份证照片', data: null, render: function(data, type, row, meta) {
                    return '<a href="javascript:void(0)" style="margin-right: 10px;" onclick="StaffMager.checkPic(event)">查看</a>' +
                        '<a href="javascript:void(0)" onclick="StaffMager.uploadPic(event)">上传</a>';
                }
        },
            {title:"人员详情", data:null, render: function(data, type, row, meta){
                    return '<a href="javascript:void(0)" style="margin-right: 10px;" onclick="examine(event)">查看</a>'+
                        '<a href="javascript:void(0)" onclick="information(event)">编辑</a>'
                }}
        ]
    },
    isEdit: {
        status: false,
        id: null
    }
};
/**
 * 平台标题
 * @param data
 */
var homePageQueryTitle = function () {
    $.ajax({
        type: 'get',
        url: "/homePage/homePageQueryTitle",
        dataType: "json",
        //contentType: "application/json",
        // jsonpCallback: "admin_cross",
        success: function (json) {
            if(json.code=="200"){
                console.log(json)
                // console.log(json.result.title)
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
StaffMager.openLayer = function (title, content, callback) {
    var _this = this;
    var maxWidth = window.screen.width * 0.5;
    var maxHeight = window.screen.height * 0.5;
    _this.layerIndex = layer.open({
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

/**
 * 单击查看部门人员信息
 * @param e
 * @param treeId
 * @param treeNode
 */
var onClickTree = function (e, treeId, treeNode) {
    $(".dept-name").text(treeNode.name);
    StaffMager.updateStaffTable(treeNode.id);
    StaffMager.dept = treeNode.id;
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
StaffMager.loadDept = function () {
    var ztreeObj = null;
    var ztree = new $ZTree("dept-tree", null);
    ztree.setSettings(setting);
    $.ajax({
        type: "GET",
        url: "/peopleManage/department",
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
 * 查看人员详情
 *
 */
var  zheshiid={};//获取到的人员id
/*
*这是查看人员信息
*
 */

function examine(event){
    var a = $(event.currentTarget).parent().siblings().eq(0).text()
    
    //console.log(zheshiid[0])
    for(var i=0;i<zheshiid.length;i++){
        if (zheshiid[i].cardid == a) {
           var employeeId = zheshiid[i].id;

        }
    }

   $("#index-img1").remove()
    $(".deleteimgs1").hide()
    // $(".Personnel-details-center").width($(".Personnel-details").width()*0.6).height($(".Personnel-details").height()*0.9)
    $(".Personnel-details").show()
    $(".Save-cancel").hide()
    // $(".Personnel-details-left-top").height($(".Personnel-details-left-top").width()+"px")
    // $(".img-touxiang-div").width($(".Personnel-details").width()*0.072).height($(".Personnel-details").width()*0.072)
    console.log(employeeId)
        $.ajax({
            type: 'get',
            url: "/peopleManage/employeeInfo/"+employeeId,
            dataType: "json",
            success:function (json){
                if (json.code==200){
                    console.log(json)
                    $(".details-name").text(json.result.name)
                    $(".job-title-p").text(json.result.jobName)
                    $(".img-touxiang").attr('src',json.result.headUrl)
                    $(".job-title-span").text(json.result.jobName)
                    $(".work-unit").text(json.result.departmentName)
                    $(".job-phone").text(json.result.phone)
                    $(".family-address").text(json.result.address)

                }else {
                    Feng.error(json.message);
                }
            },
            error: function (json) {
                console.log(json.message)
            }
        })
        $.ajax({
            type: 'get',
            url: "/peopleManage/queryEmployee/"+employeeId,      
            dataType: "json",
            success:function (json) {
                console.log(json)
                if (json.result==null){
                    $(".addImages").hide()
                    $(".addImages1").hide()
                    $(".corporationperiod").text("")
                    $(".corporationname").text("")
                    $(".itemname").text("")
                    $(".itemperiod").text("")
                    $(".classesname").text("")
                    $(".classesperiod").text("")

                    $(".qualified").text("")
                    $(".explains").text("")
                    $(".achievement").text("")
                    $(".constructionname").text("施工单位培训组织人员姓名:")
                    $(".supervisorname").text("监理单位审查人员名单:")
                    $(".remarks td").text("备注:")
                }else {
                   if(json.code==200){

                       if (json.result.image){
                           $(".addImages").show()
                           $(".deleteimgs").hide()
                           $(".examine").hide()
                           $(".file").hide()
                           $(".text-detail").hide()
                           $(".examines").show()
                           $(".addImages img").remove()
                           $(".addImages").append("<img src="+json.result.image+">")
                       }else {
                           $(".addImages").hide()
                       }
                       if (json.result.fileurl.length>0){
                           $(".examines1").show()
                           $(".addImages1").show()
                           $(".text-detail1").hide()
                           $(".file1").hide()
                           $(".addImages1").append("<p id='index-img1' style='line-height: 100px' data-src="+json.result.fileurl+">"+json.result.filename+"</p>")
                       }else {
                           $(".addImages1").hide()
                       }


                    $(".corporationname").text(json.result.corporationname)
                    $(".corporationperiod").text(json.result.corporationperiod  +"学时")

                    $(".itemname").text(json.result.itemname)
                    $(".itemperiod").text(json.result.itemperiod  +"学时")

                    $(".classesname").text(json.result.classesname)
                    $(".classesperiod").text(json.result.classesperiod  +"学时")

                    $(".qualified").text(json.result.qualified)
                    $(".explains").text(json.result.explains)
                    $(".achievement").text(json.result.achievement)
                    $(".constructionname").text("施工单位培训组织人员姓名:"+json.result.constructionname+"")
                    $(".supervisorname").text("监理单位审查人员名单:"+json.result.supervisorname+"")
                    $(".remarks td").text("备注:"+json.result.remarks+"")
            }else{
                      Feng.error(json.message);

                   }
                }

            },
            error: function (json) {
                console.log(json.message)
            }
    
        })

}
/*
* 这是编辑人员信息
* */
var ModifyData ={
    id: 0,
    image:"",
    classesname: "",
    classesperiod: "",
    corporationname: "",
    corporationperiod: "",
    itemname:"",
    itemperiod: "",
    employeeid:0,
    fileurl:"",
    filename:"",
    explains: "",
    achievement: "",
    constructionname: "",
    outletid: 2,
    qualified: "",
    remarks: "",
    supervisorname: "",
}

var date=new Date();
var dangqian=(date .getFullYear())+"-"+(date .getMonth()+1)+"-"+(date .getDate())
 //修改人员信息
function information(event) {
    $(".examines").hide()
    $(".addImages").show()
    $(".addImages1").show()
    $("#index-img1").remove()
    $(".examines1").hide()
    var a = $(event.currentTarget).parent().siblings().eq(0).text()
    $(".Save-cancel").show()
    for(var i=0;i<zheshiid.length;i++){
        if (zheshiid[i].cardid == a) {
            var employeeId = zheshiid[i].id;
        }
    }

    $(".Personnel-details").show()
    $.ajax({
        type: 'get',
        url: "/peopleManage/employeeInfo/"+employeeId,
        dataType: "json",
        success:function (json){
            if (json.result==null){
                ModifyData.id=null;
            } else {
            if (json.code==200){
                console.log(json)
                $(".details-name").text(json.result.name)
                $(".job-title-p").text(json.result.jobName)
                $(".img-touxiang").attr('src',json.result.headUrl)
                $(".job-title-span").text(json.result.jobName)
                $(".work-unit").text(json.result.departmentName)
                $(".job-phone").text(json.result.phone)
                $(".family-address").text(json.result.address)
            }else {
                Feng.error(json.message);
            }
            }
        },
        error: function (json) {
            console.log(json.message)
        }
    })
    $.ajax({
        type: 'get',
        url: "/peopleManage/queryEmployee/"+employeeId,
        dataType: "json",
        success:function (json) {
            console.log(json)
            ModifyData.employeeid =employeeId
          if (json.result==null){
              $(".file1").show()
              $(".file").show()
              $(".examines1").hide()
              $(".examine").hide()
              $(".deleteimgs").hide()
              $(".deleteimgs1").hide()
              $(".addImages img").remove()
              $("#index-img1").remove()
              $(".text-detail").show()
              $(".text-detail1").show()
              ModifyData.id=null;
              
              $(".corporationperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+dangqian+" style='width: 80%;border:1px solid #F0F0F0;'><br> 学时:<input value='15' style='width: 60%; margin-top: 10px;'>")
              $(".corporationname").empty().append("名字:<input width='70%' value='靳柒勤' > <br> 职位:<input width='60%'  value='安质部部长'>")

              $(".itemperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+dangqian+" style='width: 80%;border:1px solid #F0F0F0;'><br>学时:<input value='15' style='width: 60%; margin-top: 10px;'>")
              $(".itemname").empty().append("名字:<input width='70%' value='李志凯' > <br>职位:<input width='60%'  value='安质部副部长'>")

              $(".classesperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+dangqian+" style='width: 80%; border:1px solid #F0F0F0;'><br>学时:<input value='20' style='width: 60%; margin-top: 10px;'>")
              $(".classesname").empty().append("名字:<input width='70%' value='李小鹏'><br> 职位:<input width='60%' value='安质部部员'>")
              // $(".constructionname").empty().text("施工单位培训组织人员姓名:")
              $(".qualified").empty().append("<input value=''>")
              $(".achievement").empty().append("<input value=''>")
              $(".explains").empty().append("<input value=''>")
              $(".constructionname").empty().append("<span>施工单位培训组织人员姓名:</span><input value=''> ")
              $(".supervisorname").empty().append("<span>监理单位审查人员名单:</span><input value=''> ")
              $(".remarks td").empty().append("备注:<input style='width: 80%' value=''>")
              $(".datetimepicker").datetimepicker({
                  format: 'yyyy-mm-dd',
                  autoclose: true,
                  todayBtn: true,
                  todayHighlight: true,
                  language: 'zh-CN',
                  minView: 'month'
              })
          } else {
            if(json.code==200){
                console.log(json)
                if (json.result.image){
                    $(".file").hide()
                    $(".addImages img").remove()
                    $(".addImages").append("<img src="+json.result.image+">")
                    $(".deleteimgs").show()
                    $(".examine").show()
                    $(".text-detail").hide()
                }else {
                    $(".file").show()
                    $(".addImages img").remove()
                    $(".text-detail").show()
                    $(".deleteimgs").hide()
                    $(".examine").hide()
                }

                if (json.result.fileurl.length>0){

                    $(".file1").hide()
                    $(".examines1").show()
                    $(".addImages1").show()
                    $(".addImages1").append("<p id='index-img1' data-src="+json.result.fileurl+" style='line-height: 100px;'>"+json.result.filename+"</p>")
                    $(".deleteimgs1").show()
                    $(".text-detail1").hide()
                }else if (json.result.fileurl==null) {

                    $(".file1").show()
                    $(".examines1").hide()
                    $("#index-img1").remove()
                    $(".text-detail1").show()
                    $(".deleteimgs1").hide()

                }else {
                    $(".file1").show()
                    $(".examines1").hide()
                    $("#index-img1").remove()
                    $(".text-detail1").show()
                    $(".deleteimgs1").hide()
                }
                

                ModifyData.id= json.result.id

                // 这是公司级
                if (json.result.corporationperiod.length>10){
                 var corporationperiod=json.result.corporationperiod.split(" ")
                    $(".corporationperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+corporationperiod[0]+" style='width: 80%'><br>学时:<input style='width: 60%; margin-top: 10px;' value="+corporationperiod[1]+" >")
            }else {
                    $(".corporationperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+json.result.corporationperiod+" style='width: 80% border:1px solid #f0f0f0;'><br>学时:<input  style='width: 60%; margin-top: 10px;'value='0'>")

                }
            if (json.result.corporationname.search(" ") !=-1){
                var corporationname=json.result.corporationname.split(" ")
                $(".corporationname").empty().append("名字:<input  value="+corporationname[0]+"> <br> 职位:<input value="+corporationname[1]+">")
            }else {
                $(".corporationname").empty().append("名字:<input value="+json.result.corporationname+" ><br>职位:<input value=''>")
            }

                // 这是项目级
                if (json.result.itemperiod.length>10){
                    var itemperiod=json.result.itemperiod.split(" ")
                    $(".itemperiod").empty().append("<input type='text' class='form-control datetimepicker'  placeholder='请输入日期' value="+itemperiod[0]+" style='width: 80%; '><br>学时:<input  style='width: 60%; margin-top: 10px;' value="+itemperiod[1]+">")
                }else {
                    $(".itemperiod").empty().append("<input type='text' class='form-control datetimepicker'  placeholder='请输入日期' style=' border:1px solid #f0f0f0;' value="+json.result.corporationperiod+" ><br>学时:<input  style='width: 60%; margin-top: 10px;' value='0' '>")
                    // $(".corporationname").empty().append("名字:<input width='70%' value="+json.result.corporationname+" ><br>职位:<input value='0'> ")
                }
                if (json.result.itemname.search(" ") !=-1){
                    var itemname=json.result.itemname.split(" ")
                    $(".itemname").empty().append("名字:<input  value="+itemname[0]+"> <br> 职位:<input value="+itemname[1]+">")
                }else {
                    $(".corporationname").empty().append("名字:<input  value="+json.result.corporationname+" ><br>职位:<input value=''>")
                }

                if (json.result.itemperiod.length>10){
                    var classesperiod=json.result.classesperiod.split(" ")
                    $(".classesperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' value="+classesperiod[0]+" style='width: 80%'><br>学时:<input  style='width: 60%; margin-top: 10px;' value="+classesperiod[1]+">")
                }else {
                    $(".classesperiod").empty().append("<input type='text' class='form-control datetimepicker' placeholder='请输入日期' style=' border:1px solid #f0f0f0;' value="+json.result.classesperiod+" style='width: 80%'><br>学时:<input style='width: 60%; margin-top: 10px;' value='0' >")

                }
                if (json.result.itemname.search(" ") !=-1){
                    var classesname=json.result.classesname.split(" ")
                    $(".classesname").empty().append("名字:<input  value="+classesname[0]+"> <br> 职位:<input value="+classesname[1]+">")
                }else {
                    $(".itemname").empty().append("名字:<input  value="+json.result.itemname+" ><br>职位:<input value=''>")
                }
                $(".constructionname").empty().text("施工单位培训组织人员姓名:"+json.result.constructionname)
                $(".qualified").empty().append("<input value="+json.result.qualified+">")
                $(".achievement").empty().append("<input value="+json.result.achievement+">")
                $(".explains").empty().append("<input value="+json.result.explains+">")
                $(".constructionname").empty().append("<span>施工单位培训组织人员姓名:</span><input value="+json.result.constructionname+"> ")
                $(".supervisorname").empty().append("<span>监理单位审查人员名单:</span><input value="+json.result.supervisorname+"> ")
                $(".remarks td").empty().append("备注:<input style='width: 80%' value="+json.result.remarks+">")
                $(".datetimepicker").datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    todayBtn: true,
                    todayHighlight: true,
                    language: 'zh-CN',
                    minView: 'month'
                })
            }else{
                Feng.error(json.message);
            }
          }
        },
        error: function (json) {
            console.log(json.message)
        }

    })
}
function CloseDetails(){
    $(".Personnel-details").hide()
    $(".file").val("")
    $(".file1").val("")
    $("#index-img2").remove()
}

/**
 *保存表格内容传到后端
 * @constructor
 */

function SaveData(){

    ModifyData.corporationperiod=($(".corporationperiod input").eq(0).val() )+" "+  ($(".corporationperiod input").eq(1).val())
    ModifyData.corporationname=$(".corporationname input").eq(0).val() +" "+$(".corporationname input").eq(1).val()
    ModifyData .classesperiod=($(".classesperiod input").eq(0).val())+" "+ ($(".classesperiod input").eq(1).val())

    ModifyData.classesname =$(".classesname input").eq(0).val() +" "+$(".classesname input").eq(1).val()


    ModifyData .itemperiod=($(".itemperiod input").eq(0).val())+" "+ ($(".itemperiod input").eq(1).val())
    ModifyData .itemname =($(".itemname input").eq(0).val() )+" "+($(".itemname input").eq(1).val())

    ModifyData.remarks =$(".remarks input").val()
    ModifyData.achievement=$(".achievement input").val()
    ModifyData.qualified =$(".qualified input").val()
    ModifyData.explains =$(".explains input").val()
    ModifyData.constructionname =$(".constructionname input").val()
    ModifyData.supervisorname =$(".supervisorname input").val()
    // ModifyData.image=$(".file")[0].files[0]
    console.log($(".addImages img"))
    if ($(".addImages img").length==1){
        if ($(".file")[0].files.length==1){
            var formData = new FormData();
            formData.append("image",$(".file")[0].files[0]);
            var temp=$(".file")[0].files
            $.ajax({
                async:false,
                type: 'POST',
                url: "/peopleManage/uploadPersonImage",
                data: formData,
                contentType: "multipart/form-data",
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false,
                success: function(result){
                    if (result.code==200){
                        console.log(result.result)
                        ModifyData.image=result.result
                    }
                  else {
                        Feng.error(json.message);
                        result;
                    }
                    // var obg=document.getElementById("fileInput")
                }
            })
        }else {
            ModifyData.image=$(".addImages img")[0].src
        }
    }
    else {
        ModifyData.image=""
    }

    if ($(".addImages1>p").text().length>1){
        if ($(".file1")[0].files.length==1){
            $("#index-img2").remove()
            var formData1 = new FormData();
            formData1.append("file",$(".file1")[0].files[0]);
            var temp=$(".file1")[0].files
            console.log(formData1)
            $.ajax({
                async:false,
                type: 'POST',
                url: "/peopleManage/uploadPersonFile",
                data: formData1,
                contentType: "multipart/form-data",
                processData: false, // 告诉jQuery不要去处理发送的数据
                contentType: false,
                success: function(result){
                    console.log(result)
                    if (result.code==200){
                        ModifyData.filename=result.result.filename
                        ModifyData.fileurl=result.result.fileurl
                    } else{
                        Feng.error(json.message);
                        result;
                    }

                }
            })
        }else {
            ModifyData.fileurl=$("#index-img1").data("src")
            ModifyData.filename=$("index-img1").text()
        }
    }
    else {
        ModifyData.filename=""
        ModifyData.fileurl=""
    }
    console.log(ModifyData)
    $.ajax({
        async:false,
        type: 'POST',
        url: "/peopleManage/saveOrUpdateEmployeeInfo",
        data: JSON.stringify(ModifyData),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function (json) {
            console.log(json)
            if(json.code==200){
                Feng.success(json.result)
            }
            else {
                console.log("失败")
            }
        },
        error:function (e) {
            console.log("发送失败")
        }
    })

    // console.log(temp)
    // var formData = ("image", temp);

    $(".file").val("")
    // formData.image=$(".file")[0].files[0]
    $("#index-img1").remove()
    console.log(formData)

    $(".Personnel-details").hide()
}
/**
 * 更新表格数据
 * @param deptId
 */

StaffMager.updateStaffTable = function (deptId) {

    if(deptId){
        var _this = this;
        _this.table.instance.clear();
        $.ajax({
            type: "GET",
            url: "/peopleManage/employeeList/"+deptId,
            async: false,
            dataType: "json",
            success: function (json) {
                if(json.code==200){
                    console.log(123)
                    zheshiid=json.result
                    console.log(zheshiid)
                    _this.table.instance.rows.add(json.result).draw();
                                         //我获取到的id
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

/**
 * 查看照片
 * @param event
 */
StaffMager.checkPic = function (event) {
    var currentTarget = event.currentTarget;
    var data = this.table.instance.row($(currentTarget).closest('tr')).data();
    var callback = function () {

        var $detail = $("#pic-detail"), html = "";
        if(data.cardfronturl){
            console.log(data)
            html = html + '<div class="col-sm-6">' +
                '<div class="thumbnail">' +
                '<img src="'+ data.cardfronturl +'" alt="身份证正面照">' +
                '<div class="caption ellipsis">' +
                '<p>身份证正面照</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        if(data.cardreverseurl){
            html = html + '<div class="col-sm-6">' +
                '<div class="thumbnail">' +
                '<img src="'+ data.cardreverseurl +'" alt="身份证反面照">' +
                '<div class="caption ellipsis">' +
                '<p>身份证反面照</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        $('<div class="row"></div>').html(html).appendTo($detail);
    };
    this.openLayer('身份证照片', '<div class="content" id="pic-detail"></div>', callback);
};

/**
 * 上传照片
 * @param event
 */
StaffMager.uploadPic =function (event) {
    var currentTarget = event.currentTarget;
    var data = this.table.instance.row($(currentTarget).closest('tr')).data();
    var callback = function () {
        var faceImgUpload = new ImageUpload({
            id: '#face-image',
            uploadUrl: '/peopleManage/uploadPersonImage',
            removeUrl: '/resource/deletePersonImage'
        });
        var conImgUpload = new ImageUpload({
            id: '#con-image',
            uploadUrl: '/peopleManage/uploadPersonImage',
            removeUrl: '/resource/deletePersonImage'
        });
    };
    this.openLayer('上传照片', $("#upload-pic").html(), callback);
    if(data.cardfronturl && data.cardfronturl!=""){
        $("#face-image").find('.cancel').css('display', 'block');
        $("#face-image").find('img').attr({
            'src': data.cardfronturl || '../image/upload.png',
            'data-url': data.cardfronturl
        });
    }
    if(data.cardreverseurl && data.cardreverseurl!=""){
        $("#con-image").find('.cancel').css('display', 'block');
        $("#con-image").find('img').attr({
            'src': data.cardreverseurl || '../image/upload.png',
            'data-url': data.cardreverseurl
        });
    }
    this.isEdit = {
        status: true,
        id: data.id
    }
};

StaffMager.savePersonInfo = function () {
    var _this = this, data = new Object();
    var cardfronturl = $("#face-image").find('img').attr('data-url');
    var cardreverseurl = $("#con-image").find('img').attr('data-url');
    if(cardfronturl){
        data.cardfronturl = cardfronturl;
    }
    if(cardreverseurl){
        data.cardreverseurl = cardreverseurl;
    }
    if(_this.isEdit.status){
        data.id = _this.isEdit.id;
    }
    $.ajax({
        url: '/peopleManage/updateemployeeInfo',
        type: 'POST',
        data: data,
        dataType: "json"
    }).then(function (json) {
        if(json.code==200){
            Feng.success("保存成功");
            layer.close(_this.layerIndex);
            _this.updateStaffTable(_this.dept);
        }else{
            Feng.error(json.message);
        }
    }, function (error) {
        Feng.error(error);
    });
};

$(function () {
    var maxHeight = parseInt($('section.content').css('min-height'));
    $(".box").css('min-height', maxHeight-30+'px');
    $('#dept-tree').css({'max-height': maxHeight-140, 'overflow-y': 'auto'});
    $("#staff_table").css('max-height', maxHeight-172+'px');
    StaffMager.loadDept();

    StaffMager.table.instance = $("#"+StaffMager.table.id).DataTable({
        columnDefs: [
            {
                className: "text-center",
                "targets": "_all"
            },
            {
                targets: -1,
                data: null,
                orderable: false
            }
        ],
        columns:StaffMager.table.columns,
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
});
$(function () {
    homePageQueryTitle();
});


//添加个人信息的图片。
var userAgent = navigator.userAgent;
$(".file").change(function () {
    var docObj =$(this)[0];
    var picDiv=$(this).parents(".picDiv");
    //得到所有的图片文件
    var fileList = docObj.files;

    $(".addImages").append("<img id='index-img' />")
    var imgObjPreview=$("#index-img")
    if(userAgent.indexOf('MSIE') == -1){//IE以外浏览器

        imgObjPreview.attr("src",window.URL.createObjectURL(docObj.files[0]));
        $(".text-detail").hide()//获取上传图片文件的物理路径
    }else {//IE浏览器
        if (docObj.value.indexOf(",") != -1) {
            var srcArr = docObj.value.split(",");
            imgObjPreview.attr("src",srcArr[0]);
        } else {
            imgObjPreview.attr("src",docObj.value)
        }
    }
    $(".deleteimgs").show()
    $(".examine").show()
});
var deleteimgs={
    fileType:"",
    outletid:"",
    date:"",
    filename:""
}

//录入删除的图片

$(".deleteimgs").click(function () {
    Feng.confirm("是否删除图片？", function () {
        if ($(".file")[0].files.length==1) {
            $("#index-img").remove()
            $(".text-detail").show()
            $(".file").val("")
            $(".deleteimgs").hide()
            $(".examine").hide()
            $(".addImages img").remove()
        }else {
            var images=$(".addImages img")[0].src
            console.log(images)
            images=images.split("resource/")
            console.log(images[1].split("/"))
            var DeleteThePicture=images[1].split("/")
            deleteimgs.fileType=DeleteThePicture[0]
            deleteimgs.outletid=DeleteThePicture[1]
            deleteimgs.fileType=DeleteThePicture[2]
            deleteimgs.fileType=DeleteThePicture[3]
            $.ajax({
                url: '/resource/deletePersonImage',
                type: 'get',
                data: deleteimgs,
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success:function (json) {
                    Feng.success(json.result)
                    $("#index-img").remove()
                    $(".text-detail").show()
                    $(".file").val("")
                    $(".addImages img").remove()
                    $(".deleteimgs").hide()
                    $(".examine").hide()
                }
            })
        }
    })
})


//查看图片
$(".examines").on("click",function(){
    if ($(".addImages img").length==1){
        layer.open({
            type:1,
            title: "查看图片",
            content: $('#teb-imgs').html(),
            area:["1000px","800px"],
        })
        var imageTeb=$(".addImages img")[0].src
        $(".img-tebs").attr("src",imageTeb).width("100%").height("700px")
    }else {
        return
    }

})
$(".examines1").on("click",function(){
    
        layer.open({
            type:1,
            title: "查看图片",
            content: $('#teb-imgs1').html(),
            area:["900px","800px"],
        })
        var wordsrc=$("#index-img1").data("src")
        $(".img-tebs1").attr("src",wordsrc).width("860px").height("600px")
 

})




//添加文档。
// var userAgent = navigator.userAgent;
$(".file1").change(function () {
$("#index-img1").remove()
    var docObj =$(this)[0];
    //得到所有的图片文件
    var fileformat=".doc"
    var fileList = docObj.files;

    if (fileList[0].name.indexOf(fileformat)!=-1){
        $(".addImages1").append("<p id='index-img2' style='line-height: 100px;'>"+fileList[0].name+"</p>")
        $(".text-detail1").hide()
        $(".deleteimgs1").show()
    }else {
        Feng.error("文件类型错误");
        $(this).val("")
    }
});

$(".deleteimgs1").click(function (){
    Feng.confirm("是否删除文件？", function () {
        if($(".file1")[0].files.length==1){
            $("#index-img1").remove()
            $("#index-img2").remove()
            $(".text-detail1").show()
            $(".file1").val("")
            $(".file1").show()
            $(".deleteimgs1").hide()
            $(".examines1").hide()
        }else {
            var words=$("#index-img1").data("src")
            words=words.split("resource/")
            console.log(words[1].split("/"))
            var DeleteThePicture=words[1].split("/")
            deleteimgs.fileType=DeleteThePicture[0]
            deleteimgs.outletid=DeleteThePicture[1]
            deleteimgs.fileType=DeleteThePicture[2]
            deleteimgs.fileType=DeleteThePicture[3]
            $.ajax({
                url: '/resource/deletePersonFile',
                type: 'get',
                data: deleteimgs,
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success:function (json) {
                    if(json.code==200){
                        Feng.success(json.result)
                        $(".examines1").hide()
                        $("#index-img1").remove()
                        $(".text-detail1").show()
                        $(".file1").val("")
                        $(".file1").show()
                        $(".deleteimgs1").hide()

                    }else {
                        Feng.error("删除失败");
                        return;
                    }

                }
            })
        }

    })
})
