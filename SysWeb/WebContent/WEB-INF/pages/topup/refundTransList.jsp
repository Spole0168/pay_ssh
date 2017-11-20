<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "refundTransRefundModify.action";
		var deleteUrl = "refundTransRefundDelete.action";
		var createUrl = "refundTransRefundCreate.action";
// 		var searchUrl = "refundTransSearch.action";
		var exportUrl = "refundTransRefundExport.action";
		var viewUrl   = "refundTransRefundInfo.action";	// 查看
		
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
				var days = Math.abs(parseInt(times/(1000*60*60*24)));
				if(times > 0) {
					$("div.warning span").html("结束时间必须大于开始时间！");
					$("div.warning").show();
				} else if(days >= 30) {
					$("div.warning span").html("开始时间与结束时间间跨度小于等于30天!");
					$("div.warning").show();
				} else{
					doSearch();
				}
			}
		});
		
		function validform(){
			return $("#cnlTransRefundListForm").validate({
				rules : {
					'cnlTransRefund\.startTime' : {
						required : true
					},
					'cnlTransRefund\.endTime'   :{
						required : true
					},
					'cnlTrans\.refundCode' : {
						stringMaxLength:20
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
		
		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$("#startTime").val("${startTime}");
			$("#endTime").val("${endTime}");
			$("div.warning span").html("");
			$("div.warning").hide();
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		//查看详情
		$("#view").click( function() {
			$("div.warning span").html("");
			$("div.warning").hide();
			// 获取ID
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			if (id != null) {
				$("#cnlTransRefundListForm").attr("action", viewUrl + "?id=" + id);
				$("#cnlTransRefundListForm").submit();
			} else {
				alert("请选择一条数据列！");
			}
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
		
		var refundStatusRender = $('#refundStatusRender').attr('value');
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect: false,
			shrinkToFit:false,
			autowidth:true,
			colNames:['ID', 
					'${app:i18n('cnlTransRefund.refundCode')}',
					'${app:i18n('cnlTransRefund.refundAmount')}',
					'${app:i18n('cnlTransRefund.refundContact')}',
					'${app:i18n('cnlTransRefund.refundContactTel')}',
					'${app:i18n('cnlTransRefund.refundReason')}',
					'${app:i18n('cnlTransRefund.bankDebitCustName')}',
					'${app:i18n('cnlTransRefund.bankDebitName')}',
					'${app:i18n('cnlTransRefund.bankDebitCardNum')}',
					'${app:i18n('cnlTransRefund.handler')}',
					'${app:i18n('cnlTransRefund.handleTime')}',
					'${app:i18n('cnlTransRefund.reviewer')}',
					'${app:i18n('cnlTransRefund.reviewTime')}',
					'${app:i18n('cnlTransRefund.reviewMsg')}',
					'${app:i18n('cnlTransRefund.refundStatus')}',
					'${app:i18n('cnlTransRefund.refundFailReason')}'],
					//'${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'refundCode',index : 'refundCode',align : 'left', width : '140px'},
						{name : 'refundAmount',index : 'refundAmount',align : 'right', width : '120px'},
						{name : 'refundContact',index : 'refundContact',align : 'left', width : '140px'},
						{name : 'refundContactTel',index : 'refundContactTel',align : 'left', width : '140px'},
						{name : 'refundReason',index : 'refundReason',align : 'left', width : '140px'},
						{name : 'bankDebitCustName',index : 'bankDebitCustName',align : 'left', width : '140px'},
						{name : 'bankDebitName',index : 'bankDebitName',align : 'left', width : '140px'},
						{name : 'bankDebitCardNum',index : 'bankDebitCardNum',align : 'left', width : '140px'},
						{name : 'handler',index : 'handler',align : 'left', width : '140px'},
						{name : 'handleTime',index : 'handleTime',align : 'left', width : '120px'},
						{name : 'reviewer',index : 'reviewer',align : 'left', width : '120px'},
						{name : 'reviewTime',index : 'reviewTime',align : 'left', width : '120px'},
						{name : 'reviewMsg',index : 'reviewMsg',align : 'left', width : '140px'},
						{name : 'refundStatus',index : 'refundStatus',align : 'left', width : '140px',formatter:'select', editoptions:{value:refundStatusRender}},//退款状态
						{name : 'refundFailReason',index : 'refundFailReason',align : 'left', width : '140px'},
					    //{name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlTransRefund.refundTransListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
// 				for(var i=0;i < ids.length;i++) {
// 					//控制只有审核失败显示修改按钮
// 					var item = $("#gridTable").jqGrid("getRowData",ids[i]);
// 					if(item.refundStatus =="03"|| item.refundStatus =="审核失败"){
// 						var all = "";
// 						var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
	
// 						mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
						
// 						var id = ids[i];
// 						var rowData = $("#gridTable").getRowData(id);
	
// 						all = all + mod;
	
// 						$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
// 					}
// 				}
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
		$("#gridTable").jqGrid("setGridParam",{url:"refundTransSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("refundCode", $("#refundCode").val());
		$("#gridTable").setPostDataItem("refundStatus", $("#refundStatus").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
	}
	
	function verify(){
// 		var gr;
// 		gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
// 		if (gr.length != 1) {
// 			alert("${app:i18n('topupTransTrace.delete.error.message')}");
// 			return;
// 		}
// 		var id = $("#gridTable").jqGrid('getGridParam','selrow');
// 		window.location="refundTransRefund/refundTransRefundVerify.action?id="+id;		
		
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var refundStatus = rowData.refundStatus;
			refundStatus = refundStatus.trim(" ");
			if(refundStatus==01){
				window.location="refundTransRefundVerify.action?id="+id;
			}else{
	    		alert("只能对未审核的退款申请进行审核！");
	    		return false;
	   		}
		}else{
			alert("请选择一条数据列！");
		}
	}
	
	// 修改
	function modify(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var refundStatus = rowData.refundStatus;
			refundStatus = refundStatus.trim(" ");
			// 只能对审核失败的记录进行修改
			if(refundStatus == 03){
				window.location="refundTransRefundModify.action?id="+id;
			} else{
	    		alert("只能对审核失败的退款申请进行修改！");
	    		return false;
	   		}
		} else{
			alert("请选择一条数据列！");
		}
	}
	
	function updateResult(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var refundStatus = rowData.refundStatus;
			refundStatus = refundStatus.trim(" ");
			if(refundStatus==02){
				window.location="refundTransRefundUpdateResult.action?id="+id;
			}else{
	    		alert("只能对审核成功的退款申请更新结果！");
	    		return false;
	   		}
		}else{
			alert("请选择一条数据列！");
		}
	}
	
</script>

<s:form id="cnlTransRefundListForm" method="post" action="refundTransList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlTransRefund.refundTransListJsp.headTitle')}</h3>
		</div>
		<input id="refundStatusRender" name="refundStatusRender" type="hidden" value="${refundStatusRender}" />
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
								<!-- 退款单号 -->
								<th width="10%">${app:i18n('cnlTransRefund.refundCode')}：</th>
								<td width="30%"><input name="refundCode" id="refundCode" style="width:160px" /></td>
								<!-- 退款状态 -->
								<th width="10%">${app:i18n('cnlTransRefund.refundStatus')}：</th>
								<td>
									<select name="cnlTransRefund.refundStatus" id="refundStatus" style="width:160px">
										<option value="" selected>${app:i18n('cnlTransRefund.refundSelected')}</option>
										<s:iterator value="%{refundStatusList}" id="refundStatusItem">
									        <option value="<s:property value="#refundStatusItem.key" />">
									        	<s:property value="#refundStatusItem.value" />
									        </option>
										</s:iterator>
							         </select>
								</td>
							</tr>
							<tr>					
								<!-- 退款开始时间 -->
								<th style="width:20%"><em>*</em>${app:i18n('cnlTransRefund.startTime')}：</th>
								<td>
									<input style="width:160px" name="cnlTransRefund.startTime" id="startTime" class="Wdate" value="${startTime}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"/> 
								</td>
								
								<!-- 退款结束时间 -->
								<th style="width:20%"><em>*</em>${app:i18n('cnlTransRefund.endTime')}：</th>											
								<td>
									<input style="width:160px" name="cnlTransRefund.endTime" id="endTime" class="Wdate" value="${endTime}" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
								</td>
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
				 	<app:isPermission resource='O_REFUND_ADD'>
						<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('cnlTransRefund.refundRequest')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='O_REFUND_AUDIT'>
						<a id="verify" onclick="verify();" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-audit">${app:i18n('cnlTransRefund.check')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='O_REFUND_MODIFY'>
						<a id="modify" onclick="modify();" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a> 
					</app:isPermission>
					<app:isPermission resource='O_REFUND_UPDATE'>
						<a id="updateResult" onclick="updateResult();" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('cnlTransRefund.updateResult')}</span></span></a> 
					</app:isPermission>
					<a id="view" class="l-btn-plain l-btn" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('cnlTransRefund.view')}</span></span></a>
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
