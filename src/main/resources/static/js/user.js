let index = {
	init: function() {
		$("#btn-save").on("click", () => {	// functioni() {} 대신 => 를 쓰는 이유는 this를 바인딩하기 위해서이다.
			this.save();
		});
		// on() : 첫 번째 파라미터 = 이벤트, 두번째 파라미터 = 동작
		// $("#btn-login").on("click", () => {	
		// 	this.login();
		// });
		$("#btn-update").on("click", () => {	
			this.update();
		});
	},

	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// console.log(data);
		// ajax호출시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버거 json을 리턴해주면 자동으로 자바 오브젝트로 변환(버전에 따라?)
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json; charset=utf-8",	// body 데이터의 타입(MIME)
			dataType: "json"		// 요청에 대한 서버의 응답(String)의 형태가 json이라면 javascript Object로 변환
			// 회원가입 수행 요청
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다. ");
			console.log(resp);
			location.href = "/login";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

	},
	
	update: function() {
		let data = {
			id:$("#id").val(),
			username : $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	
			contentType: "application/json; charset=utf-8",	
			dataType: "json"	
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다. ");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});	

	},
	
/*
	login: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json; charset=utf-8",	// body 데이터의 타입(MIME)
			dataType: "json"	
		}).done(function(resp) {
			alert("로그인이 완료되었습니다. ");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	}
*/
}

index.init();