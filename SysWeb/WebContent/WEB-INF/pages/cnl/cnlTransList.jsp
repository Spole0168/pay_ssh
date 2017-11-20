<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var searchUrl = "cnlTransSearch.action";
		
		$("#search").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			if(validform().form()){
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				startTime = startTime.replace(/-/g,'/');
				endTime = endTime.replace(/-/g,'/');
				startTime = new Date(startTime);
				endTime = new Date(endTime);
				var times = startTime.getTime() - endTime.getTime();
				//var days = Math.abs(parseInt(times/(1000*60*60*24)));
				if(times >= 0) {
					$("div.warning span").html("结束时间必须大于开始时间！");
					$("div.warning").show();
				} else{
					doSearch();
				}
			}
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$("#startTime").val("${startTime}");
			$("#endTime").val("${endTime}");
			$("#timeType").val("1");
			$("div.warning span").html("");
			$("div.warning").hide();
		});
		
		function validform(){
			return $("#cnlTransListForm").validate({
				rules : {
			     'cnlTrans\.timeType' : {
						required : true
					},
					'cnlTrans\.startTime' : {
						required : true
					},
					'cnlTrans\.endTime'   :{
						required : true
					},
					'cnlTrans\.transNum' : {
						stringMaxLength:32
					},
					'cnlTrans\.reqInnerNum' : {
						stringMaxLength:32
					},
					'cnlTrans\.transOrderNum' : {
						stringMaxLength:32
					},
					'cnlTrans\.bankTransNum' : {
						stringMaxLength:32
					}
					
				},
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
		
		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();
		
			$("#gridTable").gridUtil().exportExcel({url: "cnlTransExport.action"});
		});
		
		//$("#create").click(function(){
		//	window.location.href=createUrl;
		//});

		//$("#delete").click( function() {
		//	var gr;
		//	gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
		//	if (gr.length != 1) {
		//		alert("请选择一行修改");
		//		return;
		//	}
		//	//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
		//	$("#cnlTransListForm").attr("action",deleteUrl+"?id="+gr[0]);
		//	$("#cnlTransListForm").submit();
		//});
		
		var transDcRender = $('#transDcRender').attr('value');
		var transTypeRender = $('#transTypeRender').attr('value');
		var transStatusRender = $('#transStatusRender').attr('value');
		var bankHandlePrioriryRender = $('#bankHandlePrioriryRender').attr('value');
		var tradeTerminalTypeRender = $('#tradeTerminalTypeRender').attr('value');
		var isInGlRender = $('#isInGlRender').attr('value');
		var isPrintedRender = $('#isPrintedRender').attr('value');
		var transCurrencyListRender = $('#transCurrencyListRender').attr('value');
		var cnlCnlRender = $('#cnlCnlRender').attr('value');
		var bankPmtCnlTypeRender = $('#bankPmtCnlTypeRender').attr('value');
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			shrinkToFit:false,
			//editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			rowList:[50,100,200],
			rowNum:50,
			colNames:['ID', 
					'${app:i18n('cnlTrans.reqInnerNum')}',
					'${app:i18n('cnlTrans.transNum')}',
					'${app:i18n('cnlTrans.transOrderNum')}',
					'${app:i18n('cnlTrans.bankTransNum')}',
					'${app:i18n('cnlTrans.transCurrency')}',
					'${app:i18n('cnlTrans.transAmount')}',
					'${app:i18n('cnlTrans.transDc')}',
					'${app:i18n('cnlTrans.transType')}',
					'${app:i18n('cnlTrans.transStatus')}',
					'${app:i18n('cnlTrans.transComments')}',
					'${app:i18n('cnlTrans.transTime1')}',
					'${app:i18n('cnlTrans.bankHandlePriority')}',
					'${app:i18n('cnlTrans.bankReqTrnasDate')}',
					'${app:i18n('cnlTrans.bankReqTransTime')}',
					'${app:i18n('cnlTrans.bnakServiceFeeAct')}',					
					'${app:i18n('cnlTrans.bankAccepteTime')}',
					'${app:i18n('cnlTrans.bankReturnTime')}',
					'${app:i18n('cnlTrans.bankDebitName')}',
					'${app:i18n('cnlTrans.bankDebitCustName')}',
					'${app:i18n('cnlTrans.bankDebitAcntCode')}',
					'${app:i18n('cnlTrans.bankCreditName')}',
					'${app:i18n('cnlTrans.bankCreditCustName')}',
					'${app:i18n('cnlTrans.bankCreditAcntCode')}',
					'${app:i18n('cnlTrans.bankPmtCnlType')}',
// 					'${app:i18n('cnlTrans.cnlCnlCode')}',
					'${app:i18n('cnlTrans.cnlCnlName')}',
					'${app:i18n('cnlTrans.termialType')}',
					'${app:i18n('cnlTrans.isinGl')}',
					'${app:i18n('cnlTrans.inglTime')}'
// 					'${app:i18n('cnlTrans.isPrinted')}',
// 					'${app:i18n('cnlTrans.printedTime')}'
					   ],
			colModel:[
						{name : 'id', hidden: true,align:'center'},
						{name : 'reqInnerNum',index : 'reqInnerNum',align:'center'},//系统申请单流水号
						{name : 'transNum',index : 'transNum',align:'center'},//系统交易流水号
						{name : 'transOrderNum',index : 'transOrderNum',align:'center'},//渠道订单号
						//{name : 'reqBatch',index : 'reqBatch',align:'center'},//渠道订单批次号
						{name : 'bankTransNum',index : 'bankTransNum',align:'center'},//银行交易流水号
						{name : 'transCurrency',index : 'transCurrency',align:'center',formatter:'select', editoptions:{value:transCurrencyListRender}},//交易币种
						{name : 'transAmount',index : 'transAmount',align:'center'},//金额
						//{name : 'todo',index : 'todo',align:'center'},//TODO 交易金额汇率
						{name : 'transDc',index : 'transDc',align:'center',formatter:'select', editoptions:{value:transDcRender}},//交易方向
						{name : 'transType',index : 'transType',align:'center',formatter:'select', editoptions:{value:transTypeRender}},//交易类型
						{name : 'transStatus',index : 'transStatus',align:'center',formatter:'select', editoptions:{value:transStatusRender}},//交易状态
						{name : 'transComments',index : 'transComments',align:'center'},//交易摘要
						{name : 'transTime',index : 'transTime',align:'center'},//交易时间
						{name : 'bnakHandlePriority',index : 'bankHandlePriority',align:'center',formatter:'select', editoptions:{value:bankHandlePrioriryRender}},//银行处理优先级
						{name : 'bankReqTrnasDateString',index : 'bankReqTrnasDateString',align:'center'},//要求转账日期
						{name : 'bankReqTransTimeString',index : 'bankReqTransTimeString',align:'center'},//要求转账时间
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct',align:'center'},//支付转账手续费账号
						{name : 'bankAccepteTime',index : 'bankAccepteTime',align:'center'},//银行网关接入时间
						{name : 'bankReturnTime',index : 'bankReturnTime',align:'center'},//到账时间
						{name : 'bankDebitName',index : 'bankDebitName',align:'center'},//收款银行
						{name : 'bankDebitCustName',index : 'bankDebitCustName',align:'center'},//收款户名
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum',align:'center'},//收款卡号
						{name : 'bankCreditName',index : 'bankCreditName',align:'center'},//付款银行
						{name : 'bankCreditCustName',index : 'bankCreditCustName',align:'center'},//付款户名
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum',align:'center'},//付款卡号
						{name : 'bankPmtCnlType',index : 'bankPmtCnlType',align:'center',formatter:'select', editoptions:{value:bankPmtCnlTypeRender}},//支付通道
// 						{name : 'cnlCnlCode',index : 'cnlCnlCode',align:'center'},//渠道来源标识
						{name : 'cnlCnlCode',index : 'cnlCnlCode',align:'center',formatter:'select', editoptions:{value:cnlCnlRender}},//渠道名称
						{name : 'termialType',index : 'termialType', align:'center',formatter:'select', editoptions:{value:tradeTerminalTypeRender}},// 交易终端类型
						{name : 'isinGl',index : 'isinGl',align:'center',formatter:'select', editoptions:{value:isInGlRender}},//入账标志
						{name : 'inglTime',index : 'inglTime',align:'center'}//入账时间
// 						{name : 'isPrinted',index : 'isPrinted', align:'center',formatter:'select', editoptions:{value:isPrintedRender}},//打印标志
// 						{name : 'printedTime',index : 'printedTime',align:'center'},// 打印时间
					    //{name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
// 			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTrans.cnlTransListJsp.tableTitle')}",
			exportFileName: "当前交易流水.xls"
			//gridComplete: function() {
			//	var ids = $("#gridTable").jqGrid('getDataIDs'); 
			//	for(var i=0;i < ids.length;i++) {
			//		var all = "";
			//		var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
			//
			//		mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
			//		
			//		var id = ids[i];
			//		var rowData = $("#gridTable").getRowData(id);
			//
			//		all = all + mod;
			//
			//		$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
			//	}
			//}
		});

		$("#gridTable").gridUtil().customizeColumn();
// 		$("#gridToolbar").appendTo($("#t_gridTable"));

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
		//$("#cnlTransListForm").validate();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlTransSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		
		// 设置查询参数
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("transOrderNum", $("#transOrderNum").val());
		//$("#gridTable").setPostDataItem("reqBatch", $("#reqBatch").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("timeType", $("#timeType").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		//$("#gridTable").setPostDataItem("tradeSubType", $("#tradeSubType").val());
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());

	}
</script>
<s:form id="cnlTransListForm" method="post" action="cnlTransList">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTrans.cnlTransListJsp.headTitle')}</h3>
		</div>
		<input id="transDcRender" name="transDcRender" type="hidden" value="${transDcRender}" />
		<input id="transTypeRender" name="transTypeRender" type="hidden" value="${transTypeRender}" />
		<input id="transStatusRender" name="transStatusRender" type="hidden" value="${transStatusRender}" />
		<input id="bankHandlePrioriryRender" name="bankHandlePrioriryRender" type="hidden" value="${bankHandlePrioriryRender}" />
		<input id="tradeTerminalTypeRender" name="tradeTerminalTypeRender" type="hidden" value="${tradeTerminalTypeRender}" />
		<input id="isInGlRender" name="isInGlRender" type="hidden" value="${isInGlRender}" />
		<input id="isPrintedRender" name="isPrintedRender" type="hidden" value="${isPrintedRender}" />
		<input id="transCurrencyListRender" name="transCurrencyListRender" type="hidden" value="${transCurrencyListRender}" />
		<input id="cnlCnlRender" name="cnlCnlRender" type="hidden" value="${cnlCnlRender}" />
		<input id="bankPmtCnlTypeRender" name="bankPmtCnlTypeRender" type="hidden" value="${bankPmtCnlTypeRender}" />
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
<!-- 						<thead> -->
<!-- 						</thead> -->
<!-- 						<tfoot> -->
<!-- 						</tfoot> -->
<!-- 						<tbody> -->
<!-- 							<tr> -->
<!-- 								<td> -->
<!-- 									<table> -->
										<tr>
											<!-- 渠道来源标识 -->
											<th style="width:13%">${app:i18n('cnlTrans.cnlCnlName')}：</th>
											<td style="width:19%">										
												<select name="cnlTrans.cnlCnlCode" id="cnlCnlCode" style="width:162px">
													<s:iterator value="%{cnlCnlList}" id="cnlItem">
												        <option value="<s:property value="#cnlItem.key" />">
												        	<s:property value="#cnlItem.value" />
												        </option>
													</s:iterator>
										         </select>
											</td>
			
											<!-- 系统交易流水号 -->
											<th style="width:15%">${app:i18n('cnlTrans.transNum')}：</th>
											<td style="width:19%"><input name="cnlTrans.transNum" id="transNum" style="width:160px" maxlength="32"/></td>
											
											<!-- 系统申请单流水号 -->
											<th style="width:14%">${app:i18n('cnlTrans.reqInnerNum')}：</th>
											<td style="width:18%"><input name="cnlTrans.reqInnerNum" id="reqInnerNum" style="width:160px" maxlength="32"/></td>											
										</tr>
										<tr>
											<!-- 渠道订单号 -->
											<th>${app:i18n('cnlTrans.transOrderNum')}：</th>
											<td><input name="cnlTrans.transOrderNum" id="transOrderNum" style="width:160px" maxlength="32"/></td>
											
											<!-- 银行交易流水号 -->
											<th>${app:i18n('cnlTrans.bankTransNum')}：</th>
											<td><input name="cnlTrans.bankTransNum" id="bankTransNum" style="width:160px" maxlength="32"/></td>
											<th></th>
											<td></td>
										</tr>
										<tr>			
											<!-- 时间类型 -->						
											<th><em>*</em>${app:i18n('cnlTrans.timeType')}：</th>
											<td>
												<select name="cnlTrans.timeType" id="timeType" style="width:162px">
<!-- 													<option value="">请选择</option> -->
													<option value="1" selected>${app:i18n('cnlTrans.timeTypeText1')}</option>
													<option value="2">${app:i18n('cnlTrans.timeTypeText2')}</option>
													<option value="3">${app:i18n('cnlTrans.timeTypeText3')}</option>
												</select>
											</td>	
											
											<!-- 开始时间 -->
											<th><em>*</em>${app:i18n('cnlTrans.startTime')}：</th>
											<td><input style="width:160px" name="cnlTrans.startTime" id="startTime" value="${startTime}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'})"/></td>
											
											<!-- 结束时间 -->
											<th><em>*</em>${app:i18n('cnlTrans.endTime')}：</th>											
											<td><input style="width:160px" name="cnlTrans.endTime" id="endTime" value="${endTime}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d 00:00:00',maxDate:'%y-%M-%d 23:59:59'})"/></td>
										</tr>
										<tr>
											<!-- 交易类型 -->
											<th>${app:i18n('cnlTrans.transType')}：</th>
											<td>
												<select name="cnlTrans.transType" id="transType" style="width:162px">
													<option value="" selected>${app:i18n('cnlTrans.transTypeText1')}</option>
													<s:iterator value="%{transTypeList}" id="transTypeItem">
												        <option value="<s:property value="#transTypeItem.key" />">
												        	<s:property value="#transTypeItem.value" />
												        </option>
													</s:iterator>
										         </select>
											</td>
											
											<!-- 交易方向 -->
											<th>${app:i18n('cnlTrans.transDc')}：</th>
											<td colspan="3">
												<select name="cnlTrans.transDc" id="transDc" style="width:162px">
													<option value="" selected>${app:i18n('cnlTrans.transDcText1')}</option>
													<s:iterator value="%{transDcList}" id="transDcItem">
												        <option value="<s:property value="#transDcItem.key" />">
												        	<s:property value="#transDcItem.value" />
												        </option>
													</s:iterator>
										         </select>
											</td>
											
											<!-- 交易状态 -->
<%-- 											<th>${app:i18n('cnlTrans.trnasStatus')}：</th> --%>
<!-- 											<td> -->
<!-- 												<select name="transStatus" id="transStatus" style="width:180px"> -->
<%-- 													<option value="" selected>${app:i18n('cnlTrans.trnasStatusText1')}</option> --%>
<%-- 													<s:iterator value="%{transStatusList}" id="transStatusItem"> --%>
<%-- 												        <option value="<s:property value="#transStatusItem.key" />"> --%>
<%-- 												        	<s:property value="#transStatusItem.value" /> --%>
<!-- 												        </option> -->
<%-- 													</s:iterator> --%>
<!-- 										         </select> -->
<!-- 											</td>						 -->
										</tr>
<!-- 									</table> -->
								
<!-- 								</td> -->

<!-- 							</tr> -->
<!-- 						</tbody> -->
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
				<!-- <div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
				</div> -->
			</div>
		</div>
	</div>
</div>
</s:form>
