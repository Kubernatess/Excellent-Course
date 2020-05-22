// JavaScript Document
window.onload=function(){
	let editForm = document.getElementById("editForm");
	let p = document.querySelector("main>p");
	// 如果子栏目数目为0,则显示欢迎文本
	let hyperlinks = document.querySelectorAll("#asideForm>a");
	if(hyperlinks.length==0){
		p.hidden = false;
		editForm.hidden = true;
	}
	else{
		editForm.hidden = false;
		p.hidden = true;
		// 如果没有columnIndex参数,则默认点击第一个超链接
		let columnIndex = getQueryVariable("columnIndex");
		if(columnIndex==""){
			hyperlinks[0].click();
			return;
		}
		else{
			// 根据URL的columnIndex参数值显示对应的焦点样式
			let inputObject = document.querySelector("#asideForm input[data-columnindex=\""+columnIndex+"\"]");
			let hyperlink = inputObject.parentNode;
			hyperlink.setAttribute("class","tap");
		}
	}


	// 右键菜单事件
	for(let i=0; i<hyperlinks.length; i++){
		let input = hyperlinks[i].children[0];
		input.oncontextmenu = function(){
			//阻止默认行为
			var ev = ev||event;
			ev.preventDefault();
			//记录当前的坐标(x轴和y轴)
			let x = ev.clientX;
			let y = ev.clientY;
			//右键点击时显示菜单框
			let menu = document.querySelector("body>ul");
			menu.style.display = "block";
			menu.style.left = x+'px';
			menu.style.top = y+'px';
			// 绑定右键菜单功能
			let moveup = menu.children[0];
			let movedown = menu.children[1];
			let rename = menu.children[2];
			let remove = menu.children[3];
			// 绑定右键菜单的所有功能
			moveup.onclick = moveColumn.bind(this,"moveup");
			movedown.onclick = moveColumn.bind(this,"movedown");
			rename.onclick = renameColumn.bind(this);
			remove.onclick = removeColumn.bind(this);
			
			// 如果上一个元素为空,则隐藏上移功能
			let A = this.parentNode;
			let previous = A.previousElementSibling;
			moveup.hidden = previous==null?true:false;
			
			// 同理,如果下一个元素为空,则隐藏下移功能
			let next = A.nextElementSibling;
			movedown.hidden = next.tagName!="A"?true:false;
		};
	}

	// 在栏目范围外,右键隐藏菜单
	document.onclick = function(){
		let menu = document.querySelector("body>ul");
		menu.style.display = "none";
    }
	
	// 点击添加按钮事件处理
	let add = document.querySelector("#asideForm>button");
	add.onclick = function(){
		// 获取最后一个元素的索引值
		let form = this.form;
		let INPUTS = form.querySelectorAll("a>input");
		let lastInput = INPUTS[INPUTS.length-1];
		let lastIndex = 0;
		if(lastInput!=null){
			lastIndex = lastInput.getAttribute("data-columnindex");
		}
		// 创建新节点
		let A = document.createElement('a');
		let button = form.querySelector("button");
		form.insertBefore(A,button);
		A.innerHTML = "<input type=\"text\" value=\"新增子栏目\" data-columnindex=\""+(parseInt(lastIndex)+1)+"\">";
		// 自动执行重命名方法
		renameColumn.call(A.children[0]);
	};

	
	// 需要通过js来控制保存按钮的位置
	let editor = document.getElementById("editor");
	editor.onmouseover = function(){
		let save = document.getElementById('save');
		let preview = document.getElementById('preview');
		save.style.top = (this.scrollHeight+150)+'px';
		preview.style.top = (this.scrollHeight+150)+'px';
		
		// 提交按钮只能出现一个,并且提交按钮和video元素不能同时存在
		let submitObject = this.querySelector("input[type=\"submit\"]");
		let btnSubmit = document.getElementById("btnSubmit");
		let videoInput = document.getElementById("video");
		let btnVideo = videoInput.parentNode;
		let videoObject = this.querySelector("video");
		if(submitObject==null&&videoObject==null){
			btnSubmit.disabled = false;
			videoInput.disabled = false;
			btnVideo.disabled = false;
		}
		else {
			btnSubmit.disabled = true;
			videoInput.disabled = true;
			btnVideo.disabled = true;
		}

		// subjective和objective两个表单组也不能同时存在
		let subjectiveFieldset = document.getElementById("subjective");		
		let radioObject = this.querySelector("input[type=\"radio\"]");
		let checkboxObject = this.querySelector("input[type=\"checkbox\"]");		
		if(radioObject!=null||checkboxObject!=null){
			subjectiveFieldset.disabled = true;
		}
		else{
			subjectiveFieldset.disabled = false;
		}

		let objectiveFieldset = document.getElementById("objective");
		let textareaObject = this.querySelector("textarea");
		let uploadObject = this.querySelector("input[type=\"file\"]");
		if(textareaObject!=null||uploadObject!=null){
			objectiveFieldset.disabled = true;
		}
		else{
			objectiveFieldset.disabled = false;
		}
		
	};
	

	// 创建 JavaScript 对象实例
	let font = new Font(24,"normal","black","normal","left");
	
	// 字体大小事件
	let fontSizes = document.querySelectorAll(".dropdown-content>li");
	for(let i=0; i<fontSizes.length; i++){
		fontSizes[i].index = i;
		fontSizes[i].onclick = function(){
			font.size = 16+this.index*4;
			appendNewText(font);
		};
	}
	
	// 清除样式事件,也就是默认字体样式
	let clear = document.getElementById('clear');
	clear.onclick = function(){
		font.size = 24;
		font.style = "normal";
		font.color = "black";
		font.weight = "normal";
		appendNewText(font);
	};
		
	// 正常字体事件
	let normal = document.getElementById('normal');
	normal.onclick = function(){
		font.style = "normal";
		font.weight = "normal";
		appendNewText(font);
	};
	
	// 加粗字体事件
	let bolder = document.getElementById('bold');
	bolder.onclick = function(){
		font.weight = "bold";
		appendNewText(font);
	};
	
	// 倾斜字体事件
	let italic = document.getElementById('italic');
	italic.onclick = function(){
		font.style = "italic";
		appendNewText(font);
	};
		
	// 字体颜色事件
	let fontColor = document.getElementById('fontColor');
	fontColor.onchange = function(){
		font.color = new String(this.value);
		appendNewText(font);
	};


	// 添加左对齐字体
	let leftAlign = document.getElementById("leftAlign");
	leftAlign.onclick = function(){
		font.align = "left";
		appendNewText(font);
	};

	// 添加居中字体
	let centerAlign = document.getElementById("centerAlign");
	centerAlign.onclick = function(){
		font.align = "center";
		appendNewText(font);
	};

	// 添加右对齐字体
	let rightAlign = document.getElementById("rightAlign");
	rightAlign.onclick = function(){
		font.align = "right";
		appendNewText(font);
	};
	
	
	// 添加超链接
	let hyperlink = document.getElementById('hyperlink');
	hyperlink.onclick = function(){
		let hyperlink = prompt("请输入网址","http://");
		if (hyperlink!=null && hyperlink!=""){
			let div = document.createElement("div");
			editor.appendChild(div);
			div.innerHTML = "<a target=\"_blank\">这是一个链接</a>";
			div.children[0].href = hyperlink;
		}
	};
	
	
	// 附件形式下载
	let attachment = document.getElementById('attachment');
	attachment.onchange = function(){
		// 表单项必须要有name属性才能提交数据
		this.name = "upload";
		// 提取文件名
		let index = this.value.lastIndexOf('\\');
		let fileName = this.value.substr(index+1);
		// 创建一些节点
		let divnode=document.createElement("div");
		editor.appendChild(divnode);
		divnode.innerHTML = "附件："+fileName+"<a target=\"_blank\">下载链接</a>";		
		// 设置下载链接地址
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if (this.readyState==4 && this.status==200)
			{
				let A = divnode.children[0];
				A.href = this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 上传完文件需要立刻移除name属性
		this.removeAttribute("name");
	};
	
	
	// 上传图片事件
	let image = document.getElementById('image');
	image.onchange = function(){
		// 表单项必须要有name属性才能提交数据
		this.name = "upload";
		
		let imagenode = document.createElement("img");
		editor.appendChild(imagenode);
		
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if (this.readyState==4 && this.status==200)
			{
				imagenode.src = this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		// 上传完文件需要立刻移除name属性
		this.removeAttribute("name");
	};
	
	
	// 上传视频事件
	let video = document.getElementById('video');
	video.onchange = function(){
		// 表单项必须要有name属性才能提交数据
		this.name = "upload";
		
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<video width=\"800\" height=\"400\" controls><source type=\"video/mp4\"></video>";
		
		let fd = new FormData(this.form);
		let xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if (this.readyState==4 && this.status==200)
			{
				let source = div.querySelector("source");
				source.src = this.responseText;
			}
		}
		xmlhttp.open("POST","../UploadServlet",true);
		xmlhttp.send(fd);
		
		// 上传完文件需要立刻移除name属性
		this.removeAttribute("name");
	};
	
	
	// 播放pdf和文本文件
	let pdf = document.getElementById('pdf');
	let txt = document.getElementById('txt');
	pdf.onchange = txt.onchange = function(){
		iframPlayer.call(this,"UploadServlet");
	}
	
	// 播放Word、PPT、Excel文件
	let word = document.getElementById('word');
	let ppt = document.getElementById('ppt');
	let excel = document.getElementById('excel');
	word.onchange = ppt.onchange = excel.onchange = function(){
		iframPlayer.call(this,"ConverterServlet");
	};

	
	// 保存和保存预览事件处理
	let save = document.getElementById('save');
	let preview = document.getElementById('preview');
	// 为了减少报错信息
	if(save==null){
		return;
	}
	save.addEventListener("click",submitHandler);
	preview.addEventListener("click",submitHandler);
	preview.onclick = function(){
		window.open("../learning.jsp"+window.location.search);
	};
	

	// 创建多行文本框
	let textarea = document.getElementById("Textarea");
	textarea.onclick = function(){
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<textarea form=\"subjective\" name=\"textarea\"></textarea>";
	};

	// 创建单选按钮
	let btnRadio = document.getElementById("radio");
	btnRadio.onclick = function(){
		let anyName = getAnyName();
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<ol type=\"A\"><li><input type=\"radio\" name="+anyName+">选项A</li><li><input type=\"radio\" name="+anyName+">选项B</li><li><input type=\"radio\" name="+anyName+">选项C</li><li><input type=\"radio\" name="+anyName+">选项D</li></ol>";
	}

	// 创建多选按钮
	let btnCheckbox = document.getElementById("checkbox");
	btnCheckbox.onclick = function(){
		let anyName = getAnyName();
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<ol type=\"A\"><li><input type=\"checkbox\" name="+anyName+">选项A</li><li><input type=\"checkbox\" name="+anyName+">选项B</li><li><input type=\"checkbox\" name="+anyName+">选项C</li><li><input type=\"checkbox\" name="+anyName+">选项D</li><li><input type=\"checkbox\" name="+anyName+">选项E</li></ol>";
	};
	
	// 添加上传按钮
	let UploadFile = document.getElementById("UploadFile");
	UploadFile.onclick = function(){
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<input type=\"file\" name=\"upload\" form=\"subjective\">";
	};

	// 创建提交按钮
	let btnSubmit = document.getElementById("btnSubmit");
	btnSubmit.onclick = function(){
		let div = document.createElement("div");
		editor.appendChild(div);
		div.innerHTML = "<input type=\"submit\" class=\"btn\" value=\"提交答案\" form=\"\" >";
	};

};


//删除栏目方法
function removeColumn(){
	// 设置隐藏域的值
	let form = this.form;
	let columnIndex = form.querySelector("input[name=\"columnIndex\"]");	
	columnIndex.value = this.getAttribute("data-columnindex");
	// 提交表单
	form.submit();
}


// 重命名方法
function renameColumn(){
	// 禁用所有节点右键菜单事件和所有的超链接
	let form = this.form;
	let hyperlinks = form.querySelectorAll("a");
	for(let i=0; i<hyperlinks.length; i++){
		hyperlinks[i].removeAttribute("href");
		hyperlinks[i].children[0].disabled = true;
		hyperlinks[i].children[0].oncontextmenu = function(){};
	}
	// 禁用添加按钮
	let button = form.querySelector("button");
	button.disabled = true;
	// 启用当前输入框
	this.readOnly = false;
	this.disabled = false;
	this.style.cursor = "auto";
	this.name = "columnName";
	this.focus();

	// 启用提交按钮
	let btnSubmit = form.querySelector("input[type=\"submit\"]");
	btnSubmit.disabled = false;
	
	// 设置隐藏域的值
	let columnIndex = form.querySelector("input[name=\"columnIndex\"]");
	columnIndex.value = this.getAttribute("data-columnindex");
}


//栏目的上移和下移操作
function moveColumn(move){
	// 设置隐藏域的值
	let form = this.form;
	let columnIndex = form.querySelector("input[name=\"columnIndex\"]");
	columnIndex.value = this.getAttribute("data-columnindex");
	let anotherIndex = form.querySelector("input[name=\"anotherIndex\"]");
	// 获取上一个元素的索引值
	if(move=="moveup"){		
		let previous = this.parentNode.previousElementSibling.children[0];	
		anotherIndex.value = previous.getAttribute("data-columnindex");
	}
	// 获取上一个元素的索引值
	else if(move=="movedown"){		
		let next = this.parentNode.nextElementSibling.children[0];
		anotherIndex.value = next.getAttribute("data-columnindex");
	}
	// 提交表单
	form.submit();
}


// 字体类构造器
function Font(size,style,color,weight,align)
{
    this.size = size;
    this.style = style;
    this.color = color;
	this.weight = weight;
	this.align = align;
}


// 追加新的文本
function appendNewText(font){
	let div = document.createElement("div");
	editor.appendChild(div);
	div.innerHTML = "<div style=\"width:100%;text-align:"+font.align+";\"><span style=\"font-size:"+font.size+"px;font-style:"+font.style+";color:"+font.color+";font-weight:"+font.weight+";\">在此处编辑</span></div>";
}


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


//提交表单时的一些处理
function submitHandler(){
	// 单选组和复选组全部改成递增序列
	let ol = editor.querySelectorAll("ol");
	for(let i=0; i<ol.length; i++){
		let list = ol[i].querySelectorAll("li");		
		for(let j=0; j<list.length; j++){
			let input = list[j].children[0];
			input.name = i+1;
			// 并初始化value值
			input.value = String.fromCharCode(65+j);
			if(input.checked){
				input.setAttribute("checked",true);
			}
			else{
				input.removeAttribute("checked");
			}
		}
	}
	// 更新序列隐藏域
	let sequence = document.getElementsByName("sequence")[0];
	sequence.value = ol.length;
	
	// 最后保存编辑器里所有的HTML标签
	let content = document.getElementsByName('content')[0];
	content.value = editor.innerHTML;
}


// 创建iframe播放器
function iframPlayer(servlet) {
	// 表单项必须要有name属性才能提交数据
	this.name = "upload";
	// 创建iframe节点,用作pdf播放器
	let iframeNode = document.createElement("iframe");
	editor.appendChild(iframeNode);
	iframeNode.style.border = "none";
	iframeNode.style.resize = "both";
	// 接收URL地址
	let fd = new FormData(this.form);
	let xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200)
		{
			iframeNode.src = this.responseText;
		}
	}
	xmlhttp.open("POST","../"+servlet,true);
	xmlhttp.send(fd);
	// 上传完文件需要立刻移除name属性
	this.removeAttribute("name");
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