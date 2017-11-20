<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		
		var modifyUrl = "cnlTransTraceModify.action";
		var deleteUrl = "cnlTransTraceDelete.action";
		var createUrl = "cnlTransTraceCreate.action";
		//var searchUrl = "cnlTransTraceSearch.action";
		var exportUrl = "cnlTransTraceExport.action";
		
		$("#search").click(function(){
			var ok = true;
			
			$("div.warning span").html("");
			$("div.warning").hide();
			if(validform().form()){
				
			}else{
				ok = false;
			}
			
			// 校验渠道订单号合发信息
			if($("#reqNum").val() != null && $("#reqNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#reqNum").val())){
				}else{
					$("div.warning span").html("请输入正确的渠道订单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统申请流水单号合法信息
			if($("#reqInnerNum").val() != null && $("#reqInnerNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#reqInnerNum").val())){
				}else{
					$("div.warning span").html("请输入正确的系统申请流水单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验银行交易流水号合法信息
			if($("#bankTransNum").val() != null && $("#bankTransNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#bankTransNum").val())){
				}else{
					$("div.warning span").html("请输入正确的银行交易流水号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统交易流水号合法信息
			if($("#transNum").val() != null && $("#transNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#transNum").val())){
				}else{
					$("div.warning span").html("请输入正确的系统交易流水号！");
					$("div.warning").show();
					ok = false;
				}
			}
			
			
			// 开始时间与结束时间间跨度小于等于一个月的校验
			var createtime = $("#startTime").val();
			var endtime = $("#endTime").val();
			if(createtime == null || createtime == "" || endtime == null || endtime == ""){
				$("div.warning span").html("请输入必填信息!");
				$("div.warning").show();
				ok = false;
			}
			
			
			if(ok){
				doSearch();
			}
		});
		
		// 页面表达校验
		function validform(){
			return $("#cnlTransTraceListForm").validate({
				invalidHandler : function(e, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						var message = "请填写正确的查询信息！";
						$("div.warning span").html(message);
						$("div.warning").show();
					} else {
						$("div.warning").hide();
					}
				}
			});
		}

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
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			rowList:[50,100,200],
			shrinkToFit:false,
			colNames:['ID', 
					'${app:i18n('cnlTransTrace.reqInnerNum')}',
					'${app:i18n('cnlTransTrace.custCode')}',
					'${app:i18n('cnlTransTrace.cnlCustCode')}',
					'${app:i18n('cnlTransTrace.reqNum')}',
					'${app:i18n('cnlTransTrace.reqBatch')}',
					'${app:i18n('cnlTransTrace.transTypess')}',
					'${app:i18n('cnlTransTrace.transDc')}',
					'${app:i18n('cnlTransTrace.tradeSubType')}',
					'${app:i18n('cnlTransTrace.transCurrency')}',
					'${app:i18n('cnlTransTrace.transAmount')}',
					'${app:i18n('cnlTransTrace.trnasStatus')}',
					'${app:i18n('cnlTransTrace.transDate')}',
					'${app:i18n('cnlTransTrace.transTime')}',
					'${app:i18n('cnlTransTrace.transRate')}',
					'${app:i18n('cnlTransTrace.transComments')}',
					'${app:i18n('cnlTransTrace.bankAccepteTime')}',
					'${app:i18n('cnlTransTrace.bankTransNum')}',
					'${app:i18n('cnlTransTrace.bankHandleNum')}',
					'${app:i18n('cnlTransTrace.bankReturnTime')}',
					'${app:i18n('cnlTransTrace.bankReturnResult')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlType')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlCode')}',
					'${app:i18n('cnlTransTrace.cnlCnlCode')}',
					'${app:i18n('cnlTransTrace.isinGl')}',
					'${app:i18n('cnlTransTrace.isPrinted')}',
					
					'${app:i18n('cnlTransTrace.printedTime')}',
					
					'${app:i18n('cnlTransTrace.bankCreditName')}',
					'${app:i18n('cnlTransTrace.bankCreditCustName')}',
					'${app:i18n('cnlTransTrace.bankCreditAcntCode')}',
					'${app:i18n('cnlTransTrace.bankDebitName')}',
					'${app:i18n('cnlTransTrace.bankDebitCustName')}',
					'${app:i18n('cnlTransTrace.bankDebitAcntCode')}',
					'${app:i18n('cnlTransTrace.bankReqTrnasDate')}',
					'${app:i18n('cnlTransTrace.bnakServiceFeeAct')}',
					'${app:i18n('cnlTransTrace.bankReqTransTime')}',
					'${app:i18n('cnlTransTrace.bnakHandlePriority')}',
					'${app:i18n('cnlTransTrace.errType')}',
					'${app:i18n('cnlTransTrace.errCode')}',
					'${app:i18n('cnlTransTrace.errMsg')}',
					'${app:i18n('cnlTransTrace.handleStatus')}',
					'${app:i18n('cnlTransTrace.handleMsg')}',
					'${app:i18n('cnlTransTrace.handleTime')}',
					'${app:i18n('cnlTransTrace.operator')}',
					'${app:i18n('cnlTransTrace.isValid')}',
					'${app:i18n('cnlTransTrace.createTime')}',
					'${app:i18n('cnlTransTrace.updateTime')}',
					'${app:i18n('cnlTransTrace.creator')}',
					'${app:i18n('cnlTransTrace.updator')}',

					'${app:i18n('cnlTransTrace.bankCreditCardNum')}',
					'${app:i18n('cnlTransTrace.bankDebitCardNum')}',
					'${app:i18n('cnlTransTrace.transNum')}' ],
			colModel:[
						{name : 'id', hidden: true,width : '10%'},
						{name : 'reqInnerNum',index : 'reqInnerNum'},
						{name : 'custCode',index : 'custCode', hidden: true},
						{name : 'cnlCustCode',index : 'cnlCustCode', hidden: true},
						{name : 'reqNum',index : 'reqNum'},
						{name : 'reqBatch',index : 'reqBatch', hidden: true},
						{name : 'transType',index : 'transType'},
						{name : 'transDc',index : 'transDc'},
						{name : 'tradeSubType',index : 'tradeSubType', hidden: true},
						{name : 'transCurrency',index : 'transCurrency'},
						{name : 'transAmount',index : 'transAmount'},
						{name : 'trnasStatus',index : 'trnasStatus'},
						{name : 'transDate',index : 'transDate', hidden: true},
						{name : 'transTime',index : 'transTime'},
						{name : 'transRate',index : 'transRate', hidden: true},
						{name : 'transComments',index : 'transComments'},
						{name : 'bankAccepteTime',index : 'bankAccepteTime'},
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'bankHandleNum',index : 'bankHandleNum', hidden: true},
						{name : 'bankReturnTime',index : 'bankReturnTime'},
						{name : 'bankReturnResult',index : 'bankReturnResult', hidden: true},
						{name : 'bankPmtCnlType',index : 'bankPmtCnlType', hidden: true},
						{name : 'bankPmtCnlCode',index : 'bankPmtCnlCode'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode'},
						{name : 'isinGl',index : 'isinGl'},
						{name : 'isPrinted',index : 'isPrinted'},
						
						{name : 'printedTime',index : 'printedTime'},
						
						{name : 'bankCreditName',index : 'bankCreditName'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName'},
						{name : 'bankCreditAcntCode',index : 'bankCreditAcntCode', hidden: true},
						{name : 'bankDebitName',index : 'bankDebitName'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName'},
						{name : 'bankDebitAcntCode',index : 'bankDebitAcntCode', hidden: true},
						{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate'},
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct'},
						{name : 'bankReqTransTime',index : 'bankReqTransTime'},
						{name : 'bnakHandlePriority',index : 'bnakHandlePriority'},
						{name : 'errType',index : 'errType'},
						{name : 'errCode',index : 'errCode'},
						{name : 'errMsg',index : 'errMsg'},
						{name : 'handleStatus',index : 'handleStatus'},
						{name : 'handleMsg',index : 'handleMsg', hidden: true},
						{name : 'handleTime',index : 'handleTime', hidden: true},
						{name : 'operator',index : 'operator'},
						{name : 'isValid',index : 'isValid', hidden: true},
						{name : 'createTime',index : 'createTime', hidden: true},
						{name : 'updateTime',index : 'updateTime', hidden: true},
						{name : 'creator',index : 'creator', hidden: true},
						{name : 'updator',index : 'updator', hidden: true},

						{name : 'bankCreditCardNum',index : 'bankCreditCardNum'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum'},
						{name : 'transNum',index : 'transNum'},
						
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransTrace.cnlTransTraceExceptionListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				if(ids.length <= 0){
					$("div.warning span").html("没有记录！");
					$("div.warning").show();
				}
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
		$("#gridTable").jqGrid("setGridParam",{url:"cnlTransTraceSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqBatch", $("#reqBatch").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("tradeSubType", $("#tradeSubType").val());
		$("#gridTable").setPostDataItem("transCurrency", $("#transCurrency").val());
		$("#gridTable").setPostDataItem("transAmount", $("#transAmount").val());
		$("#gridTable").setPostDataItem("trnasStatus", $("#trnasStatus").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("transRate", $("#transRate").val());
		$("#gridTable").setPostDataItem("transComments", $("#transComments").val());
		$("#gridTable").setPostDataItem("bankAccepteTime", $("#bankAccepteTime").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("bankHandleNum", $("#bankHandleNum").val());
		$("#gridTable").setPostDataItem("bankReturnTime", $("#bankReturnTime").val());
		$("#gridTable").setPostDataItem("bankReturnResult", $("#bankReturnResult").val());
		$("#gridTable").setPostDataItem("bankPmtCnlType", $("#bankPmtCnlType").val());
		$("#gridTable").setPostDataItem("bankPmtCnlCode", $("#bankPmtCnlCode").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("isinGl", $("#isinGl").val());
		$("#gridTable").setPostDataItem("isPrinted", $("#isPrinted").val());
		$("#gridTable").setPostDataItem("bankCreditName", $("#bankCreditName").val());
		$("#gridTable").setPostDataItem("bankCreditCustName", $("#bankCreditCustName").val());
		$("#gridTable").setPostDataItem("bankCreditAcntCode", $("#bankCreditAcntCode").val());
		$("#gridTable").setPostDataItem("bankDebitName", $("#bankDebitName").val());
		$("#gridTable").setPostDataItem("bankDebitCustName", $("#bankDebitCustName").val());
		$("#gridTable").setPostDataItem("bankDebitAcntCode", $("#bankDebitAcntCode").val());
		$("#gridTable").setPostDataItem("bankReqTrnasDate", $("#bankReqTrnasDate").val());
		$("#gridTable").setPostDataItem("bnakServiceFeeAct", $("#bnakServiceFeeAct").val());
		$("#gridTable").setPostDataItem("bankReqTransTime", $("#bankReqTransTime").val());
		$("#gridTable").setPostDataItem("bnakHandlePriority", $("#bnakHandlePriority").val());
		$("#gridTable").setPostDataItem("errType", $("#errType").val());
		$("#gridTable").setPostDataItem("errCode", $("#errCode").val());
		$("#gridTable").setPostDataItem("errMsg", $("#errMsg").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
		$("#gridTable").setPostDataItem("handleMsg", $("#handleMsg").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("operator", $("#operator").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());

		
		//设置时间类型参数
		$("#gridTable").setPostDataItem("timeType",$("#timeType").val());
		//设置开始时间参数
		$("#gridTable").setPostDataItem("startTime",$("#startTime").val());
		//设置结束时间参数
		$("#gridTable").setPostDataItem("endTime",$("#endTime").val());
		
		// 系统交易流水号TRANS_NUM
		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		
	}
	
	
	// ===========================对未处理的数据进行人工出处理==============================
	var handleAuditId = "#handleAuditDiv";
	function handleAudit(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var handleStatus = rowData.handleStatus;
			handleStatus = handleStatus.trim(" ");
			alert(id);
			alert("---"+handleStatus+"---");
			if(handleStatus == "未处理"){// 表示未处理状态
				var url = "handleAudit.action?id="+id+"&time="+new Date().getTime();
				var title = "人工处理";
				$(handleAuditId).dialog({width:500,height:250,modal:true});
				openDialog(handleAuditId,title,url);
			}else{// 表示已处理状态
				$("div.warning span").html("该数据列状态为已处理！");
				$("div.warning").show();
		    	return false;
		    }
		}else{
			$("div.warning span").html("请选择一条未处理状态的数据列！");
			$("div.warning").show();
			return ;
		}
	}
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:[400,100],
			modal:true,
			useSlide:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
	}
	
</script>

<s:form id="cnlTransTraceListForm" method="post" action="cnlTransTraceList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTraceExceptionList.headTitle')}</h3>
		</div>
		<div class="block_container">
			<div class="warning" style="display:none;">
				<span></span>
			</div>
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
								<!-- 系统申请流水单号REQ_INNER_NUM -->
								<th width="20%">${app:i18n('cnlTransTrace.reqInnerNum')}：</th>
								<td width="20%"><input name="reqInnerNum" id="reqInnerNum" class="width_c" /></td>
								
								<!-- 渠道来源标识CNL_CNL_CODE -->
								<th width="20%">${app:i18n('cnlTransTrace.cnlCnlCoder')}：</th>
								<td width="20%">
									<select id="cnlCnlCode" name="cnlCnlCode">
										<option value="">${app:i18n('cnlTransTrace.pleaseSelect')}</option>
										<s:iterator value="cnlCnlCodeList" var="cnlCnlCode">
											<option value="${cnlCnlCode.key}">${cnlCnlCode.value}</option>
										</s:iterator>
									</select>
								</td>
								
								<!-- 系统交易流水号TRANS_NUM 关联表中 -->
								<th width="20%">${app:i18n('cnlTransTrace.transNum')}：</th>
								<td width="20%"><input name="transNum" id="transNum" class="width_c" /></td>
							</tr>
							<tr>
								<!-- 渠道订单号REQ_NUM -->	
								<th width="10%">${app:i18n('cnlTransTrace.reqNum')}：</th>
								<td width="20%"><input name="reqNum" id="reqNum" class="width_c" /></td>
								
								<!-- 银行流水号BANK_TRANS_NUM -->
								<th width="10%">${app:i18n('cnlTransTrace.bankTransNum')}：</th>
								<td width="20%"><input name="bankTransNum" id="bankTransNum" class="width_c" /></td>
								
								<!-- 处理状态HANDLE_STATUS -->
								<th width="10%">${app:i18n('cnlTransTrace.handleStatus')}：</th>
								<td width="20%">
									<select id="handleStatus" name="handleStatus">
										<option value="">${app:i18n('cnlTransTrace.pleaseSelect')}</option>
										<s:iterator value="handleStatusList" var="handleStatus">
											<option value="${handleStatus.key}">${handleStatus.value}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<!-- 时间类型 -->
								<th width="10%"><em>*</em>${app:i18n('cnlTransTrace.timeType')}：</th>
								<td width="20%">									
									<select id="timeType" name="timeType">
										<option value="00">${app:i18n('cnlTransTrace.transTime')}</option>
										<option value="02">${app:i18n('cnlTransTrace.bankAccepteTimee')}</option>
										<option value="03">${app:i18n('cnlTransTrace.bankReturnTimee')}</option>
										<option value="04">${app:i18n('cnlTransTrace.handleTime')}</option>
									</select>
								</td>
								
								<!-- 开始时间 -->
								<th width="10%"><em>*</em>${app:i18n('cnlTransTrace.startTime')}：</th>
								<td width="20%">
									<input class="width_c" readonly="readonly" name="startTime"
										id="startTime" value=""  class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d 00:00:00\'}'})" />
								</td>
								
								<!-- 结束时间 -->	
								<th width="10%"><em>*</em>${app:i18n('cnlTransTrace.endTime')}：</th>
								<td width="20%">
									<input class="width_c" readonly="readonly" name="endTime"
										id="endTime" value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d 23:59:59\'}'})" />
								</td>
							</tr>
							<tr>
								<!-- 错误类型ERR_TYPE -->
								<th width="10%">${app:i18n('cnlTransTrace.errType')}：</th>
								<td width="20%">
									<select id="errType" name="errType">
										<s:iterator value="errTypeList" var="errType" begin="5" end="7">
											<option value="${errType.key}">${errType.value}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
						
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					</div>
				</div>
			</div>
			<div id="handleAuditDiv" class="fieldset_container">

			</div>	
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar" align="right">
					<a id="handleAudit" class="l-btn-plain l-btn m-l4" onclick="handleAudit();"><span class="l-btn-right"><span class="l-btn-text icon-add">${app:i18n('global.jsp.handleAudit')}</span></span></a> 
				</div>
			</div>
		</div>
	</div>
</div>


</s:form>
