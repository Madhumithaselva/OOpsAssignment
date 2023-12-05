package se.lexicon;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

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

    public String getSummary() {
        return "Person{ Id:" + id + ", Name:" + firstName + " " + lastName + ",Email:" + email + "}";
    }

}