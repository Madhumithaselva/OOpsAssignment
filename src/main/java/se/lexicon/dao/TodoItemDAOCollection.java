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

    public ToDoItem persist(ToDoItem toDoItem) {

        items.add(toDoItem);
        return toDoItem;
    }

    public ToDoItem findById(int id) {
        for (ToDoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Collection<ToDoItem> findAll() {
        return Collections.unmodifiableCollection(items);
    }

    public Collection<ToDoItem> findAllByDoneStatus(boolean done) {
        Set<ToDoItem> itemsByStatus = new HashSet<>();

        for (ToDoItem i : items) {
            if (i.isDone() == true) {
                itemsByStatus.add(i);
            }
        }
        return itemsByStatus;
    }

    public Collection<ToDoItem> findByTitleContains(String title) {
        Set<ToDoItem> itemsByTitle = new HashSet<>();

        for (ToDoItem i : items) {
            if (title.equals(i.getTitle())) {
                itemsByTitle.add(i);
            }
        }
        return itemsByTitle;
    }

    public Collection<ToDoItem> findByPersonId(int personId) {
        Set<ToDoItem> itemsByPersonId = new HashSet<>();

        for (ToDoItem i : items) {
            if (i.getId() == personId) {
                itemsByPersonId.add(i);
            }
        }
        return itemsByPersonId;
    }

    public Collection<ToDoItem> findByDeadlineBefore(LocalDate Date) {
        Set<ToDoItem> itemsByDeadlineBefore = new HashSet<>();

        for (ToDoItem i : items) {
            if (i.getDeadLine().isBefore(Date)) {
                itemsByDeadlineBefore.add(i);
            }
        }
        return itemsByDeadlineBefore;
    }

    public Collection<ToDoItem> findByDeadlineAfter(LocalDate Date) {
        Set<ToDoItem> itemsByDeadlineAfter = new HashSet<>();

        for (ToDoItem i : items) {
            if (i.getDeadLine().isAfter(Date)) {
                itemsByDeadlineAfter.add(i);
            }
        }
        return itemsByDeadlineAfter;
    }

    public void remove(int id) {
      ToDoItem removeItem = findById(id);
        if(removeItem ==null){
            System.out.println("No Item to remove");
        }
        items.remove(removeItem);
    }
    /*public void remove(int id){
        Iterator<ToDoItem> iterator = items.iterator();
        while(iterator.hasNext()){
            ToDoItem doItem = iterator.next();
            if(doItem.getId() ==id){
                iterator.remove();
            }
        }
    }*/
}
