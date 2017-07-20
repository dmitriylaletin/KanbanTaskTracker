package com.eisgroup.tasktracker.beans;

import com.eisgroup.tasktracker.model.Status;
import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.service.TaskService;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:59
 */
@ManagedBean
@ViewScoped
public class DashboardBean implements Serializable{
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{widgetMap}")
    private transient WidgetMap widgetMap;

    @ManagedProperty(value = "#{taskService}")
    private transient TaskService taskService;

    private DashboardModel model;

    public DashboardBean() {}

    public void setWidgetMap(WidgetMap widgetMap) {
        this.widgetMap = widgetMap;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public DashboardModel getModel() {
        return model;
    }

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn columnIdea = new DefaultDashboardColumn();
        DashboardColumn columnTodo = new DefaultDashboardColumn();
        DashboardColumn columnDone = new DefaultDashboardColumn();

        for (Map.Entry<String, Task> taskEntry : widgetMap.getWidgetMap().entrySet()) {
            String widgetId = taskEntry.getKey();
            Task task = taskEntry.getValue();

            if (task.getStatus() == Status.IDEA) {
                columnIdea.addWidget(widgetId);
            }
            if (task.getStatus() == Status.TODO) {
                columnTodo.addWidget(widgetId);
            }
            if (task.getStatus() == Status.DONE) {
                columnDone.addWidget(widgetId);
            }
        }
        model.addColumn(columnIdea);
        model.addColumn(columnTodo);
        model.addColumn(columnDone);
    }

    public void handleReorder(DashboardReorderEvent event) {
        // auto collapse tasks moved to DONE column from another columns
        if (event.getColumnIndex() == Status.DONE.ordinal() &&
                event.getSenderColumnIndex() != null) {
            widgetMap.getWidgetMap().get(event.getWidgetId()).setCollapsed(true);
        }
        saveReorderedTasks();
    }

    private void saveReorderedTasks() {
        for (int column = 0; column < model.getColumns().size(); column++) {
            for (int task_order = 0; task_order < model.getColumn(column).getWidgets().size(); task_order++) {

                Task task = widgetMap.getWidgetMap().get(model.getColumn(column).getWidget(task_order));
                task.setDashboardOrder(task_order);
                task.setStatus(Status.values()[column]);
            }
        }
        taskService.updateAllTasks(widgetMap.getWidgetMap().values());
    }

    public void handleToggle(ToggleEvent event) {
        Task task = widgetMap.getWidgetMap().get(event.getComponent().getId());
        if (event.getVisibility() == Visibility.HIDDEN) {
            task.setCollapsed(true);
        } else if (event.getVisibility() == Visibility.VISIBLE) {
            task.setCollapsed(false);
        }
        taskService.updateTask(task);
    }

}
