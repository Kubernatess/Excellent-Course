window.onload=function(){		
	// 刚加载页面,聚焦第一个子栏目
	var firstHyperlink=document.querySelector("body>aside>ul>li:first-child>a");
	var iframe=document.querySelector("body>div>iframe");
	iframe.src=""+firstHyperlink.href;
};