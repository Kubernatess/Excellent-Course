window.onload = function(){
    let hyperlinks = document.getElementsByTagName('a');
    for(let i=0; i<hyperlinks.length; i++){
    	let progress = hyperlinks[i].getElementsByTagName("progress")[0];
        let samp = hyperlinks[i].getElementsByTagName("samp")[0];
        samp.innerHTML = progress.value+'%';
    }
};