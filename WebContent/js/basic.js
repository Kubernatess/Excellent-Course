// JavaScript Document
window.onload=function(){
	let courseID = getQueryVariable("courseID");
	let edit = document.querySelector("form>div>input[type=\"image\"]");
	// 如果URL地址栏上没有courseID参数,说明是第一次编辑课程
	if(courseID==""){
		edit.hidden = true;
	}
	// 如果URL地址栏上有courseID参数,说明是再次编辑课程
	else{
		let firstInput = document.querySelector("form input");
		firstInput.value = courseID;
		firstInput.readOnly = true;
		// 需要保留一下课程代码,以便需要修改课程代码操作使用
		let hidden = document.querySelector("input[type=\"hidden\"]");
		hidden.name = "originID";
		hidden.value = courseID;
		// 编辑按钮事件
		edit.onclick = function(){
			let firstInput = document.querySelector("form input");
			let FIELDSET = document.querySelector("fieldset");
			if(firstInput.readOnly==true){
				firstInput.readOnly = false;
				FIELDSET.disabled = true;
			}
			else{
				firstInput.readOnly = true;
				FIELDSET.disabled = false;
			}
			return false;
		};
	}
	
	
	// 添加教师事件
	let add = document.getElementById('add');
	add.onclick = function(){
		let parent = this.parentNode;
		let previous = this.previousElementSibling;
		let newInput = previous.cloneNode(true);
		parent.insertBefore(newInput,this);
		return false;
	};

	// 删除教师事件
	let reduce = document.getElementById('reduce');
	reduce.onclick = function(){
		let parent = this.parentNode;
		let input = parent.querySelectorAll("input[type=\"text\"]");
		let len = input.length;
		if(len>1){
			parent.removeChild(input[len-1]);
		}
		return false;
	};
	
	
	// 保存预览事件
	let hyperlinkPreview = document.getElementById('preview');
	hyperlinkPreview.onclick = function(){
		window.open("../course.html"+window.location.search);
	};
	
};


//获取URL参数值
function getQueryVariable(variable){
	let query = window.location.search.substring(1);
	let vars = query.split("&");
    for (let i=0;i<vars.length;i++) {
    	let pair = vars[i].split("=");
        if(pair[0] == variable)
        	return pair[1];
    }
    return "";
}