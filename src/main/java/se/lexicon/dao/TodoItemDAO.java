package se.lexicon.dao;
import se.lexicon.Person;
import se.lexicon.ToDoItem;


import java.lang.*;
import java.util.*;
import java.time.LocalDate;

public interface TodoItemDAO {
    ToDoItem create(ToDoItem toDoItem);
    Collection<ToDoItem> findAll();
    ToDoItem findById(int id);
    Collection<ToDoItem> findByDoneStatus(boolean done);
    Collection<ToDoItem> findByAssignee(int id);
    Collection<ToDoItem> findByAssignee(Person person);
    Collection<ToDoItem> findByUnassignedTodoItems();
    Collection<ToDoItem> update(ToDoItem toDoItem);
    boolean deleteById(int id);
}
