package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.service.TaskService;
import org.primefaces.event.FileUploadEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:03
 */
@ManagedBean
public class ImageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    public ImageBean() {
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void upload(FileUploadEvent event) {
        final Task task = (Task) event.getComponent().getAttributes().get("task");
        task.setImage(event.getFile().getContents());
        taskService.updateTask(task);
    }

    public void deleteImage(Task task) {
        task.setImage(null);
        taskService.updateTask(task);
    }

}
