package se.lexicon.dao;
import se.lexicon.Person;
import se.lexicon.ToDoItem;


import java.lang.*;
import java.util.*;
import java.time.LocalDate;

public interface TodoItemDAO {
    ToDoItem persist(String title, String taskDescription, LocalDate deadline, boolean done, Person creator);
    ToDoItem findById(int id);
    Collection<ToDoItem> findAll();
    Collection<ToDoItem> findByDoneStatus(boolean done);
    Collection<ToDoItem> findByTitleContains(String title);
    Collection<ToDoItem> findByPersonId(int personId);
    Collection<ToDoItem> findByDeadlineBefore(LocalDate end);
    Collection<ToDoItem> findByDeadlineAfter(LocalDate start);
    boolean remove(ToDoItem toDoItem);
}
