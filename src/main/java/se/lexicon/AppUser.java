package se.lexicon;
import java.util.Objects;
import java.util.object;

public class AppUser {
    private String username;
    private String password;
    private AppRole role;

    public AppUser(String username, String password, AppRole role) {
        if (username==null||username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password==null||password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (role==null){
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username==null||username.isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password==null||password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        if(role==null){
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }
    public String toString(){
        return "AppUser {" +
                "username:" + username + "\n"+
                "Role: "+role+" }";
    }
   public int hashCode(){
        int result=17;
        result=31*result+username.hashCode();
        result=31*result+role.hashCode();
        return result;
   }

    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)) return false;
        AppUser appUser = (AppUser) object;
        return equals == appUser.equals && java.util.Objects.equals(username, appUser.username) && role == appUser.role;
    }

}
