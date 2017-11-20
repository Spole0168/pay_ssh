/**
 * Drag and Drop
 *
 * @author Liu Haifeng
 * @created 2007-2-26
 * @modified 2010-10-19
 */

if(typeof dnd == "undefined") {

Number.prototype.NaN0 = function() { return isNaN(this) ? 0 : this; }

var dnd = {
	/* 数据定义 */
	isMouseDown: false,
	dragSources: [],
	dragObjects: [],
	curDragObject: null,
	dndContainers: [],
	orgDndContainer: null,
	curDndContainer: null,
	assistObjects: [],
	curAssistObject: null,
	orgDragPosition: { refNode: null, position: "" },
	selDropPosition: { refNode: null, position: "" },
	oldSelPos: { refNode: null, position: "" },
	selIndicatorNode: null,
	mouseOffset: null,
	topZIndex: 999,
	
	dndDirection: 'vertical',
	
	/* dndState. -1: drop failed, 0: no change, 1: drop to a new position */
	dndState: 0,
	
	/* 回调函数 */
	cbDragChecker: null,
	cbOnDragStart: null,
	cbOnDragEnd: null,
	cbOnPostDrag: null,
	
	/*******************************************************************************************
	 *
	 *  用户接口部分
	 *
	 *******************************************************************************************/
	
	init: function(dndDirection) {
		if(dndDirection != null)
			this.dndDirection = dndDirection;

		document.onmousedown = dnd.onMouseDown;
		document.onmousemove = dnd.onMouseMove;
		document.onmouseup = dnd.onMouseUp;
	},

	/* 添加拖拽源 */
	addDragSource: function(group, node, notifyStart, notifyEnd) {
		/* 添加拖拽源到数组 */
		if(group == null || node == null) {
			return false;
		}
		dnd.dragSources.push({
							  group: group,
							  node: node,
							  notifyStart: notifyStart,
							  notifyEnd: notifyEnd
							  });
		/* 绑定鼠标事件 */
		//node.onselectstart = function(){return false;}
		return true;
	},
	
	/* 删除拖拽源 */
	delDragSource: function(node) {
		var temp = [];
		for(var i = 0; i < dnd.dragSourcess.length; i++) {
			if(dnd.dragSourcess[i].node != node) {
				temp.push(dnd.dragSourcess[i]);
			}else{
				dnd.dragSourcess[i].node.onmousedown = null;
			}
		}
		dnd.dragSourcess = temp;
	},

	/* 添加拖拽对象 */
	addDragObject: function(group, node, notifyStart, notifyEnd) {
		/* 添加拖拽对象到数组 */
		if(group == null || node == null) {
			return false;
		}
		dnd.dragObjects.push({
							  group: group,
							  node: node,
							  notifyStart: notifyStart,
							  notifyEnd: notifyEnd,
							  enable: true
							  });
		/* 绑定鼠标移动事件 */
		document.onmousedown = dnd.onMouseDown;
		return true;
	},
	
	/* 删除拖拽对象 */
	delDragObject: function(node) {
		var temp = [];
		for(var i = 0; i < dnd.dragObjects.length; i++) {
			if(dnd.dragObjects[i].node != node) {
				temp.push(dnd.dragObjects[i]);
			}else{
				dnd.dragObjects[i].node.onmousedown = null;
			}
		}
		dnd.dragObjects = temp;
	},

	/* 添加拖拽容器 */
	addDndContainer: function(group, node, notifyStart, notifyEnd) {
		/* 添加对象到数组 */
		if(group == null || node == null) {
			return false;
		}
		dnd.dndContainers.push({
							   group: group,
							   node: node,
							   notifyStart: notifyStart,
							   notifyEnd: notifyEnd,
							   enable: true
							   });
		
		return true;
	},
	
	/* 删除拖拽容器 */
	delDndContainer: function(node) {
		var temp = [];
		for(var i = 0; i < dnd.dndContainers.length; i++) {
			if(dnd.dndContainers[i].node != node) {
				temp.push(dnd.dndContainers[i]);
			}
		}
		dnd.dndContainers = temp;
	},
		
	/* 添加辅助对象 */
	addAssistObject: function(group, node, notifyOverFun, notifyOutFun) {
		/* 添加对象到数组 */
		if(group == null || node == null) {
			return false;
		}
		dnd.assistObjects.push({
								group: group,
								node: node,
								notifyOverFun: notifyOverFun,
								notifyOutFun: notifyOutFun,
								enable: true,
								mouseOver: false
								});
		return true;
	},

	/* 删除辅助对象 */
	delAssistObject: function(node) {
		var temp = [];
		for(var i = 0; i < dnd.assistObjects.length; i++) {
			if(dnd.assistObjects[i].node != node) {
				temp.push(dnd.assistObjects[i]);
			}
		}
		dnd.assistObjects = temp;
	},

	/*******************************************************************************************
	 *
	 *  界面处理层
	 *
	 *******************************************************************************************/

	/* 鼠标按下 */
	onMouseDown: function(e) {
		e = e || window.event;
		var target = e.target || e.srcElement;

		/* 判断是否为有效拖拽对象 */
		for(var i = 0; i < dnd.dragObjects.length; i++) {
			if(target == dnd.dragObjects[i].node && dnd.dragObjects[i].enable) {
				/* 做按下标记 */
				dnd.isMouseDown = true;
				return false;
			}
		}
		for(var i = 0; i < dnd.dragSources.length; i++) {
			if(target == dnd.dragSources[i].node) {
				/* 做按下标记 */
				dnd.isMouseDown = true;
				return false;
			}
		}
		return true;
	},
	
	/* 鼠标移动 */
	onMouseMove: function(e) {
		if(!dnd.isMouseDown) {
			return true;
		}
		e = e || window.event;
		var target = e.target || e.srcElement;
		var mousePos = dnd._getMousePos(e);

		/* 开始拖拽 */
		if(dnd.curDragObject == null) {
			for(var i = 0; i < dnd.dragObjects.length; i++) {
				if(target == dnd.dragObjects[i].node) {
					dnd.onDragStart(dnd.dragObjects[i], e);
					break;
				}
			}
		}
		if(dnd.curDragObject == null) {
			for(var i = 0; i < dnd.dragSources.length; i++) {
				if(target == dnd.dragSources[i].node) {
					dnd.onDragStart(dnd.dragSources[i], e);
					break;
				}
			}
		}

		/* 选取释放位置，指示拖拽 */
		if(dnd.curDragObject != null) {
			/* 选择释放位置 */
			dnd.curDndContainer = dnd._getDndContainer(mousePos);
			dnd.onSelDropPosition(mousePos);
			dnd.showSelection(dnd.selDropPosition);

			/* 拖拽指示（鼠标跟随） */
			var pos = {
				x: mousePos.x - dnd.mouseOffset.x,
				y: mousePos.y - dnd.mouseOffset.y
			}
			dnd._placeNode(pos, dnd.curDragObject.node);
			if(dnd.dndState == -1) {
				dnd.curDragObject.node.style.cursor = "not-allowed";
			}else{
				dnd.curDragObject.node.style.cursor = "move";
			}
		}
		
		/* 通知辅助对像进行处理 */
		dnd.onSelAssistObject(mousePos);

		return false;
	},
	
	/* 鼠标松开 */
	onMouseUp: function(e) {
		e = e || window.event;
		dnd.isMouseDown = false;
		/* 结束拖拽 */
		if(dnd.curDragObject != null) {
			/* 发送通知 */
			dnd.notifyDropToDragObject(dnd.curDragObject,
											dnd.selDropPosition,
											dnd.curDndContainer ? dnd.curDndContainer : dnd.orgDndContainer,
											dnd.dndState);
			
			dnd.notifyDropToDndContainer(dnd.curDragObject,
											  dnd.selDropPosition,
											  dnd.curDndContainer ? dnd.curDndContainer : dnd.orgDndContainer,
											  dnd.dndState);
			
			if(dnd.cbOnDragEnd != null) {
				dnd.cbOnDragEnd(dnd.curDragObject,
									 dnd.curDndContainer ? dnd.curDndContainer : dnd.orgDndContainer,
									 dnd.dndState);
			}
			dnd.notifyDropToAssistObject();

			var stateCopy = dnd.dndState;

			/* drag end */
			dnd.onDragEnd(e);
			
			if(dnd.cbOnPostDrag != null){
				dnd.cbOnPostDrag(dnd.curDragObject,
									 dnd.curDndContainer ? dnd.curDndContainer : dnd.orgDndContainer,
									 stateCopy);
			}
		}
		return true;
	},

	/* 选择指示 */
	showSelection: function(selPos) {
		if(dnd.selIndicatorNode == null ||
		   dnd.curDragObject == null)
		{ return; /* But HOW can it be ?! */}
		//if(dnd._isSamePosition(selPos, dnd.oldSelPos)) { return; }
		var rc = dnd._getNodeRect(dnd.curDragObject.node);
		dnd.selIndicatorNode.style.width = rc.width + "px";
		dnd.selIndicatorNode.style.height = rc.height + "px";
		dnd._insertNode(dnd.selIndicatorNode, selPos);
		dnd.oldSelPos = selPos;
	},
	
	/*******************************************************************************************
	 *
	 *  逻辑控制层
	 *
	 *******************************************************************************************/
	
	/* 拖拽开始 */
	onDragStart: function(dragObject, e) {
		var mousePos = dnd._getMousePos(e);
		var originNode = dragObject.node;
		var isClone = false;
		for(var i = 0; i < dnd.dragSources.length; i++) {
			if(dnd.dragSources[i] == dragObject) {
				var clonedNode = dnd.dragSources[i].node.cloneNode(true);
				dnd.addDragObject(
					dnd.dragSources[i].group,
					clonedNode,
					dnd.dragSources[i].notifyStart,
					dnd.dragSources[i].notifyEnd
				);
				dragObject = dnd.dragObjects[dnd.dragObjects.length-1];
				break;
			}
		}
		/* 取出拖拽对像，判断是否有效 */
		for(var i = 0; i < dnd.dragObjects.length; i++) {
			if(dnd.dragObjects[i] == dragObject && dragObject.enable) {
				dnd.curDragObject = dragObject;
				break;
			}
		}
		if(dnd.curDragObject == null) { return; } /* Oh, what happend?! */
		
		/* 如果 selIndicatorNode 尚未创建，则创建并初始化它 */
		if(dnd.selIndicatorNode == null) {
			dnd.selIndicatorNode = document.createElement("div");
			dnd.selIndicatorNode.className = "dnd_sel-indicator-node";
			dnd.addAssistObject(dnd.curDragObject.group, dnd.selIndicatorNode);
		}
		
		/* 设置鼠标偏移 */
		dnd.mouseOffset = dnd._getOffset(mousePos, originNode);
		
		/* 保存拖拽对像的原始位置 */
		dnd.orgDragPosition = originNode == dragObject.node ? dnd._getLogicalPosition(dragObject.node) : null;
		dnd.selDropPosition = dnd.orgDragPosition;

		/* 初始化当前拖拽容器 */
		dnd.orgDndContainer = dnd._getDndContainer(mousePos);
		dnd.curDndContainer = dnd.orgDndContainer;

		/* 进入拖拽状态 */
		dnd.dndState = 0;
		dnd.showSelection(dnd.selDropPosition);

		dragObject.node.style.position = "absolute";
		dragObject.node.style.zIndex = dnd.topZIndex++;
		dragObject.node.style.opacity = "0.7";
		dragObject.node.style.filter = "alpha(opacity=70)";
		document.body.appendChild(dragObject.node);
		if(dnd.cbOnDragStart != null) {
			dnd.cbOnDragStart(dnd.curDragObject,
								   dnd.curDndContainer);
		}
		dnd.notifyDragToDragObject(dnd.curDragObject,
										dnd.orgDragPosition,
										dnd.curDndContainer);
		
		dnd.notifyDragToDndContainer(dnd.curDragObject,
										  dnd.orgDragPosition,
										  dnd.curDndContainer);
	},
	
	/* 选择目标位置 */
	onSelDropPosition: function(pos) {
		var dropPos = null;
		for(var i = 0; i < dnd.dragObjects.length; i++) {
			var rePos = dnd._getRelativePosition(pos, dnd.dragObjects[i].node);			
			if(rePos.isIn &&
			   dnd.curDragObject.group == dnd.dragObjects[i].group &&
			   dnd.curDragObject != dnd.dragObjects[i])
			{
				/* 找到正常目标 */
				dropPos = { refNode: dnd.dragObjects[i].node, position: "" };
				if(dnd.dndDirection == 'vertical') {
					switch(rePos.v) {
					case "top": /* 在上部 */
						dropPos.position = "before";
						break;
					case "bottom": /* 在下部 */
						dropPos.position = "after";
						break;
					}
				} else {
					switch(rePos.h) {
					case "left": /* 在左边 */
						dropPos.position = "before";
						break;
					case "right": /* 在右边 */
						dropPos.position = "after";
						break;
					}
				}
				break;
			}
		}
		if(dropPos == null) {
			/* 在辅助对象里找 */
			for(var i = 0; i < dnd.assistObjects.length; i++) {
				var rePos = dnd._getRelativePosition(pos, dnd.assistObjects[i].node);
				if(rePos.isIn &&
				   dnd.curDragObject.group == dnd.assistObjects[i].group &&
				   dnd.selIndicatorNode == dnd.assistObjects[i].node)
				{
					/* 找到辅助对象 */
					dropPos = dnd.selDropPosition;
				}
			}
		}
		if(dropPos == null) {
			/* 在空器里找 */
			if(dnd.curDndContainer != null &&
			   dnd.curDndContainer.group == dnd.curDragObject.group) {
				/* 找到容器 */
				dropPos = {
					refNode: dnd.curDndContainer.node,
					position: "append"
				};
			}
		}
		if(dropPos != null && dnd.isValidDropPosition(dnd.curDragObject, dnd.curDndContainer, dropPos)) {
			dnd.selDropPosition = dropPos;
			if(dnd._isSamePosition(dropPos, dnd.orgDragPosition)) {
				dnd.dndState = 0;
			}else{
				dnd.dndState = 1;
			}
		}else{
			dnd.selDropPosition = dnd.orgDragPosition;
			dnd.dndState = -1;
		}
	},
	
	/* 辅助对像对拖动的处理 */
	onSelAssistObject: function(pos) {
		/* 判断鼠标是否移入或移出一个辅助对像 */
		for(var i = 0; i < dnd.assistObjects.length; i++) {
			if(
			   !dnd.assistObjects[i].enable ||
					(dnd.assistObjects[i].notifyOverFun == null &&
					 dnd.assistObjects[i].notifyOutFun == null)
			   )
			{
				continue;
			}
			var rePos = dnd._getRelativePosition(pos, dnd.assistObjects[i].node);
			if(rePos.isIn &&
			   dnd.assistObjects[i].mouseOver == false &&
			   dnd.assistObjects[i].notifyOverFun != null)
			{
				/* 设置标记并发送通知 */
				dnd.assistObjects[i].mouseOver = true;
				dnd.assistObjects[i].notifyOverFun(dnd.assistObjects[i].node);
			}
			if(!rePos.isIn &&
			   dnd.assistObjects[i].mouseOver == true &&
			   dnd.assistObjects[i].notifyOutFun != null)
			{
				/* 设置标记并发送通知 */
				dnd.assistObjects[i].mouseOver = false;
				dnd.assistObjects[i].notifyOutFun(dnd.assistObjects[i].node);
			}
		}
	},
	
	/* 拖拽结束 */
	onDragEnd: function(e) {
		/* 移除选择指示 */
		if(dnd.selIndicatorNode.parentNode != null) {
			dnd.selIndicatorNode.parentNode.removeChild(dnd.selIndicatorNode);
		}
		dnd.curDragObject.node.style.position = "";
		dnd.curDragObject.node.style.opacity = "";
		dnd.curDragObject.node.style.filter = "";
		dnd._insertNode(dnd.curDragObject.node, dnd.selDropPosition);
		dnd.curDragObject.node.style.cursor = "move";
		
		dnd.curDragObject = null;
		dnd.selDropPosition = null;
		dnd.dndState = 0;
		dnd.oldSelPos = null;
	},
	
	/* 是否为有效目标 */
	isValidDropPosition: function(dragObject, dndContainer, dropPos) {
		return dnd.notifyCheckDropPosition(dragObject, dndContainer, dropPos);
	},
	
	/*******************************************************************************************
	 *
	 *  与外部交互
	 *
	 *******************************************************************************************/

	/* 向拖拽对象发送 drag 通知 */
	notifyDragToDragObject: function(dragObject, orgPos, dndContainer) {
		if(dragObject == null) { return; }
		if(dragObject.notifyStart != null) {
			dragObject.notifyStart(dragObject, orgPos, dndContainer);
		}
	},
	
	/* 向拖拽容器发送 drag 通知 */
	notifyDragToDndContainer: function(dragObject, dropPos, dndContainer) {
		if(dndContainer == null) { return; }
		if(dndContainer.notifyStart != null) {
			dndContainer.notifyStart(dragObject, dropPos, dndContainer);
		}
	},
	
	/* 向拖拽对象发送 drop 通知 */
	notifyDropToDragObject: function(dragObject, dropPos, dndContainer, dndState) {
		if(dragObject == null) { return; }
		if(dragObject.notifyEnd != null) {
			dragObject.notifyEnd(dragObject, dropPos, dndContainer, dndState);
		}
	},
	
	/* 向拖拽容器发送 drop 通知 */
	notifyDropToDndContainer: function(dragObject, dropPos, dndContainer, dndState) {
		if(dndContainer == null) { return; }
		if(dndContainer.notifyEnd != null) {
			dndContainer.notifyEnd(dragObject, dropPos, dndContainer, dndState);
		}
	},
	
	/* 询问外部程序是否允许拖动目标放置到指定位置 */
	notifyCheckDropPosition: function(dragObject, dndContainer, dropPos) {
		if(dnd.cbDragChecker != null) {
			return dnd.cbDragChecker(dragObject, dndContainer, dropPos);
		}else{
			return true;
		}
	},

	/* 通知辅助对象拖拽已结束 */
	notifyDropToAssistObject: function() {
		for(var i = 0; i < dnd.assistObjects.length; i++) {
			if(!dnd.assistObjects[i].enable)
			{
				continue;
			}
			if(dnd.assistObjects[i].mouseOver == true)
			{
				/* 设置标记并发送通知 */
				dnd.assistObjects[i].mouseOver = false;
				if(dnd.assistObjects[i].notifyOutFun != null) {
					dnd.assistObjects[i].notifyOutFun(dnd.assistObjects[i].node);
				}
			}
		}
	},

	/*******************************************************************************************
	 *
	 *  基础工具函数层
	 *
	 *******************************************************************************************/
	
	/* 绑定事件 */
	_connectEvent: function(obj, ev, fun) {
		if(obj != null && ev != null && fun != null) {
			if(obj.attachEvent) {
				obj.attachEvent("on" + ev, fun);
			}else if(obj.addEventListener) {
				obj.addEventListener(ev, fun, false);
			}else{
				obj["on" + ev] = fun;
			}
		}
	},
	
	/* 取鼠标坐标 */
	_getMousePos: function(e) {
		if(e.pageX || e.pageY) {
			return {
				x: e.pageX,
				y: e.pageY
			};
		}
		var o = document.documentElement;
		return {
			x: e.clientX + o.scrollLeft - o.clientLeft,
			y: e.clientY + o.scrollTop - o.clientTop
		};
	},
	
	/* 取指定坐标基于目标对象的偏移 */
	_getOffset: function(pos, node) {
		var elemRect = dnd._getNodeRect(node);
		return {
			x: pos.x - elemRect.left,
			y: pos.y - elemRect.top
		};
	},
	
	/* 取节点矩形 */
	_getNodeRect: function(node) {
		var left = 0;
		var top  = 0;
		var width = (parseInt(node.offsetWidth)).NaN0();
		var height = (parseInt(node.offsetHeight)).NaN0();
		while(typeof node.offsetParent == 'object' && node.offsetParent) {
			left += node.offsetLeft + (node.currentStyle?(parseInt(node.currentStyle.borderLeftWidth)).NaN0():0);
			top += node.offsetTop + (node.currentStyle?(parseInt(node.currentStyle.borderTopWidth)).NaN0():0);
			node = node.offsetParent;
		}
		left += node.offsetLeft + (node.currentStyle?(parseInt(node.currentStyle.borderLeftWidth)).NaN0():0);
		top += node.offsetTop + (node.currentStyle?(parseInt(node.currentStyle.borderTopWidth)).NaN0():0);

		return {
			left: left,
			top: top,
			width: width,
			height: height
		};
	},
	
	/* 移动节点到指定位置 */
	_placeNode: function(pos, node) {
		node.style.left = pos.x + "px";
		node.style.top = pos.y + "px";
	},
	
	/* 取下一节点 */
	_getNextNode: function(node) {
		if(!node.parentNode)
			return null;
		var nodes = node.parentNode.childNodes;
		var i;
		for(i = 0; i < nodes.length; i++) {
			if(nodes[i] === node) {
				break;
			}
		}
		for(i++; i < nodes.length; i++) {
			if(nodes[i].nodeType == 1) {
				return nodes[i];
			}
		}
		return null;
	},
	
	/* 插入节点到指定位置 */
	_insertNode: function(node, position) {
		if(node == null) return;
		if(position == null || position.refNode == null) {
			if(node.parentNode){
				node.parentNode.removeChild(node);
			}
			node == null;
			return;
		}
		switch(position.position) {
		case "before":
			position.refNode.parentNode.insertBefore(node, position.refNode);
			break;
		case "after":
			var refNode = dnd._getNextNode(position.refNode);
			if(refNode != null) {
				refNode.parentNode.insertBefore(node, refNode);
			}else{
				position.refNode.parentNode.appendChild(node);
			}
			break;
		case "append":
			position.refNode.appendChild(node);
			break;
		}
	},

	/* 判断两个位置是否为同一位置 */
	_isSamePosition: function(p1, p2) {
		if(p1 == p2) { return true; }
		if(p1 == null || p2 == null) { return false; }
		if(!(p1.refNode || p2.refNode)) { return false; }
		if(!(p1.refNode && p2.refNode)) { return false; }
		if(p1.refNode == p2.refNode && p1.position == p2.position) { return true; }
		if(p1.refNode != p2.refNode && p1.position == p2.position) { return false; }
		if(p1.refNode == p2.refNode && p1.position != p2.position) { return false; }

		var a, b, n;

		/* 父子关系判断 */
		if(p1.refNode.parentNode == p2.refNode ||
		   p2.refNode.parentNode == p1.refNode)
		{
			if(p1.refNode.parentNode == p2.refNode) { a = p2; b = p1; }
			else { a = p1; b = p2; }

			if(a.position != "append") { return false; }
			n = dnd._getNextNode(b.refNode);
			while(n == dnd.selIndicatorNode && n != null) { n = dnd._getNextNode(n); }
			if(n == null) { return true; }
			else { return false; }
		}
		
		/* 兄弟关系判断 */
		if(p1.position == "append" || p2.position == "append") { return false; }
		if(p1.position == "after") { a = p1; b = p2; }
		else { a = p2; b = p1; }
		n = dnd._getNextNode(a.refNode);
		while(n == dnd.selIndicatorNode && n != null) { n = dnd._getNextNode(n); }
		if(n == b.refNode) { return true; }
		else { return false; }
	},

	/* 取指定坐标相对指定节点的逻辑位置 */
	_getRelativePosition: function(pos, node) {
		var rc = dnd._getNodeRect(node);
		var isIn = false;
		var h = null;
		var v = null;
		if(pos.x >= rc.left && pos.x <= (rc.left + rc.width / 2)) { h = "left"; }
		if(pos.x >= (rc.left + rc.width / 2) && pos.x <= (rc.left + rc.width)) { h = "right"; }
		if(pos.y >= rc.top && pos.y <= (rc.top + rc.height / 2)) { v = "top"; }
		if(pos.y >= (rc.top + rc.height / 2) && pos.y <= (rc.top + rc.height)) { v = "bottom"; }
		if((h != null) && (v != null)) { isIn = true; }

		return {
			isIn: isIn,
			h: h,
			v: v
		};
	},

	/* 取节点逻辑位置 */
	_getLogicalPosition: function(node) {
		var refNode = dnd._getNextNode(node);
		var position = "before";
		if(refNode == null) {
			refNode = node.parentNode;
			position = "append";
		}
		return {
			refNode: refNode,
			position: position
		};
	},
	
	/* 根据位置取拖拽容器 */
	_getDndContainer: function(pos) {
		if(dnd.dndContainers) {
			for(var i = 0; i < dnd.dndContainers.length; i++) {
				var rePos = dnd._getRelativePosition(pos, dnd.dndContainers[i].node);
				if(rePos.isIn) {
					return dnd.dndContainers[i];
				}
			}
		}
		return null;
	}
};

}
