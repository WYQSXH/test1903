$(function() {
	$("#loginName").on("change", function() {
		var val = this.value;
		if(!isNumber(val) || val.length!=9){
			$("#loginName").val("");
			this.focus();
			alert('账号必须为9位数字');
		}
	});
	$("#email").on("change", function() {
		var val = this.value;
		if(!isEmail(val)){
			$("#email").val("");
			this.focus();
			alert('邮箱格式不对');
		}
	});
	$("form").on("submit", function(event) {
		var pwd1 = $("input[name='password']").val();
		var pwd2 = $("input[name='passwordagain']").val();
		if(pwd2 != pwd1){
			alert("两次密码不一致")
			event.preventDefault();
		}
	});
	function isNumber(value) {         //验证是否为数字
		var patrn = /^(-)?\d+(\.\d+)?$/;
		if (patrn.exec(value) == null || value == "") {
			return false
		} else {
			return true
		}
	}
	function isEmail(value) {         //验证是否为邮箱
		var patrn = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		if (patrn.exec(value) == null || value == "") {
			return false
		} else {
			return true
		}
	}
});
