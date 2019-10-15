/**
 * ztree插件的封装
 */
(function() {

    var hiddenNodes = []; //用于存储被隐藏的结点
	var $ZTree = function(id, url) {
		this.instance = null;
		this.id = id;
		this.url = url;
		this.onClick = null;
		this.settings = null;
		this.ondblclick = null;
		this.hiddenNodes = [];
	};

	$ZTree.prototype = {
		/**
		 * 初始化ztree的设置
		 */
		initSetting : function() {
			var settings = {
				view : {
                    showLine: false,
					dblClickExpand : true,
					selectedMulti : false
				},
				data : {simpleData : {enable : true}},
                edit : { drag : {isCopy : false, isMove : false}},
				callback : {
					onClick : this.onClick,
					onDblClick:this.ondblclick
				}
			};
			return settings;
		},

		/**
		 * 手动设置ztree的设置
		 */
		setSettings : function(val) {
			this.settings = val;
		},

		/**
		 * 初始化ztree
		 */
		init : function() {
			var zNodeSeting = null;
			if(this.settings != null){
				zNodeSeting = this.settings;
			}else{
				zNodeSeting = this.initSetting();
			}
			var zNodes = this.loadNodes();
            this.instance = $.fn.zTree.init($("#" + this.id), zNodeSeting, zNodes);
            return this.instance;
		},
        /**
         * 初始化本地ztree
         */
		initLocal : function (zNodes) {
            var zNodeSeting = null;
            if(this.settings != null){
                zNodeSeting = this.settings;
            }else{
                zNodeSeting = this.initSetting();
            }
            this.instance = $.fn.zTree.init($("#" + this.id), zNodeSeting, zNodes);
            return this.instance;
        },
		/**
		 * 绑定onclick事件
		 */
		bindOnClick : function(func) {
			this.onClick = func;
		},
		/**
		 * 绑定双击事件
		 */
		bindOnDblClick : function(func) {
			this.ondblclick=func;
		},


		/**
		 * 加载节点
		 */
		loadNodes : function() {
			var zNodes = null;
			var ajax = new $ax(Feng.ctxPath + this.url, function(data) {
				zNodes = data;
			}, function(data) {
				Feng.error("加载ztree信息失败!");
			});
			ajax.start();
			return zNodes;
		},

		/**
		 * 获取选中的值
		 */
		getSelectedVal : function(){
			var zTree = $.fn.zTree.getZTreeObj(this.id);
			var nodes = zTree.getSelectedNodes();
			return nodes[0].name;
		},
        /**
         * ztree节点过滤
         */
		filter: function (ztreeObj, _keyword, filter) {
			//显示上次搜索后背隐藏的结点
            ztreeObj.showNodes(hiddenNodes);
            //查找不符合条件的叶子节点(层级为1，且名字不包含自定字段的节点)
			var parents = [];
            var _concat = function(arr1,arr2) {
                //不要直接使用var arr = arr1，这样arr只是arr1的一个引用，两者的修改会互相影响
                var arr = arr1.concat();
                //或者使用slice()复制，var arr = arr1.slice(0)
                for (var i = 0; i < arr2.length; i++) {
                    arr.indexOf(arr2[i]) === -1 ? arr.push(arr2[i]) : 0;
                }
                parents = arr;
            };

            var filterFunc = function (node){
                if(node.name.indexOf(_keyword)>=0){
                    _concat(parents, node.getPath());
                    return false;
                }else{
                    return true;
                }
            };
            filter = filter?filter:filterFunc;
            //获取不符合条件的叶子结点
			if(_keyword){
                hiddenNodes = ztreeObj.getNodesByFilter(filter);
                $.each(parents, function (i, item) {
                	for(var x = 0;x<hiddenNodes.length;x++){
                		if(item.id===hiddenNodes[x].id){
                			hiddenNodes.splice(x,1);
						}
					}
                });
                //隐藏不符合条件的叶子结点
                ztreeObj.hideNodes(hiddenNodes);
                ztreeObj.expandAll(true);
			}else{
                hiddenNodes = [];
                ztreeObj.expandAll(false);
			}

        }
	};

	window.$ZTree = $ZTree;

}());