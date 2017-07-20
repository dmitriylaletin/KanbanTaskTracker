package com.eisgroup.tasktracker.db;

import com.eisgroup.tasktracker.dao.TaskDAO;
import com.eisgroup.tasktracker.model.Task;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 10:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:**/TestContext.xml",})
public class TaskDAOTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testAddTask() {
        int taskInitCount = countRowsInTable("tasks");

        Task testTask = getTestTask();
        taskDAO.addTask(testTask);
        int taskAfterCount = countRowsInTable("tasks");

        assertEquals("Task count must be +1 after adding one task", taskAfterCount, taskInitCount + 1);
    }

    @Test
    public void testUpdateTask() {
        Task testTask = getTestTask();
        taskDAO.addTask(testTask);
        testTask.setTitle("newUniqueTitle");
        taskDAO.updateTask(testTask);
        sessionFactory.getCurrentSession().flush();

        String query = "select title from tasks WHERE id=" + testTask.getId();
        String titleFromDb = jdbcTemplate.queryForObject(query, String.class);

        assertEquals(testTask.getTitle(), titleFromDb);
    }

    @Test
    public void testDeleteTask() {
        Task testTask = getTestTask();
        taskDAO.addTask(testTask);

        int taskInitCount = countRowsInTable("tasks");
        taskDAO.deleteTask(testTask);

        int taskAfterCount = countRowsInTable("tasks");
        assertEquals("Task count must be -1 after deleting one task", taskAfterCount, taskInitCount - 1);
    }

    @Test
    public void testGetTaskListByUserId() {
        Task testTask = getTestTask();
        taskDAO.addTask(testTask);
        taskDAO.addTask(getTestTask());
        taskDAO.addTask(getTestTask());

        final List<Task> taskList = taskDAO.getTaskListByUserId(testTask.getUserId());
        assertEquals(taskList.size(), 3);
        assertEquals(taskList.get(1).getUserId(), testTask.getUserId());
    }

    @Test
    public void testUpdateAllTasks() {
        Task testTask = getTestTask();
        taskDAO.addTask(testTask);
        taskDAO.addTask(testTask);
        taskDAO.addTask(testTask);

        final List<Task> taskList = taskDAO.getTaskListByUserId(testTask.getUserId());
        for (Task task : taskList) {
            task.setTitle("newUniqueTitle");
        }
        taskDAO.updateAllTasks(taskList);

        final List<Task> taskList2 = taskDAO.getTaskListByUserId(testTask.getUserId());
        for (Task task : taskList2) {
            assertEquals(task.getTitle(), "newUniqueTitle");
        }
    }

    @Test
    public void testGetImage() {
        Task testTask = getTestTask();
        taskDAO.addTask(testTask);

        String query = "select id from tasks WHERE title='" + testTask.getTitle() + "'";
        int testTaskId = jdbcTemplate.queryForObject(query, Integer.class);

        final byte[] image = taskDAO.getImage(testTask.getUserId(), testTaskId);
        assertArrayEquals(testTask.getImage(), image);
    }

    private Task getTestTask() {
        Task task = new Task();
        task.setCollapsed(false);
        task.setDashboardOrder(10);
        task.setDate(new Date());
        task.setImage(new byte[]{'i', 'm', 'a', 'g', 'e'});
        task.setUserId(99999);
        task.setTitle("test");
        return task;
    }
}
