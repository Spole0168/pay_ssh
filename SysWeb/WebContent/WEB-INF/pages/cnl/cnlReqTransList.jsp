<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="container" class="layout block m-b">

<s:form id="cnlReqTransListForm" method="post" action="cnlReqTransList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle.cyy')}</h3>
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
								<th width="20%">${app:i18n('cnlReqTrans.reqInnerNum.cyy')}：</th>
								<td width="20%"><input name="reqInnerNum" id="reqInnerNum" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.reqType')}：</th>
								<td width="20%">
									<!-- <input name="reqType" id="reqType" class="width_c" /> -->
									<select name="reqType" id="reqType" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="reqTypes" var="reqTy">
											<option value="${reqTy.key }">${reqTy.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="20%">${app:i18n('cnlReqTrans.reqStatus.cyy')}：</th>
								<td width="20%">
									<!-- <input name="reqStatus" id="reqStatus" class="width_c" /> -->
									<select name="reqStatus" id="reqStatus" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="reqStatuss" var="reqSta">
											<option value="${reqSta.key }">${reqSta.value }</option>
										</s:iterator>
									</select>
								</td>
								
							</tr>
							<tr>	
								<th width="20%"><em>*</em>${app:i18n('cnlReqTrans.timeType')}：</th>
								<td width="20%">
									<!-- <input name="timeType" id="timeType" class="width_c" /> -->
									<select name="timeType" id="timeType" class="width_c" style="width: 190px">
										<option value="1" selected>报文时间</option>
										<option value="2">报文接收时间</option>
										<option value="3">报文处理时间</option>
									</select>
								</td>
							
								<th width="20%"><em>*</em>${app:i18n('cnlReqTrans.startTime')}：</th>
								<td width="20%">
									<!-- <input name="startTime" id="startTime" class="width_c" /> -->
									<input name="startTime" id="startTime" class="Wdate" value="${cnlReqTrans.startTime}"  readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" style="width: 190px"/> 
								</td>
							
								<th width="20%"><em>*</em>${app:i18n('cnlReqTrans.endTime')}：</th>
								<td width="20%">
									<!-- <input name="endTime" id="endTime" class="width_c" /> -->
									<input name="endTime" id="endTime" class="Wdate" value="${cnlReqTrans.endTime}" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})" style="width: 190px"/>
								</td>
							</tr>
							<tr>	
								<th width="20%">${app:i18n('cnlReqTrans.cnlCnlCode.cyy')}：</th>
								<!-- <td width="20%"><input name="cnlCnlCode" id="cnlCnlCode" class="width_c" /></td> -->
								<td width="20%">
									<!-- <input name="reqStatus" id="reqStatus" class="width_c" /> -->
									<select name="cnlCnlCode" id="cnlCnlCode" class="width_c" style="width: 190px">
										<s:iterator value="cnlCnlCodes" var="ccd">
											<option value="${ccd.key }">${ccd.value }</option>
										</s:iterator>
									</select>
								</td>
								<%-- 
								<th width="20%">${app:i18n('cnlReqTrans.reqNum')}：</th>
								<td width="20%"><input name="reqNum" id="reqNum" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.reqBatch')}：</th>
								<td width="20%"><input name="reqBatch" id="reqBatch" class="width_c" /></td>

								


								<th width="20%">${app:i18n('cnlReqTrans.timeZone')}：</th>
								<td width="20%"><input name="timeZone" id="timeZone" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.msgTime')}：</th>
								<td width="20%"><input name="msgTime" id="msgTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.recieveTime')}：</th>
								<td width="20%"><input name="recieveTime" id="recieveTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.handleTime')}：</th>
								<td width="20%"><input name="handleTime" id="handleTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.msgCode')}：</th>
								<td width="20%"><input name="msgCode" id="msgCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.errCode')}：</th>
								<td width="20%"><input name="errCode" id="errCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.errMsg')}：</th>
								<td width="20%"><input name="errMsg" id="errMsg" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.isValid')}：</th>
								<td width="20%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.createTime')}：</th>
								<td width="20%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.updateTime')}：</th>
								<td width="20%"><input name="updateTime" id="updateTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.creator')}：</th>
								<td width="20%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlReqTrans.updator')}：</th>
								<td width="20%"><input name="updator" id="updator" class="width_c" /></td> 
								--%>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						<%-- <a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>  --%>
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="lookDetail" class="l-btn-plain l-btn m-14"><span class="l-btn-right">
						<span class="l-btn-text icon-view">${app:i18n('cnlTransTrace.lookDetail')}</span></span></a>
						
					<%-- <a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> --%>
				</div> 
			</div>
		</div>
	</div>
</div>
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
	    $("#startTime").val(s1);
	    $("#endTime").val(s2);
	}
</script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	//判断值是否为空
	function isNotNull(d){
		return d!=null&&d!="";
	};
	
	$().ready(function() {

		var modifyUrl = "cnlReqTransModify.action";
		var deleteUrl = "cnlReqTransDelete.action";
		var createUrl = "cnlReqTransCreate.action";
		var searchUrl = "cnlReqTransSearch.action";
		var exportUrl = "cnlReqTransExport.action";
		var detailUrl = "cnlReqTransDetail.action";
		
		
		$("#search").click(function(){
			$("div.warning").hide();
			var startTime=$("#startTime").val();
			var start =isNotNull(startTime);
			var endTime=$("#endTime").val(); 
			var end=isNotNull(endTime);
			//进行时间是否为空的判定
			if(start&&end){
				//进行时间差是否大于一个月的判定
				startTime = startTime.replace(/-/g,'/');
				endTime = endTime.replace(/-/g,'/');
				startTime = new Date(startTime);
				endTime = new Date(endTime);
				var times = startTime.getTime() - endTime.getTime();
				var days = Math.abs(parseInt(times/(1000*60*60*24)));
				if(times>=0){
					$("div.warning span").html("入账结束时间必须大于入账开始时间！");
					$("div.warning").show();
					return;
				};
				//判断开始时间和结束时间是否相差一个月
				if(days>30){
					$("div.warning span").html("开始时间与结束时间相差不能超过一个月！");
					$("div.warning").show();
					return;
				}else{
					doSearch();
				}
			}else{
				$("div.warning span").html("请输入时间！");
				$("div.warning").show();
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
			var rowData = $("#gridTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
		    var reqType= rowData.reqType; //获得制定列的值 （auditStatus 为colModel的name）
		    var reqInnerNum=rowData.reqInnerNum;
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			//调用明细方法
			detail(reqType,reqInnerNum);
		});
		
		$("#reset").click(function(){
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
			$("#cnlReqTransListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlReqTransListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			rowNum:50,
			rowList:[50,100,200],
			colNames:['ID', 
			        '${app:i18n('cnlReqTrans.reqInnerNum.cyy')}',
			        '${app:i18n('cnlReqTrans.reqInnerNum.cyy')}',
			        //'${app:i18n('cnlReqTrans.cnlCustCode')}',
			        //'${app:i18n('cnlReqTrans.localName')}',
					'${app:i18n('cnlReqTrans.reqType')}',
					'${app:i18n('cnlReqTrans.reqStatus.cyy')}',
					'${app:i18n('cnlReqTrans.msgTime.cyy')}',
					'${app:i18n('cnlReqTrans.recieveTime.cyy')}',
					'${app:i18n('cnlReqTrans.handleTime.cyy')}',
					
					
					/**
					'${app:i18n('cnlReqTrans.bankAccepteTime')}',
					'${app:i18n('cnlReqTrans.bankReqTransTime')}',
					'${app:i18n('cnlReqTrans.bankTransNum')}',
					'${app:i18n('cnlReqTrans.isinGl')}',
					'${app:i18n('cnlReqTrans.transTime')}',
					'${app:i18n('cnlReqTrans.bankReturnTime')}',
					'${app:i18n('cnlReqTrans.isPrinted')}',
					'${app:i18n('cnlReqTrans.printedTime')}',
					'${app:i18n('cnlReqTrans.stlNum')}',
					'${app:i18n('cnlReqTrans.reqInnerNum.cyy')}',
					'${app:i18n('cnlReqTrans.localName')}',
					'${app:i18n('cnlReqTrans.cnlCustCode')}',
					'${app:i18n('cnlReqTrans.reqNum')}',
					'${app:i18n('cnlReqTrans.reqNum')}',
					'${app:i18n('cnlReqTrans.reqBatch')}',
					'${app:i18n('cnlReqTrans.timeZone')}',
					'${app:i18n('cnlReqTrans.msgCode')}',
					'${app:i18n('cnlReqTrans.errCode')}',
					'${app:i18n('cnlReqTrans.errMsg')}',
					'${app:i18n('cnlReqTrans.isValid')}',
					'${app:i18n('cnlReqTrans.createTime')}',
					'${app:i18n('cnlReqTrans.updateTime')}',
					'${app:i18n('cnlReqTrans.creator')}',
					'${app:i18n('cnlReqTrans.updator')}',
					*/
					 /*   '${app:i18n('global.jsp.operation')}' */ 
			],
			colModel:[
						{name : 'id',width : '20%', hidden: true},
						{name : 'reqInnerNum',index : 'reqInnerNum',width : '20%',hidden:true},
						{name : 'reqNum',index : 'reqNum',width : '20%'},
						//{name : 'cnlCustCode',index : 'cnlCustCode',width : '20%'},
						//{name : 'localName',index : 'localName',width : '20%'},
						{name : 'reqType',index : 'reqType',width : '20%'},
						{name : 'reqStatus',index : 'reqStatus',width : '20%'},
						{name : 'msgTime',index : 'msgTime',width : '20%'},
						{name : 'recieveTime',index : 'recieveTime',width : '20%'},
						{name : 'recieveTime',index : 'recieveTime',width : '20%'},
						
						
						/**
						{name : 'bankAccepteTime',index : 'bankAccepteTime',width : '20%'},
						{name : 'bankReqTransTime',index : 'bankReqTransTime',width : '20%'},
						{name : 'bankTransNum',index : 'bankTransNum',width : '20%'},
						{name : 'isinGl',index : 'bankReqTransTime',width : '20%'},
						{name : 'transTime',index : 'transTime',width : '20%'},
						{name : 'bankReturnTime',index : 'bankReturnTime',width : '20%'},
						{name : 'isPrinted',index : 'isPrinted',width : '20%'},
						{name : 'printedTime',index : 'printedTime',width : '20%'},
						{name : 'stlNum',index : 'stlNum',width : '20%'},
						{name : 'reqNum',index : 'reqNum',width : '20%'},
						{name : 'localName',index : 'localName',width : '20%'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '20%'},
						{name : 'reqInnerNum',index : 'reqInnerNum',width : '20%'},
						{name : 'reqNum',index : 'reqNum',width : '20%'},
						{name : 'reqBatch',index : 'reqBatch',width : '20%'},
						{name : 'timeZone',index : 'timeZone',width : '20%'},
						{name : 'msgCode',index : 'msgCode',width : '20%'},
						{name : 'errCode',index : 'errCode',width : '20%'},
						{name : 'errMsg',index : 'errMsg',width : '20%'},
						{name : 'isValid',index : 'isValid',width : '20%'},
						{name : 'createTime',index : 'createTime',width : '20%'},
						{name : 'updateTime',index : 'updateTime',width : '20%'},
						{name : 'creator',index : 'creator',width : '20%'},
						{name : 'updator',index : 'updator',width : '20%'},
						*/
					    /* {name:  'operation',width:'20%',align:'center', search:false,sortable:false,editable:true,title:false}, */
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
					/* var dlt = "<a href='#' class='icon-edit m-r ' onclick='detail(\"#reqType\",\"#reqInnerNum\")'><em>${app:i18n('global.jsp.detail')}</em></a>"; */
					
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					/* dlt = dlt.replace(/#reqType/,reqType).replace(/#reqInnerNum/,reqInnerNum); */
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);
					
					//all = all + mod+dlt;
					all=all+mod;
					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				};
				//判断是否有值
				/* if(!ids.length){
					$("div.warning span").html("未查找到数据！");
					$("div.warning").show();
				}; */
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
		$("#gridTable").jqGrid("setGridParam",{url:"cnlReqTransSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("reqType", $("#reqType option:selected").val());
		$("#gridTable").setPostDataItem("reqStatus", $("#reqStatus option:selected").val());
		$("#gridTable").setPostDataItem("timeType", $("#timeType option:selected").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		
		/* 
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqBatch", $("#reqBatch").val());
		$("#gridTable").setPostDataItem("timeZone", $("#timeZone").val());
		$("#gridTable").setPostDataItem("msgTime", $("#msgTime").val());
		$("#gridTable").setPostDataItem("recieveTime", $("#recieveTime").val());
		$("#gridTable").setPostDataItem("handleTime", $("#handleTime").val());
		$("#gridTable").setPostDataItem("msgCode", $("#msgCode").val());
		$("#gridTable").setPostDataItem("errCode", $("#errCode").val());
		$("#gridTable").setPostDataItem("errMsg", $("#errMsg").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
 		*/
	}
	var diaDetail="#dialog-ajax-detail";
	//弹出明细框
	function detail(reqType,reqInnerNum){
		var url = "cnlReqTransDetail.action?reqType="+reqType+"&reqInnerNum="+reqInnerNum;
		var title = "明细";
		$(diaDetail).dialog({width:800,height:500,modal:true});
		openDialog(diaDetail,title,url);
	}
	
/* 	function openDialog(dia, title, url){
		$(dia).html("");
		//设置显示框
		$(dia).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title,
			close: function(event, ui) { 			   
		  		$(dia).html(" ");		 
  			},
			buttons: {
	        	'${app:i18n("global.jsp.ok")}': function(){
	        		//$(diaDetail).dialog('close');
	        		closeDialog();
	            }
	        }
		});
		//发送ajax数据
		$.post(url,{"reqType":reqType,"reqInnerNum":reqInnerNum},function(data){
			data=data.replace("[]","");
			$(diaDetail).html(data).dialog('open');
		},"text"); 
		$(dia).load(url);
		$(dia).dialog('open');
		$(dia).dialog({
		    
		});
		
		function closeDialog(){
			$(diaDetail).dialog('close');
		}
	}  */
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			title:title,
			buttons: {
	        	'${app:i18n("global.jsp.ok")}': function(){
	        		//$(diaDetail).dialog('close');
	        		closeDialog();
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
		$("#dialog-ajax-detail").dialog('close');
	}
</script>
<div id="dialog-ajax-detail" class="fieldset_container"></div>
</s:form>
</div>