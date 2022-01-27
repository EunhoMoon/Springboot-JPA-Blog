<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- 상대경로로 잡아야 찾을 수 있다. -->

<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">목록으로</button>
	<c:if test="${board.user.id == principal.user.id}">
		<button id="btn-update-form" class="btn btn-warning" onclick="location.href='/board/${board.id}/updateForm'">수정</button>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /> <br />

	<div class="form-group">
		<h3>${board.title}</h3>
	</div>
	<div>
		글번호 : <span id="id"><i>${board.id} </i></span>&nbsp;&nbsp; 작성자 : <span><i>${board.user.username} </i></span>
	</div>
	<hr />
	<div class="form-group">
		<div>${board.content}</div>
	</div>
	<hr />

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


