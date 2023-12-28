package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Person;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOCollectionTest {

    private PersonDAOCollection personDAO;
    private Person testPerson1;
    private Person testPerson2;

    @BeforeEach
    void setUp() {
        personDAO = new PersonDAOCollection(new HashSet<>());
        testPerson1 = new Person(1,"John", "Doe", "john.doe@example.com");
        testPerson2 = new Person(2,"Jane", "Doe", "jane.doe@example.com");
    }

    @Test
    void persist() {
        assertEquals(testPerson1, personDAO.persist(testPerson1));
        assertEquals(testPerson2, personDAO.persist(testPerson2));
        assertTrue(personDAO.findAll().contains(testPerson1));
        assertTrue(personDAO.findAll().contains(testPerson2));
    }

    @Test
    void findById() {
        personDAO.persist(testPerson1);
        assertEquals(testPerson1, personDAO.findById(testPerson1.getId()));
        assertNull(personDAO.findById(999));
    }

    @Test
    void findByEmail() {
        personDAO.persist(testPerson1);
        assertEquals(testPerson1, personDAO.findByEmail(testPerson1.getEmail()));
        assertNull(personDAO.findByEmail("nonexistent@example.com"));
    }

    @Test
    void findAll() {
        personDAO.persist(testPerson1);
        personDAO.persist(testPerson2);
        Collection<Person> allPersons = personDAO.findAll();
        assertTrue(allPersons.contains(testPerson1));
        assertTrue(allPersons.contains(testPerson2));
        assertEquals(2, allPersons.size());
    }

    @Test
    void remove() {
        personDAO.persist(testPerson1);
        assertTrue(personDAO.findAll().contains(testPerson1));
        personDAO.remove(testPerson1.getId());
        assertFalse(personDAO.findAll().contains(testPerson1));

    }
}