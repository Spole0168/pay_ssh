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
			var reqNum = $("#reqNum").val();
			if(reqNum != null && reqNum != ""){
				var zz = /^[a-zA-Z\d]*$/;
				if(zz.test(reqNum)){
				}else{
					$("div.warning span").html("请输入正确的渠道订单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统申请流水单号合法信息
			var reqInnerNum = $("#reqInnerNum").val();
			if(reqInnerNum != null && reqInnerNum != ""){
				var zz = /^[a-zA-Z\d]*$/;
				if(zz.test(reqInnerNum)){
				}else{
					$("div.warning span").html("请输入正确的系统申请流水单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验银行交易流水号合法信息
			var bankTransNum = $("#bankTransNum").val();
			if(bankTransNum != null && bankTransNum != ""){
				var zz = /^[0-9]*$/;
				if(zz.test(bankTransNum)){
				}else{
					$("div.warning span").html("请输入正确的银行交易流水号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统交易流水号合法信息
			var transNum = $("#transNum").val();
			if(transNum != null && transNum != ""){
				var zz = /^[0-9]*$/;
				if(zz.test(transNum)){
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
			colNames:[
			          
			        'ID', 
			        '${app:i18n('cnlTransTrace.reqNum')}', // 渠道订单号=关联表中的申请单号
					'${app:i18n('cnlTransTrace.reqInnerNum')}', // 系统申请流水单号=关联表中的 申请单流水号
					'${app:i18n('cnlTransTrace.bankTransNum')}', // 银行交易流水号==关联关系
					'${app:i18n('cnlTransTrace.transNumr')}', // 系统交易流水号
					'${app:i18n('cnlTransTrace.bankReqTime')}', // 请求时间 = 关联表中的 
					'${app:i18n('cnlTransTrace.bankAccepteTimer')}', // 银行网关时间
					'${app:i18n('cnlTransTrace.transTypei')}', // 接口类型=交易类型
					'${app:i18n('cnlTransTrace.msgHandleStatus')}', // 报文的处理状态**************
					//'${app:i18n('cnlTransTrace.bankMsgCode')}', // 银行报文编码
					
					'${app:i18n('cnlTransTrace.bankReturnResult')}',
					'${app:i18n('cnlTransTrace.custCode')}',
					'${app:i18n('cnlTransTrace.cnlCustCode')}',
					'${app:i18n('cnlTransTrace.reqBatch')}',
					'${app:i18n('cnlTransTrace.transDc')}',
					'${app:i18n('cnlTransTrace.tradeSubType')}',
					'${app:i18n('cnlTransTrace.transCurrency')}',
					'${app:i18n('cnlTransTrace.transAmount')}',
					'${app:i18n('cnlTransTrace.trnasStatus')}',
					'${app:i18n('cnlTransTrace.transDate')}',
					'${app:i18n('cnlTransTrace.transTime')}',
					'${app:i18n('cnlTransTrace.transRate')}',
					'${app:i18n('cnlTransTrace.transComments')}',
					'${app:i18n('cnlTransTrace.bankHandleNum')}',
					'${app:i18n('cnlTransTrace.bankReturnTime')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlType')}',
					'${app:i18n('cnlTransTrace.bankPmtCnlCode')}',
					'${app:i18n('cnlTransTrace.cnlCnlCode')}',
					'${app:i18n('cnlTransTrace.isinGl')}',
					'${app:i18n('cnlTransTrace.isPrinted')}',
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
					'${app:i18n('cnlTransTrace.updator')}'
					 ],
			colModel:[
			          
					{name : 'id', hidden: true,width : '10%',hidedlg:true},
					{name : 'reqNum',index : 'reqNum'},
					{name : 'reqInnerNum',index : 'reqInnerNum'},
					{name : 'bankTransNum',index : 'bankTransNum'},
					{name : 'transNum',index : 'transNum'},
					{name : 'bankReqTime',index : 'bankReqTime'},
					{name : 'bankAccepteTime',index : 'bankAccepteTime'},
					{name : 'transType',index : 'transType'},
					
					{name : 'msgHandleStatus',index : 'msgHandleStatus'},// 报文处理状态
			          
					//{name : 'bankMsgCode',index : 'bankMsgCode', hidden: true},// 银行报文编码  
					
					{name : 'bankReturnResult',index : 'bankReturnResult', hidden: true},
					{name : 'custCode',index : 'custCode', hidden: true, hidden: true},
					{name : 'cnlCustCode',index : 'cnlCustCode', hidden: true, hidden: true},
					{name : 'reqBatch',index : 'reqBatch', hidden: true, hidden: true},
					{name : 'transDc',index : 'transDc', hidden: true, hidden: true},
					{name : 'tradeSubType',index : 'tradeSubType', hidden: true, hidden: true},
					{name : 'transCurrency',index : 'transCurrency', hidden: true, hidden: true},
					{name : 'transAmount',index : 'transAmount', hidden: true, hidden: true},
					{name : 'trnasStatus',index : 'trnasStatus', hidden: true, hidden: true},
					{name : 'transDate',index : 'transDate', hidden: true, hidden: true},
					{name : 'transTime',index : 'transTime', hidden: true, hidden: true},
					{name : 'transRate',index : 'transRate', hidden: true, hidden: true},
					{name : 'transComments',index : 'transComments', hidden: true, hidden: true},
					{name : 'bankHandleNum',index : 'bankHandleNum', hidden: true, hidden: true},
					{name : 'bankReturnTime',index : 'bankReturnTime', hidden: true, hidden: true},
					{name : 'bankPmtCnlType',index : 'bankPmtCnlType', hidden: true, hidden: true},
					{name : 'bankPmtCnlCode',index : 'bankPmtCnlCode', hidden: true, hidden: true},
					{name : 'cnlCnlCode',index : 'cnlCnlCode', hidden: true, hidden: true},
					{name : 'isinGl',index : 'isinGl', hidden: true, hidden: true},
					{name : 'isPrinted',index : 'isPrinted', hidden: true, hidden: true},
					{name : 'bankCreditName',index : 'bankCreditName', hidden: true, hidden: true},
					{name : 'bankCreditCustName',index : 'bankCreditCustName', hidden: true, hidden: true},
					{name : 'bankCreditAcntCode',index : 'bankCreditAcntCode', hidden: true, hidden: true},
					{name : 'bankDebitName',index : 'bankDebitName', hidden: true, hidden: true},
					{name : 'bankDebitCustName',index : 'bankDebitCustName', hidden: true, hidden: true},
					{name : 'bankDebitAcntCode',index : 'bankDebitAcntCode', hidden: true, hidden: true},
					{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate', hidden: true, hidden: true},
					{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct', hidden: true, hidden: true},
					{name : 'bankReqTransTime',index : 'bankReqTransTime', hidden: true, hidden: true},
					{name : 'bnakHandlePriority',index : 'bnakHandlePriority', hidden: true, hidden: true},
					{name : 'errType',index : 'errType', hidden: true, hidden: true},
					{name : 'errCode',index : 'errCode', hidden: true, hidden: true},
					{name : 'errMsg',index : 'errMsg', hidden: true, hidden: true},
					{name : 'handleStatus',index : 'handleStatus', hidden: true, hidden: true},
					{name : 'handleMsg',index : 'handleMsg', hidden: true, hidden: true},
					{name : 'handleTime',index : 'handleTime', hidden: true, hidden: true},
					{name : 'operator',index : 'operator', hidden: true, hidden: true},
					{name : 'isValid',index : 'isValid', hidden: true, hidden: true},
					{name : 'createTime',index : 'createTime', hidden: true, hidden: true},
					{name : 'updateTime',index : 'updateTime', hidden: true, hidden: true},
					{name : 'creator',index : 'creator', hidden: true, hidden: true},
					{name : 'updator',index : 'updator', hidden: true, hidden: true},
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

		// 获取到 报文处理状态
		var msgHandleStatus = $("#msgHandleStatus").val();
		var msg = msgHandleStatus == "02";
		if(!msg){ // 不是未发送状态下
			//设置时间类型参数
			$("#gridTable").setPostDataItem("timeType",$("#timeType").val());
			//设置开始时间参数
			$("#gridTable").setPostDataItem("startTime",$("#startTime").val());
			//设置结束时间参数
			$("#gridTable").setPostDataItem("endTime",$("#endTime").val());
		}
		
		// 渠道订单号
		$("#gridTable").setPostDataItem("reqNum",$("#reqNum").val());
		// 系统申请流水单号
		$("#gridTable").setPostDataItem("reqInnerNum",$("#reqInnerNum").val());
		// 报文处理状态 
		$("#gridTable").setPostDataItem("msgHandleStatus",$("#msgHandleStatus").val());
		// 系统交易流水号
		$("#gridTable").setPostDataItem("transNum",$("#transNum").val());
		
	}
	//===========================报文明细==================================
	function showAlertDialog(alertTitle, alertContent){
		$('#alertDialog').dialog('destroy');
	    $('#alertDialog').show();
	    $('#alertDialog').html(alertContent);
	    
	    $("#alertDialog").dialog({
	        modal: true,
            opacity: 0.9,
            width : 900,
            height : 430,
        title:alertTitle,
	        buttons: {
	        	'${app:i18n("global.jsp.close")}': function() {
	            	$('#alertDialog').dialog('close');
	            }
	        }
	    });
	}
	
	// 单击事件方法
	function selectMsgCode(){
		$("div.warning span").html("");
		$("div.warning").hide();
		 var id = $("#gridTable").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
	     if(id!=null){
		     var rowData = $("#gridTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
		     var msgCode= rowData.reqInnerNum; //获得制定列的值 （auditStatus 为colModel的name）
		     var bankPmtCnlCode = rowData.bankPmtCnlCode;
			 var sUrl='corBankMsgSearchByMsgCode.action?loadPageCache=true&msgCode='+msgCode;/* +'&bankPmtCnlCode='+bankPmtCnlCode */
	     	$.get(sUrl,function(data){
	    	 	data=data.substring(0,data.length-2);
	    	 	showAlertDialog('报文内容', data);
	     	},'html');
	     	return ;
	     }else{
			$("div.warning").html("请选择需要查看报文的数据列！");
			$("div.warning").show();
	    	return false;
	     }
	}
	
	
</script>

<div id="alertDialog"></div>

<s:form id="cnlTransTraceListForm" method="post" action="cnlTransTraceList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTraceListJsp.headTitle')}</h3>
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
								<!-- 渠道订单号 -->
								<th width="10%">${app:i18n('cnlTransTrace.reqNum')}：</th>
								<td width="10%"><input name="reqNum" id="reqNum" class="width_c" /></td>
								
								<!-- 系统申请流水单号 -->
								<th width="10%">${app:i18n('cnlTransTrace.reqInnerNum')}：</th>
								<td width="10%"><input name="reqInnerNum" id="reqInnerNum" class="width_c" /></td>

								<!-- 银行交易流水号 -->
								<th width="10%">${app:i18n('cnlTransTrace.bankTransNum')}：</th>
								<td width="10%"><input name="bankTransNum" id="bankTransNum" class="width_c" /></td>
							<tr/>
							<tr>
								<!-- 时间类型 -->
								<th width="20%"><em>*</em>${app:i18n('cnlTransTrace.timeType')}：</th>
								<td width="20%">
									<select name="timeType" id="timeType" style="width: 100%" >
										<option value="">${app:i18n('cnlTransTrace.pleaseSelect')}</option> 
										<option value="05">${app:i18n('cnlTransTrace.bankReqTime')}</option> 
										<option value="02">${app:i18n('cnlTransTrace.bankAccepteTime')}</option> 
									</select>
								</td>
								
								<!-- 开始时间 -->
								<th width="20%"><em>*</em>${app:i18n('cnlTransTrace.startTime')}：</th>
								<td width="20%">
									<!-- <input class="width_c" readonly="true" name="startTime" 
										id="startTime" value=""  class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d 00:00:00\'}'})" /> -->
									<input style="width: 99%" name="startTime" id="startTime" class="Wdate" value="${cnlTransTrace.startTime}"  readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"/> 
								</td>
								
								<!-- 结束时间 -->
								<th width="20%"><em>*</em>${app:i18n('cnlTransTrace.endTime')}：</th>
								<td width="20%">
									<!-- <input class="width_c" readonly="true" name="endTime"  width="10"
										id="endTime" value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d 23:59:59\'}'})" /> -->
									<input style="width: 99%" name="endTime" id="endTime" class="Wdate" value="${cnlTransTrace.endTime}" readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
							<tr/>
							<tr>
								<!-- 系统交易流水号= 关联关系表中的交易流水号 -->
								<th width="20%">${app:i18n('cnlTransTrace.transNumr')}：</th>
								<td width="20%"><input name="transNum" id="transNum" class="width_c" /></td>
							
								<!-- 接口类型 = 交易类型-->
								<th width="20%">${app:i18n('cnlTransTrace.transTypei')}：</th>
								<td width="20%">
									<select name="transType" id="transType" style="width: 100%">
										<option value="">${app:i18n('cnlTransTrace.pleaseSelect')}</option>
										<s:iterator value="transTypeList" var="transType" >
											<c:if test="${transType.key == '02' || transType.key == '03'}">
												<option value="${transType.key}">${transType.value}</option>
											</c:if>
										</s:iterator>
										<!-- <option value="02">充值</option>
										<option value="03">提现</option> -->
									</select>
								</td>
								
								<!-- 报文处理状态   -->
								<th width="20%">${app:i18n('cnlTransTrace.msgHandleStatus')}：</th>
								<td width="20%">
									<select name="msgHandleStatus" id=msgHandleStatus style="width: 100%">
										<option value="">${app:i18n('cnlTransTrace.pleaseSelect')}</option>
										<s:iterator value="msgHandleStatusList" var="msgHandleStatus">
											<option value="${msgHandleStatus.key}">${msgHandleStatus.value}</option>
										</s:iterator>
									</select>
								</td>
							<tr/>
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
					<a id="selectMsgCode" class="l-btn-plain l-btn m-l4" onclick="selectMsgCode();"><span class="l-btn-right"><span class="l-btn-text icon-view">${app:i18n('global.jsp.selectMsgCode')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</div>

</s:form>
