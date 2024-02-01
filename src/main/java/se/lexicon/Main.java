package se.lexicon;

import se.lexicon.dao.PersonDAOCollection;
import se.lexicon.dao.TodoItemDAOCollection;

import java.util.Objects;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*Person nisse=new Person(4,"Nisse","Olsson","nisse@gmail.com");
        //System.out.println(nisse.getSummary());
        LocalDate todayDate=LocalDate.parse("2020-03-25");
        ToDoItem item=new ToDoItem(4,"Change tires","Change the tyres",todayDate,nisse);
        item.setCreator(nisse);
        //System.out.println(item.getSummary());
        TodoItemTask itemTask=new TodoItemTask(4,item,nisse);
        //System.out.println(itemTask.getSummary());*/



        //Person nisse = new Person(2,"Nisse","Olofsson","nisse@gmail.com");
        //Person Maria = new Person(3,"Maria","Andersson","maria@gmail.com");
        Collection<Person> personCollection = new ArrayList<>();
        PersonDAOCollection p1 = new PersonDAOCollection(personCollection);
        //Person personToUpdate= new Person(4,"Matilda","Pearsson","matilda@gmail.com");
        //p1.create(personToUpdate);
        //p1.findAll().forEach(System.out::println);
       // p1.findByName("Nisse").forEach(System.out::println);
        // p1.findById(1).forEach(System.out::println);
       // p1.update(personToUpdate).forEach(System.out::println);
       // p1.deleteById(1);

        Person p5 = new Person(5,"Ulrik","Olofsson","ulrik@gmail.com");
        //p1.create(p5);

        Collection<ToDoItem> itemCollection = new ArrayList<>();
        ToDoItem item1 = new ToDoItem(1,"Pickup","Pick up the items for order",LocalDate.parse("2024-02-05"),true);
        ToDoItem item2 = new ToDoItem(2,"Delivery","Delivery the items",LocalDate.parse("2024-03-03"),true);
        TodoItemDAOCollection DAOCollection1 = new TodoItemDAOCollection(itemCollection);
       // DAOCollection1.create(item2);
        //DAOCollection1.findAll().forEach(System.out::println);;
        //DAOCollection1.findById(12).forEach(System.out::println);;
        //DAOCollection1.findByDoneStatus(false).forEach(System.out::println);;
        //DAOCollection1.findByAssignee(3).forEach(System.out::println);;
       // DAOCollection1.findByAssignee(p5).forEach(System.out::println);;
        //DAOCollection1.findByUnassignedTodoItems().forEach(System.out::println);;

       // item1.setTitle("Delivery");
       // DAOCollection1.update(item1);
        DAOCollection1.deleteById(14);


    }
}