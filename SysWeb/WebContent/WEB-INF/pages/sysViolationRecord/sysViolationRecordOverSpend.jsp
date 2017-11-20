<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style type="text/css">
	.dis_none {
		display:none;
	}
	.dis_block {
		display:block;
	}
</style>
<script>
//获取当前系统时间
	$(function(){
		getTodayTime();
	})
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    $("#starTime").val(s1);
	    $("#endTime").val(s2);
	}
</script>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
		var modifyUrl = "sysViolationRecordModify.action";
		var deleteUrl = "sysViolationRecordDelete.action";
		var createUrl = "sysViolationRecordCreate.action";
		var searchUrl = "sysViolationRecordOverSpendSearch.action";
		var exportUrl = "sysViolationRecordExport.action";

	$().ready(function() {
		
		
		$("#search").click(function(){
		 	 //doSearch();
		$("div.warning").hide();
		var starTime=$("#starTime").val();
		var endTime=$("#endTime").val(); 

			//进行时间差是否大于一个月的判定
			starTime = starTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			starTime = new Date(starTime);
			endTime = new Date(endTime);
			var times = starTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			if(times>=0)
			{
				$("div.warning span").html("结束时间必须大于结束时间！");
				$("div.warning").show();
				return;
			}
			//判断开始时间和结束时间是否相差一个月
			if(days>29)
			{
				$("div.warning span").html("开始时间与结束时间相差不能超过30天！");
				$("div.warning").show();
				return;
			}else
			{
				doSearch();
			}
	
	});
		
		$("#reset").click(function(){
			// 设置查询参数为空
			// 设置查询参数为空
			$("div.warning").hide();
			$("#queryField :input").val("");
			getTodayTime();
		});
		
		//加入ip异常
		$("#gridTable1").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('sysViolationRecordIPExceptionDTO.cnlCustCode')}',
					'${app:i18n('sysViolationRecordIPExceptionDTO.cnlIntfCode')}',
					'${app:i18n('sysViolationRecordIPExceptionDTO.reqNum')}',
					'${app:i18n('sysViolationRecordIPExceptionDTO.violationId')}',
					'${app:i18n('sysViolationRecordIPExceptionDTO.transType')}',
					'${app:i18n('sysViolationRecordIPExceptionDTO.createTime')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
						{name : 'cnlIntfCode',index : 'cnlIntfCode',width : '10%'},
						{name : 'reqNum',index : 'reqNum',width : '10%'},
						{name : 'violationId',index : 'violationId',width : '10%'},
						{name : 'transType',index : 'transType',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false,hidden: true},
			],
			pager: "#gridPager1",
			toolbar: [true,"top1"],
			caption: "${app:i18n('sysViolationRecord.sysViolationRecordListJsp.tableTitle')}",
 			gridComplete: function() {
 				var ids = $("#gridTable1").jqGrid('getDataIDs');
 			//id长度小于等于零时 提示请输入警告
				if(ids.length<=0){
 					$("div.warning span").html("没有记录"); 
					$("div.warning").show(); 
				}
 				for(var i=0;i < ids.length;i++) {
 					var all = "";
 					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
 					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
 					var id = ids[i];
 					var rowData = $("#gridTable").getRowData(id);
 					all = all + mod;
 					$("#gridTable1").jqGrid('setRowData',ids[i],{operation:all});
 				}
 			}
		});	
		$("#gridTable1").gridUtil().customizeColumn();
 		$("#gridToolbar1").appendTo($("#t_gridTable1"));
		
		
		$("#gridTable2").gridUtil().simpleGrid({
			url:"",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('sysViolationRecordBlackListDTO.cnlCustCode')}',
					'${app:i18n('sysViolationRecordBlackListDTO.cnlIntfCode')}',
					'${app:i18n('sysViolationRecordBlackListDTO.reqNum')}',
					'${app:i18n('sysViolationRecordBlackListDTO.violationSubType')}',
					'${app:i18n('sysViolationRecordBlackListDTO.violationId')}',
					'${app:i18n('sysViolationRecordBlackListDTO.transType')}',
					'${app:i18n('sysViolationRecordBlackListDTO.createTime')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
						{name : 'cnlIntfCode',index : 'cnlIntfCode',width : '10%'},
						{name : 'reqNum',index : 'reqNum',width : '10%'},
						{name : 'violationSubType',index : 'violationSubType',width : '10%'},
						{name : 'violationId',index : 'violationId',width : '10%'},
						{name : 'transType',index : 'transType',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false,hidden: true},
			],
			pager: "#gridPager2",
			toolbar: [true,"top2"],
			caption: "${app:i18n('sysViolationRecord.sysViolationRecordListJsp.tableTitle')}",
 			gridComplete: function() {
 				var ids = $("#gridTable2").jqGrid('getDataIDs'); 
 				if(ids.length<=0){
 					$("div.warning span").html("没有记录"); 
 					$("div.warning").show(); 
 					}
				for(var i=0;i < ids.length;i++) {
 					var all = "";
 					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

 					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
 					var id = ids[i];
 					var rowData = $("#gridTable").getRowData(id);

 					all = all + mod;

 					$("#gridTable2").jqGrid('setRowData',ids[i],{operation:all});
 				}
			}
		});
		
		$("#gridTable2").gridUtil().customizeColumn();
		$("#gridToolbar2").appendTo($("#t_gridTable2"));
		
		

		//加入OverSpend异常
		$("#gridTable3").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			shrinkToFit:false,
			colNames:['ID', 
			          '${app:i18n('SysViolationRecordOverSpendDTO.cnlCustCode')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.cnlIntfCode')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.createTime')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.reqNum')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.reqInnerNum')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transNum')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankTransNum')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transCurrency')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.violationId')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transDc')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transType')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transStatus')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.violationDesc')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transTime')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bnakHandlePriority')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankReqTrnasDate')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankReqTransTime')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bnakServiceFeeAct')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankAccepteTime')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankReturnTime')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankDebitName')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankDebitCustName')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankCreditName')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankCreditCustName')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankCreditCardNum')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.bankPmtCnlType')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.cnlCustCode')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.termialType')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.isinGl')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.transTimeS')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.isPrinted')}',
						'${app:i18n('SysViolationRecordOverSpendDTO.printedTime')}',
						   '${app:i18n('global.jsp.operation')}' ],
				colModel:[
							{name : 'id', hidden: true},
							{name : 'cnlCustCode',index : 'cnlCustCode'},
							{name : 'cnlIntfCode',index : 'cnlIntfCode'},
							{name : 'createTime',index : 'createTime'},
							{name : 'reqNum',index : 'reqNum'},
							{name : 'reqInnerNum',index : 'reqInnerNum'},
							{name : 'transNum',index : 'transNum'},
							{name : 'bankTransNum',index : 'bankTransNum'},
							{name : 'transCurrency',index : 'transCurrency'},
							{name : 'violationId',index : 'violationId'},
							{name : 'transDc',index : 'transDc'},
							{name : 'transType',index : 'transType'},
							{name : 'transStatus',index : 'transStatus'},
							{name : 'violationDesc',index : 'violationDesc'},
							{name : 'transTime',index : 'transTime'},
							{name : 'bnakHandlePriority',index : 'bnakHandlePriority'},
							{name : 'bankReqTrnasDate',index : 'bankReqTrnasDate'},
							{name : 'bankReqTransTime',index : 'bankReqTransTime'},
							{name : 'bnakServiceFeeAct',index : 'bnakServiceFeeAct'},
							{name : 'bankAccepteTime',index : 'bankAccepteTime'},
							{name : 'bankReturnTime',index : 'bankReturnTime'},
							{name : 'bankDebitName',index : 'bankDebitName'},
							{name : 'bankDebitCustName',index : 'bankDebitCustName'},
							{name : 'bankCreditName',index : 'bankCreditName'},
							{name : 'bankCreditCustName',index : 'bankCreditCustName'},
							{name : 'bankCreditCardNum',index : 'bankCreditCardNum'},
							{name : 'bankPmtCnlType',index : 'bankPmtCnlType'},
							{name : 'cnlCustCode',index : 'cnlCustCode'},
							{name : 'termialType',index : 'termialType'},
							{name : 'isinGl',index : 'isinGl'},
							{name : 'transTimeS',index : 'transTimeS'},
							{name : 'isPrinted',index : 'isPrinted'},
							{name : 'printedTime',index : 'printedTime'},
						    {name:  'operation',align:'center', search:false,sortable:false,editable:true,title:false,hidden: true},
			],
			pager: "#gridPager3",
			toolbar: [true,"top3"],
			caption: "${app:i18n('sysViolationRecord.sysViolationRecordListJsp.tableTitle')}",
 			gridComplete: function() {
 				var ids = $("#gridTable3").jqGrid('getDataIDs'); 
				if(ids.length<=0){
					$("div.warning span").html("没有记录"); 
 					$("div.warning").show();
 					}
				for(var i=0;i < ids.length;i++) {
					var all = "";
 					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

 					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
 					var id = ids[i];
 					var rowData = $("#gridTable3").getRowData(id);

 					all = all + mod;

 					$("#gridTable3").jqGrid('setRowData',ids[i],{operation:all});
 				}
 			}
		});
	
		$("#gridTable3").gridUtil().customizeColumn();
		$("#gridToolbar3").appendTo($("#t_gridTable3"));
		
		$("#div1").addClass("dis_none");
		$("#div2").addClass("dis_none");
		$("#div3").addClass("dis_none");
		
	$("#violationType").change(function check(){
		
		$("#div1").removeClass("dis_block");
		$("#div1").addClass("dis_none");
		$("#div2").removeClass("dis_block");
		$("#div2").addClass("dis_none");
		$("#div3").removeClass("dis_block");
		$("#div3").addClass("dis_none");
		
		
		var vt = $("#violationType").val();
		if(vt!=''){
			if(vt == '01'){
				$("#div1").addClass("dis_block");
			}else if(vt == '02'){
				$("#div2").addClass("dis_block");
			}else if(vt == '03'){
				$("#div3").addClass("dis_block");
			}else{
				alert("请选择事件来源！");
			}
		}else{
			alert("请选择事件来源！");
		}
	});	
	});
	
	
	
	//在进行搜索的时候调不同的Action
	//IP异常
	function doSearch(){
		// 设置查询参数
		var vtp = $("#violationType").val();
		if(vtp != ''){
			if(vtp=='01'){
				setQueryCondition1();
				$("#gridTable1").jqGrid("setGridParam",{page:1});
				$("#gridTable1").jqGrid("setGridParam",{url:"sysViolationRecordSearch.action"}).trigger("reloadGrid");
			}else if(vtp=='02'){
				setQueryCondition2();
				$("#gridTable2").jqGrid("setGridParam",{page:1});
				$("#gridTable2").jqGrid("setGridParam",{url:"sysViolationRecordBlackListSearch.action"}).trigger("reloadGrid");
			}else if(vtp=='03'){
				setQueryCondition3();
				$("#gridTable3").jqGrid("setGridParam",{page:1});
				$("#gridTable3").jqGrid("setGridParam",{url:"sysViolationRecordOverSpendSearch.action"}).trigger("reloadGrid");
			}else{alert("请选择事件来源！");}
		}else{
			alert("请选择事件来源！");
		}
	}
	function setQueryCondition1(){
		// 设置查询参数
	
		$("#gridTable1").setPostDataItem("violationType", $("#violationType").val());
		$("#gridTable1").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable1").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable1").setPostDataItem("cnlIntfCode", $("#cnlIntfCode").val());
		$("#gridTable1").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable1").setPostDataItem("starTime", $("#starTime").val());
		$("#gridTable1").setPostDataItem("endTime", $("#endTime").val());
	}
	function setQueryCondition2(){
		// 设置查询参数
	
		$("#gridTable2").setPostDataItem("violationType", $("#violationType").val());
		$("#gridTable2").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable2").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable2").setPostDataItem("cnlIntfCode", $("#cnlIntfCode").val());
		$("#gridTable2").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable2").setPostDataItem("starTime", $("#starTime").val());
		$("#gridTable2").setPostDataItem("endTime", $("#endTime").val());
	}
	function setQueryCondition3(){
		// 设置查询参数
	
		$("#gridTable3").setPostDataItem("violationType", $("#violationType").val());
		$("#gridTable3").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable3").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable3").setPostDataItem("cnlIntfCode", $("#cnlIntfCode").val());
		$("#gridTable3").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable3").setPostDataItem("starTime", $("#starTime").val());
		$("#gridTable3").setPostDataItem("endTime", $("#endTime").val());
	}

	
	
</script>
<s:form id="sysViolationRecordListForm" method="post" action="sysViolationRecordList.action">
<div class="layout">
	<div class="block m-b" >
		<div class="block_title">
			<h3>${app:i18n('sysViolationRecord.sysViolationRecordListJsp.headTitle')}</h3>
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
								<th width="12%">事件来源：</th>
								<td width="30%">
									<select name="violationType" id="violationType" class="width_c"  style="width: 190px" >
										<option value="">--请选择--</option>
										<s:iterator value="violationTypeList" var="violationType">
											<option value="${violationType.key }">${violationType.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="14%">${app:i18n('sysViolationRecord.reqNum')}：</th>
								<td width="30%"><input name="reqNum" id="reqNum" class="width_c" /></td>
								
								<th width="14%">${app:i18n('sysViolationRecord.cnlCustCode')}：</th>
								<td width="30%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>
							</tr>
							<tr>
								<th width="12%">${app:i18n('sysViolationRecord.cnlIntfCode')}：</th>
								<td width="30%"><input name="cnlIntfCode" id="cnlIntfCode" class="width_c" /></td>
								
								<th width="14%">发生开始时间：</th>
								<td width="30%"><input class="Wdate" name="starTime"  id="starTime"  type="text"  onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" style="width: 190px"  /></td>
								
								<th width="14%">发生结束时间：</th>
								<td width="30%"><input class="Wdate" name="endTime"  id="endTime"  type="text"  onFocus="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starTime\')}'})" style="width: 190px" /></td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					</div>
				</div>
			</div>
		 			 	
			<div class="block"  name="ipExcption"  id="one">
				<div id="ipExcption">
					<div id = "div1">
						<table id="gridTable1">
						</table>
						<div id="gridPager1"></div>
						<div id="gridToolbar1" hidden="ture" ></div>
					</div>
					<div id = "div2">
						<table id="gridTable2">
						</table>
						<div id="gridPager2"></div>
						<div id="gridToolbar2" hidden="ture" ></div>
					</div>
					<div id = "div3">
						<table id="gridTable3">
						</table>
						<div id="gridPager3"></div>
						<div id="gridToolbar3" hidden="ture" >
					</div>
					</div>
					
				</div>
					
				</div>
			</div>
	 
		</div>
		
	</div>
	
 
</div>
</s:form>
