package se.lexicon.dao;

import se.lexicon.Person;

import java.util.*;
import java.lang.*;

public class PersonDAOCollection implements PersonDAO {
    private Collection<Person> person1;

    public PersonDAOCollection(Collection<Person> person1) {
        this.person1 = person1;
    }

    @Override
    public Person create(Person person) {
        return null;
    }
    @Override
    public Collection<Person> findAll(){
        return Collections.unmodifiableCollection(person1);
    }

    @Override
    Person findById(int id){
        for (Person p : person1){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }
    @Override
    public Collection<Person> findByName(String name) {
        return null;
    }
    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
