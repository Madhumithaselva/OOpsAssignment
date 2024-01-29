package se.lexicon.dao;

import se.lexicon.AppUser;
import se.lexicon.Person;

import java.util.*;

public interface PersonDAO {
    Person create(Person person);

    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person update(Person person);
    boolean deleteById(int id);

}
