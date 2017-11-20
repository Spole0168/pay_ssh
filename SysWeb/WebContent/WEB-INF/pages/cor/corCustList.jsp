<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	
	
	
	$().ready(function() {

		var modifyUrl = "corCustModify.action";
		var deleteUrl = "corCustDelete.action";
		var createUrl = "corCustCreate.action";
		var searchUrl = "corCustSearch.action";
		var exportUrl = "corCustExport.action";
		
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
			$("#corCustListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corCustListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('corCust.custCode')}',
					'${app:i18n('corCust.custType')}',
					'${app:i18n('corCust.localName')}',
					'${app:i18n('corCust.country')}',
					'${app:i18n('corCust.englishName')}',
					'${app:i18n('corCust.certType')}',
					'${app:i18n('corCust.certNum')}',
					'${app:i18n('corCust.certExpireDate')}',
					'${app:i18n('corCust.createTime')}',
					'${app:i18n('corCust.creator')}',
					'${app:i18n('corCust.dataSource')}',
					'${app:i18n('corCust.status')}',
					'${app:i18n('corCustPhones.phoneNum')}',
					'${app:i18n('global.jsp.operation')}',
					
					
					'${app:i18n('corCust.certCopy')}',
					'${app:i18n('corCust.regTime')}',
					'${app:i18n('corCust.isValid')}',
					'${app:i18n('corCust.updator')}',
					'${app:i18n('corCustCompany.unitProperty')}',
					'${app:i18n('corCustCompany.corporateName')}',
					'${app:i18n('corCustCompany.corporateCountryCode')}',
					'${app:i18n('corCustCompany.corporateCertType')}',
					'${app:i18n('corCustCompany.corporateCertNum')}',
					'${app:i18n('corCustCompany.corporateCertCopy')}',
					'${app:i18n('corCustCompany.corporateCertExpireDate')}',
					'${app:i18n('corCustCompany.corporatePhonenum')}',
					'${app:i18n('corCustCompany.country')}',
					'${app:i18n('corCustCompany.regTime')}',
					'${app:i18n('corCustCompany.businessScope')}',
					'${app:i18n('corCustCompany.industry')}',
					'${app:i18n('corCustCompany.companyTel')}',
					'${app:i18n('corCustCompany.companyAddr')}',
					'${app:i18n('corCustCompany.postcode')}',
					'${app:i18n('corCustCompany.companyFax')}',
					'${app:i18n('corCustCompany.companyWebsite')}',
					'${app:i18n('corCustCompany.provience')}',
					'${app:i18n('corCustCompany.city')}',
					'${app:i18n('corCustCompany.district')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'custCode',index : 'custCode',width : '10%'},
						{name : 'custType',index : 'custType',width : '10%'},
						{name : 'localName',index : 'localName',width : '10%'},
						{name : 'country',index : 'country',width : '10%'},
						{name : 'englishName',index : 'englishName',width : '10%', hidden: true},
						{name : 'certType',index : 'certType',width : '10%'},
						{name : 'certNum',index : 'certNum',width : '10%'},
						{name : 'certExpireDate',index : 'certExpireDate',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'creator',index : 'creator',width : '10%', hidden: true},
						{name : 'dataSource',index : 'dataSource',width : '10%'},
						{name : 'status',index : 'status',width : '10%'},
						{name : 'phoneNum',index : 'phoneNum',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
						{name : 'regTime',index : 'regTime',width : '10%',hidden: true},
						{name : 'isValid',index : 'isValid',width : '10%', hidden: true},
						{name : 'updator',index : 'updator',width : '10%', hidden: true},
						{name : 'companyCode',index : 'companyCode',width : '10%', hidden: true},
						{name : 'certCopy',index : 'certCopy',width : '10%', hidden: true},
						{name : 'unitProperty',index : 'unitProperty',width : '10%', hidden: true},
						{name : 'corporateName',index : 'corporateName',width : '10%', hidden: true},
						{name : 'corporateCountryCode',index : 'corporateCountryCode',width : '10%', hidden: true},
						{name : 'corporateCertType',index : 'corporateCertType',width : '10%', hidden: true},
						{name : 'corporateCertNum',index : 'corporateCertNum',width : '10%', hidden: true},
						{name : 'corporateCertCopy',index : 'corporateCertCopy',width : '10%', hidden: true},
						{name : 'corporateCertExpireDate',index : 'corporateCertExpireDate',width : '10%', hidden: true},
						{name : 'corporatePhonenum',index : 'corporatePhonenum',width : '10%', hidden: true},
						{name : 'country',index : 'country',width : '10%', hidden: true},
						{name : 'regTime',index : 'regTime',width : '10%', hidden: true},
						{name : 'businessScope',index : 'businessScope',width : '10%', hidden: true},
						{name : 'industry',index : 'industry',width : '10%', hidden: true},
						{name : 'companyAddr',index : 'companyAddr',width : '10%', hidden: true},
						{name : 'postcode',index : 'postcode',width : '10%', hidden: true},
						{name : 'companyFax',index : 'companyFax',width : '10%', hidden: true},
						{name : 'companyWebsite',index : 'companyWebsite',width : '10%', hidden: true},
						{name : 'provience',index : 'provience',width : '10%', hidden: true},
						{name : 'city',index : 'city',width : '10%', hidden: true},
						{name : 'district',index : 'district',width : '10%', hidden: true},
						
					    
		    ],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('corCust.corCustListJsp.tableTitle')}",
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
		$("#gridTable").jqGrid("setGridParam",{url:"corCustSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数

		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("custType", $("#custType").val());
		$("#gridTable").setPostDataItem("country", $("#country").val());
		$("#gridTable").setPostDataItem("localName", $("#localName").val());
		$("#gridTable").setPostDataItem("englishName", $("#englishName").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("certExpireDate", $("#certExpireDate").val());
		$("#gridTable").setPostDataItem("status", $("#status").val());
		$("#gridTable").setPostDataItem("regTime", $("#regTime").val());
		$("#gridTable").setPostDataItem("dataSource", $("#dataSource").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("certCopy", $("#certCopy").val());
		$("#gridTable").setPostDataItem("kaiTime",$("#kaiTime").val());
		$("#gridTable").setPostDataItem("jieTime",$("#jieTime").val());
		
	}
	
	
	var handleAuditId = "#searchdiv";
	function handleAudit(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var url = "handleAudit.action?id="+id+"&time="+new Date().getTime();
			var title = "查看详情";
			$(handleAuditId).dialog({width:800,height:700,modal:true});
			openDialog(handleAuditId,title,url);
		}else{
	    	alert("请选择一条数据列！");
	    	return false;
	    }
	}
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
	
	function corCustFind(){
		var id = $("#gridTable").jqGrid('getGridParam','selrow');
		if(id!=null){
			var rowData = $("#gridTable").jqGrid("getRowData",id);
			var status = rowData.status;
			status = status.trim(" ");

			if(status==03){
				window.location="corCustFind.action?id="+id;
			}else{
	    		alert("只能对未审核的客户才能进行审核！");
	    		return false;
	   		}
		}else{
			alert("请选择一条数据列！");
		}
	}
	
</script>

<s:form id="corCustListForm" method="post" action="corCustList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corCust.corCustListJsp.headTitle')}</h3>
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
									<th width="10%">${app:i18n('corCust.custType')}：</th>
									<td width="30%">
										<!-- <input name="custType" id="custType"
										class="width_c" /> --> <select name="custType" id="custType"
										class="width_c">
											<option value=""><--请选择--></option>
											<option value="01"><--企业用户--></option>
											<option value="02"><--个人用户--></option>

									</select>
									</td>

									<th width="10%">${app:i18n('corCust.custCode')}：</th>
									<td width="30%"><input name="custCode" id="custCode"
										class="width_c" /></td>

									<th width="10%">${app:i18n('corCust.localName')}：</th>
									<td width="30%"><input name="localName" id="localName"
										class="width_c" /></td>
								</tr>
								<tr>
									<th width="10%">${app:i18n('corCust.certType')}：</th>
									<td width="30%">
										<!-- <input name="certType" id="certType"
										class="width_c" />  --> <select name="certType" id="certType"
										class="width_c">
											<option value="">请选择</option>
											<option value="营业执照">营业执照</option>
											<option value="批文">批文</option>
											<option value="身份证">身份证</option>
											<option value="军官证">军官证</option>
											<option value="士兵证">士兵证</option>
											<option value="户口簿">户口簿</option>
											<option value="护照">护照</option>
											<option value="港澳同胞回乡证">港澳同胞回乡证</option>
									</select>
									</td>

									<th width="10%">${app:i18n('corCust.certNum')}：</th>
									<td width="30%"><input name="certNum" id="certNum"
										class="width_c" /></td>

									<th width="10%">${app:i18n('corCust.status')}：</th>
									<td width="30%">
										<!-- <input name="status" id="status"
										class="width_c" /> --> <select name="status" id="status"
										class="width_c">
											<option value=""><--请选择--></option>
											<option value="1"><--未通过--></option>
											<option value="2"><--已通过--></option>
											<option value="3"><--待审核--></option>
											<option value="4"><--无效--></option>
									</select>
									</td>
								</tr>
								<tr>

									<th width="15%">${app:i18n('corCust.kaiTime')}：</th>
									<td width="30%"><input name="kaiTime" id="kaiTime"
										class="width_c" readonly="true"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'jieTime\')||\'%y-%M-%d 23:59:59\'}'})" /></td>

									<th width="15%">${app:i18n('corCust.jieTime')}：</th>
									<td width="30%"><input name="jieTime" id="jieTime"
										class="width_c" readonly="true"
										onfocus="WdatePicker({doubleCalendar:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'kaiTime\')||\'%y-%M-%d 23:59:59\'}'})" /></td>
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
				<div id="searchdiv" class="fieldset_container"></div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<div id="gridToolbar">
						<a id="create" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>
						<a id="handleAudit" onclick="handleAudit();"><span
							class="l-btn-right"><span class="l-btn-text icon-search">${app:i18n('global.jsp.detail')}</span></span></a>

						<a id="corCustFind" onclick="corCustFind();"><span
							class="l-btn-right"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.verify')}</span></span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
