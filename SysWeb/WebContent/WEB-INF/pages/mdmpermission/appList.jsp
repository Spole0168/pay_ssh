<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
$().ready(function() {
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
	$().ready(function() {
		//构建表格，并给删除、新增和修改按钮增加操作
		buildPopupGrid({
			caption:"应用列表",
			gridName:"appList",
			formName:"appForm",
			pagerName:"appPager",
			searchButtonName:"search",
			deleteButtonName:"delete",
			createButtonName:"create",
			modifyButtonName:"modify",
			
			searchUrl:"appSearch.action",
			deleteUrl:"appDelete.action",
			createUrl:"appToSaveOrUpdate.action",
			modifyUrl:"appToSaveOrUpdate.action",
			modifyFunctionName:"modify",
			colNames:[ 'ID', '名 称','描述','操作' ],
			colModel:[ 
					    {name : 'id',index : 'id',width : '10%',hidden:true},
					    {name : 'appName',index : 'appName',width : '30%',align:'center'},
					    {name : 'description',index : 'description',width : '30%'},
					    {name:  'operation',index:'operation',width:'10%', search:false,sortable:false,editable:false}
			],
			searchParameters: ["appNameSearch","descriptionSearch","pageCache"],
			sortName:"id",
			sortOrder:"asc"	
		});
	
	});
	$("#create").click(function(){
		var url = "appToSaveOrUpdate.action";
		var title = "新建应用信息";
		openDialog(title, url);
		
	});
	$("#reset").click(function(){
		$("#appNameSearch").val("");
		$("#descriptionSearch").val("");
	});
	$("#app_search_table input").input().enter(function(){
		$("#search").click();
	});
});

var dlgId = "#dialog-ajax-app-edit";
var gridId = "#appList";
function modify(id){
	var url = "appToSaveOrUpdate.action?id="+id+"&time="+new Date().getTime();
	var title = "修改应用信息";
	openDialog(title,url);
}
function refreshGrid(){
	$(gridId).trigger("reloadGrid");
}
function openDialog(title, url){
	$(dlgId).dialog({
		autoOpen:false,
		width:380,
		height:250,
		position:'center',
		modal:true,
		resizable: false,
		title:title
		});
	$(dlgId).load(url);
	$(dlgId).dialog('open');
}
function closeDialog(){
	$(dlgId).dialog('close');
}
	
</script>

<form id="appForm" action="appList.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_title">
	   <h3>应用列表</h3>
	</div>
	<div class="block_container">
	<div id="app_search_table" class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">应用查询</a></div>
	<div class="fieldset_container">
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td width="10%" align="right"><label for="appNameSearch">名称:</label></td>
		<td width="35%"><input type="text" name="appNameSearch" id="appNameSearch" size="25" maxlength="100" /></td>
		<td width="10%" align="right"><label for="descriptionSearch">应用描述:</label></td>
		<td width="35%"><input type="text" name="descriptionSearch" id="descriptionSearch" size="25" maxlength="100" /></td>
		</tr>
	</table>
	<div class="btn_layout">
	<a  id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span>
	  </span>
	</a>
	<a id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.reset')}</span></span></a>
    </div>
	</div>
	</div>

 <div id="toolbar">
		<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">新 增</span></span></a> 
	</div>
<table id="appList"></table>
<div id="appPager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
<div id="dialog-ajax-app-edit"></div>
</form>
