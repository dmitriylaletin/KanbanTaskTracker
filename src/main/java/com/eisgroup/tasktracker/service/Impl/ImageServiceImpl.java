package com.eisgroup.tasktracker.service.Impl;

import com.eisgroup.tasktracker.dao.TaskDAO;
import com.eisgroup.tasktracker.model.Task;
import com.eisgroup.tasktracker.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:28
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Autowired
    TaskDAO taskDAO;

    @Override
    public byte[] getTaskImage(int userId, int taskId) {
        return taskDAO.getImage(userId, taskId);
    }

    @Override
    public int getNoCacheToken(Task task) {
        final byte[] image = task.getImage();
        return image == null ? 0 : image.length;
    }
}
