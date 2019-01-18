<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>生产通知单信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cxj/cxjOrder/">生产通知单信息列表</a></li>
		<shiro:hasPermission name="cxj:cxjOrder:edit"><li><a href="${ctx}/cxj/cxjOrder/form">生产通知单信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cxjOrder" action="${ctx}/cxj/cxjOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderOn" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>客户：</label>
				<form:input path="customer" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>客户</th>
				<th>发货时间</th>
				<th>发货状态</th>
				<th>产品要求</th>
				<th>其他功能</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="cxj:cxjOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cxjOrder">
			<tr>
				<td><a href="${ctx}/cxj/cxjOrder/form?id=${cxjOrder.id}">
					${cxjOrder.orderOn}
				</a></td>
				<td>
					${cxjOrder.customer}
				</td>
				<td>
					<fmt:formatDate value="${cxjOrder.logisticsCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(cxjOrder.logisticsStatus, 'logistics_status', '')}
				</td>
				<td>
					${cxjOrder.requirement}
				</td>
				<td>
					${cxjOrder.otherFunctions}
				</td>
				<td>
					<fmt:formatDate value="${cxjOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cxjOrder.remarks}
				</td>
				<shiro:hasPermission name="cxj:cxjOrder:edit"><td>
    				<a href="${ctx}/cxj/cxjOrder/form?id=${cxjOrder.id}">修改</a>
					<a href="${ctx}/cxj/cxjOrder/delete?id=${cxjOrder.id}" onclick="return confirmx('确认要删除该生产通知单信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
        
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>