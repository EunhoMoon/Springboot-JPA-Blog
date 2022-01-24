let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		// on() : 첫 번째 파라미터 = 이벤트, 두번째 파라미터 = 동작
	},

	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		// console.log(data);
		
		$.ajax().done().fail();	// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}

index.init();