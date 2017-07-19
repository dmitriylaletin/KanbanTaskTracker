package com.eisgroup.tasktracker.service.Impl;

import com.eisgroup.tasktracker.dao.TaskDAO;
import com.eisgroup.tasktracker.model.Status;
import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:30
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskDAO taskDAO;

    @Override
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDAO.deleteTask(task);
    }

    @Override
    public List<Task> getTaskListByUserId(int userId) {
        return taskDAO.getTaskListByUserId(userId);
    }

    @Override
    public void updateAllTasks(Collection<Task> tasks) {
        taskDAO.updateAllTasks(tasks);
    }

    @Override
    public void addTask(int userId, String title, Date date) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(title);
        task.setDate(date);
        task.setStatus(Status.IDEA);
        task.setCollapsed(false);
        task.setDashboardOrder(99);
        taskDAO.addTask(task);
    }
}
