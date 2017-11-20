<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "corBankAcntTransModify.action";
		var deleteUrl = "corBankAcntTransDelete.action";
		var createUrl = "corBankAcntTransCreate.action";
		var searchUrl = "corBankAcntTransSearch.action";
		var exportUrl = "corBankAcntTransExport.action";

		$("#search").click(function(){
			   var ok = true;
				
				$("div.warning span").html("");
				$("div.warning").hide();
				if(validform().form()){
				}else{
					ok = false;
				}
			// 校验银行卡号合法信息
			if($("#bankCardNum").val() != null && $("#bankCardNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#bankCardNum").val())){
				}else{
					$("div.warning span").html("请输入正确的银行卡号信息  ！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验开户名合法信息
			if($("#custName").val() != null && $("#custName").val() != ""){
				var zz = /^[\u4e00-\u9fa5]*$/;
				if(zz.test($("#custName").val())){
				}else{
					$("div.warning span").html("请输入正确的开户名信息 只能输入汉字  ！");
					$("div.warning").show();
					ok = false;
				}
			}
			//校验最大金额 合法信息
			if($("#maxAmount").val() != null && $("#maxAmount").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#maxAmount").val())){
				}else{
					$("div.warning span").html("请输入正确的最大金额 信息   ！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验最小 金额合法信息
			if($("#minAmount").val() != null && $("#minAmount").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#minAmount").val())){
				}else{
					$("div.warning span").html("请输入正确的最小金额信息   ！");
					$("div.warning").show();
					ok = false;
				}
			}
			
		 	// 开始时间与结束时间间跨度小于等于一个月的校验
			var createtime = $("#startTime").val();
			var endtime = $("#endTime").val();
		if(createtime ==null || createtime=="" ||endtime==null||endtime==""){
			
			
		}else{
			var	start=new Date(createtime.replace("-", "/").replace("-", "/")); 
		    var	end=new Date(endtime.replace("-", "/").replace("-", "/"));
		 
			var endStart = end.getTime() - start.getTime();
			var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
			var month =leaveTime / (30 * 24 * 3600 * 1000);
			
			if(start < end && month < 1){
				$("div.warning").hide();
				
			}else{
				$("div.warning").html("开始时间与结束时间间跨度小于等于一个月!");
				$("div.warning").show();
				ok = false;
			}
		}
			if(ok){
				doSearch();
			}
		});
	 	// 页面表达校验
		function validform(){
			return $("#corBankAcntTransListForm").validate({
				rules : {
			     	'direction'		:{
						required : true
					},
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
			$("#corBankAcntTransListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corBankAcntTransListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			shrinkToFit:false,
			colNames:['ID',
			          '${app:i18n('corReservedFundAcnt.bankName')}',
			          '${app:i18n('corReservedFundAcnt.bankAcntCode')}',
			          '${app:i18n('corReservedFundAcnt.bankCardNum')}',
			          '${app:i18n('corReservedFundAcnt.custName')}',
			          
					'${app:i18n('corBankAcntTrans.transNum')}',
					'${app:i18n('corBankAcntTrans.bankCreditAcntCode')}',
					'${app:i18n('corBankAcntTrans.bankCreditName')}',
					'${app:i18n('corBankAcntTrans.bankCreditCardNum')}',
					'${app:i18n('corBankAcntTrans.bankDebitAcntCode')}',
					'${app:i18n('corBankAcntTrans.bankDebitName')}',
					'${app:i18n('corBankAcntTrans.bankDebitCardNum')}',
					'${app:i18n('corBankAcntTrans.transType')}',
					'${app:i18n('corBankAcntTrans.direction')}',
					'${app:i18n('corBankAcntTrans.amount')}',
					'${app:i18n('corBankAcntTrans.transCurrency')}',
					'${app:i18n('corBankAcntTrans.transTime')}',
					'${app:i18n('corBankAcntTrans.transDate')}',
					'${app:i18n('corBankAcntTrans.transComments')}',
					'${app:i18n('corBankAcntTrans.transStatus')}',
					'${app:i18n('corBankAcntTrans.bankTransNum')}',
					'${app:i18n('corBankAcntTrans.bankMsgCode')}',
					'${app:i18n('corBankAcntTrans.returnCode')}',
					'${app:i18n('corBankAcntTrans.errMsg')}',
					'${app:i18n('corBankAcntTrans.handleTime')}',
					'${app:i18n('corBankAcntTrans.operator')}',
					'${app:i18n('corBankAcntTrans.handleMsg')}',
					'${app:i18n('corBankAcntTrans.handleStatus')}',
					'${app:i18n('corBankAcntTrans.isValid')}',
					'${app:i18n('corBankAcntTrans.createTime')}',
					'${app:i18n('corBankAcntTrans.updateTime')}',
					'${app:i18n('corBankAcntTrans.creator')}',
					'${app:i18n('corBankAcntTrans.updator')}',
					'${app:i18n('corBankAcntTrans.bankBalanceAfterTrans')}',
					'${app:i18n('corBankAcntTrans.bankFrozenAmoumt')}',
					'${app:i18n('corBankAcntTrans.bankAvaliableAmount')}',
					'${app:i18n('corBankAcntTrans.transRate')}',
					'${app:i18n('corBankAcntTrans.bankNum')}',
					'${app:i18n('corBankAcntTrans.otherBankName')}',
					'${app:i18n('corBankAcntTrans.otherBankNum')}',
					'${app:i18n('corBankAcntTrans.otherBankCarNum')}',
					'${app:i18n('corBankAcntTrans.otherCustName')}',
					'${app:i18n('corBankAcntTrans.documentStaff')}',
					'${app:i18n('corBankAcntTrans.documentReviewer')}',
					'${app:i18n('corBankAcntTrans.certType')}',
					'${app:i18n('corBankAcntTrans.certNum')}',
					'${app:i18n('corBankAcntTrans.interestsStartDate')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'bankName',index : 'bankName'},
						{name : 'bankAcntCode',index : 'bankAcntCode'},
						{name : 'bankCardNum',index : 'bankCardNum'},
						{name : 'custName',index : 'custName'},
						{name : 'transNum',index : 'transNum', hidden: true},
						{name : 'bankCreditAcntCode',index : 'bankCreditAcntCode', hidden: true},
						{name : 'bankCreditName',index : 'bankCreditName', hidden: true},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum', hidden: true},
						{name : 'bankDebitAcntCode',index : 'bankDebitAcntCode', hidden: true},
						{name : 'bankDebitName',index : 'bankDebitName', hidden: true},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum', hidden: true},
						{name : 'transType',index : 'transType'},
						{name : 'direction',index : 'direction'},
						{name : 'amount',index : 'amount'},
						{name : 'transCurrency',index : 'transCurrency'},
						{name : 'transTime',index : 'transTime'},
						{name : 'transDate',index : 'transDate'},
						{name : 'transComments',index : 'transComments'},
						
						{name : 'transStatus',index : 'transStatus', hidden: true},
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'bankMsgCode',index : 'bankMsgCode', hidden: true},
						{name : 'returnCode',index : 'returnCode', hidden: true},
						{name : 'errMsg',index : 'errMsg', hidden: true},
						{name : 'handleTime',index : 'handleTime', hidden: true},
						{name : 'operator',index : 'operator', hidden: true},
						{name : 'handleMsg',index : 'handleMsg', hidden: true},
						{name : 'handleStatus',index : 'handleStatus', hidden: true},
						{name : 'isValid',index : 'isValid', hidden: true},
						{name : 'createTime',index : 'createTime', hidden: true},
						{name : 'updateTime',index : 'updateTime', hidden: true},
						{name : 'creator',index : 'creator', hidden: true},
						{name : 'updator',index : 'updator', hidden: true},
						{name : 'bankBalanceAfterTrans',index : 'bankBalanceAfterTrans'},
						{name : 'bankFrozenAmoumt',index : 'bankFrozenAmoumt'},
						{name : 'bankAvaliableAmount',index : 'bankAvaliableAmount'},
						{name : 'transRate',index : 'transRate'},
						{name : 'bankNum',index : 'bankNum', hidden: true},
						{name : 'otherBankName',index : 'otherBankName'},
						{name : 'otherBankNum',index : 'otherBankNum'},
						{name : 'otherBankCarNum',index : 'otherBankCarNum'},
						{name : 'otherCustName',index : 'otherCustName'},
						{name : 'documentStaff',index : 'documentStaff'},
						{name : 'documentReviewer',index : 'documentReviewer'},
						{name : 'certType',index : 'certType'},
						{name : 'certNum',index : 'certNum'},
						{name : 'interestsStartDate',index : 'interestsStartDate'},
					    {name:  'operation',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corBankAcntTrans.corBankAcntTransListJsp.tableTitle')}",
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
		$("#gridTable").jqGrid("setGridParam",{url:"corBankAcntTransSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		$("#gridTable").setPostDataItem("bankCreditAcntCode", $("#bankCreditAcntCode").val());
		$("#gridTable").setPostDataItem("bankCreditName", $("#bankCreditName").val());
		$("#gridTable").setPostDataItem("bankCreditCardNum", $("#bankCreditCardNum").val());
		$("#gridTable").setPostDataItem("bankDebitAcntCode", $("#bankDebitAcntCode").val());
		$("#gridTable").setPostDataItem("bankDebitName", $("#bankDebitName").val());
		$("#gridTable").setPostDataItem("bankDebitCardNum", $("#bankDebitCardNum").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("direction", $("#direction").val());
		$("#gridTable").setPostDataItem("amount", $("#amount").val());
		$("#gridTable").setPostDataItem("transCurrency", $("#transCurrency").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transComments", $("#transComments").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("bankMsgCode", $("#bankMsgCode").val());
		$("#gridTable").setPostDataItem("returnCode", $("#returnCode").val());
		$("#gridTable").setPostDataItem("errMsg", $("#errMsg").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("operator", $("#operator").val());
		$("#gridTable").setPostDataItem("handleMsg", $("#handleMsg").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("bankBalanceAfterTrans", $("#bankBalanceAfterTrans").val());
		$("#gridTable").setPostDataItem("bankFrozenAmoumt", $("#bankFrozenAmoumt").val());
		$("#gridTable").setPostDataItem("bankAvaliableAmount", $("#bankAvaliableAmount").val());
		$("#gridTable").setPostDataItem("transRate", $("#transRate").val());
		$("#gridTable").setPostDataItem("documentStaff", $("#documentStaff").val());
		$("#gridTable").setPostDataItem("documentReviewer", $("#documentReviewer").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("otherBankName", $("#otherBankName").val());
		$("#gridTable").setPostDataItem("otherBankNum", $("#otherBankNum").val());
		$("#gridTable").setPostDataItem("otherBankCarNum", $("#otherBankCarNum").val());
		$("#gridTable").setPostDataItem("otherCustName", $("#otherCustName").val());
		$("#gridTable").setPostDataItem("interestsStartDate", $("#interestsStartDate").val());
		$("#gridTable").setPostDataItem("bankNum", $("#bankNum").val());
		$("#gridTable").setPostDataItem("bankName", $("#bankName").val());
		$("#gridTable").setPostDataItem("bankCardNum", $("#bankCardNum").val());
		$("#gridTable").setPostDataItem("custName", $("#custName").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("maxAmount", $("#maxAmount").val());
		$("#gridTable").setPostDataItem("minAmount", $("#minAmount").val());
	}
</script>

<s:form id="corBankAcntTransListForm" method="post"
	action="corBankAcntTransList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corBankAcntTrans.corBankAcntTransListJsp.headTitle')}</h3>
			</div>
			<div class="block_container">
				<div class="warning" style="display: none;">
					<span></span>
				</div>
				<div class="fieldset_border fieldset_bg m-b" id="queryField">
					<div class="legend_title">
						<a href="/cor/corBankAcntTrans/search" class="container_show">${app:i18n('global.jsp.search')}</a>
					</div>
					<div class="fieldset_container" id="test1">
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
								<tr>
									<th width="10%">${app:i18n('corReservedFundAcnt.bankName')}：</th>
									<td width="30%"><select name="bankName" id="bankName"
										class="width_c">
											<option value="">请选择</option>
											<s:iterator value="bankNameList" var="bankNamelist">
												<option value="${bankNamelist.key}">${bankNamelist.value}</option>
											</s:iterator>
									</select></td>

									<th width="10%">${app:i18n('corReservedFundAcnt.bankCardNum')}：</th>
									<td width="30%"><input name="bankCardNum" id="bankCardNum"
										class="width_c" /></td>

									<th width="10%">${app:i18n('corReservedFundAcnt.custName')}：</th>
									<td width="30%"><input name="custName" id="custName"
										class="width_c" /></td>
								</tr>
								<tr>
									<!-- 开始时间 -->
									<th width="10%">${app:i18n('corBankAcntTrans.startTime')}：</th>
									<td width="15%"><input class="width_c" id="startTime"
										value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d 00:00:00\'}'})" />
									</td>

									<!-- 结束时间 -->
									<th width="10%">${app:i18n('corBankAcntTrans.endTime')}：</th>
									<td width="15%"><input class="width_c" id="endTime"
										value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d 23:59:59\'}'})" />
									</td>
									<th width="10%"><em>*</em>${app:i18n('corBankAcntTrans.direction')}：</th>
									<td width="30%"><select name="direction" id="direction"
										class="width_c">
											<option value="">请选择</option>
											<s:iterator value="directionList" var="directionlist">
												<option value="${directionlist.key}">${directionlist.value}</option>
											</s:iterator>
									</select></td>
								</tr>
								<tr>
									<!-- 最大金额 -->
									<th width="10%">${app:i18n('corBankAcntTrans.maxAmount')}：</th>
									<td width="30%"><input name="maxAmount" id="maxAmount"
										class="width_c"/></td>

									<!-- 最小金额 -->
									<th width="10%">${app:i18n('corBankAcntTrans.minAmount')}：</th>
									<td width="30%"><input name="minAmount" id="minAmount"
										class="width_c" /></td>

									<th width="10%">${app:i18n('corBankAcntTrans.transCurrency')}：</th>
									<td width="30%"><select name="transCurrency"
										id="transCurrency" class="width_c">
											<option value="">请选择</option>
											<s:iterator value="transCurrencyList" var="transCurrencylist">
												<option value="${transCurrencylist.key}">${transCurrencylist.value}</option>
											</s:iterator>
									</select></td>
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
							<a name="export" id="export" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar">
						<%-- <a id="create" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
