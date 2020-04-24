window.onload=function(){
	// 显示授课老师的数量
	let span = document.querySelector("aside>section:nth-of-type(1)>span");
    let num = document.querySelectorAll("aside>section:nth-of-type(1)>figure").length;
    span.innerHTML = num+'位授课老师';
    
 // 选项卡的样式与行为
    let tabs = document.querySelectorAll("article>span");
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
    let list = document.querySelectorAll("article>section:nth-of-type(2)>ul>li");
    tabs[1].innerHTML = "课程评价("+list.length+")";
    
    // 设置隐藏域的值
    let courseID = getQueryVariable("courseID");
    let hidden = document.getElementsByName("courseID")[0];
    hidden.value = courseID;
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