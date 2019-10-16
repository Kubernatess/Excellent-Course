window.onload=function(){
	// 设置iframe高度
	var iframe=document.getElementsByTagName('iframe');
	var bHeight=document.body.scrollHeight;
	var dHeight=document.documentElement.scrollHeight;
	iframe[0].height=iframe[1].height=Math.max(bHeight, dHeight);
	
	
	// 获取一些元素
	var nav=document.getElementsByTagName('nav')[0];
	var button=nav.getElementsByTagName('button')[0];
	var a=nav.getElementsByTagName('a');
	var menu=document.getElementsByTagName('div')[0].getElementsByTagName('div')[0];
	
	// 初始聚焦a[0]
	focusing(a[0]);
	
	
	// 加载页面的时候,进行一些初始化操作
	for(var i=0;i<a.length;i++){
		// 点击事件处理
		a[i].onclick=function(){
			focusing(this);
		};		
		// 右键弹出菜单
		a[i].oncontextmenu=function(){
			focusing(this);
			popMenu(this);
		};
		// 设置可编辑
		a[i].contentEditable=true;
	}
	
	

	// 设置删除栏目事件处理
	menu.onclick=function(){
		nav.removeChild(currentNode);
	};
	
	
	// 在栏目范围外,右键隐藏菜单
    document.onclick=function(){
		menu.style.display='none';
    }
	
	// 添加标题栏目方法
	button.onclick=function(){
		var node=document.createElement('a');
		var textnode=document.createTextNode("新增栏目");
		node.appendChild(textnode);
		// 默认是可编辑的
		node.contentEditable=true;
		nav.insertBefore(node,button);
		// 点击事件处理
		node.onclick=function(){
			focusing(this);
		};		
		// 右键弹出菜单
		node.oncontextmenu=function(){
			focusing(this);
			popMenu(this);
		};
			
	};
	
	function focusing(node){
		// 每次点击时把该节点指向当前节点
		currentNode=node;
		// 获取所有栏目节点
		var a=nav.getElementsByTagName('a');
		
		// 对其他栏目设置默认样式
		for(var i=0;i<a.length;i++){
			a[i].style.backgroundColor="#FFF";
			a[i].style.border="1px solid #797979";
			// 设置索引值
			a[i].index=i;
		};
		// 给当前栏目设置样式
		node.style.backgroundColor="#d2d3d561";
		
		// 如果不是第一个节点就显示第二个iframe
		if(currentNode.index!=0){
			iframe[1].style.display="block";
			iframe[0].style.display="none";
		}
		// 如果是第一个节点就显示第一个iframe
		else{
			iframe[0].style.display="block";
			iframe[1].style.display="none";	
		}
		
	};
	
	function popMenu(column) {
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




