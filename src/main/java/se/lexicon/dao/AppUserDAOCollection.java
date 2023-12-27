package se.lexicon.dao;

import se.lexicon.AppRole;
import se.lexicon.AppUser;
import se.lexicon.sequencers.PersonIdSequencer;

import java.util.*;
import java.util.Collections;
import java.util.Collection;
import java.lang.*;


public class AppUserDAOCollection implements AppUserDAO {
    private Collection<AppUser> users;

    public AppUserDAOCollection(java.util.Collection<AppUser> users) {
        this.users = users;
    }

    public AppUser persist(String username, String password, AppRole role) {
        AppUser newUser = new AppUser(username, password, role);
        return users.add(newUser) ? newUser : null;
    }

    public AppUser findByUsername(String name) {

          for (AppUser a : users) {
            if (a.getUsername().trim().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public Collection<AppUser> findAll(){
        return Collections.unmodifiableCollection(users);
    }

    public boolean removeUser(AppUser user){
         return users.remove(user);
    }
}
