<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "cnlTransRefundModify.action";
		var deleteUrl = "cnlTransRefundDelete.action";
		var createUrl = "cnlTransRefundCreate.action";
		var searchUrl = "cnlTransRefundSearch.action";
		var exportUrl = "cnlTransRefundExport.action";
		
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
			$("#cnlTransRefundListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlTransRefundListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('cnlTransRefund.refundCode')}',
					'${app:i18n('cnlTransRefund.cnlCnlCode')}',
					'${app:i18n('cnlTransRefund.reqNum')}',
					'${app:i18n('cnlTransRefund.refundContact')}',
					'${app:i18n('cnlTransRefund.refundContactTel')}',
					'${app:i18n('cnlTransRefund.refundReason')}',
					'${app:i18n('cnlTransRefund.refundTime')}',
					'${app:i18n('cnlTransRefund.refundCurrency')}',
					'${app:i18n('cnlTransRefund.refundAmount')}',
					'${app:i18n('cnlTransRefund.refundDate')}',
					'${app:i18n('cnlTransRefund.refundStatus')}',
					'${app:i18n('cnlTransRefund.voucherNum')}',
					'${app:i18n('cnlTransRefund.voucherLocation')}',
					'${app:i18n('cnlTransRefund.refundFailReason')}',
					'${app:i18n('cnlTransRefund.bankTransNum')}',
					'${app:i18n('cnlTransRefund.bankCreditCustName')}',
					'${app:i18n('cnlTransRefund.bankCreditName')}',
					'${app:i18n('cnlTransRefund.bankCreditCode')}',
					'${app:i18n('cnlTransRefund.bankCreditCardNum')}',
					'${app:i18n('cnlTransRefund.bankDebitCustName')}',
					'${app:i18n('cnlTransRefund.bankDebitName')}',
					'${app:i18n('cnlTransRefund.bankDebitCode')}',
					'${app:i18n('cnlTransRefund.bankDebitCardNum')}',
					'${app:i18n('cnlTransRefund.handler')}',
					'${app:i18n('cnlTransRefund.handleTime')}',
					'${app:i18n('cnlTransRefund.reviewer')}',
					'${app:i18n('cnlTransRefund.reviewResult')}',
					'${app:i18n('cnlTransRefund.reviewErrCode')}',
					'${app:i18n('cnlTransRefund.reviewTime')}',
					'${app:i18n('cnlTransRefund.reviewMsg')}',
					'${app:i18n('cnlTransRefund.isValid')}',
					'${app:i18n('cnlTransRefund.createTime')}',
					'${app:i18n('cnlTransRefund.updateTime')}',
					'${app:i18n('cnlTransRefund.creator')}',
					'${app:i18n('cnlTransRefund.updator')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'refundCode',index : 'refundCode',width : '10%'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode',width : '10%'},
						{name : 'reqNum',index : 'reqNum',width : '10%'},
						{name : 'refundContact',index : 'refundContact',width : '10%'},
						{name : 'refundContactTel',index : 'refundContactTel',width : '10%'},
						{name : 'refundReason',index : 'refundReason',width : '10%'},
						{name : 'refundTime',index : 'refundTime',width : '10%'},
						{name : 'refundCurrency',index : 'refundCurrency',width : '10%'},
						{name : 'refundAmount',index : 'refundAmount',width : '10%'},
						{name : 'refundDate',index : 'refundDate',width : '10%'},
						{name : 'refundStatus',index : 'refundStatus',width : '10%'},
						{name : 'voucherNum',index : 'voucherNum',width : '10%'},
						{name : 'voucherLocation',index : 'voucherLocation',width : '10%'},
						{name : 'refundFailReason',index : 'refundFailReason',width : '10%'},
						{name : 'bankTransNum',index : 'bankTransNum',width : '10%'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName',width : '10%'},
						{name : 'bankCreditName',index : 'bankCreditName',width : '10%'},
						{name : 'bankCreditCode',index : 'bankCreditCode',width : '10%'},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum',width : '10%'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName',width : '10%'},
						{name : 'bankDebitName',index : 'bankDebitName',width : '10%'},
						{name : 'bankDebitCode',index : 'bankDebitCode',width : '10%'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum',width : '10%'},
						{name : 'handler',index : 'handler',width : '10%'},
						{name : 'handleTime',index : 'handleTime',width : '10%'},
						{name : 'reviewer',index : 'reviewer',width : '10%'},
						{name : 'reviewResult',index : 'reviewResult',width : '10%'},
						{name : 'reviewErrCode',index : 'reviewErrCode',width : '10%'},
						{name : 'reviewTime',index : 'reviewTime',width : '10%'},
						{name : 'reviewMsg',index : 'reviewMsg',width : '10%'},
						{name : 'isValid',index : 'isValid',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
						{name : 'creator',index : 'creator',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransRefund.cnlTransRefundListJsp.tableTitle')}",
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

		$("#gridTable").setPostDataItem("refundCode", $("#refundCode").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("refundContact", $("#refundContact").val());
		$("#gridTable").setPostDataItem("refundContactTel", $("#refundContactTel").val());
		$("#gridTable").setPostDataItem("refundReason", $("#refundReason").val());
		$("#gridTable").setPostDataItem("refundTime", $("#refundTime").val());
		$("#gridTable").setPostDataItem("refundCurrency", $("#refundCurrency").val());
		$("#gridTable").setPostDataItem("refundAmount", $("#refundAmount").val());
		$("#gridTable").setPostDataItem("refundDate", $("#refundDate").val());
		$("#gridTable").setPostDataItem("refundStatus", $("#refundStatus").val());
		$("#gridTable").setPostDataItem("voucherNum", $("#voucherNum").val());
		$("#gridTable").setPostDataItem("voucherLocation", $("#voucherLocation").val());
		$("#gridTable").setPostDataItem("refundFailReason", $("#refundFailReason").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("bankCreditCustName", $("#bankCreditCustName").val());
		$("#gridTable").setPostDataItem("bankCreditName", $("#bankCreditName").val());
		$("#gridTable").setPostDataItem("bankCreditCode", $("#bankCreditCode").val());
		$("#gridTable").setPostDataItem("bankCreditCardNum", $("#bankCreditCardNum").val());
		$("#gridTable").setPostDataItem("bankDebitCustName", $("#bankDebitCustName").val());
		$("#gridTable").setPostDataItem("bankDebitName", $("#bankDebitName").val());
		$("#gridTable").setPostDataItem("bankDebitCode", $("#bankDebitCode").val());
		$("#gridTable").setPostDataItem("bankDebitCardNum", $("#bankDebitCardNum").val());
		$("#gridTable").setPostDataItem("handler", $("#handler").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("reviewer", $("#reviewer").val());
		$("#gridTable").setPostDataItem("reviewResult", $("#reviewResult").val());
		$("#gridTable").setPostDataItem("reviewErrCode", $("#reviewErrCode").val());
		$("#gridTable").setPostDataItem("reviewTime", $("#reviewTime").val());
		$("#gridTable").setPostDataItem("reviewMsg", $("#reviewMsg").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());

	}
</script>

<s:form id="cnlTransRefundListForm" method="post" action="cnlTransRefundList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.cnlTransRefundListJsp.headTitle')}</h3>
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

								<th width="10%">${app:i18n('cnlTransRefund.refundCode')}：</th>
								<td width="30%"><input name="refundCode" id="refundCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.cnlCnlCode')}：</th>
								<td width="30%"><input name="cnlCnlCode" id="cnlCnlCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reqNum')}：</th>
								<td width="30%"><input name="reqNum" id="reqNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundContact')}：</th>
								<td width="30%"><input name="refundContact" id="refundContact" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundContactTel')}：</th>
								<td width="30%"><input name="refundContactTel" id="refundContactTel" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundReason')}：</th>
								<td width="30%"><input name="refundReason" id="refundReason" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundTime')}：</th>
								<td width="30%"><input name="refundTime" id="refundTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundCurrency')}：</th>
								<td width="30%"><input name="refundCurrency" id="refundCurrency" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundAmount')}：</th>
								<td width="30%"><input name="refundAmount" id="refundAmount" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundDate')}：</th>
								<td width="30%"><input name="refundDate" id="refundDate" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundStatus')}：</th>
								<td width="30%"><input name="refundStatus" id="refundStatus" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.voucherNum')}：</th>
								<td width="30%"><input name="voucherNum" id="voucherNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.voucherLocation')}：</th>
								<td width="30%"><input name="voucherLocation" id="voucherLocation" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.refundFailReason')}：</th>
								<td width="30%"><input name="refundFailReason" id="refundFailReason" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankTransNum')}：</th>
								<td width="30%"><input name="bankTransNum" id="bankTransNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankCreditCustName')}：</th>
								<td width="30%"><input name="bankCreditCustName" id="bankCreditCustName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankCreditName')}：</th>
								<td width="30%"><input name="bankCreditName" id="bankCreditName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankCreditCode')}：</th>
								<td width="30%"><input name="bankCreditCode" id="bankCreditCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankCreditCardNum')}：</th>
								<td width="30%"><input name="bankCreditCardNum" id="bankCreditCardNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankDebitCustName')}：</th>
								<td width="30%"><input name="bankDebitCustName" id="bankDebitCustName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankDebitName')}：</th>
								<td width="30%"><input name="bankDebitName" id="bankDebitName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankDebitCode')}：</th>
								<td width="30%"><input name="bankDebitCode" id="bankDebitCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.bankDebitCardNum')}：</th>
								<td width="30%"><input name="bankDebitCardNum" id="bankDebitCardNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.handler')}：</th>
								<td width="30%"><input name="handler" id="handler" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.handleTime')}：</th>
								<td width="30%"><input name="handleTime" id="handleTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reviewer')}：</th>
								<td width="30%"><input name="reviewer" id="reviewer" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reviewResult')}：</th>
								<td width="30%"><input name="reviewResult" id="reviewResult" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reviewErrCode')}：</th>
								<td width="30%"><input name="reviewErrCode" id="reviewErrCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reviewTime')}：</th>
								<td width="30%"><input name="reviewTime" id="reviewTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.reviewMsg')}：</th>
								<td width="30%"><input name="reviewMsg" id="reviewMsg" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.isValid')}：</th>
								<td width="30%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.createTime')}：</th>
								<td width="30%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.updateTime')}：</th>
								<td width="30%"><input name="updateTime" id="updateTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.creator')}：</th>
								<td width="30%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlTransRefund.updator')}：</th>
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
