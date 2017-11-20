<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
     $().ready(function() {

		
    	var modifyUrl = "cnlCustAcntModify.action";
		var deleteUrl = "cnlCustAcntDelete.action";
		var createUrl = "cnlCustAcntCreate.action";
		var searchUrl = "cnlCustAcntSearch.action";
		var exportUrl = "cnlCustAcntExport.action";
		
		

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
			$("#cnlCustAcntListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCustAcntListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID',
			          '${app:i18n('cnlCustAcnt.cnlCustCode')}',
			          '${app:i18n('cnlCustAcnt.localName')}',
			          '${app:i18n('cnlCustAcnt.currency')}',
			          '${app:i18n('cnlCustAcnt.balanceAvalible')}',
			          '${app:i18n('cnlCustAcnt.acntStatus')}',
			          '${app:i18n('cnlCustAcnt.balanceFrozen')}',
			          '${app:i18n('cnlCustAcnt.balance')}'],
			colModel:[	
{name : 'id',width : '10%', hidden: true},
{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
{name : 'localName',index : 'localName',width : '10%'},
{name : 'currency',index : 'currency',width : '10%'},
{name : 'balanceAvalible',index : 'balanceAvalible',width : '10%'}, 
{name : 'acntStatus',index : 'acntStatus',width : '10%'},
{name : 'balanceFrozen',index : 'balanceFrozen',width : '10%'},
{name : 'balance',index : 'balance',width : '10%'},

],
			pager: "#gridPager",
			//toolbar: [true,"top"],
			caption: "${app:i18n('cnlCustAcnt.cnlCustAcntListJsp.tableTitle')}",
			  gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				
				if(ids.length<=0){
					/* $("div.warning span").html("没有记录");
					$("div.warning").show(); */
				} 
				/* for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);

					all = all + mod;

					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}*/
			} 
		});

		 $("#gridTable").gridUtil().customizeColumn();
	 	/* $("#gridToolbar").appendTo($("#t_gridTable"));  */

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
		
		
		$("#statisticalTypeSearch").change(function(){
			
			var type = $("#statisticalTypeSearch").val();
			if(type!=""){
				$("input[name=maxSearch]").removeAttr("readonly");
				$("input[name=minSearch]").removeAttr("readonly");
			}else{
				$("#maxSearch").val("");
				$("#minSearch").val("");
				$("input[name=maxSearch]").attr("readonly","readonly");
				$("input[name=minSearch]").attr("readonly","readonly");
			}
			
		});
		
		$("#search").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			var maxval = $("#maxSearch").val();
			var minval = $("#minSearch").val();
			var type = $("#statisticalTypeSearch").val();
            if(minval!=""){
                
			if( !/^\d+(\.\d+)?$/.test(minval)){
				
				$("div.warning span").html("最小值只能输入数字！");
				$("div.warning").show();
				$("#minSearch").val('');
				   return;
			}
			}
		
		  if(maxval!=""){
			if( !/^\d+(\.\d+)?$/.test(maxval)){
				$("div.warning span").html("最大值只能输入数字！");
				$("div.warning").show();
				$("#maxSearch").val('');
				return;
			}  
			}
			
			
            if(eval(minval)>eval(maxval)){
				$("div.warning span").html("最小值不能大于最大值");
				$("div.warning").show();
				$("#minSearch").val('');
				return;
			}
               doSearch();

		});
		
		$("#minSearch").click(function(){
			var type = $("#statisticalTypeSearch").val();
			if(type==""){
				$("div.warning span").html("请先选择统计类型");
				$("div.warning").show();
			}else{
				$("div.warning span").html("");
				$("div.warning").hide();
			}
			});
		
		$("#maxSearch").click(function(){
			var type = $("#statisticalTypeSearch").val();
			if(type==""){
				$("div.warning span").html("请先选择统计类型");
				$("div.warning").show();
			}else{
				$("div.warning span").html("");
				$("div.warning").hide();
			}
			});
		
		
		
		
		
	});
	
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlCustAcntSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("custTypeSearch", $("#custTypeSearch").val());
		$("#gridTable").setPostDataItem("cnlCustCodeSearch", $("#cnlCustCodeSearch").val());
		$("#gridTable").setPostDataItem("localNameSearch", $("#localNameSearch").val());
		$("#gridTable").setPostDataItem("currencySearch", $("#currencySearch").val());
		$("#gridTable").setPostDataItem("statisticalTypeSearch", $("#statisticalTypeSearch").val());
		$("#gridTable").setPostDataItem("maxSearch", $("#maxSearch").val());
		$("#gridTable").setPostDataItem("minSearch", $("#minSearch").val());

	}
	
	
	 
</script>

<s:form id="cnlCustAcntListForm" method="post" action="cnlCustAcntList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustAcnt.cnlCustAcntListJsp.headTitle')}</h3>
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
							
                           <!-- 客户类型 -->
						   <th width="10%">${app:i18n('cnlCustAcnt.custType')}：</th>
						   <td width="30%">
				           <select id="custTypeSearch" name="custTypeSearch" style="width:137px">
				           <option value="">${app:i18n('pleaseSelect')}</option>
				           <s:iterator value="custTypeList" var="typeList">
				           <option value="${typeList.key}">${typeList.value }</option>
				           </s:iterator>
				           </select>
			             </td>
			             
			               
			             <th width="10%">${app:i18n('cnlCustAcnt.statisticalType')}：</th>  
					     <td width="30%" >
				         <select id="statisticalTypeSearch" name="statisticalTypeSearch" style="width:137px">
				         <option value="">${app:i18n('pleaseSelect')}</option>
					     <option value="balanceAvalible">${app:i18n('cnlCustAcnt.balanceAvalible')}</option>
					     <option value="balanceFrozen">${app:i18n('cnlCustAcnt.balanceFrozen')}</option>
				         </select>
			             </td>
								
								
						<th width="10%">${app:i18n('cnlCustAcnt.min')}：</th>
						<td width="30%">
						<input name="minSearch" id="minSearch" readonly="readonly"  />
						</td>		
								
						</tr>
							
						<tr>	
							
							
						   <th width="10%">${app:i18n('cnlCustAcnt.cnlCustCode')}：</th>
						   <td width="30%"><input name="cnlCustCodeSearch" id="cnlCustCodeSearch" maxlength='25'/></td>
							
						   <th width="10%">${app:i18n('cnlCustAcnt.currency')}：</th>
					       <td width="30%">
				           <select id="currencySearch" name="currencySearch" style="width:137px">
				           <s:iterator value="currencyTypeList" var="currencyList">
				           <option value="${currencyList.key}">${currencyList.value }</option>
				           </s:iterator>
				           </select>
			               </td>
			             
			             
			             <th width="10%">${app:i18n('cnlCustAcnt.max')}：</th>
						 <td width="30%">
						 <input name="maxSearch" id="maxSearch" readonly="readonly" />
						 </td>
								
						</tr>
								
								<tr>
								
								<th width="10%">${app:i18n('cnlCustAcnt.localName')}：</th>
								<td width="30%"><input name="localNameSearch" id="localNameSearch" maxlength='25'/></td>
							
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
				<%--  <div id="gridToolbar"> 
		
				 
				</div> --%>
			</div>
		</div>
	</div>
</div>
</s:form>
