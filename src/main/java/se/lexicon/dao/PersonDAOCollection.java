package se.lexicon.dao;

import se.lexicon.AppUser;
import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.sequencers.PersonIdSequencer;

import java.util.*;
import java.lang.*;

public class PersonDAOCollection implements PersonDAO {
    private Collection<Person> person1;

    public PersonDAOCollection(Collection<Person> person1) {
        this.person1 = person1;
    }

    public Person persist(Person person){

        person1.add(person);
        return person;
    }

    public Person findById(int id){
        for (Person p : person1){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public Person findByEmail(String email){
        for (Person p : person1){
            if (p.getEmail().trim().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }

    public Collection<Person> findAll(){
        return Collections.unmodifiableCollection(person1);
    }

    public void remove(int id){
        Person removePerson = findById(id);
        if (removePerson == null){
            System.out.println("No Person to remove");
        }
        person1.remove(removePerson);
    }
   /* public void remove(int id){
        Iterator<Person> iterator = person1.iterator();
        while(iterator.hasNext()){
            Person person = iterator.next();
            if(person.getId() ==id){
                iterator.remove();
            }
        }
    }*/

}
