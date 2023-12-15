package se.lexicon;
import java.util.Objects;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person nisse=new Person(4,"Nisse","Olsson","nisse@gmail.com");
        //System.out.println(nisse.getSummary());
        LocalDate todayDate=LocalDate.parse("2020-03-25");
        ToDoItem item=new ToDoItem(4,"Change tires","Change the tyres",todayDate,nisse);
        item.setCreator(nisse);
        //System.out.println(item.getSummary());
        TodoItemTask itemTask=new TodoItemTask(4,item,nisse);
        //System.out.println(itemTask.getSummary());
    }
}