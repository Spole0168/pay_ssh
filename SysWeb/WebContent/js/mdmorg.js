  
function createorg(selectedOrgId)
{
	var level=$("#actionVO_orgInfo_level").val();
	var init_level=7;
 
	if(level>=init_level)
	{
		alert("组织机构层级不能超过最大层级数："+init_level);
		return;
	}
	
	var addUrl="gridRowAdd.action";
	  $.ajax({
			async:true, 
			type:"post", 
			dataType:"html", 
			url:addUrl, 
			data:"selectedOrgId="+selectedOrgId, 
			success:function(data) {
			    
				$("#rightOrgContent").html(data);
		                  }
		});
}

function modifyorg(id)
{
	var modifyUrl="gridRowEdit.action";
	var  modifyUrl= "rightContent.action";
	var selectedOrgId=$("#selectedOrgId").val();
	 
	 
	  $.ajax({
			async:true, 
			type:"post", 
			dataType:"html", 
			url:modifyUrl, 
			data:"id="+id+"&selectedOrgId="+id, 
			success:function(data) {
			    
				$("#rightOrgContent").html(data);
				
				
				 
				 openandSelect(selectedOrgId,id);
				 window.setTimeout("setSelectNode('"+id+"')", 1000);
				 // setSelectNode(id);
				//openNode(selectedOrgId);
				//openNode(id);
				//setSelectNode(id);
		                  }
		});
}

function deleteorg(id)
{
	 $("#gridTableOrg").jqGrid('delGridRow',id,{drag:false,
			afterSubmit:function(resp, postdata){
				var result = JSON.parse(resp.responseText);
				
				if(result.success == 'false'){
					alert('Delete failed:' + result.text);		
					return [true,"",""];
				} else {
				    //alert('Delete is succefully');	
					return [true,"",""];
				}
				
			}
		});
	}

function modifyaddinfo(){
	var modifyUrl="loadOrgAddInfo.action";
	var orgAddInfoId=$("#orgAddInfoId").val();
	 
	var selectedOrgId=$("#selectedOrgId").val();
	  $.ajax({
			async:true, 
			type:"post", 
			dataType:"html", 
			url:modifyUrl, 
			data:"actionVO.orgInfo.orgAddInfo.id="+orgAddInfoId+"&selectedOrgId="+selectedOrgId+"&actionVO.orgInfo.id="+selectedOrgId, 
			success:function(data) {
			    
				//$("#rightOrgContent").html(data);
		      $("#orgaddinfocontent").html(data);
		  
		                  }
		});
	  
}
 

 