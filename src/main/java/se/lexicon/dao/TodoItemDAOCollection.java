package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.sequencers.TodoItemIdSequencer;

import java.util.*;
import java.time.LocalDate;

public class TodoItemDAOCollection implements TodoItemDAO{
    private Collection<ToDoItem> items;

    public TodoItemDAOCollection(Collection<ToDoItem> items) {
        this.items = items;
    }

    public ToDoItem persist(String title, String taskDescription, LocalDate deadline, boolean done, Person creator){

        ToDoItem newItem = new ToDoItem(TodoItemIdSequencer.nextId(),title,taskDescription,deadline,done,creator);

        return items.add(newItem)?newItem:null;
    }
    public ToDoItem findById(int id){
        for (ToDoItem item:items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public Collection<ToDoItem> findAll(){
        return Collections.unmodifiableCollection(items);
    }

    public Collection<ToDoItem> findAllByDoneStatus(boolean done){
        Set<ToDoItem> itemsByStatus = new HashSet<>();

        for (ToDoItem i : items){
            if (i.isDone()==true){
                itemsByStatus.add(i);
            }
        }
        return itemsByStatus;
    }

    public Collection<ToDoItem> findByTitleContains(String title){
        Set<ToDoItem> itemsByTitle = new HashSet<>();

        for (ToDoItem i : items){
            if (i.getTitle() ==title){
                itemsByTitle.add(i);
            }
        }
        return itemsByTitle;
    }
    public Collection<ToDoItem> findByPersonId(int personId){
        Set<ToDoItem> itemsByPersonId = new HashSet<>();

        for (ToDoItem i : items){
            if (i.getId() == personId){
                itemsByPersonId.add(i);
            }
        }
        return itemsByPersonId;
    }
    public Collection<ToDoItem> findByDeadlineBefore(LocalDate Date){
        Set<ToDoItem> itemsByDeadlineBefore = new HashSet<>();

        for (ToDoItem i : items){
            if (i.getDeadLine().isBefore(Date)){
                itemsByDeadlineBefore.add(i);
            }
        }
        return itemsByDeadlineBefore;
    }
    public Collection<ToDoItem> findByDeadlineAfter(LocalDate Date){
        Set<ToDoItem> itemsByDeadlineAfter = new HashSet<>();

        for (ToDoItem i : items){
            if (i.getDeadLine().isAfter(Date)){
                itemsByDeadlineAfter.add(i);
            }
        }
        return itemsByDeadlineAfter;
    }
    public boolean removeItem(ToDoItem item){
        return items.remove(item);
    }
}