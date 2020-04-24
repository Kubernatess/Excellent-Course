window.onload = function(){
	// 点击搜索按钮跳转搜索页面
	let form = document.querySelector(".container>form");
	let inputSearch = form.children[0];
	let inputImage = form.children[1];
	inputImage.onclick = function(){
		window.open("search.jsp?key="+inputSearch.value,"_blank");
	};
	
	// 统计搜索结果数
	let span = document.querySelector(".container>span");
	let contents = document.querySelectorAll(".container>.content");
	let hidden = document.querySelector(".container>input[type=\"hidden\"]");
	if(hidden!=null){
		span.innerHTML = "共"+contents.length+"条<span>"+hidden.value+"</span>相关的结果";
	}
};