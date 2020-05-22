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
		firstInput.readOnly = true;
		// 编辑按钮事件
		edit.onclick = function(){
			let firstInput = document.querySelector("form input");
			let FIELDSET = document.querySelector("fieldset");
			let hidden = document.querySelector("input[type=\"hidden\"]");
			if(firstInput.readOnly==true){
				firstInput.readOnly = false;
				FIELDSET.disabled = true;
				hidden.name = "defaultID";
			}
			else{
				firstInput.readOnly = true;
				FIELDSET.disabled = false;
				hidden.removeAttribute("name");
			}
			return false;
		};
	}
	
	// 填充'专业'下拉列表
	let departList = this.document.getElementsByName("depart")[0];
	relateMajorList(departList.value);
	
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
		window.open("../course.jsp"+window.location.search);
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


//关联'专业'下拉列表
function relateMajorList(depart){
	let departAndMajor = new Array();
    departAndMajor["软件工程系"] = ["","软件工程","数据科学与大数据技术","电子与计算机工程"];
    departAndMajor["网络技术系"] = ["","网络工程","信息管理与信息系统"];
    departAndMajor["电子系"] = ["","智能科学与技术","电子信息工程","通信工程","自动化"];
    departAndMajor["游戏系"] = ["","数字媒体技术","动画","网络与新媒体"];
    departAndMajor["数码媒体系"] = ["","环境设计","风景园林","视觉传达设计","数字媒体艺术","产品设计"];
    departAndMajor["管理系"] = ["","行政管理","物流管理","工商管理","市场营销","人力资源管理"];
    departAndMajor["国际经贸系"] = ["","电子商务","国际经济与贸易"];
    departAndMajor["财会系"] = ["","财务管理","会计学"];
    departAndMajor["外语系"] = ["","英语","日语"];
    departAndMajor["计算机系"] = ["","计算机科学与技术","物联网工程"];
	let majorList = document.querySelector("select[name=\"major\"]");
	let defualtMajor = majorList.value;
	majorList.remove(0);
    let majors = departAndMajor[depart];
    for(let i=0; i<majors.length; i++){
        let major = new Option(majors[i],majors[i]);
		majorList.add(major);
		// 默认选中项
		if(majors[i]==defualtMajor){
			major.selected = true;
		}
    }
}