<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	var  dlgUserId="#dialog-ajax-sysnamelist-view";
 
	$().ready(function() {
		doSearch();
		var modifyUrl = "sysNamelistModify.action";
		var deleteUrl = "sysNamelistDelete.action";
		var createUrl = "sysNamelistCreate.action";
		var searchUrl = "sysNamelistSearch.action";
		var exportUrl = "sysNamelistExport.action";
		var reviewUrl="sysNamelistReview.action";
		
		
 
		
		$("#search").click(function(){
			 
			doSearch();
			 
		});
		
		
		

		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
		});

		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		$("#create").click(function(){
			window.location.href=createUrl;
		});
		
		//修改
		$("#modify").click(function(){
			
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
		 	
			var status= $("#gridTable").getCell(id,"status");
			
			if(id!=null){
				
				if("已审核"==status)
				{
					$("div.warning").html("只能修改状态为待审核或拒绝的记录！");
					$("div.warning").show();
				}else{
					window.location=modifyUrl+"?loadPageCache=true&id="+id+"";
				}
				
			}else{
				
				$("div.warning").html("请选择一条数据修改！");
				
				$("div.warning").show();
				
		    	return false;
				
			}
			
		})
		 
		$("#review").click(function(){
			//获取选中行的id，即数据库id
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
		 
			
			if(id!=null){
				
				var status= $("#gridTable").getCell(id,"status");
		 		if(status!="待审核"){
		 			
		 			$("div.warning").html("只能审核待审核的记录！");
					
					$("div.warning").show();
					
		 			return false;
		 		}else{
					var url = "sysNamelistReview.action?id="+id+"&time="+new Date().getTime();
					var title = "黑名单--审批";
					$(dlgUserId).dialog({width:400,height:300,modal:true});
					openDialog(dlgUserId,title,url);
		 		}
			}else{
					$("div.warning").html("请选择一条未处理状态的数据列！");
					
					$("div.warning").show();
			    	return false;
			}
			 
		//	openDialog(dlgUserId,title,url);
			
		})
		
		
		$("#delete").click( function() {
			var id= $('#gridTable').jqGrid('getGridParam','selrow')
			 
			$("div.warning").hide();
			if (id==null||id=="") {
				$("div.warning").html("请选择一行删除！");
				
				$("div.warning").show();
				
				return;
			}else{
				$.boxUtil.confirm(
						'请确认是否删除信息？', 
						null, 
						function(){

							$("#sysNamelistListForm").attr("action",deleteUrl+"?id="+id);
							$("#sysNamelistListForm").submit();
						}, 
						function(){
							//return false;
						});
				}
					return false;
				
			
			});
		 
	 
		
		$("#gridTable").gridUtil().simpleGrid({
 
			url: "",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect: false,
			colNames:['ID', 
					'${app:i18n('sysNamelist.nlType')}',//黑名单类型
					'${app:i18n('sysNamelist.nlId')}',//黑名单值
					'${app:i18n('sysNamelist.status')}',
					'${app:i18n('sysNamelist.reviewer')}',
					'${app:i18n('sysNamelist.reviewTime')}',
					'${app:i18n('sysNamelist.updateTime')}',
					'${app:i18n('sysNamelist.updator')}',
					'${app:i18n('sysNamelist.nlDesc')}',
					   /* '${app:i18n('global.jsp.operation')}' */ ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'nlType',index : 'nlType',width : '10%'},//黑名单类型
						{name : 'nlId',index : 'nlId',width : '10%'},//黑名单值
						{name : 'status',index : 'status',width : '10%'},
						{name : 'reviewer',index : 'reviewer',width : '10%'},
						{name : 'reviewTime',index : 'reviewTime',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
						{name : 'nlDesc',index : 'nlDesc',width : '10%'},
					   /*  {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
 */			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('sysNamelist.sysNamelistListJsp.tableTitle')}",
			gridComplete: function() {
				
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
		$("#gridTable").jqGrid("setGridParam",{url:"sysNamelistSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("nlType", $("#nlType").val());
		$("#gridTable").setPostDataItem("nlId", $("#nlId").val());
		$("#gridTable").setPostDataItem("nlDesc", $("#nlDesc").val());
		$("#gridTable").setPostDataItem("accessType", $("#accessType").val());
		$("#gridTable").setPostDataItem("status", $("#status").val());
		$("#gridTable").setPostDataItem("reviewer", $("#reviewer").val());
		$("#gridTable").setPostDataItem("reviewTime", $("#reviewTime").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());

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
		$(dlgUserId).dialog('close');//关闭
	}
</script>

<s:form id="sysNamelistListForm" method="post" action="sysNamelistList">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('sysNamelist.sysNamelistListJsp.headTitle')}</h3>
		</div>
		
		

		<div class="block_container">
		<div class="warning"  style="display: none">
					<span> </span>
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

								<th width="10%">${app:i18n('sysNamelist.nlId')}：</th>
								<td width="20%"><input name="nlId" id="nlId" class="width_c" /></td>
						
								<th width="10%">${app:i18n('sysNamelist.nlType')}：</th>
								<td width="20%">	
								
								<select name="sysNamelist.nlType" id="nlType" class="width_c"  >
										<option value="">请选择</option>
										<s:iterator  value="nlTypeList" var="nlType">
											<option value="${nlType.key}">${nlType.value}</option>
										</s:iterator>
								</select></td>
					 
							
								
								<th width="10%">${app:i18n('sysNamelist.status')}：</th>
								<td width="20%">	
								
								<select name="sysNamelist.status" id="status" class="width_c"  >
										<option value="">请选择</option>
										<s:iterator  value="statusList" var="s">
											<option value="${s.key}">${s.value}</option>
										</s:iterator>
								</select></td>
					 </tr>
						
					</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
					</div>
				</div>
			</div>
				<div class="layout ">
 		
			<div class="block m-b">
			<div class="block tabs" id="userListDiv">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
				
					<a id="review" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-select">${app:i18n('global.jsp.review')}</span></span></a>
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>
					<a id="modify" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('global.jsp.modify')}</span></span></a>
					<a id="delete" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-delete">${app:i18n('global.jsp.delete')}</span></span></a>
					
				</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
</s:form>
<div id="dialog-ajax-sysnamelist-view"></div>
