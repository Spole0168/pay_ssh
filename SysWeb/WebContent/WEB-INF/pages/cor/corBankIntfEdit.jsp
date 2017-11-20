<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type= "text/javascript">
$().ready(function() {

     $("#corBankIntfEditForm").validate({
          rules: {
              
           "corBankInfoDTO.corBankIntf.bankInnerCode" : {
              required: true,
              stringMaxLength:32,
              isLegal: true
              },
           "corBankInfoDTO.corBankSetting.bankName" :{
               //银行名称必填
              required: true,
              stringMaxLength:32,
              isLegal: true
              
          },
           "corBankInfoDTO.corBankSetting.bankLevel" :{
               //银行分层必填
              required: true,
              stringMaxLength:32,
              isLegal: true
          },
           "corBankInfoDTO.corBankSetting.country" :{
               //国家必填
              required: true,
              stringMaxLength:32,
              isLegal: true
          },
           "corBankInfoDTO.corBankSetting.contractEffectDate" :{
               //合同开始时间 必填
              required: true,
              stringMaxLength:32,
              isLegal: true
              
          },
           "corBankInfoDTO.corBankSetting.contractExpireDate" :{
               //合同结束时间必填
              required: true,
              stringMaxLength:32,
              isLegal: true
              
          },
           "corBankInfoDTO.corBankIntf.techSupportPhonenum" :{
              isTelNo: true
          },
           "corBankInfoDTO.corBankIntf.businessSupportPhonenum" :{
              
              isTelNo: true
              },
           "corBankInfoDTO.corBankIntf.techSupportEmail" :{
          
              email: true
              },
           "corBankInfoDTO.corBankIntf.businessSupportEmail" :{
          
              email: true
              },
           "corBankInfoDTO.corBankSetting.bankNum" :{
               //银行联号 必填
              required: true,
              stringMaxLength:40,
              isLegal: true
          },
           "corBankInfoDTO.corBankSetting.swiftCode" :{
               //SWITF 代码 必填
              required: true,
              stringMaxLength:40,
              isLegal: true
              
          },
           "corBankInfoDTO.corBankSetting.branchCode" :{
               //BRANCH 代码 必填
              required: true,
              stringMaxLength:40,
              isLegal: true
              
          },
           "corBankInfoDTO.corBankSetting.ngsnCode" :{
              
               //ngsn 代码 必填
              required: true,
              stringMaxLength:40,
              isLegal: true
              
          }
          },   
          invalidHandler: function(e, validator) {
               var errors = validator.numberOfInvalids();
               if (errors) {
                    var message = "请输入必填项！" ;
                   $( "div.warning span").html(message);
                   $( "div.warning").show();
                        
              } else {
                   $( "div.warning").hide();
              }
          }
     });

     
     //银行
     var bankname=$( "#bname").val();
     console.info(bankname);
     if(bankname){
          $( "#bankName").find("option[value='" +bankname+"']").attr( "selected",true );
     }
     
     //银行层级
     var bankLevel=$( "#bbankLevel").val();
     console.info(bankLevel);
     if(bankLevel){
          $( "#bankLevel").find("option[value='" +bankLevel+"']").attr( "selected",true );
     }
     
     //国家
     var country=$( "#bcountry").val();
     console.info(country);
     if(country){
          $( "#country").find("option[value='" +country+"']").attr( "selected",true );
     }
     
     //确认银行信息是否重复
     $("#bankInnerCode").blur( function(){
          
           var  code=$("#bankInnerCode" ).val();
          
          
           if(code){
              
               $( "div.warning").hide();
          } else{
              
              $( "div.warning span").html("请输入必填信息" );
            
                 $( "div.warning").show();
          }
          
     });  
 
     
              
//保存点击事件          
     $("#save").click( function(){
    	 var contractEffectDate = $("#contractEffectDate").val();
    	 var contractExpireDate = $("#contractExpireDate").val();
    	 var bankNum = $("#bankNum").val();
    	 var swiftCode = $("#swiftCode").val();
    	 var branchCode = $("#branchCode").val();
    	 var ngsnCode = $("#ngsnCode").val();
         $( "div.warning").hide();
         if(contractEffectDate == "" || contractEffectDate == null){
				$(".warning span").html("合同开始日期不能为空！");
				$(".warning").show();
			}else if(contractExpireDate == "" || contractExpireDate == null){
				$(".warning span").html("合同结束日期不能为空！");
				$(".warning").show();
			}else if(bankNum == "" || bankNum == null){
				$(".warning span").html("银行联行号不能为空！");
				$(".warning").show();
			}else if(swiftCode == "" || swiftCode == null){
				$(".warning span").html("SWIFT代码不能为空！");
				$(".warning").show();
			}else if(branchCode == "" || branchCode == null){
				$(".warning span").html("BRANCH代码不能为空！");
				$(".warning").show();
			}else if(ngsnCode == "" || ngsnCode == null){
				$(".warning span").html("NGSN代码不能为空！");
				$(".warning").show();
			}else{
		         $.boxUtil.confirm(
		              '请确认是否保存信息？' ,
		              null,
		              function(){
							$.ajax({
							 	type:"POST",
							 	dateType:"text",
							 	data:$("#corBankIntfEditForm").serialize(),
								url:"corBankIntfSaveOrUpdate.action",
							 	success:function(msg){
							  		if(msg == "1"){
									   	$.growlUI('成功信息！', '修改成功！');
									   	location.href="/system/corBankInfo/corBankIntfList.action";
								   	}
							 	}
							})
		              },
		               function(){
		              });
		           return true ;
			}
     });

                   
     $("#undo").click( function(){
          window.location = "corBankIntf/corBankIntfList.action?loadPageCache=true" ;
     });

     // 初始化所有只读字段的样式
     $("* [readonly]").addClass( "read_only");
     $("input[readonly]").input().disableBackSpace();

});  
 

function doSave(){
     <c:if test= "${isModify}">
          $( "#corBankIntfEditForm").submit();
     </c:if>
     <c:if test= "${isModify == false}">
          $( "#corBankIntfEditForm").submit();
     </c:if>
}


//alert框
function showAlertDialog(alertTitle, alertContent){
     $('#alertDialog').dialog( 'destroy');
    $('#alertDialog').show();
    $('#alertDialog').html( '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+ alertContent + '</p>');
    $('#alertDialog').html( '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>'+' id: '+'corBankInfoDTO.corBankIntf.id '+'</p>');
   
    $("#alertDialog").dialog({
        resizable: false,
        modal: true,
        overlay: {
            backgroundColor: '#000',
            opacity: 0.9
        },
        title:alertTitle,
        buttons: {
           '${app:i18n("global.jsp.ok")}' : function() {
              $( '#alertDialog').dialog('close' );
            }
        }
    });

}

</script>

<div id= "alertDialog"></div >

<s:form id= "corBankIntfEditForm" method ="post" action="corBankIntfSaveOrUpdate.action" namespace="/corBankIntf">
<s:hidden name= "isModify"/>
 
<div class= "layout">
     <div class="block m-b">
           <div class= "block_title">
                <h3> ${app:i18n('corBankIntf.corBankIntfEditJsp.title')}</h3>
           </div>
           <div class= "block_container">
	           <div class= "warning" style ="display :none;">
	               <span></span>
	           </div>
               <div class= "fieldset_border fieldset_bg m-b">
                    <div class= "fieldset_container">
                         <form id= "validate_form">
                              <table cellpadding="0" cellspacing="0" class="table_form">
                                   <thead>
                                   </thead>
                                   <tfoot>
                                   </tfoot>
                                   <tbody>
                                   <input name="corBankInfoDTO.createTime" id ="createTime" type="hidden" />
                                   <input name="corBankInfoDTO.creator" id ="creator" type= "hidden" />
                                   <tr>
                                       <th hidden="true" ><em> *</em>${app:i18n('corBankInfoDTO.id') }：</th>
                                       <td hidden="true" ><input name= "corBankInfoDTO.corBankIntf.id" id="id" class="width_c" value="${corBankInfoDTO.corBankIntf.id}" maxlength="225" hidden="ture" /></td>
                                       <th hidden="true" ><em> *</em>${app:i18n('corBankInfoDTO.id') }：</th>
                                       <td hidden="true" ><input name="corBankInfoDTO.corBankSetting.id" id="id" class="width_c" value="${corBankInfoDTO.corBankSetting.id}" maxlength="225" hidden="ture"/></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.bankInnerCode') }：</th>
                                       <td><input name="corBankInfoDTO.corBankIntf.bankInnerCode" id="bankInnerCode" class="width_c" value="${corBankInfoDTO.corBankIntf.bankInnerCode}" maxlength="225" readonly="readonly" ></td>
                                   </tr>
									<tr>
                                        <th><em>*</em >${app:i18n('corBankInfoDTO.bankName') }：</th>
                                       <td>
                                       <%-- <input  type="hidden"  value="${bankInfoDTO.bankName}" id="bname"> --%>
	                                       <input  type="hidden" value=" ${corBankInfoDTO.corBankSetting.bankName}" id="bname" >
	                                       <select name="corBankInfoDTO.corBankSetting.bankName" id="bankName" class="width_c" style=" width:190px;">
		                                       <s:iterator value="bankNameList" var="nameList">
		                                       		<s:if test="#nameList.key == corBankInfoDTO.corBankSetting.bankCode">
		                                       			<option value="${nameList.key }" selected="selected"> ${nameList.value }</option>
		                                       		</s:if>
		                                       		<s:else>
		                                       			<option value="${nameList.key }"> ${nameList.value }</option>
		                                       		</s:else>
		                                       </s:iterator>
	                                       </select>
                                       </td>
                                   </tr>
                        
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.bankLevel') }：</th>
                                       <td>
                                            <input  type="hidden"   value=" ${corBankInfoDTO.corBankSetting.bankLevel}" id="bbankLevel" >
                                      
                                            <select name="corBankInfoDTO.corBankSetting.bankLevel" id="bankLevel" class="width_c" style=" width: 190px">
                                                 <s:iterator value= "bankLevel"  var ="level">
                                                 	<s:if test="#level.key == corBankInfoDTO.corBankSetting.bankLevel">
                                                 		<option value= "${level.key}" selected="selected">${level.value}</option>
                                                 	</s:if>
                                                 	<s:else>
                                                 		<option value= "${level.key}">${level.value}</option>
                                                 	</s:else>
                                                 </s:iterator>
                                            </select>
                                           
                                       </td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.country') }：</th>
                                       <td>
                                            <input  type="hidden"   value=" ${corBankInfoDTO.corBankSetting.country}" id="bcountry" >
                                            <select name="corBankInfoDTO.corBankSetting.country"   id="country" class="width_c" style=" width: 190px">
                                                 <s:iterator value= "countryList" var ="country">
                                                 	<s:if test="#country.key == corBankInfoDTO.corBankSetting.country">
                                                 		<option value="${country.key}" selected="selected">${country.value }</option>
                                                 	</s:if>
                                                 	<s:else>
                                                 		<option value="${country.key}">${country.value }</option >
                                                 	</s:else>
                                                 </s:iterator>
                                            </select>
                                       </td>
                                   </tr>
                                   <tr>
                                        <th><em></em >${app:i18n('corBankInfoDTO.bankAddr') }：</th>
                                       <td><input name="corBankInfoDTO.corBankSetting.bankAddr" id="bankAddr" class="width_c" value="${corBankInfoDTO.corBankSetting.bankAddr}" maxlength="225"/></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.contractEffectDate') }： </th>
                                       <td><input name="corBankInfoDTO.corBankSetting.contractEffectDate" id="contractEffectDate" class="width_c" value="${corBankInfoDTO.corBankSetting.contractEffectDate}"
                                        onfocus= "WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'contractExpireDate\')}'})" maxlength="225" /></td >
                                   </tr>
                                   <tr>
                                        <th><em>*</em >${app:i18n('corBankInfoDTO.contractExpireDate') }： </th>
                                       <td><input name="corBankInfoDTO.corBankSetting.contractExpireDate" id="contractExpireDate" class="width_c" value="${corBankInfoDTO.corBankSetting.contractExpireDate}"
                                        onfocus= "WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss', minDate:'#F{$dp.$D(\'contractEffectDate\')}'})" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.techSupportName') }：</th>
                                       <td><input name="corBankInfoDTO.corBankIntf.techSupportName" id="techSupportName" class="width_c" value="${corBankInfoDTO.corBankIntf.techSupportName}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.techSupportPhonenum') }： </th>
                                       <td><input name="corBankInfoDTO.corBankIntf.techSupportPhonenum" id="techSupportPhonenum" class="width_c" value="${corBankInfoDTO.corBankIntf.techSupportPhonenum}" maxlength="225" /></td>
                                   </tr>
                                       <tr>
                                        <th><em></em >${app:i18n('corBankInfoDTO.techSupportEmail') }： </th>
                                       <td><input name="corBankInfoDTO.corBankIntf.techSupportEmail" id="techSupportEmail" class="width_c" value="${corBankInfoDTO.corBankIntf.techSupportEmail}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.businessSupportName') }： </th>
                                       <td><input name="corBankInfoDTO.corBankIntf.businessSupportName" id="businessSupportName" class="width_c" value="${corBankInfoDTO.corBankIntf.businessSupportName}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.businessSupportPhonenum') }： </th>
                                       <td><input name="corBankInfoDTO.corBankIntf.businessSupportPhonenum" id="businessSupportPhonenum" class="width_c" value="${corBankInfoDTO.corBankIntf.businessSupportPhonenum}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.businessSupportEmail') }： </th>
                                       <td><input name="corBankInfoDTO.corBankIntf.businessSupportEmail" id="businessSupportEmail" class="width_c" value="${corBankInfoDTO.corBankIntf.businessSupportEmail}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.bankNum') }：</th>
                                       <td><input name="corBankInfoDTO.corBankSetting.bankNum" id="bankNum" class="width_c" value="${corBankInfoDTO.corBankSetting.bankNum}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.swiftCode') }：</th>
                                       <td><input name="corBankInfoDTO.corBankSetting.swiftCode" id="swiftCode" class="width_c" value="${corBankInfoDTO.corBankSetting.swiftCode}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.branchCode') }： </th>
                                       <td><input name="corBankInfoDTO.corBankSetting.branchCode" id="branchCode" class="width_c" value="${corBankInfoDTO.corBankSetting.branchCode}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em>*</em >${app:i18n('corBankInfoDTO.ngsnCode') }：</th>
                                       <td><input name="corBankInfoDTO.corBankSetting.ngsnCode" id="ngsnCode" class="width_c" value="${corBankInfoDTO.corBankSetting.ngsnCode}" maxlength="225" /></td>
                                   </tr>
                                   <tr>
                                       <th><em></em >${app:i18n('corBankInfoDTO.desc') }： </th>
                                       <td><input name="corBankInfoDTO.corBankSetting.desc" id="desc" class="width_c" value="${corBankInfoDTO.corBankSetting.desc}" maxlength="225" /></td>
                                   </tr>
                                   </tbody>
                              </table>
                         </form>
                    </div>
               </div>
               <div id= "tabs-1">
                    <a id= "save" class ="easyui-linkbutton l-btn" href="#"><span class ="l-btn-left"><span class="l-btn-text icon-save">${app:i18n('global.jsp.save')}</span></span ></a>
                    <a id= "undo" class ="easyui-linkbutton l-btn" href= "#"><span class="l-btn-left" ><span class= "l-btn-text icon-undo">取消 </span></span></a >
               </div>
           </div>
     </div>
</div>
</s:form>
