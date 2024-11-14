let taskArr = []; 
 
const inputRef = document.querySelector("#taskInput"); 
const totalTasksUl = document.getElementById("totalTasks"); 
const completedTasksUl = document.getElementById("completedTasks"); 
 
function Addtask() { 
    const taskText = inputRef.value; 
    if (taskText) { 
        taskArr.push({ text: taskText, completed: false }); 
        inputRef.value = ''; 
        displayTask(); 
    } 
} 
 
function displayTask() { 
    totalTasksUl.innerHTML = ''; 
    completedTasksUl.innerHTML = ''; 
 
    taskArr.forEach((task, index) => { 
        const liRef = document.createElement('li'); 
        liRef.innerText = task.text; 
        if (task.completed) { 
            liRef.className = 'completed'; 
        } else { 
            liRef.className = ' '; 
        } 
 
        const taskDiv = document.createElement('div'); 
        taskDiv.className = 'task'; 
        taskDiv.appendChild(liRef); 
 
        if (!task.completed) { 
            const confirmButton = document.createElement('button'); 
            confirmButton.innerText = "Check"; 
            confirmButton.onclick = () => checkTask(index); 
 
            const deleteButton = document.createElement('button'); 
            deleteButton.innerText = "Delete"; 
            deleteButton.onclick = () => deleteTask(index); 
 
            taskDiv.appendChild(confirmButton); 
            taskDiv.appendChild(deleteButton); 
 
            totalTasksUl.appendChild(taskDiv); 
        } else { 
            completedTasksUl.appendChild(taskDiv); 
        } 
    }); 
} 
 
 
function checkTask(index) { 
    taskArr[index].completed = !taskArr[index].completed; 
    displayTask(); 
} 
 
function deleteTask(index) { 
    taskArr.splice(index, 1); 
    displayTask(); 
}
