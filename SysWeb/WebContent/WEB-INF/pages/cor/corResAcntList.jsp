<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {
		var bankInnerCode = ${bankInnerCode };
		var modifyUrl = "corReservedFundAcntModify.action";
		var deleteUrl = "corReservedFundAcntDelete.action";
		var createUrl = "corReservedFundAcntCreate.action";
		/* var getinfo = "getinfo.action"; */
		/* var showPage = "showBankAcntJSP.action"; */
		var exportUrl = "corReservedFundAcntExport.action";
		
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
			window.location.href=createUrl+"?bankInnerCode="+bankInnerCode;
		});
		
		$("#undo").click(function(){
			window.location.href="/system/corBankInfo/corBankIntfList.action?menuId=10005";
		});

		$("#delete").click( function() {
			$(".warning").hide();
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			if(id!=null){
				if(confirm("是否删除")){
					$.ajax({
						type:"POST",
						dateType:"text",
						url:deleteUrl+"?id="+id,
						success:function(msg){
							if(msg == "1"){
								$.growlUI('成功信息！', '删除成功！');
								location.href="bankNum.action?bankInnerCode="+bankInnerCode;
							}
						}
					})
				}
			}else{
		    	$(".warning").html("请选择一条需要删除的数据！");
		    	$(".warning").show();
		    	return false;
			}
		});
		
		
		$("#update").click(function(){
			$(".warning").hide();
			var id=$('#gridTable').jqGrid('getGridParam','selrow');
			
			if(id!=null){
				window.location= modifyUrl+"?id="+id;
			}else{
		    	$(".warning").html("请选择一条未处理状态的数据列！");
		    	$(".warning").show();
		    	return false;
			}
		})
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "findByBankNum.action?bankInnerCode="+bankInnerCode,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('corReservedFundAcnt.custName')}',
					'${app:i18n('corReservedFundAcnt.certType')}',
					'${app:i18n('corReservedFundAcnt.certNum')}',
					'${app:i18n('corReservedFundAcnt.bankCardNum')}',
					'${app:i18n('corReservedFundAcnt.currency')}',
					'${app:i18n('corReservedFundAcnt.acntCategor')}'],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'custName',index : 'custName',width : '10%'},
						{name : 'certType',index : 'certType',width : '10%'},
						{name : 'certNum',index : 'certNum',width : '10%'},
						{name : 'bankCardNum',index : 'bankCardNum',width : '10%'},
						{name : 'currency',index : 'currency',width : '10%'},
						{name : 'acntCategory',index : 'acntCategory',width : '10%'},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corReservedFundAcnt.corReservedFundAcnt.tableTitle')}",
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
	}
	function setQueryCondition(){
		// 设置查询参
	}
</script>

<s:form id="corReservedFundAcntListForm" method="post" action="corReservedFundAcntList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_container">
			<div class="warning" style="display:none;">
				<span></span>
			</div>
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					<a id="update" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-edit">${app:i18n('uptate')}</span></span></a> 
					<a id="delete" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-delete">${app:i18n('global.jsp.delete')}</span></span></a> 
					<a id="undo" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a> 
				</div>
			</div>
		</div>
	</div>
</div>
</s:form>
