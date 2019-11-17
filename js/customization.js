window.onload=function(){
	
	// 加载页面的时候,进行一些初始化操作
	var form=document.getElementsByTagName('form')[0];
	var hyperlink=form.getElementsByTagName('a');	
		
		
	for(var i=0;i<hyperlink.length;i++){
		// 刚加载页面,禁用掉所有输入框
		hyperlink[i].children[0].disabled=true;
				
		hyperlink[i].addEventListener("click",clickHyperlink);
		
		// 跳过基本信息这一栏
		if(i!=0){
			hyperlink[i].addEventListener("contextmenu",popmenu);
		}
	}
	
	
	// 在栏目范围外,右键隐藏菜单
    document.onclick=function(){
		var menu=document.getElementsByTagName('ul')[0];
		menu.style.display='none';
    }
	
	
	// 添加标题栏目方法
	var button=form.getElementsByTagName('button')[0];	
	button.onclick=function(){
		var form=this.parentNode;
		var node=form.getElementsByTagName('a');
		var len=node.length;
		node=node[len-1];
		node=node.cloneNode(true);
		node.children[0].value="新建标题";
		node.children[0].name="tag";
		form.insertBefore(node,this);
		//自动执行重命名事件
		clickHyperlink.call(node)
		renameTag.call(node);
		// 设置隐藏域的值
		var tabIndex=document.getElementsByName('tabIndex')[0];
		tabIndex.value=len;
		// 修改iframe页面
		var iframe=document.getElementsByName('iframe')[0];
		iframe.src="custom.html";
	};
	
};



/*点击标题栏目的事件处理*/
function clickHyperlink(){
	// 对其他栏目设置默认样式
	var a=this.parentNode.getElementsByTagName('a');
	for(var i=0;i<a.length;i++){
		a[i].children[0].style.backgroundColor="#FFF";	
	};
	// 给当前栏目设置样式
	this.children[0].style.backgroundColor="#d2d3d561";
}


/*右键弹出菜单事件*/
function popmenu(){
	clickHyperlink.call(this);
	//阻止默认行为
	var ev = ev || event;
	ev.preventDefault();
	//记录当前的坐标(x轴和y轴)
	var x = ev.clientX;
	var y = ev.clientY;
	//右键点击时显示菜单框
	var menu=document.getElementsByTagName('ul')[0];
	menu.style.display = 'block';
	menu.style.left = x + 'px';
	menu.style.top = y + 'px';
	
	
	var rename=menu.children[0];
	var remove=menu.children[3];
	
	// 重命名事件
	rename.addEventListener("click",renameTag.bind(this));
	
	// 设置删除栏目事件处理
	remove.addEventListener("click",removeTag.bind(this));
}


// 点击重命名事件
function renameTag(){
	// 先禁用其他标题栏目所有点击事件和右键菜单事件
	var hyperlink=this.parentNode.getElementsByTagName('a');
	for(var i=0;i<hyperlink.length;i++){
		hyperlink[i].removeAttribute("href");
		hyperlink[i].removeEventListener("click",clickHyperlink);
		hyperlink[i].removeEventListener("contextmenu",popmenu);
	}
	// 禁用添加按钮
	var button=this.parentNode.getElementsByTagName('button')[0];
	button.disabled=true;	
	// 禁用input表单
	this.children[0].disabled=false;
}


// 点击删除事件
function removeTag(){
	var form=document.getElementsByTagName('form')[0];	
	form.removeChild(this);
	form.submit();
}