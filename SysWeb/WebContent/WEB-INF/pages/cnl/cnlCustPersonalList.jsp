<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "cnlCustPersonalModify.action";
		var deleteUrl = "cnlCustPersonalDelete.action";
		var createUrl = "cnlCustPersonalCreate.action";
		var searchUrl = "cnlCustPersonalSearch.action";
		var exportUrl = "cnlCustPersonalExport.action";
		
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
			$("#cnlCustPersonalListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCustPersonalListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: searchUrl,
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
					'${app:i18n('cnlCustPersonal.cnlCustCode')}',
					'${app:i18n('cnlCustPersonal.custCode')}',
					'${app:i18n('cnlCustPersonal.isSmsVerified')}',
					'${app:i18n('cnlCustPersonal.name')}',
					'${app:i18n('cnlCustPersonal.regTime')}',
					'${app:i18n('cnlCustPersonal.email')}',
					'${app:i18n('cnlCustPersonal.country')}',
					'${app:i18n('cnlCustPersonal.provience')}',
					'${app:i18n('cnlCustPersonal.city')}',
					'${app:i18n('cnlCustPersonal.district')}',
					'${app:i18n('cnlCustPersonal.addr')}',
					'${app:i18n('cnlCustPersonal.employer')}',
					'${app:i18n('cnlCustPersonal.jobTitle')}',
					'${app:i18n('cnlCustPersonal.workTel')}',
					'${app:i18n('cnlCustPersonal.gender')}',
					'${app:i18n('cnlCustPersonal.birthday')}',
					'${app:i18n('cnlCustPersonal.industry')}',
					'${app:i18n('cnlCustPersonal.highestEdu')}',
					'${app:i18n('cnlCustPersonal.phonenum')}',
					'${app:i18n('cnlCustPersonal.postcode')}',
					'${app:i18n('cnlCustPersonal.isMarried')}',
					'${app:i18n('cnlCustPersonal.spouseCountry')}',
					'${app:i18n('cnlCustPersonal.spouseLocalName')}',
					'${app:i18n('cnlCustPersonal.spouseEnglishName')}',
					'${app:i18n('cnlCustPersonal.spouseCertType')}',
					'${app:i18n('cnlCustPersonal.spouseCertNum')}',
					'${app:i18n('cnlCustPersonal.spouseCertExpireDate')}',
					'${app:i18n('cnlCustPersonal.isValid')}',
					'${app:i18n('cnlCustPersonal.spousePhonenum')}',
					'${app:i18n('cnlCustPersonal.creator')}',
					'${app:i18n('cnlCustPersonal.updator')}',
					'${app:i18n('cnlCustPersonal.createTime')}',
					'${app:i18n('cnlCustPersonal.updateTime')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id',width : '10%', hidden: true},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '10%'},
						{name : 'custCode',index : 'custCode',width : '10%'},
						{name : 'isSmsVerified',index : 'isSmsVerified',width : '10%'},
						{name : 'name',index : 'name',width : '10%'},
						{name : 'regTime',index : 'regTime',width : '10%'},
						{name : 'email',index : 'email',width : '10%'},
						{name : 'country',index : 'country',width : '10%'},
						{name : 'provience',index : 'provience',width : '10%'},
						{name : 'city',index : 'city',width : '10%'},
						{name : 'district',index : 'district',width : '10%'},
						{name : 'addr',index : 'addr',width : '10%'},
						{name : 'employer',index : 'employer',width : '10%'},
						{name : 'jobTitle',index : 'jobTitle',width : '10%'},
						{name : 'workTel',index : 'workTel',width : '10%'},
						{name : 'gender',index : 'gender',width : '10%'},
						{name : 'birthday',index : 'birthday',width : '10%'},
						{name : 'industry',index : 'industry',width : '10%'},
						{name : 'highestEdu',index : 'highestEdu',width : '10%'},
						{name : 'phonenum',index : 'phonenum',width : '10%'},
						{name : 'postcode',index : 'postcode',width : '10%'},
						{name : 'isMarried',index : 'isMarried',width : '10%'},
						{name : 'spouseCountry',index : 'spouseCountry',width : '10%'},
						{name : 'spouseLocalName',index : 'spouseLocalName',width : '10%'},
						{name : 'spouseEnglishName',index : 'spouseEnglishName',width : '10%'},
						{name : 'spouseCertType',index : 'spouseCertType',width : '10%'},
						{name : 'spouseCertNum',index : 'spouseCertNum',width : '10%'},
						{name : 'spouseCertExpireDate',index : 'spouseCertExpireDate',width : '10%'},
						{name : 'isValid',index : 'isValid',width : '10%'},
						{name : 'spousePhonenum',index : 'spousePhonenum',width : '10%'},
						{name : 'creator',index : 'creator',width : '10%'},
						{name : 'updator',index : 'updator',width : '10%'},
						{name : 'createTime',index : 'createTime',width : '10%'},
						{name : 'updateTime',index : 'updateTime',width : '10%'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false},
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			caption: "${app:i18n('cnlCustPersonal.cnlCustPersonalListJsp.tableTitle')}",
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

		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("isSmsVerified", $("#isSmsVerified").val());
		$("#gridTable").setPostDataItem("name", $("#name").val());
		$("#gridTable").setPostDataItem("regTime", $("#regTime").val());
		$("#gridTable").setPostDataItem("email", $("#email").val());
		$("#gridTable").setPostDataItem("country", $("#country").val());
		$("#gridTable").setPostDataItem("provience", $("#provience").val());
		$("#gridTable").setPostDataItem("city", $("#city").val());
		$("#gridTable").setPostDataItem("district", $("#district").val());
		$("#gridTable").setPostDataItem("addr", $("#addr").val());
		$("#gridTable").setPostDataItem("employer", $("#employer").val());
		$("#gridTable").setPostDataItem("jobTitle", $("#jobTitle").val());
		$("#gridTable").setPostDataItem("workTel", $("#workTel").val());
		$("#gridTable").setPostDataItem("gender", $("#gender").val());
		$("#gridTable").setPostDataItem("birthday", $("#birthday").val());
		$("#gridTable").setPostDataItem("industry", $("#industry").val());
		$("#gridTable").setPostDataItem("highestEdu", $("#highestEdu").val());
		$("#gridTable").setPostDataItem("phonenum", $("#phonenum").val());
		$("#gridTable").setPostDataItem("postcode", $("#postcode").val());
		$("#gridTable").setPostDataItem("isMarried", $("#isMarried").val());
		$("#gridTable").setPostDataItem("spouseCountry", $("#spouseCountry").val());
		$("#gridTable").setPostDataItem("spouseLocalName", $("#spouseLocalName").val());
		$("#gridTable").setPostDataItem("spouseEnglishName", $("#spouseEnglishName").val());
		$("#gridTable").setPostDataItem("spouseCertType", $("#spouseCertType").val());
		$("#gridTable").setPostDataItem("spouseCertNum", $("#spouseCertNum").val());
		$("#gridTable").setPostDataItem("spouseCertExpireDate", $("#spouseCertExpireDate").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("spousePhonenum", $("#spousePhonenum").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());

	}
</script>

<s:form id="cnlCustPersonalListForm" method="post" action="cnlCustPersonalList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustPersonal.cnlCustPersonalListJsp.headTitle')}</h3>
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

								<th width="10%">${app:i18n('cnlCustPersonal.cnlCustCode')}：</th>
								<td width="30%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.custCode')}：</th>
								<td width="30%"><input name="custCode" id="custCode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.isSmsVerified')}：</th>
								<td width="30%"><input name="isSmsVerified" id="isSmsVerified" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.name')}：</th>
								<td width="30%"><input name="name" id="name" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.regTime')}：</th>
								<td width="30%"><input name="regTime" id="regTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.email')}：</th>
								<td width="30%"><input name="email" id="email" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.country')}：</th>
								<td width="30%"><input name="country" id="country" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.provience')}：</th>
								<td width="30%"><input name="provience" id="provience" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.city')}：</th>
								<td width="30%"><input name="city" id="city" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.district')}：</th>
								<td width="30%"><input name="district" id="district" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.addr')}：</th>
								<td width="30%"><input name="addr" id="addr" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.employer')}：</th>
								<td width="30%"><input name="employer" id="employer" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.jobTitle')}：</th>
								<td width="30%"><input name="jobTitle" id="jobTitle" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.workTel')}：</th>
								<td width="30%"><input name="workTel" id="workTel" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.gender')}：</th>
								<td width="30%"><input name="gender" id="gender" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.birthday')}：</th>
								<td width="30%"><input name="birthday" id="birthday" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.industry')}：</th>
								<td width="30%"><input name="industry" id="industry" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.highestEdu')}：</th>
								<td width="30%"><input name="highestEdu" id="highestEdu" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.phonenum')}：</th>
								<td width="30%"><input name="phonenum" id="phonenum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.postcode')}：</th>
								<td width="30%"><input name="postcode" id="postcode" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.isMarried')}：</th>
								<td width="30%"><input name="isMarried" id="isMarried" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseCountry')}：</th>
								<td width="30%"><input name="spouseCountry" id="spouseCountry" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseLocalName')}：</th>
								<td width="30%"><input name="spouseLocalName" id="spouseLocalName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseEnglishName')}：</th>
								<td width="30%"><input name="spouseEnglishName" id="spouseEnglishName" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseCertType')}：</th>
								<td width="30%"><input name="spouseCertType" id="spouseCertType" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseCertNum')}：</th>
								<td width="30%"><input name="spouseCertNum" id="spouseCertNum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spouseCertExpireDate')}：</th>
								<td width="30%"><input name="spouseCertExpireDate" id="spouseCertExpireDate" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.isValid')}：</th>
								<td width="30%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.spousePhonenum')}：</th>
								<td width="30%"><input name="spousePhonenum" id="spousePhonenum" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.creator')}：</th>
								<td width="30%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.updator')}：</th>
								<td width="30%"><input name="updator" id="updator" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.createTime')}：</th>
								<td width="30%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="10%">${app:i18n('cnlCustPersonal.updateTime')}：</th>
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
