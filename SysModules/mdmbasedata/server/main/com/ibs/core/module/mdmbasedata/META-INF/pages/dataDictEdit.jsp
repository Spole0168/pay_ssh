<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$().ready(function() {
	 
	 $("#displayOrder").autoNumeric({aSep: '',mNum: 4, mDec: 0});
	
	$("#exittodatatictlist").click(function(){
		// window.location.href="dataDictList.action";
		closeDialog();
	});
	$("#savedataDict").click(function() {

		 $('#editblockidiv').block({ 
				message: '正在处理，请稍候...',
				css: {padding: '10px'}
         });
         
		 
			
		   var returnval=	checkValue('code');		  
		   if(returnval>0)
		   {
			   $('#editblockidiv').unblock();
			   return;
			   }
		   returnval=	checkValue('name');		  
		   if(returnval>0)
		   {
			   $('#editblockidiv').unblock();
			   return;
			   }
			$("#dataDictEditForm").submit();
		});

	 $("#dataDictEditForm").validate({
			rules : {
		     'dataDict\.code' : {
		 
					required : true, minlength: 1,maxlength:30
				},
				'dataDict\.name'    : {
					required : true,maxlength:30
				},
				'dataDict\.value'   :{ maxlength:1000 }
				,
				  
				'dataDict\.extendAtt1'  :{maxlength:50  }
				,'dataDict\.displayOrder'  :{digits:true }
				,'dataDict\.remark'  :{maxlength:1000  }
				 
			},
			invalidHandler : function(e, validator) {
				var errors = validator.numberOfInvalids();
				 
				if (errors) {
					var message = "请正确填写信息！";
					$("div.warning span").html(message);
					$("div.warning").show();
					 $('#editblockidiv').unblock();
				} else {
					$("div.warning").hide();
				}
			},
			submitHandler : function() { 
			 
				  $.ajax({
		                async: true,
		                type: "post",
		                dataType: "json",
		                url:"dataDictSaveOrUpdate.action",
		                data:"dataDict.type="+encodeURI($("#type").val())
		                +"&dataDict.id="+encodeURI($("#dataDict_id").val())
		                +"&dataDict.status="+encodeURI($("#dataDict_status").val())
		                 +"&dataDict.languageCode="+encodeURI($("#languageCode").val())
		                  +"&dataDict.code="+encodeURI($("#code").val())
		                   +"&dataDict.name="+encodeURI($("#name").val())
		                  +"&dataDict.value="+encodeURI($("#dataDict_value").val())
		                   +"&dataDict.extendAtt1="+encodeURI($("#extendAtt1").val())
		                    +"&dataDict.displayOrder="+encodeURI($("#displayOrder").val())
		                     +"&dataDict.remark="+encodeURI($("#remark").val())
		                      +"&isModify="+encodeURI($("#isModify").val()) 
		                 
		                +"&time=" + new Date().getTime(),
		                success: function(data,textStatus){
		                       // alert("data.message:"+data.message);
							    var flag=data.success;
								if(flag=="false"){
									$.boxUtil.error(data.text);
			  					  
								}else{
									
								   closeDialog();
								   $.growlUI('成功信息！', '保存数据成功！');
			                       refreshGrid();
			                       
								}
								 $('#editblockidiv').unblock();
		                },
						error: function(XMLHttpRequest, textStatus, errorThrown){
							$("div.warning").hide();
							 $('#editblockidiv').unblock();
							if (XMLHttpRequest=='timeout') {
								$.boxUtil.error('操作已超时,请重试!');
							} else {
								$.boxUtil.error('发生了错误,请重试!');
							}
						}
		            });
				 
				},
			onkeyup : false 
		});

	 
});



function checkValue(parm) {
	var data = "";
	var id = $("#dataDict_id").val();
	var type = $("#type").val();
	var language=$("#languageCode").val();
	var message=""; 
	var code = $("#code").val(); 
	var codeName = $("#name").val(); 
	if (parm == 'code') {
		if(code==null||code=="")
		{
			 
			return 0;
			}
	}else
	{
		if(codeName==null||codeName=="")
		{
			return 0;
			}
		
		}
	

	if (parm == 'code') {
	   var booleanval=  (/^[a-z0-9A-Z_]*$/).test(code);  
	   if(!booleanval)
	   {
		   $("div.warning span").html("代码只能是字母、数字、或者下划线(_)");
			$("div.warning").show();
			returnval=1;
			 
			return ;
		   }else
		   {
			   $("div.warning").hide();
			   }
	}
	   
	    
	var returnval="";
	if (parm == 'code') {
	    
		if (id == "" || id == null) {

			data = "checkParm=code" + "&checkContent=" + code+"&type="+type+"&language="+language; 
			message="${app:i18n("mdmdatadict.error.hascode")}";
			
		}else
		{
			return 0;
			}
	

	}else
	{
		if (id == "" || id == null) {

			data = "checkParm=name" + "&checkContent=" + codeName+"&type="+type+"&language="+language; 
			message="数据字典名称已经存在，请重新输入！";
			
		}else
		{
			data = "checkParm=name" + "&checkContent=" + codeName+"&type="+type+"&language="+language+"&id="+id; 
			message="数据字典名称已经存在，请重新输入！";
			}

		}
	 

	$.ajax( {
		type : "post",
		dataType : "json",
		url : "validValue.action",
		data : data,
		async:false,
		success : function(data) {

			if (data.success == "false") {
				$.boxUtil.error(data.text);
				returnval=1;
				return ;
			}
			if (data.code > 0) {
				$("div.warning span").html(message);
				$("div.warning").show();
				returnval=1;
				 
				return ;
			}
			$("div.warning").hide();
			returnval=0;
			
			return ;
		}
	});
	 return returnval;

}	
</script>
 

 
<div id="editblockidiv" class="layout block m-b">
<form method="post" action="dataDictSaveOrUpdate.action" name="dataDictEditForm" id="dataDictEditForm">
<s:hidden name="isModify"/>
 

<input type="hidden" name="dataDict.id" id="dataDict_id" value="${dataDict.id}" />
<input type="hidden" name="dataDict.status" id="dataDict_status" value="${dataDict.status}" />


<div class="block_container" style="height:220">
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
		 
		<th style="width:90px">字典类型：</th>
		<td width="100px">
		 
		   <select name="dataDict.type"   disabled="disabled"  style="width:131px">
			<s:iterator value="%{typeList}" id="typeItem">
			    <s:if test="dataDict.type == #typeItem.key">
			    	<option value="<s:property value="#typeItem.key" />" selected>
			    		<s:property value="#typeItem.value" />
			        </option>      	
			    </s:if>
			    <s:else>
			        <option value="<s:property value="#typeItem.key" />">
			        	<s:property value="#typeItem.value" />
			        </option>
			    </s:else>
			</s:iterator>
         </select>
         <input type="hidden" name="dataDict.type" id="type" value="${dataDict.type}" /> 
		   
		
		</td>
		<th style="width:100px">语言：</th> 
		<td width="100px">
		 <s:if test="isModify==true">
		 <select name="dataDict.languageCode"    disabled="disabled" style="width:131px">
			<s:iterator value="%{languageList}" id="typeItem">
			    <s:if test="dataDict.languageCode == #typeItem.key">
			    	<option value="<s:property value="#typeItem.key" />" selected>
			    		<s:property value="#typeItem.value" />
			        </option>      	
			    </s:if>
			    <s:else>
			        <option value="<s:property value="#typeItem.key" />">
			        	<s:property value="#typeItem.value" />
			        </option>
			    </s:else>
			</s:iterator>
         </select>
           <input type="hidden" name="dataDict.languageCode" id="languageCode" value="${dataDict.languageCode}" /> 
		  </s:if>
		  <s:else>
		  <select name="dataDict.languageCode" id="languageCode" onchange="checkValue('code')" style="width:131px" >
			<s:iterator value="%{languageList}" id="typeItem">
			    <s:if test="dataDict.languageCode == #typeItem.key">
			    	<option value="<s:property value="#typeItem.key" />" selected>
			    		<s:property value="#typeItem.value" />
			        </option>      	
			    </s:if>
			    <s:else>
			        <option value="<s:property value="#typeItem.key" />">
			        	<s:property value="#typeItem.value" />
			        </option>
			    </s:else>
			</s:iterator>
         </select>
		   </s:else>
		
		</td>
		
		</tr>
		<tr>
	 
		 
			<th  style="width:100px" title="代码只能是字母、数字、或者下划线(_)"><em>*</em>代码：</th>
			
			
		      <s:if test="isModify==false">
		      
		      
			    <td width="100px" title="代码只能是字母、数字、或者下划线(_)">
			
			        <s:if test="dataDict.type == 'FINANCIAL_DICT' ">
			        
			        <select name="selectFinCode" id="code"  onchange="checkValue('code')" style="width:131px">
					      <s:iterator value="%{typeListFin}" id="typeItem"> 
					    	<option value="<s:property value="#typeItem.key" />"  >
					    		<s:property value="#typeItem.value" />
					        </option>   
					     </s:iterator>
	               </select>
			        
			         </s:if>
			         <s:elseif test="dataDict.type == 'FINANCIAL_ORG_BIZ' ">
			           <select name="selectOrgCode" id="code"  onchange="checkValue('code')" style="width:131px">
					      <s:iterator value="%{orgBusTypeList}" id="typeItem"> 
					    	<option value="<s:property value="#typeItem.key" />"  >
					    		<s:property value="#typeItem.value" />
					        </option>   
					     </s:iterator>
	               </select>
			         
			          </s:elseif>
				    <s:else>
				     <input type="text" name="dataDict.code" id="code" class="width_b"  onblur="checkValue('code')"
			size="25" maxlength="100" value="${dataDict.code}" />
				    
				    
				     </s:else>
			
			
			
			
			</td>
			    </s:if>
			    <s:else>
			    	<td width="100px" title="代码只能是字母、数字、或者下划线(_)"><input type="text" readonly="readonly" disabled="disabled" name="dataDict.code" id="code" class="width_b"
			size="25" maxlength="100" value="${dataDict.code}" /></td>
			 <input type="hidden" name="dataDict.code" id="code" value="${dataDict.code}" /> 
			        
			    </s:else>
			    
		
	     <th style="width:100px" ><em>*</em>名称：</th> 
		<td  width="100px"><input type="text" name="dataDict.name" id="name" class="width_b"  onblur="checkValue('name')" size="25" maxlength="100" value="${dataDict.name}" />
			
			 
			
			</td>
	</tr>
	
	
	
	<tr>
		 
		  <th  >数据值：</th>
		  
		<td  ><input type="text" name="dataDict.value" id="dataDict_value" class="width_b"
			size="25" maxlength="1000" value="${dataDict.value}" /></td>
	  
	  <th  >扩展属性：</th>
		 
		<td ><input type="text" name="dataDict.extendAtt1" id="extendAtt1" class="width_b"
			size="25" maxlength="9" value="${dataDict.extendAtt1}" /></td>	
			
	</tr>
	
	
	
	<tr>
	 <th  >显示顺序：</th>
		 
		<td colspan="3"><input type="text" name="dataDict.displayOrder" id="displayOrder" class="digits width_b"
			size="25" maxlength="9" value="${dataDict.displayOrder}" /></td>
		
	</tr>
	
	<tr>
		 
	 
		<th  > 描述：</th>
								 
								 <td colspan="3"  ><s:textarea      id="remark" cols="68"  rows="2"  name="dataDict.remark" > </s:textarea>
								  
								 <span class="form_tips"></span>
								 </td> 
	 
		  
		 
	</tr>
	<tr>
	<td>
	<br>
	</td>
	 
	</tr>
	<tr>
	<th>&nbsp;</th>
	 
	 
	<td colspan="7">
	 <s:if test="dataDict.status=='VALID'|| dataDict.id==''||dataDict.id==null  ">
	 <a  id="savedataDict" class="easyui-linkbutton l-btn" href="#">
					<span class="l-btn-left">
						<span class="l-btn-text icon-search">保存</span>
					</span>
				</a>
				
	
	</s:if>
	<a id="exittodatatictlist" class="easyui-linkbutton l-btn" href="#"><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a>
									
	 
	
	</td>
	
	</tr>
	
	</tbody>
</table>


</div>

</form>

</div>

  
 