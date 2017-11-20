<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
 
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
	var dlgUserId ="#dialog-ajax-cnl-view";
	$().ready(function() {

		
		var modifyUrl = "cnlCnlCfgModify.action";
		var deleteUrl = "cnlCnlCfgDelete.action";
		var createUrl = "cnlCnlCfgCreate.action";
		var searchUrl = "cnlCnlCfgSearch.action";
		var exportUrl = "cnlCnlCfgExport.action";
		
	
		
		
		$("#search").click(function(){
			
			
			
			$("div.warning").hide();
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			
			if(startTime==""&&endTime==""){
				doSearch();
			}else{
				var	start=new Date(startTime.replace("-", "/").replace("-", "/")); 
			    var	end=new Date(endTime.replace("-", "/").replace("-", "/"));
			    
				var endStart = end.getTime() - start.getTime();
				var leaveTime = endStart % (12 * 30 * 24 * 3600 * 1000);
				var month =leaveTime / (30 * 24 * 3600 * 1000);
				//var times = startTime.getTime() - endTime.getTime();
				var days = Math.abs(parseInt(endStart/(1000*60*60*24)));
				if(days>29){
					$("div.warning").html("开始时间与结束时间间跨度小于等于30天!");
					$("div.warning").show();
					return false;
					
				}else{
					doSearch();
				}
			}
		});
		
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
			$("#cnlCommentListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCommentListForm").submit();
		});
		
		
		$("#update").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取csiid
			var csiid= $("#gridTable").getCell(id,"csiid");
			//根据id获取对应的custCode
			var clnCustCode= $("#gridTable").getCell(id,"cnlCustCode");
			
			if(id!=null){
				window.location=modifyUrl+"?loadPageCache=true&id="+id+"&csiid="+csiid+"&cnlCustCode="+clnCustCode;
			}else{
					$("div.warning").html("请选择一条信息修改！");
					$("div.warning").show();
			    	return false;
			}
  
		})
		
		
		
		$("#view").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			//根据id获取csiid
			var csiid= $("#gridTable").getCell(id,"id");
			//根据id获取对应的custCode
			var custCode= $("#gridTable").getCell(id,"custCode");
			
			if(id!=null){
				$("div.warning").hide();
				var url = "cnlCnlCfgView.action?id="+csiid+"&time="+new Date().getTime();
				var title = "渠道-渠道管理信息";
				$(dlgUserId).dialog({width:800,height:400,modal:true});
				openDialog(dlgUserId,title,url);
			}else{
					$("div.warning").html("请选择一条信息查看！");
					$("div.warning").show();
			    	return false;
			}
		})
		
		
		//判断是否存在loadPage
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			shrinkToFit:false,
			colNames:['ID', 
			          'csiid',
			          'cccid',
			          'custCode',
					'${app:i18n('cnlComment.cnlCnlCode')}',
					'${app:i18n('cnlCustCode')}',
					'${app:i18n('cnlComment.cnlSysName')}',
					'${app:i18n('cnlComment.cnlCnlCfg.cnlDesc')}',
					'${app:i18n('cnlComment.addr')}',
					'${app:i18n('cnlComment.country')}',
					'${app:i18n('cnlComment.cnlCnlCfg.contractEffectDate')}',
					'${app:i18n('cnlComment.contractExpireDate')}',
					'${app:i18n('cnlComment.accessKey')}',
					'${app:i18n('cnlComment.accessType')}',
					'${app:i18n('cnlComment.sendSms')}',
					'${app:i18n('cnlComment.sendEmail')}',
					'${app:i18n('cnlComment.techSupportName')}',
					'${app:i18n('cnlComment.techSupportPhonenum')}',
					'${app:i18n('cnlComment.techSupportEmail')}',
					'${app:i18n('cnlComment.businessSupportName')}',
					'${app:i18n('cnlComment.businessSupportPhonenum')}',
					'${app:i18n('cnlComment.businessSupportEmail')}',
					'${app:i18n('cnlComment.creator')}',
					'${app:i18n('cnlComment.createTime')}',
					
					'${app:i18n('cnlComment.comments')}'
					  ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'csiid',width : '10%', hidden: true}, 
						{name : 'cccid',width : '10%', hidden: true},
						{name : 'custCode',width : '10%', hidden: true},
						{name : 'cnlCnlCode',index : 'cnlCnlCode'},
						{name : 'cnlCustCode',index : 'cnlCnlCode'},
						{name : 'cnlSysName',index : 'cnlSysName'},
						{name : 'cnlDesc',index : 'cnlDesc'},
						{name : 'addr',index : 'addr'},
						{name : 'country',index : 'country'},
						{name : 'contractEffectDate',index : 'contractEffectDate'},
						{name : 'contractExpireDate',index : 'contractExpireDate'},
						{name : 'accessKey',index : 'accessKey'},
						{name : 'accessType',index : 'accessType'},
						{name : 'sendSms',index : 'sendSms'},
						{name : 'sendEmail',index : 'sendEmail'},
						{name : 'techSupportName',index : 'techSupportName'},
						{name : 'techSupportPhonenum',index : 'techSupportPhonenum'},
						{name : 'techSupportEmail',index : 'techSupportEmail'},
						{name : 'businessSupportName',index : 'businessSupportName'},
						{name : 'businessSupportPhonenum',index : 'businessSupportPhonenum'},
						{name : 'businessSupportEmail',index : 'businessSupportEmail'},
						{name : 'creator',index : 'creator'},
						{name : 'createTime',index : 'createTime'},
						{name : 'comments',index : 'comments'}
					   
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlCommentListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
			}
		});

		$("#gridTable").gridUtil().customizeColumn();
		$("#gridToolbar").appendTo($("#t_gridTable"));

		
		//时间格式 
		var stime=$("#startTime").val().replace("+"," ");
		var etime=$("#endTime").val().replace("+"," ");
		$("#startTime").val(stime); 	
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
		
		
		//提示成功失败编辑信息
		var  msg=$("#data").val();
		
		console.info(msg+" 提示信息--->"+"${data}");
		
		if(msg){
			
			$.growlUI('编辑信息！', msg);
			setSession();
		} 
		doSearch();
	});
	
	
	

	
	function doSearch(){
	 
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlCnlCfgSearch.action"}).trigger("reloadGrid");
		 
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("cnlSysName", $("#cnlSysName").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		
		$("#gridTable").setPostDataItem("startTime", $("#startTime").val());
		$("#gridTable").setPostDataItem("endTime", $("#endTime").val());

	}
	
 
	//弹出层
	function openDialog(dlgId, title, url){
		//$(dlgId).html("");
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
	}
	
	function closeDialog(){
		$(dlgUserId).dialog('close');
		
	}
	
	//获取到对应的session信息后在后台将其置空
	function setSession(){
		$.ajax({
         type: "post",
         url: "channel/cnlCnlCfgSetSession.action",
         dataType: "json",
         async:false,
         success: function(data){
         	
         	var  msg=data.data;//获取对应
         	$("#data").val(msg);
         	
         }
	   });
	}
</script>


<div id="alertDialog"></div>
<s:form id="cnlCommentListForm" method="post"
	action="cnlCnlCfgList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('cnlCommentListJsp.headTitle')}</h3>
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

									<th width="10%">${app:i18n('cnlComment.cnlCnlCode')}：</th>
									<td width="30%">
								
									
									<select  name="cnlCnlCode" id="cnlCnlCode" style="width:190px;"
										>
										<option  value="">请选择</option>
										<s:iterator  value="cnlCnlCfgList"   var ="cfg">
										<option value="${cfg.cnlCnlCode}">${cfg.cnlCnlCode}</option>
										</s:iterator>
									</select>
									
									</td>
										
									<th width="10%">${app:i18n('cnlComment.cnlSysName')}：</th>
									<td width="30%">
											
									<select  name="cnlSysName" id="cnlSysName" style="width:190px;"
										class="width_c">
										<option  value="">请选择</option>
										<s:iterator  value="cnlCnlCfgList"   var ="cfg">
										<option value="${cfg.cnlSysName}">${cfg.cnlSysName}</option>
										</s:iterator>
									</select>
									
										</td>
									 
								</tr>
								
								<tr>

									<th width="10%">${app:i18n('cnlCustCode')}：</th>
									<td width="30%">
							 
									
									
									<input name="cnlCustCode" id="cnlCustCode" 	class="width_c" />
									</td>
										
								</tr>
								<tr>
									<th width="10%">${app:i18n('cnlComment.startTime')}：</th>
									<td width="30%">
									 <input class="width_c" name="startTime"  id="startTime"  type="text"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})">
								 	</td>
								 	
								 	<th width="10%">${app:i18n('cnlComment.endTime')}：</th>
									<td width="30%">
									 <input class="width_c" name="endTime"  id="endTime"  type="text"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})">
									</td>
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
							
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager">
						<input id="data" type="hidden"
							value="<s:property value="data"/>">
					</div>
					<div id="gridToolbar">
						<a id="create" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>
							
						<a id="update" class="l-btn-plain l-btn m-14">
						<span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a>	
						
						
						<a id="view" class="l-btn-plain l-btn m-14"><span class="l-btn-left">
						<span class="l-btn-text icon-view">${app:i18n('global.jsp.view')}</span></span></a>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-ajax-cnl-view"></div>
</s:form>



