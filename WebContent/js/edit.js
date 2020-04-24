// JavaScript Document
window.onload=function(){
	// 需要通过js来控制保存按钮的位置
	let editor = document.getElementById("editor");
	editor.onmouseover = function(){
		let save = document.getElementById('save');
		let preview = document.getElementById('preview');
		save.style.top = (this.scrollHeight+100)+'px';
		preview.style.top = (this.scrollHeight+100)+'px';
	};
	
	
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
		var hypernode=document.createElement("a");
		var txtnode=document.createTextNode("这是一个链接");
		var hyperlink = prompt("请输入网址","http://");
		hypernode.target = "_blank";
		if (hyperlink!=null && hyperlink!=""){
			hypernode.href=hyperlink;
		}
		hypernode.appendChild(txtnode);
		let editor = document.getElementById('editor');
		editor.appendChild(hypernode);
	};
	
	
	// 附件形式下载
	var attachment=document.getElementsByName('attachment')[0];
	attachment.onchange=function(){
		// 先把所有文件上次表单项的值设置为空
		let files=document.querySelectorAll("input[type=\"file\"]");
		for(let i=0;i<files.length;i++){
			if(files[i]!=this)files[i].value="";
		}
		// 创建一些节点
		var editor=document.getElementById('editor');
		var divnode=document.createElement("div");
		var textnode1=document.createTextNode("附件: ");
		var spannode=document.createElement("span");
		let index = this.value.lastIndexOf('\\');
		let fileName = this.value.substr(index+1);
		var textnode2=document.createTextNode(fileName);
		var hypernode=document.createElement("a");
		var textnode3=document.createTextNode("下载地址");
		// 追加节点
		hypernode.appendChild(textnode3);
		spannode.appendChild(textnode2);
		divnode.appendChild(textnode1);
		divnode.appendChild(spannode);
		divnode.appendChild(hypernode);
		editor.appendChild(divnode);
		// 设置下载链接地址
		var fd = new FormData(this.form);
		var xmlhttp=new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if (this.readyState==4 && this.status==200)
			{
				hypernode.href=this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 在提交表单之前,更新隐藏域的值
		var hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
	};
	
	
	// 上传图片事件
	var image=document.getElementsByName('image')[0];
	image.onchange=function(){
		// 先把所有文件上传表单项的值设置为空
		let files=document.querySelectorAll("input[type=\"file\"]");
		for(let i=0;i<files.length;i++){
			if(files[i]!=this)files[i].value="";
		}
		
		let editor = document.getElementById('editor');
		let imagenode = document.createElement("img");
		editor.appendChild(imagenode);
		
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if (this.readyState==4 && this.status==200)
			{
				imagenode.src=this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 在提交表单之前,更新隐藏域的值
		var hidden = document.getElementsByName('content')[0];
		hidden.value = editor.innerHTML;
	};
	
	
	// 上传视频事件
	let video = document.getElementsByName('video')[0];
	video.onchange = function(){
		// 先把所有文件上次表单项的值设置为空
		let files = document.querySelectorAll("input[type=\"file\"]");
		for(let i=0; i<files.length; i++){
			if(files[i]!=this)files[i].value="";
		}
		
		let videoNode = document.createElement("video");
		let sourceNode = document.createElement("source");
		videoNode.appendChild(sourceNode);
		let editor = document.getElementById('editor');
		editor.appendChild(videoNode);
		videoNode.controls = true;
		videoNode.width = "800";
		videoNode.height = "400";
		sourceNode.type = "video/mp4";
		
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if (this.readyState==4 && this.status==200)
			{
				sourceNode.src = this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 在提交表单之前,更新隐藏域的值
		var hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
	};
	
	
	// 播放pdf和文本文件
	let pdf = document.getElementsByName('pdf')[0];
	let txt = document.getElementsByName('txt')[0];
	pdf.onchange = txt.onchange = function(){
		// 先把所有文件上次表单项的值设置为空
		let files = document.querySelectorAll("input[type=\"file\"]");
		for(let i=0; i<files.length; i++){
			if(files[i]!=this)files[i].value="";
		}
		// 创建iframe节点,用作pdf播放器
		let iframeNode = document.createElement("iframe");
		iframeNode.style.border = "none";
		iframeNode.style.resize = "both";
		let editor = document.getElementById('editor');
		editor.appendChild(iframeNode);
		// 接收URL地址
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200)
			{
				iframeNode.src = this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 在提交表单之前,更新隐藏域的值
		let hidden=document.getElementsByName('content')[0];
		hidden.value=editor.innerHTML;
	};
	
	
	// 播放Word、PPT、Excel文件
	let word = document.getElementsByName('word')[0];
	let ppt = document.getElementsByName('ppt')[0];
	let excel = document.getElementsByName('excel')[0];
	word.onchange = ppt.onchange = excel.onchange = function(){
		// 先把所有文件上次表单项的值设置为空
		let files = document.querySelectorAll("input[type=\"file\"]");
		for(let i=0; i<files.length; i++){
			if(files[i]!=this)files[i].value="";
		}
		// 创建iframe节点,用作pdf播放器
		let iframeNode = document.createElement("iframe");
		iframeNode.style.border = "none";
		iframeNode.style.resize = "both";
		let editor = document.getElementById('editor');
		editor.appendChild(iframeNode);
		// 接收URL地址
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200)
			{
				iframeNode.src = this.responseText;
			}
		}
		xmlhttp.open("POST","../ConverterServlet",true);
		xmlhttp.send(fd);
		// 在提交表单之前,更新隐藏域的值
		let hidden = document.getElementsByName('content')[0];
		hidden.value = editor.innerHTML;
	};
	
	
	// 保存按钮事件处理
	let save = document.getElementById('save');
	save.onclick = function(){
		let content = document.getElementsByName('content')[0];
		let editor = document.getElementById('editor');
		content.value = editor.innerHTML;
	};
	// 保存预览事件处理
	let preview = document.getElementById('preview');
	preview.onclick = function(){
		let content = document.getElementsByName('content')[0];
		let editor = document.getElementById('editor');
		content.value = editor.innerHTML;
		window.open("../learning.jsp"+window.location.search);
	};
	
	
	// 创建文本框
	let textField = document.getElementById("TextField");
	textField.onclick = function(){
		let editor=document.getElementById('editor');
		let inputNode = document.createElement("input");
		inputNode.type = "text";
		inputNode.name = "text";
		inputNode.required = true;
		inputNode.setAttribute("form","homework");
		editor.appendChild(inputNode);
	};

	// 创建多行文本框
	let textarea = document.getElementById("Textarea");
	textarea.onclick = function(){
		let editor=document.getElementById('editor');
		let textareaNode = document.createElement("textarea");
		textareaNode.name = "textarea";
		textareaNode.required = true;
		textareaNode.setAttribute("form","homework");
		editor.appendChild(textareaNode);
	};
	
	// 创建单选按钮
	let btnRadio = document.getElementById("radio");
	btnRadio.onclick = function(){
		let anyName = getAnyName();

		let editor = document.getElementById('editor');
		let radioA = document.createElement("input");
		let textA = document.createTextNode("选项A");
		editor.appendChild(radioA);
		editor.appendChild(textA);
		radioA.type = "radio";
		radioA.name = anyName;
		radioA.value = "A";
		
		let radioB = radioA.cloneNode(true);
		let textB = document.createTextNode("选项B");
		editor.appendChild(radioB);
		editor.appendChild(textB);
		radioB.value = "B";

		let radioC = radioA.cloneNode(true);
		let textC = document.createTextNode("选项C");
		editor.appendChild(radioC);
		editor.appendChild(textC);
		radioC.value = "C";

		let radioD = radioA.cloneNode(true);
		let textD = document.createTextNode("选项D");
		editor.appendChild(radioD);
		editor.appendChild(textD);
		radioD.value = "D";
		
		// 至少要有一个默认选择,以访用户漏选
		radioA.checked = true;
	}

	// 创建多选按钮
	let btnCheckbox = document.getElementById("checkbox");
	btnCheckbox.onclick = function(){
		let anyName = getAnyName();

		let editor = document.getElementById('editor');
		let checkboxA = document.createElement("input");
		let textA = document.createTextNode("选项A");
		editor.appendChild(checkboxA);
		editor.appendChild(textA);
		checkboxA.type = "checkbox";
		checkboxA.name = anyName;
		checkboxA.value = "A";
		
		let checkboxB = checkboxA.cloneNode(true);
		let textB = document.createTextNode("选项B");
		editor.appendChild(checkboxB);
		editor.appendChild(textB);
		checkboxB.value = "B";

		let checkboxC = checkboxA.cloneNode(true);
		let textC = document.createTextNode("选项C");
		editor.appendChild(checkboxC);
		editor.appendChild(textC);
		checkboxC.value = "C";

		let checkboxD = checkboxA.cloneNode(true);
		let textD = document.createTextNode("选项D");
		editor.appendChild(checkboxD);
		editor.appendChild(textD);
		checkboxD.value = "D";

		let checkboxE = checkboxA.cloneNode(true);
		let textE = document.createTextNode("选项E");
		editor.appendChild(checkboxE);
		editor.appendChild(textE);
		checkboxE.value = "E";
		
		// 至少要有一个默认选择,以访用户漏选
		checkboxA.checked = true;
		checkboxB.checked = true;
	};
	
	// 添加上传按钮
	let UploadFile = document.getElementById("UploadFile");
	UploadFile.onclick = function(){
		let editor=document.getElementById('editor');
		let inputNode = document.createElement("input");
		inputNode.type = "file";
		inputNode.name = "uploadFile";
		inputNode.required = true;
		inputNode.setAttribute("form","homework");
		editor.appendChild(inputNode);
	};

	// 创建提交按钮
	let submittesting = document.getElementById("Submit");
	submittesting.onclick = function(){
		let editor=document.getElementById('editor');
		let inputNode = document.createElement("input");
		inputNode.type = "submit";
		inputNode.className = "btn";
		inputNode.value = "提交答案";
		inputNode.setAttribute("form","testing");
		editor.appendChild(inputNode);
	};
	
	// 创建提交作业按钮
	let submithomework = document.getElementById("Submithomework");
	submithomework.onclick = function(){
		let editor = document.getElementById('editor');
		let inputNode = document.createElement("input");
		inputNode.type = "submit";
		inputNode.className = "btn";
		inputNode.value = "提交作业";
		inputNode.setAttribute("form","homework");
		editor.appendChild(inputNode);
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

//获取一个时间戳
function getAnyName() {
	let today = new Date();
	let year = today.getFullYear();
	let month = today.getMonth();
	let day = today.getDate()
	let hour = today.getHours();
	let minute = today.getMinutes();
	let second = today.getSeconds();
	return new String(""+year+month+day+hour+minute+second);
}