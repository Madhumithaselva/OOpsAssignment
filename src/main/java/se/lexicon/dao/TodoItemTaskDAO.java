package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.TodoItemTask;

import java.util.*;

public interface TodoItemTaskDAO {
    TodoItemTask persist(boolean assigned, ToDoItem toDoItem, Person assignee);
    TodoItemTask findById(int id);
    Collection<TodoItemTask> findAll();
    Collection<TodoItemTask> findByAssignedStatus(boolean status);
    Collection<TodoItemTask> findByPersonId(int personId);
    boolean remove(int id);
}
