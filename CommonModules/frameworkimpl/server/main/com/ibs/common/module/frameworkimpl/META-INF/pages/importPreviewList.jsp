<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" charset="UTF-8">
	
	function doSearch(){
		$("#gridTable").gridUtil().searchGrid($("#searchForm"));
	}

	function doBeforeUnload() {
		return "你想离开本页面吗，导入数据可能丢失！";
	}

	$(document).ready(function(){
		$(".legend_title a").toggle(function(){
				$(this).parent().next(".fieldset_container:first").hide();
				$(this).removeClass("container_show");
				$(this).addClass("container_hide");
			},function(){
				$(this).parent().next(".fieldset_container:first").show();
				$(this).removeClass("container_hide");
				$(this).addClass("container_show");
		});

		$(window).bind('beforeunload', doBeforeUnload);
		
		var searchUrl = "importDataSearch.action?importId=${importId}&columnName1=${importInfo.columnName1}&columnName2=${importInfo.columnName2}&columnName3=${importInfo.columnName3}&columnName4=${importInfo.columnName4}&columnName5=${importInfo.columnName5}";
		var saveUrl = "importDataSave.action?importId=${importId}";
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			colNames:[ 'id',
					   'importId',
					   '行号',
					   '校验结果',
					   '错误描述',
					   'excel数据'
					<s:iterator value="excelSetting.columns" var="column">
						,'<s:property value="#column.value.name" escape="false" />'
					</s:iterator>
					],
			colModel:[  {name : 'id', hidden:true, key:true},
					    {name : 'importId', hidden:true},
					    {name : 'rowNo', index : 'rowNo',width : 40},
					    {name : 'isSuccess',index : 'isSuccess',width : 50,formatter:'select', editoptions:{value:"true:成功;false:失败"}},
					    {name : 'errorMessage',index : 'errorMessage',width : 150 },
					    {name : 'excelData', hidden: true}
					<s:iterator value="excelSetting.columns" var="column">
						,{name : '<s:property value="#column.value.field"/>', sortable: false}
					</s:iterator>
					],
			pager: "#gridPager",
			//toolbar: [true,"top"],
			rowNum: 20,
			autowidth: false,
			width: 975,
			shrinkToFit: false,
			multiselect: false,
			sortname: 'rowNo',
			sortorder: "ASC",
			caption: "导入数据预览",
			footerrow : true,
			userDataOnFooter : true,
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				for(var i = 0; i < ids.length; ++i) {
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);
					
					if ('false' == rowData.isSuccess) {
						$(this.rows[i+1]).removeClass("altClass").addClass("grid_color1");
					}
					
					var data = JSON.parse(rowData.excelData);
					<s:iterator value="excelSetting.columns" var="column">
						$("#gridTable").jqGrid("setRowData",ids[i],{'<s:property value="#column.value.field"/>':data['<s:property value="#column.value.field"/>']});
					</s:iterator>
					
					<%--
					//$("#gridTable").jqGrid("setRowData",ids[i],{"orderLogisticsCode":data.orderLogisticsCode});
					//console.log(data);
					//console.log(rowData.excelData);
					//$("#gridTable").jqGrid('setRowData',ids[i],{operation:mod});
					--%>
				}
				
				<%--
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var mod = "<a href='#' class='a_btn_edit m-r ' onclick='window.location=\"#modifyUrl?id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);
					
					$("#gridTable").jqGrid('setRowData',ids[i],{operation:mod});
				}
				--%>
			}
		}, {heightfixed: true, heightspan: 350});

		//$("#gridToolbar").appendTo($("#t_gridTable"));

		// click search
		$("#searchForm input").input().trim();

		$("#searchForm input").input().enter(doSearch);
		
		$("#search").click(doSearch);
		
		// click reset
		$("#reset").click(function() {
			$('#searchForm')[0].reset();
		});
		
		// click save
		$("#save").click(function() {
			$(window).unbind('beforeunload', doBeforeUnload);
			$.boxUtil.saving();
			$("#searchForm").attr('action', saveUrl);
			$("#searchForm").submit();
		});
	});
</script>

<form method="post" id="searchForm" name="searchForm" onsubmit="return false;">
<%-- <input type="hidden" id="importId" name="importId" value="${importId}" />
<input type="hidden" id="handleClass" name="excelSetting.handleClass" value="${excelSetting.handleClass}" /> --%>

<div id="layout">
	<div class="block m-b">
		<div class="block_title">
		   <h3>${excelSetting.description}</h3>
		</div>
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="legend_title"><a href="javascript:void(0)" class="container_show"><legend>查询</legend></a></div>
				<div class="fieldset_container">
					<table cellpadding="0" cellspacing="0" class="table_form">
						<tbody>
							<tr>
								<th>校验结果:</th>
								<td>
									<select id="isSuccess" name="isSuccess">
										<option value="">--请选择--</option>
										<option value="1">成功</option>
										<option value="0">失败</option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<!-- important, 如果 href是javascript:void(0)，则IE7/IE8会触发onbeforeunload事件 -->
						<s:if test='stopOnError=="" || stopOnError!="stop"'>
							<a id="save" class="a_btn2 m-r" href="###" type="submit"><em>确定导入</em></a>
						</s:if>
						
						
						<a id="search" class="easyui-linkbutton l-btn" href="###"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span></span></a>
						<a id="reset" class="easyui-linkbutton l-btn" href="###"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.reset')}</span></span></a>
				    </div>
				</div>
			</div>
			<table id="gridTable"></table>
			<div id="gridPager"></div>
			<%--
			<div id="gridToolbar">
				<a id="enable" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-audit">启用</span></span></a>
				<a id="delete" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-remove">删除</span></span></a>
			</div>
			--%>
		</div>
	</div>
</div>
</form>