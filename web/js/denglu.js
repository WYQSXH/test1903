// JavaScript Document
function name1()
	{   
    var fr= document.denglu.name.value;	
	if(fr.length>6)
		 {
		 alert("用户名不能超过6位");
		 return false;
		 }
		 return true;
	}
function password1()
	{   
    var fr= document.denglu.password.value;	
	if(fr.length>16)
		 {
		 alert("密码不能超过16位");
		 return false;
		 }
		 return true;
	}
function cheekAll()
	{	
		if(document.getElementById("name").value.length == 0){
				alert("用户名不能为空！（提示：用户名为 admin）");
				document.denglu.username.focus();
				return false;
				}
  		if(document.getElementById("name").value.length != 0){
			if(document.getElementById("name").value != "admin"){
				alert("用户名错误，请重新输入！（提示：用户名为 admin）")
				document.denglu.username.focus();
		  		return false; 
				}
			}
		if(document.getElementById("password").value.length == 0){
			alert("密码不能为空！（提示：密码为 1234567）");
			document.denglu.password.focus();
			return false;
			}
		if(document.getElementById("password").value.length != 0){
			if(document.getElementById("password").value != "1234567"){
				alert("密码错误，请重新输入！（提示：密码为 1234567）")
				document.denglu.password.focus();
				return false;
				}
			}	
		
		return true;
	}
