<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ include file="/common/metas.jsp"%>
<!-- 包含简单表格的JavaScript -->
<html>
<head>
<base target="_self"/>
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
<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/js/jstree-1.0rc2/themes/default/style.css'/>" />
<script type="text/javascript" src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
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
	"core":{
		"initially_open": ["${assignedMenus}"]
	},
	"ui" :{
		 "select_multiple_modifier" : "on",
		 "initially_select" : [<c:if test='${not empty assignedMenus }'>"${assignedMenus }"</c:if>]
	},
		"plugins" : [ "themes", "html_data" ,"ui","checkbox2","crrm","types"]
	})
	.bind("click", function (e){
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
				alert("子菜单有被选中");
				$("#menuTree").jstree('check_node','#'+id);
			}
		}
	});

	$("#role-menu-ok").click(function(){
		var t = $("#menuTree").jstree("get_checked");
		var size = t.size();
		var msg = "";
		if(size>0){
			//msg = msg + $(t[0]).attr('ti')+'-'+$(t[0]).attr("id");
			msg = msg + $(t[0]).attr("id");
			
		}
		for( var i=1;i<size;i++){
//			var temp = $(t[i]).attr('ti')+'-'+$(t[i]).attr("id");
			var temp = $(t[i]).attr("id");
			msg = msg + ","+ temp;
		}
		$('#grantedMenuIdString').attr('value',msg);
		$("#userForm").attr("action", "assignManageMenu.action");
		document.userForm.submit();
		window.returnValue  =$("#id").val();
		window.close();
	/*	$.ajax({
			async:true, 
			type:"post", 
			dataType:"html", 
			url:"assignMenu.action",
			data:"id="+encodeURI($("#id").val())+"&grantedMenuIdString="+encodeURI(msg), 
			success:function(data) {
				if(data != ''){
					alert("保存成功!");
				}
				$("#menuTree").jstree('destroy');
				$("#roleList").trigger("reloadGrid");
				$("#dialog-ajax-role-menu").dialog('close');
			}
		})*/
	});
	
	$("#role-menu-back").click(function(){
	/*	$("#menuTree").jstree('destroy');
		$("#roleList").trigger("reloadGrid");
		$("#dialog-ajax-role-menu").dialog('close');*/
		window.returnValue  =$("#id").val();
		window.close();
	});
	
});
	
	
</script>
</head>
<body>
<form id="userForm" name='userForm' action="roleList.action"
	method="post">
<div class="layout">
<div class="block m-b">
<div class="block_container">
<div class="fieldset_border fieldset_bg m-b">
<div class="legend_title"><a href="#" class="container_show">角色信息</a></div>
<div class="fieldset_container"><input type="hidden" name="id"
	id="id" value="${role.id}" /> <input type="hidden"
	name="grantedMenuIdString" id="grantedMenuIdString" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td width="10%" align="right"><label for="roleCode">代码:</label></td>
		<td width="35%"><input type="text" name="roleCode" id="roleCode"
			value="${role.roleCode }" size="25" maxlength="100"
			disabled="disabled" /></td>
		<td width="10%" align="right"><label for="roleName">名称:</label></td>
		<td width="35%"><input type="text" name="roleName" id="roleName"
			value="${role.roleName }" size="25" maxlength="100"
			disabled="disabled" /></td>
		<td width="10%" align="right"><label for="description">描述:</label></td>
		<td width="35%"><input type="text" name="description"
			id="description" size="25" value="${role.description }"
			maxlength="100" disabled="disabled" /></td>
	</tr>
</table>
<div class="btn_layout"><a id="role-menu-ok"
	class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span
	class="l-btn-text icon-save">保 存</span> </span> </a> <a id="role-menu-back"
	class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span
	class="l-btn-text icon-undo">返 回</span></span></a></div>
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
</form>
</body>
</html>