window.onload = function(){
	let hyperlinks = document.querySelectorAll("body>a");
	for(let i=0; i<hyperlinks.length; i++){
		hyperlinks[i].onclick = function(){
			return false;
		};
		// 设置双击事件
		hyperlinks[i].ondblclick = function(){
			window.location.href = this.href;
		};
	}
	
	// 设置超链接导航
	let p = document.querySelector("p");
	let courseID = getQueryVariable("courseID");
	let courseName = decodeURI(getQueryVariable("courseName"));
	let tabIndex = getQueryVariable("tabIndex");
	let tabName = decodeURI(getQueryVariable("tabName"));
	let columnIndex = getQueryVariable("columnIndex");
	let columnName = decodeURI(getQueryVariable("columnName"));
	// 拼接url字符串
	let url = p.children[0].href;
	if(courseID!=""){
		let hypernode = document.createElement("a");
		p.appendChild(hypernode);
		hypernode.innerHTML = courseName;
		hypernode.target = "_self";
		url += "?courseID="+courseID+"&courseName="+courseName;
		hypernode.href = url;
	}
	else return;
	
	if(tabIndex!=""){
		let hypernode = document.createElement("a");
		p.appendChild(hypernode);
		hypernode.innerHTML = tabName;
		hypernode.target = "_self";
		url += "&tabIndex="+tabIndex+"&tabName="+tabName;
		hypernode.href = url;		
	}
	else return;
	
	if(columnIndex!=""){
		let hypernode = document.createElement("a");
		p.appendChild(hypernode);
		hypernode.innerHTML = columnName;
		hypernode.target = "_self";
		url += "&columnIndex="+columnIndex;
		hypernode.href = url;	
	}
	else return;
};

function getQueryVariable(variable){
	let query = window.location.search.substring(1);
	let vars = query.split("&");
    for (let i=0;i<vars.length;i++) {
    	let pair = vars[i].split("=");
        if(pair[0] == variable)
        	return pair[1];
    }
    return "";
}