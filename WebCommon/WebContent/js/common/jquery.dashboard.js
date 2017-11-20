var ct = null;		//Current active tab
var c1, c2, c3;		//The three main containers

Array.prototype.each = function(f) {
	for (var i=0; i < this.length; i++)
		f(this[i],i,this);
};
Array.prototype.remove = function(m) {
	for (var i=0; i < this.length; i++)
		if (this[i] === m) this.splice(i, 1);
	return this;
};
Array.prototype.test = function(id) {
	for (var i=0; i < this.length; i++)
		if (this[i].id == id) return this[i];
	return false;
};

// Self-remove
jQuery.fn.rm = function() {
	return this.each(function() {
		this.parentNode.removeChild(this);
	});
};

// For DIV.module to load content
jQuery.fn.loadContent = function(url) {
	return this.each(function() {
		u = (url || this.url);
		if (u == '') return;
		x = $(".moduleContent", this);
		x.load(u, prepModule);
		this.loaded = true;
	});
};

// Load layout defined in modules.js
function loadLayout() {
	// Predefined layout from modules.js is used here, but you can use
	// dynamic script to load customized layout instead
	var list = _layout;

	layout(eval(list));

	function layout(l) {
		$.each(l.reverse(), function(i,m) {
			if (m) {
				var x = eval("_modules."+m.i);
				if ($("#"+m.t).size() > 0)
					$("#"+m.c).addModule({id:m.i, title:x.t, url:x.l, tab:m.t, color:(x.c || null)});
			}
		});
	}
};

// Used on pre-formated <DIV><UL.tabsul><DIVs></DIV> section
jQuery.fn.Tabs = function() {
	return this.each(function() {
		x = $(this);
		var targets = x.children("div").addClass("tabsdiv").hide();

		x.children(".tabsul").children("li").each(function(i) {
			this.target = targets[i];
			$(this.target).attr("ref", this.id);
			$(this).click(function() {
				if ($(this).is(".on")) return;

				y = $(this).siblings(".on");
				y.add(this).toggleClass("on");
				if ((this.id) && (!this.loaded)) {
					var params = $(this.target).find("form").serializeObject();
					$(this.target).load(eval("_modules."+this.id+".l"), params, prepModule);
					this.loaded = true;
				}
				$(this.target).add(y.size() > 0 ? y[0].target : null).toggle();
			});
		}).eq(0).click();
	});
};

// Process the content of a newly loaded module before showing
function prepModule() {
	$(".tabs",this).Tabs();
	$("a.local",this).LocalLink();
	$("a.tab",this).TabLink();
	$("a[href^=http]",this).attr("target","_blank");
	$("a[rel=new]",this).attr("target","_blank");

	$(this).slideDown();
};

//Used on DIV.main_container only, 'tab' is used only when loading layouts
//When 'tab' is omitted, ct (current tab) is used instead
jQuery.fn.addModule = function(settings) { //id, title, url, tab, class
	return this.each(function() {
		var options = $.extend({
			title	: null,
			tab		: null,
			color	: null
		}, settings);

		var tx = options.tab ? document.getElementById(options.tab) : ct;
		if (!options.tab && (m = ct.modules.test(options.id))) {
			$(m).fadeTo('fast', 0.5).fadeTo('fast', 1);
			return; //Disable duplicate in a same tab
		}

		y = $("#module_template").clone(true);
		x = y[0];
		x.loaded = false;
		x.url = options.url;
		x.tab = tx.id;
		x.id = options.id;

		tx.modules.push(x);

		if (options.title) $(".moduleTitle", x).text(options.title);
		else $(".moduleHeader",x).rm();

		if (options.color) $(x).addClass(options.color);

		if (tx === ct) $(x).show();
		$(this).prepend(x);

		if (!options.tab) {
			$(x).loadContent();
		}
	});
};

// Actions taken when you click a header tab
function HeaderTabClick(){
	if (this === ct) return;	//Return if click on current active tab

	location.hash = '#' + this.id;
	$(this).siblings(".on").andSelf().toggleClass("on");

	//Hide last tab's modules
	$(".module:visible").hide();

	ct = this;
	x = eval("_tabs."+ct.id);

	//Ajust column widths
	c1.css({width:x.c1});
	c2.css({width:x.c2});
	if (x.c2 == "auto") {
		c2.css({'float':"none", marginLeft:x.c1});
	} else {
		c2.css({'float':"left", marginLeft:0});
	}
	c3.css({width:(x.c3 || 0)});

	//Load content and show module
	this.modules.each(function(m,i){
		// Lazy loading when the tab is activated
		if(!m.loaded) $(m).loadContent();
		$(m).fadeIn();
	});
};


function loadDashboard() {
	c1 = $("#c1");
	c2 = $("#c2");
	c3 = $("#c3");

	$("#header_tabs li").each(function(){
		// During initialization, attach a "modules" array to each tab
		this.modules = [];
		$(this).click(HeaderTabClick);
	});

	$(".main_containers").sortable({
		connectWith: ['.main_containers'],
		handle: '.moduleHeader',
		revert: true,
		tolerance: 'pointer'
	});

	// Load module definitions for each tab
	loadLayout();

	$("#expd").click(showAll);
	$("#clps").click(hideAll);
	$(".action_refresh").mousedown(loadContent);
	$(".action_min").mousedown(minModule);
	$(".action_max").mousedown(maxModule);
	$(".action_close").mousedown(closeModule);
	//Make all external links open in new window
	$("a[href^=http]").attr("target","_blank");
	//Make all links with rel=new attribute to open in new window
	$("a[rel=new]").attr("target","_blank");

	t = location.hash;
	if ((t == '') || ($(t).size() != 1)) t = ":first";

	setTimeout("$('#header_tabs li"+t+"').click()", 200);
}

function loadPortlet(p) {
	var target = $(p).parentsUntil(".tabsdiv").parent();
	var form = $(p).parentsUntil("form").parent();
	target.load(eval("_modules."+target.attr("ref")+".l"), form.serializeObject(), prepModule);
}

// Container Actions, apply on modules
function showAll() {
	$(".moduleContent").show();
	$(".action_min").show();
	$(".action_max").hide();
	$("#expd").hide();
	$("#clps").show();
};

function hideAll() {
	$(".moduleContent").hide();
	$(".action_max").show();	
	$(".action_min").hide();	
	$("#expd").show();
	$("#clps").hide();
};

// Module Actions, apply on current module
function loadContent() {
	$(this).parents(".module").loadContent();
};

function minModule() {
	$(this).hide().parents(".moduleFrame").children(".moduleContent").hide();
	$(this).siblings(".action_max").show();
};

function maxModule() {
	$(this).hide().parents(".moduleFrame").children(".moduleContent").show();
	$(this).siblings(".action_min").show();
};

function closeModule() {
	var m = $(this).parents(".module");
	ct.modules.remove(m[0]);
	m.rm();
};


// ----------------------------------------------------
//    Extra Methods for More Dynamic Content Loading
// ----------------------------------------------------

// Used on A.local to change the content of current module
jQuery.fn.LocalLink = function() {
	return this.click(function() {
		$(this).parents(".module").loadContent(this.href);
		return false
	});
}

// Used on A.tab to switch to another Tab
jQuery.fn.TabLink = function() {
	return this.click(function() {
		$("#header_tabs li" + "#" + this.id).click();
		return false
	});
}

