<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
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
	    var s3 = "9999999999999.99";
	    var s4 = "0.00";
	    $("#startTime").val(s1);
	    $("#endTime").val(s2);
	    $("#maxAmount").val(s3);
	    $("#minAmount").val(s4);
	}
	$
</script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "corBankAcntTransDtlModify.action";
		var deleteUrl = "corBankAcntTransDtlDelete.action";
		var createUrl = "corBankAcntTransDtlCreate.action";
		var searchUrl = "corBankAcntTransDtlSearch.action";
		var exportUrl = "corBankAcntTransDtlExport.action";
		
		$("#search").click(function(){
			 var ok = true;
				$("div.warning span").html("");
				$("div.warning").hide();
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
			//交易方向必填校验 
			if($("#direction").val()==""||$("#direction").val()==null){
				$("div.warning span").html("请选择交易方向！");
				$("div.warning").show();
				ok=false;
			}
			
			//校验最大金额 合法信息
			if($("#maxAmount").val() != null && $("#maxAmount").val() != ""){
				var zz =/^\d*\.{0,1}\d{0,}$/;
				if(zz.test($("#maxAmount").val())){
					
				}else{
					$("div.warning span").html("请输入正确的最大金额 信息   ！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验最小 金额合法信息
			if($("#minAmount").val() != null && $("#minAmount").val() != ""){
				var zz = /^\d*\.{0,1}\d{0,}$/;
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
				
			}else{
				$("div.warning").html("开始时间与结束时间间跨度小于等于30天 !");
				$("div.warning").show();
				ok = false;
			}
		}
			if(ok){
				doSearch();
			}
		});
	 	// 页面表达校验
		
		$("#reset").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			// 设置查询参数为空
			$("#queryField :input").val("");
			getTodayTime();
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
			$("#corBankAcntTransDtlListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corBankAcntTransDtlListForm").submit();
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
			          '${app:i18n('corBankAcntTransDtl.bankName')}',
			          '${app:i18n('corBankAcntTransDtl.bankNum')}',
			          '${app:i18n('corBankAcntTransDtl.bankCardNum')}',
			          '${app:i18n('corBankAcntTransDtl.custName')}',
			          '${app:i18n('corBankAcntTransDtl.transDate')}',
			          '${app:i18n('corBankAcntTransDtl.transTime')}',
			          '${app:i18n('corBankAcntTransDtl.direction')}',
			          '${app:i18n('corBankAcntTransDtl.transType')}',
			          '${app:i18n('corBankAcntTransDtl.bankTransNum')}',
			          '${app:i18n('corBankAcntTransDtl.transCurrency')}',
			          '${app:i18n('corBankAcntTransDtl.amount')}',
			          '${app:i18n('corBankAcntTransDtl.bankBalanceAfterTrans')}',
			          '${app:i18n('corBankAcntTransDtl.bankAvaliableAmount')}',
			          '${app:i18n('corBankAcntTransDtl.bankFrozenAmoumt')}',
			          '${app:i18n('corBankAcntTransDtl.transComments')}',
			          '${app:i18n('corBankAcntTransDtl.interestsStartDate')}',
			          '${app:i18n('corBankAcntTransDtl.otherBankCarNum')}',
			          '${app:i18n('corBankAcntTransDtl.otherCustName')}',
			          '${app:i18n('corBankAcntTransDtl.otherBankNum')}',
			          '${app:i18n('corBankAcntTransDtl.otherBankName')}',
			          '${app:i18n('corBankAcntTransDtl.transRate')}',
			          '${app:i18n('corBankAcntTransDtl.certType')}',
			          '${app:i18n('corBankAcntTransDtl.certNum')}',
			          '${app:i18n('corBankAcntTransDtl.documentStaff')}',
			          '${app:i18n('corBankAcntTransDtl.documentReviewer')}',
			          
					
			        '${app:i18n('corBankAcntTransDtl.bankCreditAcntCode')}',
					'${app:i18n('corBankAcntTransDtl.bankCreditName')}',
					'${app:i18n('corBankAcntTransDtl.bankCreditCardNum')}',
					'${app:i18n('corBankAcntTransDtl.bankDebitAcntCode')}',
					'${app:i18n('corBankAcntTransDtl.bankDebitName')}',
					'${app:i18n('corBankAcntTransDtl.bankDebitCardNum')}',
					'${app:i18n('corBankAcntTransDtl.transStatus')}',
					'${app:i18n('corBankAcntTransDtl.bankMsgCode')}',
					'${app:i18n('corBankAcntTransDtl.createTime')}',
					'${app:i18n('corBankAcntTransDtl.updateTime')}',
					'${app:i18n('corBankAcntTransDtl.errCode')}',
					'${app:i18n('corBankAcntTransDtl.errDesc')}',
					'${app:i18n('corBankAcntTransDtl.handleTime')}',
					'${app:i18n('corBankAcntTransDtl.operator')}',
					'${app:i18n('corBankAcntTransDtl.handleDesc')}',
					'${app:i18n('corBankAcntTransDtl.handleStatus')}',
					'${app:i18n('corBankAcntTransDtl.orgReqNum')}',
					'${app:i18n('corBankAcntTransDtl.isValid')}'], 
			colModel:[
						{name : 'id', hidden: true,hidedlg:true},
						{name : 'bankName',index : 'bankName'},
						{name : 'bankNum',index : 'bankNum'},
						{name : 'bankCardNum',index : 'bankCardNum'},
						{name : 'custName',index : 'custName'},
						{name : 'transDate',index : 'transDate'},
						{name : 'transTime',index : 'transTime'},
						{name : 'direction',index : 'direction'},
						{name : 'transType',index : 'transType'},
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'transCurrency',index : 'transCurrency'},
						{name : 'amount',index : 'amount'},
						{name : 'bankBalanceAfterTrans',index : 'bankBalanceAfterTrans'},
						{name : 'bankAvaliableAmount',index : 'bankAvaliableAmount'},
						{name : 'bankFrozenAmoumt',index : 'bankFrozenAmoumt'},
						{name : 'transComments',index : 'transComments'},
						{name : 'interestsStartDate',index : 'interestsStartDate'},
						{name : 'otherBankCarNum',index : 'otherBankCarNum'},
						{name : 'otherCustName',index : 'otherCustName'},
						{name : 'otherBankNum',index : 'otherBankNum'},
						{name : 'otherBankName',index : 'otherBankName'},
						{name : 'transRate',index : 'transRate'},
						{name : 'certType',index : 'certType'},
						{name : 'certNum',index : 'certNum'},
						{name : 'documentStaff',index : 'documentStaff'},
						{name : 'documentReviewer',index : 'documentReviewer'},
						
						
						{name : 'bankCreditAcntCode',index : 'bankCreditAcntCode', hidden: true},
						{name : 'bankCreditName',index : 'bankCreditName', hidden: true},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum', hidden: true},
						{name : 'bankDebitAcntCode',index : 'bankDebitAcntCode', hidden: true},
						{name : 'bankDebitName',index : 'bankDebitName', hidden: true},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum', hidden: true},
						{name : 'transStatus',index : 'transStatus', hidden: true},
						{name : 'bankMsgCode',index : 'bankMsgCode', hidden: true},
						{name : 'createTime',index : 'createTime', hidden: true},
						{name : 'updateTime',index : 'updateTime', hidden: true},
						{name : 'errCode',index : 'errCode', hidden: true},
						{name : 'errDesc',index : 'errDesc', hidden: true},
						{name : 'handleTime',index : 'handleTime', hidden: true},
						{name : 'operator',index : 'operator', hidden: true},
						{name : 'handleDesc',index : 'handleDesc', hidden: true},
						{name : 'handleStatus',index : 'handleStatus', hidden: true},
						{name : 'orgReqNum',index : 'orgReqNum', hidden: true},
						{name : 'isValid',index : 'isValid', hidden: true},
			],
			pager: "#gridPager",
			/* toolbar: [true,"top"], */
			caption: "${app:i18n('corBankAcntTransDtl.corBankAcntTransDtlListJsp.tableTitle')}",
			
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				/* if(ids.length <= 0){
					$("div.warning").html("没有记录！");
					$("div.warning").show();
				} */
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
		/* $("#gridToolbar").appendTo($("#t_gridTable")); */

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
		$("#gridTable").jqGrid("setGridParam",{url:"corBankAcntTransDtlSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		
		$("#gridTable").setPostDataItem("bankName", $("#bankName").val());
		$("#gridTable").setPostDataItem("bankNum", $("#bankNum").val());
		$("#gridTable").setPostDataItem("bankCardNum", $("#bankCardNum").val());
		$("#gridTable").setPostDataItem("custName", $("#custName").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("direction", $("#direction").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
		$("#gridTable").setPostDataItem("transCurrency", $("#transCurrency").val());
		$("#gridTable").setPostDataItem("amount", $("#amount").val());
		$("#gridTable").setPostDataItem("bankBalanceAfterTrans", $("#bankBalanceAfterTrans").val());
		$("#gridTable").setPostDataItem("bankAvaliableAmount", $("#bankAvaliableAmount").val());
		$("#gridTable").setPostDataItem("bankFrozenAmoumt", $("#bankFrozenAmoumt").val());
		$("#gridTable").setPostDataItem("transComments", $("#transComments").val());
		$("#gridTable").setPostDataItem("interestsStartDate", $("#interestsStartDate").val());
		$("#gridTable").setPostDataItem("otherBankCarNum", $("#otherBankCarNum").val());
		$("#gridTable").setPostDataItem("otherCustName", $("#otherCustName").val());
		$("#gridTable").setPostDataItem("otherBankNum", $("#otherBankNum").val());
		$("#gridTable").setPostDataItem("otherBankName", $("#otherBankName").val());
		$("#gridTable").setPostDataItem("transRate", $("#transRate").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("documentStaff", $("#documentStaff").val());
		$("#gridTable").setPostDataItem("documentReviewer", $("#documentReviewer").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("maxAmount", $("#maxAmount").val());
		$("#gridTable").setPostDataItem("minAmount", $("#minAmount").val());
		
		
		/* $("#gridTable").setPostDataItem("bankCreditAcntCode", $("#bankCreditAcntCode").val());
		$("#gridTable").setPostDataItem("bankCreditName", $("#bankCreditName").val());
		$("#gridTable").setPostDataItem("bankCreditCardNum", $("#bankCreditCardNum").val());
		$("#gridTable").setPostDataItem("bankDebitAcntCode", $("#bankDebitAcntCode").val());
		$("#gridTable").setPostDataItem("bankDebitName", $("#bankDebitName").val());
		$("#gridTable").setPostDataItem("bankDebitCardNum", $("#bankDebitCardNum").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());
		$("#gridTable").setPostDataItem("bankMsgCode", $("#bankMsgCode").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("errCode", $("#errCode").val());
		$("#gridTable").setPostDataItem("errDesc", $("#errDesc").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("operator", $("#operator").val());
		$("#gridTable").setPostDataItem("handleDesc", $("#handleDesc").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
		$("#gridTable").setPostDataItem("orgReqNum", $("#orgReqNum").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val()); */
	}
</script>

<s:form id="corBankAcntTransDtlListForm" method="post"
	action="corBankAcntTransDtlList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corBankAcntTransDtl.corBankAcntTransDtlListJsp.headTitle')}</h3>
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
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>
								<tr>
									<th>${app:i18n('corReservedFundAcnt.bankNames')}：</th>
									<td style="padding-right:60px"><select name="bankName" id="bankName"
										class="width_c" style="width: 160px">
											<option value="">请选择</option>
											<s:iterator value="bankNameList" var="bankName">
												<option value="${bankName.key}">${bankName.value}</option>
											</s:iterator>
									</select></td>

									<th>${app:i18n('corReservedFundAcnt.bankCardNum')}：</th>
									<td style="padding-right:60px"><input name="bankCardNum" id="bankCardNum"
										class="width_c" style="width: 160px" /></td>

									<th>${app:i18n('corReservedFundAcnt.custName')}：</th>
									<td><input name="custName" id="custName"
										class="width_c" style="width: 160px" maxlength="50" /></td>
								</tr>
								<tr>
									<!-- 开始时间 -->
									<th >${app:i18n('corBankAcntTransDtl.startTime')}：</th>
									<td>
										<!-- <input class="width_c" readonly="true" name="startTime" 
										id="startTime" value=""  class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d 00:00:00\'}'})" /> -->
										<input name="startTime" id="startTime" class="Wdate"
										style="width: 158px" value="${corBankAcntTransDtl.startTime}"
										readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
									</td>

									<!-- 结束时间 -->
									<th>${app:i18n('corBankAcntTransDtl.endTime')}：</th>
									<td>
										<!-- <input class="width_c" readonly="true" name="endTime"  width="10"
										id="endTime" value="" class="Wdate"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d 23:59:59\'}'})" /> -->
										<input name="endTime" id="endTime" class="Wdate"
										style="width: 167px" value="${corBankAcntTransDtl.endTime}"
										readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})" />
									</td>
									<th ><em>*</em>${app:i18n('corBankAcntTransDtl.direction')}：</th>
									<td ><select name="direction" id="direction"
										class="width_c" style="width: 170px">
											<option value="">请选择</option>
											<s:iterator value="directionList" var="direction">
												<option value="${direction.key}">${direction.value}</option>
											</s:iterator>
									</select></td>
								</tr>
								<tr>
									<!-- 最大金额 -->
									<th >${app:i18n('corBankAcntTrans.maxAmount')}：</th>
									<td><input name="maxAmount" id="maxAmount"
										class="width_c" style="width: 150px" value="9999999999999.99" /></td>
									<!-- 最小金额 -->
									<th>${app:i18n('corBankAcntTrans.minAmount')}：</th>
									<td ><input name="minAmount" id="minAmount"
										class="width_c" style="width: 160px" value="0.00" /></td>

									<th>${app:i18n('corBankAcntTrans.transCurrency')}：</th>
									<td><select name="transCurrency"
										id="transCurrency" class="width_c" style="width: 170px">
											<option value="">请选择</option>
											<s:iterator value="transCurrencyList" var="transCurrency">
												<option value="${transCurrency.key}">${transCurrency.value}</option>
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
							<%-- <a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>  --%>
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<%-- <div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>  
				</div> --%>
				</div>
			</div>
		</div>
	</div>
</s:form>
