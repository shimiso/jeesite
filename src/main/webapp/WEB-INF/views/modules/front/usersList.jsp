<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>前端用户管理管理</title>
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
		<li class="active"><a href="${ctx}/front/users/">前端用户管理列表</a></li>
		<shiro:hasPermission name="front:users:edit"><li><a href="${ctx}/front/users/form">前端用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="users" action="${ctx}/front/users/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>username：</label>
				<form:input path="username" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>age：</label>
				<form:input path="age" htmlEscape="false" maxlength="3" class="input-medium"/>
			</li>
			<li><label>email：</label>
				<form:input path="email" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>mobile：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>address：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>username</th>
				<th>age</th>
				<th>email</th>
				<th>mobile</th>
				<th>address</th>
				<shiro:hasPermission name="front:users:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="users">
			<tr>
				<td><a href="${ctx}/front/users/form?id=${users.id}">
					${users.username}
				</a></td>
				<td>
					${users.age}
				</td>
				<td>
					${users.email}
				</td>
				<td>
					${users.mobile}
				</td>
				<td>
					${users.address}
				</td>
				<shiro:hasPermission name="front:users:edit"><td>
    				<a href="${ctx}/front/users/form?id=${users.id}">修改</a>
					<a href="${ctx}/front/users/delete?id=${users.id}" onclick="return confirmx('确认要删除该前端用户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>