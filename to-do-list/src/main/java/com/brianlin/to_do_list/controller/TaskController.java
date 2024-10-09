package com.brianlin.to_do_list.controller;

import com.brianlin.to_do_list.service.TaskService;
import com.brianlin.to_do_list.model.Task;
import com.brianlin.to_do_list.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:
 * Description:
 *              UI 類別（或層級）通常 1.負責處理來自前端的 HTTP 請求，判斷是否為合法request，是的話才調用service層的方法
 *                                 2.並與 Service 層進行互動，將資料傳遞給 Service 層進行邏輯處理後，將結果返回給前端。
 *              你可以將它理解為控制器層，在典型的 Java Web 應用程式中，這個層通常會使用 Servlet 或 Spring Controller 來實現。
 *
 *              RESTful 控制器遵循 REST 的架構風格，通過不同的 HTTP 方法來對資源進行操作：
 *
 *              GET：獲取資源
 *              POST：新增資源
 *              PUT：更新資源
 *              DELETE：刪除資源
 *
 * @Author Rin
 * @Create 2024/8/30 下午 05:03
 * @Version 1.0
 */

@RestController
@RequestMapping("/api/tasks") //@RequestMapping("/api/tasks") 代表所有 /api/tasks 開頭的請求會映射到這個控制器處理。
public class TaskController {

    private final TaskService taskService;

    public TaskController(){
        taskService = new TaskService();
    }

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 取得所有任務
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 根據ID取得單個任務
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    /**
     * 新增任務
     * @param task
     * 這裡的 @RequestBody 標註告訴 Spring 框架，它應該從 HTTP 請求的請求體(RequestBody)中解析數據，
     * 並將這些數據轉換成 Task 類的實例。這意味著前端發送的請求體中的 JSON 或 XML 數據會被 "自動轉換為一個 Task 對象"。
     */
    @PostMapping
    public Task addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return task;
    }

    // 更新任務
    @PutMapping("/{id}")
    public void updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    // 刪除任務
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTaskById(id);
    }
}
