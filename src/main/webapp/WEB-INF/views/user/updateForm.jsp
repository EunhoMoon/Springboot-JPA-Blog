<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- 상대경로로 잡아야 찾을 수 있다. -->

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" value="${principal.user.username }" class="form-control" placeholder="Enter username" id="username" readonly="readonly">
		</div>
		<div class="form-group">
			<c:if test="${empty principal.user.oauth}">
				<label for="password">Password:</label>
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</c:if>
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" value="${principal.user.email }" class="form-control" placeholder="Enter email" id="email" readonly="readonly">
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">Submit</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


