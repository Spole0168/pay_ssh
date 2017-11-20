<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {
		
		var searchUrl = "cnlTrans3mSearch.action";
		
		$("#search").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			if(validform().form()){
				var beginTime = $("#beginTime").val();
				var endTime = $("#endTime").val();
				var lastDay = $("#lastDay").val();
				beginTime = beginTime.replace(/-/g,'/');
				endTime = endTime.replace(/-/g,'/');
				lastDay = lastDay.replace(/-/g,'/');
				beginTime = new Date(beginTime);
				endTime = new Date(endTime);
				lastDay = new Date(lastDay);
				var times = beginTime.getTime() - endTime.getTime();
				var days = Math.abs(parseInt(times/(1000*60*60*24)));
				var diffBeginAndlast = Math.abs(parseInt( (lastDay.getTime() - beginTime.getTime())/(1000*60*60*24)));
				var diffEndAndlast = Math.abs(parseInt( (lastDay.getTime() - endTime.getTime())/(1000*60*60*24)));
				if(times >= 0) {
					$("div.warning span").html("结束时间必须大于开始时间！");
					$("div.warning").show();
				} else if( diffBeginAndlast > 90) {
					$("div.warning span").html(" 只能查看90日内的历史交易流水！");
					$("div.warning").show();
				} else if(diffEndAndlast > 90) {
					$("div.warning span").html("只能查看前90日内的历史交易流水！ ");
					$("div.warning").show();
				} else if(days > 30) {
					$("div.warning span").html("开始时间和结束时间的时间间隔不能超过一个月！ ");
					$("div.warning").show();
				} else{
					doSearch();
				}
			}
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$("#beginTime").val("${beginTime}");
			$("#endTime").val("${endTime}");
			$("#dateType").val("1");
			$("div.warning span").html("");
			$("div.warning").hide();
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();
		
			$("#gridTable").gridUtil().exportExcel({url: "cnlTrans3mExport.action"});
		});
		
		function validform(){
			return $("#cnlTrans3mListForm").validate({
				rules : {
			     'cnlTrans3m\.dateType' : {
						required : true
					},
					'cnlTrans3m\.beginTime' : {
						required : true
					},
					'cnlTrans3m\.endTime'   :{
						required : true
					},
					'cnlTrans3m\.transNum' : {
						stringMaxLength:32
					},
					'cnlTrans3m\.reqInnerNum' : {
						stringMaxLength:32
					},
					'cnlTrans3m\.transOrderNum' : {
						stringMaxLength:32
					},
					'cnlTrans3m\.bankTransNum' : {
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
		
		var transDcRender = $('#transDcRender').attr('value');
		var transTypeRender = $('#transTypeRender').attr('value');
		var transStatusRender = $('#transStatusRender').attr('value');
		var bankHandlePrioriryRender = $('#bankHandlePrioriryRender').attr('value');
		var tradeTerminalTypeRender = $('#tradeTerminalTypeRender').attr('value');
		var isInGlRender = $('#isInGlRender').attr('value');
		var isPrintedRender = $('#isPrintedRender').attr('value');
		var transCurrencyListRender = $('#transCurrencyListRender').attr('value');
		var cnlListRender = $('#cnlListRender').attr('value');
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
			          '${app:i18n('cnlTrans3m.transNum')}',
						'${app:i18n('cnlTrans3m.transOrderNum')}',
						'${app:i18n('cnlTrans3m.reqInnerNum')}',
						'${app:i18n('cnlTrans3m.bankTransNum')}',
						'${app:i18n('cnlTrans3m.transCurrency')}',
						'${app:i18n('cnlTrans3m.transAmount')}',
						'${app:i18n('cnlTrans3m.transType')}',
						'${app:i18n('cnlTrans3m.transDc')}',
						'${app:i18n('cnlTrans3m.transStatus')}',
						'${app:i18n('cnlTrans3m.transComments')}',
						'${app:i18n('cnlTrans3m.transTime')}',
						'${app:i18n('cnlTrans3m.bankAccepteTime1')}',
						'${app:i18n('cnlTrans3m.bankReturnTime1')}',
						'${app:i18n('cnlTrans3m.bankDebitName1')}',
						'${app:i18n('cnlTrans3m.bankDebitCustName1')}',
						'${app:i18n('cnlTrans3m.bankDebitAcntCode1')}',
						'${app:i18n('cnlTrans3m.bankCreditName1')}',
						'${app:i18n('cnlTrans3m.bankCreditCustName1')}',
						'${app:i18n('cnlTrans3m.bankCreditAcntCode1')}',
						'${app:i18n('cnlTrans3m.bankPmtCnlType1')}',
// 				        '${app:i18n('cnlTrans3m.cnlCnlCode1')}',
				        '${app:i18n('cnlTrans3m.cnlCnlName')}',
						'${app:i18n('cnlTrans3m.termialType')}',
						'${app:i18n('cnlTrans3m.isinGl1')}',
// 						'${app:i18n('cnlTrans3m.isPrinted')}',
						'${app:i18n('cnlTrans3m.bnakHandlePriority')}',
						'${app:i18n('cnlTrans3m.bankReqTrnasDate1')}',
						'${app:i18n('cnlTrans3m.bankReqTransTime1')}',
						'${app:i18n('cnlTrans3m.bnakServiceFeeAct1')}',
						],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'transNum',index : 'transNum'},
						{name : 'transOrderNum',index : 'transOrderNum'},
						{name : 'reqInnerNum',index : 'reqInnerNum',align:'center'},//系统申请单流水号
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'transCurrency',index : 'transCurrency',formatter:'select', editoptions:{value:transCurrencyListRender}},
						{name : 'transAmount',index : 'transAmount'},
						{name : 'transType',index : 'transType',formatter:'select', editoptions:{value:transTypeRender}},
						{name : 'transDc',index : 'transDc',formatter:'select', editoptions:{value:transDcRender}},
						{name : 'transStatus',index : 'transStatus',formatter:'select', editoptions:{value:transStatusRender}},
						{name : 'transComments',index : 'transComments'},
						{name : 'transTime',index : 'transTime'},
						{name : 'bankAccepteTime',index : 'bankAccepteTime'},
						{name : 'bankReturnTime',index : 'bankReturnTime'},
						{name : 'bankDebitName',index : 'bankDebitName'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum'},
						{name : 'bankCreditName',index : 'bankCreditName'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName'},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum'},
						{name : 'bankPmtCnlType',index : 'bankPmtCnlType',formatter:'select', editoptions:{value:bankPmtCnlTypeRender}},
// 						{name : 'cnlCnlCode',index : 'cnlCnlCode'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode',formatter:'select', editoptions:{value:cnlListRender}},
						{name : 'termialType',index : 'termialType',formatter:'select', editoptions:{value:tradeTerminalTypeRender}},
						{name : 'isinGl',index : 'isinGl',formatter:'select', editoptions:{value:isInGlRender}},
// 						{name : 'isPrinted',index : 'isPrinted',formatter:'select', editoptions:{value:isPrintedRender}},
						{name : 'bnakHandlePriority',index : 'bnakHandlePriority',formatter:'select', editoptions:{value:bankHandlePrioriryRender}},
						{name : 'strBankReqTrnasDate',index : 'strBankReqTrnasDate'},
						{name : 'strBankReqTransTime',index : 'strBankReqTransTime'},
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct'},
					   
			],
			pager: "#gridPager",
// 			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTrans3m.cnlTrans3mListJsp.tableTitle')}",
/* 			gridComplete: function() {
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
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlTrans3mSearch.action"}).trigger("reloadGrid");
	}
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("transOrderNum", $("#transOrderNum").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("dateType", $("#dateType").val());
		$("#gridTable").setPostDataItem("beginTime", $("#beginTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());

	}
</script>

<s:form id="cnlTrans3mListForm" method="post" action="cnlTrans3mList">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTrans3m.cnlTrans3mListJsp.headTitle')}</h3>
		</div>
		<input id="transDcRender" name="transDcRender" type="hidden" value="${transDcRender}" />
		<input id="transTypeRender" name="transTypeRender" type="hidden" value="${transTypeRender}" />
		<input id="transStatusRender" name="transStatusRender" type="hidden" value="${transStatusRender}" />
		<input id="bankHandlePrioriryRender" name="bankHandlePrioriryRender" type="hidden" value="${bankHandlePrioriryRender}" />
		<input id="tradeTerminalTypeRender" name="tradeTerminalTypeRender" type="hidden" value="${tradeTerminalTypeRender}" />
		<input id="isInGlRender" name="isInGlRender" type="hidden" value="${isInGlRender}" />
		<input id="isPrintedRender" name="isPrintedRender" type="hidden" value="${isPrintedRender}" />
		<input id="lastDay" name="lastDay" type="hidden" value="${endTime}" />
		<input id="transCurrencyListRender" name="transCurrencyListRender" type="hidden" value="${transCurrencyListRender}" />
		<input id="cnlListRender" name="cnlListRender" type="hidden" value="${cnlListRender}" />
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
							<tr>
								<!--渠道来源标识  -->
								<th style="width:13%">${app:i18n('cnlTrans3m.cnlCnlName')}：</th>
								<td style="width:19%">
									<select name="cnlTrans3m.cnlCnlCode" id="cnlCnlCode" style="width:162px">
										<s:iterator value="%{cnlList}" id="cnlItem">
									        <option value="<s:property value="#cnlItem.key" />">
									        	<s:property value="#cnlItem.value" />
									        </option>
										</s:iterator>
									</select>
								</td>
								
								<!--系统交易流水号  -->
								<th style="width:15%">${app:i18n('cnlTrans3m.transNum')}：</th>
								<td style="width:19%"><input name="cnlTrans3m.transNum" id="transNum"  style="width:160px" maxlength="32"/></td>
								
								<!--系统申请单流水号  -->
								<th style="width:14%">${app:i18n('cnlTrans3m.reqInnerNum')}：</th>
								<td style="width:18%"><input name="cnlTrans3m.reqInnerNum" id="reqInnerNum"  style="width:160px" maxlength="32"/></td>
							<tr/>
							<tr>
								<!--渠道订单号-->
								<th>${app:i18n('cnlTrans3m.transOrderNum')}：</th>
								<td><input name="cnlTrans3m.transOrderNum" id="transOrderNum"  style="width:160px" maxlength="32"/></td>
								
								<!--银行流水号  -->
								<th>${app:i18n('cnlTrans3m.bankTransNum')}：</th>
								<td><input name="cnlTrans3m.bankTransNum" id="bankTransNum"  style="width:160px" maxlength="32"/></td>
								
								<th></th>
								<td></td>
							<tr/>
							<tr>	
								<!--时间类型  -->
								<th><em>*</em>${app:i18n('cnlTrans3m.dateType')}：</th>
								<td>
									<select id="dateType" name="cnlTrans3m.dateType" style="width:162px">
										<option value="1" selected>交易时间</option>
										<option value="2">银行网关进入时间</option>
										<option value="3">到账时间</option>
									</select>
								</td>
								
								<!--开始时间  -->
								<th><em>*</em>${app:i18n('cnlTrans3m.beginTime')}：</th>
								<td><input id="beginTime" style="width:160px" value="${beginTime}" name="cnlTrans3m.beginTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-#{%d-1} 23:59:59'})"/></td>
								
								<!--结束时间  -->
								<th><em>*</em>${app:i18n('cnlTrans3m.endTime')}：</th>
								<td><input id="endTime" style="width:160px" value="${endTime}" name="cnlTrans3m.endTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-#{%d-1} 23:59:59'})"/></td>
							<tr/>
							<tr>
								<!--交易类型  -->
								<th>${app:i18n('cnlTrans3m.transType')}：</th>
								<td>
									<select name="cnlTrans3m.transType" id="transType" style="width:162px">
										<option value="">全部</option>
										<s:iterator value="%{transTypeList}" id="transTypeItem">
									        <option value="<s:property value="#transTypeItem.key" />">
									        	<s:property value="#transTypeItem.value" />
									        </option>
										</s:iterator>
							         </select>
								</td>
								
								<!--交易方向  -->
								<th>${app:i18n('cnlTrans3m.transDc')}：</th>
								<td colspan="3">
								<select name="cnlTrans3m.transDc" id="transDc" style="width:162px">
									<option value="">全部</option>
									<s:iterator value="%{transDcList}" id="transDcItem">
								        <option value="<s:property value="#transDcItem.key" />">
								        	<s:property value="#transDcItem.value" />
								        </option>
									</s:iterator>
						         </select>
								</td>
								
<%-- 								<th>${app:i18n('cnlTrans.trnasStatus')}：</th> --%>
<!-- 								<td> -->
<!-- 									<select name="cnlTrans3m.transStatus" id="transStatus" style="width:180px"> -->
<!-- 										<option value="">请选择</option> -->
<%-- 										<s:iterator value="%{transStatusList}" id="transStatusItem"> --%>
<%-- 									        <option value="<s:property value="#transStatusItem.key" />"> --%>
<%-- 									        	<s:property value="#transStatusItem.value" /> --%>
<!-- 									        </option> -->
<%-- 										</s:iterator> --%>
<!-- 							         </select> -->
<!-- 								</td>				 -->
							</tr>
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
			</div>
		</div>
	</div>
</div>
</s:form>
