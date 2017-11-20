<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "cnlTransTraceModify.action";
		var deleteUrl = "cnlTransTraceDelete.action";
		var createUrl = "cnlTransTraceCreate.action";
		var searchUrl = "cnlTransTraceSearch.action";
		var exportUrl = "cnlTransTraceExport.action";
		
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
			window.location.href=createUrl;
		});

		$("#delete").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行修改");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#cnlTransTraceListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlTransTraceListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('cnlTransTrace.reqInnerNum')}',
					'${app:i18n('cnlTransTrace.custCode')}',
					'${app:i18n('cnlTransTrace.cnlCustCode')}',
					'${app:i18n('cnlTransTrace.reqNum')}',
					'${app:i18n('cnlTransTrace.reqBatch')}',
					'${app:i18n('cnlTransTrace.stlNum')}',
					'${app:i18n('cnlTransTrace.transType')}',
					'${app:i18n('cnlTransTrace.transDc')}',
					'${app:i18n('cnlTransTrace.transSubType')}',
					'${app:i18n('cnlTransTrace.transOrderNum')}',
					'${app:i18n('cnlTransTrace.transCurrency')}',
					'${app:i18n('cnlTransTrace.transAmount')}',
					'${app:i18n('cnlTransTrace.transLatestAmount')}',
					'${app:i18n('cnlTransTrace.transStatus')}',
					'${app:i18n('cnlTransTrace.transDate')}',
					'${app:i18n('cnlTransTrace.transTime')}',
					'${app:i18n('cnlTransTrace.transRate')}',
					'${app:i18n('cnlTransTrace.transComments')}',
					'${app:i18n('cnlTransTrace.termialType')}',
					'${app:i18n('cnlTransTrace.bankReqTime')}',
					'${app:i18n('cnlTransTrace.bankAccepteTime')}',
					'${app:i18n('cnlTransTrace.bankTransNum')}',
					'${app:i18n('cnlTransTrace.bankHandleNum')}',
					'${app:i18n('cnlTransTrace.bankReturnTime')}',
					'${app:i18n('cnlTransTrace.bankReturnResult')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlType')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlCode')}',
					'${app:i18n('cnlTransTrace.cnlCnlCode')}',
					'${app:i18n('cnlTransTrace.isinGl')}',
					'${app:i18n('cnlTransTrace.inglTime')}',
					'${app:i18n('cnlTransTrace.printedTime')}',
					'${app:i18n('cnlTransTrace.isPrinted')}',
					'${app:i18n('cnlTransTrace.bankCreditAcntCode')}',
					'${app:i18n('cnlTransTrace.bankCreditName')}',
					'${app:i18n('cnlTransTrace.bankCreditCode')}',
					'${app:i18n('cnlTransTrace.bankCreditBranchName')}',
					'${app:i18n('cnlTransTrace.bankCreditBranchCode')}',
					'${app:i18n('cnlTransTrace.bankCreditCustName')}',
					'${app:i18n('cnlTransTrace.bankCreditCardNum')}',
					'${app:i18n('cnlTransTrace.bankDebitAcntCode')}',
					'${app:i18n('cnlTransTrace.bankDebitName')}',
					'${app:i18n('cnlTransTrace.bankDebitCode')}',
					'${app:i18n('cnlTransTrace.bankDebitBranchName')}',
					'${app:i18n('cnlTransTrace.bankDebitBranchCode')}',
					'${app:i18n('cnlTransTrace.bankDebitCustName')}',
					'${app:i18n('cnlTransTrace.bankDebitCardNum')}',
					'${app:i18n('cnlTransTrace.bankReqTrnasDate')}',
					'${app:i18n('cnlTransTrace.bnakServiceFeeAct')}',
					'${app:i18n('cnlTransTrace.bankReqTransTime')}',
					'${app:i18n('cnlTransTrace.bnakHandlePriority')}',
					'${app:i18n('cnlTransTrace.returnUrl')}',
					'${app:i18n('cnlTransTrace.errType')}',
					'${app:i18n('cnlTransTrace.errCode')}',
					'${app:i18n('cnlTransTrace.errMsg')}',
					'${app:i18n('cnlTransTrace.handleStatus')}',
					'${app:i18n('cnlTransTrace.handleResult')}',
					'${app:i18n('cnlTransTrace.handleMsg')}',
					'${app:i18n('cnlTransTrace.handleTime')}',
					'${app:i18n('cnlTransTrace.operator')}',
					'${app:i18n('cnlTransTrace.reviewer')}',
					'${app:i18n('cnlTransTrace.reviewMsg')}',
					'${app:i18n('cnlTransTrace.reviewStatus')}',
					'${app:i18n('cnlTransTrace.reviewResult')}',
					'${app:i18n('cnlTransTrace.reviewTime')}',
					'${app:i18n('cnlTransTrace.voucherNum')}',
					'${app:i18n('cnlTransTrace.voucherLocation')}',
					'${app:i18n('cnlTransTrace.isValid')}',
					'${app:i18n('cnlTransTrace.createTime')}',
					'${app:i18n('cnlTransTrace.updateTime')}',
					'${app:i18n('cnlTransTrace.creator')}',
					'${app:i18n('cnlTransTrace.updator')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'reqInnerNum',index : 'reqInnerNum',width : '10%'},
						{name : 'custCode',index : 'custCode',width : '10%'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
						{name : 'reqNum',index : 'reqNum',width : '10%'},
						{name : 'reqBatch',index : 'reqBatch',width : '10%'},
						{name : 'stlNum',index : 'stlNum',width : '10%'},
						{name : 'transType',index : 'transType',width : '10%'},
						{name : 'transDc',index : 'transDc',width : '10%'},
						{name : 'transSubType',index : 'transSubType',width : '10%'},
						{name : 'transOrderNum',index : 'transOrderNum',width : '10%'},
						{name : 'transCurrency',index : 'transCurrency',width : '10%'},
						{name : 'transAmount',index : 'transAmount',width : '10%'},
						{name : 'transLatestAmount',index : 'transLatestAmount',width : '10%'},
						{name : 'transStatus',index : 'transStatus',width : '10%'},
						{name : 'transDate',index : 'transDate',width : '10%'},
						{name : 'transTime',index : 'transTime',width : '10%'},
						{name : 'transRate',index : 'transRate',width : '10%'},
						{name : 'transComments',index : 'transComments',width : '10%'},
						{name : 'termialType',index : 'termialType',width : '10%'},
						{name : 'bankReqTime',index : 'bankReqTime',width : '10%'},
						{name : 'bankAccepteTime',index : 'bankAccepteTime',width : '10%'},
						{name : 'bankTransNum',index : 'bankTransNum',width : '10%'},
						{name : 'bankHandleNum',index : 'bankHandleNum',width : '10%'},
						{name : 'bankReturnTime',index : 'bankReturnTime',width : '10%'},
						{name : 'bankReturnResult',index : 'bankReturnResult',width : '10%'},
						{name : 'bankPmtCnlType',index : 'bankPmtCnlType',width : '10%'},
						{name : 'bankPmtCnlCode',index : 'bankPmtCnlCode',width : '10%'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode',width : '10%'},
						{name : 'isinGl',index : 'isinGl',width : '10%'},
						{name : 'inglTime',index : 'inglTime',width : '10%'},
						{name : 'printedTime',index : 'printedTime',width : '10%'},
						{name : 'isPrinted',index : 'isPrinted',width : '10%'},
						{name : 'bankCreditAcntCode',index : 'bankCreditAcntCode',width : '10%'},
						{name : 'bankCreditName',index : 'bankCreditName',width : '10%'},
						{name : 'bankCreditCode',index : 'bankCreditCode',width : '10%'},
						{name : 'bankCreditBranchName',index : 'bankCreditBranchName',width : '10%'},
						{name : 'bankCreditBranchCode',index : 'bankCreditBranchCode',width : '10%'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName',width : '10%'},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum',width : '10%'},
						{name : 'bankDebitAcntCode',index : 'bankDebitAcntCode',width : '10%'},
						{name : 'bankDebitName',index : 'bankDebitName',width : '10%'},
						{name : 'bankDebitCode',index : 'bankDebitCode',width : '10%'},
						{name : 'bankDebitBranchName',index : 'bankDebitBranchName',width : '10%'},
						{name : 'bankDebitBranchCode',index : 'bankDebitBranchCode',width : '10%'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName',width : '10%'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum',width : '10%'},
						{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate',width : '10%'},
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct',width : '10%'},
						{name : 'bankReqTransTime',index : 'bankReqTransTime',width : '10%'},
						{name : 'bnakHandlePriority',index : 'bnakHandlePriority',width : '10%'},
						{name : 'returnUrl',index : 'returnUrl',width : '10%'},
						{name : 'errType',index : 'errType',width : '10%'},
						{name : 'errCode',index : 'errCode',width : '10%'},
						{name : 'errMsg',index : 'errMsg',width : '10%'},
						{name : 'handleStatus',index : 'handleStatus',width : '10%'},
						{name : 'handleResult',index : 'handleResult',width : '10%'},
						{name : 'handleMsg',index : 'handleMsg',width : '10%'},
						{name : 'handleTime',index : 'handleTime',width : '10%'},
						{name : 'operator',index : 'operator',width : '10%'},
						{name : 'reviewer',index : 'reviewer',width : '10%'},
						{name : 'reviewMsg',index : 'reviewMsg',width : '10%'},
						{name : 'reviewStatus',index : 'reviewStatus',width : '10%'},
						{name : 'reviewResult',index : 'reviewResult',width : '10%'},
						{name : 'reviewTime',index : 'reviewTime',width : '10%'},
						{name : 'voucherNum',index : 'voucherNum',width : '10%'},
						{name : 'voucherLocation',index : 'voucherLocation',width : '10%'},
						{name : 'isValid',index : 'isValid',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
						{name : 'creator',index : 'creator',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransTrace.cnlTransTraceListJsp.tableTitle')}",
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
	
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:$("#searchUrl").val()}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqBatch", $("#reqBatch").val());
		$("#gridTable").setPostDataItem("stlNum", $("#stlNum").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("transSubType", $("#transSubType").val());
		$("#gridTable").setPostDataItem("transOrderNum", $("#transOrderNum").val());
		$("#gridTable").setPostDataItem("transCurrency", $("#transCurrency").val());
		$("#gridTable").setPostDataItem("transAmount", $("#transAmount").val());
		$("#gridTable").setPostDataItem("transLatestAmount", $("#transLatestAmount").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("transRate", $("#transRate").val());
		$("#gridTable").setPostDataItem("transComments", $("#transComments").val());
		$("#gridTable").setPostDataItem("termialType", $("#termialType").val());
		$("#gridTable").setPostDataItem("bankReqTime", $("#bankReqTime").val());
		$("#gridTable").setPostDataItem("bankAccepteTime", $("#bankAccepteTime").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("bankHandleNum", $("#bankHandleNum").val());
		$("#gridTable").setPostDataItem("bankReturnTime", $("#bankReturnTime").val());
		$("#gridTable").setPostDataItem("bankReturnResult", $("#bankReturnResult").val());
		$("#gridTable").setPostDataItem("bankPmtCnlType", $("#bankPmtCnlType").val());
		$("#gridTable").setPostDataItem("bankPmtCnlCode", $("#bankPmtCnlCode").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("isinGl", $("#isinGl").val());
		$("#gridTable").setPostDataItem("inglTime", $("#inglTime").val());
		$("#gridTable").setPostDataItem("printedTime", $("#printedTime").val());
		$("#gridTable").setPostDataItem("isPrinted", $("#isPrinted").val());
		$("#gridTable").setPostDataItem("bankCreditAcntCode", $("#bankCreditAcntCode").val());
		$("#gridTable").setPostDataItem("bankCreditName", $("#bankCreditName").val());
		$("#gridTable").setPostDataItem("bankCreditCode", $("#bankCreditCode").val());
		$("#gridTable").setPostDataItem("bankCreditBranchName", $("#bankCreditBranchName").val());
		$("#gridTable").setPostDataItem("bankCreditBranchCode", $("#bankCreditBranchCode").val());
		$("#gridTable").setPostDataItem("bankCreditCustName", $("#bankCreditCustName").val());
		$("#gridTable").setPostDataItem("bankCreditCardNum", $("#bankCreditCardNum").val());
		$("#gridTable").setPostDataItem("bankDebitAcntCode", $("#bankDebitAcntCode").val());
		$("#gridTable").setPostDataItem("bankDebitName", $("#bankDebitName").val());
		$("#gridTable").setPostDataItem("bankDebitCode", $("#bankDebitCode").val());
		$("#gridTable").setPostDataItem("bankDebitBranchName", $("#bankDebitBranchName").val());
		$("#gridTable").setPostDataItem("bankDebitBranchCode", $("#bankDebitBranchCode").val());
		$("#gridTable").setPostDataItem("bankDebitCustName", $("#bankDebitCustName").val());
		$("#gridTable").setPostDataItem("bankDebitCardNum", $("#bankDebitCardNum").val());
		$("#gridTable").setPostDataItem("bankReqTrnasDate", $("#bankReqTrnasDate").val());
		$("#gridTable").setPostDataItem("bnakServiceFeeAct", $("#bnakServiceFeeAct").val());
		$("#gridTable").setPostDataItem("bankReqTransTime", $("#bankReqTransTime").val());
		$("#gridTable").setPostDataItem("bnakHandlePriority", $("#bnakHandlePriority").val());
		$("#gridTable").setPostDataItem("returnUrl", $("#returnUrl").val());
		$("#gridTable").setPostDataItem("errType", $("#errType").val());
		$("#gridTable").setPostDataItem("errCode", $("#errCode").val());
		$("#gridTable").setPostDataItem("errMsg", $("#errMsg").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
		$("#gridTable").setPostDataItem("handleResult", $("#handleResult").val());
		$("#gridTable").setPostDataItem("handleMsg", $("#handleMsg").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("operator", $("#operator").val());
		$("#gridTable").setPostDataItem("reviewer", $("#reviewer").val());
		$("#gridTable").setPostDataItem("reviewMsg", $("#reviewMsg").val());
		$("#gridTable").setPostDataItem("reviewStatus", $("#reviewStatus").val());
		$("#gridTable").setPostDataItem("reviewResult", $("#reviewResult").val());
		$("#gridTable").setPostDataItem("reviewTime", $("#reviewTime").val());
		$("#gridTable").setPostDataItem("voucherNum", $("#voucherNum").val());
		$("#gridTable").setPostDataItem("voucherLocation", $("#voucherLocation").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());

	}
</script>

<s:form id="cnlTransTraceListForm" method="post" action="cnlTransTraceList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTraceListJsp.headTitle')}</h3>
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

								<th width="10%">${app:i18n('cnlTransTrace.reqInnerNum')}：</th>
								<td width="30%"><input name="reqInnerNum" id="reqInnerNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.custCode')}：</th>
								<td width="30%"><input name="custCode" id="custCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.cnlCustCode')}：</th>
								<td width="30%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reqNum')}：</th>
								<td width="30%"><input name="reqNum" id="reqNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reqBatch')}：</th>
								<td width="30%"><input name="reqBatch" id="reqBatch" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.stlNum')}：</th>
								<td width="30%"><input name="stlNum" id="stlNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transType')}：</th>
								<td width="30%"><input name="transType" id="transType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transDc')}：</th>
								<td width="30%"><input name="transDc" id="transDc" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transSubType')}：</th>
								<td width="30%"><input name="transSubType" id="transSubType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transOrderNum')}：</th>
								<td width="30%"><input name="transOrderNum" id="transOrderNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transCurrency')}：</th>
								<td width="30%"><input name="transCurrency" id="transCurrency" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transAmount')}：</th>
								<td width="30%"><input name="transAmount" id="transAmount" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transLatestAmount')}：</th>
								<td width="30%"><input name="transLatestAmount" id="transLatestAmount" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transStatus')}：</th>
								<td width="30%"><input name="transStatus" id="transStatus" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transDate')}：</th>
								<td width="30%"><input name="transDate" id="transDate" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transTime')}：</th>
								<td width="30%"><input name="transTime" id="transTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transRate')}：</th>
								<td width="30%"><input name="transRate" id="transRate" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.transComments')}：</th>
								<td width="30%"><input name="transComments" id="transComments" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.termialType')}：</th>
								<td width="30%"><input name="termialType" id="termialType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankReqTime')}：</th>
								<td width="30%"><input name="bankReqTime" id="bankReqTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankAccepteTime')}：</th>
								<td width="30%"><input name="bankAccepteTime" id="bankAccepteTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankTransNum')}：</th>
								<td width="30%"><input name="bankTransNum" id="bankTransNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankHandleNum')}：</th>
								<td width="30%"><input name="bankHandleNum" id="bankHandleNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankReturnTime')}：</th>
								<td width="30%"><input name="bankReturnTime" id="bankReturnTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankReturnResult')}：</th>
								<td width="30%"><input name="bankReturnResult" id="bankReturnResult" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankPmtCnlType')}：</th>
								<td width="30%"><input name="bankPmtCnlType" id="bankPmtCnlType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankPmtCnlCode')}：</th>
								<td width="30%"><input name="bankPmtCnlCode" id="bankPmtCnlCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.cnlCnlCode')}：</th>
								<td width="30%"><input name="cnlCnlCode" id="cnlCnlCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.isinGl')}：</th>
								<td width="30%"><input name="isinGl" id="isinGl" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.inglTime')}：</th>
								<td width="30%"><input name="inglTime" id="inglTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.printedTime')}：</th>
								<td width="30%"><input name="printedTime" id="printedTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.isPrinted')}：</th>
								<td width="30%"><input name="isPrinted" id="isPrinted" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditAcntCode')}：</th>
								<td width="30%"><input name="bankCreditAcntCode" id="bankCreditAcntCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditName')}：</th>
								<td width="30%"><input name="bankCreditName" id="bankCreditName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditCode')}：</th>
								<td width="30%"><input name="bankCreditCode" id="bankCreditCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditBranchName')}：</th>
								<td width="30%"><input name="bankCreditBranchName" id="bankCreditBranchName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditBranchCode')}：</th>
								<td width="30%"><input name="bankCreditBranchCode" id="bankCreditBranchCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditCustName')}：</th>
								<td width="30%"><input name="bankCreditCustName" id="bankCreditCustName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankCreditCardNum')}：</th>
								<td width="30%"><input name="bankCreditCardNum" id="bankCreditCardNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitAcntCode')}：</th>
								<td width="30%"><input name="bankDebitAcntCode" id="bankDebitAcntCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitName')}：</th>
								<td width="30%"><input name="bankDebitName" id="bankDebitName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitCode')}：</th>
								<td width="30%"><input name="bankDebitCode" id="bankDebitCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitBranchName')}：</th>
								<td width="30%"><input name="bankDebitBranchName" id="bankDebitBranchName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitBranchCode')}：</th>
								<td width="30%"><input name="bankDebitBranchCode" id="bankDebitBranchCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitCustName')}：</th>
								<td width="30%"><input name="bankDebitCustName" id="bankDebitCustName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankDebitCardNum')}：</th>
								<td width="30%"><input name="bankDebitCardNum" id="bankDebitCardNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankReqTrnasDate')}：</th>
								<td width="30%"><input name="bankReqTrnasDate" id="bankReqTrnasDate" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bnakServiceFeeAct')}：</th>
								<td width="30%"><input name="bnakServiceFeeAct" id="bnakServiceFeeAct" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bankReqTransTime')}：</th>
								<td width="30%"><input name="bankReqTransTime" id="bankReqTransTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.bnakHandlePriority')}：</th>
								<td width="30%"><input name="bnakHandlePriority" id="bnakHandlePriority" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.returnUrl')}：</th>
								<td width="30%"><input name="returnUrl" id="returnUrl" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.errType')}：</th>
								<td width="30%"><input name="errType" id="errType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.errCode')}：</th>
								<td width="30%"><input name="errCode" id="errCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.errMsg')}：</th>
								<td width="30%"><input name="errMsg" id="errMsg" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.handleStatus')}：</th>
								<td width="30%"><input name="handleStatus" id="handleStatus" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.handleResult')}：</th>
								<td width="30%"><input name="handleResult" id="handleResult" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.handleMsg')}：</th>
								<td width="30%"><input name="handleMsg" id="handleMsg" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.handleTime')}：</th>
								<td width="30%"><input name="handleTime" id="handleTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.operator')}：</th>
								<td width="30%"><input name="operator" id="operator" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reviewer')}：</th>
								<td width="30%"><input name="reviewer" id="reviewer" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reviewMsg')}：</th>
								<td width="30%"><input name="reviewMsg" id="reviewMsg" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reviewStatus')}：</th>
								<td width="30%"><input name="reviewStatus" id="reviewStatus" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reviewResult')}：</th>
								<td width="30%"><input name="reviewResult" id="reviewResult" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.reviewTime')}：</th>
								<td width="30%"><input name="reviewTime" id="reviewTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.voucherNum')}：</th>
								<td width="30%"><input name="voucherNum" id="voucherNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.voucherLocation')}：</th>
								<td width="30%"><input name="voucherLocation" id="voucherLocation" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.isValid')}：</th>
								<td width="30%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.createTime')}：</th>
								<td width="30%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.updateTime')}：</th>
								<td width="30%"><input name="updateTime" id="updateTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.creator')}：</th>
								<td width="30%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransTrace.updator')}：</th>
								<td width="30%"><input name="updator" id="updator" class="width_c" /></td>
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
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
