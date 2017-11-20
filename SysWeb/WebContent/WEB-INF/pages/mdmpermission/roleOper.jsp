<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
	var menuTypeColumnRender = $('#menuTypeColumnRender').attr('value'); // column render
	$("#operList").jqGrid({
	   	url:'operSearch.action?rowNum=10',
		datatype: "json",
		mtype: "POST",
		rownumbers: true,
		colNames:[ 'ID', '名 称','类 型','路 径','描 述' ],
		colModel:[ 
				    {name : 'id',index : 'id',width : '20%', hidden:true},
				    {name : 'menuName',index : 'menuName',width : '20%'},
				    {name : 'menuType',index : 'menuType',width : '20%',formatter:'select', editoptions:{value:menuTypeColumnRender}},
				    {name : 'location',index : 'location',width : '20%'},
				    {name : 'description',index : 'description',width : '20%'},
		],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	pager: '#operPager',
	   	sortname: 'menuName',
	   	sortorder: "asc",
	   	autowidth:true,	   		   	
	   	height: 'auto', 
	   	multiselect: true, // 多选
	   	hidegrid: false, // 隐藏
	    viewrecords: true,
	    editurl:"operDelete.action", // 只用于删除
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
    	// onSelectRow : function(rowid){},
    	// loadBeforeSend : function(xhr){},
    	loadComplete : function(xhr){
    		var grantedOperIdString = $('#grantedOperIdString').attr('value');
    		
        	// 选中角色已有的资源
        	if(grantedOperIdString){
	    		var grantedOperArray = grantedOperIdString.split(',');
				$.each(grantedOperArray, function(n, value) {
					$("#operList").jqGrid('setSelection', value);
				});
        	}
        },
	    caption:"操作列表"
	});
	
	// 添加分页导航条
	$("#operList").jqGrid('navGrid','#operPager',{edit:false,add:false,del:false,search:false,refresh:true});	

	// 查询 ajax
	$("#role-oper-search").click(function(){
		var url = 'operSearch.action?rowNum=' + $('#operList').jqGrid('getGridParam','rowNum');
		
		var operNameSearch = $('#operNameSearch').val();
		var operTypeSearch = $('#operTypeSearch').val();
		var operValueSearch = $('#operValueSearch').val();
		var descriptionSearch = $('#descriptionSearch').val();
		var pageCache = $("#pageCache").val();
		$("#operList").appendPostData({operNameSearch:operNameSearch,
			operTypeSearch:operTypeSearch,
			operValueSearch:operValueSearch,
			descriptionSearch:descriptionSearch,
			pageCache:pageCache});
		
		$("#operList").jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
		// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
		$("#operList").jqGrid("setGridParam",{page:1});
	});
	$("#role-oper-reset").click(function(){
		$("#operNameSearch").val("");
		$("#descriptionSearch").val("");
		$("#operTypeSearch").val("");
		$("#operValueSearch").val("");
	});


	$("#role-oper-ok").click(function(){
		var id = $('#id').attr('value');
		var allIds = $("#operList").jqGrid('getDataIDs');
		var allIdstr = "";
		for(var i=0;i<allIds.length;i++){
			allIdstr = allIdstr+allIds[i];
			if(i<allIds.length-1){
				allIdstr = allIdstr+",";
			}
		}
		var selectedIds = $("#operList").jqGrid('getGridParam','selarrrow');
		var selectedIdstr = "";
		for(var j=0;j<selectedIds.length;j++){
			selectedIdstr = selectedIdstr+selectedIds[j];
			if(j<selectedIds.length-1){
				selectedIdstr = selectedIdstr+",";
			}
		}
		$.ajax({url: '<c:url value="/permission/assignOper.action"/>',
			type: 'POST',
			data: {id : id, currentPageAllOperIds : allIdstr, currentPageSelectedOperIds : selectedIdstr},
			dataType: 'json',
			//timeout: 3000,
			error: function(){
				alert('err!');
			},
			success: function(data){
				var newGrantedMenuIdString = $(data).attr('message');
				if("error" != newGrantedMenuIdString){
					$('#grantedOperIdString').attr('value', newGrantedMenuIdString);
					grantedOperIdString = newGrantedMenuIdString;
					
					alert('保存成功!');
				} else{
					alert('保存失败!');
				}
			}			
		});
	});
		

	$("#role-oper-back").click(function(){
	//	$("#operForm").attr("action", "roleList.action");
	//	$("#operForm").submit();
		$("#dialog-ajax-role-oper").dialog('close');
	});

	$("#role_oper_search_table input").input().enter(function(){
		$("#role-oper-search").click();
	});
});
	
		
	
</script>

<form id="operForm" action="operList.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_container">
	<div class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">角色信息</a></div>
	<div class="fieldset_container">
	<input type="hidden" name="id" id="id" value="${role.id}" />
	<input id="grantedOperIdString" name="grantedOperIdString" type="hidden" value="${grantedOperIdString}" />
	<input id="menuTypeColumnRender" name="menuTypeColumnRender" type="hidden" value="${menuTypeColumnRender}" />
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
		<tr>
		<td width="10%" align="right"><label for="roleCode">代码:</label></td>
		<td width="23%"><input type="text" name="roleCode" id="roleCode" value="${role.roleCode }" size="25" maxlength="100" disabled="disabled" /></td>
		<td width="10%" align="right"><label for="roleName">名称:</label></td>
		<td width="23%"><input type="text" name="roleName" id="roleName" value="${role.roleName }" size="25" maxlength="100" disabled="disabled" /></td>
		<td width="10%" align="right"><label for="description">描述:</label></td>
		<td width="24%"><input type="text" name="description" id="description" size="25" value="${role.description }" maxlength="100" disabled="disabled" /></td>
		</tr>
	</table>
	</div>
	</div>
<div id='role_oper_search_table' class="fieldset_border fieldset_bg m-b">
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
	<a  id="role-oper-search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span>
	  </span>
	</a>
	<a id="role-oper-reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
	<a  id="role-oper-ok" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-search">保 存</span>
	  </span>
	</a>
	<a id="role-oper-back" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
	</div>
	</div>
	</div>
<table id="operList"></table>
<div id="operPager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
</form>
