<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/common/metas.jsp"%>
<!-- 包含简单表格的JavaScript -->
<html>
<head>
<base target="_self"/>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/js/jstree-1.0rc2/themes/default/style.css'/>" />
<script type="text/javascript" src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<style type="text/css">
.leftOrgTree {
	HEIGHT: 350px;
	SCROLLBAR-FACE-COLOR: #e8e5f8;
	SCROLLBAR-HIGHLIGHT-COLOR: #f7f5ff;
	OVERFLOW: auto;
	SCROLLBAR-SHADOW-COLOR: #cac4e8;
	SCROLLBAR-3DLIGHT-COLOR: #ffffff;
	LINE-HEIGHT: 120%;
	SCROLLBAR-ARROW-COLOR: #9e9ccd;
	SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
}

.jstree-default .jstree-undetermined>a>.jstree-checkbox {
	background-position: -2px -19px !important;
}

.jstree-default .jstree-undetermined>a>.jstree-checkbox:hover {
	background-position: -2px -37px !important;
}

/* IE6 BEGIN */
.jstree-default .jstree-undetermined a .jstree-checkbox {
	_background-position: -2px -19px;
}

.jstree-default .jstree-checked a .jstree-checkbox {
	_background-position: -38px -19px;
}

.jstree-default .jstree-unchecked a .jstree-checkbox {
	_background-position: -2px -19px;
}
/* IE6 END */
</style>
<script type="text/javascript"
	src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
var editMenu = false;
$().ready(function() {
	$(".legend_title a").toggle(function() {
		$(this).parent().next(".fieldset_container:first").hide();
		$(this).removeClass("container_show");
		$(this).addClass("container_hide");
	}, function() {
		$(this).parent().next(".fieldset_container:first").show();
		$(this).removeClass("container_hide");
		$(this).addClass("container_show");
	});
	var saveResult = $("#saveResult").val();
	if(saveResult != ''){
		if(saveResult == 'success'){
			$.boxUtil.alert("保存成功");
		}else{
			$.boxUtil.error("保存失败");
		}
	}
	$.unblockUI();
	var id = $("#id").val();
	if(id != null && id != ""){
		$("#roleCode").attr("readonly","readonly");
		$("#roleCode").removeAttr("class");
	} 
	
	$("#menuTree").jstree({
		"html_data" : {
		"ajax" : {
			"url" : "getManageNode.action",
			"data" : function (n) {
				return { nodeId : n.attr ? n.attr("id") : 0 };
			}
		}
	},
	"checkbox2": {
		"associate_parent": false,
		"associate_children": false
	},
	"core":{
		"initially_open": ["${assignedMenus}"]
	},
	"types" : {
		"type_attr" : "ti",
		"types":{
			"control":{
				"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
			},
			"url":{
				"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
			},
			"button":{
				"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
			}
		}
	},
	"ui" :{
		 "select_multiple_modifier" : "on",
		 "initially_select" : [<c:if test='${not empty assignedMenus }'>"${assignedMenus }"</c:if>]
	},
		"plugins" : [ "themes", "html_data" ,"ui","checkbox2","crrm","types"]
	})
	.bind("click", function (e){
		editMenu = true;
		var tn = $(e.target);
		tn = tn.is("li") ? tn : tn.parents(".jstree li").first();
		if(!tn.length) return;
		var id = tn.attr("id");
		if(tn.hasClass('jstree-checked')){
			$("#menuTree").jstree('open_node','#'+id);
			tn.parents('.jstree li').each(function(index){
				var pid = $(this).attr('id');
				$("#menuTree").jstree('check_node','#'+pid);
			});
		}else{
			var subchecked = false;
			tn.find('li').each(function(index){
				if($(this).hasClass('jstree-checked')){
					subchecked = true;
					return;
				}
//				var sid = $(this).attr('id');
	//			$("#menuTree").jstree('open_node','#'+sid);
		//		$("#menuTree").jstree('check_node','#'+sid);
			});
			if(subchecked){
				$.boxUtil.alert("子菜单有被选中");
				 
				$("#menuTree").jstree('check_node','#'+id);
			}
		}
	});

	$("#roleEditForm").validate( {
		rules : {
		
		},
		invalidHandler : function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = "请正确填写信息！";
				$("div.warning span").html(message);
				$("div.warning").show();
			} else {
				$("div.warning").hide();
			}
		},
		onkeyup : false,
		submitHandler : function() {
			$.blockUI({
				message: $('#loading_save').html(),
					css: {padding: '10px'}
				});
			document.roleEditForm.submit();
			window.returnValue  =$("#id").val();
			
			//window.close();
			//saveRole();
		},
		messages : {
			"roleCode":{required: "必填字段",remote:"该角色名称已被使用"}
		}
	});
	$("#role-edit-ok").click(function(){
		/* var flag = 0;
		$("input[name='checkedScopes']").each(function(){
			if($(this).attr('checked') == true){
				flag = 1;
			}
		});
		if(flag == 0){
			$.boxUtil.warn("请至少选择一个组织适用范围");
			return;
		} 
		var flag2 = 0;
		$("input[name='checkedUserType']").each(function(){
			if($(this).attr('checked') == true){
				flag2 = 1;
			}
		});
		if(flag2 == 0){
			$.boxUtil.warn("请至少选择一个用户类型适用范围");
			return;
		}
		
		//增加对管理范围的勾选状态判断
		var flag3 = 0;
		$("input[name='checkedManageScopes']").each(function(){
			if($(this).attr('checked') == true){
				flag3 = 1;
			}
		});
		if(flag3 == 0){
			$.boxUtil.warn("请至少选择一个角色管理适用范围");
			return;
		}
		*/
		var t = $("#menuTree").jstree("get_checked");
		var size = t.size();
		var msg = "";
		if(size>0){
			msg = msg + $(t[0]).attr('ti')+'-'+$(t[0]).attr("id");
		}
		for( var i=1;i<size;i++){
			var temp = $(t[i]).attr('ti')+'-'+$(t[i]).attr("id");
			msg = msg + ","+ temp;
		}
		$('#grantedMenuIdString').attr('value',msg);
		
		$("#roleEditForm").append('<input type="hidden" name="editMenu" value="'+editMenu+'" />');
		$("#roleEditForm").submit();
		
	});
	
	$("#role-edit-back").click(function(){
		window.returnValue  =$("#id").val();
		window.close();
	});
	
});
	
</script>
</head>
<body>
<form id="roleEditForm" name="roleEditForm" action="roleSaveOrUpdate.action" method="post">
<div class="layout" style="min-width: 1000px">
<div class="block m-b">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<div class="fieldset_border fieldset_bg m-b">
<div class="legend_title"><a href="#" class="container_show">角色信息</a></div>
<div class="fieldset_container">
<input type="hidden" name="id" id="id" value="${role.id}" /> 
<input type="hidden" name="grantedMenuIdString" id="grantedMenuIdString" />
<input type="hidden" name="checkedScopeStr" id="checkedScopeStr" />
<input type="hidden" name="saveResult" id="saveResult" value="${saveResult }"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td align="right"><label for="roleCode"><em style="color:red">*</em>代码:</label></td>
		<td ><input type="text" name="roleCode" id="roleCode" value="${role.roleCode }" size="25" maxlength="100"
			class="{required : true, remote: {url: 'checkRoleCode.action', type: 'post', dataType: 'json', data: {}}}"/></td>
		<td align="right"><label for="roleName"><em style="color:red">*</em>名称:</label></td>
		<td ><input type="text" name="roleName" id="roleName" value="${role.roleName }" size="25" maxlength="100" class="{required : true}"/></td>
		<td align="right">
			<label for="appId">所属应用:</label>
		</td>
		<td>
			<select id="appId" name="appId">
				<c:forEach items="${appList }" var="item">
					<option value="${item.key }" <c:if test="${role.appId eq item.key }">selected</c:if>>${item.value }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<%-- <tr>
		<td align="right"><label for="checkedManageScopes"><em style="color:red">*</em>角色管理适用范围:</label></td>
		<td>
			<s:checkboxlist name="checkedManageScopes" list="%{manageScopeList }" listKey="key" listValue="value" ></s:checkboxlist>
		</td>
	</tr> --%>
	<tr>
		<td align="right"><label for="description">描述:</label></td>
		<td colspan='3'><textarea class="textarea" style="width:600px" rows="3" id="description" name="description">${role.description }</textarea>
		</td>
	</tr>
</table>
<div class="btn_layout">
	<a id="role-edit-ok" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">保 存</span> </span> </a> 
	<a id="role-edit-back" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a></div>
</div>
</div>

<div
	class="tabs ui-tabs ui-widget ui-widget-content ui-corner-all leftOrgTree"
	id="tabs2" style="height: 500px; background: #ffffee">
<div id="menuTree" class="jstree"></div>
</div>
</div>
</div>
</div>
<div id="loading_save" style="display:none">
	<span class="loading_span">正在处理，请稍候...</span> <br/>
	<img  	src="<c:url context='/common' value='/images/framework/ajax-loader.gif'/>" />
</div>
</form>
</body>
</html>