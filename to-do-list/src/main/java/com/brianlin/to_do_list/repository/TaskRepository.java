package com.brianlin.to_do_list.repository;

import com.brianlin.to_do_list.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:
 * Description:
 *
 * @Author Rin
 * @Create 2024/8/1 下午 06:14
 * @Version 1.0
 */
public class TaskRepository {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/to_do_list";  // 數據庫 URL
    private final String jdbcUsername = "root";                         // 數據庫用戶名
    private final String jdbcPassword = "09060704";                         // 數據庫密碼

    // 建立連接方法
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
    }

    // (增)創建任務方法: return id
    public int addTask(Task task) {
        String sql = "INSERT INTO task (task_name, deadline) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName()); //index指的是?的順序，1就是指第一個?。
            stmt.setTimestamp(2, task.getDeadline());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int newTaskId = getMaxId();
        return newTaskId;
    }

    // (刪)根據ID刪除任務方法
    public void deleteTask(int id) {
        String sql = "DELETE FROM task WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (查複數筆資料)查詢所有任務方法
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String taskname = rs.getString("task_name");
                Timestamp deadline = rs.getTimestamp("deadline");
//                LocalDateTime deadline1 = rs.getObject("deadline", LocalDateTime.class); 如果deadline是 LocalDateTime類的話可以這樣用
                tasks.add(new Task(id, taskname, deadline));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // (查唯一筆資料)根據ID查詢任務方法
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM task WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String taskname = rs.getString("task_name");
                Timestamp deadline = rs.getTimestamp("deadline");
                return new Task(id, taskname, deadline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // (查max id)查當前作後一項 task id
    public int getMaxId() {
        String sql = "SELECT id FROM task ORDER BY id DESC LIMIT 1";
        int id = 0;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // (改)更新任務方法
    public void updateTask(Task task) {
        String sql = "UPDATE task SET taskname = ?, deadline = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskName());
            stmt.setTimestamp(2, task.getDeadline());
            stmt.setInt(3, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
