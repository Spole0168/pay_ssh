<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div id="container" class="layout block m-b">

<s:form id="cnlCustCompanyListForm" method="post" action="cnlCustCompanyList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustCompany.cnlCustCompanyListJsp.headTitle')}</h3>
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
								<th width="20%">${app:i18n('cnlCustCompany.cnlCnlCode')}：</th>
								<td width="20%">
									<select name="cnlCnlCode" id="cnlCnlCode" class="width_c" style="width: 190px">
										<s:iterator value="cnlCnlCodes" var="ccd">
											<option value="${ccd.key }">${ccd.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="20%">${app:i18n('cnlCustCompany.createStartTime')}：</th>
								<td width="20%">
									<input name="createStartTime" id="createStartTime" class="Wdate" value="${cnlCustCompany.createStartTime}" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createEndTime\')}'})" style="width: 190px"/>
									<!-- <input name="createStartTime" id="createStartTime" class="width_c" /> -->
								</td>
								
								<th width="20%">${app:i18n('cnlCustCompany.createEndTime')}：</th>
								<td width="20%">
								<input name="createEndTime" id="createEndTime" class="Wdate" value="${cnlCustCompany.createEndTime}" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createStartTime\')}'})" style="width: 190px"/>
									<!-- <input name="createEndTime" id="createEndTime" class="width_c" /> -->
								</td>
							</tr>
							<tr>	
								<th width="20%">${app:i18n('cnlCustCompany.cnlCustType')}：</th>
								<td width="20%">
									<select name="cnlCustType" id="cnlCustType" class="width_c" style="width: 190px">
										<option value="" selected>企业客户</option>
									</select>
									<!-- <input name="custType" id="custType" class="width_c" value="企业客户" readonly="readonly"/> -->
								</td>
								
								<th width="20%">${app:i18n('cnlCustCompany.cnlCustCode')}：</th>
								<td width="20%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>
								
								<th width="20%">${app:i18n('cnlCustCompany.custStatus')}：</th>
								<td width="20%">
									<select name="custStatus" id="custStatus" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="custStatuss" var="reqSta">
											<option value="${reqSta.key }">${reqSta.value }</option>
										</s:iterator>
									</select>
									<!-- <input name="custStatus" id="custStatus" class="width_c" /> -->
								</td>
							
								
								
							</tr>
							<tr>
								<th width="20%">${app:i18n('cnlCustCompany.companyName')}：</th>
								<td width="20%"><input name="companyName" id="companyName" class="width_c" /></td>
									
								<th width="20%">${app:i18n('cnlCustCompany.certType')}：</th>
								<td width="20%">
									<select name="certType" id="certType" class="width_c" style="width: 190px">
										<option value="" selected>营业执照</option>
									</select>
									<!-- <input name="certType" id="certType" class="width_c" /> -->
								</td>
								
								<th width="20%">${app:i18n('cnlCustCompany.certNum')}：</th>
								<td width="20%"><input name="certNum" id="certNum" class="width_c" /></td>
								
								<%-- <th width="20%">${app:i18n('cnlCustCompany.cnlCustCode')}：</th>
								<td width="20%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.isSmsVerified')}：</th>
								<td width="20%"><input name="isSmsVerified" id="isSmsVerified" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.companyType')}：</th>
								<td width="20%"><input name="companyType" id="companyType" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.companyCode')}：</th>
								<td width="20%"><input name="companyCode" id="companyCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.contractEffectDate')}：</th>
								<td width="20%"><input name="contractEffectDate" id="contractEffectDate" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.contractExpireDate')}：</th>
								<td width="20%"><input name="contractExpireDate" id="contractExpireDate" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.regTime')}：</th>
								<td width="20%"><input name="regTime" id="regTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.website')}：</th>
								<td width="20%"><input name="website" id="website" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.email')}：</th>
								<td width="20%"><input name="email" id="email" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.companyTel')}：</th>
								<td width="20%"><input name="companyTel" id="companyTel" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.companyRegAddr')}：</th>
								<td width="20%"><input name="companyRegAddr" id="companyRegAddr" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.financeContact')}：</th>
								<td width="20%"><input name="financeContact" id="financeContact" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.financeTel')}：</th>
								<td width="20%"><input name="financeTel" id="financeTel" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.finnaceEmail')}：</th>
								<td width="20%"><input name="finnaceEmail" id="finnaceEmail" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.regCapital')}：</th>
								<td width="20%"><input name="regCapital" id="regCapital" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.regCapitalCurrency')}：</th>
								<td width="20%"><input name="regCapitalCurrency" id="regCapitalCurrency" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.companyFax')}：</th>
								<td width="20%"><input name="companyFax" id="companyFax" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateName')}：</th>
								<td width="20%"><input name="corporateName" id="corporateName" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateCertType')}：</th>
								<td width="20%"><input name="corporateCertType" id="corporateCertType" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateCertNum')}：</th>
								<td width="20%"><input name="corporateCertNum" id="corporateCertNum" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateCertCopy')}：</th>
								<td width="20%"><input name="corporateCertCopy" id="corporateCertCopy" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateCountry')}：</th>
								<td width="20%"><input name="corporateCountry" id="corporateCountry" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateCertExpireDate')}：</th>
								<td width="20%"><input name="corporateCertExpireDate" id="corporateCertExpireDate" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.corporateTel')}：</th>
								<td width="20%"><input name="corporateTel" id="corporateTel" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.country')}：</th>
								<td width="20%"><input name="country" id="country" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.provience')}：</th>
								<td width="20%"><input name="provience" id="provience" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.city')}：</th>
								<td width="20%"><input name="city" id="city" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.district')}：</th>
								<td width="20%"><input name="district" id="district" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.addr')}：</th>
								<td width="20%"><input name="addr" id="addr" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.postcode')}：</th>
								<td width="20%"><input name="postcode" id="postcode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.isValid')}：</th>
								<td width="20%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.industry')}：</th>
								<td width="20%"><input name="industry" id="industry" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.updateTime')}：</th>
								<td width="20%"><input name="updateTime" id="updateTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.creator')}：</th>
								<td width="20%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustCompany.updator')}：</th>
								<td width="20%"><input name="updator" id="updator" class="width_c" /></td> --%>
							</tr>
						</tbody>
					</table>
					<div class="btn_layout">
						<a name="search" id="search" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-find">${app:i18n('global.jsp.search')}</span></span></a>
						<a name="reset" id="reset" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-reset">${app:i18n('global.jsp.reset')}</span></span></a>
						<%-- <a name="export" id="export" class="easyui-linkbutton l-btn" href="#"><span	class="l-btn-left"><span class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a>  --%>
					</div>
				</div>
			</div>
					
			<div class="block">
				<table id="gridTable">
				</table>
				<div id="gridPager"></div>
				<div id="gridToolbar">
					<a id="lookDetail" class="l-btn-plain l-btn m-14"><span class="l-btn-right">
						<span class="l-btn-text icon-view">${app:i18n('cnlTransTrace.lookDetail')}</span></span></a>
						
					<%-- <a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a>  --%>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$(function(){
		getTodayTime();
	})
	//注入时间
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    $("#createStartTime").val(s1);
	    $("#createEndTime").val(s2);
	}
	
	//判断值是否为空
	function isNotNull(d){
		return d!=null&&d!="";
	};
	var cnlCustCode;
	
	$().ready(function() {
		
		var modifyUrl = "cnlCustCompanyModify.action";
		var deleteUrl = "cnlCustCompanyDelete.action";
		var createUrl = "cnlCustCompanyCreate.action";
		var searchUrl = "cnlCustCompanySearch.action";
		var exportUrl = "cnlCustCompanyExport.action";
		var detailUrl = "cnlCustCompanyDetail.action";
		
		$("#search").click(function(){
			$("div.warning").hide();
			var startTime=$("#createStartTime").val();
			var endTime=$("#createEndTime").val(); 
			//进行时间差是否大于一个月的判定
			startTime = startTime.replace(/-/g,'/');
			endTime = endTime.replace(/-/g,'/');
			startTime = new Date(startTime);
			endTime = new Date(endTime);
			var times = startTime.getTime() - endTime.getTime();
			var days = Math.abs(parseInt(times/(1000*60*60*24)));
			if(days>30){
				$("div.warning span").html("开始时间与结束时间相差不能超过30天！");
				$("div.warning").show();
			}else{
				doSearch();
			}
		});
		
		//查看详情
		$("#lookDetail").click( function() {
			$("div.warning span").html("");
			$("div.warning").hide();
			
			var id = $("#gridTable").jqGrid('getGridParam','selrow');
			
			if (id == null) {
				$("div.warning span").html("请选择一行");
				$("div.warning").show();
				return;
			}
			var rowData = $("#gridTable").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
		    cnlCustCode= rowData.cnlCustCode; //获得指定列的值 
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			//调用明细方法
			detail(cnlCustCode);
		});
		
		$("#reset").click(function(){
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
				$("div.warning span").html("请选择一行修改！");
				$("div.warning").show();
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#cnlCustCompanyListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCustCompanyListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			shrinkToFit:false,
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
			          '${app:i18n('cnlCustCompany.cnlCnlCode')}',
			          '${app:i18n('cnlCustCompany.cnlCustCode')}',
			          '${app:i18n('cnlCustCompany.cnlCustType')}',
			          '${app:i18n('cnlCustCompany.custStatus')}',
			          '${app:i18n('cnlCustCompany.companyName')}',
			          '${app:i18n('cnlCustCompany.country')}',
			          '${app:i18n('cnlCustCompany.certType')}',
			          '${app:i18n('cnlCustCompany.certNum')}',
			          '${app:i18n('cnlCustCompany.certExpireDate')}',
			          '${app:i18n('cnlCustCompany.relationTel')}',
			          '${app:i18n('cnlCustCompany.createTime')}',
			          '${app:i18n('cnlCustCompany.creator')}'
			          
			          
					/* '${app:i18n('cnlCustCompany.cnlCustCode')}',
					'${app:i18n('cnlCustCompany.isSmsVerified')}',
					'${app:i18n('cnlCustCompany.companyType')}',
					'${app:i18n('cnlCustCompany.companyCode')}',
					'${app:i18n('cnlCustCompany.contractEffectDate')}',
					'${app:i18n('cnlCustCompany.contractExpireDate')}',
					'${app:i18n('cnlCustCompany.regTime')}',
					'${app:i18n('cnlCustCompany.website')}',
					'${app:i18n('cnlCustCompany.email')}',
					'${app:i18n('cnlCustCompany.companyRegAddr')}',
					'${app:i18n('cnlCustCompany.financeContact')}',
					'${app:i18n('cnlCustCompany.financeTel')}',
					'${app:i18n('cnlCustCompany.finnaceEmail')}',
					'${app:i18n('cnlCustCompany.regCapital')}',
					'${app:i18n('cnlCustCompany.regCapitalCurrency')}',
					'${app:i18n('cnlCustCompany.companyFax')}',
					'${app:i18n('cnlCustCompany.corporateName')}',
					'${app:i18n('cnlCustCompany.corporateCertType')}',
					'${app:i18n('cnlCustCompany.corporateCertNum')}',
					'${app:i18n('cnlCustCompany.corporateCertCopy')}',
					'${app:i18n('cnlCustCompany.corporateCountry')}',
					'${app:i18n('cnlCustCompany.corporateCertExpireDate')}',
					'${app:i18n('cnlCustCompany.corporateTel')}',
					'${app:i18n('cnlCustCompany.provience')}',
					'${app:i18n('cnlCustCompany.city')}',
					'${app:i18n('cnlCustCompany.district')}',
					'${app:i18n('cnlCustCompany.addr')}',
					'${app:i18n('cnlCustCompany.postcode')}',
					'${app:i18n('cnlCustCompany.isValid')}',
					'${app:i18n('cnlCustCompany.industry')}',
					'${app:i18n('cnlCustCompany.updateTime')}',
					'${app:i18n('cnlCustCompany.updator')}', */
					  /*  '${app:i18n('global.jsp.operation')}' */ ],
			colModel:[
						{name : 'id',width : '10px', hidden: true},
						{name : 'cnlCnlCode',width : '200px',index : 'cnlCnlCode'},
						{name : 'cnlCustCode',width : '150px',index : 'cnlCustCode'},
						{name : 'cnlCustType',width : '80px',index : 'cnlCustType'},
						{name : 'custStatus',index : 'custStatus'},
						{name : 'companyName',index : 'companyName'},
						{name : 'country',index : 'country'},
						{name : 'certType',index : 'certType'},
						{name : 'certNum',width : '150px',index : 'certNum'},
						{name : 'certExpireDateString',width : '100px',index : 'certExpireDateString'},
						{name : 'companyTel',index : 'companyTel'},
						{name : 'createTime',index : 'createTime'},
						{name : 'creator',width : '150px',index : 'creator'}
						
						
						/* {name : 'cnlCustCode',index : 'cnlCustCode',width : '20%'},
						{name : 'isSmsVerified',index : 'isSmsVerified',width : '20%'},
						{name : 'companyType',index : 'companyType',width : '20%'},
						{name : 'companyCode',index : 'companyCode',width : '20%'},
						{name : 'contractEffectDate',index : 'contractEffectDate',width : '20%'},
						{name : 'contractExpireDate',index : 'contractExpireDate',width : '20%'},
						{name : 'regTime',index : 'regTime',width : '20%'},
						{name : 'website',index : 'website',width : '20%'},
						{name : 'email',index : 'email',width : '20%'},
						{name : 'companyRegAddr',index : 'companyRegAddr',width : '20%'},
						{name : 'financeContact',index : 'financeContact',width : '20%'},
						{name : 'financeTel',index : 'financeTel',width : '20%'},
						{name : 'finnaceEmail',index : 'finnaceEmail',width : '20%'},
						{name : 'regCapital',index : 'regCapital',width : '20%'},
						{name : 'regCapitalCurrency',index : 'regCapitalCurrency',width : '20%'},
						{name : 'companyFax',index : 'companyFax',width : '20%'},
						{name : 'corporateName',index : 'corporateName',width : '20%'},
						{name : 'corporateCertType',index : 'corporateCertType',width : '20%'},
						{name : 'corporateCertNum',index : 'corporateCertNum',width : '20%'},
						{name : 'corporateCertCopy',index : 'corporateCertCopy',width : '20%'},
						{name : 'corporateCountry',index : 'corporateCountry',width : '20%'},
						{name : 'corporateCertExpireDate',index : 'corporateCertExpireDate',width : '20%'},
						{name : 'corporateTel',index : 'corporateTel',width : '20%'},
						{name : 'provience',index : 'provience',width : '20%'},
						{name : 'city',index : 'city',width : '20%'},
						{name : 'district',index : 'district',width : '20%'},
						{name : 'addr',index : 'addr',width : '20%'},
						{name : 'postcode',index : 'postcode',width : '20%'},
						{name : 'isValid',index : 'isValid',width : '20%'},
						{name : 'industry',index : 'industry',width : '20%'},
						{name : 'updateTime',index : 'updateTime',width : '20%'},
						{name : 'updator',index : 'updator',width : '20%'}, */
					   /*  {name:  'operation',width:'20%',align:'center', search:false,sortable:false,editable:true,title:false}, */
			],
			pager: "#gridPager",
			toolbar: [true,"top"],
			rowNum:50,
			caption: "${app:i18n('cnlCustCompany.cnlCustCompanyListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs'); 
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>"; 
					
					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);

					//all = all + mod;
					all = all + mod;
					$("#gridTable").jqGrid('setRowData',ids[i],{operation:all});
				}
				//判断是否有值
				/* if(!ids.length){
					$("div.warning span").html("未查找到数据！");
					$("div.warning").show();
				} */
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
		});
		
		// input blur event for trimming
		$("#queryField input").input().trim();
		$("#queryField input").input().enter(doSearch);
		
	});
	
	function doSearch(){
		// 设置查询参数
		setQueryCondition();
		$("#gridTable").jqGrid("setGridParam",{page:1});
		$("#gridTable").jqGrid("setGridParam",{url:"cnlCustCompanySearch.action"}).trigger("reloadGrid");
	};
	
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("custType", $("#custType").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("custCode", $("#custCode").val());
		$("#gridTable").setPostDataItem("createStartTime", $("#createStartTime").val());
		$("#gridTable").setPostDataItem("createEndTime", $("#createEndTime").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("companyName", $("#companyName").val());
		$("#gridTable").setPostDataItem("custStatus", $("#custStatus").val());
		
		
		/* $("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("isSmsVerified", $("#isSmsVerified").val());
		$("#gridTable").setPostDataItem("companyType", $("#companyType").val());
		$("#gridTable").setPostDataItem("companyCode", $("#companyCode").val());
		$("#gridTable").setPostDataItem("contractEffectDate", $("#contractEffectDate").val());
		$("#gridTable").setPostDataItem("contractExpireDate", $("#contractExpireDate").val());
		$("#gridTable").setPostDataItem("regTime", $("#regTime").val());
		$("#gridTable").setPostDataItem("website", $("#website").val());
		$("#gridTable").setPostDataItem("email", $("#email").val());
		$("#gridTable").setPostDataItem("companyTel", $("#companyTel").val());
		$("#gridTable").setPostDataItem("companyRegAddr", $("#companyRegAddr").val());
		$("#gridTable").setPostDataItem("financeContact", $("#financeContact").val());
		$("#gridTable").setPostDataItem("financeTel", $("#financeTel").val());
		$("#gridTable").setPostDataItem("finnaceEmail", $("#finnaceEmail").val());
		$("#gridTable").setPostDataItem("regCapital", $("#regCapital").val());
		$("#gridTable").setPostDataItem("regCapitalCurrency", $("#regCapitalCurrency").val());
		$("#gridTable").setPostDataItem("companyFax", $("#companyFax").val());
		$("#gridTable").setPostDataItem("corporateName", $("#corporateName").val());
		$("#gridTable").setPostDataItem("corporateCertType", $("#corporateCertType").val());
		$("#gridTable").setPostDataItem("corporateCertNum", $("#corporateCertNum").val());
		$("#gridTable").setPostDataItem("corporateCertCopy", $("#corporateCertCopy").val());
		$("#gridTable").setPostDataItem("corporateCountry", $("#corporateCountry").val());
		$("#gridTable").setPostDataItem("corporateCertExpireDate", $("#corporateCertExpireDate").val());
		$("#gridTable").setPostDataItem("corporateTel", $("#corporateTel").val());
		$("#gridTable").setPostDataItem("country", $("#country").val());
		$("#gridTable").setPostDataItem("provience", $("#provience").val());
		$("#gridTable").setPostDataItem("city", $("#city").val());
		$("#gridTable").setPostDataItem("district", $("#district").val());
		$("#gridTable").setPostDataItem("addr", $("#addr").val());
		$("#gridTable").setPostDataItem("postcode", $("#postcode").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("industry", $("#industry").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val()); */

	}
	var diaDetail="#dialog-ajax-detail";
	//弹出明细框
	function detail(cnlCustCode){
		var url = "cnlCustCompanyDetail.action?cnlCustCode="+cnlCustCode;
		var title = "查看用户信息";
		$(diaDetail).dialog({width:700,height:600,modal:true});
		openDialog(diaDetail,title,url);
	}
	/* function openDialog(diaDetail, title, url){
		$(diaDetail).html("");
		//设置显示框
		$(diaDetail).dialog({
			autoOpen:false,
			position:'center',
			modal:true,
			resizable: false,
			useSlide:true,
			title:title,
			buttons: {
	        	'${app:i18n("global.jsp.ok")}': function() {
	            	$("#dialog-ajax-detail").dialog('close');
	            }
	        }
		});
		//发送ajax数据
		//$.post(url,{"cnlCustCode":cnlCustCode},function(data){
			//data=data.replace("[]","");
			//$(diaDetail).html(data).dialog('open');
		//},"text");
		$(diaDetail).load(url);
		$(diaDetail).dialog('open');
		$(diaDetail).dialog({
			   close: function(event, ui) { 			   
			  		$(diaDetail).html(" ");		 
	  			}  
		});
	}  */
	function openDialog(dlgId, title, url){
		$(dlgId).html("");
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
		$(dlgId).dialog({
			   close: function(event, ui) { 			   
			  		$(dlgId).html(" ");			   
	  			}  
		});
	}
	function closeDialog(){
		$("#dialog-ajax-detail").dialog('close');
	}
</script>
<!-- 明细表格 -->
<div class="fieldset_container" id="dialog-ajax-detail"></div>
</s:form>
</div>
