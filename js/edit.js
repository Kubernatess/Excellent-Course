// JavaScript Document
window.onload=function(){
	// 创建 JavaScript 对象实例
	var font=new Font(24,"normal","black","normal");
	
	
	// 字体大小事件
	var fontSizes=document.getElementsByClassName('dropdown-content')[0].children;
	for(var i=0;i<fontSizes.length;i++){
		fontSizes[i].index=i;
		fontSizes[i].onclick=function(){
			// 设置字体大小
			font.size=16+this.index*4;
			// 追加新的文本
			appendNewText(font);
		};
	}
	
	
	// 清除样式事件
	var clear=document.getElementById('clear');
	clear.onclick=function(){
		// 设置字体属性
		font.size=24;
		font.style="normal";
		font.color="black";
		font.weight="normal";
		// 追加新的文本
		appendNewText(font);
	};
	
	
	// 正常字体事件
	var normal=document.getElementById('normal');
	normal.onclick=function(){
		// 设置字体样式
		font.style="normal";
		font.weight="normal";
		// 追加新的文本
		appendNewText(font);
	};
	
	
	// 加粗字体事件
	var bolder=document.getElementById('bold');
	bolder.onclick=function(){
		// 设置字体样式
		font.weight="bold";
		// 追加新的文本
		appendNewText(font);
	};
	
	
	// 倾斜字体事件
	var italic=document.getElementById('italic');
	italic.onclick=function(){
		// 设置字体样式
		font.style="italic";
		// 追加新的文本
		appendNewText(font);
	};
	
	
	// 字体颜色事件
	var fontColor=document.getElementsByName('favcolor')[0];
	fontColor.onchange=function(){
		// 设置字体颜色
		font.color=new String(this.value);
		// 追加新的文本
		appendNewText(font);
	};
	
	
	// 添加超链接
	var hyperlink=document.getElementById('hyperlink');
	hyperlink.onclick=function(){
		var editor=document.getElementById('editor');
		var hypernode=document.createElement("a");
		var txtnode=document.createTextNode("这是一个链接");
		var hyperlink=prompt("请输入网址","http://");
		if (hyperlink!=null && hyperlink!=""){
			hypernode.href=hyperlink;
		}
		hypernode.appendChild(txtnode);
		editor.appendChild(hypernode);
	};
	
	
	// 附件形式下载
	var attachment=document.getElementsByName('attachment')[0];
	attachment.onchange=function(){
		// 提取文件名
		var index=this.value.lastIndexOf('\\');
		var fileName=this.value.substr(index+1);
		// 创建一些节点
		var form=document.getElementsByTagName('form')[0];
		var editor=document.getElementById('editor');
		var divnode=document.createElement("div");
		var textnode1=document.createTextNode("附件: ");
		var spannode=document.createElement("span");
		var textnode2=document.createTextNode(fileName);
		var hypernode=document.createElement("a");
		hypernode.href="#";
		var textnode3=document.createTextNode("下载地址");
		// 追加节点
		hypernode.appendChild(textnode3);
		spannode.appendChild(textnode2);
		divnode.appendChild(textnode1);
		divnode.appendChild(spannode);
		divnode.appendChild(hypernode);
		editor.appendChild(divnode);
		// 在提交表单之前,更新隐藏域的值
		var hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
		
		form.submit();
	};
	
	
	// 上传图片事件
	var image=document.getElementsByName('image')[0];
	image.onchange=function(){
		var form=document.getElementsByTagName('form')[0];
		var editor=document.getElementById('editor');
		var imagenode=document.createElement("img");
		imagenode.src=""+this.value;
		editor.appendChild(imagenode);
		// 在提交表单之前,更新隐藏域的值
		var hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
		
		form.submit();
	};
	
	
	// 上传视频事件
	var video=document.getElementsByName('video')[0];
	video.onchange=function(){
		// 提取文件名
		var index=this.value.lastIndexOf('\\');
		var fileName=this.value.substr(index+1);
		var form=document.getElementsByTagName('form')[0];
		var editor=document.getElementById('editor');
		var videoNode=document.createElement("video");
		videoNode.width="320";
		videoNode.height="240";
		videoNode.controls=true;
		var sourceNode1=document.createElement("source");
		sourceNode1.src=""+fileName;
		sourceNode1.type="video/mp4";
		var sourceNode2=document.createElement("source");
		sourceNode2.src=""+fileName;
		sourceNode2.type="video/ogg";
		videoNode.appendChild(sourceNode1);
		videoNode.appendChild(sourceNode2);
		editor.appendChild(videoNode);
		// 在提交表单之前,更新隐藏域的值
		var hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
		
		form.submit();
	};
	
	
	// 保存按钮事件
	var btnSave=document.getElementById('save');
	btnSave.onclick=function(){
		var form=document.getElementsByTagName('form')[0];
		var hidden=document.getElementsByName('content')[0]
		var editor=document.getElementById('editor');
		hidden.value=editor.innerHTML;
		form.submit();
	};


};


// 字体类构造器
function Font(size,style,color,weight)
{
    this.size=size;
    this.style=style;
    this.color=color;
	this.weight=weight;
}


// 追加新的文本
function appendNewText(font){
	var editor=document.getElementById('editor');
			
	var spannode = document.createElement("span");
	var textnode = document.createTextNode("在此处编辑");
	spannode.appendChild(textnode);
	editor.appendChild(spannode);
	
	// 设置字体属性
	spannode.style.fontSize=font.size+"px";
	spannode.style.fontStyle=font.style;
	spannode.style.color=font.color;
	spannode.style.fontWeight=font.weight;
}



function selectText(){
		if(document.Selection){       
			//ie浏览器
			return document.selection.createRange().text;     	 
		}else{    
			//标准浏览器
			return window.getSelection().toString();	 
		}	 
}