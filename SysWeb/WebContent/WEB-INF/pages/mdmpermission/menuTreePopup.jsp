<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
.menuTreeStyle {
	HEIGHT: 300px;
	SCROLLBAR-FACE-COLOR: #e8e5f8;
	SCROLLBAR-HIGHLIGHT-COLOR: #f7f5ff;
	OVERFLOW: auto;
	SCROLLBAR-SHADOW-COLOR: #cac4e8;
	SCROLLBAR-3DLIGHT-COLOR: #ffffff;
	LINE-HEIGHT: 120%;
	SCROLLBAR-ARROW-COLOR: #9e9ccd;
	SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
}
</style>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url context='/common' value='/js/jstree-1.0rc2/themes/default/style.css'/>" />
<script type="text/javascript" src="<c:url context='/common' value='/js/jstree-1.0rc2/jquery.jstree.js'/>"></script>
<script type="text/javascript" charset="UTF-8">
var parm = "popup";
$().ready(function() {

	$("#menuTreePopup").jstree({
		 "html_data" : {
			"ajax" : {
				"url" : "getMenuNode.action?parm="+parm+"&time="+new Date().getTime(),
				"data" : function (n) {
					return { nodeId : n.attr ? n.attr("id") : 0 };
				}
			}
		},
		"types" : {
			"type_attr" : "ti",
			"types":{
				"control":{
					"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
				},
				"url":{
					"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
				},
				"button":{
					"icon":{"image" : "<c:url context='/common' value='/images/file.png'/>"}
				}
			}
		},
		"search" : {
			"case_insensitive" : true,
			"ajax":{
				"url":"searchMenu.action"
			}
		},
		"plugins" : [ "themes", "html_data" ,"ui","crrm","types","search"]
	}).bind("select_node.jstree", function(e,data){
		$("#menuId-popup").val(data.rslt.obj.attr("id"));
		$("#menuTitle-popup").val(data.rslt.obj.attr("tn"));
		$("#menuType-popup").val(data.rslt.obj.attr("ti"));
		//$("#menuTreePopup").jstree('open_node','#'+data.rslt.obj.attr("id"));
	});

	$("#menu-popup-ok").click(function(){
		var mid = $("#menuId-popup").val();
		var mtitle = $("#menuTitle-popup").val();
		var curId = $("#id").val();
		var canSelect = false;
		if(curId != ''){
			curId = parm+curId;
		}
		if (mid && curId)	{
			if(curId == mid){
				alert('不能选择自己作为父菜单！');
				return false;
			}
			var node = $("#"+curId).jstree("_get_node");
			if(node == ''){
				mid = mid.replace(parm,'');
				setParentMenu(mid, mtitle);
			}else if(node.is('li')){
				canSelect = true;
				var subnode = node.find('li');
				subnode.each(function(index){
					var myid = $(this).attr('id');
					if(myid != '' && myid == mid){
						alert('不能选择自己的子节点作为父菜单!');
						canSelect = false;
						return ;
					}
				});
				if(canSelect == false){
					return false;
				}else{
					mid = mid.replace(parm,'');
					setParentMenu(mid, mtitle);
				}
			}else{
				mid = mid.replace(parm,'');
				setParentMenu(mid, mtitle);
			}
		} else{
// 			setParentMenu(mid, "");
			mid = mid.replace(parm,'');
			setParentMenu(mid, mtitle);
		}
		
	});
	$("#menu-popup-back").click(function(){
		$("#dialog-ajax-select-parent").dialog('close');
	});
	
});	

</script>

<form id="menuForm" action="menuSelect.action" method="post">

<div class="layout">
<div class="block m-b">
	<div class="block_container">
	<div class="fieldset_border fieldset_bg m-b">
<div class="tabs ui-tabs ui-widget ui-widget-content ui-corner-all menuTreeStyle" id="tabs3" style="background: #ffffee">	
<div id="menuTreePopup" class="jstree" ></div>
</div>
</div>
<div class="btn_layout">
<input type="hidden" name="menuId-popup" id="menuId-popup" size="40" maxlength="20" />
<input type="hidden" name="menuTitle-popup" id="menuTitle-popup" size="40" maxlength="20" />
<input type="hidden" name="menuType-popup" id="menuType-popup" size="40" maxlength="20" />
		<a id="menu-popup-ok" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-ok">确 定</span></span></a> 
		<a id="menu-popup-back" class="easyui-linkbutton l-btn" ><span class="l-btn-left"><span class="l-btn-text icon-undo">返 回</span></span></a> 
</div>

</div>
</div>
</div>
</form>

