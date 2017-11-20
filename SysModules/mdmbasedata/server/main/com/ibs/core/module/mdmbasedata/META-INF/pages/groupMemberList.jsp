<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	var orgDivId = "dialog-group-member-select-ajax";
	var groupMemberSearchUrl = "../mdmgrouputil/groupMemberSearch.action?groupId="+$("#groupId").val();

	$("#groupMemberGridTable").gridUtil().simpleGrid({
		url: groupMemberSearchUrl,
		//editurl:"#deleteUrl",
		//autowidth: false,
		//shrinkToFit: false,
		//sortname:'',
		//sortorder:'desc',
		multiselect:false,
		colNames:[
					'ID', 
					'组员编号',
					'组员名称',
					'组员类型'
				],
		colModel:[
					{name : 'id',index : 'id', hidden:true},
					{name : 'memberCode',index : 'memberCode',width : '15%'},
					{name : 'memberName',index : 'memberName',width : '15%'},
					{name : 'memberType',index : 'memberType',width : '15%'}
				],
		pager: "#groupMemberGridPager",
		toolbar: [true,"top"],
		caption: "组成员纪录",
		gridComplete: function() {
			var ids = $("#groupMemberGridTable").jqGrid('getDataIDs'); 
			for(var i=0;i < ids.length;i++) {
				var all = "";
				$("#groupMemberGridTable").jqGrid('setRowData',ids[i],{operation:all});
			}
		}
	});
	
	$("#groupMemberGridToolbar").appendTo($("#t_groupMemberGridTable"));

	$("#reset").click(function(){
		$('#searchForm')[0].reset(); 
	});
	
	$("#undo").click(function(){
		$('#dialog-edit-ajax').dialog('close');
	});
	
	$("#groupMemberModify").click(function(){
		$dlg = $.IBSUtil.getOrCreateDialog(orgDivId, {title: "网点选择", width: 960, height:650});
		$dlg.dialog('open');
		$dlg.load("../mdmorganization/popOrgList.action", 
				$.extend({}, globalMemberOptions, {multipleSelectList: true, divId: orgDivId})
		);
		return false;
	});
	
	// 删除 ajax
	$("#gridRowDelete").click(function(){
		var selectIds = $("#groupMemberGridTable").jqGrid('getGridParam','selarrrow');
		if (selectIds!=null && selectIds!="")	{
			$("#groupMemberGridTable").jqGrid('delGridRow',selectIds,{drag:true,
				left:585, top:320,
				afterSubmit:function(resp, postdata){
					var result = JSON.parse(resp.responseText);								
					if(result.success == 'false'){
						alert("存在关联数据，无法删除。请先删除关联数据");		
						return [true,"",""];
					} else {
						return [true,"",""];
					}								
				},
				ajaxDelOptions:{dataType:'json'}
			});
		} else{
			alert('请选择实例记录!');
		}
	});

	$("#groupMemberSearch").click(function(){
		doGroupMemberSearch();
	});

	$(".legend_title a").collapseContainer();

});
function doGroupMemberSearch(){
	// 设置查询参数
	//$("#groupMemberGridTable").setPostDataItem("groupId", $("#groupId").val());
	$("#groupMemberGridTable").jqGrid("setGridParam",{page:1});
	$("#groupMemberGridTable").jqGrid("setGridParam",{url:$("#groupMemberSearchUrl").val()}).trigger("reloadGrid");
}
function setOrgSelectVal(id,code,name,parm){
	$('#groupMemberOrgIds').val(id);
	$('#groupMemberOrgNames').val(name); 
	$('#groupMemberOrgCodes').val(code);
	//保存组成员信息
	$.ajax({
		 	type: "POST",
		 	url: "../mdmgrouputil/groupMemberSaveOrUpdate.action",
		 	dataType: "json",
	 	   	data: ({groupId: $("#groupId").val(),groupMemberOrgIds: $("#groupMemberOrgIds").val(),groupMemberOrgNames: $("#groupMemberOrgNames").val(),groupMemberOrgCodes: $("#groupMemberOrgCodes").val()}),
	 	   	success: function(result){
		 		//$('#deliveryReportDiv').unblock();
				$.boxUtil.success('保存成功');
				doGroupMemberSearch();
				//$('#dialog-edit-ajax').dialog('close');
				return false;
			},
	   	   	error: function(err) {
	 	   		//$('#deliveryReportDiv').unblock();
	 		  	$.boxUtil.warn('${app:i18n("motor.exception")}', {title: '${app:i18n("global.jsp.attention")}'}, function(){});
	 		  	return false;
	   	   	}
	 	});
}
</script>


<s:hidden name="groupId" id="groupId"/>
<s:hidden name="groupMemberOrgIds" id="groupMemberOrgIds"/>
<s:hidden name="groupMemberOrgNames" id="groupMemberOrgNames"/>
<s:hidden name="groupMemberOrgCodes" id="groupMemberOrgCodes"/>

<div id="container">
	<div class="layout">
			<div class="block m-b">
				<div class="block_container">
				<div class="layout">
					<div class="block m-b">
						<table id="groupMemberGridTable"></table>
						<div id="groupMemberGridPager"></div>
							<div id="groupMemberGridToolbar">
								<a id="groupMemberModify" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-edit">修改</span></span></a>
							</div>
						</div>
					</div>
					<div id="tabs-1">
						<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
					</div>
					<!-- 外框结束 -->
				</div>
			</div>
		</div>
	</div>
