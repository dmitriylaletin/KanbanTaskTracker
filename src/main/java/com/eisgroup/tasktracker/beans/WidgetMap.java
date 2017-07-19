package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.model.User;
import com.eisgroup.tasktracker.service.AuthManager;
import com.eisgroup.tasktracker.service.TaskService;
import com.eisgroup.tasktracker.utils.Constants;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 02:14
 */
@ManagedBean
@ViewScoped
public class WidgetMap implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authManager}")
    private transient AuthManager authManager;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    private Map<String, Task> widgetMap;

    public WidgetMap() {}

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public Map<String, Task> getWidgetMap() {
        return widgetMap;
    }

    @PostConstruct
    private void init() {
        widgetMap = new LinkedHashMap<>();
        User currentUser = authManager.getCurrentUser();
        final List<Task> taskList = taskService.getTaskListByUserId(currentUser.getId());
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String widgetId = Constants.TASK_ID_PREFIX + i;
            widgetMap.put(widgetId, task);
        }
    }
}
