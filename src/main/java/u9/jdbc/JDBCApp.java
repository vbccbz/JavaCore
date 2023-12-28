package u9.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*

"The update counts for the last batch of SQL statements were 100, 20, and 50." This means that the first SQL statement updated 100 rows, the second SQL statement updated 20 rows, and the third SQL statement updated 50 rows.

execute() : The method used for all types of SQL statements, and that is, returns a boolean value of TRUE or FALSE.
If the method return TRUE, return the ResultSet object and FALSE returns the int value.

executeUpdate() : This method is used for execution of DML statement(INSERT, UPDATE and DELETE) which is return int value, count of the affected rows.

executeQuery() : This method is used to retrieve data from database using SELECT query. This method returns the ResultSet object that returns the data according to the query.
*/

public class JDBCApp {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public static void main(String[] args) {

        try {
            connect();

            preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?)");

//            batchWithAutoCommitTrue();
            batchWithAutoCommitFalse();


            int debugtrash = 123;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void clearStudents() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students;")) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void batchWithAutoCommitFalse() throws SQLException {
        clearStudents();

        for (int i = 0; i < 1000; ++i) {
            preparedStatement.setString(1, "Kek");
            preparedStatement.setInt(2, i);
            preparedStatement.addBatch();
        }
//        connection.setAutoCommit(true);
//        preparedStatement.executeBatch();// slow

        connection.setAutoCommit(false);
        preparedStatement.executeBatch();// is needed commit()
        connection.commit();// doesn't setAutoCommit(true)
//        connection.setAutoCommit(true);// does commit()
        
    }

    public static void disconnect() {
        try {
            statement.close();// better to be before connection.close()
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();// do not mistake with autoclose feature ;-)
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        // results
        // preparedstatments
    }

    public static void connect() throws SQLException {
//        java.sql.DriverManager.registerDriver(new org.sqlite.JDBC()); classpath from Maven allows to find org.sqlite.JDBC class

        try {
            Class.forName("org.sqlite.JDBC");//isn't needed in new versions???
//            class JDBC{
//            static {
//                try {
//                    java.sql.DriverManager.registerDriver(new JDBC());// DM is a singleton. org.sqlite.JDBC is an implementation of Driver
//                } catch (SQLException var1) {
//                    var1.printStackTrace();
//                }
//            }
//            ...
//            }

            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
//          sqllite creates new db if there isn't
//          java.util.Properties properties = new java.util.Properties();
//          properties.setProperty("user", "me");
//          properties.setProperty("password", "1234");
//          connection = DriverManager.getConnection("jdbc:sqlite:main.db", properties);
//          "jdbc:posgresql://localhost:5432/jc_student","login","password"

            statement = connection.createStatement();
//            query = "CREATE DATABASE lol.db;";// doesn't work this way...


        } catch (SQLException | ClassNotFoundException exception) {// Вместо двух разных удобнее бросать один
            throw new SQLException("Unable to connect");
        }

    }
}
