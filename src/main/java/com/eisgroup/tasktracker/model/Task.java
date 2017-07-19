package com.eisgroup.tasktracker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 00:54
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private String title;
    private Status status;
    private Date date;
    private int dashboardOrder;
    private boolean collapsed;
    private List<SubTask> subTasks;
    private byte[] image;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDashboardOrder() {
        return dashboardOrder;
    }

    public void setDashboardOrder(int dashboardOrder) {
        this.dashboardOrder = dashboardOrder;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

