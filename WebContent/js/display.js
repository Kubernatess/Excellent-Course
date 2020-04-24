window.onload = function(){
	// 禁用所有多媒体缩放
	let iframes = document.getElementsByTagName("iframe");
	for(let i=0; i<iframes.length; i++){
		iframes[i].style.resize = "none";
	}
	// 移除所有的滑块
	let sliders = document.querySelectorAll("input[type=\"range\"]");
	for(let i=0; i<sliders.length; i++){
		let parentnode = sliders[i].parentNode;
		parentnode.removeChild(sliders[i]);
	}
	
	// 给所有的单选按钮和复选框加上form属性
	let radios = document.querySelectorAll("input[type=\"radio\"]");
	let checkboxs = document.querySelectorAll("input[type=\"checkbox\"]");
	for(let i=0; i<radios.length; i++){
		radios[i].setAttribute("form","testing");
	}
	// 至少要有一个默认答案,以访用户漏选
	radios[0].checked = true;
	for(let i=0; i<checkboxs.length; i++){
		checkboxs[i].setAttribute("form","testing");
	}
	// 至少要有一个默认答案,以访用户漏选
	checkboxs[0].checked = true;
	checkboxs[1].checked = true;
	
	
	let isFinishedVideo = false;
	let isFinishedSubmit = false;
	
	let videos = document.getElementsByTagName("video");
	let btnSubmit = document.querySelector("input[type=\"submit\"]");
	if(videos.length==0){
		isFinishedVideo = true;
	}
	if(btnSubmit==null){
		isFinishedSubmit = true;
	}
	
	// 如果完成视频任务和表单任务,就可以打绿灯
	if(isFinishedVideo==true&&isFinishedSubmit==true){
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","../Excellent-Course/RecordServlet"+window.location.search,true);
		xmlhttp.send(null);
	}
	
	// content内容里如果包含了video元素,一定要把视频看完才能打绿灯
	if(videos.length>0){ 
		let count = 0;
		for(let i=0; i<videos.length; i++){
			videos[i].onended = function(){
				count++;
				if(count>=videos.length){
					isFinishedVideo = true;
					if(isFinishedSubmit==true){
						let xmlhttp = new XMLHttpRequest();
						xmlhttp.open("GET","../Excellent-Course/RecordServlet"+window.location.search,true);
						xmlhttp.send(null);
					}
				}
			};
		}
	}

	
	// content内容里是否包含了提交按钮
	if(btnSubmit!=null){
		if(btnSubmit.getAttribute("form")=="homework"){
			// 提交按钮一般只要一个即可,也就是只需提交一次即可
			let form = document.getElementById("homework");
			form.onsubmit = function(){
				isFinishedSubmit = true;
				if(isFinishedVideo==true){
					let xmlhttp = new XMLHttpRequest();
					xmlhttp.open("GET","../Excellent-Course/RecordServlet"+window.location.search,true);
					xmlhttp.send(null);
				}
			};
		}
		if(btnSubmit.getAttribute("form")=="testing"){
			// 拼接字符串名
			let combination = document.getElementsByName('combination')[0]
			let radios = document.querySelectorAll("input[type=\"radio\"]");
			let str = "";
			for(let i=0; i<radios.length; i++){
				if(radios[i].name!=str){
					combination.value += ','+radios[i].name;
					str = radios[i].name;
				}
			}
			let checkboxs = document.querySelectorAll("input[type=\"checkbox\"]");
			for(let i=0; i<checkboxs.length; i++){
				if(checkboxs[i].name!=str){
					combination.value += ','+checkboxs[i].name;
					str = checkboxs[i].name;
				}
			}
		}
	}
	
};