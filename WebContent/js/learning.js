window.onload = function(){	
	
	let hyperlinks = document.querySelectorAll("#subnavbar>a");
	if(hyperlinks.length==0){
		return;
	}
	// 如果没有tabIndex参数,则默认点击第一个超链接
	let tabIndex = getQueryVariable("tabIndex");
	if(tabIndex==""){
		hyperlinks[0].click();
		return;
	}	
	else{
		// 根据URL的tabIndex参数值显示对应的焦点样式
		let hyperlinkObject = document.querySelector("#subnavbar>a[data-tabindex=\""+tabIndex+"\"]");
		hyperlinkObject.setAttribute("class","tap");
	}

	
	hyperlinks = document.querySelectorAll("aside>a");
	if(hyperlinks.length==0){
		return;
	}
	// 如果没有columnIndex参数,则默认点击第一个超链接
	let columnIndex = getQueryVariable("columnIndex");
	if(columnIndex==""){
		hyperlinks[0].click();
		return;
	}	
	else{
		// 根据URL的columnIndex参数值显示对应的焦点样式
		let hyperlinkObject = document.querySelector("aside>a[data-columnindex=\""+columnIndex+"\"]");
		hyperlinkObject.setAttribute("class","taptap");
	}
	
	
	
	// 给所有的单选按钮和复选框加上form属性
	let radios = document.querySelectorAll("input[type=\"radio\"]");
	let checkboxs = document.querySelectorAll("input[type=\"checkbox\"]");
	for(let i=0; i<radios.length; i++){
		radios[i].setAttribute("form","objective");
		radios[i].removeAttribute("checked");
	}
	for(let i=0; i<checkboxs.length; i++){
		checkboxs[i].setAttribute("form","objective");
		checkboxs[i].removeAttribute("checked");
	}
	
	
	let videos = document.querySelector("video");
	let btnSubmit = document.querySelector("main input[type=\"submit\"]");
	// 如果没有视频任务和表单任务,可以直接打绿灯
	if(videos==null&&btnSubmit==null){
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","../Excellent-Course/CompletionServlet"+window.location.search,true);
		xmlhttp.send(null);
	}

	
	// content内容里如果包含了video元素,一定要把所有视频看完才能打绿灯
	if(videos!=null){ 
		videos.onended = function(){
			let xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET","../Excellent-Course/CompletionServlet"+window.location.search,true);
			xmlhttp.send(null);
		};
	}

	
	if(btnSubmit!=null){
		// 如果有提交按钮,然后判断类型是主观题还是客观题
		if(radios.length>0||checkboxs.length>0){
			btnSubmit.setAttribute("form","objective");
		}
		else{
			btnSubmit.setAttribute("form","subjective");
		}
		// 给这些提交按钮加点击事件
		btnSubmit.onclick = function(){
			let r = confirm("确定提交答案?");
	        if (r==false){
		        return false;
            }
		};
		// 不管是提交作业按钮还是提交答案按钮,只要提交成功,就打绿灯
		let form = btnSubmit.form;
		form.onsubmit = function(){
			let xmlhttp = new XMLHttpRequest();
			xmlhttp.open("GET","../Excellent-Course/CompletionServlet"+window.location.search,true);
			xmlhttp.send(null);
		}
	}
	
	
	let list = document.querySelectorAll("#replyForm li");
	for(let i=0; i<list.length; i++){
		// 提取作者名
		let authorName = list[i].querySelector("address").innerHTML;
		let index = authorName.indexOf(": ");
		authorName = authorName.substring(index+1);
		// 填充placeholder属性值
		let textareaObject = list[i].querySelector("textarea");
		textareaObject.placeholder = "回复"+authorName+":";
		// 点击回复按钮
		let button = list[i].querySelector("button[type=\"button\"]");
		button.onclick = function() {
			// 先把前面的评论区域隐藏掉
			let fieldsets = document.querySelectorAll("#replyForm fieldset");
			for(let j=0; j<fieldsets.length; j++){
				fieldsets[j].style.display = "none";
			}
			// 再把当前的评论区显示出来
			let li = this.parentNode;
			let curretnFieldset = li.querySelector("fieldset");
			curretnFieldset.style.display = "block";
		};
		// 点击提交按钮事件处理
		let btnSubmit = list[i].querySelector("input[type=\"submit\"]");
		btnSubmit.onclick = function(){
			let liObject = this.parentNode.parentNode;
			let textareaObject = liObject.querySelector("textarea");			
			textareaObject.name = "comment";
			let opposite = document.getElementsByName("opposite")[0];
			let addressObject = liObject.querySelector("address");
			opposite.value = addressObject.getAttribute("data-identity");
			let lunchTime = document.getElementsByName("lunchTime")[0];
			let timeObject = liObject.querySelector("time");
			lunchTime.value = timeObject.dateTime;
		};
		// 点击删除按钮
		let btnRemove = list[i].querySelector("button[type=\"submit\"]");
		if(btnRemove!=null){
			btnRemove.onclick = function(){
				// 确认是否删除
		        let r=confirm("确定删除此评论?");
		        if (r==false){
			        return false;
				}
				let dateTime = document.getElementsByName("dateTime")[0];
				let timeObject = this.nextElementSibling;
				dateTime.value = timeObject.dateTime;
			};
		}
	}
	
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