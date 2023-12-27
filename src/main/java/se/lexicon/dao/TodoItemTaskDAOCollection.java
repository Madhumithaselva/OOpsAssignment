package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.TodoItemTask;
import se.lexicon.sequencers.TodoItemIdSequencer;

import java.util.*;
import java.util.Iterator;

public class TodoItemTaskDAOCollection {
    private Collection<TodoItemTask> tasks;

    public TodoItemTaskDAOCollection(Collection<TodoItemTask> tasks) {
        this.tasks = tasks;
    }

    public TodoItemTask persist(boolean assignValue, ToDoItem item, Person p1){
        TodoItemTask newTask = new TodoItemTask(TodoItemIdSequencer.getCurrentId(),item,p1);
        return tasks.add(newTask)?newTask:null;
    }
    public TodoItemTask findById(int id){
        for (TodoItemTask itemTask: tasks) {
            if (itemTask.getId() == id) {
                return itemTask;
            }
        }
        return null;
    }
    public Collection<TodoItemTask> findAll(){
        return Collections.unmodifiableCollection(tasks);
    }
    public Collection<TodoItemTask> findByAssignedStatus(boolean done){
        Set<TodoItemTask> taskByStatus = new HashSet<>();

        for (TodoItemTask t : tasks){
            if (t.isAssigned() ==true){
                taskByStatus.add(t);
            }
        }
        return taskByStatus;
    }
    public Collection<TodoItemTask> findByPersonId(int personId){
        Set<TodoItemTask> tasksByPersonId = new HashSet<>();

        for (TodoItemTask t : tasks){
            if (t.getId() == personId){
                tasksByPersonId.add(t);
            }
        }
        return tasksByPersonId;
    }
    public void remove(int id){
        Iterator<TodoItemTask> iterator = tasks.iterator();
        while(iterator.hasNext()){
            TodoItemTask task = iteator.next();
            if(task.getId()==id){
                iterator.remove();
                }
        }
    }
}
