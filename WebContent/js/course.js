window.onload=function(){
	// 显示授课老师的数量
	let span = document.querySelector("#teaching>span");
    let num = document.querySelectorAll("#teaching>figure").length;
    span.innerHTML = num+'位授课老师';
    
    // 选项卡的样式与行为
    let tabs = document.querySelectorAll("article>a");
    for(let i=0; i<tabs.length; i++){
        tabs[i].onmouseover = function(){
            // 取消前面已有的样式
            let hovering = document.getElementsByClassName("hovering")[0];
            hovering.removeAttribute("class");
            // 再设置自身样式
            this.setAttribute("class","hovering");
            // 切换显示部分
            let sections = document.querySelectorAll("article>section");
            for(let j=0; j<sections.length; j++){
                if(i==j){
                    sections[j].hidden = false;
                }
                else{
                    sections[j].hidden = true;
                }
            }
        }
    }

    // 统计所有评论数目
    let list = document.querySelectorAll("#review li");
    tabs[1].innerHTML = "课程评价("+list.length+")";
    
    
    // 点击删除按钮
    for(let i=0; i<list.length; i++){
    	let btnRemove = list[i].querySelector("button[type=\"submit\"]");    	
    	if(btnRemove!=null){
    		btnRemove.onclick = function(){
        		// 确认是否删除
        	    let r=confirm("确定删除此评价?");
        	    if (r==false){
        	    	return false;
        		}
        		let dateTime = document.getElementsByName("dateTime")[0];
        		let timeObject = this.nextElementSibling;
        		dateTime.value = timeObject.dateTime;
        	};
    	}   	
    }
    
    
    // 相关课程只能显示前十门课程
	let hyperlinks = document.querySelectorAll("#related>a");
    for(let i=10; i<hyperlinks.length; i++){
    	hyperlinks[i].hidden = true;
    }
    
};


//获取URL参数值
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

// 是否参加事件处理
function isjoin(){
	let r = confirm("是否确定加入该课程,后台系统将会记录您的进度");
    if (r==false){
    	//阻止默认行为
    	var ev = ev || event;
    	ev.preventDefault();
    }
}