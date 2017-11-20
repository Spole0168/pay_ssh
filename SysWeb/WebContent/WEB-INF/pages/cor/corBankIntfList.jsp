<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
	    $("#starTime").val(s1);
	    $("#endTime").val(s2);
	}
</script> 
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
var dlgUserId ="#dialog-ajax-cnl-acnt";

	$().ready(function() {
 		
		var modifyUrl = "corBankIntfModify.action";
		var deleteUrl = "corBankIntfDelete.action";
		var createUrl = "corBankIntfCreate.action";
		var searchUrl = "corBankIntfSearch.action";
		var exportUrl = "corBankIntfExport.action";
		
		$("#search").click(function(){
			/* doSearch(); */
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
				if(times>0)
				{
					$("div.warning span").html("结束时间必须大于结束时间！");
					$("div.warning").show();
					return;
				}
				//判断开始时间和结束时间是否相差一个月
				if(days>30)
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
				$("div.warning").hide();
				/* alert("请选择一行修改"); */
				$("div.warning span").html("请选择一行修改");
				$("div.warning").show();
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#corBankIntfListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corBankIntfListForm").submit();
		});
		

		
		$("#modify").click( function() {
			 
			$("div.warning").hide();
			var gr = $("#gridTable").jqGrid('getGridParam','selrow');
			var rowData = $("#gridTable").jqGrid('getRowData',gr);
			var id = rowData.id;
			var bid = rowData.bid;
			//alert(id + ":" + bid);
			if(gr == null){
				/* alert("请选择一条记录"); */
				
				/* alert("请选择一行修改"); */
				$("div.warning span").html("请选择一行数据");
				$("div.warning").show();
				return false;
			}
			window.location = "corBankIntfModify.action?id="+ id +"&bid=" + bid +"&loadPageCache=true";
		});
		
		
		//备付金
		$("#acnt").click(function(){
			$("div.warning").hide();
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
		
			//根据id获取对应的custCode
			var bankInnerCode= $("#gridTable").getCell(id,"bankInnerCode");

			console.info(bankNum);

			if(id!=null){
				$("div.warning").hide();
				location.href="bankNum.action?bankInnerCode="+bankInnerCode;
			}else{
					$("div.warning").html("请选择一行数据！");
					$("div.warning").show();
			    	return false;
			}

			
		});
	
		
		
		
		<!--   -->	
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			shrinkToFit:false,
			colNames:['ID', 
			          'bid',
						'${app:i18n('corBankInfoDTO.bankInnerCode')}',
						'${app:i18n('corBankInfoDTO.bankName')}',
					//	'${app:i18n('corBankInfoDTO.bankBranchName')}',
						'${app:i18n('corBankInfoDTO.bankLevel')}',
						'${app:i18n('corBankInfoDTO.country')}',
						'${app:i18n('corBankInfoDTO.bankAddr')}',
						'${app:i18n('corBankInfoDTO.contractEffectDate')}',
						'${app:i18n('corBankInfoDTO.contractExpireDate')}',
						'${app:i18n('corBankInfoDTO.techSupportName')}',
						'${app:i18n('corBankInfoDTO.techSupportPhonenum')}',
						'${app:i18n('corBankInfoDTO.techSupportEmail')}',
						'${app:i18n('corBankInfoDTO.businessSupportName')}',
						'${app:i18n('corBankInfoDTO.businessSupportPhonenum')}',
						'${app:i18n('corBankInfoDTO.businessSupportEmail')}',
						'${app:i18n('corBankInfoDTO.bankNum')}',
						'${app:i18n('corBankInfoDTO.swiftCode')}',
						'${app:i18n('corBankInfoDTO.branchCode')}',
						'${app:i18n('corBankInfoDTO.ngsnCode')}',
						'${app:i18n('corBankInfoDTO.createTime')}',
						'${app:i18n('corBankInfoDTO.creator')}',
						'${app:i18n('corBankInfoDTO.desc')}',	
							],
			colModel:[
						{name : 'id', hidden: true},
						{name : 'bid', hidden: true},
						{name : 'bankInnerCode',index : 'bankInnerCode'},
						{name : 'bankName',index : 'bankName'},
				//		{name : 'bankBranchName',index : 'bankBranchName'},
						{name : 'bankLevel',index : 'bankLevel'},
						{name : 'country',index : 'country'},
						{name : 'bankAddr',index : 'bankAddr'},
						{name : 'contractEffectDate',index : 'contractEffectDate'},
						{name : 'contractExpireDate',index : 'contractExpireDate'},
						{name : 'techSupportName',index : 'techSupportName'},
						{name : 'techSupportPhonenum',index : 'techSupportPhonenum'},
						{name : 'techSupportEmail',index : 'techSupportEmail'},
						{name : 'businessSupportName',index : 'businessSupportName'},
						{name : 'businessSupportPhonenum',index : 'businessSupportPhonenum'},
						{name : 'businessSupportEmail',index : 'businessSupportEmail'},
						{name : 'bankNum',index : 'bankNum'},	
						{name : 'swiftCode',index : 'swiftCode'},		
						{name : 'branchCode',index : 'branchCode'},		
						{name : 'ngsnCode',index : 'ngsnCode'},		
						{name : 'createTime',index : 'createTime'},		
						{name : 'creator',index : 'creator'},	
						{name : 'desc',index : 'desc'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corBankIntf.corBankIntfListJsp.tableTitle')}",
			 gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				if(ids.length<=0){
/* 				$("div.warning span").html("没有记录"); 
				$("div.warning").show(); */
				}
				else{$("div.warning").hide();}
				
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id&bid=#bid\"'><em>${app:i18n('global.jsp.modify')}</em></a>";
					var id = ids[i];
					//根据id获取csiid
					var bid= $("#gridTable").getCell(id,"bid");
					
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]).replace(/#bid/, bid);
					
					var rowData = $("#gridTable").getRowData(id);

					all = all + mod;

					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}
			} 
		});

		$("#gridTable").gridUtil().customizeColumn();
		$("#gridToolbar").appendTo($("#t_gridTable"));
		//时间格式 
		var stime=$("#starTime").val().replace("+"," ");
		var etime=$("#endTime").val().replace("+"," ");
		$("#starTime").val(stime); 	
		$("#endTime").val(etime);
		


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
		$("#gridTable").jqGrid("setGridParam",{url:"corBankIntfSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("bankInnerCode", $("#bankInnerCode").val());
		//$("#gridTable").setPostDataItem("bankName", $("#bankName").find("option[value='"+$("#bankName").val()+"']").text());
		$("#gridTable").setPostDataItem("bankName", $("#bankName").val());
		//$("#gridTable").setPostDataItem("bankBranchName", $("#bankBranchName").val());
		$("#gridTable").setPostDataItem("bankLevel", $("#bankLevel").val());
		$("#gridTable").setPostDataItem("country", $("#country").val());
		$("#gridTable").setPostDataItem("bankAddr", $("#bankAddr").val());
		$("#gridTable").setPostDataItem("contractEffectDate", $("#contractEffectDate").val());
		$("#gridTable").setPostDataItem("contractExpireDate", $("#contractExpireDate").val());
		$("#gridTable").setPostDataItem("techSupportName", $("#techSupportName").val());
		$("#gridTable").setPostDataItem("techSupportPhonenum", $("#techSupportPhonenum").val());
		$("#gridTable").setPostDataItem("techSupportEmail", $("#techSupportEmail").val());
		$("#gridTable").setPostDataItem("businessSupportName", $("#businessSupportName").val());
		$("#gridTable").setPostDataItem("businessSupportPhonenum", $("#businessSupportPhonenum").val());
		$("#gridTable").setPostDataItem("businessSupportEmail", $("#businessSupportEmail").val());
		$("#gridTable").setPostDataItem("bankNum", $("#bankNum").val());
		$("#gridTable").setPostDataItem("swiftCode", $("#swiftCode").val());
		$("#gridTable").setPostDataItem("branchCode", $("#branchCode").val());
		$("#gridTable").setPostDataItem("ngsnCode", $("#ngsnCode").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("desc", $("#desc").val());
		$("#gridTable").setPostDataItem("starTime", $("#starTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());
		
	}
	function showAlertDialog1(alertTitle, alertContent){
		
		$('#alertDialog').dialog('destroy');
	    $('#alertDialog').show();
	    $('#alertDialog').html('<p>'+222+'</p>');		
	 
	
	    
	    $("#alertDialog").dialog({
	        resizable: false,
	        modal: true,
	        overlay: {
	            backgroundColor: '#000',
	            opacity: 0.9,
	            width:'400px'
	        },
	        title:alertTitle,
	        buttons: {
	        	'${app:i18n("global.jsp.ok")}': function() {
	            	$('#alertDialog').dialog('close');
	            }
	        }
	    });

	}
	
	
	function showAlertDialog(alertTitle, alertContent){
		$('#alertDialog').dialog('destroy');
	    $('#alertDialog').show();
	    $('#alertDialog').html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+ alertContent +'</p>');
	   
	  
	    $("#alertDialog").dialog({
	        resizable: false,
	        modal: true,
	        overlay: {
	            backgroundColor: '#000',
	            opacity: 0.9,
	            width:'400px'
	        },
	        title:alertTitle,
	        buttons: {
	        	'${app:i18n("global.jsp.ok")}': function() {
	            	$('#alertDialog').dialog('close');
	            }
	        }
	    });

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
	}
	
	function closeDialog(){
		$(dlgUserId).dialog('close');
		
	}
	
 
	
//查看
var diaDetail="#handleAuditDiv"
 
		
function alertDialog(){
	var id = $("#gridTable").jqGrid('getGridParam','selrow');
	var rowData = $("#gridTable").jqGrid("getRowData",id);
	$("div.warning").hide();
	if(id!=null){
		var url = "showBankInfoJSP.action?id="+id+"&time="+new Date().getTime();
		var title = "明细";
		$(diaDetail).dialog({width:800,height:330,modal:true});
		openDialog(diaDetail,title,url);
	}else{
	    	/* alert("请选择一条数据列！"); */
		/* alert("请选择一行修改"); */
		$("div.warning span").html("请选择一行数据");
		$("div.warning").show();
	    	return false;
	}
}
 
//弹出层		
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
    	'${app:i18n("global.jsp.ok")}': function(){
    		//$(diaDetail).dialog('close');
    		closeDialog();
        }
    }
	});
	$(dlgId).load(url);
	$(dlgId).dialog('open');
	}
	
function closeDialog(){
	$(diaDetail).dialog('close');
	
}

</script>

<div id="alertDialog"></div>
<s:form id="corBankIntfListForm" method="post" action="corBankIntfList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corBankIntf.corBankIntfListJsp.headTitle')}</h3>
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
								<th width="12%">${app:i18n('corBankInfoDTO.bankInnerCode')}：</th>
								<td width="18%">
								<select name="bankInnerCode" id="bankInnerCode"  class="width_c"   style="width: 190px">
										<option value="">请选择</option>
										<s:iterator  value="bankInnerCodeList"   var ="cor">
										<option value="${cor.bankInnerCode}">${cor.bankInnerCode}</option>
										</s:iterator>
								</select>
								</td>	
							<!-- 	<input name="bankInnerCode" id="bankInnerCode" class="width_c" /> -->
													
								<th width="14%">${app:i18n('corBankInfoDTO.bankName')}：</th>
								<td width="18%">
									<select name="bankName" id="bankName" class="width_c"  style="width: 190px" >
									<option value="">请选择</option>
									<s:iterator value="bankNameList" var="nameList">
										<option value="${nameList.key }">${nameList.value}</option>
									</s:iterator>
									</select>
								</td>
								<%-- <th width="10%">${app:i18n('corBankInfoDTO.bankBranchName')}：</th>
								<td width="18%"><input name="bankBranchName" id="bankBranchName" class="width_c" /></td>			 --%>			
						
								<th width="14%">${app:i18n('corBankInfoDTO.bankNum')}：</th>
								<td width="18%"><input name="bankNum" id="bankNum" class="width_c" /></td>
						</tr>
						<tr>
								<th width="12%">${app:i18n('corBankInfoDTO.bankLevel')}：</th>
								<td width="18%">
									<select nabankLevel" id="bankLevel" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<option value="01">总行</option>
										<option value="02">分行</option>
										<option value="03">支行</option>	
									</select>
								</td>								
								<th width="10%">${app:i18n('starTime')}：</th>
								<td width="18%"><input class="width_c" name="starTime"  id="starTime"  type="text"  
								onfocus="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})" /></td>
								<th width="12%">${app:i18n('endTime')}：</th>
								<td width="18%"><input class="width_c" name="endTime"  id="endTime"  type="text"  
								onFocus="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starTime\')}'})"></td>						
								<th width="12%"></th>
								<td width="18%"></td>			
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					
					</div>
					
					<div id="gridPager">
						<input id="message" type="hidden"
							value="<s:property value="message"/>">
					</div>
				</div>
			</div>
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					<a id="modify" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a> 
					<a id="detail" class="l-btn-plain l-btn m-l4" onclick="alertDialog()"><span class="l-btn-left"><span class="l-btn-text icon-view">${app:i18n('detail')}</span></span></a> 
					<a id="acnt" class="l-btn-plain l-btn m-14"><span class="l-btn-left">
					<span class="l-btn-text icon-search">${app:i18n('acnt')}</span></span></a>
					
				</div>
			</div>
			<div id="handleAuditDiv">
			</div>
		</div>
	</div>
</div>
<div id="dialog-ajax-cnl-acnt"></div>
</s:form>
