/**
 * 在光标位置插入字符的小工具
 * 用法：$(selector).insertAtCaret("value");
 */
(function($){
	$.fn.extend({
		insertAtCaret: function(myValue){
			var $t=$(this)[0];
			if (document.selection) {
				this.focus();
				sel = document.selection.createRange();
				sel.text = myValue;
				this.focus();
			} else if ($t.selectionStart || $t.selectionStart == '0') {
				var startPos = $t.selectionStart;
				var endPos = $t.selectionEnd;
				var scrollTop = $t.scrollTop;
				$t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
				this.focus();
				$t.selectionStart = startPos + myValue.length;
				$t.selectionEnd = startPos + myValue.length;
				$t.scrollTop = scrollTop;
			} else {
				this.value += myValue;
				this.focus();
			}
		}
	})
})(jQuery);

$(function(){

$("#btnPlus").click(inputFormulaElem);
$("#btnSub").click(inputFormulaElem);
$("#btnMul").click(inputFormulaElem);
$("#btnDiv").click(inputFormulaElem);
$("#btnEDiv").click(inputFormulaElem);
$("#btnLPar").click(inputFormulaElem);
$("#btnRPar").click(inputFormulaElem);
$("#btnFixed").click(inputFormulaElem);
$("#btnWeight").click(inputFormulaElem);
$("#btnWeightPrice").click(inputFormulaElem);
$("#btnTotal").click(inputFormulaElem);
$("#btnFirstWeight").click(inputFormulaElem);
$("#btnFirstWeightPrice").click(inputFormulaElem);
$("#btnAddWeight").click(inputFormulaElem);
$("#btnAddWeightPrice").click(inputFormulaElem);
$("#btnParam1").click(inputFormulaElem);
$("#btnParam2").click(inputFormulaElem);
$("#btnParam3").click(inputFormulaElem);

$("#btnBackspace").click(function(){
	var target = $("#showingStage > .dnd_object:last-child");
	if(target != null && target.length > 0){
		removeFormulaSign(target[0]);
	}
});
$("#btnBackspace")[0].onselectstart=function(){return false;}

$("#btnClear").click(function(){
	$.each($("#showingStage > .dnd_object"), function(index, elem){
		dnd.delDragObject(elem);
	});
	$.each($("#showingStage > .dnd_object"), function(index, elem){
		elem.parentNode.removeChild(elem);
	});
	updateFormula();
});
$("#btnClear")[0].onselectstart=function(){return false;}

$("#btnSave").click(function(){

	$('#priceRuleFormulaForm').attr('action','priceRule_saveOrUpdateFormula.action?formula='+encodeURIComponent($("#ibFormula").attr('value')));
	//alert(encodeURIComponent($("#ibFormula").attr('value')));
	$("#priceRuleFormulaForm").submit();
});

$("#btnCalc").click(function(){
	var formula = $("#ibFormula").attr('value');

	if(   isNaN($("#ibFixed").attr('value'))) 
	{ 
	    alert($("#ibFixed").attr('value') + " is not a number");
	    $("#ibFixed").focus();		    
	    return;
	}
	if(   isNaN($("#ibWeight").attr('value'))) 
	{ 
	    alert($("#ibWeight").attr('value') + " is not a number");
	    $("#ibWeight").focus();
	    return;
	}
	if(   isNaN($("#ibWeightPrice").attr('value'))) 
	{ 
	    alert($("#ibWeightPrice").attr('value') + " is not a number");
	    $("#ibWeightPrice").focus();
	    return;
	}
	if(   isNaN($("#ibTotal").attr('value'))) 
	{ 
	    alert($("#ibTotal").attr('value') + " is not a number");
	    $("#ibTotal").focus();
	    return;
	}
	if(   isNaN($("#ibFirstWeight").attr('value'))) 
	{ 
	    alert($("#ibFirstWeight").attr('value') + " is not a number");
	    $("#ibFirstWeight").focus();
	    return;
	}
	if(   isNaN($("#ibFirstWeightPrice").attr('value'))) 
	{ 
	    alert($("#ibFirstWeightPrice").attr('value') + " is not a number");
	    $("#ibFirstWeightPrice").focus();
	    return;
	}
	if(   isNaN($("#ibAddWeight").attr('value'))) 
	{ 
	    alert($("#ibAddWeight").attr('value') + " is not a number");
	    $("#ibAddWeight").focus();
	    return;
	}
	if(   isNaN($("#ibAddWeightPrice").attr('value'))) 
	{ 
	    alert($("#ibAddWeightPrice").attr('value') + " is not a number");
	    $("#ibAddWeightPrice").focus();
	    return;
	}
	if(   isNaN($("#ibParam1").attr('value'))) 
	{ 
	    alert($("#ibParam1").attr('value') + " is not a number");
	    $("#ibParam1").focus();
	    return;
	}
	if(   isNaN($("#ibParam2").attr('value'))) 
	{ 
	    alert($("#ibParam2").attr('value') + " is not a number");
	    $("#ibParam2").focus();
	    return;
	}
	if(   isNaN($("#ibParam3").attr('value'))) 
	{ 
	    alert($("#ibParam3").attr('value') + " is not a number");
	    $("#ibParam3").focus();
	    return;
	}		

	formula = formula.replace(/\\/g, "%");

	$.post('priceRule_simulateCalculate.action',
		{
		formula			: formula,
		fixed			:$("#ibFixed").attr('value'),
		weight			:$("#ibWeight").attr('value'),
		weightPrice		:$("#ibWeightPrice").attr('value'),
		total			:$("#ibTotal").attr('value'),
		firstWeight 	:$("#ibFirstWeight").attr('value'),
		firstWeightPrice:$("#ibFirstWeightPrice").attr('value'),
		addWeight		:$("#ibAddWeight").attr('value'),
		addWeightPrice	:$("#ibAddWeightPrice").attr('value'),
		param1			:$("#ibParam1").attr('value'),
		param2			:$("#ibParam2").attr('value'),
		param3			:$("#ibParam3").attr('value')
		},
		function(data){
			$("#calcResult").html(data.message);
		}, 'json'
	);
});

$(function(){/* DnD */
	dnd.init('horizonal');
	dnd.addDragSource("group1", $("#btnWeight")[0]);
	dnd.addDragSource("group1", $("#btnWeightPrice")[0]);
	dnd.addDragSource("group1", $("#btnFirstWeight")[0]);
	dnd.addDragSource("group1", $("#btnFirstWeightPrice")[0]);
	dnd.addDragSource("group1", $("#btnAddWeight")[0]);
	dnd.addDragSource("group1", $("#btnAddWeightPrice")[0]);
	dnd.addDragSource("group1", $("#btnFixed")[0]);
	dnd.addDragSource("group1", $("#btnTotal")[0]);
	dnd.addDragSource("group1", $("#btnParam1")[0]);
	dnd.addDragSource("group1", $("#btnParam2")[0]);
	dnd.addDragSource("group1", $("#btnParam3")[0]);
	dnd.addDragSource("group1", $("#btnPlus")[0]);
	dnd.addDragSource("group1", $("#btnSub")[0]);
	dnd.addDragSource("group1", $("#btnMul")[0]);
	dnd.addDragSource("group1", $("#btnDiv")[0]);
	dnd.addDragSource("group1", $("#btnEDiv")[0]);
	dnd.addDragSource("group1", $("#btnLPar")[0]);
	dnd.addDragSource("group1", $("#btnRPar")[0]);

	dnd.addDndContainer("group1", $("#showingStage")[0]);
	dnd.addDndContainer("group1", $("#dropIt")[0]);

	dnd.cbDragChecker = function(){ return true; }
	dnd.cbOnDragEnd = function(o, c, s){
		if(s == 1 && c.node == $("#dropIt")[0]){
			dnd.selDropPosition = null;
		}
	}
	dnd.cbOnPostDrag = function(o, c, s){
		updateFormula();
	}
});

$(function(){
	var formula = $("#ibFormula").attr("value");
	if(formula != null && formula.length > 0){
		var signList = parseFormula2(formula);
		$.each(signList, function(index, sign){
			appendFormulaSign($("#btnContainer > .dnd_object[sign="+sign+"]")[0]);
		});
	}
});

});

function inputFormulaElem(e){
	var elemNode = e.target || e.srcElement;
	if(!elemNode || !elemNode.parentNode || elemNode.parentNode.id != 'btnContainer')
		return;
	appendFormulaSign(elemNode);
}

function appendFormulaSign(signNode){
	if(signNode == null) return;
	var copy = $(signNode).clone(false);
	dnd.addDragObject('group1', copy[0]);
	copy.appendTo("#showingStage");
	updateFormula();
}

function removeFormulaSign(signNode){
	if(!signNode)
		return;
	dnd.delDragObject(signNode);
	if(signNode.parentNode) signNode.parentNode.removeChild(signNode);
	updateFormula();
}

function updateFormula(){
	var formula = "";
	$.each($("#showingStage > .dnd_object"), function(index, element){
		if(formula.length > 0) formula += " ";
		formula += $(element).attr("sign");
	});
	$("#ibFormula").attr("value", formula);
}
/* 公式反解析方案1，以空格分隔 */
function parseFormula(formulaStr){
	if(!formulaStr) return null;
	return formulaStr.split(' ');
}

/* 公式反解析方案2，用正则式解析 */
function parseFormula2(formulaStr){
	var signList = [];
	var tmpStr = "";
	var state = 0;/* 0：开始，1: 变量，2：运算符，3：空格或结束 */
	var i = 0;
	while(i < formulaStr.length){
		var ch = formulaStr.charAt(i);
		var newState;
		if(/[\+\-\*\/\%\\\(\)\[\]\{\}\=]/.test(ch))/* 匹配： + - * / % 反斜杠 大中小括号 = */
			newState = 2;
		else if(/\s/.test(ch))/* 匹配空格 */
			newState = 3;
		else /* 其它均视为变量名称 */
			newState = 1;
		
		changeState(newState, ch);
		i++;
	}
	changeState(3, '\0');/* 切换至结束状态 */
	return signList;
	
	function changeState(newState, ch){
		if(state != newState){
			switch(state){
			case 1:
			case 2:
				signList.push(tmpStr);
				break;
			}
			tmpStr = "";
		} else if (state == 2) {
				signList.push(tmpStr);
				tmpStr = "";
		}
		tmpStr+=ch;
		state = newState;
	}
}
