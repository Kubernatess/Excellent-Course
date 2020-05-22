window.onload = function(){
	
	// 自适应iframe高度
	let mainIframe = document.getElementsByName('mainIframe')[0];
	mainIframe.onload = mainIframe.onmouseover = function(){
		this.height = this.contentWindow.document.documentElement.scrollHeight||this.contentWindow.document.body.scrollHeight;		
	};
	
	
	// 加载页面的时候,进行一些初始化操作
	let INPUTS = document.querySelectorAll("form>a>input");
	for(let i=0; i<INPUTS.length; i++){
		// 跳过基本信息这一栏,基本信息这一栏不用设置右键事件
		if(i!=0){
			INPUTS[i].oncontextmenu = popmenu;
		}
		// 但是点击样式是每个标题栏目都需要设置的
		INPUTS[i].onclick = function(){
			// 取消前面已有的样式
			let tap = document.getElementsByClassName("tap")[0];
			tap.removeAttribute("class");
			// 再设置自身样式
			this.setAttribute("class","tap");
		}
	}
	
	
	// 在栏目范围外,右键隐藏菜单
	document.onclick = function(){
		let menu = document.querySelector("ul");	
		menu.hidden = true;
    }
	
	
	// 根据用户是否传递courseID参数来判断是否启用添加按钮
	let courseID = getQueryVariable("courseID");
	let button = document.querySelector("form>button");
	if(courseID==""){
		button.disabled = true;
	}
	else{
		button.onclick = addTab;
	}
};


/*右键弹出菜单事件*/
function popmenu(){
	//阻止默认行为
	var ev = ev || event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	let x = ev.clientX;
	let y = ev.clientY;
	//右键点击时显示菜单框
	let menu = document.querySelector("ul");
	menu.hidden = false;
	menu.style.left = x+'px';
	menu.style.top = y+'px';
	
	let rename = menu.children[0];
	let remove = menu.children[3];
	rename.onclick = renameTab.bind(this);
	remove.onclick = removeTab.bind(this);
	
	let currentTap = document.getElementsByClassName("tap")[0];
	let mainIframe = document.getElementsByName('mainIframe')[0];
	let editForm = mainIframe.contentWindow.document.querySelector("#editForm");
	// 如果是当前点击的这个标题栏目,并且内联框架没有显示出来,则可以删除功能
	remove.hidden = (currentTap.isSameNode(this) && editForm.hidden==true)?false:true;
}


//添加一个标题栏目
function addTab() {
	let form = this.form;
	// 获取可用的tabId
	let str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	let INPUTS = form.querySelectorAll("a>input");
	for(let i=1; i <INPUTS.length; i++){
		let ch = INPUTS[i].getAttribute("data-tabIndex");
		if(str.includes(ch)){
			str = str.replace(ch,"");
		}
	}
	// 创建新节点
	let A = document.createElement('a');
	let button = form.querySelector("button");
	this.form.insertBefore(A,button);
	A.innerHTML = "<input type=\"text\" value=\"新增栏目\" data-tabindex=\""+str.charAt(0)+"\">";
	//自动执行重命名事件
	renameTab.call(A.children[0]);
}


//点击重命名事件
function renameTab(){
	// 先禁用其他标题栏目所有点击事件和右键菜单事件
	let form = this.form;
	let hyperlinks = form.querySelectorAll("a");
	for(let i=0; i<hyperlinks.length; i++){
		hyperlinks[i].removeAttribute("href");
		hyperlinks[i].children[0].disabled = true;
		hyperlinks[i].children[0].oncontextmenu = function(){};
	}
	// 禁用添加按钮
	let button = form.querySelector("button");
	button.disabled = true;
	// 启用当前输入框
	this.disabled = false;
	this.readOnly = false;
	this.style.cursor = "auto";
	this.name = "tabName";
	this.focus();
	// 启用提交按钮
	let btnSubmit = form.querySelector("input[type=\"submit\"]");
	btnSubmit.disabled = false;
	// 设置隐藏域的值
	let tabIndex = form.querySelector("input[name=\"tabIndex\"]");
	tabIndex.value = this.getAttribute("data-tabindex");
}

//点击删除事件
function removeTab(){
	let tabIndex = document.getElementsByName('tabIndex')[0];
	tabIndex.value = this.getAttribute("data-tabindex");
	this.form.submit();
}


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