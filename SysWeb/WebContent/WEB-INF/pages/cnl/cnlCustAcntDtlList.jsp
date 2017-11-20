<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script>
	$(function(){
		getTodayTime();
	})
	function getTodayTime(){
		var d = new Date();
	    function addzero(v) {if (v < 10) return '0' + v;return v.toString();}
	    var date=d.getFullYear().toString() +"-"+ addzero(d.getMonth() + 1) +"-" + addzero(d.getDate())
	    var s1 = date +" 00:00:00";
	    var s2 = date +" 23:59:59";
	    $("#startTransTime").val(s1);
	    $("#endTransTime").val(s2);
	}
</script>
<!-- 包含简单表格的JavaScript -->
<script type="text/javascript" charset="UTF-8">
	//判断值是否为空
	function isNotNull(d){
		return d!=null&&d!="";
	};
	$().ready(function() {
		var modifyUrl = "cnlCustAcntDtlModify.action";
		var deleteUrl = "cnlCustAcntDtlDelete.action";
		var createUrl = "cnlCustAcntDtlCreate.action";
		var searchUrl = "cnlCustAcntDtlSearch.action";
		var exportUrl = "cnlCustAcntDtlExport.action";
		
		$("#search").click(function(){
			$("div.warning").hide();
			var startTime=$("#startTransTime").val();
			var start =isNotNull(startTime);
			var endTime=$("#endTransTime").val(); 
			var end=isNotNull(endTime);
			//进行时间是否为空的判定
			if(start&&end){
				var today=new Date();
				//进行时间差是否大于一个月的判定
				startTime = startTime.replace(/-/g,'/');
				endTime = endTime.replace(/-/g,'/');
				startTime = new Date(startTime);
				endTime = new Date(endTime);
				var times = startTime.getTime() - endTime.getTime();
				var days = Math.abs(parseInt(times/(1000*60*60*24)));
				var t=today.getTime()-startTime.getTime();
				var searchDays=Math.abs(parseInt(t/(1000*60*60*24)));
				if(times>=0){
					$("div.warning span").html("入账结束时间必须大于入账开始时间！");
					$("div.warning").show();
					return;
				}
				//判断是否查询在三个月的数据
				if(searchDays>90){
					$("div.warning span").html("请查询90天以内的入账时间！");
					$("div.warning").show();
					return;
				};
				//判断开始时间和结束时间是否相差一个月
				if(days>30){
					$("div.warning span").html("入账开始时间与入账结束时间相差不能超过30天！");
					$("div.warning").show();
					return;
				}else{
					doSearch();
				}
			}else{
				$("div.warning span").html("请输入时间！");
				$("div.warning").show();
			}
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
				alert("全部一行修改");
				return;
			}
			//$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false}); 
			$("#cnlCustAcntDtlListForm").attr("action",deleteUrl+"?id="+gr[0]);
			$("#cnlCustAcntDtlListForm").submit();
		});
		
		$("#gridTable").gridUtil().simpleGrid({
			url: "",
			editurl:"#deleteUrl",
			shrinkToFit:false,
			sortname:'id', // 默认的排序字段
			sortorder:'desc',
			multiselect:false,
			colNames:['ID', 
			         '${app:i18n('cnlCustAcntDtl.cnlCnlCode')}',
			         '${app:i18n('cnlCustAcntDtl.cnlCustCode')}',
			         '${app:i18n('cnlCustAcntDtl.custName.cyy')}',
			         '${app:i18n('cnlCustAcntDtl.acntType')}',
			         '${app:i18n('cnlCustAcntDtl.currency')}',
			         '${app:i18n('cnlCustAcntDtl.direction.cyy')}',
			         '${app:i18n('cnlCustAcntDtl.amount')}',
			         '${app:i18n('cnlCustAcntDtl.balance')}',
			         '${app:i18n('cnlCustAcntDtl.transNum')}',
			         '${app:i18n('cnlCustAcntDtl.trnasTime')}',
			         '${app:i18n('cnlCustAcntDtl.createTime.cyy')}',
			         '${app:i18n('cnlCustAcntDtl.cnlAcntCode.cyy')}',
			         '${app:i18n('cnlCustAcntDtl.voucherNum')}',
			         '${app:i18n('cnlCustAcntDtl.comments')}' 
			         /*,
			          '${app:i18n('cnlCustAcntDtl.acntDtlCode')}',
					'${app:i18n('cnlCustAcntDtl.isPrinted')}'
					'${app:i18n('cnlCustAcntDtl.cnlCustCode')}',
					'${app:i18n('cnlCustAcntDtl.transDate')}',
					'${app:i18n('cnlCustAcntDtl.transType')}',
					'${app:i18n('cnlCustAcntDtl.isValid')}',
					'${app:i18n('cnlCustAcntDtl.creator')}',
					'${app:i18n('cnlCustAcntDtl.updator')}',
					'${app:i18n('cnlCustAcntDtl.updateTime')}',
					   '${app:i18n('global.jsp.operation')}'*/
					   ], 
			colModel:[	
						{name : 'id', hidden: true},	
						{name : 'cnlCnlCode',index : 'cnlCnlCode',width : '200px'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '200px'},
						{name : 'localName',index : 'localName',width : '200px'},
						{name : 'acntType',index : 'acntType',width : '80px'},
						{name : 'currency',index : 'currency',width : '200px'},
						{name : 'direction',index : 'direction',width : '50px'},
						{name : 'amount',index : 'amount',width : '100px'},
						{name : 'balance',index : 'balance',width : '100px'},
						{name : 'transNum',index : 'transNum',width : '150px'},
						{name : 'trnasTime',index : 'trnasTime',width : '120px'},
						{name : 'createTime',index : 'createTime',width : '120px'},
						{name : 'cnlAcntCode',index : 'cnlAcntCode',width : '220px'},
						{name : 'voucherNum',index : 'voucherNum',width : '230px'}, 
						{name : 'comments',index : 'comments',width : '250px'}
						/* ,
						{name : 'acntDtlCode',index : 'acntDtlCode',width : '20%'},
						{name : 'isPrinted',index : 'isPrinted',width : '20%'},
						{name : 'cnlCustCode',index : 'cnlCustCode',width : '20%'}, 
						{name : 'transDate',index : 'transDate',width : '20%'},
						{name : 'transType',index : 'transType',width : '20%'},
						{name : 'isValid',index : 'isValid',width : '20%'},
						{name : 'creator',index : 'creator',width : '20%'},
						{name : 'updator',index : 'updator',width : '20%'},
						{name : 'updateTime',index : 'updateTime',width : '20%'},
					    {name:  'operation',width:'20%',align:'center', search:false,sortable:false,editable:true,title:false},*/
			],
			pager: "#gridPager",
			rowList:[50,100,200],
			rowNum:50,
			/* toolbar: [true,"top"], */
			caption: "${app:i18n('cnlCustAcntDtl.cnlCustAcntDtlListJsp.tableTitle')}",
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
				//判断是否有值
				/* if(!ids.length){
					$("div.warning span").html("未查找到数据！");
					$("div.warning").show();
				} */
			}
		});

		$("#gridTable").gridUtil().customizeColumn();
	/* 	$("#gridToolbar").appendTo($("#t_gridTable")); */

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
		$("#gridTable").jqGrid("setGridParam",{url:"cnlCustAcntDtlSearch.action"}).trigger("reloadGrid");
	}
	function setQueryCondition(){
		// 设置查询参数
		$("#gridTable").setPostDataItem("acntDtlCode", $("#acntDtlCode").val());
		$("#gridTable").setPostDataItem("cnlCnlCode", $("#cnlCnlCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("cnlCustType", $("#cnlCustType").val());
		$("#gridTable").setPostDataItem("acntType", $("#acntType").val());
		$("#gridTable").setPostDataItem("transNum", $("#transNum").val());
		$("#gridTable").setPostDataItem("currency", $("#currency").val());
		$("#gridTable").setPostDataItem("voucherNum", $("#voucherNum").val());
		$("#gridTable").setPostDataItem("direction", $("#direction").val());
		$("#gridTable").setPostDataItem("isPrinted", $("input:radio:checked").val());
		$("#gridTable").setPostDataItem("acntDtlCode", $("#acntDtlCode").val());
		$("#gridTable").setPostDataItem("localName", $("#localName").val());
		$("#gridTable").setPostDataItem("startTransTime", $("#startTransTime").val());
		$("#gridTable").setPostDataItem("endTransTime", $("#endTransTime").val());
		$("#gridTable").setPostDataItem("localName", $("#localName").val());
		/* 
		$("#gridTable").setPostDataItem("cnlAcntCode", $("#cnlAcntCode").val());
		$("#gridTable").setPostDataItem("cnlCustCode", $("#cnlCustCode").val());
		$("#gridTable").setPostDataItem("amount", $("#amount").val());
		$("#gridTable").setPostDataItem("balance", $("#balance").val());
		$("#gridTable").setPostDataItem("transDate", $("#transDate").val());
		$("#gridTable").setPostDataItem("transTime", $("#transTime").val());
		$("#gridTable").setPostDataItem("comments", $("#comments").val());
		$("#gridTable").setPostDataItem("transType", $("#transType").val());
		$("#gridTable").setPostDataItem("isValid", $("#isValid").val());
		$("#gridTable").setPostDataItem("creator", $("#creator").val());
		$("#gridTable").setPostDataItem("updator", $("#updator").val());
		$("#gridTable").setPostDataItem("createTime", $("#createTime").val());
		$("#gridTable").setPostDataItem("updateTime", $("#updateTime").val()); */

	}
</script>

<s:form id="cnlCustAcntDtlListForm" method="post" action="cnlCustAcntDtlList.action">
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('cnlCustAcntDtl.cnlCustAcntDtlListJsp.headTitle')}</h3>
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
							<%-- <tr style="visibility:hidden">
	
								<th width="20%">${app:i18n('cnlCustAcntDtl.acntDtlCode')}：</th>
								<td width="20%"><input name="acntDtlCode" id="acntDtlCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlAcntCode')}：</th>
								<td width="20%"><input name="cnlAcntCode" id="cnlAcntCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlCustCode')}：</th>
								<td width="20%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.amount')}：</th>
								<td width="20%"><input name="amount" id="amount" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.direction')}：</th>
								<td width="20%"><input name="direction" id="direction" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.balance')}：</th>
								<td width="20%"><input name="balance" id="balance" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.transDate')}：</th>
								<td width="20%"><input name="transDate" id="transDate" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.transTime')}：</th>
								<td width="20%"><input name="transTime" id="transTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.comments')}：</th>
								<td width="20%"><input name="comments" id="comments" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.isValid')}：</th>
								<td width="20%"><input name="isValid" id="isValid" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.creator')}：</th>
								<td width="20%"><input name="creator" id="creator" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.updator')}：</th>
								<td width="20%"><input name="updator" id="updator" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.createTime')}：</th>
								<td width="20%"><input name="createTime" id="createTime" class="width_c" /></td>

								<th width="20%">${app:i18n('cnlCustAcntDtl.updateTime')}：</th>
								<td width="20%"><input name="updateTime" id="updateTime" class="width_c" /></td>
							</tr>
							 --%>
							<tr>	
								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlCnlCode')}：</th>
								<td width="20%">
									<select name="cnlCnlCode" id="cnlCnlCode" class="width_c" style="width: 190px">
										<s:iterator value="cnlCnlCodes" var="ccd">
											<option value="${ccd.key }">${ccd.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="20%">${app:i18n('cnlCustAcntDtl.direction.cyy')}：</th>
								<td width="20%">
									<select name=direction id="direction" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="directions" var="direct">
											<option value="${direct.key }">${direct.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="20%">${app:i18n('cnlCustAcntDtl.transNum')}：</th>
								<td width="20%"><input name="transNum" id="transNum" class="width_c" /></td>
								
							</tr>
							<tr>						
								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlCustCode')}：</th>
								<td width="20%"><input name="cnlCustCode" id="cnlCustCode" class="width_c" /></td>
								
								<th width="20%"><em>*</em>${app:i18n('cnlCustAcntDtl.startTransTime')}：</th>
								<td width="20%">
									<!-- <input name="startTime" id="startTime" class="width_c" /> -->
									<input name="startTransTime" id="startTransTime" class="Wdate" value="${cnlReqTrans.startTransTime}"  readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTransTime\')}'})" style="width: 190px"/> 
								</td>
								
								<th width="20%"><em>*</em>${app:i18n('cnlCustAcntDtl.endTransTime')}：</th>
								<td width="20%">
									<!-- <input name="endTime" id="endTime" class="width_c" /> -->
									<input name="endTransTime" id="endTransTime" class="Wdate" value="${cnlReqTrans.endTransTime}" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTransTime\')}'})" style="width: 190px"/>
								</td>
							</tr>
							<tr>
								<%-- <th width="20%">${app:i18n('cnlCustAcntDtl.acntDtlCode')}：</th>
								<td width="20%"><input name="acntDtlCode" id="acntDtlCode" class="width_c" /></td> --%>
								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlCustName')}：</th>
								<td width="20%">
									<input name="localName" id="localName" class="width_c" />
								</td>
								
								<th width="20%">${app:i18n('cnlCustAcntDtl.currency')}：</th>
								<td width="20%">
									<select name="currency" id="currency" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="currencys" var="curr">
											<option value="${curr.key }">${curr.value }</option>
										</s:iterator>
										<!-- <option value="">人民币</option> -->
									</select>
								</td>	
							
								<th width="20%">${app:i18n('cnlCustAcntDtl.voucherNum')}：</th>
								<td width="20%"><input name="voucherNum" id="voucherNum" class="width_c" /></td>
							</tr>
							<tr>
								<th width="20%">${app:i18n('cnlCustAcntDtl.cnlCustType')}：</th>
								<td width="20%">
									<select name="cnlCustType" id="cnlCustType" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="custTypes" var="ct">
											<option value="${ct.key }">${ct.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<th width="20%">${app:i18n('cnlCustAcntDtl.acntType')}：</th>
								<td width="20%">
									<select name="acntType" id="acntType" class="width_c" style="width: 190px">
										<option value="">请选择</option>
										<s:iterator value="acntTypes" var="acnty">
											<option value="${acnty.key }">${acnty.value }</option>
										</s:iterator>
									</select>
								</td>
								
								<%-- <th width="20%">${app:i18n('cnlCustAcntDtl.isPrinted')}：</th>
								<td width="20%">
									<input type="radio" name="isPrinted" checked="checked" value=""/>全部
									<input type="radio" name="isPrinted" value="02"/>已打印
									<input type="radio" name="isPrinted" value="01"/>未打印
								</td> --%>
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
				<%-- <div id="gridToolbar">
					<a id="create" class="l-btn-plain l-btn m-l4" ><span class="l-btn-left"><span class="l-btn-text icon-add">${app:i18n('global.jsp.create')}</span></span></a> 
				</div> --%>
			</div>
		</div>
	</div>
</div>
</s:form>
