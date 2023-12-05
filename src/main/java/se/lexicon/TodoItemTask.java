package se.lexicon;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private ToDoItem toDoItem;
    private Person assignee;

    public TodoItemTask(int id,ToDoItem toDoItem,Person assignee){
        this.id=id;
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

    public String getSummary(){
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
    }
}

