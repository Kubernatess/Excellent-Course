window.onload = function(){
    // 求出打灯数量
    let imgs = document.querySelectorAll("#completion>figure>img");
    let lights = 0;
    for(let i=0; i<imgs.length; i++){
        if(imgs[i].style.visibility!="hidden"){
            lights += 1;
        }
    }
    // 填充数据
    let progress = document.querySelector("#completion>progress");
    progress.value = lights>imgs.length?100:(lights/imgs.length)*100;
    let samp = document.querySelector("#completion>samp:empty");
    samp.innerHTML = progress.value;

    // 计算总的完成度
    let progresses = document.querySelectorAll("progress");
    progress = document.querySelector("p>progress");
    progress.value = (progresses[0].value*30 + progresses[1].value*30 + progresses[2].value*40)/100;
    samp = document.querySelector("p>samp");
    samp.innerHTML = progress.value;
};