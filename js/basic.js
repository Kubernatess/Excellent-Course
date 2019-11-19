// JavaScript Document
window.onload=function(){
	// 初始化显示哪个专业组
	var depart=document.getElementById('depart');
	var optgroup=document.getElementById('major').getElementsByTagName('optgroup')[depart.selectedIndex];
	optgroup.style.display="block";
	
	
	// 关联两个下拉列表事件	
	depart.onchange=function(){
		var major=document.getElementById('major');
		var optgroup=major.getElementsByTagName('optgroup');
		// 先把其他组隐藏
		for(var i=0;i<optgroup.length;i++){
			optgroup[i].style.display="none";
		}
		// 再显示当前组
		optgroup[this.selectedIndex].style.display="block";
		optgroup[this.selectedIndex].children[0].selected=true;
	};
	
	
	// 添加教师事件
	var add=document.getElementById('teach');
	add.onclick=function(){
		var parent=this.parentNode;
		var input=document.createElement("input");
		input.name="teacherName";
		input.required=true;
		parent.insertBefore(input,this);
	};
	
	
	// 选择模板事件
	var template=document.getElementById('template');
	template.onclick=function(){
		var outer=document.getElementById('outer');
		outer.style.display="block";
	};
	
	
	// 关闭模态框事件
	var Close=document.getElementById('close');
	var Cancel=document.getElementById('cancel');
	Close.onclick=Cancel.onclick=function(){
		var outer=document.getElementById('outer');
		outer.style.display="none";
	};
	
	
	// 选择模板时的一些样式
	var model=document.getElementById('model');
	var ul=model.getElementsByTagName('ul')[0];
	var list=ul.children;
	for(var i=0;i<list.length;i++){
		list[i].onclick=function(){
			// 对其他模板清除样式
			var list=this.parentNode.children;
			for(var i=0;i<list.length;i++){
				list[i].style.border="none";
			}
			this.style.border="3px solid #3fff2e9e";
		};
	}
	
	
	// 模态框点击确认前的一些操作
	var Confirm=document.getElementById('confirm');
	Confirm.onclick=function(){
		var outer=document.getElementById('outer');
		var list=document.getElementById('model').getElementsByTagName('ul')[0].children;
		var template=document.getElementsByName('template')[0];
		for(var i=0;i<list.length;i++){
			if(list[i].style.border!="none"){
				var templateName=list[i].getElementsByTagName('span')[0].innerHTML;
				template.value=templateName;
			}
		}
		// 最后关闭模态框
		outer.style.display="none";
	};
};