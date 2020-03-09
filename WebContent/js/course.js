window.onload=function(){
	// 显示授课老师的数量
    let span=document.querySelector("aside>.teach>span:nth-of-type(2)");
    let num=document.querySelectorAll("aside>.teach>ul>li").length;
    span.innerHTML=num+"位授课老师";
    
    
    // 选项卡的样式与行为
    let txtDetail = document.querySelectorAll(".content>span")[0];
    let txtComment = document.querySelectorAll(".content>span")[1];
    txtDetail.onmouseover = function(){
        // 选项卡切换样式
        this.style.borderBottom = "3px solid #3C0";
        this.style.color = "#3C0";
        let txtComment = document.querySelectorAll(".content>span")[1]; 
        txtComment.style.borderBottom = "none";
        txtComment.style.color = "#000";
        // 切换显示部分
        let detail = document.querySelector(".content>.detail");
        let comment = document.querySelector(".content>.comment");
        detail.style.display = "block";
        comment.style.display = "none";
    };
    txtComment.onmouseover = function(){
        // 选项卡切换样式
        this.style.borderBottom = "3px solid #3C0";
        this.style.color = "#3C0";
        let  txtDetail = document.querySelectorAll(".content>span")[0]; 
        txtDetail.style.borderBottom = "none";
        txtDetail.style.color = "#000";
        // 切换显示部分
        let detail = document.querySelector(".content>.detail");
        let comment = document.querySelector(".content>.comment");
        detail.style.display = "none";
        comment.style.display = "block";
    };


    // 统计所有评论数目
    let list = document.querySelectorAll(".container>.content>.comment>ul>li");
    txtComment.innerHTML = "课程评价("+list.length+")";
};