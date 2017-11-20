<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	$().ready(function() {

		var modifyUrl = "corReservedFundAcntModify.action";
		var deleteUrl = "corReservedFundAcntDelete.action";
		var createUrl = "corReservedFundAcntCreate.action";
		var searchUrl = "corReservedFundAcntSearch.action";
		var exportUrl = "corReservedFundAcntExport.action";
		
		$("#search").click(function(){
             var ok = true;
			
			$("div.warning span").html("");
			$("div.warning").hide();
			if(validform().form()){
				
			}else{
				ok = false;
			}
			
			// 校验银行卡号合法信息
			if($("#bankCardNum").val() != null && $("#bankCardNum").val() != ""){
				var zz = /^[0-9]*$/;
				if(zz.test($("#bankCardNum").val())){
				}else{
					$("div.warning span").html("请输入正确的银行卡号信息  ！");
					$("div.warning").show();
					ok = false;
				}
			}
			if(ok){
				doSearch();
			}
		});
		
		
		// 页面表达校验
		function validform(){
			return $("#corReservedFundAcntListForm").validate({
				rules : {
				},
				invalidHandler : function(e, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						var message = "请填写正确的查询信息！";
						$("div.warning span").html(message);
						$("div.warning").show();
					} else {
						$("div.warning").hide();
					}
				}
			});
		}


		$("#reset").click(function(){
			$("div.warning span").html("");
			$("div.warning").hide();
			
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
			$("#corReservedFundAcntListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#corReservedFundAcntListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			rowList:[50,100,200],
			shrinkToFit:false,
			colNames:['ID', 
					'${app:i18n('corReservedFundAcnt.bankAcntCode')}',
					'${app:i18n('corReservedFundAcnt.cnlCustCode')}',
					'${app:i18n('corReservedFundAcnt.country')}',
					'${app:i18n('corReservedFundAcnt.custName')}',
					'${app:i18n('corReservedFundAcnt.certType')}',
					'${app:i18n('corReservedFundAcnt.certNum')}',
					'${app:i18n('corReservedFundAcnt.bankName')}',
					'${app:i18n('corReservedFundAcnt.bankCode')}',
					'${app:i18n('corReservedFundAcnt.bankBranchCode')}',
					'${app:i18n('corReservedFundAcnt.bankCardNum')}',
					'${app:i18n('corReservedFundAcnt.bankCardType')}',
					'${app:i18n('corReservedFundAcnt.status')}',
					'${app:i18n('corReservedFundAcnt.currency')}',
					'${app:i18n('corReservedFundAcnt.acntCategory')}',
					'${app:i18n('corReservedFundAcnt.acntType')}',
					'${app:i18n('corReservedFundAcnt.createTime')}',
					'${app:i18n('corReservedFundAcnt.updateTime')}',
					'${app:i18n('corReservedFundAcnt.isValid')}',
					'${app:i18n('corReservedFundAcnt.creator')}',
					'${app:i18n('corReservedFundAcnt.updator')}',
					'${app:i18n('corReservedFundAcnt.lastTransTime')}',
					'${app:i18n('corReservedFundAcnt.frozenAmoumt')}',
					'${app:i18n('corReservedFundAcnt.avaliableAmount')}',
					'${app:i18n('corReservedFundAcnt.bankNum')}',
					'${app:i18n('corReservedFundAcnt.balance')}',
					   '${app:i18n('global.jsp.operation')}' ],
			colModel:[
						{name : 'id', hidden: true},
						{name : 'bankAcntCode',index : 'bankAcntCode',hidden: true},
						{name : 'cnlCustCode',index : 'cnlCustCode',hidden: true},
						{name : 'country',index : 'country',hidden: true},
						{name : 'custName',index : 'custName'},
						{name : 'certType',index : 'certType',hidden: true},
						{name : 'certNum',index : 'certNum',hidden: true},
						{name : 'bankName',index : 'bankName'},
						{name : 'bankCode',index : 'bankCode',hidden: true},
						{name : 'bankBranchCode',index : 'bankBranchCode',hidden: true},
						{name : 'bankCardNum',index : 'bankCardNum'},
						{name : 'bankCardType',index : 'bankCardType',hidden: true},
						{name : 'status',index : 'status'},
						{name : 'currency',index : 'currency'},
						{name : 'acntCategory',index : 'acntCategory',hidden: true},
						{name : 'acntType',index : 'acntType',hidden: true},
						{name : 'createTime',index : 'createTime',hidden: true},
						{name : 'updateTime',index : 'updateTime',hidden: true},
						{name : 'isValid',index : 'isValid',hidden: true},
						{name : 'creator',index : 'creator',hidden: true},
						{name : 'updator',index : 'updator',hidden: true},
						{name : 'lastTransTime',index : 'lastTransTime'},
						{name : 'frozenAmoumt',index : 'frozenAmoumt'},
						{name : 'avaliableAmount',index : 'avaliableAmount'},
						{name : 'bankNum',index : 'bankNum',hidden: true},
						{name : 'balance',index : 'balance'},
					    {name:  'operation',width:'10%',align:'center', search:false,sortable:false,editable:true,title:false,hidden: true},
			],
			pager: "#gridPager",
			/* toolbar: [true,"top"], */
			caption: "${app:i18n('corReservedFundAcnt.corReservedFundAcntListJsp.tableTitle')}",
			gridComplete: function() {
				var ids = $("#gridTable").jqGrid('getDataIDs');
				/* if(ids.length <= 0){
					$("div.warning").html("没有记录！");
					$("div.warning").show();
				} */
				for(var i=0;i < ids.length;i++) {
					var all = "";
					var mod = "<a href='#' class='icon-edit m-r ' onclick='window.location=\"#modifyUrl?loadPageCache=true&id=#id\"'><em>${app:i18n('global.jsp.modify')}</em></a>";

					mod = mod.replace(/#modifyUrl/, modifyUrl).replace(/#id/, ids[i]);
					
					var id = ids[i];
					var rowData = $("#gridTable").getRowData(id);

					all = all + mod;

					$("#gridTa ble").jqGrid('setRowData',ids[i],{operation:all});
				}
			}
		});

		$("#gridTable").gridUtil().customizeColumn();
		/* $("#gridToolbar").appendTo($("#t_gridTable")); */

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
		$("#gridTable").jqGrid("setGridParam",{url:"corReservedFundAcntSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		
		$("#gridTable").setPostDataItem("bankAcntCode", $("#bankAcntCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("country", $("#country").val());
		$("#gridTable").setPostDataItem("custName", $("#custName").val());
		$("#gridTable").setPostDataItem("certType", $("#certType").val());
		$("#gridTable").setPostDataItem("certNum", $("#certNum").val());
		$("#gridTable").setPostDataItem("bankName", $("#bankName").val());
		$("#gridTable").setPostDataItem("bankCode", $("#bankCode").val());
		$("#gridTable").setPostDataItem("bankBranchCode", $("#bankBranchCode").val());
		$("#gridTable").setPostDataItem("bankCardNum", $("#bankCardNum").val());
		$("#gridTable").setPostDataItem("bankCardType", $("#bankCardType").val());
		$("#gridTable").setPostDataItem("status", $("#status").val());
		$("#gridTable").setPostDataItem("currency", $("#currency").val());
		$("#gridTable").setPostDataItem("acntCategory", $("#acntCategory").val());
		$("#gridTable").setPostDataItem("acntType", $("#acntType").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("lastTransTime", $("#lastTransTime").val());
		$("#gridTable").setPostDataItem("frozenAmoumt", $("#frozenAmoumt").val());
		$("#gridTable").setPostDataItem("avaliableAmount", $("#avaliableAmount").val());
		$("#gridTable").setPostDataItem("bankNum", $("#bankNum").val());
		$("#gridTable").setPostDataItem("balance", $("#balance").val());
		
	}
</script>

<s:form id="corReservedFundAcntListForm" method="post"
	action="corReservedFundAcntList.action">
	<div class="layout">
		<div class="block m-b">
			<div class="block_title">
				<h3>${app:i18n('corReservedFundAcnt.corReservedFundAcntListJsp.headTitle')}</h3>
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
									<th width="10%">${app:i18n('corReservedFundAcnt.bankNames')}：</th>
									<td width="30%"><select name="bankName" id="bankName"
										class="width_c">
											<option value="">请选择</option>
											<s:iterator value="bankNameList" var="bankName">
												<option value="${bankName.key}">${bankName.value}</option>
											</s:iterator>
									</select></td>

									<th width="10%">${app:i18n('corReservedFundAcnt.bankCardNum')}：</th>
									<td width="30%"><input name="bankCardNum" id="bankCardNum"
										class="width_c" /></td>

									<th width="10%">${app:i18n('corReservedFundAcnt.custName')}：</th>
									<td width="30%"><input name="custName" id="custName"
										class="width_c" maxlength="50"/></td>
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
							<%-- <a name="export" id="export" class="easyui-linkbutton l-btn"
								href="#"><span class="l-btn-left"><span
									class="l-btn-text icon-excel">${app:i18n('global.jsp.export')}</span></span></a> --%>
						</div>
					</div>
				</div>

				<div class="block">
					<table id="gridTable">
					</table>
					<div id="gridPager"></div>
					<%-- <div id="gridToolbar">
						 <a id="create" class="l-btn-plain l-btn m-l4"><span
							class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
					</div> --%>
				</div>
			</div>
		</div>
	</div>
</s:form>
