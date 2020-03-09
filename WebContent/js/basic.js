// JavaScript Document
window.onload=function(){
	// 添加教师事件
	var add=document.getElementById('add');
	add.onclick=function(){
		var parent=this.parentNode;
		var input=document.createElement("input");
		input.name="teacherName";
		input.required=true;
		parent.insertBefore(input,this);
	};

	// 删除教师事件
	var reduce=document.getElementById('reduce');
	reduce.onclick=function(){
		var parent=this.parentNode;
		let input=parent.getElementsByTagName('input');
		let len=input.length-1;
		if(len>=0){
			parent.removeChild(input[len]);
		}
	};
	
	
	// 保存预览事件
	var hyperlinkPreview=document.getElementById('preview');
	hyperlinkPreview.onclick=function(){
		var form=document.getElementsByTagName('form')[0];
		form.submit();
	};
	
};