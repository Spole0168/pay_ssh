<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {

	$("#corCustPersonalEditForm").validate({
		rules: {
			"corCompound.id": {required: false,stringMaxLength:50,isLegal: true},
			"corCompound.localName": {required: true,stringMaxLength:50,isLegal: true},
			"corCompound.firstName": {required: true,stringMaxLength:50,isLegal: true},
			"corCompound.lastName": {required: true,stringMaxLength:50,isLegal: true},
			"corCompound.dataSource": {required: true,stringMaxLength:20,isLegal: true},
			"corCompound.realNameLeve": {required: false,stringMaxLength:6,isLegal: true},
			"corCompound.custLevel": {required: false,stringMaxLength:6,isLegal: true},
			"corCompound.birthday": {required: true,stringMaxLength:30,isLegal: true},
			"corCompound.gender": {required: true,stringMaxLength:2,isLegal: true},
			"corCompound.isMarried": {required: true,stringMaxLength:2,isLegal: true},
			"corCompound.country": {required: true,stringMaxLength:4,isLegal: true},
			"corCompound.provience": {required: true,stringMaxLength:4,isLegal: true},
			"corCompound.city": {required: true,stringMaxLength:4,isLegal: true},
			"corCompound.district": {required: true,stringMaxLength:4,isLegal: true}, 
			"corCompound.addr": {required: true,stringMaxLength:80,isLegal: true},
			"corCompound.postcode": {required: false,stringMaxLength:10,isLegal: true},
			"corCompound.highestEdu": {required: true,stringMaxLength:2,isLegal: true},
			"corCompound.occupation": {required: false,stringMaxLength:2,isLegal: true},
			"corCompound.jobTitle": {required: false,stringMaxLength:2,isLegal: true},
			"corCompound.employer": {required: false,stringMaxLength:50,isLegal: true},
			"corCompound.phoneNum": {required: false,stringMaxLength:20,isLegal: true},
			"corCompound.workTel": {required: false,stringMaxLength:20,isLegal: true},
			"corCompound.email": {required: false,stringMaxLength:20,isLegal: true},
			"corCompound.spouseLocalName": {required: false,stringMaxLength:20,isLegal: true},
			"corCompound.spousePhonenum": {required: false,stringMaxLength:20,isLegal: true},
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
		window.location = "corCustPersonal/corCustPersonalList.action?loadPageCache=true";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

function doSave(){
	<c:if test="${isModify}">
		$("#corCustPersonalEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#corCustPersonalEditForm").submit(); 
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

<div id="alertDialog"></div>

<s:form id="corCustPersonalEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/corCustPersonal">
<s:hidden name="isModify"/>
<s:hidden name="corCustPersonal.id" id="corCustPersonalId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('corCustPersonal.corCustPersonalEditJsp.title')}</h3>
		</div>
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
							<!-- 添加，修改用户信息 -->
							<!-- ID -->
							<tr hidden="true">
								<th><em>*</em>${app:i18n('corCustPersonal.id') }：</th>
								<td><input name="corCompound.id" id="id" class="width_c" value="${corCompound.id}" maxlength="225"/></td>
							</tr>
							<!-- 客户姓名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.localName') }：</th>
								<td><input name="corCompound.localName" id="localName" class="width_c" value="${corCompound.localName}" maxlength="225"/></td>
							</tr>
							<!-- 英文名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.firstName') }：</th>
								<td><input name="corCompound.firstName" id="firstName" class="width_c" value="${corCompound.firstName}" maxlength="225"/></td>
							</tr>
							<!-- 英文姓 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.lastName') }：</th>
								<td><input name="corCompound.lastName" id="lastName" class="width_c" value="${corCompound.lastName}" maxlength="225"/></td>
							</tr>
							<!-- 出生日期 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.birthday') }：</th>
								<td>
								<input name="corCompound.birthday" id="birthday" class="Wdate" value="${corCompound.birthday}" maxlength="225"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1900-01-01',maxDate:'%y-%M-%d'})"/>
								</td>
							</tr>
							<!-- 性别 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.gender') }：</th>
								<td>
									<select id="gender" name="corCompound.gender" class="width_c" maxlength="225" >
										<option value="1" <c:if test="${'1' eq corCompound.gender}">selected</c:if>>男</option>
										<option value="2" <c:if test="${'2' eq corCompound.gender}">selected</c:if>>女</option>
									</select>
								</td>
							</tr>
							<!-- 是否已婚 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.isMarried') }：</th>
								<td>
									<select id="isMarried" name="corCompound.isMarried" class="width_c" maxlength="225" >
										<option value="1" <c:if test="${'1' eq corCompound.isMarried}">selected</c:if>>未婚</option>
										<option value="2" <c:if test="${'2' eq corCompound.isMarried}">selected</c:if>>已婚</option>
										<option value="3" <c:if test="${'3' eq corCompound.isMarried}">selected</c:if>>离异</option>
										<option value="4" <c:if test="${'4' eq corCompound.isMarried}">selected</c:if>>丧偶</option>
									</select>
								</td>
							</tr>
							<!-- 国籍 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.country') }：</th>
								<td>
									<select id="country" name="corCompound.country" class="width_c" maxlength="225" >
										<option value="1" <c:if test="${'1' eq corCompound.country}">selected</c:if>>中国</option>
										<option value="2" <c:if test="${'2' eq corCompound.country}">selected</c:if>>美国</option>
										<option value="3" <c:if test="${'3' eq corCompound.country}">selected</c:if>>日本</option>
										<option value="4" <c:if test="${'4' eq corCompound.country}">selected</c:if>>英国</option>
									</select>
								</td>
							</tr>
							<!-- 省份 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.provience') }：</th>
								<td><input name="corCompound.provience" id="provience" class="width_c" value="${corCompound.provience}" maxlength="225"/></td>
							</tr>
							<!-- 城市 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.city') }：</th>
								<td><input name="corCompound.city" id="city" class="width_c" value="${corCompound.city}" maxlength="225"/></td>
							</tr>
							<!-- 区域 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.district') }：</th>
								<td><input name="corCompound.district" id="district" class="width_c" value="${corCompound.district}" maxlength="225"/></td>
							</tr>
							<!-- 地址 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.addr') }：</th>
								<td><input name="corCompound.addr" id="addr" class="width_c" value="${corCompound.addr}" maxlength="225"/></td>
							</tr>
							<!-- 邮编 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.postcode') }：</th>
								<td><input name="corCompound.postcode" id="postcode" class="width_c" value="${corCompound.postcode}" maxlength="225"/></td>
							</tr>
							<!-- 最高学历 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.highestEdu') }：</th>
								<td>
									<select id="highestEdu" name="corCompound.highestEdu" class="width_c" maxlength="225" >
										<option value="1" <c:if test="${'1' eq corCompound.highestEdu}">selected</c:if>>高中</option>
										<option value="2" <c:if test="${'2' eq corCompound.highestEdu}">selected</c:if>>专科</option>
										<option value="3" <c:if test="${'3' eq corCompound.highestEdu}">selected</c:if>>本科</option>
										<option value="4" <c:if test="${'4' eq corCompound.highestEdu}">selected</c:if>>硕士</option>
									</select>
								</td>
							</tr>
							<!-- 所属行业 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.occupation') }：</th>
								<td>
									<input name="corCompound.occupation" id="occupation" class="width_c" value="${corCompound.occupation}" maxlength="225"/>
								</td>
							</tr>
							<!-- 职务 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.jobTitle') }：</th>
								<td><input name="corCompound.jobTitle" id="jobTitle" class="width_c" value="${corCompound.jobTitle}" maxlength="225"/></td>
							</tr>
							<!-- 工作单位 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.employer') }：</th>
								<td><input name="corCompound.employer" id="employer" class="width_c" value="${corCompound.employer}" maxlength="225"/></td>
							</tr>
							<!-- 手机号码 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.phoneNum') }：</th>
								<td>
									<input name="corCompound.phoneNum" id="phoneNum" class="width_c" value="${corCompound.phoneNum}" maxlength="225"/>
								</td>
							</tr>
							<!-- 工作电话 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.workTel') }：</th>
								<td><input name="corCompound.workTel" id="workTel" class="width_c" value="${corCompound.workTel}" maxlength="225"/></td>
							</tr>
							<!-- 电子邮件 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.email') }：</th>
								<td><input name="corCompound.email" id="email" class="width_c" value="${corCompound.email}" maxlength="225"/></td>
							</tr>
							<!-- 渠道信息 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.dataSource') }：</th>
								<td><input disabled="true" name="corCompound.dataSource" id="dataSource" class="width_c" value="${corCompound.dataSource}" maxlength="225"/></td>
							</tr>
							<!-- 配偶姓名 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.spouseLocalName') }：</th>
								<td><input name="corCompound.spouseLocalName" id="spouseLocalName" class="width_c" value="${corCompound.spouseLocalName}" maxlength="225"/></td>
							</tr>
							<!-- 配偶联系电话 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.spousePhoneNum') }：</th>
								<td><input name="corCompound.spousePhonenum" id="spousePhonenum" class="width_c" value="${corCompound.spousePhonenum}" maxlength="225"/></td>
							</tr>
							<!-- 客户实名级别 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.realNameLeve') }：</th>
								<td><input disabled="true"  name="corCompound.realNameLeve" id="realNameLeve" class="width_c" value="${corCompound.realNameLeve}" maxlength="225"/></td>
							</tr>
							<!-- 客户等级 -->
							<tr>
								<th><em>*</em>${app:i18n('corCustPersonal.custLevel') }：</th>
								<td><input disabled="true" name="corCompound.custLevel" id="custLevel" class="width_c" value="${corCompound.custLevel}" maxlength="225"/></td>
							</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="saveOrUpdate.action"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="corCustPersonalList.action"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
