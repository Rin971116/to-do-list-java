package com.brianlin.to_do_list.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ClasstaskName:
 * Description:
 *                  通常是與 資料庫表 對應的 java類，它代表一個待辦事項的資料模型（例如，包含任務名稱、描述、創建日期等屬性）
 * @Author Rin
 * @Create 2024/8/5 下午 06:27
 * @Version 1.0
 */
public class Task {
    private int id;
    private String taskName;
    private Timestamp deadline;

    public Task(){

    };

    public Task(String taskName, Timestamp deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public Task(int id, String taskName, Timestamp deadline) {
        this.id = id;
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, deadline);
    }
}
