<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "cnlSysIntfIpLimitModify.action";
		var deleteUrl = "cnlSysIntfIpLimitDelete.action";
		var createUrl = "cnlSysIntfIpLimitCreate.action";
		var searchUrl = "cnlSysIntfIpLimitSearch.action";
		
		$("#search").click(function(){
			doSearch();
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		$("#create").click(function(){
			window.location = "cnlSysIntfIpLimitCreate.action?loadPageCache=true";
		});
		
		

		$("#delete").click( function() {
			var gr = $("#gridTable").jqGrid('getGridParam','selrow');
			if(gr == null){
				alert("请选择一条记录");
				return false;
			}
			var conf = confirm("确认删除记录?");
			if(conf){
				$.get(deleteUrl+"?id="+gr,function(data){
					//alert(data);
					var res = eval("(" + data + ")");
					//alert(res.message);
					if(res.message == "01"){
						alert("删除成功");
						window.location.reload();
					}else if(res.message == "02"){
						alert("数据异常，删除失败");
					}
				})
			}
		});
		
		$("#modify").click( function() {
			var gr = $("#gridTable").jqGrid('getGridParam','selrow');
			if(gr == null){
				alert("请选择一条记录");
				return false;
			}
			window.location = "cnlSysIntfIpLimitModify.action?id="+ gr +"&loadPageCache=true";
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			//sortname:'id', // 默认的排序字段
			//sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('cnlSysIntfIpLimit.cnlCustCode')}',
					'${app:i18n('cnlSysIntfIpLimit.cnlIntfCode')}',
					'${app:i18n('cnlSysIntfIpLimit.ipRangeFrom')}',
					'${app:i18n('cnlSysIntfIpLimit.ipRangeTo')}',
					'${app:i18n('cnlSysIntfIpLimit.ipOptTime')}',
					'${app:i18n('cnlSysIntfIpLimit.ipOpt')}',
					],
			colModel:[
						{name : 'id',width : '10%', hidden: true,align:'center'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '15%',align:'center'},
						{name : 'cnlIntfCode',index : 'cnlIntfCode',width : '15%',align:'center'},
						{name : 'ipRangeFrom',index : 'ipRangeFrom',width : '20%',align:'center'},
						{name : 'ipRangeTo',index : 'ipRangeTo',width : '20%',align:'center'},
						{name : 'ipOptTime',index : 'ipOptTime',width : '20%',align:'center'},
						{name : 'ipOpt',index : 'ipOpt',width : '10%',align:'center'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlSysIntfIpLimit.cnlSysIntfIpLimitListJsp.tableTitle')}",
			/* gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
		
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);
		
					all = all + mod;
		
					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}
			} */
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
		checkIpFormat();
		setQueryCondition();
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlSysIntfIpLimitSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("cnlIntfCode", $("#cnlIntfCode").val());
		$("#gridTable").setPostDataItem("ipRangeFrom", $("#ipRangeFrom").val());
		$("#gridTable").setPostDataItem("ipRangeTo", $("#ipRangeTo").val());
	}
	
	function checkIpFormat(){
		var ipRangeFrom = $("#ipRangeFrom").val().trim();
		var ipRangeTo = $("#ipRangeTo").val().trim();
		var pattern = new RegExp("[0-9]{3}\.[0-9]{3}\.[0-9]{3}\.[0-9]{3}");
		//alert(typeof ipRangeFrom);
		if(ipRangeFrom != ""){
			if(pattern.test(ipRangeFrom) == false){
				alert("请输入正确的IP地址格式!");
				return false;
			}
		}
		if(ipRangeTo != ""){
			if(pattern.test(ipRangeTo) == false){
				alert("请输入正确的IP地址格式!");
				return false;
			}
		}
	}
</script>

<s:form id="cnlSysIntfIpLimitListForm" method="post"
	action="cnlSysIntfIpLimitList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('cnlSysIntfIpLimit.cnlSysIntfIpLimitListJsp.headTitle')}</h3>
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
									<th width="10%">${app:i18n('cnlSysIntfIpLimit.cnlCustCode')}：</th>
									<td width="30%"><input name="cnlCustCode" id="cnlCustCode"
										class="width_c" /></td>
									<th width="10%">${app:i18n('cnlSysIntfIpLimit.cnlIntfCode')}：</th>
									<td width="30%"><input name="cnlIntfCode" id="cnlIntfCode"
										class="width_c" /></td>
								</tr>
								<tr>
									<th width="10%">${app:i18n('cnlSysIntfIpLimit.ipRangeFrom')}：</th>
									<td width="30%"><input name="ipRangeFrom" id="ipRangeFrom"
										class="width_c" /></td>
									<th width="10%">${app:i18n('cnlSysIntfIpLimit.ipRangeTo')}：</th>
									<td width="30%"><input name="ipRangeTo" id="ipRangeTo"
										class="width_c" /></td>
								</tr>
								<tr>
									<th width="10%"></th>
									<td><scan style="color: red;">IP输入格式：***.***.***.***</scan></td>
								</tr>
							</tbody>
						</table>
						<div class="btn_layout">
							<a name="search" id="search" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
							<a name="reset" id="reset" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
							<%-- <a name="export" id="export" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a> --%>
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
</s:form>
