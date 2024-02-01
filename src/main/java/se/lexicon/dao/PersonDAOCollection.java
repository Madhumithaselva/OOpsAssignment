package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.db.MySQLConnection;
import java.sql.*;

import java.util.*;
import java.lang.*;

public class PersonDAOCollection implements PersonDAO {
    private Collection<Person> person1;

    public PersonDAOCollection(Collection<Person> person1) {
        this.person1 = person1;
    }

    @Override
    public Person create(Person person) {
        if(person == null) throw new NullPointerException("Person is null");
        String insertQuery = "INSERT INTO person (first_name,last_name) VALUES(?,?)";

        try(
                Connection connection= MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            ){
            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());

            int rowsAffected=preparedStatement.executeUpdate();

            if (rowsAffected>0){
                System.out.println("Person created successfully");
            }

            try(ResultSet generatedKeys= preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int generatedPersonId = generatedKeys.getInt(1);
                    person.setId(generatedPersonId);
                    System.out.println("Generated PersonId: "+generatedPersonId);
                }else {
                    System.out.println("No keys were generated");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return person;
    }
    @Override
    public Collection<Person> findAll(){

        try(
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
                ){
            while(resultSet.next()){
                int personId= resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Person person = new Person(personId,firstName,lastName);
                person1.add(person);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return person1;
    }

    @Override
    public Person findById(int id){
        Person person = null;
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE person_id=?");
                ){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){

                if (resultSet.next()){
                    int personId= resultSet.getInt("person_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");

                    person = new Person(personId,firstName,lastName);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return person;
    }
    @Override
    public Collection<Person> findByName(String name) {
        Person person = null;
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE first_name=?");
        ){
            preparedStatement.setString(1,name);
            try(ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()){
                    int personId= resultSet.getInt("person_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");

                    person = new Person(personId,firstName,lastName);
                    person1.add(person);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return person1;
    }
    @Override
    public Person update(Person person) {
       try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET first_name=?,last_name=? WHERE person_id=?");
        ){

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());

            System.out.println("Updated:"+person.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person with ID " + person.getId() + " updated successfully.");
                return person;
            } else {
                System.out.println("No person found with ID " + person.getId() + " to update.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE person_id=?");
        ){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Person with ID " + id + " deleted successfully.");
                return true;
            } else {
                System.out.println("No person found with ID " + id + " to delete.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
