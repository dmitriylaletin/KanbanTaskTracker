package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.model.SubTask;
import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.service.TaskService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:10
 */
@ManagedBean
@ViewScoped
public class SubTaskBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    private String newSubTask;

    public SubTaskBean() {}

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getNewSubTasks() {
        return newSubTask;
    }

    public void setNewSubTasks(String newSubTasks) {
        this.newSubTask = newSubTasks;
    }

    public void addSubTask(Task task) {
        SubTask subTask = new SubTask();
        subTask.setText(newSubTask);
        subTask.setTaskId(task.getId());
        task.getSubTasks().add(subTask);
        taskService.updateTask(task);
        newSubTask = null;
    }

    public void deleteSubTask(Task task, SubTask subTask) {
        task.getSubTasks().remove(subTask);
        taskService.updateTask(task);
    }

    public String getStyle(SubTask subTask) {
        if (subTask.isDone()) {
            return "line-through";
        } else {
            return "none";
        }
    }
}
