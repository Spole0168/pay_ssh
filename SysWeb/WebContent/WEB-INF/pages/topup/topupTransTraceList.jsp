<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">

	var modifyUrl = "topupTransTraceModify.action"; // 修改
	var searchUrl = "topupTransTraceSearch.action"; // 检索
	var viewUrl   = "topupTransTraceInfo.action";	// 查看
	var handleUrl = "topupTransTraceHandle.action"; // 审核
	var reviewUrl = "topupTransTraceReview.action"; // 复核
	var checksUrl = "topupTransTraceChecks.action"; // 批量复核
	var exportUrl = "topupTransTraceExport.action"; // 导出

	$().ready(function() {
		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();
			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});

		// 批量审核
		$("#checks").click(function() {

			clearWarning();

			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr != null) {
				if (gr.length == 0){
					showWarning("${app:i18n('topupTransTrace.checks.select.error.message')}");
					return;
				}

				// 获取ID
				var ids = $("#gridTable").jqGrid('getGridParam','selarrrow');
				for (i=0; i < ids.length; i++){
					var rowData = $("#gridTable").getRowData(ids[i]);

					// 处理状态
					var handleStatus = rowData.handleStatus;
					
					// 只能对“待审核”或“复核失败”的记录进行批量审核
					if(!(handleStatus == "${app:i18n('topupTransTrace.gridTable.handleStatus.waitCheck')}" || 
							handleStatus == "${app:i18n('topupTransTrace.gridTable.handleStatus.reCheckFailure')}")) {
						showWarning("${app:i18n('topupTransTrace.checks.error.message')}");
						return;
					}
				}

				$.boxUtil.confirm(
					"${app:i18n('topupTransTrace.checks.info.message')}", 
					null, 
					function(){
						doChecks(ids);
					}, 
					function(){
						//return false;
					}
				);
				
			}
		});

		// 复核
		$("#recheck").click(function() {

			clearWarning();

			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr != null) {
				if (gr.length == 0){
					showWarning("${app:i18n('topupTransTrace.reCheck.select.error.message_1')}");
					return;
				} else if (gr.length > 1){
					showWarning("${app:i18n('topupTransTrace.reCheck.select.error.message_2')}");
					return;
				}
			}

			// 获取ID
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			var rowData = $("#gridTable").getRowData(id);

			// 处理状态
			var handleStatus = rowData.handleStatus;
			
			// 只能复核“审核成功”的记录
			if(handleStatus=="${app:i18n('topupTransTrace.gridTable.handleStatus.checkSuccess')}") {
				// 复核画面
				$("#topupTransTraceListForm").attr("action", reviewUrl+"?id=" + id);
				$("#topupTransTraceListForm").submit();
			} else {
				$("div.warning span").html("${app:i18n('topupTransTrace.reCheck.error.message')}");
				$("div.warning").show();
				return;
			}
		});

		// 审核
		$("#check").click( function() {

			clearWarning();
			
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr != null) {
				if (gr.length == 0){
					showWarning("${app:i18n('topupTransTrace.check.select.error.message_1')}");
					return;
				} else if (gr.length > 1){
					showWarning("${app:i18n('topupTransTrace.check.select.error.message_2')}");
					return;
				}
			}
			
			// 获取ID
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			var rowData = $("#gridTable").getRowData(id);

			// 处理状态
			var handleStatus = rowData.handleStatus;
			
			// 只能审核“待审核”或者“复核失败”的记录
			if(handleStatus=="${app:i18n('topupTransTrace.gridTable.handleStatus.waitCheck')}" || 
					handleStatus=="${app:i18n('topupTransTrace.gridTable.handleStatus.reCheckFailure')}") {
				// 审核画面
				$("#topupTransTraceListForm").attr("action", handleUrl+"?id=" + id);
				$("#topupTransTraceListForm").submit();
			} else {
				showWarning("${app:i18n('topupTransTrace.check.error.message')}");
				return;
			}
		});

		$("#search").click(function(){

			clearWarning();

			if(validform().form()){
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				startTime = startTime.replace(/-/g,'/');
				endTime = endTime.replace(/-/g,'/');
				startTime = new Date(startTime);
				endTime = new Date(endTime);
				var times = startTime.getTime() - endTime.getTime();
				var days = Math.abs(parseInt(times/(1000*60*60*24)));

				if(times >= 0) {
					showWarning("${app:i18n('topupTransTrace.search.condition.check.error.message_1')}");
				} else if (days > 30) {
					showWarning("${app:i18n('topupTransTrace.search.condition.check.error.message_2')}");
				} else {
					doSearch();
				}
			}
		});

		function validform(){
			return $("#topupTransTraceListForm").validate({
				rules : {
					'topupTransTrace\.reqNum' : {
						stringMaxLength:20
					},
					'topupTransTrace\.reqInnerNum' : {
						stringMaxLength:20
					},
					'topupTransTrace\.cnlCnlCode' : {
						stringMaxLength:20
					},
					'topupTransTrace\.cnlSysName' : {
						stringMaxLength:20
					},
					'topupTransTrace\.cnlCustCode' : {
						stringMaxLength:20
					},
					'topupTransTrace\.localName' : {
						stringMaxLength:20
					}
				},
				invalidHandler : function(e, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						showWarning("${app:i18n('topupTransTrace.validform.error.message')}");
					} else {
						clearWarning();
					}
				}
			});
		}
		$("#reset").click(function(){

			// 设置查询参数为空
			$("#queryField :input").val("");
			$("#startTime").val("${startTime}");
			$("#endTime").val("${endTime}");
			$("div.warning span").html("");
			$("div.warning").hide();

		});

		//查看详情
		$("#view").click( function() {
			clearWarning();
			var gr = $("#gridTable").jqGrid('getGridParam', 'selarrrow');
			if (gr != null) {
				if (gr.length == 0){
					showWarning("${app:i18n('topupTransTrace.view.select.error.message_1')}");
					return;
				} else if (gr.length > 1){
					showWarning("${app:i18n('topupTransTrace.view.select.error.message_2')}");
					return;
				}
			}
			$("#topupTransTraceListForm").attr("action",viewUrl+"?id="+gr[0]);
			$("#topupTransTraceListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:checksUrl,
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:true,
			shrinkToFit:false,
			autowidth:true,
			colNames:[
				'ID', 
				'${app:i18n('topupTransTrace.reqNum')}',
// 				'${app:i18n('topupTransTrace.reqInnerNum')}',
// 				'${app:i18n('topupTransTrace.cnlCnlCode')}',
				'${app:i18n('topupTransTrace.cnlSysName')}',

				'${app:i18n('topupTransTrace.cnlCustCode')}',
				'${app:i18n('topupTransTrace.localName')}',
				'${app:i18n('topupTransTrace.transTime')}',
				'${app:i18n('topupTransTrace.transAmount')}',
				'${app:i18n('topupTransTrace.transLatestAmount')}',

				'${app:i18n('topupTransTrace.bankDebitName')}',
				'${app:i18n('topupTransTrace.bankDebitCustName')}',
				'${app:i18n('topupTransTrace.bankDebitCardNum')}',
				'${app:i18n('topupTransTrace.bankCreditName')}',
				'${app:i18n('topupTransTrace.bankCreditCustName')}',
				'${app:i18n('topupTransTrace.bankCreditCardNum')}',

				'${app:i18n('topupTransTrace.operator')}',
				'${app:i18n('topupTransTrace.handleTime')}',

				'${app:i18n('topupTransTrace.reviewer')}',
				'${app:i18n('topupTransTrace.reviewTime')}',

				'${app:i18n('topupTransTrace.handleStatus')}',
				'${app:i18n('topupTransTrace.handleResult')}',

				'${app:i18n('topupTransTrace.handleMsg')}',
				'${app:i18n('topupTransTrace.bankReturnTime')}',
				'${app:i18n('topupTransTrace.transCurrency')}',
				'${app:i18n('topupTransTrace.transDc')}',
				'${app:i18n('topupTransTrace.transType')}',
				'${app:i18n('topupTransTrace.transComments')}',
				'${app:i18n('topupTransTrace.transStatus')}',
				'${app:i18n('topupTransTrace.bankTransNum')}',
				'${app:i18n('topupTransTrace.bankPmtCnlType')}',
				'${app:i18n('topupTransTrace.voucherLocation')}',
				'${app:i18n('topupTransTrace.termialType')}',
				'${app:i18n('topupTransTrace.transBankSummary')}',
				'${app:i18n('topupTransTrace.bankReturnResult')}',

			],
			colModel:[

			    {name : 'id',index : 'id',hidden: true},

			    {name : 'reqNum',				index : 'reqNum',				align : 'left', width : '120px'},
// 				{name : 'reqInnerNum',			index : 'reqInnerNum',			align : 'left', width : '120px'},
// 				{name : 'cnlCnlCode',			index : 'cnlCnlCode',			align : 'left', width : '120px'},
				{name : 'cnlSysName',			index : 'cnlSysName',			align : 'left', width : '120px'},

				{name : 'cnlCustCode',			index : 'cnlCustCode',			align : 'left', width : '120px'},
				{name : 'localName',			index : 'localName',			align : 'left', width : '120px'},
				{name : 'transTime',			index : 'transTime',			align : 'center', width : '120px'},
				{name : 'transAmount',			index : 'transAmount',			align : 'right', width : '120px'},
				{name : 'transLatestAmount',	index : 'transLatestAmount',	align : 'right', width : '120px'},

				{name : 'bankDebitName',		index : 'bankDebitName',		align : 'left', width : '120px'},
				{name : 'bankDebitCustName',	index : 'bankDebitCustName',	align : 'left', width : '120px'},
				{name : 'bankDebitCardNum',		index : 'bankDebitCardNum',		align : 'left', width : '120px'},
				{name : 'bankCreditName',		index : 'bankCreditName',		align : 'left', width : '120px'},
				{name : 'bankCreditCustName',	index : 'bankCreditCustName',	align : 'left', width : '120px'},
				{name : 'bankCreditCardNum',	index : 'bankCreditCardNum',	align : 'left', width : '120px'},

				{name : 'operator',				index : 'operator',				align : 'left', width : '120px'},
				{name : 'handleTime',			index : 'handleTime',			align : 'center', width : '120px'},
				{name : 'reviewer',				index : 'reviewer',				align : 'left', width : '120px'},
				{name : 'reviewTime',			index : 'reviewTime',			align : 'center', width : '120px'},

				{name : 'handleStatus',			index : 'handleStatus',			align : 'left', width : '120px'},
				{name : 'handleResult',			index : 'handleResult',			align : 'left', width : '120px'},

				{name : 'handleMsg',			index : 'handleMsg',			hidden: true},
				{name : 'bankReturnTime',		index : 'bankReturnTime',		hidden: true},
				{name : 'transCurrency',		index : 'transCurrency',		hidden: true},
				{name : 'transDc',				index : 'transDc',				hidden: true},
				{name : 'transType',			index : 'transType',			hidden: true},
				{name : 'transComments',		index : 'transComments',		hidden: true},
				{name : 'transStatus',			index : 'transStatus',			hidden: true},
				{name : 'bankTransNum',			index : 'bankTransNum',			hidden: true},
				{name : 'bankPmtCnlType',		index : 'bankPmtCnlType',		hidden: true},
				{name : 'voucherLocation',		index : 'voucherLocation',		hidden: true},
				{name : 'termialType',			index : 'termialType',			hidden: true},
				{name : 'transBankSummary',		index : 'transBankSummary',		hidden: true},
				{name : 'bankReturnResult',		index : 'bankReturnResult',		hidden: true},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
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
			caption: "${app:i18n('topupTransTrace.topupTransTraceListJsp.tableTitle')}",
			exportFileName: "列表.xls",
			gridComplete: function() {
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
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("reqNum", 		$("#reqNum").val());
		$("#gridTable").setPostDataItem("reqInnerNum", 	$("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", 	$("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("cnlSysName", 	$("#cnlSysName").val());
		$("#gridTable").setPostDataItem("cnlCustCode", 	$("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("localName", 	$("#localName").val());
		$("#gridTable").setPostDataItem("startTime", 	$("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", 		$("#endTime").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
	}
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"topupTransTraceSearch.action"}).trigger("reloadGrid");
	}
	function clearWarning() {
		$("div.warning span").html("");
		$("div.warning").hide();
	}
	function showWarning(msg) {
		$("div.warning span").html(msg);
		$("div.warning").show();
	}
	// 批量审核
	function doChecks(ids) {
		setQueryCondition();
		$("#gridTable").setPostDataItem("ids", ids.join(","));
		$("#gridTable").jqGrid("setGridParam",{url: checksUrl}).trigger("reloadGrid");
	}
</script>

<s:form id="topupTransTraceListForm" method="post" action="topupTransTraceList.action" namespace="/topupTransTrace">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('topupTransTrace.topupTransTraceListJsp.headTitle')}</h3>
		</div>
		<div class="block_container">
			<div class="warning" style="display: none;">
				<span></span>
			</div>

			<div class="fieldset_border fieldset_bg m-b" id="queryField">
				<div class="legend_title">
					<a href="#" class="container_show">${app:i18n('global.jsp.search')}</a>
				</div>
				<div class="fieldset_container" id="test1">
					<table cellpadding="0" cellspacing="0" class="table_form" border="0">
						<thead>
						</thead>
						<tfoot>
						</tfoot>
						<tbody>
							<tr>
								<!-- 申请单号 -->
								<th width="10%">${app:i18n('topupTransTrace.reqNum')}：</th>
								<td width="22%"><input name="topupTransTrace.reqNum" id="reqNum" class="width_c" /></td>
								
								<!-- 渠道名称 -->
								<th>${app:i18n('topupTransTrace.cnlSysName')}：</th>
								<td>
									<select name="cnlCnlCode" id="cnlCnlCode" class="width_c" style="width: 190px">
										<s:iterator value="%{cnlCnlCodesList}" var="cnlCnlCode">
									        <option value="<s:property value="#cnlCnlCode.key" />">
									        	<s:property value="#cnlCnlCode.value" />
									        </option>
										</s:iterator>
									</select>
								</td>
								<!-- 入账处理状态 -->
								<th>${app:i18n('topupTransTrace.handleStatus.condition')}：</th>
								<td>
									<select name="topupTransTrace.handleStatus" id="handleStatus" style="width:80px">
										<option value="" selected>${app:i18n('topupTransTrace.all')}</option>
										<s:iterator value="%{handleStatusList}" id="handleStatus">
									        <option value="<s:property value="#handleStatus.key" />">
									        	<s:property value="#handleStatus.value" />
									        </option>
										</s:iterator>
							         </select>
								</td>
							</tr>
							<tr>
								<!-- 渠道客户编码 -->
								<th>${app:i18n('topupTransTrace.cnlCustCode')}：</th>
								<td><input name="topupTransTrace.cnlCustCode" id="cnlCustCode" class="width_c" /></td>
								<!-- 交易开始时间 -->
								<th>${app:i18n('topupTransTrace.startTime')}：</th>
								<td>
									<input style="width:80%" name="topupTransTrace.startTime" id="startTime" class="Wdate" value="${startTime}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"/> 
								</td>
								<!-- 交易结束时间 -->
								<th>${app:i18n('topupTransTrace.endTime')}：</th>
								<td>
									<input style="width:80%" name="topupTransTrace.endTime" id="endTime" class="Wdate" value="${endTime}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
								
							</tr>
							<tr>
								<!-- 客户名称 -->
								<th>${app:i18n('topupTransTrace.localName')}：</th>
								<td><input name="topupTransTrace.localName" id="localName" class="width_c" /></td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						<a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<app:isPermission resource="O_TOPUP_AUTID">
						<a id="check" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('topupTransTrace.handle')}</span></span></a>
						<a id="checks" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('topupTransTrace.checks')}</span></span></a>
					</app:isPermission>
					<app:isPermission resource="O_TOPUP_DOUBLE_AUTID">
						<a id="recheck" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-audit">${app:i18n('topupTransTrace.review')}</span></span></a>
					</app:isPermission>
					<a id="view" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('topupTransTrace.view')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
