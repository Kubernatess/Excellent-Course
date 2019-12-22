window.onload=function(){		
	// 刚加载页面,进行初始化操作
	var arrLi=document.querySelectorAll("body>aside ul>li");
	for(var i=0;i<arrLi.length;i++){
		// 对切换图标添加切换事件
		var span=arrLi[i].getElementsByTagName('span')[0];
		if(span!=null){
			span.addEventListener("click",ontoggle);
		}
	}
	
	// 刚加载页面,聚焦第一个子栏目
	var firstHyperlink=document.querySelector("body>aside>ul>li:first-child>ul>li:first-child>a");
	var iframe=document.querySelector("body>div>iframe");
	iframe.src=""+firstHyperlink.href;

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