<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style>
	#custpersonalinfo{
		font-family:隶书;
	}
	#custpersonalinfo td{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:left;
	}
	#custpersonalinfo th{
		border-bottom:1px dashed #85F2F2;
		color:#141D28;
		text-align:right;
	}
</style>
<div id="editblockidiv" class="layout block m-b">
<center>
<table width="100%" border="0" cellspacing="5" cellpadding="2" id="custpersonalinfo">
	<div class="block">
		<table id="balanceGridTable">
		</table>
		<div id="balanceGridPager"></div>
	</div>
	<!-- <input type="hidden" id="reqInnerNum" name="#reqInnerNum" /> -->
</center>
</div>
<script type="text/javascript" charset="UTF-8">
	/* $(document).ready(function(){
		var judge_custpersonal = ${judge_custpersonal };
		if(judge_custpersonal == false){
			$("#custpersonalinfo tr:lt(8)").hide();
			$("#custpersonalinfo tr:eq(8)").show();
		}else{
			$("#custpersonalinfo tr:lt(8)").show();
			$("#custpersonalinfo tr:eq(8)").hide();
		} 
	}) */
	
	$().ready(function() {
		var modifyUrl = "cnlReqTransModify.action";
		var deleteUrl = "cnlReqTransDelete.action";
		var createUrl = "cnlReqTransCreate.action";
		var searchUrl = "cnlReqTransSearch.action";
		var exportUrl = "cnlReqTransExport.action";
		var detailUrl = "cnlReqTransDetail.action";
		var str="${reqInnerNum}";
		console.info(str);
		var url="query.action?reqInnerNum="+str;
		$("#balanceGridTable").gridUtil().simpleGrid({
			url: url,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			rowNum:50,
			rowList:[50,100,200],
			colNames:['ID', 
			        '${app:i18n('cnlReqTrans.reqInnerNum.cyy')}',
			        '${app:i18n('cnlReqTrans.reqInnerNum.cyy')}',
					'${app:i18n('cnlReqTrans.cnlCustCode')}',
					'${app:i18n('cnlReqTrans.isinGl')}',
					'${app:i18n('cnlReqTrans.transDc')}',
					'${app:i18n('cnlReqTrans.transCurrency')}',
					'${app:i18n('cnlReqTrans.transAmount')}',
					'${app:i18n('cnlReqTrans.transStatus')}',
					'${app:i18n('cnlReqTrans.transDate')}',
					'${app:i18n('cnlReqTrans.TransTime')}',
					'${app:i18n('cnlReqTrans.transComments')}',
			],
			colModel:[
						{name : 'id',width : '20%', hidden: true},
						{name : 'reqInnerNum',index : 'reqInnerNum',width : '20%',hidden:true},
						{name : 'reqNum',index : 'reqNum',width : '20%'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '20%'},
						{name : 'isinGl',index : 'isinGl',width : '20%'},
						{name : 'transDc',index : 'transDc',width : '20%'},
						{name : 'transCurrency',index : 'transCurrency',width : '20%'},
						{name : 'transAmount',index : 'transAmount',width : '20%'},
						{name : 'transStatus',index : 'transStatus',width : '20%'},
						{name : 'transDate',index : 'transDate',width : '20%'},
						{name : 'transTime',index : 'transTime',width : '20%'},
						{name : 'transComments',index : 'transComments',width : '20%'},
			],
			pager: "#balanceGridPager",
			toolbar: [true,"top"],
			/* caption: "${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle')}", */
			gridComplete: function() {
				var ids = $("#balanceGridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
					/* var dlt = "<a href='#' class='icon-edit m-r ' onclick='detail(\"#reqType\",\"#reqInnerNum\")'><em>${app:i18n('global.jsp.detail')}</em></a>"; */
					
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					/* dlt = dlt.replace(/#reqType/,reqType).replace(/#reqInnerNum/,reqInnerNum); */
					
					var id = ids[i];
					var rowData = $("#balanceGridTable").getRowData(id);
					
					//all = all + mod+dlt;
					all=all+mod;
					$("#balanceGridTable").jqGrid('setRowData',ids[i],{operation:all});
				};
				//判断是否有值
				/* if(!ids.length){
					$("div.warning span").html("未查找到数据！");
					$("div.warning").show();
				}; */
			}
		});
		
	});
	function setQueryCondition(){
		alert($("reqInnerNum"));
		// 设置查询参数
		$("#balanceGridTable").setPostDataItem("reqInnerNum", $("reqInnerNum"));
	}
	$("#search").click(function(){
		doSearch();
	});
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#balanceGridTable").jqGrid("setGridParam",{page:1});
		$("#balanceGridTable").jqGrid("setGridParam",{url:"query.action"}).trigger("reloadGrid");
	}
</script>