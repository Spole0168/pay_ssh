<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<div id="container" class="layout block m-b">
<s:form id="menuForm" action="menu.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_title">
	   <h3>菜单及操作权限管理</h3>
	</div>
	<div class="block_container">
	<div class="fieldset_border fieldset_bg m-b">
<div class="tabs ui-tabs ui-widget ui-widget-content ui-corner-all leftOrgTree " id="tabs2" style="background: #ffffee">	
<div id="menuTree" class="jstree"></div>
</div>
</div>
<div class="fieldset_border fieldset_bg m-b">
<div class="fieldset_container">
<input id="menuId" name="menuId" type="hidden"/>
<input id="menuType" name="menuType" type="hidden"/>
<input id="subCount" name="subCount" type="hidden"/>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td width="10%" align="right"><label for="locationDis">菜单地址</label></td>
		<td width="60%"><input type="text" name="locationDis" size=120" id="locationDis" readonly/></td>
	</tr>
</table>
<div class="btn_layout">
		 
		<a id="add" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-add">新 增</span></span></a> 
		<a id="modify" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-edit">修 改</span></span></a> 
<!-- 		<a id="link_mac" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-edit">绑定MAC地址</span></span></a>  -->
		<a id="deleteMenu" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-remove">删 除</span></span></a>
		<a id="reset" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-reset">重 置</span></span></a>
	</div>
</div>
</div>

</div>
</div>
</div>
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
</style>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/js/jstree-1.0rc2/themes/default/style.css'/>" />
<script type="text/javascript" src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<script type="text/javascript" charset="UTF-8">
var dlgId = "#dialog-ajax-menu-edit";
var jstree = $("#menuTree");
$().ready(function() {
	
	reset();
	
	$("#menuTree").jstree({
		 "html_data" : {
			"ajax" : {
				"url" : "getMenuNode.action?time="+new Date().getTime(),
				"data" : function (n) {
					return { nodeId : n.attr ? n.attr("id") : 0 };
				}
			}
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
		"search" : {
			"case_insensitive" : true,
			"ajax":{
				"url":"searchMenu.action"
			}
		},
		"plugins" : [ "themes", "html_data" ,"ui","crrm","types","search"]
	})
	.bind("select_node.jstree", function(e,data){
		$("#menuId").val(data.rslt.obj.attr("id"));
		$("#menuType").val(data.rslt.obj.attr("ti"));
		$("#menuNameDis").val(data.rslt.obj.attr("tn"));
	//	$("#menuTypeDis").val(data.rslt.obj.attr("ti"));
		$("#locationDis").val("null"==data.rslt.obj.attr("extra")?"":data.rslt.obj.attr("extra"));
		$("#subCount").val(data.rslt.obj.attr("subCount"));
		$("#menuTree").jstree('open_node','#'+data.rslt.obj.attr("id"));
	});

	
	$("#add").click(function(){
		var type = $("#menuType").val();
		if(!("menu"==type || "guimenu"==type || "pdamenu"==type || ""==type)){
			$.boxUtil.warn('操作权限下面禁止新增菜单或其他类型操作权限', {title: ''}, function(){
			});
			return;
		}
		var title = "增加菜单信息";
		var url = 'menuCreate.action?parentMenuId=' + $("#menuId").attr('value')+'&menuType='+type; 
		//	openDialog(title, url);
		var dl_height = screen.height<600?screen.height:600;
		var dl_width = screen.width<800?screen.width:800;
		$(dlgId).dialog({width:dl_width,height:dl_height,modal:true});
		openDialog(dlgId, title, url);
	
// 	var dl_top = (screen.height-dl_height)/2;
// 	var dl_left = (screen.width-dl_width)/2;
	
// 	var a =  window.showModalDialog(
// 		 url,
// 		 window,
// 		 "help:no;scroll:auto;resizable:no;status:no;dialogWidth:"+dl_width+"px;dialogHeight:"+dl_height+"px;center:yes;dialogTop:"+dl_top+"px;dialogLeft:"+dl_left+"px" );
	
// /*		var a =  window.showModalDialog(
// 				 url,
// 				 window,
// 				"help:no;scroll:auto;resizable:no;status:no;dialogWidth:800px;dialogHeight:600px;dialogTop:200px; dialogLeft:200px;" );
// */		if(a != null && a != '' && a.length==2){
// 			if(a[0]!=''){
// 				$("#menuTree").jstree("refresh","#"+a[0]);
// 			}else{
// 				$("#menuTree").jstree("refresh");
// 			}
// 			if(a[1]!='' && a[1]!=a[0]){
// 				$("#menuTree").jstree("refresh","#"+a[1]);
// 			}else{
// 				$("#menuTree").jstree("refresh");
// 			}
// 		}else{
// 			$("#menuTree").jstree("refresh");
// 		}
	});
	
	// 修改 页面跳转
	$("#modify").click(function(){
		var id = $("#menuId").attr('value');
		
		if (id)	{
			var title = "修改菜单信息";
			var url = 'menuModify.action?id=' + id+"&time="+new Date().getTime(); 

			var dl_height = screen.height<600?screen.height:600;
			var dl_width = screen.width<800?screen.width:800;
			$(dlgId).dialog({width:dl_width,height:dl_height,modal:true});
			openDialog(dlgId, title, url);
// 	var dl_top = (screen.height-dl_height)/2;
// 	var dl_left = (screen.width-dl_width)/2;
	
// 	var a =  window.showModalDialog(
// 		 url,
// 		 window,
// 		 "help:no;scroll:auto;resizable:no;status:no;dialogWidth:"+dl_width+"px;dialogHeight:"+dl_height+"px;center:yes;dialogTop:"+dl_top+"px;dialogLeft:"+dl_left+"px" );
		
// 	/*		var a =  window.showModalDialog(
// 					 url,
// 					 window,
// 					"help:no;scroll:auto;resizable:no;status:no;dialogWidth:800px;dialogHeight:600px;dialogTop:100px; dialogLeft:100px;" );
// 	*/		
   
// 	if(a != null && a != '' && a.length==2){
// 				if(a[0]!=''){
// 					$("#menuTree").jstree("refresh","#"+a[0]);
// 				}else{
// 					$("#menuTree").jstree("refresh");
// 				}
// 				if(a[1]!='' && a[1]!=a[0]){
// 					$("#menuTree").jstree("refresh","#"+a[1]);
// 				}else{
// 					$("#menuTree").jstree("refresh");
// 				}
// 			}else{
// 				$("#menuTree").jstree("refresh");
// 			}
		} else{
			alert('请选择一条记录!');
		}
	});
	
	$("#deleteMenu").click(function(){
		var id = $("#menuId").attr('value');
		var url = '<c:url value="/permission/menuDelete.action"/>';
		if (id)	{
			$("#dialog-delete-confirm").dialog({
				autoOpen: false,
				bgiframe:true,
				width: 200,
				height:150,
				modal:true,
				buttons: {
					"确定": function() {
						$.ajax({url: url,
							type: 'POST',
							data: {id : id},
							dataType: 'json',
							//timeout: 1000,
							error: function(){
								//alert('err!');
								$.boxUtil.error('连接网络超时!');
							},
							success: function(data){
								if($(data) && 'false' == $(data).attr('success')){
									$.boxUtil.error('删除失败:' + $(data).attr('text'));	
								} else{
									$("#menuTree").jstree("remove");
									$("#menuTree").jstree("refresh");
									$.boxUtil.success('删除成功');
								}
							}			
						});	
						$(this).dialog("close");
					},
					"取消": function() {
						$(this).dialog("close");
					}
				}
			});
			$("#dialog-delete-confirm").dialog('open');
		} else{
			$.boxUtil.warn('请选择要删除的菜单!');
		}
	});
	
	$('#dialog-delete-confirm').dialog({
		autoOpen: false
	});
	
// 	$("#view").click(function(){
// 		var id = $("#menuId").attr('value');;
		
// 		if (id)	{
// 			$('#menuForm').attr('action','menuView.action?id=' + id);
// 			$("#menuForm").submit(); 
// 		} else{
// 			$.boxUtil.warn('请选择一条记录!');
// 		}
// 	});

	$("#reset").click(function(){
		reset();
	});

// 	$("#link_mac").click(function(){
// 		var type = $("#menuType").val();
		
// 		if ("menu" == type) {
// 			var loc = $("#locationDis").val();
// 			if (loc == null || loc == "" || loc == "null") {
// 				$.boxUtil.warn('该web菜单没有URL地址，不能添加MAC地址验证');
// 				return;
// 			}
// 		} else if ("button" == type) {
			
// 		} else {
// 			$.boxUtil.warn('只允许Web菜单和按钮才能添加MAC地址验证');
// 			return;
// 		}
		
// 		var url = "toBindingMenuToMac.action?menuId="+$("#menuId").val() + "&rand=" + Math.random();
// 		var title = "菜单MAC地址绑定";
// 		$("#dialog-ajax-menu-mac").dialog({width:800,height:600,modal:true});
// 		openDialog("#dialog-ajax-menu-mac",title,url);
		
// 	});
});	

function reset() {
	$("#menuId").val("");
	$("#menuNameDis").val("");
	$("#menuTypeDis").val("");
	$("#locationDis").val("");
	$("#menuType").val("");
	$("#subCount").val("");
	$("#menuTree").jstree("deselect_all");
	$("#menuTree").jstree("close_all");
}

var dlgId = "#dialog-ajax-menu-edit";

// function openDialog(title, url){
// 	$(dlgId).html("");
// 	$(dlgId).dialog({
// 		autoOpen:false,
// 		width:600,
// 		height:350,
// 		position:'center',
// 		modal:true,
// 		title:title
// 		});
// 	$(dlgId).load(url);
// 	$(dlgId).dialog('open');
// 	$(dlgId).dialog({
// 		   close: function(event, ui) { 			   
// 		  		$(dlgId).html(" ");			   
// 			}  
// 	});
// }

function openDialog(dlgId, title, url){
	$(dlgId).html("");
	$(dlgId).dialog({
		autoOpen:false,
		position:'center',
		modal:true,
		resizable: false,
		title:title
		});
	$(dlgId).load(url);
	$(dlgId).dialog('open');
	$(dlgId).dialog({
		   close: function(event, ui) { 			   
		  		$(dlgId).html(" ");			   
			}  
	});
}

function closeDialog(){
// 	alert("5");
	$(dlgId).dialog('close');
	$("#menuTree").jstree("refresh",'#menuTree');
}
</script>
<div id="dialog-ajax-menu-edit"></div>
<div id="dialog-delete-confirm" title="删除">
	<p>删除所选菜单？</p>
</div>
<div id="dialog-ajax-menu-mac"></div>
</s:form>
</div>
