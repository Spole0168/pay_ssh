<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<div id="container" class="layout block m-b">
<s:form id="userForm" action="userList.action" method="post">
<div class="layout">
<div class="block m-b">
	<div class="block_title">
	   <h3>用户列表</h3>
	</div>
	<input id="typeColumnRender" name="typeColumnRender" type="hidden" value="${typeColumnRender}" />
	<input id="statusColumnRender" name="statusColumnRender" type="hidden" value="${statusColumnRender}" />
	<input id="usedColumnRender" name="usedColumnRender" type="hidden" value="${usedColumnRender}" />
	<input id="currentUserId" name="currentUserId" type="hidden" value="${currentUserId}" />
	<div class="block_container">
	<div id='user_search_table' class="fieldset_border fieldset_bg m-b">
	<div class="legend_title"><a href="#" class="container_show">用户查询</a></div>
	<div class="fieldset_container">
	
	<table width="100%" border="0" cellspacing="2" cellpadding="2" class="table_form">
		<tr>
			<th>登录名：</th>
			<td><input type="text" name="userNameSearch" id="userNameSearch" size="25" maxlength="100" style="width:180px" /></td>
			
			<th>用户名：</th>
			<td><input type="text" name="userCodeSearch" id="userCodeSearch" size="25" maxlength="100" style="width:180px" /></td>
			
			<th>用户类型：</th>
			<td>
				<select id="userTypeSearch" name="userTypeSearch" style="width:180px">
					<option value="">请选择</option>
					<c:forEach items="${typeList }" var="item">
						<option value="${item.key }">${item.value }</option>
					</c:forEach>
				</select>
			</td>
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
<div class="layout ">
<div class="block m-b ">	
<div class="block tabs" id="userListDiv">
<div id="toolbar">
		<a id="user-create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">新 增</span></span></a> 
		<a id="user-delete" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-remove">删 除</span></span></a> 
</div>
<table id="userList"></table>
</div>
<div id="userPager"></div>
<div id="tblMasterMessage"></div>
	</div>
	</div>
	</div>
	</div>
	</div>
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
	var usedColumnRender = $('#usedColumnRender').attr('value'); // column render
	
	$("#userList").jqGrid({
		url:'userSearch.action',
// 		url:'',
		editurl:"userDelete.action",
		multiselect:true,
		datatype: "json",
		mtype: "POST",
		shrinkToFit:false,
		toolbar:[true, 'top'],
		rownumbers: true,
		colNames:['ID', '登录名','用户名','用户类型','电话', 'Email','创建日期','创建人','上次登录时间','备注','操作' ],
		colModel:[ 
				    
				    {name : 'id',index : 'id',hidden:true},
				    {name : 'userName',index : 'userName'},
				    {name : 'userCode',index : 'userCode'},
				    {name : 'userType',index : 'userType',formatter:'select', editoptions:{value:typeColumnRender}},
				    {name : 'phonenum',index : 'phonenum'},
				    {name : 'email',index : 'email'},
				    {name : 'createTm',index : 'createTm'},
				    {name : 'creator',index : 'creator'},
				    {name : 'lastLoginTm',index : 'lastLoginTm'},
				    {name : 'description',index : 'description'},
				    {name : 'operation',index : 'operation',sortable:false,editable:true,del:true}
		],
	   	rowNum:50,
	   	rowList:[50,100,200],
	   	pager: '#userPager',
	   	multiselect:true,
	   	multiboxonly:true,
		sortname:"userName",
		sortOrder:"asc",
	   	autowidth:true,
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
    		 var ids = $("#userList").getDataIDs();//jqGrid('getDataIDs');
             for(var i=0;i<ids.length;i++){
                 var id = ids[i];
                 var modifyButton = "<a onclick='modify(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">修改</span></span></a>";
                 var detailButton = "<a onclick='userRole(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-audit\">角色</span></span></a>";
                 //var detailButton = "<a onclick='detail(\"" + id + "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-audit\">查看</span></span></a>";
//                 	$(gridVar).jqGrid('setRowData',id,{operation:modifyButton+detailButton});
                 $(gridVar).jqGrid('setRowData',id,{operation:detailButton+modifyButton});
             } 
    	}
	    	
	});
	$("#toolbar").appendTo($("#t_userList"));
	$("#user-create").click(function(){
		var url = "userCreate.action";
		var title = "新增用户";
		$(dlgEditId).dialog({width:450,height:400,modal:true});
		openDialog(dlgEditId,title,url);
			
	});
	
	$("#search").click(function(){
		var url = "userSearch.action";
	
		var userCode = $('#userCodeSearch').val();
		var userName = $('#userNameSearch').val();
		var userType = $('#userTypeSearch').val();
		$(gridVar).appendPostData({
			userCodeSearch:userCode,
			userNameSearch:userName,
			userTypeSearch:userType,
			});
		
		$(gridVar).jqGrid("setGridParam",{url:url}).trigger("reloadGrid");
			
		// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
		$(gridVar).jqGrid("setGridParam",{page:1});
	});

	$("#user-delete").click(function(){
		
		var gr = $(gridVar).jqGrid('getGridParam','selarrrow');
		if (gr.length < 1) {
			$.boxUtil.warn("请选择要删除的用户！");
			return;
		}
		if (gr.length != 1) {
			$.boxUtil.warn("一次只能删除一个用户！");
			return;
		}
		var id = gr[0];
		var data = $(gridVar).jqGrid('getRowData', id);
		if(data.userType=='admin'){
			$.boxUtil.warn("不能删除管理员！");
			return;
		}
// 		alert("自身id："+ $("#currentUserId").val());
// 		alert("选择要删除的id："+ data.id);
		if(data.id == $("#currentUserId").val()) {
			$.boxUtil.warn("不能删除自身！");
			return;
		}
		$(gridVar).jqGrid("delGridRow", id,{ajaxDelOptions:{dataType:"JSON"}}); 

	});
	
	$("#reset").click(function(){
		$("#userCodeSearch").val("");
		$("#userNameSearch").val("");
		$("#userTypeSearch").val("");
	});
	
});

var dlgEditId = "#dialog-ajax-user-edit";
var dlgRoleId = "#dialog-ajax-user-role";
var gridId = "#userList";

function modify(id){
	var url = "userModify.action?id="+id+"&time="+new Date().getTime();
	var title = "修改用户信息";
	$(dlgEditId).dialog({width:450,height:400,modal:true});
	openDialog(dlgEditId,title,url);
}

function detail(id){
	var url = "userDetail.action?id="+id;
	var title = "查看用户信息";
	$(dlgEditId).dialog({width:450,height:400,modal:true});
	openDialog(dlgEditId,title,url);
}

function userRole(id){
	var url = "toUserAssignRoles.action?id=" + id+"&time="+new Date().getTime();
	var data = $("#userList").jqGrid('getRowData',id);
// 	var title = "用户\""+data.displayName+"\"角色信息";
	var title = "用户\""+data.userName+"\"角色信息";
	$(dlgRoleId).dialog({width:800,height:600,modal:true});
	openDialog(dlgRoleId,title,url);
}
function refreshGrid(){
	$(gridId).trigger("reloadGrid");
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
	$(dlgId).dialog({
		   close: function(event, ui) { 			   
		  		$(dlgId).html(" ");			   
  			}  
	});
}
function closeDialog(){
	$(dlgEditId).dialog('close');
}	
	
</script>
<div id="dialog-ajax-user-edit"></div>
<div id="dialog-ajax-user-role"></div>
</s:form>
</div>