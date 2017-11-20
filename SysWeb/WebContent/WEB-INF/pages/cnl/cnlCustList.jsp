<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="js/My97DatePicker-4.7PR2/WdatePicker.js"></script>
<!-- 包含简单表格的JavaScript -->

<div id="container" class="layout block m-b">
<s:form id="cnlCustListForm" method="post" action="cnlCustList.action">
<div class="layout" style="x-index:997;">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('custPersonal.cnlCustListJsp.headTitle')}</h3>
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
								<th width="10%">${app:i18n('custPersonal.cnlCustCode')}：</th>
								<td width="20%"><input name="cnlCustCode" width="150px" id="cnlCustCode" class="width_c" maxlength="32" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"    
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\w\.\/]/ig,''))"/></td>
								
								<th width="10%">${app:i18n('custPersonal.cnlCustType')}：</th>
								<td width="20%">
									<select name="cnlCustType" id="cnlCustType" style="width:100%;">
										<option value="01">个人客户</option>
									</select>
								</td>
								
								<th width="10%">${app:i18n('custPersonal.cnlSysName')}：</th>
								<td width="30%">
									<select name="cnlSysName" style="width:67%;" id="cnlSysName">
										<option value="">请选择</option>
										<s:iterator value="cfgList" var="cfg">
											<option value="${cfg.cnlSysName }">${cfg.cnlSysName }</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th width="10%">${app:i18n('custPersonal.custStatus')}：</th>
								<td width="20%">
									<select name="custStatus" id="custStatus"  style="width:100%;">
										<option value="">请选择</option>
										<s:iterator value="cnlCustStatusList" var="cnlCustStatus">
											<option value="${cnlCustStatus.key }">${cnlCustStatus.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="10%">${app:i18n('custPersonal.certType')}：</th>
								<td width="20%">
									<select name="certType" id="certType"  style="width:100%;">
										<option value="">请选择</option>
										<s:iterator value="certTypeList" var="certType">
											<option value="${certType.key }">${certType.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="10%">${app:i18n('custPersonal.certNum')}：</th>
								<td width="30%"><input name="certNum" id="certNum" class="width_c" maxlength="20" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"   
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\w\.\/]/ig,''))"/></td>
							</tr>
							<tr>
								<th width="10%">${app:i18n('custPersonal.realNamelevel')}：</th>
								<td width="20%">
									<select name="realNamelevel" id="realNamelevel"  style="width:100%;"> 
										<option value="">请选择</option>
										<s:iterator value="realNameLevelList" var="realNameLevel">
											<option value="${realNameLevel.key }">${realNameLevel.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="10%">${app:i18n('custPersonal.createStartTime')}：</th>
								<td width="20%"><input name="createStartTime" id="createStartTime" class="Wdate" style="width:100%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createEndTime\')}'})"/></td>
								
								<th width="10%">${app:i18n('custPersonal.createEndTime')}：</th>
								<td width="30%"><input name="createEndTime" id="createEndTime" class="Wdate" style="width:67%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createStartTime\')}'})"/></td>
							</tr>
							
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
				<div id="gridToolbar">
					<a id="searchinfo" class="l-btn-plain l-btn m-l4"><span
						class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('custPersonal.searchinfo')}</span></span></a>
				</div>
			</div>
		</div>	
	</div>
</div>
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {
		$(function(){
			getTodayTime();
		})
		function getTodayTime(){
			var d = new Date();
		    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
		    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
		    var s1 = date +" 00:00:00";
		    var s2 = date +" 23:59:59";
		    $("#createStartTime").val(s1);
		    $("#createEndTime").val(s2);
		}
		
		var modifyUrl = "cnlCustModify.action";
		var deleteUrl = "cnlCustDelete.action";
		var createUrl = "cnlCustCreate.action";
		var searchUrl = "cnlCustSearch.action";
		var exportUrl = "cnlCustExport.action";
		$("#dialog-ajax-custPerosnal-info").hide();
		var gridVar = "#gridTable";
		$("#search").click(function(){
			$(".warning").hide();
			//var nowtime = new Date();  
			var createtime = $("#createStartTime").val();
			var start = null;
			if(createtime != null && createtime != ""){
				start=new Date(createtime.replace("-", "/").replace("-", "/")); 
			}
			var endtime = $("#createEndTime").val();
			var end = null;
			if(endtime != null && endtime != ""){
		    	end=new Date(endtime.replace("-", "/").replace("-", "/")); 
			}    
			var certType = $("#certType").val();
			var certNum = $("#certNum").val();
			if(certType == "" && certNum != null && certNum != ""){
				$(".warning").html("请选择证件类型!");
				$(".warning").show();
			}else{
				/* var StartAndCurr = start.getTime() - nowtime.getTime();
				var thireMonth = StartAndCurr / (90 * 24 * 3600 * 1000);
				alert(thireMonth); 
				if(thireMonth > 1){
					$(".warning").html("开始时间落在90天内!");
					$(".warning").show();
				}else */
				if(start != null && end != null){
					var endStart = end.getTime() - start.getTime();
					var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
					var month = leaveTime / (30 * 24 * 3600 * 1000);
					if(start < end ){
						if(month <= 1){
							$(".warning").hide();
							doSearch();
						}else{
							$(".warning").html("开始时间与结束时间间跨度小于等于30天!");
							$(".warning").show();
						}
					}else if(start > end){
						$(".warning").html("开始时间不能大于结束时间!");
						$(".warning").show();
					}
				}else{
					doSearch();
				}
			}
		});

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
			$(".warning").hide();
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
			$("#cnlCustListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCustListForm").submit();
		});
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			shrinkToFit:false,
			colNames:['${app:i18n('custPersonal.id')}', 
			        '${app:i18n('custPersonal.cnlSysName')}',
					'${app:i18n('custPersonal.cnlCustCode')}',
					'${app:i18n('custPersonal.cnlCustType')}',
					'${app:i18n('custPersonal.custStatus')}',
					'${app:i18n('custPersonal.localName')}',
					'${app:i18n('custPersonal.firstName')}',
					'${app:i18n('custPersonal.lastName')}',
					'${app:i18n('custPersonal.country')}',
					'${app:i18n('custPersonal.certType')}',
					'${app:i18n('custPersonal.certNum')}',
					'${app:i18n('custPersonal.certExpireDat')}',
					'${app:i18n('custPersonal.createTime')}',
					'${app:i18n('custPersonal.creator')}',
					'${app:i18n('custPersonal.realNamelevel')}',
					'${app:i18n('custPersonal.custLevel')}',
					'${app:i18n('custPersonal.phonenum')}'],
			colModel:[
						{name : 'id', hidden: true},
						{name : 'cnlSysName',index : 'cnlSysName',width:'200px'},
						{name : 'cnlCustCode',index : 'cnlCustCode'},
						{name : 'cnlCustType',index : 'cnlCustType'},
						{name : 'custStatus',index : 'custStatus'},
						{name : 'localName',index : 'localName'},
						{name : 'firstName',index : 'firstName'},
						{name : 'lastName',index : 'lastName'},
						{name : 'country',index : 'country'},
						{name : 'certType',index : 'certType'},
						{name : 'certNum',index : 'certNum'},
						{name : 'certExpireDateString',index : 'certExpireDateString'},
						{name : 'createTime',index : 'createTime'},
						{name : 'creator',index : 'creator'},
						{name : 'realNamelevel',index : 'realNamelevel'},
						{name : 'custLevel',index : 'custLevel'},
						{name : 'phonenum',index : 'phonenum'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('custPersonal.cnlCustListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var id = ids[i];
					var all = "";
					//var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='modify(\"" + id + "\")'><em>${app:i18n('global.jsp.searchinfo')}</em></a>";
					
					mod = mod.replace(/#id/, ids[i]);
					//$(gridVar).jqGrid('setRowData',id,{operation:modifyButton});
					var rowData = $("#gridTable").getRowData(id);

					all = all + mod;

					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}
			}
		});
		
		
		$("#searchinfo").click(function(){
			$(".warning").hide();
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				modify(id);
			}else{
		    	$(".warning").html("请选择一条需要查看的数据！");
		    	$(".warning").show();
		    	return false;
			}
		})

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
	
	var dlgEditId = "#dialog-ajax-custPerosnal-info";
	function modify(id){
		var url = "findCustInfo.action?id="+id+"&time="+new Date().getTime();
		var title = "查看用户信息";
		$(dlgEditId).dialog({width:800,height:420,modal:true});
		openDialog(dlgEditId,title,url);
	}
	function refreshGrid(){
		$("#gridTable").trigger("reloadGrid");
	}
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:[400,100],
			modal:true,
			useSlide:true,
			resizable: false,
			title:title,
			buttons: {
	        	'${app:i18n("global.jsp.ok")}': function() {
	            	$(dlgEditId).dialog('close');
	            }
	        }
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
		$(dlgId).dialog({
			   close: function(event, ui) { 			   
			  		$(dlgId).html(" ");			   
	  			}  
		});
	}
	function closeDialog(){
		$(dlgEditId).dialog('close');
	}
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlCustSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("cnlSysName", $("#cnlSysName").val());
		$("#gridTable").setPostDataItem("cnlCustType", $("#cnlCustType").val());
		$("#gridTable").setPostDataItem("custStatus", $("#custStatus").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("realNamelevel", $("#realNamelevel").val());
		$("#gridTable").setPostDataItem("createStartTime", $("#createStartTime").val());
		$("#gridTable").setPostDataItem("createEndTime", $("#createEndTime").val()); 
	}
</script>
<div class="fieldset_container" id="dialog-ajax-custPerosnal-info"></div>
</s:form>
</div>
