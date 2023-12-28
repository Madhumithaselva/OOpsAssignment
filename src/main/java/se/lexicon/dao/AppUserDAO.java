package se.lexicon.dao;
import se.lexicon.AppRole;
import se.lexicon.AppUser;
import java.util.*;


public interface AppUserDAO {
    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);

}
