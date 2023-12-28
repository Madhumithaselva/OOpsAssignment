package se.lexicon.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.AppRole;
import se.lexicon.AppUser;
import java.lang.String;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AppUserDAOCollectionTest {

    private AppUserDAOCollection userDAO;

    @BeforeEach
    void setUp(){

        Collection<AppUser> users = new HashSet<>();
        userDAO = new AppUserDAOCollection(users);

        AppUser user1 = new AppUser("ABC", "1234", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("DEF", "5678",AppRole.ROLE_APP_ADMIN);
        userDAO.persist(user1);
        userDAO.persist(user2);
    }

    @Test
    void testPersist() {
        AppUser newUser = new AppUser("John", "ABC1234",AppRole.ROLE_APP_ADMIN);
        AppUser persistedUser = userDAO.persist(newUser);

        assertNotNull(persistedUser);
        assertTrue(userDAO.findAll().contains(persistedUser));

    }

    @Test
    void testFindByUsername() {
        String existingUsername = "John";
        String nonExistingUsername = "nonExistingUser";

        AppUser foundUser = userDAO.findByUsername(existingUsername);
        assertNotNull(foundUser);
        assertEquals(existingUsername, foundUser.getUsername());

        AppUser notFoundUser = userDAO.findByUsername(nonExistingUsername);
        assertNull(notFoundUser);
    }

    @Test
    void testFindAll() {
        Collection<AppUser> allUsers = userDAO.findAll();
        assertEquals(2, allUsers.size());
    }

    @Test
    void testRemove() {
        String usernameToRemove = "John";
        userDAO.remove(usernameToRemove);

        assertNull(userDAO.findByUsername(usernameToRemove));
        assertEquals(1, userDAO.findAll().size());
    }
}