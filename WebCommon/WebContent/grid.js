$(function()
{
	var modUrl = "jmesa.html";
	var delUrl = "jmesa.html";
	
	var mydata = [
                {id:"1",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01", level:0, leaf: "true"},
                {id:"2",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"3",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"4",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"5",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"6",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"7",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"8",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"9",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"11",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"12",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"13",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"14",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"15",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"16",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"17",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"18",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"19",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"21",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"22",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"23",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"24",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"25",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"26",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"27",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"28",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"29",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"31",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"32",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"33",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"34",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"35",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"36",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"37",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"38",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"39",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"41",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"42",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"43",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"44",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"45",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"46",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"47",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"48",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"49",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"51",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"52",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"53",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"54",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"55",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"56",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"57",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"58",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"59",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"}
	                ];

	$("#gridTable").gridUtil().simpleGrid({
		datatype: "local",
		data: mydata,
		width: 975,
		//url : "data3.json",
		caption: "第一个jqGrid例子",
		//sortable:true,
		//sortname:'id',
		//sortorder:'asc',
		rowNum: 30,
		rownumWidth: 50,
		toolbar: [true,"top"],
		pager: "#gridPager",
		//multiselect: false,
		cellEdit: true,
		multiboxonly: true,
		gridComplete: function() {
			var ids = $("#gridTable").jqGrid('getDataIDs');
			for(var i=0;i < ids.length;i++){
				var mod = "<a href='###' class='a_btn icon-edit m-r ' onclick='alert(\"修改：" + ids[i] + "\");'><em>修改</em></a>";
				var del = "<a href='###' class='a_btn icon-remove m-r' onclick='alert(\"删除：" + ids[i] + "\");'><em>删除</em></a>";
				var view = "<a href='###' class='a_btn icon-view m-r' onclick='alert(\"查看：" + ids[i] + "\");'><em>查看</em></a>";
				var enable = "<a href='###' class='a_btn icon-ok m-r' onclick='alert(\"启用：" + ids[i] + "\");'><em>启用</em></a>";
				var disable = "<a href='###' class='a_btn icon-logout m-r' onclick='alert(\"禁用：" + ids[i] + "\");'><em>禁用</em></a>";
				var submit = "<a href='###' class='a_btn icon-submit m-r' onclick='alert(\"提交：" + ids[i] + "\");'><em>提交</em></a>";
			//	var mod = "<a href='javascript:void(0)' class='a_btn_edit m-r ' onclick='window.location=\"#modUrl?id=#id\"'><em>修改</em></a>";
			//	var del = "<a href='javascript:void(0)' class='a_btn_remove m-r ' onclick='window.location=\"#delUrl?id=#id\"'><em>删除</em></a>";
			//	mod = mod.replace(/#modUrl/, modUrl).replace(/#id/, ids[i]);
			//	del = del.replace(/#delUrl/, delUrl).replace(/#id/, ids[i]);
				$("#gridTable").jqGrid('setRowData',ids[i],{operation:mod + del + view + enable + disable + submit});
			}
		},
		colNames:['编号','用户名', '性别', '邮箱', 'QQ','手机号','出生日期','操作'],
		colModel:[
				{name:'id',index:'id', width:60, sorttype:"int"},
				{name:'userName',index:'userName', width:90},
				{name:'gender',index:'gender', width:90},
				{name:'email',index:'email', width:125,sorttype:"string"},
				{name:'QQ',index:'QQ', width:100},
				{name:'mobilePhone',index:'mobilePhone', width:120, editable: true, editrules:{number:true}},
				{name:'birthday',index:'birthday', width:100, sorttype:"date"},
				{name:'operation',align:'center', width: 400}/* no index in operation */
		]
	}, {heightfixed: true});
	// jqGrid pager
	$("#gridTable").jqGrid('navGrid','#gridPager',{edit:false,add:false,del:false},{},{},{top:1000,left:1000});
	// jqGrid toolbar
	$("#gridToolbar").appendTo($("#t_gridTable"));
	// resize jqgrid
	$("#gridTable").jqGrid('gridResize',null);
	//$("#gridTable").jqGrid('gridResize',{minWidth:350,maxWidth:800,minHeight:80, maxHeight:350});
	//$("#gridTable").setGridWidth($(window).width(),true);
	//$("#gridTable").setGridHeight($(window).height()-150);
	
	$("#gridRowEdit").click( function()	{
		var	gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
		if (gr.length != 1)	{
			alert("请选择一行修改");
			return;
		}
		$("#gridTable").jqGrid('editGridRow',gr,{height:280,reloadAfterSubmit:false});
	});
	$("#gridRowDelete").click( function() {
		var gr = $("#gridTable").jqGrid('getGridParam','selarrrow');
		if (gr.length != 1)	{
			alert("请选择一行修改");
			return;
		}
		$("#gridTable").jqGrid('delGridRow',gr,{reloadAfterSubmit:false});
	});
	$("#gridShowColumn").click( function() {
		//$("#gridTable").gridUtil().exportExcel({url: "www.baidu.com"});
		$("#gridTable").gridUtil().exportExcel({url: "jmesa.html"});
		//$("#gridTable").gridUtil().exportExcel({url: "jmesa.html"}, function(){alert('finish download');});
	});

	//jqGrid 动态显示列
	$("#gridTable").jqGrid('navButtonAdd',"#gridPager",{
		caption: '显示列',
		title: '显示列',
		onClickButton :	function (){ $("#gridTable").jqGrid('columnChooser', $.gridUtil().columnOpts); }
	});
	//alert ($("#gridTable").gridUtil().getGridId());

	var mydata1 = [
                {id:"1",userName:"李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四李四polaris polaris polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01",level:"1"},
                {id:"2",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01",level:"1"},
                {id:"3",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01",level:"2"},
                {id:"4",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01",level:"2"},
                {id:"5",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01",level:"3"},
                {id:"6",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01",level:"3"},
                {id:"7",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01",level:"4"},
                {id:"8",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01",level:"4"},
                {id:"9",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21",level:"5"},
                {id:"11",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01",level:"5"},
                {id:"12",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"13",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"14",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"15",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"16",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"17",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"18",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"19",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"21",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"22",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"23",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"24",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"25",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"26",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"27",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"28",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"29",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"31",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"32",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"33",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"34",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"35",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"36",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"37",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"38",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"39",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"41",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"42",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"43",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"44",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"45",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"46",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"47",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"48",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"49",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"},
                {id:"51",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
                {id:"52",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
                {id:"53",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
                {id:"54",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
                {id:"55",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
                {id:"56",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
                {id:"57",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
                {id:"58",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
                {id:"59",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"}
	                ];

	// gridTable_scroll
	$("#gridTable_scroll").gridUtil().simpleGrid({
		datatype: "local",
		data: mydata1,
		autowidth: false,
		width: 960,
		shrinkToFit: false,
		pager: "#gridPager_scroll",
		caption: "多列滚动jqGrid",
		colNames:['编号','用户名', '性别', '邮箱', 'QQ','手机号','出生日期','扩展1','扩展2','扩展3','扩展4','扩展5','扩展6','扩展7','扩展8','扩展9','扩展10','扩展11','扩展12','扩展13','扩展14','扩展15','扩展16','扩展17','扩展18','扩展19','扩展20','操作'],
		colModel:[
				{name:'id',index:'id', width:60, sorttype:"int"},
				{name:'userName',index:'userName', width:90},
				{name:'gender',index:'gender', width:90},
				{name:'email',index:'email', width:125,sorttype:"string"},
				{name:'QQ',index:'QQ', width:100},
				{name:'mobilePhone',index:'mobilePhone', width:120},
				{name:'birthday',index:'birthday', width:100, sorttype:"date"},
				{name:'ext01',index:'ext01', width:100},
				{name:'ext02',index:'ext02', width:100},
				{name:'ext03',index:'ext03', width:100},
				{name:'ext04',index:'ext04', width:100},
				{name:'ext05',index:'ext05', width:100},
				{name:'ext06',index:'ext06', width:100},
				{name:'ext07',index:'ext07', width:100},
				{name:'ext08',index:'ext08', width:100},
				{name:'ext09',index:'ext09', width:100},
				{name:'ext10',index:'ext10', width:100},
				{name:'ext11',index:'ext11', width:100},
				{name:'ext12',index:'ext12', width:100},
				{name:'ext13',index:'ext13', width:100},
				{name:'ext14',index:'ext14', width:100},
				{name:'ext15',index:'ext15', width:100},
				{name:'ext16',index:'ext16', width:100},
				{name:'ext17',index:'ext17', width:100},
				{name:'ext18',index:'ext18', width:100},
				{name:'ext19',index:'ext19', width:100},
				{name:'ext20',index:'ext20', width:100},
				{name:'operation',align:'center'}
		]
	}, {singleselect: true});

	//$("#gridTable_scroll").jqGrid('sortableRows');
	//$("[aria-describedby='gridTable_scroll_cb'] > input").attr("disabled", true);
	
	//$("#gridTable_scroll").closest(".ui-jqgrid-bdiv").css({'overflow-x' : 'scroll'});
	
	// gridTable1
	$("#gridTable1").gridUtil().simpleGrid({
		datatype: "local",
		data: mydata1,
		autowidth: false,
		//width: 1200,
		//scrollrows: false,
		//scrollOffset: 50,
		pager: "#gridPager1",
		caption: "多列滚动jqGrid",
		colNames:['编号','用户名', '性别', '邮箱', 'QQ','手机号','出生日期','扩展1','扩展2','扩展3','扩展4','扩展5','扩展6','扩展7','扩展8','扩展9','扩展10','扩展11','扩展12','扩展13','扩展14','扩展15','扩展16','扩展17','扩展18','扩展19','扩展20','操作'],
		colModel:[
				{name:'id',index:'id', width:60, sorttype:"int"},
				{name:'userName',index:'userName', width:90},
				{name:'gender',index:'gender', width:90},
				{name:'email',index:'email', width:125,sorttype:"string"},
				{name:'QQ',index:'QQ', width:100},				
				{name:'mobilePhone',index:'mobilePhone', width:120},				
				{name:'birthday',index:'birthday', width:100, sorttype:"date"},
				{name:'ext01',index:'ext01', width:100},
				{name:'ext02',index:'ext02', width:100},
				{name:'ext03',index:'ext03', width:100},
				{name:'ext04',index:'ext04', width:100},
				{name:'ext05',index:'ext05', width:100},
				{name:'ext06',index:'ext06', width:100},
				{name:'ext07',index:'ext07', width:100},
				{name:'ext08',index:'ext08', width:100},
				{name:'ext09',index:'ext09', width:100},
				{name:'ext10',index:'ext10', width:100},
				{name:'ext11',index:'ext11', width:100},
				{name:'ext12',index:'ext12', width:100},
				{name:'ext13',index:'ext13', width:100},
				{name:'ext14',index:'ext14', width:100},
				{name:'ext15',index:'ext15', width:100},
				{name:'ext16',index:'ext16', width:100},
				{name:'ext17',index:'ext17', width:100},
				{name:'ext18',index:'ext18', width:100},
				{name:'ext19',index:'ext19', width:100},
				{name:'ext20',index:'ext20', width:100},
				{name:'operation',align:'center'}
		]
	});
	
	// gridTable_scroll
	$("#gridTable_warn").gridUtil().simpleGrid({
		datatype: "local",
		data: mydata1,
		autowidth: false,
		width: 960,
		shrinkToFit: false,
		pager: "#gridPager_warn",
		caption: "告警jqGrid",
		colNames:['编号','预警','用户名', '性别', '邮箱', 'QQ','手机号','出生日期','扩展1'],
		colModel:[
				{name:'id',index:'id', width:60, sorttype:"int", key:true},
				{name:'level',index:'level', width:50, hidden:true},
				{name:'userName',index:'userName', width:90},
				{name:'gender',index:'gender', width:90},
				{name:'email',index:'email', width:125,sorttype:"string"},
				{name:'QQ',index:'QQ', width:100},
				{name:'mobilePhone',index:'mobilePhone', width:120},
				{name:'birthday',index:'birthday', width:100, sorttype:"date"},
				{name:'ext01',index:'ext01', width:100}
		],
		gridComplete: function() {
			//console.log(this.rows);
			for(var i = 0; i < this.rows.length; ++i) {
				var celldata = $("#gridTable_warn").jqGrid('getCell', i, 2);
				if(celldata != "") {
					$(this.rows[i]).removeClass("altClass");
					//$(this.rows[i]).unbind('mouseover').unbind('mouseout');
					$(this.rows[i]).bind('mouseover', function(){return false;}).bind('mouseout', function(){return false;});
					$(this.rows[i]).addClass("grid_color" + celldata);
				}
			}
			
		}
	});
	//$("#gridTable_warn").jqGrid("setGridWidth", 960);
	//$("#gridTable_warn").closest(".ui-jqgrid-bdiv").css({'overflow-x' : 'scroll'});
	
	
	var groupdata = [
			{id:"1",scheduleCode:"9C8965",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"2",scheduleCode:"9C8965",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"3",scheduleCode:"9C8965",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"4",scheduleCode:"CA0002",fromSiteFullName:"广州新白云机场",toSiteFullName:"上海虹桥机场"},
			{id:"5",scheduleCode:"CA0002",fromSiteFullName:"广州新白云机场",toSiteFullName:"上海虹桥机场"},
			{id:"6",scheduleCode:"CA1001",fromSiteFullName:"北京首都机场",toSiteFullName:"上海虹桥机场"},
			{id:"7",scheduleCode:"CA1001",fromSiteFullName:"北京首都机场",toSiteFullName:"上海虹桥机场"},
			{id:"8",scheduleCode:"CA1001",fromSiteFullName:"北京首都机场",toSiteFullName:"上海虹桥机场"},
			{id:"9",scheduleCode:"CA1611",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"10",scheduleCode:"CA1611",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"11",scheduleCode:"CA1611",fromSiteFullName:"上海虹桥机场",toSiteFullName:"哈尔滨太平机场"},
			{id:"12",scheduleCode:"FM001",fromSiteFullName:"上海铁路站",toSiteFullName:"北京铁路局"},
			{id:"13",scheduleCode:"FM001",fromSiteFullName:"上海铁路站",toSiteFullName:"北京铁路局"},
			{id:"14",scheduleCode:"FM001",fromSiteFullName:"上海铁路站",toSiteFullName:"北京铁路局"}
				];

	$("#gridTable_group").gridUtil().simpleGrid({
		datatype: "local",
		data: groupdata,
		autowidth: false,
		width: 960,
		shrinkToFit: false,
		//multiboxonly: false,
		gridview: true,
		colNames:[ 'ID', 
				   '班次号', 
				   '出发点', 
				   '到达点' ],
		colModel:[ 
					{name : 'id',width : '1%', hidden:true, hidedlg:true, width : '50'},
					{name : 'scheduleCode',index : 'scheduleCode',width : '100'},
					{name : 'fromSiteFullName',index : 'fromSiteFullName',width : '400'},
					{name : 'toSiteFullName',index : 'toSiteFullName',width : '400'}
		],
		viewrecords:true,
		pager: "#gridPager_group",
		grouping:true,
		groupingView : {
			groupField : ['scheduleCode'],
			groupColumnShow : [false],
			groupText : ['<b>班次{0} - {1} 条记录</b>']
		}, 
		caption: "运输班次表格"
	});
	
	//jqGrid 动态显示列
	$("#gridTable_group").gridUtil().customizeColumn();
	//$("#gridTable_group").gridUtil().customizeColumn({caption: '显示列', title: '显示列'});
	
	/*
	$("#gridTable_group").jqGrid('navGrid','#gridPager_group',{edit:false,add:false,del:false},{},{},{top:1000,left:1000});
	$("#gridTable_group").jqGrid('navButtonAdd',"#gridPager_group",{
		caption: '显示列',
		title: '显示列',
		onClickButton :	function (){ $("#gridTable_group").jqGrid('columnChooser'); }
	});
	*/
	
	
	$("#gridTable_subgrid").gridUtil().simpleGrid({
		datatype: "local",
		data: groupdata,
		autowidth: false,
		width: 960,
		shrinkToFit: false,
		multiselect: false,
		subGrid: true,
		//subGridModel: [ 'ID', '班次号', '出发点', '到达点' ],
		//rownumbers: false,
		colNames:[ 'ID', 
				   '班次号', 
				   '出发点', 
				   '到达点' ],
		colModel:[ 
					{name : 'id',width : '1%', hidden:true, hidedlg:true, width : '50'},
					{name : 'scheduleCode',index : 'scheduleCode',width : '100'},
					{name : 'fromSiteFullName',index : 'fromSiteFullName',width : '400'},
					{name : 'toSiteFullName',index : 'toSiteFullName',width : '400'}
		],
		viewrecords:true,
		pager: "#gridPager_subgrid",
		caption: "subgrid example",
		subGridRowExpanded: function(subgrid_id, row_id) {
			// we pass two parameters
			// subgrid_id is a id of the div tag created whitin a table data
			// the id of this elemenet is a combination of the "sg_" + id of the row
			// the row_id is the id of the row
			// If we wan to pass additinal parameters to the url we can use
			// a method getRowData(row_id) - which returns associative array in type name-value
			// here we can easy construct the flowing
			
			var subgrid_table_id, pager_id;
			subgrid_table_id = subgrid_id+"_t";
			pager_id = "p_"+subgrid_table_id;
			$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			jQuery("#"+subgrid_table_id).jqGrid({
				//url:"subgrid.php?q=2&id="+row_id,
				datatype: "local",
				data: mydata1,
				colNames:['编号','预警','用户名', '性别', '邮箱', 'QQ','手机号','出生日期','扩展1'],
				colModel:[
						{name:'id',index:'id', width:60, sorttype:"int"},
						{name:'level',index:'level', width:50, hidden:true},
						{name:'userName',index:'userName', width:90, edittype: 'textarea'},
						{name:'gender',index:'gender', width:90},
						{name:'email',index:'email', width:125,sorttype:"string"},
						{name:'QQ',index:'QQ', width:100},
						{name:'mobilePhone',index:'mobilePhone', width:120},
						{name:'birthday',index:'birthday', width:100, sorttype:"date"},
						{name:'ext01',index:'ext01', width:100}
				],
				rowNum:1000,
				//pager: pager_id,
				//sortname: 'num',
				//sortorder: "asc",
				height: '100%'
			});
			//jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false})
		},
		subGridRowColapsed: function(subgrid_id, row_id) {
		},
		gridComplete: function() {
			// expand one node using script
			var rowIds = $("#gridTable_subgrid").getDataIDs();
			$.each(rowIds, function (index, rowId) {
				if (index == 0)
					$("#gridTable_subgrid").expandSubGridRow(rowId); 
			});
			$('#gridTable_subgrid tr.jqgrow td').css('white-space', 'normal');
		}
	});

/*
	// gridTreeTable
	$("#gridTreeTable").jqGrid({
		url: 'treedata.xml',
		treedatatype: "xml",
		//url: 'treedata.json',
		//treedatatype: "json",
		ajaxGridOptions: { contentType: 'application/json' },
		//ajaxGridOptions: { contentType: 'text/plain; charset=utf-8' },
		mtype: "GET",
		jsonReader: {
			root: "rows",
			page: "page",
			total: "total",
			records: "records",
			repeatitems : false
		},
		treeGridModel : 'adjacency',
		treeGrid: true,
		ExpandColumn: 'name',
		ExpandColClick: true,
		caption: "第一个jqGrid例子",
		toolbar: [true,"top"],
		pager: "#gridTreePager",
		gridComplete: function() {
			var ids = $("#gridTable").jqGrid('getDataIDs');
			for(var i=0;i < ids.length;i++){
				var mod = "<a href='###' class='a_btn_edit m-r ' onclick='alert(\"修改：" + ids[i] + "\");'><em>修改</em></a>";
				var del = "<a href='###' class='a_btn_remove m-r' onclick='alert(\"删除：" + ids[i] + "\");'><em>删除</em></a>";
				$("#gridTable").jqGrid('setRowData',ids[i],{operation:mod + del});
			}
		},
		colNames:["id","Account","Acc Num", "Debit", "Credit","Balance"], 
		colModel:[ {name:'id',index:'id', width:1,hidden:true,key:true}, {name:'name',index:'name', width:180}, {name:'num',index:'acc_num', width:80, align:"center"}, {name:'debit',index:'debit', width:80, align:"right"}, {name:'credit',index:'credit', width:80,align:"right"}, {name:'balance',index:'balance', width:80,align:"right"} ]
	});

	// jqGrid pager
	$("#gridTreeTable").jqGrid('navGrid','#gridTreePager',{edit:false,add:false,del:false});
	// jqGrid toolbar
	$("#gridTreeToolbar").appendTo($("#t_gridTreeTable"));
*/
});