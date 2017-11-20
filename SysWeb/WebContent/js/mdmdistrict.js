$().ready(function(){
    var array = new Array();
    var searchButtonVar = "#searchSubDistrict";
    var createButtonVar = "#gridRowAdd";
    var modifyButtonVar = "#gridRowEdit";
    var deleteUrl = "";
    var pageCacheVar = "false";
    var searchUrl = "listSubDistrict.action";
    var selectDistrictId = $("#selectedDistrictId").val();
    var searchParameters = ["selectedDistrictId", "pageCache"];
    if ($('#pageCache') != null && $('#pageCache').attr('value') == 'true') {
        pageCacheVar = "true";
    }
    var rooturl = "listSubDistrict.action" + "?pageCache=" + pageCacheVar;
    if (selectDistrictId != null && selectDistrictId != "") {
        rooturl = rooturl + "&selectedDistrictId=" + selectDistrictId;
    }
   var captionvar="子行政区域";
    if(selectDistrictId=="0")
    {
    	captionvar="国家/地区";
    }
    $("#gridTableDistrict").jqGrid({
        url: rooturl,
        datatype: "json",
        mtype: "POST",
        sortable: true,
        height: 'auto',
        colNames: ['编号', '名称', '邮编', '操作'],
        colModel: [{
            name: 'code',
            index: 'id',
            width: 160,
            sorttype: "string"
        }, {
            name: 'name',
            index: 'name',
            width: 311,
            sorttype: "string"
        }, {
            name: 'postalCode',
            index: 'postalCode',
            width: 140,
            sorttype: "string"
        }, {
            name: 'operation',
            index: 'operation',
            width: "152.6",
            align: 'center',
            search: false,
            sortable: false,
            editable: false
        }],
        sortname: 'code',
        sortorder: 'asc',
        altRows: true,
        rownumbers:true,//显示序号
        altclass: 'altClass',
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: "#gridPagerDistrict",
        toolbar: [true, "top"],
        multiselect:false,
        viewrecords: false,
        caption: captionvar,
        jsonReader: {
            root: "rows",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false
        },
        loadError: function(xhr, st, err){
            alert('err:' + err);
            $("#tblMasterMessage").html("Type: " + st + "; Response: " + xhr.status + " " + xhr.statusText);
        },
        loadComplete: function(data){
            if (data.success == "false") {
                alert(data.text);
            }
            var h = $("#rightDistrictContent").height();
            if (h > 460) {
                h = h - 24;
                $("#leftDistrictTree").height(h);
            }
            else {
                $("#leftDistrictTree").height(460);
            }
        },
        onPaging: function(pgButton){
            $(searchButtonVar).click();
        },
        gridComplete: function(){
            var ids = $("#gridTableDistrict").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                var id = ids[i];
                var mod = "";
                mod = "<a href='#' class='a_btn_edit m-r ' onclick='javascript:modifydistrict(\"" + id + "\");'><em>修改</em></a>";
                $("#gridTableDistrict").jqGrid('setRowData', id, {
                    operation: mod
                });
            }
        },
        editurl: deleteUrl
    });
    $("#gridTableDistrict").jqGrid("navGrid", '#gridPagerDistrict', {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true
    });
    $("#gridTableDistrict").jqGrid('sortableRows');
    $(searchButtonVar).click(function(){
        var url = searchUrl + "?rowNum=" + $("#gridTableDistrict").jqGrid('getGridParam', 'rowNum');
        var postData = new Object();
        for (var i = 0; i < searchParameters.length; i++) {
            var name = searchParameters[i];
            var currentValue = $("#" + searchParameters[i]).attr('value');
            eval("postData." + name + "=currentValue");
        }
        $("#gridTableDistrict").appendPostData(postData);
        $("#gridTableDistrict").jqGrid("setGridParam", {
            url: url
        }).trigger("reloadGrid");
        $("#gridTableDistrict").jqGrid("setGridParam", {
            page: 1
        });
    });
});
function modifydistrict(id){
	var url ="gridRowEdit.action?selectedDistrictId=" + $("#selectedDistrictId").val() + "&modifyId=" + id + "&time=" + new Date().getTime();
	var title = "修改行政区域";
	openDialog(title,url);
	/*
    $.ajax({
        async: true,
        type: "post",
        dataType: "html",
        url: "gridRowEdit.action",
        data: "selectedDistrictId=" + $("#selectedDistrictId").val() + "&modifyId=" + id + "&time=" + new Date().getTime(),
        success: function(data){
            $("#rightDistrictContent").html(data);
        }
    });
    */
}
