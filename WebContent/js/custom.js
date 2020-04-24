window.onload=function(){		
	/**
	 * 刚加载页面,进行初始化操作
	 *
	 */ 
	let iframe = document.querySelector("body>div>iframe");
	let p = document.querySelector("body>div>p");
	// 如果子栏目数目为0,则显示欢迎文本
	let INPUTS = document.querySelectorAll("body>form>a>input");
	if(INPUTS.length<=0){
		p.hidden = false;
		iframe.hidden = true;
	}
	// 否则显示iframe,聚焦第一个子栏目的内容
	else{
		iframe.hidden = false;
		p.hidden = true;
		let firstHyperlink = document.querySelector("body>form>a:first-child");
		firstHyperlink.click();
	}


	// 刚加载页面,进行初始化操作
	for(let i=0; i<INPUTS.length; i++){
		INPUTS[i].oncontextmenu = popmenu;
	}

	// 在栏目范围外,右键隐藏菜单
	document.onclick = function(){
		let menu = document.getElementsByTagName('ul')[0];	
		menu.hidden = true;
    }
	
	
	// 点击添加按钮事件处理
	let button = document.querySelector("body>form>button");
	button.onclick = addColumn;
};


// 右键弹出菜单
function popmenu(){
	//阻止默认行为
	var ev = ev||event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	let x = ev.clientX;
	let y = ev.clientY;		
	//右键点击时显示菜单框	
	let menu = document.getElementsByTagName('ul')[0];
	menu.hidden = false;
	menu.style.left = x+'px';
	menu.style.top = y+'px';
	// 绑定右键菜单功能
	let moveup = menu.children[0];
	let movedown = menu.children[1];
	let rename = menu.children[2];
	let remove = menu.children[3];
	// 绑定右键菜单的所有功能
	moveup.onclick = moveupColumn.bind(this);
	movedown.onclick = movedownColumn.bind(this);
	rename.onclick = renameColumn.bind(this);
	remove.onclick = removeColumn.bind(this);
	
	// 如果上一个元素为空,则隐藏上移功能
	let A = this.parentNode;
	let previous = A.previousElementSibling;
	moveup.hidden = previous==null?true:false;
	
	// 同理,如果下一个元素为空,则隐藏下移功能
	let next = A.nextElementSibling;
	movedown.hidden = next.tagName!="A"?true:false;
};


// 添加栏目方法
function addColumn(){
	// 获取最后一个元素的索引值
	let len = document.querySelectorAll("body>form>a>input").length;
	let lastInput = document.querySelectorAll("body>form>a>input")[len-1];
	let lastIndex = 0;
	if(lastInput!=null){
		lastIndex = lastInput.getAttribute("data-columnindex");
	}
	// 创建新节点
	let A = document.createElement('a');
	let INPUT = document.createElement('input');
	A.appendChild(INPUT);
	let button = this.form.querySelector("button");
	this.form.insertBefore(A,button);
	INPUT.type = "text";
	INPUT.value = "新增子栏目";
	INPUT.setAttribute("data-columnindex",parseInt(lastIndex)+1);
	// 自动执行重命名方法
	renameColumn.call(INPUT);
	// 设置隐藏域的值
	let operation = document.getElementsByName('operation')[0];
	operation.value="add";
}


// 删除栏目方法
function removeColumn(){
	// 设置隐藏域的值
	let columnIndex = document.getElementsByName('columnIndex')[0];	
	columnIndex.value = this.getAttribute("data-columnindex");
	let operation = document.getElementsByName('operation')[0];
	operation.value = "remove";
	// 提交表单
	this.form.submit();
}


// 重命名方法
function renameColumn(){
	// 禁用所有节点右键菜单事件和所有的超链接
	let hyperlinks = document.querySelectorAll("body>form>a");
	for(let i=0; i<hyperlinks.length; i++){
		hyperlinks[i].removeAttribute("href");
		hyperlinks[i].children[0].disabled = true;
		hyperlinks[i].children[0].oncontextmenu = function(){};
	}
	// 禁用添加按钮
	let button = document.querySelector("body>form>button");
	button.disabled = true;

	// 启用当前输入框
	this.readOnly = false;
	this.disabled = false;
	this.style.cursor = "auto";
	this.name = "columnName";
	this.focus();

	// 启用提交按钮
	let btnSubmit = document.querySelector("body>form>input[type=\"submit\"]");
	btnSubmit.disabled = false;
	
	// 设置隐藏域的值
	let columnIndex = document.getElementsByName('columnIndex')[0];
	columnIndex.value = this.getAttribute("data-columnindex");
	let operation = document.getElementsByName('operation')[0];
	operation.value = "rename";
}

// 栏目的上移
function moveupColumn(){
	// 设置隐藏域的值
	let columnIndex = document.getElementsByName('columnIndex')[0];	
	columnIndex.value = this.getAttribute("data-columnindex");
	// 获取上一个元素的索引值
	let previous = this.parentNode.previousElementSibling.children[0];
	let anotherIndex = document.getElementsByName('anotherIndex')[0];
	anotherIndex.value = previous.getAttribute("data-columnindex");
	let operation = document.getElementsByName('operation')[0];
	operation.value = "moveup";
	// 提交表单
	this.form.submit();
}

//栏目的下移
function movedownColumn(){
	// 设置隐藏域的值
	let columnIndex = document.getElementsByName('columnIndex')[0];	
	columnIndex.value = this.getAttribute("data-columnindex");
	// 获取上一个元素的索引值
	let next = this.parentNode.nextElementSibling.children[0];
	let anotherIndex = document.getElementsByName('anotherIndex')[0];
	anotherIndex.value = next.getAttribute("data-columnindex");
	let operation = document.getElementsByName('operation')[0];
	operation.value = "movedown";
	// 提交表单
	this.form.submit();
}