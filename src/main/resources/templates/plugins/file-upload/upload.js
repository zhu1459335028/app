function DragImgUpload(id,options){
    this.me = $(id);
    var defaultOpt = {
        boxWidth:  '200px',
        boxHeight:'auto'
    };
    this.preview = $('<div id="preview">' +
                        '<img src="image/upload.png" class="img-responsive"  style="width: 100%;height: auto;" alt="上传图片" title="">' +
                    '</div>');
    this.opts = $.extend(true, defaultOpt, {}, options);
    this.init();
    this.addCallback = this.opts.addCallback;
    this.removeCallback = this.opts.removeCallback;
}
DragImgUpload.prototype = {
    init: function(){
        // this.me.append(this.preview);
        // this.me.append(this.fileupload);
        this.cssInit();
        this.eventClickInit();
    },
    cssInit: function(){
        this.me.css({'width':this.opts.boxWidth,'height':this.opts.boxHeight,'border':'1px solid #cccccc','padding':'10px','cursor':'pointer'});
        this.preview.css({'height':'100%','overflow':'hidden'})
    },
    onDragover: function(e){
        e.stopPropagation();
        e.preventDefault();
        e.dataTransfer.dropEffect='copy';
    },
    onDrop: function(e){
        var self=this;
        e.stopPropagation();
        e.preventDefault();
        var fileList = e.dataTransfer.files;
        if(fileList.length==0){
            return false;
        }
        if(fileList[0].type.indexOf('image')===-1){
            alert("您拖的不是图片！");
            return false;
        }
        var img = window.URL.createObjectURL(fileList[0]);
        var filename = fileList[0].name;
        var filesize = Math.floor((fileList[0].size)/1024);
        if(filesize>500){
            alert("上传大小不能超过500K.");
            return false;
        }
        self.me.find("img").attr("src",img);
        self.me.find("img").attr("title",filename);
        if(this.addCallback){
            this.addCallback(fileList, this.me);
        }
    },
    eventClickInit: function(){
        var self = this;
        this.me.unbind()
            .on('click', 'img', function(){
                self.createImageUploadDialog();
            })
            .on('click', 'i.cancel', function () {
                self.removeCallback(self.me);
            });
        var dp = this.me[0];
        dp.addEventListener('dragover',function(e){
            self.onDragover(e);
        });
        dp.addEventListener("drop",function(e){
            self.onDrop(e);
        });
    },
    onChangeUploadFile: function(){
        var fileInput = this.fileInput;
        var files = fileInput.files;
        // var file = files[0];
        // var img = window.URL.createObjectURL(file);
        // var filename = file.name;
        // this.me.find("img").attr("src",img);
        // this.me.find("img").attr("title",filename);
        if(this.addCallback){
            this.addCallback(files, this.me);
        }
    },
    createImageUploadDialog: function(){
        this.fileInput = this.me.find('input')[0];
        // var fileInput = this.fileInput;
        // if(!fileInput){
        //     fileInput = document.createElement('input');
        //     fileInput.type = 'file';
        //     fileInput.name = 'ime-images';
        //     fileInput.multiple = true;
        //     fileInput.onchange = this.onChangeUploadFile.bind(this);
        //     this.fileInput = fileInput;
        // }
        if(this.fileInput){
            this.fileInput.onchange = this.onChangeUploadFile.bind(this);
            this.fileInput.click();
        }
    }
};