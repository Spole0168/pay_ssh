<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$()
			.ready(
					function() {
						var appId = $("#appId").val();
						if (appId != null && appId != '') {
							$("#roleAppSearch").val(appId);
							$("#roleAppSearch").attr('disabled', 'disabled');
						}
						$(function() {
							$(".legend_title a").toggle(
									function() {
										$(this).parent().next(
												".fieldset_container:first")
												.hide();
										$(this).removeClass("container_show");
										$(this).addClass("container_hide");
									},
									function() {
										$(this).parent().next(
												".fieldset_container:first")
												.show();
										$(this).removeClass("container_hide");
										$(this).addClass("container_show");
									});
						});
						$("#roleList")
								.jqGrid(
										{
											url : 'roleSearch.action?roleScope='
													+ $("#roleScope").val()
													+ "&userId="
													+ $("#currentAssignRoleUserId").val()
													+ "&roleApp="
													+ $("#roleAppSearch").val(),
											datatype : "json",
											mtype : "POST",
											rownumbers : true,
											cellEdit:true,
											colNames : [ 'ID', '名 称', '描 述',
													'角色菜单' ],
											colModel : [ {
												name : 'id',
												index : 'id',
												hidden : true
											}, {
												name : 'roleName',
												index : 'roleName',
												width : '20%'
											}, {
												name : 'description',
												index : 'description',
												width : '50%'
											}, {
												name : 'operation',
												index : 'operation',
												width : '30%',
												align : 'center',
												sortable : false
											}

											],
											rowNum : 10,
											rowList:[10,50,100],
											pager: '#rolePager',
											sortname : 'roleName',
											sortorder : "asc",
											multiselect : true,
											autowidth : true,
											height : '350',
											hidegrid : false, // 隐藏
											viewrecords : true,
											jsonReader : {
												root : "rows",
												page : "page",
												total : "total",
												records : "records",
												repeatitems : false
											},
											loadError : function(xhr, st, err) {
												alert('err:' + err);
												$("#tblMasterMessage")
														.html(
																"Type: "
																		+ st
																		+ "; Response: "
																		+ xhr.status
																		+ " "
																		+ xhr.statusText);
											},
											onPaging : function(pgButton) {
												$("#search").click();
											},
											
											// onSelectRow : function(rowid){},
											// loadBeforeSend : function(xhr){},
											loadComplete : function(xhr) {
												var assignedRoleIdString = $(
														'#assignedRoleIdString')
														.attr('value');

												// 选中角色已有的资源
												if (assignedRoleIdString) {
													var assignedRoleArray = assignedRoleIdString
															.split(',');
													$
															.each(
																	assignedRoleArray,
																	function(n,
																			value) {
																		$(
																				"#roleList")
																				.jqGrid(
																						'setSelection',
																						value);
																	});
												}
											},

											//增加操作列	    	
											gridComplete : function() {
												var ids = $("#roleList")
														.getDataIDs();//jqGrid('getDataIDs');
												for ( var i = 0; i < ids.length; i++) {
													var id = ids[i];
													var editButton = "<a onclick='viewRole(\""
															+ id
															+ "\")' href=\"javascript:void(0);\"><span class=\"l-btn-left\"><span class=\"l-btn-text icon-edit\">所有资源</span></span></a>";

													$("#roleList")
															.jqGrid(
																	'setRowData',
																	id,
																	{
																		operation : editButton
																	});

												}
											},
											caption : "角色列表"
										});

						// 添加分页导航条
						$("#roleList").jqGrid('navGrid', '#rolePager', {
							edit : false,
							add : false,
							del : false,
							search : false,
							refresh : true
						});

						// 查询 ajax
						/*
						function search(){
							var url = 'roleSearch.action?rowNum=' + $('#roleList').jqGrid('getGridParam','rowNum');		
							$("#roleList").jqGrid('setGridParam',{url:url}).trigger("reloadGrid");
						}*/

						$("#user-role-search")
								.click(
										function() {
											var url = "roleSearch.action?rowNum="
													+ $("#roleList").jqGrid(
															'getGridParam',
															'rowNum');
											var roleCode = $('#roleCodeSearch')
													.val();
											var roleName = $('#roleNameSearch')
													.val();
											var description = $(
													'#roleDescriptionSearch')
													.val();
											var roleApp = $("#roleAppSearch")
													.val();
											var roleScope = $("#roleScope")
													.val();
											var roleGroup = $(
													"#roleGroupSearch").val();
											var userId = $("#currentAssignRoleUserId")
													.val();
											var pageCache = $("#pageCache")
													.val();
											$("#roleList").appendPostData({
												roleCode : roleCode,
												roleName : roleName,
												description : description,
												roleScope : roleScope,
												roleApp : roleApp,
												roleGroup : roleGroup,
												userId : userId,
												pageCache : pageCache
											});

											$("#roleList").jqGrid(
													"setGridParam", {
														url : url
													}).trigger("reloadGrid");

											// 修正分页 如:当前浏览第2页,输入查询条件后查询结果共1页,jqgrid仍然会停留在第2页,需要手工翻到第1页
											$("#roleList").jqGrid(
													"setGridParam", {
														page : 1
													});
										});
						$("#user-role-reset").click(function() {
							$("#roleCodeSearch").val("");
							$("#roleNameSearch").val("");
							$("#roleDescriptionSearch").val("");
							$("#roleScopeSearch").val("");
							var appId = $("#appId").val();
							if (appId == null || appId == '') {
								$("#roleAppSearch").val("");
							}

							$("#roleGroupSearch").val("");
						});
						$("#user-role-save")
								.click(
										function() {
											//	 $.blockUI({
											//			message: $('#loading_org').html(),
											//				css: {padding: '10px'}
											//			});
// alert($('#currentAssignRoleUserId')
// 		.attr('value'));
											$(dlgRoleId).block(
													{
														message : $(
																'#loading_org')
																.html(),
														css : {
															padding : '10px'
														}
													});
											var userId = $('#currentAssignRoleUserId')
													.attr('value');
											var allIds = $("#roleList").jqGrid(
													'getDataIDs');
											var allIdstr = "";
											for ( var i = 0; i < allIds.length; i++) {
												allIdstr = allIdstr + allIds[i];
												if (i < allIds.length - 1) {
													allIdstr = allIdstr + ",";
												}
											}
											var selectedIds = $("#roleList")
													.jqGrid('getGridParam',
															'selarrrow');
											var selectedIdstr = "";
											for ( var j = 0; j < selectedIds.length; j++) {
												selectedIdstr = selectedIdstr
														+ selectedIds[j];
												if (j < selectedIds.length - 1) {
													selectedIdstr = selectedIdstr
															+ ",";
												}
											}

											$
													.ajax({
														url : '<c:url value="/permission/userAssignRoles.action"/>',
														type : 'POST',
														data : {
															id : userId,
															currentPageAllRoleIds : allIdstr,
															currentPageSelectedRoleIds : selectedIdstr
														},
														dataType : 'json',
														//timeout: 3000,
														error : function() {
															//alert('err!');
															$.boxUtil
																	.error('连接网络超时!');
															$(dlgRoleId)
																	.unblock();
														},
														success : function(data) {
															var newAssignedRoleIdString = $(
																	data).attr(
																	'message');
															if ("error" != newAssignedRoleIdString) {
																$(
																		'#assignedRoleIdString')
																		.attr(
																				'value',
																				newAssignedRoleIdString);
																grantedMenuIdString = newAssignedRoleIdString;
																$('#roleList')
																		.trigger(
																				"reloadGrid");

																$.boxUtil
																		.success('保存成功!');
																//	$.unblockUI();
																$(dlgRoleId)
																		.unblock();
															} else {
																$.boxUtil
																		.error('保存失败!');
																//$.unblockUI();
																$(dlgRoleId)
																		.unblock();
															}
														}
													});

										});

						$("#user-role-exit").click(function() {
							//	$('#userRoleForm').attr('action','userMain.action');
							//	$("#userRoleForm").submit();
							$("#dialog-ajax-user-role").html("");
							$("#dialog-ajax-user-role").dialog('close');
						});

						$('#dialog-ajax').dialog({
							autoOpen : false,
							bgiframe : true,
							width : 600
						});

						$("#user_role_search_table input").input().enter(
								function() {
									$("#user-role-search").click();
								});

					});

	var gridId = "#roleList";

	function viewRole(id) {
		var url = "roleModify.action?type=view&id=" + id + "&time="
				+ new Date().getTime();
		var title = "角色所有资源";
		openDialogForUserole(title, url);
	}
	function openDialogForUserole(title, url) {
		var time = new Date().getTime();
		var divId = "dialog" + time;
		$("body")
				.append(
						'<div id="' + divId + '" title="角色所有资源"><div style="text-align: center;"><img src="/common/images/framework/ajax-loader4.gif" /></div></div>');
		$('#' + divId).dialog({
			autoOpen : false,
			bgiframe : true,
			width : 1000,
			height : 600,
			modal : true
		});
		url = url + "&divId=" + divId;

		$('#' + divId).dialog('open');
		$('#' + divId).dialog('moveToTop');
		$('#' + divId).dialog({
			close : function() {
				$("#" + divId).html(" ");
				//$('#' + divId).dialog('close');
			}
		});

		$('#' + divId).load(url);
	}

	function setOrgValue(orgId, orgName) {

		$("#parentOrgCode").val(orgId);
		$("#parentOrgName").val(orgName);
		return true;
	}
</script>

<form id="userRoleForm" action="toUserAssignRoles.action" method="post">
	<div class="layout">
		<div class="block m-b">
			<div class="block_container">
				<input type="hidden" name="currentAssignRoleUserId" id="currentAssignRoleUserId"
					value="${id}" /> <input type="hidden" name="currentOrgId"
					id="currentOrgId" value="${currentOrgId}" /> <input type="hidden"
					name="roleScope" id="roleScope" value="${roleScope }" /> <input
					id="assignedRoleIdString" name="assignedRoleIdString" type="hidden"
					value="${assignedRoleIdString}" />


				<div id='user_role_search_table'
					class="fieldset_border fieldset_bg m-b">
					<div class="legend_title">
						<a href="#" class="container_show">角色查询</a>
					</div>
					<div class="fieldset_container">
						<table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
								<td width="13%" align="right"><label for="roleNameSearch">名称:</label>
								</td>
								<td width="20%"><input type="text" name="roleNameSearch"
									id="roleNameSearch" size="25" maxlength="100" />
								</td>
								<td width="13%" align="right"><label
									for="roleDescriptionSearch">描述:</label>
								</td>
								<td width="20%"><input type="text"
									name="roleDescriptionSearch" id="roleDescriptionSearch"
									size="25" maxlength="100" />
								</td>
								<td width="14%" align="right"><label for="roleAppSearch">所属应用:</label>
								</td>
								<td width="20%"><select id="roleAppSearch"
									name="roleAppSearch">
										<c:forEach items="${appList }" var="item">
											<option value="${item.key }">${item.value }</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
						<div class="btn_layout">
							<a id="user-role-search" class="easyui-linkbutton l-btn"
								href="javascript:void(0);"><span class="l-btn-left"><span
									class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span>
							</span> </a> <a id="user-role-reset" class="easyui-linkbutton l-btn"
								href="javascript:void(0);"><span class="l-btn-left"><span
									class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span>
							</span> </a> <a id="user-role-save" class="easyui-linkbutton l-btn"
								href="javascript:void(0);"><span class="l-btn-left"><span
									class="l-btn-text icon-save">保存</span> </span> </a> <a id="user-role-exit"
								class="easyui-linkbutton l-btn" href="javascript:void(0);"><span
								class="l-btn-left"><span class="l-btn-text icon-undo">返回</span>
							</span> </a>
						</div>
					</div>
				</div>

				<table id="roleList"></table>
				<div id="rolePager"></div>
				<div id="tblMasterMessage"></div>
			</div>
		</div>
	</div>
</form>
