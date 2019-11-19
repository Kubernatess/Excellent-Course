window.onload=function(){		
	// 刚加载页面,进行初始化操作
	var arrLi=document.querySelectorAll("body>form ul>li");
	for(var i=0;i<arrLi.length;i++){
		// 对切换图标添加切换事件
		var span=arrLi[i].getElementsByTagName('span')[0];
		if(span!=null){
			span.addEventListener("click",ontoggle);
		}
		
		// 给全部栏目添加右键事件
		var input=arrLi[i].getElementsByTagName('input')[0];
		input.addEventListener("contextmenu",popmenu.bind(arrLi[i]));
		// 禁用所有输入表单
		input.readOnly=true;
	}


	// 在栏目范围外,右键隐藏菜单
	var menu=document.getElementsByTagName('ul')[0];	
    document.onclick=function(){
		menu.style.display='none';
    }
	
	
	// 点击添加按钮事件处理
	var button=document.querySelector("body>form>button");
	button.onclick=function(){
		// 每次只能点击一次添加按钮
		this.disabled=true;
		
		addColumn();
	};
};


// 右键弹出菜单
function popmenu(){		
	//阻止默认行为
	var ev=ev||event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	var x=ev.clientX;
	var y=ev.clientY;		
	var menu=document.getElementsByTagName('ul')[0];
	menu.style.display ='block';//右键点击时显示菜单框
	menu.style.left=x+'px';
	menu.style.top=y+'px';
	
	// 绑定右键菜单功能
	var addchild=menu.children[0];
	var remove=menu.children[1];
	var rename=menu.children[2];
	var moveup=menu.children[3];
	var movedown=menu.children[4];
	// 绑定右键菜单的所有功能
	addchild.addEventListener("click",addChildColumn.bind(this));
	remove.addEventListener("click",removeColumn.bind(this));
	rename.addEventListener("click",renameColumn.bind(this));
	moveup.addEventListener("click",moveColumn.bind(this,"moveup"));
	movedown.addEventListener("click",moveColumn.bind(this,"movedown"));
	// 如果是子栏目,则需要隐藏右键菜单中的添加子栏目项
	if(this.getElementsByTagName('input')[0].name=='subcolumnName'){
		addchild.style.display="none";
	}
	else{
		addchild.style.display="block";
		// 如果子栏目数不为零,则隐藏删除功能
		if(this.getElementsByTagName('ul')[0].getElementsByTagName('li').length!=0){
			remove.style.display="none";
		}
	}
	
	// 如果上一个元素为空,则隐藏上移功能
	var previous=this.previousElementSibling;
	if(previous==null){
		moveup.style.display="none";
	}
	else{
		moveup.style.display="block";
	}
	// 同理,如果下一个元素为空,则隐藏下移功能
	var next=this.nextElementSibling;
	if(next==null){
		movedown.style.display="none";
	}
	else{
		movedown.style.display="block";
	}
};


// 三角形开关事件
function ontoggle(){
	var li=this.parentNode;
	var ul=li.getElementsByTagName('ul')[0];
	// 显示它的子节点
	if(this.innerHTML=="▶"){
		this.innerHTML="▼";
		ul.style.display="block";
	}
	// 隐藏它的子节点
	else{
		this.innerHTML="▶";
		ul.style.display="none";
	}
}


// 添加栏目方法
function addColumn(){
	// 创建新节点
	var newLi=document.createElement('li');
	var span=document.createElement('span');
	var sign=document.createTextNode("▼");
	var input=document.createElement('input');
	var newUL=document.createElement('ul');
	var newSubLi=document.createElement('li');
	var a=document.createElement('a');
	var SubInput=document.createElement('input');
	input.type="text";
	input.value="新增栏目";
	input.name="columnName";
	SubInput.type="text";
	SubInput.value="新增子栏目";
	newUL.style.display="block";
	span.appendChild(sign);
	a.appendChild(SubInput);
	newSubLi.appendChild(a);
	newUL.appendChild(newSubLi);
	newLi.appendChild(span);
	newLi.appendChild(input);
	newLi.appendChild(newUL);
	
	var ul=document.querySelector("form>ul");
	ul.appendChild(newLi);	
	
	// 设置隐藏域的值
	var columnIndex=document.getElementsByName('columnIndex')[0];
	var previous=newLi.previousElementSibling;
	columnIndex.value=previous.getElementsByTagName("input")[0].tabIndex+1;
	
	// 自动执行重命名方法
	renameColumn.call(newLi);
	
	// 切换iframe页面
	var iframe=document.querySelector("body>div>iframe");
	iframe.src="edit.jsp";
	
	// 设置隐藏域的值
	var operation=document.getElementsByName('operation')[0];
	operation.value="add";

}


// 添加子栏目方法
function addChildColumn(){
	// 创建子节点
	var newLi=document.createElement('li');
	var a=document.createElement('a');
	var input=document.createElement('input');
	input.type="text";
	input.value="新增子栏目";
	input.name="subcolumnName";
	a.appendChild(input);
	newLi.appendChild(a);
	
	// 切换iframe页面
	var iframe=document.querySelector("body>div>iframe");
	iframe.src="edit.jsp";
	
	var span=this.getElementsByTagName('span')[0];
	span.innerHTML="▼";
	var ul=this.getElementsByTagName('ul')[0];
	ul.style.display="block";
	ul.appendChild(newLi);
	// 设置隐藏域的值
	var columnIndex=document.getElementsByName('columnIndex')[0];	
	var subcolumnIndex=document.getElementsByName('subcolumnIndex')[0];
	columnIndex.value=this.getElementsByTagName("input")[0].tabIndex;
	var previous=newLi.previousElementSibling;
	subcolumnIndex.value=previous.getElementsByTagName("input")[0].tabIndex+1;
	
	// 自动执行重命名方法
	renameColumn.call(newLi);
	
	// 设置隐藏域的值
	var operation=document.getElementsByName('operation')[0];
	operation.value="add";

}


// 删除栏目方法
function removeColumn(){
	var ul=this.parentNode;
	ul.removeChild(this);
}


// 重命名方法
function renameColumn(){
	// 禁用所有节点右键菜单事件
	var arrInput=document.querySelectorAll("body>form ul li input");
	for(var i=0;i<arrInput.length;i++){
		arrInput[i].disabled=true;
	}
	// 禁用开关按钮
	var arrSpan=document.querySelectorAll("body>form>ul>li>span");
	for(var i=0;i<arrSpan.length;i++){
		arrSpan[i].removeEventListener("click",ontoggle);
	}
	// 禁用所有超链接
	var arrHyperlink=document.querySelectorAll("body>form>ul>li>ul>li>a");
	for(var i=0;i<arrHyperlink.length;i++){
		arrHyperlink[i].removeAttribute("href");
	}
	// 启用当前输入框
	var input=this.getElementsByTagName('input')[0];
	input.disabled=false;
	input.readOnly=false;
	input.style.cursor="auto";
	input.focus();	
	// 禁用添加按钮
	var button=document.querySelector("body>form>button");
	button.disabled=true;	
	// 设置隐藏域的值
	var operation=document.getElementsByName('operation')[0];
	operation.value="rename";
	
	
	var columnIndex=document.getElementsByName('columnIndex')[0];	
	var subcolumnIndex=document.getElementsByName('subcolumnIndex')[0];
	if(this.getElementsByTagName('input')[0].name=='columnName'){
		columnIndex.value=this.getElementsByTagName('input')[0].tabIndex;
	}
	else{
		columnIndex.value=this.parentNode.parentNode.getElementsByTagName('input')[0].tabIndex;
		subcolumnIndex.value=this.getElementsByTagName('input')[0].tabIndex;
	}
}


// 栏目的上移和下移
function moveColumn(move){
	
	var columnIndex=document.getElementsByName('columnIndex')[0];	
	var subcolumnIndex=document.getElementsByName('subcolumnIndex')[0];
	var operation=document.getElementsByName('operation')[0];
	operation.value=move;
	
	if(this.getElementsByTagName('input')[0].name=='columnName'){
		columnIndex.value=this.getElementsByTagName('input')[0].tabIndex;
	}
	else{
		columnIndex.value=this.parentNode.parentNode.getElementsByTagName('input')[0].tabIndex;
		subcolumnIndex.value=this.getElementsByTagName('input')[0].tabIndex;
	}
	
	// 提交表单
	var form=document.querySelector("body>form");
	form.submit();
	
}