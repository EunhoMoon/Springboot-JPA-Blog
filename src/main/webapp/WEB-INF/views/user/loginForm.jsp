<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- 상대경로로 잡아야 찾을 수 있다. -->

<div class="container">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>


