<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

  
<style type="text/css">
.leftOrgTree {
	float: left;
	width: 200px;
	HEIGHT: 453px;
	padding: 6px;
	BACKGROUND:#FFFFEE;
	border: 1px solid #b8b2db;
	SCROLLBAR-FACE-COLOR: #e8e5f8;
	SCROLLBAR-HIGHLIGHT-COLOR: #f7f5ff;
	OVERFLOW: auto;
	SCROLLBAR-SHADOW-COLOR: #cac4e8;
	SCROLLBAR-3DLIGHT-COLOR: #ffffff;
	LINE-HEIGHT: 120%;
	SCROLLBAR-ARROW-COLOR: #9e9ccd;
	SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
}

.rightOrgContent {
	width: 780px;
	HEIGHT: 450px;
	float: right
}
</style>
<script type="text/javascript" charset="UTF-8">
var dlgEditId = "#dialog-ajax-user-edit";
var dlgRoleId = "#dialog-ajax-user-role";
var gridId = "#userList";

$().ready(function() {
 
		//构建树
		$("#leftOrgTree").load("leftTree.action");
		var currentOrgId = $("#currentOrgId").val();
		$("#rightOrgContent").load("userList.action?currentOrgId="+currentOrgId);
		 
});
function resizeLeft()
{
		//var h= $("#rightOrgContent").height();
		var h= $("#userListDiv").height();
		if(h>307){
		 	$("#leftOrgTree").height(h+180);
		 	$("#rightOrgContent").height(h+143);
		}else{
			$("#leftOrgTree").height(487);
			$("#rightOrgContent").height(450);
		}
}
	
	
</script>


<style type="text/css">
/*demo page css*/
.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

ul#icons {
	margin: 0;
	padding: 0;
}

ul#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

ul#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}
</style>

<form id="empMain" name="empMain" action="empMain.action" method="post">
<div class="main">
<div class="container">

<div class="layout">

<div class="leftOrgTree" id="leftOrgTree"></div>
<div class="rightOrgContent" id="rightOrgContent"  ></div>

<input id="currentOrgId" name="currentOrgId" value="${currentOrgId }" type="hidden" />


</div>
<div class="clear"></div>
</div>

</div>


<div class="clear">&nbsp;</div>
<div id="dialog-ajax-user-edit"></div>
<div id="dialog-ajax-user-role"></div>
</form>
