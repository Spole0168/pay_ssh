/**
 * 包含简单表格的JavaScript 
 */



$(function(){
	var searchParameters = ["pageCache"];
	var pageCacheVar = "false";
	var searchButtonVar;
	if($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
		pageCacheVar = "true";
	}
	$("#customerGroupListGrid").jqGrid({
	   	url:"customerGroup_search.action?pageCache=" + pageCacheVar,
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
	   	pager: "#customerGroupListPager",
	   	sortname: "code",
	   	sortorder: "asc",
	   	height: 'auto',
	   	width: 480,
	   	multiselect:true,
		altRows:true,
		altclass:'altClass',
	   	toolbar: [true,"top"],
	    viewrecords: true,
	    editurl:null, 
	    jsonReader: {
      		root: "rows",
      		page: "page",
     		total: "total",
     		records: "records", 
        	repeatitems : false
     	},
    	onPaging : function(pgButton){
    		$(searchButtonVar).click();
    	},
	    caption:"客户组列表"
	});
	$("#toolbar_customer").appendTo($("#t_customerGroupListGrid"));
	$("#customerGroupListGrid").jqGrid("navGrid","#customerGroupListPager",{edit:false,add:false,del:false,search:false,refresh:true});
	// 查询 ajax
	$(searchButtonVar).click(function(){
		var url = "customerGroup_search.action?rowNum=" + $("#customerGroupListGrid").jqGrid('getGridParam','rowNum');
		var postData = new Object();
		for(var i = 0 ; i < searchParameters.length; i++) {
			var name = searchParameters[i];
			var currentValue = $("#" + searchParameters[i]).attr('value');
		    eval("postData."+name+"=currentValue");
		}
		$("#customerGroupListGrid").appendPostData(postData);
		$("#customergGroupListGrid").jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
		// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
		$("#customerGroupListGrid").jqGrid("setGridParam",{page:1});
	});
	/* {{{ start of build simple grid {{{ */
	var searchParameters = ["pageCache"];
	var pageCacheVar = "false";
	var searchButtonVar;
	if($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
		pageCacheVar = "true";
	}
	$("#orgGroupListGrid").jqGrid({
	   	url:"orgGroup_search.action?pageCache=" + pageCacheVar,
		datatype: "json",
		/*datatype:'local',
		data:[
				{
					code:"1", 
					name:"测试一"
				},
				{
					code:"3", 
					name:"测试三"
				}
			],*/
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
	   	pager: "#orgGroupListPager",
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
	   	multiselect:true,
	   	//multiboxonly设为ture后，点击其它没有被选中的行，那么点中的行被选择，其它行取消选择
	   	multiboxonly:false,
	   	//是否隐藏
	   	hidegrid: false,
	   	//显示斑马线
		altRows:true,
		altclass:'altClass',
	   	//添加Toolbar
	   	toolbar: [true,"top"],
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
     		alert('err:' + err);
        	$("#tblMasterMessage").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
    	},
    	onPaging : function(pgButton){
    		$(searchButtonVar).click();
    	},
    	//标题
	    caption:"网点组列表",
	    
	    //加载结束后处理,处理session过期
	    loadComplete: function(data){
    		if(data.success=="false"){
    			alert(data.text);
    		}	
    	}
	});
	$("#toolbar").appendTo($("#t_orgGroupListGrid"));
	// 添加分页导航条
	$("#orgGroupListGrid").jqGrid("navGrid","#orgGroupListPager",{edit:false,add:false,del:false,search:false,refresh:true});
	//排序
	$("#orgGroupListGrid").jqGrid('sortableRows'); 
	
	// 查询 ajax
	$(searchButtonVar).click(function(){
		var url = "orgGroup_search.action?rowNum=" + $("#orgGroupListGrid").jqGrid('getGridParam','rowNum');
    	
		var postData = new Object();
		for(var i = 0 ; i < searchParameters.length; i++) {
			var name = searchParameters[i];
			var currentValue = $("#" + searchParameters[i]).attr('value');
			
		    eval("postData."+name+"=currentValue");
		}
		$("#orgGroupListGrid").appendPostData(postData);
		
		$("#orgGroupListGrid").jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
		// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
		$("#orgGroupListGrid").jqGrid("setGridParam",{page:1});
	});
	/* }}} end of build simple grid }}} */
});
	

	$(function(){
		// sub form toggle
		$( function() {
		$(".legend_title a").toggle(function(){
					$(this).parent().next(".fieldset_container:first").hide();
					$(this).removeClass("container_show");
					$(this).addClass("container_hide");
				},function(){
					$(this).parent().next(".fieldset_container:first").show();
					$(this).removeClass("container_hide");
					$(this).addClass("container_show");
			});
		});
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
		 * 浏览网点组的弹出对话框
		 */
		$(function(){
			$("#customerGroupBrowser").dialog({
				autoOpen: false,
				bgiframe: true,
				height: 450,
				width: 504,
				modal: true,
				buttons: {
					"Ok": function() { 
						$(this).dialog("close");
						var ids = $("#customerGroupListGrid").jqGrid('getGridParam', 'selarrrow');
						if(ids != null){
							var grid = $("#gridCustomerGroup");
							for(var i = 0; i < ids.length; i++){
								var id = ids[i];
								var code = $("#customerGroupListGrid").jqGrid("getRowData", id).code;
								var name = $("#customerGroupListGrid").jqGrid("getRowData", id).name;
								if(grid.jqGrid('getRowData', id).code == null)
									grid.jqGrid('addRowData', id, {code:code, name:name});
							}
						}
					}, 
					"Cancel": function() {
						$(this).dialog("close"); 
					}
				}
			});
		});
		
		/**
		 * 浏览网点组的弹出对话框
		 */
		$(function(){
			$("#groupBrowser").dialog({
				autoOpen: false,
				bgiframe: true,
				height: 450,
				width: 504,
				modal: true,
				buttons: {
					"Ok": function() { 
						$(this).dialog("close");
						var ids = $("#orgGroupListGrid").jqGrid('getGridParam', 'selarrrow');
						if(ids != null){
							var target = $("#groupBrowser").dialog("option", "target");
							var grid = null;
							if(target == "customer"){
								grid = $("#gridCustomerGroup");
							} else {
								grid = $("#gridDestGroup");
							}
							for(var i = 0; i < ids.length; i++){
								var id = ids[i];
								var code = $("#orgGroupListGrid").jqGrid("getRowData", id).code;
								var name = $("#orgGroupListGrid").jqGrid("getRowData", id).name;
								if(grid.jqGrid('getRowData', id).code == null)
									grid.jqGrid('addRowData', id, {code:code, name:name});
							}
						}
					}, 
					"Cancel": function() {
						$(this).dialog("close"); 
					}
				}
			});
		});

		/**
		 * 起始网点组列表
		 */
		$(function(){
			$("#gridCustomerGroup").jqGrid({
				//url: 'price-info.js',
				datatype: 'local',
				colNames: ['组编号', '组名称'],
				colModel: [
					{name:'code', index:'code', width:50},
					{name:'name', index:'name', width:100}
				],
				/*
				rowNum: 10,
				rowList: [10, 20, 30],
				pager: '#gridCustomerGroupPager',
				*/
				sortname: 'code',
				multiselect: true,
				viewrecords: true,
				sortorder: 'asc',
				caption: '客户信息',
				width: 198,
				toolbar: [true, "top"],

				/**
				 * 点选行（可能选中，也可能取消）事件处理。
				 * 此处实现联动，列出选中组下面的网点。
				 */
				onSelectRow: function(id){
					var ids = $("#gridCustomerGroup").jqGrid('getGridParam', 'selarrrow');
					showCustomers($("#gridCustomer"), ids);
				}
			});
			//$("#gridCustomerGroup").jqGrid('navGrid', '#gridCustomerGroupPager', {edit: false, add: false, del: false});
		});
		$("#gridCustomerGroupToolbar").appendTo($("#t_gridCustomerGroup"));

		/**
		 * 起始网点列表
		 */
		$(function(){
			$("#gridCustomer").jqGrid({
				url: 'about:blank',
				datatype: 'json',
				colNames: ['客户编码', '客户名称'],
				colModel: [
					{name:'code', index:'code', width:50},
					{name:'name', index:'name', width:100}
				],
			    //定义json数据的格式描述信息
			    jsonReader: {
		      		root: "rows",
		      		page: "page",
		     		total: "total",
		     		records: "records", 
		        	repeatitems : false
		     	},
				/*
				rowNum: 10,
				rowList: [10, 20, 30],
				pager: '#gridCustomerPager',
				*/
				sortname: 'code',
				multiselect: false,
				viewrecords: true,
				sortorder: 'asc',
				caption: '网点信息',
				width: 198
			});
			//$("#gridCustomer").jqGrid('navGrid', '#gridCustomerPager', {edit: false, add: false, del: false});
		});
			
		/* price detail list */
		$(function(){
			$("#gridPriceDetail").jqGrid({
				url: 'departmentFreightDetail_search.action?freightId='+$("#freightId").attr('value'),
				editurl: 'departmentFreightDetail_saveOrUpdate.action',
				/*data:[
		{
			id:"1", 
			customerGroup:"测试一",
			destGroup:"测试目的组",
			fixedPrice:"",
			priceRule:"",
			calcType:"",
			firstPartPrice:"",
			firstPartWeight:"",
			otherPartPrice:"",
			otherPartInc:"",
			calcParam:""
		},
		{
			id:"3", 
			customerGroup:"测试3",
			destGroup:"测试目的组2",
			fixedPrice:"5",
			priceRule:"",
			calcType:"",
			firstPartPrice:"3",
			firstPartWeight:"1",
			otherPartPrice:"",
			otherPartInc:"",
			calcParam:""
		}
	],*/		
				shrinkToFit: false,
				datatype: 'json',
				colNames: [ '发出客户组', '目的网点组', '状态','固定价格', '价格规则', '计价类型', '首重价格', '首重重量', '续重价格', '续重步进重量', '计价参数'],
				colModel: [

					{name:'fromCustomerGroup.name', index:'fromCustomerGroup.name', width:100},
					{name:'toOrgGroup.name', index:'toOrgGroup.name', width:100},
					{name:'status', index:'status', width:100, formatter:statusFormatter},
					{name:'fixPrice', index:'fixPrice', width:100, editable: true },
					{name:'priceRule.name', index:'priceRule.name', width:100, editable: true, edittype:'select', editoptions:{value:selpriceRuleOpts}},
					{name:'priceType.name', index:'priceType.name', width:100, editable: true, edittype:'select', editoptions:{value:selpriceTypeOpts}},
					{name:'firstWeightPrice', index:'firstWeightPrice', width:100, editable: true},
					{name:'firstWeight', index:'firstWeight', width:100, editable: true},
					{name:'addWeightPrice', index:'addWeightPrice', width:100, editable: true},
					{name:'addWeightStep', index:'addWeightStep', width:100, editable: true},
					{name:'parm1', index:'parm1', width:100, editable: true}
				],
				rowNum: 10,
				rowList: [10, 20, 30],
				pager: '#gridPriceDetailPager',
				//sortname: 'id',
				multiselect: true,
				viewrecords: true,
				sortorder: 'asc',
				caption: '已生成价格明细列表信息',
				width: 576,
				height: 'auto',
				toolbar: [true, "top"],

				/**
				 * 本事件处理实现单击行切换到编辑状态。
				 * 'clientArray'参数指明修改的数据保存到客户端数组里（点“保存”时批量提交）。
				 */
				onSelectRow: function(id){
					if(id && id!==window['price_detail_lastsel']){
						$('#gridPriceDetail').jqGrid('saveRow', window['price_detail_lastsel']);
						$('#gridPriceDetail').jqGrid('editRow',id,true);
						window['price_detail_lastsel'] = id;
					} else {
						$('#gridPriceDetail').jqGrid('saveRow', id);
						window['price_detail_lastsel'] = null;
					}
				},
			
				jsonReader: {
		      		root: "rows",
		      		page: "page",
		     		total: "total",
		     		records: "records", 
		        	repeatitems : false
		     	}
			});
			$("#gridPriceDetail").jqGrid('navGrid', '#gridPriceDetailPager', {edit: false, add: false, del: false});
			$("#gridPriceDetailToolbar").appendTo($("#t_gridPriceDetail"));
			
			// form validate
			//$.validator.messages.required = "";	// field message not show behind
			$("#departmentFreightDetailForm").validate({
				rules: {
					fixPrice: {number: true},
					unitPrice: {number: true},
					firstPartWeight: {number: true},
					firstPartPrice: {number: true},
					addWeightStep: {number: true},
					addWeightPrice: {number: true},
					weightLeftRange: {number: true},
					weightRightRange: {number: true},
					priceLeftRange: {number: true},
					priceRightRange: {number: true},
					parm1: {number: true},
					parm2: {number: true},
					parm3: {number: true},
					parm4: {number: true},
					parm5: {number: true}
				},
				invalidHandler: function(e, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						var message = "请正确填写表单信息！";
						$("div.warning span").html(message);
						$("div.warning").show();
					} else {
						$("div.warning").hide();
					}
				},
				onkeyup: false,
				// if use ajax submit, please override this function
				// if use struts2 action submit, please remove this function
				submitHandler: function() {
					var customer = $("#gridCustomerGroup").jqGrid("getDataIDs");
					var dest = $("#gridDestGroup").jqGrid("getDataIDs");
					//alert($("#choosenNodes").attr('value'));
					//alert(customer+"->"+dest);
					$.post(
						"departmentFreightDetail_generateTempDetail.action?freightId="+$("#freightId").attr('value'),
						{
							originalCustomerGroupIds	:customer.toString(), 
							destinyOrgGroupIds 	:dest.toString(),
							
							priceTypeId			:$("#priceTypeId").attr('value'),
							priceRuleId			:$("#priceRuleId").attr('value'),
							fixPrice			:$("#ibFixed").attr('value'),
							unitPrice			:$("#ibWeightPrice").attr('value'),
							firstWeight			:$("#ibFirstWeight").attr('value'),
							firstWeightPrice  	:$("#ibFirstWeightPrice").attr('value'),
							addWeightPrice		:$("#ibAddWeightPrice").attr('value'),
							addWeight			:$("#ibAddWeight").attr('value'),
							carryTypeKey		:$("#carryTypeKey").attr('value'),
							weightLeftRange		:$("#ibWeightLeftRange").attr('value'),
							weightRightRange	:$("#ibWeightRightRange").attr('value'),
							priceLeftRange		:$("#ibPriceLeftRange").attr('value'),
							priceRightRange		:$("#ibPriceRightRange").attr('value'),
							parm1			:$("#ibParam1").attr('value'),
							parm2			:$("#ibParam2").attr('value'),
							parm3			:$("#ibParam3").attr('value'),
							parm4			:$("#ibParam4").attr('value'),
							parm5			:$("#ibParam5").attr('value'),
							choosenNodes	:$("#choosenNodes").attr('value')
						},
						function(){
							$("div.warning").hide();
							alert("提交成功");
							$("#gridPriceDetail").trigger("reloadGrid"); 
						}
					);
				},
				messages: {
					// 自定义错误描述，可以忽略
					level: "请选择紧急程度"
				},
				debug:false
			});

		});
			

		/**
		 * 目的网点组列表
		 */
		$(function(){
			$("#gridDestGroup").jqGrid({
				//url: 'price-info.js',
				datatype: 'local',
				colNames: ['组编号', '组名称'],
				colModel: [
					{name:'code', index:'code', width:50},
					{name:'name', index:'name', width:100}
				],
				/*
				rowNum: 10,
				rowList: [10, 20, 30],
				pager: '#gridDestGroupPager',
				*/
				sortname: 'code',
				multiselect: true,
				viewrecords: true,
				sortorder: 'asc',
				caption: '目的网点组信息',
				width: 198,
				toolbar: [true, "top"],
				onSelectRow: function(id){
					var ids = $("#gridDestGroup").jqGrid('getGridParam', 'selarrrow');
					showNodes($("#gridDestNode"), ids);
				}
			});
			//$("#gridDestGroup").jqGrid('navGrid', '#gridDestGroupPager', {edit: false, add: false, del: false});
		});
		$("#gridDestGroupToolbar").appendTo($("#t_gridDestGroup"));

		/**
		 * 目的网点列表
		 */
		$(function(){
			$("#gridDestNode").jqGrid({
				url: 'about:blank',
				datatype: 'json',
				colNames: ['网点编号', '网点名称'],
				colModel: [
					{name:'code', index:'code', width:50},
					{name:'name', index:'name', width:100}
				],
			    //定义json数据的格式描述信息
			    jsonReader: {
		      		root: "rows",
		      		page: "page",
		     		total: "total",
		     		records: "records", 
		        	repeatitems : false
		     	},
				/*
				rowNum: 10,
				rowList: [10, 20, 30],
				pager: '#gridDestNodePager',
				*/
				sortname: 'code',
				multiselect: false,
				viewrecords: true,
				sortorder: 'asc',
				caption: '网点信息',
				width: 198
			});
			//$("#gridDestNode").jqGrid('navGrid', '#gridDestNodePager', {edit: false, add: false, del: false});
		});
		
		/**
		 * 浏览部门的对话框
		 */
		$("#orgBrowser").dialog({
			autoOpen: false,
			bgiframe: true,
			height: 450,
			width: 450,
			modal: true,
			buttons: {
				"确定": function() { 
					$(this).dialog("close");
					var node = $("#orgTree").jstree('get_selected');
					if(!node || !node.length)
						return;
					
					$("#choosenNodes").val(node.attr("id"));// TODO 可选 ：node.attr("ti");
					//alert($("#choosenNodes").val());
					$("#ibCalcOrgName").val(node.attr("tn"));
				}, 
				"取消": function() {
					$(this).dialog("close"); 
				}
			}
		});

		/**
		 * 部门树
		 */
		$("#orgTree").jstree({
			"html_data" : {
				"ajax": {
					"url" : "orgGroup_getNode.action",
					"data" : function(n) {
						return { clickNodeCode : n.attr ? n.attr("ti") : topOrgCode };
						//return { clickNodeCode : n.attr ? n.attr("ti") : "YT0018" };
					}
				}
			},
			"ui" : {
				select_limit: 1
			},
			"plugins": ["themes", "html_data", "ui"]
		});

		/**
		 * 自动完成
		 */
		$("#ibCustomerGroup").autocomplete({
			source: ['上海', '北京', 'Shanghai', 'Beijing']
		});
		$("#ibDestGroup").autocomplete({
			source: ['上海', '北京', 'Shanghai', 'Beijing']
		});
			
		/**
		 * 按钮事件：删除起始网点组
		 */
		$("#deleteCustomerGroups").click(function(){
			var ids = $("#gridCustomerGroup").jqGrid('getGridParam', 'selarrrow');
			if(ids != null){
				for(var i = ids.length - 1; i >= 0; i--){
					$('#gridCustomerGroup').jqGrid('delRowData', ids[i]);
				}
				var ids = $("#gridCustomerGroup").jqGrid('getGridParam', 'selarrrow');
				showCustomers($("#gridCustomer"), ids);
			}
		});

		/**
		 * 按钮事件：删除目的网点组
		 */
		$("#deleteDestGroups").click(function(){
			var ids = $("#gridDestGroup").jqGrid('getGridParam', 'selarrrow');
			if(ids != null){
				for(var i = ids.length - 1; i >= 0; i--){
					$('#gridDestGroup').jqGrid('delRowData', ids[i]);
				}
				var ids = $("#gridDestGroup").jqGrid('getGridParam', 'selarrrow');
				showNodes($("#gridDestNode"), ids);
			}
		});
		
		/**
		 * 按钮事件：浏览计费部门
		 */
		$("#btnSelectCalcOrg").click(function(){
			$("#orgBrowser").dialog("open");
		});

		/**
		 * 按钮事件：生成价格配置
		 */
		$("#btnGenerate").click(function(){
			$("#departmentFreightDetailForm").submit();
		});

		/**
		 * 按钮事件：删除价格详细条目
		 */
		$("#btnDelPriceDetail").click(function(){
			var checked = $("#gridPriceDetail").jqGrid('getGridParam', 'selarrrow');
			if(checked != null){
				$.post("departmentFreightDetail_delete.action",
					{id: checked.toString()},
					function(data){
						//var checked = data.delSuccessList;
						//for(var i = checked.length - 1; i >= 0; i--){
						//	$("#gridPriceDetail").jqGrid('delRowData', checked[i]);
						//}
						$("#gridPriceDetail").trigger("reloadGrid"); 
				});
			}else {
				alert("请选择要删除的数据。");
				return;
			}
		});

		/**
		 * 按钮事件：保存价格详细列表
		 */
		$("#btnSavePriceDetail").click(function(){
			alert(selOpts);
			if(window['price_detail_lastsel'] != null)/*如果有处于编辑状态的行，先保存到数组*/
				$("#gridPriceDetail").jqGrid('saveRow', window['price_detail_lastsel']);
			var ids = $("#gridPriceDetail").jqGrid('getGridParam', 'selarrrow');
			if(ids == null || ids.length < 1){
				alert("请选择要生效的数据。");
				return;
			}
			$.post("departmentFreightDetail_active.action", 
					{id:ids.toString()}, 
					function(data){
					//alert("OK!" + data);
				$("#gridPriceDetail").trigger("reloadGrid"); 
				});
		});
	});

	function statusFormatter (cellvalue, options, rowObject)
	{
	   if(cellvalue == "VALID"){
		   return "已生效";
	   } else {
		   return "临时状态";
	   }

	}


	/**
	 * 按钮事件：弹出浏览起始网点组对话框
	 */
	function browseCustomerGroup(){
		//$("#orgGroupListGrid").jqGrid("setGridParam", {url:"#customergroup"}).trigger("reloadGrid");
		$("#customerGroupBrowser").dialog('option', 'target', 'customer');
		$("#customerGroupListGrid").jqGrid("resetSelection");
		$("#customerGroupBrowser").dialog('open');
	}

	/**
	 * 按钮事件：弹出浏览目的网点组对话框
	 */
	function browseDestGroup(){
		//$("#orgGroupListGrid").jqGrid("setGridParam", {url:"#destgroup"}).trigger("reloadGrid");
		$("#groupBrowser").dialog('option', 'target', 'dest');
		$("#orgGroupListGrid").jqGrid("resetSelection");
		$("#groupBrowser").dialog('open');
	}

	function showCustomers(targetGrid, groupIds){
		targetGrid.jqGrid('setGridParam',{url:"departmentFreightDetail_listCustomerInGroup.action?choosenCustomerGroupId="+groupIds.toString()}).trigger("reloadGrid"); 
	}
	
	function showNodes(targetGrid, groupIds){
		targetGrid.jqGrid('setGridParam',{url:"departmentFreightDetail_listOrgInGroup.action?choosenOrgGroupId="+groupIds.toString()}).trigger("reloadGrid"); 
	}
