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
    public String getSummary() {
        LocalDate currentDate = LocalDate.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id{ ");
        stringBuilder.append(", Title:").append(title);
        stringBuilder.append(", Description:").append(taskDescription);
        stringBuilder.append(", Deadline:").append(deadline);
        if (currentDate.isAfter(deadline)) {
            stringBuilder.append("Creator Info:").append(creator.getSummary());
        } else {
            stringBuilder.append("Todo Item is overdue");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    }


