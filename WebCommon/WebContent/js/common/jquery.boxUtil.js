/*
* boxUtil 0.1
* Description: jQuery UI extention
* Dependency: jQuery UI
* Copyright (c) 
* Date: 2011-01-10
* Author: 
* $Id: jquery.boxUtil.js 45072 2011-09-21 09:41:07Z  $
*/

;(function($){
	// .boxUtil interface
	$.boxUtil = {
		alert: function(message, options, callback) {
			this.msg(message, options, callback, "warn", "提示");
		},
		warn: function(message, options, callback) {
			this.msg(message, options, callback, "warn", "警告");
		},
		error: function(message, options, callback) {
			this.msg(message, options, callback, "error", "错误");
		},
		info: function(message, options, callback) {
			this.msg(message, options, callback, "info", "信息");
		},
		success: function(message, options, callback) {
			this.msg(message, options, callback, "success", "成功");
		},
		saving: function(message) {
			$.blockUI({
				message: "<div id='loading'><div class='loading_img'><span class='loading_span'>正在保存，请稍候...</span></div></div>",
				css: {padding: '20px'}
			});
		},
		
		confirm: function(message, options, okCallback, cancelCallback) {
			var defaults = {
				autoOpen: true,
				modal : true,
				resizable : false,
				bgiframe:true,
				buttons : {
					'确定': function() {
						$(this).dialog('close');
						if (typeof okCallback == 'function')
							okCallback();
					},
					'取消': function() {
						$(this).dialog('close');
						if (typeof cancelCallback == 'function')
							cancelCallback();
						return false;
					}
				},
				minWidth : 50,
				minHeight : 50,
				title : '确认'
			}
			
			$box = _getOrCreateDialog('confirm');

			$box.dialog("destroy");
			$("p", $box).html("<span class='question'></span>" + message);
			// init dialog
			$box.dialog($.extend({}, defaults, options));
		},
		
		msg: function(message, options, callback, level, title) {
			if (typeof(level) == 'undefined' || level == null || level == "")
				level = "warn";
			if (typeof(title) == 'undefined' || title == null || title == "")
				title = "提示";

			var defaults = {
				autoOpen: true,
				modal : true,
				resizable : false,
				bgiframe:true,
				buttons : {
					'确定': function() {
						$(this).dialog('close');
						if (typeof callback == 'function')
							callback();
					}
				},
				minWidth : 50,
				minHeight : 50,
				title : title
			};

			$box = _getOrCreateDialog('alert');

			$("p", $box).html("<span class='" + level + "'></span>" + message);
			// init dialog
			$box.dialog("destroy");
			$box.dialog($.extend({}, defaults, options));
		}
	};
	
	var _getOrCreateDialog = function(id) {
		$box = $('#' + id);
		if (!$box.length) {
			$box = $('<div id="' + id + '"><p class="box"></p></div>').hide().appendTo('body');
		}
		return $box;
	};

	$.extend( $.ui.autocomplete, {
		escapeRegex: function( value ) {
			return value.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
		},
		filter: function(array, term) {
			var matcher = new RegExp( $.ui.autocomplete.escapeRegex(term), "i" );
			return $.grep( array, function(value) {
				return matcher.test( value.label || value.value || value );
			});
		}
	});
	// autocomplete select first extention
	$( ".ui-autocomplete-input" ).live( "autocompleteopen", function() {
		var autocomplete = $( this ).data( "autocomplete" ),
			menu = autocomplete.menu;
	
		if ( typeof autocomplete.options.selectFirst != 'undefined' 
			&& !autocomplete.options.selectFirst) {
			return;
		}
	
		menu.activate( $.Event({ type: "mouseenter" }), menu.element.children().first() );
	});
	
})(jQuery);
