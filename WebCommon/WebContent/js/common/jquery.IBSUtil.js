/*
* IBSUtil 0.1
* Description: IBS Application Utilities
* Dependency: IBS Application
* Copyright (c) 
* Date: 2011-09-01
* Author: 
* $Id: jquery.domUtil.js 31570 2011-03-14 07:18:41Z  $
*/

;(function($){
	// .IBSUtil interface
	$.IBSUtil = {
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * options是autocomplete的一些参数(url查询的地址, requestParamName请求名称, responseParamName返回结果名称,可为空)
		 * conditions对于AJAX请求时的查询条件
		 * displays显示信息 {label标签信息, value值信息, ...}，其中label和value为必填信息，
		 * 			例如：{label: "item.code + ' - ' + item.name", value: "item.code + ' - ' + item.name", ...} 
		 * elements回写输入框信息，即一般选中某条数据后，回写隐藏的input
		 * 			例如：{orgCode: $("#orgCode"), orgName: $("#orgName")}；表示从将orgCode从item中获取后，数据写到隐藏的orgCode和orgName中
		 * callback回调函数，当触发select事件时做的操作
		 */
		autocomplete : function(inputVar, options, conditions, displays, elements, callback) {
			
			var displaysInfo = $.extend({}, displays);
			var elementsInfo = $.extend({}, elements);
			var cond = new Object();
			
			inputVar.autocomplete({
				source: function( request, response ) {
					cond[options.requestParamName] = inputVar.val();
					var conditionsInfo = $.extend({}, conditions, cond);

					// 清空数据
					for (var p in elementsInfo) {
						elementsInfo[p].val("");
					}
					
					$.ajax({
						url: options.url,
						type: "POST",
						dataType: "json",
						data: conditionsInfo,
						success: function( data ) {
							var result = data;
							if (options.responseParamName)
								result = data[options.responseParamName];
							response( $.map( result, function( item ) {
								var result = new Object();
								for(p in displaysInfo) {
									result[p] = eval(displaysInfo[p]);
								}
								return result;
							}));
						}
					});
				},
				select: function( event, ui ) {
					for (var p in elementsInfo) {
						elementsInfo[p].val(ui.item[p]);
					}
					inputVar.val(ui.item.value);
					if ($.isFunction(callback))
						callback(ui.item);
				},
				change: function( event, ui ) {
					try {
						if(ui.item == null || ui.item.value != inputVar.val()){
							for (var p in elementsInfo) {
								elementsInfo[p].val("");
							}
							inputVar.val("");
						}
					} catch(err) {
						for (var p in elementsInfo) {
							elementsInfo[p].val("");
						}
						inputVar.val("");
					}
				}
			});
		},
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * options(url查询的地址, requestParamName请求名称, responseParamName返回结果名称)
		 * conditions查询条件 {topOrgCode根节点, functions具有职能}，默认值是空即所有
		 * displays显示信息 {label标签信息, value值信息}，默认值是code - name
		 * elements回写输入框信息 {orgCode编码, orgName名称, orgType类型, orgId}
		 * callback回调函数，当处罚select事件时做的操作
		 */
		findOrgs : function(inputVar, options, conditions, displays, elements, callback) {
			
			var url = $.commonUtil.contextPath() + "mdmorgutil/autoCompleteOrgs.action";
			var optionsInfo = $.extend({}, {url: url, requestParamName: "searchVar", responseParamName: "orgs"}, options);
			
			var defaultDisplays = {
					label: "item.code + ' - ' + item.name", 
					value: "item.code + ' - ' + item.name", 
					orgCode: "item.code", 
					orgName: "item.name",
					orgType: "item.type",
					orgId: "item.id"
				};
			
			var displaysInfo = $.extend({}, defaultDisplays, displays);
			
			this.autocomplete(inputVar, optionsInfo, conditions, displaysInfo, elements, callback);
			
		},
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * options(url查询的地址, requestParamName请求名称, responseParamName返回结果名称)
		 * conditions查询条件 {orgCode网点, needSubOrg包含下属网点'Y'，recordsSize: 30}，默认值是空即所有
		 * displays显示信息 {label标签信息, value值信息}，默认值是code - name
		 * elements回写输入框信息 {empCode编码, empName名称, orgType类型, id}
		 * callback回调函数，当处罚select事件时做的操作
		 */
		findEmps : function(inputVar, options, conditions, displays, elements, callback) {
			
			var url = $.commonUtil.contextPath() + "mdmemputil/autoCompleteEmployee.action";
			var optionsInfo = $.extend({}, {url: url, requestParamName: "searchVar", responseParamName: "emps"}, options);
			
			var defaultDisplays = {
					label: "item.code + ' - ' + item.name", 
					value: "item.code + ' - ' + item.name", 
					empCode: "item.code", 
					empName: "item.name",
					empId: "item.id"
				};
			
			var displaysInfo = $.extend({}, defaultDisplays, displays);
			
			this.autocomplete(inputVar, optionsInfo, conditions, displaysInfo, elements, callback);
			
		},
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * options(url查询的地址, requestParamName请求名称, responseParamName返回结果名称)
		 * conditions查询条件 {orgCode网点, needSubOrg包含下属网点'Y', recordsSize: 10, userTypes: 'customer|employee', userStatus: 'VALID'}，默认值是空即所有
		 * displays显示信息 {label标签信息, value值信息}，默认值是code - name
		 * elements回写输入框信息 {code编码, name名称, type类型, id}
		 * callback回调函数，当处罚select事件时做的操作
		 */
		findUsers : function(inputVar, options, conditions, displays, elements, callback) {
			
			var url = $.commonUtil.contextPath() + "mdmuserutil/autoCompleteUser.action";
			var optionsInfo = $.extend({}, {url: url, requestParamName: "searchVar", responseParamName: "users"}, options);
			
			var defaultDisplays = {
					label: "item.userName + ' - ' + item.displayName",
					value: "item.userName + ' - ' + item.displayName",
					code: "item.userName", 
					name: "item.displayName",
					type: "item.userType",
					id: "item.id"
				};
			
			var displaysInfo = $.extend({}, defaultDisplays, displays);
			
			this.autocomplete(inputVar, optionsInfo, conditions, displaysInfo, elements, callback);
			
		},
		// TODO
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * groupOptions组信息{type: 'ORG', code: 'XXXX', isPublic: ''}
		 * memberOptions组成员选项(对于网点组：topOrgCode，userPostParm，sqlParm等)
		 * conditions查询条件 {topOrgCode根节点, functions具有职能}，默认值是空即所有
		 * displays显示信息 {label标签信息, value值信息}，默认值是code - name
		 */
		setFieldSetMembers: function(fieldsetVar, groupOptions, memberOptions, conditions, displays) {
			
			// 自定义显示
			var displayFlag = window.location.pathname;
			
			fieldsetVar.find(" > legend .icon-search");
			
			// 保存为默认
			fieldsetVar.find(" > legend .icon-save").click(function(){
				$.blockUI({
					message: "<div id='loading'><div class='loading_img'><span class='loading_span'>正在保存，请稍候...</span></div></div>",
					css: {padding: '20px'}
				});
				
				$.ajax({
					type: "POST",
					url: $.commonUtil.contextPath() + "mdmgrouputil/groupMemberDisplaySave.action",
					dataType: "json",
					data: ( $.param({
						flag: displayFlag,
						type: 'ORG',
						allValues: fieldsetVar.check().getAllValues(),
						allNames: fieldsetVar.check().getAllNames(),
						//allNames: fieldsetVar.children("label").text(),
						selectedValues: fieldsetVar.check().getCheckedValues()
					}, true)),
					success: function(result){
						$.unblockUI();
						if(result.message=="OK") {
							$.growlUI('成功信息！', '保存成功！');
						} else {
							$.boxUtil.error('保存失败');
						}
					}
				});
			});
			
			// 清除
			fieldsetVar.find(" > legend .icon-delete").click(function(){fieldsetVar.check().clearAll();});
			
			// 全选
			fieldsetVar.find(" > legend .icon-select").click(function(){fieldsetVar.check().selectAllValues();});
			
			// 反选
			fieldsetVar.find(" > legend .icon-deselect").click(function(){fieldsetVar.check().deselectAllValues();});
			
			this.findOrgs(fieldsetVar.find(" > legend input:first"), null, conditions, displays, null, function(item){
				// 删除说明文字
				fieldsetVar.find(".form_tips").remove();
				fieldsetVar.check().append({array:[{id: item.orgCode, name: item.orgName, checked: true}], checkboxName: fieldsetVar.attr("id")});
				// 清空输入框
				item.value = "";
			});
			
			// 加载默认值
			$.ajax({
				type: "POST",
				url: $.commonUtil.contextPath() + "mdmgrouputil/groupMemberDisplaySearch.action",
				dataType: "json",
				data: {
					flag: displayFlag,
					type: 'ORG'
				},
				success: function(result){
					if (result.length > 0) {
						fieldsetVar.check().bindDS({array:result, hiddenField: "memberCode", displayedField: "memberName", 
							checkedField: "checked", checkboxName: fieldsetVar.attr("id")});
					}
				}
			});
		},
		/*
		 * inputVar(autocomplete元素的jquery变量)
		 * groupOptions组信息{type: 'ORG', flag: 'XXXX', displayFlag: 'XXXX', includePublic: true}
		 * memberOptions组成员选项(对于网点组：topOrgCode，userPostParm，sqlParm，functions等)
		 * displays显示信息 {label标签信息, value值信息}，默认值是code - name
		 */
		setFieldSetGroups: function(fieldsetVar, groupOptions, memberOptions, displays) {
			// 维护网点组
			var dialogId = "dialog-org-group-ajax";
			
			var defaultGroupOptions = {type: "ORG", flag: "DEFAULT", includePublic: true};
			var data = $.extend({dialogId: dialogId, fieldsetId: fieldsetVar.attr("id")}, defaultGroupOptions, groupOptions);
			var url = $.commonUtil.contextPath() + "mdmgrouputil/groupList.action";
			// 自定义显示
			var displayFlag = window.location.pathname;
			if (groupOptions && groupOptions.displayFlag)
				displayFlag = groupOptions.displayFlag;
			
			// 维护网点组
			fieldsetVar.find(" > legend .icon-search").click(function(){
				$dlg = $.IBSUtil.getOrCreateDialog(dialogId, {title: '网点组管理', width: 960, height:650});
				$dlg.dialog('open');
				$dlg.load(url, data);
				return false;
			});
			
			// 成员全局变量
			globalMemberOptions = memberOptions;
			
			// 保存为默认
			fieldsetVar.find(" > legend .icon-save").click(function(){
				$.blockUI({
					message: "<div id='loading'><div class='loading_img'><span class='loading_span'>正在保存，请稍候...</span></div></div>",
					css: {padding: '20px'}
				});
				
				$.ajax({
					type: "POST",
					url: $.commonUtil.contextPath() + "mdmgrouputil/groupDisplaySave.action",
					dataType: "json",
					data: ( $.param({
						flag: displayFlag,
						allValues: fieldsetVar.check().getAllValues(), 
						selectedValues: fieldsetVar.check().getCheckedValues()
					}, true)),
					success: function(result){
						$.unblockUI();
						if(result.message=="OK") {
							$.growlUI('成功信息！', '保存成功！');
						} else {
							$.boxUtil.error('保存失败');
						}
					}
				});
			});
			
			// 清除
			fieldsetVar.find(" > legend .icon-delete").click(function(){fieldsetVar.check().clearAll();});
			
			// 全选
			fieldsetVar.find(" > legend .icon-select").click(function(){fieldsetVar.check().selectAllValues();});
			
			// 反选
			fieldsetVar.find(" > legend .icon-deselect").click(function(){fieldsetVar.check().deselectAllValues();});
			
			// 输入框 autocomplete
			var options = {url: $.commonUtil.contextPath() + "mdmgrouputil/groupAutocomplete.action", requestParamName: "searchVar"};
			$.IBSUtil.autocomplete(fieldsetVar.find(" > legend input:first"), 
					options, 
					{flag: data.flag}, 
					{label: "item.groupName", value: "item.groupName", groupId: "item.groupId"}, 
					null, 
					function(item){
						// 删除说明文字
						fieldsetVar.find(".form_tips").remove();
						fieldsetVar.check().append({array:[{id: item.groupId, name: item.label, checked: true}], checkboxName: fieldsetVar.attr("id"), isHref: true});
						// 清空输入框
						item.value = "";
						$.IBSUtil.tipGroup(fieldsetVar);
					});
			
			// 加载默认值
			$.ajax({
				type: "POST",
				url: $.commonUtil.contextPath() + "mdmgrouputil/groupDisplaySearch.action",
				dataType: "json",
				data: {
					flag: displayFlag
				},
				success: function(result){
					if (result.length > 0) {
						fieldsetVar.check().bindDS({array:result, hiddenField: "groupId", displayedField: "groupName", 
							checkedField: "checked", checkboxName: fieldsetVar.attr("id"), isHref: true});
						$.IBSUtil.tipGroup(fieldsetVar);
					}
				}
			});
		},

		getOrCreateDialog : function(id, options) {
			$dlg = $('#' + id);
			if (!$dlg.length) {
				$dlg = $("<div id='" + id + "'</div>").hide().appendTo("body");
			}
			$dlg.html("<div style='text-align: center;'><img src='/common/images/framework/ajax-loader4.gif' />");
			
			var defaults = {autoOpen: false, bgiframe:true, resizable:false, modal: true};
			
			$dlg.dialog($.extend({}, defaults, options));
			
			return $dlg;
		},
		
		getOrCreateForm : function(id, data) {
			
		},
		
		tipGroup : function(fieldsetVar) {
			// 商品提示
			fieldsetVar.find("legend").nextAll().find("a").each(function(){
				$(this).click(function(){return false;});
				$(this).qtip({
					content: {
						text: "<div class='loading_text'>加载中，请稍后...</div>",
						url: $.commonUtil.contextPath() + "mdmgrouputil/groupMemberView.action",
						data: {groupId: $(this).prev("input").attr("value")},
						title: {text: "包含组成员"},
						once: true
					},
					position: {
						corner:	{
						   target: 'topMiddle', // Position the tooltip above the link
						   tooltip:	'bottomMiddle'
						},
						adjust: {
							screen: true,
							scroll: false
						}
					},
					show: { 
						when: 'click',
						//delay: 300,
						solo: true // Only show one tooltip at a time
					},
					hide: {
						when: {
							target: $(document.body).children().not( $(self) ),
							event : 'mousedown'
						}
					},
					style:	{
						tip: true,
						width: 200,
						name: 'cream' // Use the default light	style
					},
					api: {
						beforeRender: function(self) {
							this.options.content.data.id = this.elements.target.html();
						}
					}
				});
			});
		}
		
	};

})(jQuery);
