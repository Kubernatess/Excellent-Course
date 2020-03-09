window.onload=function(){
	
	// 加载页面的时候,进行一些初始化操作
	var arrHyperlink=document.querySelectorAll("form>a");	
	for(var i=0;i<arrHyperlink.length;i++){
		// 刚加载页面,禁用掉所有输入框
		arrHyperlink[i].children[0].disabled=true;
				
		arrHyperlink[i].addEventListener("click",changeStyle);
		
		// 跳过基本信息这一栏
		if(i!=0){
			arrHyperlink[i].addEventListener("contextmenu",popmenu);
		}
	}
	
	
	// 在栏目范围外,右键隐藏菜单
    document.onclick=function(){
		var menu=document.getElementsByTagName('ul')[0];
		menu.style.display='none';
    }
	
	
	// 添加标题栏目方法
	var button=document.querySelector("form>button");	
	button.onclick=function(){
		var form=this.parentNode;
		var allTab=form.getElementsByTagName("a");
		var lastTab=allTab[allTab.length-1]
		var newTab=lastTab.cloneNode(true);
		var input=newTab.children[0];
		input.value="新建标题";
		input.name="tabName";
		form.insertBefore(newTab,this);
		//自动执行重命名事件
		changeStyle.call(newTab);
		renameTag.call(newTab);
		// 设置隐藏域的值
		var tabIndex=document.getElementsByName('tabIndex')[0];
		tabIndex.value=lastTab.children[0].tabIndex+1;
		// 修改iframe页面
		var iframe=document.getElementsByName('iframeA')[0];
		iframe.src="custom.jsp";
		// 设置隐藏域的值
		var operation=document.getElementsByName('operation')[0];
		operation.value="add";
	};
	
};



/*点击标题栏目的事件处理*/
function changeStyle(){
	// 对其他栏目设置默认样式
	var allTab=document.querySelectorAll("form>a");
	for(var i=0;i<allTab.length;i++){
		allTab[i].children[0].style.backgroundColor="#FFF";	
	};
	// 给当前栏目设置样式
	this.children[0].style.backgroundColor="#d2d3d561";
}


/*右键弹出菜单事件*/
function popmenu(){
	changeStyle.call(this);
	//阻止默认行为
	var ev = ev || event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	var x = ev.clientX;
	var y = ev.clientY;
	//右键点击时显示菜单框
	var menu=document.getElementsByTagName('ul')[0];
	menu.style.display='block';
	menu.style.left=x+'px';
	menu.style.top=y+'px';
	
	
	var rename=menu.children[0];
	var leftShift=menu.children[1];
	var rightShift=menu.children[2];
	var remove=menu.children[3];
	
	rename.addEventListener("click",renameTag.bind(this));
	leftShift.addEventListener("click",moveTag.bind(this,"leftshift"));
	rightShift.addEventListener("click",moveTag.bind(this,"rightshift"));
	remove.addEventListener("click",removeTag.bind(this));
	
	
	if(this.previousElementSibling.children[0].tabIndex==0){
		leftShift.style.display="none";
	}
	else{
		leftShift.style.display="block";
	}
	
	if(this.nextElementSibling.tagName!='A'){
		rightShift.style.display="none";
	}
	else{
		rightShift.style.display="block";
	}
}


// 点击重命名事件
function renameTag(){
	// 先禁用其他标题栏目所有点击事件和右键菜单事件
	var allTab=this.parentNode.getElementsByTagName('a');
	for(var i=0;i<allTab.length;i++){
		allTab[i].removeAttribute("href");
		allTab[i].removeEventListener("click",changeStyle);
		allTab[i].removeEventListener("contextmenu",popmenu);
	}
	// 禁用添加按钮
	var button=this.parentNode.getElementsByTagName('button')[0];
	button.disabled=true;	
	// 启用input表单
	this.children[0].disabled=false;
	// 设置隐藏域的值
	var operation=document.getElementsByName('operation')[0];
	operation.value="rename";
	
	var tabIndex=document.getElementsByName('tabIndex')[0];	
	tabIndex.value=this.children[0].tabIndex;
}


// 点击删除事件
function removeTag(){
	var form=document.getElementsByTagName('form')[0];	
	form.removeChild(this);
	form.submit();
}


// 标题栏目的左移和右移
function moveTag(move){
	
	var tabIndex=document.getElementsByName('tabIndex')[0];	
	tabIndex.value=this.children[0].tabIndex;
	
	var operation=document.getElementsByName('operation')[0];
	operation.value=move;

	// 提交表单
	var form=this.parentNode;
	form.submit();
}