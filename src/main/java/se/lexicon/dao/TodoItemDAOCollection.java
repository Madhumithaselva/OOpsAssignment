package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.TodoItemTask;
import se.lexicon.sequencers.TodoItemIdSequencer;

import java.util.*;
import java.time.LocalDate;
import java.lang.String;

public class TodoItemDAOCollection implements TodoItemDAO {
    private Collection<ToDoItem> items;

    public TodoItemDAOCollection(Collection<ToDoItem> items) {
        this.items = items;
    }

    @Override
    public ToDoItem create(ToDoItem toDoItem) {
        return null;
    }
    @Override
    public Collection<ToDoItem> findAll() {

        return Collections.unmodifiableCollection(items);
    }
    @Override
    public ToDoItem findById(int id) {
        for (ToDoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Collection<ToDoItem> findByDoneStatus(boolean done) {
        return null;
    }

    @Override
    public Collection<ToDoItem> findByAssignee(int id) {
        return null;
    }

    @Override
    public Collection<ToDoItem> findByAssignee(Person person) {
        return null;
    }

    @Override
    public Collection<ToDoItem> findByUnassignedTodoItems() {
        return null;
    }

    @Override
    public Collection<ToDoItem> update(ToDoItem toDoItem) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }





}
