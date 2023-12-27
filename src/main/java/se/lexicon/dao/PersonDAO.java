package se.lexicon.dao;

import se.lexicon.AppUser;
import se.lexicon.Person;

import java.util.*;

public interface PersonDAO {
    Person persist(String firstName, String lastName, String email, AppUser credentials);
    Person findById(int id);
    Person findByEmail(String email);
    Collection<Person> findAll();
    boolean remove(Person person);

}
