package se.lexicon.dao;
import se.lexicon.AppRole;
import se.lexicon.AppUser;
import java.util.*;


public interface AppUserDAO {
    AppUser persist(String username, String password, AppRole role);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    boolean removeUser(AppUser appUser);

}
