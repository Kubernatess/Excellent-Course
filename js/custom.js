window.onload=function(){
	var aside=document.getElementsByTagName('aside')[0];
	var button=aside.getElementsByTagName('button')[0];
	var ul=aside.getElementsByTagName('ul')[0];
	var menu=document.body.getElementsByTagName('ul')[0];
	var add=menu.getElementsByTagName('li')[0];
	var addchild=menu.getElementsByTagName('li')[1];
	var remove=menu.getElementsByTagName('li')[2];
	var moveup=menu.getElementsByTagName('li')[3];
	var movedown=menu.getElementsByTagName('li')[4];
	// 全局变量,时刻保存着当前节点
	var currentNode=null;
	
	// 初始化操作
	var li=ul.getElementsByTagName('li');
	for(var i=0;i<li.length;i++){
		// 默认是可编辑的
		li[i].contentEditable=true;
		// 添加右键事件
		li[i].oncontextmenu=function(){
			currentNode=this;
			popMenu();
		};
		// 鼠标点击事件
		li[i].onclick=function(){
			currentNode=this;
		};
	}
	
	
	// 点击添加按钮事件处理
	button.onclick=function(){
		var liNode=document.createElement('li');
		var spanNode=document.createElement('span');
		var signNode=document.createTextNode("▶");
		var textNode=document.createTextNode("新增子栏目");
		// 添加子栏目
		spanNode.appendChild(signNode);
		liNode.appendChild(spanNode);
		liNode.appendChild(textNode);
		ul.appendChild(liNode);	
		// 默认是可编辑的
		liNode.contentEditable=true;
		// 添加右键事件
		liNode.oncontextmenu=function(){
			currentNode=this;
			popMenu();
		};
		// 鼠标点击事件
		liNode.onclick=function(){
			currentNode=this;
		};
	
	};
	
	// 增加栏目方法
	add.onclick=function(){
		var liNode=document.createElement('li');
		var spanNode=document.createElement('span');
		var signNode=document.createTextNode("▶");
		var textNode=document.createTextNode("新增子栏目");
		// 添加子栏目
		spanNode.appendChild(signNode);
		liNode.appendChild(spanNode);
		liNode.appendChild(textNode);
		// 设置可编辑状态
		liNode.contentEditable=true;
		// 添加右键事件
		liNode.oncontextmenu=function(){
			currentNode=this;
			popMenu();
		};
		// 鼠标点击事件
		liNode.onclick=function(){
			currentNode=this;
		};
		// 获得父节点
		var parentNode=currentNode.parentNode;
		// 获得下一节点
		var nextNode=currentNode.nextSibling;
		parentNode.insertBefore(liNode,nextNode);	
	};
	
	// 添加子栏目
	addchild.onclick=function(){
		var ulNode=document.createElement('ul');
		var liNode=document.createElement('li');
		var spanNode=document.createElement('span');
		var signNode=document.createTextNode("▶");
		var textNode=document.createTextNode("新增子栏目");
		// 添加子节点
		spanNode.appendChild(signNode);
		liNode.appendChild(spanNode);
		liNode.appendChild(textNode);
		ulNode.appendChild(liNode);
		// 设置可编辑状态
		liNode.contentEditable=true;
		// 添加右键事件
		liNode.oncontextmenu=function(){
			currentNode=this;
			popMenu();
		};
		// 鼠标点击事件
		liNode.onclick=function(){
			currentNode=this;
		};
		// 获得父节点
		var parentNode=currentNode.parentNode;
		// 获得下一节点
		var nextNode=currentNode.nextSibling;
		parentNode.insertBefore(ulNode,nextNode);
	};
	
	//删除节点方法
	remove.onclick=function(){
		var parent=currentNode.parentNode;
		parent.removeChild(currentNode);
	};
	
	// 在栏目范围外,右键隐藏菜单
    document.onclick = function(){
		menu.style.display='none';
    }
	
	// 右键弹出菜单
	function popMenu(){		
		//阻止默认行为
		var ev = ev || event;
		ev.preventDefault();
		//记录当前的坐标(x轴和y轴)
		var x = ev.clientX;
		var y = ev.clientY;		
		menu.style.display = 'block';//右键点击时显示菜单框
		menu.style.left = x + 'px';
		menu.style.top = y + 'px';
	};

};