window.onload = function(){
    // 点击编辑按钮事件
    let btnEdit = document.getElementsByName("edit");
    for(let i=0; i<btnEdit.length; i++){
        btnEdit[i].addEventListener("click",editHandler);
    }

    // 点击删除按钮事件
    let btnRemove = document.getElementsByName("remove");
    for(let i=0; i<btnRemove.length; i++){
        btnRemove[i].onclick = function(){
        	// 确认是否删除
	        let r=confirm("确定删除此用户?");
	        if (r==false){
		        return;
            }
	        // 开启当前这一行的第一个表单项
            let selectedRow = this.parentNode.parentNode;
            selectedRow.querySelector("input").disabled = false;
            // 设置隐藏域的值
            let operation = document.getElementsByName("operation")[0];
            operation.value = "remove";
        };
    }
    

    // 添加用户事件
    let add = document.querySelector("button");
    add.onclick = function(){
    	// 复制节点
    	let table = document.querySelector("table");
        let lastRow = table.rows[table.rows.length-1];
        let clonedRow = lastRow.cloneNode(true);
        table.appendChild(clonedRow);
        // 侦听关联事件
        let depart = clonedRow.querySelector("select[name=\"depart\"]");
        depart.addEventListener("change",changeDeaprtList);
        // 触发它的编辑按钮
        let edit = clonedRow.querySelector("input[name=\"edit\"]");
        edit.addEventListener("click",editHandler.call(edit));
        // 开启学号/工号表单项
        let firstInput = clonedRow.querySelector("input");
        firstInput.disabled = false;
        firstInput.focus();
        // 设置隐藏域的值
        let operation = document.getElementsByName("operation")[0];
        operation.value = "add";
    };

    // 下拉列表事件
    let departList = document.getElementsByName("depart");
    for(let i=0; i<departList.length; i++){
        // 初始化页面时需要执行一次
        departList[i].onchange = changeDeaprtList.call(departList[i]);
        departList[i].addEventListener("change",changeDeaprtList);
    }
    
};


function editHandler(){
	// 设置隐藏域的值
    let operation = document.getElementsByName("operation")[0];
    operation.value = "modify";
    // 开启当前这一行的所有表单项目
    let selectedRow = this.parentNode.parentNode;
    let inputnodes = selectedRow.querySelectorAll("input[type=\"text\"]");
    for(let i=0; i<inputnodes.length; i++){
        inputnodes[i].disabled = false;
        if(i==0){
            inputnodes[i].readOnly = true;
        }
    }
    let selectnodes = selectedRow.querySelectorAll("select");
    for(let i=0; i<selectnodes.length; i++){
        selectnodes[i].disabled = false;
    }
    // 关闭所有的点击按钮
    let btnImages = document.querySelectorAll("input[type=\"image\"]");
    for(let i=0; i<btnImages.length; i++){
        btnImages[i].disabled = true;
    }
}


function changeDeaprtList(){
    // 定义系别专业数组
    let departsAndMajors = new Array();
    departsAndMajors["软件工程系"] = ["电子与计算机工程","数据科学与大数据技术","软件工程"];
    departsAndMajors["网络技术系"] = ["网络工程","信息管理与信息系统"];
    departsAndMajors["电子系"] = ["智能科学与技术","电子信息工程","通信工程","自动化"];
    departsAndMajors["游戏系"] = ["数字媒体技术","动画","网络与新媒体"];
    departsAndMajors["数码媒体系"] = ["环境设计","风景园林","视觉传达设计","数字媒体艺术","产品设计"];
    departsAndMajors["管理系"] = ["行政管理","物流管理","工商管理","市场营销","人力资源管理"];
    departsAndMajors["国际经贸系"] = ["电子商务","国际经济与贸易"];
    departsAndMajors["财会系"] = ["财务管理","会计学"];
    departsAndMajors["外语系"] = ["英语","日语"];
    departsAndMajors["计算机系"] = ["计算机科学与技术","物联网工程"];
    let selectedRow = this.parentNode.parentNode;
    let majorList = selectedRow.querySelector("select[name=\"major\"]");
    let selDepart = this.options[this.selectedIndex].value;
    while (majorList.options.length){
        majorList.remove(0);
    }
    let departAndMajor = departsAndMajors[selDepart];
    if (departAndMajor){
        for(let i=0; i<departAndMajor.length; i++){
            let major = new Option(departAndMajor[i],departAndMajor[i]);
            majorList.options.add(major);
        }
    }
}