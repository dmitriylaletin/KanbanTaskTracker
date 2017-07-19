package com.eisgroup.tasktracker.service;

import com.eisgroup.tasktracker.model.Task;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:24
 */
public interface TaskService {
    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getTaskListByUserId(int userId);

    void updateAllTasks(Collection<Task> tasks);

    void addTask(int userId, String title, Date date);
}
