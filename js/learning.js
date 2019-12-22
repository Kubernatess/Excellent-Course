window.onload=function(){
	
	// 加载页面的时候,进行一些初始化操作
	var arrHyperlink=document.querySelectorAll("nav>a");	
	for(var i=0;i<arrHyperlink.length;i++){
		arrHyperlink[i].addEventListener("click",changeStyle);
	}
	
	// 刚加载页面,聚焦第一个子栏目
	var firstHyperlink=document.querySelector("body>div>nav>a:first-child");
	var iframe=document.querySelector("body>div>iframe");
	iframe.src=""+firstHyperlink.href;
};



/*点击标题栏目的事件处理*/
function changeStyle(){
	// 对其他栏目设置默认样式
	var arrHyperlink=document.querySelectorAll("nav>a");
	for(var i=0;i<arrHyperlink.length;i++){
		arrHyperlink[i].style.backgroundColor="#FFF";	
	};
	// 给当前栏目设置样式
	this.style.backgroundColor="#d2d3d561";
}