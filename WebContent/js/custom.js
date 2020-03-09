window.onload=function(){		
	/**
	 * 刚加载页面,进行初始化操作
	 *
	 */ 
	
	// 给全部栏目添加右键事件
	let arrInput=document.querySelectorAll("body>form>ul>li>a>input");
	for(let i=0;i<arrInput.length;i++){
		arrInput[i].oncontextmenu=popMenu;
		arrInput[i].readOnly=true;
	}

	// 在栏目范围外,右键隐藏菜单
	let menu=document.querySelector("body>ul");	
	document.onclick=function(){
		menu.style.display='none';
    }
	
	
	let allColumn=document.querySelectorAll("body>form>ul>li");
	let iframe=document.querySelector("body>div>iframe");
	let p=document.querySelector("body>div>p");
	// 如果子栏目数目为0,则显示欢迎文本
	if(allColumn.length<=0){
		p.style.display="block";
		iframe.style.display="none";
	}
	// 否则,聚焦第一个子栏目的内容
	else{
		iframe.style.display="block";
		p.style.display="none";
		let firstHyperlink=document.querySelector("body>form>ul>li:first-child>a");
		iframe.src=firstHyperlink.href;
	}
	
	
	// 每次只能点击一次添加按钮
	let button=document.querySelector("body>form>button");
	button.onclick=function(){
		this.disabled=true;
		addColumn();
	};
};


// 右键弹出菜单
function popMenu(){
	//阻止默认行为
	var ev=ev||event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	let x=ev.clientX;
	let y=ev.clientY;		
	let menu=document.querySelector("body>ul");
	menu.style.display ='block';//右键点击时显示菜单框
	menu.style.left=x+'px';
	menu.style.top=y+'px';
	// 获得右键菜单按钮
	let moveup=menu.children[0];
	let movedown=menu.children[1];
	let rename=menu.children[2];
	let remove=menu.children[3];
	// 绑定右键菜单的所有功能
	moveup.onclick=moveupColumn.bind(this);
	movedown.onclick=movedownColumn.bind(this);
	rename.onclick=renameColumn.bind(this);
	remove.onclick=removeColumn.bind(this);
	
	// 如果上一个元素为空,则隐藏上移功能
	let li=this.parentNode.parentNode;
	let previous=li.previousElementSibling;
	if(previous==null){
		moveup.style.display="none";
	}
	else{
		moveup.style.display="block";
	}
	
	// 同理,如果下一个元素为空,则隐藏下移功能
	let next=li.nextElementSibling;
	if(next==null){
		movedown.style.display="none";
	}
	else{
		movedown.style.display="block";
	}
};


// 添加栏目方法
function addColumn(){
	// 创建新节点
	let column=document.createElement('li');
	let hyperlink=document.createElement('a');
	let input=document.createElement('input');
	hyperlink.href="edit.jsp";
	hyperlink.target="iframeB";
	input.type="text";
	input.value="新增栏目";
	input.name="columnName";
	hyperlink.appendChild(input);
	column.appendChild(hyperlink);
	
	let ul=document.querySelector("body>form>ul");
	ul.appendChild(column);	
	
	// 设置隐藏域的值
	let columnIndex=document.getElementsByName('columnIndex')[0];
	let previous=column.previousElementSibling;
	if(previous==null){
		columnIndex.value=1
	}
	else{
		columnIndex.value=previous.getElementsByTagName("input")[0].tabIndex+1;
	}
	input.tabIndex=columnIndex.value;
	
	// 自动执行重命名方法
	renameColumn.call(input);
	
	// 切换iframe页面
	let iframe=document.querySelector("body>div>iframe");
	iframe.src="edit.jsp";
	
	// 设置隐藏域的值
	let operation=document.getElementsByName('operation')[0];
	operation.value="add";
}


// 删除栏目方法
function removeColumn(){
	// 设置隐藏域的值
	let columnIndex=document.getElementsByName('columnIndex')[0];	
	columnIndex.value=this.tabIndex;
	// 设置隐藏域的值
	let operation=document.getElementsByName('operation')[0];
	operation.value="removeColumn";
	// 提交表单
	let form=document.querySelector("body>form");
	form.submit();
}


// 重命名方法
function renameColumn(){
	// 禁用所有节点右键菜单事件
	let arrInput=document.querySelectorAll("body>form ul li input");
	for(let i=0;i<arrInput.length;i++){
		arrInput[i].disabled=true;
	}
	// 禁用开关按钮
	let arrSpan=document.querySelectorAll("body>form>ul>li>span");
	for(let i=0;i<arrSpan.length;i++){
		arrSpan[i].removeEventListener("click",ontoggle);
	}
	// 禁用所有超链接
	let arrHyperlink=document.querySelectorAll("body>form>ul>li>ul>li>a");
	for(let i=0;i<arrHyperlink.length;i++){
		arrHyperlink[i].removeAttribute("href");
	}
	// 启用当前输入框
	this.disabled=false;
	this.readOnly=false;
	this.style.cursor="auto";
	this.focus();	
	// 禁用添加按钮
	let button=document.querySelector("body>form>button");
	button.disabled=true;	
	// 设置隐藏域的值
	let operation=document.getElementsByName('operation')[0];
	operation.value="renameColumn";
	
	let columnIndex=document.getElementsByName('columnIndex')[0];	
	columnIndex.value=this.tabIndex;
}

// 栏目的上移
function moveupColumn(){
	let columnIndex=document.getElementsByName('columnIndex')[0];	
	let subcolumnIndex=document.getElementsByName('subcolumnIndex')[0];
	let operation=document.getElementsByName('operation')[0];
	operation.value="moveUpColumn";
	columnIndex.value=this.tabIndex;
	// 提交表单
	let form=document.querySelector("body>form");
	form.submit();
}


//栏目的下移
function movedownColumn(){
	let columnIndex=document.getElementsByName('columnIndex')[0];	
	let subcolumnIndex=document.getElementsByName('subcolumnIndex')[0];
	let operation=document.getElementsByName('operation')[0];
	operation.value="moveDownColumn";
	columnIndex.value=this.tabIndex;
	// 提交表单
	let form=document.querySelector("body>form");
	form.submit();
}