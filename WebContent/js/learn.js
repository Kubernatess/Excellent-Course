window.onload=function(){		
	// 刚加载页面,聚焦第一个子栏目
	let firstHyperlink=document.querySelector("body>aside>a:first-child");
	if(firstHyperlink!=null){
		let iframe=document.querySelector("body>div>iframe");
		iframe.src=""+firstHyperlink.href;
	}
};