package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.db.MySQLConnection;

import java.util.*;
import java.time.LocalDate;
import java.lang.String;
import java.sql.*;

public class TodoItemDAOCollection implements TodoItemDAO {
    private Collection<ToDoItem> items;

    public TodoItemDAOCollection(Collection<ToDoItem> items) {
        this.items = items;
    }

    @Override
    public ToDoItem create(ToDoItem toDoItem) {

        if(toDoItem == null) throw new NullPointerException("ToDoItem is null");
        String insertQuery = "INSERT INTO todo_item (title,description,deadline,done,assignee_id) VALUES(?,?,?,?,?)";
        try(
                Connection connection= MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, toDoItem.getTitle());
            preparedStatement.setString(2, toDoItem.getTaskDescription());
            preparedStatement.setObject(3, toDoItem.getDeadLine());
            preparedStatement.setBoolean(4, toDoItem.isDone());
            if (toDoItem.getCreator()!=null){
                preparedStatement.setInt(5,toDoItem.getCreator().getId());
            }else{
                preparedStatement.setNull(5,Types.INTEGER);
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("ToDoItem created successfully");
            }

            try(ResultSet generatedKeys= preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int generatedItemId = generatedKeys.getInt(1);
                    toDoItem.setId(generatedItemId);
                    System.out.println("Generated ItemId: "+generatedItemId);
                }else {
                    System.out.println("No keys were generated");
                }
            }
        }catch (SQLException e){
                e.printStackTrace();
            }

        return toDoItem;
    }
    @Override
    public Collection<ToDoItem> findAll() {
        ToDoItem toDoItem = null;

        try(
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item");
        ){
            while(resultSet.next()){
                int itemId= resultSet.getInt("todo_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                boolean status = resultSet.getBoolean("done");
                int assigneeId = resultSet.getInt("assignee_id");

                toDoItem = new ToDoItem(itemId,title,description,deadline,status);
                items.add(toDoItem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
    @Override
    public ToDoItem findById(int id) {
        ToDoItem toDoItem = null;
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE todo_id=?");
        ){
            if (preparedStatement!=null) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {

                    if (resultSet.next()) {
                        int itemId = resultSet.getInt("todo_id");
                        String title = resultSet.getString("title");
                        String description = resultSet.getString("description");
                        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                        boolean done = resultSet.getBoolean("done");
                        int assigneeId = resultSet.getInt("assignee_id");

                        toDoItem = new ToDoItem(itemId, title, description, deadline, done);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return toDoItem;
    }

    @Override
    public Collection<ToDoItem> findByDoneStatus(boolean done) {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE done=?");
        ){
            preparedStatement.setBoolean(1,done);
            try(ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                    boolean status = resultSet.getBoolean("done");
                    int assigneeId = resultSet.getInt("assignee_id");

                    ToDoItem toDoItem = new ToDoItem(itemId, title, description, deadline, status);
                    items.add(toDoItem);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Collection<ToDoItem> findByAssignee(int id) {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id=?");
        ){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                    boolean status = resultSet.getBoolean("done");
                    int assigneeId = resultSet.getInt("assignee_id");

                    ToDoItem toDoItem = new ToDoItem(itemId, title, description, deadline, status);
                    items.add(toDoItem);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Collection<ToDoItem> findByAssignee(Person person) {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id=?");
        ){
            preparedStatement.setInt(1,person.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery();){

                while (resultSet.next()) {

                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                    boolean status = resultSet.getBoolean("done");
                    int assigneeId = resultSet.getInt("assignee_id");
                    int itemId = resultSet.getInt("todo_id");

                    ToDoItem toDoItem = new ToDoItem(itemId, title, description, deadline, status);
                    items.add(toDoItem);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Collection<ToDoItem> findByUnassignedTodoItems() {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo_item WHERE assignee_id IS NULL");
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                    boolean status = resultSet.getBoolean("done");
                    int assigneeId = resultSet.getInt("assignee_id");

                    ToDoItem toDoItem = new ToDoItem(itemId, title, description, deadline, status);
                    items.add(toDoItem);
                }

            }catch (SQLException e){
                 e.printStackTrace();
            }
        return items;
    }

    @Override
    public ToDoItem update(ToDoItem toDoItem) {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE todo_item SET title=?,description=?,deadline=?,done=?,assignee_id=? WHERE todo_id=?");
        ){
            preparedStatement.setString(1,toDoItem.getTitle());
            preparedStatement.setString(2,toDoItem.getTaskDescription());
            preparedStatement.setObject(3,toDoItem.getDeadLine());
            preparedStatement.setBoolean(4,toDoItem.isDone());
            preparedStatement.setInt(5,toDoItem.getAssigneeId());
            preparedStatement.setInt(6, toDoItem.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item with ID " + toDoItem.getId() + " updated successfully.");
                return toDoItem;
            } else {
                System.out.println("No item found with ID " + toDoItem.getId() + " to update.");
                return toDoItem;
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
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM todo_item WHERE todo_id=?");
        ){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item with ID " + id + " deleted successfully.");
                return true;
            } else {
                System.out.println("No item found with ID " + id + " to delete.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
      }
}

