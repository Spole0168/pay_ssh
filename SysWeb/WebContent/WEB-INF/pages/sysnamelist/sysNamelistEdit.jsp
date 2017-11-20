<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
$().ready(function() {
	//赋值 黑名单类型 	
	var nlType=$("#sysNamelistnlType").val();
	if(nlType){
		
		$("#nlType").find("option[value='"+nlType+"']").attr("selected",true);
		
	} 
	
	$("#sysNamelistEditForm").validate({
		rules: {
			"sysNamelist.nlId": {required: true,stringMaxLength:50,isLegal: true},
			"sysNamelist.nlType": {required: true,stringMaxLength:200,isLegal: true},
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
		var falg=true;
		//黑名单类型
		var type=$("#nlType").val();
		
		//黑名单值
		var obj=$("#nlId").val();  
		
		//id
		var id=$("#sysNamelistId").val();
	 
		console.info(isSame(type,obj,id,falg));
		//判断黑名单中是否存在此值
		if(!isSame(type,obj,id,falg)){
			
			return;
		}else{
		 
		$("div.warning").hide();	
		
		$.boxUtil.confirm(
			'请确认是否保存信息？', 
			null, 
			function(){
				doSave();
			}, 
			function(){
				//return false;
			});
	} 
		return false;
	});
	$("#undo").click(function(){
		window.location = "sysNamelist/sysNamelistList.action";
	});

	// 初始化所有只读字段的样式
	$("* [readonly]").addClass("read_only");
	$("input[readonly]").input().disableBackSpace();

});	

//判断添加的黑名单是否重复
function  isSame(type,obj,id,falg){
	var url="sysNamelist/sysNamelistIsSame.action";
	var ismodify="${isModify}";
 
	if(ismodify=='true'){
		url="sysNamelist/sysNamelistIsSameModify.action";
	}
	  $.ajax({
          type: "post",
          url: url,
          data: {nlType:type,nlId:obj,id:id},
          dataType: "json",
          async:false,
          success: function(data){
         	 
         	 var msg=data.message;
         	 console.info(msg+"========"+data);
                if(msg){
             	   
             	   $("div.warning span").html(msg);
             	   
    			   $("div.warning").show();
    				   
             	   falg=false;
             	   
                }else{
             	   
             	   $("div.warning").hide();
             	   
                }
            }
      });
	  return falg;
	
}


function doSave(){
	<c:if test="${isModify}">
		$("#sysNamelistEditForm").submit(); 
	</c:if>
	<c:if test="${isModify == false}">
		$("#sysNamelistEditForm").submit(); 
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

<s:form id="sysNamelistEditForm" method="post" action="saveOrUpdate.action?loadPageCache=true" namespace="/sysNamelist">
<s:hidden name="isModify"/>
<s:hidden name="sysNamelist.id" id="sysNamelistId"/>
<div class="layout">
	<div class="block m-b">
		<div class="block_title">
			<h3>${app:i18n('sysNamelist.sysNamelistEditJsp.title')}</h3>
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
 
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.nlType') }：</th>
								<td>
								
								 
							 	<input type="hidden" value="${sysNamelist.nlType}"  id="sysNamelistnlType"/> 
								<select name="sysNamelist.nlType" id="nlType" class="width_c"  style="width:190px;">
									<option value="">请选择</option>
									  	<s:iterator  value="nlTypeList" var="nlType">
										 <c:if test="${nlType.key!='05'}">  
											<option value="${nlType.key}">${nlType.value}</option>
									  	</c:if>  
										</s:iterator>
								</select>
								
								</td>
							</tr>
							<tr>
								<th><em>*</em>${app:i18n('sysNamelist.nlId') }：</th>
								<td><input name="sysNamelist.nlId" id="nlId" class="width_c" value="${sysNamelist.nlId}" maxlength="225"/></td>
							</tr>
							<tr>
								<th>${app:i18n('sysNamelist.nlDesc') }：</th>
								<td><input name="sysNamelist.nlDesc" id="nlDesc" class="width_c" value="${sysNamelist.nlDesc}" maxlength="225"/></td>
							</tr>
							 
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div id="tabs-1">
				<a id="save" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span></a>
				<a id="undo" class="easyui-linkbutton l-btn" href="#" onclick="javascript:history.back(-1);"><span class="l-btn-left"><span class="l-btn-text icon-undo">${app:i18n('global.jsp.back')}</span></span></a>
			</div>
		</div>
	</div>
</div>
</s:form>
