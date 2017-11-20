<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

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
		var searchUrl = "sysViolationRecordSearch.action";

		var exportUrl = "sysViolationRecordExport.action";

	$().ready(function() {
		
		$("#search").click(function(){
			 	// doSearch();
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
			$("#sysViolationRecordListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#sysViolationRecordListForm").submit();
		});		
		//加入ip异常
		$("#gridTable").gridUtil().simpleGrid({
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
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('sysViolationRecord.sysViolationRecordListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
			//id长度小于等于零时 提示请输入警告
				if(ids.length<=0){
/* 					$("div.warning span").html("没有记录"); 
					$("div.warning").show(); */
					}
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
	//在进行搜索的时候调不同的Action
	//IP异常
	function doSearch(){
		// 设置查询参数
		setQueryCondition();		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"sysViolationRecordSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数	
		$("#gridTable").setPostDataItem("violationType", $("#violationType").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());		
		$("#gridTable").setPostDataItem("cnlIntfCode", $("#cnlIntfCode").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("starTime", $("#starTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
	}	
</script>
<s:form id="sysViolationRecordListForm" method="post" action="sysViolationRecordList.action">
<div class="layout">
	<div class="block m-b" >
		<div class="block_title">
			<h3>${app:i18n('sysViolationRecord.sysViolationRecordListJsp.headTitle')}</h3>
		</div>
		<div class="block_container">
		<!-- 警告提示DIV -->
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
								<td width="30%"><select name="violationType" id="violationType" class="width_c"   style="width: 190px">
								<option value="01">IP异常</option></select></td>								
								<th width="14%">${app:i18n('sysViolationRecord.reqNum')}：</th>
								<td width="30%"><input name="reqNum" id="reqNum" class="width_c" /></td>								
								<th width="10%">${app:i18n('sysViolationRecord.cnlCustCode')}：</th>
								<td width="30%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>
							</tr>
							<tr>
								<th width="12%">${app:i18n('sysViolationRecord.cnlIntfCode')}：</th>
								<td width="30%"><input name="cnlIntfCode" id="cnlIntfCode" class="width_c" /></td>								
								<th width="14%">发生开始时间：</th>
								<td width="30%"><input class="Wdate" name="starTime"  id="starTime"  type="text"  onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" style="width: 190px" /></td>								
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
					<table id="gridTable">
					</table>
					
					<div id="gridPager"></div>
					<div id="gridToolbar" hidden="ture">
						<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					</div>					
				</div>
			</div>	 
		</div>		
	</div>	 
</div>
</s:form>
