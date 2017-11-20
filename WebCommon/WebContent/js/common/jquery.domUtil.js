/*
* domUtil 0.1
* Description: HTML DOM operation
* Dependency: jQuery
* Copyright (c) 
* Date: 2010-09-01
* Author: 
* $Id: jquery.domUtil.js 66941 2012-06-15 08:01:57Z  $
*/

;(function($){
	// jQuery default settings
	$.ajaxSettings.traditional = true;
	
	// .list() interface
	$.fn.list = function(jq){
		if( this instanceof $.fn.list ){
			this.$ = jq;
			return this;
		}
		return new $.fn.list(this);
	};
	// $.list(selector) interface
	$.list = function(selector){
		return new $.fn.list($(selector));
	};

	$.fn.list.prototype = $.fn.list.fn = {
		defaults : {
			array: [],
			hiddenField: "id",
			displayedField: "name",
			prepend: null
		},
		
		bindDS : function(options, value){
			var options = $.extend({}, this.defaults, options);
			//console.log(this.$);
			//console.log($(this));
			
			var thisList = this.$;
			$(thisList).empty();

			if (options.prepend != null) {
				var opt = document.createElement("option");
				opt.value = options.prepend[options.hiddenField];
				opt.innerHTML = options.prepend[options.displayedField];
				opt.selected = false;
				$(thisList)[0].appendChild(opt);
			}
			
			$.each(options.array, function(i, n) {
				var opt = document.createElement("option");
				opt.value = n[options.hiddenField];
				opt.innerHTML = n[options.displayedField];
				if (typeof(value) != 'undefined' && opt.value == value)
					opt.selected = true;
				else
					opt.selected = false;
				$(thisList)[0].appendChild(opt);
			});
		},
		
		setValue: function(value){
			var thisList = this.$;
			if($.browser.msie && $.browser.version=="6.0") {	// IE6
				$.each($(thisList)[0], function(i, n) {
					if (n.value == value) {
						n.selected = true;
						return false;
					}
				});
			} else {
				thisList.attr('value', value);
			}
		},

		append : function(options){
			var defaults = {
				array: [],
				hiddenField: "id",
				displayedField: "name"
			};
			var options = $.extend({}, this.defaults, options);
			var thisList = this.$;
			
			$.each(options.array, function(i, n) {
				var opt = document.createElement("option");
				opt.value = n[options.hiddenField];
				opt.innerHTML = n[options.displayedField];
				opt.selected = false;
				$(thisList)[0].appendChild(opt);
			});
		},
		
		preppend : function(){
		},
		
		getAllValues : function(){
			var thisList = this.$;
			var values = new Array($(thisList)[0].length);
			
			$.each($(thisList)[0], function(i, n) {
				values[i] = n.value;
			});
			
			return values;
		},
		
		getSelectedValues : function(){
			var thisList = this.$;
			var selectedList = $(thisList).children("option:selected");
			var values = new Array(selectedList.length);
			
			$.each(selectedList, function(i, n) {
				values[i] = n.value;
			});
			
			return values;
		},

		moveUp: function(){
			var thisList = this.$;

			var selectedList = $(thisList).children("option:selected");
			if(selectedList[0] != null && selectedList[0].index!=0){
				selectedList.each(function(){
					$(this).prev().before($(this));
				});
			}
			thisList.focus();
		},
		
		moveDown: function(){
			var thisList = this.$;
			var selectedList = $(thisList).children("option:selected");
			if(selectedList[0] != null && selectedList.get(selectedList.length-1).index != $(thisList).children("option").length - 1){
				for(i = selectedList.length - 1; i>=0 ; i--){
					var item = $(selectedList.get(i));
					item.insertAfter(item.next());
				}
			}
			thisList.focus();
		},
		
		copySelected: function(dest){
			var thisList = this.$;
			$(thisList).children("option:selected").filter(function(i){
				return $.inArray($(this).val(), dest.list().getAllValues()) == -1;
			}).clone().appendTo(dest);
		},
		
		copyAll: function(dest){
			var thisList = this.$;
			dest.empty();
			$(thisList).children("option").clone().appendTo(dest);
		},
		
		removeSelected: function(dest){
			var thisList = this.$;
			dest.children("option:selected").remove();
		},
		
		removeAll: function(dest){
			var thisList = this.$;
			dest.children("option").remove();
		},
		
		moveSelected: function(dest){
			var thisList = this.$;
			$(thisList).children("option:selected").clone().appendTo(dest);
			$(thisList).children("option:selected").remove();
		},
		
		moveAll: function(dest){
			var thisList = this.$;
			$(thisList).children("option").clone().appendTo(dest);
			$(thisList).children("option").remove();
		}
	};

	// .check() interface
	$.fn.check = function(jq){
		if( this instanceof $.fn.check ){
			this.$ = jq;
			return this;
		}
		return new $.fn.check(this);
	};
	// $.check(selector) interface
	$.check = function(selector){
		return new $.fn.check($(selector));
	};
	
	$.fn.check.prototype = $.fn.check.fn = {
		defaults : {
			array: [],
			hiddenField: "id",
			displayedField: "name",
			checkedField: "checked",
			checkboxName: "",
			isHref: false
		},
		
		bindDS : function(options){
			var thisCheck = this.$;
			$(thisCheck).find("legend").nextAll().remove();
			
			this.append(options);
		},
		append : function(options){
			var thisCheck = this.$;
			
			var opts = $.extend({}, this.defaults, options);
			
			var _innerHTML = "";
			$.each(opts.array, function(i, n) {
				if (jQuery.inArray(n[opts.hiddenField], $(thisCheck).check().getAllValues()) < 0) {	// unique value

					var checked = "";
					if (n[opts.checkedField])
						checked = "checked='checked'";
					
					var content = "";
					if (opts.isHref)
						content = "<a>" + n[opts.displayedField] + "</a>";
					else
						content = n[opts.displayedField];
	
					_innerHTML += "<label><input " + checked + " type='checkbox' name='" + opts.checkboxName + "' value='" + n[opts.hiddenField] + "' /> " + content + "</label>\n";
				}
			});
			
			$(thisCheck).append(_innerHTML);
		},
		getCheckedValues : function(){
			var thisCheck = this.$;
			
			var selectedCheck = $(thisCheck).find("legend").nextAll().find("input:checked");
			var values = new Array(selectedCheck.length);
			
			$.each(selectedCheck, function(i, n) {
				values[i] = n.value;
			});
			
			return values;
		},
		setCheckedValues : function(values){
			var thisCheck = this.$;
			
			var allCheck = $(thisCheck).find("legend").nextAll();
			$.each(values, function(i, n) {
				allCheck.find("input[value='" + n + "']").attr("checked", true);
			});
			
		},
		getAllValues : function(){
			var thisCheck = this.$;
			
			var allCheck = $(thisCheck).find("legend").nextAll().find("input");
			var values = new Array(allCheck.length);
			
			$.each(allCheck, function(i, n) {
				values[i] = n.value;
			});
			
			return values;
		},
		getAllNames : function(){
			var thisCheck = this.$;
			
			var allCheck = $(thisCheck).find("legend").nextAll();
			var names = new Array(allCheck.length);
			
			$.each(allCheck, function(i, n) {
				names[i] = $(n).text();
			});
			
			return names;
		},
		selectAllValues : function(){
			var thisCheck = this.$;
			
			$(thisCheck).find("legend").nextAll().find("input").attr("checked", true);
		},
		deselectAllValues : function(){
			var thisCheck = this.$;
			
			$(thisCheck).find("legend").nextAll().find("input").attr("checked", false);
		},
		clearAll : function(){
            var thisCheck = this.$;
            
			$(thisCheck).find("legend").nextAll().remove();
		}
	};

	// .input() interface
	$.fn.input = function(jq){
		if( this instanceof $.fn.input ){
			this.$ = jq;
			return this;
		}
		return new $.fn.input(this);
	};
	// $.input(selector) interface
	$.input = function(selector){
		return new $.fn.input($(selector));
	};

	$.fn.input.prototype = $.fn.input.fn = {
		enter : function(callback){
			this.keypress([13, 16777296], callback);
			
			return this;
		},
		tab : function(callback){
			this.keypress([9], callback);
			
			return this;
		},
		keypress : function(keyCode, callback) {
			if (typeof(callback) != 'function')
				return false;
			
			this.$.bind('keypress', function(e){
				if ($.inArray(e.which, keyCode) >= 0) {
					e.preventDefault();
					e.target.blur();
					callback();
				}
			});
			
			return true;
		},
		disableBackSpace : function() {
			this.disable(8);
			
			return this;
		},
		disable : function(keyCode) {
			this.$.bind('keydown', function(e){
				if (e.which == keyCode) {
					e.preventDefault();
				}
			});
			
			return this;
		},
		trim : function() {
			this.$.bind('blur', function(e){
				var str = $.trim($(this).val());
				//str = str.replace(/(^\32*)|(\32*$)/g, "");
				$(this).val( str );
			});
			
			return this;
		},
		upperCase : function(f) {
			this.$.bind('blur', function(e){
				if (typeof f == 'function') {
					if (f.call())
						return;	// don't upper when match condition
				}
				var str = $(this).val().toUpperCase();

				$(this).val( str );
			});
			
			return this;
		},
		lowerCase : function() {
			this.$.bind('blur', function(e){
				var str = $(this).val().toLowerCase();

				$(this).val( str );
			});
			
			return this;
		}
	};

	$.fn.serializeObject = function(){
		var obj = {};

		$.each( this.serializeArray(), function(i,o){
			var n = o.name,
			v = o.value;
			
			obj[n] = obj[n] === undefined ? v
			  : $.isArray( obj[n] ) ? obj[n].concat( v )
			  : [ obj[n], v ];
		});
		
		return obj;
	};
	
	$.fn.dropdownMenu = function(options){
		var defaults = {type : 'hover'};
		var settings = $.extend({}, defaults, options);

		return this.each(function() {
			var obj = $(this);

			if ('hover' == settings.type) {
				// 解决IE中的父子元素的onmouseover问题
				obj.bind("mouseenter mouseleave", function(e){
					$(this).toggleClass("dropdown-active");
				});
			} else if ('click' == settings.type) {
				$(document).click(function() {
					obj.removeClass('dropdown-active');
				});
				
				obj.bind('click', function(){
					$('.dropdown-active').not(obj).removeClass("dropdown-active");
					
					if (obj.hasClass("dropdown-active")) {
						obj.removeClass("dropdown-active");
					} else {
						obj.addClass("dropdown-active");
					}
				});
				
				obj.bind('click', function(e){
					e.stopPropagation();
				});
			}
		});
	};
	
	$.fn.collapseBlock = function(options){
		var defaults = { collapsed : false };
		var settings = $.extend({}, defaults, options);
		
		return this.each(function() {			
			if (settings.collapsed) {
				$(this).next(".block_container:first").hide();
				$(this).children(".block_show").removeClass("block_show").addClass("block_hide");
				
				$(this).css("cursor", "pointer").toggle(function(){
					$(this).next(".block_container:first").show();
					$(this).children(".block_hide").removeClass("block_hide").addClass("block_show");
				}, function(){
					$(this).next(".block_container:first").hide();
					$(this).children(".block_show").removeClass("block_show").addClass("block_hide");
				});
			} else {
				$(this).css("cursor", "pointer").toggle(function(){
					$(this).next(".block_container:first").hide();
					$(this).children(".block_show").removeClass("block_show").addClass("block_hide");
				}, function(){
					$(this).next(".block_container:first").show();
					$(this).children(".block_hide").removeClass("block_hide").addClass("block_show");
				});
			}
		});
	};
	
	$.fn.collapseContainer = function(options){
		var defaults = { collapsed : false };
		var settings = $.extend({}, defaults, options);
		
		return this.each(function() {
			if (settings.collapsed) {
				$(this).parent().next(".fieldset_container:first").hide();
				$(this).removeClass("container_show").addClass("container_hide");

				$(this).toggle(function(){
					$(this).parent().next(".fieldset_container:first").show();
					$(this).removeClass("container_hide").addClass("container_show");
				},function(){
					$(this).parent().next(".fieldset_container:first").hide();
					$(this).removeClass("container_show").addClass("container_hide");
				});
			} else {
				$(this).toggle(function(){
					$(this).parent().next(".fieldset_container:first").hide();
					$(this).removeClass("container_show").addClass("container_hide");
				},function(){
					$(this).parent().next(".fieldset_container:first").show();
					$(this).removeClass("container_hide").addClass("container_show");
				});
			}
		});
	};
	
})(jQuery);

