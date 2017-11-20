<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div>
	



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


var flag1=0;
var flag2=0;
function resetFlag(){
	
	flag1=0;
	flag2=0;
}
function defaultStart(){
	if(flag1==0){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
		var s1 = date +" 00:00:00";
		$("#startTime").val(s1);
		flag1=1;
	}
}
function defaultEnd(){
	if(flag2==0){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
		 var s2 = date +" 23:59:59";
		$("#endTime").val(s2);
		flag2=1;
	}
}


function GetQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); return null; 
	}



	$().ready(function() {
		getTodayTime();
		var modifyUrl = "cnlReqTransModifyInfDataInquery.action";
		var deleteUrl = "cnlReqTransDeleteInfDataInquery.action";
		var createUrl = "cnlReqTransCreateInfDataInquery.action";
		//var searchUrl = "cnlReqTransSearchInfDataInquery.action";
		var exportUrl = "cnlReqTransExportInfDataInquery.action";
		//===========MSG=======
		var searchUrl_msg="cnlMsgSearchByMsgCodeInfDataInquery.action";
		
		$("#search").click(function(){
			
			
			
			
			var ok = true;
			
			$("div.warning").hide();
			//校验填入时间
			/* if($("#timeType").val()!="0"){
			
				if(($("#startTime").val()=="")||($("#startTime").val()==null)||($("#endTime").val()=="")||($("#endTime").val()==null)){
					$("div.warning span").html("请填入完整时间！");
					$("div.warning").show();
				}else{
					doSearch();
				}
			} */
			
			
			if(($("#timeType").val()=="0")&&(($("#startTime").val()=="")||($("#startTime").val()==null))&&(($("#endTime").val()=="")||($("#endTime").val()==null))){
			}else{
				if(($("#timeType").val()=="0")&&((($("#startTime").val()!="")||($("#startTime").val()!=null))||(($("#endTime").val()!="")||($("#endTime").val()!=null)))){
					$("div.warning span").html("请选择时间类型！");
					$("div.warning").show();
					ok = false;
				}else
					
					
				if((($("#startTime").val()=="")||($("#startTime").val()==null))&&((($("#endTime").val()!="")||($("#endTime").val()!=null))||($("#timeType").val()!="0"))){
					$("div.warning span").html("请填入开始时间！");
					$("div.warning").show();
					ok = false;
				}else
				if((($("#endTime").val()=="")||($("#endTime").val()==null))&&((($("#timeType").val()!="0")||($("#startTime").val()!=""))||($("#startTime").val()!=null))){
					$("div.warning span").html("请填入结束时间！");
					$("div.warning").show();
					ok = false;
				}
			}
			
			
			
			// 校验渠道订单号合发信息
			if($("#reqNum").val() != null && $("#reqNum").val() != ""){
				var zz = /^[a-zA-Z\d]*$/;
				if(zz.test($("#reqNum").val())){
				}else{
					$("div.warning span").html("请输入正确的渠道订单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			// 校验系统申请流水单号合法信息
			if($("#reqInnerNum").val() != null && $("#reqInnerNum").val() != ""){
				var zz =/^[a-zA-Z\d]*$/;
				if(zz.test($("#reqInnerNum").val())){
				}else{
					$("div.warning span").html("请输入正确的系统申请流水单号！");
					$("div.warning").show();
					ok = false;
				}
			}
			
			// 开始时间与结束时间间跨度小于等于一个月的校验
			// 开始时间与结束时间间跨度小于等于一个月的校验
			var createtime = $("#startTime").val();
			var endtime = $("#endTime").val();
			var timeType = $("#timeType").val();
			if(timeType == null || timeType == "" || createtime == null || createtime == "" || endtime == null || endtime == ""){
				$("div.warning").html("请输入必填信息!");
				$("div.warning").show();
				ok = false;
			}else{
				var	start=new Date(createtime.replace("-", "/").replace("-", "/")); 
			    var	end=new Date(endtime.replace("-", "/").replace("-", "/"));
			    
				var endStart = end.getTime() - start.getTime();
				var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
				var month =leaveTime / (30 * 24 * 3600 * 1000);
				
				if(start < end && month < 1){
					$("div.warning").hide();
				}else{
					$("div.warning").html("开始时间与结束时间间跨度小于等于30天!");
					$("div.warning").show();
					ok = false;
				}
			}
			
			
			if(ok){
				doSearch();
			}
			
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
			colNames:['ID', 
			        '${app:i18n('cnlReqTransInfDataInquery.reqNum')}',
					'${app:i18n('cnlReqTransInfDataInquery.reqInnerNum')}',
					'${app:i18n('cnlReqTransInfDataInquery.cnlCnlCode')}',
					'${app:i18n('cnlReqTransInfDataInquery.msgTime')}',
					'${app:i18n('cnlReqTransInfDataInquery.recieveTime')}',
					'${app:i18n('cnlReqTransInfDataInquery.handleTime')}', 
					'${app:i18n('cnlReqTransInfDataInquery.reqStatus')}', 
					'${app:i18n('cnlReqTransInfDataInquery.errMsg')}',
					'${app:i18n('cnlReqTransInfDataInquery.msgCode')}'],
			colModel:[
						{name : 'id',width : '10%', hidden: true,hidedlg:true},
						{name : 'reqNum',index : 'reqNum',width : '10%'},
						{name : 'reqInnerNum',index : 'reqInnerNum',width : '10%'},
						{name : 'cnlCnlCode',index : 'cnlCnlCode',width : '10%'},
						{name : 'msgTime',index : 'msgTime',width : '10%'},
						{name : 'recieveTime',index : 'recieveTime',width : '10%'},
						{name : 'handleTime',index : 'handleTime',width : '10%'}, 
						{name : 'reqStatus',index : 'reqStatus',width : '10%'}, 
						{name : 'errMsg',index : 'errMsg',width : '10%'},
						{name : 'msgCode',index :'msgCode',width : '10%', hidden: true,hidedlg:true	}				
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#searchUrl_msg?loadPageCache=true&msgCode=#msgCode\"'><em>${app:i18n('global.jsp.detail')}</em></a>";

					mod = mod.replace(/#searchUrl_msg/, searchUrl_msg).replace(/#msgCode/, ids[i]);
					
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
		
		var sname = GetQueryString("reqType"); 
		var reqType = decodeURIComponent(sname); 
		var searchurl1='cnlReqTransSearchInfDataInquery.action?reqType='+reqType;
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:searchurl1}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		
		$("#gridTable").setPostDataItem("timeType", $("#timeType").val());
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqInnerNum", $("#reqInnerNum").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("reqNum", $("#reqNum").val());
		$("#gridTable").setPostDataItem("reqStatus", $("#reqStatus").val());
	}
	
	
	//alert框
	function showAlertDialog(alertTitle, alertContent){
		
		$('#alertDialog').dialog('destroy');
	    $('#alertDialog').show();
	    $('#alertDialog').html(alertContent);
	    
	    $("#alertDialog").dialog({
	        modal: true,
	            opacity: 0.9,
	            width : 900,
	            height : 600,
	        title:alertTitle,
	        buttons: {
	        	'${app:i18n("global.jsp.close")}': function() {
	            	$('#alertDialog').dialog('close');
	            }
	        }
	    });

	}
	
	function alertDialog(){
		 var id = $("#gridTable").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
	     var rowData = $("#gridTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
	     var msgCode= rowData.msgCode; //获得制定列的值 （auditStatus 为colModel的name）
	     var cnlCnlCode=rowData.cnlCnlCode;
		 var sUrl='cnlMsgSearchByMsgCode.action?loadPageCache=true&msgCode='+msgCode+'&cnlCnlCode='+cnlCnlCode;
	     if(id!=null){
	    	$("div.warning").hide();
	     	$.get(sUrl,function(data){
	     		data=data.substring(0,data.length-2);
	    	 	showAlertDialog('报文内容', data);
	     	},'html');
	     }else{
	    	 $("div.warning span").html("请选择查询数据列！");
			 $("div.warning").show();
	     }
	}
	
	
	  
	
</script>

<div id="alertDialog"></div>

	

<s:form id="cnlReqTransListForm" method="post"
	action="cnlReqTransList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>
				<%
					int reqType=0;
					if(null==session.getAttribute("reqType"))
					{
						reqType=1;
						%>
						${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle1')}
						<%
					}else{
						
						String reqTypeStr=(String)session.getAttribute("reqType");
						reqType=Integer.parseInt(reqTypeStr);
						switch(reqType){
						case 1:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle1')}<%
							break;
						case 2:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle2')}<%
							break;
						case 3:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle3')}<%
							break;
						case 4:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle4')}<%
							break;
						case 5:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle5')}<%
							break;
						case 6:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle6')}<%
							break;
						case 7:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle7')}<%
							break;
						case 8:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.headTitle8')}<%
							break;
						}
					}
				%>
			
			
				
				</h3>
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
									<th><em>*</em>时间类型：</th>
									<td><select name="timeType" id="timeType" style="width:110px">
											<option value="">请选择</option>
											<option value="3">报文发送时间</option>
											<option value="1">接收时间</option>
											<option value="2">处理时间</option>
											
									</select></td>

									<th><em>*</em>开始时间：</th>
									<td><input style="width:136px" name="startTime" id="startTime" class="Wdate" value=""  readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"/> 
									</td>
									<th><em>*</em>结束时间：</th>
									<td>
									<input style="width:136px" name="endTime" id="endTime" class="Wdate" value="" readonly="readonly"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"/>
									</td>
										
									
									
								</tr>
								<tr>
								<th style="padding-left:10px">${app:i18n('cnlReqTransInfDataInquery.cnlCnlCode')}：</th>
									<td ><select name="cnlCnlCode" id="cnlCnlCode"style="width:110px">
											<option value="">请选择</option>
											<s:iterator value="cnlCnlCodeList" var="typelist">
												<option value="${typelist.key}">${typelist.value }</option>
											</s:iterator>
											
									</select></td>
								
								<th style="padding-left:20px" >${app:i18n('cnlReqTransInfDataInquery.reqStatus')}：</th>
									<td ><select name="reqStatus" id="reqStatus" style="width:138px">
											<option value="">请选择</option>
											<s:iterator value="reqStatusList" var="typelist">
												<option value="${typelist.key}">${typelist.value }</option>
											</s:iterator>
									</select></td>
										
									<th style="padding-left:20px">${app:i18n('cnlReqTransInfDataInquery.reqNum')}：</th>
									<td ><input name="reqNum" id="reqNum"style="width:128px"
										class="width_c" /></td>
									
									<th style="padding-left:20px">${app:i18n('cnlReqTransInfDataInquery.reqInnerNum')}：</th>
									<td ><input name="reqInnerNum" id="reqInnerNum"style="width:128px"
										class="width_c" /></td>
								
									
								</tr>
							</tbody>
						</table>
						<div class="btn_layout">
							<a name="search" id="search" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
							<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
							
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar"style="text-align: left">
						<a class="l-btn-plain l-btn m-l4"  onclick="alertDialog()"><span
							class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('global.jsp.detail')}</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
<input type="hidden" id="hidden" value="
<%
					int reqType=0;
					if(null==session.getAttribute("reqType"))
					{
						reqType=1;
						%>
						${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle1')}
						<%
					}else{
						
						String reqTypeStr=(String)session.getAttribute("reqType");
						reqType=Integer.parseInt(reqTypeStr);
						switch(reqType){
						case 1:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle1')}<%
							break;
						case 2:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle2')}<%
							break;
						case 3:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle3')}<%
							break;
						case 4:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle4')}<%
							break;
						case 5:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle5')}<%
							break;
						case 6:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle6')}<%
							break;
						case 7:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle7')}<%
							break;
						case 8:
							%>${app:i18n('cnlReqTrans.cnlReqTransListJsp.tableTitle8')}<%
							break;
						}
					}
				%>
			
			
">
<script type="text/javascript">
window.onload=function(){
	$(".ui-jqgrid-title").text($("#hidden").val());
	
	} 

</script>
