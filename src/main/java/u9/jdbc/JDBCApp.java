package u9.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/*
"The update counts for the last batch of SQL statements were 100, 20, and 50." This means that the first SQL statement updated 100 rows, the second SQL statement updated 20 rows, and the third SQL statement updated 50 rows.

execute() : The method used for all types of SQL statements, and that is, returns a boolean value of TRUE or FALSE. If the method return TRUE, return the ResultSet object and FALSE returns the int value.

executeUpdate() : This method is used for execution of DML statement(INSERT, UPDATE and DELETE) which is return int value, count of the affected rows.

executeQuery() : This method is used to retrieve data from database using SELECT query. This method returns the ResultSet object that returns the data according to the query.
*/

public class JDBCApp {

    private static Connection connection;// interface for connect base
    private static Statement statement;// interface  for query into base
    private static PreparedStatement preparedStatement;//

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
//        static {
//            try {
//                DriverManager.registerDriver(new JDBC());
//            } catch (SQLException var1) {
//                var1.printStackTrace();
//            }
//        }

//        java.util.Properties properties = new java.util.Properties();
//        properties.setProperty("user", "me");
//        properties.setProperty("password", "1234");
//        DriverManager.getConnection("jdbc:sqlite:main.dj", properties);
        //// "jdbc:posgresql://localhost:5432/jc_student","login","password"
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            connect();

            statement.execute("drop table firsttable");

            statement.execute("create table firsttable (id INTEGER primary key autoincrement, name TEXT, score INTEGER);");
            statement.execute("insert into firsttable (name, score) values ('Aaa', 11);");

            java.sql.Savepoint sp = connection.setSavepoint();// set autocommit(false)
            statement.execute("insert into firsttable (name, score) values ('Bbb', 22);");// waits commit
            connection.rollback(sp);// drops exception if there isn't savepoints // deletes waiting statements

            statement.execute("insert into firsttable (name, score) values ('Ccc', 333);");// autocommit still false!
            connection.commit();// deletes savepoints // save autocomit (false)

            connection.setAutoCommit(true);
            statement.execute("insert into firsttable (name, score) values ('Ddd', 444);");// autocommit still false!

            SEQRS("students");


        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void PSEB() throws SQLException {
        //connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?,?););");
        for (int i = 0; i < 10; ++i) {
            preparedStatement.setString(1, "Bob");
            preparedStatement.setInt(2, 50);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        //connection.commit();
        //connection.setAutoCommit(true);

    }

    //sql injection
    //String parm = Integer.toString(1);
    //String str = "SELECT FROM students WHERE id = " + parm + ";";// 1; DROP TABLE :D
    // checks existence of students, precompiled ??? // примет только указанные аргументы
    public static void PSE() throws SQLException {
        //connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?); ");
        for (int i = 0; i < 10; ++i) {
            // preparedStatement.setObject();
            preparedStatement.setString(1, "PP " + i);
            preparedStatement.setInt(2, 0);
            preparedStatement.execute();
        }
        //connection.commit();// сначала 1000 запросов улетает, потом делаем коммит и завершаем транзакцию.
        //connection.setAutoCommit(true);
    }

    public static void SEU() throws SQLException {
        //connection.setAutoCommit(false);
        for (int i = 0; i < 1000; ++i) {
            statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob', 50)");
        }
        //connection.commit();
        //connection.setAutoCommit(true);
    }

    public static void statementExecuteUpdate() throws java.sql.SQLException {
        //connection.setAutoCommit(false);

        //            statement.executeUpdate("DROP TABLE students");
        statement.executeUpdate("DELETE FROM students");
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Joe', 90 ); INSERT INTO students (name) VALUES ('Boris'); ");
//        statement.executeUpdate("INSERT INTO students (name) SELECT (name) FROM dudes; ");
        statement.executeUpdate("UPDATE students SET score = 91 WHERE name == 'Boris'; ");
        statement.executeUpdate("DELETE FROM students WHERE name = 'Boris';");
        statement.executeUpdate("DELETE FROM students ");

        //connection.commit();
        //connection.setAutoCommit(true);
    }

    public static void SEQRS(String str) throws SQLException {
        System.out.println("printing...");
        try (java.sql.ResultSet rs = statement.executeQuery("SELECT id, name, score FROM " + str + ";")) {// rs must be closed BEFORE connection.close()
            // SELECT * FROM students
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }
        } catch (java.sql.SQLException exception) {
            exception.printStackTrace();
        }
        System.out.println("printing end___________________________________");

    }


}
