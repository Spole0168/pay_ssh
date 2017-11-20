<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<c:url context='/common' value='/js/common/jquery.gridUtil.js'/>"></script>
<script type="text/javascript" charset="UTF-8">
function getMacUser(id){
	$('#mac_user_dialog').html("");
	$('#mac_user_dialog').dialog({
		autoOpen:false,
		width:700,
		height:500,
		position:'center',
		modal:true,
		title:'已绑定用户'
		});
	$('#mac_user_dialog').load('toMacUsers.action?macId=' + id);
	$('#mac_user_dialog').dialog('open');
}

function displayBindingInfo(selectedMacs){
	$("#bindingInfo").html('此菜单已绑定 了<font color="red">' + selectedMacs.length + ' </font>个MAC，勾选MAC后点击保存按钮更新数据');
	$("#selectedUsers").val(selectedMacs);
}

function doSearch() {
	$("#gridTable").gridUtil().searchGrid($("#searchMacForm"));
}

	$(document).ready(function(){
		var selectedIds = new Array();
		var selectedMacs = new Array();
		
		<c:forEach items="${macIdList}" var="mac">
			selectedIds.push("${mac.id}");
			selectedMacs.push("${mac.mac}");
		</c:forEach>
		displayBindingInfo(selectedMacs);
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "searchMac.action?menuId=${menuId}",
			colNames:[ 'id',
			           'userCount',
					   'MAC',
			           'CPU ID',
			           '操作'
			           ],
			colModel:[  {name : 'id', hidden:true, key:true},
			            {name : 'userCount', hidden:true},
					    {name : 'mac',index : 'MAC',width : '33%'},
					    {name : 'cpuId',index : 'CPU_ID',width : '33%'},
					    {name : 'operation', search:false,sortable:false,editable:false,width : '33%'}
			],
			pager: "#gridPager",
			sortname: 'MAC',
			sortorder: "ASC",
			caption: "MAC地址列表",
			multiboxonly: false,
			cellEdit: true,
			gridComplete: function() {
				for(var i = 0; i < selectedIds.length; i++){
					$("#gridTable").jqGrid('setSelection', selectedIds[i]);
				}
				
				var ids = $("#gridTable").jqGrid('getDataIDs');
				
				for(var i=0;i < ids.length;i++) {
					var mod = '<a href="javascript:void(0)" class="a_btn_edit m-r" onclick="javascript:getMacUser(\''+ ids[i] +'\')"><em>查看绑定用户</em></a>';
					var rowData = $("#gridTable").getRowData(ids[i]);
					//if(rowData.userCount > 0){
						$("#gridTable").jqGrid('setRowData',ids[i],{operation:mod});
					//}
				}
			},
			onSelectAll: function(aRowids,status){
				var arrMacStr = aRowids + '';
				var arrMac = arrMacStr.split(",");
				if(status){
					for(i = 0; i < arrMac.length; i++){
						if($.inArray(arrMac[i], selectedIds) < 0){
							selectedIds.push(arrMac[i]);
							selectedMacs.push($("#gridTable").getRowData(arrMac[i]).mac);
						}
					}
				}else{
					for(i = 0; i < arrMac.length; i++){
						var index = $.inArray(arrMac[i], selectedIds);
						if(index >= 0){
							selectedIds.splice(index, 1);
							selectedMacs.splice(index, 1);
						}
					}
				}
				
				displayBindingInfo(selectedMacs);
			},
			onSelectRow: function(rowid,status){
				if(status){
					if($.inArray(rowid, selectedIds) < 0){
						selectedIds.push(rowid);
						selectedMacs.push($("#gridTable").getRowData(rowid).mac);
					}
				}else{
					var index = $.inArray(rowid, selectedIds);
					if(index >= 0){
						selectedIds.splice(index, 1);
						selectedMacs.splice(index, 1);
					}
				}
				
				displayBindingInfo(selectedMacs);
			}
		});

		$("#search").click(doSearch);
		$("#searchMacForm input").input().trim();
		$("#searchMacForm input").input().enter(doSearch);
		
		$("#reset").click(function(){					
			$('#searchMacForm')[0].reset(); 
		});
		
		$("#backToMenuList").click(function(){
			$("#dialog-ajax-menu-mac").html(""); 
			$("#dialog-ajax-menu-mac").dialog('close'); 
		});
		
		$("#macSave").click(function(){
			$("#dialog-ajax-menu-mac").block({
	    		showOverlay: true,
				message: '<span class="loading_span">正在保存数据，请稍后...</span><br/><img src="/common/images/framework/ajax-loader.gif" />',
				css: {padding: '20px'}
			});
			
			$.ajax({
	    		type: "POST",
	    		dataType: "json",
	    		url: "saveMenuBindingMac.action",
	    		data: {
	    			macIdJson: JSON.stringify(selectedIds),
	    			menuId: $("#menuId").val()
	    		},
	    		success: function(result) {
	    			$("#dialog-ajax-menu-mac").unblock();
	    			
	    			// fail
	    			if(result.success == "false") {
					    $.boxUtil.alert(result.text);
					    return;
					}
	    			
	    			// success
	    			$("#backToMenuList").trigger('click');
	    			$.growlUI('成功信息！', '菜单绑定MAC地址成功！');
	    		},
	    		error: function(err) {
	    			// exception
	    			$("#dialog-ajax-menu-mac").unblock();
	    		}
	    	});
		});
		
	});
</script>
<input type="hidden" name="menuId" id="menuId" value="${menuId}"/>
<input type="hidden" name="status" id="status" value="1">
<div id="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="legend_title"><a href="javascript:void(0)" class="container_show"><legend>MAC地址查询</legend></a></div>
				<form method="post" action="toBindingMenuToMac.action" id="searchMacForm">
				<div class="fieldset_container">
					<table cellpadding="0" cellspacing="0" class="table_form">
						<tbody>
							<tr>
								<th width="80">MAC地址：</th>
								<td><input type="text" id="mac" name="mac"/></td>
								<th width="120">用户姓名/用户编号</th>
								<td><input type="text" id="userName" name="userName"/></td>
							</tr>
							<tr>
								<th>CPU ID：</th>
								<td><input type="text" id="cpuid" name="cpuid"/></td>
								<th></th>
								<td><span id="bindingInfo"></span></td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a id="search" class="easyui-linkbutton l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span></span></a>
						<a id="reset" class="easyui-linkbutton l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.reset')}</span></span></a>
						<a id="macSave" class="easyui-linkbutton l-btn" href="javascript:void(0);"><span class="l-btn-left"><span class="l-btn-text icon-save">保存</span></span></a>
						<a id="backToMenuList" class="easyui-linkbutton l-btn" href="javascript:void(0);"><span class="l-btn-left"><span class="l-btn-text icon-undo">返回</span></span></a>
				    </div>
				</div>
				</form>
			</div>
			<div class="m-b">
				<textarea id="selectedUsers" rows="2" cols="145" readonly="readonly"></textarea>
			</div>
			<table id="gridTable"></table>
			<div id="gridPager"></div>
		</div>
	</div>
</div>
<div id='mac_user_dialog'></div>