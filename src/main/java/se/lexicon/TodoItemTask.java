package se.lexicon;

import se.lexicon.sequencers.TodoItemIdSequencer;

import java.lang.String;
import java.util.*;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private ToDoItem toDoItem;
    private Person assignee;

    public TodoItemTask(int id,ToDoItem toDoItem,Person assignee){
        this.id= TodoItemIdSequencer.getCurrentId();
        setToDoItem(toDoItem);
        setAssignee(assignee);
    }

    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public ToDoItem getToDoItem() {
        return toDoItem;
    }

    public void setToDoItem(ToDoItem toDoItem) {
        this.toDoItem = toDoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if (assignee!=null){
            assigned=true;
        }
        this.assignee = assignee;
    }

    //Override toString() method except person object

    @java.lang.Override
    public java.lang.String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", toDoItem=" + toDoItem +
                '}';
    }

    //Override equals() method except Person object

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        TodoItemTask itemTask = (TodoItemTask) object;
        return id == itemTask.id && assigned == itemTask.assigned && java.util.Objects.equals(toDoItem, itemTask.toDoItem);
    }

    //Override hashCode() method except Person object
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, assigned, toDoItem);
    }




    /*public String getSummary(){
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("\n==============================\n");
       stringBuilder.append("Assignee Information");
       stringBuilder.append("Id { ").append(id);
       stringBuilder.append("\nisAssigned: ").append(assigned);
       //stringBuilder.append("\nTodoItem: ").append(toDoItem.getSummary());
       //stringBuilder.append("\nAssignee:").append(assignee.getSummary());
       if(assignee!=null){
          // stringBuilder.append("ToDoItem Info: ").append(toDoItem.getSummary());
           stringBuilder.append("\n Assignee info: ").append(assignee.getSummary());
          }
       else {
           stringBuilder.append("No Assignee found");
       }
       stringBuilder.append("}");
       return stringBuilder.toString();
    }*/
}

