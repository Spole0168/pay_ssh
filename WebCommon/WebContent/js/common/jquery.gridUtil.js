/*
* gridUtil 0.1
* Description: jqGrid extention
* Dependency: jQuery/jQuery UI/jqGrid 
* Copyright (c) 
* Date: 2010-10-10
* Author: 
* $Id: jquery.gridUtil.js 63572 2012-05-16 02:02:44Z  $
*/

;(function($){
	// .gridUtil() interface
	$.fn.gridUtil = function(jq){
		if( this instanceof $.fn.gridUtil ){
			this.$ = jq;
			return this;
		}
		return new $.fn.gridUtil(this);
	};
	// $.gridUtil(selector) interface
	$.gridUtil = function(selector){
		return new $.fn.gridUtil($(selector));
	};

	$.fn.gridUtil.prototype = $.fn.gridUtil.fn = {
		defaults : {
			datatype: "json",
			mtype: "POST",
			height: "auto",
			autowidth: true,
			sortable: true,
			altRows: true,
			altclass: 'altClass',
			multiboxonly: true,
			rowNum: 50,
			rowList: [50,100,200],
			multiselect: true,
			viewrecords: true,
			rownumbers: true,
			//autoencode: true,
			loadError : function(xhr,st,err) {
				//TODO
			},
			loadComplete: function(data){
				if(data.success == "false") {
					if (typeof data.text != 'undefined' && data.text != null && data.text.indexOf("未登录系统") > -1) {
						window.location.href = $.commonUtil.contextPath();
					} else {
						$.boxUtil.alert(data.text);
					}
				}
			},
			jsonReader: {
				root: "rows",
				page: "page",
				total: "total",
				records: "records",
				repeatitems : false,
				subgrid: { 
					root: "rows",
					page: "page",
					total: "total",
					records: "records", 
					repeatitems : false
				}
			}
		},
		defaultParams : {
			heightspan : 300
		},
		treeOptions : {
			treeGridModel : 'adjacency',
			treeGrid: true
			//ExpandColumn: 'name',
			//ExpandColClick: true
		},
		/*
		 * options : jqGrid options
		 * params : { 
		 *             singleselect : true  // could select only one row
		 *             lazyload : true  // will not load data UI onload
		 *             heightfixed : true // will fix height and freeze row header
		 *             heightspan : 300 // default is 300
		 *          }
		 */
		simpleGrid: function(options, params) {
			var opts;

			var parameters = $.extend({}, this.defaultParams, params);
			
			if (parameters.pageCache == false) {	// no page cache
				opts = {postData: {}};
			}
			else if (window.location.search.indexOf("loadPageCache=true") >= 0) {
				
				var cache, rowNum;
				try {
					//cache = $.parseJSON($.parseJSON(decodeURI($.cookie('pageCache'))));
					cache = $.parseJSON(decodeURI($.cookie('pageCache')));
					if (typeof(cache) == "string")
						cache = $.parseJSON(cache);
					rowNum = cache.rows;
				} catch(e){ };
				
				opts = {postData: {pageCache: true, loadPageCache: true}, rowNum: rowNum || options.rowNum || this.defaults.rowNum};
				
				if (typeof(cache) != 'undefined' && cache != null) {
					//console.log(cache);
					for (prop in cache){
						if ($.inArray(prop, ["sidx", "rows", "page", "sord", "nd", "pageCache"]) < 0) {
							//alert(prop + ":" + cache[prop]);
							// set post data
							opts.postData[prop] = cache[prop];
							// set search value
							$("[name='" + prop + "']").val(cache[prop]);
							//$("#" + prop).val(cache[prop]);
						}
					}
				}

				//console.log(opts);
			}
			else {
				opts = {postData: {pageCache: true, loadPageCache: false}};
			}

			// pagination check
			if (typeof(options.onPaging) == 'function') {
				var g = options.onPaging;
				options.onPaging = function(p) {
					var jq = $(this);
					if (p == 'user') {
						var pager = jq.getGridParam("pager");
						var jump = $(pager + " .ui-pg-input").val();
						if (!jump.isNumber()) {
							$.boxUtil.alert('请输入数字！');
							return 'stop';
						}
					}
					
					g.call();
				}
			} else {
				options.onPaging = function(p) {
					var jq = $(this);
					if (p == 'user') {
						var pager = jq.getGridParam("pager");
						var jump = $(pager + " .ui-pg-input").val();
						if (!jump.isNumber()) {
							$.boxUtil.alert('请输入数字！');
							return 'stop';
						}
					}
				}
			}

			// singleselect
			if (parameters.singleselect) {
				if (typeof(options.gridComplete) == 'function') {
					var g = options.gridComplete;
					options.gridComplete = function() {
						g.call();
						$("[aria-describedby='" + this.id + "_cb'] > input").attr("disabled", true);
						$("#cb_" + this.id).hide();
					}
				} else {
					options.gridComplete = function() {
						$("[aria-describedby='" + this.id + "_cb'] > input").attr("disabled", true);
						$("#cb_" + this.id).hide();
					}
				}
			}
			
			// lazyload
			if (parameters.lazyload) {
				opts.datatype = "local";
				opts.url = "";
			}

			var settings = $.extend({}, this.defaults, options, opts);
			var jq = this.$.jqGrid(settings);
			
			// lazyload
			if (parameters.lazyload) {
				jq.jqGrid("setGridParam",{url: options.url, datatype: "json"});
			}
			
			// heightfixed
			if (parameters.heightfixed) {
				$(window).bind('resize', function() {
					jq.jqGrid("setGridHeight", $(window).height()- parameters.heightspan );
				}).trigger('resize');
			}

			if($.browser.msie) {
				jq.closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'scroll' });
			}
			
			jq.setPostDataItem("loadPageCache", false);
			
			return jq;
		},
		treeGrid: function(options, params) {
			var settings = $.extend({}, this.defaults, this.treeOptions, options);
			
			var jq = this.$.jqGrid(settings);
			if($.browser.msie) {
				jq.closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'scroll' });
			}
			return jq;
		},
		searchGrid: function(form) {
			var objs = form.serializeObject();
			for (var p in objs) {
				this.$.setPostDataItem(p, objs[p]);
			}
			this.$.jqGrid("setGridParam",{page:1}).trigger("reloadGrid");
			
			return false;
		},
		exportGridExcel: function(form, options) {
			var objs = form.serializeObject();
			for (var p in objs) {
				this.$.setPostDataItem(p, objs[p]);
			}
			this.exportExcel(options);
			
			return false;
		},
		customizeColumn: function(options) {
			var util = this;
			var jq = this.$;
			// clone array
			var defaultColModel = $.parseJSON(JSON.stringify(jq.getGridParam("colModel")));
			// get name index
			var defaultColIndex = {};
			$.each(defaultColModel, function(i, n) {
				defaultColIndex[n.name] = i;
			});
			
			jq.jqGrid('navGrid',jq.getGridParam("pager"),{edit:false,add:false,del:false,search:false,refresh:true},{},{},{top:1000,left:1000});
			
			var settings = $.extend({
				caption: '自定义显示列',
				title: '自定义显示列',
				onClickButton :	function (){
					jq.jqGrid('columnChooser', util.columnOpts);
				}
			}, options || {});

			jq.jqGrid('navButtonAdd',jq.getGridParam("pager"),settings);
			
			var settings2 = $.extend({
				caption: '还原为默认列',
				title: '还原为默认列',
				onClickButton :	function (){
					// remote
					$.ajax({
						type: "POST",
						dataType: "json",
						url: "../jqGridDelAction.action",
						data: { 
							jqgrid: util.getGridId()
						}
					});
					
					// local
					var perm = new Array();
					var colIndex = {};
					var cols = jq.getGridParam("colModel");
					// sort index
					$.each(cols, function(i, n) {
						colIndex[n.name] = i;
					});
					// show and hide
					$.each(defaultColModel, function(i, n){
						if (n.hidden)
							jq.jqGrid("hideCol", n.name);
						else
							jq.jqGrid("showCol", n.name);
						perm.push(colIndex[n.name]);
					});
					// sort
					jq.jqGrid("remapColumns", perm, true);
				}
			}, options || {});
			jq.jqGrid('navButtonAdd',jq.getGridParam("pager"),settings2);
			
			// load customized columns
			$.ajax({
				type:"POST",
				dataType: "json",
				url:"../jqGridFindAction.action",
				data: {
					jqgrid: util.getGridId()
				},
				success:function(result){
					try {
						if (result != null && result.field != null) {
							
							// parse customize data
							var cols = JSON.parse(result.field);
							var perm = new Array();
							
							// if new version modify colModel, will not use customize column
							if (cols.length != defaultColModel.length)
								return;
							
							for(var i = 0; i < cols.length; ++i) {
								if (cols[i].hidden)
									jq.jqGrid("hideCol", cols[i].name);
								else
									jq.jqGrid("showCol", cols[i].name);
								
								var idx = defaultColIndex[cols[i].name];
								if (typeof(idx) == 'undefined' || idx == null)
									return;	// colModel modified, return
								
								perm.push(idx);
							}
							
							jq.jqGrid("remapColumns", perm, true);
						}
					} catch(e) {}
					
				}
			});
			
			return jq;
		},
		exportExcel: function(options, callback) {
			if (options.url.indexOf("loadPageCache=true") >= 0) {
				var rowData = this.$.jqGrid('getRowData');
				if (typeof(rowData) == 'undefined' || rowData == null  || rowData.length < 1) {
					$.boxUtil.alert('下载数据为空，请重新过滤查询条件！');
					return false;
				}
			}
			
			var postData = new Object();
			var k = 0;
			
			var colNames = this.$.getGridParam("colNames");
			var colModel = this.$.getGridParam("colModel");
			
			$.each(colModel, function(i, n){
				if(typeof(n['index']) != 'undefined' && n['index'] != null) {
					// && !n['hidden']
					var name = colNames[i];
					var model = n['name'];
					eval("postData.exportColNames" + k + "=name");
					eval("postData.exportColModels" + k + "=model");
					eval("postData.exportColModelList" + k + "=JSON.stringify(n)");
					++k;
				}
			});
			
			//this.$.appendPostData(postData);
			//var params = jQuery.param(this.$.getPostData());
			
			var params = $.extend({}, postData, this.$.getPostData());
			
			if (false != options.openWindow)
				$.commonUtil.downloadWindow(options.url, params, "post", callback);
			else
				$.commonUtil.download(options.url, params, "post", callback);
			
			//if(options.url.indexOf("?") != -1) { url = options.url+"&"+params; }
			//else { url = options.url+"?"+params; }
			//window.location = encodeURI(url);
		},
		getGridId : function(self){
			if (typeof(self) == 'undefined' || self == null)
				self = this.$;
			
			return this.columnOpts.grid_id(self);
		},
		columnOpts : {
			"grid_id" : function(self) {
				var gridId = window.location.pathname + "/#" + self[0].id;
				
				return gridId;
			},
			"customize" : function(self) {
				var colModel = self.jqGrid("getGridParam", "colModel");
				var gridId = this.grid_id(self);
				
				// save customized columns
				$.ajax({
					type:"POST",
					dataType: "json",
					url:"../jqGridSaveAction.action",
					data: {
						jqgrid: gridId,
						field: JSON.stringify(colModel)
					}
				});

			},
			"dlog_opts" : function(opts) {
				var self = this;

                var buttons = {};
				buttons['保存自定义列'] = function() {
                    opts.apply_perm();
                    opts.cleanup(false);
					opts.customize(self);
                };
                buttons[opts.bSubmit] = function() {
                    opts.apply_perm();
                    opts.cleanup(false);
                };
                buttons[opts.bCancel] = function() {
                    opts.cleanup(true);
                };
                return {
                    "buttons": buttons,
                    "close": function() {
                        opts.cleanup(true);
                    },
					"modal" : false,
                    "resizable": false,
                    "width": opts.width+20
                };
            }
		}
		
	};
	
	// .commonUtil interface
	$.commonUtil = { };
	
	$.commonUtil.contextPath = function() {
		var pathname = window.location.pathname;
		var contextPath = pathname.substr(0, pathname.indexOf("/", 1) + 1);
		return contextPath;
	};
	
	$.commonUtil.unparam = function(p) {
		var ret = {},
			seg = p.replace(/^.*\?/,'').split('&'),
			len = seg.length, i = 0, s;
		for (;i<len;i++) {
			if (!seg[i]) { continue; }
			s = seg[i].split('=');
			ret[s[0]] = s[1];
		}
		return ret;
	};
	
	$.commonUtil.download = function(url, data, method, callback) {
		var inputs = "";
		var iframeX;

		if(url && data){
			$.blockUI({
				showOverlay: false,
				message: '<div class="loading_img"><span class="loading_span">正在下载，请稍后...</span></div>',
				css: {padding: '20px'}
			});

			// remove old iframe if has
			if($("#iframeX")) $("#iframeX").remove();
			// creater new iframe
			iframeX = $("<iframe style='display:none;' src='javascript:false;' name='iframeX' id='iframeX'></iframe>").appendTo("body").hide();
			
			//split params into form inputs
			$.each(data, function(p, val){
				var value = val;
				if (val != null && typeof(val) == 'string') {
					value = val.replace(/"/g, "&quot;");
				}
				inputs += "<input type='hidden' name='"+ p +"' value=\""+ value +"\" />";
			});
			
			if (typeof method == 'undefined' || method == null)
				method = "POST";
			//create form to send request
			var form = $("<form action='" + url + "' method='" + method + "' target='iframeX'>" + inputs + "</form>").appendTo("body");
			
			if($.browser.msie){
				iframeX.get(0).onreadystatechange = function() {
					if ( iframeX.get(0).readyState == "loading" ) {	// loading state
						$.unblockUI();
					} else if ( iframeX.get(0).readyState == "complete" ) {
						_finish(iframeX, form, callback);
					}
				};
			} else if ($.browser.mozilla){
				iframeX.get(0).onload = function() {
					$.unblockUI();
					_finish(iframeX, form, callback);
				};
			} else {	// do not support chrome yet
				setTimeout($.unblockUI,	2000);
			}

			form.submit();
			form.remove();
			
			setTimeout($.unblockUI,	200000);
		};
	};

	$.commonUtil.downloadWindow = function(url, data, method, callback) {
		var inputs = "";

		if(url && data){
			
			//split params into form inputs
			$.each(data, function(p, val){
				var value = val;
				if (val != null && typeof(val) == 'string') {
					value = val.replace(/"/g, "&quot;");
				}
				inputs += "<input type='hidden' name='"+ p +"' value=\""+ value +"\" />";
			});
			
			if (typeof method == 'undefined' || method == null)
				method = "POST";
			//create form to send request
			var form = $("<form action='" + url + "' method='" + method + "' target='_blank'>" + inputs + "</form>").appendTo("body");
			
			form.submit();
			form.remove();
		};
	};
	
	var _finish = function(iframeX, form, callback) {
		$.unblockUI();
		
		/*
		setTimeout(function(){
			//form.remove();
			iframeX.remove();
		}, 100);
		*/
		
		if (typeof(callback) == 'function')
			callback();
	};

})(jQuery);
