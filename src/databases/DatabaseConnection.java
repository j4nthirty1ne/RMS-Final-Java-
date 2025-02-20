package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://202.178.125.77:3333/ros_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234567890";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
