package com.brianlin.to_do_list.service;

import com.brianlin.to_do_list.model.Task;
import com.brianlin.to_do_list.repository.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * ClassName:
 * Description:
 *              這裡放置業務邏輯的處理。你可能會有一個 TaskService 類，
 *              其中包含處理業務邏輯的方法，例如新增任務、刪除任務或更新任務。
 *              這裡是處理從前端傳來的數據並準備將其存儲到資料庫的地方。
 * @Author Rin
 * @Create 2024/8/2 上午 03:25
 * @Version 1.0
 */
public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();
    public void addTask(Task task){
        int newTaskId = taskRepository.addTask(task);
        task.setId(newTaskId);
        System.out.println("sucess to add task.");
    }

    public void deleteTaskById(int id){
        taskRepository.deleteTask(id);
        System.out.println("sucess to delete ID = " + id + " task.");
    }

    public Task getTaskById(int id){
        Task task = taskRepository.getTaskById(id);
        return task;
    }

    public List<Task> getAllTasks(){
        List<Task> allTasks = taskRepository.getAllTasks();
        return allTasks;
    }

    public void updateTask(Task newTask){
        taskRepository.updateTask(newTask);
        System.out.println("sucess update");
    }
}
