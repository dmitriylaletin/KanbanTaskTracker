package com.eisgroup.tasktracker.dao;

import com.eisgroup.tasktracker.model.Task;

import java.util.Collection;
import java.util.List;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 00:58
 */
public interface TaskDAO {
    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTaskListByUserId(int userId);

    void updateAllTasks(Collection<Task> tasks);

    byte[] getImage(int userId, int taskId);
}
