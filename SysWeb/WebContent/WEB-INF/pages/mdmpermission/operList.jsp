<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	
	var menuTypeColumnRender = $('#menuTypeColumnRender').attr("value");  // column render
	$().ready(function() {
		//构建表格，并给删除、新增和修改按钮增加操作
		buildPopupGrid({
			caption:"操作列表",
			gridName:"operList",
			formName:"operForm",
			pagerName:"operPager",
			searchButtonName:"search",
			deleteButtonName:"delete",
			createButtonName:"create",
			modifyButtonName:"modify",
			modifyFunctionName:"modify",
			searchUrl:"operSearch.action",
			deleteUrl:"operDelete.action",
			createUrl:"operCreate.action",
			modifyUrl:"operModify.action",

			colNames:[ '名 称','类 型','路 径','描 述','操作' ],
			colModel:[ 
					    {name : 'menuName',index : 'menuName',width : '35%'},
					    {name : 'menuType',index : 'menuType',width : '10%',formatter:'select', editoptions:{value:menuTypeColumnRender}},
					    {name : 'location',index : 'location',width : '20%'},
					    {name : 'description',index : 'description',width : '35%'},
					    {name:  'operation',index:'operation',width:'10%', search:false,sortable:false,editable:false}
			],
			searchParameters: ["operNameSearch","operTypeSearch","operValueSearch","descriptionSearch","pageCache"],
			sortName:"menuName",
			sortOrder:"asc"	
		});
	
	});
	$("#create").click(function(){
		var url = "operCreate.action";
		var title = "新建操作信息";
		openDialog(title, url);
		
	});
	$("#modify").click(function(){
		var id = $("#operList").jqGrid('getGridParam','selrow');
		
		if (id)	{
			$('#operForm').attr('action','operModify.action?id=' + id);
			$("#operForm").submit(); 
		} else{
			alert('请选择数据字典记录!');
		}
	});
	$("#reset").click(function(){
		$("#operNameSearch").val("");
		$("#descriptionSearch").val("");
		$("#operTypeSearch").val("");
		$("#operValueSearch").val("");
	});

	$("#operation_search_table input").input().enter(function(){
		$("#search").click();
	});
	
});
var dlgId = "#dialog-ajax-oper-edit";
var gridId = "#operList";
function modify(id){
	var url = "operModify.action?id="+id+"&time="+new Date().getTime();
	var title = "修改操作信息";
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

<form id="operForm" action="operList.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_title">
	   <h3>操作列表</h3>
	</div>
	<input id="menuTypeColumnRender" name="menuTypeColumnRender" type="hidden" value="${menuTypeColumnRender}" />
	<div class="block_container">
	<div id='operation_search_table' class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">操作查询</a></div>
	<div class="fieldset_container">
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
			<td width="15%" align="right"><label for="operNameSearch">名称:</label></td>
			<td width="35%"><input type="text" name="operNameSearch" id="operNameSearch" size="25" maxlength="100" /></td>
			<td width="15%" align="right"><label for="operTypeSearch">类型:</label></td>
			<td width="35%">
			<select id="operTypeSearch" name="operTypeSearch" >
				<option value="">所有类型</option>
				<c:forEach items="${operTypePairs}" var="item">
					<option value="${item.key }">${item.value }</option>
				</c:forEach>
			</select>
		</tr>
		<tr>
			<td width="15%" align="right"><label for="operValueSearch">路径:</label></td>
			<td width="35%"><input type="text" name="operValueSearch" id="operValueSearch" size="25" maxlength="100" /></td>
			<td width="15%" align="right"><label for="descriptionSearch">操作描述:</label></td>
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
		<a id="delete" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-remove">删 除</span></span></a>
	</div>
<table id="operList"></table>
<div id="operPager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
<div id="dialog-ajax-oper-edit"></div>
</form>
