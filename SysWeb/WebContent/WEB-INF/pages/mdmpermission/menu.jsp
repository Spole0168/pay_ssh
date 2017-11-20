<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript">

var oldParentMenuId = "${parentMenuId}";

function checkName() {
	var id=$("#id").val();
	var returnval="";
	var postData = "";
	var menuType = $("#menuType1").val();
	var message=""; 
	var menuName = $("#menuName").val(); 
	if(menuName==null||menuName==""){
		return 0;
	}
	
	if(menuType!="button"){
		return 0;
	}
	
	postData = "menuName=" + menuName+"&menuType="+menuType+"&id="+id; 
	$.ajax({
		type : "post",
		dataType : "json",
		url : "validValue.action",
		data : postData,
		async:false,
		success : function(data) {
			if (data.success == "false") {
				$.boxUtil.error(data.text);
				returnval=1;
				return ;
			}
			if (data.code > 0) {
				message="该按钮名称已经存在！";
				$("div.warning span").html(message);
				$("div.warning").show();
				returnval=1;
				return ;
			}
			$("div.warning").hide();
			returnval=0;
			return ;
		}
	});
	return returnval;
}	


$().ready(function() {
	var id= $("#id").val();
	if(id != null && id != ""){
		$("#menuType1").attr("disabled","disabled");
	}
	$("#menuSaveOrUpdateForm").validate( {
		rules : {
			menuName : {
				required : true
				/* remote : {
					url : "menuValidateName.action", //后台处理程序   
					type : "post", //数据发送方式   
					dataType : "json", //接受数据格式      
					data : { //要传递的数据，默认已传递应用此规则的表单项   
						"menuType": $('#menuType').val() 
					}
				} */
			},
			appId : {
				required : true
			},
			menuTitle : {
				required : true
			},
			displayOrder : {
				digits:true
			}
		},
		invalidHandler : function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				$('#editblockidiv').unblock();
				var message = "请正确填写信息！";
				$("div.warning span").html(message);
				$("div.warning").show();
			} else {
				$('#editblockidiv').unblock();
				$("div.warning").hide();
			}
		},
		onkeyup : false,
		submitHandler : function() {
		 	menuSave();
			//document.menuSaveOrUpdateForm.submit();
			
		},
		messages : {
			displayOrder:"请输入整数",
			"menuName" : {
				remote : "已存相同名称的菜单！"
			},
			"menuTitle" : {
				remote : "已存在相同显示名称的菜单！"
			},
		}
	});
	$("#menu-save").click(function() {
	   var returnval= checkName();		  
	   if(returnval>0)
	   {
		   //$('#editblockidiv').unblock();
		   return;
		}
		$('#editblockidiv').block({ 
				message: '正在处理，请稍候...',
				css: {padding: '10px'}
	 	});
		$("#menuSaveOrUpdateForm").submit();
	});
	$("#menu-back").click(function(){
		//$(dlgId).dialog("close");
		back();
	});
	$("#application").click(function(){
		var url = "menuApp.action";
		var title = "所属应用";
		$("#dialog-ajax-select-app").dialog({
			title:title
		});
		 $("#dialog-ajax-select-app").load(url);
		 $("#dialog-ajax-select-app").dialog('open');
		return true;
	
	});
	$("#parentMenu").click(function(){
		var url = "menuTree.action";
		var title = "上级菜单";
		$("#dialog-ajax-select-parent").dialog({
			title:title
		});
		$("#dialog-ajax-select-parent").load(url);
		$("#dialog-ajax-select-parent").dialog('open');
		return true;
	
	});
	$("#dialog-ajax-select-app").dialog({
		autoOpen: false,
		bgiframe:true,
		modal:true,
		width: 550,
		height:400
	});
	$("#dialog-ajax-select-parent").dialog({
		autoOpen: false,
		bgiframe:true,
		modal:true,
		width: 550,
		height:500
	});

	buildPopupGrid({

		caption:"菜单对应角色",
		gridName:"roleListGrid",
		formName:"menuSaveOrUpdateForm",
		pagerName:"roleListPager",
		searchButtonName:"search",
		deleteButtonName:"delete",
		createButtonName:"create",
		modifyButtonName:"modify",
		modifyFunctionName:"modify",
		searchUrl:"menuSearchRole.action?id="+$("#id").val(),
		
		colNames:[ "${app:i18n('mdmproduct.commonJsp.codeTitle')}", 
					"${app:i18n('mdmproduct.commonJsp.nameTitle')}",
					"${app:i18n('mdmproduct.commonJsp.descriptionTitle')}"],
		colModel:[ 			  
				    {name : 'roleCode',index : 'roleCode',width : '30%'},
				    {name : 'roleName',index : 'roleName',width : '30%'},					   				  
				    {name : 'description',index : 'description',width : '30%'},
		],
		searchParameters: ["pageCache"],
		sortName:"roleCode",
		sortOrder:"asc"	
	});
	
});
function back(){
// 	var	id = $("#parentMenuId").val();
// 	var ids = [];
// 	ids.push(id);
// 	ids.push(oldParentMenuId);
// 	window.returnValue = ids;
	 $.unblockUI();
	 closeDialog();
// 	window.close();
}
function menuSave(){
	var id=$("#id").val();
	var appId=$("#appId").val();
	var parentMenuId=$("#parentMenuId").val();
	var menuName=$("#menuName").val();
	var menuTitle=$("#menuTitle").val();
	var menuTitleEn=$("#menuTitleEn").val();
	var location=$("#location").val();
	var displayOrder=$("#displayOrder").val();
	var description=$("#description").val();
// 	var target=$("#target").val();
	var menuType=$("#menuType1").val();
	if(menuName == "" || menuTitle == ""){
		return ;
	}
	var data = "menuName="+encodeURI(menuName)+"&menuTitle="+encodeURI(menuTitle);
	if(id != ""){
		data = data+"&id="+id;
	}
	if(appId != ""){
		data = data+"&appId="+appId;
	}
	if(parentMenuId != ""){
		data = data+ "&parentMenuId="+parentMenuId;
	}
	if(menuTitleEn != ""){
		data = data+"&menuTitleEn="+encodeURI(menuTitleEn);
	}
	if(location != ""){
		data = data+"&location="+encodeURI(location);
	}
// 	if(target != ""){
// 		data = data+"&target="+target;
// 	}
	if(displayOrder != ""){
		data = data+"&displayOrder="+displayOrder;
	}
	if(id != ""){
		data = data+ "&description="+encodeURI(description);
	}
	data = data + "&menuType="+menuType;

	 
	 $.blockUI({
			message: $('#loading_menu_save').html(),
				css: {padding: '10px'}
			});
	
	$.ajax({
		async:false, 
		type:"post", 
		dataType:"json", 
		url:"menuSaveOrUpdate.action",
		data:data, 
		success:function(data) {
// 		alert("4");
		back();
		
		/*
			if(id != "" && oldParentMenuId != parentMenuId){
				$("#menuTree").jstree("refresh","#"+oldParentMenuId) ; 
				alert("1");
			} 
			if("" == parentMenuId){
				$("#menuTree").jstree("refresh");
				alert("2");
			}else{
				//alert(parentMenuId);
				$("#menuTree").jstree("refresh","#"+parentMenuId) ;
				alert("3");
			} 
			$(dlgId).dialog('close');
		*/
		}
	});
	
}

function setAppInfo(appId, appName, description){
	$("#appId2").val(appId);
	$("#appName").val(appName);
	$("#dialog-ajax-select-app").dialog("close");
}
function setParentMenu(menuId, menuTitle){
	$("#parentMenuId").val(menuId);
	$("#parentMenuTitle").val(menuTitle);
	$("#menuTreePopup").jstree("destroy");
	$("#dialog-ajax-select-parent").dialog("close");
}
function resetType(){
	checkName();
	var menuType = $("#menuType1").val();
	$("#menuTypeValue").val(menuType);
}
</script>
<div id="editblockidiv" class="layout block m-b">
<form method="post" action="menuSaveOrUpdate.action" name="menuSaveOrUpdateForm" id="menuSaveOrUpdateForm">
<div class="layout">
<div class="block m-b">
<div class="block_container">
<div class="warning" style="display: none;"><span></span></div>
<input id="id" name="id" type="hidden" value="${menu.id}" />
<input id="appId2" name="appId2" type="hidden" value="${appId}" />
<input id="parentMenuId" name="parentMenuId" type="hidden" value="${parentMenuId}" />
<table width="100%" border="0" cellspacing="2" cellpadding="2">
				
	<tr>
		<td width="35%" align="right"><label for="menuName"><em style="color: red">*</em>名称:</label></td>
			<c:if test = "${ not empty menu.id }">
				<td width="65%"><input type="text" name="menuName" id="menuName" 
					size="25" maxlength="100" value="${menu.menuName}" /></td>
			</c:if>
			<c:if test = "${ empty menu.id }">
				<td width="65%"><input type="text" name="menuName" id="menuName" class="{required : true, remote: {url: 'checkMenuName.action', type: 'post', dataType: 'json', data: {}}}"
					size="25" maxlength="100" value="${menu.menuName}" /></td>
			</c:if>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="menuTitle"><em style="color: red">*</em>显示名称:</label></td>
			<c:if test = "${ not empty menu.id }">
				<td width="65%"><input type="text" name="menuTitle" id="menuTitle" 
					size="25" maxlength="100" value="${menu.menuTitle}" /></td>
			</c:if>
			<c:if test = "${ empty menu.id }">
				<td width="65%"><input type="text" name="menuTitle" id="menuTitle" class="{required : true, remote: {url: 'checkMenuTitle.action', type: 'post', dataType: 'json', data: {}}}"
				size="25" maxlength="100" value="${menu.menuTitle}" /></td>
			</c:if>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="menuTitleEn">英文显示名称:</label></td>
		<td width="65%"><input type="text" name="menuTitleEn" id="menuTitleEn"
			size="25" maxlength="100" value="${menu.menuTitleEn}" /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="location">路径:</label></td>
		<td width="65%"><input type="text" name="location" id="location"
			size="25" maxlength="100" value="${menu.location}" /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="menuType1">类型:</label></td>
		<td width="65%">
			<input type="hidden" id="menuTypeValue" name="menuTypeValue" value="${menu.menuType }" />
			<select id="menuType1" name="menuType1" onchange="resetType()">
				<c:forEach items="${menuTypeList }" var="item">
					<option value="${item.key }" <c:if test='${menu.menuType eq item.key }'>selected</c:if>>${item.value }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="appName"><em style="color: red">*</em>所属应用:</label></td>
		<!-- <td width="65%"><input type="text" name="appName" id="appName"
			size="25" maxlength="100" value="${appName}" readonly />
			<a class="a_btn_edit m-r" id="application"><em>选择</em></a>
		</td> -->
		<td>
			<select id="appId" name="appId">
				<c:forEach items="${appList }" var="item">
					<option value="${item.key }" <c:if test="${menu.application.id eq item.key }">selected</c:if>>${item.value }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td width="35%" align="right"><label for="target"><em style="color: red"></em>认证方式:</label></td> -->
<!-- 		<td> -->
<!-- 			<select id="target" name="target"> -->
<%-- 				<c:forEach items="${targetList }" var="item"> --%>
<%-- 					<option value="${item.key }" <c:if test="${menu.target eq item.key }">selected</c:if>>${item.value }</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td width="35%" align="right"><label for="parentMenuTitle">上级菜单:</label></td>
		<td width="65%"><input type="text" name="parentMenuTitle" id="parentMenuTitle"
			size="25" maxlength="100" value="${parentMenuTitle}" readonly/>
			<c:if test="${empty menu.id }">
			<a class="a_btn_edit m-r" id="parentMenu"><em>选择</em></a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="displayOrder">排序:</label></td>
		<td width="65%"><input type="text" name="displayOrder" id="displayOrder" class="digits"
			size="25" maxlength="9" value="${menu.displayOrder}" /></td>
	</tr>
	<tr>
		<td width="35%" align="right"><label for="description">描述:</label></td>
		<td width="65%">
		<textarea class="textarea" id="description" name="description" style="width: 200px" rows="2" >${menu.description }</textarea>
		</td>
	</tr>
</table>
<fieldset class="action">
<a id="menu-save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">保 存</span></span></a>
<a id="menu-back" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
</fieldset>
</div>


</div>
</div>
<div id="dialog-ajax-select-app">
</div>
<div id="dialog-ajax-select-parent">
</div>
 <div id="loading_menu_save" style="display:none">
	<span class="loading_span">正在处理，请稍候...</span> <br/>
	<img  	src="<c:url context='/common' value='/images/framework/ajax-loader.gif'/>" />
</div>
<table id="roleListGrid"></table>
<div id="roleListPager"></div>
<div id="tblMasterMessage"></div>
</form>
</div>
