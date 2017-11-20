<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script>
	$(function(){
		getTodayTime();
	})
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    $("#startTime").val(s1);
	    $("#endTime").val(s2);
	}
</script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "cnlTransErrModify.action";
		var deleteUrl = "cnlTransErrDelete.action";
		var createUrl = "cnlTransErrCreate.action";
		//var searchUrl = "cnlTransErrSearch.action";
		var exportUrl = "cnlTransErrExport.action";
		
		$("#search").click(function(){
			var ok = true;
			
			$("div.warning span").html("");
			$("div.warning").hide();
			
			// 校验渠道订单号合发信息
			if($("#reqNum").val() != null && $("#reqNum").val() != ""){
				var zz = /^[a-zA-Z\d]*$/;
				if(zz.test($("#reqNum").val())){
				}else{
					$("div.warning span").html("请输入正确的渠道订单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统申请流水单号合法信息
			if($("#reqInnerNum").val() != null && $("#reqInnerNum").val() != ""){
				var zz = /^[a-zA-Z\d]*$/;
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
			var timeType = $("#timeType").val();
			if(timeType == null || timeType == "" || createtime == null || createtime == "" || endtime == null || endtime == ""){
				$("div.warning").html("请输入必填信息!");
				$("div.warning").show();
				ok = false;
			}else{
				var	start=new Date(createtime.replace("-", "/").replace("-", "/")); 
			    var	end=new Date(endtime.replace("-", "/").replace("-", "/"));
			    
				var endStart = end.getTime() - start.getTime();
				var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
				var month =leaveTime / (30 * 24 * 3600 * 1000);
				
				var days = Math.abs(parseInt(endStart/(1000*60*60*24)));
				if(days>29){
					$("div.warning").html("开始时间与结束时间间跨度小于等于30天!");
					$("div.warning").show();
					ok=false
					
				}else{
					$("div.warning").hide();
				}
			}
			
			if(ok){
				doSearch();
			}
		});

		
		$("#reset").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			
			// 设置查询参数为空
			$("#queryField :input").val("");
			getTodayTime();
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
					'${app:i18n('cnlTransErr.transNum')}',
					'${app:i18n('cnlTransErr.reqInnerNumr')}',
					'${app:i18n('cnlTransErr.reqNumr')}',
					'${app:i18n('cnlTransErr.transType')}',
					'${app:i18n('cnlTransErr.transDc')}',
					'${app:i18n('cnlTransErr.transCurrency')}',
					'${app:i18n('cnlTransErr.transAmount')}',
					'${app:i18n('cnlTransErr.transStatus')}',
					'${app:i18n('cnlTransErr.transTime')}',
					'${app:i18n('cnlTransErr.transComments')}',
					'${app:i18n('cnlTransErr.bankAccepteTime')}',
					'${app:i18n('cnlTransErr.bankTransNum')}',
					'${app:i18n('cnlTransErr.bankReturnTime')}',
					'${app:i18n('cnlTransErr.bankPmtCnlCoder')}',
					'${app:i18n('cnlTransErr.cnlCnlCoderr')}',
					'${app:i18n('cnlTransErr.isinGl')}',
					'${app:i18n('cnlTransErr.printedTime')}',
					'${app:i18n('cnlTransErr.isPrinted')}',
					'${app:i18n('cnlTransErr.bankCreditName')}',
					'${app:i18n('cnlTransErr.bankCreditCustName')}',
					'${app:i18n('cnlTransErr.bankCreditCardNum')}',
					'${app:i18n('cnlTransErr.bankDebitName')}',
					'${app:i18n('cnlTransErr.bankDebitCustName')}',
					'${app:i18n('cnlTransErr.bankDebitCardNum')}',
					'${app:i18n('cnlTransErr.bankReqTrnasDate')}',
					'${app:i18n('cnlTransErr.bnakServiceFeeAct')}',
					'${app:i18n('cnlTransErr.bankReqTransTime')}',
					'${app:i18n('cnlTransErr.bnakHandlePriority')}',
					'${app:i18n('cnlTransErr.errType')}',
					'${app:i18n('cnlTransErr.errCode')}',
					'${app:i18n('cnlTransErr.errMsg')}',
					'${app:i18n('cnlTransErr.handleStatus')}',
					'${app:i18n('cnlTransErr.operator')}'],
			colModel:[
						{name : 'id', hidden: true,width : '10%',hidedlg:true},
						{name : 'transNum',index : 'transNum'},
						{name : 'reqInnerNum',index : 'reqInnerNum'},
						{name : 'reqNum',index : 'reqNum'},
						{name : 'transType',index : 'transType'},
						{name : 'transDc',index : 'transDc'},
						{name : 'transCurrency',index : 'transCurrency'},
						{name : 'transAmount',index : 'transAmount'},
						{name : 'transStatus',index : 'transStatus'},
						{name : 'transTime',index : 'transTime'},
						{name : 'transComments',index : 'transComments'},
						{name : 'bankAccepteTime',index : 'bankAccepteTime'},
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'bankReturnTime',index : 'bankReturnTime'},
						{name : 'bankPmtCnlCode',index : 'bankPmtCnlCode'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode'},
						{name : 'isinGl',index : 'isinGl'},
						{name : 'printedTime',index : 'printedTime'},
						{name : 'isPrinted',index : 'isPrinted'},
						{name : 'bankCreditName',index : 'bankCreditName'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName'},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum'},
						{name : 'bankDebitName',index : 'bankDebitName'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum'},
						{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate'},
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct'},
						{name : 'bankReqTransTime',index : 'bankReqTransTime'},
						{name : 'bnakHandlePriority',index : 'bnakHandlePriority'},
						{name : 'errType',index : 'errType'},
						{name : 'errCode',index : 'errCode'},
						{name : 'errMsg',index : 'errMsg'},
						{name : 'handleStatus',index : 'handleStatus'},
						{name : 'operator',index : 'operator'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransErr.cnlTransErrListJsp.tableTitle')}",
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
		$("#gridTable").jqGrid("setGridParam",{url:"cnlTransErrSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("fixReqNum", $("#fixReqNum").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqBatch", $("#reqBatch").val());
		$("#gridTable").setPostDataItem("stlNum", $("#stlNum").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("transSubType", $("#transSubType").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("transCurrency", $("#transCurrency").val());
		$("#gridTable").setPostDataItem("transAmount", $("#transAmount").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());
		$("#gridTable").setPostDataItem("transLatestAmount", $("#transLatestAmount").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("transRate", $("#transRate").val());
		$("#gridTable").setPostDataItem("transComments", $("#transComments").val());
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
		$("#gridTable").setPostDataItem("termialType", $("#termialType").val());
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
		$("#gridTable").setPostDataItem("reconStatus", $("#reconStatus").val());
		$("#gridTable").setPostDataItem("reconTime", $("#reconTime").val());
		$("#gridTable").setPostDataItem("reviewer", $("#reviewer").val());
		$("#gridTable").setPostDataItem("reviewMsg", $("#reviewMsg").val());
		$("#gridTable").setPostDataItem("reviewResult", $("#reviewResult").val());
		$("#gridTable").setPostDataItem("reviewTime", $("#reviewTime").val());

		
		//设置时间类型参数
		$("#gridTable").setPostDataItem("timeType",$("#timeType").val());
		//设置开始时间参数
		$("#gridTable").setPostDataItem("startTime",$("#startTime").val());
		//设置结束时间参数
		$("#gridTable").setPostDataItem("endTime",$("#endTime").val());
	}
	
	
	// ===========================对未处理的数据进行人工出处理==============================
	var handleAuditId = "#handleAuditDiv";
	function handleAudit(){
		$("div.warning span").html("");
		$("div.warning").hide();
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var handleStatus = rowData.handleStatus;
			handleStatus = handleStatus.trim(" ");
			//alert(id);
			//alert(""+handleStatus+"");
			if(handleStatus == "未处理"){// 表示未处理状态
				var url = "handleAuditErr.action?id="+id+"&time="+new Date().getTime();
				var title = "人工处理";
				$(handleAuditId).dialog({width:500,height:250,modal:true});
				openDialog(handleAuditId,title,url);
			}else{// 表示已处理状态
				$("div.warning").html("只能处理状态为“未处理”的数据列！");
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
			position:[500,200],
			modal:true,
			useSlide:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
	}
	
</script>

<s:form id="cnlTransErrListForm" method="post" action="cnlTransErrList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransErr.cnlTransErrListJsp.headTitle')}</h3>
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
								<th width="20%">${app:i18n('cnlTransErr.reqInnerNumr')}：</th>
								<td width="20%"><input name="reqInnerNum" id="reqInnerNum" class="width_c" /></td>
								
								<!-- 渠道来源标识CNL_CNL_CODE -->
								<th width="20%">${app:i18n('cnlTransErr.cnlCnlCoderr')}：</th>
								<td width="20%">
									<select id="cnlCnlCode" name="cnlCnlCode" style="width: 100%">
										<option value="">${app:i18n('cnlTransErr.pleaseSelect')}</option>
										<s:iterator value="cnlCnlCodeList" var="cnlCnlCode">
											<option value="${cnlCnlCode.key}">${cnlCnlCode.value}</option>
										</s:iterator>
									</select>
								</td>
								
								<!-- 系统交易流水号TRANS_NUM 关联表中 -->
								<th width="20%">${app:i18n('cnlTransErr.transNumr')}：</th>
								<td width="20%"><input name="transNum" id="transNum" class="width_c" /></td>
							</tr>
							<tr>
								<!-- 渠道订单号REQ_NUM -->	
								<th width="10%">${app:i18n('cnlTransErr.reqNumr')}：</th>
								<td width="20%"><input name="reqNum" id="reqNum" class="width_c" /></td>
								
								<!-- 银行流水号BANK_TRANS_NUM -->
								<th width="10%">${app:i18n('cnlTransErr.bankTransNumr')}：</th>
								<td width="20%"><input name="bankTransNum" id="bankTransNum" class="width_c" /></td>
								
								<!-- 处理状态HANDLE_STATUS -->
								<th width="10%">${app:i18n('cnlTransErr.handleStatusr')}：</th>
								<td width="20%">
									<select id="handleStatus" name="handleStatus" style="width: 100%">
										<option value="">${app:i18n('cnlTransErr.pleaseSelect')}</option>
										<s:iterator value="handleStatusList" var="handleStatus">
											<option value="${handleStatus.key}">${handleStatus.value}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<!-- 时间类型 -->
								<th width="10%"><em>*</em>${app:i18n('cnlTransErr.timeTyper')}：</th>
								<td width="20%">									
									<select id="timeType" name="timeType" style="width: 100%">
										<option value="01">${app:i18n('cnlTransErr.transTime')}</option>
										<option value="02">${app:i18n('cnlTransErr.bankAccepteTimee')}</option>
										<option value="04">${app:i18n('cnlTransErr.bankReturnTimee')}</option>
										<option value="03">${app:i18n('cnlTransErr.handleTime')}</option>
									</select>
								</td>
								
								<!-- 开始时间 -->
								<th width="10%"><em>*</em>${app:i18n('cnlTransErr.startTimer')}：</th>
								<td width="20%">
									<!-- <input class="width_c" readonly="readonly" name="startTime"
										id="startTime" value=""  class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d 00:00:00\'}'})" /> -->
									<input style="width: 99%" name="startTime" id="startTime" class="Wdate" value="${cnlTransErr.startTime}"  readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
								</td>
								
								<!-- 结束时间 -->	
								<th width="10%"><em>*</em>${app:i18n('cnlTransErr.endTimer')}：</th>
								<td width="20%">
									<!-- <input class="width_c" readonly="readonly" name="endTime"
										id="endTime" value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d 23:59:59\'}'})" /> -->
									<input style="width: 99%" name="endTime" id="endTime" class="Wdate" value="${cnlTransErr.endTime}" readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							</tr>
							<tr>
								<!-- 错误类型ERR_TYPE -->
								<th width="10%">${app:i18n('cnlTransErr.errTyper')}：</th>
								<td width="20%">
									<select id="errType" name="errType" style="width: 100%">
										<option value="">${app:i18n('cnlTransErr.pleaseSelect')}</option>
										<s:iterator value="errTypeList" var="errType">
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
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar" align="left">
					<span>
						<a id="handleAudit" class="l-btn-plain l-btn m-l4" onclick="handleAudit();"><span class="l-btn-right"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.handleAudit')}</span></span></a>
					</span>	 
					<span id="handleAuditDiv" class="fieldset_container"/>	
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
