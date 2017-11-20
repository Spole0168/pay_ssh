<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<!-- 包含简单表格的JavaScript -->

<script type="text/javascript" charset="UTF-8">
var dlgUserId ="#dialog-ajax-cnl-view";
	$().ready(function() {
		var startDate = $("#beginTime").val();
		var endDate = $("#endTime").val();
		if($("#message").val()!=""){
			 alert($("#message").val());
			 $("#message").val("");
		}
		
		if($("#msg").val()!=""){
			 $.growlUI('成功信息！',$("#msg").val());
			 $("#msg").val("");
		}
		if($("#errorMsg").val()!=""){
			 $.growlUI('失败信息！',$("#errorMsg").val());
			 $("#errorMsg").val("");
		}
		
		var modifyUrl = "cnlTransTracePendingModify.action";
		var deleteUrl = "cnlTransTraceDelete.action";
		var createUrl = "cnlTransTraceCreate.action";
		var searchUrl = "cnlTransTracePendingSearch.action";
		var exportUrl = "cnlTransTraceExport.action";
		var detailUrl = "cnlTransTracePendingDetail.action";
		var verifyUrl ="cnlTransTracePendingVerify.action";
		
		
		$("#addHelp").click(function(){
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			if(id!=null){
				var rowData = $("#gridTable").getRowData(id);
				var bankAccepteTime = rowData.bankAccepteTime;
				var handleStatus = rowData.handleStatus;
				var transStatus = rowData.transStatus;
				if(bankAccepteTime!=""&&bankAccepteTime!=null){
					if("未提交"== handleStatus&&"处理中"==transStatus){
						window.location="addHelp.action?id="+id;
					}else if("未提交"!= handleStatus){
						alert("已经提交过补救单");
					}else{
						alert("交易状态不对，请确认");
					}
				}else{
					alert("此条记录还没有对接银行接口");
				}
				
			}else{
				alert("请选择一条数据列");
				return false;
			}
		});
		
		
		$("#search").click(function(){
			
			
			
			var ok = true;
			$("div.warning span").html("");
			$("div.warning").hide();
			
			
			if($("#reqBatch").val()!=null&&$("#reqBatch").val()!=""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#reqBatch").val())){
					
				}else{
					$("div.warning span").html("批次号只能是数字");
					$("div.warning").show();
					ok = false;
				}
			}
			
			// 开始时间与结束时间间跨度小于等于一个月的校验
			var createtime = $("#beginTime").val();
			var endtime = $("#endTime").val();
			if(createtime == null || createtime == "" || endtime == null || endtime == ""){
				$("div.warning span").html("请输入必填信息!");
				$("div.warning").show();
				ok = false;
			}else{
				
				var	start=new Date(createtime.replace("-", "/").replace("-", "/")); 
			    var	end=new Date(endtime.replace("-", "/").replace("-", "/"));
			    
				var endStart = end.getTime() - start.getTime();
				var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
				var month =leaveTime / (30 * 24 * 3600 * 1000);
				
				if((start < end && month < 1)||($("#beginTime").val()>$("#endTime").val())){
					
					$("div.warning").hide();
				}else{
					
					$("div.warning span").html("开始时间与结束时间间跨度小于等于30天!");
					$("div.warning").show();
					ok = false;
				}
				if($("#beginTime").val()>$("#endTime").val()){
					$("div.warning span").html("结束时间不能小于开始时间");
					$("div.warning").show();
					ok = false;
				}
			}
			
			if(ok){
				doSearch();
			}
			
			
		});
		
		
		//查看详情
		$("#lookDetail").click( function() {
			$("div.warning span").html("");
			$("div.warning").hide();
			 
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			if (id == null) {
				$("div.warning span").html("请选择一行");
				$("div.warning").show();
				return;
			}
		     var url = detailUrl+"?id="+id+"&time="+new Date().getTime();
		     var title = "明细界面";
		     $(dlgUserId).dialog({width:800,height:600,modal:true});
			 openDialog(dlgUserId,title,url);
		});
		
	

		
		
		
		
		//审核
		$("#verify").click( function() {
			$("div.warning span").html("");
			$("div.warning").hide();
			
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			if (id ==null) {
				$("div.warning span").html("请选择一行");
				$("div.warning").show();
				return;
			}
			
			
				var rowData = $("#gridTable").getRowData(id);
				
				var trnasStatus = rowData.transStatus;
				var handleStatus = rowData.handleStatus;
				if(trnasStatus=="处理中"&&handleStatus=="已提交"){
					$("#cnlTransTraceListForm").attr("action",verifyUrl+"?id="+id);
					$("#cnlTransTraceListForm").submit();
					$("div.warning span").html("");
					$("div.warning").hide();
					return;
					
				}
			
				$("div.warning span").html("此记录不符合审核要求");
				$("div.warning").show();
				
		});
		
		//修改
		$("#update").click( function() {
			$("div.warning span").html("");
			$("div.warning").hide();
			
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			if (id ==null) {
				$("div.warning span").html("请选择一行");
				$("div.warning").show();
				return;
			}
				var rowData = $("#gridTable").getRowData(id);
				
				var transStatus = rowData.transStatus;
				var handleStatus = rowData.handleStatus;
				if(transStatus=="处理中"&&handleStatus=="审核失败"){
					
					$("#cnlTransTraceListForm").attr("action",modifyUrl+"?id="+id);
					$("#cnlTransTraceListForm").submit();
					$("div.warning span").html("");
					$("div.warning").hide();
					return;
				}
		
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
				$("div.warning span").html("此记录不符合修改要求");
				$("div.warning").show();
				
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$("#beginTime").val(startDate);
			$("#endTime").val(endDate);
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		

		

		$("#gridTable").gridUtil().simpleGrid({
			url:"",
			shrinkToFit:false,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			rowList:[50,100,200],
			multiselect:false,
			
			colNames:['ID', 
			          '${app:i18n('cnlTransTrace.pending.reqNumsss')}',
			          '${app:i18n('cnlTransTrace.pending.reqInnerNum')}',
			          '${app:i18n('cnlTransTrace.pending.transCurrency')}',
			          '${app:i18n('cnlTransTrace.pending.transAmount')}',
			          '${app:i18n('cnlTransTrace.pending.transDc')}',
			          '${app:i18n('cnlTransTrace.pending.transTypess')}',
			          '${app:i18n('cnlTransTrace.pending.transCommentss')}',
			          '${app:i18n('cnlTransTrace.pending.transStatus')}',
			          '${app:i18n('cnlTransTrace.pending.transTime')}',
			          '${app:i18n('cnlTransTrace.pending.bnakHandlePriority')}',
			          '${app:i18n('cnlTransTrace.pending.bankReqTrnasDate')}',
			          '${app:i18n('cnlTransTrace.pending.bankReqTransTime')}',
			          '${app:i18n('cnlTransTrace.pending.bnakServiceFeeAct')}',
			          '${app:i18n('cnlTransTrace.pending.bankTransNumr')}',
			          '${app:i18n('cnlTransTrace.pending.bankDebitName')}',
			          '${app:i18n('cnlTransTrace.pending.bankDebitCustName')}',
			          '${app:i18n('cnlTransTrace.pending.bankDebitCardNum')}',
			          '${app:i18n('cnlTransTrace.pending.bankCreditName')}',
			          '${app:i18n('cnlTransTrace.pending.bankCreditCustName')}',
			          '${app:i18n('cnlTransTrace.pending.bankCreditCardNum')}',
			          '${app:i18n('cnlTransTrace.pending.bankPmtCnlType')}',
			          '${app:i18n('cnlTransTrace.pending.cnlCnlCoder')}',
			          '${app:i18n('cnlTransTrace.pending.termialType')}',
			          '${app:i18n('cnlTransTrace.pending.bankAccepteTime')}',
			          '${app:i18n('cnlTransTrace.pending.handleStatus')}',
			          '${app:i18n('cnlTransTrace.pending.errMsg')}',],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'reqNum',index : 'reqNum'},
						{name : 'reqInnerNum',index : 'reqInnerNum'},
						{name : 'transCurrency',index : 'transCurrency'},
						{name : 'transAmount',index : 'transAmount'},
						{name : 'transDc',index : 'transDc'},
						{name : 'transType',index : 'transType'},
						{name : 'transComments',index : 'transComments'},
						{name : 'transStatus',index : 'transStatus'},
						{name : 'transTime',index : 'transTime'},
						{name : 'bnakHandlePriority',index : 'bnakHandlePriority'},
						{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate'},
						{name : 'bankReqTransTime',index : 'bankReqTransTime'},
						{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct'},
						{name : 'bankTransNum',index : 'bankTransNum'},
						{name : 'bankDebitName',index : 'bankDebitName'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum'},
						{name : 'bankCreditName',index : 'bankCreditName'},
						{name : 'bankCreditCustName',index : 'bankCreditCustName'},
						{name : 'bankCreditCardNum',index : 'bankCreditCardNum'},
						{name : 'bankPmtCnlType',index : 'bankPmtCnlType'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode'},
						{name : 'termialType',index : 'termialType'},					
						{name : 'bankAccepteTime',index : 'bankAccepteTime',hidden: true},
						{name : 'handleStatus',index : 'handleStatus'},
						{name : 'errMsg',index : 'errMsg'},],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransTrace.cnlTransTracePendingListJsp.tableTitle')}",
		/* 	gridComplete: function() {
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
		$("#gridTable").jqGrid("setGridParam",{url:"cnlTransTracePendingSearch.action"}).trigger("reloadGrid");
	}
	
	

	
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("transDc", $("#transDc").val());
		$("#gridTable").setPostDataItem("dateType", $("#dateType").val());
		$("#gridTable").setPostDataItem("beginTime", $("#beginTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("transStatus", $("#transStatus").val());
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("handleStatus", $("#handleStatus").val());
		$("#gridTable").setPostDataItem("errType", $("#errType").val());
		$("#gridTable").setPostDataItem("errCode", $("#errCode").val());
		$("#gridTable").setPostDataItem("bankTransNum", $("#bankTransNum").val());
	}
	
	
	//弹出层
	function openDialog(dlgId, title, url){
		//$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
		$(dlgId).dialog({
			   close: function(event, ui) { 			   
			  		$(dlgId).html(" ");			   
	  			}  
		});
	}
	
	
	
	function refreshGrid(){
		$("#gridTable").trigger("reloadGrid");//重新载入
	}
	function closeDialog(){
		$("#dialog-ajax-cnl-view").dialog('close');//关闭
	}
	
</script>


<s:form id="cnlTransTraceListForm" method="post"
	action="cnlTransTracePendingList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('cnlTransTrace.cnlTransTracePendingListJsp.headTitle')}</h3>
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
									<!-- 渠道来源标识 -->
									<th width="15%">${app:i18n('cnlTransTrace.cnlCnlCoder')}：</th>
									<td width="20%"><select id="cnlCnlCode" name="cnlCnlCode"
										style="width: 137px">
											<option value="" selected="selected">请选择</option>
											<s:iterator value="reqNumList">
												<option value="<s:property value="key"/>">
													<s:property value="value" />
												</option>
											</s:iterator>

									</select></td>

									<!-- 渠道订单号 -->
									<th width="15%">${app:i18n('cnlTransTrace.reqNumsss')}：</th>
									<td width="20%"><input name="reqNum" id="reqNum"
										class="width_c" style="width: 128px" /></td>

									<!-- 系统申请单流水号 -->
									<th width="15%">${app:i18n('cnlTransTrace.pending.reqInnerNum')}：</th>
									<td width="20%"><input name="reqInnerNum" id="reqInnerNum"
										class="width_c" style="width: 128px" /></td>
								<tr />

								<!-- 时间类型 -->
								<th width="15%"><em>*</em>${app:i18n('cnlTrans3m.dateType')}：</th>
								<td width="20%"><select id="dateType" name="dateType"
									style="width: 137px">
										<option value="1">交易时间</option>
										<option value="2">银行网关进入时间</option>
								</select></td>
								<!-- 开始时间 -->
								<th width="15%"><em>*</em>${app:i18n('cnlTransTrace.beginTime')}：</th>
								<td width="20%"><input style="width: 135px"
									name="beginTime" id="beginTime" value="${beginTime}"
									class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>

								<!-- 结束时间 -->
								<th width="15%"><em>*</em>${app:i18n('cnlTransTrace.endTime')}：</th>
								<td width="20%"><input style="width: 135px" name="endTime"
									id="endTime" value="${endTime}" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								<tr />


								<!-- 交易类型 -->
								<th width="15%">${app:i18n('cnlTransTrace.transTypess')}：</th>
								<td width="20%"><select id="transType" name="transType"
									style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="typeList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>


								<!-- 交易方向 -->
								<th width="15%">${app:i18n('cnlTransTrace.transDc')}：</th>
								<td width="20%"><select id="transDc" name="transDc"
									style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="dcList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>

								<!-- 交易状态 -->
								<th width="15%"><em>*</em>${app:i18n('cnlTransTrace.transStatus')}：</th>
								<td width="20%"><select id="transStatus" name="transStatus"
									style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="statusList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>
								</tr>
								<!-- 补救单申请状态 -->
								<th width="15%">${app:i18n('cnlTransTrace.handleStatuss')}：</th>
								<td width="20%"><select id="handleStatus"
									name="handleStatus" style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="handleStatusList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>
								<!-- 错误类型 -->
								<th width="15%">${app:i18n('cnlTransTrace.errorType')}：</th>
								<td width="20%"><select id="errType" name="errType"
									style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="errTypeList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>
								<!-- 错误代码 -->
								<th width="15%">${app:i18n('cnlTransTrace.errorCode')}：</th>
								<td width="20%" style="width: 137px"><select id="errCode"
									name="errCode" style="width: 137px">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="errCodeList">
											<option value="<s:property value="key"/>"><s:property
													value="value" />
											</option>
										</s:iterator>
								</select></td>
								</tr>
								<!-- 银行流水流水号 -->
								<th width="15%">${app:i18n('cnlTransTrace.pending.bankInnerNum')}：</th>
								<td width="20%"><input name="bankTransNum"
									id="bankTransNum" class="width_c" style="width: 128px" /></td>
							</tbody>
						</table>
						<div class="btn_layout">
							<a name="search" id="search" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
							<a name="reset" id="reset" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>

						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager">
						<input id="message" type="hidden"
							value="<s:property value="message"/>"> <input id="msg"
							type="hidden" value="<s:property value="msg"/>"> <input
							id="errorMsg" type="hidden"
							value="<s:property value="errorMsg"/>">
					</div>
					<div id="gridToolbar">
						<app:isPermission resource='O_CNL_TRANS_TRACE_ADD'>
							<a id="addHelp" class="l-btn-plain l-btn m-14"><span
								class="l-btn-right"><span class="l-btn-text icon-add">${app:i18n('cnlTransTrace.addHelp')}</span></span></a>
						</app:isPermission>

						<a id="lookDetail" class="l-btn-plain l-btn m-14"><span
							class="l-btn-right"> <span class="l-btn-text icon-view">${app:i18n('cnlTransTrace.lookDetail')}</span></span></a>


						<app:isPermission resource='O_CNL_TRANS_TRACE_MODIFY'>
							<a id="update" class="l-btn-plain l-btn m-14"><span
								class="l-btn-right"><span class="l-btn-text icon-edit">${app:i18n('cnlTransTrace.update')}</span></span></a>
						</app:isPermission>


						<app:isPermission resource='O_CNL_TRANS_TRACE_AUDIT'>
							<a id="verify" class="l-btn-plain l-btn m-14"><span
								class="l-btn-right"><span class="l-btn-text icon-select">${app:i18n('TransTrace_verify')}</span></span></a>
						</app:isPermission>


					</div>
				</div>
			</div>
		</div>
	</div>
	<div hidden="true">
		<form action="findById">
			<input id="" type="hidden" value=""> <input id=""
				type="hidden" value=""> <input id="" type="hidden" value="">
			<input id="" type="hidden" value=""> <input id=""
				type="hidden" value=""> <input id="" type="hidden" value="">
		</form>
	</div>

	<div id="dialog-ajax-cnl-view"></div>
</s:form>



