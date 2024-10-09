/**
 * 在頁面加載完成後初始化
 */
window.onload = function() {
    init(); // 初始化
    getAllTasks(); // 獲取任務
};


/**
 * 初始化 HTML 元素
 */
function init() {
    taskInput = document.getElementById('new-task');
    deadline = document.getElementById('deadline');
}


/**
 * 獲取所有任務後，並透過調用 renderTasks(task) 重新渲染頁面
 */
function getAllTasks() {
    fetch('/api/tasks', {
        method: 'GET'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            renderTasks(data);
        })
        .catch((error) => {
            console.error('Error fetching tasks:', error);
        });
}

/**
 * 獲取指定任務後，透過調用 renderTasks(task) 重新渲染頁面
 */
function getTaskById(id) {
    fetch('/api/tasks/' + id, {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            renderTasks(data);
        })
        .catch((error) => {
            console.error('Error fetching tasks:', error);
        });
}


/**
 * 渲染任務到頁面
 */
function renderTasks(tasks) {
    const taskList = document.getElementById('task-list');
    taskList.innerHTML = ''; // 清空目前的任務列表

    tasks.forEach(task => {
        addTaskToTable(task);

        // const li = document.createElement('li'); // 創建一個新的列表項(單一項，tasklist中的基本單元)
        // li.textContent = `${task.taskName} - ${task.deadline}`; // 填充任務名稱和截止日期
        // taskList.appendChild(li); // 添加到任務列表中
        //
        // // 在html中建立一個<button> 元素，並且幫它連接好對應 function()
        // const deleteButton = document.createElement('button');
        // deleteButton.textContent = 'Delete';
        // deleteButton.onclick = function() {
        //     taskList.removeChild(li);
        // };
        //
        // // 將deleteBuutton添加為li 的child element
        // li.appendChild(deleteButton);
        //
        // // 再將li 添加為taskList 的child element
        // taskList.appendChild(li);
    });
}


/**
 * 增加 task 至 表格中
 * @param task
 */
function addTaskToTable(task) {
    console.log("Adding task to table:", task);  // 调试输出
    let taskTableBody = document.getElementById('task-body');
    const newRow = document.createElement('tr');

    const taskIdCell = document.createElement('td');
    const taskNameCell = document.createElement('td');
    const taskDeadlineCell = document.createElement('td');
    const taskActionCell = document.createElement('td');

    taskIdCell.textContent = task.id;
    taskNameCell.textContent = task.taskName;
    taskDeadlineCell.textContent = task.deadline;

    let deleteButton = document.createElement('button');
    deleteButton.textContent = "delete";
    deleteButton.onclick = function(){
        console.log(typeof task.id);
        deleteTaskById(task.id);
        taskTableBody.removeChild(newRow);
    }

    taskActionCell.appendChild(deleteButton);

    // 將各個單元格添加到新的一行中
    newRow.appendChild(taskIdCell);
    newRow.appendChild(taskNameCell);
    newRow.appendChild(taskDeadlineCell);
    newRow.appendChild(taskActionCell);

    // 將新的一行添加到表格的 tbody 中
    taskTableBody.appendChild(newRow);
}

/**
 * (此方法目前沒有被使用)
 * 透過<span>區隔屬性
 */
function addTaskToList(task) {
    let li = document.createElement('li');

    let taskNameSpan = document.createElement('span');
    taskNameSpan.textContent = `Task: ${task.taskName}`;
    li.appendChild(taskNameSpan);

    let taskDeadlineSpan = document.createElement('span');
    taskDeadlineSpan.textContent = ` Deadline: ${task.deadline}`;
    li.appendChild(taskDeadlineSpan);

    document.getElementById('task-list').appendChild(li);
}


/**
 * 增加 task到 db中
 */
function addTask() {
    console.log(123);
    let taskInput = document.getElementById('new-task');
    let deadline = document.getElementById('deadline');
    // taskList 指向id="task-list" 的html元素，可能是<ol>或<ul>
    let taskList = document.getElementById('task-list');
    taskInput.placeholder = "繼續添加";

    if (taskInput.value.trim() === '') {
        alert('Please enter a task.');
        return;
    }
    if (deadline.value.trim() === '') {
        alert('Please enter a deadline.');
        return;
    }

    // 使用 fetch API 發送 AJAX POST請求
    fetch('/api/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            taskName: taskInput.value,
            deadline: deadline.value + ":00", //這邊是因為datetime-local只取到分鐘，但後端使用的timestamp是取到秒，所以+"00"把秒數設定成00
        })
    })
        .then(response => {
            if(!response.ok){
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // 立即在前端畫面更新
            addTaskToTable(data);
            console.log('POST成功:', data);
        })
        .catch((error) => {
            console.error('POST錯誤:', error);
        });


    //把taskInput.value，也就是輸入行 變成空的
    taskInput.value = '';
}


/**
 * 從 db 中刪除 task by id
 */
function deleteTaskById(id){

    // 使用 fetch API 發送 AJAX POST請求
    fetch('/api/tasks/' + id, {
        method: 'DELETE'
    })
        .then(response => {
            if(!response.ok){
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('DELETE成功:', data);
        })
        .catch((error) => {
            console.error('DELETE錯誤:', error);
        });

}

