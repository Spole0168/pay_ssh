<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "sysSysCfgModify.action";
		var deleteUrl = "sysSysCfgDelete.action";
		var createUrl = "sysSysCfgCreate.action";
		var searchUrl = "sysSysCfgSearch.action";
		var exportUrl = "sysSysCfgExport.action";
		
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

		$("#delete").click( function() {
			var gr;
			gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
			if (gr.length != 1) {
				alert("请选择一行修改");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#sysSysCfgListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#sysSysCfgListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('sysSysCfg.category')}',
					'${app:i18n('sysSysCfg.code')}',
					'${app:i18n('sysSysCfg.name')}',
					'${app:i18n('sysSysCfg.value')}',
					'${app:i18n('sysSysCfg.version')}',
					'${app:i18n('sysSysCfg.isValid')}',
					'${app:i18n('sysSysCfg.creator')}',
					'${app:i18n('sysSysCfg.updator')}',
					'${app:i18n('sysSysCfg.createTime')}',
					'${app:i18n('sysSysCfg.updateTime')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'category',index : 'category',width : '10%'},
						{name : 'code',index : 'code',width : '10%'},
						{name : 'name',index : 'name',width : '10%'},
						{name : 'value',index : 'value',width : '10%'},
						{name : 'version',index : 'version',width : '10%'},
						{name : 'isValid',index : 'isValid',width : '10%'},
						{name : 'creator',index : 'creator',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('sysSysCfg.sysSysCfgListJsp.tableTitle')}",
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
		$("#gridTable").jqGrid("setGridParam",{url:$("#searchUrl").val()}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("category", $("#category").val());
		$("#gridTable").setPostDataItem("code", $("#code").val());
		$("#gridTable").setPostDataItem("name", $("#name").val());
		$("#gridTable").setPostDataItem("value", $("#value").val());
		$("#gridTable").setPostDataItem("version", $("#version").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());

	}
</script>

<s:form id="sysSysCfgListForm" method="post" action="sysSysCfgList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('sysSysCfg.sysSysCfgListJsp.headTitle')}</h3>
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

								<th width="10%">${app:i18n('sysSysCfg.category')}：</th>
								<td width="30%"><input name="category" id="category" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.code')}：</th>
								<td width="30%"><input name="code" id="code" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.name')}：</th>
								<td width="30%"><input name="name" id="name" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.value')}：</th>
								<td width="30%"><input name="value" id="value" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.version')}：</th>
								<td width="30%"><input name="version" id="version" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.isValid')}：</th>
								<td width="30%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.creator')}：</th>
								<td width="30%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.updator')}：</th>
								<td width="30%"><input name="updator" id="updator" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.createTime')}：</th>
								<td width="30%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="10%">${app:i18n('sysSysCfg.updateTime')}：</th>
								<td width="30%"><input name="updateTime" id="updateTime" class="width_c" /></td>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						<a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a> 
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
