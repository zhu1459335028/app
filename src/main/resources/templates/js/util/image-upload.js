function ImageUpload(options) {
    var defaultOpt = {

    };
    this.opt = $.extend(true, defaultOpt, options);
    return this.initUploader();
}

ImageUpload.prototype = {
    initUploader: function () {
        var _this = this;
        return new DragImgUpload(_this.opt.id,{
                addCallback:function (files, me) {
                    _this.success(files, me);
                },
                removeCallback: function (me) {
                    _this.cancel(me);
                }
            });
    },
    success: function (files, me) {
        if(me.find('img').attr('data-url')){
            this.cancel(me);
        }
        var file = files[0];
        var formData = new FormData(), img = window.URL.createObjectURL(file);
        formData.append("image", file);
        $.ajax({
            url: this.opt.uploadUrl,
            type: "POST",
            data: formData,
            contentType: false,
            processData: false
        }).then(function (json) {
            if(json.code==200){
                me.find('img').attr({
                    'src': img,
                    'data-url': json.result
                });
                me.find('.cancel').css('display', 'block');
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    },
    cancel: function (me) {
        var dataUrl = me.find('img').data('url');
        dataUrl = dataUrl.split('/');
        $.ajax({
            url: this.opt.removeUrl,
            type: "GET",
            async: false,
            data: {fileType: dataUrl[2], outletid: dataUrl[3], date: dataUrl[4], filename: dataUrl[5]},
            dataType: 'json'
        }).then(function (json) {
            if(json.code==200){
                me.find('.cancel').css('display', 'none');
                me.find('img').attr('src', '../image/upload.png').removeAttr('data-url');
            }else{
                Feng.error(json.message);
            }
        }, function (error) {
            Feng.error(error);
        });
    }
};
