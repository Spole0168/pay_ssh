<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 包含简单表格的JavaScript -->
 

 
<div id="container" class="layout block m-b">

<s:form method="post" action="dataDictList.action">
<input type="hidden" name="pageCache" id="pageCache" value="true">
 
 <input type="hidden" name="canModifyData" id="canModifyData" value="" /> 
<s:if test="searchFromWhere == 'fromFIN' ">	
 
<app:isPermission resource='O_MDM_ORG_DICT_FIN_MODIFY'>
<script type="text/javascript" charset="UTF-8"> 
$("#canModifyData").val('true'); 
  
</script>
</app:isPermission> 

</s:if>

<script type="text/javascript" charset="UTF-8">
    $("#canModifyData").val('true'); 
</script>
 
<div class="block_title">
<h3>数据字典信息列表</h3>
</div>
<div class="block_container">
<div id='data_dict_search_table' class="fieldset_border fieldset_bg m-b">
<div class="legend_title"><a href="#" class="container_show">数据字典信息查询</a>  </div>
<div class="fieldset_container">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
	
	    <td width="65" align="right"><label for="searchType">数据类型：</label></td>
		<td width="50">
		 <input type="hidden" name="searchStatus" id="searchStatus" value="VALID" />
		 
		   <input type="hidden" name="searchFromWhere" id="searchFromWhere" value="${searchFromWhere}" /> 
		 
			<select name="searchType" id="searchType"   style="width:123px">
			<option value="">不限定   </option>			
			<s:iterator value="%{typeList}" id="typeItem">
				<option value="<s:property value="#typeItem.key" />">
			    	<s:property value="#typeItem.value" />
			    </option>
			</s:iterator>
            </select>        
         </td>
         
         <td  width="50" align="right"><label for="searchLanguage">语言：</label></td>
		<td  width="50">
		
			<select name="searchLanguage" id="searchLanguage"  style="width:123px">
			<option value="">不限定   </option>	
			<s:iterator value="%{languageList}" id="typeItem">
				<option value="<s:property value="#typeItem.key" />">
			    	<s:property value="#typeItem.value" />
			    </option>
			</s:iterator>
            </select>        
         </td>
       
         
		<td  width="50" align="right"><label for="searchCode">代码：</label></td>
		<td width="50">
			<input type="text" name="searchCode" id="searchCode" size="20" maxlength="50"></input>
		</td>
		
		<td width="50" align="right"><label for="searchName">名称：</label></td>
		<td  width="50">
			<input type="text" name="searchName" id="searchName" size="20" maxlength="100"></input>
		</td>
		<td  width="50"align="right"><label for="searchDescription">描述：</label></td>
		<td  width="50">
			<input type="text" name="searchDescription" id="searchDescription" size="20" maxlength="100"></input>
		</td>
	</tr>
 
</table>
<div class="btn_layout">
	<a  id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-search">${app:i18n('global.jsp.search')}</span>
	  </span>
	</a>
	<a id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.reset')}</span></span></a>
	<a id="export-data-dict" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-excel">导出</span></span></a>
    </div>
    
</div>
</div>
 
<div id="toolbar">
	<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">新 增</span></span></a>  
	<a id="delete" class="l-btn-plain l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-remove">删 除</span></span></a>	 
</div>
<div style="height: 1px; line-height: 1px;"></div>

<table id="dataDictListGrid"></table>
<div id="dataDictListPager"></div>
<div id="tblMasterMessage"></div>
</div>


 <script type="text/javascript" charset="UTF-8">
var array = new Array();
	$().ready(function() {
 
		
		 var searchFromWhere=$("#searchFromWhere").val();
		  var searchUrlInput="dataDictSearch.action?searchFromWhere="+searchFromWhere; 

		//构建表格，并给删除、新增和修改按钮增加操作
		buildPopupGrid({
			caption:"数据列表",
			gridName:"dataDictListGrid",
			columnButtonVar:"选择列",
			formName:"dataDictList",
			pagerName:"dataDictListPager",
			searchButtonName:"search",			 
			createButtonName:"create",
			modifyButtonName:"modify",
			searchUrl:searchUrlInput,
			deleteUrl:"dataDictDelete.action",
			createUrl:"dataDictCreate.action",
			modifyUrl:"dataDictModify.action",
			modifyFunctionName:"modify",

// 			//导出设置
			exportButtonName:"export-data-dict",
			exportUrl:"dataDictExport.action",			
			exportColNames:[ '代码', '名 称', '类型','父类型代码','语言','显示顺序','描述'],
			exportColModels:[ 'code', 'name', 'typeName','ctgCode', 'locale.name', 'displayOrder', 'remark'],
			exportParameters:  ["searchType","searchLanguage","searchStatus","searchCode","searchName","searchDescription","searchFromWhere","pageCache"],
			exportFileName:"数据字典.xls",

// 			colNames:[ '代码', '名 称', '类型','类型','语言','显示顺序','描述', '操作' ],
			colNames:[ '代码', '名 称', '类型','类型','父类型代码','语言','显示顺序','描述', '操作' ],
			colModel:[ 
			          {name : 'code',index : 'code',width : '20%'},
					    {name : 'name',index : 'name',width : '20%'}, 
					    {name : 'typeName',index : 'type',width : '20%'},
					    {name : 'type', width : '20%',hidden:true},
					 	{name : 'ctgCode', width : '20%'},
					    {name : 'locale.name',index : 'locale',width : '20%'},
					    {name : 'displayOrder',index : 'displayOrder',width : '10%'},
					    {name : 'remark',index : 'remark',width : '30%'},					   				   
					    {name:  'operation',width:'10%',sortable:false},
			],
			searchParameters: ["searchType","searchLanguage","searchStatus","searchCode","searchName","searchDescription","searchFromWhere","pageCache"],
			sortName:"type",
			sortOrder:"asc"	
		});

		$("#reset").click(function(){
			$("#searchType").val("");
			$("#searchLanguage").val("");
			$("#searchName").val("");
			$("#searchDescription").val("");
			$("#searchStatus").val("");
			$("#searchCode").val("");
		});
		
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
		});

		$("#create").click(function(){
			var searchFromWhere=$("#searchFromWhere").val(); 
			var selecttype=$("#searchType").val();
			if(selecttype==null||selecttype=="")
			{
			$.boxUtil.error("添加数据之前，请先选择数据类型");
               
             return;
				}
			var url = "dataDictCreate.action?dataDict.type="+$("#searchType").val()+"&searchFromWhere="+searchFromWhere; 
			var title = "添加数据";
			openDialog(title, url);
			
		});
		$("#data_dict_search_table input").input().enter(function(){
			$("#search").click();
		});
		
		$("#delete").click(function(){
			var selectIds = $("#dataDictListGrid").jqGrid('getGridParam','selarrrow');
			if (selectIds!=null && selectIds!="")	{

// 				for(var i=0;i < selectIds.length;i++){ 
//            		   var checkid = selectIds[i];
//            		 var datatype = $('#dataDictListGrid').getRowData(checkid).type;
           		  
//            		 if(datatype!='FINANCIAL_DICT'&&datatype!='FINANCIAL_ORG_BIZ')
//            		 {
//            			$.boxUtil.error("该数据类型不能删除");
//            			return;
//                		 }           		   
// 				}
           	 				
				$("#dataDictListGrid").jqGrid('delGridRow',selectIds,{drag:false,
					afterSubmit:function(resp, postdata){
					var result = $.parseJSON(resp.responseText);
					if(result.success == 'false'){
						alert(result.text);		
						return [true,"",""];
					} else {
						return [true,"",""];
					}
				}
				});
			
				
			}else{
				alert('请选择要删除的数据字典项!');
			}
		});
	});
</script>
 <%-- 弹出 start --%>
<script type="text/javascript" charset="UTF-8">
	var dlgId = "#dialog-ajax-datadict-edit";
	var gridId = "#dataDictListGrid";
	function modify(id){

		var searchFromWhere=$("#searchFromWhere").val(); 
		var canmodify=$("#canModifyData").val();
		 
		if(canmodify=='true')
		{
			var url = "dataDictModify.action?id="+id+"&time="+new Date().getTime()+"&searchFromWhere="+searchFromWhere; 
			var title = "修改数据";
			openDialog(title,url);
			}else
			{
				$.boxUtil.error("你没有权限修改数据");
				return ;
			} 
		
	}
	function refreshGrid(){
		$(gridId).trigger("reloadGrid");
	}
	function openDialog(title, url){
		$(dlgId).dialog({
			autoOpen:false,
			width:600,
			height:350,
			position:'center',
			modal:true,
			resizable: false,
			title:title
			});
		$(dlgId).load(url);
		$(dlgId).dialog('open');
		 $(dlgId).dialog({
			   close: function() { 			   
			  $(dlgId).html(" ");			   
	     }  
			});
	}
	function closeDialog(){
		$(dlgId).dialog('close');
	}
</script>
<%-- 弹出 end --%>
 <div id="dialog-ajax-datadict-edit"></div>
</s:form>
</div>


 
