<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	$("#appList").jqGrid({
	   	url:'appSearch.action?rowNum=10',
		datatype: "json",
		mtype: "POST",
		rownumbers: true,
		colNames:[ 'ID', '名 称','描述' ],
		colModel:[ 
				    {name : 'id',index : 'id',width : '10px',hidden:true},
				    {name : 'appName',index : 'appName',width : '160px',align:'center'},
				    {name : 'description',index : 'description',width : '300px',align:'center'},
		],
	   	rowNum:10,
	   	rowList:[10,50,100],
	   	pager: '#appPager',
		sortName:"appName",
		sortOrder:"asc",
	   	height: 'auto', 
	   	width:'500px',
	   	hidegrid: false, // 隐藏
	    viewrecords: true,
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
    		$("#search").click();
    	},
    	
	  //加载结束后处理,处理session过期
	    loadComplete: function(data){
    		if(data.success=="false"){
    			alert(data.text);
    		}	
    	}
	    	
	});
	$("#ok").click(function(){
		var id = $("#appList").jqGrid('getGridParam','selrow');
		
		if (id)	{
			var info = $("#appList").jqGrid('getRowData',id);
			setAppInfo(id, info.appName, info.description);
		} else{
			setAppInfo("", "", "");
		}
		return true;
	});
	$("#back").click(function(){
		$("#dialog-ajax-select-app").dialog('close');
	});
	
});	

</script>

<form id="appForm" action="appList.action" method="post">

<div class="layout">
<div class="block m-b">
	<div class="block_container">
<table id="appList"></table>
<div id="appPager"></div>
<div id="tblMasterMessage"></div>
	<div class="btn_layout">
		<a id="ok" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-ok">确 定</span></span></a> 
		<a id="back" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a> 
	</div>
</div>

</div>

</div>
</form>

