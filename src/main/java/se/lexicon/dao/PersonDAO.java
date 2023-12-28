package se.lexicon.dao;

import se.lexicon.AppUser;
import se.lexicon.Person;

import java.util.*;

public interface PersonDAO {
    Person persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    Collection<Person> findAll();
    void remove(int id);

}
