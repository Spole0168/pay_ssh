<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>



<div id="alertDialog"></div>

<s:form id="cnlTransTraceEditForm" method="post" action="saveHandleStatus.action?loadPageCache=true" namespace="/cnlTransTrace">
<s:hidden name="isModify"/>
<s:hidden name="cnlTransTrace.id" id="cnlTransTraceId"/>
<div class="layout">
	<div class="block m-b">
		<%-- <div class="block_title">
			<h3>${app:i18n('cnlTransTrace.cnlTransTraceEditJsp.title')}</h3>
		</div> --%>
		<div class="block_container">
			<div class="fieldset_border fieldset_bg m-b">
				<div class="fieldset_container">
					<form id="validate_form">
						<div class="warning" style="display:none;">
							<span></span>
						</div>
						<table cellpadding="0" cellspacing="0" class="table_form">
							<thead>
							</thead>
							<tfoot>
							</tfoot>
							<tbody>

							<tr hidden="true">
								<th><em>*</em>${app:i18n('cnlTransTrace.id') }：</th>
								<td><input name="id" id="id" class="width_c" value="${cnlTransTrace.id}" maxlength="225"/></td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('cnlTransTrace.handleStatus') }：</th>
								<td>
									<select id="handleStatus" name="handleStatus">
										<option value="">---请选择---</option>
										<option value="01">已处理</option>
										<option value="02">未处理</option>
									</select>
								</td>
							</tr>

							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$().ready(function() {
		
			$("#cnlTransTraceEditForm").validate({
				rules: {
					"cnlTransTrace.handleStatus": {required: true,stringMaxLength:6,isLegal: true},
				},
				invalidHandler: function(e, validator) {
					var errors = validator.numberOfInvalids();
					if (errors) {
						var message = "请正确填写表单信息！";
						$("div.warning span").html(message);
						$("div.warning").show();
					} else {
						$("div.warning").hide();
					}
				}
			});
			var handleStatus = $("#handleStatus").val();
			$("#save").click(function(){
				$.boxUtil.confirm(
					'请确认是否保存信息？', 
					null, 
					function(){
						doSave();
					}, 
					function(){
						//return false;
					});
				return false;
			
			});
			
			  $("#undo").click(function(){
				$("#handleAuditDiv").dialog('close');
			}); 
		
			// 初始化所有只读字段的样式
			$("* [readonly]").addClass("read_only");
			$("input[readonly]").input().disableBackSpace();
		
		});	
		
		function doSave(){
			<c:if test="${isModify}">
				$("#cnlTransTraceEditForm").submit(); 
			</c:if>
			<c:if test="${isModify == false}">
				$("#cnlTransTraceEditForm").submit(); 
			</c:if>
		}
		//alert框
		function showAlertDialog(alertTitle, alertContent){
			$('#alertDialog').dialog('destroy');
		    $('#alertDialog').show();
		    $('#alertDialog').html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+ alertContent +'</p>');
		    
		    $("#alertDialog").dialog({
		        resizable: false,
		        modal: true,
		        overlay: {
		            backgroundColor: '#000',
		            opacity: 0.9
		        },
		        title:alertTitle,
		        buttons: {
		        	'${app:i18n("global.jsp.ok")}': function() {
		            	$('#alertDialog').dialog('close');
		            }
		        }
		    });
		
		}
		
	</script>
</s:form>
