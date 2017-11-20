<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" charset="UTF-8">

var dialogId = "${dialogId}";
var fieldsetId = "${fieldsetId}";
var dialogEditId = "dialog-edit-ajax";
var groupSearchUrl = "../mdmgrouputil/groupSearch.action?type=${type}&flag=${flag}&includePublic=${includePublic}";
var groupCreateUrl = "../mdmgrouputil/groupCreate.action?group.type=${type}&group.flag=${flag}&group.isPublic=N";

var groupModifyUrl = "../mdmgrouputil/groupModify.action";
var groupDeleteUrl = "../mdmgrouputil/groupDelete.action";
var memberListUrl = "../mdmgrouputil/groupMemberList.action";

$().ready(function() {
	
	$("#groupGridTable").gridUtil().simpleGrid({
		url: groupSearchUrl,
		editurl:"#groupDeleteUrl",
		//autowidth: false,
		//shrinkToFit: false,
		//sortname:'deliveryReportTime',
		//sortorder:'desc',
		colNames:[
					'ID', 
					'名称',
					'类型',
					'描述',
					'公用组',
					'用户代码',
					'操作'
				],
		colModel:[ 
					{name : 'id',index : 'id', hidden:true},
					{name : 'name',index : 'name',width : '15%'},
					{name : 'type',index : 'type',width : '10%', formatter:'select', editoptions:{value: 
						"<s:property escape='false' value='@com.ibs.portal.framework.util.SelectRenderUtils@toRenderString(@com.ibs.core.module.mdmbasedata.common.GroupType@getAll())'/>" }},
					{name : 'description',index : 'description',width : '30%'},
					{name : 'isPublic',index : 'isPublic',width : '10%', formatter:'select', editoptions:{value:"Y:是;N:否"}},
					{name : 'userCode',index : 'userCode',width : '10%', hidden:true},
					{name:  'operation',width:"30%",align:'center', search:false,sortable:false,editable:true,title:false}
				],
		pager: "#groupGridPager",
		toolbar: [true,"top"],
		caption: "${groupTypeName}组记录",
		gridComplete: function() {
			var ids = $("#groupGridTable").jqGrid('getDataIDs'); 
			for(var i=0;i < ids.length;i++) {
				var all = "";
				var mod = "<a target='' onclick='groupModify(\"#id\")' class='a_btn_edit m-r '><em>修改信息</em></a>";
				var mem = "<a target='' onclick='groupMemberList(\"#id\")' class='a_btn_edit m-r '><em>维护${groupTypeName}成员</em></a>";
				
				mod = mod.replace(/#id/, ids[i]);
				mem = mem.replace(/#id/, ids[i]);
				
               	all = all + mod + mem;
				
				$("#groupGridTable").jqGrid('setRowData',ids[i],{operation:all});
			}
		}
	});
	
	$("#groupGridToolbar").appendTo($("#t_groupGridTable"));

	$("#reset").click(function(){					
		$('#searchForm')[0].reset(); 
	});
	
	$("#groupCreate").click(function(){
		$dlg = $.IBSUtil.getOrCreateDialog(dialogEditId, {title: "${groupTypeName}组新增", width: 960, height: 500});
		$dlg.dialog("open");
		$dlg.load(groupCreateUrl);
		return false;
	});
	
	// 删除 ajax
	$("#groupDelete").click(function(){
		var selectIds = $("#groupGridTable").jqGrid('getGridParam','selarrrow');
		if (selectIds != null && selectIds != ""){
			var strSelectIds = "";
			for(var i = 0; i < selectIds.length; i++){
				strSelectIds += selectIds[i] + ",";
			}
			$.ajax({
			   type: "POST",
			   url: "../mdmgrouputil/groupDelete.action",
			   dataType: "json",
			   data: ({groupIds: strSelectIds}),
			   success: function(result){
				   if(result.message=="OK"){
					   doGroupSearch();
				   }else{
					   $.boxUtil.warn('${app:i18n("motor.exception")}', {title: '${app:i18n("global.jsp.attention")}'}, function(){});
				   }
			   	}
			});
		}else{
			$.boxUtil.warn('请至少选择一项纪录！', {title: '${app:i18n("global.jsp.attention")}'}, function(){});
		}
	});
	
	$("#groupSearch").click(function(){
		doGroupSearch();
	});
	
	$("#groupSelect").click(function(){
		var	ids = $("#groupGridTable").jqGrid('getGridParam','selarrrow');
		if (ids.length < 1)	{
			$.boxUtil.warn('请选择一个组作为查询条件！', {title: '${app:i18n("global.jsp.attention")}'}, function(){});
			return false;
		}
		
		var sels = new Array();
		for(var i = 0; i < ids.length; i++){
			var rowData = $("#groupGridTable").getRowData(ids[i]);
			sels.push({id: rowData.id, name: rowData.name, checked: 'checked'});
		}
		$('#' + fieldsetId).check().bindDS({array: sels, isHref: true});
		$.IBSUtil.tipGroup($('#' + fieldsetId));
		$('#' + dialogId).dialog('close');
	});
	
	$("#groupUndo").click(function(){
		$('#' + dialogId).dialog('close');
	});

	$(".legend_title a").collapseContainer();

});
function doGroupSearch(){
	// 设置查询参数
	//$("#groupGridTable").jqGrid("setGridParam",{page:1});
	//$("#groupGridTable").jqGrid("setGridParam",{url:$("#groupSearchUrl").val()}).trigger("reloadGrid");
	$("#groupGridTable").gridUtil().searchGrid($("#searchGroupForm"));
}
function groupModify(id){
	$dlg = $.IBSUtil.getOrCreateDialog(dialogEditId, {title: "${groupTypeName}组修改", width: 960, height:650});
	$dlg.dialog("open");
	$dlg.load(groupModifyUrl, {id: id});
	return false;
}
function groupMemberList(groupId){
	$dlg = $.IBSUtil.getOrCreateDialog(dialogEditId, {title: "${groupTypeName}组成员维护", width: 960, height:650});
	$dlg.dialog("open");
	$dlg.load(memberListUrl, {groupId: groupId, type: "${type}" });
	return false;
}
</script>


<div id="container">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${groupTypeName}组管理</h3>
			</div>
			<div class="block_container">
				<form name="searchGroupForm" id="searchGroupForm" method="post">
					<input type="hidden" name="pageCache" id="pageCache" value="true" />
					<fieldset>
						<div class="layout m-b">
							<div class="fieldset_border fieldset_bg m-b">
								<div class="legend_title"><a href="#" class="container_show">查询</a></div>
								<div class="fieldset_container">
									<table cellpadding="0" cellspacing="0" class="table_form">
										<thead>
										</thead>
										<tfoot>
										</tfoot>
										<tbody>
											<tr>
												<th><em>*</em>${groupTypeName}组名称：</th>
												<td>
													<input name="groupName" id="groupName" />
												</td>
											</tr>
										</tbody>
									</table>
									<div class="btn_layout">
										<a name="groupSearch" id="groupSearch" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
										<a name="groupUndo" id="groupUndo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
										<a name="groupSelect" id="groupSelect" class="a_btn2 m-r" href="#"><em>选 择</em></a>
										<%-- <a name="groupSelect" id="groupSelect" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-ok">${app:i18n('global.jsp.ok')}</span></span></a> --%>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</form>
				<div class="layout">
					<div class="block m-b">
						<table id="groupGridTable"></table>
						<div id="groupGridPager"></div>
							<div id="groupGridToolbar">
								<a id="groupCreate" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-add">新增</span></span></a>
								<a id="groupDelete" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-remove">删除</span></span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
