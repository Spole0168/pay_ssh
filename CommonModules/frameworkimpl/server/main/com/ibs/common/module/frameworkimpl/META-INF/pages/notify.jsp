<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/common/taglibs.jsp"%>

<script	type="text/javascript">

var _ord_interval;

function doErrorMessage() {
	$("#notify-item-ord .notify-item-tip .notify-item-tip-body").html("网络超时！");
	$("#notify-item-ord .notify-item-tip").show();
	clearInterval(_ord_interval);
}

function getOrderNotifyMessage() {
	// order message
	$.ajax({
		type: "GET",
		url: "/ord/ordquery/orderNotifyMessage.action",
		//url: "http://localhost1:aaa/",
		dataType: "json",
		cache: false,
		success: function(result){
			if (result == null || typeof(result) == 'undefined') {
				doErrorMessage();
			}
			if (result.records == 0) {
				$("#notify-item-ord .notify-item-tip").hide();
				$("#notify-item-ord .notify-item-panel-body .notify-item-panel-list").html("");
			} else {
				$("#notify-item-ord .notify-item-tip").show();
				$("#notify-item-ord .notify-item-tip .notify-item-tip-body em").html(result.records);
				
				var msgHtml = "";
				for (i = 0; i < result.rows.length; ++i) {
					var row = result.rows[i];
					msgHtml += "<li><span class='notify-item-list-content'><a target='_blank' href='" + row.notifyHref + "'>有<em>" + row.notifyQty 
							+ "</em>条" + row.notifyContent + "</a></span><span class='notify-item-list-type'>" + row.notifyType + "</span></li>";
				}
				
				$("#notify-item-ord .notify-item-panel-body .notify-item-panel-list").html(msgHtml);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			doErrorMessage();
		}
	});
}

$(function(){
	// notify
	$(document).bind("click", function(e){
		$('#notify .notify-item-active').removeClass('notify-item-active');
	});
	
	$('#notify .notify-item-drop > a').bind('click', function(e){
		var _item = $(this).parent().parent();

		$('#notify .notify-item-active').not(_item).removeClass("notify-item-active");

		if (_item.is(".notify-item-active")) {
			_item.removeClass("notify-item-active");
		} else {
			_item.addClass("notify-item-active");
		}
	});
	
	$('#notify .notify-item').bind('click', function(e){
		e.stopPropagation();
	});
	
	setTimeout(getOrderNotifyMessage, 3000);	// first time execution
	_ord_interval = setInterval(getOrderNotifyMessage, 30000);
});
</script>
<!-- notify -->
<div id="notify">
	<div class="notify-toolbar">
		<span class="notify-item" id="notify-item-ord">
			<span class="notify-item-tip" style="display:none;">
				<span class="notify-item-tip-body">共<em>55</em>条</span>
				<span class="notify-item-tip-arrow"></span>
			</span>
			<span class="notify-item-drop">
				<a href="javascript:void(0)">订单消息</a>
				<i class="notify-item-drop-arrow"></i>
			</span>
			<div class="notify-item-panel" style="width: 350px; right: -1px;">
				<div class="notify-item-panel-head"><h2>订单消息</h2></div>
				<div class="notify-item-panel-body" style="height:360px">
					<ul class="notify-item-panel-list">
						<img src="/common/images/framework/ajax-loader4.gif" />加载中，请稍后...
					</ul>
					<div class="notify-item-panel-page">
						<a target="_blank" href="/ord/ordquery/totalOrderRemind.action" style="float:right; padding-right:30px;">查看全部</a>
					</div>
				</div>
			</div>
		</span>
		<%--
		<span class="notify-item">
			<span class="notify-item-tip" style="display:none;">
				<span class="notify-item-tip-body">共<em>0</em>条</span>
				<span class="notify-item-tip-arrow"></span>
			</span>
			<span class="notify-item-drop">
				<a href="javascript:void(0)">结算消息</a>
				<i class="notify-item-drop-arrow"></i>
			</span>
			<div class="notify-item-panel" style="width: 350px; right: -1px;">
				<div class="notify-item-panel-head"><h2>结算消息</h2></div>
				<div class="notify-item-panel-body" style="height:360px">
					正在建设中...
				</div>
			</div>
		</span>
		<span class="notify-item">
			<span class="notify-item-tip" style="display:none;">
				<span class="notify-item-tip-body">共<em>0</em>条</span>
				<span class="notify-item-tip-arrow"></span>
			</span>
			<span class="notify-item-drop">
				<a href="javascript:void(0)">其他消息</a>
				<i class="notify-item-drop-arrow"></i>
			</span>
			<div class="notify-item-panel" style="width: 350px; right: -1px;">
				<div class="notify-item-panel-head"><h2>其他消息</h2></div>
				<div class="notify-item-panel-body" style="height:360px">
					正在建设中...
				</div>
			</div>
		</span>
		--%>
		<span class="foot_top">
			<a id="backToTop" href="#">返回顶部↑</a>
		</span>
	</div>
</div>
