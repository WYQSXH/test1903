$(function() {
	$("#loginName").on("change",function () {
		var str = this.value;
		if(!isNumber(str) || str.length!=9){
			$("#loginName").val("");
			this.focus();
			alert('账号必须为9位数字');
		}
	})
	function isNumber(value) {         //验证是否为数字
		var patrn = /^(-)?\d+(\.\d+)?$/;
		if (patrn.exec(value) == null || value == "") {
			return false
		} else {
			return true
		}
	}
})
