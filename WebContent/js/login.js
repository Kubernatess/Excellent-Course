// JavaScript Document
window.onload=function(){
	let userIcon = document.getElementById("icon");
	let common = document.getElementById("common");
	let member = document.getElementById("member");
	// 点击普通用户时
	common.onclick=function(){
		this.style.borderBottom = "2px solid #33CC33";
		member.style.borderBottom = "none";		
		userIcon.src="image/user-empty-grey.png";
		let status = document.getElementsByName('status')[0];
		status.value="student";
		let text = document.getElementsByName('identity')[0];
		text.placeholder="请输入学号";
		let btnSubmit = document.querySelector("input[type=\"submit\"]");
		btnSubmit.value="登陆";
		btnSubmit.form.reset();		
	};
	// 点击会员登陆时
	member.onclick=function(){
		this.style.borderBottom = "2px solid #33CC33";
		common.style.borderBottom = "none";		
		userIcon.src="image/user.png";
		let status = document.getElementsByName('status')[0];
		status.value="teacher";
		let text = document.getElementsByName('identity')[0];
		text.placeholder="请输入教师工号";
		let btnSubmit = document.querySelector("input[type=\"submit\"]");
		btnSubmit.value="会员登陆";
		btnSubmit.form.reset();
	};
};