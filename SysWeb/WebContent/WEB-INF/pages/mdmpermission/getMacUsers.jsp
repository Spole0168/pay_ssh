<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<c:url context='/common' value='/js/common/jquery.gridUtil.js'/>"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		$("#macUsersGridTable").gridUtil().simpleGrid({
			url: "searchMacUsers.action?macId=${macId}",
			rownumbers: true,
			colNames:[ '用户姓名',
			           '用户编号',
			           '网点名称',
			           '网点编号'],
			colModel:[  {name : 'userName',index : 'USER_NAME',width : '30%'},
					    {name : 'userCode',index : 'USER_CODE',width : '30%'},
					    {name : 'orgName',index : 'ORG_NAME',width : '30%'},
					    {name : 'orgCode',index : 'ORG_CODE',width : '30%'}
			],
			pager: "#macUsersGridPager",
			multiselect:false,
			sortname: 'USER_CODE',
			sortorder: "ASC",
			caption: "用户列表",
			gridComplete: function() {
			}
		});
		
		$("#backToMacList").click(function(){
			$("#mac_user_dialog").html(""); 
			$("#mac_user_dialog").dialog('close'); 
		});
	});
</script>
<form method="post" action="toMacUsers.action" id="searchMacUserForm">
<input type="hidden" name="pageCache" id="pageCache" value="true">
<div id="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="legend_title"><a href="javascript:void(0)" class="container_show">MAC绑定的用户</a></div>
				<div class="fieldset_container">
					<table cellpadding="0" cellspacing="0" class="table_form">
						<tbody>
							<tr>
								<td>
									<a id="backToMacList" class="easyui-linkbutton l-btn" href="javascript:void(0);"><span class="l-btn-left"><span class="l-btn-text icon-undo">返回</span></span></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<table id="macUsersGridTable"></table>
			<div id="macUsersGridPager"></div>
		</div>
	</div>
</div>
</form>