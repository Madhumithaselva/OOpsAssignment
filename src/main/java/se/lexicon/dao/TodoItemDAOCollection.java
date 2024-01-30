package se.lexicon.dao;

import com.mysql.cj.protocol.Resultset;
import se.lexicon.Person;
import se.lexicon.ToDoItem;
import se.lexicon.db.MySQLConnection;

import java.util.*;
import java.time.LocalDate;
import java.lang.String;

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
        ){
            preparedStatement.setString(1,toDoItem.getTitle());
            preparedStatement.setString(2,toDoItem.getTaskDescription());
            preparedStatement.setObject(3,toDoItem.getDeadLine());
            preparedStatement.setBoolean(4,toDoItem.isDone());
            preparedStatement.setInt(5,toDoItem.getId());

            int rowsAffected=preparedStatement.executeUpdate();

            if (rowsAffected>0){
                System.out.println("ToDoItem created successfully");
            }

            try(Resultset generatedKeys= preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    int generatedItemId = generatedKeys.getInt(1);
                    person.setId(generatedItemId);
                    System.out.println("Generated ItemId: "+generatedItemId);
                }else {
                    System.out.println("No keys were generated");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return toDoItem;
    }
    @Override
    public Collection<ToDoItem> findAll() {

        List<ToDoItem> items = new ArrayList<>();

        try(
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item");
        ){
            while(resultSet.next()){
                int itemId= resultSet.getInt("todo_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate deadline = resultSet.getObject("deadline",LocalDate.class);
                boolean done = resultSet.getBoolean("done");
                int assigneeId = resultSet.getInt("assignee_id");

                ToDoItem toDoItem = new ToDoItem(itemId,title,description,deadline,done,assigneeId);
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
                        LocalDate deadline = resultSet.getObject("deadline");
                        boolean done = resultSet.getBoolean("done");
                        int assigneeId = resultSet.getInt("assignee_id");

                        toDoItem = new ToDoItem(itemId, title, description, deadline, done, assigneeId);
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
        List<ToDoItem> items = new ArrayList<>();
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
                    LocalDate deadline = resultSet.getObject("deadline",LocalDate.class);
                    boolean status = resultSet.getBoolean("done");
                    int assigneeId = resultSet.getInt("assignee_id");

                    ToDoItem toDoItem = new ToDoItem(itemId, title, description, deadline, status, assigneeId);
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
        return null;
    }

    @Override
    public Collection<ToDoItem> findByAssignee(Person person) {
        return null;
    }

    @Override
    public Collection<ToDoItem> findByUnassignedTodoItems() {
        return null;
    }

    @Override
    public Collection<ToDoItem> update(ToDoItem toDoItem) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }





}
