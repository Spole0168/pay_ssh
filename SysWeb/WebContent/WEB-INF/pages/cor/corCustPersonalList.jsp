<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	
	
	
	$().ready(function() {

		var verifyUrl = "corCustPersonalVerify.action";
		var modifyUrl = "corCustPersonalModify.action";
		var detailUrl = "corCustPersonalDetail.action";
		var createUrl = "corCustPersonalCreate.action";
		var searchUrl = "corCustPersonalSearch.action";
		var exportUrl = "corCustPersonalExport.action";
		var deleteUrl = "corCustPersonalDelete.action";
		
		//查询
		$("#search").click(function(){
			doSearch();
		});
	
		//重置
		$("#reset").click(function(){
			// 设置查询参数为空
			$("#queryField :input").val("");
		});

		//导出
		$("#export").click(function(){
			// 设置查询参数
			setQueryCondition();

			$("#gridTable").gridUtil().exportExcel({url: exportUrl});
		});
		
		//创建
		$("#create").click(function(){
			window.location.href=createUrl;
		});

		//查看详情
		$("#detail").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#corCustPersonalListForm").attr("action",detailUrl+"?id="+gr[0]);
			$("#corCustPersonalListForm").submit();
		});
		
		//审核
		$("#verify").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行");
				return;
			}
			
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#corCustPersonalListForm").attr("action",verifyUrl+"?id="+gr[0]);
			$("#corCustPersonalListForm").submit();
		});
		
		
		//删除
		$("#delete").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#corCustPersonalListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corCustPersonalListForm").submit();
		});
		
		
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			//shrinkToFit:true,
			//rowList:[50,100,200],
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:true,
			colNames:[ 'ID', 
					'${app:i18n('corCustPersonal.custCode')}',
					'${app:i18n('corCustPersonal.localName')}',
					'${app:i18n('corCustPersonal.firstName')}',
					'${app:i18n('corCustPersonal.lastName')}',
					'${app:i18n('corCustPersonal.country')}',
					'${app:i18n('corCustPersonal.certType')}',
					'${app:i18n('corCustPersonal.certNum')}',
					'${app:i18n('corCustPersonal.certExpireDate')}',
					'${app:i18n('corCustPersonal.createTime')}',
					'${app:i18n('corCustPersonal.creator')}',
					'${app:i18n('corCustPersonal.dataSource')}',
					'${app:i18n('corCustPersonal.realNameLeve')}',
					'${app:i18n('corCustPersonal.custLevel')}',
					'${app:i18n('corCustPersonal.status')}',
					'${app:i18n('corCustPersonal.phoneNum')}',
					'${app:i18n('corCustPersonal.operation')}',
					],
			colModel:[
					{name : 'id', hidden: true},
					{name : 'custCode',index : 'custCode'},
					{name : 'localName',index : 'localName'},
					{name : 'firstName',index : 'firstName'},
					{name : 'lastName',index : 'lastName'},
					{name : 'country',index : 'country'},
					{name : 'certType',index : 'certType'},
					{name : 'certNum',index : 'certNum'},
					{name : 'certExpireDate',index : 'certExpireDate'},
					{name : 'createTime',index : 'createTime'},
					{name : 'creator',index : 'creator'},
					{name : 'dataSource',index : 'dataSource'},
					{name : 'realNameLeve',index : 'realNameLeve'},
					{name : 'custLevel',index : 'custLevel'},
					{name : 'status',index : 'status'},
					{name : 'phoneNum',index : 'phoneNum'},
					{name :  'operation',align:'center', search:false,sortable:false,editable:true,title:false},
		    ],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corCustPersonal.corCustPersonalListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
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

		// sub form toggles
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
		$("#gridTable").jqGrid("setGridParam",{url:"corCustPersonalSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("localName", $("#localName").val());
		$("#gridTable").setPostDataItem("status", $("#status").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("phoneNum", $("#phoneNum").val());
		$("#gridTable").setPostDataItem("realNameLeve", $("#realNameLeve").val());
		$("#gridTable").setPostDataItem("custLever", $("#custLever").val());
		$("#gridTable").setPostDataItem("createTimeStart", $("#createTimeStart").val());
		$("#gridTable").setPostDataItem("createTimeEnd", $("#createTimeEnd").val());
		
	}
	
	
	/* 
	var handleAuditId = "#searchdiv";
	function handleAudit(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var url = "handleAudit.action?id="+id+"&time="+new Date().getTime();
			var title = "查看详情";
			$(handleAuditId).dialog({width:800,height:700,modal:true});
			openDialog(handleAuditId,title,url);
		}else{
	    	alert("请选择一条未处理状态的数据列！");
	    	return false;
	    }
	} */
	
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
		$(dlgId).dialog({
			autoOpen:false,
			position:[400,100],
			modal:true,
			useSlide:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
	}
	
	/* 
	var corCustFind = "#corCustFind";
	function corCustFind(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var url = "corCustFind.action?id="+id+"&time="+new Date().getTime();
			var title = "查看详情";
			$(corCustFind).dialog({width:800,height:700,modal:true});
			openDialog(corCustFind,title,url);
		}else{
	    	alert("请选择一条未处理状态的数据列！");
	    	return false;
	    }
	} */
	
</script>

<s:form id="corCustPersonalListForm" method="post"
	action="corCustPersonalList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corCustPersonal.corCustPersonalListJsp.headTitle')}</h3>
			</div>
			<div class="block_container">
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
								<!-- 客户编号 -->
								<th width="15%">${app:i18n('corCustPersonal.custCode')}：</th>
								<td width="30%">
									<input name="corCondition.custCode" id="custCode" class="width_c" />
								</td>
								<!-- 客户名字 -->
								<th width="15%">${app:i18n('corCustPersonal.localName')}：</th>
								<td width="30%">
									<input name="corCondition.localName" id="localName" class="width_c" />
								</td>
								<!-- 客户状态 -->
								<th width="15%">${app:i18n('corCustPersonal.status')}：</th>
								<td width="30%">
									<select name="corCondition.status"id="status" class="width_c">
										<option value="">请选择</option>
										<option value="1">未通过</option>
										<option value="2">已通过</option>
										<option value="3">待审核</option>
										<option value="4">无效</option>
									</select>
								</td>
							</tr>
							<tr>
								<!-- 证件类型 -->
								<th width="15%">${app:i18n('corCustPersonal.certType')}：</th>
								<td width="30%">
									<select name="corCondition.certType" id="certType" class="width_c">
									<option value="">请选择</option>
									<option value="1">营业执照</option>
									<option value="2">批文</option>
									<option value="3">身份证</option>
									<option value="4">军官证</option>
									<option value="5">士兵证</option>
									<option value="6">户口簿</option>
									<option value="7">护照</option>
									<option value="8">港澳同胞回乡证</option>
								</select>
								</td>
								<!-- 证件号码 -->
								<th width="15%">${app:i18n('corCustPersonal.certNum')}：</th>
								<td width="30%"><input name="corCondition.certNum" id="certNum"
									class="width_c" /></td>
								<!-- 客户电话-->
								<th width="15%">${app:i18n('corCustPersonal.phoneNum')}：</th>
								<td width="30%">
									<input name="corCondition.phoneNum" id="phoneNum" class="width_c" />
							    </td>
							</tr>
							<tr>
								<!-- 客户实名级别-->
								<th width="15%">${app:i18n('corCustPersonal.realNameLeve')}：</th>
								<td width="30%">
									<select name="corCondition.realNameLeve" id="realNameLeve" class="width_c">
										<option value="">请选择</option>
										<option value="1">未认证</option>
										<option value="2">一级认证</option>
										<option value="3">二级认证</option>
										<option value="4">三级认证</option>
									</select>
							    </td>
								<!-- 客户等级-->
								<th width="15%">${app:i18n('corCustPersonal.custLevel')}：</th>
								<td width="30%">
									<select name="corCondition.custLevel" id="custLevel" class="width_c">
										<option value="">请选择</option>
										<option value="1">普通用户</option>
										<option value="2">白银用户</option>
										<option value="3">白金用户</option>
										<option value="4">钻石用户</option>
									</select>
							    </td>
							</tr>
							<tr>
								<!-- 创建时间起点 -->
								<th width="15%">${app:i18n('corCustPersonal.createTimeStart')}：</th>
								<td width="30%"><input name="corCondition.createTimeStart"
									id="createTimeStart" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1990-01-01 00:00:00',maxDate:'#F{$dp.$D(\'createTimeEnd\')||\'%y-%M-%d %H-%m-%s\'}'})" />
								</td>
								<!-- 创建时间终点 -->
								<th width="15%">${app:i18n('corCustPersonal.createTimeEnd')}：</th>
								<td width="30%"><input name="corCondition.createTimeEnd"
									id="createTimeEnd" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createTimeStart\')||\'1990-01-01 00:00:00\'}',maxDate:'%y-%M-%d %H-%m-%s'})" />
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
							<a name="export" id="export" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>
						</div>
					</div>
				</div>
				<div id="searchdiv" class="fieldset_container">

				</div>	
				
				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar">
						<a id="create" class="l-btn-plain l-btn m-l4">
						<span class="l-btn-left">
							<span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span>
						</span></a>
						<a id="detail" class="l-btn-plain l-btn m-l4">
						<span class="l-btn-left">
							<span class="l-btn-text icon-search">${app:i18n('global.jsp.detail')}</span>
						</span></a>
						<a id="verify" class="l-btn-plain l-btn m-l4">
						<span class="l-btn-left">
							<span class="l-btn-text icon-reset">${app:i18n('global.jsp.verify')}</span>
						</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
