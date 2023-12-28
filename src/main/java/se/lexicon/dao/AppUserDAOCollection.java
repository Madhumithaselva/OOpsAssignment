package se.lexicon.dao;

import se.lexicon.AppRole;
import se.lexicon.AppUser;
import se.lexicon.Person;


import java.util.*;
import java.lang.*;


public class AppUserDAOCollection implements AppUserDAO {
    private Collection<AppUser> users;

    public AppUserDAOCollection(Collection<AppUser> users) {
        this.users = users;
    }

    public AppUser persist(AppUser appUser){
        users.add(appUser);
        return appUser;
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

    public void remove(String username){
        Iterator<AppUser> iterator = users.iterator();
        while(iterator.hasNext()){
            AppUser user = iterator.next();
            if(user.getUsername().equals(username)){
                iterator.remove();
            }
        }
    }
}
