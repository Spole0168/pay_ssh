<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
var dlgEditId = "#dialog-ajax-role-edit";
var dlgUserId = "#dialog-ajax-role-user";
var dlgMenuId = "#dialog-ajax-role-menu";
var dlgOperId = "#dialog-ajax-role-oper";
var gridId = "#roleList";

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
	
		var statusColumnRender = $('#statusColumnRender').attr('value'); // column render
		
		$("#roleList").jqGrid({
		   	url:'roleSearch.action?rowNum=50',
		   	editurl:"roleDelete.action",
			datatype: "json",
			mtype: "POST",
			rownumbers: true,
			colNames : [ 'id', '代 码', '名 称', '描述','管理员', '是否可删除','操作' ],
			colModel : [ {name : 'id', index : 'id', width : '10%', hidden:true},
			             {name : 'roleCode', index : 'roleCode', width : '20%',sortable:true}, 
			 			 {name : 'roleName', index : 'roleName', width : '30%'}, 
			 			 {name : 'description', index : 'description', width : '30%'}, 
			 			 {name : 'isAdmin', width : '30%',hidden:true}, 
			 			 {name : 'deletable', width : '30%',hidden:true}, 
			 			 {name : 'operation', index : 'operation', width : '20%', sortable : false} ],
		   	rowNum:50,
		   	multiselect:true,
		   	rowList:[50,100,200],
		   	pager: '#rolePager',
		   	altRows:true,
			altclass:'altClass',
			sortname:"roleCode",
			sortorder:"asc",
			sortable:true,
		   	autowidth:true,	 
		   	toolbar:[true, 'top'],	   	
		   	height: 'auto', 
		   	hidegrid: false, // 隐藏
		    viewrecords: true,
		    jsonReader: {
	      		root: "rows",
	      		page: "page",
	     		total: "total",
	     		records: "records", 
	        	repeatitems : false
	     	},
	     	loadError : function(xhr,st,err) {
	    	},
	    	onPaging : function(pgButton){
	    		$("#search").click();
	    	},
	    	
		    caption:"角色列表",
		  //加载结束后处理,处理session过期
		    loadComplete: function(data){
	    		if(data.success=="false"){
	    			alert(data.text);
	    		}	
	    	},
		    //增加操作列	    	
		    gridComplete: function(){
	    		 var ids = $("#roleList").getDataIDs();//jqGrid('getDataIDs');
	             for(var i=0;i<ids.length;i++){
	                 var id = ids[i];
					var data = $("#roleList").jqGrid('getRowData',id);
					var isAdmin = data.isAdmin;
				//	var createOrgId = data.createOrgId;
	                 var editButton = "<a onclick='modifyRole(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">修改</span></span></a>";
	                 var userButton = "<a onclick='roleUser(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">用户</span></span></a>";
	                 var menuButton = "<a onclick='roleMenu(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">所管理资源</span></span></a>";
	            //     var operButton = "<a onclick='roleOper(\"" + id + "\")' href=\"#\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">操作</span></span></a>";
	            	var menus = userButton;
	            	//    if(createOrgId=="${currentOrgId}"){
	                	//$("#roleList").jqGrid('setRowData',id,{operation:editButton+userButton});
	                	menus += editButton;
// 	                	if(isAdmin=='Y' || isAdmin == 'y'){
// 		                	menus += menuButton;
// 	                	}
	                // }
	                $("#roleList").jqGrid('setRowData',id,{operation:menus});
	                             
	             } 

	    	}
		    	
		});
		$("#toolbar").appendTo($("#t_roleList"));
		$("#create").click(function(){
		//	$('#roleForm').attr('action',
		//			'roleCreate.action');
		//	$("#roleForm").submit();
			var title = "新增角色";
			var url = "roleCreate.action";
		//	$(dlgEditId).dialog({width:400,height:250,modal:true});
		//	openDialog(dlgEditId,title,url);
			var dl_height = screen.height<760?screen.height:760;
			var dl_width = screen.width<1000?screen.width:1000;
			var dl_top = (screen.height-dl_height)/2;
			var dl_left = (screen.width-dl_width)/2;
	
			var a =  window.showModalDialog(
		 		url,
		 		window,
		 		"help:no;scroll:auto;resizable:no;status:no;dialogWidth:"+dl_width+"px;dialogHeight:"+dl_height+"px;center:yes;dialogTop:"+dl_top+"px;dialogLeft:"+dl_left+"px" );
		
	/*		var a =  window.showModalDialog(
					 url,
					 window,
					"help:no;scroll:auto;resizable:no;status:no;dialogWidth:1000px;dialogHeight:600px;dialogTop:100px; dialogLeft:100px;title:"+title );
	*/		
			if(a != null){
				$("#roleList").trigger("reloadGrid");
				}
			
		});
		$("#search").click(function(){
			var url = "roleSearch.action?rowNum=" + $("#roleList").jqGrid('getGridParam','rowNum');
			var roleCode = $('#roleCodeSearch').val();
			var roleName = $('#roleNameSearch').val();
			var description = $('#descriptionSearch').val();
			var roleScope = $("#roleScopeSearch").val();
			var roleApp = $("#roleAppSearch").val();
			var pageCache = $("#pageCache").val();
			$("#roleList").appendPostData({roleCode:roleCode,roleName:roleName,description:description,
				roleScope:roleScope,roleApp:roleApp,pageCache:pageCache});
			
			$("#roleList").jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
				
			// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
			$("#roleList").jqGrid("setGridParam",{page:1});
		});
			$("#reset").click(function() {
				$("#roleCodeSearch").val("");
				$("#roleNameSearch").val("");
				$("#descriptionSearch").val("");
				$("#roleScopeSearch").val("");
				$("#roleAppSearch").val("");
			});
			$("#roleUserMapping").click(
					function() {
						var id = $("#roleList")
								.jqGrid('getGridParam', 'selrow');

						if (id) {
							$('#roleForm').attr('action',
									'toAssignedUser.action?id=' + id);
							$("#roleForm").submit();
						} else {
							alert('请选择角色记录!');
						}
					});

			$("#roleMenuMapping").click(
					function() {
						var id = $("#roleList")
								.jqGrid('getGridParam', 'selrow');
						if (id) {
							$('#roleForm').attr('action',
									'toAssignedMenu.action?id=' + id);
							$("#roleForm").submit();
						} else {
							alert('请选择角色记录!');
						}
					});
			$("#roleOperMapping").click(
					function() {
						var id = $("#roleList")
								.jqGrid('getGridParam', 'selrow');
						if (id) {
							$('#roleForm').attr('action',
									'toAssignedOper.action?id=' + id);
							$("#roleForm").submit();
						} else {
							alert('请选择角色记录!');
						}
					});
			$("#delete").click(function(){
				var selectIds = $("#roleList").jqGrid('getGridParam','selarrrow');
				var undeletable = "";
				var nopermission = "";
				if (selectIds!=null && selectIds!="")	{
					for(var i=0;i<selectIds.length;i++){
		                 var id = selectIds[i];
		                 var data = $("#roleList").jqGrid('getRowData', id);
						if(data.deletable=='false'){
							undeletable += "“"+data.roleName+"”，";
						}
						if(data.createOrgId != "${currentOrgId}"){
							nopermission += "“"+data.roleName+"”，";
						}
					}
					var warn = "";
					if(undeletable != ""){
						warn += "角色"+undeletable+"为系统角色，不允许删除<br/>";
							
					}
					/* if(nopermission != ""){
						warn += "角色"+nopermission+"非当前组织创建角色，不允许删除<br/>	";
					} */
					if(warn != ""){
						$.boxUtil.warn(warn);
					}else {
					$("#roleList").jqGrid('delGridRow',selectIds,{ajaxDelOptions:{dataType:"JSON"} 		 
					});
					}

					
				} else{
					alert('请选择实例记录!');
				}
			});

			$("#role_search_table input").input().enter(function(){
				$("#search").click();
			});

		});

	function modifyRole(id){
		var url = "roleModify.action?id="+id+"&time="+new Date().getTime();
		var title = "修改角色信息";
	//	$(dlgEditId).dialog({width:400,height:200,modal:true});
	//	openDialog(dlgEditId,title,url);
	var dl_height = screen.height<760?screen.height:760;
	var dl_width = screen.width<1000?screen.width:1000;
	var dl_top = (screen.height-dl_height)/2;
	var dl_left = (screen.width-dl_width)/2;
	
	var a =  window.showModalDialog(
		 url,
		 window,
		 "help:no;scroll:auto;resizable:no;status:no;dialogWidth:"+dl_width+"px;dialogHeight:"+dl_height+"px;center:yes;dialogTop:"+dl_top+"px;dialogLeft:"+dl_left+"px" );
	
	/*	var a =  window.showModalDialog(
				 url,
				 window,
				"help:no;scroll:auto;resizable:no;status:no;dialogWidth:1000px;dialogHeight:600px;dialogTop:100px; dialogLeft:100px;title:"+title );
	*/	if(a != null){
			$("#roleList").trigger("reloadGrid");
		}
	}
	
	function roleUser(id){
		var url = "toAssignedUser.action?id="+id+"&time="+new Date().getTime();
		var title = "角色-用户信息";
		$(dlgUserId).dialog({width:900,height:600,modal:true});
		openDialog(dlgUserId,title,url);
	}
	
	function roleMenu(id){
		var url = "toAssignedMenu.action?id="+id+"&time="+new Date().getTime();
		var title = "角色-菜单信息";
		var dl_height = screen.height<760?screen.height:760;
		var dl_width = screen.width<1000?screen.width:1000;
		var dl_top = (screen.height-dl_height)/2;
		var dl_left = (screen.width-dl_width)/2;
		
		var a =  window.showModalDialog(
			 url,
			 window,
			 "help:no;scroll:auto;resizable:no;status:no;dialogWidth:"+dl_width+"px;dialogHeight:"+dl_height+"px;center:yes;dialogTop:"+dl_top+"px;dialogLeft:"+dl_left+"px" );
			
	/*	var a =  window.showModalDialog(
				 url,
				 window,
				"help:no;scroll:auto;resizable:no;status:no;dialogWidth:1000px;dialogHeight:600px;dialogTop:100px; dialogLeft:100px;" );
	*/	if(a != null){
			$("#roleList").trigger("reloadGrid");
		}
	//	$(dlgMenuId).html("");
	//	$(dlgMenuId).dialog({width:800,height:600,modal:true});
	//	openDialog(dlgMenuId,title,url);
	}
	function roleOper(id){
		var url = "toAssignedOper.action?id="+id+"&time="+new Date().getTime();;
		var title = "角色-操作信息";
		$(dlgOperId).dialog({width:800,height:700,modal:true});
		openDialog(dlgOperId, title,url);
	}

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
	}

	
</script>

<form id="roleForm" action="roleList.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_title">
	   <h3>角色列表</h3>
	</div>
	<div class="block_container">
	<div id='role_search_table' class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">角色查询</a></div>
	<div class="fieldset_container">
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
		<!-- <td width="10%" align="right"><label for="roleCodeSearch">代码:</label></td> -->
		<td width="10%" align="right"><label for="roleCodeSearch">代码:</label></td>
		<td width="35%"><input type="text" name="roleCodeSearch" id="roleCodeSearch" size="25" maxlength="100" /></td>
		<td width="10%" align="right"><label for="roleNameSearch">名称:</label></td>
		<td width="35%"><input type="text" name="roleNameSearch" id="roleNameSearch" size="25" maxlength="100" /></td>
		</tr>
		<tr>
		<td width="10%" align="right">
				<label for="roleAppSearch">所属应用:</label>
		</td>
		<td width="23%">
				<select id="roleAppSearch" name="roleAppSearch">
					<c:forEach items="${appList }" var="item">
						<option value="${item.key }">${item.value }</option>
					</c:forEach>
				</select>
		</td>
		<td width="10%" align="right"><label for="descriptionSearch">描述:</label></td>
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
<table id="roleList"></table>
<div id="rolePager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
<div id="dialog-ajax-role-edit"></div>
<div id="dialog-ajax-role-user"></div>
<div id="dialog-ajax-role-menu"></div>
<div id="dialog-ajax-role-oper"></div>
</form>
