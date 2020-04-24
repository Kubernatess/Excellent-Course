window.onload=function(){
	// 刚加载页面,聚焦第一个子栏目
	let firstHyperlink = document.querySelector("#subnavbar>a:first-child");
	let iframe = document.querySelector("body>iframe");
	iframe.src = ""+firstHyperlink.href;
	
	// 对所有标题栏目设置点击事件
	let hyperlinks = document.querySelectorAll("#subnavbar>a");
	for(let i=0; i<hyperlinks.length; i++){
		hyperlinks[i].onclick = function(){
			// 先取消所有样式
			let hyperlinks = document.querySelectorAll("#subnavbar>a");
			for(let i=0; i<hyperlinks.length; i++){
				hyperlinks[i].style.backgroundColor = "#FFF";
				hyperlinks[i].style.color = "#000";
			}
			// 再设置自身样式
			this.style.backgroundColor = "#07c160";
			this.style.color = "#FFF";
		};
	}
	
	// 点击搜索按钮跳转搜索页面
	let form = document.querySelector("#navbar>form");
	let inputSearch = form.children[0];
	let inputImage = form.children[1];
	inputImage.onclick = function(){
		window.open("search.jsp?key="+inputSearch.value,"_blank");
		return false;
	};
	
	// 自适应iframe高度
	iframe.onload = function(){
		let inlineIframe = this.contentWindow.document.querySelector("body>div>iframe");
		inlineIframe.onload = function(){
			this.height = this.contentWindow.document.documentElement.scrollHeight||this.contentWindow.document.body.scrollHeight;
			localStorage.setItem("inlineHeight", this.height);
		}
		this.height = localStorage.getItem("inlineHeight");
	};
};