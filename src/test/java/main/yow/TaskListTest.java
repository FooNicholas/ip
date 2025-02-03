package main.yow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import yow.YowException;
import yow.TaskList;
import yow.Task;
import yow.ToDos;



public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddTask() {
        Task task = new ToDos("Buy milk", false);
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() throws YowException {
        Task task = new ToDos("Read book", false);
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testMarkTask() throws YowException {
        Task task = new ToDos("Complete homework", false);
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).toString().contains("[X]"));
    }

    @Test
    public void testUnmarkTask() throws YowException {
        Task task = new ToDos("Wash car", false);
        taskList.addTask(task);
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.getTask(0).toString().contains("[X]"));
    }
}
