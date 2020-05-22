window.onload = function(){
	// 默认显示第一页内容
	let tables = document.querySelectorAll("table");
	if(tables.length>10){
		for(let i=10; i<tables.length; i++){
			tables[i].hidden = true;
		}
	}
	
	// 创建分页卡
	let pages = Math.ceil(tables.length/10);
	let ul = document.getElementById("page");
	let next = ul.querySelector("li:last-child");
	for(let i=1; i<=pages; i++){
		let newLi = document.createElement("li");
		ul.insertBefore(newLi,next);
		newLi.innerHTML = i;
		if(i==1){
			newLi.setAttribute("class","active");
		}
	}
	
	// 点击上一页事件处理
	let previous = ul.querySelector("li:first-child");
	previous.onclick = function(){
		let activeNode = document.getElementsByClassName("active")[0];
		let previousNode = activeNode.previousElementSibling;
		if(previousNode.previousElementSibling!=null){
			activeNode.removeAttribute("class");
			previousNode.setAttribute("class","active");
			// 把当前这一页的内容先隐藏起来
			let currentPage = parseInt(activeNode.innerHTML);
			for(let i=currentPage*10-10; i<=currentPage*10; i++){
				if(i>=tables.length){
					break;
				}
				tables[i].hidden = true;
			}
			// 显示上一页的内容
			let previousPage = currentPage-1;
			for(let i=previousPage*10-10; i<=previousPage*10; i++){
				tables[i].hidden = false;
			}
		}
	};
	// 点击下一页事件处理
	next.onclick = function(){
		let activeNode = document.getElementsByClassName("active")[0];
		let nextNode = activeNode.nextElementSibling;
		if(nextNode.nextElementSibling!=null){
			activeNode.removeAttribute("class");
			nextNode.setAttribute("class","active");
			// 把当前这一页的内容先隐藏起来
			let currentPage = parseInt(activeNode.innerHTML);
			for(let i=currentPage*10-10; i<=currentPage*10; i++){
				tables[i].hidden = true;
			}
			// 显示上一页的内容
			let nextPage = currentPage+1;
			for(let i=nextPage*10-10; i<=nextPage*10; i++){
				if(i>=tables.length){
					break;
				}
				tables[i].hidden = false;
			}
		}
	};
	
	// 精彩推荐课程只显示前十门课程
	let list = document.querySelectorAll("aside>ol>li");
	for(let i=10; i<list.length; i++){
		list[i].hidden = true;
	}

}