package com.eisgroup.tasktracker.service;

import com.eisgroup.tasktracker.model.Task;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:23
 */
public interface ImageService {

    byte[] getTaskImage(int userId, int taskId);

    int getNoCacheToken(Task task);
}
