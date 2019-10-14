// JavaScript Document
window.onload=function(){
	// 获取选项卡
	var arr=document.getElementsByTagName('a');
	// 获取用户图标
	var userIcon=document.getElementsByTagName('img')[1];
	// 获取隐藏域
	var hidden=document.getElementsByName('hidden')[0];
	// 获取文本输入框
	var text=document.getElementsByName('identity')[0];
	// 提交按钮
	var btnSubmit=document.getElementsByName('login')[0];
	// 点击普通用户时
	arr[0].onclick=function(){
		arr[0].style.borderBottom="2px solid #33CC33";
		arr[1].style.borderBottom="none";
		btnSubmit.form.reset();
		userIcon.src="image/user-empty-grey.png";
		hidden.value="student";
		text.placeholder="请输入学号";
		btnSubmit.value="登陆";
	};
	// 点击会员登陆时
	arr[1].onclick=function(){
		arr[1].style.borderBottom="2px solid #33CC33";
		arr[0].style.borderBottom="none";
		btnSubmit.form.reset();
		userIcon.src="image/user.png";
		hidden.value="teacher";
		text.placeholder="请输入教师工号";
		btnSubmit.value="会员登陆";
	};
};