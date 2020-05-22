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
        // 触发它的编辑按钮
        let edit = clonedRow.querySelector("input[name=\"edit\"]");
        edit.addEventListener("click",editHandler.call(edit));
        // 开启学号/工号表单项
        let firstInput = clonedRow.querySelector("input");
        firstInput.readOnly = false;
        firstInput.focus();
    };

    // 初始化默认'系别'下拉选项
    let departList = document.getElementsByName("depart");
    for(let i=0; i<departList.length; i++){
        let depart = departList[i].getAttribute("data-depart");
        for(let j=0; j<departList[i].length; j++){
            if(departList[i].options[j].value==depart){
                departList[i].options[j].selected = true;
                break;
            }
        }
    }

    // 初始化默认'身份'下拉选项
    let statusList = document.getElementsByName("status");
    for(let i=0; i<statusList.length; i++){
        let status = statusList[i].getAttribute("data-status");
        for(let j=0; j<statusList[i].length; j++){
            if(statusList[i].options[j].value==status){
                statusList[i].options[j].selected = true;
                break;
            }
        }
    }

};


function editHandler(){
    // 禁用添加用户按钮
    let add = document.querySelector("button");
    add.disabled = true;
    // 开启当前这一行的所有表单项目
    let selectedRow = this.parentNode.parentNode;
    let inputnodes = selectedRow.querySelectorAll("input[type=\"text\"]");
    for(let i=0; i<inputnodes.length; i++){
        inputnodes[i].disabled = false;
        if(i==0){
            inputnodes[i].readOnly = true;
        }
    }
    // 开启下拉列表
    let selectNodes = selectedRow.querySelectorAll("select");
    for(let i=0; i<selectNodes.length; i++){
        selectNodes[i].disabled = false;
    }
    // 关闭所有的点击按钮
    let btnImages = document.querySelectorAll("input[type=\"image\"]");
    for(let i=0; i<btnImages.length; i++){
        btnImages[i].disabled = true;
    }
}