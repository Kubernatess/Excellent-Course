// JavaScript Document
window.onload=function(){
	
	// 添加点击下拉按钮事件
	var a=document.getElementsByTagName('a');
	for(var i=0;i<a.length;i++){
		a[i].onclick=function(){
			// 初始状态值
			var status=this.innerHTML;
			var forms=document.getElementsByTagName('form');
			var a=document.getElementsByTagName('a');
			// 先隐藏两个表单和两个下拉按钮
			for(var i=0;i<a.length;i++){
				forms[i].hidden = true;
				a[i].innerHTML="╲╱";
			}
			// 最后显示自己的表单和下拉按钮
			var form=this.parentNode.nextElementSibling;
			if(status=="╲╱"){
				form.hidden = false;
				this.innerHTML="╱╲";
			}
			else{
				form.hidden = true;
				this.innerHTML="╲╱";
			}
		}
	}
	
	
	var btnPassword=document.querySelectorAll("form>input[type=\"button\"]")[0];
	btnPassword.onclick=function(){
		var currentPassword=document.getElementsByName('currentPassword')[0];		
		var recentPassword=document.getElementsByName('recentPassword')[0];	
		var confirmPassword=document.getElementsByName('confirmPassword')[0];	
		let form=document.getElementsByTagName('form')[0];
		if(currentPassword.value.length==0){
			alert("当前密码为空!");
			form.reset();
			return;
		}
		if(recentPassword.value.length==0){
			alert("新的密码为空!");
			form.reset();
			return;
		}
		if(confirmPassword.value.length==0){
			alert("确认密码为空");
			form.reset();
			return;
		}
		var xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				if(eval(xmlhttp.responseText)==0){
					alert("当前密码输入有误")
					let form=document.getElementsByTagName('form')[0];
					form.reset();
					return;
				}
				
				var recentPassword=document.getElementsByName('recentPassword')[0];	
				var confirmPassword=document.getElementsByName('confirmPassword')[0];	
				if(recentPassword.value!=confirmPassword.value){
					alert("密码前后不一致");
					form.reset();
					return;
				}
				
				form.submit();
			}
		}
		xmlhttp.open("POST","../Excellent-Course/ConfirmPassword",true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("currentPassword="+currentPassword.value+"");
	};
	
};