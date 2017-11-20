<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
var dlgUserId ="#dialog-ajax-cnl-view";
	$().ready(function() {

		var modifyUrl = "cnlSysIntfModify.action";
		var deleteUrl = "cnlSysIntfDelete.action";
		var createUrl = "cnlSysIntfCreate.action";
		var searchUrl = "cnlSysIntfSearch.action";
		var exportUrl = "cnlSysIntfExport.action";
		
		$(".warning").hide();
		$("#search").click(function(){
			doSearch();
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$(".warning").hide();
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		$("#create").click(function(){
			window.location.href=createUrl;
		});

		$("#delete").click(function(){
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			
			if(id!=null){
				if(confirm("是否删除") == true){
					$("#cnlCommentListForm").attr("action",deleteUrl+"?id="+id);
					$("#cnlCommentListForm").submit();
				}
			}else{
			    	$(".warning").html("请选择一条未处理状态的数据列！");
			    	$(".warning").show();
			    	return false;
			}
			
		})
		
		$("#modify").click(function(){
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			
			if(id!=null){
				window.location= "cnlSysIntfModify.action?loadPageCache=true&id="+id;
			}else{
		    	$(".warning").html("请选择一条未处理状态的数据列！");
		    	$(".warning").show();
		    	return false;
			}
		})
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID',
					'${app:i18n('cnlComment.cnlCustCode')}',
					'${app:i18n('cnlComment.accessCode')}',
					'${app:i18n('cnlComment.currency')}',
					'${app:i18n('cnlComment.perTransLimit')}',
					'${app:i18n('cnlComment.dayLimit')}',
					'${app:i18n('cnlComment.weekLimit')}',
					'${app:i18n('cnlComment.monthLimit')}',
					'${app:i18n('cnlComment.yearLimit')}',
					'${app:i18n('cnlComment.lmtOpt')}',
					'${app:i18n('cnlComment.lmtOptTime')}',
					'${app:i18n('cnlComment.comments')}'],
			colModel:[{name : 'id',width : '10%',hidden:true},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
						{name : 'accessCode',index : 'accessCode',width : '10%'},
						{name : 'currency',index : 'currency',width : '10%'},
						{name : 'perTransLimit',index : 'perTransLimit',width : '10%'},
						{name : 'dayLimit',index : 'dayLimit',width : '10%'},
						{name : 'weekLimit',index : 'weekLimit',width : '10%'},
						{name : 'monthLimit',index : 'monthLimit',width : '10%'},
						{name : 'yearLimit',index : 'yearLimit',width : '10%'},
						{name : 'lmtOpt',index : 'creator',width : '10%'},
						{name : 'lmtOptTime',index : 'createTime',width : '10%'},
						{name : 'comments',index : 'comments',width : '10%'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlComment.cnlCommentListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id&csiid=#csiid&custCode=#custCode\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

					
					var id = ids[i];
					var csiid= $("#gridTable").getCell(id,"csiid");
					var custCode= $("#gridTable").getCell(id,"custCode");
					var rowData = $("#gridTable").getRowData(id);
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					all = all + mod;

					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}
			}
		});

		$("#gridTable").gridUtil().customizeColumn();
		$("#gridToolbar").appendTo($("#t_gridTable"));

		// sub form toggle
		$( function() {
		$(".legend_title a").toggle(function(){
					$(this).parent().next(".fieldset_container:first").hide();
					$(this).removeClass("container_show");
					$(this).addClass("container_hide");
				},function(){
					$(this).parent().next(".fieldset_container:first").show();
					$(this).removeClass("container_hide");
					$(this).addClass("container_show");
			});
		})
		
		// input blur event for trimming
		$("#queryField input").input().trim();
		$("#queryField input").input().enter(doSearch);
		
	});
	
	
	

	
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlSysIntfSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("accessCode", $("#accessCode").val());
		$("#gridTable").setPostDataItem("currency", $("#currency").val());
	}
	
 
	//弹出层
	function openDialog(dlgId, title, url){
		//$(dlgId).html("");
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

<s:form id="cnlCommentListForm" method="post"
	action="cnlCnlCfgList.action">
	<div class="warning" style="display:none;">
		
	</div>
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('cnlComment.cnlCommentListJsp.headTitle')}</h3>
			</div>
			<div class="block_container">
				<div class="fieldset_border fieldset_bg m-b" id="queryField">
					<div class="legend_title">
						<a href="#" class="container_show">${app:i18n('global.jsp.search')}</a>
					</div>
					<div class="fieldset_container" id="test1">
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
								<tr>
									<th width="8%">${app:i18n('cnlSysIntfCfg.cnlCustCode')}：</th>
									<td width="25%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>
									
									<th width="10%">${app:i18n('cnlSysIntfCfg.accessCode')}：</th>
									<td width="25%"><input name="accessCode" id="accessCode" class="width_c" /></td>
									
									<th width="7%">${app:i18n('cnlSysIntfCfg.currency')}：</th>
									<td width="25%">
										<select name="currency" id="currency" style="width:100%;">
											<s:iterator value="currencyList" var="currency">
												<s:if test="#currency.key == '50'">
													<option value="${currency.key }" selected="selected">${currency.value }</option>
												</s:if>
												<s:else>
													<option value="${currency.key }">${currency.value }</option>
												</s:else>
											</s:iterator>
										</select>
									</td>
								</tr>
								<%-- <tr>
									<th width="10%">${app:i18n('cnlSysIntfCfg.currency')}：</th>
									<td width="30%">
										<select name="currency" id="currency">
											<s:iterator value="currencyList" var="currency">
												<s:if test="#currency.key == '50'">
													<option value="${currency.key }" selected="selected">${currency.value }</option>
												</s:if>
												<s:else>
													<option value="${currency.key }">${currency.value }</option>
												</s:else>
											</s:iterator>
										</select>
									</td>
									
									<th></th>
									<td></td>
								</tr> --%>
							</tbody>
						</table>
						<div class="btn_layout">
						
							<a name="search" id="search" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
							<a name="reset" id="reset" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar">
						<a id="create" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>
						<a id="delete" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-delete">${app:i18n('global.jsp.delete')}</span></span></a>
						<a id="modify" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-ajax-cnl-view"></div>
</s:form>
