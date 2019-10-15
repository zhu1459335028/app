function OCX () {

}

OCX.prototype = {
    isIE: function () {
        if(navigator.userAgent.indexOf("MSIE") > 0 || (navigator.userAgent.toLowerCase().indexOf("trident") > -1 && navigator.userAgent.indexOf("rv") > -1)) {
            return true;
        }
        return false;
    },
    isFirefox: function () {
        if(navigator.userAgent.indexOf("Firefox") > 0) {
            return true;
        }
        return false;
    },
    isChrome: function() {
        if(navigator.userAgent.indexOf("Chrome") > 0) {
            return true;
        }
        return false;
    },
    detectPluginObject: function () {
        var isExist = false;
        if(this.isIE()) {
            try {
                if(document.all.ocxObject.object == null) {
                    return false;
                }else{
                    return true;
                }
            } catch(e) {
                return false;
            }
        } else if(this.isFirefox() || this.isChrome()) {
            $.each(navigator.plugins, function (i, item) {
                if(item.name.indexOf('NGSWebPlugin')!=-1){
                    isExist = true;
                    return false;
                }
            });
            return isExist;
        }
    },
    createPluginObject: function (targetEle, ocxId){
        ocxId = ocxId? ocxId: 'ocxObject';
        if(this.isIE()) {
            targetEle.prepend('<object id="'+ocxId+'" classid="clsid:CD31B02B-D618-410D-8D7A-218E8653B788" style="width:100%;height:100%;border: none;"></object>');
        } else {
            targetEle.prepend('<embed id="'+ocxId+'" type="application/NGSWebPlugin" style="width:100%;height:100%;"></embed>');
        }
    }
};


