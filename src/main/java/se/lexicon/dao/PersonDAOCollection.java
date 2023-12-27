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

    public Person persist(String firstName, String lastName, String email, AppUser credentials){

        Person newPerson = new Person(PersonIdSequencer.nextId(),firstName,lastName,email,credentials);
        return person1.add(newPerson)?newPerson:null;
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
        Iterator<Person> iterator = person1.iterator();
        while(iterator.hasNext()){
            Person person = iterator.next();
            if(person.getId() ==id){
                iterator.remove();
            }
        }
    }

}
