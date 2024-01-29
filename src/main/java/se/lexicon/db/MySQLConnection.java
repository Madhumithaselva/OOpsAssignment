package se.lexicon.db;

import java.lang.String;
import java.sql.*;

public class MySQLConnection {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/todoit";
    private static final String JDBC_USERNAME="root";
    private static final String JDBC_PASSWORD="1234";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
