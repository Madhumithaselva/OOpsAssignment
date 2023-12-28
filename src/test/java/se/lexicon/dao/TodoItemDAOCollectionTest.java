package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Person;
import se.lexicon.ToDoItem;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemDAOCollectionTest {

    private TodoItemDAOCollection todoItemDAO;
    private ToDoItem testItem1;
    private ToDoItem testItem2;
    private Person testPerson;

    @BeforeEach
    void setUp() {
        todoItemDAO = new TodoItemDAOCollection(new HashSet<>());
        testPerson = new Person(1,"John", "Doe", "john.doe@example.com");
        testItem1 = new ToDoItem(1,"Task 1","Register",LocalDate.now(),testPerson);
        testItem2 = new ToDoItem(2,"Task 2","Submit", LocalDate.now().plusDays(2),testPerson);
    }

    @Test
    void persist() {
        assertEquals(testItem1, todoItemDAO.persist(testItem1));
        assertEquals(testItem2, todoItemDAO.persist(testItem2));
        assertTrue(todoItemDAO.findAll().contains(testItem1));
        assertTrue(todoItemDAO.findAll().contains(testItem2));
    }

    @Test
    void findById() {
        todoItemDAO.persist(testItem1);
        assertEquals(testItem1, todoItemDAO.findById(testItem1.getId()));
    }

    @Test
    public void findAll() {
        todoItemDAO.persist(testItem1);
        todoItemDAO.persist(testItem2);
        Collection<ToDoItem> allItems = todoItemDAO.findAll();
        assertTrue(allItems.contains(testItem1));
        assertTrue(allItems.contains(testItem2));
        assertEquals(2, allItems.size());
    }

    @Test
    public void findAllByDoneStatus() {
        testItem1.setDone(true);
        todoItemDAO.persist(testItem1);
        todoItemDAO.persist(testItem2);
        Collection<ToDoItem> doneItems = todoItemDAO.findAllByDoneStatus(true);
        assertTrue(doneItems.contains(testItem1));
        assertEquals(1, doneItems.size());
    }

    @Test
    public void remove() {
        todoItemDAO.persist(testItem1);
        assertTrue(todoItemDAO.findAll().contains(testItem1));
        todoItemDAO.remove(testItem1.getId());
        assertFalse(todoItemDAO.findAll().contains(testItem1));

    }
}