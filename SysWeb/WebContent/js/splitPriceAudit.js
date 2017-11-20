  

$(function(){
	 
	/* {{{ start of build simple grid {{{ */
	 
	 
	 

	$("#originalSearchOrgGroupListGrid").jqGrid({
	   	url:"orgGroup_searchWithAllNetGroup.action?type=start&pageCache=true",
		datatype: "json",		 
		mtype: "POST",
		sortable: true, 
		rownumbers: true,
	   	colNames:[ '代 码', '名 称' ],
	   	colModel:[ 			  
				    {name : 'code',index : 'code',width : '20%'},
				    {name : 'name',index : 'name',width : '20%'}					   				  
		],
	   	rowNum:10,
	   	rowList:[10,50,100],
	   	//导航条对象
	   	pager: "#originalSearchOrgGroupListPager",
	   	//默认的排序字段名称
	   	sortname: "code",
	    //默认的排序方式,可选值asc或desc
	   	sortorder: "asc",
	   	//自动宽度
	   	/*autowidth:true,*/
	   	//高度
	   	height: 'auto',
	   	width: 480,
	   	//是否多选
	   	multiselect:false,
	   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
	   	multiboxonly:false,
	   	//是否隐藏
	   	hidegrid: false,
	   	//显示斑马线
		altRows:true,
		altclass:'altClass',
	   
	   	//是否看到总条目
	    viewrecords: true,
	    //设置编辑的URL地址，此处只用于删除
	    editurl:null, 
	    //
	    //定义json数据的格式描述信息
	    jsonReader: {
      		root: "rows",
      		page: "page",
     		total: "total",
     		records: "records", 
        	repeatitems : false
     	},
     	loadError : function(xhr,st,err) {
     		$.boxUtil.alert('err:' + err);
        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
    	},
//    	onPaging : function(pgButton){
//    		 
//    	},
    	//标题
	     
	   
    	ondblClickRow: function(id, e){
			if(id != null){
				$("#searchStartOrgGroupName").val($("#originalSearchOrgGroupListGrid").jqGrid("getRowData", id).name);
				$("#searchStartOrgGroupId").val(id);
			}
    		//$("#originalSearchOrgGroupListGrid").dialog("close");
			$("#originalSearchGroupBrowser").dialog("close");
    	},
	    
	    //加载结束后处理,处理session过期
	    loadComplete: function(data){
    		if(data.success=="false"){
    			$.boxUtil.alert(data.text);
    		}	
    	}
	});
 
	// 添加分页导航条
	$("#originalSearchOrgGroupListGrid").jqGrid("navGrid","#originalSearchOrgGroupListPager",{edit:false,add:false,del:false,search:false,refresh:true});
	//排序
	$("#originalSearchOrgGroupListGrid").jqGrid('sortableRows'); 
	
 
	/* }}} end of build simple grid }}} */	
	
	/* {{{ start of build simple grid {{{ */
	 
	$("#destSearchOrgGroupListGrid").jqGrid({
	   	url:"orgGroup_searchWithAllNetGroup.action?type=end&pageCache=true"  ,
		datatype: "json", 
		mtype: "POST",
		sortable: true, 
		rownumbers: true,
	   	colNames:[ '代 码', '名 称' ],
	   	colModel:[ 			  
				    {name : 'code',index : 'code',width : '20%'},
				    {name : 'name',index : 'name',width : '20%'}					   				  
		],
	   	rowNum:10,
	   	rowList:[10,50,100],
	   	//导航条对象
	   	pager: "#destSearchOrgGroupListPager",
	   	//默认的排序字段名称
	   	sortname: "code",
	    //默认的排序方式,可选值asc或desc
	   	sortorder: "asc",
	   	//自动宽度
	   	/*autowidth:true,*/
	   	//高度
	   	height: 'auto',
	   	width: 480,
	   	//是否多选
	   	multiselect:false,
	   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
	   	multiboxonly:false,
	   	//是否隐藏
	   	hidegrid: false,
	   	//显示斑马线
		altRows:true,
		altclass:'altClass',
	    
	   	//是否看到总条目
	    viewrecords: true,
	    //设置编辑的URL地址，此处只用于删除
	    editurl:null, 
	    //
	    //定义json数据的格式描述信息
	    jsonReader: {
      		root: "rows",
      		page: "page",
     		total: "total",
     		records: "records", 
        	repeatitems : false
     	},
     	loadError : function(xhr,st,err) {
     		$.boxUtil.alert('err:' + err);
        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
    	},
//    	onPaging : function(pgButton){
//    		$(searchButtonVar).click();
//    	},
    	//标题
	   
	    
    	ondblClickRow: function(id, e){
			if(id != null){
				$("#searchEndOrgGroupName").val($("#destSearchOrgGroupListGrid").jqGrid("getRowData", id).name);
				$("#searchEndOrgGroupId").val(id);
			}
    		//$("#originalSearchOrgGroupListGrid").dialog("close");
			$("#destSearchGroupBrowser").dialog("close");
    	},
	    
	    //加载结束后处理,处理session过期
	    loadComplete: function(data){
    		if(data.success=="false"){
    			$.boxUtil.alert(data.text);
    		}	
    	}
	});
	$("#toolbar").appendTo($("#t_destSearchOrgGroupListGrid"));
	// 添加分页导航条
	$("#destSearchOrgGroupListGrid").jqGrid("navGrid","#destSearchOrgGroupListPager",{edit:false,add:false,del:false,search:false,refresh:true});
	//排序
	$("#destSearchOrgGroupListGrid").jqGrid('sortableRows'); 
	
	// 查询 ajax
	 
	/* }}} end of build simple grid }}} */
});
	 
	 
	/**
	 * price-detail.js
	 *
	 * 价格详细配置处理脚本
	 *
	 */
	$(function(){
		
		var pdTreeStartIdPrefix = "pdTreeStart-";
		var pdTreeDestIdPrefix = "pdTreeDest-";

		/**
		 * 浏览起始网点组的弹出对话框
		 */
 
		
		/**
		 * 浏览网点组的弹出对话框
		 */
		 
		
		/**
		 * 搜索起始网点组的弹出对话框
		 */
		$(function(){
			$("#originalSearchGroupBrowser").dialog({
				autoOpen: false,
				bgiframe: true,
				height: 450,
				width: 504,
				modal: true,
				buttons: {
					"确定": function() { 
						$(this).dialog("close");
						var id = $("#originalSearchOrgGroupListGrid").jqGrid('getGridParam','selrow');
						if(id != null){
							$("#searchStartOrgGroupName").val($("#originalSearchOrgGroupListGrid").jqGrid("getRowData", id).name);
							$("#searchStartOrgGroupId").val(id);
						}
					}, 
					"取消": function() {
						$(this).dialog("close"); 
					}
				}
			});
		});
		
		/**
		 * 搜索目的网点组的弹出对话框
		 */
		$(function(){
			$("#destSearchGroupBrowser").dialog({
				autoOpen: false,
				bgiframe: true,
				height: 450,
				width: 504,
				modal: true,
				buttons: {
					"确定": function() { 
						$(this).dialog("close");
						var id = $("#destSearchOrgGroupListGrid").jqGrid('getGridParam', 'selrow');
						//$.boxUtil.alert(ids);
						if(id != null){
							$("#searchEndOrgGroupName").val($("#destSearchOrgGroupListGrid").jqGrid("getRowData", id).name);
							$("#searchEndOrgGroupId").val(id);
						}
					}, 
					"取消": function() {
						$(this).dialog("close"); 
					}
				}
			});
		});
  
	 
	});

  

	
	/**
	 * 按钮事件：弹出搜索起始网点组对话框
	 */
	function browseSearchStartGroup(){
		var pricetype=$("#searchPriceType").attr('value');
		if(pricetype==null||pricetype=="")
		{
			   $.boxUtil.warn("请先选择价格类型", {title: '提示信息'}, function(){							 
				});
			   
			 
			return;
		}
		
		$("#originalSearchOrgGroupListGrid").jqGrid("setGridParam",{	url:"orgGroup_searchWithAllNetGroup.action?type=start&priceType="+$("#searchPriceType").attr('value')});
		$("#originalSearchGroupBrowser").dialog('option', 'target', 'start');
		$("#originalSearchOrgGroupListGrid").trigger("reloadGrid");
		$("#originalSearchGroupBrowser").dialog('open');
	}

	/**
	 * 按钮事件：弹出搜索目的网点组对话框
	 */
	function browseSearchEndGroup(){
		var pricetype=$("#searchPriceType").attr('value');
		if(pricetype==null||pricetype=="")
		{
			   $.boxUtil.warn("请先选择价格类型", {title: '提示信息'}, function(){							 
				});
			return;
		}
		$("#destSearchOrgGroupListGrid").jqGrid("setGridParam",{	url:"orgGroup_searchWithAllNetGroup.action?type=end&priceType="+$("#searchPriceType").attr('value')});
		$("#destSearchGroupBrowser").dialog('option', 'target', 'dest');
		$("#destSearchOrgGroupListGrid").trigger("reloadGrid");
		$("#destSearchGroupBrowser").dialog('open');
	}
	
	 
 
