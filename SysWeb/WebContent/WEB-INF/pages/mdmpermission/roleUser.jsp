<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
$().ready(function() {
	$(".legend_title a").toggle(function(){
		$(this).parent().next(".fieldset_container:first").hide();
		$(this).removeClass("container_show");
		$(this).addClass("container_hide");
	},function(){
		$(this).parent().next(".fieldset_container:first").show();
		$(this).removeClass("container_hide");
		$(this).addClass("container_show");
});
	var gridVar = "#userList";
	var statusColumnRender = $('#statusColumnRender').attr('value'); // column render
	var typeColumnRender = $('#typeColumnRender').attr('value'); // column render
	$("#userList").jqGrid({
	   	url:'assignedUserSearch.action?rowNum=10&id=${role.id}&currentOrgId=${currentOrgId }&time='+new Date().getTime(),
		datatype: "json",
		mtype: "POST",
		rownumbers: true,
		colNames:['ID',/* '代 码', */'登录用户名','用户名称','用户类型',/*'组织代码','组织名称', */'描述'  ],
		colModel:[ 
				    {name : 'id',index : 'user.id',hidden:true,width : '30%'},
				    /*{name : 'userCode',index : 'user.userCode',width : '30%'}, 
				    {name : 'userName',index : 'user.userName',width : '30%'},
				    {name : 'displayName',index : 'user.displayName',width : '30%'},
				    {name : 'userType',index : 'user.userType',width : '30%',formatter:'select', editoptions:{value:typeColumnRender}},
				    {name : 'orgCode',index : 'org.org.orgCode',width : '30%'},
				    {name : 'orgName',index : 'org.org.orgName',width : '30%'},
				     */
				    {name : 'userName',index : 'user.userName',width : '30%'},
				    {name : 'userCode',index : 'user.userCode',width : '30%'}, 
				    {name : 'userType',index : 'user.userType',width : '30%',formatter:'select', editoptions:{value:typeColumnRender}},
				    {name : 'description',index : 'user.description',width : '30%'}
				    ],
	   	rowNum:50,
	   	rowList:[50,100,200],
	   	pager: '#userPager',
		sortname:"user.userCode",
		sortOrder:"asc",
	   	autowidth:true,	   		   	
	   	height: 'auto', 
	   	hidegrid: false, // 隐藏
	    viewrecords: true,
	    multiselect:true,
	    toolbar: [true,"top"],
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
    	
	    caption:"用户列表",
	  //加载结束后处理,处理session过期
	    loadComplete: function(data){
    		if(data.success=="false"){
    			alert(data.text);
    		}	
    		
    	},
	    //增加操作列	    	
	    gridComplete: function(){
    	}
	    	
	});
	$("#role_user_toolbar").appendTo($("#t_userList"));

	$("#role_user_delete").click(function(){
		var selectIds = $("#userList").jqGrid('getGridParam','selarrrow');

		if (selectIds!=null && selectIds!="")	{
			$.ajax({
				url: "removeUserFromRole.action",
				type: "POST",
				dataType: "json",
				data: {
					userId:selectIds.toString(),
					id:$("#id").val()
				},
				success: function( data ) {
					if(data.success == 'false'){
						$.boxUtil.alert("删除失败！");
						 
					}else{
						$.boxUtil.alert("删除成功！");
						 
					}
					$("#userList").trigger("reloadGrid");
				}
			});
		} else{
			$.boxUtil.alert('请选择要删除的用户!');
			 
		}
	});
	
	$("#user-role-search").click(function(){
		var url = "assignedUserSearch.action?rowNum=" + $(gridVar).jqGrid('getGridParam','rowNum')+"&time="+new Date().getTime();
// 		var currentOrgId = $('#userOrgId').val();
// 		if(currentOrgId == ''){
// 			currentOrgId = $('#currentOrgId').val();
// 		}
	//	$("#currentOrgId").val(currentOrgId);
		var userCode = $('#userCodeSearch').val();
		var userName = $('#userNameSearch').val();
// 		var userDisplayName = $('#userDisplayNameSearch').val();
		var userType = $('#userTypeSearch').val();
// 		var description = $('#userDescriptionSearch').val();
// 		var districtId = $('#districtId').val();
		$(gridVar).appendPostData({
			id:$('#id').val(),
// 			currentOrgId:currentOrgId,
// 			needSubOrg:$("#needSubOrg").attr("checked"),
			userCodeSearch:userCode,
			userNameSearch:userName,
// 			userDisplayNameSearch:userDisplayName,
			userTypeSearch:userType
// 			districtId:districtId,
// 			descriptionSearch:description
			});
		
		$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
		// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
		$(gridVar).jqGrid("setGridParam",{page:1});
	});


	$("#user-role-reset").click(function(){
		$("#userCodeSearch").val("");
		$("#userNameSearch").val("");
// 		$("#userDescriptionSearch").val("");
		$("#userTypeSearch").val("");
// 		$("#userDisplayNameSearch").val("");
// 		$('#userOrgId').val($("#currentOrgId").val());
//     	$('#userOrg').val($("#currentOrgName").val());
// 		$('#districtId').val("");
// 		$('#districtName').val("");
// 		$('#needSubOrg').attr('checked',true);
	//	$('#currentOrgId').val($("#selectedOrgId").val());
	});
	$("#user-role-exit").click(function(){
		$(dlgUserId).html('');
		$(dlgUserId).dialog('close');
	});
	
// 	$( "#userOrg" ).autocomplete({
//    		source: function( request, response ) {
// 			$.ajax({
// 				url: "<c:url context='/mdm' value='/mdmorgutil/autoCompleteOrgs.action'/>",
// 				type: "POST",
// 				dataType: "json",
// 				data: {
// 					searchVar:$("#userOrg").val()
// 				},
// 				success: function( data ) {
// 					response( $.map( data.orgs, function( item ) {
// 						return {
// 							label: item.code + ' - ' + item.name,
// 							value: item.name,
// 							orgName: item.code + ' - ' + item.name,
// 							orgId: item.id
// 						};
// 					}));
// 				}
// 			});
// 		},
// 		select: function( event, ui ) {
// 			$('#userOrgId').val(ui.item.orgId);
// 			$('#userOrg').val(ui.item.orgName);
// 		},
// 		change: function(event, ui){
// 		    try{
// 			    if(ui.item == null || ui.item.value != $('#userOrg').val()){
// 			    	$('#userOrgId').val("");
// 			    	$('#userOrg').val("");
// 			    }
// 		    }catch(err){ 
// 		    	$('#userOrgId').val("");
// 		    	$('#userOrg').val("");
// 		    }
// 		},
// 		open: function() {
// 			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
// 		},
// 		close: function() {
// 			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
// 		}
// 	});
// 	$( "#districtName" ).autocomplete({
//    		source: function( request, response ) {
// 			$.ajax({
// 				url: "<c:url context='/mdm' value='/mdmdistrict/autoCompleteDistrict.action'/>",
// 				type: "POST",
// 				dataType: "json",
// 				data: {
// 					searchVar:$('#districtName').val()
// 				},
// 				success: function( data ) {
// 					response( $.map( data.districts, function( item ) {
// 						return {
// 							label: item.name,
// 							value: item.name,
// 							distId: item.id
// 						};
// 					}));
// 				}
// 			});
// 		},
// 		select: function( event, ui ) {
// 			$('#districtId').val(ui.item.distId);
// 		},
// 		change: function(event, ui){
// 		    try{
// 			    if(ui.item == null || ui.item.value != $(this).val()){
// 			    	$('#districtId').val("");
// 			    	$('#districtName').val("");
// 			    }
// 		    }catch(err){ 
// 		    	$('#districtId').val("");
// 		    	$('#districtName').val("");
// 		    }
// 		},
// 		open: function() {
// 			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
// 		},
// 		close: function() {
// 			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
// 		}
// 	});
	$("#user_search_table input").input().enter(function(){
		$("#user-role-search").click();
	});
	
});

// var dlgEditId = "#dialog-ajax-user-edit";
// var dlgRoleId = "#dialog-ajax-user-role";
// var gridId = "#userList";

// function modify(id){
// 	var url = "userModify.action?id="+id+"&time="+new Date().getTime();
// 	var title = "修改用户信息";
// 	$(dlgEditId).dialog({width:400,height:300,modal:true});
// 	openDialog(dlgEditId,title,url);
// }

// function userRole(id){
// 	var url = "toUserAssignRoles.action?id=" + id+"&time="+new Date().getTime();
// 	var title = "用户-角色信息";
// 	$(dlgRoleId).dialog({width:800,height:600,modal:true});
// 	openDialog(dlgRoleId,title,url);
// }


	
	
</script>
<form id="userForm" action="assignedUserList.action" method="post">
<div class="layout">
<div class="block m-b">
	<input id="id" name="id" type="hidden" value="${role.id}" />
	<input id="typeColumnRender" name="typeColumnRender" type="hidden" value="${typeColumnRender}" />
	<input id="statusColumnRender" name="statusColumnRender" type="hidden" value="${statusColumnRender}" />
<%-- 	<input id="currentOrgId" name="currentOrgId" value="${currentOrgId }" type="hidden" /> --%>
<%-- 	<input id="currentOrgCode" name="currentOrgCode" value="${currentOrgCode }" type="hidden" /> --%>
<%-- 	<input id="currentOrgName" name="currentOrgName" value="${currentOrgName }" type="hidden" /> --%>
	<div class="block_container">
	<div id='user_search_table' class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">用户查询</a></div>
	<div class="fieldset_container">
	
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<%-- <tr>
			<td width="25%" align="center" colspan="2">
				<label for="needSubOrg">是否包含子组织:</label>
				<input type="checkbox" id="needSubOrg" name="needSubOrg" checked="checked" value="y"></input>
			</td>
			<td width="10%" align="right">
				<label for="districtName">行政区域:</label>
			</td>
			<td width="15%">
				<input type="text" name="districtName" id="districtName" size="25" maxlength="100" />
				<input type="hidden" name="districtId" id="districtId" size="25" maxlength="100" />
			</td>
			<td width="10%" align="right">
				<label for="userCodeSearch">代码:</label>
			</td>
			<td width="15%">
				<input type="text" name="userCodeSearch" id="userCodeSearch" size="25" maxlength="100" />
			</td>
		</tr> --%>
		<tr>
			<td width="10%" align="right">
				<label for="userNameSearch">登录名:</label>
			</td>
			<td width="15%">
				<input type="text" name="userNameSearch" id="userNameSearch" size="25" maxlength="100" />
			</td>
		
			<td width="10%" align="right">
				<label for="userCodeSearch">用户名:</label>
			</td>
			<td width="15%">
				<input type="text" name="userCodeSearch" id="userCodeSearch" size="25" maxlength="100" />
			</td>
		</tr>
		<tr>
			<td width="10%" align="right">
				<label for="userTypeSearch">用户类型:</label>
			</td>
			<td width="15%">
				<select id="userTypeSearch" name="userTypeSearch" style="width:162px">
					<option value="">请选择</option>
					<c:forEach items="${typeList }" var="item">
						<option value="${item.key }">${item.value }</option>
					</c:forEach>
				</select>
			</td>
			<!-- <td width="10%" align="right">
				<label for="userOrg">所属组织:</label>
			</td>
			<td width="15%">
				<input type="hidden" name="userOrgId" id="userOrgId" size="25" value="${currentOrgId }" maxlength="100" />
				<input type="text" name="userOrg" id="userOrg" size="25" value="${currentOrgName }" maxlength="100" />
			</td> --%>
			<!-- <td width="10%" align="right"><label for="userDescriptionSearch">描述:</label></td>
			<td width="15%"><input type="text" name="userDescriptionSearch" id="descriptionSearch" size="25" maxlength="100" /></td> -->
		</tr>
	</table>
	<div class="btn_layout">
	<a  id="user-role-search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span>
	  </span>
	</a>
	<a id="user-role-reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.reset')}</span></span></a>
	<a id="user-role-exit" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
    </div>
	</div>
	</div>
<div class="layout ">
<div class="block m-b ">	
<div class="block tabs" id="userListDiv">
<table id="userList"></table>
</div>
<div id="role_user_toolbar">
	<a id="role_user_delete" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-remove">删 除</span></span></a> 
</div>
<div id="userPager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
	</div>
	</div>
<div id="dialog-ajax-user-edit"></div>
<div id="dialog-ajax-user-role"></div>
</form>
