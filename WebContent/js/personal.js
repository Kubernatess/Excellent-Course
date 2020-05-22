// JavaScript Document
window.onload = function(){
	// 添加点击下拉按钮事件
	let button = document.querySelector("button");
	button.onclick = function(){
		let form = document.querySelector('form');
		if(form.hidden==true){
			form.hidden = false;
			this.innerHTML="╱╲";
		}
		else{
			form.hidden = true;
			this.innerHTML="╲╱";
		}
	}
	
	// 表单验证
	let form = document.querySelector("form");
	form.onsubmit = function(){
		let currentPassword = document.getElementsByName('currentPassword')[0];		
		let recentPassword = document.getElementsByName('recentPassword')[0];	
		let confirmPassword = document.getElementsByName('confirmPassword')[0];
		let remotePassword = currentPassword.getAttribute("data-password");
		if(currentPassword.value!=remotePassword){
			alert("当前密码输入有误");
			this.reset();
			return false;
		}
		if(recentPassword.value!=confirmPassword.value){
			alert("密码前后不一致");
			this.reset();
			return false;
		}
		alert("修改密码成功,点击确认,立即重新登录");
	};
	
};