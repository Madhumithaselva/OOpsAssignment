package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.TodoItemTask;

import java.util.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTaskDAOCollectionTest {

    private TodoItemTaskDAOCollection taskDAO;
    private ToDoItem testItem;
    private Person testPerson;
    private TodoItemTask testTask1;
    private TodoItemTask testTask2;


    @BeforeEach
    void setUp() {
        taskDAO = new TodoItemTaskDAOCollection(new HashSet<>());
        testPerson = new Person(1,"John", "Doe", "john.doe@example.com");
        testItem = new ToDoItem(1,"Task 1","Register",LocalDate.now(),testPerson);
        testTask1 = new TodoItemTask(1,testItem, testPerson);
        testTask2 = new TodoItemTask(2,testItem, testPerson);
    }

    @Test
    void persist() {
        assertEquals(testTask1, taskDAO.persist(testTask1));
        assertEquals(testTask2, taskDAO.persist(testTask2));
        assertTrue(taskDAO.findAll().contains(testTask1));
        assertTrue(taskDAO.findAll().contains(testTask2));
    }

    @Test
    void findById() {
        taskDAO.persist(testTask1);
        assertEquals(testTask1, taskDAO.findById(testTask1.getId()));
        assertNull(taskDAO.findById(999));
    }

    @Test
    void findAll() {
        taskDAO.persist(testTask1);
        taskDAO.persist(testTask2);
        Collection<TodoItemTask> allTasks = taskDAO.findAll();
        assertTrue(allTasks.contains(testTask1));
        assertTrue(allTasks.contains(testTask2));
        assertEquals(2, allTasks.size());
    }

    @Test
    void findByAssignedStatus() {
            taskDAO.persist(testTask1);
            Collection<TodoItemTask> assignedTasks = taskDAO.findByAssignedStatus(true);
            assertTrue(assignedTasks.contains(testTask1));
            assertEquals(1, assignedTasks.size());
            taskDAO.persist(testTask2);
            assignedTasks = taskDAO.findByAssignedStatus(true);
            assertTrue(assignedTasks.contains(testTask1));
            assertEquals(2, assignedTasks.size());
    }

    @Test
    void findByPersonId() {
            taskDAO.persist(testTask1);
            Collection<TodoItemTask> tasksByPersonId = taskDAO.findByPersonId(testTask1.getId());
            assertTrue(tasksByPersonId.contains(testTask1));
            assertEquals(1, tasksByPersonId.size());
            taskDAO.persist(testTask2);
        tasksByPersonId = taskDAO.findByPersonId(testTask1.getId());
        assertTrue(tasksByPersonId.contains(testTask1));
        assertEquals(2, tasksByPersonId.size());
        }

    @Test
    public void remove() {
        taskDAO.persist(testTask1);
        assertTrue(taskDAO.findAll().contains(testTask1));
        taskDAO.remove(testTask1.getId());
        assertFalse(taskDAO.findAll().contains(testTask1));

    }
}