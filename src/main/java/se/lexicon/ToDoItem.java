package se.lexicon;

import java.time.LocalDate;

public class ToDoItem {
private int id;
private String title;
private String taskDescription;
private LocalDate deadline;
private boolean done;
private Person creator;

public ToDoItem(int id,String title,String taskDescription,LocalDate deadline,Person creator){
    this.id=id;
    setTitle(title);
    setTaskDescription(taskDescription);
    setDeadLine(deadline);
    this.creator=creator;
}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title==null && title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty!!!");
        }
            this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getDeadLine() {
          return deadline;
    }

    public void setDeadLine(LocalDate deadline) {
    if(deadline==null) {
        throw new IllegalArgumentException("Deadline cannot be null");
    }
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public Person getCreator(){
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public boolean isOverdue(){
         LocalDate currentDate=LocalDate.parse("2020-03-25");
         done=!done && currentDate.isBefore(deadline);
         return done;
    }

    //Override toString() method except Person object

    @java.lang.Override
    public java.lang.String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title=" + title +
                ", taskDescription=" + taskDescription +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }

    //Override equals() method except Person object

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ToDoItem item = (ToDoItem) object;
        return id == item.id && done == item.done && java.util.Objects.equals(title, item.title) && java.util.Objects.equals(taskDescription, item.taskDescription) && java.util.Objects.equals(deadline, item.deadline);
    }

    //Override hashCode() method except Person object
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, title, taskDescription, deadline, done);
    }

/* public String getSummary() {
        LocalDate currentDate = LocalDate.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id{ ").append(id);
        stringBuilder.append(", Title:").append(title);
        stringBuilder.append(", Description:").append(taskDescription);
        stringBuilder.append(", Deadline:").append(deadline);
        if (currentDate.isBefore(deadline)) {
            stringBuilder.append("\nTodo Item is overdue");
        } else {
            stringBuilder.append("Creator Info:").append(creator.getSummary());

        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }*/

    }


