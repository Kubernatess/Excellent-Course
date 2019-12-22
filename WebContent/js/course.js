window.onload=function(){
    let span=document.querySelector("aside>.teach>span:nth-of-type(2)");
    let num=document.querySelectorAll("aside>.teach>ul>li").length;
    span.innerHTML=num;
};