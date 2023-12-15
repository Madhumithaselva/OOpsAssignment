package se.lexicon;
import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public AppUser getCredentials() {
        return credentials;
    }

    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
    }

    public Person(int id, String firstName, String lastName, String email) {

        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

   /* public Person (int id,String firstName, String lastName, String email) {
        this(id, firstName, lastName);
        setEmail(email);
    }*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("First Name was null");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("Last Name was null");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email was null");
        this.email = email;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                '}';
    }

    //Ovrride hashCode() method
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, firstName, lastName, email, hashCode);
    }

//Override equals() method
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Person person = (Person) object;
        return id == person.id && hashCode == person.hashCode && java.util.Objects.equals(firstName, person.firstName) && java.util.Objects.equals(lastName, person.lastName) && java.util.Objects.equals(email, person.email);
    }


/* public String getSummary() {
        return "Person{ Id:" + id + ", \nName:" + firstName + " " + lastName + "\nEmail:" + email + "}";
    }*/

}