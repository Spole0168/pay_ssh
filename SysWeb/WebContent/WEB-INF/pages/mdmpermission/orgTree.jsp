<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<script type="text/javascript" charset="UTF-8">
$().ready(function() {

	$("#orgTree").jstree({
		"html_data" : {
		"ajax" : {
			"url" : "getUserNode.action",
			"data" : function (n) {
					return { nodeId : n.attr ? n.attr("id") : "" }; 
				}
			}
		},
		"core" : { "initially_open" : ['${topOrgId}'] },	
		"ui" : {"initially_select" : ['${topOrgId}']},	 
		"plugins" : [ "themes", "html_data" ,"ui"  ]
	})
	.bind("select_node.jstree", function(e,data){
		var selectedOrgId=data.rslt.obj.attr("id");
		var selectedOrgName = data.rslt.obj.attr("tn");
		var selectedOrgCode = data.rslt.obj.attr("ti");
		$("#currentOrgId").val(selectedOrgId);
		$("#selectedOrgId").val(selectedOrgId);
		$("#currentOrgCode").val(selectedOrgCode);
		$("#currentOrgName").val(selectedOrgName);
		 $.ajax({
				async:true, 
				type:"post", 
				dataType:"html", 
				url:"userList.action?currentOrgId="+selectedOrgId+"&currentOrgCode="+selectedOrgCode, 
				data:"", 
				success:function(data) {
				    
					$("#rightOrgContent").html(data);
			    }
			});
	});
 
});	



</script>

<form id="orgTreeForm" action="leftTree.action" method="post">
<input type="hidden" id="topOrgId" name="topOrgId" value="${topOrgId }" />
<input type="hidden" id="selectedOrgId" name="selectedOrgId" value="${topOrgId }" />
<div id="orgTree" class="jstree"></div>
 
 
</form>

