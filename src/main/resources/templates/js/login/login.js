var validateFields = {
    monitorName: {
        message: '请输入运营中心名称',
        validators: {
            notEmpty: {
                message: '请输入运营中心名称'
            }
        }
    },
    userName: {
        message: '请输入账号',
        validators: {
            notEmpty: {
                message: '请输入账号'
            }
        }
    },
    passWord: {
        message: '请输入密码',
            validators: {
            notEmpty: {
                message: '请输入密码'
            },
            regexp: {
                regexp: /^[0-9a-zA-Z_]{1,}$/,
                    message: '密码只能由字母、数字和下划线组成'
            }
        }
    }
};

/**
 * 验证表单
 */
var validate = function (fields) {
    var $form = $('#loginForm');
    if(!$form.data('bootstrapValidator')){
        $form.bootstrapValidator({
            // message: 'This value is not valid',
            //excluded:[":hidden",":disabled",":not(visible)"] ,//bootstrapValidator的默认配置
            // excluded: [':disabled', ':hidden', ':not(:visible)'],//关键配置，表示只对于禁用域不进行验证，其他的表单元素都要验证
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',//显示验证成功或者失败时的一个小图标
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields:  validateFields
        });
    }

    var bootstrapValidator = $form.data('bootstrapValidator');
    $form.bootstrapValidator('validate');
    return bootstrapValidator.isValid();
};

var mytime = function(){
    var date = new Date();
    var year = date.getFullYear();
    var month =date.getMonth()+1;
    var day = date.getDate();
    var week = ' 星期'+'日一二三四五六'.charAt(date.getDay()) ;
    var hour = date.getHours();
    if(hour<10){
        hour = '0' + hour;
    }
    var minute = date.getMinutes();
    if(minute<10){
        minute = '0' + minute;
    }
    var seconds = date.getSeconds();
    if(seconds<10){
        seconds = '0' + seconds;
    }
    $(".time").text(hour+":"+minute+":"+seconds);
    $(".week").text(week);
    $(".date").text(year+'/'+month+'/'+day);
};
setInterval(mytime,1000);


$("#login").click(function (event) {
    $(this).blur();
    if(!validate()){
        return;
    }
    var formInfo = $("#loginForm").serializeArray();
    var loginInfo = new Object();
    $.each(formInfo, function (i, item) {
        loginInfo[item.name] = item.value;
    });
    $.ajax({
        url: baseURL + '/login',
        type: 'POST',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(loginInfo),
        dataType: 'json',
        success: function (json) {
            if(json.code==200){
                setCookie("user.name", loginInfo.userName, 0.5);
                setCookie("user.cookie", json.result, 0.5);
                console.log(json.result)
                // return;
                location.href = './index.html';
            }else{
                $(".login-error").text(json.message).show();
                var timeout = setTimeout(function () {
                    $(".login-error").fadeOut();
                    clearTimeout(timeout);
                }, 3000);
            }
        },
        error: function (err) {
            alert(JSON.stringify(err));
        }
    });
});

document.onkeydown = function (event) {
    var e = event || window.event;
    if (e && e.keyCode == 13) { //回车键的键值为13
        $("#login").click().trigger("change");
    }
};

$(function () {
    mytime();
});